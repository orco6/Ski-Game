package DP;

import game.competition.Competition;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public interface SkiCompetitionBuilder {

	public void BuildCompetitors();
	public void BuildArena();
	public void BuildDiscipline();
	public void BuildLeague();
	public void BuildGender();
	public void BuildFinished();
	public Competition getComp();
}
