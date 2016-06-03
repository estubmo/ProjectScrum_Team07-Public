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
        <title>Administrator</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Administrator</h1>
                    <p>Administrasjon av systemet</p>
                </div>
            </div>
        </section>
        <section>
            <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Slett svar</h3>
                        </div>
                        <div class="panel-body">
                            <form method="post">
                                <fieldset>

                                    <div class="input-group" id="egen">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-lock"></span>
                                        </span>
                                        <input class="form-control" placeholder="Passord" name='passord' type="password" required="">

                                    </div>

                                    <span class="input-group btn"></span>
                                    <input class="btn btn-lg btn-danger btn-block knappen" type="submit" value="SLETT ALLE SVAR">

                                    <div class="alert alert-danger" role="alert">
                                        <a href="#" class="alert-link">Pass på! Dette sletter alle svar fra databasen! Du kan ikke angre etterpå!</a>
                                    </div>
                                    
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </section>
    </body>
</html>