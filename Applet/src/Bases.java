import java.applet.Applet;
import java.awt.Graphics;
import java.net.URL;


/*
 * <applet code="Bases" width=500 height=400> 
 * </applet>
 */

public class Bases extends Applet {
	
	// Display code and document bases.
	public void paint(Graphics g) {
		String msg;
		
		URL url = getCodeBase();
		msg = "Code base: "+url.toString();
		g.drawString(msg, 10, 20);
		
		url = getDocumentBase();
		msg = "Document base: "+url.toString();
		g.drawString(msg, 10, 40);
	}
	

}
