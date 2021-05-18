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

import DP.IWinterSportsman;
import game.competition.Competition;
import game.entities.sportsman.WinterSportsman;
import game.enums.Colors;

public class quickAddFrame extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cmbColor;
	private JComboBox<String> cmbID;
	private Competition competition;
	private IWinterSportsman newSportsMan;
	private Screen screen;
	
	/**
	 * ctor of quick add frame
	 * @param competition
	 * @param screen
	 */
	public quickAddFrame(Competition competition,Screen screen)
	{
		
		
		super("Quick adding");
		this.setSize(465,190);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.competition=competition;
		this.screen=screen;


		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(null);
		controlsPanel.setPreferredSize(new Dimension(465,190));
		
		this.add(controlsPanel);
		
		JLabel l = new JLabel("Choose competitor:");
		controlsPanel.add(l);
		l.setLocation(2, 2);
		l.setSize(120, 15);

		String []list1 = new String[competition.getActiveCompetitors().size()];
		for(int i=0;i<competition.getActiveCompetitors().size();i++)
		{
			list1[i]=String.valueOf(( competition.getActiveCompetitors().get(i)).getID());
		}
		cmbID=new JComboBox<String>(list1);
		cmbID.setLocation(2,18);
		cmbID.setSize(135, 20);
		controlsPanel.add(cmbID);
		
		JLabel l2 = new JLabel("Choose new color:");
		controlsPanel.add(l2);
		l2.setLocation(150, 2);
		l2.setSize(120, 15);
		
		String []list2 = {"white","lightGray","gray","darkGray","black","red","pink","orange","yellow","green","magenta","cyan","blue"};
		cmbColor=new JComboBox<String>(list2);
		cmbColor.setLocation(150,18);
		cmbColor.setSize(135, 20);
		controlsPanel.add(cmbColor);
		
		
		JButton buildArenaBut = new JButton("add competitor");
		buildArenaBut.setLocation(150, 80);
		buildArenaBut.setSize(135, 30);
		buildArenaBut.addActionListener(this);
		controlsPanel.add(buildArenaBut);
		
		
	}
	
	
	/**
	 * func that add the new competitor
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "add competitor":
				int indexKeeper=0;
				for(int j=0;j<competition.getActiveCompetitors().size();j++)
				{
					if(cmbID.getSelectedItem().equals((competition.getActiveCompetitors().get(j)).getID()+""))
					{
						indexKeeper=j;
					}
				}
				try {
					newSportsMan = (IWinterSportsman) (competition.getActiveCompetitors().get(indexKeeper)).clone();
					newSportsMan.upgrade(Colors.get(cmbColor.getSelectedItem().toString()));
					this.screen.addCompetitior(newSportsMan);
					this.dispose();
				}
				catch(Exception e3) {
					JFrame error = new JFrame();
					error.setSize(700,200);
					JOptionPane.showMessageDialog(error, "Error creating racer", "Message",JOptionPane.ERROR_MESSAGE);
					return;	
				}
		}
	}
		 
}
