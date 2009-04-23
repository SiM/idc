package idc;

import java.util.Date;
import java.io.*;
import java.nio.charset.*;
import java.lang.reflect.Array;

/**
 *
 * @author fridim
 */
public class Message implements Serializable, Cipherable {

    private Node sender;
    private Integer size;
    private Date date;
    private byte[] data;
    private boolean isciphered;
    private byte[] signature;

    public Message(String message, Node sender) {
        this.sender = sender;
        date = new Date();
        data=message.getBytes();          
        signature=new byte[0];
        integrity();
    // ici on calcule signature avec sender+date+str pour éviter les redondances
    }
    
    public Message(Message msg){
    	sender=msg.sender;
    	data=msg.data;
    	date=new Date();
    	signature=new byte[0];
    	integrity();
    }
    public boolean isCiphered(){
    	integrity();
    	return isciphered;	
    }
    
    public boolean isByteEmpty(){
    	integrity();
    	if(data.length==0){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public void setAsCiphered(boolean bool){
    	integrity();
    	isciphered=bool;
    }
    
    public boolean authentification(){
    	integrity();
    	return false;
    }
    
    public String getMessage() {
    	integrity();
        return new String(data);
    }
    
    public byte[] getData(){
    	integrity();
    	return data;
    }

    public void setData(byte[] ocTab){
    	integrity();
    	data=ocTab;
    	integrity();
    }
   
       
    private void writeObject(ObjectOutputStream stream) throws IOException {
        integrity();
    	//stream.defaultWriteObject();
        stream.writeObject(size);
        stream.writeObject(date);
        //stream.writeObject(str);
        stream.writeObject(signature);
        integrity();
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        integrity();
    	//stream.defaultReadObject();
        size = (Integer) stream.readObject();
        date = (Date) stream.readObject();
        //str = (String) stream.readObject();
        signature = (byte[]) stream.readObject();
        integrity();
    }
    
    private void integrity(){
    	assert(sender!=null);
    	assert(size>=0);
    	assert(signature!=null);
    	assert(data!=null);    
    }
}

