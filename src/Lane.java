public class Lane {
	public int timer;
	public int numCars;
	public Lane(int numCars, int timer) {
		this.numCars = numCars;
		this.timer = timer;
	}
	
	public String getScoreString(int loc) {
		return "("+loc+")"+timer+" "+numCars;
	}
	
	public int getScore() {
		return timer+numCars;
	}
}
