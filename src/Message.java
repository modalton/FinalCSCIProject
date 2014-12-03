import java.io.Serializable;


public class Message implements Serializable{
	private String message; 
	private String username;
	private String recipient;
	private String team;
	int deleteme;
	
	public Message(String text, int number, String user, String recip, String t){
		message = text;
		deleteme = number;
		username = user;
		recipient = recip;
		team = t;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getRecipient(){
		return recipient;
	}

	public String getTeam(){
		return team;
	}

	

}