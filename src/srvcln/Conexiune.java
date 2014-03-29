

package srvcln;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class Conexiune extends Thread { 
  int identitate; Socket cs = null; DataInputStream is = null;  DataOutputStream os =null  ;
  
  ArrayList<DataOutputStream> sockete;
  ArrayList<String> UserName;
  ArrayList<String> Categorie;
  
  public Conexiune(Socket client, int i, ArrayList<DataOutputStream> sockete, ArrayList<String> UserName, ArrayList<String> Categorie) throws IOException
  {
    cs = client; identitate = i;  
    is = new DataInputStream(cs.getInputStream());  
    os = new DataOutputStream(cs.getOutputStream());
    this.sockete = sockete;  
    start(); 
    this.UserName = UserName;
    this.Categorie = Categorie;
  }
  
  
  public void run() {
    try {
       while (true) {
                String MSG = is.readUTF();
                String[] aux = new String[5];
                aux = MSG.split(",",4);
                if(aux[0].equals("Delogat")) {
                    for (int index = 0; index < sockete.size(); index++)
                        sockete.get(index).writeUTF(MSG);
                    for ( int index = 0; index < UserName.size(); index++)
                        if ( UserName.get(index).equals(aux[1])) {
                            UserName.remove(index);
                            sockete.remove(index);
                            Categorie.remove(index);
                        }
                } else if (aux[0].equals("Msg")) {
                    for (int index = 0; index < UserName.size(); index++) {
                        if (UserName.get(index).equals(aux[2])) { 
                            sockete.get(index).writeUTF(aux[0] + "," + aux[1] + "," + aux[2] + "," + aux[1] + ": " + aux[3]);   
                        }
                    }
                }  else if ( aux[0].equals("File")) {
//                            C Ob = new C();
//                            int port = 1209;
//                            Registry reg = LocateRegistry.createRegistry(port);
//                            reg.bind("Ob",Ob);
                            String MSG1 = is.readUTF();
                            String[] aux1 = new String[5];
                            aux1 = MSG1.split(",",4);
//                            System.out.println("Conexiune: " + aux[3]);
                            int index_nume = aux[3].lastIndexOf("\\");
                            String nume_fisier;
                            nume_fisier = aux[3].substring(index_nume+1);
                            if(aux1[0].equals("Ready"))
                            for (int index = 0; index < UserName.size(); index++) {
                                if (UserName.get(index).equals(aux[2])) { 
                                        sockete.get(index).writeUTF(aux[0] + "," + aux[1] + "," + aux[2] + "," + nume_fisier);   
                                        //sockete.get(index).writeUTF(aux[0] + "," + aux[1] + "," + aux[2] + "," + aux[3]);   
                        }
                    }
                } else if ( aux[0].equals("Quit")) {
                    for ( int i = 0; i < UserName.size(); i++ )
                        if ( UserName.get(i).equals(aux[1]))
                            sockete.get(i).writeUTF(MSG);
                }
        }
    }
    catch(Exception e) { 
        System.out.println( e.toString() ); 
    }
  }
}
