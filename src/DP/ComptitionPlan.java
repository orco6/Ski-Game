package DP;

import java.util.ArrayList;

import game.arena.IArena;
import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public interface ComptitionPlan {
	public void setCompetitors(ArrayList<IWinterSportsman> array);
	public void setArena(IArena arena);
	public void setDiscipline(Discipline disp);
	public void setLeague(League league);
	public void setGender(Gender gender);
	public void setFinishedCompetitors(ArrayList<IWinterSportsman> array);
}
