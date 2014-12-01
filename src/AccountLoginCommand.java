import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;

public class AccountLoginCommand extends SQLCommand {

	String userName, passWord;

	public AccountLoginCommand(ReentrantLock queryLock, String userName, String passWord) {
		super(queryLock);
		// TODO Auto-generated constructor stub

		this.userName = userName;
		this.passWord = passWord;
	}

	@Override
	public boolean execute() {
		String largestAID;
		int highAID = 0;

		try {
			Class.forName(DRIVER);

			Connection dbConn = DriverManager.getConnection(DB_ADDRESS+DB_NAME, USER, PASSWORD);
			Statement stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS lastUser FROM user_accounts");
			rs.next();
			largestAID = rs.getString("lastUser");
			System.out.println("Number of rows = " + largestAID);
			highAID = Integer.parseInt(largestAID);
			/*if (largestAID != null){ //If there is at least one user in the system
					highAID = Integer.parseInt(largestAID);
					highAID++;
				} else {
					highAID = 1;
					//highAID++;
				}*/
			System.out.println("Highest userID = " + highAID);

			rs = stmt.executeQuery("SELECT * FROM user_accounts AS currUser");
			for (int i = 0; i < highAID; i++){ //Check if the username already exists in the server
				rs.next();
				if (rs.getString(2).equals(userName)){
					if (rs.getString(3).equals(passWord)){
						System.out.println("Login successful");
						return true;
					} else{
						System.out.println("Login FAILED!");
						return false;
					}
				} 
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Login FAILED!");
		return false;
	}

	public static void main (String[] args){

	}

}



