package heartblades.actors.AI;

import java.awt.event.KeyEvent;

import heartblades.core.Core;
import heartblades.movement.Direction;
import heartblades.rendering.RenderingUtils;
import heartblades.screens.DungeonScreen;

public class PlayerAI extends AI {
	
	@Override
	public boolean isPlayer(){ 
		return true; 
	}
	
	@Override
	public void onTurn() { 
		Core.debug("Player turn");
	}
	
	@Override
	public boolean onTurn(KeyEvent e){
		if (e.getKeyCode() ==  KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
			return holder.move(Direction.UP);
		}
		if (e.getKeyCode() ==  KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
			return holder.move(Direction.DOWN);
		}
		if (e.getKeyCode() ==  KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
			return holder.move(Direction.LEFT);
		}
		if (e.getKeyCode() ==  KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
			return holder.move(Direction.RIGHT);
		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
			return holder.move(Direction.RIGHT_UP);
		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
			return holder.move(Direction.RIGHT_DOWN);
		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
			return holder.move(Direction.LEFT_UP);
		}
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
			return holder.move(Direction.LEFT_DOWN);
		}
		if (e.getKeyChar() == '<') {
			boolean usedStairs = holder.useStairs(Direction.UP); 
			if (usedStairs){
				RenderingUtils.setScreen(new DungeonScreen(holder.getDungeon()));
			}
			return usedStairs;
		}
		if (e.getKeyChar() == '>') {
			boolean usedStairs = holder.useStairs(Direction.DOWN);
			if (usedStairs){
				RenderingUtils.setScreen(new DungeonScreen(holder.getDungeon()));
			}
			return usedStairs;
		}	
		
		return false; 
	}
	
}
