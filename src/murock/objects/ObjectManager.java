package murock.objects;

import java.util.*;

public abstract class ObjectManager {
	Map<String, Equipable> objetosEquipables;
	
	public ObjectManager(){
		objetosEquipables=new HashMap<String, Equipable>();
	}
	
	public abstract void preparar();
	
	protected void insertar(String s, Equipable e){
		objetosEquipables.put(s, e);
	}
	
	public boolean existe(String s){
		return objetosEquipables.containsKey(s);
	}
}
