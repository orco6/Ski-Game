package game.arena;


import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class WinterArena extends Arena {
	/**
	 * WinterArena Ctor
	 * @param length is the length of the arena
	 * @param surface enum that returns the friction of the arena
	 * @param condition enum that returns the conditioin of the arena
	 */
	public WinterArena(double length,SnowSurface surface,WeatherCondition condition){
		super(length,surface,condition);
	}
	


}
