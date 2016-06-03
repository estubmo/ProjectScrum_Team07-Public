<%-- 
    Document   : baseLayout
    Created on : 09.jan.2015, 10:17:57
    Author     : Bal
--%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body>
        <div class="container-fluid" id="meny">
            <tiles:insertAttribute name="menu" />
        </div>
        
         <div class="container" id="innstillinger">
            <tiles:insertAttribute name="innstillinger" />
        </div>
        
        <div class="container" id="body">
            <tiles:insertAttribute name="body" />
        </div>
        
</body>
</html>

