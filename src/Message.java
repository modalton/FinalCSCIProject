import java.io.Serializable;


public class Message implements Serializable{
	private String message; 
	
	int deleteme;
	
	public Message(String text, int number){
		message = text;
		deleteme = number;
	}
	
	public String getMessage(){
		return message;
	}
	

}
