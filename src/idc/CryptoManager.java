package idc;

import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.math.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher.*;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

public class CryptoManager {
  
  private PublicKey public_key;
  private PrivateKey private_key;
  private Signature IdSign;

   public CryptoManager() {
      try {
         loadKeyPair();
      } catch (IOException ex) {
         Logger.getLogger(CryptoManager.class.getName()).log(Level.SEVERE, null, ex);
         genKeyPair();
      }

      integrity();
   }

   private void loadKeyPair() throws IOException {
      try {
         ObjectInputStream pub = new ObjectInputStream(new FileInputStream(Config.FilePub));
         ObjectInputStream priv = new ObjectInputStream(new FileInputStream(Config.FilePriv));

         public_key = (PublicKey) pub.readObject();
         private_key = (PrivateKey) priv.readObject();
      } catch (ClassNotFoundException ex) {
         Logger.getLogger(CryptoManager.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private void genKeyPair() {
      {
         try {
            KeyPairGenerator KeyFact = KeyPairGenerator.getInstance("RSA");
            KeyFact.initialize(Config.RSA_size);
            KeyPair PairOfKey = KeyFact.genKeyPair();
            public_key = PairOfKey.getPublic();
            private_key = PairOfKey.getPrivate();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Config.FilePub));
            out.writeObject(public_key);

            out = new ObjectOutputStream(new FileOutputStream(Config.FilePriv));
            out.writeObject(private_key);
         } catch (IOException ex) {
            Logger.getLogger(CryptoManager.class.getName()).log(Level.SEVERE, null, ex);
         } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptoManager.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   public void SignMessage(Message msg) {
      integrity();

      try {
         Cipher rsaCoder = Cipher.getInstance("RSA");
         rsaCoder.init(Cipher.ENCRYPT_MODE, private_key);

         byte[] coded = rsaCoder.doFinal(msg.getDigest());
         msg.setSignature(coded);
      } catch (NoSuchAlgorithmException err) {
         System.out.println(err);
      } catch (NoSuchPaddingException err) {
         System.out.println(err);
      } catch (InvalidKeyException err) {
         System.out.println(err);
      } catch (IllegalBlockSizeException err) {
         System.out.println(err);
      } catch (BadPaddingException err) {
         System.out.println(err);
      }
   }
   
   static public String getId() {
      // TODO
      return "ID:myid";
   }

   private void integrity() {
     assert (IdSign != null);
     assert (PubKey != null);
     assert (PrivKey != null);
   }
}

