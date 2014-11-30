import java.io.Serializable;


public class LoginMessage implements Serializable{
	boolean team1full;
	boolean team2full;
	
	boolean newAccount = false;
	boolean nametaken = false;
	boolean teamtaken = false;
	boolean success_login = false;
	//changes//
	
	String desired_team;
	String desired_username;
	String attempted_password;
	
	public LoginMessage(String team, String name, String pword, boolean newAccount){
		desired_team=team;
		desired_username = name;
		attempted_password = pword;
		this.newAccount = newAccount; //Should be true if want to create new account, false otherwise
	}

}
