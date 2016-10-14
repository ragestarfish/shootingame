package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import model.hitbox.*;

public class HitboxTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void testHitboxes(){
		HitboxCircle circleHitbox = new HitboxCircle(0.0, 0.0, 1.0);
		HitboxDot	 dotHitbox1 = new HitboxDot(0.5,0.3),
					dotHitbox2 = new HitboxDot(2.0,0.0);
		assertEquals(circleHitbox.intersects(dotHitbox1),true);
		assertEquals(circleHitbox.intersects(dotHitbox2),false);
		
		HitboxPolygon polygonHitbox = new HitboxPolygon(0.0,0.0, 1.0,0.0 , 1.0,1.0 , 0.0,1.0);
		assertEquals(polygonHitbox.intersects(dotHitbox1), true);
		assertEquals(polygonHitbox.intersects(dotHitbox2),false);
		
		
		HitboxPolygon polygonHitbox2 = new HitboxPolygon( 0.5,2.0 , 0.5,-1.0 , 3.0,-1.0 , 3.0,2.0 );
		HitboxPolygon polygonHitbox3 = new HitboxPolygon( 2.0,2.0 ,	2.0,-1.0 , 3.0,-1.0 , 3.0,2.0 );
		
		assertEquals(polygonHitbox.intersects(polygonHitbox2),true);
		assertEquals(polygonHitbox.intersects(polygonHitbox3),false);
		assertEquals(circleHitbox.intersects(polygonHitbox2),true);
		assertEquals(circleHitbox.intersects(polygonHitbox3),false);
	}
	
	

}
