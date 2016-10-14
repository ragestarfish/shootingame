package weapon.concrete;

import java.util.Random;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;
import model.movement.CurvedMovement;
import model.movement.Standstill;
import model.movement.StraightLineMovement;
import weapon.EnemyProjectile;
import weapon.EnemyWeapon;
import weapon.projectile.SimpleCircularProjectile;

public class SpiralWeapon extends EnemyWeapon {
	
	private double angularVelocity;
	private double expansionRate;
	private double pieces;
	private double levels;


	public SpiralWeapon(ReadOnlyDoubleProperty enemyX, ReadOnlyDoubleProperty enemyY, int pieces) {
		super(enemyX, enemyY);
		this.pieces = pieces;
		levels = 15;
		angularVelocity = 1;
		expansionRate = 50;
		setProjectile(new SimpleCircularProjectile(enemyX.get(),enemyY.get(),new StraightLineMovement(new SimpleDoubleProperty(),new SimpleDoubleProperty(),0,0)) );
	}

	@Override
	public void fireWeapon() {
		for(double k=0;k<levels;k++){
			for(double i=0;i<pieces;i++){
				Double theta = i / pieces;
				Double r =  expansionRate * k / levels;
				Double gamma =  0.2+k/levels;
				circleSpawn(r, theta, gamma);
				}
		}
		

	}
	
	private void circleSpawn(double r, double theta, double gamma){
		double x0 = enemyX.get();
		double y0 = enemyY.get();
		
		EnemyProjectile proj = getProjectile().copy();
		proj.setMovement(new Standstill(Duration.millis(1000)));
		proj.updateSpawnLocation(x0 + r * Math.cos((theta+gamma)*2*Math.PI), y0 + r * Math.sin((theta+gamma)*2*Math.PI));
		proj.spawn();
	}

}
