import java.io.Serializable;


public class GameMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String msgSender, batterSn, pitcherSn; //Who is sending (server or batter/pitcher)
	public String username, team_choice; //What's the username and the team_choice
	public int gridX, gridY; //for the grid
	public boolean firstMsg;
	public int strikes, outs, scoreA, scoreB;
	public boolean changeIn;
	public boolean []onBase;
	public int inning;
	public boolean changePit, changeBat;
	public boolean isUp;
	public boolean aBat;
	public boolean gameOver, aWins, tieGame;
	public boolean homeRun;
	boolean onFirst,onSecond, onThird;
	
	
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
	public GameMessage (String sender, int x, int y, String batterSn, String pitcherSn, int strikes, int outs, boolean homeRun, int score1, int score2, boolean onFirst, boolean onSecond, boolean onThird, boolean inningChange, int inning, boolean pitChange, boolean batChange, boolean aBatting, boolean gameOver, boolean aWins, boolean tieGame, boolean firstMsg, String username, String team_choice){
		msgSender = sender;
		
		
		gridX = x;
		gridY = y;
		
		//only if your batter/pitcher is up
		this.batterSn = batterSn;
		this.pitcherSn = pitcherSn;
		this.strikes = strikes;
		this.outs = outs;
		scoreA = score1;
		scoreB = score2;
		this.onFirst = onFirst;
		this.onSecond = onSecond;
		this.onThird = onThird;
		
		//at end of inning
		changeIn = inningChange;
		this.inning = inning;
		changePit = pitChange;
		changeBat = batChange;
		
		this.homeRun = homeRun;
		
		aBat = aBatting;
		
		//when game is over
		this.gameOver = gameOver;
		this.aWins = aWins;
		this.tieGame = tieGame;
		
		this.firstMsg = firstMsg;
		this.username = username;
		this.team_choice = team_choice;
		
		
	}

}
