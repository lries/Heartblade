package heartblades.map;

import java.util.Comparator;

import heartblades.actors.Actor;

public class SpeedComparator implements Comparator<Actor> {
	@Override
	public int compare( Actor x, Actor y ) {
		if ( y == null ) { 
			return -1; 
		}
		if ( x == null ) { 
			return 1; 
		}
		if ( x.getTU( ) < y.getTU( ) ) {
			return -1;
		}
		if ( x.getTU( ) > y.getTU( ) ) {
			return 1;
		}
		return 0;
	}
}
