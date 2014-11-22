
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server<T> {
	ArrayList<ClientThread> all_connections;
	ArrayList<ClientThread> team1;
	ArrayList<ClientThread> team2;
    public static void main(String[] args) throws IOException {
    	Server<Message> temp = new Server<Message>(4444, 4);
    }
    
    
    public Server(int port, int amount_of_players){
    	all_connections = new ArrayList<ClientThread>();
    	
    	//Establishing server socket scope
        ServerSocket ss = null;
        try {
        	
        	//make server socket based on port number passed in
            ss = new ServerSocket(port);
            
            //lets a certain amount of players connect and starts thier connections thread
            while(amount_of_players-- != 0){
            	Socket player_connect = ss.accept();
            	System.out.println("connection made" );
            	ClientThread t = new ClientThread(player_connect);
            	all_connections.add(t);
            	t.start();
            	
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
    
    //Self explanitory functions
    public <T> void sendToTeamOne(T object){
    	for(ClientThread i: team1){
			i.messageClient(object);
		}
    }
    
    public <T> void sendToTeamTwo(T object){
    	for(ClientThread i: team2){
			i.messageClient(object);
		}
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
		
		//Store streams from socket passed to us
		try{
			output = new ObjectOutputStream(my_socket.getOutputStream());
			input = new ObjectInputStream(my_socket.getInputStream());
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	
	
	public void run(){
		while(true){
			try{
				
				T msg = (T) input.readObject();
				sendToAll(msg);
				
				
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
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//safely get rid of all connections. no need to look over
	public void closeSafely(){
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


