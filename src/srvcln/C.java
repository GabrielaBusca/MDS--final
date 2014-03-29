/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srvcln;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class C{
//    String f; 
//    int lung = 10000; 
//    InputStream is;  
//    OutputStream os;
//    byte[] octeti = new byte[lung]; 

//    public C() throws RemoteException { }

//    @Override
//        public boolean fisierSC(String f) {
//        this.f = f;
//        System.out.println("S-a cerut fisierul: " + f);
//        try { 
//        is = new FileInputStream(f);
//        //is = new FileInputStream("C:\\Users\\Diana");
//        return true;
//        }
//        catch(Exception e) { 
//        System.out.println("Fisier inexistent789 !"); 
//        return false;
//    }
//  }
//    @Override
//    public boolean fisierCS(String f) {
//    this.f = f;
//    try { 
//      //os = new FileOutputStream("C:\\Users\\Diana\\Desktop\\spring.jpg");
//      os = new FileOutputStream(f);
//      System.out.println("Se incarca fisierul :" + f);
//      return true;
//    }
//    catch(FileNotFoundException e) { 
//      System.out.println("Fisierul " + f + " nu poate fi creat");
//      return false;
//    }
//  }
//
//
//    @Override
//        public Pachet get_pachet() {
//        Pachet p = new Pachet(lung); 
//        int i=0;
//        try { 
//        i = is.read(octeti,0,lung);
//        //System.out.println("S-au citit "+lung+" octeti"); 
//        for (int j=0; j<lung; j++) {
//            p.octeti[j] = octeti[j];
//        p.nr_octeti = lung;
//        //System.out.print(p.octeti[j] + " ");
//        }
//        }
//        catch(IOException e) { };
//        if(i==-1) {
//            System.out.println("Fisier transmis!"); 
//            return null; 
//        }
//        else return p;
//  }
//    @Override
//        public void send_pachet(Pachet p) { 
//        try {
//                //System.out.print("trakfnnjf");
//              for (int j=0; j<p.nr_octeti; j++) {
//                  //System.out.print(p.octeti[j]);
//                  os.write(p.octeti[j]);}
//            }
//        catch(Exception e) { }
//      }
//
//    @Override
//     public void close() {
//        try { os.close(); } catch(IOException e ) { }
//        System.out.println("S-a incarcat fisierul :" + f);
//
//}
}
