import java.io.Serializable;


public class Message implements Serializable{
	private String message; 
	private String username;
	private String recipient;
	int deleteme;
	
	public Message(String text, int number, String user){
		message = text;
		deleteme = number;
		username = user;
		
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getUsername(){
		return username;
	}


	

}
