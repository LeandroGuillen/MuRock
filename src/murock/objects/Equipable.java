package murock.objects;

import murock.objects.types.EquipableType;

public class Equipable {
	private int cantidad;
	private String nombre;
	protected EquipableType tipo;
	
	public Equipable(){
		tipo=null;
		cantidad=0;
		nombre=null;
	}

	public int getCantidad(){
		return cantidad;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}

	public EquipableType getTipo(){
		return tipo;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
