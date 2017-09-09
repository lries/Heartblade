package heartblades.screens;

import java.awt.event.KeyEvent;

public abstract class Screen {

	protected int upPadding = 0, downPadding = 0, leftPadding = 0, rightPadding = 0;

	public abstract void onTurn( KeyEvent e );

	public abstract void onOpen( );

	public abstract void onClose( );

	public abstract void render( );

}
