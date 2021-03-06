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
	//private boolean[] onBase; // what base people are on
	boolean gameOver, aWins, tieGame;
	boolean firstMsg;
	boolean aBatting;
	boolean isTeamA;
	boolean homeRun;
	SpriteAnimation pitcher;
	SpriteAnimation batter;
	Ball ball;
	ScorePanel sp;
	boolean onFirst,onSecond, onThird;
	boolean isUp;
	String batterSn, pitcherSn;
	
	String inningStatement;
	
	JLabel myTurn;
	JLabel thePlay;
	
	BatterGrid bg;
	
	JPanel test;
	DiamondPanel dp;

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

		scoreA = 0;
		scoreB = 0;
		base = 0;
		inning = 0; 
		strikes = 0;
		outs = 0;
		
		isUp = false;
		pitcher = new SpriteAnimation(pitcherLabel, false); //Second parameter is "isBatter"
		batter = new SpriteAnimation(batterLabel, true);
		ball = new Ball(this);
/*		pitcher.start();
		batter.start();*/
		
		myTurn = new JLabel ("YOUR TURN", SwingConstants.CENTER);
		myTurn.setBounds(250,  30, 200, 20);
		myTurn.setBackground(Color.LIGHT_GRAY);
		myTurn.setForeground(Color.RED);
		myTurn.setOpaque(true);
		add(myTurn);
		myTurn.setVisible(false);
		
		thePlay = new JLabel ("Homerun", SwingConstants.CENTER);
		thePlay.setBounds(10,  300, 200, 50);
		thePlay.setBackground(Color.LIGHT_GRAY);
		thePlay.setForeground(Color.RED);
		thePlay.setOpaque(true);
		add(thePlay);
		thePlay.setVisible(false);
		
		sp = new ScorePanel();
		sp.setBounds(0, 0, 700, 30);
		add(sp);
		
		bg = new BatterGrid(this);
		bg.setBounds(325, 325, 100, 100);
		add(bg);
		
		
		
		dp = new DiamondPanel();
		dp.setBounds(550,20, 100, 100);
		add(dp);
		
		
		
		//HAVE TO KNOW WHAT TEAM THIS GAMEPANEL'S CLIENT IS ON
		
		setOpaque(false);
		
		firstMsg = true;
		hasMessage = true;
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int x = getWidth();
		int y = getHeight();
		
		
		//draws ball
		ball.drawSelf(g, x, y);//////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
	}


	@Override
	public void processInputObject(GameMessage object) {
		
		//Extract information from message
		
		/*ANIMATIONS WILL NOT BEGIN UNTIL 
		THERE IS AT LEAST ONE BATTER 
		AND ONE PITCHER*/
		if(object.firstMsg){
			System.out.println("THINKS SERVER IS SENDING FIRST MESSAGE");
			pitcher.start();
			batter.start();
			ball.start();

			if (object.aBat){
				aBatting = true;
				if (isTeamA){
					isBatting = true;
				} else
					isBatting = false;
			} else {
				aBatting = false;
				if (isTeamA){
					isBatting = false;
				} else
					isBatting = true;
			} 
			
			this.batterSn = object.batterSn;
			this.pitcherSn = object.pitcherSn;

			if (isBatting){
				if (this.username.equals(batterSn)){
					bg.setEnabledHandlers(true);
					isUp = true;
					myTurn.setText("YOU'RE BATTING, " + batterSn);
					myTurn.setBackground(Color.GREEN);
					myTurn.setVisible(true);
					repaint();
					revalidate();
					updateUI();
				} else{
					bg.setEnabledHandlers(false);
					isUp = false;
					myTurn.setText(batterSn + "'S BATTING");
					myTurn.setBackground(Color.DARK_GRAY);
					myTurn.setVisible(true);
					repaint();
					revalidate();
					updateUI();
				}
			} else{
				if (this.username.equals(pitcherSn)){
					bg.setEnabledHandlers(true);
					isUp = true;
					myTurn.setText("YOU'RE PITCHING, " + pitcherSn);
					myTurn.setBackground(Color.GREEN);
					myTurn.setVisible(true);
					repaint();
					revalidate();
					updateUI();
				} else {
					bg.setEnabledHandlers(false);
					isUp = false;
					myTurn.setText(pitcherSn + "'S PITCHING");
					myTurn.setBackground(Color.DARK_GRAY);
					myTurn.setVisible(true);
					repaint();
					revalidate();
					updateUI();
				}
			}
			
			return;
		}
		
		
		thePlay.setVisible(false);
		repaint();
		revalidate();
		updateUI();
		
		homeRun = object.homeRun;

		if (homeRun){
			thePlay.setText("HOMERUN!"); 
			thePlay.setBackground(Color.YELLOW);
			thePlay.setForeground(Color.RED);
			thePlay.setOpaque(true);
			thePlay.setVisible(true);
			repaint();
			revalidate();
			updateUI();
		}
		else if (this.scoreA < object.scoreA){
			thePlay.setText("RUN SCORED!"); 
			thePlay.setBackground(Color.WHITE);
			thePlay.setForeground(Color.BLACK);
			thePlay.setOpaque(true);
			thePlay.setVisible(true);
			repaint();
			revalidate();
			updateUI();
		} else if (this.scoreB < object.scoreB){
			thePlay.setText("RUN SCORED!"); 
			thePlay.setBackground(Color.WHITE);
			thePlay.setForeground(Color.BLACK);
			thePlay.setOpaque(true);
			thePlay.setVisible(true);
			repaint();
			revalidate();
			updateUI();
		} else if (this.strikes < object.strikes){
			thePlay.setText("STRIKE!"); 
			thePlay.setBackground(Color.PINK);
			thePlay.setForeground(Color.WHITE);
			thePlay.setOpaque(true);
			thePlay.setVisible(true);
			repaint();
			revalidate();
			updateUI();
		} else if (this.outs < object.outs){
			thePlay.setText("OUT!"); 
			thePlay.setBackground(Color.BLUE);
			thePlay.setForeground(Color.YELLOW);
			thePlay.setOpaque(true);
			thePlay.setVisible(true);
			repaint();
			revalidate();
			updateUI();
		} else if (this.inning < object.inning){
			thePlay.setText("INNING CHANGE"); 
			thePlay.setBackground(Color.GRAY);
			thePlay.setForeground(Color.RED);
			thePlay.setOpaque(true);
			thePlay.setVisible(true);
			repaint();
			revalidate();
			updateUI();
		}
		
		inningStatement = "";
		
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
		
		pitcher.setIsStatic(false);
		ball.ready = true;

		Thread t = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
					Thread.sleep(2000);
					batter.setIsStatic(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		t.start();
		
		
		this.onFirst = object.onFirst;
		this.onSecond = object.onSecond;
		this.onThird = object.onThird;
		//this.onBase = object.onBase;
		System.out.println("****");
		System.out.println("onFirst = " + onFirst);
		System.out.println("onSecond = " + onSecond);
		System.out.println("onThird = " + onThird);
		System.out.println("****");

		
		Thread a = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sp.inningChange(inningStatement);
				sp.batterStrike(object.strikes);
				sp.batterOut(object.outs);
				sp.addScore(object.scoreA, object.scoreB);
				sp.repaint();
			    sp.revalidate();
				sp.updateUI();
				dp.baseChanged(onFirst, onSecond, onThird);

			}
		});
		a.start();
		
		

	

		remove(sp);
		sp.setBounds(0, 0, 700, 30);
		add(sp);
			
		if (object.aBat){
			aBatting = true;
			if (isTeamA){
				isBatting = true;
			} else
				isBatting = false;
		} else {
			aBatting = false;
			if (isTeamA){
				isBatting = false;
			} else
				isBatting = true;
		} 
		
		this.batterSn = object.batterSn;
		this.pitcherSn = object.pitcherSn;
		
		if (object.gameOver){
			if (object.tieGame){
				JLabel iWin = new JLabel ("TIE GAME!", SwingConstants.CENTER);
				iWin.setBounds(0,  0, 700, 500);
				iWin.setBackground(Color.gray);
				iWin.setForeground(Color.GREEN);
				iWin.setOpaque(true);
				add(iWin);
				iWin.setVisible(true);
			}
			else if (object.aWins){
				if (this.isTeamA){
					JLabel iWin = new JLabel ("YOU WIN!", SwingConstants.CENTER);
					iWin.setBounds(0,  0, 700, 500);
					iWin.setBackground(Color.gray);
					iWin.setForeground(Color.GREEN);
					iWin.setOpaque(true);
					add(iWin);
					iWin.setVisible(true);
				} else {
					JLabel iLose = new JLabel ("YOU LOSE!", SwingConstants.CENTER);
					iLose.setBounds(0,  0, 700, 500);
					iLose.setBackground(Color.DARK_GRAY);
					iLose.setForeground(Color.RED);
					iLose.setOpaque(true);
					add(iLose);
					iLose.setVisible(true);
				}
			} else {
				if (this.isTeamA){
					JLabel iLose = new JLabel ("YOU LOSE!", SwingConstants.CENTER);
					iLose.setBounds(0,  0, 700, 500);
					iLose.setBackground(Color.DARK_GRAY);
					iLose.setForeground(Color.RED);
					iLose.setOpaque(true);
					add(iLose);
					iLose.setVisible(true);
					
				} else {
					JLabel iWin = new JLabel ("YOU WIN!", SwingConstants.CENTER);
					iWin.setBounds(0,  0, 700, 500);
					iWin.setBackground(Color.gray);
					iWin.setForeground(Color.GREEN);
					iWin.setOpaque(true);
					add(iWin);
					iWin.setVisible(true);
				}
			}
		}

		if (isBatting){
			if (this.username.equals(batterSn)){
				bg.setEnabledHandlers(true);
				isUp = true;
				myTurn.setText("YOU'RE BATTING, " + batterSn);
				myTurn.setBackground(Color.GREEN);
				myTurn.setVisible(true);
				repaint();
				revalidate();
				updateUI();
			} else{
				bg.setEnabledHandlers(false);
				isUp = false;
				myTurn.setText(batterSn + "'S BATTING");
				myTurn.setBackground(Color.DARK_GRAY);
				myTurn.setVisible(true);
				repaint();
				revalidate();
				updateUI();
			}
		} else{
			if (this.username.equals(pitcherSn)){
				bg.setEnabledHandlers(true);
				isUp = true;
				myTurn.setText("YOU'RE PITCHING, " + pitcherSn);
				myTurn.setBackground(Color.GREEN);
				myTurn.setVisible(true);
				repaint();
				revalidate();
				updateUI();
			} else {
				bg.setEnabledHandlers(false);
				isUp = false;
				myTurn.setText(pitcherSn + "'S PITCHING");
				myTurn.setBackground(Color.DARK_GRAY);
				myTurn.setVisible(true);
				repaint();
				revalidate();
				updateUI();
			}
		}
		
		this.strikes = object.strikes;
		this.outs = object.outs;
		this.inning = object.inning;
		this.scoreA = object.scoreA;
		this.scoreB = object.scoreB;
		
		
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
			theMsg = new GameMessage("BATTER", x, y, "", "", strikes, outs, homeRun, scoreA, scoreB, onFirst, onSecond, onThird, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, username, team_choice);
		} else{
			theMsg = new GameMessage("PITCHER", x, y, "", "", strikes, outs, homeRun, scoreA, scoreB, onFirst, onSecond, onThird, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, username, team_choice);

		}
		
		firstMsg = false;
		return theMsg;
		
	}	
}
