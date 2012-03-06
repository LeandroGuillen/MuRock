package util.graficos;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * A sprite to be displayed on the screen. Note that a sprite contains no state
 * information, i.e. its just the image and not the location. This allows us to
 * use a single sprite in lots of different places without having to store
 * multiple copies of the image.
 * 
 * @author Kevin Glass
 */
public class Sprite {
	/** The image to be drawn for this sprite */
	private BufferedImage image;
	private BufferedImage flippedImage;
	private String nombre;
	private AffineTransform tx;

	/**
	 * Create a new sprite based on an image
	 * 
	 * @param image The image that is this sprite
	 */
	public Sprite(BufferedImage image) {
		this(image, "sin-nombre");
	}

	public Sprite(BufferedImage image, String nombre) {
		this.image = image;
		this.nombre = nombre;

		// Flip the image horizontally
		tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		flippedImage = op.filter(image, null);
	}

	/**
	 * Get the width of the drawn sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Get the height of the drawn sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight() {
		return image.getHeight(null);
	}

	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param g The graphics context on which to draw the sprite
	 * @param x The x location at which to draw the sprite
	 * @param y The y location at which to draw the sprite
	 */
	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	public void drawFlipped(Graphics2D g, int x, int y) {
		g.drawImage(flippedImage, x, y, null);
	}

	public String toString() {
		return nombre;
	}
}