import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ReadAFile {

	public static void main(String[] args) { 
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		boolean success = false;
		FileReader fr = null;
		BufferedReader br = null;
		while(!success) {
			String filename = null;
			try {
				System.out.println("Enter filename: ");
				filename = keyboard.readLine();
				fr = new FileReader(filename);
				br = new BufferedReader(fr);
				success = true;
			} catch (FileNotFoundException e) {
				System.err.println("File was not found!");
				System.out.println("File \""+filename+"\" was not found, try again... ");
			} catch (IOException e) {
				System.err.println("Failed to read the keyboard!");
			}
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
