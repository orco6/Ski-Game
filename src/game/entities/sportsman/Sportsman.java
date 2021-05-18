package game.entities.sportsman;


import game.entities.MobileEntity;
import game.enums.Gender;

public abstract class Sportsman extends MobileEntity{
	private final String name;
	private final  double age;
	private final Gender gender;
	
	/**
	 * ctor of Sportsman
	 * @param name-name of the sportman
	 * @param age-age of the sportman
	 * @param gender-age of the sportman
	 * @param acceleration-acceleration of sportsman
	 * @param maxSpeed=max-speed of sportsman
	 */
	public Sportsman(String name,  double age , Gender gender , double acceleration , double maxSpeed )throws IllegalArgumentException
	{
		super(maxSpeed,acceleration);
		if(gender==null||age<=0)
			throw new IllegalArgumentException("Sportman:ctor gender or age invalid");
		this.name = name;
		this.gender = gender;
		this.age=age;
	}
	/**
	 * func that returns age
	 */
	public double getAge()
	{
		return this.age;
	}
	/**
	 * func that returns the name of sportsmans
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	
	}
	/**
	 * func that returns the gender of sportsman
	 * @return gender
	 */
	public Gender getGender()
	{
		return this.gender;
	}
	/**
	 * func that returns toString of sporsman
	 */
	public String toString() {
		return this.getClass().getSimpleName()+" " +this.name;
	}
	


	

}

