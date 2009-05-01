/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idc;

import java.security.*;
import java.io.IOException;
import java.net.*;

/**
 * 
 * @author fridim
 */

public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		CryptoManager CM = new CryptoManager();

		Node my_node = new Node("el-indio");
		
				
		System.out.println("Node " + my_node.getNickname()
				+ " created with the id : " + new String(my_node.getId()));

		// TEST idc manager
		IDCManager manager = new IDCManager();
		manager.addFriend(new FriendNode("el-indio","127.0.0.1"));
		Message msg = new Message(
				"TEST MESSAGE UN PEU PLUS LONG POUR VOIR SI TOUT MARCHE BIEN",
				my_node);
		System.out.println("Sending processing");
		
		manager.send(msg);
		
		
	}
}
