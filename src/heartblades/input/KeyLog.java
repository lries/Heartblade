package heartblades.input;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import heartblades.core.Core;
import heartblades.rendering.RenderingUtils;

public class KeyLog implements Serializable {

	private static final long serialVersionUID = 6694162315102139662L;
	protected List<KeyEvent> log;
	
	public KeyLog(){
		log = new ArrayList<KeyEvent>(); 
	}
	
	public void log(KeyEvent e) {
		log.add(e); 
	} 
	
	public void replay(){
		if (Core.log == this) {
			Core.log = new KeyLog(); 
		}
		
		int length = log.size();
		
		for (int x=length-1; x>=0; x--){
			RenderingUtils.getScreen().onTurn(log.get(x));
		}
	}
}
