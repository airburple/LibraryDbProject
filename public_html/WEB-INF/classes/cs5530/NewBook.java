package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

//
public class NewBook{
	public void NewBook() {}
public static void newBookInfo(String isbn, String title, String authors, String publisher, String subject, String year, String format, String summary ,Connection con) {

		PreparedStatement preparedStmt =null;
		PreparedStatement preparedStmt2 =null;

		String query_3 = "INSERT INTO `cs5530db38`.`BookInfo` (`isbn`, `title`, `publisher`, `subject`, `format`, `yearPublished`, `summary`)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";

		try {
			// create the mysql insert preparedstatement
			preparedStmt = con.prepareStatement(query_3);
			preparedStmt.setString(1, isbn);
			preparedStmt.setString(2, title);
			preparedStmt.setString(3, publisher);
			preparedStmt.setString(4, subject);
			preparedStmt.setString(5, format);
			preparedStmt.setString(6, year);
			preparedStmt.setString(7, summary);

			// execute the preparedstatement
			preparedStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		String[] individuals = authors.split(",", -1);


		for (int i=0; i < individuals.length; i++){
				String query_4 = "INSERT INTO `cs5530db38`.`Author` (`isbn`, `author`)"
				+ " values (?, ?)";

		try {
			// create the mysql insert preparedstatement
			preparedStmt2 = con.prepareStatement(query_4);
			preparedStmt2.setString(1, isbn);
			preparedStmt2.setString(2, individuals[i]);
			

			// execute the preparedstatement
			preparedStmt2.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

			
		}


		

	}
}