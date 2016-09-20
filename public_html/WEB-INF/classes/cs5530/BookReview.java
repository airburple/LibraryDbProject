package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

//
public class BookReview{
	public void BookReview() {}

public static void reviewBook(String cardId, String isbn, String score, String review, Connection con) {
		

		String query_3 = "INSERT INTO `cs5530db38`.`BookReview` (`cardId`, `bid`, `score`, `opinion`)"
				+ " values (?, ?, ?, ?)";

		try {
			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query_3);
			preparedStmt.setString(1, cardId);
			preparedStmt.setString(2, isbn);
			preparedStmt.setString(3, score);
			preparedStmt.setString(4, review);

			// execute the preparedstatement
			preparedStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}