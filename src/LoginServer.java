import java.io.IOException;


public class LoginServer extends Server<LoginMessage> {

	LoginServer(int port, int amount_of_players) {
		super(port, amount_of_players);
		System.out.println("mo");
		
	}

	public static void main(String[] args) throws IOException {
    	Server<LoginMessage> temp = new LoginServer(4444, 4);
    }
	
	@Override
	public <T> void doServerAction(T object) {
		// TODO Auto-generated method stub
		sendToAll(object);
	}

	
}
