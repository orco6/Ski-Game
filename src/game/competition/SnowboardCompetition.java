package game.competition;

import DP.IWinterSportsman;
import game.arena.Arena;
import game.arena.WinterArena;
import game.entities.sportsman.Snowboarder;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public class SnowboardCompetition extends WinterCompetition {
	/**
	 * SnowboardCompetiton ctor
	 * @param arena the arena of the race
	 * @param maxCompetitors the maximum number of competitors
	 * @param discipline Discipline of the competitor
	 * @param league the league of the competitor
	 * @param gender the gender of the competitor
	 */
	public SnowboardCompetition(Arena arena,int maxCompetitors, Discipline discipline, League league, Gender gender) {
		super(arena,maxCompetitors, discipline,league,gender);
	}
	/**
	 * func that checks if competitor is valid
	 */
	@Override
	public boolean isValidCompetitor(IWinterSportsman competitor) {
		return super.isValidCompetitor(competitor);
	}
}
