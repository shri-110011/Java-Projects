// This program shows the concept of suppressed exception while using try with resources.

package fileIO;

class SwingException extends Exception {
	
	private String message = "";
	
	public SwingException() {
		
	}
	
	public SwingException(String msg) {
		message = msg;
	}
	
	public String toString() {
		return "SwingException: "+message;
	}
	
	
}

class CloseException extends Exception {
	private String message = "";
		
	public CloseException() {
		
	}
	
	public CloseException(String msg) {
		message = msg;
	}
	
	public String toString() {
		return "CloseException: "+message;
	}
}
class Door implements AutoCloseable {
	
	public void swing() throws Exception {
		System.out.println("The door is unhinged !");
		throw new SwingException("The door is swinging!");
	}

	@Override
	public void close() throws Exception {
		System.out.println("The door is closed !");
//		throw new CloseException();
	}
	
}
public class SuppressedExceptionExample {

	public static void main(String[] args) {
		try(Door door = new Door()) {
			door.swing();
		}
		catch(Exception e) {
			System.out.println("Primary exception: "+e);
			System.out.println("Number of suppresed exceptions: "+e.getSuppressed().length);
			if(e.getSuppressed().length > 0)
				System.out.println("Supressed exception: "+e.getSuppressed()[0]);
		}
	}

}
