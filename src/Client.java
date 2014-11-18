import java.io.*;
import java.net.*;
import java.util.Scanner;
 
public class Client {
	String hostName = "localhost";
    
    
    
    
    
    
    public static void main(String[] args) throws IOException {
    	Client temp = new Client(4444);
    	
    }
    
    Client(int port){
        
        try {
        	
        	//Server i/o connections
			Socket connection = new Socket(hostName, port);
			
			
			//Output and input streams to server
			ObjectInputStream recievefromserver = new ObjectInputStream(connection.getInputStream());
			ObjectOutputStream sendtoserver = new ObjectOutputStream(connection.getOutputStream());
			
			
			
			
			//Thread for just listening the whole time
			ListenFromServer all_recieving = new ListenFromServer(recievefromserver);
			all_recieving.start();
			
			//Loop to send messages whenver user chooses
			Scanner rawinput = new Scanner(System.in);
			while(true){
				try{
					Message temp = new Message(rawinput.nextLine(),0);
					if(temp.equals(".")){break;}
					sendtoserver.writeObject(temp);
				}
				catch(Exception e){
					
				}
			}
			
			
			connection.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
    	}
    

}

class ListenFromServer extends Thread{
	ObjectInputStream passedvalue;
	
	ListenFromServer(ObjectInputStream original){
		passedvalue = original;
	}
	
	
	
	public void run(){
		while(true){
			try{
				
			Message msg = (Message) passedvalue.readObject();
			
			System.out.println(msg.getMessage());
			
			}
			catch(Exception e){
				System.err.println(e.getMessage());
				break;
			}
		}
	}
}


