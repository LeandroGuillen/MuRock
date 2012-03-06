package murock;

import murock.unidades.Unidad;
import murock.unidades.enumerados.Estado;
import util.Cronometro;
import util.graficos.Punto;

public class Accion {
	private Cronometro c;
	private Estado estado;
	private Punto punto;
	private Unidad objetivo;
	private double tiempoRestante; // en milisegundos

	public Accion(Estado e, Punto p) {
		estado = e;
		punto = p;
	}

	public Accion(Estado e, Unidad u) {
		this(e, u.getPosicion());
		objetivo = u;
	}

	public Accion(Estado e, Unidad u, double tiempo) {
		this(e, u.getPosicion());
		objetivo = u;
		estado = e;
		c = new Cronometro();
		e = Estado.ESPERAR;
		this.tiempoRestante = tiempo;
	}

	public Accion(Estado e, double tiempo) {
		estado = e;
		c = new Cronometro();
		e = Estado.ESPERAR;
		this.tiempoRestante = tiempo;
	}

	public Estado getEstado() {
		return estado;
	}

	public Punto getPunto() {
		return punto;
	}

	public Unidad getObjetivo() {
		return objetivo;
	}

	public boolean isTerminada() {
		return c.getTicks() > tiempoRestante * 1000;
	}

	public void comenzarEspera() {
		c.start();
	}

	public String toString() {
		return estado.toString();
	}
}
