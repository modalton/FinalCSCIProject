import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;


public class LoginServer extends Server<LoginMessage> {
	List<String> all_usernames = Collections.synchronizedList(new ArrayList<String>());
	
	ReentrantLock server_lock = new ReentrantLock();
	
	LoginServer(int port, int amount_of_players) {
		super(port, amount_of_players);
		System.out.println(all_usernames.size());
		
		
		
	}

	
	
	@Override
	public <T> void doServerAction(T object, ClientThread ct) {
		// TODO Auto-generated method stub
		LoginMessage status = (LoginMessage) object;
		
		CreateAccountCommand query = new CreateAccountCommand(server_lock, status.desired_username, status.attempted_password);
		status.success_login = query.execute();
		
		
		
		//PRE- SQL stuff
		if(all_usernames.contains(status.desired_username)){
			status.nametaken = true;
		}
		else{
			all_usernames.add(status.desired_username);
		}
		//
		
		
		ct.messageClient(status);
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
