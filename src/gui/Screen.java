package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DP.ArenaFactroy;
import DP.Creator;
import DP.IWinterSportsman;
import DP.SkiBuilder;
import DP.SkiCompetitionBuilder;
import StatePattern.Destiny;
import game.GameEngine;
import game.arena.Arena;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competitor;
import game.competition.WinterCompetition;
import game.entities.MobileEntity;
import game.entities.sportsman.Snowboarder;
import game.entities.sportsman.WinterSportsman;
import game.enums.Colors;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

/**
 * Gui for race
 * 
 * @author liels
 *
 */
public class Screen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<IWinterSportsman> racers;
	private JLabel BackgroundLabel;
	private Image racersImages[] = null;
	private JFrame infoTable = null;
	private JPanel controlsPanel, mainPanel, arenaPanel;
	private JTextField tfArenaLength;
	private JTextField tfMaxSpeed;
	private JTextField tfAcceleration;
	private JTextField tfName;
	private JTextField tfMaxComptitiors;
	private JTextField tfAge;
	private JComboBox<String> cmbArena;
	private JComboBox<String> cmbSurface;
	private JComboBox<String> cmbWeather;
	private JComboBox<String> cmbComptition;
	private JComboBox<String> cmbDiscipline;
	private JComboBox<String> cmbLeague;
	private JComboBox<String> cmbGender;
	private JComboBox<String> cmbColor;
	private String type;
	private String chosenArena = null;
	private String gendercomp;
	private IArena arena = null;
	private WinterCompetition competition = null;
	private static boolean raceStarted = false;
	private boolean raceFinished = false;
	private int maxcomp;
	private static int racersNumber = 0;
	private int arenaWidth = 1000;
	private int arenaLength = 800;
	private int maxRacers = 20;
	private InfoTable table;

	/**
	 * ctor for the gui class
	 */
	public Screen() {
		super("Competition");
		this.setContentPane(getMainPanel());
		this.setSize(arenaWidth, arenaLength + 110);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * func that add comptitor ,add arena,add comptition, start race ,show info race
	 * by press on the button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Build arena":
			try {
				clearParams();
				ArenaFactroy factory = new ArenaFactroy();
				int length = Integer.parseInt(tfArenaLength.getText());
				SnowSurface surface = SnowSurface.valueOf(cmbSurface.getSelectedItem().toString().toUpperCase());
				WeatherCondition weather = WeatherCondition
						.valueOf(cmbWeather.getSelectedItem().toString().toUpperCase());
				chosenArena = cmbWeather.getSelectedItem().toString();
				if(cmbArena.getSelectedItem().toString()=="summer")
					throw new Exception("Summer arena is not possiable");
				if ((length >= 700) && (length <= 900)) {
					arenaLength = length;
					arena = factory.getArena(cmbArena.getSelectedItem().toString(), length, surface, weather);
					MobileEntity.setArena(arena);
				} else {
					throw new Exception("Invalid length number please choose length between 700-900");
				}
			} catch (Exception e1) {
				JFrame error = new JFrame();
				error.setSize(700, 200);
				JOptionPane.showMessageDialog(error, e1.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
			}
			updateBackground();
			break;

		case "Create comptition":
			try {
				clearParams();
				maxcomp = Integer.parseInt(tfMaxComptitiors.getText().toString());
				if (arena == null) {
					throw new Exception("Please build arena,create comptition and add Comptitors");
				} else if (maxcomp < 1 || 20 < maxcomp) {
					throw new Exception("Invalid input values! Please try again");
				}

				else {
					Discipline discip = Discipline.valueOf(cmbDiscipline.getSelectedItem().toString().toUpperCase());
					League league = League.valueOf(cmbLeague.getSelectedItem().toString().toUpperCase());
					Gender gender = Gender.valueOf(cmbGender.getSelectedItem().toString().toUpperCase());
					gendercomp = cmbGender.getSelectedItem().toString();
					type = cmbComptition.getSelectedItem().toString();
					ClassLoader cl = ClassLoader.getSystemClassLoader();
					Class c = cl.loadClass("game.competition." + type + "Competition");
					Constructor con = c.getConstructor(Arena.class, int.class, Discipline.class, League.class,
							Gender.class);
					competition = (WinterCompetition) con.newInstance(arena, maxcomp, discip, league, gender);
					if (maxcomp > 14) {
						arenaWidth += (maxcomp - 14) * 60;
					} else {
						arenaWidth = 1000;
					}
				}
			} catch (Exception e1) {
				JFrame error = new JFrame();
				error.setSize(700, 200);
				JOptionPane.showMessageDialog(error, e1.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
			}
			updateBackground();
			break;

		case "Add comptitior":
			try {
				if (arena == null) {
					throw new Exception("please build arena first");
				}
				if (competition == null) {
					throw new Exception("please create competiton first");
				}
				if (isRaceStarted() == true) {
					throw new Exception("race started cant add comptitior");
				}

				if (tfAcceleration.getText().isEmpty() || tfMaxSpeed.getText().isEmpty() || tfAge.getText().isEmpty()
						|| tfName.getText().isEmpty()) {
					throw new IllegalArgumentException("please fill all comptitor fields");
				}
				String name = tfName.getText().toString();
				double age = Integer.parseInt(tfAge.getText().toString());
				double maxspeed = Double.parseDouble(tfMaxSpeed.getText().toString());
				double acceleration = Double.parseDouble(tfAcceleration.getText().toString());
				Color color = Colors.get(cmbColor.getSelectedItem().toString());

				if (acceleration <= 0 || maxspeed <= 0 || age <= 0) {
					throw new IllegalArgumentException("please put only positive inputs");
				}

				ClassLoader cl = ClassLoader.getSystemClassLoader();
				Class c = cl.loadClass("game.entities.sportsman." + type + "er");
				Constructor con = c.getConstructor(String.class, double.class, Gender.class, double.class, double.class,
						Discipline.class, Color.class);
				WinterSportsman wintersportsman = (WinterSportsman) con.newInstance(name, age, competition.getGender(),
						acceleration, maxspeed, competition.getDiscipline(), color);
				addCompetitior(wintersportsman);

			} catch (IllegalArgumentException e1) {
				JFrame error = new JFrame();
				error.setSize(700, 200);
				JOptionPane.showMessageDialog(error, e1.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
			}

			catch (Exception e2) {
				JFrame error = new JFrame();
				error.setSize(700, 200);
				JOptionPane.showMessageDialog(error, e2.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
			}
			break;

		case "Quick adding":
			try {
				if (arena == null) {
					throw new Exception("please build arena first");
				}
				if (competition == null) {
					throw new Exception("please create competiton first");
				}
				if (isRaceStarted() == true) {
					throw new Exception("race started cant add comptitior");
				}
				if (racersNumber==0) {
					throw new Exception("please add at least one comptitor");
				}
				new quickAddFrame(competition, this);

			} catch (IllegalArgumentException e1) {
				JFrame error = new JFrame();
				error.setSize(700, 200);
				JOptionPane.showMessageDialog(error, e1.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
			}

			catch (Exception e2) {
				JFrame error = new JFrame();
				error.setSize(700, 200);
				JOptionPane.showMessageDialog(error, e2.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
			}
			break;

		case "Start comptition":
			if (arena == null || racersNumber == 0 || competition == null) {
				JOptionPane.showMessageDialog(this, "Please build arena and comptition first and add racers!");
				return;
			}
			if (raceFinished) {
				JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena and add racers.");
				return;
			}
			if (isRaceStarted()) {
				JOptionPane.showMessageDialog(this, "Race already started!");
				return;
			}
			initTable();
			Destiny.CreateDisabled(competition);
			Destiny.CreateInjured(competition);
			startCompetionFrame frame = new startCompetionFrame(competition);
			break;

		case "Show info":
			if (arena == null || racersNumber == 0 || competition == null) {
				JOptionPane.showMessageDialog(this, "Please build arena and competition first and add racers!");
				return;
			}
			initTable();
			table.setVisible(true);
			
			
			break;

		case "DeafualtCompetition":
			clearParams();
			new DefualtCompetitionFrame(this);
			break;
		
		case "edit Competitor":
			new AddDecoratorFrame(this,competition);
		}
	}

	/**
	 * func that adds the background to the arena panel and adds the comptitors to
	 * the panel
	 * 
	 * @return arenapanel
	 */
	private JPanel getArenaPanel() {
		arenaPanel = new JPanel();
		arenaPanel.setLayout(null);
		arenaPanel.setPreferredSize(new Dimension(arenaWidth - 165, arenaLength + 100));
		return arenaPanel;
	}

	/**
	 * func that add the buttons of the jpanel menu
	 * 
	 * @return jpanel for that to add to the jframe
	 */
	private JPanel getControlsPanel() {
		controlsPanel = new JPanel();
		controlsPanel.setLayout(null);
		controlsPanel.setPreferredSize(new Dimension(140, arenaLength + 100));

		JLabel l1 = new JLabel("BUILD ARENA");
		makeUnderLine(l1);
		controlsPanel.add(l1);
		l1.setLocation(0, 5);
		l1.setSize(100, 15);

		JLabel l16 = new JLabel("Choose arena:");
		l16.setLocation(0, 22);
		l16.setSize(100, 15);
		controlsPanel.add(l16);

		String[] list10 = { "winter", "summer" };
		cmbArena = new JComboBox<String>(list10);
		cmbArena.setLocation(0, 40);
		cmbArena.setSize(135, 20);
		controlsPanel.add(cmbArena);

		JLabel l2 = new JLabel("Arena length:");
		l2.setLocation(0, 64);
		l2.setSize(100, 15);
		controlsPanel.add(l2);

		tfArenaLength = new JTextField("" + arenaLength);
		tfArenaLength.setLocation(0, 80);
		tfArenaLength.setSize(135, 25);
		controlsPanel.add(tfArenaLength);

		JLabel l3 = new JLabel("Snow surface:");
		l3.setLocation(0, 103);
		l3.setSize(100, 15);
		controlsPanel.add(l3);

		String[] list1 = { "Powder", "Ice", "Crud" };
		cmbSurface = new JComboBox<String>(list1);
		cmbSurface.setLocation(0, 118);
		cmbSurface.setSize(135, 20);
		controlsPanel.add(cmbSurface);

		JLabel l4 = new JLabel("Weather condition:");
		controlsPanel.add(l4);
		l4.setLocation(0, 138);
		l4.setSize(120, 15);

		String[] list2 = { "Cloudy", "Stormy", "Sunny" };
		cmbWeather = new JComboBox<String>(list2);
		cmbWeather.setLocation(0, 153);
		cmbWeather.setSize(135, 20);
		controlsPanel.add(cmbWeather);

		JButton buildArenaBut = new JButton("Build arena");
		buildArenaBut.setLocation(0, 175);
		buildArenaBut.setSize(135, 30);
		buildArenaBut.addActionListener(this);
		controlsPanel.add(buildArenaBut);

		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setLocation(0, 208);
		sep.setSize(150, 10);
		controlsPanel.add(sep);

		JLabel l5 = new JLabel("CREATE COMPTITION");
		makeUnderLine(l5);
		l5.setLocation(0, 210);
		l5.setSize(120, 15);
		controlsPanel.add(l5);

		JLabel l6 = new JLabel("Choose comptition:");
		l6.setLocation(0, 224);
		l6.setSize(120, 15);
		controlsPanel.add(l6);

		String[] list3 = { "Ski", "Snowboard" };
		cmbComptition = new JComboBox<String>(list3);
		cmbComptition.setLocation(0, 240);
		cmbComptition.setSize(135, 20);
		controlsPanel.add(cmbComptition);

		JLabel l7 = new JLabel("Max comptitiors number:");
		l7.setLocation(0, 260);
		l7.setSize(150, 15);
		controlsPanel.add(l7);

		tfMaxComptitiors = new JTextField("10");
		tfMaxComptitiors.setLocation(0, 275);
		tfMaxComptitiors.setSize(135, 25);
		controlsPanel.add(tfMaxComptitiors);

		JLabel l8 = new JLabel("Discipline:");
		l8.setLocation(0, 300);
		l8.setSize(150, 15);
		controlsPanel.add(l8);

		String[] list4 = { "Downhill", "Freestyle", "Giant_slalom", "Slalom" };
		cmbDiscipline = new JComboBox<String>(list4);
		cmbDiscipline.setLocation(0, 315);
		cmbDiscipline.setSize(135, 20);
		controlsPanel.add(cmbDiscipline);

		JLabel l9 = new JLabel("League:");
		l9.setLocation(0, 334);
		l9.setSize(150, 15);
		controlsPanel.add(l9);

		String[] list5 = { "Adult", "Junior", "Senior" };
		cmbLeague = new JComboBox<String>(list5);
		cmbLeague.setLocation(0, 350);
		cmbLeague.setSize(135, 20);
		controlsPanel.add(cmbLeague);

		JLabel l10 = new JLabel("Gender:");
		l10.setLocation(0, 370);
		l10.setSize(150, 15);
		controlsPanel.add(l10);

		String[] list6 = { "Male", "Female" };
		cmbGender = new JComboBox<String>(list6);
		cmbGender.setLocation(0, 385);
		cmbGender.setSize(135, 20);
		controlsPanel.add(cmbGender);

		JButton CrtcompBut = new JButton("Create comptition");
		CrtcompBut.setLocation(0, 408);
		CrtcompBut.setSize(135, 30);
		CrtcompBut.addActionListener(this);
		controlsPanel.add(CrtcompBut);

		JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
		sep2.setLocation(0, 440);
		sep2.setSize(150, 10);
		controlsPanel.add(sep2);

		JLabel l11 = new JLabel("ADD COMPTITIOR");
		makeUnderLine(l11);
		l11.setLocation(0, 445);
		l11.setSize(150, 15);
		controlsPanel.add(l11);

		JLabel l12 = new JLabel("Name:");
		l12.setLocation(0, 462);
		l12.setSize(150, 15);
		controlsPanel.add(l12);

		tfName = new JTextField("");
		tfName.setLocation(0, 478);
		tfName.setSize(135, 25);
		controlsPanel.add(tfName);

		JLabel l13 = new JLabel("Age:");
		l13.setLocation(0, 502);
		l13.setSize(150, 15);
		controlsPanel.add(l13);

		tfAge = new JTextField("");
		tfAge.setLocation(0, 518);
		tfAge.setSize(135, 25);
		controlsPanel.add(tfAge);

		JLabel l14 = new JLabel("Max speed:");
		l14.setLocation(0, 542);
		l14.setSize(150, 15);
		controlsPanel.add(l14);

		tfMaxSpeed = new JTextField("");
		tfMaxSpeed.setLocation(0, 558);
		tfMaxSpeed.setSize(135, 25);
		controlsPanel.add(tfMaxSpeed);

		JLabel l15 = new JLabel("Acceleration:");
		l15.setLocation(0, 583);
		l15.setSize(150, 15);
		controlsPanel.add(l15);

		tfAcceleration = new JTextField("");
		tfAcceleration.setLocation(0, 598);
		tfAcceleration.setSize(135, 25);
		controlsPanel.add(tfAcceleration);

		JLabel l17 = new JLabel("Color:");
		l17.setLocation(0, 624);
		l17.setSize(150, 15);
		controlsPanel.add(l17);

		String[] list7 = { "white", "lightGray", "gray", "darkGray", "black", "red", "pink", "orange", "yellow",
				"green", "magenta", "cyan", "blue" };
		cmbColor = new JComboBox<String>(list7);
		cmbColor.setLocation(0, 639);
		cmbColor.setSize(135, 20);
		controlsPanel.add(cmbColor);

		JButton createCompBut = new JButton("Add comptitior");
		createCompBut.setLocation(0, 662);
		createCompBut.setSize(135, 30);
		createCompBut.addActionListener(this);
		controlsPanel.add(createCompBut);

		JButton duplicateCompetitorBut = new JButton("Quick adding");
		duplicateCompetitorBut.setLocation(0, 694);
		duplicateCompetitorBut.setSize(135, 30);
		duplicateCompetitorBut.addActionListener(this);
		controlsPanel.add(duplicateCompetitorBut);

		JSeparator sep3 = new JSeparator(SwingConstants.HORIZONTAL);
		sep3.setLocation(0, 725);
		sep3.setSize(150, 10);
		controlsPanel.add(sep3);
		
		JButton editCompBut = new JButton("edit Competitor");
		editCompBut.setLocation(0, 728);
		editCompBut.setSize(135, 30);
		editCompBut.addActionListener(this);
		controlsPanel.add(editCompBut);
		
		JButton startRaceBut = new JButton("Start comptition");
		startRaceBut.setLocation(0, 760);
		startRaceBut.setSize(135, 30);
		startRaceBut.addActionListener(this);
		controlsPanel.add(startRaceBut);

		JButton Dcomp = new JButton("DeafualtCompetition");
		Dcomp.setLocation(0, 792);
		Dcomp.setSize(135, 30);
		Dcomp.addActionListener(this);
		controlsPanel.add(Dcomp);

		JButton printInfoBut = new JButton("Show info");
		printInfoBut.setLocation(0, 824);
		printInfoBut.setSize(135, 30);
		printInfoBut.addActionListener(this);
		controlsPanel.add(printInfoBut);



		return controlsPanel;
	}

	/**
	 * func that make underline and blue font to the labels
	 * 
	 * @param HeadLine the label that we want to make underline and make font blue
	 */
	private void makeUnderLine(JLabel HeadLine) {
		Font font = HeadLine.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		HeadLine.setFont(font.deriveFont(attributes));
		HeadLine.setForeground(Color.BLUE);
	}

	/**
	 * func that make the main panel of the jframe and add to it the arena panel and
	 * the control panel
	 * 
	 * @return main panel
	 */
	private JPanel getMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(getArenaPanel(), BorderLayout.WEST);
		mainPanel.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);
		mainPanel.add(getControlsPanel(), BorderLayout.EAST);
		return mainPanel;
	}
	/**
	 * func that adds the comptitor to the comptition
	 * @param newSportsMan
	 * @throws IOException
	 */
	public void addCompetitior(IWinterSportsman newSportsMan) throws IOException {
		competition.addCompetitor(newSportsMan);
		newSportsMan.initRace((int) ((newSportsMan.getLocation().getX() + 60) * racersNumber), 0);
		racers.add(newSportsMan);
		updateCompetitorGui(newSportsMan);
	}
	/**
	 * func that update the background of arena
	 */
	private void updateBackground() {
		this.setSize(arenaWidth, arenaLength + 110);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		if ((chosenArena != null) && (arena != null)) {
			arenaPanel.removeAll();
			arenaPanel.setLayout(null);
			arenaPanel.setPreferredSize(new Dimension(arenaWidth - 165, arenaLength + 55));

			Image imageIcon1 = new ImageIcon(arenaPanel.getClass().getResource("/" + chosenArena + ".jpg")).getImage()
					.getScaledInstance(arenaWidth - 80, arenaLength + 100, Image.SCALE_DEFAULT);
			BackgroundLabel = new JLabel(new ImageIcon(imageIcon1));
			BackgroundLabel.setLocation(0, 0);
			BackgroundLabel.setSize(arenaWidth - 80, arenaLength + 55);
			arenaPanel.add(BackgroundLabel);
			arenaPanel.repaint();
		}
	}
	/**
	 * func that add the competitor to the gui
	 * @param newSportsMan
	 * @throws IOException
	 */
	private void updateCompetitorGui(IWinterSportsman newSportsMan) throws IOException {

		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/" + type + "Male.png"));

		for (int y = 0; y < image.getHeight(); y++)
			for (int x = 0; x < image.getWidth(); x++) {
				if (image.getRGB(x, y) == Color.BLUE.getRGB()) {
					// mix imageColor and desired color
					image.setRGB(x, y, newSportsMan.getColor().getRGB());
				}
			}

		Image racerImage = new ImageIcon(image).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		// racersImages[racersNumber]=racerImage;
		racersNumber++;

		RacerLabel racerlabel = new RacerLabel(new ImageIcon(racerImage));
		racerlabel.setLocation(((int) newSportsMan.getLocation().getX()), (int) newSportsMan.getLocation().getY());
		racerlabel.setSize(50, 50);
		BackgroundLabel.add(racerlabel);
		newSportsMan.setObserver(racerlabel);
		BackgroundLabel.repaint();

	}
	/**
	 * func that clear the parameters of the gui
	 */
	private void clearParams() {
		updateBackground();
		table=null;
		WinterSportsman.setLastIDtozero();
		raceFinished = false;
		setRaceStarted(false);
		racersNumber = 0;
		competition = null;
		racersImages = new Image[maxRacers];
		racers = new ArrayList<IWinterSportsman>();
	}
	/**
	 * func that returns if the race started
	 * @return race started
	 */
	public static boolean isRaceStarted() {
		return raceStarted;
	}
	/**
	 * func the init the raceStarted parameter
	 * @param raceStarted
	 */
	public static void setRaceStarted(boolean raceStarted) {
		Screen.raceStarted = raceStarted;
	}
	/**
	 * func that builds the defualtcomptetion
	 * @param num
	 */
	public void defualtComptition(int num) {
		SkiCompetitionBuilder cBuilder = new SkiBuilder(num);
		Creator creator = new Creator(cBuilder);
		creator.constructComptition();
		competition = (WinterCompetition) creator.getComp();
		chosenArena = "Cloudy";
		arenaLength = 800;
		maxcomp = competition.getMaxComp();
		arena = competition.getArena();
		MobileEntity.setArena(arena);
		type = "Ski";
		if (maxcomp > 14) {
			arenaWidth =1000+ (maxcomp - 14) * 60;
		} else {
			arenaWidth = 1000;
		}
		updateBackground();
		for (int i = 0; i < competition.getActiveCompetitors().size(); i++) {
			try {
				((WinterSportsman) (competition.getActiveCompetitors().get(i))).initRace(
						(int) ((((WinterSportsman) (competition.getActiveCompetitors().get(i))).getLocation().getX()+ 60) * racersNumber),0);
				updateCompetitorGui((WinterSportsman) competition.getActiveCompetitors().get(i));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	/**
	 * func that update the decorator competitiors
	 * @throws IOException
	 */
	public void UpdateDecorators() throws IOException {
		BackgroundLabel.removeAll();
		for(int i=0;i<competition.getActiveCompetitors().size();i++) {
			IWinterSportsman iWinterSportsman = competition.getActiveCompetitors().get(i);
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/" + type + "Male.png"));
			for (int y = 0; y < image.getHeight(); y++)
				for (int x = 0; x < image.getWidth(); x++) {
					if (image.getRGB(x, y) == Color.BLUE.getRGB()) {
						// mix imageColor and desired color
						image.setRGB(x, y, iWinterSportsman.getColor().getRGB());
					}
				}

			Image racerImage = new ImageIcon(image).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
			RacerLabel racerlabel = new RacerLabel(new ImageIcon(racerImage));
			racerlabel.setLocation(((int) iWinterSportsman.getLocation().getX()), (int) iWinterSportsman.getLocation().getY());
			racerlabel.setSize(50, 50);
			BackgroundLabel.add(racerlabel);
			iWinterSportsman.setObserver(racerlabel);
		}
		BackgroundLabel.repaint();
		
	}
	/**
	 * func that init Table info
	 */
	private void initTable() {
		if(table==null||raceStarted==false) {
			if(table!=null) {
				for(int i=0;i<competition.getActiveCompetitors().size();i++) 
					competition.getActiveCompetitors().get(i).removeTable(table);
				GameEngine.getInstance().deleteObserver(table);
				table.dispose();
			}
			table = new InfoTable(competition, racersNumber);
			GameEngine.getInstance().addObserver((Observer) table);
			for(int i=0;i<competition.getActiveCompetitors().size();i++) {
				competition.getActiveCompetitors().get(i).setTableindex(i);
				competition.getActiveCompetitors().get(i).setObserver(table);
				
			}
		}
	}
}
