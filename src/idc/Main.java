/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idc;

import java.security.*;

/**
 *
 * @author fridim
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	CryptoManager CM=new CryptoManager();
    	
        Node my_node=new Node("el-indio","id");
        Message msg=new Message("TEST MESSAGE UN PEU PLUS LONG POUR VOIR SI TOUT MARCHE BIEN",my_node);
     
        System.out.println("Node "+my_node.getNickname()+" created with the id : "+my_node.getId());
        Channel chan=new Channel("TEST CHANNEL");
        CM.CreateSecretKey();
        CM.code(chan.getId(),msg);
        System.out.println("Encoded Message :"+msg.getMessage());
        System.out.println("The message is encoded :"+msg.isCiphered());
        CM.decode(chan.getId(),msg);
        System.out.println("Decoded Message :"+msg.getMessage());
        
    }
    IDCManager manager = new IDCManager();
}