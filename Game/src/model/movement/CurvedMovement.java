package model.movement;

import java.util.function.Function;

import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

public class CurvedMovement extends AbstractMovement {

	/**
	 * (f,g): [0,1] -> {Path}
	 */
	protected Function<Double,Double> f;
	protected Function<Double,Double> g;
	
	public void setXCurve(Function<Double,Double> f){this.f=f;}
	public void setYCurve(Function<Double,Double> g){this.g=g;}
	public Function<Double,Double> getXCurve(){return f;}
	public Function<Double,Double> getYCurve(){return g;}
	
	protected double startX;
	protected double startY;
	
	public void setStartX(double x){startX=x;}
	public void setStartY(double y){startY=y;}
	public double getStartX(double x){return startX;}
	public double getStartY(double y){return startY;}
	
	public CurvedMovement(DoubleProperty x, DoubleProperty y, Function<Double,Double> f, Function<Double,Double> g) {
		super(x, y);
		this.f=f;
		this.g=g;
	}
	
	protected CurvedMovement(DoubleProperty x, DoubleProperty y){
		super(x,y);
	}
	
	protected CurvedMovement(DoubleProperty x, DoubleProperty y,Duration duration){
		super(x,y,duration);
	}
	
	protected CurvedMovement(){
		super();
	}
	
	protected CurvedMovement(Duration duration){
		super(duration);
	}
	
	public CurvedMovement add(CurvedMovement m){
		Function<Double,Double> newF = f;
		Function<Double,Double> newG = g;
		m.setStartX(0);
		m.setStartY(0);
		CurvedMovement result = new CurvedMovement(x,y){
			@Override
			protected void interpolate(double frac){
				if(x==null || y==null){
					System.out.println("x or y was null");
				}
				if(m.getXCurve()==null || m.getYCurve()==null){
					System.out.println("x or y curve was null");
				}
				if(newF==null || newG==null){
					System.out.println("f or g was null");
				}
				x.setValue(newF.apply(frac)+m.getXCurve().apply(frac));
				y.setValue(newG.apply(frac)+m.getYCurve().apply(frac));
			}
		};
		return result;
	}
	
	@Override
	public void move() {
		play();
	}

	@Override
	protected void interpolate(double frac) {
		x.setValue( f.apply(frac) );
		y.setValue( g.apply(frac) );
	}

}
