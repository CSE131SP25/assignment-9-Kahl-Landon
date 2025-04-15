package assignment9;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int score;
	
	public Snake() {
		deltaX = 0;
		deltaY = 0;
		this.segments = new LinkedList<>();
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		double headXPosition = segments.get(0).getX();
		double headYPosition = segments.get(0).getY();
		
		segments.get(0).setX(headXPosition + deltaX);
		segments.get(0).setY(headYPosition + deltaY);
		
		for (int i = 1; i < segments.size(); i++) {
			BodySegment seg = segments.get(i); // Get the element at index
			
			double currentX = seg.getX();
			double currentY = seg.getY();
			
			seg.setX(headXPosition);
			seg.setY(headYPosition);
			
			headXPosition = currentX;
			headYPosition = currentY;
		}
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment element : segments) {
			element.draw();
		}
		
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		double distance = Math.sqrt(Math.pow(segments.get(0).getX()-f.getX(), 2) + Math.pow(segments.get(0).getY()-f.getY(), 2));
		
		if (distance <= 2*SEGMENT_SIZE) {
			segments.add(new BodySegment(segments.get(0).getX(), segments.get(0).getY()-(2*SEGMENT_SIZE), SEGMENT_SIZE));
			return true;
		}
		else {
			return false;
		}
		
		
		
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		double positionX = segments.get(0).getX();
		double positionY = segments.get(0).getY();
		if ((positionX < 1) && (positionX > 0) && (positionY < 1) && (positionY > 0)) {
			return true;
		}
		else {
			return false;
		}
	}
}
