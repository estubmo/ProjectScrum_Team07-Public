<%-- 
    Document   : menu
    Created on : 09.jan.2015, 10:22:00
    Author     : Bal
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- Navigation -->
<!-- Top Menu Items -->
<head>
<script>
    function popup() {
        window.open("<spring:url value="/main/chat" />", 'window', 'width=800,height=600');
    }
</script> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
</head>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="row">
        
            <div class="navbar-header ">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
                    <div class="navbar-header">
                        <a class="navbar-brand" href="<spring:url value="/main/"/> ">PST Hovedside</a>
                    </div>
                
            </div>

            <div id="navbar" class="collapse navbar-collapse col-lg-offset-5">
                <ul class="nav navbar-nav">

                    <li><a href="<spring:url value="/main/startspill"/>">Start spill</a></li>
                    <li><a href="javascript:popup()" >Chat</a></li>
                    <li><a href="<spring:url value="/main/highscore"/> ">Highscore</a></li>
                    <li><a href="<spring:url value="/main/godkjenningsliste"/> "> Godkjenningsliste</a></li>
                    <li><a data-toggle="modal" data-target="#myModal" href="<spring:url value="/main/innstillinger"/>"><span class="glyphicon glyphicon-cog"></span> Innstillinger</a></li>
                </ul>
                <sec:authorize url="/main/admin"> 
                    <ul class="nav navbar-nav">
                        <li style=""><a href="<spring:url value="/main/admin"/>">Admin</a></li>
                    </ul>
                </sec:authorize> 
                <ul class="nav navbar-nav">
                    <li><a href="<spring:url value="/login?logout"/>"><span class="glyphicon glyphicon-off"></span>  Logg ut</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
