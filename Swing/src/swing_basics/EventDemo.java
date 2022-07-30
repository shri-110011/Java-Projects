package swing_basics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class EventDemo {
	
	JLabel jlab;
	
	EventDemo() {
		JFrame jfrm = new JFrame("An Event Example");
		
		//jfrm.setLayout(null);
		jfrm.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
		
		jfrm.setSize(300, 100);
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton jbtnAlpha = new JButton("Alpha");
		JButton jbtnBeta = new JButton("Beta");
		
		jbtnAlpha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jlab.setText("Alpha was pressed");
			}
		});
		
		jbtnBeta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jlab.setText("Beta was pressed");	
			}
		});
		
		//jbtnAlpha.setBounds(10, 10, 70, 30);
		jfrm.add(jbtnAlpha);
		
		//jbtnBeta.setBounds(90, 10, 70, 30);
		jfrm.add(jbtnBeta);
		
		jlab = new JLabel("Press a button.");
		
		//jlab.setBounds(170, 10, 120, 30);
		jfrm.add(jlab);
		
		jfrm.setVisible(true);
		
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EventDemo();
				System.out.println("Inside run(): "+Thread.currentThread());
			}
		});

	}

}
