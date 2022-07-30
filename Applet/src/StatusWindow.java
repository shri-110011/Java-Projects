import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class StatusWindow extends Applet {
	
	/*
	 * <applet code="StatusWindow" width=500 height=400> 
	 * </applet>
	 */
	
	public void init() {
		setBackground(Color.cyan);
	}
	
	public void paint(Graphics g) {
		g.drawString("This is in the applet window.", 50, 30);
		showStatus("This is shown in the status window.");
	}

}
