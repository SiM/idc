package idc;


import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestClass {

   @Test
   public void TestChan() {
      Channel chan = new Channel("TEST CHANNEL");
      assertFalse(chan.getName().equals(""));

      Node node = new Node("TEST NODE", "1");
      chan.addNode(node);
      assertTrue(chan.getNodeList().isEmpty());

   }

   @Test
   public void TestMessage() {
      Node node = new Node("TEST NODE", "1");
      Message msg = new Message("TEST NODE", node);
      assertTrue(msg.getData() != null);
   }

   @Test
   public void TestNode() {
      Node node = new Node("TEST NODE", "2");
      assertTrue(node.getId() != "");
      assertTrue(node.getId() != null);
   }
   
   @Test
   public void TestChannel() {
      Channel c = new Channel("badiss ding dong");
      Message msg = new Message("test chiffrement AES", new Node("fridim", "ID:blabla"));
      System.out.println(new String(msg.getDigest()));
      Message original = new Message(msg.getMessage(), new Node("fridim", "ID:blabla"));
      
      c.cipher(msg);
      c.decipher(msg);
      assertTrue(original.getMessage().equals(msg.getMessage()));
   }
   
}
