package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;

//
public class CheckIn{
	public void CheckIn() {}

public static String checkInBook(String cardId, String bid, String isLostString, Connection con) {
		
		String result = "Check In Status: <br>";
		 int isLost =Integer.parseInt(isLostString);
		

		

		if (isLost == 1) {
			String updateTableSql = "Update CheckoutRecord set checkInDate = CURRENT_TIMESTAMP where cardId LIKE ? and bid LIKE ? ;";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStatement;
			try {
				preparedStatement = con.prepareStatement(updateTableSql);

				preparedStatement.setString(1, cardId);
				preparedStatement.setString(2, bid);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			updateTableSql = "UPDATE Book SET available = ? WHERE bid = ? ;";
			// create the mysql insert preparedstatement
			// PreparedStatement preparedStatement;
			try {
				preparedStatement = con.prepareStatement(updateTableSql);

				preparedStatement.setInt(1, 1);
				preparedStatement.setString(2, bid);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			result += "<br>Book Id " + bid + " Has been retuned: ";

			String query_2 = "SELECT * FROM BookWaitList where bid LIKE ?";
			PreparedStatement query_2_statment = null;
			try {
				query_2_statment = con.prepareStatement(query_2);

				query_2_statment.setString(1, bid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResultSet rs2 = null;
			try {
				rs2 = query_2_statment.executeQuery();
				result+=" Card Ids of any people reserving this book are below: ";
				while (rs2.next()) {

					result+= "<br><br>Card Id: " + rs2.getString("cardId");
					result+="<br>Date reservation was made: "
							+ rs2.getString("dateAdded");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (isLost == 2) {
			String updateTableSql = "Update CheckoutRecord set lostDate = CURRENT_TIMESTAMP where cardId LIKE ? and bid LIKE ? ;";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStatement;
			try {
				preparedStatement = con.prepareStatement(updateTableSql);

				preparedStatement.setString(1, cardId);
				preparedStatement.setString(2, bid);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			updateTableSql = "Update CheckoutRecord set lostInt = ? where cardId = ? and bid = ? ;";
			;
			// create the mysql insert preparedstatement
			// PreparedStatement preparedStatement;
			try {
				preparedStatement = con.prepareStatement(updateTableSql);

				preparedStatement.setInt(1, 1);
				preparedStatement.setString(2, cardId);
				preparedStatement.setString(3, bid);
				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			result+= "<br>Book Id " + bid
					+ " Has been reported lost...oh boy!: ";
		}
		return result;
	}
}