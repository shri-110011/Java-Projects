package fileNIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ExplicitChannelWrite {

	public static void main(String[] args) {
		
		try(SeekableByteChannel fChan = Files.newByteChannel(
				Paths.get("test.txt"), StandardOpenOption.CREATE, 
				StandardOpenOption.WRITE)) {
			ByteBuffer mBuf = ByteBuffer.allocate(26);
			
			for(int i=0; i<26; i++) {
				mBuf.put((byte)('A'+i));
			}
			
			mBuf.rewind();
			
			for(int i=0; i<26; i++) {
				fChan.write(mBuf);
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
