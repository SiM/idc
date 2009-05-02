/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import java.io.*;
import java.net.*;

/**
 *
 * @author fridim
 * 
 * On ajoute à Node une adresse pour pouvoir s'y connecter. Il s'agit d'une
 * connexion directe.
 */
public class FriendNode extends Node {

   private String address;
   private int port = Config.port;

   public FriendNode(String nickname, String address) {
      super(nickname);

      this.address = address;
      integrity();
   }

   protected void integrity() {
      super.integrity();
      assert (address != null);
      assert (address.length() > 0);
   }

   public String getAddress(){
	   integrity();
	   return address;
   }
   
   public void send(Message message) {
      Socket socket = null;
      ObjectOutputStream out = null;
      ObjectInputStream in = null;

      try {
         socket = new Socket(address, port);
         out = new ObjectOutputStream(socket.getOutputStream());
         in = new ObjectInputStream(socket.getInputStream());
         out.flush();
      } catch (UnknownHostException e) {
         e.printStackTrace(System.err);
      } catch (IOException e) {
         e.printStackTrace(System.err);
      }

      try {
         out.writeObject(message);
      } catch (IOException e) {
         e.printStackTrace(System.err);
      }
   }
   //public Message receive() {}
}
