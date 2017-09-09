package heartblades.screens;

import java.awt.event.KeyEvent;

import heartblades.factory.ActorFactory;
import heartblades.map.Dungeon;
import heartblades.procgen.ObjectPlacer;
import heartblades.rendering.RenderingUtils;
import heartblades.ui.InfoPanel;

public class DungeonScreen extends Screen {

	protected InfoPanel panel = new InfoPanel(); 
	protected Dungeon dungeon = new Dungeon(100,100);
	protected int rightPadding = 15; 
	

	public DungeonScreen(){
		dungeon.placeActorRandomly(ActorFactory.getPlayer());
		Dungeon dungeon2 = new Dungeon(100,100);
		ObjectPlacer.placeStairs(dungeon, dungeon2);
	}
	
	public DungeonScreen(Dungeon dungeon){
		this.dungeon = dungeon; 
	}

	@Override
	public void onTurn(KeyEvent e) {
		render();
		dungeon.onTurn(leftPadding, RenderingUtils.terminal.getWidthInCharacters() - rightPadding, upPadding, RenderingUtils.terminal.getHeightInCharacters() - downPadding, e);
	}

	@Override
	public void onOpen() {
	}

	@Override
	public void onClose() {
	}

	@Override
	public void render() {
		dungeon.render(leftPadding, RenderingUtils.terminal.getWidthInCharacters()-rightPadding, downPadding, RenderingUtils.terminal.getHeightInCharacters()-upPadding);
		panel.render();
	}

}
