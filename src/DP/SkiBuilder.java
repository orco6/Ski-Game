package DP;

import java.awt.Color;
import java.util.ArrayList;

import game.competition.Competition;
import game.competition.Competitor;
import game.competition.SkiCompetition;
import game.entities.sportsman.Skier;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import gui.Screen;

public class SkiBuilder implements SkiCompetitionBuilder {
	private Competition comp;
	private int numcomp;
	
	/**
	 * ctor of ski Builder
	 * @param numcomp
	 */
	public SkiBuilder(int numcomp) {
		comp=new SkiCompetition();
		this.numcomp=numcomp;
	}
	/**
	 * func that builds the competitiors
	 */
	@Override
	public void BuildCompetitors() {
		ArrayList<IWinterSportsman> list=new ArrayList<IWinterSportsman>();
		Skier sportman=null;
		if (numcomp>0) {
			sportman=new Skier("Yossi" ,21 , Gender.MALE, 3 , 20 , Discipline.DOWNHILL , Color.BLACK );
			list.add(sportman);
			sportman.addObserver(comp);
		}
		for(int i=1;i<numcomp;i++) {
			try {
				Skier s=(Skier) sportman.clone();
				s.addObserver(comp);
				list.add(s);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		comp.setCompetitors(list);
	}

	/**
	 * func that build arena
	 */
	@Override
	public void BuildArena() {
		comp.setArena(new ArenaFactroy().getArena("winter", 800, SnowSurface.POWDER, WeatherCondition.CLOUDY));
		
	}
	/**
	 * func that builds Discipline
	 */
	@Override
	public void BuildDiscipline() {
		comp.setDiscipline(Discipline.DOWNHILL);
		
	}
	/**
	 * func that builds league
	 */
	@Override
	public void BuildLeague() {
		comp.setLeague(League.ADULT);
		
	}
	/**
	 * func that builds gender
	 */
	@Override
	public void BuildGender() {
		comp.setGender(Gender.MALE);
	}
	/**
	 * func that returns competition
	 */
	@Override
	public Competition getComp() {
		// TODO Auto-generated method stub
		return comp;
	}
	/**
	 * func that build finished comptition
	 */
	@Override
	public void BuildFinished() {
		ArrayList<IWinterSportsman> list=new ArrayList<IWinterSportsman>();
		comp.setFinishedCompetitors(list);
		
	}

}
