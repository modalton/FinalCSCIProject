import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;

public class CreateAccountCommand extends SQLCommand {

	String userName, passWord;

	public CreateAccountCommand(ReentrantLock queryLock, String userName, String passWord) {
		super(queryLock);
		// TODO Auto-generated constructor stub

		this.userName = userName;
		this.passWord = passWord;
	}

	@Override
	public boolean execute() {
		String largestAID; //largestAccountID (largestAID)
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
				//System.out.println("The first string, which should be a username is = " + rs.getString(1) + ", rs.getString(2) = " + rs.getString(2));
				if (rs.getString(2).equals(userName)){
					System.out.println("Username already taken!");
					return false;
				} 
			}
			PreparedStatement ps = dbConn.prepareStatement("Insert Into user_accounts(user_name, user_pass) VALUES(?, ?)") ;
			ps.setString(1, userName);
			ps.setString(2, passWord); 
			ps.execute();
			System.out.println(userName + " added with password " + passWord);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static void main (String[] args){

	}

}
