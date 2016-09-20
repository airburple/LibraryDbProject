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

	Form1: Authors seperated by comma:
	<form name="authorSplit" method=get onsubmit="return check_all_fields(this)" action="authorsplit.jsp">
		<input type=hidden name="searchAttribute" value="authors">
		<input type=text name="attributeValue" length=30>
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
	
	cs5530.SplitAuthors sa = new SplitAuthors();
	
%>  

  <p><b>Listing Book in JSP: </b><BR><BR>

  The authors for query: <b><%=searchAttribute%>='<%=attributeValue%>'</b> are  as follows:<BR><BR>
  <%=sa.splitAuthors(attributeValue)%> <BR><BR>
  
  

<%
 
}  // We are ending the braces for else here
%>

<BR><a href="authorsplit.jsp"> New query </a></p>



</body>