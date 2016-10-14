package model.hitbox;

import java.util.EventObject;

import model.Gizmo;

public class CollisionEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;


	public CollisionEvent(Gizmo source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Gizmo getSource(){
		return (Gizmo) super.getSource();
	}
	
}
