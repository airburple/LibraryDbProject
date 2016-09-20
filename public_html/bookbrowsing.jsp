<%@ page language="java" import="cs5530.*" %>
<html>
 
<head>
        <link rel="stylesheet" type="text/css" href="main.css" />
 
    
 
</head>
 
<h3>You may search the Library by Author, Publisher, title-words, and/or subject. </h3>
 
 
 
<body>
 
 
        <%
    
        String authorat = request.getParameter("author");
        
 
        if(authorat == null){
 
        %>
 
        
        <form name="bookbrowsing" method=get onsubmit="return check_all_fields(this)" action="bookbrowsing.jsp">
 
 
                <br>Add an Author to the search.<br>
              
                <input type=hidden name="author">
                <input type=text name="authorValue" value="None" onFocus="value=''">
                <br><br>

                <br>Add a Publishers to the search. <br>
              
                <input type=hidden name="publisher">
                <input type=text name="publisherValue" value="None" onFocus="value=''">
                <br><br>

                
                <br>Add Title-Words to the search. <br>
              
                <input type=hidden name="titleWords">
                <input type=text name="titleWordsValue" value="None" onFocus="value=''">
                <br><br>

                <br>Add a Subject to your search.<br>
              
                <input type=hidden name="subject">
                <input type=text name="subjectValue" value="None" onFocus="value=''">

                <br><br>

                <br>Which conjunction would you like to use? Select none for singular searches.<br>
              
                <input type=hidden name="andOr">
                <input type="radio" name="andOrValue" value= "and" >And<br>
                <input type="radio" name="andOrValue" value= "or" >Or<br>
                <input type="radio" name="andOrValue" value= "None" checked="checked" >None

                <br><br>

                <input type=hidden name="available">
                <input type="radio" name="availableValue" value= "all" checked="checked" >Return All Results<br>
                <input type="radio" name="availableValue" value= "available" >Return Only Available Books<br>

                <br>Choose A Way To Sort Results.<br>

                <input type=hidden name="sorted">
                <input type="radio" name="sortedValue" value= "year" >Year Published<br>
                <input type="radio" name="sortedValue" value= "score" >Average Review Scores<br>
                <input type="radio" name="sortedValue" value= "popularity" >Popularity<br>
                <input type="radio" name="sortedValue" value= "None" checked="checked">None<br>
                




                <input type=submit value="Browse">

        </form> 
<%
 
} else {

        String authorVal = request.getParameter("authorValue");
        String publisherVal = request.getParameter("publisherValue");
        String titleWordsVal = request.getParameter("titleWordsValue");
        String subjectVal = request.getParameter("subjectValue");
        String andOrVal = request.getParameter("andOrValue");
        String sortedVal = request.getParameter("sortedValue");
        String availableVal = request.getParameter("availableValue");
       
       
        cs5530.Connector connector = new Connector();
        cs5530.BookBrowsing browse = new BookBrowsing();  
        
       

            %>
       <%=browse.browseBooks(authorVal, publisherVal, titleWordsVal , subjectVal, andOrVal , sortedVal, availableVal ,connector.con)%> <BR><BR>

<%

connector.closeConnection();
}
%>
 

 
<BR><a href="bookbrowsing.jsp"> New Search</a></p>

<BR><a href="index.html"> Home </a></p>
 
</body>
</html>