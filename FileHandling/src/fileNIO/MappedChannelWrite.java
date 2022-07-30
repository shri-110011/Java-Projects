package fileNIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MappedChannelWrite {

	public static void main(String[] args) {
		
		// Obtain a channel to a file within a try-with-resources block.
		try(FileChannel fChan = (FileChannel)Files.newByteChannel(
				Paths.get("test.txt"), StandardOpenOption.CREATE, 
				StandardOpenOption.WRITE, StandardOpenOption.READ)) {
			
			// Now map the file into a buffer.
			MappedByteBuffer mBuf = fChan.map(MapMode.READ_WRITE, 0, 26);
			
			// Write some bytes to the buffer.
			for(int i=0; i<26; i++) {
				mBuf.put((byte)('A'+i));
			}
			
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
