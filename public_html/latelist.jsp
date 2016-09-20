<%@ page language="java" import="cs5530.*" %>
<html>
 
<head>
        <link rel="stylesheet" type="text/css" href="main.css" />
 
        
 
</head>
 
<h1>Late List</h1>
 
 
 
<body>
 
 
        
 
        
<%
 


        
       
        cs5530.Connector connector = new Connector();
	    cs5530.LateList late = new LateList();	 
 
        
       
 
        %>
       <%=late.printLateList(connector.con)%> <BR><BR>
<!-- Close connection  -->
<%

connector.closeConnection();

%>
 
<BR><a href="latelist.jsp"> Refresh Late List</a></p>
<BR><a href="index.html"> Home </a></p>
 
</body>
</html>