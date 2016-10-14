package weapon.concrete;

import java.util.Random;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import model.movement.StraightLineMovement;
import weapon.EnemyProjectile;
import weapon.projectile.SimpleCircularProjectile;

public class PartialCircleWeapon extends CircularWeapon {

	protected int angle;
	protected int spread;
	private int variance=0;
	
	public PartialCircleWeapon(ReadOnlyDoubleProperty enemyX, ReadOnlyDoubleProperty enemyY , int pieces, int angle, int spread) {
		super(enemyX, enemyY);
		this.pieces = pieces;
		this.angle=angle;
		this.spread=spread;
		setProjectile( new SimpleCircularProjectile(enemyX.get(),enemyY.get(),
					new StraightLineMovement(new SimpleDoubleProperty(),new SimpleDoubleProperty(),direction.getX(),direction.getY())) );
		rotateDirectionBy((double) angle);
	}
	
	public PartialCircleWeapon(ReadOnlyDoubleProperty enemyX, ReadOnlyDoubleProperty enemyY , int pieces, int angle, int spread, boolean randomized) {
		super(enemyX, enemyY);
		this.pieces = pieces;
		this.angle=angle;
		this.spread=spread;
		setProjectile( new SimpleCircularProjectile(enemyX.get(),enemyY.get(),
					new StraightLineMovement(new SimpleDoubleProperty(),new SimpleDoubleProperty(),direction.getX(),direction.getY())) );
		rotateDirectionBy((double) angle);
	}
	
	//represents angle deviation by +- variance
	public void setRandomized(int variance){this.variance=variance;}
	
	@Override
	public void fireWeapon(){
		double oldX = direction.getX();
		double oldY = direction.getY();
		if(variance != 0){
			Random randomNumber = new Random();
			rotateDirectionBy( (double) (variance - 2*randomNumber.nextInt(variance+1)) );
		}
		if( pieces % 2 == 0 ){
			rotateDirectionBy( -(double) spread/2+ spread/(pieces+1) );

			for(int i=0;i<pieces;i++){
				shootInCurrentDirection();
				rotateDirectionBy( (double) spread/(pieces+1));
			}
		}
		else{
			shootInCurrentDirection();
			rotateDirectionBy( - (double) spread/2 );
			for(int i=0;i<pieces;i++){
				if( i!=(pieces-1)/2 ){
					shootInCurrentDirection();
				}
				rotateDirectionBy((double) spread/(pieces-1));
			}
		}//end else
		
		direction = new Point2D(oldX,oldY);
		
	}
	
	private void shootInCurrentDirection(){
		EnemyProjectile proj = getProjectile().copy();
		proj.updateSpawnLocation(enemyX.get(), enemyY.get());
		proj.setMovement(new StraightLineMovement(proj.xProperty(),proj.yProperty(),direction.getX(),direction.getY()));
		proj.spawn();
	}
	
}
