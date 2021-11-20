package swing_basics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GUI2 {
	private JFrame frame;
	private JButton button;
	private JTextArea textArea;
	
	public void go() {
		frame = new JFrame("GUI components");
		frame.setLayout(new GridLayout(2, 1));
		
		button = new JButton("Clcik Me");
		textArea = new JTextArea();
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Button clciked at "+ new Date()+ "\n");
				
			}
		});
		
		frame.add(button);
		frame.add(textArea);
		
		frame.setBounds(10, 10, 300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new GUI2().go();
	}

}
