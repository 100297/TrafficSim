package InputSim;

import java.util.ArrayList;
import java.util.Random;

public class Compiler {
	static ArrayList<Lane> lanes = new ArrayList<Lane>();
	public static void main(String[] args) {
		
		Random r = new Random();
		for(int i = 0; i<8; i++) {
			int cars = r.nextInt(10);
			int timer = r.nextInt(30);
			Lane l = new Lane(cars, timer);
			lanes.add(l);
		}
		
		System.out.println("   "+lanes.get(0).getScoreString(0)+"  "+lanes.get(1).getScoreString(1));
		System.out.println(lanes.get(7).getScoreString(7)+"        "+lanes.get(2).getScoreString(2));
		System.out.println(lanes.get(6).getScoreString(6)+"        "+lanes.get(3).getScoreString(3));
		System.out.println("   "+lanes.get(5).getScoreString(5)+"  "+lanes.get(4).getScoreString(4));
		
		System.out.println(result());
	}
	
	public static String result() {
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
		
		return result;
	}
}
