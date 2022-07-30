import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList<AgeCategory> ageList;
	private JComboBox<String> empCombo;
	private JCheckBox citizenCheck;
	private JTextField taxIdField;
	private JLabel taxIdLabel;
	private JRadioButton femaleRadio;
	private JRadioButton maleRadio;
	private ButtonGroup genderGroup;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);//Note the BorderLayout manager won't respect the set height but it will respect the set width.
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox();
		taxIdField = new JTextField(10);
		taxIdLabel = new JLabel("Tax ID: ");
		
		genderGroup = new ButtonGroup();
		femaleRadio = new JRadioButton("Female");
		maleRadio = new JRadioButton("Male");
		
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		
		maleRadio.setSelected(true);
		
		okBtn = new JButton("Submit");
		
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		// Setup list box
		DefaultListModel<AgeCategory> ageModel = new DefaultListModel<AgeCategory>();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "Above 65"));
		ageList.setModel(ageModel);
		
		ageList.setPreferredSize(new Dimension(110,70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(0);
		
		// Setup combo box
		DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<String>();
		empModel.addElement("Employed");
		empModel.addElement("Unemployed");
		empModel.addElement("Self-employed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(1);
		
		
		//Setup the US citizen check box
		citizenCheck = new JCheckBox();
		
		//Setup the taxId field and taxtId label
		
		taxIdField.setEnabled(false);
		taxIdLabel.setEnabled(false);
		
		//Set up the gender radio buttons
		genderGroup.add(femaleRadio);
		genderGroup.add(maleRadio);
		
		citizenCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = citizenCheck.isSelected();
				taxIdField.setEnabled(isTicked);
				taxIdLabel.setEnabled(isTicked);
			}
		});
		
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCategory = (AgeCategory)ageList.getSelectedValue();
				String empCat = (String)empCombo.getSelectedItem();
				boolean usCitizen = citizenCheck.isSelected();
				String taxId = taxIdField.getText();
				String gender = genderGroup.getSelection().getActionCommand();
				
				System.out.println("employment category:"+empCat);
				
				FormEvent formEvent = new FormEvent(e, name, occupation, ageCategory.getId(), empCat, usCitizen, 
						taxId, gender);
				
				if(formListener != null) {
					formListener.formEventOccurred(formEvent);
				}
			}
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
//		setBorder(BorderFactory.createTitledBorder("Add Person"));
		
		layoutComponents();
	}
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		////////// First row  //////////////////////////////////
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(nameLabel, gc);
			
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(nameField, gc);
		
		////////// Next row  //////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(occupationField, gc);
		
		////////// Next row  //////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Age: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(ageList, gc);
		
		//////////Next row  //////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Employment: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);
		
		//////////Next row  //////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("US Citizen: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(citizenCheck, gc);
		
		//////////Next row  //////////////////////////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(taxIdLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(taxIdField, gc);
		
		
		////////// Next row  //////////////////////////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(new JLabel("Gender"), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(maleRadio, gc);
		
		//////////Next row  //////////////////////////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(femaleRadio, gc);
		
		//////////Next row  //////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn, gc);
	}
	public void setFormListener(FormListener formListener) {
		this.formListener = formListener;
	}
}

class AgeCategory {
	private int id;
	private String text;
	
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public int getId() {
		return this.id;
	}
}