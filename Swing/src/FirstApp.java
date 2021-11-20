import java.awt.BorderLayout;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FirstApp {
	private MyActionListener mal = new MyActionListener();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FirstApp firstApp = new FirstApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public FirstApp () {
		initialize();
	}
	
	private void initialize() {
		JFrame f = new JFrame();//creating instance of JFrame  
		f.setSize(400,300);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  

		JButton b = new JButton("Click Me");//creating instance of JButton  
		b.setBounds(140,100,120, 40);//x axis, y axis, width, height  
		f.add(b);//adding button in JFrame  
		b.addActionListener(new MyActionListener());
	}
	
	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Hello!");
		}
	}

}
