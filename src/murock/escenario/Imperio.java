package murock.escenario;

import java.util.HashMap;
import murock.ciudad.Ciudad;

public class Imperio {
	private static int idsImperio = 0;
	private HashMap<Integer, Ciudad> ciudades;	// mapa (ID, CIUDAD)
	private int miId;
	private String nombreImperio;
	private Jugador propietario;

	public Imperio(String nombreImperio){
		miId = idsImperio++;
		this.nombreImperio = nombreImperio;
		ciudades=new HashMap<Integer, Ciudad>();
		propietario=null;
	}
	public Imperio(String nombreImperio, Jugador propietario) {
		this(nombreImperio);
		this.propietario = propietario;
	}

	public String getNombreImperio() {
		return nombreImperio;
	}

	public void addCiudad(Ciudad ciudad){
		ciudades.put(ciudad.getId(), ciudad);
	}
	
	public int getId(){
		return miId;
	}
	
	public Jugador getPropietario(){
		return propietario;
	}
	
	@Override
	public String toString(){
		return "IMPERIO: "+nombreImperio+"("+miId+")"+"\t\t-\tPropietario: "+propietario.toString();
	}
	
}
