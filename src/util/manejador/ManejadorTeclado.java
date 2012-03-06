package util.manejador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import murock.Juego;

/**
 * A class to handle keyboard input from the user. The class handles both
 * dynamic input during game play, i.e. left/right and shoot, and more static
 * type input (i.e. press any key to continue) This has been implemented as an
 * inner class more through habbit then anything else. Its perfectly normal to
 * implement this as seperate class if slight less convienient.
 * 
 * @author Kevin Glass
 */
public class ManejadorTeclado extends KeyAdapter {
	private Juego juego;
	
	public ManejadorTeclado(Juego j) {
		juego=j;
	}
	/**
	 * Notification from AWT that a key has been pressed. Note that a key being
	 * pressed is equal to being pushed down but *NOT* released. Thats where
	 * keyTyped() comes in.
	 * 
	 * @param e The details of the key that was pressed
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				break;
			case KeyEvent.VK_SPACE:
				break;
			case KeyEvent.VK_P: // Tecla P pausa el juego
				juego.pause();
				break;
		}
	}

	/**
	 * Notification from AWT that a key has been released.
	 * 
	 * @param e The details of the key that was released
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

		}
	}

	/**
	 * Notification from AWT that a key has been typed. Note that typing a key
	 * means to both press and then release it.
	 * 
	 * @param e The details of the key that was typed.
	 */
	public void keyTyped(KeyEvent e) {
		// if we hit escape, then quit the game
		if (e.getKeyChar() == 27) {
			System.exit(0);
		}
	}
}