package idc;

import java.util.Date;
import java.io.*;

/**
 *
 * @author fridim
 */
public class Message implements Serializable, Cipherable {

    private Node sender;
    private Integer size;
    private Date date;
    private String str;
    private boolean isciphered;
    private byte[] signature;

    public Message(String message, Node sender) {
        this.sender = sender;
        date = new Date();
        str = new String(message);
        integrity();
    // ici on calcule signature avec sender+date+str pour éviter les redondances
    }

    public boolean isCiphered(){
    	integrity();
    	return isciphered;	
    }
    
    public void setAsCiphered(boolean bool){
    	isciphered=bool;
    }
    
    public boolean authentification(){
    	return false;
    }
    
    public String getMessage() {
        return new String(str);
    }
    
    
    
    private void writeObject(ObjectOutputStream stream) throws IOException {
        //stream.defaultWriteObject();
        stream.writeObject(size);
        stream.writeObject(date);
        stream.writeObject(str);
        stream.writeObject(signature);
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        //stream.defaultReadObject();
        size = (Integer) stream.readObject();
        date = (Date) stream.readObject();
        str = (String) stream.readObject();
        signature = (byte[]) stream.readObject();
    }
    
    private void integrity(){
    	assert(sender!=null);
    	assert(size>=0);
    	assert(str!=null);
    }
}

