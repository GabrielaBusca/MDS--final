
package srvcln;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Srv extends UnicastRemoteObject implements I_BD, I 
{   
  static int i = 0;   
  static Statement stmt;
  
    String f; 
    int lung = 10000; 
    InputStream is;  
    OutputStream os;
    byte[] octeti = new byte[lung];
  
  Srv(Statement s) throws Exception { stmt = s; }
  
  Srv() throws RemoteException { }
 
  
   @Override
        public boolean fisierSC(String f) {
        this.f = f;
        System.out.println("S-a cerut fisierul: " + f);
        try { 
        is = new FileInputStream(f);
        //is = new FileInputStream("C:\\Users\\Diana");
        return true;
        }
        catch(Exception e) { 
        System.out.println("Fisier inexistent789 !"); 
        return false;
    }
  }
    @Override
    public boolean fisierCS(String f) {
    this.f = f;
    try { 
      //os = new FileOutputStream("C:\\Users\\Diana\\Desktop\\spring.jpg");
      os = new FileOutputStream(f);
      System.out.println("Se incarca fisierul :" + f);
      return true;
    }
    catch(FileNotFoundException e) { 
      System.out.println("Fisierul " + f + " nu poate fi creat");
      return false;
    }
  }
    @Override
        public Pachet get_pachet() {
        Pachet p = new Pachet(lung); 
        int i=0;
        try { 
        i = is.read(octeti,0,lung);
        //System.out.println("S-au citit "+lung+" octeti"); 
        for (int j=0; j<lung; j++) {
            p.octeti[j] = octeti[j];
        p.nr_octeti = lung;
        //System.out.print(p.octeti[j] + " ");
        }
        }
        catch(IOException e) { };
        if(i==-1) {
            System.out.println("Fisier transmis!"); 
            return null; 
        }
        else return p;
  }
    @Override
        public void send_pachet(Pachet p) { 
        try {
                //System.out.print("trakfnnjf");
              for (int j=0; j<p.nr_octeti; j++) {
                  //System.out.print(p.octeti[j]);
                  os.write(p.octeti[j]);}
            }
        catch(Exception e) { }
      }

    @Override
     public void close() {
        try { os.close(); } catch(IOException e ) { }
        System.out.println("S-a incarcat fisierul :" + f);

}
 
  
  
  //BAZA DE DATE
  
  
  //ADAUGA ELEV ( account )
  @Override
  public void adauga(Elev p) {
    String sql = "INSERT INTO ELEVI(ID_ELEV,NUME,PRENUME,EMAIL,PAROLA,INTREBARE_SECURITATE,RASPUNS,CICLU,PROFIL,STARE)" + " VALUES(seq_elev.nextval" + ",'" + p.rNume() + "','" + p.rPrenume() + "','" + p.rEmail() + "','" + p.rParola() + "','" + p.rIntrebare() + "','" + p.rRaspuns() +  "','" + p.rCiclu() + "','" + p.rProfil() +  "','"  + p.rStare() + "')" ;
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  public ArrayList<String> da_tot()  {
      ArrayList<String> tot = new ArrayList<>();
      String sql;
      sql = "SELECT MATERIE, NUME || ' ' || PRENUME AS NUME, COUNT(*), COUNT(distinct id_elev) as di, sum(round((ora_sfarsit-ora_inceput)*1440)) as sum from profesori p, discutii d where p.id_prof = d.id_profesor group by materie, nume || ' ' || prenume";
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() )
          {
              tot.add(rs.getString("MATERIE"));
              tot.add(rs.getString("NUME"));
              tot.add(rs.getString("COUNT(*)") + "");
              tot.add(rs.getString("di") +"");
              tot.add(rs.getString("sum") +"");
          }
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return tot;
      
  }
  
  
  public void update_data_sf(int nr1, int nr2 ) 
  {
      String sql = "UPDATE DISCUTII SET ORA_SFARSIT=sysdate WHERE ID_ELEV='" + nr1 + "' AND ID_PROFESOR='" + nr2 + "'";
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  public void update_satisfactie ( int nr1, int nr2, String satisfactie )
  {
      String sql = "UPDATE DISCUTII SET REZOLVA='" + satisfactie + "'";
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  
  //ADAUGA PROFESOR ( account ) 
    @Override
  public void adauga_prof ( Profesor prof )
  {
    String sql = "INSERT INTO PROFESORI(ID_PROF,NUME,PRENUME,EMAIL,PAROLA,INTREBARE,RASPUNS,CICLU,PROFIL,STARE,MATERIE)" + " VALUES(seq_prof.nextval" + ",'" + prof.rNume() + "','" + prof.rPrenume() + "','" + prof.rEmail() + "','" + prof.rParola() + "','" + prof.rIntrebare() + "','" + prof.rRaspuns() +  "','" + prof.rCiclu() + "','" + prof.rProfil() +  "','"  + prof.rStare() + "','" + prof.rMaterie() + "')" ;
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  //SELECTEAZA NUME PRENUME PENTRU TOTI ELEVII si profii ACTIVI (online- copac)
  @Override
  public ArrayList<String> active_elevi()
  {
      ArrayList<String> names = new ArrayList<>();
      String sql = "SELECT NUME, PRENUME FROM ELEVI WHERE STARE='activ'" ;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() ) 
          {
                names.add(rs.getString("NUME") + " " + rs.getString("PRENUME"));
        }
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      } 
      
      return names;
  }
    public ArrayList<String> active_prof()
  {
      ArrayList<String> names = new ArrayList<>();
      String sql = "SELECT NUME, PRENUME FROM PROFESORI WHERE STARE='activ'" ;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() ) 
          {
                names.add(rs.getString("NUME") + " " + rs.getString("PRENUME"));
        }
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      } 
      return names;
  }
  
  
  
  @Override
  public int afla_id_elev ( String nume )
  {
      String[] aux = new String[2];
      aux = nume.split(" ", 2);
      String sql = "SELECT ID_ELEV FROM ELEVI WHERE NUME='" + aux[0] + "' AND PRENUME='" + aux[1] + "'";
      int id = 0;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() )
              id = rs.getInt("ID_ELEV");
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return id;
  }
  
 /* public ArrayList<String> istoric ( int nr )
  {
      String sql = "SELECT MATERIE, COUNT(*), ROUND((ORA_SFARSIT - ORA_INCEPUT)*1440) FROM DISCUTII d, PROFESORI p WHERE p.id_prof = d.id_prof GROUP BY MATERIE";
  }*/
  
  public int durata_totala_elevi (int nr) {
      String sql = "SELECT SUM(ROUND((ORA_SFARSIT - ORA_INCEPUT)*1440)) AS DURATA FROM DISCUTII WHERE ID_ELEV = '" + nr + "'";
      int nr1 = 0;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while (rs.next())
              nr1 = rs.getInt("DURATA");
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return nr1;
}
      
  
    public int afla_id_prof ( String nume )
  {
      String[] aux = new String[2];
      aux = nume.split(" ", 2);
      String sql = "SELECT ID_PROF FROM PROFESORI WHERE NUME='" + aux[0] + "' AND PRENUME='" + aux[1] + "'";
      int id = 0;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() )
              id = rs.getInt("ID_PROF");
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return id;
  }
    
  public void discutie (int nr1, int nr2 )
  {
      String sql = "INSERT INTO DISCUTII(ID_ELEV, ID_PROFESOR, ORA_INCEPUT) VALUES('" + nr1 + "','" + nr2 + "', sysdate)";
      try {
          stmt.executeUpdate(sql);
      
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  public int nr_discutii (int nr)
  {
      String sql = "SELECT COUNT(*) FROM DISCUTII WHERE ID_ELEV = '" + nr + "'";
      int nr1 = 0;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while (rs.next())
              nr1 = rs.getInt("COUNT(*)");
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return nr1;
  }
  public String afla_categorie( String nume )
  {
      String[] aux = new String[2];
      aux = nume.split(" ", 2);
      String sql = "SELECT ID_ELEV FROM ELEVI WHERE NUME='" + aux[0] + "' AND PRENUME='" + aux[1] + "'";
      int a = -1;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while (rs.next())
               a = rs.getInt("ID_ELEV");

      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
                if ( a == -1 )
              return "Profesor";
          else
              return "Elev";
  }
  
  public String afla_materie_nume ( String nume )
  {
      String[] aux = nume.split(" ", 2);
              String t = null;
      String sql = "SELECT MATERIE FROM PROFESORI WHERE NUME='" + aux[0] + "' AND PRENUME='" + aux[1] + "'";
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while (rs.next())
              t = rs.getString("MATERIE");
              
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return t;
  }
  
  // categorii profi si elevi
  @Override
  public ArrayList<String> Categorii ( )
  {
      ArrayList<String> Categorii = new ArrayList<>();
      String sql = "SELECT NUME, MATERIE FROM PROFESORI WHERE STARE='activ'";
      ResultSet rs = null;
      try {
          rs = stmt.executeQuery(sql);
          while ( rs.next() )
          {
              rs.getString("NUME");
              Categorii.add(rs.getString("MATERIE"));
              
          }
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return Categorii; 
  }
  
  
  //UPDATE CONT ELEVI (editeaza)
  public void cont_update ( String nume_vechi, String nume, String prenume, String email, String parola)
  {

      String sql = "UPDATE ELEVI SET NUME='" + nume + "', PRENUME='" + prenume + "', EMAIL='" + email + "', PAROLA='" + parola + "' WHERE NUME='" + nume_vechi + "'";  
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  @Override
    public void update_activ_elev(String mail)
  {
      String sql = "UPDATE ELEVI SET STARE='activ' WHERE EMAIL='" + mail + "'";
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
    
  @Override
   public void update_activ_prof(String mail)
   {
       String sql = "UPDATE PROFESORI SET STARE='activ' WHERE EMAIL='" + mail + "'";
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
  @Override
   public void inactiv_elev(String nume)
   {
       String sql = "UPDATE ELEVI SET STARE = 'inactiv' WHERE NUME='" + nume + "'";
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   public void inactiv_prof(String nume)
   {
       String sql = "UPDATE PROFESORI SET STARE = 'inactiv' WHERE NUME='" + nume + "'";
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
  
  //UPDATE CONT PENTRU PROFESORU (editeaza)
  @Override
  public void cont_update_prof ( String nume_vechi, String nume, String prenume, String email, String parola)
  {
      String sql = "UPDATE PROFESORI SET NUME='" + nume + "', PRENUME='" + prenume + "', EMAIL='" + email + "', PAROLA='" + parola + "' WHERE NUME='" + nume_vechi + "'";    
      try {
          stmt.executeUpdate(sql);
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  //INFORMATII DESPRE UN ANUMIT ELEV DUPA MAIL (viewprofile)
  @Override
  public Elev information ( String nume ) 
  {
      String sql = "SELECT * FROM ELEVI WHERE NUME='" + nume + "'";
      Elev p = null;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() ) {
                p = new Elev(rs.getString("ID_ELEV"),
                            rs.getString("NUME"),
                            rs.getString("PRENUME"),
                            rs.getString("EMAIL"),
                            rs.getString("PAROLA"),
                            rs.getString("INTREBARE_SECURITATE"),
                            rs.getString("RASPUNS"),
                            rs.getString("CICLU"),
                            rs.getString("PROFIL"),
                            rs.getString("STARE"));
          }
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return p;
  }
  
   @Override
  public Profesor information_prof ( String nume ) 
  {
      String sql = "SELECT * FROM PROFESORI WHERE NUME='" + nume + "'";
      Profesor p = null;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() ) {
                p = new Profesor(rs.getString("ID_PROF"),
                            rs.getString("NUME"),
                            rs.getString("PRENUME"),
                            rs.getString("EMAIL"),
                            rs.getString("PAROLA"),
                            rs.getString("INTREBARE"),
                            rs.getString("RASPUNS"),
                            rs.getString("CICLU"),
                            rs.getString("PROFIL"),
                            rs.getString("STARE"),
                            rs.getString("MATERIE"));
          }
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return p;
  }

  //NUME PRENUME PENTRU ELEVUL CU MAIL (sign_in)
  @Override
  public String danumeprenume(String mail) throws Exception {
    
    String sql = "SELECT NUME, PRENUME FROM ELEVI WHERE EMAIL='" + mail +"'";
    ResultSet rs = stmt.executeQuery(sql);
    String s = null;
    while ( rs.next() ) 
             s =  rs.getString("NUME") + " " + rs.getString("PRENUME");
    return s;
    }
  
  //NUME PRENUME PENTRU PROFESORUL CU MAIL (sign_in)
  @Override
  public String danumeprenume_prof(String mail) throws Exception
  {
        String sql = "SELECT NUME, PRENUME FROM PROFESORI WHERE EMAIL='" + mail +"'";
        ResultSet rs = stmt.executeQuery(sql);
        String s = null;
        while ( rs.next() ) 
                 s =  rs.getString("NUME") + " " + rs.getString("PRENUME");
        return s;
  }
  
  //VERIFICARE USER SI PAROLA DUPA MAIL DACA EXISTA (sign_in)
  @Override
  public int verifica(String username, String password) { 
      String mail = null, parola = null;
      try 
      {
          String sql = "SELECT EMAIL, PAROLA FROM ELEVI WHERE EMAIL = '" + username + "'";
          ResultSet rs = stmt.executeQuery(sql);
          
          while ( rs.next() ) {
                mail = rs.getString("EMAIL");
                parola = rs.getString("PAROLA");
          }          
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }   
     
      if(mail == null)
              return 0;
          else
              if(!password.equals(parola))
                  return 1;
      return 2;
  }
  
  //VERIFICA PROF DUPA MAIL SI USER(sign_in)
  @Override
  public int verifica_prof(String username, String password) { 
      String mail = null, parola = null;
      try 
      {
          String sql = "SELECT EMAIL, PAROLA FROM PROFESORI WHERE EMAIL = '" + username + "'";
          ResultSet rs = stmt.executeQuery(sql);
          
          while ( rs.next() ) {
                mail = rs.getString("EMAIL");
                parola = rs.getString("PAROLA");
          }          
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }   
     
      if(mail == null)
              return 0;
          else
              if(!password.equals(parola))
                  return 1;
      return 2;
  }
  
  //afla materia unui prof cu mail-ul (sign_in)
  @Override
  public String afla_materie ( String mail ) throws Exception
  {
      String sql = "SELECT MATERIE FROM PROFESORI WHERE EMAIL = '" + mail + "'";
      String materie = null;
      ResultSet rs = stmt.executeQuery(sql);
      while ( rs.next() )
          materie = rs.getString("MATERIE");
      return materie;
  }
  
  
  public static void main(String[] arg) throws Exception {

    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    
    String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
    Connection con = DriverManager.getConnection(url,"ConquerorTeam","oracle");
    System.out.println("Conectat");
    Statement stmt = con.createStatement();

    Srv bd = new Srv(stmt);
    
    Registry reg = LocateRegistry.createRegistry(1099);
    reg.bind("bd", bd); 
    
    Srv Ob = new Srv();
    Registry reg2 = LocateRegistry.createRegistry(1209);
    reg2.bind("Ob",Ob);
    
    
    ArrayList<DataOutputStream> sockete = new ArrayList<DataOutputStream>();
    ArrayList<String> UserName = new ArrayList<String>();
    ArrayList<String> Categorie = new ArrayList<String>();
    ServerSocket ss = null; Socket cs = null;
    
    int port  = 1111;
    ss = new ServerSocket( 1111 );  
    while (true) { 
      cs = ss.accept(); 
      sockete.add(new DataOutputStream(cs.getOutputStream())); 
      DataInputStream isnk = new DataInputStream(cs.getInputStream());

      String MSG = isnk.readUTF();
      String[] aux = new String[10];
      aux = MSG.split(",",4);
      if (aux[0].equals("Logat")) {
        UserName.add(aux[1]);
        Categorie.add(aux[2]);
        //trimite la toti cine sa logat
        for (int index = 0; index < sockete.size(); index++) {
            sockete.get(index).writeUTF(MSG);
        }
      }
      System.out.println("\nClient nou. ");
      new Conexiune(cs, ++i, sockete, UserName, Categorie); 
    }
  }

    @Override
    public int nr_discutii_prof(int nr) {
        String sql = "SELECT SUM(ROUND((ORA_SFARSIT - ORA_INCEPUT)*1440)) AS DURATA FROM DISCUTII WHERE ID_PROFESOR = '" + nr + "'";
      int nr1 = 0;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while (rs.next())
              nr1 = rs.getInt("DURATA");
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return nr1;
    }

    @Override
    public int durata_totala_prof(int nr) {
        String sql = "SELECT SUM(ROUND((ORA_SFARSIT - ORA_INCEPUT)*1440)) AS DURATA FROM DISCUTII WHERE ID_PROFESOR = '" + nr + "'";
      int nr1 = 0;
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while (rs.next())
              nr1 = rs.getInt("DURATA");
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
      return nr1;
    }

    @Override
    public double rating(int nr)  {
        double rat = 0;
        String sql = "select  sum(decode(rezolva,'Multumit',1,0))*100/count(*) as rating from  discutii where id_profesor='" + nr + "'";
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() )
              rat = rs.getDouble("rating");
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
       return rat; 
    }
    
    public ArrayList<String> istoric (int nr)
    {
        ArrayList<String> istoric = new ArrayList<>();
        String sql = "select materie, nume || ' ' || prenume as nume, count(*) as total, sum(round((ora_sfarsit-ora_inceput)*1440)) as durata from profesori p, discutii d where p.id_prof = d.id_profesor and id_elev = '" + nr + "' group by materie, nume || ' ' || prenume";
      try {
          ResultSet rs = stmt.executeQuery(sql);
          while ( rs.next() )
          {
              istoric.add(rs.getString("materie"));
              istoric.add(rs.getString("nume"));
              istoric.add(rs.getString("total"));
              istoric.add(rs.getString("durata"));
          }
              
      } catch (SQLException ex) {
          Logger.getLogger(Srv.class.getName()).log(Level.SEVERE, null, ex);
      }
         return istoric; 
    }
}

