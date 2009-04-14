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
 * @author fridim
 */
public class IDCManager implements Com{
    static private List nodes; // tous les noeuds connus du réseau
    static private List friends; // Connexions directes
    static private Server server; // le serveur qui écoute sur le port Config.port

    IDCManager() {
        nodes = new ArrayList();
        friends = new ArrayList();
        
        new Server().start();
        
        /* Test */
        /*
        FriendNode local = new FriendNode("fridim", "blabla", "localhost");
        local.send(new Message("coucou", local));
         */
    }
        
    /* fait du broadcast et retourne une liste de noeuds connectés et qui ne
     * sont pas déjà dans la liste "friends". On peut ensuite faire des demandes
     * de connexion directe à chacun de ces noeuds */
    public List getLANNodes() {
       // TODO
       return new ArrayList();
    }
    
    /* envoie un message au réseau */
     public void send(Message message) {
        // TODO
    }
     
    
    
    /* envoie un message à un noeud du réseau */
    static public void send(Node n, Message message) {
        // TODO

    }
    public void integrity(){
    	assert(nodes!=null);
    	assert(friends!=null);
    }
}
