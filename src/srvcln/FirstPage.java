

package srvcln;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.Naming;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static srvcln.Cln.adresa;
import static srvcln.Cln.port;


public class FirstPage extends javax.swing.JFrame {
    static java.awt.event.MouseEvent evt1;
    static String User = null;
    public FirstPage() 
    {
        initComponents();
        setIcon();
        UserName.setFocusable(true);
        PassWord.setFocusable(true);
        UserName.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        PassWord = new javax.swing.JPasswordField();
        UserName = new javax.swing.JTextField();
        SignIn = new javax.swing.JButton();
        Cont = new javax.swing.JButton();
        Name = new javax.swing.JLabel();
        Pass = new javax.swing.JLabel();
        UseCategory = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jLabel4.setText("jLabel4");

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SH4SS");
        setBackground(new java.awt.Color(0, 51, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(350, 528));
        setMinimumSize(new java.awt.Dimension(350, 528));
        setPreferredSize(new java.awt.Dimension(350, 528));
        setResizable(false);
        getContentPane().setLayout(null);

        PassWord.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        PassWord.setForeground(new java.awt.Color(0, 0, 102));
        PassWord.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PassWordKeyPressed(evt);
            }
        });
        getContentPane().add(PassWord);
        PassWord.setBounds(100, 250, 150, 25);

        UserName.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        UserName.setForeground(new java.awt.Color(0, 0, 102));
        UserName.setMaximumSize(new java.awt.Dimension(150, 25));
        UserName.setMinimumSize(new java.awt.Dimension(150, 25));
        UserName.setPreferredSize(new java.awt.Dimension(150, 25));
        UserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UserNameKeyPressed(evt);
            }
        });
        getContentPane().add(UserName);
        UserName.setBounds(100, 210, 150, 25);
        UserName.getAccessibleContext().setAccessibleName("UserName");

        SignIn.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        SignIn.setText("Conectare");
        SignIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignInMouseClicked(evt);
            }
        });
        getContentPane().add(SignIn);
        SignIn.setBounds(110, 310, 110, 30);

        Cont.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Cont.setText("Creeare cont");
        Cont.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ContMouseClicked(evt);
            }
        });
        getContentPane().add(Cont);
        Cont.setBounds(80, 360, 170, 29);

        Name.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Name.setText("Email:");
        Name.setMaximumSize(new java.awt.Dimension(60, 14));
        Name.setMinimumSize(new java.awt.Dimension(60, 14));
        Name.setPreferredSize(new java.awt.Dimension(60, 14));
        getContentPane().add(Name);
        Name.setBounds(50, 220, 78, 14);

        Pass.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Pass.setText("Parola:");
        Pass.setMaximumSize(new java.awt.Dimension(60, 14));
        Pass.setMinimumSize(new java.awt.Dimension(60, 14));
        Pass.setPreferredSize(new java.awt.Dimension(60, 14));
        getContentPane().add(Pass);
        Pass.setBounds(50, 250, 78, 14);

        UseCategory.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        UseCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elev", "Profesor" }));
        getContentPane().add(UseCategory);
        UseCategory.setBounds(90, 170, 150, 23);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(23, 6, 13, 514);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/firstpage.jpg"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 350, 530);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 0, 0);

        jMenu3.setText("Ajutor");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/help.png"))); // NOI18N
        jMenuItem1.setText("Vizualizare Ajutor");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar3.add(jMenu3);

        setJMenuBar(jMenuBar3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ContMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContMouseClicked
        try {
            Account Acc = new Account();
            Acc.setVisible(true);
            UserName.requestFocusInWindow();
        } catch (IOException ex) {
            Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ContMouseClicked

    private void SignInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignInMouseClicked
        String MyName = null;
        boolean logu, logp;
        logu = true;
        logp = true;
        String CampPassword = null;
        String CampUsername = null;
        if (UserName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nu ati introdus numele de cont!", "Atentie", JOptionPane.INFORMATION_MESSAGE);
            logu = false;
        }
        else {
            CampUsername = UserName.getText();
            logu = true;
        }
        if (String.valueOf(PassWord.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nu ati introdus parola!", "Atentie", JOptionPane.INFORMATION_MESSAGE);
            logu = false;
        } else {
            CampPassword = String.valueOf(PassWord.getPassword());
            logp = true;
        }
        if (CampUsername.equals("manager@yahoo.com") && CampPassword.equals("123456789")){
            Director Dr = new Director();
            Dr.setVisible(true);
            this.setVisible(false);
        } else {
            
        I_BD bd = null;
        String url = "rmi://" + adresa + ":" + 1099 + "/bd";
        try {
            bd = (I_BD)Naming.lookup(url);
            if(logp == true && logu == true)
            {
                int verifica_elev = -1; int verifica_prof = -1;
                if ( UseCategory.getSelectedItem().toString().equals("Elev") )
                    verifica_elev = bd.verifica(CampUsername,CampPassword);
                else
                    verifica_prof = bd.verifica_prof(CampUsername, CampPassword);
                if( verifica_elev == 0 || verifica_prof == 0)
                    JOptionPane.showMessageDialog(null, "Username-ul introdus nu exista in baza de date!", "Atentie", JOptionPane.INFORMATION_MESSAGE);
                else
                    if(verifica_elev == 1 || verifica_prof == 1 )
                        JOptionPane.showMessageDialog(null, "Parola incorecta!", "Atentie", JOptionPane.INFORMATION_MESSAGE);
                    else if(verifica_elev == 2 || verifica_prof == 2) {
                        Socket cs = new Socket( adresa, port ); 
                        DataOutputStream os = new DataOutputStream( cs.getOutputStream()); 
                        final DataInputStream is = new DataInputStream( cs.getInputStream()); 
                        OnLine OL = null;
                        if (UseCategory.getSelectedItem().toString().equals("Elev"))
                        {
                            User = bd.danumeprenume(CampUsername);
                            
                            //os.writeUTF(User);
                            //os.writeUTF("Elev");
                            os.writeUTF("Logat," + User + "," +  "Elev" + ", ");
                            OL = new OnLine(cs, User, is, os, UseCategory.getSelectedItem().toString(), CampUsername );
                            OL.setVisible(true);
                            this.setVisible(false);
                            UserName.requestFocusInWindow();

                        }
                        else
                        {
                            System.out.println("Else");
                            User = bd.danumeprenume_prof(CampUsername);
                            
                            //os.writeUTF(User);
                            //os.writeUTF(bd.afla_materie(CampUsername));
                            os.writeUTF("Logat," + User + "," +  bd.afla_materie(CampUsername) + ", ");
                            OL = new OnLine(cs, User, is, os, bd.afla_materie(CampUsername), CampUsername );
                        }
                        OL.setVisible(true);
                        this.setVisible(false);
                        PassWord.requestFocusInWindow();
                    }
              }
        } catch (Exception ex) {
            Logger.getLogger(FirstPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_SignInMouseClicked

    private void UserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserNameKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            SignInMouseClicked(evt1);
    }//GEN-LAST:event_UserNameKeyPressed

    private void PassWordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PassWordKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            SignInMouseClicked(evt1);
    }//GEN-LAST:event_PassWordKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       try {
        Runtime r = Runtime.getRuntime();
        //r.exec("file:///D://proiect%20MDS//MDS-%20var3//help.html");
        r.exec("C:\\Program Files\\Mozilla Firefox\\firefox.exe help.html");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FirstPage().setVisible(true);
            }            
        });
 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cont;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Pass;
    private javax.swing.JPasswordField PassWord;
    private javax.swing.JButton SignIn;
    private javax.swing.JComboBox UseCategory;
    private javax.swing.JTextField UserName;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book » Bookmark.png")));
    }
}
