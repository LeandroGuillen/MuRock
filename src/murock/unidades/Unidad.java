package murock.unidades;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import murock.Accion;
import murock.unidades.enumerados.*;
import murock.unidades.tipos.Proyectil;
import util.graficos.Animation;
import util.graficos.Punto;
import util.graficos.Sprite;
import util.graficos.SpriteStore;

public class Unidad implements Comparable<Unidad> {
	private static int idUnidad;
	private Accion accionActual;
	private Queue<Accion> accionesPendientes;
	private double angulo;
	protected Map<Estado, Animation> animaciones;
	private double cooldown;
	private Direccion direccion;
	private Estado estadoActual;
	private int hitPoints;
	protected UnidadManager manager;
	private int maxHitPoints;
	private Equipo miEquipo;
	/** Identificador de la unidad. */
	private int miId;
	private Unidad objetivo;
	private String ruta;
	/** Apunta al sprite que se va a dibujar en la iteracion actual. */
	private Sprite spriteActual;
	/** Velocidad lineal. */
	private double velocidad;
	/** Velocidad vectorial en los ejes de coordenadas. */
	private double vx, vy;
	/** Coordenadas de la unidad. */
	private double x, oldx, y;
	/** Coordenadas del destino. */
	private double x2, y2;

	/**
	 * Crea una unidad con un sprite por defecto "normal.png" dentro de la
	 * carpeta provista en ref. Ademas lo coloca en la posicion indicada en x e
	 * y.
	 * 
	 * @param ref Nombre de la carpeta de la unidad.
	 * @param x Coordenada x en la pantalla.
	 * @param y Coordenada y en la pantalla.
	 */
	public Unidad(String ref, double x, double y, UnidadManager um) {
		// Nueva animacion
		animaciones = new HashMap<Estado, Animation>();
		accionesPendientes = new LinkedList<Accion>();
		manager = um;

		ruta = ref;
		// Crea animaciones para cada estado
		for (Estado estado : Estado.values()) {
			animaciones.put(estado, new Animation());
		}
		// Para el estado ESPERAR, igual que NORMAL
		animaciones.put(Estado.ESPERAR, animaciones.get(Estado.NORMAL));
		// Para el estado MORIR, igual que NORMAL
		animaciones.put(Estado.MORIR, animaciones.get(Estado.NORMAL));

		// Coloca el sprite del estado por defecto
		insertarFrame(Estado.NORMAL, "normal.png");

		// Datos basicos
		miId = idUnidad++;
		this.x = x;
		this.y = y;
		vx = vy = 0;
		velocidad = 0.3;
		estadoActual = Estado.NORMAL;
		spriteActual = getFrame();
		cooldown = 0.5;
	}

	public Unidad(String[] refs, double x, double y, UnidadManager um) {
		this(refs[0], x, y, um);
		int i = 0;
		for (String s : refs) {
			insertarFrame(Estado.NORMAL, s + "/normal" + i + ".png");
			i++;
		}
	}

	public void accion(Accion a) {
		accionesPendientes.add(a);
	}

	public void addHP(int hitPoints) {
		this.hitPoints += hitPoints;
		if (this.hitPoints > maxHitPoints)
			this.hitPoints = maxHitPoints;
		else if (this.hitPoints < 0) this.hitPoints = 0;
	}

	public void apuntarADestino(Punto p) {
		x2 = p.x;
		y2 = p.y;
		// Hallamos la distancia entre origen y destino
		double a = Math.abs(this.x - x2);
		double b = Math.abs(this.y - y2);
		// double a = this.x - x2;
		// double b = this.y - y2;
		double d = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		// El angulo de la trayectoria
		angulo = Math.asin(b / d);
		// Velocidad en los ejes X e Y
		vx = (velocidad * Math.cos(angulo));
		// vy es negativa porque el origen esta arriba
		vy = (velocidad * Math.sin(angulo));
	}

	public void atacarA(Unidad u) {
		if (!(this instanceof Proyectil)) {
			setObjetivo(u);
			accion(new Accion(Estado.ATACAR, u, cooldown));
			esperar(0.5);
		}
	}

	public void atacarObjetivo() {
		atacarA(objetivo);
	}

