package cs5530;
import java.util.ArrayList;
import java.sql.*;

public class CheckOut{
	public void Checkout() {}

	public  String checkout(String cardId, String bid, Connection con) {

		 
		int waiting = -1;
		String result = null;
		int available = -1;
		//top

		PreparedStatement preparedStatement2;


String query_CO9 = "SELECT * FROM Book where bid LIKE ? ";
		PreparedStatement query_CO9_statment = null;
		try {
			query_CO9_statment = con.prepareStatement(query_CO9);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			query_CO9_statment.setString(1, bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs9 = null;
		try {
			rs9 = query_CO9_statment.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs9.next()) {

				available = rs9.getInt("available");
				// cardId = rs2.getInt("cardId");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//start old
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
				result = "<br>The number of people reserving this book is: ";
				result += waiting;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (waiting > 0 ) {
			String oldestCardId = null;

			query_CO = "SELECT bid, cardId, MIN(dateAdded) FROM BookWaitList where bid LIKE ?";
			query_CO_statment = null;
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
			rs2 = null;
			try {
				rs2 = query_CO_statment.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				while (rs2.next()) {

					oldestCardId = rs2.getString("cardId");
					// cardId = rs2.getInt("cardId");
					result += "<br>The card Id with the oldest reservation is: ";
					result += oldestCardId;

					int oldestCardIdInt =Integer.parseInt(oldestCardId);
					int cardIdInt =Integer.parseInt(cardId);

					if (oldestCardIdInt == cardIdInt && available == 1) {
						result +="<br>Congrats the book is available and you hold the oldest reservation the book is due in 30 days: ";

						

						String query_3 = "INSERT INTO `CheckoutRecord` (`bid`, `cardId`)"
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

						String updateTableSql = "Update Book set available = 0 where bid LIKE ? ;";

						// create the mysql insert preparedstatement
						PreparedStatement preparedStatement;
						try {
							preparedStatement = con
									.prepareStatement(updateTableSql);

							preparedStatement.setString(1, bid);

							preparedStatement.executeUpdate();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}





						String updateTableSql2 = "Delete from BookWaitList where bid LIKE ? and cardId LIKE ? ;";

						// create the mysql insert preparedstatement
						
						try {
							preparedStatement2 = con
									.prepareStatement(updateTableSql2);

							preparedStatement2.setString(1, bid);

							preparedStatement2.setString(2, cardId);

							preparedStatement2.executeUpdate();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}






					} else {
						result +="<br>You may not check this book out at this time. ";
						return result;
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if(available == 1){
			result +="<br>Congrats the book is due in 30 days: ";

			String query_3 = "INSERT INTO `CheckoutRecord` (`bid`, `cardId`)"
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

			String updateTableSql = "Update Book set available = '0' where bid LIKE ? ;";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStatement;
			try {
				preparedStatement = con.prepareStatement(updateTableSql);

				preparedStatement.setString(1, bid);

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (available == 0){
			result += "Sorry the book is not available to check out.";
		}
		return result; 
	}
}