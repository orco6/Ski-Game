package DP;

import game.competition.Competition;

public class Creator {
	public SkiCompetitionBuilder builder;
	/**
	 * ctor of class that create the comptition
	 * @param builder
	 */
	public Creator(SkiCompetitionBuilder builder){
		this.builder=builder;
	}
	/**
	 * func that return the comptition
	 * @return comptition
	 */
	public Competition getComp() {
		return builder.getComp();
	}
	/**
	 * func that construct comptition
	 */
	public void constructComptition() {
		builder.BuildArena();
		builder.BuildCompetitors();
		builder.BuildDiscipline();
		builder.BuildGender();
		builder.BuildLeague();
		builder.BuildFinished();
	}
}
