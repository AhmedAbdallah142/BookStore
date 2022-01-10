package database.bookstore.database;

import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		UserDatabase u = new UserDatabase();
		System.out.println(u.getUsers(1).size());
	}

}
