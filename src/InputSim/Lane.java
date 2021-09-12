package InputSim;

public class Lane {
	int timer;
	int numCars;
	public Lane(int numCars, int timer) {
		this.numCars = numCars;
		this.timer = timer;
	}
	
	public String getScoreString(int loc) {
		return "("+loc+")"+(timer+numCars);
	}
	
	public int getScore() {
		return timer+numCars;
	}
}
