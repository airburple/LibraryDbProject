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
 
<h1>Add New Book Info</h1>
 
 
 
<body>
 
 
        <%
 
        String isbnat = request.getParameter("isbn");
        String titleat = request.getParameter("title");
        String publisherat = request.getParameter("publisher");
        String subjectat = request.getParameter("subject");
        String yearat = request.getParameter("yearPublished");
        String formatat = request.getParameter("format");
        String authorsat = request.getParameter("authors");
        String summaryat = request.getParameter("summary");
        if(isbnat == null){
 
        %>
 
        Please Enter Book Info:<br>
        <form name="AddBook" method=get onsubmit="return check_all_fields(this)" action="newbook.jsp">
 
                <br>Isbn<br>
                <input type=hidden name="isbn">
                <input type=text name="isbnValue" value="028413525-2" >
 
                <br>Title<br>
                <input type=hidden name="title">
                <input type=text name="titleValue" value="Grapes of Anger" >
 
 
                <br>Authors Seperated By Comma<br>
                
                <input type=hidden name="authors">
                <input type=text name="authorsValue" value="Jon Doe, Jane Doe" >
 
                <br>Publisher<br>
                
                <input type=hidden name="publisher">
                <input type=text name="publisherValue" value="Polar Books" >
 
                <br>Subject<br>
                
                <input type=hidden name="subject">
                <input type=text name="subjectValue" value="Drama" >
 
                <br>Year Published<br>
                
                <input type=hidden name="yearPublished">
                <input type=text name="yearPublishedValue" value="1922" >  

                <br>Format<br>
                
                <input type=hidden name="format">
                <input type=text name="formatValue" value="Hard Cover" > 

                <br>Summary<br>
                
                <input type=hidden name="summary">
                <input type=text name="summaryValue" value="good book" >          
 
                <br><br>
                <input type=submit value="Add Book">
 
        </form>
 
<%
 
} else {


        String isbnValue = request.getParameter("isbnValue");
        String titleValue = request.getParameter("titleValue");
        String publisherValue = request.getParameter("publisherValue");
        String subjectValue = request.getParameter("subjectValue");
        String yearPublishedValue = request.getParameter("yearPublishedValue");
        String formatValue = request.getParameter("formatValue");
        String authorsValue = request.getParameter("authorsValue");
        String summaryValue = request.getParameter("summaryValue");
 
        
 
        cs5530.Connector connector = new Connector();
		cs5530.NewBook book = new NewBook();
 
%>
<h1> Book Added! </h1>
 
<!-- Show confirmation of user being added to data base -->
        <%
 
        book.newBookInfo(isbnValue, titleValue ,authorsValue, publisherValue, subjectValue, yearPublishedValue, formatValue, summaryValue, connector.con);
        out.println("ISBN: " + isbnValue + "<br>");
        
        %>
       
<!-- Close connection  -->
<%

connector.closeConnection();
}
%>
 
<BR><a href="newbook.jsp"> Add Another Book </a></p>
<BR><a href="index.html"> Home </a></p>
 
</body>
</html>