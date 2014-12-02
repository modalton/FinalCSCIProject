import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DiamondPanel extends JPanel
{
	//boolean [] onBase;
	Image picture;
	JLabel picLabel;
	public DiamondPanel()
	{
		//setPreferredSize(new Dimension(200, 300));
		try
		{picture = ImageIO.read(new File("src/Images/EmptyBases.jpg"));}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		
		Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		//ImageIcon imageIcon=new ImageIcon(dimg);
		
		picLabel = new JLabel( new ImageIcon(dimg));
		add(picLabel);
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int x = getWidth();
		int y = getHeight();
		
		g.drawImage(picture, x, y, 0, 0, this);
		//g.drawImage(picture, ((index+x_adjust)*x/10+50), (y_adjust*y/12 + 10), 35, 35, this);
		
	}
	public void baseChanged(boolean onFirst, boolean onSecond, boolean onThird)
	{
		System.out.println("**** (IN DIAMONDPANEL)");
		System.out.println("onFirst = " + onFirst);
		System.out.println("onSecond = " + onSecond);
		System.out.println("onThird = " + onThird);
		System.out.println("****");
		
		if(onFirst && onSecond && onThird)//bases loaded
		{	
			try
			{picture = ImageIO.read(new File("src/Images/BasesLoaded.jpg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(!onFirst && !onSecond && !onThird)//bases empty
		{	
			try
			{picture = ImageIO.read(new File("src/Images/EmptyBases.jpg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(onFirst && !onSecond && !onThird)//first
		{	
			try
			{picture = ImageIO.read(new File("src/Images/First.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(!onFirst && onSecond && !onThird)//on second
		{	
			try
			{picture = ImageIO.read(new File("src/Images/Second.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(!onFirst && !onSecond && onThird)//on third
		{	
			try
			{picture = ImageIO.read(new File("src/Images/Third.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(onFirst && onSecond && !onThird)//first and second
		{	
			try
			{picture = ImageIO.read(new File("src/Images/FirstSecond.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(onFirst && !onSecond && onThird)//first and third
		{	
			try
			{picture = ImageIO.read(new File("src/Images/FirstThird.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(!onFirst && onSecond && onThird)//second and third
		{	
			try
			{picture = ImageIO.read(new File("src/Images/SecondThird.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		
		this.removeAll();
		this.add(picLabel);
		
		repaint();
		revalidate();
		updateUI();
	}
}
