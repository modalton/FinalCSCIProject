import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;



public class GamePanel extends JPanel implements ClientProcessInterface<GameMessage>{
	
	private static final long serialVersionUID = 1L;
	
	//sprites
	Vector<Sprite> all_sprites = new Vector<Sprite>();
	
	//global variables used for sending Game Message
	boolean hasMessage;
	boolean isBatting;
	int x, y;
	int scoreA, scoreB;
	int base, inning; 
	int strikes = 0, outs = 0;
	boolean inningChange, pitChange, batChange;
	boolean[] onBase; // what base people are on
	boolean gameOver, aWins, tieGame;
	boolean firstMsg;
	boolean aBatting;
	boolean isTeamA;
	SpriteAnimation pitcher;
	SpriteAnimation batter;
	ScorePanel sp;

	//user data
	String username;
	String team_choice;

	
	public GamePanel(){
		super(null);
		
		JLabel pitcherLabel = new JLabel();
		
		pitcherLabel.setBounds(300, 0, 200, 200);
		//pitcherLabel.setOpaque(false);
		JLabel batterLabel = new JLabel();
		batterLabel.setBounds(225,275,300,200);
		
		add(pitcherLabel);
		add(batterLabel);

		
		pitcher = new SpriteAnimation(pitcherLabel, false); //Second parameter is "isBatter"
		batter = new SpriteAnimation(batterLabel, true);
/*		pitcher.start();
		batter.start();*/
		
		
		sp = new ScorePanel();
		sp.setBounds(0, 0, 700, 30);
		add(sp);
		
		
		BatterGrid bg = new BatterGrid(this);
		bg.setBounds(325, 325, 100, 100);
		add(bg);
		
		/*
		DiamondPanel dp = new DiamondPanel();
		dp.setBounds(600,0, 75, 75);
		add(dp);
		*/
		
		
		//HAVE TO KNOW WHAT TEAM THIS GAMEPANEL'S CLIENT IS ON
		
		setOpaque(false);
		
		firstMsg = true;
		hasMessage = true;
		
	}

	@Override
	public void processInputObject(GameMessage object) {
		
		//Extract information from message
		
		/*ANIMATIONS WILL NOT BEGIN UNTIL 
		THERE IS AT LEAST ONE BATTER 
		AND ONE PITCHER*/
		if(object.firstMsg){
			pitcher.start();
			batter.start();
			System.out.println("THINKS SERVER IS SENDING FIRST MESSAGE");
			//return;
		}
		
		sp.batterStrike(object.strikes);
		sp.batterOut(object.outs);
		sp.addScore(object.scoreA, object.scoreB);
		sp.repaint();
	    sp.revalidate();
		sp.updateUI();
		remove(sp);
		sp.setBounds(0, 0, 700, 30);
		add(sp);
		System.out.println("Reached the end of the GamePlay process input!");
	}



	@Override
	public boolean hasOutputObject() {
		// TODO Auto-generated method stub
		return hasMessage;
	}



	@Override
	public GameMessage processOutputObject() {
		//Generate the message depending on where the player has clicked
		hasMessage = false;
		GameMessage theMsg;

		
		//get info from batting/pitching grid
		//make game message
		//send message
		
		if(firstMsg){
			System.out.println("FROM GamePanel, gamePanel.isTeamA = " + isTeamA);
			
			if (isTeamA)
				isBatting = false; //isBatting is the same is aBatting
			else
				isBatting = true; //isBatting is the opposite of aBatting

		}
		
		if (isBatting){
			theMsg = new GameMessage("BATTER", x, y, "", "", strikes, outs, scoreA, scoreB, onBase, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, username, team_choice);
		} else{
			theMsg = new GameMessage("PITCHER", x, y, "", "", strikes, outs, scoreA, scoreB, onBase, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, username, team_choice);

		}
		
		firstMsg = false;
		return theMsg;
		
	}
	
	
	
	
	
	
}
