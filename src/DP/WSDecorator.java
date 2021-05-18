package DP;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import game.entities.IMobileEntity;
import game.enums.Discipline;
import game.enums.Gender;
import utilities.Point;

public class WSDecorator extends Observable implements IWinterSportsman{
	protected IWinterSportsman sport;
	/**
	 * ctor of decorator
	 * @param sport
	 */
	public WSDecorator(IWinterSportsman sport) {
		// TODO Auto-generated constructor stub
		this.sport=sport;
	}
	/**
	 * func that init race
	 */
	@Override
	public void initRace(int x, int y) {
		sport.initRace(x, y);
		
	}
	/**
	 * func that make move
	 */
	@Override
	public void move(double friction) {
		sport.move(friction);
		
	}
	/**
	 * func that returns the location 
	 */
	@Override
	public Point getLocation() {
		return sport.getLocation();
	}
	/**
	 * run method
	 */
	@Override
	public void run() {
		sport.run();
		
	}
	/**
	 * func that returns the name
	 */
	@Override
	public String getName() {
		return sport.getName();
	}
	/**
	 * func that returns the acceleration
	 */
	@Override
	public double getAcceleration() {
		return sport.getAcceleration();
	}
	/**
	 * func that returns the max speed
	 */
	@Override
	public double getMaxSpeed() {
		return sport.getMaxSpeed();
	}
	/**
	 * func that return the id
	 */
	@Override
	public int getID() {
		return sport.getID();
	}
	/**
	 * func that set the observers
	 */
	@Override
	public void setObserver(Object obj) {
		sport.setObserver(obj);
		
	}
	/**
	 * func that returns the color
	 */
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return sport.getColor();
	}
	/**
	 * func that returns the discipline
	 */
	@Override
	public Discipline getDiscipline() {
		// TODO Auto-generated method stub
		return sport.getDiscipline();
	}
	/**
	 * func that returns gender
	 */

	@Override
	public Gender getGender() {
		// TODO Auto-generated method stub
		return sport.getGender();
	}
	/**
	 * func that return the age
	 */
	@Override
	public double getAge() {
		// TODO Auto-generated method stub
		return sport.getAge();
	}
	/**
	 * func that returns the speed
	 */
	@Override
	public double getCurrentSpeed() {
		// TODO Auto-generated method stub
		return sport.getCurrentSpeed();
	}
	/**
	 * func that set the color
	 */
	@Override
	public void setColor(Color color) {
		sport.setColor(color);
		
	}
	/**
	 * func that set the acceleration
	 */
	@Override
	public void setAcceleration(double acceleration) {
		sport.setAcceleration(acceleration);
		
	}
	/**
	 * func that returns the bouns acceleration
	 */
	@Override
	public double getBounsAcceleration() {
		return sport.getBounsAcceleration();
		
	}
	/**
	 * func that using the upgrade method
	 */
	@Override
	public void upgrade(Color color) {
		sport.upgrade(color);
		
	}
	/**
	 * func that returns the clone
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return null;
	}
	/**
	 * func that set the injuerd detials of comptitiors
	 */
	@Override
	public void setInjuerd(int location, int time) {
		sport.setInjuerd(location, time);
		
	}
	/**
	 * func that set the disabled detials of comptitiors
	 */
	@Override
	public void setDisabled(int Location) {
		sport.setDisabled(Location);
		
	}
	/**
	 * func that return the state of comptitior
	 */
	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return sport.getState();
	}
	/**
	 * func that returns the index of a table
	 */
	@Override
	public int getTableindex() {
		// TODO Auto-generated method stub
		return sport.getTableindex();
	}
	/**
	 * func that set the tamle index
	 */
	@Override
	public void setTableindex(int index) {
		sport.setTableindex(index);
		
	}
	/**
	 * func that remove table from observer comptitor
	 */
	@Override
	public void removeTable(Observer obj) {
		sport.removeTable(obj);
		
	}


}
