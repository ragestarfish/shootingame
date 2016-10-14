package weapon.projectile;

import application.graphics.Converter;
import model.movement.Movement;
import model.movement.StraightLineMovement;
import weapon.PlayerlikeProjectile;

public class DefaultPlayerProjectile extends PlayerlikeProjectile {

	private final double DEFAULT_RADIUS = 3.0;

	private double radius = DEFAULT_RADIUS;
	
	public DefaultPlayerProjectile(double locationX, double locationY) {
		
		super(locationX, locationY);
	}
	/*public DefaultPlayerProjectile(double locationX, double locationY, double radius) {
		super(locationX, locationY);
		this.radius = radius;
		// TODO Auto-generated constructor stub
	}*/
	
	public DefaultPlayerProjectile(double locationX, double locationY, Movement movement){
		super(locationX,locationY,movement);
	}
	
	//copy constr
	protected DefaultPlayerProjectile(PlayerlikeProjectile p){
		super(p.getX(), p.getY(),p.getMovement());
	}
	
	public double getRadius(){return this.radius;}
	
	@Override
	protected void registerWithConverter(){Converter.spawn(this,radius);}

	@Override
	public int dealCollisionDamage() {
		return 1;
	}
	
	@Override
	public DefaultPlayerProjectile copy(){
		return (DefaultPlayerProjectile) new DefaultPlayerProjectile(this);
	}

}
