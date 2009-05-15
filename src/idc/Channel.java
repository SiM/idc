package idc;

import idc.FriendNode;
import idc.Node;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;
import java.io.*;


public class Channel extends Object implements Serializable, Cipherable {

   private byte[] id;
   private boolean isciphered = false;
   private String name;
   private byte[] data;
   private byte[] digest;
   private ArrayList nodes;
   static private SecretKey secret_key;
   private Random gen;

   public void destroyKey() {
      integrity();
      secret_key = null;
      integrity();
   }

   public byte[] getId() {
      integrity();
      return id;
   }

   public void buildKey() {
      integrity();
      secret_key = SecretKey.class.cast(data);
      integrity();
   }

   public boolean isCiphered() {
      integrity();
      return isciphered;
   }

   public void setAsCiphered(boolean bool) {
      integrity();
      isciphered = bool;
      integrity();
   }

   public byte[] getData() {
      integrity();
      return data;
   }

   public void setData(byte[] tab) {
      integrity();
      data = tab;
      integrity();
   }

   public byte[] getSecretKey() {
      integrity();
      return secret_key.getEncoded();
   }

   public void setSignature(byte[] s) {
      integrity();
      digest = s;
      integrity();
   }

   private void writeObject(ObjectOutputStream stream) throws IOException {

      integrity();

      // stream.writeObject(isCiphered);
      stream.writeObject(id);
      stream.writeObject(nodes);
      stream.writeUTF(name);
      stream.writeObject(digest);
      stream.writeBoolean(isciphered);
      stream.writeObject(data);

   }

   private void readObject(ObjectInputStream stream) throws IOException,
           ClassNotFoundException {

      id = (byte[]) stream.readObject();
      nodes = (ArrayList) stream.readObject();
      name = stream.readUTF();
      digest = (byte[]) stream.readObject();
      isciphered = stream.readBoolean();
      data = (byte[]) stream.readObject();
      integrity();
   }

   public String getName() {
      integrity();
      return name;
   }

   public Channel(String name) {
      this.name = name;
      nodes = new ArrayList<Node>(10);
      
      gen = new Random();
      byte[] raw = new byte[256];
      gen.nextBytes(raw);
      id = CryptoManager.shasum(raw);
      
      data = new byte[0];
      digest = shasum(name); // TODO

      CreateSecretKey();

      integrity();
   }

   public void send(Message msg) {

   }

   private byte[] shasum(String in) {
      try {
         MessageDigest shasum = MessageDigest.getInstance("SHA-256");
         return shasum.digest(in.getBytes());
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null,
                 ex);
         System.exit(-1);
      }
      return new byte[0];

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
      /**
       * the constructor is not yet finished. So, a call to the method 'integrity' 
       * is forbiden.
       */
      try {
         KeyGenerator Gen = KeyGenerator.getInstance("AES");
         Gen.init(Config.AES_size);
         secret_key = Gen.generateKey();
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      }
   }

   public byte[] getDigest() {
      integrity();
      return digest;
   }

   static public void cipher(Message message) {
      try {
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(Cipher.ENCRYPT_MODE, secret_key);
         byte[] result = cipher.doFinal(message.getData());
         message.setData(result);
         message.setAsCiphered(true);
      } catch (IllegalBlockSizeException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      } catch (BadPaddingException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      } catch (InvalidKeyException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      } catch (NoSuchPaddingException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      }
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
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      } catch (BadPaddingException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      } catch (InvalidKeyException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      } catch (NoSuchPaddingException ex) {
         Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null,
                 ex);
      }
      integrity();
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

      if (aThat instanceof Channel) {
         Channel that = (Channel) aThat;

         // now a proper field-by-field evaluation can be made
         return this.getId().equals(that.getId()) || this.getName().equals(that.getName());

      }
      
      if (aThat instanceof byte[]) {
         byte[] thatid = (byte[]) aThat;
         return Arrays.equals(this.getId(), thatid);
      }
      
      if (aThat instanceof String) {
         String thatName = (String) aThat;
         return this.getName().equals(thatName);
      }
      
      return false;
      // Alternative to the above line :
      // if ( aThat == null || aThat.getClass() != this.getClass() ) return
      // false;

      // cast to native object is now safe
     
   }

}
