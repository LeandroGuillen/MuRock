package murock;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.*;
import util.manejador.*;
import murock.unidades.UnidadManager;

public class Juego extends Canvas {
	private static final int FRAMES_PER_SECOND = 100;
	private static final long serialVersionUID = -8556651630585063971L;

	public static void main(String argv[]) {
		new Juego();
	}

	private static final Font fuente1 = new Font("arial", Font.BOLD, 10);

	/** Lleva la cuenta de cuanto tiempo tarda un frame para calcular los FPS. */
	private Cronometro cronometro;
	/** Verdadero si el bucle principal se esta ejecutando. */
	private boolean ejecutarJuego = true;
	/** Si es verdadero pausa la simulacion. Se siguen dibujando los sprites. */
	private boolean pausarJuego = false;
	/** Para acelerar el page flipping. */
	private BufferStrategy strategy;
	/** Realiza operaciones de actualizacion y dibujo de las unidades. */
	private UnidadManager unidades;
	private String mensaje = "";

	public Juego() {
		// create a frame to contain our game
		JFrame container = new JFrame("Murock 0.1");

		// get hold the content of the frame and set up the resolution of the
		// game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		// setup our canvas size and put it into the content of the frame
		setBounds(0, 0, 800, 600);
		panel.add(this);

		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);

		// finally make the window visible
		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		insertarListeners(container);

		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		// initialise the entities in our game so there's something
		// to see at startup
		start();
	}

	public void gameLoop() {
		Cronometro miCrono = new Cronometro();

		miCrono.start();

		while (ejecutarJuego) {
			// Get hold of a graphics context for the accelerated
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);

			// Iniciamos el cronometro
			cronometro.start();

			unidades.dibujar(g);

			// Esta el juego en pause?
			if (!pausarJuego) {
				unidades.updateUnidades();
			} else {
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 30));
				g.drawString("PAUSA", getWidth() / 2 - 100, getHeight() / 2);
			}

			// Calcula los FPS
			if (cronometro.getTicks() < 1000 / FRAMES_PER_SECOND) {
				// Sleep the remaining frame time
				try {
					Thread.sleep((1000 / FRAMES_PER_SECOND)
							- cronometro.getTicks());
				} catch (Exception e) {
				}
			}
			// Cada diez segundos
			// if (miCrono.getTicks() / 100 > 3) {
			// miCrono.start();
			// }

			// Muestra los FPS en la esquina inferior izquierda de la
			// pantalla
			String str = "FPS: " + 1000 / cronometro.getTicks();
			g.setColor(Color.white);
			g.setFont(fuente1);
			g.drawString(str, 10, 570);

			// Dibujar barra de estado
			g.drawString(mensaje, 160, 570);
			g.drawString("Resultado de la ER:  " + res, 50, 10);

			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();
		}
	}

	private void insertarListeners(JFrame container) {
		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		addMouseListener(new ManejadorRaton(this));

		// add a key input system (defined below) to our canvas
		// so we can respond to key pressed
		addKeyListener(new ManejadorTeclado(this));
	}

	public void pause() {
		pausarJuego = !pausarJuego;
	}

	private String res;

	public void start() {
		// Crear objetos del juego
		unidades = new UnidadManager(this);
		cronometro = new Cronometro();
		cronometro.start();

		setMensajeEstado("SIMULACIÓN EN PRUEBAS");
		// Leemos el fichero de configuracion
		// Y le pasamos los datos al manager
		unidades.crearUnidades(Archivo.getContenido(new File(
				"/home/leandro/Escritorio/sprites/textos/unidades.xml")));

		// Cuando todo esta listo para empezar,
		// iniciar el bucle principal del juego
		gameLoop();
	}

	public void setMensajeEstado(String msj) {
		mensaje = msj;
	}

}
