import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Car {
	public static BufferedImage Buffer;
	
	int width = 40;
	int height = 40;
	double x;
	double y;
	int i = 0;
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
		try {
		Buffer = ImageIO.read(this.getClass().getResourceAsStream("CarImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		//AffineTransform identity = new AffineTransform();
		//AffineTransform trans = new AffineTransform();
		//trans.setTransform(identity);
		//trans.rotate( Math.toRadians(angle) );
		//trans.translate(-x, -y);
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(angle, (int)(x+(1/2*width)), (int)(y+(1/2*height))); //centerX and centerY is your center of rotation.
		//g2d.setTransform(trans);
		g2d.drawImage(Buffer, (int)x, (int)y, (int) (width), height,null);
		g2d.setColor(Color.BLACK);
		g2d.drawString(""+waitTime/60, (int)x+15, (int)y+22);
		g2d.rotate(-angle, (int)(x+(1/2*width)),(int)(y+(1/2*height))); //centerX and centerY is your center of rotation.
		//	trans.rotate(Math.toRadians(-27));
//		trans.translate(0, 0);
	//	g2d.setTransform(trans);
//		g2d.setColor(Color.RED);
	//	g.fillRoundRect((int)x, (int)y, width, height, 18, 18);
		
//		g.setStroke(new BasicStroke(4));
//		g.drawRoundRect((int)x, (int)y, width, height, 18, 18);
//		g.setStroke(new BasicStroke(1));


	}
	
	public double getX() {
		return x+(width/2);
	}
	public double getY() {
		return y+(height/2);
	}
}
