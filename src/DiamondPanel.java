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
	boolean [] onBase;
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
	public void baseChanged(boolean [] bases)
	{
		System.out.println("base changed");
		for (int i = 0; i < bases.length; i++) {
			if (bases[i]) {
				System.out.println("tr");
			}
			System.out.println("false");
		}
		
		
		if(bases[0] && bases[1] && bases[2])//bases loaded
		{	
			try
			{picture = ImageIO.read(new File("src/Images/BasesLoaded.jpg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(!bases[0] && !bases[1] && !bases[2])//bases empty
		{	
			try
			{picture = ImageIO.read(new File("src/Images/EmptyBases.jpg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(bases[0] && !bases[1] && !bases[2])//first
		{	
			try
			{picture = ImageIO.read(new File("src/Images/First.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(!bases[0] && bases[1] && !bases[2])//on second
		{	
			try
			{picture = ImageIO.read(new File("src/Images/Second.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(!bases[0] && !bases[1] && bases[2])//on third
		{	
			try
			{picture = ImageIO.read(new File("src/Images/Third.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(bases[0] && bases[1] && !bases[2])//first and second
		{	
			try
			{picture = ImageIO.read(new File("src/Images/FirstSecond.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(bases[0] && !bases[1] && bases[2])//first and third
		{	
			try
			{picture = ImageIO.read(new File("src/Images/FirstThird.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		if(!bases[0] && bases[1] && bases[2])//second and third
		{	
			try
			{picture = ImageIO.read(new File("src/Images/SecondThird.jpeg"));}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			//paint
			Image dimg = picture.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			picLabel = new JLabel( new ImageIcon(dimg));

		}
		repaint();
		revalidate();
		updateUI();
	}
}
