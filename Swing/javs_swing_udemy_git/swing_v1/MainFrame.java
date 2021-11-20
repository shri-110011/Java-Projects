import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame{

	private JButton btn;
	private TextPanel textPanel;
	private Toolbar toolbar;
	
	public MainFrame() {
		super("My New App");
		
		setLayout(new BorderLayout());//LayoutManager helps us to specify where to position the components(JButton, JtextArea etc) in the JFrame.
		
		btn = new JButton("Click Me");
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		
		toolbar.setTextPanel(textPanel);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textPanel.appendText("Hello!\n");
			}
		});
		
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);//add() helps us in adding the components to the content pane. The content pane is the main container in all frames, dialogs and applets.
		add(btn, BorderLayout.SOUTH);
		
		setSize(600,500);
		setVisible(true);//By default the JFrame is not visible i.e. why we set the visibility to true.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
