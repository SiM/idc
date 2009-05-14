package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import idc.*;
import org.junit.*;
import org.junit.runner.*;

public class TestClass {

	private IDCManager manager;

	
	@Test 
	public void TestMain(){
		manager=new IDCManager();
		
		System.out.println("SERVER UP !");
	}
	
	@Test
	public void TestChan() {
		Channel chan = new Channel("TEST CHANNEL");
		assertTrue(chan.getName() != "");

		Node node = new Node("TEST NODE", "ID_DE_TEST_NODE".getBytes());
		chan.addNode(node);
		assertTrue(chan.getNodeList().isEmpty());

	}

	@Test
	public void TestMessage() {
		Node node = new Node("TEST NODE", "ID_DE_TEST_NODE".getBytes());
		Message msg = new Message("TEST NODE", node);
		assertTrue(msg.getData() != null);
	}


	@Test 
	public void TestChannel(){
		System.out.println("TESTING Channel ------>");
		Channel chan=new Channel("TEST CHANNEL");
		assertTrue(chan.getName()!=null);
		assertTrue(chan.getNodeList()!=null);
		Node node = new Node("TEST NODE", "ID_DE_TEST_NODE".getBytes());
		Message msg=new Message("TEST MESSAGE",node);
		int i=0;
		
		while(i<100){
			chan.CreateSecretKey();
			chan.cipher(msg);
			chan.decipher(msg);
			i++;
		}
		
		System.out.println("CHANNEL TESTED SUCCESSFULLY");
	}
	
	
	
	@Test
	public void TestNode() {
		Node node = new Node("TEST NODE", "ID_DE_TEST_NODE".getBytes());
		assertTrue(node.getId() != null);
	}

	@Test
	public void TestCrypto() {
		System.out.println("TESTING CryptoManager ------>");
		CryptoManager crypto = new CryptoManager();
		Node node = new Node("TEST NODE", "ID_DE_TEST_NODE".getBytes());
		Message msg = new Message("TEST NODE", node);
		crypto.SignMessage(msg);
		System.out.println("CryptoManager TESTED SUCCESSFULLY !");
	}
	
	@Test
	public void TestConnection() {
		System.out.println("TESTING CONNECTION ---->");
		int i = 0;
		Node node = new Node("TEST NODE", "ID_DE_TEST_NODE".getBytes());
		Message msg = new Message("TEST MESSAGE", node);
		assertTrue(msg.getData() != null);
		assertTrue(msg.getMessage() != null);

		ObjectOutputStream out = null;

		try {
			Socket socket = new Socket("localhost", Config.port);
			out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket
					.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		
		while (i < 10) {
			
			manager.send(msg);
			i++;
		}

		System.out.println("CONNECTION TESTED SUCCESSFULLY");
	}

	@Test
	public void TestSend() {
		System.out.println("TESTING IDCManager ------>");
		Node node = new Node("TEST NODE", "ID_DE_TEST_NODE".getBytes());
		Message msg = new Message("TEST MESSAGE", node);
		assertTrue(msg.getData() != null);
		assertTrue(msg.getMessage() != null);
		
		//manager.addLocalNode(new FriendNode("stickman", "localhost"));
		manager.send(msg);
		System.out.println("IDCManager TESTED SUCCESSFULLY !");
	}
}
