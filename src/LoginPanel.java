import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LoginPanel extends JPanel implements ClientProcessInterface<LoginMessage>{
	JTextField username_input;
	JComboBox teamchoice;
	JButton sendmessage;
	JLabel nametaken;
	boolean hasmessage = false;
	String username;
	
	LoginPanel(JPanel cardPanel, CardLayout cl){
		super();
		//instantiate username label, combo box, and jbutton
		username_input = new JTextField();
		JLabel username_text = new JLabel("Username:");
		
		teamchoice = new JComboBox();
		teamchoice.addItem("Team 1");
		teamchoice.addItem("Team 2");
		
		sendmessage = new JButton("Join Team");
		
		nametaken = new JLabel("Username Taken");
		nametaken.setVisible(false);
		
		//jpanels to help formatting
		JPanel bottompanel = new JPanel();
		bottompanel.add(teamchoice);	
		bottompanel.add(sendmessage);	
		
		//jpanels to help formatting
		JPanel centerpanel = new JPanel(new BorderLayout());
		centerpanel.add(username_text, BorderLayout.WEST);	
		centerpanel.add(username_input, BorderLayout.CENTER);	
		
		//add final gui to main login panel
		JPanel finalgui = new JPanel();
		finalgui.setLayout(new BoxLayout(finalgui, BoxLayout.PAGE_AXIS));
		finalgui.add(nametaken);
		finalgui.add(centerpanel);
		finalgui.add(bottompanel);
		
		
		add(finalgui);
		
		//actionlisteners to button
		sendmessage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hasmessage = true;
				sendmessage.setEnabled(false);
				nametaken.setVisible(false);
				
				//TEMPORARY -- should only switch to game when connection is established
				cl.show(cardPanel, "GAME");
			}
			
		});
		
	}
	
	

	@Override
	public void processInputObject(LoginMessage object) {
		// TODO Auto-generated method stub
		System.out.println(object.desired_team + "  " + object.desired_username);
		if(object.nametaken){
			System.out.println("name taken");
			nametaken.setVisible(true);
			sendmessage.setEnabled(true);
			}
		else{
			System.out.println("your new name is above");
			username = new String(object.desired_username);
		}
		
		
	}


	@Override
	public boolean hasOutputObject() {
		// TODO Auto-generated method stub
		return hasmessage;
	}


	@Override
	public LoginMessage processOutputObject() {
		// TODO Auto-generated method stub
		hasmessage = false;
		System.out.println(teamchoice.getSelectedItem().toString());
		String attempt_username = username_input.getText();
		//username_input.setText("");
		return new LoginMessage(teamchoice.getSelectedItem().toString(), username_input.getText());
	}
}
