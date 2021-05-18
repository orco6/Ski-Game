package StatePattern;

import java.util.Random;

import game.competition.Competition;

public class Destiny {
	private static Random rand = new Random();
	/**
	 * this method choose the competitors to make the injured
	 * @param comp
	 */
	public static void CreateInjured(Competition comp) {
		int numofinjured=rand.nextInt(comp.getActiveCompetitors().size());
		for(int i=0;i<numofinjured;i++) {
			comp.getActiveCompetitors().get(rand.nextInt(comp.getActiveCompetitors().size())).
			setInjuerd(rand.nextInt((int)( comp.getArena().getLength()-1)),rand.nextInt(3000));
					
		}
		
	}
	/**
	 * func that choose the comptitiors to make them disabled during the race
	 * @param comp
	 */
	public static void CreateDisabled(Competition comp) {
		int numofdisabled=rand.nextInt(comp.getActiveCompetitors().size());
		for(int i=0;i<numofdisabled;i++) {
			comp.getActiveCompetitors().get(rand.nextInt(comp.getActiveCompetitors().size())).
			setDisabled(rand.nextInt((int)( comp.getArena().getLength()-2)));
					
		}
	}
}
