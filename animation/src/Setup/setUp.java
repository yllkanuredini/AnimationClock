package Setup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import display.Display;

public class setUp implements Runnable{
	private String title;
	private int size;
	private Display display;
	private Thread thread;
	
	private BufferStrategy buffer;
	private Graphics2D g;
	
	public setUp(String title, int size) {
		this.title = title;
		this.size = size;
		
	}
	public void init(){
		display = new Display(title, size); 
	}
	
	public void draw(){
		
	 	buffer = display.canvas.getBufferStrategy();
	 	if(buffer == null) {
	 		display.canvas.createBufferStrategy(3);
	 	 return;
	 	 
	 	}
	 	 int center = size/2;
	 	
	 	g =(Graphics2D)buffer.getDrawGraphics();
	 	g.clearRect(0, 0, size, size);
	 	//draw
	 	g.setColor(Color.black);
	 	g.fillOval(10, 10, size-20, size-20);
	 	g.setColor(Color.white);
	 	g.fillOval(20, 20, size-40, size-40);
	 	
	 	int angleX , angleY;
	 	int radius = 0;
	 	double position;
	 	double time = System.currentTimeMillis();
	 	
	 	for(int i=1; i<=12; i++) {
	 		position = i/12.0 * Math.PI * 2;
	 		radius= center - 60;
	 		
			angleX = (int)((center) + (Math.sin(position)*radius));
			angleY = (int)((center) - (Math.cos(position)*radius));
			        g.setColor(Color.black);
			        g.setFont(new Font("arial", Font.BOLD , 33));
			String a = Integer.toString(i);
	 		g.drawString(a, angleX, angleY); 
	 	}
	 	
	 	for(int i=1; i <= 60; i++){
	 		position = i/60.0 * Math.PI*2;
	 		radius = center - 20; 
	 		angleX = (int)((center) + (Math.sin(position)*radius));
	 		angleY = (int)((center) - (Math.cos(position)*radius));
	 		if(i==15 || i==30 || i==45 || i==60) {
	 			radius = center - 70;
	 		}else {
	 			radius = center - 30;
	 		}
	 	int	angleW = (int)((center) + (Math.sin(position)*radius));
	 	int	angleZ = (int)((center) - (Math.cos(position)*radius));
	 	g.setColor(Color.black);
	 	g.drawLine(angleW, angleZ, angleX, angleY);
	 	
	 	}
	 	
	 	
	 	//hour
	 	radius = center - 150;
	 	
	 	time = (System.currentTimeMillis()/(60.0 * 60 * 12 * 1000) * Math.PI*2)+1;
	 	angleX = (int)((center) + Math.sin(time) * radius);
	 	angleY = (int)((center) - Math.cos(time) * radius);
	 	g.setColor(Color.blue);
	 	g.setStroke(new BasicStroke(12));
	 	g.drawLine(center, center, angleX, angleY);
	 	g.setStroke(new BasicStroke(0));
	 	
	 	
	 	//minute
	 	radius = center - 90;
	 	
	 	time = (System.currentTimeMillis()/(60.0 * 60 * 1000) * Math.PI*2)-0.2;
	 	angleX = (int)((center) + Math.sin(time) * radius);
	 	angleY = (int)((center) - Math.cos(time) * radius);
	 	g.setColor(Color.black);
	 	g.setStroke(new BasicStroke(6));
	 	g.drawLine(center, center, angleX, angleY);
	 	g.setStroke(new BasicStroke(0));
	 	
	 	//second
        radius = center - 50;
	 	
	 	time = System.currentTimeMillis()/(60.0 * 1000) * Math.PI*2;
	 	angleX = (int)((center) + Math.sin(time) * radius);
	 	angleY = (int)((center) - Math.cos(time) * radius);
	 	g.setColor(Color.red);
	 	g.drawLine(center, center, angleX, angleY);
	 	
	 	
	 	g.setColor(Color.RED);
	 	g.fillOval(center-10, center-10, 20, 20);
	 	
	 	
	 	//end
	 	
	 	buffer.show();
	 	g.dispose();
	 	
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		init();
		while(true) {
			draw();
			
		}
	}
	
}

