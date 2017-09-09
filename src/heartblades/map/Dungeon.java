package heartblades.map;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import heartblades.actors.Actor;
import heartblades.core.Core;
import heartblades.factory.TileFactory;
import heartblades.movement.Direction;
import heartblades.movement.MovementTag;
import heartblades.movement.CircularRange;
import heartblades.procgen.DrunkenCorridors;
import heartblades.procgen.FloorActorGeneration;
import heartblades.rendering.Glyph;
import heartblades.rendering.RenderingUtils;

public class Dungeon {

	protected Tile[][] tiles;
	protected Actor[][] actors;
	protected PriorityQueue<Actor> turnOrder;

	protected int[] stairsUpLoc;
	protected int[] stairsDownLoc;
	protected Dungeon floorUp;
	protected Dungeon floorDown;
	protected Glyph stairsUpGlyph;
	protected Glyph stairsDownGlyph;

	public void setFloorUp( Dungeon floorUp, int[] stairsUpLoc, Glyph stairsUpGlyph ) {
		if ( floorUp == this ) {
			Core.debug( "Warning in setFloorUp!" );
		}
		this.floorUp = floorUp;
		this.stairsUpLoc = stairsUpLoc;
		this.stairsUpGlyph = stairsUpGlyph;
	}

	public void setFloorDown( Dungeon floorDown, int[] stairsDownLoc, Glyph stairsDownGlyph ) {
		if ( floorDown == this ) {
			Core.debug( "Warning in setFloorDown!" );
		}
		this.floorDown = floorDown;
		this.stairsDownLoc = stairsDownLoc;
		this.stairsDownGlyph = stairsDownGlyph;
	}

	public Dungeon( int width, int height ) {
		actors = new Actor[width][height];
		tiles = new Tile[width][height];
		stairsUpLoc = new int[] { -1, -1 };
		stairsDownLoc = new int[] { -1, -1 };
		for ( int x = 0; x < width; x++ ) {
			for ( int y = 0; y < height; y++ ) {
				tiles[x][y] = TileFactory.floor1Floor( );
			}
		}
		turnOrder = new PriorityQueue<Actor>( new SpeedComparator( ) );
		DrunkenCorridors.drunkenCorridors( tiles, TileFactory.floor1Wall( ), 2, 10, 25, 3, 5, 3, 5 );
		FloorActorGeneration.generateTestFloorActors( this );

	}

	public Dungeon( Tile[][] tiles ) {
		actors = new Actor[tiles.length][tiles[0].length];
		this.tiles = tiles;
		stairsUpLoc = new int[] { -1, -1 };
		stairsDownLoc = new int[] { -1, -1 };
	}

	public Actor getActorAt( int x, int y ) {
		return actors[x][y];
	}

	public Tile getTileAt( int x, int y ) {
		return tiles[x][y];
	}

	public void render( int minX, int maxX, int minY, int maxY ) {
		if ( Core.player != null ) {
			render( minX, maxX, minY, maxY, Core.player.getX( ), Core.player.getY( ) );
		}
		else {
			render( minX, maxX, minY, maxY, tiles.length / 2, tiles[0].length / 2 );
		}
	}

