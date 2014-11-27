import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.*;


public class MainClient extends JFrame{
	JPanel card_panel;
	LoginPanel login;
	ChatPanel chat;
	Client<LoginMessage, LoginPanel> client_login;
	Client<Message, ChatPanel> client_chat;
	
	MainClient(){
		super();
		
		//initialize gui
		card_panel = new JPanel();
		login = new LoginPanel();
		chat = new ChatPanel();
		
		//initialize gui controllers and clients
		client_login = new Client<LoginMessage, LoginPanel>(4444,login);
		client_chat = new Client<Message, ChatPanel>(4445, chat);
		
		
		//set card_panel's cardlayout and add cards
		card_panel.setLayout(new CardLayout());
		card_panel.add(login, "LOGINPANEL");
		card_panel.add(chat, "CHATPANEL");
		
		
		add(card_panel);
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//for sake of modluarity constuctor contains only construciton and ClientCardInteraction defines handling of variables
		ClientCardInteraction();
		

	}
	
	void ClientCardInteraction(){

		
		client_login.start();
		
		//Can't start other clients until username from login panel is esatblished!!!
		while(login.username == null){
			//sleep to free up cpu
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
		//set username for other clients and start them
		chat.username = login.username;
		client_chat.start();
		
		//switch card actions
		CardLayout panel_view = (CardLayout) card_panel.getLayout();
		panel_view.show(card_panel, "CHATPANEL");
	}
	
	
	
	public static void main(String[] args){
		MainClient done = new MainClient();
	}

		
	
	
}
