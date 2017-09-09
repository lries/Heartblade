package heartblades.map;

import heartblades.movement.MovementTag;
import heartblades.rendering.Glyph;

public class Tile {

	protected Glyph glyph; 
	protected Glyph shadow; 
	protected MovementTag[] walkable;
	
	public Tile(Glyph glyph, Glyph shadow){
		this.glyph = glyph;
		this.shadow = shadow;
		this.walkable = MovementTag.land;
	}
	
	public Tile(Glyph glyph, Glyph shadow, MovementTag[] walkable){
		this.glyph = glyph; 
		this.shadow = shadow;
		this.walkable = walkable; 
	}
	
	public boolean canWalk(MovementTag[] walk){
		for (MovementTag tag: walkable){
			for (MovementTag tag2: walk){
				if (tag == tag2) return true; 
			}
		}
		return false; 
	}
	
	public void render(boolean shadow, int x, int y){
		if (shadow && this.shadow != null){
			this.shadow.render(x, y);
		}
		else {
			glyph.render(x, y);
		}
	}

	public Glyph getGlyph() {
		return glyph;
	}
	
}
