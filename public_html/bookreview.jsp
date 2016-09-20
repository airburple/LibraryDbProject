<%@ page language="java" import="cs5530.*" %>
<html>
 
<head>
        <link rel="stylesheet" type="text/css" href="main.css" />
 
        
 
</head>
 
<h1>Add New Book Review</h1>
 
 
 
<body>
 
 
        <%
 
        String isbnat = request.getParameter("isbn");
        
        if(isbnat == null){
 
        %>
 
        Please Enter Book Review Info:<br>

       
                
                
                


        <form name="bookreview" method=get  action="bookreview.jsp">
 
                <br>Book ID<br>
                <input type=hidden name="isbn">
                <input type=text name="isbnValue" value="028413525-3" >
 
                <br>Card Id<br>
                <input type=hidden name="cardId">
                <input type=text name="cardIdValue" value="80" >
 
 
                <br>Score 1-10 Worst is 1 Best is 10<br>
                
                <input type=hidden name="score">
                <input type=text name="scoreValue" value="10" >

                <br>Optional Review<br>
                <input type=hidden name="review">
                <input type=text name="reviewValue" value="this book was ok" >
 
                <br>
                <input type=submit value="Submit Review">

                <br>
 
        </form>
         
         
<%
 
} else {


        String isbnValue = request.getParameter("isbnValue");
        String cardIdValue = request.getParameter("cardIdValue");
        String scoreValue = request.getParameter("scoreValue");
        String reviewValue = request.getParameter("reviewValue");
        
 
        
 
        cs5530.Connector connector = new Connector();
	cs5530.BookReview review = new BookReview();
 
%>
<h1> Review Added! </h1>
 <!-- (String cardId, String isbn, String score, String review, Connection con) -->

        <%
 
        review.reviewBook(cardIdValue, isbnValue , scoreValue, reviewValue, connector.con);
        out.println("ISBN: " + isbnValue + "<br>");
        
        %>
       

<%

connector.closeConnection();
}
%>
 
<BR><a href="bookreview.jsp"> Review Another Book </a></p>
<BR><a href="index.html"> Home </a></p>
 
</body>
</html>