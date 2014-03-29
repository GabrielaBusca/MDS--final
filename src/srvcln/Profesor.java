/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srvcln;

import java.io.Serializable;

/**
 *
 * @author Gabitza
 */
public class Profesor implements Serializable {
    
    private String nume,prenume, email, parola,intrebare, raspuns,categorie,ciclu,profil,stare,materie;

  public Profesor(String id, String s1, String s2, String s3, String s4, String s5, String s6, String s8, String s9, String st, String s10 ) {
    nume = s1; prenume = s2; email = s3; parola = s4; intrebare = s5; raspuns = s6;  ciclu = s8; profil =s9;
     stare = "inactiv"; materie = s10;
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
  public String rMaterie () { return materie; }
  
  @Override
  public String toString() {
    return nume + "\t" + prenume + "\t";
  }
}
