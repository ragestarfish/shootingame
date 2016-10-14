package model.enemy;

public class FireCommand implements Runnable{
	
	private AutomatedEnemy enemy;
	private int time;
	
	public FireCommand(AutomatedEnemy enemy, int time){
		setTime( time );
		this.enemy = enemy;
	}
	
	public int getTime(){return this.time;}
	public void setTime(int time){this.time=time;}
	
	@Override
	public void run(){
		try{
			this.enemy.shoot();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

