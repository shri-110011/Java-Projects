import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame{

	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	
	public MainFrame() {
		super("My New App");
		
		setLayout(new BorderLayout());//LayoutManager helps us to specify where to position the components(JButton, JtextArea etc) in the JFrame.
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		
		setJMenuBar(createMenubar());
		
		//Here we are using a better way to communicate b/w the custom components(Toolbar and TextPanel) through this MainFrame Controller.
		//Earlier the communication between Toolbar and TextPanel was direct and to create a Toolbar we had to create a Textpanel.
		//This problem got solved by creating the StringListener interface.
		toolbar.setTextListener(new StringListener() {
			
			@Override
			public void textEmitted(String text) {
				textPanel.appendText(text);
			}
		});
		
		formPanel.setFormListener(new FormListener() {
			
			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmpCategory();
				String gender = e.getGender();
				
				textPanel.appendText(name+":"+occupation+":"+ageCat+":"+empCat+"\n");
				
				System.out.println("gender:"+gender);
			}
		});
		
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);//add() helps us in adding the components to the content pane. The content pane is the main container in all frames, dialogs and applets.
		add(formPanel, BorderLayout.WEST);
		
		setMinimumSize(new Dimension(500,500));
		setSize(600,500);
		System.out.println(getSize());
		setVisible(true);//By default the JFrame is not visible i.e. why we set the visibility to true.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private JMenuBar createMenubar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show Menu");
		JCheckBox showFormItem = new JCheckBox("Person Form");
		showFormItem.setSelected(true);
		
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox menuItem = (JCheckBox) e.getSource(); 
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		
		fileMenu.setMnemonic(KeyEvent.VK_F);//Mnemonics are like short keys and they can be set for JMenuItem, JButton, JLabel or any other UI controls. 
		exitItem.setMnemonic(KeyEvent.VK_X);//If mnemonic is set for a JMenuItem then the corresponding menu must be expanded first for the mnemonic to work. 
		
		//An Accelerator allows to to call a MenuItem's action listener just using two key combination.
		//An Accelerator doesn't need for the MenuItem to be visible first before using the key combination.
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String text = JOptionPane.showInputDialog(MainFrame.this, "Enter your user name.", "Enter User Name:", JOptionPane.QUESTION_MESSAGE);
				System.out.println(text);
				
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want tot exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}
		});
		
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}
		});
		
		return menuBar;
		
		
	}

}
