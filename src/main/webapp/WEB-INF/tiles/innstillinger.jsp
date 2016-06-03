<%-- 
    Document   : instillinger
    Created on : Jan 8, 2015, 3:20:13 PM
    Author     : Eirik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <style type="text/css">
        </style>
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script>
        </script>

        <title>Innstillinger</title>
    </head>
    <body>
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Innstillinger</h4>
                    </div>
                    <div class="modal-body">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-envelope"></span>
                            </span>
                            <input class="form-control" type="text" placeholder="Epost" value="${pageContext.request.userPrincipal.name}" readonly>
                        </div>
                        <form method="post" id="passwordForm" action="<c:url value='/main/endrepassord' />" modelAttribute="passwordChange">
                            <label for="oldPassword">Endre passord</label>
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </span>
                                <input class="input-lg form-control" type="password" name="gammeltPassord" id="oldPassword" autocomplete="off" placeholder="Gammelt passord" required="" />
                            </div> 

                            <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </span>
                                <input class="input-lg form-control" type="password" name="nyttPassord" id="password1" autocomplete="off" placeholder="Nytt passord" required="" />
                            </div> 
                            <div class="row">
                                <div class="col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2">
                                    <span id="8char" class="glyphicon glyphicon-remove" style="color:#FA5858;"></span> 8 tegn langt<br>
                                    <span id="ucase" class="glyphicon glyphicon-remove" style="color:#FA5858;"></span> En stor bokstav
                                </div>
                                <div class="col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2">
                                    <span id="lcase" class="glyphicon glyphicon-remove" style="color:#FA5858;"></span> En liten bokstav<br>
                                    <span id="num" class="glyphicon glyphicon-remove" style="color:#FA5858;"></span> Ett tall
                                </div>
                            </div>
                            <div class="input-group input-group-lg">    
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </span>
                                <input class="input-lg form-control"  type="password" name="nyttPassordGjenta" id="password2" autocomplete="off" placeholder="Gjenta passord" required="" />
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit" id="changePasswordButton"  value="disabled" disabled>Endre passord</button>
                                </span>

                            </div> 
                            <div class="row">
                                <div class="col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2 col-xs-4 col-xs-offset-2">
                                    <span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FA5858;"></span> Passordene er like
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">

    $("input[type=password]").keyup(function() {
        var ucase = new RegExp("[A-Z]+");
        var lcase = new RegExp("[a-z]+");
        var num = new RegExp("[0-9]+");
        var allTrue = [false, false, false, false, false];

        if ($("#password1").val().length >= 8) {
            $("#8char").removeClass("glyphicon-remove");
            $("#8char").addClass("glyphicon-ok");
            $("#8char").css("color", "#58FA58");

            allTrue[0] = true;
        } else {
            $("#8char").removeClass("glyphicon-ok");
            $("#8char").addClass("glyphicon-remove");
            $("#8char").css("color", "#FA5858");
            allTrue[0] = false;
        }

        if (ucase.test($("#password1").val())) {
            $("#ucase").removeClass("glyphicon-remove");
            $("#ucase").addClass("glyphicon-ok");
            $("#ucase").css("color", "#58FA58");
            allTrue[1] = true;
        } else {
            $("#ucase").removeClass("glyphicon-ok");
            $("#ucase").addClass("glyphicon-remove");
            $("#ucase").css("color", "#FA5858");
            allTrue[1] = false;
        }

        if (lcase.test($("#password1").val())) {
            $("#lcase").removeClass("glyphicon-remove");
            $("#lcase").addClass("glyphicon-ok");
            $("#lcase").css("color", "#58FA58");
            allTrue[2] = true;
        } else {
            $("#lcase").removeClass("glyphicon-ok");
            $("#lcase").addClass("glyphicon-remove");
            $("#lcase").css("color", "#FA5858");
            allTrue[2] = false;
        }

        if (num.test($("#password1").val())) {
            $("#num").removeClass("glyphicon-remove");
            $("#num").addClass("glyphicon-ok");
            $("#num").css("color", "#58FA58");
            allTrue[3] = true;
        } else {
            $("#num").removeClass("glyphicon-ok");
            $("#num").addClass("glyphicon-remove");
            $("#num").css("color", "#FA5858");
            allTrue[3] = false;
        }

        if ($("#password1").val() === $("#password2").val()) {
            $("#pwmatch").removeClass("glyphicon-remove");
            $("#pwmatch").addClass("glyphicon-ok");
            $("#pwmatch").css("color", "#58FA58");
            allTrue[4] = true;
        } else {
            $("#pwmatch").removeClass("glyphicon-ok");
            $("#pwmatch").addClass("glyphicon-remove");
            $("#pwmatch").css("color", "#FA5858");
            allTrue[4] = false;
        }

        if (allTrue[0] === true && allTrue[1] === true && allTrue[2] === true && allTrue[3] === true && allTrue[4] === true) {
            $("#changePasswordButton").prop("disabled", false);
        } else {
            $("#changePasswordButton").prop("disabled", true);
        }

    }
    );

</script>
</script>
</body>
</html>
