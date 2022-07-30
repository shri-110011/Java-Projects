package Networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class UCDemo {

	public static void main(String[] args) throws IOException {
		int c;
		URL hp = new URL("https://my-chatbot-app.herokuapp.com");
		URLConnection hpCon = hp.openConnection();
		
		// Get date.
		long d = hpCon.getDate();
		if(d != 0) {
			System.out.println("Date: "+new Date(d));
		}
		else {
			System.out.println("No date information.");
		}
			
		// Get content type.
		System.out.println("Content-Type: "+hpCon.getContentType());
		
		// Get expiration date.
		d = hpCon.getExpiration();
		if(d != 0) {
			System.out.println("Expires: "+new Date(d));
		}
		else {
			System.out.println("No expiration information.");
		}
		
		// Get last modified date.
		d = hpCon.getLastModified();
		if(d != 0) {
			System.out.println("Last-Modified: "+new Date(d));
		}
		else {
			System.out.println("No last-modified information.");
		}
		
		// Get cntent length.
		long len = hpCon.getContentLengthLong();
		if(len != -1) {
			System.out.println("Content-length: "+len);
		}
		else {
			System.out.println("Content length unavailable.");
		}
		
		if(len != 0) {
			System.out.println("===Content===");
			InputStream in = hpCon.getInputStream();
			while((c = in.read()) != -1) {
				System.out.print((char)c);
			}
			in.close();
		}
		else {
			System.out.println("No content available.");
		}
		
	}

}
