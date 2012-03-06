package murock.objects.weapons;

import murock.objects.ObjectManager;
import murock.objects.types.WeaponType;

public class WeaponManager extends ObjectManager {

	public WeaponManager() {
		super();
	}

	@Override
	public void preparar() {
		Weapon w;

		w = new Weapon(WeaponType.MELEE);
		w.setCantidad(2);
		w.setAlcance(1);
		w.setNombre("Machete");
		insertar(w.getNombre(), w);

		w = new Weapon(WeaponType.MELEE);
		w.setCantidad(1);
		w.setAlcance(1);
		w.setNombre("Navaja");
		insertar(w.getNombre(), w);

		w = new Weapon(WeaponType.MELEE);
		w.setCantidad(1);
		w.setAlcance(1);
		w.setNombre("Cuchilla");
		insertar(w.getNombre(), w);

		w = new Weapon(WeaponType.MELEE);
		w.setCantidad(5);
		w.setAlcance(1);
		w.setNombre("Espada");
		insertar(w.getNombre(), w);
	}

}
