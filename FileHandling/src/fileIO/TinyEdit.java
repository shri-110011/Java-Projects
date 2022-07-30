package fileIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TinyEdit {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter lines of text.");
		System.out.println("Enter 'stop' to quit.");
		String str[] = new String[100];
		
		for(int i=0; i<100; i++) {
			str[i] = br.readLine();
			if(str[i].equals("stop")) break;
		}
		
		
		for(int i=0;i<100;i++) {
			if(str[i].equals("stop")) break;
			System.out.println(str[i]);
		}
	}

}

/*
How are you?
My name is Shrikant.
Red light means stop
 */