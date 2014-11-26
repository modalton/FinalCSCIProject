import java.awt.BorderLayout;

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
	
	public MainFrame() {
		super("Baseball Game");
		
		setSize(800,600);
		
		//panel initialization
		outerPanel = new JPanel(new BorderLayout());
		gamePanel = new GamePanel();
		chatPanel = new ChatPanel();
		//lineupPanel = new LineupPanel();
		
		
		outerPanel.add(gamePanel, BorderLayout.CENTER);
		outerPanel.add(chatPanel, BorderLayout.SOUTH);
		//outerPanel.add(lineupPanel, BorderLayout.SOUTH);
		add(outerPanel);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main (String []args) {
		MainFrame mf = new MainFrame();
	}
	
}
