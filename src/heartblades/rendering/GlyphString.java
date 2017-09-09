package heartblades.rendering;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GlyphString {
	
	protected List<Glyph> string; 
	
	public GlyphString(Color color, Color backgroundColor, String text){
		string = new ArrayList<Glyph>();
		int length = text.length();
		for (int x=0; x<length; x++){
			string.add(new Glyph(text.charAt(x), color, backgroundColor));
		}
	}
	
	public int length() {
		return string.size();
	}
	
	public void insert(Glyph g, int pos){
		if (pos > length()){
			pos = length(); 
		}
		string.add(pos, g);
	}
	
	public void add(Glyph g){
		string.add(g);
	}
	
	public void append(Glyph g){
		string.add(length(), g);
	}

	public void render(int minX, int maxX, int y){
		int length = length(); 
		
		for (int x=0; x<=maxX-minX; x++){
			if (x >= length) {
				return; 
			}
			string.get(x).render(x+minX, y);
		}
	}
	
	/**********************************
	 * Render with word wrap
	 * @return the number of lines used
	 **********************************/
	public int render(int minX, int maxX, int minY, int maxY) {
		int length = length(); 
		
		if (maxX < minX || maxY < minY){
			return 0; 
		}
		
		for (int y=0; y<=maxY-minY; y++){
			 for (int x=0; x<=maxX-minX; x++){
				 if (y*(maxX-minX)+x > length) {
					 return y+1; 
				 }
				 
				 string.get(y*(maxX-minX)+x).render(x+minX, y+minY);
			 }
		}
		
		return maxY-minY + 1; 
	}
	
	
	
}
