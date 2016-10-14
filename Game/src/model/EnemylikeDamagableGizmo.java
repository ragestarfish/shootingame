package model;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.hitbox.CollisionEvent;

public abstract class EnemylikeDamagableGizmo extends EnemylikeGizmo {

	protected Deque<Gizmo> lastPartner = new LinkedBlockingDeque<>(5);; //TODO: is this necessary?
	protected final SimpleIntegerProperty health;

	protected EnemylikeDamagableGizmo(double x, double y) {
		super(x, y);
		health = new SimpleIntegerProperty(1);
	}
	
	protected EnemylikeDamagableGizmo(double x, double y, int health) {
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
		try{
			if( lastPartner != null && ( !lastPartner.contains(partner) || lastPartner.isEmpty() ) )
			{
				if(lastPartner.size() < 5)
					lastPartner.addFirst(partner);
				else{
					lastPartner.pollLast();
					lastPartner.addFirst(partner);
				}
				health.set(health.get()-partner.dealCollisionDamage());
				if(health.get()<=0)
					despawn();
			}
		}
		catch(Exception E){
			E.printStackTrace();
		}
	}//end handle

}

