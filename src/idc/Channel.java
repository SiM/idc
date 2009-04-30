package idc;

import idc.FriendNode;
import idc.Node;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

public class Channel {

   private int id;
   private String name;
   private List nodes;
   private SecretKey secret_key;

   public int getId() {
      integrity();
      return id;
   }

   public String getName() {
      integrity();
      return name;
   }

   public Channel(String name) {
      this.name = name;
      nodes = new ArrayList();
      id = IdGen.getID();
      CreateSecretKey();
      integrity();
   }

   public void addNode(Node node) {
      integrity();

      if (node != null) {
         return;
      } else {
         nodes.add(node);
      }
   }

   public List getNodeList() {
      integrity();
      return nodes;
   }

   public void integrity() {
      assert (name != null);
      assert (name.length() >= 0);
      assert (nodes != null);
   }

   public void CreateSecretKey() {
      try {
         integrity();

         KeyGenerator Gen = KeyGenerator.getInstance("AES");
         Gen.init(Config.AES_size);
         secret_key = Gen.generateKey();
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      }
      integrity();
   }

   public void cipher(Message message) {
      integrity();
      try {
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(Cipher.ENCRYPT_MODE, secret_key);
         byte[] result = cipher.doFinal(message.getData());
         message.setData(result);
         message.setAsCiphered(true);
      } catch (IllegalBlockSizeException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      } catch (BadPaddingException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      } catch (InvalidKeyException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      } catch (NoSuchPaddingException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      }
      integrity();
   }

   public void decipher(Message message) {
      integrity();
      try {
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(Cipher.DECRYPT_MODE, secret_key);
         byte[] result = cipher.doFinal(message.getData());
         message.setData(result);
         message.setAsCiphered(true);
      } catch (IllegalBlockSizeException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      } catch (BadPaddingException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      } catch (InvalidKeyException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      } catch (NoSuchPaddingException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
      }
      integrity();
   }
}
