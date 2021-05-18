package game.enums;
import java.awt.Color;
public enum Colors {
	red (Color.RED),blue (Color.BLUE),green (Color.GREEN),white(Color.WHITE),lightGray(Color.LIGHT_GRAY),
	gray(Color.GRAY),darkGray(Color.DARK_GRAY),black(Color.BLACK),pink(Color.PINK),orange(Color.ORANGE),yellow(Color.YELLOW),
	magenta(Color.MAGENTA),cyan(Color.CYAN);

	private final Color color;
	 /**
	  * ctor of colors 
	  * @param color
	  */
	Colors(Color color) { 
		this.color=color;
		
		}
	/**
	 * func that returns the color from String
	 * @param name
	 * @return
	 */
	public static Color get(String name){
		for(Colors a:Colors.values()) {
			if(a.toString().equals(name))
				return a.color;
			}
		return null;
		}
}

