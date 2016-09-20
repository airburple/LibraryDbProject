package cs5530;
import java.util.ArrayList;
import java.sql.*;
//import javax.servlet.http.*;

public class UserRecord{
	public UserRecord() {
	}
	  public static String printUserRecord(String cardId, Connection con) {
		// TODO Auto-generated method stub

		String query_2 = "SELECT * FROM User where cardId LIKE ?";
		PreparedStatement query_2_statment = null;
		String result = null ;
		try {
			query_2_statment = con.prepareStatement(query_2);

			query_2_statment.setString(1, cardId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();

			while (rs2.next()) {
				result = "User Info: ";
				result +="<br>User Name: " + rs2.getString("userName");
				result +="<br>Card Id: " + rs2.getString("cardId");
				result +="<br>Full Name: " + rs2.getString("fullName");
				result +="<br>Address: " + rs2.getString("address");
				result +="<br>Phone Number: " + rs2.getString("phone");
				result +="<br>Email Address: " + rs2.getString("email");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int bid = -1;
		String isbn = "001" ;
		String allReviews = "";

		query_2 = "SELECT * FROM CheckoutRecord where cardId LIKE ?";
		query_2_statment = null;
		try {
			query_2_statment = con.prepareStatement(query_2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			query_2_statment.setString(1, "%" + cardId + "%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs2 = null;
		try {
			rs2 = query_2_statment.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String query_4 = "SELECT * FROM BookReview where cardId LIKE ?";
				PreparedStatement query_4_statment = null;
				
					query_4_statment = con.prepareStatement(query_4);
				

				
					query_4_statment.setString(1, cardId);
				
				
					
				
				ResultSet rs4 = null;
				
					rs4 = query_4_statment.executeQuery();
				

				
					while (rs4.next()) {

						allReviews += "<br>You gave the book number " +rs4.getString("bid")
								+ " a score of " + rs4.getString("score");
						allReviews += "<br>With Review Comments: "
								+ rs4.getString("opinion") + "<br>";

					}



			
			// Create new ArrayList.
			ArrayList<Integer> Books = new ArrayList<>();
			result +="<br><br>Checkout Record: ";
			while (rs2.next()) {













				// print book id
				result +="<br><br>Book Id: " + rs2.getString("bid");
				bid = rs2.getInt("bid");

				// prepare and add to book review list
				
			

				result +="<br>Check Out Date: "
						+ rs2.getString("checkOutDate");
				// System.out.print("\nLost Date: "+rs2.getString("lostDate"));
				int lost = rs2.getInt("lostInt");
				result +=lost;
				if (lost == (1)) {
					result += "<br>Book number" + bid
							+ " was reported lost.";
					Books.add(bid);
				} else {
					result +="<br>Check In Date: "
							+ rs2.getString("checkInDate");
				}
				String query_3 = "SELECT * FROM Book where bid LIKE ?";
				PreparedStatement query_3_statment = null;
				
					query_3_statment = con.prepareStatement(query_3);
			

				
					query_3_statment.setInt(1, bid);
				
				ResultSet rs3 = null;
				
					rs3 = query_3_statment.executeQuery();
				

				
					while (rs3.next()) {

						result +="<br>Book Title: "
								+ rs3.getString("title");
						result +="<br>Book ISBN: "
								+ rs3.getString("isbn");

					}

			}
			result +="<br><br>The following books were lost.";
			// Loop through Books.
			for (int i = 0; i < Books.size(); i++) {
				int value = Books.get(i);
				result +="<br>Book Id: " + value;
			}

				result +="<br><br>The following books are requested:";
				ArrayList<Integer> requests = new ArrayList<>();
				query_2 = "SELECT * FROM BookWaitList where cardId LIKE ?";
				query_2_statment = null;
			
				query_2_statment = con.prepareStatement(query_2);
			

			
				query_2_statment.setString(1, cardId);
			 
				rs2 = null;
			
				rs2 = query_2_statment.executeQuery();
		
				while (rs2.next()) {
					requests.add(+rs2.getInt("bid"));

				}
			

			// Loop through Books.
			for (int i = 0; i < requests.size(); i++) {
				int value = requests.get(i);
				result +="<br>Book Id: " + value;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result +="<br><br>Book Reviews:";
		result +=allReviews;
		return result;
	}

}
