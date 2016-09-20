<%@ page language="java" import="cs5530.*" %>
<html>
 
<head>
        <link rel="stylesheet" type="text/css" href="main.css" />
 
    
 
</head>
 
<h1>Print User Stats</h1>
 
 
 
<body>
 
 
        <%
    
        String checkedat = request.getParameter("checked");
        
 
        if(checkedat == null){
 
        %>
 
        
        <form name="userstats" method=get onsubmit="return check_all_fields(this)" action="userstats.jsp">
 
 
                <br>Print the list of the Top n users who have checked out the most books.<br>
              
                <input type=hidden name="checked">
                <input type=text name="checkedValue" value="0" onFocus="value=''">
                <br><br>

                <br>Print the list of the Top n users who have rated the most books.<br>
              
                <input type=hidden name="rated">
                <input type=text name="ratedValue" value="0" onFocus="value=''">
                <br><br>

                
                <br>Print the list of the Top n users who have lost the most books.<br>
              
                <input type=hidden name="lost">
                <input type=text name="lostValue" value="0" onFocus="value=''">
                <br><br>

                

                <br><br>
                <input type=submit value="Print Stats">

        </form> 
<%
 
} else {

        String checkedVal = request.getParameter("checkedValue");
        String ratedVal = request.getParameter("ratedValue");
        String lostVal = request.getParameter("lostValue");
        
       
       
        cs5530.Connector connector = new Connector();
        cs5530.UserStats stats = new UserStats();    
 
        
       
 
        %>
       <%=stats.printCheckedOutMost(checkedVal, ratedVal, lostVal ,connector.con)%> <BR><BR>

<%

connector.closeConnection();
}
%>
 
<BR><a href="userstats.jsp"> Setup New User Stats Search </a></p>

<BR><a href="index.html"> Home </a></p>
 
</body>
</html>