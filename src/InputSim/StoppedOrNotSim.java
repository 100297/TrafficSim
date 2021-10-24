package InputSim;

import java.util.Date;
import java.util.Random;

public class StoppedOrNotSim {
	static int Ttotal;
	static boolean rintersection;
	static int totcars;
	static double waitrat;
	static int abase;
	static int bbase;
	static int acurrent;
	static int bcurrent;
	static int awaitime;
	static int bwaitime;
	static int duration;
	static int atemp;
	static int btemp;

	public static void main(String[] args) {
		atemp = 0;
		btemp = 0;
		totcars = 0; 
		duration = 100;
		Ttotal = 0;
		rintersection = true;
		
		TCAR[] a = new TCAR[10];

		abase = 7;
		bbase = 2;
		awaitime = 0;
		bwaitime = 0;
		for (int i = 0; i < a.length; i++) {
			a[i] = new TCAR();
			a[i].waittime = 0;
			a[i].isstopped = false;
		}
		TCAR[] b = new TCAR[10];
		for (int i = 0; i < b.length; i++) {
			b[i] = new TCAR();
			b[i].waittime = 0;
			b[i].isstopped = false;
		}

		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;
		while (duration > 0) {
			if (elapsedTime % 1000 == 0) {
				Random r = new Random();
				acurrent = abase + r.nextInt(5) - 2;
				bcurrent = bbase + r.nextInt(5) - 2;
				if (duration == 100) {
					atemp = acurrent;
					btemp = bcurrent;
					System.out.println("Values Set: " + atemp + " and " + btemp);
				}
				for (int i = 0; i < acurrent; i++) {
					a[i].isstopped = true;
				}
				for (int i = 0; i < bcurrent; i++) {
					b[i].isstopped = true;
				}
				if (rintersection == true) {
						btemp = bcurrent;
					System.out.print(awaitime + " ");
					System.out.println(bwaitime + "");
					Ttotal += bwaitime;
					bwaitime = 0;
					for (int i = 0; i < atemp; i++) {
						
		
							awaitime += 1;
						

					}
					for (int i = 0; i < btemp; i++) {
							totcars++;
							bwaitime += 1;
						

					}
				} else {
					atemp = acurrent;
					Ttotal += awaitime;
					awaitime = 0;
					for (int i = 0; i < btemp; i++) {
						
							bwaitime += 1;
						
					}
					for (int i = 0; i < atemp; i++) {
							
						totcars++;
							awaitime += 1;
						

					}

				}
				
				if (bwaitime > awaitime) {
					rintersection = true;
				} else {
					rintersection = false;
		
					}

			}
			
		elapsedTime = 0;
			duration--;
		}
		
		waitrat = (double) Ttotal / (double) totcars;
		System.out.println(Ttotal + "");
		System.out.println(totcars + "");
		System.out.println("Average wait time is: " + waitrat);
	}

}
