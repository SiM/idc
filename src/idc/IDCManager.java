/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;


import java.util.*;
import java.net.InetAddress;

import ihm.*;

/**
 * 
 * @author fridim
 */
public class IDCManager {


	static private Hashtable<String, Node> nodes; // tous les noeuds connus du

	// réseau

	static private Hashtable<String, String> WaitingStruct;

	static public List friends; // Connexions directes

	static public List channels;

	static private Hashtable<Node, Queue<InetAddress>> gate;

	static private boolean server = false;

	static private boolean broadcastserver = false;

	// Config.port
	static public Node myNode;


	public IDCManager() {
		nodes = new Hashtable<String, Node>(100, 100);
		friends = new ArrayList<FriendNode>();
		channels = new ArrayList<Channel>();
		myNode = new Node(Config.nickname, CryptoManager.getId());
		System.out.println("MON ID : " + HexBin.encode(myNode.getId()));

		WaitingStruct = new Hashtable<String, String>(100, 100);
		gate = new Hashtable<Node, Queue<InetAddress>>(100, 100);
		Server server = new Server();
		server.start();

		BroadcastServer broadcaster = new BroadcastServer();
		broadcaster.start();

		new BroadcastClient().start();

	}


	static public void enQueue(Node node, InetAddress address) {
		/**
		 * on enfile les InetAddress pour chaque noeud. La tête correspond a la
		 * meilleur gate. L'esprit du truc est le suivant, pour atteindre tel
		 * noeuds, je dois passer par telle gate. si celle-ci tombe on defile la
		 * tête et le suivant devient la meilleur gate.
		 */
		assert (node != null);
		assert (address != null);

		if (gate.containsKey(node)) {
			if (gate.get(node).contains(address)) {
				return;
			} else {
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
	static public List getLANNodes() {
		// TODO
		return friends;
	}

	static public Hashtable<String, Node> getNodeStruct() {
		return nodes;
	}

	static public void setBroadcastServerAsUp(boolean bool) {
		broadcastserver = bool;
	}

	static public boolean isBroadcastServerUp() {
		return broadcastserver;

	}

	static public boolean isServerUp() {
		return server;

	}

	static public void setServerAsUp(boolean bool) {
		server = bool;
	}

	

	/* envoie un message à un noeud du réseau */
	static public void send(Node n, Message message) {
		// TODO

	}

	static public void askForRequest(byte[] source, byte[] target, Channel chan) {

		/**
		 * 1 -> we ask someone to engage a private conversation
		 * 
		 */
		/*
		 * if (!(nodes.containsKey(source) || nodes.containsKey(target))) {
		 * System.out.println("Source or Target doesn't exist !"); return; }
		 */
		addChannel(chan);
		
		Request req = new Request(source, target, chan.getId(),CryptoManager.public_key);
		req.setAsAnswer(false);
		WaitingStruct.put(new String(source), new String(target));
		send(req);
		/**
		 * here we need to know who we are waiting for. We also need to know the
		 * id of the channel.
		 */

		/**
		 * this could be dangerous if we have some people waiting for one.
		 */
	}

	static public void catchRequest(Request req) {
		/**
		 * autor : a request has been catched by the ServerThread and it's the
		 * duty of this method to deal with that.
		 */

		
		assert (req != null);

		String me = new String(myNode.getId());
		String targ = new String(req.getTarget());

		if (!me.contentEquals(new StringBuffer(targ))) {
			System.out.println("Request not for me !");
			return;
		}

		if (req.isAnAnswer()) {

			if (WaitingStruct.containsKey(new String(req.getSource()))) {

				WaitingStruct.remove(req.getSource());

				if (req.getAnswer()) {
                                        //int i = channels.indexOf(req.getIdChan());
                                        //Channel c = (Channel) channels.get(i);
					System.out.println("THE ANSWER IS YES!");

					Channel c =new Channel("private channel between "+new String(req.getSource())+" and "+new String(req.getTarget()));
					CryptoManager.keyExchangeProcess(c, req.getKey());
					send(new Agreement(CryptoManager.public_key, c));

				} else {
					System.out.println("Request refused !");
					return;
				}

			}
		} else {
			Request answer = new Request(myNode.getId(), req.getSource(), req
					.getIdChan(), CryptoManager.public_key);

			assert(nodes!=null);
			
			
			
			
			accept acceptation = new accept("",answer);

			// idée 1 -> boucle while
			
			
			
			//System.out.println("name of the requester :"+nodes.get("{"+new String( req.getSource())).getNickname());
			answer.setAsAnswer(true);
			acceptation.setNom(nodes.get( new String(myNode.getId()) ).getNickname());
			acceptation.setVisible(true);
			while(accept.click == -1){};
			send(answer);
		}
	}
 /* envoie un message au réseau */
   static public void send(Object message) {
      /**
       * on envoi pas le message en utilisant le broadcastServer à tout le
       * monde on envoi sur le port server de l'application.
       * 
       */
      if (friends.isEmpty()) {
         System.out.println("YOU HAVE NO FRIENDS !");
         return;
      }

      ListIterator<FriendNode> iter = friends.listIterator();
      /* obtention de l'adresse par les friend node */
      while (iter.hasNext()) {
         Socket socket = null;
         ObjectOutputStream out = null;
         ObjectInputStream in = null;
         FriendNode friend = iter.next();
         friend.send(message);

         if (message.getClass().toString().equals("class idc.Request") && !((Request) message).isAnAnswer()) {
            WaitingStruct.put(new String(((Request) message).getSource()), new String(((Request) message).getTarget()));
         }
      }
   }

	public static void addNode(String str,Node n) {
		if (! nodes.containsKey(str)) {
			
			nodes.put(str, n);
			
			System.out.println("Noded added");
		
		}

	}
      
   public static void addChannel(Channel c) {
      if (!channels.contains(c)) {
         channels.add(c);
      }
   }
  


  public static void addLocalNode(FriendNode n) {
    if (!friends.contains(n)) {
      String id = HexBin.encode(n.getId());
      System.out.println("Ajout friendnode : " + n.getNickname() + " ID : " + id + " (" + n.getAddress() + ")");
      
      friends.add(n);
      /** on ajoute une info au chat**/
        String dial = new String(ServerThread.getHeure() +" ### "+ n.getNickname() + " a rejoint le salon ###  \n");
        Accueil.jtrep.get(0).append(dial);
        Accueil.jTextArea1.setText(Accueil.jtrep.get(0).getText());
      /** fin **/
      
    }
  }

}
