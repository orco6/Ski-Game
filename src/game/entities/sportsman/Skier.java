package game.entities.sportsman;

import java.awt.Color;

import game.enums.Discipline;
import game.enums.Gender;

public class Skier extends WinterSportsman{
	
	/**
	 * Skier ctor
	 * @param name-the name of Skier
	 * @param age-the age of Skier
	 * @param gender-the gender of Skier
	 * @param acceleration-the acceleration of Skier
	 * @param maxSpeed-the maxspeed of Skier
	 * @param discipline-the discipline of Skier
	 */
	public Skier(String name ,double age , Gender gender , double acceleration  , double maxSpeed , Discipline discipline , Color color )throws IllegalArgumentException
	{
		super(name,age,gender ,acceleration , maxSpeed , discipline,color);
	}
	/**
	 * func that return clone of competitior
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return new Skier(this.getName(),this.getAge(),this.getGender(),this.getAcceleration(),this.getMaxSpeed(),this.getDiscipline(),this.getColor()); 

	}




	



}
