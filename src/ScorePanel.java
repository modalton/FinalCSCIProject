import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class ScorePanel extends JPanel
{
	int homescoreint, awayscoreint, inningint, outint, strikeint, ballint, battersScoring;
	boolean homeUp, onfirst, onsecond, onthird, first, second, third;
	JButton homescore, awayscore, strikesballs, outs;
	JLabel inning;
	public ScorePanel()
	{
		homescoreint = 0;
		awayscoreint=0;
		inningint = 0;
		outint=0;
		strikeint=0;
		ballint=0;
		homeUp=false;
		onfirst = false;
		onsecond = false;
		onthird=false;


		setBackground(Color.GRAY);
		JLabel hometeam = new JLabel("HOME  ");
		hometeam.setForeground(Color.white);

		homescore= new JButton(homescoreint + "");
		homescore.setForeground(Color.red);
		//homescore.setEnabled(false);

		JLabel awayteam = new JLabel("AWAY  ");
		awayteam.setForeground(Color.white);


		awayscore = new JButton(awayscoreint + "");
		awayscore.setForeground(Color.blue);
		//awayscore.setEnabled(false);

		//special JLabel
		inning = new JLabel("Inning: " + inningint);
		inning.setForeground(Color.white);

		strikesballs= new JButton(ballint + "-" + strikeint);
		strikesballs.setBackground(Color.white);

		outs= new JButton(outint + " OUT");
		outs.setBackground(Color.white);



		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(hometeam);
		add(homescore);
		add(awayteam);
		add(awayscore);
		//add special label
		add(inning);
		add(strikesballs);
		add(outs);


	}

	public void batterOut(int numouts)
	{
		//update all the string variables and increase number of outs

		/*if(outint==2)
		{
			outint=0;
			inningChange();
		}
		else
		{
			outint++; 
			strikeint=0;
			ballint=0;*/
		//strikesballs.setText(ballint + "-" + strikeint);
		//String numOuts = String.valueOf(numouts);
		outs.setText(numouts + " OUT");
		repaint();
		revalidate();
		updateUI();

		//}
	}

	public void batterStrike(int strikes)
	{
		//update all the string variables and leave number of outs
		/*if(strikeint==2)
		{
			batterOut();
			strikesballs.setText(ballint + "-" + strikeint);
			//outs.setText(outint + " OUT");
		}
		else
		{*/	
		//strikeint++;
		strikesballs.setText(ballint + "-" + strikes);
		repaint();
		revalidate();
		updateUI();
		//outs.setText(outint + " OUT");
		//}
	}

	public void batterBall()
	{
		//update all the string variables and leave number of outs
		if(ballint==3)
		{batterSingle();}
		else
		{
			ballint++;
		}
	}

	public void inningChange()
	{
		strikeint=0;
		ballint=0;
		outint=0;

		strikesballs.setText(ballint + "-" + strikeint);
		outs.setText(outint + " OUT");

		if(homeUp) //home team was batting
		{//go to bottom of inning
			homeUp=false;
			//change the JLabel
		}
		else
		{//go to top of inning
			homeUp=true;
			inningint++;
			inning.setText("Inning: " + inningint);
			//change the JLabel
		}
	}
	public void batterSingle()
	{
		if(onthird)
		{
			battersScoring++;
			onthird=false;
		}
		if(onsecond)
		{
			onthird=true;
			onsecond=false;
		}
		if(onfirst)
		{
			onsecond=true;
			onfirst=false;
		}
		onfirst=true;
		//addScore(battersScoring);
		battersScoring=0;
	}
	public void batterDouble()
	{
		if(onthird)
		{
			battersScoring++;
			onthird=false;
		}
		if(onsecond)
		{
			battersScoring++;
			onsecond=false;
		}
		if(onfirst)
		{
			onfirst=false;
			onthird=true;
		}
		onsecond=true;
		//addScore(battersScoring);
		battersScoring=0;
	}
	public void batterTriple()
	{
		if(onthird)
		{
			battersScoring++;
			onthird=false;
		}
		if(onsecond)
		{
			battersScoring++;
			onsecond=false;
		}
		if(onfirst)
		{
			battersScoring++;
			onfirst=false;
		}
		onthird=true;
		//addScore(battersScoring);
		battersScoring=0;
	}
	public void batterHR()
	{
		if(onthird)
		{
			battersScoring++;
			onthird=false;
		}
		if(onsecond)
		{
			battersScoring++;
			onsecond=false;
		}
		if(onfirst)
		{
			battersScoring++;
			onfirst=false;
		}
		battersScoring++;
		//addScore(battersScoring);
		battersScoring=0;
	}
	public void addScore(int homeScore, int awayScore)
	{
		homescore.setText(homeScore + "");
		awayscore.setText(awayScore + "");
		repaint();
		revalidate();
		updateUI();
	}
}