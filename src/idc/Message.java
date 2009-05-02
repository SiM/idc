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
public class Message extends Object implements Serializable, Cipherable {

   private Node sender;
   private Date date;
   private boolean isCiphered;
   private byte[] data;
   private byte[] digest;
   private byte[] id;

   public Message(String message, Node sender) {
	  super();
	  this.sender = sender;
      date = new Date();
      data = message.getBytes();
      id = shasum(new String(data) + date.toString() + sender.getId());
      digest = shasum(new String(data));

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
  
  public String getMessage() {
    integrity();
    return new String(data);
  }

  public byte[] getDigest() {
    integrity();
    return digest;
  }
  
  public void setSignature(byte[] s){
	  integrity();
	  digest=s;
	  integrity();
  }
  
  public void setDigest(byte[] dig) {
    integrity();
    digest = dig;
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
    stream.writeObject(sender);
    stream.writeObject(date);
    //stream.writeObject(isCiphered);
    stream.writeObject(data);
    stream.writeObject(digest);
    stream.writeObject(id);
    integrity();
  }

  private void readObject(ObjectInputStream stream) throws IOException,
    ClassNotFoundException {
    integrity();
    sender=(Node)stream.readObject();
    date=(Date)stream.readObject();
    //isCiphered=(boolean)stream.readBoolean();
    data = (byte[])stream.readObject();
    digest=(byte[])stream.readObject();
    id=(byte[])stream.readObject();
    integrity();
  }

  private void integrity() {
    assert (sender != null);
    assert (digest != null);
    assert (data != null);
    assert (data.length > 0);
    assert (digest.length > 0);
    assert (digest!=null);
    
  }
}
