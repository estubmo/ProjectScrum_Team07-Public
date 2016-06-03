<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PST7 - Login</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/alert.js"></script>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <link href="signin.css" rel="stylesheet">
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
                        <li class="active"><a href="login">Hjem</a></li>
                        <li><a href="registrering">Registrer</a></li>

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

        <div class="container">
            <div class="jumbotron" >
                <br>
                <h1>Velkommen!</h1>
                <p>PST7 er en interaktiv læringsplattform for HTML5 og CSS.</p>
                <br>
                <p><a class="btn btn-primary btn-lg" href="registrering" role="button">Opprett bruker</a></p>
            </div>
        </div>

        <div class="container">
            <div class="alert alert-danger" role="alert" ${errorpw}><b>Brukernavn eller passord ugyldig.</b></div>
            <div class="alert alert-success" id="epostSendt" role="alert" ${successNewPw}>Nytt passord har blitt sendt til <b>${epost}</b>.</div>
            <div class="alert alert-danger" id="epostEksistererIkke" role="alert" ${errorNewPw}>Bruker med epost <b>${epost}</b> eksisterer ikke.</div>
            <hr class="featurette-divider">

            <div class="row featurette">
                <div class="col-md-7">
                    <h2 class="featurette-heading">HTML 5 <span class="text-muted"> - fremtiden er her</span></h2>
                    <p class="lead"> Det er lite som har hatt like stor påvirkning på internett som HTML5. Dette er åpenbart en<br>
                        løgn, men uansett. Det ser utrolig mye finere ut med litt tekst her en det gjør uten. Det skal ikke så veldig mange linjene til med tekst
                        for å beskrive internett på noen få setninger i en større sammenheng. Svadageneratoren er i full sving, og man stopper ikke opp for noe som helst. 
                        Det gjør heller ikke noe at teksten er ca like stor som bildet, dette gir et meget godt helhetlig inntrykk av websiden. Dette vet jeg, Balder og flere. Vi er mennesker
                        Vi gjør feil, men vi gjør aldri samme feil mange ganger. Jo, det er det som er så flott.</p>
                </div>
                <div class="col-md-5">
                    <img class="featurette-image img-responsive"  alt="" src="http://www.writeraccess.com/blog/wp-content/uploads/2014/08/blog-html-5.png">
                </div>
            </div>

            <hr class="featurette-divider">
        </div>


        <!-- Statisk footer, følger med siden om man scroller-->
        <div class="container">
            <footer class="footer">
                <div class="container">
                    <p class="text-muted">Utviklet av studenter ved HIST. Alle rettigheter forbeholdt.</p>
                </div>
            </footer>
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
    </body>
</html>
