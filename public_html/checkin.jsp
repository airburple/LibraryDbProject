<%@ page language="java" import="cs5530.*" %>
<html>
 
<head>
        <link rel="stylesheet" type="text/css" href="main.css" />
 
        <script LANGUAGE="javascript">
 
        function check_all_fields(form_obj){
                alert(form_obj.searchAttribute.value+"='"+form_obj.attributeValue.value+"'");
                if( form_obj.attributeValue.value == ""){
                        alert("No empty fields!");
                        return false;
                }
                return true;
        }
 
 
        </script>
 
</head>
 
<h1>Check In a Book</h1>
 
 
 
<body>
 
 
        <%
 
        
        String idat = request.getParameter("cardid");
       
        String bookat = request.getParameter("bid");
        
 
        if(idat == null){
 
        %>
 
        Please enter the required information:<br>
        <form name="CheckInBook" method=get onsubmit="return check_all_fields(this)" action="checkin.jsp">
 
                
 
                <br>Card Id<br>
                <input type=hidden name="cardid">
                <input type=text name="idValue" value="272727" >
 
                <br>Book Id<br>
              
                <input type=hidden name="bid">
                <input type=text name="bidValue" value="24" >

                <br>Are You Reporting This Book Lost?<br>
              
                <input type=hidden name="isLost">
                <input type="radio" name="isLostValue" value= "1" >No<br>
                <input type="radio" name="isLostValue" value= "2" >Yes


                <br><br>
                <input type=submit value="Check In">

        </form> 
<%
 
} else {

        String idVal = request.getParameter("idValue");
        String bidVal = request.getParameter("bidValue");
        String isLostVal = request.getParameter("isLostValue");
       
        cs5530.Connector connector = new Connector();
	   cs5530.CheckIn ci = new CheckIn();	 
 
        
       
 
        %>
       <%=ci.checkInBook(idVal, bidVal, isLostVal ,connector.con)%> <BR><BR>
<!-- String cardId, String bid, String isLost, Connection con -->
<%

connector.closeConnection();
}
%>
 
<BR><a href="checkin.jsp"> Check In Another Book </a></p>

<BR><a href="index.html"> Home </a></p>
 
</body>
</html>