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

	public static int NO_DIRECTION = 8;

	public static int translateDxDy( int dx, int dy ) {
		if ( dx < 0 ) {
			if ( dy < 0 ) {
				return LEFT_DOWN;
			}
			if ( dy == 0 ) {
				return LEFT;
			}
			if ( dy > 0 ) {
				return LEFT_UP;
			}
		}
		if ( dx == 0 ) {
			if ( dy < 0 ) {
				return DOWN;
			}
			if ( dy == 0 ) {
				return NO_DIRECTION;
			}
			if ( dy > 0 ) {
				return UP;
			}
		}
		if ( dx > 0 ) {
			if ( dy < 0 ) {
				return RIGHT_DOWN;
			}
			if ( dy == 0 ) {
				return RIGHT;
			}
			if ( dy > 0 ) {
				return RIGHT_UP;
			}
		}

		return NO_DIRECTION;
	}

	public static int getDx( int direction ) {
		if ( direction == RIGHT || direction == RIGHT_UP || direction == RIGHT_DOWN ) {
			return 1;
		}
		if ( direction == LEFT || direction == LEFT_UP || direction == LEFT_DOWN ) {
			return -1;
		}
		return 0;
	}

	public static int getDy( int direction ) {
		if ( direction == UP || direction == RIGHT_UP || direction == LEFT_UP ) {
			return -1;
		}
		if ( direction == DOWN || direction == RIGHT_DOWN || direction == LEFT_DOWN ) {
			return 1;
		}
		return 0;
	}

	public static int random( ) {
		return Core.random.nextInt( 8 );
	}

	public static int[] allMovementDirections( ) {
		return new int[] { RIGHT, LEFT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN  };
	}

}
