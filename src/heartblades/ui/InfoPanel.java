package heartblades.ui;

import asciiPanel.AsciiPanel;
import heartblades.rendering.Glyph;
import heartblades.rendering.RenderingUtils;

public class InfoPanel extends UIElement {

	protected int width = 15; 
	
	Glyph border = new Glyph((char)186, AsciiPanel.brightWhite, AsciiPanel.black);
	
	public InfoPanel(){
		maxX = RenderingUtils.terminal.getWidthInCharacters();
		minX = maxX-width;
		
		maxY = RenderingUtils.terminal.getHeightInCharacters();
		minY = 0; 
	}

	@Override
	public void render() {
		for (int y=minY; y<maxY; y++){
			border.render(minX, y);
		}
	}
}
