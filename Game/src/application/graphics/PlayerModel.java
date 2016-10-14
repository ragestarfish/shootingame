package application.graphics;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.hitbox.HitboxPolygon;
import model.player.Player;

//TODO: do I want an interface for this type of model class?
public class PlayerModel {

	public static Node playerNode(Player player, double x, double y){
		double width=10.0, height=10.0;
		Rectangle model = new Rectangle(width,height);
			model.relocate(x,y);
			player.xProperty().bind(model.translateXProperty());
			player.yProperty().bind(model.translateYProperty());
		
		//draw rectangle with a polygon
		HitboxPolygon playerHitbox = new HitboxPolygon(4);
			playerHitbox.xVertex(0).bind( player.xProperty() );
			playerHitbox.yVertex(0).bind( player.yProperty() );
			
			playerHitbox.xVertex(1).bind( player.xProperty().add(width) );
			playerHitbox.yVertex(1).bind( player.yProperty() );
			
			playerHitbox.xVertex(2).bind( player.xProperty().add(width) );
			playerHitbox.yVertex(2).bind( player.yProperty().add(height) );
			
			playerHitbox.xVertex(3).bind( player.xProperty() );
			playerHitbox.yVertex(3).bind( player.yProperty().add(height) );
			
			player.setHitbox(playerHitbox);
		
		return model;
	}

}
