package application.graphics;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import model.enemy.SimpleCircularEnemy;
import model.enemy.SimpleDotEnemy;
import model.enemy.SimpleRectangularEnemy;
import model.hitbox.CollisionDirector;
import model.movement.StraightLineMovement;
import model.player.Player;
import weapon.projectile.DefaultPlayerProjectile;
import weapon.projectile.SimpleCircularProjectile;
import model.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import application.controls.Controls;

/**
 * Converts model elements to graphical objects. Provides overloaded spawn functionality for
 * every entity that should be spawned to the static window member. 
 * @author mowe
 *
 */

public class Converter {

	private static Pane window;	
	private static Map<Object,Node> map = Collections.synchronizedMap(new HashMap<Object,Node>());
	
	
	public static synchronized void setWindow(Pane window){
		Converter.window = window;
	}
	
	private static synchronized void addNode(Object o, Node node){
		window.getChildren().add(node);
		map.put(o, node);
	}
	
	//despawning is the same for all gizmos
	public static synchronized void despawn(Object o){
		if(map.containsKey(o)){
			Platform.runLater(new Runnable(){
				
				public void run(){
					if(map.get(o) != null){
					map.get(o).setVisible(false);
					//TODO: remove
					if(!window.getChildren().remove( map.get(o) ))
						System.out.println("error removing?");
					}
					map.remove(o);
				}
			});
		}
	}
	
	
//overloaded spawn method for each spawnable class
	public static void spawn(Player player, double x, double y){
		Node playerModel = PlayerModel.playerNode(player,x,y);
		Controls.initialize(player,playerModel, window);
		Controls.setPlayerControls();
		Platform.runLater(() -> addNode(player,playerModel));
		//addNode(player,playerModel);
	}
	
	public static void spawn(SimpleCircularEnemy enemy){
		//addNode(enemy, CircularEnemyModel.enemyNode(enemy) );
		Platform.runLater(() -> addNode(enemy, CircularEnemyModel.enemyNode(enemy) ));
	}
	
	public static void spawn(SimpleRectangularEnemy enemy, double x, double y){
		//addNode(enemy, RectangularEnemyModel.rectangularEnemyModel(enemy,x,y));
		Platform.runLater(() -> addNode(enemy, RectangularEnemyModel.rectangularEnemyModel(enemy,x,y)));
	}
	
	public static void spawn(SimpleDotEnemy enemy, double x, double y){
		//addNode(enemy, DotEnemyModel.dotNode(enemy, x, y));
		Platform.runLater(() -> addNode(enemy, DotEnemyModel.dotNode(enemy, x, y)));
	}

	public static void spawn(DefaultPlayerProjectile proj, double radius) {
		Platform.runLater(() -> addNode(proj,CircularProjectileModel.node(proj, radius)));
	}
	
	public static void spawn(SimpleCircularProjectile proj, double radius) {
		Platform.runLater(() -> addNode(proj,CircularProjectileModel.node(proj, radius)));
	}
}//end converter
