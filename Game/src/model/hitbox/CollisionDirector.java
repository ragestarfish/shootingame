package model.hitbox;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import model.EnemylikeGizmo;
import model.PlayerlikeGizmo;

public class CollisionDirector {
	
	public static final long CHECK_DELAY = 20;
	

	private static ScheduledThreadPoolExecutor executor  = new ScheduledThreadPoolExecutor(2);; //initializes lazily
	private static ScheduledFuture<?> handler;
	
	//TODO: different capacity or overload factor values?
	//has to be synchronized versions because the executor threads might unregister gizmos, modifying the collections
	private static final Set<PlayerlikeGizmo> playerLikes = Collections.synchronizedSet(new HashSet<>());
	private static final Set<EnemylikeGizmo> enemyLikes = Collections.synchronizedSet(new HashSet<>()) ;
	
	
	//begin public services
	public static void check(){
		if(handler == null || handler.isCancelled()){
		final Runnable checkTask = new Runnable(){
				@Override
				public void run(){
					//TODO: remove try block?
					//save the collisions in a map to prevent concurrent modifications
					Map<PlayerlikeGizmo,EnemylikeGizmo> collisions = new HashMap<>(playerLikes.size()+enemyLikes.size());
					try{
						synchronized (playerLikes){ synchronized(enemyLikes) {
						for( PlayerlikeGizmo playerLike : playerLikes ){
							for( EnemylikeGizmo enemyLike : enemyLikes ){
								if(playerLike.getHitbox().intersects(enemyLike.getHitbox())){
									collisions.put(playerLike, enemyLike);
									System.out.println("Collision!");
								}
							}
						}
						//fire the collision events after checking for them
						for( PlayerlikeGizmo playerLike : collisions.keySet() )
							fireCollisionEvent(playerLike,collisions.get(playerLike));
					}}//end synch
				}//end try
				catch(Exception e){e.printStackTrace();}
				}//end run
			}; //end Runnable
		//TODO: use this?
		handler=executor.scheduleAtFixedRate(checkTask,0, CHECK_DELAY, TimeUnit.MILLISECONDS);
		}
	}//end check
	
	
	
	public static void stop(){executor.shutdown();}
	public static void register(PlayerlikeGizmo gizmo){playerLikes.add(gizmo);}
	public static void register(EnemylikeGizmo gizmo){enemyLikes.add(gizmo);}
	public static void unregister(PlayerlikeGizmo gizmo){synchronized (playerLikes){playerLikes.remove(gizmo);}}
	public static void unregister(EnemylikeGizmo gizmo){synchronized(enemyLikes){enemyLikes.remove(gizmo);}}
	//end public services
	
	//private methods
	private static void fireCollisionEvent(PlayerlikeGizmo playerLike, EnemylikeGizmo enemyLike){
	//check for null first, i.e. if the thing despawned already
		if(playerLike != null )
			playerLike.handleCollision( new CollisionEvent(enemyLike));
		if(enemyLike != null )
			enemyLike.handleCollision( new CollisionEvent(playerLike));
	}
	//end private methods
}

