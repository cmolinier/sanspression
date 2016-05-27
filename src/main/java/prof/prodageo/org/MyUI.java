package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.server.FileResource;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Image;

import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.ComboBox;
import java.io.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* import for explicit declaration of callback */
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button.ClickEvent;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("prof.prodageo.org.MyAppWidgetset")
public class MyUI extends UI {

        private static final Logger log = LoggerFactory.getLogger(MyUIServlet.class);

    /* explicit declaration as attributes of graphical components for GenMyModel */
        private VerticalLayout layout = new VerticalLayout();
        private HorizontalLayout layoutPrincipal = new HorizontalLayout();
        private HorizontalLayout layoutIm = new HorizontalLayout();
        private TextField name = new TextField();
        private TextField firstname = new TextField();
        private TextField pseudo = new TextField();
        private TextField email = new TextField();
        private TextField dateDeNaissance = new TextField();
        private TextField ecole = new TextField();
        private TextField formationSuivie = new TextField();
        private PasswordField motDePasse = new PasswordField();
        private PasswordField motDePasseConfime = new PasswordField();
        Button button = new Button("Validation") ;
        private ProgressBar securite = new ProgressBar(0.0f);
        private ComboBox classe = new ComboBox("Classes");
        private ImageUploader receiver = new ImageUploader();
        private Upload upload = new Upload("Selectionner un photo de profil", receiver);
        private Image image = new Image(null,new ClassResource("mario.jpg"));
        private Controleur controleur = new Controleur();
        private boolean champsValide = true;

    /* explicit callback */
    /* https://vaadin.com/docs/-/part/framework/application/application-events.html */
    public class ClickMeClass implements Button.ClickListener {
        public void buttonClick(ClickEvent event) {
          champsValide = champsValide && controleur.controlePrenomValide(name.getValue());
          champsValide = champsValide && controleur.controleNomValide(firstname.getValue());
          champsValide = champsValide && controleur.controleEmailVailde(email.getValue());
          champsValide = champsValide && controleur.controleDateDeNaissanceValide(dateDeNaissance.getValue());
          champsValide = champsValide && controleur.controleEcoleValide(ecole.getValue());
          champsValide = champsValide && controleur.controleFormationSuivieValide(formationSuivie.getValue());
          champsValide = champsValide && controleur.controleMotDePasseValide(motDePasse.getValue());
          champsValide = champsValide && controleur.controleConfirmeMotDePasseValide(motDePasseConfime.getValue());
          if (champsValide) {
            layout.addComponent(new Label("Merci " + name.getValue() + ", vous êtes désormais inscrit ! "));
            log.info("Button clicked with value : " + name.getValue());
          }
          else {
            layout.addComponent(new Label("un des champs n'est pas valide"));
          }

        }
    } //appeler le controleur ici



    @Override
    protected void init(VaadinRequest vaadinRequest) {


        // final VerticalLayout layout = new VerticalLayout();

        // final TextField name = new TextField();
        name.setCaption("Prenom");
        firstname.setCaption("Nom");
        pseudo.setCaption("Pseudo");
        dateDeNaissance.setCaption("Date de naissance");
        ecole.setCaption("Ecole");
        formationSuivie.setCaption("Formation Suivie");
        //classe deroulant
        classe.addItem("seconde");
        classe.addItem("premiere");
        classe.addItem("terminal");
        classe.addItem("BAC+1");
        classe.addItem("BAC+2");
        classe.addItem("BAC+3");
        classe.addItem("BAC+4");
        classe.addItem("BAC+5");

        email.setCaption("Email");
        motDePasse.setCaption("Mot de passe");

        motDePasseConfime.setCaption("Confirmation du mot de passe");

        //Upload de l'Image
        upload.setButtonCaption("Charger");


        ClickMeClass callback = new ClickMeClass() ;
        button.addClickListener(callback);

        layout.addComponents(name, firstname, pseudo, dateDeNaissance, ecole, formationSuivie, classe, email, motDePasse, securite, motDePasseConfime, button);
        layout.setMargin(true);
        layout.setSpacing(true);

        layoutIm.addComponents(image, upload);
        layoutIm.setMargin(true);
        layoutIm.setSpacing(true);


        layoutPrincipal.addComponent(layout);
        layoutPrincipal.addComponent(layoutIm);
        layoutPrincipal.setMargin(true);
        layoutPrincipal.setSpacing(true);

        setContent(layoutPrincipal);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}


/*
class ImageUploader implements Receiver, SucceededListener {
    public File file;

    public OutputStream receiveUpload(String filename,
                                      String mimeType) {
        // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        try {
            // Open the file for writing.
            file = new File("/tmp/uploads/" + filename);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            new Notification("Could not open file<br/>",
                             e.getMessage(),
                             Notification.Type.ERROR_MESSAGE)
                .show(Page.getCurrent());
            return null;
        }
        return fos; // Return the output stream to write to
    }

    public void uploadSucceeded(SucceededEvent event) {
        // Show the uploaded file in the image viewer
        image.setVisible(true);
        image.setSource(new FileResource(file));
    }
};
*/

class ImageUploader implements Receiver {
            private static final long serialVersionUID = -1276759102490466761L;
            final ByteArrayOutputStream os = new ByteArrayOutputStream(10240);
            public String filename; // The original filename

            public OutputStream receiveUpload(String filename, String mimeType) {
                this.filename = filename;
                os.reset(); // If re-uploading
                return os;
            }
        };
