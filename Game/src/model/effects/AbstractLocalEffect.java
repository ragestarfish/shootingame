package model.effects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class AbstractLocalEffect extends AbstractEffect {
	
	// (x,y) is "where" the effect happens
	protected DoubleProperty x;
	protected DoubleProperty y;
	
	protected ReadOnlyDoubleWrapper readOnlyX;
	protected ReadOnlyDoubleWrapper readOnlyY;

	protected AbstractLocalEffect(int time, double x, double y) {
		super(time);
		this.x = new SimpleDoubleProperty(x); 
		this.y= new SimpleDoubleProperty(y);
		readOnlyX = new ReadOnlyDoubleWrapper(); 
		readOnlyY = new ReadOnlyDoubleWrapper();
		readOnlyX.bind(this.x);
		readOnlyY.bind(this.y);
	}
	
	public ReadOnlyDoubleProperty xPropertyUnmodifiable(){return readOnlyX.getReadOnlyProperty();}
	public ReadOnlyDoubleProperty yPropertyUnmodifiable(){return readOnlyY.getReadOnlyProperty();}

}
