package fileNIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ExplicitChannelWrite2 {

	public static void main(String[] args) {
		
		FileOutputStream fout = null;
		FileChannel fchan = null;
		String dirName = "test.txt";
		
		try {
			fout = new FileOutputStream(dirName);
			
			// Get a channel to the output file.
			fchan = fout.getChannel();
			
			// Create a buffer.
			ByteBuffer mBuf = ByteBuffer.allocate(26);
			
			// Write some bytes to the buffer.
			for(int i=0; i<26; i++)
				mBuf.put((byte)('A'+i));
			
			// Rewind the buffer so that it can be written.
			mBuf.rewind();
			
			// Writing the buffer to the output file.
			fchan.write(mBuf);
			System.out.println("Write complete!");
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}
		finally {
			try {
				if(fout != null) {
					fout.close();
					System.out.println("FileOutputStream for file: "+dirName+" closed!");
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			try {
				if(fchan != null) {
					fchan.close();
					System.out.println("FileChannel for file: "+dirName+" closed!");
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}
