package database.bookstore.entites;

public class Author {
	String Name;
	int ISBN;
	public Author(String name, int iSBN) {
		super();
		Name = name;
		ISBN = iSBN;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	
}
