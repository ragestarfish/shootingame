package weapon.concrete;

import java.util.Random;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import model.movement.StraightLineMovement;
import weapon.EnemyProjectile;
import weapon.EnemyWeapon;
import weapon.projectile.SimpleCircularProjectile;

public class CircularWeapon extends EnemyWeapon {

	protected Point2D direction = new Point2D(0,600);
	protected Integer pieces;
	
	public CircularWeapon(ReadOnlyDoubleProperty enemyX, ReadOnlyDoubleProperty enemyY, int pieces){
		super(enemyX,enemyY);
		this.pieces = pieces;
		setProjectile(new SimpleCircularProjectile(enemyX.get(),enemyY.get(),new StraightLineMovement(new SimpleDoubleProperty(),new SimpleDoubleProperty(),direction.getX(),direction.getY())) );
	}
	
	protected CircularWeapon(ReadOnlyDoubleProperty enemyX, ReadOnlyDoubleProperty enemyY){
		super(enemyX,enemyY);
	}
	

	@Override
	public void fireWeapon() {
		Random randomAngle = new Random();
		rotateDirectionBy( (double) randomAngle.nextInt(360/pieces) );
		for(int i=0;i<pieces;i++){
			EnemyProjectile proj = getProjectile().copy();
			proj.updateSpawnLocation(enemyX.get(), enemyY.get());
			rotateDirectionBy((double) 360/pieces);
			proj.setMovement(new StraightLineMovement(proj.xProperty(),proj.yProperty(),direction.getX(),direction.getY()));
			proj.spawn();
		}
	}

	
	protected void rotateDirectionBy(double angle){
		//rotate direction vector
		direction = new Point2D( Math.cos(2*Math.PI/360*angle)*direction.getX() - Math.sin(2*Math.PI/360*angle)*direction.getY() 
						, Math.sin(2*Math.PI/360*angle)*direction.getX() + Math.cos(2*Math.PI/360*angle)*direction.getY() );
	}
	
}
