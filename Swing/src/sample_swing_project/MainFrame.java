package sample_swing_project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private TextPanel textPanel;
	private Toolbar toolbar;
	
	private File choosenFile;

	
	public MainFrame() {
		super("Simple Text Editor");
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		
		toolbar.setFileListener(new FileListener() {
			
			@Override
			public void emitFileInfo(File file) {
				openFileInTextPanel(file);
			}
			
		});
		
		toolbar.setClearTextPanelListener(new ClearTextPanelListener() {
			
			@Override
			public void clear() {
				textPanel.clearTextArea();
			}
		});
		
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		
		setMinimumSize(new Dimension(500, 500));
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		System.out.println(getSize());
	}
	
	private void openFileInTextPanel(File file) {
		// read the file's content and append the content in the TextPanel 
		
		FileReader in = null;
		try {
			in = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.out.println("I/O error: "+e);
		}
		
		try(BufferedReader f = new BufferedReader(in)) {
			int c;
			while((c = f.read()) != -1) {
				textPanel.appendText(Character.toString(c));
			}
		}
		catch(IOException e) {
			System.out.println("I/O error: "+e);
		}
		
	}
	
}
