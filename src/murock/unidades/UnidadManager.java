package murock.unidades;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import murock.Juego;
import murock.unidades.enumerados.Equipo;
import murock.unidades.tipos.Grunt;
import murock.unidades.tipos.Proyectil;
import murock.unidades.tipos.Psycho;

public class UnidadManager {
	/** Contiene todas las entidades que existen. */
	private List<Unidad> l1, l2;
	protected Juego juego;

	public UnidadManager(Juego j) {
		juego = j;
		l1 = new ArrayList<Unidad>();
		l2 = new ArrayList<Unidad>();
	}

	public List<Unidad> getListaAmigosDe(Equipo miEquipo) {
		if (miEquipo == Equipo.A)
			return l1;
		else
			return l2;
	}

	public List<Unidad> getListaEnemigosDe(Equipo miEquipo) {
		if (miEquipo == Equipo.B)
			return l1;
		else
			return l2;
	}

	public void dibujar(Graphics2D g) {
		g.translate(40, 40);
		g.scale(0.8, 0.8);
		g.setColor(Color.white);
		g.drawRect(0, 0, 799, 599);
		// Dibuja todas las unidades
		for (Unidad i : l1) {
			if (i.isAlive()) i.dibujar(g);
		}

		for (Unidad i : l2) {
			if (i.isAlive()) i.dibujar(g);
		}
		g.scale(1.2, 1.2);
	}

	public void updateUnidades() {
		Unidad u = null;
		for (int i = 0; i < l1.size(); i++) {
			u = l1.get(i);
			if (u.isAlive()) {
				u.update();
			} else {
				u.morir();
			}
			// Quita los proyectiles que hayan llegado a su destino
			if (u instanceof Proyectil && !u.isBusy()) {
				u.morir();
			}
		}

		for (int i = 0; i < l2.size(); i++) {
			u = l2.get(i);
			if (u.isAlive()) {
				u.update();
			} else {
				u.morir();
			}
			// Quita los proyectiles que hayan llegado a su destino
			if (u instanceof Proyectil && !u.isBusy()) {
				u.morir();
			}
		}

	}

	public void insertaUnidad(Unidad u, Equipo e) {
		if (u != null) {
			if (e == Equipo.A) {
				l1.add(u);
			} else {
				l2.add(u);
			}
		}
	}

	public void crearUnidades(String data) {
		Unidad p, g;
		/*
		 * Futuros bichos: Grunt, Psycho, Tough, Sniper, Flame, Laser
		 */

		Random aleatorio = new Random();

		p = new Psycho(aleatorio.nextInt(800), aleatorio.nextInt(600), this);
		g = new Grunt(aleatorio.nextInt(800), aleatorio.nextInt(600), this);

		// Poner unidades en su equipo
		p.setEquipo(Equipo.A);
		g.setEquipo(Equipo.B);

		insertaUnidad(p, Equipo.A);
		insertaUnidad(g, Equipo.B);

		// Ordenes para Psycho p
//		p.setObjetivo(g);
		// p.atacarA(g);
		// p.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// p.atacarA(g);
		// p.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// p.atacarA(g);
		// p.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// p.atacarA(g);
		// p.atacarA(g);
		// p.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// p.atacarA(g);
		// p.atacarA(g);
		// p.atacarA(g);
		// p.atacarA(g);
		// p.atacarA(g);
		// p.atacarA(g);

		// Ordenes para Grunt g
//		g.setObjetivo(p);
		// g.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// g.esperar(1);
		// g.atacarA(p);
		// g.atacarA(p);
		// g.atacarA(p);
		// g.atacarA(p);
		// g.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// g.esperar(1);
		// g.atacarA(p);
		// g.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// g.esperar(1);
		// g.atacarA(p);
		// g.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// g.esperar(1);
		// g.atacarA(p);
		// g.moverA(aleatorio.nextInt(800), aleatorio.nextInt(600));
		// g.atacarA(p);
		// g.atacarA(p);
		// g.atacarA(p);
		// g.atacarA(p);
		// g.atacarA(p);
	}
}
