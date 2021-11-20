import java.io.BufferedReader;
import java.io.FileReader;

public class ReadAFile {

	public static void main(String[] args) throws Throwable{ //Add Throwable is a way to get around compiler error but it is not used in production quality code. 
		FileReader fr = new FileReader("abc.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		while((line = br.readLine())!= null) {
			System.out.println("Read: "+line);
		}
		
		
	}

}

---------------------------------------------

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadAFile {

	public static void main(String[] args) { 
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("abc1.txt");
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			System.err.println("File was not found!");
		}
		
		
		String line;
		try {
			while((line = br.readLine())!= null) {
				System.out.println("Read: "+line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

