import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class Client<T, P extends ClientProcessInterface<T>> extends Thread {
	//String hostName = "10.123.192.66";
	String hostName = "localhost";
	String username, team;
	P panel;
	int port;
    
    
    Client(int port, P panel){
        this.panel = panel;
        this.port = port;
            
        
    	}
    
    public void run(){
    	 try {
         	
         	//Server i/o connections
 			Socket connection = new Socket(hostName, port);
 			
 			
 			//Output and input streams to server
 			ObjectInputStream recievefromserver = new ObjectInputStream(connection.getInputStream());
 			ObjectOutputStream sendtoserver = new ObjectOutputStream(connection.getOutputStream());
 			
 			
 			
 			
 			//Make an input thread for monitoring listening stream the whole time
 			ListenFromServer all_recieving = new ListenFromServer(recievefromserver, panel);
 			all_recieving.start();
 			
 			
 			
 			//Loop to send messages whenver user chooses
 			while(true){
 				try{
 					
 					if(panel.hasOutputObject()){
 						sendtoserver.writeObject(panel.processOutputObject());
 						
 					}
 					else{
 						Thread.sleep(200);
 					}
 				}
 				catch(Exception e){
 					System.err.println(e.getMessage());
 					connection.close();
 				}
 			}
 			
 			
 			
 			
 		} catch (UnknownHostException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
    }
    
    
    


    
class ListenFromServer extends Thread{
	ObjectInputStream input;
	P panel;
	
	ListenFromServer(ObjectInputStream original, P panel){
		input = original;
		this.panel = panel;
	}
	
	
	
	public void run(){
		while(true){
			try{
			
			
			panel.processInputObject( (T) input.readObject());
			
			
			}
			catch(Exception e){
				System.err.println(e.getCause());
				break;
			}
		}
	}
	
	
}

}

