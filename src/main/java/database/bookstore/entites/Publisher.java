package database.bookstore.entites;

public class Publisher {
	int Id;
	String Name;
	String Address;
	String Phone;
	
	public Publisher(int id, String name, String address, String phone) {
		super();
		Id = id;
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
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	

}
