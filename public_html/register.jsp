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
 
<h1>Register New User</h1>
 
 
 
<body>
 
 
        <%
 
        String usernameat = request.getParameter("username");
        String idat = request.getParameter("cardid");
        String nameat = request.getParameter("full_name");
        String addrat = request.getParameter("address");
        String phoneat = request.getParameter("phonenumber");
        String emailat = request.getParameter("email");
 
        if(usernameat == null){
 
        %>
 
        Please enter the required information:<br>
        <form name="AddUser" method=get onsubmit="return check_all_fields(this)" action="register.jsp">
 
                <br>Username<br>
                <input type=hidden name="username">
                <input type=text name="userValue" value="user name" >
 
                <br>Identification Number<br>
                <input type=hidden name="cardid">
                <input type=text name="idValue" value="272727" >
 
 
                <br>Full Name<br>
                <!-- <input type="text" name="fullname"> -->
                <input type=hidden name="full_name">
                <input type=text name="nameValue" value="Jon Doe" >
 
                <br>Address<br>
                <!-- <input type="text" name="address"> -->
                <input type=hidden name="address">
                <input type=text name="addressValue" value="55 Alpine Dr." >
 
                <br>Phone Number<br>
                <!-- <input type="text" name="phonenumber"> -->
                <input type=hidden name="phonenumber">
                <input type=text name="phoneNumberValue" value="800-999-9999" >
 
                <br>Email Address<br>
                <!-- <input type="text" name="email"> -->
                <input type=hidden name="email">
                <input type=text name="emailValue" value="someplace@page.com" >           
 
                <br><br>
                <input type=submit value="Register">
 
        </form>
 
<%
 
} else {
 
        String userNameVal = request.getParameter("userValue");
        String idVal = request.getParameter("idValue");
        String nameVal = request.getParameter("nameValue");
        String addressVal = request.getParameter("addressValue");
        String phoneVal = request.getParameter("phoneNumberValue");
        String emailVal = request.getParameter("emailValue");
 
        cs5530.Connector connector = new Connector();
		cs5530.Register reg = new Register();
	
 
 
%>
<h1> User added! </h1>
 
<!-- Show confirmation of user being added to data base -->
        <%
 
        reg.addUser(userNameVal, idVal ,nameVal, addressVal, phoneVal, emailVal, connector.con);
        out.println("Username: " + userNameVal + "<br>");
        out.println("UserID: " + idVal + "<br>");
        out.println("Full Name: " + nameVal + "<br>");
        out.println("Address: " + addressVal + "<br>");
        out.println("Phone Number: " + phoneVal + "<br>");
        out.println("Email: " + emailVal + "<br>");
 
        %>
       
<!-- Close connection  -->
<%

connector.closeConnection();
}
%>
 
<BR><a href="register.jsp"> Add another user </a></p>
<BR><a href="index.html"> Home </a></p>
 
</body>
</html>