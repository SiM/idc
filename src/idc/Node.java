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
    
public class Node implements Com {
  private String nickname;
  private byte[] key;
  private byte[] id;
  
  public Node(String nickname) {
    
    if (nickname == null || nickname.length() <= 0) {
      nickname = "Anonymous";
    }
    this.nickname = new String(nickname);
    
    try{
      MessageDigest shasum=MessageDigest.getInstance("SHA1");
      id = shasum.digest((nickname+System.currentTimeMillis()).getBytes());
    }catch(NoSuchAlgorithmException err){
      System.out.println(err);
    }
    integrity();
    }



  public byte[] getId() {
    integrity();
    return id;
  }
    
  public void askForRequest(){
    integrity();
    /**
     *  1 -> we ask someone to engage a private conversation 
     *  
     */
    
    	
    integrity();
  }

    
  public void send(Message message) {
    assert (message != null);
    integrity();
    /* C'est le manager qui choisi le noeud ami (connexion directe) pour
     * envoyer le message. Ce dernier va ensuite être routé */
    //IDCManager.send(message);
  }


  public String getNickname() {
    integrity();
    return nickname;
  }




  public void send(Message message) {
    assert (message != null);
    integrity();
    /* C'est le manager qui choisi le noeud ami (connexion directe) pour
     * envoyer le message. Ce dernier va ensuite être routé */
    IDCManager.send(this, message);
  }
  
  protected void integrity() {
    assert (nickname != null);
    assert (id != null);
    assert (nickname.length() > 0);
  }


  @Override
  public boolean equals(Object aThat) {
    //check for self-comparison
    if (this == aThat) {
      return true;
    }
    
    //use instanceof instead of getClass here for two reasons
      //1. if need be, it can match any supertype, and not just one class;
      //2. it renders an explict check for "that == null" redundant, since
      //it does the check for null already - "null instanceof [type]" always
      //returns false. (See Effective Java by Joshua Bloch.)

      if (!(aThat instanceof Node)) {

         return false;
      }
      //Alternative to the above line :
      //if ( aThat == null || aThat.getClass() != this.getClass() ) return false;

      //cast to native object is now safe
      Node that = (Node) aThat;

      //now a proper field-by-field evaluation can be made
      return this.nickname.equals(that.nickname) && this.id.equals(that.id);

   }

}
