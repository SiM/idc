package idc;

import java.io.IOException;
import java.net.*;

public class BroadcastServer extends Thread {

   protected DatagramSocket socket;

   protected boolean listening = true;

   BroadcastServer() {
      try {
         socket = new DatagramSocket(Config.broadcastPort);

      } catch (SocketException e) {
         e.printStackTrace(System.err);
      }
   }

   
   public void run() {
      while (listening) {
    	  
         try {
            byte[] buf = new byte[256];
            
            // receive request
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            
            // figure out response
            
            buf = packet.getData();
            
            
            String dString = new String(buf);
       
            
            String[] d = dString.split(" ");
       
            IDCManager.addLocalNode(new FriendNode(d[0],packet.getAddress().toString()));
            IDCManager.enQueue(new Node(d[0]),packet.getAddress());
            
         } catch (Exception e) {
            e.printStackTrace(System.err);
            listening = false;
         }
      }
   }
}
