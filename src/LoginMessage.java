import java.io.Serializable;


public class LoginMessage implements Serializable{
	boolean team1full;
	boolean team2full;
	
	String desired_team;
	
	public LoginMessage(String s){
		desired_team=s;
	}

}
