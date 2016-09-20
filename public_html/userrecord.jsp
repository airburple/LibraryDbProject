<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">

function check_all_fields(form_obj){
	alert(form_obj.searchAttribute.value+"='"+form_obj.attributeValue.value+"'");
	if( form_obj.attributeValue.value == ""){
		alert("Search field should be nonempty");
		return false;
	}
	return true;
}

</script> 
</head>
<body>

<%
String searchAttribute = request.getParameter("searchAttribute");
if( searchAttribute == null ){
%>

	Form1: Print User Record by Card Id:
	<form name="user_record" method=get onsubmit="return check_all_fields(this)" action="userrecord.jsp">
		<input type=hidden name="searchAttribute" value="bid">
		<input type=text name="attributeValue" length=10>
		<input type=submit>
	</form>
	<BR><BR>
	<!-- Form2: Search orders on director name:
	<form name="director_search" method=get onsubmit="return check_all_fields(this)" action="orders.jsp">
		<input type=hidden name="searchAttribute" value="director">
		<input type=text name="attributeValue" length=10>
		<input type=submit>
	</form> -->

<%

} else {

	String attributeValue = request.getParameter("attributeValue");
	cs5530.Connector connector = new Connector();
	cs5530.UserRecord record = new UserRecord();
	
%>  

  <p><b>Showing User Record: </b><BR><BR>

  
  <%=record.printUserRecord(attributeValue, connector.con)%> <BR><BR>
  
 

<%
 connector.closeStatement();
 connector.closeConnection();
}  // We are ending the braces for else here
%>

<BR><a href="userrecord.jsp"> New query </a></p>
<BR><a href="index.html"> Home </a></p>



</body>
