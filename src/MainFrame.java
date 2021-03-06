import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class MainFrame extends JFrame {
	
	//all panels -- they will be passed a socket and other data
	//based on the panel type
	
	//main game panel
	static GamePanel gamePanel;
	
	//panel that has the next players
	//static LineupPanel lineupPanel;
	
	static ChatPanel chatPanel;
	
	//outer panel and it's 2 cards
	static JPanel outerPanel;
	static LoginPanel loginCard;
	static JPanel gameCard;
	
	Client<LoginMessage, LoginPanel> client_login;
	Client<Message, ChatPanel> client_chat;
	Client<GameMessage, GamePanel> client_game;

	
	//login/game card names
	final static String LOGINCARD = "LOGIN";
	final static String GAMECARD = "GAME";
	
	public MainFrame() {
		super("Baseball Game");
		
		setSize(700,700);
		
		
		//panel initialization
		outerPanel = new  JPanel(new CardLayout());
		gameCard = new GameCard(new BorderLayout());
		CardLayout cardl = (CardLayout) outerPanel.getLayout();
		loginCard = new LoginPanel(outerPanel, cardl);
		gamePanel = new GamePanel();
		chatPanel = new ChatPanel();
		//lineupPanel = new LineupPanel();
		
		
		//initialize GUI controllers and clients
		client_login = new Client<LoginMessage, LoginPanel>(4444,loginCard);
		client_chat = new Client<Message, ChatPanel>(4445, chatPanel);
		client_game = new Client<GameMessage, GamePanel>(4446, gamePanel);

		
		
		//add panels to game card
		gameCard.add(gamePanel, BorderLayout.CENTER);
		gameCard.add(chatPanel, BorderLayout.SOUTH);
		//gameCard.add(lineupPanel, BorderLayout.SOUTH);
		
		
		
		//add cards to outer panel
		outerPanel.add(loginCard, LOGINCARD);
		outerPanel.add(gameCard, GAMECARD);
		
		//for right now just show game
		CardLayout cl = (CardLayout)outerPanel.getLayout();
		cl.show(outerPanel, LOGINCARD);
		
		//add outer panel and make visible
		add(outerPanel);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//for sake of modluarity constuctor contains only construciton and ClientCardInteraction defines handling of variables
		ClientCardInteraction();

	}
	
	
	void ClientCardInteraction(){

		client_login.start();
		
		//Can't start other clients until username from login panel is esatblished!!!
		while(loginCard.username == null){
			//sleep to free up cpu
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
		//set username for other clients and start them
		chatPanel.username = loginCard.username;
		chatPanel.team_choice = loginCard.teamchoice.getSelectedItem().toString();
		
		
		client_chat.start();
		
		gamePanel.username = loginCard.username;
		gamePanel.team_choice = loginCard.teamchoice.getSelectedItem().toString();
		
		//SET BOOLEAN IN GAMEPANEL SO THAT client CAN KNOW IF HE'S BATTING OR NOT
		if (gamePanel.team_choice.equals("Team 1")){
			gamePanel.isTeamA = true;
		} else
			gamePanel.isTeamA = false;
		
		client_game.start();
		//SWITCH TO GAME CARD HERE
	}
		
	public static void main (String []args) {
		MainFrame mf = new MainFrame();
	}
	
}
