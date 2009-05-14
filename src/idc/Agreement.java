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

public class Agreement extends Object implements Serializable {

	private PublicKey RSAPub;
	private byte[] source;
	private byte[] target;
	private Channel chan;

	public Agreement(PublicKey pub, Channel channel,byte[] src,byte[] targ) {
		RSAPub = pub;
		chan = channel;
		target=targ;
		source = src;
		integrity();
	}

	public Channel getChannel() {
			integrity();
			assert(chan!=null);
			return chan;
	}	
	
	public byte[] getTarget(){
		integrity();
		return target;
	}
	
	public PublicKey getPubKey() {
		integrity();
		return RSAPub;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		integrity();
		stream.writeObject(source);
		stream.writeObject(target);
		stream.writeObject(RSAPub);
		stream.writeObject(chan);
		
	}

	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		
		source=(byte[])stream.readObject();
		target=(byte[])stream.readObject();
		RSAPub = (PublicKey) stream.readObject();
		chan =(Channel) stream.readObject();
		integrity();
	}

	public void integrity() {
		assert (RSAPub != null);
		assert (chan != null);
		assert (source!=null);
		assert(target!=null);
		//assert (chan.isCiphered());
	}

}
