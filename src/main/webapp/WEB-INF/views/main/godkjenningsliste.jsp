<%-- 
    Document   : godkjenningsliste
    Created on : 08.jan.2015, 15:32:39
    Author     : Ivar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <title>Godkjenningsliste</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Godkjenningsliste</h1>
                    <p>Oversikt over godkjente øvinger</p>
                </div>
            </div>
        </section>
        <section class="container">
            <div class="row">
                <table class="table table-hover">
                    <c:forEach items="${brukere}" var="bruker">
                        <tbody>
                        <td>
                            <div class="caption">
                                <h3>${bruker.etternavn}, ${bruker.fornavn}</h3>
                            </div> 
                        </td>
                        <sec:authorize url="/main/admin">
                            <td>
                                <h4>
                                    ${bruker.rettighet}
                                </h4>
                            </td>

                            <td>

                                <a href="<spring:url value="/main/godkjenningsliste/brukerrett?id=${bruker.epost}&rett=student"/>" class="btn btn-danger">Student</a>
                            </td>
                            <td>
                                <a href="<spring:url value="/main/godkjenningsliste/brukerrett?id=${bruker.epost}&rett=studass"/>" class="btn btn-danger">Studass</a>
                            </td>
                            <td>
                                <a href="<spring:url value="/main/godkjenningsliste/brukerrett?id=${bruker.epost}&rett=admin"/>" class="btn btn-danger">Admin</a>


                            </td>
                        </sec:authorize>
                        <sec:authorize url="/main/brukersvar">
                            <td>
                                <a href="<spring:url value="/main/godkjenningsliste/bruker?id=${bruker.epost}"/> " class="btn btn-primary">
                                    <span class="glyphicon-info-sign glyphicon"/></span> Se øvinger
                                </a>
                            </td>
                        </sec:authorize>
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </section>
    </body>
</html>