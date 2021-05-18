package game.entities.sportsman;

import java.awt.Color;

import game.enums.Discipline;
import game.enums.Gender;

public class Snowboarder extends WinterSportsman {
	
	/**
	 * Skier ctor
	 * @param name-the name of Snowboarder
	 * @param age-the age of Snowboarder
	 * @param gender-the gender of Snowboarder
	 * @param acceleration-the acceleration of Snowboarder
	 * @param maxSpeed-the maxspeed of Snowboarder
	 * @param discipline-the discipline of Snowboarder
	 */
	public Snowboarder(String name ,double age , Gender gender , double acceleration  , double maxSpeed , Discipline discipline , Color color )throws IllegalArgumentException
	{
		super(name,age,gender ,acceleration , maxSpeed , discipline,color);
	}
	/**
	 * func that return clone of comptitior
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return new Snowboarder(this.getName(),this.getAge(),this.getGender(),this.getAcceleration(),this.getMaxSpeed(),this.getDiscipline(),this.getColor()); 

	}

	

}
