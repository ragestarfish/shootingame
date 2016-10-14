package model.enemy;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import model.EnemylikeShooter;
import model.movement.Movement;

public abstract class AutomatedEnemy extends EnemylikeShooter {
	
	protected Movement movement;
	protected boolean alive;
	protected final int spawnTime;
	
	//for shooting; automated enemies should be able to move and shoot on their own
	protected final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
	
	public AutomatedEnemy(double locationX, double locationY, int health, int spawnTime) {
		super(locationX, locationY, health, null);
		defineWeapon();
		defineMovement();
		setAlive(true);
		this.spawnTime = spawnTime;
		registerWithSpawner();
		executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
		}
	

	
	//implementing class contracts
	protected abstract void defineWeapon();
	protected abstract void defineMovement();
	protected abstract void registerWithConverter();
	protected abstract void shootAround();
	//end contracts
	
	//begin public services
	public void move(){ movement.move();}
	public void stop(){movement.stop();}
	public Movement getMovement(){return this.movement;}
	public void setMovement(Movement movement){
		this.movement = movement;
		getMovement().onFinished(handler -> {
			if(isAlive()){
				despawn();
				setAlive(false);
			}
		});
	}
	public int getSpawnTime() {return spawnTime;}
	public void setAlive(boolean value){this.alive=true;}
	public boolean isAlive(){return this.alive;}
	
	@Override
	public void spawn(){
		registerWithConverter();
		try{
		//move();
		shootAround();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void despawn(){
		super.despawn();
		Spawner.unregister(this);
		setAlive(false);
		executor.shutdown();
	}
	//end public services
	
	//begin private stuff
	protected void registerWithSpawner(){Spawner.accept(this);}
	//end private stuff

}
