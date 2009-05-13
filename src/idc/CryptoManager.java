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

	/**
	 * 
	 * @author el-indio le constructeur ne prends pas de String indiquant
	 *         l'agorithme à utilisé car dans tout les cas il s'agît de AES.
	 */

	static public PublicKey public_key;

	static private PrivateKey private_key;

	static private byte[] id;
	
	static private HashMap<byte[], PublicKey> pubKeyMap;
	
        static public  byte[] shasum(byte[] in) {
		try {
			MessageDigest shasum = MessageDigest.getInstance("SHA-256");
			return shasum.digest(in);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null,
					ex);
			System.exit(-1);
		}
		return new byte[0];

	}
	
	public CryptoManager() {
		pubKeyMap=new HashMap<byte[], PublicKey>(100,100);
		try {
			loadKeyPair();
		} catch (IOException ex) {
			Logger.getLogger(CryptoManager.class.getName()).log(Level.SEVERE,
					null, ex);
			genKeyPair();
		}
                id = shasum(public_key.getEncoded());

		integrity();
	}

	static private void loadKeyPair() throws IOException {
		try {
			ObjectInputStream pub = new ObjectInputStream(new FileInputStream(
					Config.FilePub));
			ObjectInputStream priv = new ObjectInputStream(new FileInputStream(
					Config.FilePriv));

			public_key = (PublicKey) pub.readObject();
			private_key = (PrivateKey) priv.readObject();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(CryptoManager.class.getName()).log(Level.SEVERE,
					null, ex);
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

				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(Config.FilePub));
				out.writeObject(public_key);

				out = new ObjectOutputStream(new FileOutputStream(
						Config.FilePriv));
				out.writeObject(private_key);
			} catch (IOException ex) {
				Logger.getLogger(CryptoManager.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (NoSuchAlgorithmException ex) {
				Logger.getLogger(CryptoManager.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	
	static void addPubKey(byte[] id,PublicKey key){
		integrity();
		pubKeyMap.put(id,key);
		integrity();
		
	}
	
	static public void SignChannel(Channel chan){
		integrity();

		try {
			Cipher rsaCoder = Cipher.getInstance("RSA");
			rsaCoder.init(Cipher.ENCRYPT_MODE, private_key);
			byte[] coded = rsaCoder.doFinal(chan.getDigest());
			chan.setSignature(coded);
			
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
	
	static public Channel decryptChannel(Agreement agr){
		integrity();
				
		try{
			loadKeyPair();
			Cipher rsaDeCoder = Cipher.getInstance("RSA");
			rsaDeCoder.init(Cipher.DECRYPT_MODE,private_key);
			/**
			 * on recupère tout les champs de l'objet channel;
			 */
			
			byte[] decoded = rsaDeCoder.doFinal(agr.getChannel().getData());
			agr.getChannel().setData(decoded);
			
			
		}catch (NoSuchAlgorithmException err) {
			System.out.println(err);
		} catch (NoSuchPaddingException err) {
			System.out.println(err);
		} catch (InvalidKeyException err) {
			System.out.println(err);
		} catch (IllegalBlockSizeException err) {
			System.out.println(err);
		} catch (BadPaddingException err) {
			System.out.println(err);
		}catch(IOException err){
			System.out.println(err);
		}
		
		
		if(!checkSessionKey(agr, agr.getChannel().getData())){
			return null;
		}
		/**
		 * on reconstruit la clef a partir de data.
		 */ 
		
		agr.getChannel().buildKey();
		
		integrity();
		return agr.getChannel(); 
	}	
	
	static boolean checkSessionKey(Agreement agr,byte[] msg){
		integrity();
		
		byte[] digest=new  byte[0];
		byte[] auth=new byte[0];
		
		try{
			Cipher rsaAuth = Cipher.getInstance("RSA");
			rsaAuth.init(Cipher.DECRYPT_MODE,agr.getPubKey());
			auth=rsaAuth.doFinal(agr.getChannel().getDigest());
			MessageDigest Auth = MessageDigest.getInstance("SHA-256");
			digest=Auth.digest(msg);	
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
		
		integrity();
		return digest==auth;
		
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

	static public void keyExchangeProcess(Channel chan,PublicKey pub) {
		/**
		 * autor : el-indio on envoi la clef de session en chiffré avec le
		 * digest ( chiffré lui avec la clef RSA privée) exactement de la même
		 * manière qu'on le fait pour les messages chiffrés.
		 * l'integrité de la clef doit être vérifier a l'aide du tableau integrityOfTheKey
		 * qui est lui aussi chiffré.
		 */
		assert(chan!=null);
		assert(pub!=null);
		try {
			loadKeyPair();
			Cipher rsaCoder = Cipher.getInstance("RSA");
			
			
			
			rsaCoder.init(Cipher.ENCRYPT_MODE, pub);

			SignChannel(chan);
			chan.setData(rsaCoder.doFinal(chan.getSecretKey()));
			chan.destroyKey();
			
			chan.setAsCiphered(true);
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
		}catch(IOException err){
			System.out.println(err);
		}

	}

	static public byte[] getId() {
		// TODO
		return id.clone();
	}

	

	static private void integrity() {
		assert (id != null);
                assert(id.length > 0);
		assert (public_key != null);
		assert (private_key != null);
	}

}
