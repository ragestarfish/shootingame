package model;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;

public abstract class PlayerlikeShooter extends PlayerlikeDamagableGizmo {
	
	private ReadOnlyDoubleWrapper xReadOnly;
	private ReadOnlyDoubleWrapper yReadOnly;
	
	protected WeaponBehavior weapon;
	
	protected PlayerlikeShooter(double locationX, double locationY,  int health ) {
		super(locationX, locationY, health);
		xReadOnly = new ReadOnlyDoubleWrapper();
		yReadOnly = new ReadOnlyDoubleWrapper();
		xReadOnly.bind(xProperty());
		yReadOnly.bind(yProperty());
		// TODO Auto-generated constructor stub
	}
	
	public ReadOnlyDoubleProperty xPropertyUnmodifiable(){return xReadOnly.getReadOnlyProperty();}
	public ReadOnlyDoubleProperty yPropertyUnmodifiable(){return yReadOnly.getReadOnlyProperty();}
	
	public WeaponBehavior getWeapon(){return this.weapon;}
	public void setWeapon(WeaponBehavior weapon){this.weapon = weapon;}
	
	public void shoot(){weapon.fireWeapon();}

}
