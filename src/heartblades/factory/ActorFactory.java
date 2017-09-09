package heartblades.factory;

import asciiPanel.AsciiPanel;
import heartblades.actors.Actor;
import heartblades.actors.ActorTeam;
import heartblades.actors.AI.PlantAI;
import heartblades.actors.AI.PlayerAI;
import heartblades.core.Core;
import heartblades.movement.MovementTag;
import heartblades.rendering.Glyph;

public class ActorFactory {

	public static Actor getPlayer( ) {
		if ( Core.player != null ) {
			return Core.player;
		}

		Core.player = new Actor( new PlayerAI( ), new Glyph( '@', AsciiPanel.brightWhite ), 100, 6, ActorTeam.PLAYER );
		return Core.player;
	}

	public static Actor getBush( ) {
		return new Actor( new PlantAI( ), new Glyph( 'v', AsciiPanel.brightGreen ),
				new MovementTag[] { MovementTag.IMMOBILE }, 10, 6, ActorTeam.PLANT );
	}

}
