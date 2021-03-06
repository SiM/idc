
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package idc;
 
import java.util.*;
import java.security.*;
import java.security.spec.*;
import java.io.IOException;
import java.io.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
 
/**
*
* @author fridim
*
* Cette classe identifie un noeud du réseau. Selon le cahier des charges, un
* noeud est identifié par un nickname et par un id.
*/
public class Node implements Serializable, Com {
 
   private String nickname;
   private byte[] key;
   static public PublicKey public_key;
   private byte[] id;
 
   public Node(String nickname, byte[] id) {
 
      if (nickname == null || nickname.length() <= 0) {
         nickname = "Anonymous";
      }
      this.nickname = new String(nickname);
      this.id = id;
   }
 
   private void writeObject(ObjectOutputStream stream) throws IOException {
      integrity();
      stream.writeObject(nickname);
      stream.writeObject(id);
      stream.writeObject(key);
   }
 
   private void readObject(ObjectInputStream stream) throws IOException,
           ClassNotFoundException {
      nickname = (String) stream.readObject();
      id = (byte[]) stream.readObject();
      key = (byte[]) stream.readObject();
 
      integrity();
   }
 
   public byte[] getId() {
      integrity();
      return id.clone();
   }
 
   public void send(Message msg) {
      assert (msg != null);
      integrity();
   /*
* C'est le manager qui choisi le noeud ami (connexion directe) pour
* envoyer le message. Ce dernier va ensuite être routé
*/
   // IDCManager.send(msg);
   }
 
   public String getNickname() {
      integrity();
      return nickname;
   }
 
   protected void integrity() {
      assert (nickname != null);
      assert (id != null);
      assert (id.length > 0);
      assert (nickname.length() > 0);
   }
 
   public boolean verisign(Message msg) {
      if (public_key == null) {
         return false;
      } // TODO : il faudrait faire une request de clef publique
      
      byte[] coded;
      try {
         Cipher rsaCoder = Cipher.getInstance("RSA");
         rsaCoder.init(Cipher.DECRYPT_MODE, public_key);
 
         coded = rsaCoder.doFinal(msg.getDigest());
 
      } catch (IllegalBlockSizeException ex) {
         Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
         return false;
      } catch (BadPaddingException ex) {
         Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
         return false;
      } catch (InvalidKeyException ex) {
         Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
         return false;
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
         return false;
      } catch (NoSuchPaddingException ex) {
         Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
         return false;
      }
 
      return coded.equals(CryptoManager.shasum(msg.getData()));
   }
 
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
 
      if (!(aThat instanceof Node)) {
 
         return false;
      }
      // Alternative to the above line :
      // if ( aThat == null || aThat.getClass() != this.getClass() ) return
      // false;
 
      // cast to native object is now safe
      Node that = (Node) aThat;
 
      // now a proper field-by-field evaluation can be made
      return this.nickname.equals(that.nickname) && this.id.equals(that.id);
 
   }
}
 
