
package gui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DP.IWinterSportsman;
import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;

public class InfoTable extends JFrame implements Observer {

	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private Competition competition;
	private JPanel tabPan;
	private int racersNumber;
	/**
	 * info table ctor
	 * @param competition
	 * @param racersNumber
	 */
	public InfoTable(Competition competition, int racersNumber) {
		super("Competitors information");
		this.competition = competition;
		String[] columnNames = { "ID", "Name", "Speed", "Acceleration", "Max speed", "Location", "Finished", "State" };
		this.racersNumber = racersNumber;
		String[][] data = new String[racersNumber][8];
		int i = 0;

		for (IWinterSportsman c : competition.getActiveCompetitors()) {
			data[i][0] = "" + (c).getID();
			data[i][1] = (c).getName();
			data[i][2] = "" + (c).getCurrentSpeed();
			data[i][3] = "" + (c).getBounsAcceleration();
			data[i][4] = "" + (c).getMaxSpeed();
			data[i][5] = "" + (c).getLocation().getY();
			data[i][6] = "NO";
			data[i][7] = c.getState();
			i++;
		}

		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane = new JScrollPane(table);

		tabPan = new JPanel();
		tabPan.add(scrollPane);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(tabPan);
		pack();
		//setVisible(true);
	}
	/**
	 * func that update the table for each comptitior and when the race is finished its sort the table
	 */

	@Override
	public void update(Observable o, Object arg) {
		if ((String) (arg) == "RaceFinished") {
			int i = 0;
			for (IWinterSportsman c : competition.getFinishedCompetitors()) {
				table.setValueAt ( "" + (c).getID(),i,0);
				table.setValueAt((c).getName(),i,1);
				table.setValueAt("" + (c).getCurrentSpeed(),i,2);
				table.setValueAt("" + (c).getBounsAcceleration(),i,3);
				table.setValueAt("" + (c).getMaxSpeed(),i,4);
				table.setValueAt( "" + (c).getLocation().getY(),i,5);
				table.setValueAt( "YES",i,6);
				table.setValueAt (c.getState(),i,7);
				i++;
			}
			for (IWinterSportsman c : competition.getDisabledCompetitors()) {
				table.setValueAt ( "" + (c).getID(),i,0);
				table.setValueAt((c).getName(),i,1);
				table.setValueAt("" + (c).getCurrentSpeed(),i,2);
				table.setValueAt("" + (c).getBounsAcceleration(),i,3);
				table.setValueAt("" + (c).getMaxSpeed(),i,4);
				table.setValueAt( "" + (c).getLocation().getY(),i,5);
				table.setValueAt( "NO",i,6);
				table.setValueAt (c.getState(),i,7);
				i++;
			}

		} else {
			int i = ((IWinterSportsman) o).getTableindex();
			table.setValueAt("" + ((IWinterSportsman) o).getID(), i, 0);
			table.setValueAt(((IWinterSportsman) o).getName(), i, 1);
			table.setValueAt("" + ((IWinterSportsman) o).getCurrentSpeed(), i, 2);
			table.setValueAt("" + ((IWinterSportsman) o).getBounsAcceleration(), i, 3);
			table.setValueAt("" + ((IWinterSportsman) o).getMaxSpeed(), i, 4);
			table.setValueAt("" + ((IWinterSportsman) o).getLocation().getY(), i, 5);
			if (competition.getArena().isFinished((IWinterSportsman) o)) {
				table.setValueAt("YES", i, 6);
			} else {
				table.setValueAt("NO", i, 6);
			}
			table.setValueAt(((IWinterSportsman) o).getState(), i, 7);
		}
	}
	/**
	 * fun that returns the num of competitors
	 * @return racersNumber
	 */
	public int getRacers() {
		return racersNumber;
	}

}
