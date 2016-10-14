package model.enemy;

import application.graphics.Converter;

public class SimpleDotEnemy extends DamagableFixedMovementGizmo {

	public SimpleDotEnemy(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public SimpleDotEnemy(double x, double y, int health) {
		super(x, y, health);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void spawn() {
		// TODO Auto-generated method stub
		Converter.spawn(this, getX(), getY());
	}

	@Override
	public int dealCollisionDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
