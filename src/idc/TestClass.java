package idc;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.internal.*;


public class TestClass {
	
	@Test
	public void TestChan(){
		Channel chan=new Channel("TEST CHANNEL");
		assertTrue(chan.getName()!="");
		
		Node node = new Node("TEST NODE");
		chan.addNode(node);
		assertTrue(chan.getNodeList().isEmpty());
		
		}
	
	@Test 
	public void TestMessage(){
		Node node=new Node("TEST NODE");
		Message msg=new Message("TEST NODE",node);
		assertTrue(msg.getData()!=null);
	}
	
	@Test
	public void TestNode(){
		Node node =new Node("TEST NODE");
		assertTrue(node.getId()!=null);
	}
	
	@Test 
	public void TestSend(){
		Node node=new Node("TEST NODE");
		Message msg=new Message("TEST NODE",node);
		assertTrue(msg.getData()!=null);
		IDCManager manager = new IDCManager();
		manager.addFriend(new FriendNode("el-indio","127.0.0.1"));
		System.out.println("Sending processing");
		manager.send(msg);
	}
}
