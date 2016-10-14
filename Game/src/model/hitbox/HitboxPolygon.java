package model.hitbox;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;

public class HitboxPolygon implements Hitbox {
	
	private final List<DoubleProperty> propertyList;
	
	/**
	 * Creates a List of nVertices vertex points initialized to (0.0,0.0)
	 * @param vertices
	 */
	public HitboxPolygon(int nVertices){
		propertyList = new ArrayList<DoubleProperty>(nVertices*2);
		for(int i=0;i<2*nVertices;i++)
			propertyList.add(new SimpleDoubleProperty(42.69));
	}
	
	public HitboxPolygon(double...point){
		propertyList = new ArrayList<DoubleProperty>(point.length/2);
		for(int i=0;i<point.length;i+=2){
			propertyList.add(new SimpleDoubleProperty(point[i]));
			propertyList.add(new SimpleDoubleProperty(point[i+1]));
		}
	}
	
	/**Getter for the i-th vertex x coordinate property
	 * @param i Index of the vertex
	 * @return The double property logically associated with the x-coordinate of the i-th vertex
	 */
	public DoubleProperty xVertex(int i){
		return propertyList.get(2*i);
	}
	/**Getter for the i-th vertex y coordinate property
	 * @param i Index of the vertex
	 * @return The double property logically associated with the y-coordinate of the i-th vertex
	 */
	public DoubleProperty yVertex(int i){
		return propertyList.get(2*i+1);
	}
	
	private List<Point2D> pointsAsList(){
		int listSize = propertyList.size();
		ArrayList<Point2D> result = new ArrayList<Point2D>(listSize);
		for(int i=0;i<listSize;i+=2){
			Point2D newPoint = new Point2D(propertyList.get(i).getValue(), propertyList.get(i+1).getValue());
			result.add(newPoint);
		}
		return result;
	}
	
	public List<Point2D> getVertices() {return this.pointsAsList();}
	/*
	//TODO: remove
	@Override
	public String toString(){
		StringBuilder wip = new StringBuilder(propertyList.size());
		for(int i=0;i<propertyList.size()-1;i+=2)
		{
			wip.append("("+propertyList.get(i).getValue()+"), ("+propertyList.get(i+1).getValue()+")");
			wip.append("\n");
		}
		
		return wip.toString();
	}*/
	
	@Override
	public String toString(){
		return pointsAsList().toString();
	}
	
	//Hitbox interface
	@Override
	public boolean intersects(Hitbox visitor) {return visitor.intersectsWithPolygon(this);}

	@Override
	public boolean intersectsWithCircle(HitboxCircle c) {
		List<Point2D> points = pointsAsList();
		Point2D circleCenter = new Point2D(c.getCenterX(), c.getCenterY());
		
		//find pair of points closest to the circle
		
		TreeMap<Double,Integer> map = new TreeMap<>();
		for(int i=0;i<points.size();i++){
			map.put( points.get(i).distance(circleCenter), i);
		}
		int index = map.pollFirstEntry().getValue() , index2;
		//find out which index is the partner to draw the line with
			if(index != 0){
				if(points.get((index+1)%points.size()).distance(circleCenter) < 
					points.get((index-1)%points.size()).distance(circleCenter))
					index2 = (index+1)%points.size();
				else
					index2 = (index-1)%points.size();
			}else{
				if(points.get(1).distance(circleCenter) < 
						points.get(points.size()-1).distance(circleCenter))
					index2 = 1;
				else
					index2 = points.size()-1;
			}
		//	draw line between pair of points.
		//	determine the distance between the center of the circle and the line
		//	if this distance < radius, it must intersect with polygon
			Point2D lineVector = points.get(index).subtract(points.get(index2)).normalize();
			double distance = points.get(index).subtract(circleCenter).subtract( 
										lineVector.multiply(
												points.get(index).subtract(circleCenter).dotProduct(lineVector)
										) 
								).magnitude();
			if( distance < c.getRadius())
				return true;
			

		//if above test fails, check if center dot is within polygon
			return points.stream().anyMatch( point -> point.distance(circleCenter)<c.getRadius() );
	}

	@Override
	public boolean intersectsWithDot(HitboxDot d) {return d.intersectsWithPolygon(this);}
	
	@Override
	public boolean intersectsWithPolygon(HitboxPolygon p) {
		return p.getVertices().stream().anyMatch( testPoint -> {
			      int i;
			      int j;
			      List<Point2D> points = pointsAsList();
			      int length = points.size();
			      double testX = testPoint.getX();
			      double testY = testPoint.getY();
			      boolean result = false;
			      for (i = 0, j = length - 1; i < length; j = i++) {
			        if ((points.get(i).getY() > testY) != (points.get(j).getY() > testY) &&
			            (testX < (points.get(j).getX() - points.get(i).getX()) * (testY - points.get(i).getY()) / (points.get(j).getY()-points.get(i).getY()) + points.get(i).getX())) {
			          result = !result;
			         }
			      }
			      return result;});
	}
	//end hitbox interface

	@Override
	public boolean intersectsWithComposite(HitboxComposite comp) {
		return comp.intersectsWithPolygon(this);
	}
	
}//end class