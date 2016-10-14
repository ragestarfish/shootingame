package model.movement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface Movement {
	
	public void move();
	public void stop();
	public void onFinished(EventHandler<ActionEvent> handler);
}
