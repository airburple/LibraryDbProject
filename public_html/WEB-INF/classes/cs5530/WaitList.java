package cs5530;
import java.util.ArrayList;
import java.sql.*;

public class WaitList{
	public void WaitList() {
	}

public String waitList(String cardId, String bid, Connection con) {
		
		int waiting = -1;
		String result = null;

		String query_3 = "INSERT INTO `BookWaitList` (`bid`, `cardId`)"
				+ " values (?, ? )";
		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = con.prepareStatement(query_3);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedStmt.setString(1, bid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			preparedStmt.setString(2, cardId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// execute the preparedstatement
			preparedStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		result = "<br>You have successfully reserved book id: " + bid;

		String query_CO = "SELECT Count(*) as waiting FROM BookWaitList where bid LIKE ? ";
		PreparedStatement query_CO_statment = null;
		try {
			query_CO_statment = con.prepareStatement(query_CO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			query_CO_statment.setString(1, bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = query_CO_statment.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs2.next()) {

				waiting = rs2.getInt("waiting");
				// cardId = rs2.getInt("cardId");
				result +="<br>The number of reservations ahead of you is: ";
				waiting= waiting-1;
				result += waiting;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		return result ;
	}
}