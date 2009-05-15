/*
 * 
 * Accueil.java
 *
 * Created on 31 mars 2009, 14:53
 */
package ihm;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import idc.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;

/**
 *
 * @author  w705199
 */
public class Accueil extends javax.swing.JFrame {

   public static CryptoManager crypto;
   public static IDCManager manager;

   /** Creates new form Accueil */
   public Accueil(/*IDCManager manager_arg,CryptoManager crypto*/) {
      // Appel du pop-up pour entrer son pseudo, avant le lancement d'IDCManager
      Pseudo ps = new Pseudo(null, "Bienvenue sur IDC", true);
      Config.nickname = ps.getPseudo();

      jtrep.add(new JTextArea(""));
      jTextArea1 = new javax.swing.JTextArea();
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
            if (jListChannels.getSelectedIndex() == -1) {
               jListChannels.setSelectedIndex(0);
            }

            if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
               if (!jFormattedTextField1.getText().isEmpty()) {
                  String dial = new String(jFormattedTextField1.getText());
                  dial = Config.nickname + " : " + dial + "\n";

                  IDCManager.send(new Message(dial, IDCManager.myNode));
                  jTextArea1.setText(jtrep.get(jListChannels.getSelectedIndex()).getText());
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

      jFormattedTextField1 = new javax.swing.JFormattedTextField();
      jScrollPane1 = new javax.swing.JScrollPane();
      jListChannels = new javax.swing.JList();
      jButton1 = new javax.swing.JButton();
      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      jScrollPane2 = new javax.swing.JScrollPane();
      jListNicknames = new javax.swing.JList();
      jScrollPane3 = new javax.swing.JScrollPane();
      jButton2 = new javax.swing.JButton();
      jButton3 = new javax.swing.JButton();
      jButton4 = new javax.swing.JButton();
      jMenuBar1 = new javax.swing.JMenuBar();
      jMenu1 = new javax.swing.JMenu();
      jMenuItem1 = new javax.swing.JMenuItem();
      jMenuItem2 = new javax.swing.JMenuItem();
      jMenu2 = new javax.swing.JMenu();
      jMenuItem11 = new javax.swing.JMenuItem();
      jMenuItem12 = new javax.swing.JMenuItem();
      jMenuItem13 = new javax.swing.JMenuItem("À propos");


      // List des nicks
      ListData ld = new ListData(IDCManager.friends);
      jListNicknames.setModel(ld);
      
      jListChannels.setModel(new ListData(IDCManager.channels));


      jTextField1.setText("jTextField1");

      jScrollPane4.setViewportView(jEditorPane1);

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("IDC - Internet Distributed Chat");

      jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
      jFormattedTextField1.setText("Entrez votre texte ici...");



      //jListChannels.setModel(new javax.swing.AbstractListModel() {
      //String[] strings = { "<html><span style=\"color: red\">Vide</span></html>"};
      //public int getSize() { return strings.length; }
      //public Object getElementAt(int i) { return strings[i]; }
      //      });
      jListChannels.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      jListChannels.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jList1MouseClicked(evt);
         }
      });
      jListChannels.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

         public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            jList1ValueChanged(evt);
         }
      });
      jScrollPane1.setViewportView(jListChannels);

      jButton1.setText("<html><small><small>Envoyer");
      jButton1.addActionListener(new java.awt.event.ActionListener() {

         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
         }
      });

      jLabel1.setText("<html><small><small>Personnes sur le réseau");

      jLabel2.setText("<html><small><small>Salons disponibles");

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

      jMenu1.setText("Fichier");

      jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
      jMenuItem1.setText("Gérer les connexions...");
      jMenu1.add(jMenuItem1);

      jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
      jMenuItem2.setText("Quitter");
      jMenuItem2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                System.exit(0); 
            }
        });
      jMenu1.add(jMenuItem2);

      jMenuBar1.add(jMenu1);

      jMenu2.setText("?");

      jMenuItem11.setText("Manuel");
      //jMenu2.add(jMenuItem11);

      jMenuItem12.setText("FAQ");
      //jMenu2.add(jMenuItem12);

      jMenuItem13.addMouseListener(new java.awt.event.MouseAdapter() {

         public void mousePressed(java.awt.event.MouseEvent evt) {
            jMenuItem13MousePressed(evt);
         }
      });
      jMenu2.add(jMenuItem13);

      jMenuBar1.add(jMenu2);

      setJMenuBar(jMenuBar1);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27))).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)).addGroup(layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton4))).addGap(12, 12, 12).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE).addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGap(12, 12, 12).addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
      layout.setVerticalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jLabel1)).addGap(6, 6, 6).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE).addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)).addGap(1, 1, 1).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(layout.createSequentialGroup().addGap(7, 7, 7).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))).addContainerGap()));
      
      IDCManager.channels.add(new Channel("Chat Public"));
      ListData m = (ListData) jListChannels.getModel();
      m.refreshList();
      
      pack();




      jTextArea1.setLineWrap(true);
   }// </editor-fold>//GEN-END:initComponents
   private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      // TODO add your handling code here:
      if (jListChannels.getSelectedIndex() != -1) {
         if (!jFormattedTextField1.getText().isEmpty()) {
            String dial = new String(jFormattedTextField1.getText());
            dial = Config.nickname + " : " + dial + "\n";
            manager.send(new Message(dial, IDCManager.myNode));
            jTextArea1.setText(jtrep.get(jListChannels.getSelectedIndex()).getText());
            jFormattedTextField1.setText("");
         //Node nd = new Node("stickman");
         //IDCManager.addLocalNode(new FrienNode(nd.getNickname(),))
         }
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
         if (jListChannels.getSelectedIndex() != -1) {
            if (!jListChannels.getSelectedValue().toString().startsWith("  -")) //On affiche simplement le bon texte
            {
               jTextArea1.setText(jtrep.get(jListChannels.getSelectedIndex()).getText());
            } else// on a cliqué sur un elt qui appartient a un chanel
            {
               int i = jListChannels.getSelectedIndex();
               while (((Channel )IDCManager.channels.get(i)).getName().startsWith("  -")) {
                  i = i - 1;

               }
               jListChannels.setSelectedIndex(i);
            }
         }
      }
   }//GEN-LAST:event_jList1ValueChanged
   private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
      // TODO add your handling code here:
      for (int i = jListNicknames.getSelectedIndices().length - 1; i >= 0; i = i - 1) {
         if (jListNicknames.getSelectedIndices()[i] != -1) {
            if (jListChannels.getSelectedIndex() != -1 && jListChannels.getSelectedValue().toString().startsWith("channel")) {
                  int select = jListChannels.getSelectedIndex();
                  Channel c = new Channel((String) jListNicknames.getSelectedValues()[i]);
                  IDCManager.addChannel(c);
                  ListData m = (ListData) jListChannels.getModel();
                  m.refreshList();
                  
                  jtrep.add(select + 1, new JTextArea(""));
                  jListChannels.setSelectedIndex(select);
                  Message msg = new Message(jFormattedTextField1.getText(), (Node) IDCManager.getLANNodes().get(0));
                  IDCManager.send(msg);
               }
            } else {
               if (!IDCManager.channels.contains(jListNicknames.getSelectedValues()[i] + " (privée)")) {
                  int select = jListChannels.getSelectedIndex();
                  Channel c = new Channel(jListNicknames.getSelectedValues()[i] + " (privée)");
                  IDCManager.addChannel(c);
                  ListData m = (ListData) jListChannels.getModel();
                  m.refreshList();

                  jtrep.add(new JTextArea(""));
                  jListChannels.setSelectedIndex(select);
                  jListChannels.setSelectedIndex(IDCManager.channels.size() - 1);
               }
            }
         }
      }
   //GEN-LAST:event_jButton3MouseClicked
   private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
      // TODO add your handling code here:
      if (jListNicknames.getSelectedIndex() != -1) {
      }
   }//GEN-LAST:event_jButton2MouseClicked
   private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIx  RST:event_jList1MouseClicked
   // TODO add your handling code here:
   }//GEN-LAST:event_jList1MouseClicked
   
   // QUITTER CHAN
   private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
      // TODO add your handling code here:
      //Bouton quitter
      if (jListChannels.getSelectedIndex() != -1) {
         if (jListChannels.getSelectedValue().toString().startsWith("channel")) {
            // TODO
         } else {
            int i = jListChannels.getSelectedIndex();
            IDCManager.channels.remove(i);
            
            ListData m = (ListData) jListChannels.getModel();
            m.refreshList();
            
            jtrep.remove(i);
         }
      }
   }//GEN-LAST:event_jButton4MouseClicked
   
   // double clique sur la liste des nickname
   private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
      // TODO add your handling code here:
      if (evt.getClickCount() == 2) {
            int i=0;
            if (jListNicknames.getSelectedIndices()[i] != -1) {
               if (jListChannels.getSelectedIndex() != -1 && jListChannels.getSelectedValue().toString().startsWith("channel")) {
                  // TODO
               } else {
                     Node tp = (Node)((ListData)jListNicknames.getModel()).getRawElement(jListNicknames.getSelectedIndices()[i]);
                     byte[] id = tp.getId();
                     Channel c = new Channel(jListNicknames.getSelectedValues()[i] + " (privée)");
                     if (!IDCManager.channels.contains(c)) {
                        IDCManager.askForRequest(IDCManager.myNode.getId(), id, c);
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
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jButton1;
   private javax.swing.JButton jButton2;
   private javax.swing.JButton jButton3;
   private javax.swing.JButton jButton4;
   private javax.swing.JEditorPane jEditorPane1;
   private javax.swing.JFormattedTextField jFormattedTextField1;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   public static javax.swing.JList jListChannels;
   public static javax.swing.JList jListNicknames;
   private javax.swing.JMenu jMenu1;
   private javax.swing.JMenu jMenu2;
   private javax.swing.JMenuBar jMenuBar1;
   private javax.swing.JMenuItem jMenuItem1;
   private javax.swing.JMenuItem jMenuItem11;
   private javax.swing.JMenuItem jMenuItem12;
   private javax.swing.JMenuItem jMenuItem13;
   private javax.swing.JMenuItem jMenuItem2;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JScrollPane jScrollPane4;
   public static javax.swing.JTextArea jTextArea1;
   private javax.swing.JTextField jTextField1;
   // End of variables declaration//GEN-END:variables
}




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
