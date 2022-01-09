package database.bookstore.database;


import java.sql.SQLException;
import java.sql.ResultSet;
import database.bookstore.entites.User;

public class UserDatabase {

	 private final Database dataBase;

	    public UserDatabase() {
	        dataBase = Database.getInstance();
	    }

	    public void SignUp(User u) throws SQLException {
	    	// need to add user_name
	    	 final String queryCheck = "INSERT INTO User(first_name,last_name,email,password,is_manager) VALUES ('" + u.getFirst_name()
	                 + "','" + u.getLast_name() + "','" + u.getEmail() + "','" + u.getPassword() + "','0');";
	         dataBase.getStatement().execute(queryCheck);
	    }
	    
	    public boolean LogIn(String email,String password) throws SQLException {
	    	ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT first_name from User WHERE email = '" + email + "' AND password = '" + password + "'");
	        return resultSet.next();
	    }
	    
	    public boolean IsManger(String email) throws SQLException {
	    	ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT is_manager from User WHERE email = '" + email + "';");
	        resultSet.next();
	        if(resultSet.getString("is_manager").equalsIgnoreCase("1")) {
	        	return true;
	        }
	    	return false;
	    }
	    
	    public void Promote_User(String email) throws SQLException {
	    	 dataBase.getStatement().execute("UPDATE User SET is_manager = '1' WHERE email = '"+email+"';");
	    }
}
