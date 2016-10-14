package weapon;

import javafx.beans.property.ReadOnlyDoubleProperty;
import model.WeaponBehavior;

public abstract class PlayerWeapon implements WeaponBehavior {
	
		protected PlayerlikeProjectile projectile;
		
		protected ReadOnlyDoubleProperty playerX;
		protected ReadOnlyDoubleProperty playerY;
		
		public PlayerWeapon(ReadOnlyDoubleProperty playerX, ReadOnlyDoubleProperty playerY){
			this.playerX=playerX;
			this.playerY = playerY;
			
		}
		
		public void loadProjectile(PlayerlikeProjectile projectile){this.projectile=projectile;}
}
