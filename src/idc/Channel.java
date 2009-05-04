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

public class Channel extends Object implements Serializable,Cipherable{

	private int id;
	private boolean isciphered=false;
	private String name;
	private byte[] data;
	private byte[] digest ;
	private ArrayList nodes;
	private SecretKey secret_key;
	
	public void destroyKey(){
		integrity();
		secret_key=null;
		integrity();
	}
	
	public int getId() {
		integrity();
		return id;
	}

	
	public void buildKey(){
		integrity();
		secret_key=SecretKey.class.cast(data);
		integrity();
	}
	
	public boolean isCiphered(){
		integrity();
		return isciphered;
	}

	public void setAsCiphered(boolean bool){
		integrity();
		isciphered=bool;
		integrity();
	}
	
	public byte[] getData(){
		integrity();
		return data;
	}
	
	public void setData(byte[] tab){
		integrity();
		data=tab;
		integrity();
	}
	
	public byte[] getSecretKey(){	
		integrity();
		return secret_key.toString().getBytes();
	}
	
	public void setSignature(byte[] s){
		integrity();
		digest=s;
		integrity();
	}
	
	private void writeObject(ObjectOutputStream stream) throws IOException {
		integrity();
		
		
		// stream.writeObject(isCiphered);
		stream.writeInt(id);
		stream.writeUTF(name);
		stream.writeObject(data);
		stream.writeObject(digest);
		stream.writeObject(isciphered);
		integrity();
	}

	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		integrity();
		
		 
		id=stream.readInt();
		name=stream.readUTF();
		data = (byte[]) stream.readObject();
		digest = (byte[]) stream.readObject();
		isciphered=stream.readBoolean();
		integrity();
	}
	
	public String getName() {
		integrity();
		return name;
	}

	public Channel(String name) {
		this.name = name;
		nodes = new ArrayList();
		id = IdGen.getID();
		data=new byte[0];
		digest=shasum(name+nodes.toString()+id);
		
		CreateSecretKey();
		
		integrity();
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

	public byte[] getDigest(){
		integrity();
		return digest;
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
}
