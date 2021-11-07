package polygonTracker;

import java.util.ArrayList;

public class Polygon {
	
	ArrayList<Marker> points = new ArrayList<Marker>();
	
	public Polygon(ArrayList<Marker> points) {
		this.points = points;
	}
	
	public boolean isPointIntersectingPolygon(Marker m) {
		int counter = 0;
		for (int i = 0; i < points.size()-1; i++) {
			if(isPointIntersectingLine(m,points.get(i), points.get(i+1))) {
				counter++;
			}
		}
		
		if(isPointIntersectingLine(m, points.get(0), points.get(points.size()-1))) {
			counter++;
		}
		
		for(int i = 0; i<points.size(); i++) {
			if(points.get(i).y == m.y && points.get(i).x> m.x) {
				counter++;
			}
		}
		System.out.println("count value: "+counter);
		if(counter%2==0) {return false;}
		else {return true;}
	}
	private boolean isPointIntersectingLine(Marker m, Marker point1, Marker point2) {
		if(point1.y>=m.y && point2.y>m.y) {return false;}
		if(point1.y<m.y && point2.y<=m.y) {return false;}
		
		if(point2.y-point1.y==0) {return false;}
		
		float invSlope = (point2.x-point1.x)/(point2.y-point1.y);
		float xVal = ((m.y-point2.y)*invSlope)+point2.x;
		
		if(m.x<xVal) {return true;}
		
		return false;
	}
}
