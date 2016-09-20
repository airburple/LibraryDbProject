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
 
<h1>Check Out a Book</h1>
 
 
 
<body>
 
 
        <%
 
        
        String idat = request.getParameter("cardid");
       
        String bookat = request.getParameter("bid");
        
 
        if(idat == null){
 
        %>
 
        Please enter the required information:<br>
        <form name="CheckOutBook" method=get onsubmit="return check_all_fields(this)" action="checkout.jsp">
 
                
 
                <br>Card Id<br>
                <input type=hidden name="cardid">
                <input type=text name="idValue" value="272727" >
 
                <br>Book Id<br>
                <!-- <input type="text" name="address"> -->
                <input type=hidden name="bid">
                <input type=text name="bidValue" value="24" >

                <br><br>
                <input type=submit value="Check Out">

        </form> 
<%
 
} else {

        String idVal = request.getParameter("idValue");
        String bidVal = request.getParameter("bidValue");
       
        cs5530.Connector connector = new Connector();
	cs5530.CheckOut co = new CheckOut();	 
 
        
       
 
        %>
       <%=co.checkout(idVal, bidVal, connector.con)%> <BR><BR>
<!-- Close connection  -->
<%

connector.closeConnection();
}
%>
 
<BR><a href="checkout.jsp"> Check Out Another Book </a></p>
<BR><a href="waitlist.jsp"> Reserve Book </a></p>
<BR><a href="index.html"> Home </a></p>
 
</body>
</html>