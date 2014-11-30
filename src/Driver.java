import java.util.concurrent.locks.ReentrantLock;


public class Driver {

	public static void main(String args[])
	{
		ReentrantLock queryLock;
		queryLock = new ReentrantLock();
		/*AddHorseCommand horses[];
		horses = new AddHorseCommand[8];*/
		CreateAccountCommand accounts[];
		accounts = new CreateAccountCommand[3];
		/*Thread horseT[], raceT[];
		horseT = new Thread[8];*/
		Thread account[];
		account = new Thread[3];
		
		accounts[0] = new CreateAccountCommand(queryLock, "Akshay", "agg");
		account[0] = new Thread(accounts[0]);
		account[0].start();
		
		accounts[1] = new CreateAccountCommand(queryLock, "Akshay", "dog");
		account[1] = new Thread(accounts[1]);
		account[1].start();
		
		accounts[2] = new CreateAccountCommand(queryLock, "Akshays", "agg");
		account[2] = new Thread(accounts[2]);
		account[2].start();
		/*for(int i=0 ;i<8; i++)
		{
			races[i] = new CreateAccountCommand(queryLock, "");
			raceT[i] = new Thread(races[i]);
			raceT[i].start();	
		}*/
		
	}
}
	
