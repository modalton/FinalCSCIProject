import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LoginPanel extends JPanel implements ClientProcessInterface<LoginMessage>{
	JTextField username_input;
	JTextField userpass_input;
	JComboBox teamchoice;
	JButton sendmessage, createAccountButton;
	JLabel loginFailed, accountCreated, accountNotCreated;
	boolean hasmessage = false;
	String username;
	String team;
	boolean creatingAccount = false;
	JPanel cardPanel;
	CardLayout cl;
	
	LoginPanel(JPanel cardPanel, CardLayout cl){
		super();
		
		this.cardPanel = cardPanel;
		this.cl = cl;
		
		//instantiate username/pass label, combo box, and jbutton
		username_input = new JTextField();
		JLabel username_text = new JLabel("Username:");
		
		userpass_input = new JTextField();
		JLabel userpass_text = new JLabel("Password:");
		
		teamchoice = new JComboBox();
		teamchoice.addItem("Team 1");
		teamchoice.addItem("Team 2");
		
		createAccountButton = new JButton("              Create an Account              ");
		sendmessage = new JButton("Login and Join Team");
		
		loginFailed = new JLabel("Login Failed. Try Again.");
		loginFailed.setForeground(Color.RED);
		loginFailed.setVisible(false);
		
		accountNotCreated = new JLabel("Username exists.");
		accountNotCreated.setForeground(Color.RED);
		accountNotCreated.setVisible(false);
		
		accountCreated = new JLabel("Account created. Please login.");
		accountCreated.setForeground(Color.GREEN);
		accountCreated.setVisible(false);
		
		//jpanels to help formatting
		JPanel bottompanel = new JPanel();
		bottompanel.add(teamchoice);	
		bottompanel.add(sendmessage);	
		
		//jpanels to help formatting
		JPanel centerpanel = new JPanel(new BorderLayout());
		centerpanel.add(username_text, BorderLayout.WEST);	
		centerpanel.add(username_input, BorderLayout.CENTER);	
		
		JPanel under_centerpanel = new JPanel(new BorderLayout());
		under_centerpanel.add(userpass_text, BorderLayout.WEST);	
		under_centerpanel.add(userpass_input, BorderLayout.CENTER);	
		
		//add final gui to main login panel
		JPanel finalgui = new JPanel();
		finalgui.setLayout(new BoxLayout(finalgui, BoxLayout.PAGE_AXIS));
		
		//AkshayAkshayAkshay
		finalgui.add(accountNotCreated);
		finalgui.add(accountCreated);
		
		finalgui.add(loginFailed);
		finalgui.add(centerpanel);
		
		finalgui.add(under_centerpanel);
		
		//AKSHAYAKSHAYAKSHAY 
		//createAccountButton.setPreferredSize(new Dimension(900, 30));
		createAccountButton.setAlignmentX(under_centerpanel.CENTER_ALIGNMENT);
		finalgui.add(createAccountButton);
		
		finalgui.add(bottompanel);
		
		
		add(finalgui);
		
		//AKSHAYAKSHAYAKSHAY
		//centerpanel.setMaximumSize(new Dimension(10, 200));
	//	under_centerpanel.setMaximumSize(100);

		//actionlisteners to button
		sendmessage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				creatingAccount = false;
				hasmessage = true;
				sendmessage.setEnabled(false);

				loginFailed.setVisible(false);
				accountCreated.setVisible(false);
				accountNotCreated.setVisible(false);

			}
		});
		
		createAccountButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				creatingAccount = true;
				hasmessage = true;
				createAccountButton.setEnabled(false);
				sendmessage.setEnabled(true);
				loginFailed.setVisible(false);
				accountCreated.setVisible(false);
				accountNotCreated.setVisible(false);
			}
			
		});
		
	}
	
	

	@Override
	public void processInputObject(LoginMessage object) {
		// TODO Auto-generated method stub
		System.out.println(object.desired_team + "  " + object.desired_username);
		if(!object.success_login && !creatingAccount){
			loginFailed.setVisible(true);
			sendmessage.setEnabled(true);
		} else if (!object.success_login && creatingAccount){
			accountNotCreated.setVisible(true);
			createAccountButton.setEnabled(true);
		} else if (object.success_login && creatingAccount){
			accountNotCreated.setVisible(false);
			createAccountButton.setEnabled(true);
			accountCreated.setVisible(true);
			sendmessage.setEnabled(true);
		}
		else{
			System.out.println("your new name is above");
			username = new String(object.desired_username);
			cl.show(cardPanel, "GAME");
		}
		
		team = teamchoice.getSelectedItem().toString();
		
		
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
		return new LoginMessage(teamchoice.getSelectedItem().toString(), username_input.getText(), userpass_input.getText(), creatingAccount);
	}
}
