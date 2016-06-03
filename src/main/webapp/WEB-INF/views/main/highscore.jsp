<%-- 
    Document   : highscore
    Created on : Jan 9, 2015, 12:46:06 PM
    Author     : SimenLaptop
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Highscore</h1>
                    <p>Oversikt over beste prestasjoner</p>
                </div>
            </div>
        </section>
<section class="container">
            <div class="row">
                <table class="table table-hover">
                    <%
                        int teller = 1;
                    %>
                    <c:forEach items="${brukere}" var="bruker">
                        <tbody>
                        <td>
                            <div class="caption">
                                <h3>
                                    <%
                                        out.println(teller);
                                        teller++;
                                    %>
                                </h3>
                            </div> 
                        </td>
                        <td>
                            <div class="caption">
                                <h3>${bruker.etternavn}, ${bruker.fornavn}</h3>
                            </div> 
                        </td>
                        <td>
                            <div>
                                <h3>${bruker.score}</h3>
                            </div>
                        </td>
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </section>
    </body>
</html>
