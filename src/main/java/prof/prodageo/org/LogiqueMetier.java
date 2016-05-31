package prof.prodageo.org;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.ArrayList;


public class LogiqueMetier  {
  private final List<String> pseudos = new ArrayList<String>();

  public LogiqueMetier() {}

  public boolean verificationMotDePasse(String mdp1, String mdp2) {
    return mdp1.equals(mdp2);
  }

  public boolean verificationPseudo(String pseudo) {
    return !(this.pseudos.contains(pseudo));
  }

  public boolean champsNonNull(String champs) {
    return !(champs.equals(""));
  }



}
