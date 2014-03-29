
package srvcln;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import static srvcln.Cln.adresa;


public class OnLine extends javax.swing.JFrame {

    static DataInputStream is = null;
    static DataOutputStream os = null;
    static ArrayList<String> PersoaneLogate = new ArrayList<String>();
    static ArrayList<String> Category = new ArrayList<String>();
    static DefaultTreeModel model;
    static Socket cs;
    static String MyName = null;
    static String Categorie = null;
    I_BD bd = null;    
    static ArrayList<String> PersoaneCuCareVb = new ArrayList<>();
    static ArrayList<String> Discutii = new ArrayList<>();
    static String mess = null;
    static String MyCategory = null;
    Discution Ds = null;
    static String mail;

    public OnLine (Socket s, String MyName, final DataInputStream is, final DataOutputStream os, String categorie, String mail) {
        PersoaneCuCareVb.add(MyName);

        cs = s;
        this.mail = mail;
        Categorie = categorie;
        initComponents();
        setIcon();
        this.MyName = MyName;
        this.is = is;
        this.os = os;
        MyAccount.setText("Contul meu: " + MyName);
        Change.setFocusable(true);
        Change.requestFocusInWindow();
        model = (DefaultTreeModel)Tree.getModel();
        String url = "rmi://" + adresa + ":" + 1099 + "/bd";
        try { 
            bd = (I_BD)Naming.lookup(url);
            if ( Categorie.equals("Elev") ) {
                PersoaneLogate = bd.active_prof();
                Category = bd.Categorii(); }
            else {
                PersoaneLogate = bd.active_elevi();
                for ( int i = 0; i < PersoaneLogate.size(); i++ )
                    Category.add("Elev");
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddPersonsInTree ();
        if ( categorie.equals("Elev"))
            try {
                bd.update_activ_elev(mail);
        } catch (Exception ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            try {
                bd.update_activ_prof(mail);
        } catch (Exception ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Thread T;
        T = new Thread(new Runnable() { 
            public void run() {
                while (true) {
                    String message;
                    try {
                        String s = "";
                        String[] aux = new String[5];
                        message = is.readUTF();
                        aux = message.split(",",4);
                        if (aux[0].equals("Delogat")) {
                            if (!Categorie.equals(aux[2]))
                                Remove(aux[1], aux[2]);
                        } else if (aux[0].equals("Logat")) {
                            if (!(Categorie.equals(aux[2])))
                                AddPerson(aux[1], aux[2]);
                        } else if (aux[0].equals("Msg") && !(aux[1].equals(aux[2]))) {
                            int pozitie = -1;
                            if ((pozitie = Search(PersoaneCuCareVb, aux[1] )) != -1){
                                 // actualizare frame discutie 
                                  Ds = new Discution (aux[1], cs, aux[2]); 
                                 Ds.setmessage(aux[3], os);
                            } else { 
                                PersoaneCuCareVb.add(aux[1]);
                                Ds = new Discution(aux[1], cs, aux[2]); 
                                Ds.setmessage(aux[3], os);
                                discutie_noua(aux[1], aux[2] ); // adauga in baza de date 
                                Ds.setVisible(true);
                            }
                        } else if (aux[0].equals("File")) {
                              ReceiveFile RF = new ReceiveFile(aux[3]);
                              RF.setVisible(true);
                        } else if (aux[0].equals("Quit")) {
                            int poz = Search(PersoaneCuCareVb, aux[2]);
                            PersoaneCuCareVb.remove(poz);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Discution.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            private void discutie_noua(String eu, String nume ) {
                String url = "rmi://" + adresa + ":" + 1099 + "/bd";
                I_BD bd = null;
                try { 
                    bd = (I_BD)Naming.lookup(url);
                    try {
                        if ( bd.afla_categorie(eu).equals("Elev") )
                            if ( bd.afla_categorie(nume).equals("Profesor") )
                                bd.discutie(bd.afla_id_elev(eu), bd.afla_id_prof(nume) );
                    } catch (Exception ex) {
                        Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NotBoundException ex) {
                    Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        T.start();
    }
    
    static int Search (ArrayList<String> Array, String search) {
    for (int index = 0; index < Array.size(); index++)
        if (Array.get(index).equals(search))
            return index;
    return -1;
    }
    
    static void AddPersonsInTree () 
    {   
        DefaultMutableTreeNode Elev = (DefaultMutableTreeNode)model.getChild(model.getRoot(), 0);
        DefaultMutableTreeNode Profesor = (DefaultMutableTreeNode)model.getChild(model.getRoot(), 1);
        DefaultMutableTreeNode Biologie = (DefaultMutableTreeNode)Profesor.getChildAt(0);
        DefaultMutableTreeNode Chimie = (DefaultMutableTreeNode)Profesor.getChildAt(1);
        DefaultMutableTreeNode Fizica = (DefaultMutableTreeNode)Profesor.getChildAt(2);
        DefaultMutableTreeNode Geografie = (DefaultMutableTreeNode)Profesor.getChildAt(3);
        DefaultMutableTreeNode Informatica = (DefaultMutableTreeNode)Profesor.getChildAt(4);
        DefaultMutableTreeNode Istorie = (DefaultMutableTreeNode)Profesor.getChildAt(5);
        DefaultMutableTreeNode Romana = (DefaultMutableTreeNode)Profesor.getChildAt(6);
        DefaultMutableTreeNode Matematica = (DefaultMutableTreeNode)Profesor.getChildAt(7);
   
        for (int index = 0; index < PersoaneLogate.size(); index++) 
        {
            if (Category.get(index).equals("Elev")) {
                Elev.insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Elev);
            } else if (Category.get(index).equals("Biologie")) {
                Biologie.insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Biologie);
            } else if (Category.get(index).equals("Chimie")) {
                Chimie.insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Chimie);
            } else if (Category.get(index).equals("Fizica")) {
                Fizica.insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Fizica);
            } else if (Category.get(index).equals("Geografie")) {
                Geografie .insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Geografie);
            } else if (Category.get(index).equals("Informatica")) {
                Informatica.insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Informatica);
            } else if (Category.get(index).equals("Istorie")) {
                Istorie.insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Biologie);
            } else if (Category.get(index).equals("Limba si literatura romana")) {
               Romana.insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Romana);
            } else if (Category.get(index).equals("Matematica")) {
                Matematica.insert(new DefaultMutableTreeNode (PersoaneLogate.get(index)),0);
                model.reload(Matematica);
            }
        }
        
    }
    
    static void Remove (String nume_util, String category) 
    {
        DefaultMutableTreeNode Elev = (DefaultMutableTreeNode)model.getChild(model.getRoot(), 0);
        DefaultMutableTreeNode Profesor = (DefaultMutableTreeNode)model.getChild(model.getRoot(), 1);
        DefaultMutableTreeNode Biologie = (DefaultMutableTreeNode)Profesor.getChildAt(0);
        DefaultMutableTreeNode Chimie = (DefaultMutableTreeNode)Profesor.getChildAt(1);
        DefaultMutableTreeNode Fizica = (DefaultMutableTreeNode)Profesor.getChildAt(2);
        DefaultMutableTreeNode Geografie = (DefaultMutableTreeNode)Profesor.getChildAt(3);
        DefaultMutableTreeNode Informatica = (DefaultMutableTreeNode)Profesor.getChildAt(4);
        DefaultMutableTreeNode Istorie = (DefaultMutableTreeNode)Profesor.getChildAt(5);
        DefaultMutableTreeNode Romana = (DefaultMutableTreeNode)Profesor.getChildAt(6);
        DefaultMutableTreeNode Matematica = (DefaultMutableTreeNode)Profesor.getChildAt(7);
        
        DefaultMutableTreeNode deleteSelection;
        System.out.println(nume_util + " " + category);
        if (category.equals("Elev")) {
            ArrayList<String> nume1 = new ArrayList<String>();
            for (int index = 0; index < Elev.getChildCount(); index++)
                nume1.add(Elev.getChildAt(index).toString());
            for (int index = 0; index < nume1.size(); index++) 
                if (nume_util.equals(nume1.get(index))) {
                    Elev.remove(index);
                }
            model.reload();
        } else if (category.equals("Biologie")) {
            ArrayList<String> nume2 = new ArrayList<String>();
            for (int index = 0; index < Biologie.getChildCount(); index++)
                nume2.add(Biologie.getChildAt(index).toString());
            for (int index = 0; index < nume2.size(); index++) 
                if (nume_util.equals(nume2.get(index))) {
                    Biologie.remove(index);
                }
            model.reload();
        } else if (category.equals("Chimie")) {
            ArrayList<String> nume3 = new ArrayList<String>();
            for (int index = 0; index < Chimie.getChildCount(); index++)
                nume3.add(Chimie.getChildAt(index).toString());
            for (int index = 0; index < nume3.size(); index++) 
                if (nume_util.equals(nume3.get(index))) {
                    Chimie.remove(index);
                }
            model.reload();
        } else if (category.equals("Fizica")) {
            ArrayList<String> nume4 = new ArrayList<String>();
            for (int index = 0; index < Fizica.getChildCount(); index++)
                nume4.add(Fizica.getChildAt(index).toString());
            for (int index = 0; index < nume4.size(); index++) 
                if (nume_util.equals(nume4.get(index))) {
                    Fizica.remove(index);
                }
            model.reload();
        } else if (category.equals("Geografie")) {
            ArrayList<String> nume5 = new ArrayList<String>();
            for (int index = 0; index < Geografie.getChildCount(); index++)
                nume5.add(Geografie.getChildAt(index).toString());
            for (int index = 0; index < nume5.size(); index++) 
                if (nume_util.equals(nume5.get(index))) {
                    Geografie.remove(index);
                }
            model.reload();
        } else if (category.equals("Informatica")) {
            ArrayList<String> nume6 = new ArrayList<String>();
            for (int index = 0; index < Informatica.getChildCount(); index++)
                nume6.add(Informatica.getChildAt(index).toString());
            for (int index = 0; index < nume6.size(); index++) 
                if (nume_util.equals(nume6.get(index))) {
                    Informatica.remove(index);
                }
            model.reload();
        } else if (category.equals("Istorie")) {
            ArrayList<String> nume7 = new ArrayList<String>();
            for (int index = 0; index < Istorie.getChildCount(); index++)
                nume7.add(Istorie.getChildAt(index).toString());
            for (int index = 0; index < nume7.size(); index++) 
                if (nume_util.equals(nume7.get(index))) {
                    Istorie.remove(index);
                }
            model.reload();
        } else if (category.equals("Limba si literatura romana")) {
            ArrayList<String> nume8 = new ArrayList<String>();
            for (int index = 0; index < Romana.getChildCount(); index++)
                nume8.add(Romana.getChildAt(index).toString());
            for (int index = 0; index < nume8.size(); index++) 
                if (nume_util.equals(nume8.get(index))) {
                    Romana.remove(index);
                }
            model.reload();
        } else if (category.equals("Matematica")) {
            ArrayList<String> nume9 = new ArrayList<String>();
            for (int index = 0; index < Matematica.getChildCount(); index++)
                nume9.add(Matematica.getChildAt(index).toString());
            for (int index = 0; index < nume9.size(); index++) 
                if (nume_util.equals(nume9.get(index))) {
                    Matematica.remove(index);
                }
            model.reload();
        }
        
    }
    
    public void AddPerson(String nume_util, String category) 
    {
        DefaultMutableTreeNode Elev = (DefaultMutableTreeNode)model.getChild(model.getRoot(), 0);
        DefaultMutableTreeNode Profesor = (DefaultMutableTreeNode)model.getChild(model.getRoot(), 1);
        DefaultMutableTreeNode Biologie = (DefaultMutableTreeNode)Profesor.getChildAt(0);
        DefaultMutableTreeNode Chimie = (DefaultMutableTreeNode)Profesor.getChildAt(1);
        DefaultMutableTreeNode Fizica = (DefaultMutableTreeNode)Profesor.getChildAt(2);
        DefaultMutableTreeNode Geografie = (DefaultMutableTreeNode)Profesor.getChildAt(3);
        DefaultMutableTreeNode Informatica = (DefaultMutableTreeNode)Profesor.getChildAt(4);
        DefaultMutableTreeNode Istorie = (DefaultMutableTreeNode)Profesor.getChildAt(5);
        DefaultMutableTreeNode Romana = (DefaultMutableTreeNode)Profesor.getChildAt(6);
        DefaultMutableTreeNode Matematica = (DefaultMutableTreeNode)Profesor.getChildAt(7);
        
        if (category.equals("Elev")) {
            Elev.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        } else if (category.equals("Biologie")) {
            Biologie.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        } else if (category.equals("Chimie")) {
            Chimie.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        } else if (category.equals("Fizica")) {
            Fizica.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        } else if (category.equals("Geografie")) {
            Geografie.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        } else if (category.equals("Informatica")) {
            Informatica.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        } else if (category.equals("Istorie")) {
            Istorie.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        } else if (category.equals("Limba si literatura romana")) {
            Romana.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        } else if (category.equals("Matematica")) {
            Elev.insert(new DefaultMutableTreeNode (nume_util),0);
            model.reload();
        }
        
    }

    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tree = new javax.swing.JTree();
        jSeparator2 = new javax.swing.JSeparator();
        Change = new javax.swing.JTextField();
        MyAccount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SH4SS");
        setMaximumSize(new java.awt.Dimension(335, 750));
        setMinimumSize(new java.awt.Dimension(335, 750));
        setPreferredSize(new java.awt.Dimension(335, 750));
        setResizable(false);
        getContentPane().setLayout(null);

        Tree.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Users");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Elev");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Profesor");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Biologie");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Chimie");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fizica");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Geografie");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Informatica");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Istorie");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Limba si literatura romana");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Matematica");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        Tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        Tree.setAutoscrolls(true);
        Tree.setInvokesStopCellEditing(true);
        Tree.setLargeModel(true);
        Tree.setName(""); // NOI18N
        Tree.setToggleClickCount(1);
        Tree.setVisibleRowCount(100);
        Tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tree);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 130, 240, 530);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(0, 0, 304, 2);

        Change.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        Change.setMaximumSize(new java.awt.Dimension(6, 20));
        Change.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ChangeKeyPressed(evt);
            }
        });
        getContentPane().add(Change);
        Change.setBounds(90, 97, 210, 30);

        MyAccount.setEditable(false);
        MyAccount.setBackground(new java.awt.Color(255, 255, 255));
        MyAccount.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        MyAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyAccountActionPerformed(evt);
            }
        });
        getContentPane().add(MyAccount);
        MyAccount.setBounds(70, 50, 230, 36);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/search.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 90, 40, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/online.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 330, 700);

        jMenu3.setText("Stare");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/Emoticon » Sleep.png"))); // NOI18N
        jMenuItem1.setText("Delogare");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);
        jMenu3.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/Emoticon » Sleep.png"))); // NOI18N
        jMenuItem3.setText("Iesire");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar2.add(jMenu3);

        jMenu1.setText("Vizualizare");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/Contact Card.png"))); // NOI18N
        jMenuItem4.setText("Vizualizeaza profilul");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/Pen.png"))); // NOI18N
        jMenuItem6.setText("Activitate");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar2.add(jMenu1);

        jMenu5.setText("Ajutor");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srvcln/help.png"))); // NOI18N
        jMenuItem5.setText("Vizualizare Ajutor");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuBar2.add(jMenu5);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChangeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChangeKeyPressed
        char key = evt.getKeyChar(); 
        String nume = Change.getText(); 
        if (key == KeyEvent.VK_ENTER){ 
            for (int index = 0; index < PersoaneLogate.size(); index++) { 
                String NumeAux = PersoaneLogate.get(index);
                if (NumeAux.equals(nume)) { 
                    try { 
                        Discution Ds = new Discution(nume, cs, MyName); Ds.setVisible(true);
                    } catch (Exception ex) {
                        Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } 
        } 
    }
    }//GEN-LAST:event_ChangeKeyPressed

    private void TreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TreeMouseClicked
        DefaultMutableTreeNode selectednode; 
        selectednode = (DefaultMutableTreeNode)Tree.getLastSelectedPathComponent();  //Biologie Chimie Fizica Geografie Informatica Istorie Limba si literatura romana Matematica
        try { 
            if(selectednode.getChildCount() == 0) {
                //if (!selectednode.equals("Elev") %% !selectednode.equals("Profesor") %% !selectednode.equals("Biologie") %% !selectednode.equals("Chimie") && !selectednode.equals("Fizica") && !selectednode.equals("Geografie") && !selectednode.equals("Informatica") && !selectednode.equals("Istorie") && !selectednode.equals("Limba si literatura romana") && !selectednode.equals("Matematica")) {
                try {   
                    String nume = selectednode.toString(); 
                    
                    Discution Ds;
                    try {
                        Ds = new Discution (nume, cs, MyName);
                        Ds.setVisible(true);
                    } catch (Exception ex) {
                        Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                     
                    PersoaneCuCareVb.add(nume);
                } catch (NullPointerException Ex) {    
                }
            } 
        }catch(NullPointerException e) {
        } 
    }//GEN-LAST:event_TreeMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
