package model.movement;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class FreeFalling extends CurvedMovement {
	
	//private Point2D gravity;
	//private Point2D v0;
	private double speed;

	public FreeFalling(Duration duration) {
		super(duration);
		// TODO Auto-generated constructor stub
	}

	public FreeFalling(DoubleProperty x, DoubleProperty y, Point2D v0, Point2D gravity) {
		super(x, y);
		
		speed=1;
		//this.v0=v0;
		//this.gravity = gravity;
		startX = 0;
		startY = 0;
		f = t -> startX+v0.getX()*speed*t+0.5*gravity.getX()*speed*t*speed*t;
		g = t -> startY+v0.getY()*speed*t+0.5*gravity.getY()*speed*t*speed*t;
	}

	public void setSpeed(double speed){this.speed=speed;}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		startX = x.get();
		startY = y.get();
		play();
	}
}
