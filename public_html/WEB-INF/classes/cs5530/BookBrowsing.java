package cs5530;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;

//
public class BookBrowsing{
	public void BookBrowsing() {}

public static String browseBooks(String author, String publisher, String titleWords, String subject, String conjunction, String sortedBy, String available, Connection con){
		

		
		String result ="Results: ";
		ResultSet rs2 = null;
		String none = "None";

		boolean authorIncluded= false;
		boolean publisherIncluded= false;
		boolean titleWordsIncluded= false;
		boolean subjectIncluded= false;

		String firstVal = null;
		String secondVal = "nope";
		String thirdVal = "nope";
		String fourthVal = "nope";

		String[] isbns;
		int indexItem = 0 ;

		String tempIsbn = null;

		boolean contains = false;

		PreparedStatement query_3_statment = null;

		String query_3 = "SELECT * FROM  Author a  Join Book i on a.isbn =i.isbn where ";


		if(available.equals("available")){
			query_3+="available > 0 and ";
		}



		// AUTHOR CASES: always first val when included

		if(!none.equals(author)){ 
			authorIncluded = true;

			query_3 += " author LIKE ?" ;

			firstVal = author;
		}




		// PUBLISHER CASES always first or second val when included
		if(!none.equals(publisher)){
			publisherIncluded = true;

			if(!none.equals(conjunction)){
				query_3 += conjunction;

			} 

			query_3 += " publisher LIKE ? "; //



			if (!authorIncluded){
				firstVal = publisher;
			}else secondVal = publisher ;


		}


		// TITLE WORDS CASES always first second or third val when included

		if(!none.equals(titleWords)){
			titleWordsIncluded = true;

			if(!none.equals(conjunction)){
				query_3 += conjunction;
				
			} 


			query_3 +=  " title LIKE ? ";


			if (none.equals(conjunction)){
				firstVal = titleWords ;
			}

			if(authorIncluded && !publisherIncluded){
				secondVal = titleWords;
			}



			if(authorIncluded && publisherIncluded){
				thirdVal = titleWords;
			}

		}




		// SUBJECT CASES could be first second third or fourth val

		if(!none.equals(subject)){
			subjectIncluded = true;
			if(!none.equals(conjunction)){
				query_3 += conjunction;
				
			} 

			query_3 +=  " subject LIKE ? ";



			if (none.equals(conjunction)){
				firstVal = subject ;
			}


			// 3 cases where it could be second val
			if(authorIncluded && (!publisherIncluded && !titleWordsIncluded)){  
				secondVal = subject;
			}

			if(titleWordsIncluded && (!publisherIncluded && !authorIncluded)){  
				secondVal = subject;
			}


			if(publisherIncluded && (!authorIncluded && !titleWordsIncluded)){  
				secondVal = subject;
			}

			// 3 cases where it could be 3rd

			if(!authorIncluded && (publisherIncluded && titleWordsIncluded)){  
				thirdVal = subject;
			}

			if(!titleWordsIncluded && (publisherIncluded && authorIncluded)){  
				thirdVal = subject;
			}


			if(!publisherIncluded && (authorIncluded && titleWordsIncluded)){  
				thirdVal = subject;
			}

			// 1 case where subject is fourth value
			if(publisherIncluded && authorIncluded && titleWordsIncluded){  
				fourthVal = subject;
			}
			



		}

//String query_3 = "SELECT * FROM  Author a, Book i WHERE i.isbn = a.isbn AND author LIKE ? group by i.isbn" ;d
		if ( sortedBy.equals("year")){
				query_3+= "ORDER BY yearPublished DESC";
			}


			

		try {
			//query_3 += " group by a.author" ; f
			query_3_statment = con.prepareStatement(query_3);
			query_3_statment.setString(1, firstVal);

			if(!secondVal.equals("nope")){
				query_3_statment.setString(2, secondVal);
			} 

			if(!thirdVal.equals("nope")){
				query_3_statment.setString(3, thirdVal);
			} 

			if(!fourthVal.equals("nope")){
				query_3_statment.setString(4, fourthVal);
			} 


			//query_3_statment.setString(1, author);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			rs2 = query_3_statment.executeQuery();

			while (rs2.next()) {

				

				result += "<br>ISBN: " + rs2.getString("isbn");
				result += "<br>Title: " + rs2.getString("title");
				result += "<br>Author: " + rs2.getString("author");
				result += "<br>Publisher: " + rs2.getString("publisher");
				result += "<br>Subject: " + rs2.getString("subject");
				result += "<br>Year Published: " + rs2.getString("yearPublished");
				result += "<br>format: " + rs2.getString("format");
				result += "<br>available: " + rs2.getString("available");
				result += "<br>summary: " + rs2.getString("summary")+"<br><br>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			

			
			
		
		




		return result;
}
	

}


