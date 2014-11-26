import java.io.Serializable;


public class LoginMessage implements Serializable{
	boolean team1full;
	boolean team2full;
	
	boolean nametaken = false;
	boolean teamtaken = false;
	//changes//
	
	String desired_team;
	String desired_username;
	
	public LoginMessage(String team, String name){
		desired_team=team;
		desired_username = name;
	}

}
