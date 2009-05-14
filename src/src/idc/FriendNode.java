/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import ihm.Accueil;

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

   private InetAddress address;
   private int port = Config.port;

   public FriendNode(String nickname, byte[] id, InetAddress address) {
      super(nickname, id);

      this.address = address;
      integrity();
   }

   protected void integrity() {
      super.integrity();
      assert (address != null);
   }

   public InetAddress getAddress() {
      integrity();
      return address;
   }

   public void send(Object message) {
      Socket socket = null;
      ObjectOutputStream out = null;
      ObjectInputStream in = null;

      try {
         socket = new Socket(address, port);
         out = new ObjectOutputStream(socket.getOutputStream());
         in = new ObjectInputStream(socket.getInputStream());
         out.flush();
         out.writeObject(message);
      } catch (Exception e) {
    	  IDCManager.friends.remove(this);
          String dial = new String(ServerThread.getHeure()+" ### "+this.getNickname() + " a quitté le salon ### \n");
          Accueil.jtrep.get(0).append(dial);
          Accueil.jTextArea1.setText(Accueil.jtrep.get(0).getText());
          e.printStackTrace(System.err);
      }
   }

      
   // public Message receive() {}
   @Override
   public boolean equals(Object aThat) {
      // check for self-comparison
      if (this == aThat) {
         return true;
      }

      // use instanceof instead of getClass here for two reasons
      // 1. if need be, it can match any supertype, and not just one class;
      // 2. it renders an explict check for "that == null" redundant, since
      // it does the check for null already - "null instanceof [type]" always
      // returns false. (See Effective Java by Joshua Bloch.)

      if (!(aThat instanceof FriendNode)) {

         return false;
      }
      // Alternative to the above line :
      // if ( aThat == null || aThat.getClass() != this.getClass() ) return
      // false;

      // cast to native object is now safe
      FriendNode that = (FriendNode) aThat;

      // now a proper field-by-field evaluation can be made
      return this.getNickname().equals(that.getNickname()) /*&& this.id.equals(that.id) */ && this.address.equals(that.address);
   }
}
