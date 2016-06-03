<%-- 
    Document   : registrering
    Created on : Jan 8, 2015, 3:07:00 PM
    Author     : HavardTollefsen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <style>
            #egen {
                padding:  1px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

        <title>Registrering</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">PST7</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="login">Hjem</a></li>
                        <li class="active"><a href="registrering">Registrer</a></li>

                    </ul>
                    <form action="<c:url value='/j_spring_security_check' />" method="post" class="navbar-form navbar-right">
                        <div class="form-group">
                            <input type="email" class="form-control" name="epost" required="true"  placeholder="Epost">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="passord" required="true" placeholder="Passord">
                        </div>
                        <button type="submit" class="btn btn-inverse">Logg inn</button>
                        <a style="vertical-align: bottom; cursor: pointer; cursor: hand;"  data-toggle="modal" data-target="#glemtPassordModal">Glemt passord?</a>

                    </form>
                </div><!--/.nav-collapse -->
            </div>
        </nav>  
        <script src="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.js"></script>
        <link rel="stylesheet" href="http://lipis.github.io/bootstrap-sweetalert/lib/sweet-alert.css">
        <section>

            <div class="jumbotron">

                <div class="container">

                    <h1>Registrering</h1>

                    <p>Registrer deg som bruker</p>

                </div>

            </div>

        </section>

        <div class="container">

            <div class="row">

                <div class="col-md-4 col-md-offset-4">

                    <div class="panel panel-default">

                        <div class="panel-heading">

                            <h3 class="panel-title">Fyll inn</h3>

                        </div>

                        <div class="panel-body">


                            <form action="<c:url value='/registrering' />" method="post" modelAttribute="regBruker">
                                <fieldset>

                                    <div class="input-group" id="egen">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" placeholder="Fornavn" name='fornavn' type="text" autofocus="" required="">

                                    </div>

                                    <div class="input-group" id="egen">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-user"></span>
                                        </span>
                                        <input class="form-control" placeholder="Etternavn" name='etternavn' type="text" required="">

                                    </div>

                                    <div class="input-group" id="egen">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-envelope"></span>
                                        </span>
                                        <input class="form-control" placeholder="Epost" name='epost' type="email" value="" required="">

                                    </div>

                                    <span class="input-group btn"></span>
                                    <input class="btn btn-lg btn-success btn-block knappen" type="submit" value="Registrer">
                                    <div class="alert alert-success" id="success" role="alert" ${success}>
                                        Bra jobba! Ditt personlige passord er blitt sendt til <b>${epost}</b>.
                                    </div>
                                    <div class="alert alert-danger" id="error" role="alert" ${error}>
                                        Bruker med epost <b>${epost}</b> eksisterer allerede.
                                    </div>
                                </fieldset>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>
        <!-- Glemtpassord-Modal -->
        <div class="modal fade" id="glemtPassordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Glemt passord</h4>
                    </div>
                    <div class="modal-body">
                        <form method="post" modelAttribute="bruker" action="<c:url value='/glemtpassord' />">
                            <label for="oldPassword">Skriv inn epost</label>
                            <div class="input-group input-group-lg">

                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                <input type="email"  class="input-lg form-control" name='epost' type="text" placeholder="Epost" required>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal">Lukk</button>
                        <button  type="submit" class="btn btn-primary">Send mail</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Glemtpassord-Modal slutt -->
    </body>
</html>
