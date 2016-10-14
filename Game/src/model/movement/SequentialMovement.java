package model.movement;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SequentialMovement implements Movement {

	List<Movement> movements;
	Movement current;
	
	public SequentialMovement(){
		this.movements = new ArrayList<>();
	}
	public SequentialMovement(List<Movement> movements) {
		this.movements=movements;
		chainMovements();
	}

	public void add(Movement movement){
		this.movements.add(movement);
		current = movement;
		
		if(movements.size() > 1)
			this.movements.get(movements.size()-2).onFinished(handler->{
				movement.move();
				current = movements.get(movements.size()-1);
				});
	}
	
	@Override
	public void move() {
		this.movements.get(0).move();
		current = movements.get(0);
	}

	@Override
	public void stop() {
		//TODO: do I have to stop ALL?
		try{
		current.stop();
		}
		catch( NullPointerException e ){
			System.err.println("Movement has never started");
		}
	}

	@Override
	public void onFinished(EventHandler<ActionEvent> handler) {
		this.movements.get(movements.size()-1).onFinished(handler);
	}

	
	private void chainMovements(){
		for(int i=0;i<movements.size()-1;i++){
			final int j=i+1;
			movements.get(i).onFinished(handler->{movements.get(j).move();
			current = movements.get(j);});
		}
	}
}
