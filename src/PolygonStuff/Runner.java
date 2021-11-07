package polygonTracker;

import java.util.ArrayList;

public class Runner {
	public static void main(String[] args) {
		
		ArrayList<Marker> points = new ArrayList<Marker>();
		points.add(new Marker(1,1));
		points.add(new Marker(3,2));
		points.add(new Marker(2,4));
		points.add(new Marker(0,3));
		
		Polygon poly = new Polygon(points);
		Marker m = new Marker((float)0,(float)2);
		long t = System.currentTimeMillis();
		System.out.println(poly.isPointIntersectingPolygon(m));
		System.out.println("Time took to run: "+(System.currentTimeMillis()-t));
	}
}
