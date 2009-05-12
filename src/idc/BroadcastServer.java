package idc;

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
				
				IDCManager.addLocalNode(new FriendNode(d[0], d[1].getBytes(), packet.getAddress()));
				IDCManager.addNode(new Node(d[0],d[1].getBytes()));
				
				IDCManager.enQueue(new Node(d[0], d[1].getBytes()),packet.getAddress());

			} catch (Exception e) {
				e.printStackTrace(System.err);
				listening = false;
			}
		}
	}
}
