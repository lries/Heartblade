package heartblades.actors;

import java.util.HashMap;
import java.util.Map;

import heartblades.map.Dungeon;

public class Memory {

	protected Map<Dungeon, boolean[][]> memory;
	
	public Memory(){
		this.memory = new HashMap<Dungeon, boolean[][]>(); 
	}
	
	public void learnTile(Dungeon dungeon, int x, int y){
		if (memory.containsKey(dungeon)){
			boolean[][] remembered = memory.get(dungeon);
			if (x >= 0 && y >= 0 && x < remembered.length && y < remembered[0].length) {
				remembered[x][y] = true; 
			}
		}
		else {
			boolean[][] add = new boolean[dungeon.getTiles().length][dungeon.getTiles()[0].length]; 
			memory.put(dungeon, add);
			learnTile(dungeon, x, y); 
		}
	}
	
	public boolean knowsTile(Dungeon dungeon, int x, int y){
		if (memory.containsKey(dungeon)){
			boolean[][] remembered = memory.get(dungeon);
			if (x >= 0 && y >= 0 && x < remembered.length && y < remembered[0].length) {
				return remembered[x][y]; 
			}
		}
		return false; 	
	}
}
