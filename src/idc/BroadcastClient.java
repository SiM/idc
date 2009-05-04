/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author fridim
 */
public class BroadcastClient extends Thread {
	private long five = Config.broadcastSleep;

	public void setPacket(Node node) {

	}

	public void run() {
		while (true) {
			try {

				// get a datagram socket
				DatagramSocket sock = new DatagramSocket();

				// send request
				byte[] buf = new byte[256];

				buf = (Config.nickname + " " + new String(Config.identity))
						.getBytes();

				InetAddress address = InetAddress.getByName("255.255.255.255");

				DatagramPacket packet = new DatagramPacket(buf, buf.length,
						address, Config.broadcastPort);
				sock.send(packet);
				sock.close();
				sleep(Config.broadcastSleep);
			} catch (InterruptedException ex) {
				Logger.getLogger(BroadcastClient.class.getName()).log(
						Level.SEVERE, null, ex);
				break;
			} catch (IOException e) {
				e.printStackTrace(System.err);
				break;
			}

		}
	}

}