	public boolean placeActorRandomly( Actor a ) {

		for ( int i = 0; i < 1000; i++ ) {
			int x = Core.random.nextInt( tiles.length );
			int y = Core.random.nextInt( tiles[0].length );
			if ( placeAt( x, y, a ) ) {
				return true;
			}
		}

		for ( int x = 0; x < tiles.length; x++ ) {
			for ( int y = 0; y < tiles.length; y++ ) {
				if ( placeAt( x, y, a ) ) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean canPlaceStairs( int x, int y ) {
		return ((stairsUpLoc[0] != x || stairsUpLoc[1] != y) && (stairsDownLoc[0] != x || stairsUpLoc[1] != y)
				&& tiles[x][y].canWalk( new MovementTag[] { MovementTag.WALK } ));
	}

	public boolean placeAt( int x, int y, Actor a ) {

		if ( actors[x][y] != null ) {
			return false;
		}

		if ( tiles[x][y].canWalk( a.getWalkTypes( ) ) ) {
			actors[x][y] = a;
			a.setDungeon( this, x, y );
			turnOrder.add( a );
			return true;
		}

		return false;
	}

	public void onTurn( int minX, int maxX, int minY, int maxY, KeyEvent e ) {

		int TUs = Core.player.getTU( );

		boolean go = Core.player.onTurn( e );

		if ( go == false ) {
			return;
		}

		decreaseAllTUs( TUs );

		turnOrder.add( Core.player );

		Actor mover = null;
		while ( true ) {
			mover = turnOrder.remove( );

			if ( mover == null || mover == Core.player ) {
				break;
			}

			System.out.println( "Plant turn" );

			TUs = mover.getTU( );
			mover.onTurn( );
			decreaseAllTUs( TUs );

			turnOrder.add( mover );

			render( minX, maxX, minY, maxY );
			RenderingUtils.repaint( );
		}
	}

	protected void decreaseAllTUs( int TUs ) {
		List<Actor> actors = new ArrayList<Actor>( );
		while ( !turnOrder.isEmpty( ) ) {
			Actor actor = turnOrder.remove( );
			actor.decreaseTUs( TUs );
			actors.add( actor );
		}

		for ( Actor actor : actors ) {
			turnOrder.add( actor );
		}
	}

	public void render( int minX, int maxX, int minY, int maxY, int cx, int cy ) {
		int numRows = maxX - minX;
		int numCols = maxY - minY;

		int sx = cx - numRows / 2;
		int sy = cy - numCols / 2;

		for ( int x = sx; x < sx + numRows; x++ ) {
			if ( x < 0 ) {
				x = 0;
			}
			if ( x >= tiles.length ) {
				break;
			}
			for ( int y = sy; y < sy + numCols; y++ ) {
				if ( y < 0 ) {
					y = 0;
				}

				if ( y >= tiles[0].length ) {
					break;
				}

				if ( isShadow( x, y ) && shouldRenderShadow( x, y ) ) {
					tiles[x][y].render( true, x - sx, y - sy );
					if ( stairsDownLoc[0] == x && stairsDownLoc[1] == y ) {
						stairsDownGlyph.render( x - sx, y - sy );
					}
					if ( stairsUpLoc[0] == x && stairsUpLoc[1] == y ) {
						stairsUpGlyph.render( x - sx, y - sy );
					}
				}

				else if ( !isShadow( x, y ) ) {
					tiles[x][y].render( false, x - sx, y - sy );
					if ( stairsDownLoc[0] == x && stairsDownLoc[1] == y ) {
						stairsDownGlyph.render( x - sx, y - sy );
					}
					if ( stairsUpLoc[0] == x && stairsUpLoc[1] == y ) {
						stairsUpGlyph.render( x - sx, y - sy );
					}
					if ( actors[x][y] != null ) {
						actors[x][y].render( x - sx, y - sy );
					}
				}
			}
		}
	}

	private boolean shouldRenderShadow( int x, int y ) {
		return Core.player.tileInMemory( this, x, y );
	}

	public boolean isShadow( int x, int y ) {
		CircularRange range = Core.player.getRange( );
		for ( int[] point : range.getRange( ) ) {
			if ( point[0] == x && point[1] == y ) {
				return false;
			}
		}
		return true;
	}

	public boolean moveActor( Actor actor, int dx, int dy ) {
		if ( Math.abs( dx ) > 1 || Math.abs( dy ) > 1 ) {
			return false;
		}

		if ( actors[actor.getX( ) + dx][actor.getY( ) + dy] != null ) {
			return false;
		}

		actors[actor.getX( )][actor.getY( )] = null;
		actors[actor.getX( ) + dx][actor.getY( ) + dy] = actor;
		actor.inc( dx, dy );
		return true;
	}

	public void placeAtForce( Actor actor, int posX, int posY ) {
		Actor move = actors[posX][posY];
		actors[posX][posY] = actor;
		actor.setPos( posX, posY );

		if ( move != null ) {
			placeAtForce( move, posX + Core.random.nextInt( 3 ) - 1, posY + Core.random.nextInt( 3 ) - 1 );
		}
	}

	public int[] getStairsDownLoc( ) {
		return stairsDownLoc;
	}

	public int[] getStairsUpLoc( ) {
		return stairsUpLoc;
	}

	public boolean useStairs( int direction, Actor actor ) {

		if ( direction == Direction.UP ) {
			if ( stairsUpLoc[0] < 0 || stairsUpLoc[1] < 0 || stairsUpLoc[0] > actors.length
					|| stairsUpLoc[1] > actors[0].length ) {
				return false;
			}

			Actor move = actors[stairsUpLoc[0]][stairsUpLoc[1]];
			if ( move != actor ) {
				return false;
			}

			floorUp.placeAtForce( move, floorUp.getStairsDownLoc( )[0], floorUp.getStairsDownLoc( )[1] );
			actors[stairsUpLoc[0]][stairsUpLoc[1]] = null;
			move.setDungeon( floorUp, floorUp.getStairsDownLoc( )[0], floorUp.getStairsDownLoc( )[1] );
			return true;

		}
		else {
			if ( stairsDownLoc[0] < 0 || stairsDownLoc[1] < 0 || stairsDownLoc[0] > actors.length
					|| stairsDownLoc[1] > actors[0].length ) {
				return false;
			}

			Actor move = actors[stairsDownLoc[0]][stairsDownLoc[1]];

			if ( move != actor ) {
				return false;
			}

			floorDown.placeAtForce( move, floorDown.getStairsUpLoc( )[0], floorDown.getStairsUpLoc( )[1] );
			actors[stairsDownLoc[0]][stairsDownLoc[1]] = null;
			move.setDungeon( floorDown, floorDown.getStairsUpLoc( )[0], floorDown.getStairsUpLoc( )[1] );
			return true;
		}
	}

	public Tile[][] getTiles( ) {
		return tiles;
	}

}
