package model;
/*
 * gizmos have an observable location (relevant for converter)
 * */


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.hitbox.CollisionEvent;
import model.hitbox.Hitbox;

public abstract class Gizmo {
	
	private final DoubleProperty locationX, locationY;
	/**Since the hitbox should in some way align with the visuals to give the player feedback, 
	 * it is essentially the duty of the graphical modeling class to provide a hitbox via the setter
	 */
	
	protected Gizmo(double x, double y){
		locationX = new SimpleDoubleProperty(x);
		locationY = new SimpleDoubleProperty(y);
	}
	
	protected Gizmo(DoubleProperty x, DoubleProperty y){
		locationX = x;
		locationY = y;
	}
	
	//begin getters
	public double getX(){return locationX.getValue();}
	public double getY(){return locationY.getValue();}
	public DoubleProperty xProperty(){return this.locationX;}
	public DoubleProperty yProperty(){return this.locationY;}
	//end getters
	
	public abstract void setHitbox(Hitbox hitbox);
	
	//begin public services
	public abstract void spawn();
	public abstract int dealCollisionDamage();
	public abstract void handleCollision(CollisionEvent e);
	public boolean isProjectile(){return false;}
	//end public services
	
	//begin protected services
	protected abstract void despawn();
	//end protected services
	
}
