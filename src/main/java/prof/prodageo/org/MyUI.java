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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Slider;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.ComboBox;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Image;

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
        final VerticalLayout layoutPrincipal = new VerticalLayout();
        final HorizontaLayout layoutIm = new HorizontaLayout();
        final VerticalLayout layout = new VerticalLayout();
        final TextField name = new TextField();
        final TextField firstname = new TextField();
        final TextField email = new TextField();
        final TextField dateDeNaissance = new TextField();
        final TextField ecole = new TextField();
        final TextField formationSuivie = new TextField();
        final PasswordField motDePasse = new PasswordField();
        final PasswordField motDePasseConfime = new PasswordField();
        Button button = new Button("Validation") ;
        final Slider securite = new Slider(1, 10);
        final ComboBox classe = new ComboBox("Classes");
        final Image image = new Image(null,new ClassResource("mario.jpg"));


    /* explicit callback */
    /* https://vaadin.com/docs/-/part/framework/application/application-events.html */
    public class ClickMeClass implements Button.ClickListener
    {
        public void buttonClick(ClickEvent event)
        {
            layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
            log.info("Button clicked with value : " + name.getValue());
        }
    }



    @Override
    protected void init(VaadinRequest vaadinRequest) {


        // final VerticalLayout layout = new VerticalLayout();

        // final TextField name = new TextField();
        name.setCaption("Prenom");
        firstname.setCaption("Nom");
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
        motDePasse.setCaption("Mot de passe voulu");
        // jauge
        securite.setOrientation(SliderOrientation.HORIZONTAL);
        motDePasseConfime.setCaption("Confirmation du mot de passe");


        /*
        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
            log.info("Button clicked with value : " + name.getValue());
        });
        */
        ClickMeClass callback = new ClickMeClass() ;
        button.addClickListener(callback);

        layout.addComponents(name, firstname, dateDeNaissance, ecole, formationSuivie, classe, email, motDePasse, securite, motDePasseConfime, button, image);
        //layout.addComponent();

        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
