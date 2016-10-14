package model.effects;

import java.util.Random;

import javafx.geometry.Point2D;
import weapon.concrete.ArtilleryWeapon;

public class PartyCracker extends AbstractLocalEffect {
	
	private final ArtilleryWeapon weapon;
	private final int shots;
	private int spread;
	private Point2D v0;


	public PartyCracker(int time, double x, double y,int shots, int spread, Point2D v0, Point2D gravity) {
		super(time, x, y);
		this.shots=shots;
		this.spread = spread;
		this.v0 = v0;
		weapon = new ArtilleryWeapon(this.xPropertyUnmodifiable(), this.yPropertyUnmodifiable(),v0,gravity);
	}

	

	@Override
	public void playEffect() {
		// TODO Auto-generated method stub
		Random randomNumbers = new Random();
		for(int i=0;i<shots;i++){
			Point2D randomVector = new Point2D(2*randomNumbers.nextInt(spread)-spread,2*randomNumbers.nextInt(spread)-spread);
			randomVector = randomVector.add(v0);
			weapon.adjustVelocity(randomVector);
			weapon.fireWeapon();
		}
	}

}
