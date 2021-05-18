package game.entities;



import StatePattern.Active;
import StatePattern.Disabled;
import StatePattern.Injured;
import StatePattern.RacerState;
import game.arena.Arena;
import game.arena.IArena;
import game.enums.League;
import utilities.Point;

public abstract class MobileEntity extends Entity implements IMobileEntity {
	private double maxSpeed;
	private double acceleration;
	private double speed;
	protected static IArena arena;
	private int Injuerdlocation;
	protected int Injuerdtime;
	private int disabledlocation;
	private boolean injuerd=false;
	
	
	/**
	 * ctor of MobileEntity
	 * @param maxSpeed is the max-speed of entity
	 * @param acceleration is the acceleration of entity
	 */
	public MobileEntity(double maxSpeed , double acceleration)
	{
		if(maxSpeed<=0||acceleration<=0)
			throw new IllegalArgumentException("MobileEntity:maxspeed or acceleration is <=0");
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.speed=0;
	}
	/**
	 * func that return maxspeed
	 * @return maxSpeed
	 */
	public double getMaxSpeed()
	{
		return maxSpeed;
	}
	/**
	 * func that returns acceleration with bonus of acceleration
	 * @return acceleration with acceleration-bonus
	 */
	public double getBounsAcceleration()
	{
		
		return this.acceleration + League.calcAccelerationBonus(this.getAge());
	}
	
	public double getAcceleration() {
		return this.acceleration;
	}
	
	/**
	 * func that return current speed
	 * @return speed
	 */
	public double getCurrentSpeed()
	{
		return this.speed;
	}
	/**
	 * func that sets that current speed
	 * @param s is the new speed
	 */
	protected void setCurrentSpeed(double s)
	{
		if(s<0)
			throw new IllegalArgumentException("setCurrentSpeed is <0");
		this.speed = s;
	}


	/**
	 * func that improve speed and location
	 * @throws InterruptedException 
	 */
	@Override
	public void move(double friction)
	{
		if (getCurrentSpeed()+(getBounsAcceleration()*(1-friction))>=getMaxSpeed())
			setCurrentSpeed(getMaxSpeed());
		else
		{
			setCurrentSpeed(getCurrentSpeed()+(getBounsAcceleration()*(1-friction))); 
		}
		
		if((getLocation().getY()+ getCurrentSpeed()>Injuerdlocation)&&Injuerdlocation!=0&&!injuerd) {
			injuerd=true;
			setLocation(new Point(getLocation().getX(),Injuerdlocation));
			setChanged();
			notifyObservers();
			setState(new Injured());
			setChanged();
			notifyObservers("Injuerd");
			try {
				Thread.sleep(Injuerdtime);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setChanged();
			notifyObservers("Active");
			setState(new Active());
			return;
			
		}
		if((getLocation().getY()+ getCurrentSpeed()>disabledlocation)&&disabledlocation!=0) {
			setLocation(new Point(getLocation().getX(),disabledlocation));
			setState(new Disabled());
			setChanged();
			notifyObservers("Disabled");
			return;
		}
		
		
		if(getLocation().getY()+ getCurrentSpeed()<arena.getLength())
			this.setLocation(new Point(getLocation().getX(),getLocation().getY()+ getCurrentSpeed()));
		else
			this.setLocation(new Point(getLocation().getX(),arena.getLength()));	
	}
	/**
	 * func that gets the age
	 * @return age
	 */
	protected abstract double getAge();
	/**
	 * func that returns the arena of the comptitiors
	 * @return arena
	 */
	public static IArena getArena()
	{
		return MobileEntity.arena;
	}
	/**
	 * func that set the arena of the comptitiors
	 * @param arena2
	 */
	public static void setArena(IArena arena2)
	{
		arena=arena2;
	}
	/**
	 * func that set acceleration for comptitor
	 * @param acceleration
	 */
	public void setAcceleration(double acceleration) {
		this.acceleration=acceleration;
	}
	/**
	 * func that set disabled location for comptitor
	 */
	public void setDisabled(int Location) {
		this.disabledlocation=Location;
		
	}
	/**
	 * func that set injuerd location and time for comptitor
	 */
	public void setInjuerd(int location, int time) {
		this.Injuerdlocation=location;
		this.Injuerdtime=time;
		
	}
	/**
	 * func that set state
	 */
	public abstract void setState(RacerState state);
	
}
