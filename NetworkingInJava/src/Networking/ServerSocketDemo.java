package Networking;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {

	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(4300);
		Socket client  = ss.accept();
		
		System.out.println("Client connected.");
		
		OutputStream out = client.getOutputStream();
		String msg = "<h1>Hello</h1>";
		byte buf[] = msg.getBytes();
		out.write(buf);
		
	}

}
