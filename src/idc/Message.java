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
    //private String str;
    public String data;
   
    public byte[] crypted;
    private boolean isciphered;
    private byte[] signature;

    public Message(String message, Node sender) {
        this.sender = sender;
        date = new Date();
        //str = new String(message);
        data=message;
        crypted = new byte[0];               
        signature=new byte[0];
        integrity();
    // ici on calcule signature avec sender+date+str pour éviter les redondances
    }
    public Message(Message msg){
    	sender=msg.sender;
    	data=msg.data;
    	crypted=msg.crypted;
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
    	return data.isEmpty();
    }
    
    public void setAsCiphered(boolean bool){
    	isciphered=bool;
    }
    
    public boolean authentification(){
    	return false;
    }
    
    public String getMessage() {
        return data;
    }
    
    public byte[] getByte(){
    	integrity();
    	return data.getBytes();
    }
    
    public void setData(byte[] arrayOfByte){
    	integrity();
    	
    	integrity();
    }
       
    private void writeObject(ObjectOutputStream stream) throws IOException {
        //stream.defaultWriteObject();
        stream.writeObject(size);
        stream.writeObject(date);
        //stream.writeObject(str);
        stream.writeObject(signature);
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        //stream.defaultReadObject();
        size = (Integer) stream.readObject();
        date = (Date) stream.readObject();
        //str = (String) stream.readObject();
        signature = (byte[]) stream.readObject();
    }
    
    private void integrity(){
    	assert(sender!=null);
    	assert(size>=0);
    	assert(signature!=null);
    	assert(this.isCiphered()?data.length()>0:data!=null);
    	//assert(this.isCiphered()?str.equalsIgnoreCase("destroyed"):str!=null);
    }
}

