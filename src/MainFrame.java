import java.awt.BorderLayout;
import java.awt.CardLayout;

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
	
	static JPanel outerPanel;
	static JPanel loginCard;
	static JPanel gameCard;
	
	//login/game card names
	final static String LOGINCARD = "LOGIN";
	final static String GAMECARD = "GAME";
	
	public MainFrame() {
		super("Baseball Game");
		
		setSize(800,600);
		
		//panel initialization
		outerPanel = new  JPanel(new CardLayout());
		gameCard = new JPanel(new BorderLayout());
		loginCard = new LoginPanel();
		gamePanel = new GamePanel();
		chatPanel = new ChatPanel();
		//lineupPanel = new LineupPanel();
		
		
		//add panels to game card
		gameCard.add(gamePanel, BorderLayout.CENTER);
		gameCard.add(chatPanel, BorderLayout.SOUTH);
		//gameCard.add(lineupPanel, BorderLayout.SOUTH);
		
		//add cards to outer panel
		outerPanel.add(loginCard, LOGINCARD);
		outerPanel.add(gameCard, GAMECARD);
		
		//for right now just show game
		CardLayout cl = (CardLayout)outerPanel.getLayout();
		cl.show(outerPanel, GAMECARD);
		
		//add outer panel and make visible
		add(outerPanel);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main (String []args) {
		MainFrame mf = new MainFrame();
	}
	
}
