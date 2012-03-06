package murock.objects.weapons;

import murock.objects.Equipable;
import murock.objects.types.WeaponType;

public class Weapon extends Equipable {
	private int alcance;

	public Weapon(WeaponType tipo) {
		super();
		this.tipo = tipo;
		setNombre("Arma basica");
	}

	public void setAlcance(int alcance) {
		if (this.tipo != WeaponType.MELEE)
			this.alcance = alcance;
		else if (alcance > 2)
			this.alcance = 1;
		else
			this.alcance = alcance;
	}

	public int getAlcance() {
		return alcance;
	}
}
