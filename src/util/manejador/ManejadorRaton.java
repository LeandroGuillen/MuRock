package util.manejador;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import murock.Juego;

public class ManejadorRaton extends MouseInputAdapter {
	private Juego juego;

	public ManejadorRaton(Juego j) {
		juego = j;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		juego.setMensajeEstado("Has hecho click en (" + e.getX() + ", "
				+ e.getY() + ")");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
