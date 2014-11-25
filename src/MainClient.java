import javax.swing.*;


public class MainClient extends JFrame{
	LoginPanel login;
	ChatPanel chat;
	
	MainClient(){
		super();
		
		login = new LoginPanel();
		chat = new ChatPanel();
		chat.username =  "NAME GOOD SIR";
		
		add(chat);
		
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Client<LoginMessage, LoginPanel> client_login = new Client<LoginMessage, LoginPanel>(4444,login);
		Client<Message, ChatPanel> client_chat = new Client<Message, ChatPanel>(4445, chat);
		
		client_login.start();
		client_chat.start();
		
		
		

	}
	
	
	
	public static void main(String[] args){
		MainClient done = new MainClient();
	}

		
	
	
}
