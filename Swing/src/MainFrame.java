import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	
	public MainFrame() {
		super("My New App");
		
		setLayout(new BorderLayout());//LayoutManager helps us to specify where to position the components(JButton, JtextArea etc) in the JFrame.
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		
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
				
				textPanel.appendText(name+":"+occupation+":"+ageCat+", "+empCat+"\n");
			}
		});
		
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);//add() helps us in adding the components to the content pane. The content pane is the main container in all frames, dialogs and applets.
		add(formPanel, BorderLayout.WEST);
		
		setSize(600,500);
		setVisible(true);//By default the JFrame is not visible i.e. why we set the visibility to true.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
