package heartblades.factory;

import java.awt.Color;

import heartblades.core.Core;
import heartblades.rendering.Glyph;

public class GlyphFactory {

	public static Glyph zone1FloorBright(){
		
		double rVar = (Core.random.nextDouble()-0.5)*10;
		double gVar = (Core.random.nextDouble()-0.5)*10;
		double bVar = (Core.random.nextDouble()-0.5)*10;
		
		Color color = new Color(bind(rVar+FloorColor.FLOOR_1_RED), bind(gVar+FloorColor.FLOOR_1_GREEN), bind(bVar+FloorColor.FLOOR_1_BLUE));
		
		return new Glyph('.', color);
	}

	public static Glyph zone1FloorDark(){
		
		double rVar = (Core.random.nextDouble()-0.5)*10;
		double gVar = (Core.random.nextDouble()-0.5)*10;
		double bVar = (Core.random.nextDouble()-0.5)*10;
		
		Color color = new Color(bind(rVar+FloorColor.FLOOR_1_RED_SHADOW), bind(gVar+FloorColor.FLOOR_1_GREEN_SHADOW), bind(bVar+FloorColor.FLOOR_1_BLUE_SHADOW));
		
		return new Glyph('.', color);
	}

	public static Glyph zone1WallBright(){
		
		double rVar = (Core.random.nextDouble()-0.5)*10;
		double gVar = (Core.random.nextDouble()-0.5)*10;
		double bVar = (Core.random.nextDouble()-0.5)*10;
		
		Color color = new Color(bind(rVar+FloorColor.WALL_1_RED), bind(gVar+FloorColor.WALL_1_GREEN), bind(bVar+FloorColor.WALL_1_BLUE));
		
		return new Glyph('#', color);
	}

	public static Glyph zone1WallDark(){
		
		double rVar = (Core.random.nextDouble()-0.5)*10;
		double gVar = (Core.random.nextDouble()-0.5)*10;
		double bVar = (Core.random.nextDouble()-0.5)*10;
		
		Color color = new Color(bind(rVar+FloorColor.WALL_1_RED_SHADOW), bind(gVar+FloorColor.WALL_1_GREEN_SHADOW), bind(bVar+FloorColor.WALL_1_BLUE_SHADOW));
		
		return new Glyph('#', color);
	}
	
	private static int bind(double d){
		if (d < 0){
			return 0;
		}
		else if (d > 255){
			return 255;
		}
		return (int) d; 
	}
	
}
