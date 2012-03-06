package murock.escenario;

import murock.ciudad.Ciudad;

public class Casilla {
	private Ciudad ciudad;
	private int x, y; // Coordenadas de la casilla
	private Mapa mapa;

	public Casilla(int x, int y) {
		this.x = x;
		this.y = y;
		this.ciudad = null;
	}

	public Casilla(int x, int y, Ciudad ciudad) {
		this(x, y);
		this.ciudad = ciudad;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public boolean isLlena() {
		return ciudad != null;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
