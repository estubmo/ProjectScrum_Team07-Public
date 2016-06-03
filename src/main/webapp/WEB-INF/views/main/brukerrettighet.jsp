<%-- 
    Document   : godkjenningsliste
    Created on : 08.jan.2015, 15:32:39
    Author     : Ivar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <title>Oppgaver</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Besvarte oppgaver</h1>
                    <p>Oversikt over dine øvinger</p>
                </div>
            </div>
        </section>
        <section class="container">
            <div class="row">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Bruker</th>
                            <th>Rettighet</th>

                        </tr>
                    </thead>
                    <tbody>
                    <td>
                        <h3>
                            ${bruker.epost}
                        </h3>
                    </td>
                    <td>
                        <h3>
                            ${bruker.rettighet}
                        </h3>
                    </td>
                    </tbody>
                </table>
            </div>
        </section>
    </body>
</html>