	public void buscarObjetivo() {
		final List<Unidad> objetivos = manager.getListaEnemigosDe(miEquipo);
		int maxObjetivos = 5;

		double distanciaMinima = 10000, distanciaActual = 0;
		Unidad objetivoFinal = null;
		// Busca el enemigo mas cercano en el mapa
		for (int i = 0; i < objetivos.size() && i < maxObjetivos; i++) {
			distanciaActual = distanciaA(objetivos.get(i));
			if (distanciaActual < distanciaMinima) {
				distanciaMinima = distanciaActual;
				objetivoFinal = objetivos.get(i);
				System.out.println(this.toString() + " (mi objetivo es :"
						+ objetivos.get(i) + ")");
			}
		}
		if (objetivoFinal != null && !(this instanceof Proyectil))
			atacarA(objetivoFinal);
	}

	@Override
	public int compareTo(Unidad u) {
		if (u == null) return 0;
		if (u.getY() < y) return 1;
		if (u.getY() > y) return -1;
		return 0;
	}

	public void dibujar(Graphics2D g) {
		dibujarBarraVida(g);

		if (direccion == Direccion.IZQUIERDA) {
			spriteActual.drawFlipped(g, (int) x, (int) y);
		} else {
			spriteActual.draw(g, (int) x, (int) y);
		}

	}

	public void dibujarBarraVida(Graphics2D g) {
		Color temp = g.getColor();
		int x = (int) getX();
		int y = (int) getY();
		int w = spriteActual.getWidth();
		double p = ((double) w / (double) maxHitPoints) * (double) hitPoints;

		g.setColor(Color.red);
		g.fillRect(x, y, w, 3);

		g.setColor(Color.green);
		g.fillRect(x, y, (int) p, 3);

		g.setColor(temp);
	}

	public double distanciaA(Punto p) {
		return Math.sqrt(Math.pow(Math.abs(x - p.x), 2)
				+ Math.pow(Math.abs(y - p.y), 2));
	}

	public double distanciaA(Unidad u) {
		return distanciaA(u.getPosicion());
	}

	public void esperar(double tiempo) {
		// Tiempo en segundos
		accion(new Accion(Estado.ESPERAR, tiempo));
	}

	public Equipo getEquipo() {
		return miEquipo;
	}

	private Sprite getFrame() {
		return animaciones.get(estadoActual).getSprite();
	}

	public int getHeight() {
		return spriteActual.getHeight();
	}

	public int getHP() {
		return hitPoints;
	}

