package database.bookstore.entites;

public class User {
//	New customers are able to sign up for a new customer account by providing the necessary information:
//		user name, password, last name, first name, e-mail address, phone number, and shipping address.

	String user_name;
	String password;
	String first_name;
	String last_name;
	String email;
	String phone_number;
	String address;
	boolean is_manager;
	
	public User() {
		super();
	}
	public User(String user_name, String password, String first_name, String last_name, String email,
			String phone_number, String address) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.address = address;
		this.is_manager = false;
	}
	public boolean isIs_manager() {
		return is_manager;
	}
	public void setIs_manager(boolean is_manager) {
		this.is_manager = is_manager;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
