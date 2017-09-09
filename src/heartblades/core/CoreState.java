package heartblades.core;

import java.io.Serializable;
import java.util.Random;

import heartblades.input.KeyLog;

public class CoreState implements Serializable {

	private static final long serialVersionUID = -2523445711815024127L;
	KeyLog log; 
	Random random; 
	
	public CoreState() {
		log = Core.log;
		random = Core.random; 
	}
	
	public void load(){
		Core.log = log;
		Core.random = random; 
	}
	
}
