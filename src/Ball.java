import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;



class Ball extends Thread implements ImageObserver{
	GamePanel main;
	Double transX;
	Double transY;
	Image picture;
	boolean ready;
	
	static double lower_x= .2;
	static double upper_x = .8;
	
	
	public Ball(GamePanel p)
	{
		
		main = p;
		ready=false;
		
		transX = new Double(0.5);
		transY = new Double(0.3);
		try{
			picture = ImageIO.read(new File("src/Images/Baseball.png"));
		}
		catch(Exception e){
		}
	}
	
	public void run(){
		while(true){
			
			
			if(ready ==true) //pitcher and batter are ready
			{
				goToHomeplate();
				ready=false;
			}
			
			else//if pitcher and batter are still watiing don't do anything
			{
				goToPitcher();
				
				try{
					sleep(50);
				}
				catch(Exception e){
					
				}
			}
			
			
		}
	}
	
	public void goToScrewdrivers()
	{ //go to table saw station
		if(transY < 0.26){moveYaxisD(0.26);}
		else{ moveYaxisU(0.26); }
		goToX(0.10);
		//goToY(0.55);
	}
	
	
	public void goToRow3(){
		if(transY < 0.85){moveYaxisD(0.85);}
		else{ moveYaxisU(0.85); }
	}
	
	public void goToHomeplate(){ //go to items pane and grab item
		
		moveYaxisD(0.80);
	}
	
	public void goToPitcher(){ //go to items pane and grab item
		
		moveYaxisU(0.30);
	}

	public void goToX(double d){
		if(d<transX){  moveXaxisL(d);  }
		else{ moveXaxisR(d); }
	}
	public void goToY(double d)
	{
		if(d<transY){moveYaxisD(d);}
		else{ moveYaxisU(d); }
	}
	
	public void drawSelf(Graphics g, int x, int y){
		Integer new_x = (int) (transX*x);
		Integer new_y = (int) (transY*y);
		
		
		g.drawImage(picture, new_x, new_y, 40, 40, this);
	}
	
	public void moveXaxisR(double percentage){
		
		while(transX < percentage){
			transX = transX + 0.01 ;
			main.repaint();
			try{
				sleep(20);
			}
			catch(Exception e){
				
			}
		
		
		}
	}
	public void moveXaxisL(double percentage){
		
		if(transX>percentage){
			while(transX > percentage){
				transX = transX - 0.01;
				main.repaint();
				try{
					sleep(20);
				}
				catch(Exception e){
					
				}
			}
			return;
			}
		
	}
	
	public void moveYaxisD(double percentage){
		
		while(transY < percentage){
			transY = transY + 0.01;
			main.repaint();
			try{
				sleep(100);
			}
			catch(Exception e){
				
			}
		}
	}
	
public void moveYaxisU(double percentage){
		
		while(transY > percentage){
			transY = transY - 0.01;
			main.repaint();
			try{
				sleep(20);
			}
			catch(Exception e){
				
			}
		}
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
