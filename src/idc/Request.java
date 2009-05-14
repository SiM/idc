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
	private boolean isAnAnswer;
	private boolean answer;
	private byte[] chan;
	private PublicKey RSAPub;
	
	public Request(byte[] from,byte[] to, byte[] id_chan,PublicKey pub){
		super();
		source=from;
		target=to;
		chan=id_chan;
		answer=false;
		isAnAnswer=false;
		RSAPub=pub;
		integrity();
	}
	
	public void setAsAnswer(boolean bool){
		integrity();
		isAnAnswer=bool;
	}
	
	public boolean isAnAnswer(){
		integrity();
		return isAnAnswer;
	}
	
	public boolean getAnswer(){
		integrity();
		return answer;
	}
	public void setAnswer(boolean bool){
		integrity();
		answer=bool;
		integrity();
	}
	public byte[] getSource(){
		integrity();
		return source;
	}
	public PublicKey getKey(){
		integrity();
		return RSAPub;
	}
	
	public byte[] getIdChan(){
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
		stream.writeBoolean(isAnAnswer);
		stream.writeBoolean(answer);
		stream.writeObject(RSAPub);
		stream.writeObject(chan);
		
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		source=(byte[])stream.readObject();
		target = (byte[]) stream.readObject();
		isAnAnswer=stream.readBoolean();
		answer=stream.readBoolean();
		RSAPub=(PublicKey)stream.readObject();
		chan=(byte[]) stream.readObject();
		
		integrity();
	}
	
	public void integrity(){
		assert(chan.length>=0);
		assert(source!=null);
		assert(target!=null);
		assert(RSAPub!=null);
	}
}
