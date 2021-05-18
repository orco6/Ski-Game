package DP;

import java.awt.Color;

import game.competition.Competitor;

public class ColoredSportsman extends WSDecorator{
	/**
	 * Ctor of ColoredSportsman
	 * @param sport
	 * @param color
	 */
	public ColoredSportsman(IWinterSportsman sport, Color color) {
		super(sport);
		Color newcolor= blend(sport.getColor(), color);
		sport.setColor(newcolor);
	}
	/**
	 * func that return the mix of the old color and the new color
	 * @param c0
	 * @param c1
	 * @return
	 */
	public static Color blend(Color c0, Color c1) {
		double totalAlpha = c0.getAlpha() + c1.getAlpha();
		double weight0 = c0.getAlpha() / totalAlpha;
		double weight1 = c1.getAlpha() / totalAlpha;

		double r = weight0 * c0.getRed() + weight1 * c1.getRed();
		double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
		double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
		double a = Math.max(c0.getAlpha(), c1.getAlpha());

		return new Color((int) r, (int) g, (int) b, (int) a);
	}
	/**
	 * clone method for colored sportman
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new ColoredSportsman((IWinterSportsman) sport.clone(), sport.getColor());
	}
}
