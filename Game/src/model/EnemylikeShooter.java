package model;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;

public abstract class EnemylikeShooter extends EnemylikeDamagableGizmo {
	
	protected WeaponBehavior weapon;
	protected ReadOnlyDoubleWrapper readOnlyX;
	protected ReadOnlyDoubleWrapper readOnlyY;
		
	protected EnemylikeShooter(double locationX, double locationY,  int health , WeaponBehavior weapon) {
		super(locationX, locationY, health);
		readOnlyX = new ReadOnlyDoubleWrapper(); 
		readOnlyY = new ReadOnlyDoubleWrapper();
		readOnlyX.bind(xProperty());
		readOnlyY.bind(yProperty());
		setWeapon( weapon );
		// TODO Auto-generated constructor stub
	}
	
	//shooters need to pass the location property to the weapons, so this class provides a safe way to do that
	public ReadOnlyDoubleProperty xPropertyUnmodifiable(){return readOnlyX.getReadOnlyProperty();}
	public ReadOnlyDoubleProperty yPropertyUnmodifiable(){return readOnlyY.getReadOnlyProperty();}
	
	
	public void setWeapon(WeaponBehavior weapon){this.weapon = weapon;}
	
	public void shoot(){weapon.fireWeapon();}

}