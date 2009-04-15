package idc;

import java.io.IOException;
import java.net.*;
import java.util.Date;

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
            //System.out.println("Server received : " + dString);

            /* send the response to the client at "address"
             * and "port"
             * echo server */
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            buf = "I'am the server".getBytes();
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
         } catch (Exception e) {
            e.printStackTrace(System.err);
            listening = false;
         }
      }
   }
}
