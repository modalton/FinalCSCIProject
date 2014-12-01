import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DiamondPanel extends JPanel
{
	boolean [] onBase;
	Image picture;
	JLabel label;
	public DiamondPanel()
	{
		//setPreferredSize(new Dimension(200, 300));
		onBase = new boolean [3];
		for(int i=0; i<3; i++)
		{
			onBase[i] = false;
		}
		try
		{picture = ImageIO.read(new File("src/Images/EmptyBases.jpg"));}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		label = new JLabel();
		label.setIcon(new ImageIcon(picture));
		add(label);
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int x = getWidth();
		int y = getHeight();
		
		g.drawImage(picture, x, y, 0, 0, this);
		//g.drawImage(picture, ((index+x_adjust)*x/10+50), (y_adjust*y/12 + 10), 35, 35, this);
		
	}
	public void baseChanged(boolean [] bases)
	{
		if(onBase[0] && onBase[1] && onBase[2])//bases loaded
		{	
			try
			{picture = ImageIO.read(new File("Base_Images/BasesLoaded.jpg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
		}
		if(!onBase[0] && !onBase[1] && !onBase[2])//bases empty
		{	
			try
			{picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
		}
		if(onBase[0] && !onBase[1] && !onBase[2])//first
		{	
			try
			{picture = ImageIO.read(new File("Base_Images/First.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
		}
		if(!onBase[0] && onBase[1] && !onBase[2])//on second
		{	
			try
			{picture = ImageIO.read(new File("Base_Images/Second.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
		}
		if(!onBase[0] && !onBase[1] && onBase[2])//on third
		{	
			try
			{picture = ImageIO.read(new File("Base_Images/Third.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
		}
		if(onBase[0] && onBase[1] && !onBase[2])//first and second
		{	
			try
			{picture = ImageIO.read(new File("Base_Images/FirstSecond.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
		}
		if(onBase[0] && !onBase[1] && onBase[2])//first and third
		{	
			try
			{picture = ImageIO.read(new File("Base_Images/FirstThird.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
		}
		if(!onBase[0] && onBase[1] && onBase[2])//second and third
		{	
			try
			{picture = ImageIO.read(new File("Base_Images/SecondThird.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
		}
	}
}
