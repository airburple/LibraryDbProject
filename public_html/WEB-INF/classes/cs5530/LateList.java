package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
//import java.time.LocalDateTime;
//
public class LateList{
	public void LateList() {}

public static String printLateList(Connection con) {
		// SELECT * FROM cs5530db38.CheckoutRecord where checkInDate= '0' and
		// dueDate < now() - INTERVAL 30 DAY ;
		//LocalDateTime now;
		String result= "<br>As of today's date the books that are overdue are: ";

		String query_CO = "SELECT * FROM cs5530db38.CheckoutRecord where checkInDate= '0' and dueDate < now() ";
		PreparedStatement query_CO_statment = null;
		try {
			query_CO_statment = con.prepareStatement(query_CO);
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

				int bid = rs2.getInt("bid");
				String dueDate = rs2.getString("dueDate");

				int cardId = rs2.getInt("cardId");
				result += "<br><br>Book Id: " + bid
						+ " the Due Date was: " + dueDate;

				String query_3 = "SELECT * FROM User where cardId LIKE ?";
				PreparedStatement query_3_statment = null;
				try {
					query_3_statment = con.prepareStatement(query_3);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					query_3_statment.setInt(1, cardId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ResultSet rs3 = null;
				try {
					rs3 = query_3_statment.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					while (rs3.next()) {
						result += "User Info: ";

						result +="<br>User's Name: " + rs3.getString("fullName");

						result += "<br>User's Phone Number: "
								+ rs3.getString("phone");
						result += "<br>User's Email Address: "
								+ rs3.getString("email");

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}