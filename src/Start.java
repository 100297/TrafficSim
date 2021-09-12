import java.awt.Canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
public class Start extends JPanel implements Runnable{
	ArrayList<Car> cars = new ArrayList<Car>();
	TrafficController trafficController = new TrafficController();
	ArrayList<Lane> lanes = new ArrayList<Lane>();
	int timer = 0;
	ArrayList<Point> points;
	public Start() {
		for(int i = 0; i<8; i++) {
			lanes.add(new Lane(0,0));
		}
		new Thread(this).start();
	}
	
	public void paint(Graphics g) {
		
		timer++;
		super.paint(g);
		repaint();
		Graphics2D g2 = (Graphics2D) g;
		laneSetup(g2);
		
		trafficController.processLights(cars, lanes);
		if(timer == 4000) {
			trafficController.stopCars();
		}
		if(timer == 6000) {
			points = trafficController.calculateMode(lanes);
			timer = 0;
		}
		
		
		trafficController.draw(g2);
		//this loop moves the cars then draws
		for(Car c: cars) {
			if(timer%5==0) {
				double nextXSpot = (c.getX())+(50*Math.cos(c.angle));
				double nextYSpot = (c.getY())+(50*Math.sin(c.angle));
				boolean canMove = true;
				for(Car c2: cars) {
					double testCarX = c2.getX();
					double textCarY = c2.getY();
					if(distance(nextXSpot, nextYSpot, testCarX, textCarY)<=45) {
						canMove = false;
					}
				}
				if(points != null) {
					for(Point point: points) {
						if(distance(nextXSpot, nextYSpot, point.x, point.y)<=45) {
							canMove = false;
						}
					}
				}
				if(canMove == false) {continue;}
				if(c.x>300 && c.x<700 && c.y>300 && c.y<700) {
					if(c.positionNum == 2 || c.positionNum == 5 || c.positionNum == 6 || c.positionNum == 9) {
						c.turnLeft();
					}
					else if(c.positionNum == 0|| c.positionNum==3||c.positionNum==8||c.positionNum==11) {
						c.turnRight();
					}
					else {
						c.move();
					}
				}
				else {
					c.move();
				}
				
			}
			c.draw(g2);
		}
		if(timer%800 == 0) {
			addCar();
		}
		
		
		removeOutOfBounds();
		
	}

	public void laneSetup(Graphics2D g) {
		g.drawRect(300, 300, 400, 400);
		
		g.drawLine(0, 300, 300, 300);
		g.drawLine(300, 0, 300, 300);
		
		g.drawLine(700, 1000, 700, 700);
		g.drawLine(1000, 700, 700, 700);
		
		g.drawLine(700, 0, 700, 300);
		g.drawLine(1000, 300, 700, 300);
		
		g.drawLine(0, 700, 300, 700);
		g.drawLine(300, 1000, 300, 700);
		
		g.drawLine(380, 0, 380, 300);
		g.drawLine(460, 0, 460, 300);
		g.drawLine(620, 0, 620, 300);
		
		g.drawLine(0, 380, 300, 380);
		g.drawLine(0, 540, 300, 540);
		g.drawLine(0, 620, 300, 620);
		
		g.drawLine(700, 380, 1000, 380);
		g.drawLine(700, 460, 1000, 460);
		g.drawLine(700, 620, 1000, 620);
		
		g.drawLine(380, 700, 380, 1000);
		g.drawLine(540, 700, 540, 1000);
		g.drawLine(620, 700, 620, 1000);
		
		g.setColor(Color.BLUE);
		g.drawLine(0, 460, 300, 460);
		g.drawLine(540, 0, 540, 300);
		g.drawLine(700, 540, 1000, 540);
		g.drawLine(460, 700, 460, 1000);
	}
	
	public void addCar() {

		Random r = new Random();
		int loc = r.nextInt(12);
		int xCord = 0;
		int yCord = 0;
		String dir = "";
		switch(loc) {
		case 0:
			xCord = 320;
			yCord = 0;
			dir = "down";
			break;
		case 1:
			xCord = 400;
			yCord = 0;
			dir = "down";
			break;
		case 2:
			xCord = 480;
			yCord = 0;
			dir = "down";
			break;
		case 3:
			xCord = 1000;
			yCord = 320;
			dir = "left";
			break;
		case 4:
			xCord = 1000;
			yCord = 400;
			dir = "left";
			break;
		case 5:
			xCord = 1000;
			yCord = 480;
			dir = "left";
			break;
		case 6:
			xCord = 480;
			yCord = 1000;
			dir = "up";
			break;
		case 7:
			xCord = 560;
			yCord = 1000;
			dir = "up";
			break;
		case 8:
			xCord = 640;
			yCord = 1000;
			dir = "up";
			break;
		case 9:
			xCord = 0;
			yCord = 480;
			dir = "right";
			break;
		case 10:
			xCord = 0;
			yCord = 560;	
			dir = "right";
			break;
		default:
			xCord = 0;
			yCord = 640;
			dir = "right";
			break;
		}
		Car c = new Car(xCord, yCord, dir, loc);
		cars.add(c);
		
	}
			
	public void run() {
		try
	   	{
	   		while(true)
	   		{
	   		   Thread.currentThread().sleep(5);
	            repaint();
	         }
	      }catch(Exception e)
	      {
	      }
		
	}
	
	public double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}

	public void removeOutOfBounds() {
		for(int i = 0; i<cars.size(); i++) {
			if(cars.get(i).getX()>1100 || cars.get(i).getX()<-100 || cars.get(i).getY()>1100 || cars.get(i).getY()<-100) {
				cars.remove(i);
			}
		}
	}
	
}
