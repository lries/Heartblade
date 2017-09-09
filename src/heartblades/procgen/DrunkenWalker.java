package heartblades.procgen;

import heartblades.core.Core;
import heartblades.map.Tile;

public class DrunkenWalker {

	public static int NOT = -1;
	public static int RIGHT = 0;
	public static int LEFT = 1;
	public static int UP = 2;
	public static int DOWN = 3;

	public static void drunkenWalk( Tile[][] dungeon, Tile set, int length, int padding ) {

		int posX = Core.random.nextInt( dungeon.length );
		int posY = Core.random.nextInt( dungeon[0].length );

		drunkenWalk( dungeon, set, length, padding, posX, posY, -1 );

	}

	public static int[] drunkenWalk( Tile[][] dungeon, Tile set, int length, int padding, int posX, int posY,
			int constrainDirection ) {

		for ( int i = 0; i < length; i++ ) {

			dungeon[posX][posY] = set;
			boolean tryAgain = true;
			int value = 0;

			while ( tryAgain ) {
				tryAgain = false;
				value = Core.random.nextInt( 7 );
				if ( constrainDirection == RIGHT && (value == 0 || value == 4 || value == 6) ) {
					tryAgain = true;
				}
				if ( constrainDirection == LEFT && (value == 1 || value == 5 || value == 7) ) {
					tryAgain = true;
				}
				if ( constrainDirection == UP && (value == 2 || value == 6 || value == 7) ) {
					tryAgain = true;
				}
				if ( constrainDirection == DOWN && (value == 3 || value == 4 || value == 5) ) {
					tryAgain = true;
				}
			}

			switch ( value ) {
			case 0:
				posX = bind( posX + 1, padding, dungeon.length - 1 - padding );
				break;
			case 1:
				posX = bind( posX - 1, padding, dungeon.length - 1 - padding );
				break;
			case 2:
				posY = bind( posY + 1, padding, dungeon.length - 1 - padding );
				break;
			case 3:
				posY = bind( posY - 1, padding, dungeon.length - 1 - padding );
				break;
			case 4:
				posX = bind( posX + 1, padding, dungeon.length - 1 - padding );
				posY = bind( posY - 1, padding, dungeon.length - 1 - padding );
				break;
			case 5:
				posX = bind( posX - 1, padding, dungeon.length - 1 - padding );
				posY = bind( posY - 1, padding, dungeon.length - 1 - padding );
				break;
			case 6:
				posX = bind( posX + 1, padding, dungeon.length - 1 - padding );
				posY = bind( posY + 1, padding, dungeon.length - 1 - padding );
				break;
			case 7:
				posX = bind( posX - 1, padding, dungeon.length - 1 - padding );
				posY = bind( posY + 1, padding, dungeon.length - 1 - padding );
				break;
			}

		}

		int[] ret = { posX, posY };
		return ret;
	}

	protected static int bind( int val, int min, int max ) {
		if ( val < min ) {
			return min;
		}

		else if ( val > max ) {
			return max;
		}

		return val;
	}

}
