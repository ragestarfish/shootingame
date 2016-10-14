package model;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.hitbox.CollisionEvent;

public abstract class PlayerlikeDamagableGizmo extends PlayerlikeGizmo {
	
	protected Deque<Gizmo> lastPartner = new LinkedBlockingDeque<>(5);;
	protected final SimpleIntegerProperty health;

	protected PlayerlikeDamagableGizmo(double x, double y) {
		super(x, y);
		health = new SimpleIntegerProperty(1);
	}
	
	protected PlayerlikeDamagableGizmo(double x, double y, int health) {
		super(x, y);
		this.health = new SimpleIntegerProperty(health);
	}
	
	public int getHealth(){return health.get();}
	public IntegerProperty healthProperty(){return health;}
	
	@Override
	public void despawn(){
		super.despawn();
		this.lastPartner=null;
	}
	
	@Override
	public void handleCollision(CollisionEvent e){
		Gizmo partner=e.getSource();
		//if(lastPartner == null)
		//	lastPartner = new LinkedBlockingDeque<>(5);
		//else{
			if( !lastPartner.contains(partner) || lastPartner.isEmpty() )
			{
				health.set(health.get()-partner.dealCollisionDamage());
				if(lastPartner.size() < 5)
					lastPartner.addFirst(partner);
				else{
					lastPartner.pollLast();
					lastPartner.addFirst(partner);
				}
			}
		//}//end else
	}//end handle
	

}
