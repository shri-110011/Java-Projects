package fileNIO;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class NIOStreamWrite {

	public static void main(String[] args) {
		
		// Open the file and obtain a stream linked to it.
		try(OutputStream fout = new BufferedOutputStream(
				Files.newOutputStream(Paths.get("test.tt"))
				)) {
			// Write some bytes to the stream.
			for(int i=0; i<26; i++)
				fout.write((byte)('A' + i));
			System.out.println("Write complete!");
		}
		catch(InvalidPathException e) {
			System.out.println("Path error: "+e);
			return;
		}
		catch (IOException e) {
			System.out.println("I/O error: "+e);
		}

	}

}
