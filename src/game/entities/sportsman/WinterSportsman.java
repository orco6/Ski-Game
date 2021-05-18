package game.entities.sportsman;
import java.awt.Color;
import java.util.Observer;

import DP.IWinterSportsman;
import StatePattern.Active;
import StatePattern.Completed;
import StatePattern.Disabled;
import StatePattern.RacerState;
import game.GameEngine;
import game.competition.Competitor;
import game.entities.MobileEntity;
import game.enums.Discipline;
import game.enums.Gender;
import utilities.Point;

public abstract class WinterSportsman extends Sportsman implements IWinterSportsman, Cloneable {
	public static int LastID;
	private Discipline discipline;
	private int ID;
	private Color color;
	private RacerState state;
	private int Tableindex;

	
	/**
	 * WinterSportsman ctor
	 * @param name-the name of wintersportman
	 * @param age-the age of wintersportman
	 * @param gender-the gender of wintersportman
	 * @param acceleration-the acceleration of wintersportman
	 * @param maxSpeed-the maxspeed of wintersportman
	 * @param discipline-the discipline of wintersportman
	 */
	public WinterSportsman(String name , double age , Gender gender, double acceleration , double maxSpeed  ,Discipline discipline , Color color)throws IllegalArgumentException
	{
		super(name, age,gender ,acceleration , maxSpeed);
		if(discipline==null)
			throw new IllegalArgumentException("WinterSportman:setDiscipline is null");
		this.discipline = discipline;
		this.ID=WinterSportsman.getLastID();
		this.color = color;
		this.state=new Active();
	}
	

	/**
	 * func that set state
	 */
	@Override
	public void setState(RacerState state) {
		this.state=state;
		
	}



	/**
	 * func that return the discipline 
	 * @return discipline
	 */
	public Discipline getDiscipline(){
	 return this.discipline;
	}
	
	/**
	 * func that sets the discipline
	 * @param disci
	 */
	protected void setDiscipline(Discipline disci)
	{
		if(disci==null)
			throw new IllegalArgumentException("WinterSportman:setDiscipline is null");
	 this.discipline = disci;
	}
	
	/**
	 * func that sets location of wintersportman to(0,0)
	 */
	public void initRace(int x,int y) {
		this.setLocation(new Point(x,y));
	}


	/**
	 * func that make wintersportsman move each time until he finshed 
	 */
	/*
	public void run() {
		while(!MobileEntity.getArena().isFinished(this)){
			this.move(MobileEntity.getArena().getFriction());
			try {
				setChanged();
				notifyObservers();
				Thread.sleep(100);
				} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
		setChanged();
		notifyObservers("1");
	
		
	}
	*/
	/**
	 * run method of comptitior
	 */
	public void run() {
			
			this.move(MobileEntity.getArena().getFriction());
			setChanged();
			notifyObservers();
			if(state instanceof Disabled)
				return;
			

			if(MobileEntity.getArena().isFinished(this)) {
				setState(new Completed());
				setChanged();
				notifyObservers("Completed");				
			}
			else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GameEngine.pool.execute((WinterSportsman)this);
			}
	


	}
	
	/**
	 * func that remove table observer
	 */
	@Override
	public void removeTable(Observer obj) {
		this.deleteObserver(obj);
		
	}


	/**
	 * func that returns tableindex
	 */
	@Override
	public int getTableindex() {
		return Tableindex;
	}


	/**
	 * func that set table index
	 */
	@Override
	public void setTableindex(int index) {
		Tableindex=index;
		
	}

	/**
	 * func that returns color
	 */

	public Color getColor() {
		return this.color;
	}
	/**
	 * func that returns id
	 */
	public int getID() {
		return this.ID;
	}
	/**
	 * func that upgrade comtitior color
	 */
	public void upgrade(Color color) {
		this.color=color;
	}
	/**
	 * func that returns clone
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return null;
	}
	/**
	 * func that returns the last id of comptitior
	 * @return
	 */
	public static int getLastID() {
		WinterSportsman.LastID++;
		return WinterSportsman.LastID;
	}
	/**
	 * func that init id conuter
	 */
	public static void setLastIDtozero() {
		WinterSportsman.LastID=0;
	}
	/**
	 * func that set observer
	 */
	@Override
	public void setObserver(Object obj) {
		this.addObserver((Observer)obj);
	}
	/**
	 * func that setcolor
	 */
	@Override
	public void setColor(Color color) {
		this.color=color;
		
	}


	/**
	 * func that return the state
	 */
	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return state.alert();
	}

}
