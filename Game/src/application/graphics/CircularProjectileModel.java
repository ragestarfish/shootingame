package application.graphics;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import model.hitbox.HitboxCircle;
import weapon.EnemyProjectile;
import weapon.PlayerlikeProjectile;

public class CircularProjectileModel {

	public CircularProjectileModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Node node(PlayerlikeProjectile proj, double radius){
		Circle model = new Circle(radius);
			model.centerXProperty().bind(proj.xProperty());
			model.centerYProperty().bind(proj.yProperty());
		HitboxCircle hitbox = new HitboxCircle(proj.xProperty(), proj.yProperty(), radius);
			proj.setHitbox(hitbox);
		return model;
	}
	
	public static Node node(EnemyProjectile proj, double radius){
		Circle model = new Circle(radius);
			model.centerXProperty().bind(proj.xProperty());
			model.centerYProperty().bind(proj.yProperty());
		HitboxCircle hitbox = new HitboxCircle(proj.xProperty(), proj.yProperty(), radius);
			proj.setHitbox(hitbox);
		return model;
	}

}
