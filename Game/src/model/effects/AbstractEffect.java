package model.effects;

import model.enemy.Spawner;

public abstract class AbstractEffect implements Effect {

		private int playTime;
		
		protected void setPlayTime(int time){this.playTime=time;}
		
		@Override
		public int getPlayTime(){return this.playTime;}
		
		protected AbstractEffect(int time) {
			// TODO Auto-generated constructor stub
			setPlayTime(time);
			registerWithSpawner();
		}

		protected void registerWithSpawner(){Spawner.accept(this);}
}

