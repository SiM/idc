/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import java.util.*;
import idc.Server;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author fridim
 */
public class IDCManager {

	static private List nodes; // tous les noeuds connus du réseau

	static private Vector<FriendNode> friends; // Connexions directes

	static private Server server; // le serveur qui écoute sur le port

	// Config.port

	static public Node myNode;

	IDCManager() {
		nodes = new ArrayList();
		friends = new Vector<FriendNode>(100, 100);
		myNode = new Node(Config.nickname);

		new Server().start();
		new BroadcastServer().start();
		new BroadcastClient().start();

		/* Test */
		/*
		 * FriendNode local = new FriendNode("fridim", "blabla", "localhost");
		 * local.send(new Message("coucou", local));
		 */
	}

	/*
	 * fait du broadcast et retourne une liste de noeuds connectés et qui ne
	 * sont pas déjà dans la liste "friends". On peut ensuite faire des demandes
	 * de connexion directe à chacun de ces noeuds
	 */
	public List getLANNodes() {
		// TODO
		return new ArrayList();
	}

	public void addFriend(FriendNode friend) {
		friends.add(friend);
	}

	/* envoie un message au réseau */
	public void send(Message message) {
		/**
		 * on envoi pas le message en utilisant le broadcastServer à tout le
		 * monde on envoi sur le port server de l'application.
		 * 
		 */
		int i;

		byte[] buf = new byte[256];
		for (i = 0; i < friends.size(); i++) {
			System.out.println("Address of Friend node :"
					+ friends.get(i).getAddress());
		}
		/* obtention de l'adresse par les friend node */
		for (i = 0; i < friends.size(); i++) {
			Socket socket = null;
			ObjectOutputStream out = null;
			ObjectInputStream in = null;

			try {
				socket = new Socket(friends.get(i).getAddress(), Config.port);
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
				out.flush();
			} catch (UnknownHostException e) {
				e.printStackTrace(System.err);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}

			try {
				out.writeObject(message);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}

			System.out.println("Message sent");
		}
	}

	/* envoie un message à un noeud du réseau */
	static public void send(Node n, Message message) {
		// TODO

	}

	public static void addNode(Node n) {
		if (!nodes.contains(n))
			nodes.add(n);
		System.out.println("Noded added");
	}

}
