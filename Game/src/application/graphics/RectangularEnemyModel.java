package application.graphics;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.enemy.DamagableFixedMovementGizmo;
import model.hitbox.HitboxPolygon;
import model.movement.CurvedMovement;

public class RectangularEnemyModel {
	
	public static Node rectangularEnemyModel(DamagableFixedMovementGizmo enemy, double x, double y){
		double width = 20.0, height = 15.0;
		Node model = new Rectangle(width,height);
			//model.relocate(x, y);
			model.translateXProperty().bind(enemy.xProperty());
			model.translateYProperty().bind(enemy.yProperty());
			
			
		enemy.setMovement( new CurvedMovement( enemy.xProperty(), enemy.yProperty(), X->50*Math.sin(X*2*Math.PI)+x, Y->50*Math.cos(Y*(2*Math.PI)) - 50 + y ));
	
	//draw rectangle with a polygon
		HitboxPolygon enemyHitbox = new HitboxPolygon(4);
			enemyHitbox.xVertex(0).bind( enemy.xProperty() );
			enemyHitbox.yVertex(0).bind( enemy.yProperty() );
			
			enemyHitbox.xVertex(1).bind( enemy.xProperty().add(width) );
			enemyHitbox.yVertex(1).bind( enemy.yProperty() );
			
			enemyHitbox.xVertex(2).bind( enemy.xProperty().add(width) );
			enemyHitbox.yVertex(2).bind( enemy.yProperty().add(height) );
			
			enemyHitbox.xVertex(3).bind( enemy.xProperty() );
			enemyHitbox.yVertex(3).bind( enemy.yProperty().add(height) );
			
			enemy.setHitbox(enemyHitbox);
	
		return model;
	}
}
