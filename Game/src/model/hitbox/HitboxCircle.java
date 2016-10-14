package model.hitbox;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class HitboxCircle implements Hitbox {
	
	private final DoubleProperty centerX;
	private final DoubleProperty centerY;
	private final DoubleProperty radius;
	
	public HitboxCircle(double centerX, double centerY, double radius){
		this.centerX = new SimpleDoubleProperty(centerX);
		this.centerY = new SimpleDoubleProperty(centerY);
		this.radius = new SimpleDoubleProperty(radius);
	}
	
	public HitboxCircle(DoubleProperty x, DoubleProperty y, double radius){
		this.radius = new SimpleDoubleProperty(radius);
		this.centerX = new SimpleDoubleProperty(x.getValue());
		this.centerY = new SimpleDoubleProperty(y.getValue());
		
		this.centerX.bind(x);
		this.centerY.bind(y);
	}
	
	public DoubleProperty centerXProperty(){return centerX;}
	public DoubleProperty centerYProperty(){return centerY;}
	public DoubleProperty radiusProperty(){return radius;}
	public double getCenterX(){return centerX.getValue();}
	public double getCenterY(){return centerY.getValue();}
	public double getRadius(){return radius.getValue();}
	
	public Circle getCircle(){return toCircle();}
	
	private Circle toCircle(){
		return new Circle(getCenterX(), getCenterY(), getRadius());
	}
	
	//Hitbox interface
	@Override
	public boolean intersects(Hitbox visitor) {return visitor.intersectsWithCircle(this);}

	
	//UNTESTED
	@Override
	public boolean intersectsWithCircle(HitboxCircle partnerCircle) {
		// TODO Auto-generated method stub
		double partnerRadius = partnerCircle.getRadius();
		double partnerCenterX = partnerCircle.getCenterX();
		double partnerCenterY = partnerCircle.getCenterY();
		if( new Point2D(partnerCenterX, partnerCenterY)
				.distance(new Point2D(this.getCenterX(), this.getCenterY()) ) < partnerRadius+this.getRadius() )
			return true;
		
		return false;
	}

	//UNTESTED
	@Override
	public boolean intersectsWithDot(HitboxDot dot) {
		Circle helpCircle = toCircle();
		return helpCircle.contains(helpCircle.parentToLocal(dot.getX(), dot.getY()));
	}

	@Override
	public boolean intersectsWithPolygon(HitboxPolygon polygon) {
		return polygon.intersectsWithCircle(this);
	}
	//end hitbox interface

	@Override
	public boolean intersectsWithComposite(HitboxComposite comp) {
		return comp.intersectsWithCircle(this);
	}
	
	
}
