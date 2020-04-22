package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display{
private String title;
private int size;
private JFrame frame;
public static Canvas canvas; 
public Display(String title, int size){
       this.title = title;
       this.size = size;
       
       createDisplay(); 
       }
  public void createDisplay(){
  
     frame = new JFrame(title);
     frame.setSize(size,size);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
     frame.setResizable(false);
     frame.setVisible(true);
     
     canvas = new Canvas();
     canvas.setPreferredSize(new Dimension(size,size));
     canvas.setBackground(new Color(221,120,122));
     frame.add(canvas);
     frame.pack();
   }
}