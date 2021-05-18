package game.competition;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import DP.ComptitionPlan;
import DP.IWinterSportsman;

import java.lang.IllegalStateException;
import game.arena.IArena;

import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public abstract class Competition implements Observer, ComptitionPlan {
	private ArrayList<IWinterSportsman> activeCompetitors;
	private ArrayList<IWinterSportsman> finishedCompetitors;
	private ArrayList<IWinterSportsman> injuredCompetitors;
	private ArrayList<IWinterSportsman> disabledCompetitors;
	private IArena arena;
	private final int maxCompetitors;

	/**
	 * Competition Ctor
	 * 
	 * @param arena          the arena of the competition
	 * @param maxCompetitors the maximum number of the competitors
	 */
	public Competition() {
		this.injuredCompetitors = new ArrayList<IWinterSportsman>();
		this.disabledCompetitors = new ArrayList<IWinterSportsman>();
		maxCompetitors = 20;
	}
	/**
	 * competition ctor
	 * @param arena
	 * @param maxCompetitors
	 */
	public Competition(IArena arena, int maxCompetitors) {
		if (arena == null || maxCompetitors <= 0)
			throw new IllegalArgumentException("Competition:C'tor maxCopetitors<=0 or arena is null");
		WinterSportsman.setLastIDtozero();
		this.arena = arena;
		this.maxCompetitors = maxCompetitors;
		this.activeCompetitors = new ArrayList<IWinterSportsman>();
		this.finishedCompetitors = new ArrayList<IWinterSportsman>();
		this.injuredCompetitors = new ArrayList<IWinterSportsman>();
		this.disabledCompetitors = new ArrayList<IWinterSportsman>();
	}

	/**
	 * func that checks if competitor is valid
	 * 
	 * @param competitor the competitor of the race
	 * @return true if competitor is valid or false if not
	 */
	protected abstract boolean isValidCompetitor(IWinterSportsman competitor);

	/**
	 * func that adds the the competitor to the race if competitor is valid and add
	 * the comptition to the comptitior as observer
	 * 
	 * @param competitor the competitor that we want to add to the race
	 */
	public void addCompetitor(IWinterSportsman competitor) {
		if (competitor == null)
			throw new IllegalArgumentException("Competition:AddCompetitor Competitor null");
		if (maxCompetitors == activeCompetitors.size())
			throw new IllegalStateException("WinterArena is full max=" + maxCompetitors);
		if (this.isValidCompetitor(competitor)) {
			// (competitor).addObserver(this);
			competitor.setObserver(this);
			activeCompetitors.add(competitor);
		} else
			throw new IllegalArgumentException("Invalid competitor " + (competitor).getName());
	}

	/**
	 * func that checks if activeCompetitors is not empty
	 * 
	 * @return true if array is empty
	 */
	public boolean hasActiveCompetitors() {
		if (activeCompetitors.size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * func that returns the finished array
	 * 
	 * @return finishedCompetitors
	 */
	public ArrayList<IWinterSportsman> getFinishedCompetitors() {
		// TODO Auto-generated method stub
		synchronized (this) {
			return finishedCompetitors;
		}

	}
	/**
	 * func that set finished comptitors
	 */
	@Override
	public void setFinishedCompetitors(ArrayList<IWinterSportsman> array) {
		this.finishedCompetitors=array;
		
	}
	/**
	 * func that returns the injuredcompetitors
	 * @return injuredcompetitors
	 */
	public ArrayList<IWinterSportsman> getInjuredCompetitors() {
		synchronized (this) {
			return injuredCompetitors;
		}
		
	}
	/**
	 * func that set injuredcomptitors
	 * @param injuredCompetitors
	 */
	public void setInjuredCompetitors(ArrayList<IWinterSportsman> injuredCompetitors) {
		synchronized (this) {
			this.injuredCompetitors = injuredCompetitors;
		}
		
	}
	/**
	 * func that returns disabledcomptitors
	 * @return disabledcompetitors
	 */
	public ArrayList<IWinterSportsman> getDisabledCompetitors() {
		synchronized (this) {
			return disabledCompetitors;
		}
		
	}

	/**
	 * func that return the array of the active comptitiors
	 * 
	 * @return
	 */
	public ArrayList<IWinterSportsman> getActiveCompetitors() {
		// TODO Auto-generated method stub
		synchronized (this) {
			return activeCompetitors;
		}
	}

	/**
	 * func that remove comptitior that finish from the active array to the finshed
	 * array
	 */
	public void update(Observable o, Object arg) {
		if (arg != null) {
			int keeper = 0;
			boolean flag = true;
			for (int i = 0; i < activeCompetitors.size() && flag; i++) {
				if (activeCompetitors.get(i).getID() == ((IWinterSportsman) o).getID()) {
					keeper = i;
					flag = false;
				}
			}

			if (((String) arg) == "Completed") {
				synchronized (this) {
					finishedCompetitors.add(activeCompetitors.get(keeper));
					activeCompetitors.remove(keeper);
				}
			}
			else if (((String) arg) == "Active") {
				synchronized (this) {
					int keeper1 = 0;
					boolean flag1 = true;
					for (int i = 0; i < injuredCompetitors.size() && flag1; i++) {
						if (injuredCompetitors.get(i).getID() == ((IWinterSportsman) o).getID()) {
							keeper1 = i;
							flag1 = false;
						}
					}
					activeCompetitors.add(injuredCompetitors.get(keeper1));
					injuredCompetitors.remove(keeper1);
				}
			}
			else if (((String) arg) == "Disabled") {
				synchronized (this) {
					disabledCompetitors.add(activeCompetitors.get(keeper));
					activeCompetitors.remove(keeper);
				}
			}
			else if (((String) arg) == "Injuerd") {
				synchronized (this) {
					injuredCompetitors.add(activeCompetitors.get(keeper));
					activeCompetitors.remove(keeper);
				}
			}
			
		}
	}
	/**
	 * func that returns max comptitiors
	 * @return
	 */
	public int getMaxComp() {
		// TODO Auto-generated method stub
		return maxCompetitors;
	}
	/**
	 * func that return arena comptition
	 * @return
	 */
	public IArena getArena() {
		return arena;
	}
	/**
	 * func that set discipline
	 */
	@Override
	public void setDiscipline(Discipline disp) {
	}
	/**
	 * func set new league for comptition
	 */
	@Override
	public void setLeague(League league) {
	}
	/**
	 * func set new gender for comptition
	 */
	@Override
	public void setGender(Gender gender) {
	}
	
	/**
	 * func set new comptitors for comptition
	 */
	@Override
	public void setCompetitors(ArrayList<IWinterSportsman> array) {
		activeCompetitors = array;

	}
	/**
	 * func that set arnea for comptition
	 */
	@Override
	public void setArena(IArena arena) {
		this.arena = arena;

	}
	/**
	 * func that checks if comptition has injured competitiors
	 */
	public  boolean hasInjuredCompetitors() {
		if(injuredCompetitors.size()>0)
			return true;
		return false;
	}


}
