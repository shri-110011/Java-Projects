import java.applet.Applet;
import java.awt.Graphics;

public class AppletSkel extends Applet {
	
	/*
	 * <applet code="AppletSkel" width=500 height=400> 
	 * </applet>
	 */
	
	String msg;
	// Called first.
	public void init() {
		msg = "Hello! This is a simple applet.";
		System.out.println("Inside init()");
	}
	
	/* Called second after init(). Also called whenever the applet gets 
	 * restarted.
	 */
	public void start() {
		System.out.println("Inside start()");
	}
	
	// Called when the applet is stopped.
	public void stop() {
		System.out.println("Inside stop()");
	}
	
	// Called when an applet is terminated. This is the last method executed.
	public void destroy() {
		System.out.println("Inside destroy()");
	}
	
	// Called when the applet's window must be restored.
	public void paint(Graphics g) {
		g.drawString(msg, 50, 50);
		System.out.println("Inside paint()");
	}
	
	
}
