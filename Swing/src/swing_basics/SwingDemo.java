package swing_basics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingDemo {

	SwingDemo() {
		JFrame jfrm = new JFrame("A Simple Swing Application");
		
		jfrm.setBounds(100,100,250,100);
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel jlab = new JLabel("Swing means powerful GUIs.");
		
		jfrm.add(jlab);
		
		jfrm.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		System.out.println("Inside main(): "+Thread.currentThread());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SwingDemo();
				System.out.println("Inside run(): "+Thread.currentThread());
			}
		});
	}
	
}
