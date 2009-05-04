package idc;

import java.io.*;
import java.security.PublicKey;

/**
 * 
 * @author el-indio
 * Request are different from message. They only need to know the source/target nodes.
 */

public class Request extends Object implements Serializable {
	private byte[] target;
	private byte[] source;
	private boolean answer;
	private int chan;
	private PublicKey RSAPub;
	
	public Request(byte[] from,byte[] to,int id_chan,PublicKey pub){
		super();
		source=from;
		target=to;
		chan=id_chan;
		answer=true;
		RSAPub=pub;
	}
	
	public boolean getAnswer(){
		integrity();
		return answer;
	}
	
	public byte[] getSource(){
		integrity();
		return source;
	}
	public PublicKey getKey(){
		integrity();
		return RSAPub;
	}
	
	public int getIdChan(){
		integrity();
		return chan;
	}
	
	public byte[] getTarget(){
		integrity();
		return target;
	}
	
	private void writeObject(ObjectOutputStream stream) throws IOException {
		integrity();
		stream.writeObject(source);
		stream.writeObject(target);
		integrity();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		integrity();
		source=(byte[])stream.readObject();
		target = (byte[]) stream.readObject();
		integrity();
	}
	
	public void integrity(){
		assert(chan>=0);
		assert(source!=null);
		assert(target!=null);
	}
}
