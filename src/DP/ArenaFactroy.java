package DP;

import game.arena.IArena;
import game.arena.SummerArena;
import game.arena.WinterArena;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class ArenaFactroy {
	
	/**
	 * func that create arenas using factory method pattern
	 * @param arenaType 
	 * @param length
	 * @param surface
	 * @param condition
	 * @return IArena
	 */
	public IArena getArena(String arenaType,double length,SnowSurface surface,WeatherCondition condition) {
		if(arenaType.equals("summer")) {
			return new SummerArena(length, surface, condition);
		}
		else if(arenaType.equals("winter")) {
			return new WinterArena(length, surface, condition);
		}	
		else
			return null;
		
	}
}
