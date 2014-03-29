package srvcln;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import static srvcln.Cln.adresa;

public class Discution extends javax.swing.JFrame {

    static java.awt.event.MouseEvent evt1;
    static String CampNewMessage = "";
    static String Title = "";
    static Socket cs;
    static DataOutputStream os;
    static DataInputStream is;
    static String MyName = "";
    static String msg = "";
    static String path = "";


    public void setmessage(final String msg, DataOutputStream os) {
        java.awt.event.MouseEvent evt2 = new MouseEvent(jLabel3, 0, 0, 0 ,0 ,0 ,0, false, 0);
        this.msg = msg;
        jLabel3MouseClicked(evt2);
    }
    
    public void close_button (  )
    {
        addWindowListener(new WindowAdapter() { 
            @Override public void windowClosing(WindowEvent e) 
            { 
                I_BD bd = null;
                String url = "rmi://" + adresa + ":" + 1099 + "/bd";
                try {
                    bd = (I_BD)Naming.lookup(url);
                } catch (NotBoundException ex) {
                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if ( RatingCheck.getSelectedItem().equals("Satisfactie") && bd.afla_categorie(MyName).equals("Elev")  )
                        JOptionPane.showMessageDialog(null, "Nu ai selectat rezultatul convorbirii !", "Eroare", JOptionPane.ERROR_MESSAGE);
                    
                    else
                    {
                        int selection = JOptionPane.showConfirmDialog(null, "Esti sigur ca vrei sa parasesti discutia?", "Inchidere discutie", JOptionPane.YES_NO_OPTION);
                        if (selection == JOptionPane.YES_OPTION ) {
                            try {
                                os.writeUTF("Quit," + MyName + "," + Title + ", ");
                                
                                try {
                                    
                                    if ( bd.afla_categorie(MyName).equals("Elev")) {
                                        int nr1 = bd.afla_id_elev(MyName);
                                        int nr2 = bd.afla_id_prof(Title);
                                        bd.update_data_sf(nr1, nr2 );
                                        bd.update_satisfactie(nr1, nr2, RatingCheck.getSelectedItem().toString() );
                                    }
                                } catch (NotBoundException ex) {
                                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (MalformedURLException ex) {
                                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (RemoteException ex) {
                                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                dispose();
                            } catch (IOException ex) {
                                Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } }       } catch (Exception ex) {
                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                }
 } });
        } 

    public Discution(String nume, Socket s, String MyName) throws Exception {
        java.awt.event.MouseEvent evt2 = null;
        cs = s;
        try {
            os = new DataOutputStream(cs.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
        }
        I_BD bd = null;
        String url = "rmi://" + adresa + ":" + 1099 + "/bd";
        try {
            bd = (I_BD)Naming.lookup(url);
            
        } catch (NotBoundException ex) {
            Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        setIcon();
        this.MyName = MyName;
        setTitle(nume);
        Title = nume;
        if (bd.afla_categorie(MyName).equals("Profesor"))
                RatingCheck.setVisible(false);
        NewMessage.setFocusable(true);
        NewMessage.requestFocusInWindow();
        close_button();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Send = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        RatingCheck = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        AllMessages = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        NewMessage = new javax.swing.JEditorPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SH4SS");
        setMaximumSize(new java.awt.Dimension(632, 456));
        setMinimumSize(new java.awt.Dimension(632, 456));
        setPreferredSize(new java.awt.Dimension(632, 456));
        setResizable(false);
        getContentPane().setLayout(null);

        Send.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Send.setText("Trimite");
        Send.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SendMouseClicked(evt);
            }
        });
        getContentPane().add(Send);
        Send.setBounds(470, 350, 90, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/File.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(490, 290, 32, 43);

        RatingCheck.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        RatingCheck.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Satisfactie", "Multumit", "Nemultumit" }));
        RatingCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RatingCheckActionPerformed(evt);
            }
        });
        getContentPane().add(RatingCheck);
        RatingCheck.setBounds(450, 30, 100, 20);

        AllMessages.setEditable(false);
        AllMessages.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jScrollPane2.setViewportView(AllMessages);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(90, 80, 460, 200);

        NewMessage.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        NewMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NewMessageKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(NewMessage);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(90, 310, 370, 80);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/discution.jpg"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, -10, 630, 470);

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 340, 630, 120);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SendMouseClicked
        NewMessage.requestFocusInWindow();
        CampNewMessage = NewMessage.getText(); // iau ce scrie in NewMassage
        if (!NewMessage.getText().isEmpty()) {
            try {
                AllMessages.setText(AllMessages.getText() + "\n" + MyName + ": " + NewMessage.getText());
                //AllMessages.append("\n" + MyName + ": " + NewMessage.getText());
                os.writeUTF("Msg," + MyName + "," + Title + "," + CampNewMessage);
            } catch (IOException ex) {
                Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        NewMessage.setText("");
        NewMessage.requestFocusInWindow();
    }//GEN-LAST:event_SendMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        try {       
            SendFile SF = new SendFile(MyName, Title, new DataOutputStream (cs.getOutputStream()));
            SF.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try {
            AllMessages.setText(AllMessages.getText() + "\n" + msg);
        } catch (NullPointerException NPE) {
            //System.out.println("A intrat in NullPointerException");
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        
    }//GEN-LAST:event_jLabel3MousePressed

    private void RatingCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RatingCheckActionPerformed
        String val = RatingCheck.getSelectedItem().toString();
    }//GEN-LAST:event_RatingCheckActionPerformed

    private void NewMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NewMessageKeyPressed
       char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER) {
            SendMouseClicked(evt1);
        }
    }//GEN-LAST:event_NewMessageKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Discution(Title, cs, MyName).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane AllMessages;
    private javax.swing.JEditorPane NewMessage;
    private javax.swing.JComboBox RatingCheck;
    private javax.swing.JButton Send;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book » Bookmark.png"))); // seteaza Icon'ul frame'ului   
    }
}
