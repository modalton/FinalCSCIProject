import java.io.IOException;


public class ChatServer extends Server<Message>{
	ChatServer(int port, int amt_of_players){
		super(port, amt_of_players);
	}
	
	public static void main(String[] args) throws IOException {
    	Server<Message> temp = new ChatServer(4444, 4);
    }

	@Override
	public <T> void doServerAction(T object) {
		// TODO Auto-generated method stub
		sendToAll(object);
	}

	
}
