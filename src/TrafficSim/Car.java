package TrafficSim;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Car {
	double x;
	double y;
	double speed;
	int reactionTime;
	double accelerationSpeed;
	double width;
	double height;
	public Car(int x, int y) {
		this.speed = 0;
		this.x = x;
		this.y = y;
		this.accelerationSpeed = .0001;
		width = 10;
		height = 20;
		this.reactionTime = 0;
	}
	
	public void move() {
		
		if(speed == 0 && reactionTime<100) {
			reactionTime++;
		}
		if(speed < .1 && reactionTime >= 100) {
			//System.out.println(speed);
			speed+=accelerationSpeed;
		}
	
		x+=speed;
	}
	public void stop() {
		reactionTime = 0;
		if(speed > 0) {
			speed-=(accelerationSpeed*2);
		}
		x+=speed;
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
