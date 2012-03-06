package murock.escenario;

import java.util.LinkedList;

import murock.unidades.Unidad;

public class Ejercito {
	private Imperio imperioPropietario;
	private LinkedList<Unidad> soldados;
	
	public Ejercito(){
		soldados=new LinkedList<Unidad>();
	}

	public int getNumeroUnidades() {
		return soldados.size();
	}

	public Imperio getImperioPropietario() {
		return imperioPropietario;
	}
	
	public void setImperioPropietario(Imperio imperioPropietario) {
		this.imperioPropietario = imperioPropietario;
	}

	public void insertarSoldado(Unidad soldado){
		soldados.add(soldado);
	}
	
	@Override
	public String toString(){
		String temp="";
		for ( Unidad s : soldados)
			temp+=s+"\n";
		return temp;
	}
}
