package heartblades.rendering;

import asciiPanel.AsciiPanel;

public class Menu {

	protected GlyphString[] entries; 
	protected Glyph indicator = new Glyph('>', AsciiPanel.brightWhite, AsciiPanel.black);
	protected int pointer; 
	protected int start;
	
	public Menu(GlyphString[] entries){
		this.entries = entries; 
	}

	public void incrementPointer() {
		pointer++; 
		bindPointer(); 
	}
	
	public void decrementPointer() {
		pointer--;
		bindPointer(); 
	}

	public int getPointer() {
		return pointer; 
	}
	
	protected void bindPointer() { 
		if (pointer < 0) {
			pointer = entries.length - 1; 
		}
		if (pointer > entries.length - 1) { 
			pointer = 0; 
		}
	}
	
	public void render(int minX, int maxX, int minY, int maxY){
		int numLines = maxY - minY; 

		if (pointer < start) { 
			start = pointer; 
		}
		
		if (pointer > start + numLines){
			start = pointer - numLines; 
		}
		
		int pos = start; 
		//Render visible lines
		for (int y = minY; y <= maxY;  y++){
			if (pos >= entries.length) break; 
			if (pos == pointer){
				indicator.render(minX, y);
			}

			entries[pos].render(minX+1, maxX, y);

			pos++;
		}
	}
}
