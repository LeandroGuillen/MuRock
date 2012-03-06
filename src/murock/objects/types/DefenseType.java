package murock.objects.types;

public enum DefenseType implements EquipableType {
	ARMOR, PSIONIC, MAGIC, MELEE_SHIELD, SHIELD;

	@Override
	public String getNombre(EquipableType e) {
		e = (DefenseType) e;
		if (e == DefenseType.ARMOR)
			return "Armadura";
		if (e == DefenseType.MELEE_SHIELD)
			return "Escudo de mano";
		if (e == DefenseType.SHIELD)
			return "Escudo";
		if (e == DefenseType.MAGIC)
			return "Magico";
		if (e == DefenseType.PSIONIC)
			return "Psionico";
		return "(tipo de defensa desconocida)";
	}
}
