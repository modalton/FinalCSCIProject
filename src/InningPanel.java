import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InningPanel extends JPanel
	{
		int s, b, o, i;
		public InningPanel()
		{
			s=0;
			b=0;
			o=0;
			i=0;
			
			JLabel strikes = new JLabel("Strikes: ");
			JLabel balls = new JLabel("Balls: ");
			JLabel outs = new JLabel("Outs: ");
			JLabel inning = new JLabel("Inning: ");
			
			JLabel numstrikes = new JLabel("" + s);
			JLabel numballs = new JLabel("" + b);
			JLabel numouts = new JLabel("" + o);
			JLabel numinning = new JLabel("of" + i);
			
			JPanel top = new JPanel();
			JPanel bottom = new JPanel();
			JPanel jp1 = new JPanel();
			JPanel jp2 = new JPanel();
			setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			top.add(strikes);
			top.add(numstrikes);
			gbc.gridx=0;
			gbc.gridy=0;
			add(top, gbc);
			
			bottom.add(balls);
			bottom.add(numballs);
			gbc.gridx=0;
			gbc.gridy=1;
			add(bottom, gbc);
			
			jp1.add(outs);
			jp1.add(numouts);
			gbc.gridx=0;
			gbc.gridy=2;
			add(jp1, gbc);
			
			jp2.add(inning);
			jp2.add(numinning);
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
		
		public void batterStrike()
		{
			//update all the string variables and leave number of outs
		}
		
		public void batterBall()
		{
			//update all the string variables and leave number of outs
		}
		
		public void inningChange()
		{
			//look at other list of teams and put up next 4 batters
		}
	}