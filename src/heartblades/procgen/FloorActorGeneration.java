package heartblades.procgen;

import heartblades.actors.Actor;
import heartblades.core.Core;
import heartblades.factory.ActorFactory;
import heartblades.map.Dungeon;

public class FloorActorGeneration {

	public static void generateTestFloorActors( Dungeon dungeon ) { 
		for ( int x = 0; x < 20; x++ ) { 
			Actor bush = ActorFactory.getBush( );
			Core.debug(" placing actor ");
			ObjectPlacer.placeActor( bush, dungeon );
		}
	}
	
}
