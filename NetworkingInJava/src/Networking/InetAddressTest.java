package Networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress Address = InetAddress.getLocalHost();
		System.out.println(Address);
		
		Address = InetAddress.getByName("my-chatbot-app.herokuapp.com");
		System.out.println(Address.getHostName());
		
		InetAddress SW[] = InetAddress.getAllByName("www.amazon.com");
		for(int i=0; i<SW.length; i++)
			System.out.println(SW[i]);
		
		System.out.println(InetAddress.getByName("192.168.1.7").getHostName());

	}

}
