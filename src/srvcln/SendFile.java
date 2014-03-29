

package srvcln;

import java.awt.Toolkit;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static srvcln.Cln.adresa;

public class SendFile extends javax.swing.JFrame {

    static String path = "";
    static String MyName = "";
    static String Title = "";
    static DataOutputStream os;
    
    public SendFile(String MyName, String Title, DataOutputStream os) {
        initComponents();
        setIcon();
        SendFile.MyName = MyName;
        SendFile.Title = Title;
        SendFile.os = os;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Path = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Trimite fisier");
        setMaximumSize(new java.awt.Dimension(478, 389));
        setMinimumSize(new java.awt.Dimension(478, 389));
        setPreferredSize(new java.awt.Dimension(478, 389));
        setResizable(false);
        getContentPane().setLayout(null);

        Path.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        getContentPane().add(Path);
        Path.setBounds(90, 180, 310, 36);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Introduceti in campul de mai jos calea completa");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 90, 342, 37);

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(200, 250, 75, 30);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setText(" a fisierului pe care vreti sa-l trimiteti:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(100, 130, 297, 33);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/sendfile.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 495, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        path = Path.getText();
        if(path.isEmpty())
            JOptionPane.showMessageDialog(null, "Introduceti calea fisierului!", "Atentie", JOptionPane.INFORMATION_MESSAGE);
        else {
            try {
                try {
                    os.writeUTF("File," + MyName + "," + Title + "," + path);
                } catch (IOException ex) {
                    Logger.getLogger(SendFile.class.getName()).log(Level.SEVERE, null, ex);
                }
                int index = path.lastIndexOf("\\");
                String filename = path.substring(index + 1);
                InputStream ifile = null;
                try {
                    ifile = new FileInputStream(path); 
                }
                catch(FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Fisier inexistent456 !", "Atentie", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Fisier inexistent456 !");
                }
                I ref_Ob = null;
                String url = "rmi://" + adresa + ":" + 1209 + "/Ob";
                try {
                    ref_Ob = (I) Naming.lookup(url); }
                catch(Exception e) {
                    System.out.print("Conectare esuata");
                }
                boolean b = ref_Ob.fisierCS(filename);
                if (!b)
                {
                    System.out.println("Fisierul " + filename + " nu poate fi creat pe server"); 
                    JOptionPane.showMessageDialog(null, "Fisier inexistent !", "Atentie", JOptionPane.INFORMATION_MESSAGE);
                }
                int lung = 1000;
                byte[] octeti = new byte[lung];
                int i = 0;
                Pachet pc = new Pachet(lung);
                for( ; ; ) {
                    //System.out.println("trsjbfd");
                    try {
                        i = ifile.read(octeti,0,lung);
                        for (int j = 0; j < lung; j++) 
                            pc.octeti[j] = octeti[j];
                        pc.nr_octeti = lung;
                    }
                    catch(IOException e) { };
                    if(i == -1)
                        break;
                    else
                        ref_Ob.send_pachet(pc);
                    
                }
                ref_Ob.close();
                try {
                    ifile.close();
                    os.writeUTF("Ready," + MyName + "," + Title + "," + path);
                } catch (IOException ex) {
                    Logger.getLogger(SendFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(SendFile.class.getName()).log(Level.SEVERE, null, ex);
            }
          dispose();  
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SendFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SendFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SendFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SendFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendFile(MyName, Title, os).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Path;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book » Bookmark.png")));
    }
}
