package murock.ciudad;

public class Ciudad {
	private static int ciudadId = 0;
	private int miId;
	private String nombre;

	public Ciudad() {
		this("CIUDAD" + ciudadId);
	}

	public Ciudad(String nombre) {
		this.nombre = nombre;
		miId = ciudadId++;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return miId;
	}

	@Override
	public String toString() {
		return "Ciudad [miId=" + miId + ", nombre=" + nombre + "]";
	}

}
