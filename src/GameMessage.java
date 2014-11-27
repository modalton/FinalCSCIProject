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
	public int bases;
	public int inning;
	public boolean changePit, changeBat;
	public boolean isUp;
	
	
	public GameMessage (String sender, int x, int y, boolean isUp, int score1, int score2, int base, boolean inningChange, int inning, boolean pitChange, boolean batChange){
		msgSender = sender;
		gridX = x;
		gridY = y;
		this.isUp = isUp;
		scoreA = score1;
		scoreB = score2;
		bases = base;
		changeIn = inningChange;
		this.inning = inning;
		changePit = pitChange;
		changeBat = batChange;
	}

}