String url = "rmi://" + adresa + ":" + 1099 + "/bd";
            I_BD bd = null;
        try {
            bd = (I_BD)Naming.lookup(url);
        } catch (NotBoundException ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
                os.writeUTF("Delogat," + MyName + "," + bd.afla_materie_nume(MyName) + ", ");

            } catch (Exception ex) {
                Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
            }

                
        try {
            if ( Categorie.equals("Elev") )
                bd.inactiv_elev( MyName.split(" ")[0] );
            else
                bd.inactiv_prof(  MyName.split(" ")[0] );
        } catch (Exception ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String url = "rmi://" + adresa + ":" + 1099 + "/bd";
            I_BD bd = null;
        try {
            bd = (I_BD)Naming.lookup(url);
        } catch (NotBoundException ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        }
            try {
                System.out.println(bd.afla_materie_nume(MyName));
                os.writeUTF("Delogat," + MyName + "," + bd.afla_materie_nume(MyName) + ", ");

            } catch (Exception ex) {
                Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
            }

                
        try {
            if ( Categorie.equals("Elev") )
                bd.inactiv_elev( MyName.split(" ")[0] );
            else
                bd.inactiv_prof(  MyName.split(" ")[0] );
        } catch (Exception ex) {
            Logger.getLogger(OnLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
        close();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        ViewProfile ViewP = new ViewProfile(MyName, Categorie);
        ViewP.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        try {
        Runtime r = Runtime.getRuntime();
        r.exec("file:///D://proiect%20MDS//MDS-%20var3//help.html");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
        Runtime r = Runtime.getRuntime();
        //r.exec("file:///D://proiect%20MDS//MDS-%20var3//help.html");
        r.exec("C:\\Program Files\\Mozilla Firefox\\firefox.exe help.html");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Activitate Act = new Activitate(MyName);
        Act.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void MyAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MyAccountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MyAccountActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OnLine(cs, MyName, is, os, Categorie, mail).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Change;
    private javax.swing.JTextField MyAccount;
    private javax.swing.JTree Tree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Book » Bookmark.png")));
    }
}
