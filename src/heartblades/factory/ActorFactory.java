package heartblades.factory;

import asciiPanel.AsciiPanel;
import heartblades.actors.Actor;
import heartblades.actors.AI.PlayerAI;
import heartblades.core.Core;
import heartblades.rendering.Glyph;

public class ActorFactory {

	public static Actor getPlayer() {
		if (Core.player != null) {
			return Core.player;
		}
		
		Core.player = new Actor(new PlayerAI(), new Glyph('@', AsciiPanel.brightWhite), 100, 6);
		return Core.player; 
	}
	
}
