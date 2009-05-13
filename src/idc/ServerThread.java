/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import ihm.*;
import java.net.*;
import java.security.PublicKey;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import static org.junit.Assert.*;

/**
 * 
 * @author fridim
 */
public class ServerThread extends Thread {

   private Socket socket = null;

   public ServerThread(Socket socket) {
      this.socket = socket;
      integrity();
   }

   public void run() {
      integrity();

      ObjectOutputStream out = null;
      ObjectInputStream in = null;

      try {

         out = new ObjectOutputStream(socket.getOutputStream());
         in = new ObjectInputStream(socket.getInputStream());
         Object message = new Object();
         message = in.readObject();

         if (message.getClass().toString().equals("class idc.Message")) {

        
        	 if(((Message)message).isCiphered()){
        		 
        	 }
            Accueil.jtrep.get(0).append(((Message) message).getMessage());
            Accueil.jTextArea1.setText(Accueil.jtrep.get(0).getText());

         } else if (message.getClass().toString().equals("class idc.Request")) {
            Request req = (Request) message;
            System.out.println("RESQUEST CATCHED !");
            if (req.getKey() != null) {
               if (!CryptoManager.shasum(req.getKey().getEncoded()).equals(req.getSource())) {
                  // si la clef publique passée en requete ne correspond pas à l'id on avertie
                  JOptionPane.showMessageDialog(null, "<html>Attention, la clef RSA et l'ID ne correspondent pas :<br><small> ID : " + HexBin.encode(req.getSource()));
               }

               if (!CryptoManager.pubKeyMap.containsKey(req.getSource())) {
                  // on ajoute si la clef n'existe pas et on avertit
                  JOptionPane.showMessageDialog(null, "<html>Clef publique ajoutée.<br> <small>ID : " + HexBin.encode(req.getSource()));
                  CryptoManager.addPubKey(((Request) message).getSource(), ((Request) message).getKey());
               }
            }

            IDCManager.catchRequest(((Request) message));

         } else if (message.getClass().toString().equals("class idc.Agreement")) {

            Channel chan = CryptoManager.decryptChannel((Agreement) message);
            IDCManager.addChannel(chan);
         }

         out.close();
         in.close();
         socket.close();
      } catch (Exception e) {
         e.printStackTrace(System.err);
      }
      integrity();
   }

   public void integrity() {
      assertTrue(socket != null);
   }
}
