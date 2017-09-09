package heartblades.factory;

import heartblades.map.Tile;
import heartblades.movement.MovementTag;

public class TileFactory {

	public static Tile floor1Floor(){
		return new Tile(GlyphFactory.zone1FloorBright(), GlyphFactory.zone1FloorDark(), MovementTag.land);
	}

	public static Tile floor1Wall(){
		return new Tile(GlyphFactory.zone1WallBright(), GlyphFactory.zone1WallDark(), MovementTag.stone);
	}
}
