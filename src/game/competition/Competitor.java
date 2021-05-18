package game.competition;

import game.entities.IMobileEntity;

public interface Competitor extends IMobileEntity{
	
	/**
	 * func that that move the competitor to start of the race
	 */
	public void initRace(int x,int y);
}
