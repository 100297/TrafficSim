import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class TrafficController {
	public ArrayList<TrafficLight> trafficLights = new ArrayList<TrafficLight>();
	public int greenLane1 = -1;
	public int greenLane2 = -1;
	public TrafficController() {
		trafficLights.add(new TrafficLight(420,350,"XXX"));
		trafficLights.add(new TrafficLight(650,420,"XXX"));
		trafficLights.add(new TrafficLight(800,800,"XXX"));
		trafficLights.add(new TrafficLight(100,800,"XXX"));
	}
	
	public void draw(Graphics2D g) {
		for(TrafficLight tf: trafficLights) {
			tf.draw(g);
			
		}	
	}
	
	public void processLights(ArrayList<Car> cars, ArrayList<Lane> lanes) {

		for(Lane l: lanes) {
			l.numCars = 0;
		}
		for(Car car: cars) {
			if(car.getX()>380 && car.getX()<460 && car.getY()<300) {
				lanes.get(0).numCars++;
			} else if(car.getX()>460 && car.getX()<540 && car.getY()<300) {
				lanes.get(1).numCars++;
			} 
			
			else if(car.getY()>380 && car.getY()<460 && car.getX()>700) {
				lanes.get(2).numCars++;
			} else if(car.getY()>460 && car.getY()<540 && car.getX()>700) {
				lanes.get(3).numCars++;
			} 
			
			else if(car.getX()>460 && car.getX()<540 && car.getY()>700) {
				lanes.get(5).numCars++;
			} else if(car.getX()>540 && car.getX()<620 && car.getY()>700) {
				lanes.get(4).numCars++;
			} 
			
			else if(car.getY()>540 && car.getY()<620 && car.getX()<300) {
				lanes.get(6).numCars++;
			} else if(car.getY()>460 && car.getY()<540 && car.getX()<300) {
				lanes.get(7).numCars++;
			}
			
		}
		for(Lane l: lanes) {
			if(l.numCars == 0) {
				l.timer = 0;
			} else {
				l.timer++;
			}
		}
	}
	//this calculates the mode of the lights, then it adds which lanes to block into the points arraylist
	public ArrayList<ArrayList<Point>> calculateMode(ArrayList<Lane> lanes) {

		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Point> greenPoints = new ArrayList<Point>();
		
		int maxCombo = 0;
		int currCombo;
		String result = "none. All should be red.";
		
		
		currCombo = lanes.get(0).getScore()+lanes.get(4).getScore();
		if(currCombo>maxCombo) {
			maxCombo = currCombo;
			result = "opposites N/S";
		}
		currCombo = lanes.get(6).getScore()+lanes.get(2).getScore();
		if(currCombo>maxCombo) {
			maxCombo = currCombo;
			result = "opposites E/W";
		}
		
		currCombo = lanes.get(1).getScore()+lanes.get(5).getScore();
		if(currCombo>maxCombo) {
			maxCombo = currCombo;
			result = "lefts N/S";
		}
		
		currCombo = lanes.get(3).getScore()+lanes.get(7).getScore();
		if(currCombo>maxCombo) {
			maxCombo = currCombo;
			result = "lefts E/W";

		}
		
		currCombo = lanes.get(0).getScore()+lanes.get(1).getScore();
		if(currCombo>maxCombo) {
			maxCombo = currCombo;
			result = "single N";
		}
		
		currCombo = lanes.get(2).getScore()+lanes.get(3).getScore();
		if(currCombo>maxCombo) {
			maxCombo = currCombo;
			result = "single E";
		}
		
		currCombo = lanes.get(4).getScore()+lanes.get(5).getScore();
		if(currCombo>maxCombo) {
			maxCombo = currCombo;
			result = "single S";
		}
		
		currCombo = lanes.get(6).getScore()+lanes.get(7).getScore();
		if(currCombo>maxCombo) {
			maxCombo = currCombo;
			result = "single W";
		}
		//System.out.println(result);
		switch(result) {
		case "opposites N/S":
			points.add(new Point(2));
			points.add(new Point(4));
			points.add(new Point(5));
			points.add(new Point(8));
			points.add(new Point(10));
			points.add(new Point(11));
			greenPoints.add(new Point(1));
			greenPoints.add(new Point(7));
			greenLane1 = 0;
			greenLane2 = 4;
			break;
		case "opposites E/W":
			points.add(new Point(1));
			points.add(new Point(2));
			points.add(new Point(5));
			points.add(new Point(7));
			points.add(new Point(8));
			points.add(new Point(11));
			greenPoints.add(new Point(4));
			greenPoints.add(new Point(10));
			greenLane1 = 2;
			greenLane2 = 6;
			break;
		case "lefts N/S":
			points.add(new Point(1));
			points.add(new Point(4));
			points.add(new Point(5));
			points.add(new Point(7));
			points.add(new Point(10));
			points.add(new Point(11));
			greenPoints.add(new Point(2));
			greenPoints.add(new Point(8));
			greenLane1 = 1;
			greenLane2 = 5;
			break;
		case "lefts E/W":
			points.add(new Point(1));
			points.add(new Point(2));
			points.add(new Point(4));
			points.add(new Point(7));
			points.add(new Point(8));
			points.add(new Point(10));
			greenPoints.add(new Point(5));
			greenPoints.add(new Point(11));
			greenLane1 = 3;
			greenLane2 = 7;
			break;
		case "single N":
			points.add(new Point(4));
			points.add(new Point(5));
			points.add(new Point(7));
			points.add(new Point(8));
			points.add(new Point(10));
			points.add(new Point(11));
			greenPoints.add(new Point(1));
			greenPoints.add(new Point(2));
			greenLane1 = 0;
			greenLane2 = 1;
			break;
		case "single E":
			points.add(new Point(1));
			points.add(new Point(2));
			points.add(new Point(7));
			points.add(new Point(8));
			points.add(new Point(10));
			points.add(new Point(11));
			greenPoints.add(new Point(4));
			greenPoints.add(new Point(5));
			greenLane1 = 2;
			greenLane2 = 3;
			break;
		case "single S":
			points.add(new Point(1));
			points.add(new Point(2));
			points.add(new Point(4));
			points.add(new Point(5));
			points.add(new Point(10));
			points.add(new Point(11));
			greenPoints.add(new Point(8));
			greenPoints.add(new Point(7));
			greenLane1 = 5;
			greenLane2 = 4;
			break;
		case "single W":
			points.add(new Point(1));
			points.add(new Point(2));
			points.add(new Point(4));
			points.add(new Point(5));
			points.add(new Point(7));
			points.add(new Point(8));
			greenPoints.add(new Point(11));
			greenPoints.add(new Point(10));
			greenLane1 = 7;
			greenLane2 = 6;
			break;
		default:
			for(int i = 0; i<12; i++) {points.add(new Point(i));}
			break;
		}
		
		for(Point p: points) {
			//System.out.print(p.loc);
		}
		//System.out.println();
		ArrayList<ArrayList<Point>> combined = new ArrayList<ArrayList<Point>>();
		combined.add(points);
		combined.add(greenPoints);
		return combined;
	}
	
	public ArrayList<ArrayList<Point>> stopCars(){
		ArrayList<ArrayList<Point>> combined = new ArrayList<ArrayList<Point>>();
		ArrayList<Point> points = new ArrayList<>();
		//this is supposed to be an empty list
		ArrayList<Point> greenLights = new ArrayList<>();
		//for(int i = 0; i<12; i++) {points.add(new Point(i));}
		points.add(new Point(1));
		points.add(new Point(2));
		points.add(new Point(4));
		points.add(new Point(5));
		points.add(new Point(7));
		points.add(new Point(8));
		points.add(new Point(10));
		points.add(new Point(11));
		
		combined.add(points);
		combined.add(greenLights);
		return combined;
	}
	
	

}
