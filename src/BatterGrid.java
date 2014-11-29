import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton[] grid;
	private GridButtonHandler[] gridHandlers;
	private boolean batting;
	
	
	public BatterGrid() 
	{
		//super("");
		
		//setSize(500,500);
		//setLocation(400,100);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout (new BorderLayout());
		
		
		
		JPanel centerPanel=new JPanel();
		
		centerPanel.setLayout(new GridLayout(3, 3,0,0));//creates the 9 buttons for the grid
		grid=new JButton[9];
		gridHandlers=new GridButtonHandler[9];
		for (int i=0; i < 9; i++) 
		{
			//char ch =(char)('0'+i+1);
			grid[i]=new JButton("");
			gridHandlers[i]=new GridButtonHandler();
			grid[i].addActionListener(gridHandlers[i]);
			centerPanel.add(grid[i]);
		}
		
		outerPanel.add(centerPanel, BorderLayout.CENTER);
		
		

		add(outerPanel);
		init();
	}
	
	public void init()
	{
		setVisible(true);
		batting =false;
	}
	
	
	public static void main(String agrs[])
	{
		BatterGrid mw = new BatterGrid();
	}
	
	private class GridButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			//Get button pressed
			JButton pressed=(JButton)(e.getSource());
			
			if(batting)//already chosen a grid square 
			{
				for(int i=0; i<9 ; i++)
				{
					//change color of all grid squares to original color
					//change clicked square to yellow
				}
			}
			
			
			else
			{
				//change square color to yellow
				batting = true;
			}
			
			
		}
	}	

	
}
