package idc;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.IOException;
import java.net.*;

public class BroadcastServer extends Thread {

   protected DatagramSocket socket;
   protected boolean listening = true;

   public BroadcastServer() {
      if (!IDCManager.isBroadcastServerUp()) {

         try {
            socket = new DatagramSocket(Config.broadcastPort);
            IDCManager.setBroadcastServerAsUp(true);
         } catch (SocketException e) {
            e.printStackTrace(System.err);
         }
      } else {
         System.err.println("Th broadcast server is already up");
      }
   }

   public void run() {


      while (listening) {

         try {
            byte[] buf = new byte[256];

            // receive request
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            //
            socket.receive(packet);

            // figure out response

            buf = packet.getData();
            int len = packet.getLength();
            
            // 256 - 32 is really enough for a nickname
            if (len < 256) {
               //System.out.println("received size : " + len);
               byte[] received = new byte[len];

               for (int i = 0; i < len; i++) {
                  received[i] = buf[i];
               }

               // On copie les 32 derniers bytes dans id
               byte[] id = new byte[32];
               for (int i = 0; i < 32; i++) {
                  id[i] = received[len - 32 + i];
               }

               byte[] nick = new byte[len - 32];

               for (int i = 0; i < len - 32; i++) {
                  nick[i] = received[i];
               }

               //System.out.println("nickname : '" + new String(nick) + "'  ID : '" + HexBin.encode(id) + "'");

               IDCManager.addLocalNode(new FriendNode(new String(nick), id, packet.getAddress()));
               IDCManager.addNode(new String(id), new Node(new String(nick), id));
               IDCManager.enQueue(new Node(new String(nick), id), packet.getAddress());
            }
         } catch (Exception e) {
            e.printStackTrace(System.err);
            listening = false;
         }
      }
   }
}

