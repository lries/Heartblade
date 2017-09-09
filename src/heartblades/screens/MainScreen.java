package heartblades.screens;

import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import heartblades.rendering.GlyphString;
import heartblades.rendering.Menu;

public class MainScreen extends MenuScreen {

	public MainScreen(){
		List<String> strings = new ArrayList<String>();
		strings.add("New Game");
		strings.add("Load Game");
		strings.add("The Master's Writings");

		GlyphString[] entries = new GlyphString[3];
		for (int x=0; x<3; x++){
			entries[x] = (new GlyphString(AsciiPanel.brightWhite, AsciiPanel.black, strings.get(x)));
		}
		
		this.menu = new Menu(entries); 
	}
	
}
