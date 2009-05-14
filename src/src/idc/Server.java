package idc;

import java.net.*;
import java.io.*;

/**
 * 
 * @author fridim
 */
public class Server extends Thread {

	private ServerSocket serverSocket;

	static private boolean listeningOnServer = false;

	public Server() {
		if(!IDCManager.isServerUp()){
			
			try {
				 serverSocket = new ServerSocket(Config.port);
				 IDCManager.setServerAsUp(true);
			} catch (SocketException e) {
				e.printStackTrace(System.err);
			}catch(IOException err){
				System.out.println(err);
			}
		}else{
			System.err.println("The server is already up");
		}
	}
	
	public void run() {
		//if(IDCManager.isServerUp()) return;
		try {

			
			listeningOnServer = true;

			Socket client = null;
			while (listeningOnServer) {
				client = serverSocket.accept();

				// on traite la connexion dans un thread
				new ServerThread(client).start();

			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
			listeningOnServer=false;
		}
	}
}
