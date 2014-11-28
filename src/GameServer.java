
//HANDLE THE ENTIRE GAME

public class GameServer extends Server<GameMessage>{
	
	private static final String serverSender = "SERVER";
	private static final String batterSender = "BATTER";
	private static final String pitcherSender = "PITCHER";
	private static final int leeWayDouble = 1; //Change these to make the game easier or harder (smaller number is harder)
	private static final int leeWaySingle = 2; 
	
	String sender;
	int bX, bY, pX, pY;
	boolean isUp; 
	int scoreA, scoreB;
	int base, inning; 
	int strikes, outs;
	boolean inningChange, pitChange, batChange;
	boolean receivedPitch, receivedBat;
	boolean[] onBase;
	boolean aBatting, bBatting;
	boolean gameOver, aWins, bWins;
	
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
		bBatting = false;
		gameOver = false;
		aWins = false;
		bWins = false;
		// TODO Auto-generated constructor stub
	}
	
	private void readMessage(){
		sender = msg.msgSender;
		scoreA = msg.scoreA;
		scoreB = msg.scoreB;
		base = msg.bases;
		inning = msg.inning;
		inningChange = msg.changeIn;
		pitChange = msg.changePit;
		batChange = msg.changeBat;
		aBatting = msg.aBat;
		bBatting = msg.bBat;
		
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
				isUp = msg.isUp;
				receivedBat = true;
				break;
			case pitcherSender:
				pX = msg.gridX;
				pY = msg.gridY;
				isUp = msg.isUp;
				receivedPitch = true;
				break;
		}
		
		if (receivedBat && receivedPitch){
			processPlay();
		}
			
	}
	
	private void processPlay(){
		inningChange = false;
		if (bX == pX && bY == pY){
			//Batting team hits a home run
			updateBases(4);
			changeBatter();
			changePitcher();
		} else if (bX > pX-leeWayDouble && bX < pX+leeWayDouble){ //Double
			if (bY > pY-leeWayDouble && bY < pY+leeWayDouble){
				//Update bases
				updateBases(2);
				changeBatter();
				changePitcher();
			}
		} else if (bX > pX-leeWaySingle && bX < pX+leeWaySingle){ //Single
			if (bY > pY-leeWaySingle && bY < pY+leeWaySingle){
				//Update bases
				updateBases(1);
				changeBatter();
				changePitcher();
			}
		} else{ //No hit
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
		else if (bBatting)
			scoreB = scoreB + addScore;
	}
	
	private void updateBases(int advanceNum){ //Takes in a number to advance all players by
		//Calls update score depending on if anyone gets to the homerun
		int addScore = 0;
		if (onBase[2]){
			if (advanceNum >= 1){
				addScore++;
				onBase[2] = false;
			}
		}
		if (onBase[1]){
			if (advanceNum == 1){
				onBase[2] = true;
				onBase[1] = false;
			} else if (advanceNum > 1){
				onBase[1] = false;
				addScore++;
			}
		}
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
		bBatting = !bBatting;
		
		inningChange = true;
		
		for (int i = 0; i < 3; i++){
			onBase[i] = false;
		}
		
		inning++;
		
		if (inning == 9){
			if (scoreA != scoreB){
				gameOver = true;
				if (scoreA > scoreB)
					aWins = true;
				else
					bWins = true;
			}
		}
	}
	
	private void changeBatter(){
		//Reset the strike count
		batChange = true;
		strikes = 0;
	}
	
	private void changePitcher(){
		pitChange = true;
	}
	
	private void sendMessage(){
		GameMessage theMsg = new GameMessage ("SERVER", bX, bY, isUp, scoreA, scoreB, base, inningChange, inning, pitChange, batChange, aBatting, bBatting, gameOver, aWins, bWins);
		//Send the message
	}

	@Override
	public <T> void doServerAction(T object) {
		// TODO Auto-generated method stub
		msg = (GameMessage) object;
		readMessage();
	}
}
