/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

/**
 *
@author fridim
 */

public class IDCManager {

   static private List nodes; // tous les noeuds connus du réseau
   static private List localNodes; // noeuds découverts par broadcast
   static private List friends; // Connexions directes
   static private Server server; // le serveur qui écoute sur le port Config.port
   static public Node myNode;

   IDCManager() {
      nodes = new ArrayList();
      localNodes = new ArrayList();
      friends = new ArrayList();
      myNode = new Node(Config.nickname, CryptoManager.getId());

      new Server().start();
      new BroadcastServer().start();
      new BroadcastClient().start();

   /* Test */
   /*
   FriendNode local = new FriendNode("fridim", "blabla", "localhost");
   local.send(new Message("coucou", local));
    */
   }

   /* envoie un message au réseau */
   public void send(Message message) {
   // TODO
   }

   /* envoie un message à un noeud du réseau */
   static public void send(Node n, Message message) {
   // TODO

   }

   public static void addLocalNode(Node n) {
      if (!localNodes.contains(n)) {
         localNodes.add(n);
      }
   }

   public static void addNode(Node n) {
      if (!nodes.contains(n)) {
         nodes.add(n);
      }
   }
}
