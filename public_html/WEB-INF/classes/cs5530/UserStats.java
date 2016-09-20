package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

//
public class UserStats{
	public void UserStats() {}

public static String printCheckedOutMost(String checkedOut, String rated, String lost, Connection con) {
		
		int checkedOutInt = Integer.parseInt(checkedOut);
		int ratedInt = Integer.parseInt(rated);
		int lostInt = Integer.parseInt(lost);

		int printCount = checkedOutInt;

		String result = "<h3>The Top "+checkedOut+" Users Who Have Checked Out The Most Books</h3> ";

		String query_2 = "SELECT User.cardId, fullName, COUNT(User.cardId) AS checkoutcount FROM CheckoutRecord Join User on CheckoutRecord.cardId = User.cardId GROUP BY User.cardId ORDER BY checkoutcount DESC LIMIT ? ";

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
				result+="User Info: ";
				result+="<br>Ranking: " + ranking;
				result+="<br>Card Id: " + rs2.getString("cardId");
				result+="<br>Full Name: " + rs2.getString("fullName");

				result+="<br>Number of Books Ever Checked Out by "
						+ rs2.getString("fullName") + " : "
						+ rs2.getString("checkoutcount")+"<br><br>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if ( checkedOutInt == 0){
			result = "<u></u>"; // basically resetting the result string to null.
			
		}

		if (ratedInt>0){
			result += "<br><br>"+ printRatedMost(ratedInt, con);
			
		}

		if (lostInt>0){
			result += "<br><br>"+ printLostMost(lostInt, con);
			
		}

		return result;
	}


private static String printRatedMost(int ratedCount, Connection con) {

		String result2 ="<h3>Top "+ratedCount+" Users Who Have Rated The Most Books</h3> ";

		int printCount = ratedCount;

		String query_2 = "SELECT User.cardId, fullName, COUNT(User.cardId) AS reviewcount FROM BookReview Join User on BookReview.cardId = User.cardId GROUP BY User.cardId ORDER BY reviewcount DESC LIMIT ?";

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
				result2+="User Info: ";
				result2+="<br>Ranking:" + ranking;

				result2+="<br>Card Id: " + rs2.getString("cardId");
				result2+="<br>Full Name: " + rs2.getString("fullName");

				result2+="<br>Number of Reviws Given by "
						+ rs2.getString("fullName") + " : "
						+ rs2.getString("reviewcount")+"<br><br>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result2;
	}


private static String printLostMost(int lostCount, Connection con) {
		String result3 ="<h3>The Top "+lostCount+" Users Who Have Lost The Most Books</h3> ";

		int printCount = lostCount;

		String query_2 = "SELECT User.cardId, fullName, COUNT(User.cardId) AS lostcount FROM CheckoutRecord Join User on CheckoutRecord.cardId = User.cardId where lostInt = '1' GROUP BY User.cardId ORDER BY lostcount DESC LIMIT ? ";

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
				result3+="User Info: ";
				result3+="<br>Ranking: " + ranking;
				result3+="<br>Card Id: " + rs2.getString("cardId");
				result3+="<br>Full Name: " + rs2.getString("fullName");

				result3+="<br>Number of Books lost by "
						+ rs2.getString("fullName") + " : "
						+ rs2.getString("lostcount")+"<br><br>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result3;
	}


}