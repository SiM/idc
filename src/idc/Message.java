package idc;

import java.util.Date;
import java.io.*;
import java.nio.charset.*;
import java.lang.reflect.Array;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;

import idc.Config;

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

	private byte[] data;

	private boolean isciphered;

	private byte[] signature;

	private byte[] digest;

	public Message(String message, Node sender) {
		this.sender = sender;
		date = new Date();
		data = message.getBytes();
		signature = new byte[0];
		integrity();
		// ici on calcule signature avec sender+date+str pour éviter les
		// redondances
	}

	public Message(Message msg) {
		sender = msg.sender;
		data = msg.data;
		date = new Date();
		signature = new byte[0];
		integrity();
	}

	public boolean isCiphered() {
		integrity();
		return isciphered;
	}

	public boolean isByteEmpty() {
		integrity();
		if (data.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void setAsCiphered(boolean bool) {
		integrity();
		isciphered = bool;
	}

	public void authentification() {
		integrity();
		try {
			MessageDigest shasum = MessageDigest.getInstance("SHA1");
			digest = shasum.digest(data);
		} catch (NoSuchAlgorithmException err) {
			System.out.println(err);
		}

	}

	public String getMessage() {
		integrity();
		return new String(data);
	}

	public byte[] getDigest() {
		integrity();
		return digest;
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
		// stream.defaultWriteObject();

		stream.writeObject(date);
		// stream.writeObject(str);
		stream.writeObject(signature);
		integrity();
	}

	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		integrity();
		date = (Date) stream.readObject();
		signature = (byte[]) stream.readObject();
		integrity();
	}

	private void integrity() {
		assert (sender != null);
		assert (signature != null);
		assert (data != null);
	}
}
