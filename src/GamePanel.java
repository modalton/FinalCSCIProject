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
	boolean gameOver, aWins;

	
	public GamePanel(){
		super();
		
		JLabel label = new JLabel();
		//JLabel label2 = new JLabel();
		add(label);
		//add(label2);
		SpriteAnimation sa = new SpriteAnimation(label, false);
		//SpriteAnimation sa2 = new SpriteAnimation(label2, true);
		sa.start();
		//sa2.start();
		
		//Add Batting Box
		//Enable user input
		//Add action listener
		
		//HAVE TO KNOW WHAT TEAM THIS GAMEPANEL'S CLIENT IS ON

		
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
		return null;
	}
	
	
	
	
	
	
}
