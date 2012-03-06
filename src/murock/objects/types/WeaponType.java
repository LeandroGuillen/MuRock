package murock.objects.types;

public enum WeaponType implements EquipableType {
	EXPOSIVE, MELEE, PERSISTENT, THROWABLE, WAVE, MAGIC, PSIONIC;

	@Override
	public String getNombre(EquipableType e) {
		e = (WeaponType) e;
		if (e == WeaponType.EXPOSIVE)
			return "Explosivo";
		if (e == WeaponType.MELEE)
			return "Melee";
		if (e == WeaponType.PERSISTENT)
			return "Persistente";
		if (e == WeaponType.THROWABLE)
			return "Lanzable";
		if (e == WeaponType.WAVE)
			return "Onda";
		if (e == WeaponType.MAGIC)
			return "Magico";
		if (e == WeaponType.PSIONIC)
			return "Psionico";
		return "(tipo de arma desconocida)";
	}
}
