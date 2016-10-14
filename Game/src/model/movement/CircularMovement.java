package model.movement;

import java.util.function.Function;

import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

public class CircularMovement extends CurvedMovement {
	
	private double radius;
	private double angularVelocity;

	public CircularMovement(DoubleProperty x, DoubleProperty y, Function<Double, Double> f,
			Function<Double, Double> g) {
		super(x, y, f, g);
		this.setStartX(x.get());
		this.setStartY(y.get());
		// TODO Auto-generated constructor stub
	}

	public CircularMovement(DoubleProperty x, DoubleProperty y, double startX, double startY, double radius) {
		super(x, y);
		angularVelocity = 1;
		this.radius=radius;
		setStartX(startX);
		setStartY(startY);
		
		f = X->this.radius*Math.cos(X*angularVelocity*2*Math.PI)-radius+startX;
		g = Y->this.radius*Math.sin(Y*angularVelocity*2*Math.PI)+startY;
		// TODO Auto-generated constructor stub
	}

	public CircularMovement(DoubleProperty x, DoubleProperty y, Duration duration) {
		super(x, y, duration);
		// TODO Auto-generated constructor stub
	}

	public CircularMovement() {
		// TODO Auto-generated constructor stub
	}

	public CircularMovement(Duration duration) {
		super(duration);
		// TODO Auto-generated constructor stub
	}

}
