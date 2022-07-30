import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;


public class SimpleBanner extends Applet implements Runnable {
	
	/*
	 * <applet code="SimpleBanner" width=500 height=400>
	 * 	<param name="msg" value="Hello World!">
	 * </applet>
	 */

	String msg;
	Thread t = null;
	int state;
	volatile boolean stopFlag;
	
	public void init() {
		setBackground(Color.cyan);
		setForeground(Color.red);
	}
	
	public void start() {
		msg = getParameter("msg");
		if(msg == null) msg = "Message not found!";
		t = new Thread(this);
		stopFlag = false;
		t.start();
	}
	
	@Override
	public void run() {
		
		// Redisplay banner.
		while(true) {
			try {
				repaint();
				Thread.sleep(200);
				if(stopFlag) break;
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void stop() {
		stopFlag = true;
		t = null;
	}
	
	// Display the banner
	public void paint(Graphics g) {
		char ch;
		ch = msg.charAt(0);
		msg = msg.substring(1);
		msg += ch;
		
		g.drawString(msg, 50, 30);
	}


}
