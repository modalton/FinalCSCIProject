import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LoginPanel extends JPanel implements ClientProcessInterface<LoginMessage>{
	JTextField username_input;
	JComboBox teamchoice;
	JButton sendmessage;
	boolean hasmessage = false;
	
	LoginPanel(){
		super();
		
		username_input = new JTextField();
		JLabel username_text = new JLabel("Username:");
		
		teamchoice = new JComboBox();
		teamchoice.addItem("Team 1");
		teamchoice.addItem("Team 2");
		
		sendmessage = new JButton("Join Team");
		
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
		finalgui.add(centerpanel);
		finalgui.add(bottompanel);
		
		
		add(finalgui);
		
		//actionlisteners to button
		sendmessage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hasmessage = true;
			}
			
		});
		
	}
	
	
	public static void main(String[] args){
		JFrame temp = new JFrame();
		LoginPanel ugga = new LoginPanel();
		temp.add(ugga);
		temp.setSize(300,200);
		temp.setVisible(true);
		temp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Client<LoginMessage, LoginPanel> network = new Client<LoginMessage, LoginPanel>(4444,ugga);
		
	}


	@Override
	public void processInputObject(LoginMessage object) {
		// TODO Auto-generated method stub
		System.out.println(object.desired_team);
		hasmessage = false;
		
	}


	@Override
	public boolean hasOutputObject() {
		// TODO Auto-generated method stub
		return hasmessage;
	}


	@Override
	public LoginMessage processOutputObject() {
		// TODO Auto-generated method stub
		System.out.println(teamchoice.getSelectedItem().toString());
		return new LoginMessage(teamchoice.getSelectedItem().toString());
	}
}
