package model.movement;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


public abstract class AbstractMovement extends Transition implements Movement {
		
		private final Duration DEFAULT_DURATION = Duration.millis(3000);
	
		protected DoubleProperty x;
		protected DoubleProperty y;

		
		
		protected AbstractMovement(){
			this.x=new SimpleDoubleProperty();
			this.y=new SimpleDoubleProperty();
			setInterpolator(Interpolator.LINEAR);
			setCycleDuration( DEFAULT_DURATION );
		}
		
		protected AbstractMovement(Duration duration){
			this.x=new SimpleDoubleProperty();
			this.y=new SimpleDoubleProperty();
			setInterpolator(Interpolator.LINEAR);
			setCycleDuration( duration );
		}
		
		
		protected AbstractMovement(DoubleProperty x, DoubleProperty y){
			this.x=x;
			this.y=y;
			setInterpolator(Interpolator.LINEAR);
			setCycleDuration( DEFAULT_DURATION );
		}
		
		protected AbstractMovement(DoubleProperty x, DoubleProperty y, Duration duration){
			this.x=x;
			this.y=y;
			setInterpolator(Interpolator.LINEAR);
			setCycleDuration( duration );
		}
		
		@Override
		public void onFinished(EventHandler<ActionEvent> handler){
			super.setOnFinished(handler);
		}
		
}
