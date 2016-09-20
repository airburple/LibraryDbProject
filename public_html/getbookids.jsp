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

	Search Book ISBN for all Book Copy Ids:
	<form name="book_search" method=get onsubmit="return check_all_fields(this)" action="getbookids.jsp">
		<input type=hidden name="searchAttribute" value="bid">
		<input type=text name="attributeValue" length=10 value = "113328077-3" onFocus="value=''">
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
	cs5530.Book book = new Book();
	
%>  

  

  
  <%=book.getBookIds(attributeValue, connector.con)%> <BR><BR>
  
  

<%
 connector.closeStatement();
 connector.closeConnection();
}  // We are ending the braces for else here
%>

<BR><a href="getbookids.jsp"> New Search </a></p>
<BR><a href="index.html"> Home </a></p>



</body>
