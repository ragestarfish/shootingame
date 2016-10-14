package model.player;

import application.graphics.Converter;
import model.PlayerlikeShooter;
import model.hitbox.CollisionEvent;
import weapon.concrete.CircularWeapon;
import weapon.concrete.DefaultPlayerWeapon;


public class Player extends PlayerlikeShooter {
	
	
	
	private static final int DEFAULT_HEALTH = 3;
	
	public Player(	double locationX, double locationY, int health) {
		super(locationX, locationY,  health);
		//setWeapon(new DefaultPlayerWeapon(xProperty(),yProperty()) );
		setWeapon(new CircularWeapon(xPropertyUnmodifiable(),yPropertyUnmodifiable(),5));

		// TODO Auto-generated constructor stub
	}
	
	public Player(	double locationX, double locationY) {
		super(locationX, locationY,  DEFAULT_HEALTH );
		//setWeapon(new DefaultPlayerWeapon(xProperty(),yProperty()) );
		setWeapon(new DefaultPlayerWeapon(xPropertyUnmodifiable(), yPropertyUnmodifiable()));
		xPropertyUnmodifiable().get();
		yPropertyUnmodifiable().get();
		// TODO Auto-generated constructor stub
		
	}
	


	@Override
	public void spawn() {
		Converter.spawn(this,getX(),getY());
	}

	@Override
	public void handleCollision(CollisionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int dealCollisionDamage() {
		// TODO Auto-generated method stub
		return 1;
	}


}
