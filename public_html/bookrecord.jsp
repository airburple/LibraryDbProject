<%@ page language="java" import="cs5530.*" %>
<html>
 
<head>
        <link rel="stylesheet" type="text/css" href="main.css" />
 
    
 
</head>
 
<h1>Print A Book Record</h1>
 
 
 
<body>
 
 
        <%
 
        
        
       
        String isbnat = request.getParameter("isbn");
        
 
        if(isbnat == null){
 
        %>
 
        Please enter the required information:<br>
        <form name="CheckInBook" method=get onsubmit="return check_all_fields(this)" action="bookrecord.jsp">
 
                
 
                
 
                <br>ISBN<br>
              
                <input type=hidden name="isbn">
                <input type=text name="isbnValue" value="107" onFocus="value=''">

                <br>Include Copies and Locations?<br>
              
                <input type=hidden name="copies">
                <input type="radio" name="copiesValue" value= "1" >Yes<br>
                <input type="radio" name="copiesValue" value= "2" checked="checked" >No<br>
                <br><br>

                <br>Include Checkout Records?<br>
              
                <input type=hidden name="checkouts">
                <input type="radio" name="checkoutsValue" value= "1" >Yes<br>
                <input type="radio" name="checkoutsValue" value= "2" checked="checked" >No<br>
                <br><br>

                <br>Include Review Scores?<br>
              
                <input type=hidden name="aveScore">
                <input type="radio" name="aveScoreValue" value= "1" >Yes<br>
                <input type="radio" name="aveScoreValue" value= "2" checked="checked" >No<br>

                


                <br><br>
                <input type=submit value="Print Record">

        </form> 
<%
 
} else {

        String isbnVal = request.getParameter("isbnValue");
       
        String copiesVal = request.getParameter("copiesValue");
        String checkoutsVal = request.getParameter("checkoutsValue");
        String aveScoreVal = request.getParameter("aveScoreValue");
       
       
        cs5530.Connector connector = new Connector();
        cs5530.BookRecord record = new BookRecord();    
 
        
       
 
        %>
       <%=record.getAllBookInfo(isbnVal, copiesVal, checkoutsVal, aveScoreVal ,connector.con)%> <BR><BR>

<%

connector.closeConnection();
}
%>
 
<BR><a href="bookrecord.jsp"> Check In Another Book </a></p>

<BR><a href="index.html"> Home </a></p>
 
</body>
</html>