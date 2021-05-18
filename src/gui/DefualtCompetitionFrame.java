package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DefualtCompetitionFrame extends JFrame implements ActionListener {

	private JTextField tfNumber;

	private boolean status = false;
	private int num;
	private Screen screen;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ctor of defualt comprtition frame
	 * @param screen
	 */
	public DefualtCompetitionFrame(Screen screen) {
		super("Defualt Competition");
		this.setSize(500, 250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(null);
		controlsPanel.setPreferredSize(new Dimension(500, 250));
		this.add(controlsPanel);

		JLabel l12 = new JLabel("please enter number of competitiors");
		l12.setLocation(150, 20);
		l12.setSize(250, 15);
		controlsPanel.add(l12);

		tfNumber = new JTextField("");
		tfNumber.setLocation(175, 50);
		tfNumber.setSize(135, 25);
		controlsPanel.add(tfNumber);

		JButton editCompBut = new JButton("OK");
		editCompBut.setLocation(175, 100);
		editCompBut.setSize(135, 30);
		editCompBut.addActionListener(this);
		controlsPanel.add(editCompBut);
		this.screen=screen;

		
	}
	/**
	 * action preformed to make competition 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "OK":
			try {
				num = Integer.parseInt(tfNumber.getText());
				if(num<0||num>20){
					throw new Exception("please enter number between 0-20");
				}
			} catch (Exception e8) {
				JFrame error = new JFrame();
				error.setSize(700, 200);
				JOptionPane.showMessageDialog(error, e8.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
			screen.defualtComptition(num);
			this.dispose();
			break;
		}

	}

}
