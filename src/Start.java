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
	//this is just a small timer that keeps track of transition time and states.
	int switchStateTimer = 0;
	int transitionTimer = 0;
	ArrayList<ArrayList<Point>> points;
	ArrayList<Point> blockers;
	ArrayList<Point> greenLights;
	boolean isTransitioning = false;
	
	//Data display variables (doesnt have anything to do with the running of the program)
	String ticsPerSecString = "Calculating...";
	long startTime = System.currentTimeMillis();
	int removedCars = 0;
	int longestWaitTime = 0;
	public ArrayList<Integer> waitTimesCompiled = new ArrayList<Integer>();
	
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
		
		lightSwitchTimer();
		
		if(isTransitioning) {
			transitionTimer++;
			transitionLights();
		} else {transitionTimer = 0;}
		
			analyzeData(g);
			
			Point p = new Point(1);
			g.drawString(lanes.get(0).getScoreString(0)+"", (int)p.x, (int)p.y);
		
			p = new Point(2);
			g.drawString(lanes.get(1).getScoreString(1)+"", (int)p.x, (int)p.y);

			p = new Point(4);
			g.drawString(lanes.get(2).getScoreString(2)+"", (int)p.x, (int)p.y);
			
			p = new Point(5);
			g.drawString(lanes.get(3).getScoreString(3)+"", (int)p.x, (int)p.y);
			
			p = new Point(7);
			g.drawString(lanes.get(4).getScoreString(4)+"", (int)p.x, (int)p.y);
			
			p = new Point(8);
			g.drawString(lanes.get(5).getScoreString(5)+"", (int)p.x, (int)p.y);
			
			p = new Point(10);
			g.drawString(lanes.get(6).getScoreString(6)+"", (int)p.x, (int)p.y);
			
			p = new Point(11);
			g.drawString(lanes.get(7).getScoreString(7)+"", (int)p.x, (int)p.y);
			
		trafficController.draw(g2);
		//this loop moves the cars then draws
		for(Car c: cars) {
			if(timer%5==0) {
				double nextXSpot = (c.getX())+(50*Math.cos(c.angle));
				double nextYSpot = (c.getY())+(50*Math.sin(c.angle));
				boolean canMove = true;
				c.waitTime++;
				
				if(timer>25000 && longestWaitTime<c.waitTime) {
					longestWaitTime = c.waitTime;
				}
				for(Car c2: cars) {
					double testCarX = c2.getX();
					double textCarY = c2.getY();
					if(distance(nextXSpot, nextYSpot, testCarX, textCarY)<=45) {
						canMove = false;
					}
				}
				if(points != null) {
					for(Point point: blockers) {
						if(distance(nextXSpot, nextYSpot, point.x, point.y)<=45) {
							canMove = false;
						}
					}
					g.setColor(Color.green);
					for(Point point: greenLights) {
						g.fillOval((int)point.x, (int)point.y, 30, 30);
					}
					g.setColor(Color.black);
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

	public void analyzeData(Graphics g) {
		
		int timeElapsed = (int)(System.currentTimeMillis()-startTime);
		//double secElapsed = timeElapsed/1000;
		if(timer == 2000) {
			ticsPerSecString = (double)timer/timeElapsed+" Tics per Second";
		}
		//System.out.println(timer);
		double rateCarsPassed = (double)removedCars/timer;
		g.drawString(ticsPerSecString, 750, 40);
		g.drawString("Rate of Cars: "+rateCarsPassed, 750, 80);
		g.drawString("Longest Wait Time: "+longestWaitTime, 750, 120);
		if(timer == 80000) {
			for(int val: waitTimesCompiled) {
				System.out.print(val+" ");
			}
		}
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

		//Random r = new Random();
		//int loc = r.nextInt(12);
		ArrayList<Double> randoms = new ArrayList<Double>();
		//for (int i = 0; i < 12; i++) {
		//	randoms.add(Math.random());
		//}
		
		randoms.add(Math.random()); //0
		randoms.add(Math.random()); //1
		randoms.add(Math.random()); //2
		randoms.add(Math.random()); //3
		randoms.add(Math.random()); //4
		randoms.add(Math.random()); //5
		randoms.add(Math.random()); //6
		randoms.add(Math.random()); //7
		randoms.add(Math.random()); //8
		randoms.add(Math.random()); //9
		randoms.add(Math.random()); //10
		randoms.add(Math.random()); //11
		
		double max = 0;
		int loc = -1;
		for(int i = 0; i<randoms.size(); i++) {
			if(max<randoms.get(i)) {
				max = randoms.get(i);
				loc = i;
			}
			
		}
		
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
				if(timer>5000) {waitTimesCompiled.add(cars.get(i).waitTime);}
				
				cars.remove(i);
				removedCars++;
			}
		}
	}
	
	//this runs every tick (all the time)
	public void lightSwitchTimer(){
		
		int lane1 = trafficController.greenLane1;
		int lane2 = trafficController.greenLane2;
		if(lane1 != -1 && lane2 != -1) {
			if(lanes.get(lane1).numCars+lanes.get(lane2).numCars == 0) {
				switchStateTimer++;
			} else {
				switchStateTimer = 0;
			}
			
			if(switchStateTimer >=1000) {
				isTransitioning = true;
			}
		} else {
			points = trafficController.calculateMode(lanes);
			blockers = points.get(0);
			greenLights = points.get(1);
		}
		
		
		
		/* if(timer == 4000) {
			points = trafficController.stopCars();
			blockers = points.get(0);
			greenLights = points.get(1);
		}
		if(timer == 6000) {
			points = trafficController.calculateMode(lanes);
			blockers = points.get(0);
			greenLights = points.get(1);
			timer = 0;
		} */
		
	}
	//changes light from green to yellow to green (at a new mode)
	public void transitionLights(){
		if(transitionTimer == 1) {
			points = trafficController.stopCars();
			blockers = points.get(0);
			greenLights = points.get(1);
		}
		if(transitionTimer == 3500) {
			points = trafficController.calculateMode(lanes);
			blockers = points.get(0);
			greenLights = points.get(1);
			//timer = 0;
			isTransitioning = false;
			transitionTimer = 0;
		}
		
	}
	
	
}
