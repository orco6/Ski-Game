package game;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DP.IWinterSportsman;
import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.WinterSportsman;

import utilities.ValidationUtils;

/**
 * Engine that runs the game step by step.
 */
public class GameEngine extends Observable {
	
	/**
	 * Class singleton instance
	 */
	private static GameEngine instance;
	public static ExecutorService pool =null;

	/**
	 * @return singleton instance of the game engine
	 */
	public static GameEngine getInstance() {
		if (instance == null) {
			instance = new GameEngine();
		}
		return instance;
	}

	/**
	 * private empty Ctor for game engine
	 */
	private GameEngine() {
	}


	/**
	 * play all turns of the competition and print results at the end
	 * @param competition competition to run
	 */
	/*
	public void startRace(Competition competition) {
		ValidationUtils.assertNotNull(competition);			
		ArrayList<Competitor> list=competition.getActiveCompetitors();
		for(int i=0; i<list.size(); i++){
			new Thread((WinterSportsman)(list.get(i))).start();
		}

	}
	*/
	public void startRace(Competition competition,int threadsNumber) {
		ValidationUtils.assertNotNull(competition);			
		new Thread(){ 
			public void run(){
				ArrayList<IWinterSportsman> list=competition.getActiveCompetitors();		
				pool = Executors.newFixedThreadPool(threadsNumber);
				int n=list.size();
				for(int i=0; i<list.size(); i++){
						pool.execute(list.get(i));
				}
				while(competition.hasActiveCompetitors()||competition.hasInjuredCompetitors()) {
					try {
						Thread.sleep(1000);
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				synchronized (competition) {
					setChanged();
					notifyObservers("RaceFinished");
				}
				
				pool.shutdown();	
			}
			}.start();	
	}

}
