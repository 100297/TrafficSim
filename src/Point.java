
public class Point {
	double x;
	double y;
	int loc;
	public Point(int loc) {
		this.loc = loc;
		laneToChord();
	}
	
	public void laneToChord() {


		if(loc == 0) {
			x = 340;
			y = 300;
		} else if(loc == 1) {
			x = 420;
			y = 300;
		} else if(loc == 2) {
			x = 500;
			y = 300;
		} 
		
		else if(loc == 3) {
			x = 700;
			y = 340;
		} else if(loc == 4) {
			x = 700;
			y = 420;
		} else if(loc == 5) {
			x = 700;
			y = 500;
		} 
		
		else if(loc == 6) {
			x = 660;
			y = 700;
		} else if(loc == 7) {
			x = 580;
			y = 700;
		} else if(loc == 8) {
			x = 500;
			y = 700;
		}
		
		else if(loc == 9) {
			x = 300;
			y = 660;
		} else if(loc == 10) {
			x = 300;
			y = 580;
		} else if(loc == 11) {
			x = 300;
			y = 500;
		} 

	}
}
