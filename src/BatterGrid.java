import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
//tictactoe

public class BatterGrid extends JPanel
{
	private GridLabel[] grid;
	private GridButtonHandler[] gridHandlers;
	private boolean batting;
	private GamePanel gp;
	
	
	public BatterGrid(GamePanel gp) 
	{
		setPreferredSize(new Dimension(100, 100));
		
		this.gp = gp;
		
		//JPanel centerPanel=new JPanel();
		//setOpaque(false);
		setLayout(new GridLayout(3, 3));//creates the 9 buttons for the grid
		grid=new GridLabel[9];
		gridHandlers=new GridButtonHandler[9];
		for (int i=0; i < 9; i++) 
		{
			
			grid[i]=new GridLabel("");
			grid[i].x = i % 3;
			grid[i].y = (int)(i / 3);
			grid[i].setOpaque(true);
			gridHandlers[i]=new GridButtonHandler();
			grid[i].addMouseListener(gridHandlers[i]);
			grid[i].setBackground(Color.gray);
			grid[i].setBorder(BorderFactory.createLineBorder(Color.white));
			add(grid[i]);
		}
		
		//outerPanel.add(centerPanel, BorderLayout.CENTER);
		
		

		//add(outerPanel);
		init();
	}
	
	@Override  
    protected void paintComponent(Graphics g)  
    {  
        super.paintComponent(g);  
  
        //  Paint background image  
  
        Graphics2D g2 = (Graphics2D) g;  
  
        //  Paint foreground image with appropriate alpha value  
  
        Composite old = g2.getComposite();  
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));  
        //g2.drawRenderedImage(frontImage, AffineTransform.getTranslateInstance(x, y));  
        //g2.setComposite(old);  
    }  
	
	public void init()
	{
		setVisible(true);
		batting =false;
	}
	
	
	
	private class GridButtonHandler implements MouseListener
	{
		
		public void mouseClicked(MouseEvent e)
		{
			
			//Get button pressed
			GridLabel clickedPanel = (GridLabel) e.getSource();
			
		
		
			if(batting)//already chosen a grid square 
			{
				for(int i=0; i<9 ; i++)
				{
					//change color of all grid squares to original color
					//change clicked square to yellow
					grid[i].setBackground(Color.gray);
					clickedPanel.setBackground(Color.yellow);
				}
				repaint();
			}
			
			
			else
			{
				//change square color to yellow
				batting = true;
				clickedPanel.setBackground(Color.yellow);
			}
			
			//set x and y in GamePanel then set has message to true
			gp.x = clickedPanel.x;
			gp.y = clickedPanel.y;
			
			gp.hasMessage = true;
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			//grid[i].setBackground(Color.cyan);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
