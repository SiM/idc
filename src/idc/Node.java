/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import java.util.*;
import java.security.*;
import java.security.spec.*;
import java.math.*;

/**
 *
 * @author fridim
 * 
 * Cette classe identifie un noeud du réseau. Selon le cahier des charges, 
 * un noeud est identifié par un nickname et par un id.
 */
public class Node implements Com{

    private String nickname;
    private String id;
    private byte[] key;

    public Node(String nickname, String id) {

        if (nickname == null || nickname.length() <= 0) {
            nickname = "Anonymous";
        }

        this.id = new String(id);
        this.nickname = new String(nickname);
        integrity();
    }

    public String getNickname() {
        integrity();
        return nickname;
    }

    public String getId() {
        integrity();
        return id;
    }

    // vérifie que la signature de la clef publique correspond bien à l'id
    public boolean checkKey() {
        // TODO
        return true;
    }

    public void send(Message message) {
        assert (message != null);
        integrity();
        /* C'est le manager qui choisi le noeud ami (connexion directe) pour
         * envoyer le message. Ce dernier va ensuite être routé */
        //IDCManager.send(message);
    }
    
    public void send(int id_chan,Message message) {
        assert (message != null);
        integrity();
        
        
        
        /* C'est le manager qui choisi le noeud ami (connexion directe) pour
         * envoyer le message. Ce dernier va ensuite être routé 
         * */
        //IDCManager.send(id_chan,message);
    }
    
    public void sendCiphered(Message msg,int id_chan){
    	integrity();
    	msg.setAsCiphered(true);
    	CryptoManager.codeAndSend(msg,id_chan);
    	integrity();
    }

    protected void integrity() {
        assert (nickname != null);
        assert (id != null);
        assert (nickname.length() > 0);
    }
}
