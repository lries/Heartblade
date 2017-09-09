package heartblades.procgen;

import heartblades.core.Core;
import heartblades.map.Tile;

public class DrunkenCorridors {

	public static void drunkenCorridors( Tile[][] dungeon, Tile floor, int padding, int numRooms, int corridorLength,
			int minWidth, int maxWidth, int minHeight, int maxHeight ) {

		int width = Core.random.nextInt( maxWidth - minWidth ) + minWidth;
		int height = Core.random.nextInt( maxHeight - minHeight ) + minHeight;

		int xPos = Core.random.nextInt( dungeon.length - padding * 2 - width - 1 ) + padding + width - 1;
		int yPos = Core.random.nextInt( dungeon[0].length - padding * 2 - height - 1 ) + padding + height - 1;

		for ( int x = 0; x < numRooms; x++ ) {
			ShapeBurrower.burrowRectange( xPos, yPos, xPos + width - 1, yPos + height - 1, floor, dungeon );

			if ( x == numRooms - 1 ) {
				break;
			}

			int startX = 0;
			int startY = 0;
			int constrainDirection = 0;

			switch ( Core.random.nextInt( 4 ) ) {
			case 0:
				startX = xPos;
				startY = Core.random.nextInt( height ) + yPos;
				constrainDirection = DrunkenWalker.RIGHT;
				break;

			case 1:
				startX = xPos + width - 1;
				startY = Core.random.nextInt( height ) + yPos;
				constrainDirection = DrunkenWalker.LEFT;
				break;

			case 2:
				startX = Core.random.nextInt( width ) + xPos;
				startY = yPos;
				constrainDirection = DrunkenWalker.UP;
				break;

			case 3:
				startX = Core.random.nextInt( width ) + xPos;
				startY = yPos + height - 1;
				constrainDirection = DrunkenWalker.DOWN;
				break;
			}

			int[] temp = DrunkenWalker.drunkenWalk( dungeon, floor, corridorLength, padding, startX, startY,
					constrainDirection );
			xPos = temp[0];
			yPos = temp[1];

			width = Core.random.nextInt( maxWidth - minWidth ) + minWidth;
			height = Core.random.nextInt( maxHeight - minHeight ) + minHeight;

			if ( xPos + width >= dungeon.length - padding ) {
				xPos = dungeon.length - padding - width;
			}

			if ( yPos + height >= dungeon[0].length - padding ) {
				yPos = dungeon[0].length - padding - height;
			}
		}
	}

}