	public int getId() {
		return miId;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public Punto getPosicion() {
		return new Punto(x + getWidth() / 2, y + getHeight() / 2);
	}

	public double getVelocidad() {
		return velocidad;
	}

	public int getWidth() {
		return spriteActual.getWidth();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void insertarFrame(Estado estado, String ref) {
		animaciones.get(estado).setSprite(
				SpriteStore.get().getSprite(ruta + ref));
	}

	public void insertarFrame(Estado estado, String[] refs) {
		for (String ref : refs) {
			insertarFrame(estado, ref);
		}
	}

	public boolean isAlive() {
		return hitPoints > 0;
	}

	public boolean isBusy() {
		return estadoActual != Estado.NORMAL;
	}

	public boolean isDead() {
		return hitPoints <= 0;
	}

	private void meParo(int r) {
		if (Math.abs((x + spriteActual.getWidth() / 2)
				- (x2 + spriteActual.getWidth() / 2)) < r
				&& Math.abs((y + spriteActual.getHeight() / 2)
						- (y2 + spriteActual.getHeight() / 2)) < r) {
			setEstado(Estado.NORMAL);
		}
	}

	public void morir() {
		setEstado(Estado.MORIR);
	}

	public void moverA(double x, double y) {
		moverA(new Punto(x, y));
	}

	public void moverA(Punto p) {
		accion(new Accion(Estado.MOVER, p));
	}

	public void moverHacia(Unidad u) {
		// Para obtener la posicion actual del objetivo
		Unidad v = manager.getListaEnemigosDe(miEquipo).get(
				manager.getListaEnemigosDe(miEquipo).indexOf(u));
		moverA(v.getPosicion());
	}

	public void pensar() {
		// Maximo de acciones pendientes = 2
		if (accionesPendientes.size() < 2) {
			if (tengoObjetivo()) {
				atacarObjetivo();
			} else {
				buscarObjetivo();
			}

		}
	}

	private void quitarUnidad() {
		manager.getListaAmigosDe(miEquipo).remove(this);
	}

	private void realizarMovimiento() {
		// Para averiguar la direccion en que vamos
		oldx = x;
		// Si se mueve en la horizontal
		if (vx != 0) {
			if (x2 < x)
				x -= vx;
			else
				x += vx;
		}
		// Si se mueve en la vertical
		if (vy != 0) {
			if (y2 < y)
				y -= vy;
			else
				y += vy;
		}
		// Si hemos cambiado el sentido cambiamos el sprite
		if (oldx > x) {
			direccion = Direccion.IZQUIERDA;
		} else if (oldx < x) {
			direccion = Direccion.DERECHA;
		}
		// Si hemos llegado a al menos 5 pixels del destino nos
		// paramos
		meParo(5);
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	public void setEquipo(Equipo miEquipo) {
		this.miEquipo = miEquipo;
	}

	public void setEstado(Estado estado) {
		estadoActual = estado;
	}

	public void setHP(int hitPoints) {
		if (hitPoints < maxHitPoints) {
			this.hitPoints = hitPoints;
		} else {
			this.hitPoints = maxHitPoints;
		}
	}

	public void setMaxHitPoints(int maxHitPoints) {
		if (maxHitPoints > 0) {
			this.maxHitPoints = maxHitPoints;
			setHP(maxHitPoints);
		}
	}

	public void setObjetivo(Unidad u) {
		objetivo = u;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public boolean tengoObjetivo() {
		return objetivo != null && objetivo.isAlive();
	}

	@Override
	public String toString() {
		String t = getClass().getName();
		t = t.substring(t.lastIndexOf('.') + 1);
		return t + " [" + miId + "]";
	}

	public void update() {
		// Actualiza el estado, decidiendo uno nuevo si es necesario
		updateEstado();
		// Cambia el frame de la animacion segun el estado
		updateFrame();
	}

	private void updateEstado() {
		switch (estadoActual) {
			case MOVER:
				realizarMovimiento();
				break;
			case NORMAL:
				// La unidad "piensa" para ver que hace
				if (!(this instanceof Proyectil)) {
					pensar();
				}
				/*
				 * Si hay acciones pendientes escogemos la siguiente. Solo
				 * cuando estamos en estado normal podemos ver las nuevas
				 * acciones pendientes.
				 */
				if (!accionesPendientes.isEmpty()) {
					accionActual = accionesPendientes.poll();
					switch (accionActual.getEstado()) {
						case MOVER:
							apuntarADestino(accionActual.getPunto());
							setEstado(Estado.MOVER);
							break;
						case ESPERAR:
							accionActual.comenzarEspera();
							setEstado(Estado.ESPERAR);
							break;
						case ATACAR:
							accionActual.comenzarEspera();
							setEstado(Estado.ATACAR);
							// Miramos a donde este el objetivo
							if (accionActual.getPunto().x < x)
								direccion = Direccion.IZQUIERDA;
							else
								direccion = Direccion.DERECHA;
							break;
						default:
					}

				}
				break;
			case MORIR:
				quitarUnidad();
				break;
			case ESPERAR:
				// no hago nada, solo esperar hasta que pase el tiempo
				if (accionActual.isTerminada()) {
					setEstado(Estado.NORMAL);
				}
				break;
			case ATACAR:
				// Esperamos que acabe el timeout
				if (accionActual.isTerminada()) {
					// Si estamos atacando fijate que miras al sitio adecuado
					if (accionActual.getPunto().x < x)
						direccion = Direccion.IZQUIERDA;
					else
						direccion = Direccion.DERECHA;
					// y que el proyectil "salga" del lugar correcto
					double tx = (direccion == Direccion.IZQUIERDA ? x : x
							+ (getWidth() / 2));
					double ty = y + (getHeight() / 2);

					// Para obtener la posicion actual del objetivo
					Unidad v = null;
					int n = manager.getListaEnemigosDe(miEquipo).indexOf(
							accionActual.getObjetivo());

					if (n != -1) {
						v = manager.getListaEnemigosDe(miEquipo).get(n);
					}

					// Creamos un proyectil y lo mandamos hacia el objetivo
					if (v != null) new Proyectil(tx, ty, v, manager, this);

					setEstado(Estado.NORMAL);
				}
				break;
			default:
				break;
		}
	}

	private void updateFrame() {
		// Obtenemos el sprite que corresponde al estado de la unidad
		spriteActual = getFrame();
		// Actualizamos la animacion
		animaciones.get(estadoActual).update();
	}
}
