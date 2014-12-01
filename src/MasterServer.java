import java.io.IOException;


public class MasterServer {
	public static void main(String[] args) throws IOException {
		
    	Server<LoginMessage> login = new LoginServer(4444, 4);
    	Server<Message> chat = new ChatServer(4445, 4);
    	Server<GameMessage> game = new GameServer(4446, 4);
    	login.start();
    	chat.start();
    	game.start();
    	
    	
    	
    }
}
