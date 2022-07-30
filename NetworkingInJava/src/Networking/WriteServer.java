package Networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class WriteServer {
	public static int serverPort = 998;
	public static int clientPort = 999;
	public static int bufferSize = 1024;
	public static DatagramSocket ds;
	public static byte buf[] = new byte[bufferSize];
	
	public static void TheServer() throws IOException {
		int pos=0;
		System.out.print("Server: ");
		while(true) {
			int c = System.in.read();
			switch(c) {
				case -1:
					System.out.println("Server quits!");
					ds.close();
					return;
				case '\r':
					break;
				case '\n':
					ds.send(new DatagramPacket(buf, pos, 
							InetAddress.getLocalHost(), clientPort));
					System.out.print("Server: ");
					pos = 0;
					break;
				default:
					buf[pos++] = (byte)c;
			}
		}
			
	}
	
	public static void TheClient() throws IOException {
		while(true) {
			System.out.print("Client: ");
			DatagramPacket p = new DatagramPacket(buf, buf.length);
			ds.receive(p);
			System.out.println("Packet length: "+p.getLength());
			System.out.println(new String(p.getData(), 0, p.getLength()));
		}
	}

	public static void main(String[] args) throws IOException {
		if(args.length == 1) {
			ds = new DatagramSocket(serverPort);
			TheServer();
		}
		else {
			ds = new DatagramSocket(clientPort);
			TheClient();
		}
		

	}

}
