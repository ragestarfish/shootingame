package model.enemy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BeatShooter extends AutomatedEnemy {
	
	private final List<Integer> shootTimes;

	public BeatShooter(double locationX, double locationY, int health, int spawnTime, List<Integer> shootTimes) {
		super(locationX, locationY, health, spawnTime);
		this.shootTimes = shootTimes;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void shootAround(){
		shootTimes.stream()
			.filter( time -> time > spawnTime)
			.map( time -> new FireCommand(this,time))
			.forEach( command -> executor.schedule(command, command.getTime(), TimeUnit.MILLISECONDS ) );
	}

}
