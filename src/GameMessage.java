import java.io.Serializable;


public class GameMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String msgSender; //Who is sending (server or batter/pitcher)
	public int gridX, gridY; //for the grid

	public int scoreA, scoreB;
	public boolean changeIn;
	public boolean []onBase;
	public int inning;
	public boolean changePit, changeBat;
	public boolean isUp;
	public boolean aBat;
	public boolean gameOver, aWins, tieGame;
	
	
	/*
	 * sender = who is sending message, "server" or batter/pitcher
	 * x --> x coorinate
	 * y --> y coordinate
	 * isUp --> used to tell a client whether they are up or not
	 * scoreA --> first team's score
	 * scoreB --> second team's score
	 * onBase --> array of booleans telling clients who bases are full
	 * inningChange --> boolean telling client if inning has changed --> client knows to switch sides
	 * inning --> int telling client what inning it is
	 * changePit --> boolean telling client that pitcher needs to change
	 * changeBat --> boolean telling client that batter needs to change
	 * aBat --> boolean telling client whether or not team A is up to bat
	 * gameOver -->  boolean used to tell clients that the game is over
	 * aWins --> boolean that tells client if team A won (only applicable if game over)
	 */
	public GameMessage (String sender, int x, int y, boolean isUp, int score1, int score2, boolean []onBase, boolean inningChange, int inning, boolean pitChange, boolean batChange, boolean aBatting, boolean gameOver, boolean aWins, boolean tieGame){
		msgSender = sender;
		
		
		gridX = x;
		gridY = y;
		
		//only if your batter/pitcher is up
		this.isUp = isUp;
		
		scoreA = score1;
		scoreB = score2;
		this.onBase = onBase;
		
		//at end of inning
		changeIn = inningChange;
		this.inning = inning;
		changePit = pitChange;
		changeBat = batChange;
		
		
		aBat = aBatting;
		
		//when game is over
		this.gameOver = gameOver;
		this.aWins = aWins;
		this.tieGame = tieGame;
		
	}

}
