/*
 * 
 * Accueil.java
 *
 * Created on 31 mars 2009, 14:53
 */
package ihm;

import idc.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author  w705199
 */
public class Accueil extends javax.swing.JFrame {
<<<<<<< HEAD:src/ihm/Accueil.java
	public static CryptoManager crypto;
	public static IDCManager manager;
    /** Creates new form Accueil */
    public Accueil(/*IDCManager manager_arg,CryptoManager crypto*/) {
    	
        initComponents();
        jFormattedTextField1.addKeyListener(new java.awt.event.KeyListener() {
            public void actionPerformed(java.awt.event.KeyEvent evt) {
              //  jFormattedTextField1ActionPerformed(evt);
            }

            public void keyTyped(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
            	
                if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
                    if(jList1.getSelectedIndex() != -1)
	                	if (!jFormattedTextField1.getText().isEmpty())
	                    {
	                        String dial = new String(jFormattedTextField1.getText());
	                        dial = "[hh:mm] "+ Config.nickname +" : " + dial + "\n";
	                        manager.send(new Message(dial, IDCManager.myNode));
	                        jTextArea1.setText(jtrep.get(jList1.getSelectedIndex()).getText());
	                        jFormattedTextField1.setText("");
	                    }
            }
            public void keyReleased(KeyEvent arg0) {
            }
        });
        jFormattedTextField1.addMouseListener(new java.awt.event.MouseListener() {
            public void actionPerformed(java.awt.event.MouseEvent evt) {
              //  jFormattedTextField1ActionPerformed(evt);
            }

            public void mouseClicked(MouseEvent arg0) {
                 if (jFormattedTextField1.getText().contentEquals("Entrez votre texte ici..."))
                {
                    jFormattedTextField1.setText("");
                }
            }

            public void mousePressed(MouseEvent arg0) {
            }

            public void mouseReleased(MouseEvent arg0) {
            }

            public void mouseEntered(MouseEvent arg0) {
            }

            public void mouseExited(MouseEvent arg0) {
            }

        });
       /* jList1.addListSelectionListener(new java.awt.event.MouseListener(){

            public void mouseClicked(MouseEvent arg0) {
            }

            public void mousePressed(MouseEvent arg0) {
            }
=======
>>>>>>> fridim/master:src/ihm/Accueil.java

   public static CryptoManager crypto;
   public static IDCManager manager;

   /** Creates new form Accueil */
   public Accueil(/*IDCManager manager_arg,CryptoManager crypto*/) {
      crypto = new CryptoManager();
      manager = new IDCManager();
      initComponents();
      new refreshNicknameList().start();
      jFormattedTextField1.addKeyListener(new java.awt.event.KeyListener() {

         public void actionPerformed(java.awt.event.KeyEvent evt) {
         //  jFormattedTextField1ActionPerformed(evt);
         }

         public void keyTyped(KeyEvent arg0) {
         }

         public void keyPressed(KeyEvent arg0) {
            if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
               if (!jFormattedTextField1.getText().isEmpty()) {
                  String dial = new String(jFormattedTextField1.getText());
                  dial = "[hh:mm] " + Config.nickname + " : " + dial + "\n";

                  manager.send(new Message(dial, IDCManager.myNode));
                  jTextArea1.setText(jtrep.get(jList1.getSelectedIndex()).getText());
                  jFormattedTextField1.setText("");
               //Node nd = new Node("stickman");
               //IDCManager.addLocalNode(new FrienNode(nd.getNickname(),))


               }
            }
         }

         public void keyReleased(KeyEvent arg0) {
         }
      });
      jFormattedTextField1.addMouseListener(new java.awt.event.MouseListener() {

         public void actionPerformed(java.awt.event.MouseEvent evt) {
         //  jFormattedTextField1ActionPerformed(evt);
         }

         public void mouseClicked(MouseEvent arg0) {
            if (jFormattedTextField1.getText().contentEquals("Entrez votre texte ici...")) {
               jFormattedTextField1.setText("");
            }
         }

         public void mousePressed(MouseEvent arg0) {
         }

         public void mouseReleased(MouseEvent arg0) {
         }

         public void mouseEntered(MouseEvent arg0) {
         }

         public void mouseExited(MouseEvent arg0) {
         }
      });
   /* jList1.addListSelectionListener(new java.awt.event.MouseListener(){
   public void mouseClicked(MouseEvent arg0) {
   }
   public void mousePressed(MouseEvent arg0) {
   }
   public void mouseReleased(MouseEvent arg0) {
   }
   public void mouseEntered(MouseEvent arg0) {
   }
   public void mouseExited(MouseEvent arg0) {
   }
   });*/
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jTextField1 = new javax.swing.JTextField();
      jScrollPane4 = new javax.swing.JScrollPane();
      jEditorPane1 = new javax.swing.JEditorPane();
      jPopupMenu1 = new javax.swing.JPopupMenu();
      jPopupMenu2 = new javax.swing.JPopupMenu();
      jPopupMenu3 = new javax.swing.JPopupMenu();
      jPopupMenu4 = new javax.swing.JPopupMenu();
      jFormattedTextField1 = new javax.swing.JFormattedTextField();
      jMenuBar1 = new javax.swing.JMenuBar();
      jMenu1 = new javax.swing.JMenu();
      jMenuItem1 = new javax.swing.JMenuItem();
      jMenuItem6 = new javax.swing.JMenuItem();
      jMenuItem2 = new javax.swing.JMenuItem();
      jMenu3 = new javax.swing.JMenu();
      jMenuItem7 = new javax.swing.JMenuItem();
      jMenuItem8 = new javax.swing.JMenuItem();
      jMenuItem9 = new javax.swing.JMenuItem();
      jScrollPane1 = new javax.swing.JScrollPane();
      jList1 = new javax.swing.JList();
      jButton1 = new javax.swing.JButton();
      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      jScrollPane2 = new javax.swing.JScrollPane();
      jListNicknames = new javax.swing.JList();
      jScrollPane3 = new javax.swing.JScrollPane();
      jTextArea1 = new javax.swing.JTextArea();
      jButton2 = new javax.swing.JButton();
      jButton3 = new javax.swing.JButton();
      jButton4 = new javax.swing.JButton();
      jMenuBar2 = new javax.swing.JMenuBar();
      jMenu2 = new javax.swing.JMenu();
      jMenuItem10 = new javax.swing.JMenuItem();
      jMenuItem4 = new javax.swing.JMenuItem();
      jMenu4 = new javax.swing.JMenu();
      jMenuItem11 = new javax.swing.JMenuItem();
      jMenuItem12 = new javax.swing.JMenuItem();
      jMenuItem13 = new javax.swing.JMenuItem("À propos");


      // List des nicks
      ListData ld = new ListData(IDCManager.friends);
      jListNicknames.setModel(ld);


      jTextField1.setText("jTextField1");

      jScrollPane4.setViewportView(jEditorPane1);

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("IDC - Internet Distributed Chat");

      jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
      jFormattedTextField1.setText("Entrez votre texte ici...");

      jMenu1.setText("Fichier");

      jMenuItem1.setText("Options...");
      jMenu1.add(jMenuItem1);

      jMenuItem6.setText("Gérer les connexions...");
      jMenu1.add(jMenuItem6);

      jMenuItem2.setText("Quitter");
      jMenu1.add(jMenuItem2);

      jMenuBar1.add(jMenu1);

      jMenu3.setText("?");

      jMenuItem7.setText("Manuel");
      jMenu3.add(jMenuItem7);

      jMenuItem8.setText("FAQ");
      jMenu3.add(jMenuItem8);

      jMenuItem9.setText("A propos");
      jMenu3.add(jMenuItem9);

      jMenuBar1.add(jMenu3);

//        jList1.setModel(new javax.swing.AbstractListModel() {
      //String[] strings = { "<html><span style=\"color: red\">Vide</span></html>"};
      //public int getSize() { return strings.length; }
      //public Object getElementAt(int i) { return strings[i]; }
      //      });
      jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      jList1.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jList1MouseClicked(evt);
         }
      });
      jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

         public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            jList1ValueChanged(evt);
         }
      });
      jScrollPane1.setViewportView(jList1);

      jButton1.setText("Envoyer");
      jButton1.addActionListener(new java.awt.event.ActionListener() {

         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
         }
      });

      jLabel1.setText("<html><small><small>Personnes sur le réseau");

      jLabel2.setText(" Chan disponible");

      /*jListNicknames.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Axel", "Badiss", "Guillaume", "Simon", "Yoann" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
      });*/
      jListNicknames.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jList2MouseClicked(evt);
         }
      });
      jScrollPane2.setViewportView(jListNicknames);

      jTextArea1.setColumns(20);
      jTextArea1.setEditable(false);
      jTextArea1.setRows(5);
      jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTextArea1MouseClicked(evt);
         }
      });
      jScrollPane3.setViewportView(jTextArea1);

      jButton2.setFont(new java.awt.Font("Tahoma", 0, 10));
      jButton2.setText("Creer chan");
      jButton2.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton2MouseClicked(evt);
         }
      });
      jButton2.addActionListener(new java.awt.event.ActionListener() {

         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
         }
      });

      jButton3.setFont(new java.awt.Font("Tahoma", 0, 10));
      jButton3.setText("Inviter");
      jButton3.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton3MouseClicked(evt);
         }
      });

      jButton4.setFont(new java.awt.Font("Tahoma", 0, 10));
      jButton4.setText("Quitter chan");
      jButton4.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton4MouseClicked(evt);
         }
      });
      jButton4.addActionListener(new java.awt.event.ActionListener() {

         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
         }
      });

      jMenu2.setText("Fichier");

      jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
      jMenuItem10.setText("Gérer les connexions...");
      jMenu2.add(jMenuItem10);

      jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
      jMenuItem4.setText("Quitter");
      jMenu2.add(jMenuItem4);

      jMenuBar2.add(jMenu2);

      jMenu4.setText("?");

      jMenuItem11.setText("Manuel");
      jMenu4.add(jMenuItem11);

      jMenuItem12.setText("FAQ");
      jMenu4.add(jMenuItem12);

      jMenuItem13.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mousePressed(java.awt.event.MouseEvent evt) {
            jMenuItem13MousePressed(evt);
         }
      });
      jMenu4.add(jMenuItem13);

      jMenuBar2.add(jMenu4);

      setJMenuBar(jMenuBar2);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27))).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)).addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton4))).addGap(12, 12, 12).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE).addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGap(12, 12, 12).addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
      layout.setVerticalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jLabel1)).addGap(6, 6, 6).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE).addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)).addGap(1, 1, 1).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(layout.createSequentialGroup().addGap(7, 7, 7).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))).addContainerGap()));
      listJlist1.addElement("Chat Public");
      jList1.setListData(listJlist1);
      jtrep.add(new JTextArea(""));
      pack();

      jTextArea1.setLineWrap(true);
   }// </editor-fold>//GEN-END:initComponents
   private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      // TODO add your handling code here:

      if (!jFormattedTextField1.getText().isEmpty()) {
         String dial = new String(jFormattedTextField1.getText());
         dial = "[hh:mm] " + Config.nickname + " : " + dial + "\n";
         manager.send(new Message(dial, IDCManager.myNode));
         jTextArea1.setText(jtrep.get(jList1.getSelectedIndex()).getText());
         jFormattedTextField1.setText("");
      //Node nd = new Node("stickman");
      //IDCManager.addLocalNode(new FrienNode(nd.getNickname(),))


      }

   }//GEN-LAST:event_jButton1ActionPerformed
   private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   // TODO add your handling code here:
   }//GEN-LAST:event_jButton2ActionPerformed
   private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
   // TODO add your handling code here:
   }//GEN-LAST:event_jButton4ActionPerformed
   private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
      // TODO add your handling code here:
      if (evt.getValueIsAdjusting() == false) {
         if (jList1.getSelectedIndex() != -1) {
            if (!jList1.getSelectedValue().toString().startsWith("  -")) //On affiche simplement le bon texte
            {
               jTextArea1.setText(jtrep.get(jList1.getSelectedIndex()).getText());
            } else// on a cliqué sur un elt qui appartient a un chanel
            {
               int i = jList1.getSelectedIndex();
               while (listJlist1.get(i).startsWith("  -")) {
                  i = i - 1;

               }
               jList1.setSelectedIndex(i);
            }
         }
      }
   }//GEN-LAST:event_jList1ValueChanged
   private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
      // TODO add your handling code here:
      for (int i = jListNicknames.getSelectedIndices().length - 1; i >= 0; i = i - 1) {
         if (jListNicknames.getSelectedIndices()[i] != -1) {
            if (jList1.getSelectedIndex() != -1 && jList1.getSelectedValue().toString().startsWith("channel")) {
               if (!listJlist1.contains("  -" + jListNicknames.getSelectedValues()[i])) {
                  int select = jList1.getSelectedIndex();
                  listJlist1.add(select + 1, "  -" + jListNicknames.getSelectedValues()[i]);
                  jList1.setListData(listJlist1);
                  jtrep.add(select + 1, new JTextArea(""));
                  jList1.setSelectedIndex(select);
                  Message msg = new Message(jFormattedTextField1.getText(), (Node) IDCManager.getLANNodes().get(0));
                  IDCManager.send(msg);
               }
            } else {
               if (!listJlist1.contains(jListNicknames.getSelectedValues()[i] + " (privée)")) {
                  int select = jList1.getSelectedIndex();
                  listJlist1.addElement(jListNicknames.getSelectedValues()[i] + " (privée)");
                  jList1.setListData(listJlist1);
                  jtrep.add(new JTextArea(""));
                  jList1.setSelectedIndex(select);
                  jList1.setSelectedIndex(listJlist1.size() - 1);
               }
            }
         }
      }
   }//GEN-LAST:event_jButton3MouseClicked
   private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
      // TODO add your handling code here:
      if (jListNicknames.getSelectedIndex() != -1) {
         listJlist1.addElement("channel " + Nbr_channel + " (privée)");
         jtrep.add(new JTextArea(""));
         listJlist1.add("  -" + jListNicknames.getSelectedValue());
         jtrep.add(new JTextArea(""));
         jList1.setListData(listJlist1);
         jList1.setSelectedIndex(listJlist1.size() - 2);
         Nbr_channel++;
      }
   }//GEN-LAST:event_jButton2MouseClicked
   private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
   // TODO add your handling code here:
   }//GEN-LAST:event_jList1MouseClicked
   private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
      // TODO add your handling code here:
      //Bouton quitter
      if (jList1.getSelectedIndex() != -1) {
         if (jList1.getSelectedValue().toString().startsWith("channel")) {
            int i = jList1.getSelectedIndex();
            listJlist1.remove(i);
            jtrep.remove(i);
            while (i < listJlist1.size() && listJlist1.get(i).startsWith("  -")) {
               listJlist1.remove(i);
               jtrep.remove(i);
            }
            jList1.setListData(listJlist1);
            jList1.setSelectedIndex(listJlist1.size() - 1);
         } else {
            int i = jList1.getSelectedIndex();
            listJlist1.remove(i);
            jtrep.remove(i);
            jList1.setListData(listJlist1);
            jList1.setSelectedIndex(listJlist1.size() - 1);

         }
      }
   }//GEN-LAST:event_jButton4MouseClicked
   private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
      // TODO add your handling code here:
      if (evt.getClickCount() == 2) {
         for (int i = jListNicknames.getSelectedIndices().length - 1; i >= 0; i = i - 1) {
            if (jListNicknames.getSelectedIndices()[i] != -1) {
               if (jList1.getSelectedIndex() != -1 && jList1.getSelectedValue().toString().startsWith("channel")) {
                  if (!listJlist1.contains("  -" + jListNicknames.getSelectedValues()[i])) {
                     int select = jList1.getSelectedIndex();
                     listJlist1.add(select + 1, "  -" + jListNicknames.getSelectedValues()[i]);
                     jList1.setListData(listJlist1);
                     jtrep.add(select + 1, new JTextArea(""));
                     jList1.setSelectedIndex(select);
                  }
               } else {
                  if (!listJlist1.contains(jListNicknames.getSelectedValues()[i] + " (privée)")) {
                     int select = jList1.getSelectedIndex();
                     listJlist1.addElement(jListNicknames.getSelectedValues()[i] + " (privée)");
                     jList1.setListData(listJlist1);
                     jtrep.add(new JTextArea("Beginning chat with " + jListNicknames.getSelectedValues()[i] + " ..."));
                     jList1.setSelectedIndex(select);
                     jList1.setSelectedIndex(listJlist1.size() - 1);
                  }
               }
            }
         }
      }
   }//GEN-LAST:event_jList2MouseClicked
   private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
   // TODO add your handling code here:
   }//GEN-LAST:event_jTextArea1MouseClicked
   private void jMenuItem13SelectionBouton(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem13SelectionBouton
      // TODO add your handling code here:
      //About temp = new About();
      //temp.run_about();
      System.out.println("on est dans la selection du bouton");
      new About().setVisible(true);
   }//GEN-LAST:event_jMenuItem13SelectionBouton
   private void jMenuItem13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem13MousePressed
      // TODO add your handling code here:
      System.out.println("on est dans la selection du bouton");
      new About().setVisible(true);
   }//GEN-LAST:event_jMenuItem13MousePressed
   /**
    * @param args the command line arguments
    */
   /*public static void run_idc() {
   java.awt.EventQueue.invokeLater(new Runnable() {
   public void run() {
   this.setVisible(true);
   }
   });
   }*/
   public static Vector<JTextArea> jtrep = new Vector<JTextArea>();  //ce tableau permet d'avoir toutes les discussions en cours
   int Nbr_channel = 0;
   private Vector<String> listJlist1 = new Vector<String>();
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jButton1;
   private javax.swing.JButton jButton2;
   private javax.swing.JButton jButton3;
   private javax.swing.JButton jButton4;
   private javax.swing.JEditorPane jEditorPane1;
   private javax.swing.JFormattedTextField jFormattedTextField1;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JList jList1;
   public static javax.swing.JList jListNicknames;
   private javax.swing.JMenu jMenu1;
   private javax.swing.JMenu jMenu2;
   private javax.swing.JMenu jMenu3;
   private javax.swing.JMenu jMenu4;
   private javax.swing.JMenuBar jMenuBar1;
   private javax.swing.JMenuBar jMenuBar2;
   private javax.swing.JMenuItem jMenuItem1;
   private javax.swing.JMenuItem jMenuItem10;
   private javax.swing.JMenuItem jMenuItem11;
   private javax.swing.JMenuItem jMenuItem12;
   private javax.swing.JMenuItem jMenuItem13;
   private javax.swing.JMenuItem jMenuItem2;
   private javax.swing.JMenuItem jMenuItem4;
   private javax.swing.JMenuItem jMenuItem6;
   private javax.swing.JMenuItem jMenuItem7;
   private javax.swing.JMenuItem jMenuItem8;
   private javax.swing.JMenuItem jMenuItem9;
   private javax.swing.JPopupMenu jPopupMenu1;
   private javax.swing.JPopupMenu jPopupMenu2;
   private javax.swing.JPopupMenu jPopupMenu3;
   private javax.swing.JPopupMenu jPopupMenu4;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JScrollPane jScrollPane4;
   public static javax.swing.JTextArea jTextArea1;
   private javax.swing.JTextField jTextField1;
   // End of variables declaration//GEN-END:variables
}

