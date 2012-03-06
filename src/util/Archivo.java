package util;

import java.io.*;

public class Archivo {

	/**
	 * Obtiene el contenido completo de una archivo de texto y lo devuelve como
	 * un String. No lanza excepciones al llamante.
	 * 
	 * @param archivo Un archivo que existe y puede ser leido.
	 */
	static public String getContenido(File archivo) {
		StringBuilder contenido = new StringBuilder();

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(archivo));
			try {
				String linea = null; // not declared within while loop
				/*
				 * readLine is a bit quirky : it returns the content of a line
				 * MINUS the newline. it returns null only for the END of the
				 * stream. it returns an empty String if two newlines appear in
				 * a row.
				 */
				while ((linea = input.readLine()) != null) {
					contenido.append(linea);
					contenido.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return contenido.toString();
	}

	/**
	 * Cambia el contenido del archivo de texto completamente, sobreescribiendo
	 * cualquier texto existente. Lanza todas las excepciones al llamante.
	 * 
	 * @param archivo Archivo en el que se puede escribir.
	 * @throws IllegalArgumentException Si los parametros no son correctos.
	 * @throws FileNotFoundException Si el archivo no existe.
	 * @throws IOException Si hay un problema al escribir.
	 */
	static public void setContenido(File archivo, String contenido)
			throws FileNotFoundException, IOException {
		if (archivo == null) {
			throw new IllegalArgumentException("El archivo es null.");
		}
		if (!archivo.exists()) {
			throw new FileNotFoundException("El archivo no existe: " + archivo);
		}
		if (!archivo.isFile()) {
			throw new IllegalArgumentException("El archivo es un directorio: "
					+ archivo);
		}
		if (!archivo.canWrite()) {
			throw new IllegalArgumentException("El archivo no se puede escribir: "
					+ archivo);
		}

		// use buffering
		Writer output = new BufferedWriter(new FileWriter(archivo));
		try {
			// FileWriter always assumes default encoding is OK!
			output.write(contenido);
		} finally {
			output.close();
		}
	}

	public static void main(String... aArguments) throws IOException {
		File testFile = new File("test.txt");
		System.out.println("Original file contents: " + getContenido(testFile));
		setContenido(testFile,
				"The content of this file has been overwritten...");
		System.out.println("New file contents: " + getContenido(testFile));
	}
}
