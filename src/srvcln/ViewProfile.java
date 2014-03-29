

package srvcln;

import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static srvcln.Cln.adresa;


public class ViewProfile extends javax.swing.JFrame {

    static String MyName = null;
    I_BD bd = null;
    static String Categorie;
    
    public ViewProfile(String MyName, String categorie) {
        initComponents();
        setIcon();
        Categorie = categorie;
        this.MyName = MyName;
        UseLastName.setFocusable(true);
        UseLastName.requestFocusInWindow();
        UseLastName.setEditable(false);
        UseFirstName.setEditable(false);
        UseMail.setEditable(false);
        UseActualPassword.setVisible(false);
        UseNewPassword.setVisible(false);
        ActualPassword.setVisible(false);
        NewPassword.setVisible(false);
        ActualizeazaCont.setVisible(false);
        String url = "rmi://" + adresa + ":" + 1099 + "/bd";
         try { 
            bd = (I_BD)Naming.lookup(url);
            if ( Categorie.equals("Elev") )
            {
                Elev p = bd.information(MyName.split(" ")[0]);
                UseLastName.setText(p.rNume());
                UseFirstName.setText(p.rPrenume());
                UseMail.setText(p.rEmail());
                UseActualPassword.setText("");
                UseNewPassword.setText("");
                UseLastName.requestFocusInWindow();
            }
            else
            {
                Profesor prof = bd.information_prof( MyName.split(" ")[0] );
                UseLastName.setText(prof.rNume());
                UseFirstName.setText(prof.rPrenume());
                UseMail.setText(prof.rEmail());
                UseActualPassword.setText("");
                UseNewPassword.setText("");
                UseLastName.requestFocusInWindow();
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(ViewProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ViewProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ViewProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ViewProfile.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FirstName = new javax.swing.JLabel();
        LastName = new javax.swing.JLabel();
        Mail = new javax.swing.JLabel();
        NewPassword = new javax.swing.JLabel();
        ActualPassword = new javax.swing.JLabel();
        UseLastName = new javax.swing.JTextField();
        UseFirstName = new javax.swing.JTextField();
        UseMail = new javax.swing.JTextField();
        UseActualPassword = new javax.swing.JPasswordField();
        UseNewPassword = new javax.swing.JPasswordField();
        ActualizeazaCont = new javax.swing.JButton();
        Editeaza = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vizualizeaza profil");
        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(532, 501));
        setMinimumSize(new java.awt.Dimension(532, 501));
        setPreferredSize(new java.awt.Dimension(532, 501));
        setResizable(false);
        getContentPane().setLayout(null);

        FirstName.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        FirstName.setText("Nume:");
        getContentPane().add(FirstName);
        FirstName.setBounds(100, 140, 110, 23);

        LastName.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        LastName.setText("Prenume:");
        getContentPane().add(LastName);
        LastName.setBounds(100, 180, 110, 23);

        Mail.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Mail.setText("E-mail:");
        getContentPane().add(Mail);
        Mail.setBounds(100, 220, 110, 22);

        NewPassword.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        NewPassword.setText("Parola noua:");
        getContentPane().add(NewPassword);
        NewPassword.setBounds(100, 300, 110, 24);

        ActualPassword.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        ActualPassword.setText("Parola actuala:");
        getContentPane().add(ActualPassword);
        ActualPassword.setBounds(100, 260, 110, 21);

        UseLastName.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        UseLastName.setMaximumSize(new java.awt.Dimension(6, 20));
        UseLastName.setName(""); // NOI18N
        getContentPane().add(UseLastName);
        UseLastName.setBounds(220, 140, 182, 27);

        UseFirstName.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        UseFirstName.setMaximumSize(new java.awt.Dimension(6, 20));
        getContentPane().add(UseFirstName);
        UseFirstName.setBounds(220, 180, 182, 23);

        UseMail.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        UseMail.setMaximumSize(new java.awt.Dimension(6, 20));
        getContentPane().add(UseMail);
        UseMail.setBounds(220, 220, 182, 22);

        UseActualPassword.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        UseActualPassword.setMaximumSize(new java.awt.Dimension(6, 20));
        getContentPane().add(UseActualPassword);
        UseActualPassword.setBounds(220, 260, 182, 21);

        UseNewPassword.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        UseNewPassword.setMaximumSize(new java.awt.Dimension(6, 20));
        getContentPane().add(UseNewPassword);
        UseNewPassword.setBounds(220, 300, 182, 27);

        ActualizeazaCont.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        ActualizeazaCont.setText("Actualizeaza cont");
        ActualizeazaCont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActualizeazaContMouseClicked(evt);
            }
        });
        getContentPane().add(ActualizeazaCont);
        ActualizeazaCont.setBounds(130, 380, 222, 29);

