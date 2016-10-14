package model.enemy;

import model.EnemylikeDamagableGizmo;
import model.movement.Movement;

public abstract class DamagableFixedMovementGizmo extends EnemylikeDamagableGizmo {

	private Movement movement;
	
	protected DamagableFixedMovementGizmo(double x, double y) {
		super(x, y);
	}
	
	protected DamagableFixedMovementGizmo(double x, double y, int health) {
		super(x, y, health);
	}
	
	public void move(){movement.move();}
	public void stop(){movement.stop();}
	public void setMovement(Movement movement){	this.movement = movement;}
	
	

}
