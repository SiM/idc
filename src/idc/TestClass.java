package idc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.*;
import org.junit.runner.*;
import org.junit.internal.*;

public class TestClass {

	@Test
	public void TestChan() {
		Channel chan = new Channel("TEST CHANNEL");
		assertTrue(chan.getName() != "");

		Node node = new Node("TEST NODE");
		chan.addNode(node);
		assertTrue(chan.getNodeList().isEmpty());

	}

	@Test
	public void TestMessage() {
		Node node = new Node("TEST NODE");
		Message msg = new Message("TEST NODE", node);
		assertTrue(msg.getData() != null);
	}


	@Test 
	public void TestChannel(){
		System.out.println("TESTING Channel ------>");
		Channel chan=new Channel("TEST CHANNEL");
		assertTrue(chan.getName()!=null);
		assertTrue(chan.getNodeList()!=null);
		Node node = new Node("TEST NODE");
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
		Node node = new Node("TEST NODE");
		assertTrue(node.getId() != null);
	}

	@Test
	public void TestCrypto() {
		System.out.println("TESTING CryptoManager ------>");
		CryptoManager crypto = new CryptoManager();
		Node node = new Node("TEST NODE");
		Message msg = new Message("TEST NODE", node);
		crypto.SignMessage(msg);
		System.out.println("CryptoManager TESTED SUCCESSFULLY !");
	}
	
	@Test
	public void TestConnection() {
		System.out.println("TESTING CONNECTION ---->");
		int i = 0;
		Node node = new Node("TEST NODE");
		Message msg = new Message("TEST MESSAGE", node);
		assertTrue(msg.getData() != null);
		assertTrue(msg.getMessage() != null);

		new Server().start();
		System.out.println("SERVER UP !");

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

		
		IDCManager manager =new IDCManager();
		
		while (i < 10) {
			
			manager.send(msg);
			i++;
		}

		System.out.println("CONNECTION TESTED SUCCESSFULLY");
	}

	@Test
	public void TestSend() {
		System.out.println("TESTING IDCManager ------>");
		Node node = new Node("TEST NODE");
		Message msg = new Message("TEST MESSAGE", node);
		assertTrue(msg.getData() != null);
		assertTrue(msg.getMessage() != null);
		IDCManager manager = new IDCManager();
		manager.addLocalNode(new FriendNode("el-indio", "localhost"));
		manager.send(msg);
		System.out.println("IDCManager TESTED SUCCESSFULLY !");
	}
}
