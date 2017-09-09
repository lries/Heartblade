package heartblades.movement;

import java.util.ArrayList;
import java.util.List;

import heartblades.map.Dungeon;
import heartblades.map.Shape;

public class CircularRange {

	protected List<int[]> points;
	protected Dungeon dungeon;
	protected MovementTag[] movement;
	protected int cx;
	protected int cy;
	protected int radius;

	public CircularRange( Dungeon dungeon, MovementTag[] movement, int cx, int cy, int radius ) {
		this.dungeon = dungeon;
		this.movement = movement;
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
		calculateRange( );
	}

	public void setPosition( int x, int y ) {
		if ( cx == x && cy == y ) {
			return;
		}
		cx = x;
		cy = y;
		calculateRange( );
	}

	public List<int[]> getRange( ) {
		return points;
	}

	protected void calculateRange( ) {
		if ( dungeon == null ) {
			return;
		}
		points = new ArrayList<int[]>( );
		for ( int x = Math.max( 0, cx - radius ); x <= Math.min( dungeon.getTiles( ).length - 1, cx + radius ); x++ ) {
			for ( int y = Math.max( 0, cy - radius ); y <= Math.min( dungeon.getTiles( )[0].length - 1,
					cy + radius ); y++ ) {

				float dist = (x - cx) * (x - cx) + (y - cy) * (y - cy);

				if ( dist > radius * radius ) {
					continue;
				}

				List<int[]> line = Shape.getLine( cx, cy, x, y );

				for ( int[] point : line ) {
					if ( !dungeon.getTileAt( point[0], point[1] ).canWalk( movement ) ) {
						points.add( point );
						break;
					}
					if ( point[0] == x && point[1] == y ) {
						points.add( point );
					}
				}
			}
		}
	}

	public void setDungeon( Dungeon dungeon ) {
		System.out.println( "Setting dungeon" );
		this.dungeon = dungeon;
		calculateRange( );
	}

}
