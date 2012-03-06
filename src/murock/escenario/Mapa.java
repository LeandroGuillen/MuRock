package murock.escenario;

import java.util.LinkedList;
import java.util.Random;

import murock.ciudad.Ciudad;

public class Mapa {
	private Casilla[][] casillas;
	private int tamX, tamY;

	public Mapa(int tamX, int tamY) {
		this.tamX = tamX;
		this.tamY = tamY;

		casillas = new Casilla[tamX][];
		for (int i = 0; i < tamX; i++) {
			casillas[i] = new Casilla[tamY];
			for (int j = 0; j < tamY; j++) {
				casillas[i][j] = new Casilla(i, j);
			}
		}
	}

	public Mapa(int tamX, int tamY, int numeroCiudades) {
		this(tamX, tamY);
		assert (numeroCiudades < tamX * tamY);
		Casilla c = null;
		int i = 0;
		int j = 0;
		while (i < numeroCiudades && j < numeroCiudades * 2) {
			c = getCasillaAleatoria();
			if (!c.isLlena()) {
				c.setCiudad(new Ciudad());
				i++;
			}
			j++;
		}
	}

	public int getTamX() {
		return tamX;
	}

	public int getTamY() {
		return tamY;
	}

	public Casilla getCasilla(int x, int y) {
		return casillas[x][y];
	}

	public Casilla getCasillaAleatoria() {
		Random generator = new Random();
		int x = generator.nextInt(tamX);
		int y = generator.nextInt(tamY);
		return getCasilla(x, y);
	}

	public LinkedList<Ciudad> getCiudades() {
		LinkedList<Ciudad> tmp = new LinkedList<Ciudad>();

		for (int i = 0; i < tamX; i++) {
			for (int j = 0; j < tamY; j++) {
				if (casillas[i][j].isLlena()) {
					tmp.add(casillas[i][j].getCiudad());
				}
			}
		}
		return tmp;
	}
	/**
	 * Calcula el camino desde una casilla dada hasta otra.
	 * @param inicio Casilla de partida.
	 * @param fin Casilla de llegada.
	 * @return Camino más corto entre partida y fin.
	 */
	public LinkedList<Casilla> getCamino(Casilla inicio, Casilla fin){
		LinkedList<Casilla> camino=new LinkedList<Casilla>();
		
		return camino;
	}
	
	@Override
	public String toString() {
		String temp = "";
		for (int i = 0; i < tamX; i++) {
			for (int j = 0; j < tamY; j++) {
				temp += casillas[i][j].toString();
				temp += '\n';
			}
		}
		return temp;
	}
}
