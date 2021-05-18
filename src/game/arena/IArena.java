package game.arena;


import game.entities.IMobileEntity;

public interface IArena {
	/**
	 * get the friction of arena
	 * @return friction
	 */
	public double getFriction();
	
	/**
	 * func that checks if comptitor is finished
	 * @param competitor
	 * @return
	 */
	public boolean isFinished(IMobileEntity mobileEntity) ;
	
	public double getLength();
}
