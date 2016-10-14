package model.movement;

import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

/**
 * Represents movement in a straight line. Manipulates the DoubleProperties representing coordinates x and y.
 * Always starts from the current position when recieving the @code move command.
 * Default movement is a shift by coordinates.
 * Optional moving TO a point or TOWARDS a point.
 * @author mowe
 *
 */

public class StraightLineMovement extends CurvedMovement {

	private double byX;
	private double byY;
	private double speed;
	
	private double deltaX;
	private double deltaY;
	
	private boolean moveTo;
	private boolean moveTowards;
	
	public StraightLineMovement(double byX, double byY){
		super();
		this.byX=byX;
		this.byY=byY;
		startX = x.getValue();
		startY = y.getValue();
		moveTo = false;
		speed = 1;
		moveTowards=false;
		determineDeltas();
	}
	
	public StraightLineMovement(DoubleProperty x, DoubleProperty y, double byX, double byY){
		super(x,y);
		this.byX=byX;
		this.byY=byY;
		startX = x.getValue();
		startY = y.getValue();
		moveTo = false;
		speed = 1;
		moveTowards=false;
		determineDeltas();
	}
	
	public StraightLineMovement(DoubleProperty x, DoubleProperty y, double byX, double byY, Duration duration){
		super(x,y,duration);
		this.byX=byX;
		this.byY=byY;
		startX = x.getValue();
		startY = y.getValue();
		moveTo = false;
		speed = 1;
		moveTowards=false;
		determineDeltas();
	}
	
	public StraightLineMovement(DoubleProperty x, DoubleProperty y, double byX, double byY, Duration duration, double speed){
		super(x,y,duration);
		this.byX=byX;
		this.byY=byY;
		startX = x.getValue();
		startY = y.getValue();
		moveTo = false;
		moveTowards=false;
		this.speed= speed;
		determineDeltas();
	}
	
	/**Calling this function will cause the Movement to be TO the point (byX,byY)
	 */
	public void setToPoint(){moveTo = true; determineDeltas();}
	public void setTowards(){
		moveTo=true; moveTowards = true; determineDeltas();}
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	
	private double norm(double x, double y){
		return Math.sqrt(x*x+y*y);
	}
	
	@Override
	public void move() {
		//update start location
		startX = x.get();
		startY = y.get();
		//play
		play();
	}
	/*
	@Override
	protected void interpolate(double frac) {
			x.setValue( startX + frac * deltaX );
			y.setValue( startY + frac * deltaY );
		
	}*/
	
	private void determineDeltas(){
		if(moveTo == false){
			deltaX=byX;
			deltaY=byY;
		}
		else if(moveTowards == true){
			double n = norm(byX - startX,byY - startY);
			deltaX = (byX - startX)/n*speed;
			deltaY = (byY - startY)/n*speed;
		}
		else{
				deltaX = byX - startX;
				deltaY = byY - startY;
		}
		f = t->startX+t*deltaX;
		g = t->startY+t*deltaY;
	}
	
	
	
}
