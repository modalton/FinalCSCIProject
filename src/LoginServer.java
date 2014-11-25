import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LoginServer extends Server<LoginMessage> {
	List<String> all_usernames = Collections.synchronizedList(new ArrayList<String>());
	
	LoginServer(int port, int amount_of_players) {
		super(port, amount_of_players);
		
		
		
	}

	
	
	@Override
	public <T> void doServerAction(T object) {
		// TODO Auto-generated method stub
		sendToAll(object);
	}

	boolean CanIUseName(String name){
		for(String s : all_usernames){
			if(s.equals(name)){return false;}
		}
		return true;
	}
	
	boolean Team1Filled(){
		if(team1.size()>= total_players/2){return true;}
		else{ return false;}
	}
	
	boolean Team2Filled(){
		if(team2.size()>= total_players/2){return true;}
		else{ return false;}
	}
	
	public static void main(String[] args) throws IOException {
    	Server<LoginMessage> temp = new LoginServer(4444, 4);
    }
	
}
