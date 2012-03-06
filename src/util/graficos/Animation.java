package util.graficos;

import java.util.Vector;

public class Animation {
	private static final int MAX_SPRITES = 20;
	private static final int RETARDO_ESTANDAR = 75;
	private int marcoActual;
	private boolean pausado;
	private int pausadoEn;
	private int repeticiones;
	private Vector<Integer> retardos;
	private Vector<Sprite> sprites;
	private long ultimoTiempo;

	public Animation() {
		sprites = new Vector<Sprite>();
		retardos = new Vector<Integer>();
		marcoActual = 0;
		ultimoTiempo = System.currentTimeMillis();
		pausado = false;
		pausadoEn = -1;
		repeticiones = -1;
	}

	public Sprite getSprite() {
		return sprites.get(marcoActual);
	}

	public int numeroAnimaciones() {
		return sprites.size();
	}

	public void pause() {
		pausado = true;
	}

	/**
	 * Hace que la animacion se pare en el marco especificado
	 * 
	 * @param i Marco en el que se para la animacion.
	 */
	public void pauseOn(int i) {
		if (i < numeroAnimaciones()) {
			pausadoEn = i;
		}
	}

	public void reset() {
		marcoActual = 0;
		ultimoTiempo = System.currentTimeMillis();
	}

	public void resume() {
		pausado = false;
		ultimoTiempo = System.currentTimeMillis();
	}

	/**
	 * Provoca que la animacion se pare en el ultimo marco.
	 */
	public void setAnimacionTipoMeseta() {
		pauseOn(numeroAnimaciones() - 1);
	}

	public void saltarAlMarco(int marco) {
		if (marco < sprites.size()) {
			marcoActual = marco;
		}
		ultimoTiempo = System.currentTimeMillis();
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public void setRetardo(int retardo, int posicion) {
		// Solo pone un retardo para un marco que existe
		if (posicion < retardos.size()) retardos.set(posicion, retardo);
	}

	public void setSprite(Sprite sprite) {
		setSprite(sprite, MAX_SPRITES + 1);
	}

	public void setSprite(Sprite sprite, int posicion) {
		// Si la posicion no existe, la insertamos al final
		if (posicion >= sprites.size()) {
			sprites.addElement(sprite);
			retardos.addElement(RETARDO_ESTANDAR);
		}
		// Y si no la coloca en su lugar
		else
			sprites.set(posicion, sprite);
	}

	public void update() {
		// Si la animacion esta pausada no hace nada
		if (pausado) return;

		// Si tiene que pararse en este marco tampoco hace nada
		if (pausadoEn == marcoActual) return;

		// Comprueba si ha pasado suficiente tiempo para pasar al siguiente
		// marco
		if (retardos.get(marcoActual) < (System.currentTimeMillis() - ultimoTiempo)) {
			marcoActual++;
			ultimoTiempo = System.currentTimeMillis();

			// Si hemos llegado al final
			if (marcoActual >= sprites.size()) {
				// Si no es bucle infinito disminuir las repeticiones
				if (repeticiones != -1) {
					// Si no quedan repeticiones pausar la animacion
					if (repeticiones == 0)
						pausado = true;
					else
						repeticiones--;
				}
				marcoActual = 0;
			}
		}
	}
}
