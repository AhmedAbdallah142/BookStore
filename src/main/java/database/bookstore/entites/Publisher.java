package database.bookstore.entites;

public class Publisher {
	String Name;
	String Address;
	String Phone;
	
	public Publisher(String name, String address, String phone) {
		super();
		Name = name;
		Address = address;
		Phone = phone;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
}
