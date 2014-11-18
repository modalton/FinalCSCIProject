
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server<T> {
	ArrayList<ClientThread> all_connections;
	ArrayList<ClientThread> team1;
	ArrayList<ClientThread> team2;
    public static void main(String[] args) throws IOException {
    	Server<Message> temp = new Server<Message>(4444);
    }
    
    
    public Server(int port){
    	all_connections = new ArrayList<ClientThread>();
    	
    	//Esablishing server socket scope
        ServerSocket ss = null;
        try {
        	
        	//make server socket based on port number passed in
            ss = new ServerSocket(port);
            
            //lets a certain amount of players connect
            int player_size = 5;
            while(player_size-- != 0){
            	System.out.println("connection " + Integer.toString(4-player_size));
            	Socket player_connect = ss.accept();
            	ClientThread t = new ClientThread(player_connect);
            	all_connections.add(t);
            	t.run();
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
			
			Message msg = (Message) input.readObject();
			System.out.println(msg.getMessage());
			
			}
			catch(Exception e){
				System.err.println(e.getMessage());
				break;
			}
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



