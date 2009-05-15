/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import ihm.*;
import java.net.*;
import java.io.*;
import java.util.Calendar;
import javax.swing.JOptionPane;
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

   public static String formatChiffre(int c) {
       String s = new String();
       if (c < 10)
           s = "0"+c;
       else
           s = ""+c;
       return s;
   }

   public static String getHeure() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
       return ("["+formatChiffre(hour)+":"+formatChiffre(minute)+"]");
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
                 int indice_chan = 0;
        	 if(((Message)message).isCiphered()){
                    Message m = (Message) message;
                    Channel c = (Channel) IDCManager.channels.get(IDCManager.channels.indexOf(m.getChan()));
                    if (c != null) {
                     c.decipher(m);
                     indice_chan = IDCManager.channels.indexOf(c);
                    }
        		// IDCManager.getChannels().get(((Message)message).getIdChan());
        	 }
            // Ici on fait quelque chose avec le message

            // par exemple on l'affiche :
            //System.out.println("PASSAGE DANS LE TEST");
            //System.out.println("message :" + ((Message) message).getMessage());
            Accueil.jtrep.get(indice_chan).append(getHeure()+ " " +((Message) message).getMessage());
            Accueil.jTextArea1.setText(Accueil.jtrep.get(indice_chan).getText());
            
         } else if (message.getClass().toString().equals("class idc.Request")) {
            
        	 Request req = (Request) message;
             System.out.println("RESQUEST CATCHED !");
     

            if (req.getKey() != null) {
            	
               if (!(HexBin.encode(CryptoManager.shasum(req.getKey().getEncoded())).equals(HexBin.encode(req.getSource())))) {
                  // si la clef publique passée en requete ne correspond pas à l'id on avertie
                  JOptionPane.showMessageDialog(null, "<html>Attention, la clef RSA et l'ID ne correspondent pas :<br><small> ID : " + HexBin.encode(req.getSource()));
               }

               if (!CryptoManager.pubKeyMap.containsKey(req.getSource())) {
                  // on ajoute si la clef n'existe pas et on avertit
                  //JOptionPane.showMessageDialog(null, "<html>Clef publique ajoutée.<br> <small>ID : " + HexBin.encode(req.getSource()));
                  CryptoManager.addPubKey(((Request) message).getSource(), ((Request) message).getKey());
               }

            }

            IDCManager.catchRequest(((Request) message));

         } else if (message.getClass().toString().equals("class idc.Agreement")) {
            assert(message != null);
            
            String me = new String(IDCManager.myNode.getId());
    		String targ = new String(((Agreement)message).getTarget());
    		System.out.println("AGREEMENT TARGET EQUALS ?"+me+" -----the target---- "+targ);
    		if (!me.contentEquals(new StringBuffer(targ))) {
    			System.out.println("Agreement not for me !");
    			return;
    		}
    		System.out.println("AGREEMENT RECEIVED");
            
            CryptoManager.decryptChannel(((Agreement) message));
            //Channel chan = CryptoManager.decryptChannel(((Agreement) message));
            Channel chan = ((Agreement) message).getChannel();
            
            assert(chan != null);
            
            IDCManager.addChannel(chan);
         } else if (message.getClass().toString().equals("class idc.QuitNotice")) {
            IDCManager.send(new Void());
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
