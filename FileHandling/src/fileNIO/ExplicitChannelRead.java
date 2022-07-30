
// Use channel I/O to read a file. Requires JDK 7 or higher.
package fileNIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ExplicitChannelRead {

	public static void main(String[] args) {
		
		int count;
		Path filePath = null;
		
		// First, obtain a path to a file.
		try {
			filePath = Paths.get("test1.txt");
		}
		catch(InvalidPathException e) {
			System.out.println("Path error: "+e);
			return;
		}
		
		// Next, obtain a channel to that file within a try-with-resources block.
		try(SeekableByteChannel fChan = Files.newByteChannel(filePath, StandardOpenOption.CREATE_NEW, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
			
			// Allocate a buffer.
			ByteBuffer mBuf = ByteBuffer.allocate(128);
			
			while((count=fChan.read(mBuf)) != -1) {
				
				//Rewind the buffer so that it can be read.
				mBuf.rewind();
				
				for(int i=0; i<count; i++) System.out.print((char)mBuf.get());
				
			}
			
			System.out.println();
			
			
		} catch (IOException e) {
			e.printStackTrace();
//			System.out.println("I/O error: "+e);
		}
		

	}

}
