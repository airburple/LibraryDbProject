package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

//
public class AddCopies{
	public void AddCopies() {}

public static String addCopies(String isbn, String copiesString, Connection con) {

		int copies = Integer.parseInt(copiesString);
		
		String result = null;
		String title = null;
		String publisher = null;
		String subject = null;
		String yearPublished = null;
		String format = null;
		String summary = null;

		String query_2 = "SELECT * from BookInfo where isbn LIKE ?";
		PreparedStatement query_2_statment = null;
		try {
			query_2_statment = con.prepareStatement(query_2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			query_2_statment.setString(1, isbn);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			while (rs2.next()) {
				result = "That book's title should be: ";
				result+= rs2.getString("title");

				title = rs2.getString("title");
				publisher = rs2.getString("publisher");
				subject = rs2.getString("subject");
				yearPublished = rs2.getString("yearPublished");
				format = rs2.getString("format");
				summary = rs2.getString("summary");
				// System.out.print(publisher+subject+yearPublished+format+summary);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		
		result+= "<br>Added: " + copies + " copies of " + title;

		for (int i = 0; i < copies; i++) {

			String query_3 = "INSERT INTO `cs5530db38`.`Book` (`isbn`, `title`, `publisher`, `subject`, `format`, `yearPublished`, `summary`, `location`, `available`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt = con.prepareStatement(query_3);
				preparedStmt.setString(1, isbn);
				preparedStmt.setString(2, title);
				preparedStmt.setString(3, publisher);
				preparedStmt.setString(4, subject);
				preparedStmt.setString(5, format);
				preparedStmt.setString(6, yearPublished);
				preparedStmt.setString(7, summary);
				preparedStmt.setString(8, "Library");
				preparedStmt.setInt(9, 1);

				// execute the preparedstatement
				preparedStmt.execute();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		result += "<br>All book Ids for ISBN "+isbn+": <br>";
		String query_8 = "SELECT * from Book where isbn LIKE ?";
		PreparedStatement query_8_statment = null;
		try {
			query_8_statment = con.prepareStatement(query_8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			query_8_statment.setString(1, isbn);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ResultSet rs8 = null;
		try {
			rs8 = query_8_statment.executeQuery();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			while (rs8.next()) {
				
				result+= rs8.getString("bid")+"<br>";

				
				// System.out.print(publisher+subject+yearPublished+format+summary);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}






		return result;
	}
}