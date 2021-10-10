package InputSim;

import java.util.Date;
import java.util.Random;

public class StoppedOrNotSim {
	static int Ttotal;
	static boolean rintersection;
	public static void main(String[] args) {
		Ttotal = 0;
		rintersection = true;
		Random r = new Random ();
		TCAR[] a = new TCAR [10];
	 for (int i = 0; i < a.length; i++) {
		 a[i].waittime = 0;
		 a[i].isstopped = true;
	 }
	 TCAR[] b = new TCAR [10];
	 for (int i = 0; i < b.length; i++) {
		 b[i].waittime = 0;
		 b[i].isstopped = true;
	 }
	 
	 long startTime = System.currentTimeMillis();
	 long elapsedTime = 0L;

	  if(elapsedTime%1000 == 0) {
	   if(rintersection = true) {
		   for (int i = 0; i < a.length; i++) {
			   a[i].waittime += 1;
		   }
	   } else {
		   for (int i = 0; i < b.length; i++) { 
			   b[i].waittime += 1;
		   }
	   }
	   elapsedTime = (new Date()).getTime() - startTime;
	 }

	
	}
public static int GetTotalWait(TCAR [] p) {
	int total = 0;
	for(int i = 0; i < p.length; i++) {
		if(p[i].isstopped = true) {
			total += p[i].waittime;
		}
	}
	Ttotal += total;
	return total;
		 
	 }
}
