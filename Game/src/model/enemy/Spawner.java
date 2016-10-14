package model.enemy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import model.effects.Effect;

public class Spawner {
	private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
	
	private static final Set<AutomatedEnemy> tasks = new HashSet<>();
	private static final Set<Effect> effects = new HashSet<>();
	
	public static void accept(AutomatedEnemy enemy){tasks.add(enemy);}
	public static void accept(Effect effect){effects.add(effect);}
	
	public static void start(){
		for(AutomatedEnemy enemy : tasks)
			executor.schedule( ()->enemy.spawn() , enemy.getSpawnTime(), TimeUnit.MILLISECONDS);
		
		for(Effect effect : effects)
			executor.schedule( ()->effect.playEffect(), effect.getPlayTime(), TimeUnit.MILLISECONDS);
	}
	
	public static void unregister(AutomatedEnemy enemy){tasks.remove(enemy);}
	public static void stop(){executor.shutdown();}
}
