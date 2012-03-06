package murock.escenario;

public class Jugador {
	private String nombreJugador;

	public Jugador(String nombreJugador) {
		super();
		this.nombreJugador = nombreJugador;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	@Override
	public String toString() {
		return nombreJugador;		
	}
	
}
