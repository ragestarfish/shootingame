package model.hitbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class HitboxComposite implements Hitbox {
	
	private Collection<Hitbox> components;

	public HitboxComposite() {components = new ArrayList<>();}
	
	public void add(Hitbox hitbox){components.add(hitbox);}
	public Iterator<Hitbox> getIterator(){return components.iterator();}
	

	@Override
	public boolean intersects(Hitbox visitor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersectsWithCircle(HitboxCircle c) {
		// TODO Auto-generated method stub
		return components.stream().anyMatch( component -> component.intersectsWithCircle(c));
	}

	@Override
	public boolean intersectsWithDot(HitboxDot d) {
		// TODO Auto-generated method stub
		return components.stream().anyMatch( component -> component.intersectsWithDot(d));
	}

	@Override
	public boolean intersectsWithPolygon(HitboxPolygon p) {
		// TODO Auto-generated method stub
		return components.stream().anyMatch(component->component.intersectsWithPolygon(p));
	}

	@Override
	public boolean intersectsWithComposite(HitboxComposite comp) {
		Iterator<Hitbox> iterator = comp.getIterator();
		while(iterator.hasNext()){
			Hitbox nextElement = iterator.next();
			if(components.stream().anyMatch(component -> component.intersects(nextElement)) )
					return true;
		}
		return false;
	}

}
