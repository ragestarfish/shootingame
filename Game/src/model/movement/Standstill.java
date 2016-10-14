package model.movement;

import javafx.util.Duration;

public class Standstill extends AbstractMovement {



	public Standstill(Duration duration) {
		super(duration);
	}



	@Override
	public void move() {}

	@Override
	protected void interpolate(double frac) {
		
	}

}
