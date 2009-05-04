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

	static private HashMap<byte[],Node> nodes; // tous les noeuds connus du réseau
	static private HashMap<byte[],byte[]> WaitingStruct;
	static private List friends; // Connexions directes
	static private Vector<Channel> Channels;
	static private HashMap<Node,Queue<InetAddress>> gate;
	
	
	// Config.port

	static public Node myNode;

	IDCManager() {
		nodes = new HashMap<byte[], Node>(100,100);
		friends = new ArrayList();
		Channels=new Vector<Channel>(100,100);
		myNode = new Node(Config.nickname);
		WaitingStruct=new HashMap<byte[], byte[]>(100,100);
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
	
	
	/*
	 * fait du broadcast et retourne une liste de noeuds connectés et qui ne
	 * sont pas déjà dans la liste "friends". On peut ensuite faire des demandes
	 * de connexion directe à chacun de ces noeuds
	 */
	public List getLANNodes() {
		// TODO
		return new ArrayList();
	}

	static public HashMap<byte[], Node> getNodeStruct(){
		return nodes;
	}
	
	
	
	/* envoie un message au réseau */
	static public void send(Object message) {
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
				socket = new Socket(((FriendNode) friends.get(i)).getAddress(),
						Config.port);
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

	public void askForRequest(byte[]source ,byte[] target) {
	
		
		
		/**
		 * 1 -> we ask someone to engage a private conversation
		 * 
		 */
		if(!(nodes.containsKey(source)||nodes.containsKey(target))){
			System.out.println("Source or Target doesn't exist !");
			return;
		}
		
		Channel chan =new Channel("NEW CHANNEL");
		Channels.add(chan.getId(), chan);
		Request req=new Request(source,target,chan.getId(),null);
		sendRequest(req);
		/**
		 * here we need to know who we are waiting for. 
		 * We also need to know the id of the channel. 
		 */
		WaitingStruct.put(source, target);
		/**
		 * this could be dangerous if we have some people waiting for one.
		 */
		
	}

	
	static public void catchRequest(Request req){
		/**
		 * autor : a request has been catched by the ServerThread and it's the duty of this 
		 * method to deal with that.
		 */
		
		assert(req!=null);
		
		if(nodes.get(req.getTarget()).getId()!=Config.identity){
			System.out.println("Request not for me !");
		}
		
		if(WaitingStruct.containsKey(req.getSource())){
			/**
			 * we code the channel and send it.
			 */
			if(req.getAnswer()){
				/**
				 * on envoi sa clef publique
				 */
				
				CryptoManager.keyExchangeProcess(Channels.get(req.getIdChan()),req.getKey());
				send(new Agreement(CryptoManager.public_key,Channels.get(req.getIdChan())));
				
			}else{
				System.out.println("Request refused !");
				return;
			}
			
		}else{
			
			Request answer=new Request(req.getTarget(),req.getSource(),req.getIdChan(),null);
			sendRequest(req);
		}
	}
	
	static public void sendRequest(Request req){
		/**
		 *  autor : el-indio 
		 *  here we simply send a request, as same as we do for message.
		 */
		
		int i = 0;

		/* obtention de l'adresse par les friend node */
		while (i < friends.size()) {
			Socket socket = null;
			ObjectOutputStream out = null;
			ObjectInputStream in = null;

			try {
				socket = new Socket(((FriendNode) friends.get(i)).getAddress(),
						Config.port);
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());

			} catch (UnknownHostException e) {
				e.printStackTrace(System.err);
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}

			try {
				out.writeObject(req);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
			
			WaitingStruct.put(req.getSource(), req.getTarget());
			
			i++;

		}
		
	}
	
	public static void addNode(Node n) {
		if (!nodes.containsValue(n))
			nodes.put(n.getId(),n);
		System.out.println("Noded added");
	}

	public static void addChannel(Channel chan){
		if(!Channels.contains(chan)){
			Channels.add(chan.getId(),chan);
		}
	}
	
	public static void addLocalNode(FriendNode n) {
		if (!friends.contains(n)) {
			friends.add(n);
		}
	}

}
