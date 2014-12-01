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
	int strikes, outs;
	boolean inningChange, pitChange, batChange;
	boolean[] onBase; // what base people are on
	boolean gameOver, aWins, tieGame;
	boolean firstMsg;
	boolean aBatting;

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

		
		SpriteAnimation sa = new SpriteAnimation(pitcherLabel, false);
		SpriteAnimation sa2 = new SpriteAnimation(batterLabel, true);
		sa.start();
		sa2.start();
		
		
		ScorePanel sp = new ScorePanel();
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

	//Add actionListener for pitcher/batter click in Batting Box
	//ActionListener must toggle hasMessage
	
	@Override
	public void processInputObject(GameMessage object) {
		//Reads GameMessage
		//Update scorebox and lineup panel depending on GameMessage from Sever
		
		//Extract information from message
		isBatting = object.aBat; //etc.
		
		
		
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
		if (isBatting){
			theMsg = new GameMessage("BATTER", x, y, "", "", scoreA, scoreB, onBase, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, username, team_choice);
		} else{
			theMsg = new GameMessage("PITCHER", x, y, "", "", scoreA, scoreB, onBase, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, username, team_choice);

		}
		
		return theMsg;
		
	}
	
	
	
	
	
	
}