class ListData extends AbstractListModel {

   static private List friends;

   public ListData(List friends) {
      assert (friends != null);
      ListData.friends = friends;
   }

   public int getSize() {
      return friends.size();
   }

   public Object getElementAt(int index) {
      return ((FriendNode) friends.get(index)).getNickname();
   }

   public Object getRawElement(int index) {
      return friends.get(index);
   }

<<<<<<< HEAD:src/ihm/Accueil.java
        jMenuItem13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem13MousePressed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)))
                .addContainerGap())
        );
        listJlist1.addElement("Chat Public");
        jList1.setListData(listJlist1);
        jtrep.add(new JTextArea(""));
        pack();

		crypto=new CryptoManager();
		manager = new IDCManager();
		jTextArea1.setLineWrap(true);
    }// </editor-fold>//GEN-END:initComponents

  
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        	if(jList1.getSelectedIndex() != -1)
                if (!jFormattedTextField1.getText().isEmpty())
                {
                    String dial = new String(jFormattedTextField1.getText());
                    dial = "[hh:mm] "+ Config.nickname +" : " + dial + "\n";
                    manager.send(new Message(dial, IDCManager.myNode));
                    jTextArea1.setText(jtrep.get(jList1.getSelectedIndex()).getText());
                    jFormattedTextField1.setText("");
                }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        if (evt.getValueIsAdjusting() == false) {   
                if(jList1.getSelectedIndex()!=-1)
                {
                    if(!jList1.getSelectedValue().toString().startsWith("  -")) //On affiche simplement le bon texte
                        jTextArea1.setText(jtrep.get(jList1.getSelectedIndex()).getText());
                    else// on a cliqué sur un elt qui appartient a un chanel
                    {
                        int i=jList1.getSelectedIndex();
                        while(listJlist1.get(i).startsWith("  -"))
                        {
                            i = i-1;

                        }
                        jList1.setSelectedIndex(i);
                    }
                }
            }
    }//GEN-LAST:event_jList1ValueChanged
=======
   public void refreshList() {
      fireContentsChanged(this, 0, friends.size());
   }
}
>>>>>>> fridim/master:src/ihm/Accueil.java

class refreshNicknameList extends Thread {

   public void run() {
      try {
         while (true) {
            ((ListData) Accueil.jListNicknames.getModel()).refreshList();
            sleep(Config.refreshListSleep);
         }
      } catch (InterruptedException ex) {
         Logger.getLogger(refreshNicknameList.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
