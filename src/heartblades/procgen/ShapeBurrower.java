package heartblades.procgen;

import java.util.List;

import heartblades.map.Shape;
import heartblades.map.Tile;

public class ShapeBurrower {

	public static void burrowRectange(int minX, int minY, int maxX, int maxY, Tile set, Tile[][] map){
		List<int[]> rect = Shape.getRectangle(minX, minY, maxX, maxY);
		for (int[] position : rect){
			map[position[0]][position[1]] = set; 
		}
	}
	
}
