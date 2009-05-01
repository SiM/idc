package idc;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class BroadcastServer extends Thread {

   protected DatagramSocket socket;
   protected boolean listening = false;

   public BroadcastServer() {
	   
      try {
         socket = new DatagramSocket(Config.broadcastPort);
         listening=true;
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
            System.out.println("Broadcast received : " + dString);
            
            String[] d = dString.split(" ");
       
            IDCManager.addNode(new Node(d[0]));
         } catch (Exception e) {
            e.printStackTrace(System.err);
            listening = false;
         }
      }
   }
}
