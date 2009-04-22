package idc;

import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.math.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import idc.Config;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

public class CryptoManager {

	private byte[] PubKey;

	private byte[] PrivKey;

	private Vector <SecretKey> KeyVect;
	
	private Signature IdSign;

	public CryptoManager() {

		KeyVect= new Vector(100,100);
				
		try {
			FileOutputStream FileStreamPubO = new FileOutputStream(
					Config.FilePub);
			FileOutputStream FileStreamPrivO = new FileOutputStream(
					Config.FilePriv);
			FileInputStream FileStreamPubI = new FileInputStream(Config.FilePub);
			FileInputStream FileStreamPrivI = new FileInputStream(
					Config.FilePriv);

			KeyPairGenerator KeyFact = KeyPairGenerator.getInstance("RSA");
			KeyFact.initialize(1024);
			KeyPair PairOfKey = KeyFact.genKeyPair();

			PublicKey public_key = PairOfKey.getPublic();
			PrivateKey private_key = PairOfKey.getPrivate();

			PubKey = public_key.getEncoded();
			PrivKey = private_key.getEncoded();

			if (Config.FilePub.isFile() && Config.FilePub.exists()) {
				FileStreamPubO.write(PubKey);
			} else {
				FileStreamPubI.read(PubKey);
			}

			if (Config.FilePriv.isFile()) {
				FileStreamPrivO.write(PrivKey);
			} else {
				FileStreamPrivI.read(PrivKey);
			}

			// ------------- AUTHENTIFICATION ---------------------

			IdSign = Signature.getInstance("SHA1withRSA");
			IdSign.initSign(private_key);

			/* Update and sign the data */
			// IdSign.update(data);
			byte[] sig = IdSign.sign();

			IdSign.initVerify(public_key);

			/* Update and verify the data */
			// IdSign.update(data);
			boolean verifies = IdSign.verify(sig);

			//System.out.println("signature verifies: " + verifies);

		} catch (SignatureException err) {
			System.out.println(err);
		} catch (NoSuchAlgorithmException err) {
			System.out.println(err);
		} catch (Exception err) {
			System.out.println(err);
		}

		integrity();

	}

	public void CreateSecretKey() {
		integrity();

		try {
			/**
			 * On ne créé un CipherEngine que lorsque l'on créé la clef qui va
			 * avec. De cette manière on a toujours à disposition le
			 * CipherEngine correspondant à la clef.
			 */

			KeyGenerator Gen = KeyGenerator.getInstance("AES");
			Gen.init(128);
			SecretKey secret = Gen.generateKey();
					
			if(KeyVect.size()<Channel.getId()){
				KeyVect.setSize(Channel.getId());
			}
			
			KeyVect.add(Channel.getId(), secret);
						
			
		} catch (NoSuchAlgorithmException err) {
			System.out.println(err);
		}
			
		integrity();

	}

	static void codeAndSend(Message msg, int id_chan) {

	}

	public Message code(int id_chan, Message msg) {
		
		Coder makeIt = new Coder( KeyVect.elementAt(id_chan), msg);
		makeIt.start();
		
		try {
			makeIt.join();	
		} catch (InterruptedException err) {
			System.out.println(err);
		}
		
		msg.data="null";
		msg.setAsCiphered(true);
		
		return msg;
	}

	public Message decode(int id_chan, Message msg) {
	
		
		Decoder makeIt = new Decoder(KeyVect.elementAt(id_chan), msg);
		makeIt.start();
		
		try {
			makeIt.join();		
		} catch (InterruptedException err) {
			System.out.println(err);
		}
		 
		msg.crypted=new byte[0];
		msg.setAsCiphered(false);
		
		return msg;
	}

	private void integrity() {
		assert (IdSign != null);
		assert (PubKey != null);
		assert (PrivKey != null);
	}
}

/**
 * 
 * @author el-indio le constructeur ne prends pas de String indiquant
 *         l'agorithme à utilisé car dans tout les cas il s'agît de AES.
 */

class Coder extends Thread {
	private SecretKey key_chan;
	private Message message;

	public Coder(SecretKey key, Message msg) {
		key_chan= key;
		message = new Message(msg);
		
		integrity();
	}

	private void integrity() {
		assert (key_chan != null);
		assert (message != null);
	}

	public void run() {
		integrity();
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE,key_chan);
			
			message.crypted=cipher.doFinal(message.getByte());
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}catch(NoSuchPaddingException err){
			System.out.println(err);
		}catch(InvalidKeyException err){
			System.out.println(err);
		}catch(NoSuchAlgorithmException err){
			System.out.println(err);
		}
		
		integrity();
	}
}

class Decoder extends Thread {
	private SecretKey key_chan;
	private Message message;

	public Decoder(SecretKey key, Message msg) {
		key_chan= key;
		message = new Message(msg);
		
		integrity();
	}

	private void integrity() {
		assert (key_chan != null);
		assert (message != null);
	}

	public void run() {
		integrity();
		int i,lack;
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE,key_chan);
			
			if(message.getByte().length%16!=0)
			{
				lack=16-(message.getByte().length%16);
				for(i=0;i<lack;i++){
					message.data+=" ";
				}
				
			}
			System.out.println(message.getByte().length%16);
			
			message.crypted=cipher.doFinal(message.data.getBytes("UTF-8"));
			
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}catch(NoSuchPaddingException err){
			System.out.println(err);
		}catch(InvalidKeyException err){
			System.out.println(err);
		}catch(NoSuchAlgorithmException err){
			System.out.println(err);
		}catch(UnsupportedEncodingException err){
			System.out.println(err);
		}
		
		
		integrity();
	}
}


