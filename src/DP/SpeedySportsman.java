package DP;

public class SpeedySportsman extends WSDecorator {
	/**
	 * speedy sportsman ctor
	 * @param sport
	 * @param acceleration
	 */
	public SpeedySportsman(IWinterSportsman sport,double acceleration ) {
		super(sport);
		sport.setAcceleration(acceleration+sport.getAcceleration());
	}
	/**
	 * func that returns clone of speedy
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new SpeedySportsman((IWinterSportsman) sport.clone(), 0);
	}
	
}
