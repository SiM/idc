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
	static private HashMap<Node,Queue<InetAddress>> gate;
	static private List friends; // Connexions directes

	static private Server server; // le serveur qui écoute sur le port

	// Config.port

	static public Node myNode;

	IDCManager() {
		nodes = new ArrayList();
		friends = new ArrayList();
		myNode = new Node(Config.nickname);
		gate =new HashMap<Node, Queue<InetAddress>>(100,100);
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

	
	static public void enQueue(Node node,InetAddress address){
		/**
		 * on enfile les InetAddress pour chaque noeud. La tête correspond a la meilleur gate.
		 * L'esprit du truc est le suivant, pour atteindre tel noeuds, je dois passer par telle gate.
		 * si celle-ci tombe on defile la tête et le suivant devient la meilleur gate.
		 */
		
		assert(node!=null);
		assert(address!=null);
		
		if(gate.containsKey(node)){
			if(gate.get(node).contains(address)){
				return;
			}else{
				gate.get(node).add(address);
			}
		}
		return;	
	}
	
	/* envoie un message au réseau */
	public void send(Message message) {
		/**
		 * on envoi pas le message en utilisant le broadcastServer à tout le
		 * monde on envoi sur le port server de l'application.
		 * 
		 */
		int i = 0;
		
		while (i < friends.size()) {
			System.out.println("Address of Friend node :"
					+ ((FriendNode) friends.get(i)).getAddress());
			i++;
		}

		i = 0;

		/* obtention de l'adresse par les friend node */
		while (i < friends.size()) {
			Socket socket = null;
			ObjectOutputStream out = null;
			ObjectInputStream in = null;

			try {
				socket = new Socket(((FriendNode)friends.get(i)).getAddress(), Config.port);
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
				
			} catch (UnknownHostException e) {
				e.printStackTrace(System.err);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}

			try {
				out.writeObject(message);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			i++;

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

	public static void addLocalNode(FriendNode n) {
		if (!friends.contains(n)) {
			friends.add(n);
		}
	}
}
