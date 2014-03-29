

package srvcln;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static srvcln.Cln.adresa;

public class ReceiveFile extends javax.swing.JFrame {

    String newcale = "";
    static String filename;
    public ReceiveFile(String filename) {
        ReceiveFile.filename = filename;
        initComponents();
        setIcon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        NewPath = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Primeste fisier");
        setMaximumSize(new java.awt.Dimension(495, 420));
        setMinimumSize(new java.awt.Dimension(495, 420));
        setPreferredSize(new java.awt.Dimension(495, 420));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Introduceti in campul de mai jos calea completa");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 90, 340, 35);

        NewPath.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        NewPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPathActionPerformed(evt);
            }
        });
        getContentPane().add(NewPath);
        NewPath.setBounds(110, 170, 282, 28);

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(200, 220, 85, 27);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setText(" a fisierului pe care urmeaza sa il primiti:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(100, 130, 310, 21);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/sendfile.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 490, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        newcale = NewPath.getText();
        if(newcale.isEmpty())
            JOptionPane.showMessageDialog(null, "Introduceti calea fisierului!", "Atentie", JOptionPane.INFORMATION_MESSAGE);
        else {
            try {
                I ref_Ob = null;
                String url = "rmi://" + adresa + ":" + 1209 + "/Ob";
                try {
                    ref_Ob = (I) Naming.lookup(url);
                }
                catch(Exception e) {
                    System.out.print("Conectare esuata");}
                //System.out.print("Numele fisierului : ");
                boolean b = ref_Ob.fisierSC(filename);
                if ( !b )
                    System.out.println("Fisier inexistent123 !");
                FileOutputStream os = null;
                try {
                    os = new FileOutputStream(newcale);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ReceiveFile.class.getName()).log(Level.SEVERE, null, ex);
                }
                //am dat calea unde vreau sa ajung + numele pe care vreau sa l aiba
                Pachet p = (Pachet) ref_Ob.get_pachet();
                while( p!=null ) {
                    //System.out.println("S-au citit " + p.nr_octeti + " octeti");
                    for (int i=0; i<p.nr_octeti; i++)
                        try {
                            os.write(p.octeti[i]);
                        } catch (IOException ex) {
                            Logger.getLogger(ReceiveFile.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    p = ref_Ob.get_pachet();
                }
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(ReceiveFile.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Fisier primit !");
            } catch (RemoteException ex) {
                Logger.getLogger(ReceiveFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NewPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPathActionPerformed
    
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
            java.util.logging.Logger.getLogger(ReceiveFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiveFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiveFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiveFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReceiveFile(filename).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NewPath;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book » Bookmark.png")));
    }
}
