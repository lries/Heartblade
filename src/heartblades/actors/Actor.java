package heartblades.actors;

import java.awt.event.KeyEvent;

import heartblades.actors.AI.AI;
import heartblades.map.Dungeon;
import heartblades.movement.Direction;
import heartblades.movement.MovementTag;
import heartblades.movement.CircularRange;
import heartblades.rendering.Glyph;

public class Actor {

	protected AI ai;
	protected Glyph glyph;
	protected int posX;
	protected int posY;
	protected int vision;
	protected Dungeon dungeon;
	protected CircularRange range;
	protected MovementTag[] walkTypes;
	protected Memory memory;
	protected int turns, speed;
	protected ActorTeam team; 

	public Actor( AI ai, Glyph glyph, int speed, int vision, ActorTeam team ) {
		this( ai, glyph, new MovementTag[] { MovementTag.WALK }, speed, vision, team ); 
	}

	public Actor( AI ai, Glyph glyph, MovementTag[] walkTypes, int speed, int vision, ActorTeam team ) {
		this.ai = ai;
		this.glyph = glyph;
		this.walkTypes = walkTypes;
		this.speed = speed;
		this.turns = speed;
		this.vision = vision;
		this.range = new CircularRange( dungeon, new MovementTag[] { MovementTag.LIGHT }, 0, 0, vision );
		this.memory = new Memory( );
		this.team = team;
		ai.setHolder( this );
	}

	public boolean isPlayer( ) {
		return ai.isPlayer( );
	}

	public void render( int x, int y ) {
		glyph.render( x, y );
	}

	public int getX( ) {
		return posX;
	}

	public int getY( ) {
		return posY;
	}

	public MovementTag[] getWalkTypes( ) {
		return walkTypes;
	}

	public void setDungeon( Dungeon dungeon, int x, int y ) {
		this.dungeon = dungeon;
		this.posX = x;
		this.posY = y;
		this.range.setDungeon( dungeon );
		this.range.setPosition( posX, posY );
	}

	public int getTU( ) {
		return turns;
	}

	public void onTurn( ) {
		ai.onTurn( );
		turns += speed;
	}

	public boolean onTurn( KeyEvent e ) {
		if ( ai.onTurn( e ) ) {
			turns += speed; 
			return true; 
		}
		return false; 
	}
	
	public void decreaseTUs( int tu ) {
		turns -= tu;
	}

	public boolean move( int direction ) {

		int dx = Direction.getDx( direction );
		int dy = Direction.getDy( direction );

		if ( dungeon.getActorAt( posX + dx, posY + dy ) != null ) {
			return ai.onActorCollision( dungeon.getActorAt( posX + dx, posY + dy ) );
		}

		if ( !dungeon.getTileAt( posX + dx, posY + dy ).canWalk( walkTypes ) ) {
			return ai.onWallCollision( posX + dx, posY + dy );
		}

		return dungeon.moveActor( this, dx, dy );
	}

	public void inc( int dx, int dy ) {
		posX += dx;
		posY += dy;
		range.setPosition( posX, posY );
	}

	public void setPos( int posX, int posY ) {
		this.posX = posX;
		this.posY = posY;
		range.setPosition( posX, posY );
	}

	public boolean useStairs( int direction ) {
		return dungeon.useStairs( direction, this );
	}

	public Dungeon getDungeon( ) {
		return dungeon;
	}

	public CircularRange getRange( ) {
		for ( int[] point : range.getRange( ) ) {
			memory.learnTile( dungeon, point[0], point[1] );
		}
		return range;
	}

	public boolean tileInMemory( Dungeon dungeon2, int x, int y ) {
		return memory.knowsTile( dungeon2, x, y );
	}

	public ActorTeam getTeam( ) {
		return team;
	}
}
