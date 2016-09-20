package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

//
public class BookStats{
	public void BookStats() {}

public static String printMostCheckedOutBooks(String checkedOut, String requested, String lost, String authors, Connection con) {

		int checkedOutInt = Integer.parseInt(checkedOut);
		int requestedInt = Integer.parseInt(requested);
		int lostInt = Integer.parseInt(lost);
		int authorsInt = Integer.parseInt(authors);

		String result = "<h3>The "+checkedOut+" Most Checked Out Books</h3> ";

		

		int printCount = checkedOutInt;

		String query_2 = "SELECT  isbn, title, COUNT(CheckoutRecord.bid) AS checkoutcount FROM CheckoutRecord Join Book on CheckoutRecord.bid = Book.bid  GROUP BY isbn ORDER BY checkoutcount DESC LIMIT ? ";

		PreparedStatement query_2_statment = null;
		try {
			query_2_statment = con.prepareStatement(query_2);

			query_2_statment.setInt(1, printCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();
			int ranking = 0;
			while (rs2.next()) {
				ranking++;
				result +="Book Info: ";
				result+= "<br>Ranking: " + ranking;
				result+= "<br>ISBN: " + rs2.getString("isbn");
				result+= "<br>Title: " + rs2.getString("title");

				result+= "<br>Number of times " + rs2.getString("title")
						+ " has been checked out: "
						+ rs2.getString("checkoutcount")+"<br><br>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if ( checkedOutInt == 0){
			result = "<u></u>"; // basically resetting the result string to null.
			
		}

		if (requestedInt>0){
			result += "<br><br>"+ printMostRequestedBooks(requestedInt, con);
			
		}

		if (lostInt>0){
			result += "<br><br>"+ printMostLostBooks(lostInt, con);
			
		}

		if (authorsInt>0){
			result += "<br><br>"+ printMostPopularAuthors(authorsInt, con);
			
		}



		return result;
	}

	private static String printMostRequestedBooks(int requestedCount, Connection con ) {
		

		String result2 ="<h3>The "+requestedCount+" Most Requested Books</h3> ";

		int printCount = requestedCount;

		String query_2 = "SELECT  isbn, title, COUNT(BookWaitList.bid) AS waitlistcount FROM BookWaitList Join Book on BookWaitList.bid = Book.bid  GROUP BY isbn ORDER BY waitlistcount DESC LIMIT ? ";

		PreparedStatement query_2_statment = null;
		try {
			query_2_statment = con.prepareStatement(query_2);

			query_2_statment.setInt(1, printCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();
			int ranking = 0;
			while (rs2.next()) {
				ranking++;
				result2 += "Book Info: ";
				result2 +="<br>Ranking: " + ranking;
				result2 +="<br>ISBN: " + rs2.getString("isbn");
				result2 +="<br>Title: " + rs2.getString("title");

				result2 +="<br>Number of times " + rs2.getString("title")
						+ " has been reserved: "
						+ rs2.getString("waitlistcount")+"<br><br>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result2;
	}

	private static String printMostLostBooks(int lostCount, Connection con) {
		
		String result3 ="<h3>The "+lostCount+" Books Lost The Most</h3> ";

		int printCount = lostCount;

		String query_2 = "SELECT isbn, title, COUNT(CheckoutRecord.bid) AS lostcount FROM CheckoutRecord Join Book on CheckoutRecord.bid = Book.bid where lostInt = '1' GROUP BY Book.isbn ORDER BY lostcount DESC LIMIT ? ";

		PreparedStatement query_2_statment = null;
		try {
			query_2_statment = con.prepareStatement(query_2);

			query_2_statment.setInt(1, printCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();
			int ranking = 0;
			while (rs2.next()) {
				ranking++;
				result3+= "Book Info: ";
				result3+="<br>Ranking: " + ranking;
				result3+="<br>ISBN: " + rs2.getString("ISBN");
				result3+="<br>Title: " + rs2.getString("Title");

				result3+="<br>Number of times " + rs2.getString("title")
						+ " was lost: " + rs2.getString("lostcount")+"<br><br>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result3;
	}

private static String printMostPopularAuthors(int authorsCount, Connection con) {
		
		String result4 ="<h3>The "+authorsCount+" Most Popular Authors</h3> ";

		int printCount = authorsCount;

		String query_2 = "Select author ,count(BookWaitList.bid)as authorreserves from (Select author, Book.isbn, Book.bid, title from Book Join Author on Book.bid=Author.isbn)a join BookWaitList on a.bid=BookWaitList.bid group by author  ORDER BY authorreserves DESC LIMIT ?";

		PreparedStatement query_2_statment = null;
		try {
			query_2_statment = con.prepareStatement(query_2);

			query_2_statment.setInt(1, printCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();
			int ranking = 0;
			while (rs2.next()) {
				ranking++;
				result4+="Author Info: ";
				result4+="<br>Ranking: " + ranking;
				result4+="<br>Author Name: " + rs2.getString("author");

				result4+="<br>Number of books by "
						+ rs2.getString("author") + " currently reserved: "
						+ rs2.getString("authorreserves")+"<br><br>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result4;
	}

}