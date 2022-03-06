package TrafficSim;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Start extends JPanel implements Runnable, ActionListener {
	long startTime = System.currentTimeMillis();
	int timer = 0;
	ArrayList<Car> cars = new ArrayList<Car>();
	boolean stopperOn = false;
	public void paint(Graphics g) {
		super.paint(g);
		timer++;
		// repaint();
		Graphics2D g2 = (Graphics2D) g;
		setup(g);
		
		if(timer%500 == 1) {
			cars.add(new Car(50, 500));
		}
		
		
		if(timer%2 == 0) {
			processCars(g);
		}
		
		for(Car car: cars) {
			car.draw(g);
		}
	}
	
	public void setup(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, Sim.width, Sim.height+150);
		g.setColor(Color.gray);
		g.fillRect(0,50,Sim.width, Sim.height-100);
	}
	
	public void processCars(Graphics g) {
		
//		for(Car car: cars) {
//			for(Car comparisonCar: cars){
//			if(car != comparisonCar && car.x<= comparisonCar.x && isCollided(car.x, comparisonCar.x)) {
//				car.stop();
//			} else {
//				car.move();
//			}
//			
//			}
//		}
		if(timer%5000 == 0) {stopperOn = true;}
		
		System.out.println(timer);
		for(int i = 0; i<cars.size(); i++) {
			for(int j = 0; j<cars.size(); j++) {
				if(i!=j && cars.get(i).x <= cars.get(j).x && isCollided(cars.get(i).x, cars.get(j).x)) {
					cars.get(i).stop();
				} else if( stopperOn == false && (isCollided(cars.get(i).x, 800) || isCollided(cars.get(i).x, 1400) ) ){
					cars.get(i).stop();
				}else {
					cars.get(i).move();
				}
			}
		}
		
	}
	
	public boolean isCollided(double x1, double x2) {
		if(Math.abs(x1-x2)<100) {
			return true;
		}
		return false;
	}
	
	public Start() {
		new Thread(this).start();
	}
	
	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(10);
				repaint();
			}
		} catch (Exception e) {
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(stopperOn == true) {
			stopperOn = false;
		} else {
			stopperOn = true;
		}
	}
	
}