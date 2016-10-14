package weapon.concrete;

import java.util.ArrayList;
import java.util.List;

import model.WeaponBehavior;

public class CompositeWeapon implements WeaponBehavior {

	private List<WeaponBehavior> weapons;
	private int active;
	
	public CompositeWeapon(List<WeaponBehavior> weapons) {
		this.weapons = weapons;
		active = weapons.size()-1;
	}
	public CompositeWeapon(){weapons = new ArrayList<>();}
	
	public void add(WeaponBehavior weapon){
		weapons.add(weapon);
		active = weapons.size()-1;
	}
	public int getNumberOfWeapons(){return weapons.size();}
	public int current(){return active;}
	public void switchTo(int i){active = i;}

	@Override
	public void fireWeapon() {
		weapons.get(active).fireWeapon();
	}

}
