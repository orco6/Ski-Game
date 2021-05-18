package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import DP.ColoredSportsman;
import DP.SpeedySportsman;
import game.competition.Competition;
import game.competition.Competitor;
import game.enums.Colors;

public class AddDecoratorFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfAcceleration;
	private JTextField tfName;
	private JTextField tfAge;
	private JTextField tfMaxSpeed;
	private JComboBox<String> cmbColor;
	private JComboBox<String> cmbID, cmbID2;
	private Competition comptition;
	private Screen screen;
	
	/**
	 * ctor of add decorator frame
	 * @param screen
	 * @param comp
	 */
	public AddDecoratorFrame(Screen screen, Competition comp) {
		super("AddDecorator");
		this.setSize(400, 250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.screen = screen;
		this.comptition = comp;

		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(null);
		controlsPanel.setPreferredSize(new Dimension(400, 250));
		//this.add(controlsPanel);
		
		JPanel controlsPanel2 = new JPanel();
		controlsPanel2.setLayout(null);
		controlsPanel2.setPreferredSize(new Dimension(400, 250));
		//this.add(controlsPanel2);

		JLabel l = new JLabel("Choose competitor:");
		controlsPanel.add(l);
		l.setLocation(5, 2);
		l.setSize(120, 15);
		
		JLabel l2 = new JLabel("Choose competitor:");
		controlsPanel2.add(l2);
		l2.setLocation(5, 2);
		l2.setSize(120, 15);
		
		JLabel l3 = new JLabel("add acceleration:");
		l3.setLocation(200, 0);
		l3.setSize(150, 15);
		controlsPanel2.add(l3);
		
		tfAcceleration = new JTextField("");
		tfAcceleration.setLocation(200,18);
		tfAcceleration.setSize(135, 25);
		controlsPanel2.add(tfAcceleration);

		String[] list1 = new String[comp.getActiveCompetitors().size()];
		for (int i = 0; i < comp.getActiveCompetitors().size(); i++) {
			list1[i] = String.valueOf((comp.getActiveCompetitors().get(i)).getID());
		}
		cmbID = new JComboBox<String>(list1);
		cmbID.setLocation(5, 18);
		cmbID.setSize(135, 20);
		controlsPanel.add(cmbID);
		
		cmbID2 = new JComboBox<String>(list1);
		cmbID2.setLocation(5, 18);
		cmbID2.setSize(135, 20);
		controlsPanel2.add(cmbID2);

		JLabel l17 = new JLabel("Color:");
		l17.setLocation(200, 0);
		l17.setSize(150, 15);
		controlsPanel.add(l17);

		String[] list7 = { "white", "lightGray", "gray", "darkGray", "black", "red", "pink", "orange", "yellow",
				"green", "magenta", "cyan", "blue" };
		cmbColor = new JComboBox<String>(list7);
		cmbColor.setLocation(200, 18);
		cmbColor.setSize(135, 25);
		controlsPanel.add(cmbColor);

		JButton createCompBut = new JButton("color competitor");
		createCompBut.setLocation(120, 100);
		createCompBut.setSize(135, 30);
		createCompBut.addActionListener(this);
		controlsPanel.add(createCompBut);
		
		JButton createCompBut2 = new JButton("add acceleration");
		createCompBut2.setLocation(120, 100);
		createCompBut2.setSize(135, 30);
		createCompBut2.addActionListener(this);
		controlsPanel2.add(createCompBut2);
		
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Colored", null, controlsPanel,
		                  "Does nothing");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		tabbedPane.addTab("Speedy", null, controlsPanel2,
		                "Does twice as much nothing");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		this.add(tabbedPane);

	}
	/**
	 * action of add decorator 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "color competitor":
			int indexKeeper = 0;
			for(int j=0;j<comptition.getActiveCompetitors().size();j++)
			{
				if(cmbID.getSelectedItem().equals((comptition.getActiveCompetitors().get(j)).getID()+""))
				{
					indexKeeper=j;
				}
			}
			ColoredSportsman sport = new ColoredSportsman((comptition.getActiveCompetitors().get(indexKeeper)),Colors.get(cmbColor.getSelectedItem().toString()));
			comptition.getActiveCompetitors().remove(indexKeeper);
			comptition.getActiveCompetitors().add(indexKeeper, sport);
			try {
				screen.UpdateDecorators();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
		break;
		case "add acceleration":
			if(tfAcceleration.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter vaild acceleration amount!");
				return;
			}
			try {
				Double.parseDouble(tfAcceleration.getText());
			}
			catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Please enter vaild acceleration amount!");
				return;
			}
			if(Double.parseDouble(tfAcceleration.getText())<0) {
				JOptionPane.showMessageDialog(this, "Please enter vaild acceleration amount!");
				return;
			}
			int indexKeeper1 = 0;
			for(int j=0;j<comptition.getActiveCompetitors().size();j++)
			{
				if(cmbID2.getSelectedItem().equals((comptition.getActiveCompetitors().get(j)).getID()+""))
				{
					indexKeeper1=j;
				}
			}
			SpeedySportsman sport1 = new SpeedySportsman((comptition.getActiveCompetitors().get(indexKeeper1)),Double.parseDouble(tfAcceleration.getText()));
			comptition.getActiveCompetitors().remove(indexKeeper1);
			comptition.getActiveCompetitors().add(indexKeeper1, sport1);
			try {
				screen.UpdateDecorators();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
		break;
		}

	}
	
	
	/**
	 * func that returns acceleration
	 * @return tfAcceleration
	 */
	public JTextField gettfAcceleration() {
		return this.tfAcceleration;
	}
	/**
	 * func that returns Name
	 * @return tName
	 */
	public JTextField gettfName() {
		return this.tfName;
	}
	/**
	 * func that returns age
	 * @return tfAge
	 */
	public JTextField gettfAge() {
		return this.tfAge;
	}
	/**
	 * func that returns acceleration
	 * @return
	 */
	public JTextField gettfMaxSpeed() {
		return this.tfMaxSpeed;
	}
	/**
	 * func that returns color
	 * @return cmbColor
	 */
	public JComboBox<String> getcmbColor() {
		return this.cmbColor;
	}

}
