package murock.unidades.tipos;

import murock.Accion;
import murock.unidades.Unidad;
import murock.unidades.UnidadManager;
import murock.unidades.enumerados.Estado;

public class Proyectil extends Unidad {
	private Unidad emisor;
	public Proyectil(double x, double y, Unidad objetivo,
			UnidadManager um, Unidad e) {
		super("proyectiles/", x, y, um);
		emisor=e;
		setVelocidad(10);
		setMaxHitPoints(10);
		addHP(1);
		// setDireccion(DireccionUnidad.DERECHA);
		setEstado(Estado.NORMAL);
		insertarFrame(Estado.MOVER, "normal.png");
		insertarFrame(Estado.MORIR, "normal.png");
		
		accion(new Accion(Estado.MOVER, objetivo));
		objetivo.addHP(-15);
		setEquipo(emisor.getEquipo());
		um.insertaUnidad(this, getEquipo());
	}

	@Override
	public String toString() {
		return "Proyectil ["+getId()+"]";
	}
}
