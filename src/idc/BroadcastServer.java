package idc;

import java.io.IOException;
import java.net.*;

public class BroadcastServer extends Thread {

	protected DatagramSocket socket;

	protected boolean listening = true;

	BroadcastServer() {
		if(IDCManager.isBroadcastServerUp()){
			IDCManager.setBroadcastServerAsUp(true);
			try {
				socket = new DatagramSocket(Config.broadcastPort);
	
			} catch (SocketException e) {
				e.printStackTrace(System.err);
			}
		}
	}

	public void run() {
		socket = null;
		try {
			socket = new DatagramSocket(Config.broadcastPort);
		} catch (SocketException e1) {
			// TODO Bloc catch auto-généré
			e1.printStackTrace();
		}
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

				IDCManager.addLocalNode(new FriendNode(d[0], packet.getAddress().toString()));
	
				System.out.println("Address of the packet :"+packet.getAddress());
				IDCManager.enQueue(new Node(d[0]),packet.getAddress());

			} catch (Exception e) {
				e.printStackTrace(System.err);
				listening = false;
			}
		}
	}
}
