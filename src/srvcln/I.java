/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srvcln;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface I extends Remote {
    boolean fisierSC(String f) throws RemoteException;
    Pachet get_pachet() throws RemoteException;
    void send_pachet(Pachet p) throws RemoteException;
    void close() throws RemoteException;
    boolean fisierCS(String f) throws RemoteException;
    
} 
