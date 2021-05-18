package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import game.competition.Competition;

public class AddCompFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfAcceleration;
	private JTextField tfName;
	private JTextField tfAge;
	private JTextField tfMaxSpeed;
	private JComboBox<String> cmbColor;
	

	public AddCompFrame() {
		super("AddCompetitor");
		this.setSize(400,250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(null);
		controlsPanel.setPreferredSize(new Dimension(400,250));	
		this.add(controlsPanel);
		
		JLabel l12 = new JLabel("Name:");
		l12.setLocation(5,0);
		l12.setSize(150, 15);
		controlsPanel.add(l12);
	
		tfName = new JTextField("");
		tfName.setLocation(5,18);
		tfName.setSize(135, 25);
		controlsPanel.add(tfName);
		
		
		JLabel l13 = new JLabel("Age:");
		l13.setLocation(5,45);
		l13.setSize(150, 15);
		controlsPanel.add(l13);
		
		tfAge = new JTextField("");
		tfAge.setLocation(5,63);
		tfAge.setSize(135, 25);
		controlsPanel.add(tfAge);
		
		JLabel l14 = new JLabel("Max speed:");
		l14.setLocation(5,90);
		l14.setSize(150, 15);
		controlsPanel.add(l14);
		
		tfMaxSpeed = new JTextField("");
		tfMaxSpeed.setLocation(5,108);
		tfMaxSpeed.setSize(135, 25);
		controlsPanel.add(tfMaxSpeed);
		
		JLabel l15 = new JLabel("Acceleration:");
		l15.setLocation(5,135);
		l15.setSize(150, 15);
		controlsPanel.add(l15);
		
		tfAcceleration = new JTextField("");
		tfAcceleration.setLocation(5,153);
		tfAcceleration.setSize(135, 25);
		controlsPanel.add(tfAcceleration);
		
		JLabel l17 = new JLabel("Color:");
		l17.setLocation(200,0);
		l17.setSize(150, 15);
		controlsPanel.add(l17);
		
		String []list7= {"white","lightGray","gray","darkGray","black","red","pink","orange","yellow","green","magenta","cyan","blue"};
		cmbColor=new JComboBox<String>(list7);
		cmbColor.setLocation(200, 20);
		cmbColor.setSize(135, 25);
		controlsPanel.add(cmbColor);
		
		
		JButton createCompBut = new JButton("Create comptitior");
		createCompBut.setLocation(200, 150);
		createCompBut.setSize(135, 30);
		//createCompBut.addActionListener(this);
		controlsPanel.add(createCompBut);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Create comptitior" :
				dispose();
			}
		
	}

	public JTextField gettfAcceleration() {
		return this.tfAcceleration;
	}
	public JTextField gettfName() {
		return this.tfName;
	}	
	public JTextField gettfAge() {
		return this.tfAge;
	}	
	public JTextField gettfMaxSpeed() {
		return this.tfMaxSpeed;
	}	
	public JComboBox<String> getcmbColor() {
		return this.cmbColor;
	}

}
