package weapon.concrete;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.util.Duration;
import model.movement.StraightLineMovement;
import weapon.EnemyProjectile;
import weapon.EnemyWeapon;
import weapon.projectile.SimpleCircularProjectile;

public class HomingWeapon extends EnemyWeapon {

	private ReadOnlyDoubleProperty playerX;
	private ReadOnlyDoubleProperty playerY;
	
	public HomingWeapon(ReadOnlyDoubleProperty enemyX, ReadOnlyDoubleProperty enemyY, ReadOnlyDoubleProperty playerX, ReadOnlyDoubleProperty playerY) {
		super(enemyX, enemyY);
		this.playerX = playerX;
		this.playerY = playerY;
		setProjectile( new SimpleCircularProjectile(enemyX.get(),enemyY.get(),new StraightLineMovement(0.0,0.0)) );
	}

	@Override
	public void fireWeapon() {
		EnemyProjectile proj = getProjectile().copy();
		proj.updateSpawnLocation(enemyX.get(),enemyY.get());
		StraightLineMovement movement = new StraightLineMovement(proj.xProperty(),proj.yProperty(),playerX.get(),playerY.get(),Duration.millis(6000),600);
		movement.setTowards();

		proj.setMovement(movement);
		proj.spawn();
	}

}
