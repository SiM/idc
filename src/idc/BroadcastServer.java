package idc;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.IOException;
import java.net.*;

public class BroadcastServer extends Thread {

	protected DatagramSocket socket;

	protected boolean listening = true;

	public BroadcastServer() {
		if(!IDCManager.isBroadcastServerUp()){
			
			try {
				socket = new DatagramSocket(Config.broadcastPort);
				IDCManager.setBroadcastServerAsUp(true);
			} catch (SocketException e) {
				e.printStackTrace(System.err);
			}
		}else{
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
				
				String dString = new String(buf);
				//System.out.println("Broadcast received : " + dString);
				
				String[] d = dString.split(" ");
				//System.out.println("nickname : " + d[0] + " ID : " + d[1].substring(0, 64));
                                
                                byte[] id;
                                id = HexBin.decode(d[1].substring(0, 64));
                                //System.out.println("ID recu : " + new String(id));
                                
				IDCManager.addLocalNode(new FriendNode(d[0], id, packet.getAddress()));
				IDCManager.addNode(new String(id),new Node(d[0],id));
				
				IDCManager.enQueue(new Node(d[0], id),packet.getAddress());

			} catch (Exception e) {
				e.printStackTrace(System.err);
				listening = false;
			}
		}
	}
}

