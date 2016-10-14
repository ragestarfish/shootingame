package model;

import application.graphics.Converter;
import model.hitbox.CollisionDirector;
import model.hitbox.Hitbox;

/**
 * Gizmo splits into Playerlikes and Enemylikes, which may be treated seperately. The 
 * Purpose of this is that Playerlikes and Enemylikes may not collide among themselves, so the
 * CollisionDirector only checks necessary cases.
 * For this reason, this is the first time in the Gizmo hierarchy that a gizmo may be registered or unregistered
 * with the CollisionDirector.
 */
public abstract class EnemylikeGizmo extends Gizmo {
	
	private Hitbox hitbox;
	
	protected EnemylikeGizmo(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Hitbox getHitbox(){return this.hitbox;}
	
	@Override
	public void setHitbox(Hitbox hitbox){this.hitbox = hitbox;
	CollisionDirector.register(this);}

	
	@Override
	protected void despawn(){
		Converter.despawn(this);
		CollisionDirector.unregister(this);
	}
}
