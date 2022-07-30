package swing_basics;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class GUI {

	public static void main(String[] args) {
		JFrame jf = new JFrame("GUI components");
		jf.setLayout(new GridLayout(2, 2));
		
		JButton jb = new JButton("Click Me");
//		JTextField jt = new JTextField();
		JTextArea ja = new JTextArea(6, 15); 
		
		JPanel jp = new JPanel();
		JRadioButton rb1 = new JRadioButton("Hindi");
		JRadioButton rb2 = new JRadioButton("English");
		JRadioButton rb3 = new JRadioButton("Tamil");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		
		jp.setLayout(new GridLayout(3, 1));
		jp.add(rb1);
		jp.add(rb2);
		jp.add(rb3);
		
		String[] labels = {"One", "Two", "Three"};
		JList jl = new JList(labels);
		
		jf.add(jb);
		jf.add(ja);
		jf.add(jp);
		jf.add(jl);
		
		jf.setBounds(200, 100, 300, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		
	

	}

}
