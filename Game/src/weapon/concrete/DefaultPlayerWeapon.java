package weapon.concrete;

import javafx.beans.property.ReadOnlyDoubleProperty;
import model.movement.StraightLineMovement;
import weapon.PlayerWeapon;
import weapon.PlayerlikeProjectile;
import weapon.projectile.DefaultPlayerProjectile;

public class DefaultPlayerWeapon extends PlayerWeapon {

	
	public DefaultPlayerWeapon(ReadOnlyDoubleProperty playerX, ReadOnlyDoubleProperty playerY){
		super(playerX,playerY);
		projectile = new DefaultPlayerProjectile(playerX.get(),playerY.get());
	}
	
	@Override
	public void fireWeapon(){
		projectile.updateSpawnLocation(playerX.get(), playerY.get());
		PlayerlikeProjectile proj1 = projectile.copy();
		proj1.setMovement(new StraightLineMovement(proj1.xProperty(), proj1.yProperty(),0,-600));
		proj1.spawn();
		
		projectile.updateSpawnLocation(playerX.get()+10, playerY.get());
		PlayerlikeProjectile proj2 = projectile.copy();
		proj2.setMovement(new StraightLineMovement(proj2.xProperty(), proj2.yProperty(),0,-600));
		proj2.spawn();
	}
	
}
