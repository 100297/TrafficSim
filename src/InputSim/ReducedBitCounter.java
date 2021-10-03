package InputSim;

import java.util.ArrayList;
import java.util.Random;

public class ReducedBitCounter {
	static int [] count;
	public static ArrayList<Lane> lanes = new ArrayList<Lane>();
	public static void main(String [] args) {
	  Random r = new Random();
	  for(int i = 0; i<2; i++) {
			int cars = r.nextInt(10);
			int timer = r.nextInt(30);
			Lane l = new Lane(cars, timer);
			lanes.add(l);
		}
	  int count [] = new int [2];
	  for(int i = 0; i<2; i++) {
			if(lanes.get(i).getScore() > 0) {
				count[i] = 1;
				
			} else {
				count[i] = 0;
			}
		
		}
		

	}
 }

