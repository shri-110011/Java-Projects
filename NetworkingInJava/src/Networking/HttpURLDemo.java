package Networking;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpURLDemo {

	public static void main(String[] args) throws IOException {
		URL hp = new URL("https://www.google.com");
		
		HttpURLConnection hpCon = (HttpURLConnection)hp.openConnection();
		
		// Display the request method.
		System.out.println("Request method is: "+hpCon.getRequestMethod());
		
		// Display the response code.
		System.out.println("Response code is: "+hpCon.getResponseCode());
		
		// Display the response message.
		System.out.println("Response message is: "+hpCon.getResponseMessage());
		
		// Get a list of header fields and a set of header keys.
		Map<String, List<String>> hdrMap = new HashMap<>();
		
		hdrMap = hpCon.getHeaderFields();
		
		for(Map.Entry<String, List<String>> entry: hdrMap.entrySet()) {
			System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
		}
		
	}

}
