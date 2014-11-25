import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ChatPanel extends JPanel implements ClientProcessInterface<Message> {
	//gui stuff
	JTextArea groupchat;
	JTextArea teamchat;
	JTabbedPane chatview;
	
	//user gui input stuff
	JTextField userinput;
	JButton sendmessage;
	boolean haveamessage;
	
	//user data
	String username;
	int messages_sent;
	
	public ChatPanel(){
		super(new BorderLayout());
		
		//Create group/team text areas
		groupchat = new JTextArea();
		groupchat.setEditable(false);
		teamchat = new JTextArea();
		teamchat.setEditable(false);
		
		
		//create tabbed pane and add text areas
		chatview = new JTabbedPane();
		chatview.add("Group Chat", groupchat);
		chatview.add("Team Chat", teamchat);
		
		
		//create user input & send message button and add to minipanel
		userinput = new JTextField();
		sendmessage = new JButton("Send Message");
		
		JPanel bottombar = new JPanel(new BorderLayout());
		bottombar.add(userinput, BorderLayout.CENTER);
		bottombar.add(sendmessage, BorderLayout.EAST);
		
		
		//add final componenets to self
		this.add(chatview, BorderLayout.CENTER);
		this.add(bottombar, BorderLayout.SOUTH);
		
		
		//set to true to shoot off first message to serer with username
		messages_sent = 0;
		haveamessage = true;
		
		
		//add actionlistener to jbutton. will set haveamessage to true so client thread will send message 
		//from output of processOutputObject()
		sendmessage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				haveamessage = true;
				
			}
			
		});
		
	}
	
	
	
	
	@Override
	public void processInputObject(Message temp) {
		// TODO Auto-generated method stub
		groupchat.append("\n"+ temp.getUsername() + ":   "+ temp.getMessage());
		
	}

	@Override
	public Message processOutputObject() {
		// TODO Auto-generated method stub
		haveamessage = false;
		String temp = userinput.getText();
		return new Message(temp, messages_sent++, username);
	}
	
		@Override
	public boolean hasOutputObject() {
		// TODO Auto-generated method stub
		return haveamessage;
	}
		
	




}
