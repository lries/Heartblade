package heartblades.screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;
import heartblades.core.Core;
import heartblades.rendering.GlyphString;
import heartblades.rendering.Menu;
import heartblades.rendering.RenderingUtils;

public abstract class MenuScreen extends Screen {

	protected Menu menu;

	public MenuScreen( ) {
		this.menu = new Menu( new GlyphString[0] );
	}

	public MenuScreen( Menu menu ) {
		this.menu = menu;
	}

	public MenuScreen( List<String> strings ) {
		List<GlyphString> entries = new ArrayList<GlyphString>( );
		for ( String string : strings ) {
			entries.add( new GlyphString( AsciiPanel.brightWhite, AsciiPanel.black, string ) );
		}
	}

	@Override
	public void onTurn( KeyEvent e ) {
		Core.log.log( e );

		if ( e.getKeyCode( ) == KeyEvent.VK_UP ) {
			menu.decrementPointer( );
		}

		if ( e.getKeyCode( ) == KeyEvent.VK_DOWN ) {
			menu.incrementPointer( );
		}

		if ( e.getKeyCode( ) == KeyEvent.VK_ENTER ) {
			onSelect( menu.getPointer( ) );
		}
	}

	@Override
	public void onOpen( ) {

	}

	@Override
	public void onClose( ) {

	}

	@Override
	public void render( ) {
		menu.render( leftPadding, RenderingUtils.terminal.getWidthInCharacters( ) - rightPadding, upPadding,
				RenderingUtils.terminal.getHeightInCharacters( ) - downPadding );

	}

	public abstract void onSelect( int option );

}
