package TrafficSim;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
public class Sim extends JFrame{
	static int width = 1800;
	static int height = 800;
	
	public Sim() {
		super("Sim");
		setSize(width, height);
		Start start = new Start();
		((Component)start).setFocusable(true);
		//getContentPane().setBackground(new Color(140,140,140));
		getContentPane().add(start);
		
		//setIgnoreRepaint(true);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Sim run = new Sim();
	}
	
}
