package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;



public class Controleur {
  private LogiqueMetier lg;

  public Controleur(){
    this.lg = new LogiqueMetier();
  }

  public boolean controlePrenomValide(String prenom) {
    return lg.champsNonNull(prenom);
  }

  public boolean controleNomValide(String nom) {
    return lg.champsNonNull(nom);
  }

  public boolean controlePseudoValide(String pseudo) {
    return lg.verificationPseudo(pseudo);
  }

  public boolean controleEmailVailde(String email) {
    return lg.champsNonNull(email);
  }

  public boolean controleDateDeNaissanceValide(String dateDeNaissance) {
    return lg.champsNonNull(dateDeNaissance);
  }

  public boolean controleEcoleValide(String ecole) {
    return lg.champsNonNull(ecole);
  }

  public boolean controleFormationSuivieValide(String formationSuivie) {
    return lg.champsNonNull(formationSuivie);
  }

  public boolean controleMotDePasseValide(String mdp, String cmdp) {
    return lg.verificationMotDePasse(mdp,cmdp);
  }

}
