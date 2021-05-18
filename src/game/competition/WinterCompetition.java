package game.competition;



import java.security.PublicKey;
import java.util.ArrayList;

import DP.IWinterSportsman;
import game.arena.Arena;
import game.arena.WinterArena;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public class WinterCompetition extends Competition{
	private Discipline discipline;
	private League league;
	private Gender gender;
	
	public WinterCompetition() {}
	
	/**
	 * WinterCompetiton ctor
	 * @param arena the arena of the race
	 * @param maxCompetitors the maximum number of competitors
	 * @param discipline Discipline of the competitor
	 * @param league the league of the competitor
	 * @param gender the gender of the competitor
	 */
	public WinterCompetition(Arena arena,int maxCompetitors, Discipline discipline, League league, Gender gender) {
		super(arena,maxCompetitors);
		if(discipline==null||league==null||gender==null)
			throw new IllegalArgumentException("WinterCompetition:c'tor invalid values");
		this.discipline=discipline;
		this.league=league;
		this.gender=gender;
	}
	/**
	 * func that checks if competitor is valid
	 */
	@Override
	public boolean isValidCompetitor(IWinterSportsman competitor) {
		if(competitor instanceof IWinterSportsman) {
			if(league.isInLeague(( competitor).getAge())&&(competitor).getGender()==gender&&
					(competitor).getDiscipline()==discipline)
				return true;
		}
		return false;
	}
	
	/**
	 * func that get the gender of the comptition
	 * @return gender
	 */
	public Gender getGender() {
		return gender;
	}
	
	/**
	 * func that get the discpline of the comptition
	 * @return discipline
	 */
	public Discipline getDiscipline() {
		// TODO Auto-generated method stub
		return this.discipline;
	}
	@Override
	public void setDiscipline(Discipline disp) {this.discipline=disp;}

	@Override
	public void setLeague(League league) {this.league=league;}

	@Override
	public void setGender(Gender gender) {this.gender=gender;}





}
