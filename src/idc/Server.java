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

	public void run() {
		if(IDCManager.isServerUp()) return;
		try {

			ServerSocket server = new ServerSocket(Config.port);
			IDCManager.setServerAsUp(true);
			listeningOnServer = true;

			Socket client = null;
			while (listeningOnServer) {
				client = server.accept();

				// on traite la connexion dans un thread
				new ServerThread(client).start();

			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
}
