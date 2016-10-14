package model.enemy;

import java.util.ArrayList;
import java.util.List;

import application.graphics.Converter;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import model.movement.CircularMovement;
import model.movement.CurvedMovement;
import model.movement.FreeFalling;
import model.movement.Movement;
import model.movement.SequentialMovement;
import model.movement.StraightLineMovement;
import weapon.EnemyWeapon;
import weapon.PlayerWeapon;
import weapon.concrete.ArtilleryWeapon;
import weapon.concrete.CircularWeapon;
import weapon.concrete.CompositeWeapon;
import weapon.concrete.HomingWeapon;
import weapon.concrete.PartialCircleWeapon;
import weapon.concrete.SpiralWeapon;

public class SimpleCircularEnemy extends BeatShooter {
	
	private ReadOnlyDoubleProperty xPlayer;
	private ReadOnlyDoubleProperty yPlayer;

	public SimpleCircularEnemy(double x, double y,int health,int spawnTime, List<Integer> shootTimes ) {
		super(x, y, health, spawnTime, shootTimes);
	}
	public SimpleCircularEnemy(double x, double y,int health,int spawnTime, List<Integer> shootTimes ,ReadOnlyDoubleProperty xPlayer, ReadOnlyDoubleProperty yPlayer ) {
		super(x, y, health, spawnTime, shootTimes);
		this.xPlayer = xPlayer;
		this.yPlayer = yPlayer;
		defineWeapon();
	}
	/*
	@Override
	protected void defineWeapon(){
		if(this.xPlayer == null || this.yPlayer == null)
			return;
		PartialCircleWeapon weapon = new PartialCircleWeapon(xPropertyUnmodifiable(),yPropertyUnmodifiable(),8, 0, 90);
		weapon.setRandomized(0);
		setWeapon( weapon );
	}*/
	
	@Override
	protected void defineWeapon(){
		
		if(this.xPlayer == null || this.yPlayer == null)
			return;
		/*
		CompositeWeapon composite = new CompositeWeapon();
		composite.add(new HomingWeapon(this.xPropertyUnmodifiable(), this.yPropertyUnmodifiable(), xPlayer, yPlayer));
		composite.add(new ArtilleryWeapon( this.xPropertyUnmodifiable(), this.yPropertyUnmodifiable(),new Point2D(100,-200),new Point2D(0,700)));;
		setWeapon( composite );*/
		/*if(this.xPlayer == null || this.yPlayer == null)
			return;
		EnemyWeapon weapon = new ArtilleryWeapon( this.xPropertyUnmodifiable(), this.yPropertyUnmodifiable(),new Point2D(100,-200),new Point2D(0,700));
		setWeapon( weapon );*/
		setWeapon( new SpiralWeapon(this.xPropertyUnmodifiable(), this.yPropertyUnmodifiable(), 5  ));
	}
	
	@Override
	public void shoot(){
	/*	CompositeWeapon wep = (CompositeWeapon) weapon;
		wep.fireWeapon();
		wep.switchTo((wep.current()+1)%wep.getNumberOfWeapons());*/
		weapon.fireWeapon();
	}

	@Override
	protected void defineMovement(){
		double xStart = xProperty().get();
		double yStart = yProperty().get();
		
		StraightLineMovement straight = new StraightLineMovement(xProperty(),yProperty(), 50, 0,Duration.millis(1000));
		CurvedMovement moving = new CircularMovement( xProperty(), yProperty(), xStart+50, yStart, 50  );
		moving.setCycleCount(2);
		moving.setAutoReverse(true);
		StraightLineMovement straight2 = new StraightLineMovement(xProperty(),yProperty(), -50, 0,Duration.millis(1000));
		CurvedMovement fall = new FreeFalling(xProperty(),yProperty(),new Point2D(100,-200), new Point2D(0,700));
		SequentialMovement sequence = new SequentialMovement();
		sequence.add(straight);
		sequence.add(moving);
		sequence.add(straight2);
		sequence.add(fall);
		setMovement(sequence);
		/*
		CurvedMovement moving = new CircularMovement( xProperty(), yProperty(), xStart+50, yStart, 50  );
		CurvedMovement fall = new FreeFalling(xProperty(),yProperty(),new Point2D(100,-200), new Point2D(0,700));
		StraightLineMovement straight2 = new StraightLineMovement(xProperty(),yProperty(), 150, 0,Duration.millis(3000));
		setMovement(fall.add(moving));
		*/
	}


	@Override
	protected void registerWithConverter() {
		Converter.spawn(this);
	}

	@Override
	public int dealCollisionDamage() {
		// TODO Auto-generated method stub
		return 1;
	}

}
