package murock.unidades.tipos;

import murock.unidades.Unidad;
import murock.unidades.UnidadManager;
import murock.unidades.enumerados.Estado;

public class Psycho extends Unidad {
	private final int NUMERO_FRAMES_MOVER = 3;

	public Psycho(double x, double y, UnidadManager um) {
		super("psycho/", x, y, um);
		setVelocidad(1.2); // 0.2
		setMaxHitPoints(65);
		setCooldown(0.61);
//		addHP(10);

		String[] mover = new String[NUMERO_FRAMES_MOVER];

		for (int i = 0; i < NUMERO_FRAMES_MOVER; i++) {
			mover[i] = new String("mover" + i + ".png");
		}

		insertarFrame(Estado.MOVER, mover);

		animaciones.get(Estado.MOVER).setRetardo(200, 0);
		animaciones.get(Estado.MOVER).setRetardo(200, 1);
		animaciones.get(Estado.MOVER).setAnimacionTipoMeseta();
		
		insertarFrame(Estado.ATACAR, "ataque.png");
	}
}
