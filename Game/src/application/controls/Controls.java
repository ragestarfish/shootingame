package application.controls;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import model.player.Player;

public class Controls {
	private static final int DEFAULT_X_SPEED = 100;
	private static final int DEFAULT_Y_SPEED = 100;
	private static final int DEFAULT_DURATION_MILLIS = 1000;
	
	private static TranslateTransition playerMovement;
	private static SimpleIntegerProperty xSpeed, ySpeed;
	private static Node playerSprite;
	private static Parent pane;
	private static Player player;
	
	
	public static void initialize(Player player , Node playerSprite, Parent pane){
		Controls.player = player;
		Controls.playerSprite = playerSprite;
		xSpeed = new SimpleIntegerProperty(DEFAULT_X_SPEED);
		ySpeed = new SimpleIntegerProperty(DEFAULT_Y_SPEED);
		Controls.pane = pane;
	}
	
	
	public static void setPlayerControls(){
		if( playerSprite == null || pane == null)
			throw new NullPointerException("Pane or player sprite was null when attempting to set controls");
		xSpeed = new SimpleIntegerProperty(DEFAULT_X_SPEED);
		ySpeed = new SimpleIntegerProperty(DEFAULT_Y_SPEED);
		pane.requestFocus();
		playerMovement = new TranslateTransition(Duration.millis(DEFAULT_DURATION_MILLIS), playerSprite);
		playerMovement.setInterpolator( Interpolator.LINEAR );
		
		pane.setOnMouseClicked(value -> pane.requestFocus());
		
		pane.setOnKeyPressed( e -> {
			if(e.getCode() == KeyCode.RIGHT){
				playerMovement.stop();
				playerMovement.setByX(xSpeed.getValue());
				playerMovement.play();
			}
			if(e.getCode() == KeyCode.LEFT){
				playerMovement.stop();
				playerMovement.setByX(-xSpeed.getValue());
				playerMovement.play();
			}
			if(e.getCode() == KeyCode.UP){
				playerMovement.stop();
				playerMovement.setByY(-ySpeed.getValue());
				playerMovement.play();
			}
			if(e.getCode() == KeyCode.DOWN){
				playerMovement.stop();
				playerMovement.setByY(ySpeed.getValue());
				playerMovement.play();
			}	
			
			if(e.getCode() == KeyCode.Z){
				player.shoot();
			}
			
		});
		
		
		pane.setOnKeyReleased( e -> {
			if(e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT){
				playerMovement.stop();
				playerMovement.setByX(0);
				playerMovement.play();
			}
			if(e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN){
				playerMovement.stop();
				playerMovement.setByY(0);
				playerMovement.play();
			}
		});
		
		
		
	
	}

}
