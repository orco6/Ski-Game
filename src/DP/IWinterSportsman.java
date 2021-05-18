package DP;

import java.awt.Color;
import java.util.Observer;

import game.competition.Competitor;
import game.enums.Colors;
import game.enums.Discipline;
import game.enums.Gender;

public interface IWinterSportsman extends Competitor,Runnable,Cloneable {
	public String getName();
	public double getAcceleration();
	public double getMaxSpeed();
	public int getID();
	public void setObserver(Object obj);
	public Color getColor();
	public Discipline getDiscipline();
	public Gender getGender();
	public double getAge();
	public double getCurrentSpeed();
	public void setColor(Color color);
	public void setAcceleration(double acceleration);
	public double getBounsAcceleration();
	public void upgrade(Color color);
	public Object clone() throws CloneNotSupportedException;
	public void setInjuerd(int location, int time);
	public void setDisabled(int Location);
	public String getState();
	public int getTableindex();
	public void setTableindex(int index);
	public void removeTable(Observer obj);
}
