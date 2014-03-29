package srvcln;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.Naming;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static srvcln.Cln.adresa;


public class Account extends javax.swing.JFrame {

    static java.awt.event.MouseEvent evt1;
 
    
    public Account() throws IOException {
        initComponents();
        setIcon();
        UseCycle.setVisible(false);
        UseProfile.setVisible(false);
        UseSubject.setVisible(false);
        UseCategory.setFocusable(true);
        UseCategory.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem4 = new javax.swing.JRadioButtonMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jRadioButtonMenuItem5 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem6 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem7 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem8 = new javax.swing.JRadioButtonMenuItem();
        jLabel1 = new javax.swing.JLabel();
        LastName = new javax.swing.JLabel();
        UseLastName = new javax.swing.JTextField();
        FirstName = new javax.swing.JLabel();
        UseFirstName = new javax.swing.JTextField();
        Mail = new javax.swing.JLabel();
        UseMail = new javax.swing.JTextField();
        PassWord = new javax.swing.JLabel();
        UsePassword = new javax.swing.JPasswordField();
        ConfirmPassword = new javax.swing.JLabel();
        UseConfirmPassword = new javax.swing.JPasswordField();
        UseQuestion = new javax.swing.JComboBox();
        Question = new javax.swing.JLabel();
        Ok = new javax.swing.JButton();
        Category = new javax.swing.JLabel();
        UseCategory = new javax.swing.JComboBox();
        UseAnswer = new javax.swing.JTextField();
        Answer = new javax.swing.JLabel();
        Profile = new javax.swing.JLabel();
        UseProfile = new javax.swing.JComboBox();
        Cycle = new javax.swing.JLabel();
        UseCycle = new javax.swing.JComboBox();
        UseSubject = new javax.swing.JComboBox();
        materie = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("jRadioButtonMenuItem2");

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("jRadioButtonMenuItem3");

        jRadioButtonMenuItem4.setSelected(true);
        jRadioButtonMenuItem4.setText("jRadioButtonMenuItem4");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        jRadioButtonMenuItem5.setSelected(true);
        jRadioButtonMenuItem5.setText("jRadioButtonMenuItem5");

        jRadioButtonMenuItem6.setSelected(true);
        jRadioButtonMenuItem6.setText("jRadioButtonMenuItem6");

        jRadioButtonMenuItem7.setSelected(true);
        jRadioButtonMenuItem7.setText("jRadioButtonMenuItem7");

        jMenuItem1.setText("jMenuItem1");

        jRadioButtonMenuItem8.setSelected(true);
        jRadioButtonMenuItem8.setText("jRadioButtonMenuItem8");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Creeaza cont");
        setBackground(new java.awt.Color(153, 153, 255));
        setMaximumSize(new java.awt.Dimension(527, 509));
        setMinimumSize(new java.awt.Dimension(527, 509));
        setPreferredSize(new java.awt.Dimension(527, 509));
        setResizable(false);
        getContentPane().setLayout(null);

        LastName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        LastName.setText("Nume: *");
        getContentPane().add(LastName);
        LastName.setBounds(110, 90, 60, 14);

        UseLastName.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UseLastNameKeyPressed(evt);
            }
        });
        getContentPane().add(UseLastName);
        UseLastName.setBounds(240, 90, 200, 24);

        FirstName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        FirstName.setText("Prenume: *");
        getContentPane().add(FirstName);
        FirstName.setBounds(110, 120, 109, 17);

        UseFirstName.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UseFirstNameKeyPressed(evt);
            }
        });
        getContentPane().add(UseFirstName);
        UseFirstName.setBounds(240, 120, 200, 24);

        Mail.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Mail.setText("E-mail: *");
        getContentPane().add(Mail);
        Mail.setBounds(110, 150, 109, 14);

        UseMail.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UseMailKeyPressed(evt);
            }
        });
        getContentPane().add(UseMail);
        UseMail.setBounds(240, 150, 200, 24);

        PassWord.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        PassWord.setText("Parola: *");
        getContentPane().add(PassWord);
        PassWord.setBounds(110, 180, 109, 14);

        UsePassword.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UsePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsePasswordActionPerformed(evt);
            }
        });
        UsePassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsePasswordKeyPressed(evt);
            }
        });
        getContentPane().add(UsePassword);
        UsePassword.setBounds(240, 180, 200, 24);

        ConfirmPassword.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        ConfirmPassword.setText("Confirmare parola: *");
        getContentPane().add(ConfirmPassword);
        ConfirmPassword.setBounds(110, 210, 120, 14);

        UseConfirmPassword.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UseConfirmPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(UseConfirmPassword);
        UseConfirmPassword.setBounds(240, 210, 200, 24);

        UseQuestion.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseQuestion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Care este animalul favorit?", "Care este numele mamei?", "Unde s-a nascut tatal ?", "Care este orasul tau preferat?" }));
        UseQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UseQuestionActionPerformed(evt);
            }
        });
        UseQuestion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UseQuestionKeyPressed(evt);
            }
        });
        getContentPane().add(UseQuestion);
        UseQuestion.setBounds(240, 240, 200, 24);

        Question.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Question.setText("Intrebare de securitate:");
        getContentPane().add(Question);
        Question.setBounds(110, 240, 123, 20);

        Ok.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Ok.setText("Creeaza cont");
        Ok.setToolTipText("Creaza un cont nou ");
        Ok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OkMouseClicked(evt);
            }
        });
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });
        getContentPane().add(Ok);
        Ok.setBounds(20, 440, 190, 30);

        Category.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Category.setText("Categorie: *");
        getContentPane().add(Category);
        Category.setBounds(110, 300, 109, 14);

        UseCategory.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alege Profil", "Profesor", "Elev" }));
        UseCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UseCategoryActionPerformed(evt);
            }
        });
        UseCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UseCategoryKeyPressed(evt);
            }
        });
        getContentPane().add(UseCategory);
        UseCategory.setBounds(240, 300, 200, 24);

        UseAnswer.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        getContentPane().add(UseAnswer);
        UseAnswer.setBounds(240, 270, 200, 24);

        Answer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Answer.setText("Raspuns:");
        getContentPane().add(Answer);
        Answer.setBounds(110, 270, 109, 14);

        Profile.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Profile.setText("Profil: *");
        getContentPane().add(Profile);
        Profile.setBounds(110, 360, 50, 14);

        UseProfile.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseProfile.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matematica-Informatica", "Filologie", "Stiintele naturii" }));
        getContentPane().add(UseProfile);
        UseProfile.setBounds(240, 360, 200, 24);

        Cycle.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        Cycle.setText("Ciclu: *");
        getContentPane().add(Cycle);
        Cycle.setBounds(110, 330, 42, 14);

        UseCycle.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseCycle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scoala generala", "Liceu" }));
        getContentPane().add(UseCycle);
        UseCycle.setBounds(240, 330, 200, 24);

        UseSubject.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        UseSubject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matematica", "Informatica", "Istorie", "Geografie", "Biologie", "Fizica", "Chimie", "Limba si literatura romana" }));
        getContentPane().add(UseSubject);
        UseSubject.setBounds(240, 390, 200, 24);

        materie.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        materie.setText("Materie: *");
        getContentPane().add(materie);
        materie.setBounds(110, 390, 60, 14);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/account.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(523, 512));
        jLabel2.setMinimumSize(new java.awt.Dimension(523, 512));
        jLabel2.setPreferredSize(new java.awt.Dimension(523, 512));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 520, 510);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OkMouseClicked

    }//GEN-LAST:event_OkMouseClicked

    private void UseCategoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UseCategoryKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            OkMouseClicked(evt1);
    }//GEN-LAST:event_UseCategoryKeyPressed

    private void UseQuestionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UseQuestionKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            OkMouseClicked(evt1);
    }//GEN-LAST:event_UseQuestionKeyPressed

    private void UseConfirmPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UseConfirmPasswordKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            OkMouseClicked(evt1);
    }//GEN-LAST:event_UseConfirmPasswordKeyPressed

    private void UsePasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsePasswordKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            OkMouseClicked(evt1);
    }//GEN-LAST:event_UsePasswordKeyPressed

    private void UseMailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UseMailKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            OkMouseClicked(evt1);
    }//GEN-LAST:event_UseMailKeyPressed

    private void UseFirstNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UseFirstNameKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            OkMouseClicked(evt1);
    }//GEN-LAST:event_UseFirstNameKeyPressed

    private void UseLastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UseLastNameKeyPressed
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER)
            OkMouseClicked(evt1);
    }//GEN-LAST:event_UseLastNameKeyPressed

    private void UseQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UseQuestionActionPerformed

    }//GEN-LAST:event_UseQuestionActionPerformed

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed

            String CampLastName = null, CampFirstName = null, CampMail = null, CampPassword = null, CampConfirmPassword = null;
            String CampQuestion = null, CampAnswer = null, CampCycle = null, CampProfile = null, CampCategory = null;
            String CampActiv = "inactic", CampSubject = null;
            boolean incorect = false;
            
            if(UseLastName.getText().isEmpty()) 
            {   JOptionPane.showMessageDialog(null, "Nu ati introdus numele!", "Lipsa Date", JOptionPane.ERROR_MESSAGE);
                incorect = true;
            }
            else
                if(UseFirstName.getText().isEmpty()) 
                {   JOptionPane.showMessageDialog(null, "Nu ati introdus prenumele!", "Lipsa Date", JOptionPane.ERROR_MESSAGE);
                    incorect = true;
                }
                else
                    if(UseMail.getText().isEmpty()) 
                    {   JOptionPane.showMessageDialog(null, "Nu ati introdus emailul!", "Lipsa Date", JOptionPane.ERROR_MESSAGE);
                        incorect = true;
                    }
                    else
                        if(UseAnswer.getText().isEmpty()) 
                        {   JOptionPane.showMessageDialog(null, "Nu ati introdus raspuns la intrebarea de securitate!", "Lipsa Date", JOptionPane.ERROR_MESSAGE);
                            incorect = true;
                        }
                        else
                            if(String.valueOf(UsePassword.getPassword()).isEmpty()) 
                            {   JOptionPane.showMessageDialog(null, "Nu ati introdus parola!", "Lipsa Date", JOptionPane.ERROR_MESSAGE);
                                incorect = true;
                            }
                            else
                                if(String.valueOf(UseConfirmPassword.getPassword()).isEmpty()) 
                                {   JOptionPane.showMessageDialog(null, "Nu ati introdus verificarea parolei!", "Lipsa Date", JOptionPane.ERROR_MESSAGE);
                                    incorect = true;
                                }
                                else
                                if(!UseLastName.getText().matches("[a-zA-Z ]+"))
                                    {   JOptionPane.showMessageDialog(null, "Numele nu este valid!", "Camp incorect!", JOptionPane.ERROR_MESSAGE);
                                        incorect = true;
                                    }
                                    else
                                        if(!UseFirstName.getText().matches("[a-zA-Z ]+"))
                                        {   JOptionPane.showMessageDialog(null, "Numele nu este valid!", "Camp incorect!", JOptionPane.ERROR_MESSAGE);
                                            incorect = true;
                                        }
                                        else
                                            if(UseMail.getText().contains(" ")||!UseMail.getText().matches(".+@.+\\.[a-z]+"))
                                            {   JOptionPane.showMessageDialog(null, "Mailul nu este valid!", "Camp incorect!", JOptionPane.ERROR_MESSAGE);
                                                incorect = true;
                                            }
                                            else
                                                if(String.valueOf(UsePassword.getPassword()).length()<6)
                                                {   JOptionPane.showMessageDialog(null, "Parola prea scurta!", "Camp incorect!", JOptionPane.ERROR_MESSAGE);
                                                    incorect = true;
                                                }
                                                else
                                                    if(!String.valueOf(UseConfirmPassword.getPassword()).matches(String.valueOf(UsePassword.getPassword())))
                                                    {   JOptionPane.showMessageDialog(null, "Parola de confirmare nu corespunde!", "Camp incorect!", JOptionPane.ERROR_MESSAGE);
                                                        incorect = true;
                                                    }
            
            boolean adauga = false;
            if( incorect == false )
             {
                CampLastName = UseLastName.getText();
                CampFirstName = UseFirstName.getText();
                CampMail = UseMail.getText();
                CampPassword = String.valueOf(UsePassword.getPassword());
                CampConfirmPassword = String.valueOf(UseConfirmPassword.getPassword());
                CampCycle = (String)UseCycle.getSelectedItem();
                CampProfile = (String)UseProfile.getSelectedItem();
                CampQuestion = (String)UseQuestion.getSelectedItem();
                CampAnswer = UseAnswer.getText();
                Elev p = null;
                Profesor prof = null;
 
                try {  
                    String url = "rmi://" + adresa + ":" + 1099 + "/bd";
                    I_BD bd = null;
                    bd = (I_BD)Naming.lookup(url); 
                    if ( UseCategory.getSelectedItem().toString().equals("Elev"))
                    {
                         p = new Elev( "", CampLastName, CampFirstName, CampMail, CampPassword, CampQuestion, CampAnswer, CampCycle, CampProfile, "inactiv" );
                         bd.adauga(p);
                         dispose();
                    }
                    else
                    {
                        CampSubject = UseSubject.getSelectedItem().toString();
                        prof = new Profesor("", CampLastName, CampFirstName, CampMail, CampPassword, CampQuestion, CampAnswer, CampCycle, CampProfile, "inactiv",CampSubject );
                        bd.adauga_prof(prof);
                        dispose();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
               }
             }
    }//GEN-LAST:event_OkActionPerformed

    private void UseCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UseCategoryActionPerformed
        if ( UseCategory.getSelectedItem() == "Elev" )
        {
            UseSubject.setVisible(false);
            materie.setVisible(false);
            UseCycle.setVisible(true);
            UseProfile.setVisible(true);
        }
        else
        {
            UseSubject.setVisible(true);
            UseCycle.setVisible(false);
            UseProfile.setVisible(false);
            materie.setVisible(true);
        }
    }//GEN-LAST:event_UseCategoryActionPerformed

    private void UsePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsePasswordActionPerformed

    }//GEN-LAST:event_UsePasswordActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Account().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Answer;
    private javax.swing.JLabel Category;
    private javax.swing.JLabel ConfirmPassword;
    private javax.swing.JLabel Cycle;
    private javax.swing.JLabel FirstName;
    private javax.swing.JLabel LastName;
    private javax.swing.JLabel Mail;
    private javax.swing.JButton Ok;
    private javax.swing.JLabel PassWord;
    private javax.swing.JLabel Profile;
    private javax.swing.JLabel Question;
    private javax.swing.JTextField UseAnswer;
    private javax.swing.JComboBox UseCategory;
    private javax.swing.JPasswordField UseConfirmPassword;
    private javax.swing.JComboBox UseCycle;
    private javax.swing.JTextField UseFirstName;
    private javax.swing.JTextField UseLastName;
    private javax.swing.JTextField UseMail;
    private javax.swing.JPasswordField UsePassword;
    private javax.swing.JComboBox UseProfile;
    private javax.swing.JComboBox UseQuestion;
    private javax.swing.JComboBox UseSubject;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem7;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem8;
    private javax.swing.JLabel materie;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book » Bookmark.png")));
    }


}