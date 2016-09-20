package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;

public class Book{
	public Book() {
	}
	public String[] getBookIds(String isbn, Connection con) {

		String[] result = new String[2] ;
		int i =0;

		String query_2 = "SELECT * from Book where isbn LIKE ? ";

		PreparedStatement query_2_statment = null;
		try {
			query_2_statment = con.prepareStatement(query_2);

			query_2_statment.setString(1, isbn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();
			
			while (rs2.next()) {
				
				result[i] = rs2.getString("bid");
				
				i++;
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		


		return result;

	}
	

}