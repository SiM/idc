/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import java.net.*;
import java.io.*;

/**
 *
 * @author fridim
 */
public class ServerThread extends Thread {

    private Socket socket = null;
    
    
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    
    public void run() {
        try {
        	
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Object message = in.readObject();
            
            if (message.getClass().toString().equals("class idc.Message")) {
            	
            	// Ici on fait quelque chose avec le message
            	
            	//par exemple on l'affiche :
            	System.out.println(message);
            }

            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public void integrity(){
    	assert(socket!=null);
    }
}
