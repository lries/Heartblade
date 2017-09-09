package heartblades.actors.AI;

import java.awt.event.KeyEvent;

import heartblades.actors.Actor;
import heartblades.core.Core;

public abstract class AI {

	protected Actor holder;

	public boolean isPlayer( ) {
		return false;
	}

	public void setHolder( Actor holder ) {
		this.holder = holder;
	}

	public abstract void onTurn( );

	public boolean onTurn( KeyEvent e ) {
		Core.debug( "WARNING: Non-player KeyEvent turn" );
		onTurn( );
		return true;
	}

	public boolean onWallCollision( int i, int j ) {
		return false;
	}

	public boolean onActorCollision( Actor actor ) {
		return false;
	}
	
	public abstract boolean isAggressiveTo( Actor actor );
	
	public boolean hunt( Actor actor ) { 
		// TODO Not yet implemented 
		return false; 
	}
	
	public void wander( ) { 
		// TODO Not yet implemented
	}

}
