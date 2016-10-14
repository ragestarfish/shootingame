package application.graphics;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.enemy.SimpleCircularEnemy;
import model.hitbox.HitboxCircle;
import model.movement.CurvedMovement;
import model.movement.Movement;

public class CircularEnemyModel {
	
	
	public static Node enemyNode(SimpleCircularEnemy enemy){
		double radius = 15.0; //for now fixed radius
		//TODO: figure out if I want radius property in enemy for dynamic radius
		Pane container = new Pane();
		
		Circle model = new Circle(radius);
			model.centerXProperty().bind(enemy.xProperty());
			model.centerYProperty().bind(enemy.yProperty());
			model.setStyle("-fx-fill: red");
		
		/*double xStart = enemy.getX();
		double yStart = enemy.getY();
		CurvedMovement moving = new CurvedMovement( enemy.xProperty(), enemy.yProperty(), X->50*X*Math.sin(X*2*Math.PI)+xStart, Y->50*Y*Math.cos(Y*(2*Math.PI)) - 50 + yStart );
		moving.setCycleCount(5);
		moving.setAutoReverse(true);
				enemy.setMovement(moving);*/
		
		Label health = new Label("Hello there!");
			health.setText(String.valueOf(enemy.getHealth()));
			health.layoutXProperty().bind(enemy.xProperty());
			health.layoutYProperty().bind(enemy.yProperty());
			
		HitboxCircle hitbox = new HitboxCircle(enemy.getX(), enemy.getY(), radius);
			hitbox.centerXProperty().bind(enemy.xProperty());
			hitbox.centerYProperty().bind(enemy.yProperty());
		enemy.setHitbox(hitbox);
			
		container.getChildren().add(model);
		container.getChildren().add(health);
		return container;
	}
}
