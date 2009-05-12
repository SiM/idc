/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import ihm.*;
import java.net.*;
import java.security.PublicKey;
import java.io.*;

import javax.swing.JTextArea;

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

            // Ici on fait quelque chose avec le message

            // par exemple on l'affiche :
            //System.out.println("PASSAGE DANS LE TEST");
            System.out.println("message :" + ((Message) message).getMessage());
            Accueil.jtrep.get(0).append(((Message) message).getMessage());
            Accueil.jTextArea1.setText(Accueil.jtrep.get(0).getText());
            
         } else if (message.getClass().toString().equals("class idc.Request")) {
            System.out.println("RESQUEST CATCHED !");
        	if (((Request) message).getKey() != null) {
               CryptoManager.addPubKey(((Request) message).getSource(), ((Request) message).getKey());
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
