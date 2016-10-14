package model.enemy;

import application.graphics.Converter;

public class SimpleRectangularEnemy extends DamagableFixedMovementGizmo {

	public SimpleRectangularEnemy(double x, double y) {
		super(x, y);
		this.healthProperty().set(5);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void spawn() {
		// TODO Auto-generated method stub
		Converter.spawn(this,getX(),getY());
	}

	@Override
	public int dealCollisionDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
