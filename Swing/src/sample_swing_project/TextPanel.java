package sample_swing_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {
	private JTextArea textArea;
	private Font f;
	
	public TextPanel() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		f = new Font("Dialog", Font.PLAIN, 16);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setFont(f);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.GREEN);
		textArea.setCaretColor(Color.GREEN);
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public void appendText(String text) {
		textArea.append(text);
	}

	public void clearTextArea() {
		textArea.setText("");
		
	}
}
