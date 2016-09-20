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
 
<h1>Add Book Copies</h1>
 
 
 
<body>
 
 
        <%
 
        
        String isbnat = request.getParameter("isbn");
       
        String copiesat = request.getParameter("copies");
        
 
        if(isbnat == null){
 
        %>
 
        Please enter the following:<br>
        <form name="AddCopies" method=get onsubmit="return check_all_fields(this)" action="addcopies.jsp">
 
                
 
                <br>ISBN<br>
                <input type=hidden name="isbn">
                <input type=text name="isbnValue" value="1122334455" onFocus="value=''">
 
                <br>Copies Adding<br>
                <!-- <input type="text" name="address"> -->
                <input type=hidden name="copies">
                <input type=text name="copiesValue" value="2" onFocus="value=''">

                <br><br>
                <input type=submit value="Add Copies">

        </form> 
<%
 
} else {

        String isbnVal = request.getParameter("isbnValue");
        String copiesVal = request.getParameter("copiesValue");
       
        cs5530.Connector connector = new Connector();
	    cs5530.AddCopies adder = new AddCopies();	 
 
        
       
 
        %>
       <%=adder.addCopies(isbnVal, copiesVal, connector.con)%> <BR><BR>
<!-- Close connection  -->
<%

connector.closeConnection();
}
%>
 
<BR><a href="addcopies.jsp"> Add Another Book </a></p>
<BR><a href="index.html"> Home </a></p>
 
</body>
</html>