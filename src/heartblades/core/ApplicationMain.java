package heartblades.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import heartblades.rendering.RenderingUtils;
import heartblades.screens.MainScreen;

public class ApplicationMain extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1060623638149583738L;

	private AsciiPanel terminal;

	private long timeSinceLastKeyEvent = 1000000;
	private long timeOfLastKeyEvent = 0;

	public ApplicationMain( ) {
		super( );
		RenderingUtils.setApplicationMain( this );
		terminal = new AsciiPanel( 60, 41, AsciiFont.CP437_16x16 );
		RenderingUtils.terminal = terminal;
		add( terminal );
		pack( );
		RenderingUtils.setScreen( new MainScreen( ) );
		addKeyListener( this );
		repaint( );
	}

	@Override
	public void repaint( ) {
		System.out.println( "repainting" );
		terminal.clear( );
		RenderingUtils.getScreen( ).render( );
		super.repaint( );

	}

	@Override
	public void keyPressed( KeyEvent e ) {
		timeSinceLastKeyEvent = System.currentTimeMillis( ) - timeOfLastKeyEvent;
		if ( timeSinceLastKeyEvent < 100 ) {
			System.out.println( timeSinceLastKeyEvent );
			return;
		}
		timeOfLastKeyEvent = System.currentTimeMillis( );
		RenderingUtils.getScreen( ).onTurn( e );
		Core.log.log( e );
		repaint( );
	}

	@Override
	public void keyReleased( KeyEvent e ) {
	}

	@Override
	public void keyTyped( KeyEvent e ) {
	}

	public static void main( String[] args ) {
		ApplicationMain app = new ApplicationMain( );
		app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		app.setVisible( true );
	}
}