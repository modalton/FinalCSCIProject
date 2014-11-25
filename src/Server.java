
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;

public abstract class Server<T> {
	ArrayList<ClientThread<T>> all_connections;
	List<ClientThread> team1 = Collections.synchronizedList(new ArrayList<ClientThread>());
	List<ClientThread> team2 = Collections.synchronizedList(new ArrayList<ClientThread>());
	
	Integer total_players;
    
    
    
    public Server(int port, ArrayList<ClientThread<T>> ac){
    	all_connections = ac;
    	total_players = new Integer(ac.size());
    	//Establishing server socket scope
        ServerSocket ss = null;
        try {
        	System.out.println("me");
        	//make server socket based on port number passed in
            ss = new ServerSocket(port);
            
            //lets a certain amount of players connect and starts thier connections thread
            while(amount_of_players-- != 0){
            	Socket player_connect = ss.accept();
            	System.out.println("connection made" );
            	ClientThread t = new ClientThread(player_connect, amount_of_players);
            	all_connections.add(t);
            	t.start();
            	
            }
            
            
            
        } catch (IOException e) {
            System.err.println("Could not listen on port");
            System.exit(1);
        }

        
        while(true){
        	
        	
        }
        
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
	
	
	
	public ClientThread<T> getClientByUsername(String name){
		for(ClientThread<T> ct : all_connections){
			if(name.equals(ct.username)){
				return ct;
			}
		}
		return null;
	}
	
	public abstract <T> void doServerAction(T object);


class ClientThread<T> extends Thread{
	Socket my_socket;
	String username;
	Integer instance_number;
	ObjectInputStream input;
	ObjectOutputStream output;
	
	ClientThread (Socket passed_socket, int numberyouare){
		my_socket = passed_socket;
		instance_number = new Integer(numberyouare);
		
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
				
				
				doServerAction((T) input.readObject());
				
				
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