        Editeaza.setBackground(new java.awt.Color(255, 255, 255));
        Editeaza.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Editeaza.setText("Editeaza");
        Editeaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditeazaActionPerformed(evt);
            }
        });
        getContentPane().add(Editeaza);
        Editeaza.setBounds(310, 340, 90, 29);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/account.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 510, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditeazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditeazaActionPerformed
        if (Editeaza.isSelected() ) {
            UseLastName.setFocusable(true);
            UseLastName.requestFocusInWindow();
            UseLastName.setEditable(true);
            UseFirstName.setEditable(true);
            UseActualPassword.setVisible(true);
            UseNewPassword.setVisible(true);
            ActualPassword.setVisible(true);
            NewPassword.setVisible(true);
            ActualizeazaCont.setVisible(true);
        } else {
            UseLastName.setEditable(false);
            UseFirstName.setEditable(false);
            UseMail.setEditable(false);
            UseActualPassword.setVisible(false);
            UseNewPassword.setVisible(false);
            ActualPassword.setVisible(false);
            NewPassword.setVisible(false);
            ActualizeazaCont.setVisible(false); 
        }
            
    }//GEN-LAST:event_EditeazaActionPerformed

    private void ActualizeazaContMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualizeazaContMouseClicked
        String Nume = UseLastName.getText();
        String Prenume = UseFirstName.getText();
        String Mail = UseMail.getText();
        String ParolaActuala = UseActualPassword.getText();
        String ParolaNoua = UseNewPassword.getText();
        try {
            System.out.println("Categorie: " + Categorie );
            int verifica_elev = -1; int verifica_prof = -1;
            if ( Categorie.equals("Elev") )
                    verifica_elev = bd.verifica(Mail,ParolaActuala);
            else
                    verifica_prof = bd.verifica_prof(Mail, ParolaActuala);
            if( verifica_elev == 0 || verifica_prof == 0)
                    JOptionPane.showMessageDialog(null, "Username-ul introdus nu exista in baza de date!", "Atentie", JOptionPane.INFORMATION_MESSAGE);
            else if(verifica_elev == 1 || verifica_prof == 1 )
                    JOptionPane.showMessageDialog(null, "Parola incorecta!", "Atentie", JOptionPane.INFORMATION_MESSAGE);
                 else if(verifica_elev == 2 || verifica_prof == 2) {
                    if (Categorie.equals("Elev"))
                         bd.cont_update(MyName.split(" ")[0], Nume,Prenume,Mail,ParolaNoua );
                    else
                         bd.cont_update_prof( MyName.split(" ")[0], Nume,Prenume,Mail,ParolaNoua );
                    }
        } catch (Exception ex) {
            Logger.getLogger(ViewProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose(); 
    }//GEN-LAST:event_ActualizeazaContMouseClicked

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
            java.util.logging.Logger.getLogger(ViewProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewProfile(MyName, Categorie).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActualPassword;
    private javax.swing.JButton ActualizeazaCont;
    private javax.swing.JCheckBox Editeaza;
    private javax.swing.JLabel FirstName;
    private javax.swing.JLabel LastName;
    private javax.swing.JLabel Mail;
    private javax.swing.JLabel NewPassword;
    private javax.swing.JPasswordField UseActualPassword;
    private javax.swing.JTextField UseFirstName;
    private javax.swing.JTextField UseLastName;
    private javax.swing.JTextField UseMail;
    private javax.swing.JPasswordField UseNewPassword;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book » Bookmark.png")));
    }
}
