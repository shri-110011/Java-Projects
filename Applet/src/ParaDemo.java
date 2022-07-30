import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

public class ParaDemo extends Applet {
	
	/*
	 * <applet code="ParaDemo" width=500 height=400> 
	 * <param name=fontName value=Courier>
	 * <param name=fontSize value=14>
	 * <param name=leading value=2>
	 * <param name=accountEnabled value=true>
	 * </applet>
	 */
	
	String fontName;
	int fontSize;
	float leading;
	boolean active;
	
	public void init() {
		Font font = new Font("calibri", Font.BOLD, 24);
		setFont(font);
		System.out.println(getFont());
	}
	
	public void start() {
		String param;
		
		fontName = getParameter("fontName");
		if(fontName == null)
			fontName = "Not Found";
		
		param = getParameter("fontSize");
		try {
			if(param != null)
				fontSize = Integer.parseInt(param);
			else
				fontSize = 0;
		}
		catch(NumberFormatException e) {
			fontSize = -1;
		}
		
		param = getParameter("leading");
		try {
			if(param != null)
				leading = Float.parseFloat(param);
		}
		catch(NumberFormatException e) {
			leading = -1;
		}
		
		
		param = getParameter("accountEnabled");
		if(param != null)
			active = Boolean.valueOf(param).booleanValue();
		
	}
	
	// Display parameters.
	public void paint(Graphics g) {
		g.drawString("Font name: "+fontName , 30, 30);
		g.drawString("Font size: "+fontSize , 30, 60);
		g.drawString("Leading: "+leading , 30, 90);
		g.drawString("Account Active: "+active , 30, 120);
	}

}
