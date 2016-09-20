<%@ page language="java" import="cs5530.*" %>
<html>
 
<head>
        <link rel="stylesheet" type="text/css" href="main.css" />
 
    
 
</head>
 
<h1>Print Book Stats</h1>
 
 
 
<body>
 
 
        <%
    
        String checkedat = request.getParameter("checked");
        
 
        if(checkedat == null){
 
        %>
 
        
        <form name="bookstats" method=get onsubmit="return check_all_fields(this)" action="bookstats.jsp">
 
 
                <br>Print The list of the n most checked out books.<br>
              
                <input type=hidden name="checked">
                <input type=text name="checkedValue" value="0" onFocus="value=''">
                <br><br>

                <br>Print The list of the n most requested books.<br>
              
                <input type=hidden name="requested">
                <input type=text name="requestedValue" value="0" onFocus="value=''">
                <br><br>

                
                <br>Print The list of the n most lost books.<br>
              
                <input type=hidden name="lost">
                <input type=text name="lostValue" value="0" onFocus="value=''">
                <br><br>

                <br>Print The list of the n most popular authors.<br>
              
                <input type=hidden name="authors">
                <input type=text name="authorsValue" value="0" onFocus="value=''">

                <br><br>
                <input type=submit value="Print Stats">

        </form> 
<%
 
} else {

        String checkedVal = request.getParameter("checkedValue");
        String requestedVal = request.getParameter("requestedValue");
        String lostVal = request.getParameter("lostValue");
        String authorsVal = request.getParameter("authorsValue");
       
       
        cs5530.Connector connector = new Connector();
        cs5530.BookStats stats = new BookStats();    
 
        
       
 
        %>
       <%=stats.printMostCheckedOutBooks(checkedVal, requestedVal, lostVal, authorsVal ,connector.con)%> <BR><BR>

<%

connector.closeConnection();
}
%>
 
<BR><a href="bookstats.jsp"> Setup New Stats Search </a></p>

<BR><a href="index.html"> Home </a></p>
 
</body>
</html>