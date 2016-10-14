package weapon.projectile;

import application.graphics.Converter;
import model.movement.Movement;
import weapon.EnemyProjectile;

public class SimpleCircularProjectile extends EnemyProjectile {

	private final double DEFAULT_RADIUS = 3.0;

	private double radius = DEFAULT_RADIUS;
	
	public SimpleCircularProjectile(double locationX, double locationY) {
		//sets movement null
		super(locationX, locationY,null);
	}
	/*public DefaultPlayerProjectile(double locationX, double locationY, double radius) {
		super(locationX, locationY);
		this.radius = radius;
		// TODO Auto-generated constructor stub
	}*/
	
	public SimpleCircularProjectile(double locationX, double locationY, Movement movement){
		super(locationX,locationY,movement);
	}
	
	//copy constr
	protected SimpleCircularProjectile(EnemyProjectile p){
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
	public SimpleCircularProjectile copy(){
		return (SimpleCircularProjectile) new SimpleCircularProjectile(this);
	}

}