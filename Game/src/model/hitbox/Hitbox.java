package model.hitbox;

/**
 * Visitor pattern to ensure the correct calls to intersection method while sticking to OOD principles
 * and avoiding "instanceof" operator
 * @author mowe
 *
 */
public interface Hitbox {
	public boolean intersects(Hitbox visitor);
	public boolean intersectsWithCircle(HitboxCircle c);
	public boolean intersectsWithDot(HitboxDot d);
	public boolean intersectsWithPolygon(HitboxPolygon p);
	public boolean intersectsWithComposite(HitboxComposite comp);
}
