package idc;

import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.math.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher.*;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import idc.Config;
import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

import com.sun.crypto.provider.RSACipher;

public class CryptoManager {

	private byte[] PubKey;

	private byte[] PrivKey;

	private PublicKey public_key;
	
	private PrivateKey private_key;
	
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

			public_key = PairOfKey.getPublic();
			private_key = PairOfKey.getPrivate();
			
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

		
		} catch (NoSuchAlgorithmException err) {
			System.out.println(err);
		} catch (Exception err) {
			System.out.println(err);
		}

		integrity();

	}
	
	public void SignMessage(Message msg){
		integrity();
		msg.authentification();
		
		try{
		Cipher rsaCoder=Cipher.getInstance("RSA");
		rsaCoder.init(Cipher.ENCRYPT_MODE, private_key);
		
		byte[] coded=rsaCoder.doFinal(msg.getDigest());
		msg.setDigest(coded);
		
		}catch(NoSuchAlgorithmException err){
			System.out.println(err);
		}catch(NoSuchPaddingException err){
			System.out.println(err);
		}catch(InvalidKeyException err){
			System.out.println(err);
		}catch(IllegalBlockSizeException err){
			System.out.println(err);			
		}	catch(BadPaddingException err){
			System.out.println(err);
		}
		
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
		integrity();
		Coder makeIt = new Coder( KeyVect.elementAt(id_chan), msg);
		makeIt.start();
		
		try {
			makeIt.join();	
		} catch (InterruptedException err) {
			System.out.println(err);
		}
		
		msg.setAsCiphered(true);
		integrity();
		return msg;
	}

	public Message decode(int id_chan, Message msg) {
		integrity();
		Decoder makeIt = new Decoder(KeyVect.elementAt(id_chan), msg);
		makeIt.start();
		
		try {
			makeIt.join();		
		} catch (InterruptedException err) {
			System.out.println(err);
		}
		 
		msg.setAsCiphered(false);
		integrity();
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
		message = msg;
		
		integrity();
	}

	public void fitPadding(Message message){
		integrity();
		int lack,i;
		
		if(message.getData().length%16!=0)
		{
			lack=16-(message.getData().length%16);
			String str=new String(message.getData());
			for(i=0;i<lack;i++){
				str+=" ";
			}
			message.setData(str.getBytes());
		}
		integrity();
	}
	
	private void integrity() {
		assert (key_chan != null);
		assert (message != null);
	}

	public void run() {
		integrity();
		
		this.fitPadding(message);
		
		try {
				
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");	
			cipher.init(Cipher.ENCRYPT_MODE,key_chan);
			byte[] result=cipher.doFinal(message.getData());
			message.setData(result);
			
					
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
			err.printStackTrace();
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
		message =msg;		
		integrity();
	}

	private void integrity() {
		assert (key_chan != null);
		assert (message != null);
	}
	
	public void fitPadding(Message message){
		integrity();
		int lack,i;
		
		if(message.getData().length%16!=0)
		{
			lack=16-(message.getData().length%16);
			String str=new String(message.getData());
			for(i=0;i<lack;i++){
				str+=" ";
			}
			message.setData(str.getBytes());
		}
		integrity();
	}
	
	public void run() {
		integrity();
			
		this.fitPadding(message);
	
		try {
			
			Cipher cipher=Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE,key_chan);
			byte[] result=cipher.doFinal(message.getData());
			message.setData(result);
					
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}catch(NoSuchPaddingException err){
			System.out.println(err);
		}catch(InvalidKeyException err){
			System.out.println(err);
			err.printStackTrace();
		}catch(NoSuchAlgorithmException err){
			System.out.println(err);
		}
		
		integrity();
	}
}


