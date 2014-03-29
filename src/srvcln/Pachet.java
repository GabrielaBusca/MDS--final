/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package srvcln;

import java.io.Serializable;


public class Pachet implements Serializable {
    byte[] octeti; 
    int nr_octeti;
    Pachet(int n) { octeti = new byte[n]; 
    }
}