package database.bookstore.database;


import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import database.bookstore.entites.Publisher;
import database.bookstore.entites.User;

public class UserDatabase {

	 private final Database dataBase;

	    public UserDatabase() {
	        dataBase = Database.getInstance();
	    }

	    public boolean SignUp(User u) throws SQLException {
	    	 final String queryCheck = "INSERT INTO User(username,first_name,last_name,email,password,phone_number,shipping_address,is_manager) VALUES ('"
	    			 + u.getUser_name() + "','" + u.getFirst_name()+ "','" + u.getLast_name() + "','" 
	    			 + u.getEmail() + "','" + u.getPassword() + "','"+u.getPhone_number()+ "','"+u.getAddress()+"','0');";
	         return dataBase.getStatement().execute(queryCheck);
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
	    
	    public void PromoteUser(String email) throws SQLException {
	    	 dataBase.getStatement().execute("UPDATE User SET is_manager = '1' WHERE email = '"+email+"';");
	    }
	    
	    public void editUser(User u) throws SQLException {
	    	dataBase.getStatement().execute("UPDATE User SET username = '"+u.getUser_name()+"' , first_name = '"+u.getFirst_name()+
	    			"' , last_name ='"+u.getLast_name()+"' , password = '"+u.getPassword()+
	    			"' , phone_number ='"+u.getPhone_number()+"' , shipping_address = '"+u.getAddress()+
	    			"'  WHERE email = '"+u.getEmail()+"';");
	    }
	    
	    public User getUser(String email) throws SQLException {
	    	User user = new User();
	    	ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT * from User WHERE email = '" + email + "';");
	        resultSet.next();
	        user.setUser_name(resultSet.getString("username"));
	        user.setFirst_name(resultSet.getString("first_name"));
	        user.setLast_name(resultSet.getString("last_name"));
	        user.setPassword(resultSet.getString("password"));
	        user.setPhone_number(resultSet.getString("phone_number"));
	        user.setAddress(resultSet.getString("shipping_address"));
	        user.setEmail(email);
	    	return user;
	    }
	    
	    
	    public ArrayList<User> getUsers() throws SQLException{
	    	ArrayList<User> users = new ArrayList<User>();
	    	ResultSet resultSet = dataBase.getStatement().executeQuery("SELECT * from User;");
	        while(resultSet.next()) {
	        	User user = new User();
	        	user.setUser_name(resultSet.getString("username"));
		        user.setFirst_name(resultSet.getString("first_name"));
		        user.setLast_name(resultSet.getString("last_name"));
		        user.setPassword(resultSet.getString("password"));
		        user.setPhone_number(resultSet.getString("phone_number"));
		        user.setAddress(resultSet.getString("shipping_address"));
		        user.setEmail(resultSet.getString("email"));
		        if(resultSet.getString("is_manager").contains("1")) {
		        	user.setIs_manager(true);
		        }else {
		        	user.setIs_manager(false);
		        }
	        }
	    	return users;
	    }
	    
	    public void addPublisher(Publisher p) throws SQLException {
	         dataBase.getStatement().execute("INSERT INTO Publisher(publisher_name) VALUES ('"+p.getName()+"')");
	         dataBase.getStatement().execute("INSERT INTO PublisherPhone(publisher_name,publisher_phone) VALUES ('"+p.getName()+"','"+p.getPhone()+"')");
	         dataBase.getStatement().execute("INSERT INTO publisherAddress(publisher_name,publisher_address) VALUES ('"+p.getName()+"','"+p.getAddress()+"')");
	    }
	    
	    public void addAuthor(String author) throws SQLException {
	    	dataBase.getStatement().execute("INSERT INTO authorname VALUES ('"+author+"');");
	    }
}
