package application;

import javafx.animation.Animation.Status;

import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import application.controls.Controls;
import application.graphics.Converter;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.effects.PartyCracker;
import model.enemy.SimpleCircularEnemy;
import model.enemy.SimpleDotEnemy;
import model.enemy.SimpleRectangularEnemy;
import model.enemy.Spawner;
import model.hitbox.CollisionDirector;
import model.movement.CurvedMovement;
import model.movement.Movement;
import model.movement.StraightLineMovement;
import model.player.Player;

@SuppressWarnings("unused")
public class EventHandlingController {
	
	
	@FXML
	private BorderPane pane;
	@FXML
	private TextArea debugTextArea;


	public EventHandlingController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void initialize(){
		Converter.setWindow(pane);
		CollisionDirector.check();
		Player playerOne = new Player(0, 0);
		playerOne.spawn();
		
		double xStart = 100.0;
		double yStart = 100.0;
		
		
		Integer[] numbers = {500, 1000,1100,1200, 1300, 1400, 1500,4000,6000, 7000, 8000, 8500};
		
		
		
		SimpleRectangularEnemy enemy1 = new SimpleRectangularEnemy(150.0,150.0);
		SimpleCircularEnemy enemy2 = new SimpleCircularEnemy(100.0,100.0, 4, 500, Arrays.asList(numbers) 
						, playerOne.xPropertyUnmodifiable(), playerOne.yPropertyUnmodifiable());
		SimpleDotEnemy enemy3 = new SimpleDotEnemy(100.0,50.0);
		PartyCracker party = new PartyCracker(2000, 200, 100, 7, 50, new Point2D(-100,-250), new Point2D(0,900));
		
		enemy1.spawn();
		enemy3.spawn();
		
		Platform.runLater(() -> enemy1.move());
		enemy3.setMovement(new StraightLineMovement(enemy3.xProperty(), enemy3.yProperty(), 500.0, 0.0));
		Platform.runLater( () -> enemy3.move());
	
		/*
		enemy1.healthProperty().addListener(listener ->
		debugTextArea.setText(String.format("Invalidation fired, health is now %d\n", enemy1.getHealth()) )
		);
		
		enemy2.healthProperty().addListener((value,oldvalue,newValue)-> 
		debugTextArea.setText(String.format("Change fired, health is now %d\n", enemy2.getHealth()) ));*/
		
		Spawner.start();
		
		/*
		playerOne.xProperty().addListener( (obs,newValue,oldValue)->{
			debugTextArea.setText(String.format("player: %.02f\nmodel: %.02f\nlayout: %.02f",newValue,playerNode.getTranslateX(),playerNode.getLayoutX()) );
		
		});*/
		/*
		playerNode.translateXProperty().addListener( (obs,newValue,oldValue)->{
			debugTextArea.setText(String.format("player: %.02f, %.02f\nmodel: %.02f, %.02f\nlayout: %.02f, %.02f\n hitbox: %s",
					playerOne.getX(), playerOne.getY(),
					playerNode.getTranslateX(), playerNode.getTranslateY(),
					playerNode.getLayoutX(), playerNode.getLayoutY(),
					playerOne.hitboxString())  );
		});*/
	}

}
