package sample_swing_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {
	private JButton openButton;
	private JButton clearButton;
	private JFileChooser fileChooser;
	
	private FileListener fileListener;
	private ClearTextPanelListener clearTextPanelListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		openButton = new JButton("Open");
		clearButton = new JButton("Clear");
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new AllowedFilesFilter());
		
		openButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		add(openButton);
		add(clearButton);
	}
	
	public void setFileListener(FileListener fileListener) {
		this.fileListener = fileListener;
	}
	
	public void setClearTextPanelListener(ClearTextPanelListener clearTextPanelListener) {
		this.clearTextPanelListener = clearTextPanelListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == openButton) {
			System.out.println("Open");
			if(fileChooser.showOpenDialog(Toolbar.this) == JFileChooser.OPEN_DIALOG) {
				File file = fileChooser.getSelectedFile();
				System.out.println("Selected file: "+file);
				fileListener.emitFileInfo(file);
			}
		}
		else if((JButton)e.getSource() == clearButton) {
			System.out.println("Clear");
			clearTextPanelListener.clear();
		}
	}
	
}
