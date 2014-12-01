import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
	private boolean[] onBase; // what base people are on
	boolean gameOver, aWins, tieGame;
	boolean firstMsg;
	boolean aBatting;
	boolean isTeamA;
	boolean homeRun;
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
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//baseball move
		
		g.setFont( new Font("TimesRoman", Font.PLAIN, 32));

		if(homeRun){ //Homer
			g.setColor(Color.GREEN);
			g.drawString("HOME RUN!!!",300,250);
		}

		/*if(true){ //strike
			g.setColor(Color.RED);
			g.drawString("Strike", 325,250);
		}

		if(true){ //normal hit
			g.setColor(Color.BLUE);
			if(true){  //single 
				g.drawString("Single", 325,250);
			}
			if(true){  //double
				g.drawString("Double", 325,250);
			}
			if(true){  //double
				g.drawString("Triple", 325,250);
			}
		}*/
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
			return;
		}
		
		String inningStatement = "";
		
		switch (object.inning){
			case 1: 
				inningStatement = "1^";
				break;
			case 2:
				inningStatement = "1v";
				break;
			case 3: 
				inningStatement = "2^";
				break;
			case 4:
				inningStatement = "2v";
				break;
			case 5: 
				inningStatement = "3^";
				break;
			case 6:
				inningStatement = "3v";
				break;
			case 7: 
				inningStatement = "4^";
				break;
			case 8:
				inningStatement = "4v";
				break;
			case 9: 
				inningStatement = "5^";
				break;
			case 10:
				inningStatement = "5v";
				break;
			case 11: 
				inningStatement = "6^";
				break;
			case 12:
				inningStatement = "6v";
				break;
			case 13: 
				inningStatement = "7^";
				break;
			case 14:
				inningStatement = "7v";
				break;
			case 15: 
				inningStatement = "8^";
				break;
			case 16:
				inningStatement = "8v";
				break;
			case 17: 
				inningStatement = "9^";
				break;
			case 18:
				inningStatement = "9v";
				break;
			case 19: 
				inningStatement = "10^";
				break;
			case 20:
				inningStatement = "10v";
				break;
			case 21: 
				inningStatement = "11^";
				break;
			case 22:
				inningStatement = "11v";
				break;
			case 23: 
				inningStatement = "12^";
				break;
			case 24:
				inningStatement = "12v";
				break;
		}
		
		System.out.println("Inning = " + inning + ", InningStatement = " + inningStatement);
		
		homeRun = object.homeRun;
		sp.inningChange(inningStatement);
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
			theMsg = new GameMessage("BATTER", x, y, "", "", strikes, outs, homeRun, scoreA, scoreB, onBase, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, username, team_choice);
		} else{
			theMsg = new GameMessage("PITCHER", x, y, "", "", strikes, outs, homeRun, scoreA, scoreB, onBase, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, username, team_choice);

		}
		
		firstMsg = false;
		return theMsg;
		
	}
	
	
	
	
	
	
}
