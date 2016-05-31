package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;



public class LogiqueMetier  {

  public LogiqueMetier() {}

  public boolean verificationMotDePasse(String mdp1, String mdp2) {
    return mdp1.equals(mdp2);
  }

  

}
