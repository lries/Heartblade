package heartblades.rendering;

import asciiPanel.AsciiPanel;
import heartblades.core.ApplicationMain;
import heartblades.screens.Screen;

public class RenderingUtils {

	public static AsciiPanel terminal;

	protected static Screen activeScreen;

	public static Screen setScreen( Screen screen ) {
		if ( activeScreen != null ) {
			activeScreen.onClose( );
		}
		Screen oldScreen = activeScreen;
		screen.onOpen( );
		activeScreen = screen;
		return oldScreen;
	}

	public static Screen getScreen( ) {
		return activeScreen;
	}

	protected static ApplicationMain main;

	public static void repaint( ) {
		main.repaint( );
	}

	public static void setApplicationMain( ApplicationMain applicationMain ) {
		main = applicationMain;

	}
}
