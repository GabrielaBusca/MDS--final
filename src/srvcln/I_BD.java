
package srvcln;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Vector;

public interface I_BD extends Remote {   
    public void adauga(Elev p) throws Exception;
    public int verifica (String username, String password) throws Exception;
    public String danumeprenume(String mail) throws Exception;
    public Elev information ( String nume ) throws Exception;
    public void cont_update( String nume_vechi, String nume, String prenume, String email, String parola) throws Exception; 
    public void update_activ_elev (String mail ) throws Exception;
    public void inactiv_elev ( String nume ) throws Exception;
    public int afla_id_elev (String nume ) throws Exception;

    public void adauga_prof(Profesor prof) throws Exception;
    public int verifica_prof(String user, String pass) throws Exception;
    public String danumeprenume_prof ( String mail ) throws Exception;
    public String afla_materie ( String mail ) throws Exception;
    public Profesor information_prof ( String nume )  throws Exception;
    public void cont_update_prof ( String nume_vechi, String nume, String prenume, String email, String parola ) throws Exception;
    public void update_activ_prof ( String mail ) throws Exception;
    public void inactiv_prof(String nume) throws Exception;
    public int afla_id_prof(String nume) throws Exception;
    public String afla_materie_nume ( String nume ) throws Exception;
    public ArrayList<String> active_elevi(  ) throws Exception;
    public ArrayList<String> active_prof() throws Exception;
    public ArrayList<String> Categorii( ) throws Exception;
    public String afla_categorie (String nume) throws Exception;
    
    public void discutie( int nr1, int nr2 ) throws Exception;
    public void update_data_sf (int nr1, int nr2 ) throws Exception;
    public void update_satisfactie (int nr1, int nr2, String satisfactie) throws Exception;
    
    public int nr_discutii (int nr) throws Exception;
    public int durata_totala_elevi(int nr) throws Exception;
    public ArrayList<String> istoric (int nr ) throws Exception;
    
    public int nr_discutii_prof(int nr) throws Exception;
    public int durata_totala_prof (int nr) throws Exception;
    public double rating (int nr) throws Exception;
    
    public ArrayList<String> da_tot() throws Exception;
    
    
}

