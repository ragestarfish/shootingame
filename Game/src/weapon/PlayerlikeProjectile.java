package weapon;

import model.PlayerlikeGizmo;
import model.hitbox.CollisionEvent;
import model.movement.Movement;

public abstract class PlayerlikeProjectile extends PlayerlikeGizmo {

	protected Movement movement;
	@SuppressWarnings("unused")
	private boolean alive;
	
	/*public PlayerlikeProjectile(double x,double y) {
		super(x,y);
		// TODO Auto-generated constructor stub
	}*/

	public PlayerlikeProjectile(double x,double y) {
		super(x,y);
		this.alive=true;
		// TODO Auto-generated constructor stub
	}
	
	protected PlayerlikeProjectile(double x, double y, Movement movement){
		this(x,y);
		setMovement(movement);
	}
	
	public abstract PlayerlikeProjectile copy();
	protected abstract void registerWithConverter();
	
	@Override
	public boolean isProjectile(){return true;}
	
	@Override
	public void spawn() {
		registerWithConverter();
		getMovement().move();
	}
	
	public void setMovement(Movement movement){
		this.movement=movement;
		if(movement != null){
		getMovement().onFinished(handler -> {
			if(alive=true){
				despawn();
				alive=false;
			}
		});
		}
	}
	public Movement getMovement(){return this.movement;}
	public void updateSpawnLocation(double x, double y){
		xProperty().set(x);
		yProperty().set(y);
	}
	
	@Override
	public void handleCollision(CollisionEvent e) {
		if(e.getSource().isProjectile() == false){
			despawn();
			this.alive=false;
		}
	}
	
}
