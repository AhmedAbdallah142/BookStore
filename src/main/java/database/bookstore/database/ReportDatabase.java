package database.bookstore.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.bookstore.entites.ReportSaleItem;

public class ReportDatabase {

private final Database dataBase;
	
	public ReportDatabase() {
        dataBase = Database.getInstance();
    }
	
	public ArrayList<ReportSaleItem> Total_Sales_For_Books(int page) throws SQLException{
		ArrayList<ReportSaleItem> report = new ArrayList<ReportSaleItem>();
		ResultSet resultSet = dataBase.getStatement().executeQuery("Select b.ISBN , b.Title , sum(s.copies) as Total_Saled_Copies\r\n" + 
				"From Book AS b , Sale as s\n" + 
				"where b.ISBN = s.ISBN \n" + 
				"AND s.date >= DATE_ADD(NOW(),INTERVAL-30 DAY) \n" + 
				"Group by b.ISBN\r\n" + 
				"Order by Total_Saled_Copies desc LIMIT 50 OFFSET " +(page-1)*50 + " ;");
		while(resultSet.next()) {
			ReportSaleItem r = new ReportSaleItem();
			r.setISBN(Integer.parseInt(resultSet.getString("ISBN")));
			r.setTitle(resultSet.getString("Title"));
			r.setQuantity(Integer.parseInt(resultSet.getString("Total_Saled_Copies")));
			report.add(r);
		}
		
		return report;
	}
}
