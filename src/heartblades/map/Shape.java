package heartblades.map;

import java.util.ArrayList;
import java.util.List;

public class Shape {

	public static List<int[]> getRectangle(int sx, int sy, int gx, int gy) {
		List<int[]> rect = new ArrayList<int[]>();

		if (sx > gx) {
			int temp = sx;
			sx = gx;
			gx = temp;
		}

		if (sy > gy) {
			int temp = gy;
			sy = gy;
			gy = temp;
		}

		for (int x = sx; x < gx; x++) {
			for (int y = sy; y < gy; y++) {
				rect.add(new int[] { x, y });
			}
		}

		return rect;
	}
	
	public static List<int[]> getCircle(int x, int y, int r) {
		List<int[]> ret = new ArrayList<int[]>();
		
		for ( int x2 = x-r; x <= x+r; x++ ) { 
			for ( int y2 = y-r; y <= y+r; y++ ) {
				if ( x2*x2 + y2*y2 <= r*r ) {
					ret.add(new int[]{x2,y2});
				}
			}
		}
		
		return ret; 
	}
	
	public static List<int[]> getEllipse(int x, int y, int rx, int ry) { 
		List<int[]> ret = new ArrayList<int[]>();
		
		for ( int x2 = x-rx; x <= x+rx; x++ ) { 
			for ( int y2 = y-ry; y <= y+ry; y++ ) {
				if ( x2*x2/(rx*rx) + y2*y2/(ry*ry) <= 1 ) {
					ret.add(new int[]{x2,y2});
				}
			}
		}
		
		return ret; 
		
	}

	public static List<int[]> getLine(int x, int y, int x2, int y2) {
		List<int[]> ret = new ArrayList<int[]>();
		int w = x2 - x;
		int h = y2 - y;
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;

		if (w < 0) {
			dx1 = -1;
		} else if (w > 0) {
			dx1 = 1;
		}

		if (h < 0) {
			dy1 = -1;
		} else if (h > 0) {
			dy1 = 1;
		}

		if (w < 0) {
			dx2 = -1;
		} else if (w > 0) {
			dx2 = 1;
		}

		int longest = Math.abs(w);
		int shortest = Math.abs(h);
		if (!(longest > shortest)) {
			longest = Math.abs(h);
			shortest = Math.abs(w);
			if (h < 0) {
				dy2 = -1;
			} else if (h > 0) {
				dy2 = 1;
			}
			dx2 = 0;
		}
		int numerator = longest >> 1;
		for (int i = 0; i <= longest; i++) {
			int[] loc = { x, y };
			ret.add(loc);
			numerator += shortest;
			if (!(numerator < longest)) {
				numerator -= longest;
				x += dx1;
				y += dy1;
			} else {
				x += dx2;
				y += dy2;
			}
		}
		return ret;
	}
}
