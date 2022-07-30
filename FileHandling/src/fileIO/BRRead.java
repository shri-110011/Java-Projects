
// Here we use a BufferredReader to read characters from console.
package fileIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRRead {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char ch;
		int count = 0;
//		System.out.println("");

//		System.out.print("\r");
//		System.out.print("\r\n");
//		System.out.print("\n\r");

//		String str = System.lineSeparator();
//		if(str.equals("\n\r")) System.out.println("1");
//		else if(str.equals("\r")) System.out.println("2");
//		else if(str.equals("\n")) System.out.println("3");
//		else if(str.equals("\r\n")) System.out.println("4");
//		else System.out.println("5");
		

		System.out.println("Enter charaters, 'q' to quit.");
		do {
			ch = (char)br.read(); 
			count++;
			
				System.out.println(ch);
			
			
		} while(ch!='q');
		System.out.println("count: "+count);

	}

}
