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
		
		Node node = new Node("TEST NODE","1");
		chan.addNode(node);
		assertTrue(chan.getNodeList().isEmpty());
		
		}
	
	@Test 
	public void TestMessage(){
		Node node=new Node("TEST NODE","1");
		Message msg=new Message("TEST NODE",node);
		assertTrue(msg.getData()!=null);
	}
	
	@Test
	public void TestNode(){
		Node node =new Node("TEST NODE","2");
		assertTrue(node.getId()!="");
		assertTrue(node.getId()!=null);
	}
}
