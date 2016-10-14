package weapon;

import model.EnemylikeGizmo;
import model.hitbox.CollisionEvent;
import model.movement.Movement;

public abstract class EnemyProjectile extends EnemylikeGizmo {

	private Movement movement;
	@SuppressWarnings("unused")
	private boolean alive;
	
	public EnemyProjectile(double locationX, double locationY) {
		super(locationX, locationY);
		this.alive=true;
	}
	
	public EnemyProjectile(double locationX, double locationY, Movement movement) {
		super(locationX, locationY);
		setMovement(movement);
		this.alive=true;
	}
	
	public abstract EnemyProjectile copy();
	protected abstract void registerWithConverter();
	
	@Override
	public boolean isProjectile(){return true;}
	
	@Override
	public void spawn(){
		registerWithConverter();
		getMovement().move();
	}
	
	public void setMovement(Movement movement){this.movement=movement;
	getMovement().onFinished(handler -> {
		if(alive=true){
			despawn();
			alive=false;
		}
	});}
	public Movement getMovement(){return this.movement;}
	public void updateSpawnLocation(double x, double y){
		xProperty().set(x);
		yProperty().set(y);
	}
	
	@Override
	public void handleCollision(CollisionEvent e) {
		if( e.getSource().isProjectile() == false ){
		despawn();
		this.alive=false;
		}
	}
}
