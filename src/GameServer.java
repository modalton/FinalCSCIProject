
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
		
		switch (sender){
			case serverSender:
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
				//pitcher stays
			}
		}
		
		//SendMessage
	}
	
	private void updateScore(){
		
	}
	
	private void updateBases(int advanceNum){ //Takes in a number to advance all players by
		//Calls update score depending on if anyone gets to the homerun
		
	}
	
	private void changeInnings(){
		
	}
	
	private void changeBatter(){
		
	}
	
	private void changePitcher(){
		
	}
	
	private void sendMessage(){
		
	}

	@Override
	public <T> void doServerAction(T object) {
		// TODO Auto-generated method stub
		msg = (GameMessage) object;
	}
}
