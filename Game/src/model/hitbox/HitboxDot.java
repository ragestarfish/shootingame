package model.hitbox;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;

public class HitboxDot implements Hitbox{
	
	//a dot is a point, it only knows where it lies
	private final DoubleProperty x;
	private final DoubleProperty y;
	
	public HitboxDot(double x, double y){
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
	}
	
	public double getX(){return this.x.getValue();}
	public double getY(){return this.y.getValue();}
	public DoubleProperty xProperty(){return this.x;}
	public DoubleProperty yProperty(){return this.y;}
	
	
	//Hitbox interface
	@Override
	public boolean intersects(Hitbox visitor) {return visitor.intersectsWithDot(this);}

	@Override
	public boolean intersectsWithCircle(HitboxCircle circle) {
		//a dot "intersects" with a circle if it lies within
		return circle.intersectsWithDot(this);
	}

	@Override
	public boolean intersectsWithDot(HitboxDot dot) {
		return false;
	}

	@Override
	public boolean intersectsWithPolygon(HitboxPolygon polygon) {
		// TODO test	
			  List<Point2D> points = polygon.getVertices();
		      int i;
		      int j;
		      int length = points.size();
		      double testX = x.getValue();
		      double testY = y.getValue();
		      boolean result = false;
		      for (i = 0, j = length - 1; i < length; j = i++) {
		        if ((points.get(i).getY() > testY) != (points.get(j).getY() > testY) &&
		            (testX < (points.get(j).getX() - points.get(i).getX()) * (testY - points.get(i).getY()) / (points.get(j).getY()-points.get(i).getY()) + points.get(i).getX())) {
		          result = !result;
		         }
		      }
		      return result;
	}
	//end Hitbox interface

	@Override
	public boolean intersectsWithComposite(HitboxComposite comp) {
		return comp.intersectsWithDot(this);
	}
	  
}//end class
