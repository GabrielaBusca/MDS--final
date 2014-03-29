
package srvcln;

import java.io.Serializable;

    
public class Elev implements Serializable {
  private String nume,prenume, email, parola,intrebare, raspuns,ciclu,profil,stare;

  public Elev(String id, String s1, String s2, String s3, String s4, String s5, String s6, String s8, String s9, String st ) {
    nume = s1; prenume = s2; email = s3; parola = s4; intrebare = s5; raspuns = s6;  ciclu = s8; profil =s9;
     stare = "inactiv";
  }
  
  public String rNume() { return nume; }
  public String rPrenume() { return prenume; }
  public String rEmail () { return email;}
  public String rParola () { return parola; }
  public String rIntrebare () { return intrebare; }
  public String rRaspuns () { return raspuns; }
  public String rCiclu () { return ciclu; }
  public String rProfil () { return profil; }
  public String rStare () { return stare; }

  
  @Override
  public String toString() {
    return nume + "\t" + prenume + "\t";
  }
}

    

