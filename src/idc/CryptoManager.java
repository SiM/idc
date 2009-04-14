package idc;

import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.math.*;
import idc.Config;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

public class CryptoManager {

    private byte[] PubKey;
    private byte[] PrivKey;
    private Vector SecretKeyVect;
    private Signature IdSign;

    public CryptoManager() {
    	
    	SecretKeyVect=new Vector();
    	
        try {
            FileOutputStream FileStreamPubO = new FileOutputStream(Config.FilePub);
            FileOutputStream FileStreamPrivO = new FileOutputStream(Config.FilePriv);
            FileInputStream FileStreamPubI =new FileInputStream(Config.FilePub);
            FileInputStream FileStreamPrivI =new FileInputStream(Config.FilePriv);
            
            KeyPairGenerator KeyFact = KeyPairGenerator.getInstance("RSA");
            KeyFact.initialize(1024);
            KeyPair PairOfKey = KeyFact.genKeyPair();

            PublicKey public_key = PairOfKey.getPublic();
            PrivateKey private_key = PairOfKey.getPrivate();
            
            PubKey = public_key.getEncoded();
            PrivKey = private_key.getEncoded();

            System.out.println("publick key :"+PubKey);
            System.out.println("private key :"+PrivKey);
            	
            if (Config.FilePub.isFile()&&Config.FilePub.exists()) {
                FileStreamPubO.write(PubKey);
            }else{
            	FileStreamPubI.read(PubKey);
            }


            if (Config.FilePriv.isFile()) {
                FileStreamPrivO.write(PrivKey);
            }else{
            	FileStreamPrivI.read(PrivKey);
            }

            //------------- AUTHENTIFICATION ---------------------

            IdSign = Signature.getInstance("SHA1withRSA");
            IdSign.initSign(private_key);

            /* Update and sign the data */
            //IdSign.update(data);
            byte[] sig = IdSign.sign();

            IdSign.initVerify(public_key);

            /* Update and verify the data */
            //IdSign.update(data);
            boolean verifies = IdSign.verify(sig);


            System.out.println("signature verifies: " + verifies);

        } catch (SignatureException err) {
            System.out.println(err);
        } catch (NoSuchAlgorithmException err) {
            System.out.println(err);
        } catch (Exception err) {
            System.out.println(err);
        }

        integrity();

    }

    static public SecretKey CreateSecretKey(){
    	    	
    	int i;
    	SecretKey Secret;
    
    	try{
    	
    	Cipher coding=Cipher.getInstance("AES");
    	Cipher decoding=Cipher.getInstance("AES");
    	
    	KeyGenerator Gen=KeyGenerator.getInstance("AES");
    	Gen.init(256);
    	Secret=Gen.generateKey();
    	
    	//for the moment the key is not bounded to the cipher.
    	//now we bound both elements.
    	
       	System.out.println(Secret);
      
       	
    	}catch(NoSuchPaddingException err){
    		System.out.println(err);
    	}catch(NoSuchAlgorithmException err){
    		System.out.println(err);
    	}
    	
     	return Secret;
    	
    	
    }
    
    static void codeAndSend(Message msg,Node node){
    	
    }
    
    public Message code(Message msg){
    	integrity();
    	
    	return msg;
    }
    
    public Message decode(Message msg) {
        integrity();
        return msg;
    }

    private void integrity() {
    	assert(IdSign!=null);
    	assert(PubKey!=null);
    	assert(PrivKey!=null);
    }
}
