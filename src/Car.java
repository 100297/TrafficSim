import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Car {
	int width = 40;
	int height = 40;
	double x;
	double y;
	String direction;
	double angle;
	double speed;
	int positionNum;
	int waitTime = 0;
	double accelerationSpeed;
	int reactionTime;
	public Car(int x, int y, String direction, int pos) {
		this.x = x;
		this.y = y;
		this.speed = 1;
		this.direction = direction;
		this.positionNum = pos;
		this.reactionTime = 0;
		this.accelerationSpeed = (Math.random()/4000)+.01;
		switch(direction) {
			case "up":
				angle = Math.PI*1.5;
				break;
			case "down":
				angle = Math.PI/2;
				break;
			case "right":
				angle = 0;
				break;
			case "left":
				angle = Math.PI;
				break;
			default:
				break;
		}
	}
	
	public void move() {
			if(speed == 0 && reactionTime<100) {
				reactionTime++;
				return;
			}
			if(speed < 2) {
				speed+=accelerationSpeed;
			}
		
			x+=Math.cos(angle)*speed;
			y+=Math.sin(angle)*speed;
		}
	public void turnLeft() {
		angle-=0.005*speed;
		switch( direction ){
			case "up":
				move();
				y-=.2546*speed;
				break;
			case "down":
				move();
				y+=.2546*speed;
				break;
			case "right":
				move();
				x+=.2546*speed;
				break;
			case "left":
				move();
				x-=.2546*speed;
				break;
			default:
				break;
		}
	}
	public void turnRight() {
		angle+=0.025*speed;
		move();
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.RED);
		g.fillRoundRect((int)x, (int)y, width, height, 18, 18);
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(4));
		g.drawRoundRect((int)x, (int)y, width, height, 18, 18);
		g.setStroke(new BasicStroke(1));
		g.drawString(""+waitTime/60, (int)x+15, (int)y+22);
	}
	
	public double getX() {
		return x+(width/2);
	}
	public double getY() {
		return y+(height/2);
	}
}
