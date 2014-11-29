import java.io.IOException;


public class ChatServer extends Server<Message>{
	ChatServer(int port, int amt_of_players){
		super(port, amt_of_players);
	}
	
	public static void main(String[] args) throws IOException {
    	Server<Message> temp = new ChatServer(4444, 4);
    }

	@Override
	public <T> void doServerAction(T object, ClientThread ct) {
		// TODO Auto-generated method stub
		Message first_msg = (Message) object;
		if(first_msg.deleteme == 0){
			System.out.print(ct.username);
			ct.username = first_msg.getUsername(); return;
		}
		sendToAll(object);
	}

	
}
