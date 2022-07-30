package swing_basics;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BorderLayoutDemo {

	BorderLayoutDemo() {
		
		JFrame jfrm = new JFrame("BorderLayout Example");
		
		jfrm.setSize(350, 150);
		
		jfrm.setLayout(new BorderLayout());
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton jbtnTop = new JButton("Top Button");
		
		JButton jbtnBottom = new JButton("Bottom Button");
		
		JButton jbtnLeft = new JButton("Left Button");
		
		JButton jbtnRight = new JButton("Right Button");
		
		jfrm.add(jbtnTop, BorderLayout.NORTH);
		jfrm.add(jbtnBottom, BorderLayout.SOUTH);
		jfrm.add(jbtnLeft, BorderLayout.WEST);
		jfrm.add(jbtnRight, BorderLayout.EAST);
		
		JLabel jlab = new JLabel("This is some text.");
		
		jfrm.add(jlab, BorderLayout.CENTER);
		
		jfrm.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BorderLayoutDemo();
				System.out.println("Inside run(): "+Thread.currentThread());
			}
		});

	}
}
