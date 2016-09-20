package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

//
public class BookRecord{
	public void BookRecord() {}



public static String getAllBookInfo(String isbn, String includeLocations, String includeCheckouts, String includeScores, Connection con) {
		//first get all authors  test with 139649721-8
		int includeLocationsInt = Integer.parseInt(includeLocations);
		int includeCheckoutsInt = Integer.parseInt(includeCheckouts);
		int includeScoresInt = Integer.parseInt(includeScores);

		String result = null;
		String query_2 = "select * from Author where isbn = ? ;";
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
			result ="All Authors: ";
			while (rs2.next()) {
				
				result +="<br>Author Name: " + rs2.getString("Author");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query_3 = "SELECT * FROM BookInfo where isbn = ?";
		PreparedStatement query_3_statment = null;
		try {
			query_3_statment = con.prepareStatement(query_3);

			query_3_statment.setString(1, isbn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 rs2 = null;
		try {
			rs2 = query_3_statment.executeQuery();

			while (rs2.next()) {
				result += "<br>ISBN: " + isbn;
				result += "<br>Title: " + rs2.getString("title");
				result += "<br>Publisher: " + rs2.getString("publisher");
				result += "<br>Subject: " + rs2.getString("subject");
				result += "<br>Year Published: " + rs2.getString("yearPublished");
				result += "<br>format: " + rs2.getString("format");
				result += "<br>summary: " + rs2.getString("summary");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (includeLocationsInt==1){
			result += "<br><br>"+ copiesAndLocations(isbn, con);
			
		}

		if (includeCheckoutsInt==1){
			result += "<br><br>"+ getBookCheckOutRecord(isbn, con);
			
		}

		if (includeScoresInt==1){
			result += "<br><br>"+ getAvgScoreReviews(isbn, con);
			
		}


		return result;
	}


public static String copiesAndLocations(String isbn, Connection con) {
		
		String result2 = null;

		String query_2 = "SELECT * from Book where isbn LIKE ?";
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
			result2 = "All Copies and Locations of ISBN: "+isbn;
			while (rs2.next()) {
				result2+= "<br><br>Book Copy Info: ";
				result2+= "<br>Book ID Number: " + rs2.getString("bid");
				result2+= "<br>Location: " + rs2.getString("location");
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result2;
	}

	public static String getBookCheckOutRecord(String isbn, Connection con) {
		
		Book book = new Book();

		String[] individuals = book.getBookIds(isbn, con);

		
		String result3 = "All Users who have checked out and coorisponding check out dates";

		for (int i=0; i < individuals.length ; i++){
				
			
		
		String query_2 = "SELECT * from CheckoutRecord where bid = ? ;";
		PreparedStatement query_2_statment = null;
		try {
			query_2_statment = con.prepareStatement(query_2);

			query_2_statment.setString(1, individuals[i]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();
			//result3 = "All Users who have checked out and coorisponding check out dates of Book ID: "+ individuals[i];
			while (rs2.next()) {
				result3+= "<br><br>Check Out Info: ";
				result3+= "<br>User ID: " + rs2.getString("cardId");
				result3+= "<br>Check Out Date: " + rs2.getString("CheckOutDate");
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


		return result3;
	}

private static String getAvgScoreReviews(String isbn, Connection con ) {
		
		String result4 = null;
		//first get the average score
		String query_2 = "SELECT isbn, avg(score) as average FROM BookReview join Book on Book.bid=BookReview.bid  where isbn = ?";
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
			result4 ="The average user rating for: "+isbn;
			while (rs2.next()) {
				
				result4+= " is " + rs2.getString("average");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query_3 = "SELECT isbn, score, cardId, opinion FROM BookReview join Book on Book.bid=BookReview.bid  where isbn = ?";
		PreparedStatement query_3_statment = null;
		try {
			query_3_statment = con.prepareStatement(query_3);

			query_3_statment.setString(1, isbn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 rs2 = null;
		try {
			rs2 = query_3_statment.executeQuery();
			result4+= "<br>Individual Reviews and Scores for ISBN: " + isbn;
			while (rs2.next()) {
				
				result4+= "<br><br>User ID: " + rs2.getString("cardId");
				result4+= "<br>User's Score: " + rs2.getString("score");
				result4+= "<br>User Review: " + rs2.getString("opinion");
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result4;
	}

}