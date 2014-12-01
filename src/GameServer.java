import java.util.Vector;


//HANDLE THE ENTIRE GAME

public class GameServer extends Server<GameMessage>{
	
	private static final String serverSender = "SERVER";
	private static final String batterSender = "BATTER";
	private static final String pitcherSender = "PITCHER";
	private static final int maxPlayers = 2;

	
	//Change these to make the game easier or harder (smaller number is harder)
	private static final int leeWaySingle = 1; 
	
	String sender;
	int bX, bY, pX, pY; //grid positions of batter and pitcher
	int scoreA, scoreB;
	int base, inning; 
	int strikes, outs;
	String batterSn = null, pitcherSn = null;
	int batterIndex, pitcherIndex;
	boolean firstMsg;
	boolean inningChange, pitChange, batChange;
	boolean receivedPitch, receivedBat;
	boolean[] onBase; // what base people are on
	boolean aBatting;
	boolean gameOver, aWins, tieGame;
	Vector<Vector<String>> teams = new Vector<Vector<String>>();
	
	GameMessage msg;
	
	public GameServer(int port, int amount_of_players) {
		super(port, amount_of_players);
		inning = 0;
		base = 0;
		scoreA = 0;
		scoreB = 0;
		receivedPitch = false;
		receivedBat = false;
		onBase = new boolean[3];
		for (int i = 0; i < 3; i++){
			onBase[i] = false;
		}
		aBatting = false;
		gameOver = false;
		aWins = false;
		tieGame = false;
		
		Vector<String> team1 = new Vector<String>();
		Vector<String> team2 = new Vector<String>();
		
		teams.add(team1);
		teams.add(team2);
		// TODO Auto-generated constructor stub
	}
	
	private void readMessage(){
		sender = msg.msgSender;
		
		if (msg.firstMsg){ //Update the teams
			if (msg.team_choice.equals("Team 2")){
				teams.elementAt(1).add(msg.username);
				if (batterSn == null){
					batterSn = msg.username; 
					batterIndex = 0; //Used to iterate through the teams vector to get the next batter
					System.out.println("\n\n&&&*******&&&\nRECEIVED FIRST BATTER, name = " + batterSn + "\n&&&******&&&\n\n");
				}
				aBatting = false; //Away team bats first
			}
			else{
				teams.elementAt(0).add(msg.username);
				if (pitcherSn == null){
					pitcherSn = msg.username;
					pitcherIndex = 0; //Used to iterate through the vector to get the next pitcher
					System.out.println("\n\n&&&*******&&&\nRECEIVED FIRST PITCHER, name = " + pitcherSn + "\n&&&******&&&\n\n");
				}
			}
		} else{
			switch (sender){
				case serverSender: //When you receive a message from the server, it must be a new play
					bX = -1;
					bY = -1;
					pX = -1;
					pY = -1;
					receivedBat = false;
					receivedPitch = false;
					break;
				case batterSender:
					bX = msg.gridX;
					bY = msg.gridY;
					receivedBat = true;
					break;
				case pitcherSender:
					pX = msg.gridX;
					pY = msg.gridY;
					receivedPitch = true;
					break;
			}

			if (receivedBat && receivedPitch){
				processPlay();
			}
		}
			
	}
	
	private void processPlay(){
		inningChange = false;
		//if batter and pitcher choose exact same grid
		if (bX == pX && bY == pY){
			//Batting team hits a home run
			updateBases(4);
			changeBatter();
			changePitcher();
		//
		}
		// if batter is within 1 squares away from hit
		else if (bX > pX-leeWaySingle && bX < pX+leeWaySingle){ //Single
			if (bY > pY-leeWaySingle && bY < pY+leeWaySingle){
				//Update bases
				updateBases(1);
				changeBatter();
				changePitcher();
			}
		}
		// not within 1 squares --> no hit
		else{
			//Update strikes
			strikes++;
			if (!(strikes < 3)){
				changeBatter();
				pitChange = false;
				//pitcher stays
			} else{
				outs++; //Add an out
				if (outs >= 3){ //Check if innings have to change
					changeInning();
				}
			}
		}
		
		//SendMessage
		sendMessage();
	}
	
	private void updateScore(int addScore){
		if (aBatting)
			scoreA = scoreA + addScore;
		else
			scoreB = scoreB + addScore;
	}
	
	//Takes in a number to advance all players by
	private void updateBases(int advanceNum){ 
		//Calls update score depending on if anyone gets to the home base
		int addScore = 0;
		
		//if on 3rd base
		if (onBase[2]){
			if (advanceNum >= 1){
				addScore++;
				onBase[2] = false;
			}
		}
		
		//if on 2nd base
		if (onBase[1]){
			if (advanceNum == 1){
				onBase[2] = true;
				onBase[1] = false;
			} else if (advanceNum > 1){
				onBase[1] = false;
				addScore++;
			}
		}
		
		//if on 1st base
		if(onBase[0]){
			if(advanceNum == 1){
				onBase[1] = true;
				onBase[0] = false;
			} else if (advanceNum == 2){
				onBase[2] = true;
				onBase[0] = false;
			} else if (advanceNum > 2){
				onBase[0] = false;
				addScore++;
			}
		}
		updateScore(addScore); //update the score
	}
	
	private void changeInning(){ //9 innings in a regular game, maximum of 12 innings in extra
		//Change who's batting
		aBatting = !aBatting;
		
		inningChange = true;
		
		batterIndex = 0;
		pitcherIndex = 0;
		
		for (int i = 0; i < 3; i++){ //Empty all the bases
			onBase[i] = false; 
		}
		
		inning++;
		
		//Check if game is over
		if (inning == 10){
			if (scoreA != scoreB){
				gameOver = true;
				if (scoreA > scoreB)
					aWins = true;
				else
					aWins = false;
			}
		} else if (inning == 13){
			gameOver = true;
			if (scoreA != scoreB){
				if (scoreA > scoreB)
					aWins = true;
				else
					aWins = false;
			} else{
				tieGame = true;
			}
		}
	}
	
	private void changeBatter(){
		//Reset the strike count
		batChange = true;
		strikes = 0;
		batterIndex++;
		if (batterIndex >= maxPlayers){ 
			batterIndex = 0;
		}
		if (aBatting){//Team 1 batting
			batterSn = teams.elementAt(0).get(batterIndex);
		} else {
			batterSn = teams.elementAt(1).get(batterIndex);
		}
	}
	
	private void changePitcher(){
		pitChange = true;
		pitcherIndex++;
		if (pitcherIndex >= maxPlayers){ 
			pitcherIndex = 0;
		}
		if (aBatting){//Team 1 batting
			pitcherSn = teams.elementAt(1).get(pitcherIndex);
		} else {
			pitcherSn = teams.elementAt(0).get(pitcherIndex);
		}
	}
	
	private void sendMessage(){
		firstMsg = false;
		GameMessage theMsg = new GameMessage ("SERVER", bX, bY, batterSn, pitcherSn, scoreA, scoreB, onBase, inningChange, inning, pitChange, batChange, aBatting, gameOver, aWins, tieGame, firstMsg, "", "");
		//Send the message
		sendToAll(theMsg);
	}


	@Override
	public <T> void doServerAction(T object, ClientThread ct) {
		// TODO Auto-generated method stub
		msg = (GameMessage) object;
		readMessage();

		
	}


}
