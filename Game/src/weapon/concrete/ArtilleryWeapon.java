package weapon.concrete;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import model.movement.FreeFalling;
import weapon.EnemyProjectile;
import weapon.EnemyWeapon;
import weapon.projectile.SimpleCircularProjectile;

public class ArtilleryWeapon extends EnemyWeapon {
	
	private Point2D v0;
	private Point2D gravity;

	public ArtilleryWeapon(ReadOnlyDoubleProperty enemyX, ReadOnlyDoubleProperty enemyY, Point2D v0, Point2D gravity) {
		super(enemyX, enemyY);
		this.v0=v0;
		this.gravity=gravity;
		setProjectile( new SimpleCircularProjectile(enemyX.get(),enemyY.get(), new FreeFalling(new SimpleDoubleProperty(), new SimpleDoubleProperty(), v0, gravity )) );
	}

	public void adjustVelocity(Point2D v0){this.v0 = v0;}
	
	@Override
	public void fireWeapon() {
		EnemyProjectile proj = getProjectile().copy();
		
		proj.updateSpawnLocation(enemyX.get(), enemyY.get());
		proj.setMovement(new FreeFalling(proj.xProperty(), proj.yProperty(), v0, gravity ));
		proj.spawn();
	}

}
