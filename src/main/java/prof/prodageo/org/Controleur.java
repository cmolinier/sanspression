package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;



public class Controleur {
  private LogiqueMetier lg;

  public Controleur(){
    this.lg = new LogiqueMetier();
  }

  public boolean controlePrenomValide(String prenom) {
    return true;
  }

  public boolean controleNomValide(String nom) {
    return true;
  }

  public boolean controleEmailVailde(String email) {
    return true;
  }

  public boolean controleDateDeNaissanceValide(String dateDeNaissance) {
    return true;
  }

  public boolean controleEcoleValide(String ecole) {
    return true;
  }

  public boolean controleFormationSuivieValide(String formationSuivie) {
    return true;
  }

  public boolean controleMotDePasseValide(String mdp, String cmdp) {
    return lg.verificationMotDePasse(mdp,cmdp);
  }

}
