package weapon;

import javafx.beans.property.ReadOnlyDoubleProperty;
import model.WeaponBehavior;

public abstract class EnemyWeapon implements WeaponBehavior {
	
	protected ReadOnlyDoubleProperty enemyX;
	protected ReadOnlyDoubleProperty enemyY;

	private EnemyProjectile projectile;
	
	public EnemyWeapon(ReadOnlyDoubleProperty enemyX, ReadOnlyDoubleProperty enemyY) {
		this.enemyX=enemyX;
		this.enemyY=enemyY;
	}

	public EnemyProjectile getProjectile(){return this.projectile;}
	public void setProjectile(EnemyProjectile projectile){this.projectile=projectile;}

}
