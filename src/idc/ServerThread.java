/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import java.net.*;
import java.io.*;
import static org.junit.Assert.*;

/**
 * 
 * @author fridim
 */
public class ServerThread extends Thread {

	private Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
		integrity();
	}

	public void run() {
		integrity();

		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		

		try {

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			Object message=new Object();
			message = in.readObject();

			if (message.getClass().toString().equals("class idc.Message")) {

				// Ici on fait quelque chose avec le message

				// par exemple on l'affiche :
				System.out.println("PASSAGE DANS LE TEST");
				System.out.println(((Message) message).getMessage());
			}

			out.close();
			in.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		integrity();
	}

	public void integrity() {
		assertTrue(socket != null);
	}
}
