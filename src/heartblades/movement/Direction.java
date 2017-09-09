package heartblades.movement;

import heartblades.core.Core;

public class Direction {

	public static int RIGHT = 0; 
	public static int LEFT = 1; 
	public static int UP = 2; 
	public static int DOWN = 3;
	
	public static int RIGHT_UP = 4;
	public static int RIGHT_DOWN = 5;
	public static int LEFT_UP = 6;
	public static int LEFT_DOWN = 7; 
	
	public static int getDx(int direction) {
		if (direction == RIGHT || direction == RIGHT_UP || direction == RIGHT_DOWN) {
			return 1; 
		}
		if (direction == LEFT || direction == LEFT_UP || direction == LEFT_DOWN) {
			return -1; 
		}
		return 0;
	} 
	
	public static int getDy(int direction) {
		if (direction == UP || direction == RIGHT_UP || direction == LEFT_UP) {
			return -1; 
		}
		if (direction == DOWN || direction == RIGHT_DOWN || direction == LEFT_DOWN) {
			return 1; 
		}
		return 0;
	}

	public static int random() {
		return Core.random.nextInt(8);
	} 

}
