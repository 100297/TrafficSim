package TrafficSim;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Car {
	public double x;
	private double y;
	private double speed;
	private int reactionTime;
	private final double accelerationSpeed;
	private double width;
	private double height;
	long delta;
	long previousTime = System.currentTimeMillis();
	public Car(int x, int y) {
		this.speed = 0;
		this.x = x;
		this.y = y;
		this.accelerationSpeed = .001;
		width = 10;
		height = 20;
		this.reactionTime = 0;

	}
	
	public void move() {
		
		long start = System.currentTimeMillis();
		
		delta = (start - previousTime)/10; 
		previousTime = start;
		if(speed == 0 && reactionTime<100) {
			reactionTime++;
		}
		if(speed < .6 && reactionTime >= 100) {
			//System.out.println(speed);
			speed+=accelerationSpeed*delta;
		}
	
		x+=speed*delta;
	}
	public void stop() {
		long start = System.currentTimeMillis();
		
		delta = (start - previousTime)/10; 
		previousTime = start;
		reactionTime = 0;
		if(speed -(accelerationSpeed*2)> 0) {
			speed-=(accelerationSpeed*2);
		}
		else {
			speed = 0;
		}
		x+=speed*delta;
	}
	
public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public double getX() {
		return x+(width/2);
	}
	public double getY() {
		return y+(height/2);
	}

}
