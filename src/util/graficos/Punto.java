package util.graficos;

public class Punto {
	public double x, y;

	public Punto() {
		x = y = 0;
	}

	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
