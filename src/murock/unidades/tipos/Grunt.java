package murock.unidades.tipos;

import murock.unidades.Unidad;
import murock.unidades.UnidadManager;
import murock.unidades.enumerados.Estado;

public class Grunt extends Unidad {
	private final int NUMERO_FRAMES_MOVER = 10;

	public Grunt(double x, double y, UnidadManager um) {
		super("grunt/", x, y, um);
		setVelocidad(1.5); // 0.28
		setMaxHitPoints(55);
		setCooldown(0.45);
//		addHP(10);

		String[] mover = new String[NUMERO_FRAMES_MOVER];

		for (int i = 0; i < NUMERO_FRAMES_MOVER; i++) {
			mover[i] = new String("mover" + i + ".png");
		}

		insertarFrame(Estado.MOVER, mover);
		insertarFrame(Estado.ATACAR, "ataque.png");
	}
}
