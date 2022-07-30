package swing_basics;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GridLayoutDemo {
	
	private JFrame jfrm;
	private JPanel jpanel;

	public GridLayoutDemo() {

		jfrm = new JFrame("BorderLayout Example");
		
//		jfrm.setSize(380, 150);
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jpanel = new JPanel(new GridLayout(0 ,4, 5, 5));
		
		for(int i=0;i<12;i++) {
			JButton jbtn = new JButton("Button "+i);
			jpanel.add(jbtn);
		}
		
		jfrm.add(jpanel);
		jfrm.setVisible(true);
		jfrm.pack();
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GridLayoutDemo();
				System.out.println("Inside run(): "+Thread.currentThread());
			}
		});

	}
}
