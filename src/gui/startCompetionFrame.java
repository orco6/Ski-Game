package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.GameEngine;
import game.competition.Competition;
import game.entities.sportsman.WinterSportsman;
import game.enums.Colors;

public class startCompetionFrame extends JFrame implements ActionListener {

	private JTextField tfName;
	private Competition competition;
	private JComboBox<String> cmbID;
	private boolean racestart=false;
	/**
	 * start competion ctor
	 * @param competition
	 */
	startCompetionFrame(Competition competition) {
		super("start competition");
		this.setSize(500, 250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(null);
		controlsPanel.setPreferredSize(new Dimension(500, 250));
		this.add(controlsPanel);

		JLabel l12 = new JLabel("please enter number of threads");
		l12.setLocation(150, 20);
		l12.setSize(200, 15);
		controlsPanel.add(l12);

		
		int keeper=1;
		
		String []list1 = new String[competition.getActiveCompetitors().size()-1];
		for(int i=0;i<competition.getActiveCompetitors().size()-1;i++)
		{
			list1[i]=keeper+"";
			keeper++;
		}
		if (list1.length==0){
			list1 = new String[competition.getActiveCompetitors().size()];
			list1[0]=""+1;
		}
			
		cmbID=new JComboBox<String>(list1);
		cmbID.setLocation(182,38);
		cmbID.setSize(135, 25);
		controlsPanel.add(cmbID);
		
		JButton editCompBut = new JButton("OK");
		editCompBut.setLocation(182, 100);
		editCompBut.setSize(135, 30);
		editCompBut.addActionListener(this);
		controlsPanel.add(editCompBut);

		this.competition = competition;
	}
	/**
	 * func that choose the number of threads to run
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "OK":
			int n;
			try {
				n = Integer.parseInt(cmbID.getSelectedItem().toString());
			} catch (Exception e8) {
				JFrame error = new JFrame();
				error.setSize(700, 200);
				JOptionPane.showMessageDialog(error, e8.getMessage(), "Message",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			GameEngine.getInstance().startRace(competition, n);
			Screen.setRaceStarted(true);
			this.dispose();
			break;
		}
	}
	public boolean getStatus() {
		return racestart;
	}

}
