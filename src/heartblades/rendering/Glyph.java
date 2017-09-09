package heartblades.rendering;

import java.awt.Color;

public class Glyph {

	protected char glyph;
	protected Color color;
	protected Color backgroundColor;
	protected boolean drawBackground = true;

	public Glyph(char glyph, Color color) {
		this.glyph = glyph;
		this.color = color;
		this.backgroundColor = new Color(0, 0, 0, 0);
		drawBackground = false;
	}

	public Glyph(char glyph, Color color, Color backgroundColor) {
		this.glyph = glyph;
		this.color = color;
		this.backgroundColor = backgroundColor;
		
	}

	public void render(int x, int y) {
		if (drawBackground) {
			RenderingUtils.terminal.write(glyph, x, y, color, backgroundColor);
		} else {
			RenderingUtils.terminal.write(glyph, x, y, color);
		}
	}

	public Color getColor() {
		return color;
	}
	
	public Color getBackgroundColor(){
		return backgroundColor; 
	}
}
