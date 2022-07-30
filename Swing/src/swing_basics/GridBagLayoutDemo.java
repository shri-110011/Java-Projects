package swing_basics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class GridBagLayoutDemo {
	
	private JFrame jfrm = new JFrame("GridBagLayout Example");
	private JPanel jpanel = new JPanel(new GridBagLayout());
	private JButton jbtn1 = new JButton("One");
	private JButton jbtn2 = new JButton("Two");
	private JButton jbtn3 = new JButton("Three");
	
	
	GridBagLayoutDemo() {
		
		jfrm.setSize(350, 150);
		
		jfrm.setLayout(new BorderLayout());
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = jpanel.getPreferredSize();
		dim.width = 250;
		jpanel.setPreferredSize(dim);
		
		System.out.println(dim);
		
		Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border innerborder = BorderFactory.createEtchedBorder();
		jpanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerborder));
		
		layoutComponents();
		
	}
	
	public void layoutComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
//		gbc.ipadx = 15;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		jpanel.add(jbtn1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		jpanel.add(jbtn2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 2;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		jpanel.add(jbtn3, gbc);
		
		jfrm.add(jpanel, BorderLayout.WEST);
		
		//jfrm.pack();
		jfrm.setVisible(true);
		
		System.out.println("Inside layoutComponents(): "+Thread.currentThread());
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GridBagLayoutDemo();
				System.out.println("Inside run(): "+Thread.currentThread());
			}
		});

	}

}
