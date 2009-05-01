package idc;

import java.util.Date;
import java.io.*;
import java.nio.charset.*;

import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

/**
 *
 * @author fridim
 */
public class Message implements Serializable, Cipherable {

   private Node sender;
   private Date date;
   private boolean isCiphered;
   private byte[] data;
   private byte[] id;
   private byte[] signature;

   public Message(String message, Node sender) {
      this.sender = sender;
      date = new Date();
      data = message.getBytes();
      id = shasum(new String(data) + date.toString() + sender.getId());
      signature = shasum(new String(data));

      integrity();
   }

   public boolean isCiphered() {
      integrity();
      return isCiphered;
   }

   public void setAsCiphered(boolean bool) {
      integrity();
      isCiphered = bool;
   }

   public String getMessage() {
      integrity();
      return new String(data);
   }

   public byte[] getDigest() {
      integrity();
      return shasum(new String(data));
   }

   private byte[] shasum(String in) {
      try {
         MessageDigest shasum = MessageDigest.getInstance("SHA-256");
         return shasum.digest(in.getBytes());
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
         System.exit(-1);
      }
      return new byte[0];
   }

   public void setSignature(byte[] s) {
      signature = s;
      integrity();
   }

   public byte[] getData() {
      integrity();
      return data;
   }

   public void setData(byte[] ocTab) {
      integrity();
      data = ocTab;
      integrity();
   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      integrity();
      stream.defaultWriteObject();
      /*
      stream.writeObject(size);
      stream.writeObject(date);
      stream.writeObject(sender);
      stream.writeObject(signature);
      stream.writeObject(id);
       */
      integrity();
   }

   private void readObject(ObjectInputStream stream) throws IOException,
           ClassNotFoundException {
      integrity();
      stream.defaultReadObject();
      /*
      size = (Integer) stream.readObject();
      date = (Date) stream.readObject();
      sender = (Node) stream.readObject();
      signature = (byte[]) stream.readObject();      
      id = (byte[]) stream.readObject();
       */
      integrity();
   }

   private void integrity() {
      assert (sender != null);
      assert (id != null);
      assert (id.length > 0);
      assert (data != null);
      assert (data.length > 0);
      assert (signature != null);
      assert (signature.length > 0);
   }
}

