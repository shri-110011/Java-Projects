package Networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Whois {

	public static void main(String[] args) throws UnknownHostException, IOException {
		int c;
		
		// Create a socket connected to internic.net, port 43.
		Socket s = new Socket("whois.internic.net", 43);
		
		// Obtain input and output streams.
		 InputStream in = s.getInputStream();
		 OutputStream out = s.getOutputStream();
		 
		 // Construct a query string.
		 
		 String str = (args.length ==0 ? "amazon.com":args[0]) + "\n";
		 // Conver to bytes.
		 byte buf[] = str.getBytes();
		 
		 // Send requests.
		 out.write(buf);
		 
		 // Read and display response.
		 while((c = in.read()) != -1) {
			 System.out.print((char)c);
		 }
		 s.close();
		 System.out.println("Socket closed!");
		 
	}

}
