package application.graphics;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import model.enemy.SimpleDotEnemy;
import model.hitbox.HitboxDot;
import model.movement.StraightLineMovement;

public class DotEnemyModel {

	public DotEnemyModel() {
		// TODO Auto-generated constructor stub
	}

	public static Node dotNode(SimpleDotEnemy enemy, double x, double y){
		Circle model = new Circle(3.0);
			model.centerXProperty().bind(enemy.xProperty());
			model.centerYProperty().bind(enemy.yProperty());
		
		/*StraightLineMovement move = new StraightLineMovement(enemy.xProperty(), enemy.yProperty(), 500.0, 0.0);	
			enemy.setMovement(move);*/
			
		HitboxDot hitbox = new HitboxDot(x,y);
			hitbox.xProperty().bind(enemy.xProperty());
			hitbox.yProperty().bind(enemy.yProperty());
			enemy.setHitbox(hitbox);
			
		return model;
	}
	
}
