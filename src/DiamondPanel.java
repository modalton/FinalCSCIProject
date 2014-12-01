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
	BufferedImage img = null;
	Image picture;
	ImageIcon imageIcon;
	JLabel lbl;
	public DiamondPanel()
	{
		try
		{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
			img = ImageIO.read(new File("src/Images/EmptyBases.jpg"));
		}
		catch(Exception e)
		{System.out.println(e.getMessage());}
		int x =getWidth();
		int y = getHeight();
		BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(dimg);
		
		
        lbl=new JLabel();
        lbl.setIcon(imageIcon);
        add(lbl);
		
	}
	/*protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int x = getWidth();
		int y = getHeight();
		
		g.drawImage(imageIcon, x, y, 0, 0, this);
		//g.drawImage(picture, ((index+x_adjust)*x/10+50), (y_adjust*y/12 + 10), 35, 35, this);
		
	}*/
	public void baseChanged(boolean [] bases)
	{
		if(bases[0] && bases[1] && bases[2])//bases loaded
		{	
			
			try
			{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
				img = ImageIO.read(new File("Base_Images/BasesLoaded.jpg"));
			}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			int x =getWidth();
			int y = getHeight();
			BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			
	        lbl=new JLabel();
	        lbl.setIcon(imageIcon);
			//paint
		}
		if(!bases[0] && !bases[1] && !bases[2])//bases empty
		{	
			
			try
			{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
				img = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
			}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			int x =getWidth();
			int y = getHeight();
			BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			
	        lbl=new JLabel();
	        lbl.setIcon(imageIcon);
			//paint
		}
		if(bases[0] && !bases[1] && !bases[2])//first
		{	
			
			try
			{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
				img = ImageIO.read(new File("Base_Images/First.jpeg"));
			}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			int x =getWidth();
			int y = getHeight();
			BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			
	        lbl=new JLabel();
	        lbl.setIcon(imageIcon);
			//paint
		}
		if(!bases[0] && bases[1] && !bases[2])//on second
		{	
			
			try
			{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
				img = ImageIO.read(new File("Base_Images/Second.jpeg"));
			}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			int x =getWidth();
			int y = getHeight();
			BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			
	        lbl=new JLabel();
	        lbl.setIcon(imageIcon);
			//paint
		}
		if(!bases[0] && !bases[1] && bases[2])//on third
		{	
			
			try
			{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
				img = ImageIO.read(new File("Base_Images/Third.jpeg"));
			}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			int x =getWidth();
			int y = getHeight();
			BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			
	        lbl=new JLabel();
	        lbl.setIcon(imageIcon);
			//paint
		}
		if(bases[0] && bases[1] && !bases[2])//first and second
		{	
			
			try
			{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
				img = ImageIO.read(new File("Base_Images/FirstSecond.jpeg"));
			}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			int x =getWidth();
			int y = getHeight();
			BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			
	        lbl=new JLabel();
	        lbl.setIcon(imageIcon);
			//paint
		}
		if(bases[0] && !bases[1] && bases[2])//first and third
		{	
		
			try
			{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
				img = ImageIO.read(new File("Base_Images/FirstThird.jpeg"));
			}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			int x =getWidth();
			int y = getHeight();
			BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			
	        lbl=new JLabel();
	        lbl.setIcon(imageIcon);
			//paint
		}
		if(!bases[0] && bases[1] && bases[2])//second and third
		{	
			
			try
			{//picture = ImageIO.read(new File("Base_Images/EmptyBases.jpg"));
				img = ImageIO.read(new File("Base_Images/SecondThird.jpeg"));
			}
			catch(Exception e)
			{System.out.println(e.getMessage());}
			int x =getWidth();
			int y = getHeight();
			BufferedImage dimg = (BufferedImage) img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(dimg);
			
			
	        lbl=new JLabel();
	        lbl.setIcon(imageIcon);
			//paint
		}
	}
}
