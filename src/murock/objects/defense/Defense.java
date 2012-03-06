package murock.objects.defense;

import murock.objects.Equipable;
import murock.objects.types.DefenseType;

public class Defense extends Equipable {

	public Defense(DefenseType tipo) {
		super();
		this.tipo = tipo;
		setNombre("Defensa Basica");
	}

}
