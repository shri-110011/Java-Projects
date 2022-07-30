import java.applet.Applet;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * <applet code="ACDemo" width=500 height=400> 
 * </applet>
 */
public class ACDemo extends Applet {
	
	public void start() {
		AppletContext ac = getAppletContext();
		URL url = getCodeBase();
		try {
			ac.showDocument(new URL(url+" test.html"));
		}
		catch(MalformedURLException e) {
			showStatus("URL not found!");
		}
	}

}
