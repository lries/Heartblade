package heartblades.actors.AI;

import java.awt.event.KeyEvent;

import heartblades.actors.Actor;

public class AI {
	
	protected Actor holder;

	public boolean isPlayer() {
		return false;
	} 
	
	public void setHolder(Actor holder){
		this.holder = holder; 
	}

	public void onTurn() {
		
		
	}
	
	public boolean onTurn(KeyEvent e){
		System.out.println("WARNING: Non-player KeyEvent turn");
		onTurn(); 
		return true; 
	}
	
	public boolean onWallCollision(int i, int j){
		return false; 
	}
	
	public boolean onActorCollision(Actor actor){
		return false; 
	}

}
