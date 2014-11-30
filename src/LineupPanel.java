import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class LineupPanel extends JPanel
	{
		//list of both teams
		String batter;
		String ondeck1;
		String ondeck2;
		String ondeck3;
		boolean homeTeamUp;
		
		public LineupPanel()
		{
			homeTeamUp = true;
			batter = new String("batter1");
			ondeck1= new String("batter2");
			ondeck2= new String("batter3");
			ondeck3= new String("batter4");
			JLabel team1 = new JLabel("Batting: " + batter);
			JLabel team2 = new JLabel("On Deck 1: " + ondeck1);
			JLabel team3 = new JLabel("On Deck 2: " + ondeck2);
			JLabel team4 = new JLabel("On Deck 3: " + ondeck3);
			
			JPanel top = new JPanel();		
			JPanel bottom = new JPanel();
			JPanel jp1 = new JPanel();
			JPanel jp2 = new JPanel();
			setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			top.add(team1);
			gbc.gridx=0;
			gbc.gridy=0;
			add(top, gbc);
			
			bottom.add(team2);
			gbc.gridx=0;
			gbc.gridy=1;
			add(bottom, gbc);
			
			jp1.add(team3);
			gbc.gridx=0;
			gbc.gridy=2;
			add(jp1, gbc);
			
			jp2.add(team4);
			gbc.gridx=0;
			gbc.gridy=3;
			add(jp2, gbc);
			
		}
		
		public void batterOut()
		{
			//update all the string variables and increase number of outs
		}
		
		public void batterHit()
		{
			//update all the string variables and leave number of outs
		}
		
		public void switchTeams()
		{
			//look at other list of teams and put up next 4 batters
		}
	}