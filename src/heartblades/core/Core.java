package heartblades.core;

import java.util.Random;

import heartblades.actors.Actor;
import heartblades.input.KeyLog;

public class Core {

	public static KeyLog log = new KeyLog( );

	public static Random random = new Random( ( long ) (1000000 * Math.random( )) );

	public static Actor player;
	
	public static boolean debug = true; 
	
	public static void debug( String string ) {
		if ( debug ) {
			System.out.println( string );
		}
	}

}
