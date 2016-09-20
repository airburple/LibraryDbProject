package cs5530;

import java.sql.*;
//import javax.servlet.http.*; dd

public class Register {
	public Register() {}
	public static void addUser(String username, String idnumber, String fullname, String address, String phonenumber, String email, Connection con) throws Exception
    {
        String resultStr = "Success"; // 
        String sql;
        ResultSet results;
        PreparedStatement st;
        //query to add user to database
        sql = "INSERT INTO User (userName, cardId, fullName, email, address, phone) VALUES (?, ?, ?, ?, ?, ?)";
 
 
        try {
        //set sql parameters
            st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, idnumber);
            st.setString(3, fullname);
            st.setString(4, email);
            st.setString(5, address);
            st.setString(6, phonenumber);
            st.executeUpdate();
        } catch (Exception e) {
 
        }
 
 
    }
}
	