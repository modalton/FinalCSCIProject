import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel
{
	public ScorePanel()
	{
		JLabel team1 = new JLabel("Team 1: ");
		JLabel team2 = new JLabel("Team 2: ");
		JLabel team1score = new JLabel("0");
		JLabel team2score = new JLabel("0");
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		top.add(team1);
		top.add(team1score);
		gbc.gridx=0;
		gbc.gridy=0;
		add(top, gbc);
		bottom.add(team2);
		bottom.add(team2score);
		gbc.gridx=0;
		gbc.gridy=1;
		add(bottom, gbc);
		
	}
}
