
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server<T> {
	ArrayList<ClientThread> all_connections;
	ArrayList<ClientThread> team1;
	ArrayList<ClientThread> team2;
    public static void main(String[] args) throws IOException {
    	Server<Message> temp = new Server<Message>(4444, 2);
    }
    
    
    public Server(int port, int amount_of_players){
    	all_connections = new ArrayList<ClientThread>();
    	
    	//Establishing server socket scope
        ServerSocket ss = null;
        try {
        	
        	//make server socket based on port number passed in
            ss = new ServerSocket(port);
            
            //lets a certain amount of players connect

            while(amount_of_players-- != 0){
            	System.out.println("connection " );
            	Socket player_connect = ss.accept();
            	ClientThread t = new ClientThread(player_connect);
            	all_connections.add(t);
            	t.start();
            	System.out.println("here");
            }
            
            
            
        } catch (IOException e) {
            System.err.println("Could not listen on port");
            System.exit(1);
        }

        
        while(true){}
        
        	//ss.close();
		   // clientSocket.close();
		    //serverresponse.close();
		    
        	
       
        
        
    }
    
    public <T> void sendToTeamOne(T object){
    	
    }
    
    public <T> void sendToTeamTwo(T yhing){
    	
    }

	public <T> void sendToAll(T object){
		for(ClientThread i: all_connections){
			i.messageClient(object);
		}
	}


class ClientThread<T> extends Thread{
	Socket my_socket;
	ObjectInputStream input;
	ObjectOutputStream output;
	
	ClientThread (Socket passed_socket){
		my_socket = passed_socket;
		
		try{
			output = new ObjectOutputStream(my_socket.getOutputStream());
			input = new ObjectInputStream(my_socket.getInputStream());
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	
	
	public void run(){
		while(true){
			try{
			
			T broadcast = (T) input.readObject();
			Message msg = (Message) broadcast;
			
			sendToAll(broadcast);
			System.out.println(msg.getMessage());
			
			}
			catch(Exception e){
				System.err.println(e.getMessage());
				break;
			}
		}
	}
	
	public void messageClient(T object){
		try {
			output.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//safely get rid of all connections. no need to look over
	public void close(){
		try{
			if(output!=null){output.close();}
		}catch(Exception e){};
		try{
			if(input!=null){output.close();}
		}catch(Exception e){};
		try{
			if(my_socket!=null){output.close();}
		}catch(Exception e){};
		
	}
	
}

}


