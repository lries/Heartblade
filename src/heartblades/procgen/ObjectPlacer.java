package heartblades.procgen;

import heartblades.actors.Actor;
import heartblades.core.Core;
import heartblades.map.Dungeon;
import heartblades.map.Tile;
import heartblades.rendering.Glyph;

public class ObjectPlacer {

	public static void placeStairs( Dungeon upD, Dungeon downD ) {

		Tile[][] up = upD.getTiles( );
		Tile[][] down = downD.getTiles( );
		int posXUp = -1;
		int posYUp = -1;
		int posXDown = -1;
		int posYDown = -1;

		do {
			posXUp = Core.random.nextInt( up.length );
			posYUp = Core.random.nextInt( up[0].length );
		}
		while ( !upD.canPlaceStairs( posXUp, posYUp ) );

		do {
			posXDown = Core.random.nextInt( up.length );
			posYDown = Core.random.nextInt( up[0].length );
		}
		while ( !downD.canPlaceStairs( posXDown, posYDown ) );

		upD.setFloorDown( downD, new int[] { posXUp, posYUp },
				new Glyph( '>', up[posXUp][posYUp].getGlyph( ).getColor( ) ) );
		downD.setFloorUp( upD, new int[] { posXDown, posYDown },
				new Glyph( '<', down[posXDown][posYDown].getGlyph( ).getColor( ) ) );

		Core.debug( posXUp + " " + posYUp );

	}

	public static void placeActor( Actor actor, Dungeon dungeon ) {
		int x = -1;
		int y = -1;

		do {
			x = Core.random.nextInt( dungeon.getTiles( ).length );
			y = Core.random.nextInt( dungeon.getTiles( )[x].length );
		}
		while ( !dungeon.placeAt( x, y, actor ) );


	}

}
