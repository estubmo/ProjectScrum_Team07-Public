    <%-- 
    Document   : spill.jsp
    Created on : 08.jan.2015, 16:12:08
    Author     : petteriversen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href=""https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <style>
            #mediumRapper{
                display:flex;
                text-align: center;
                height: 300px;
            }
            #smallRapperLeft{
                width:50%;
                text-align: left;

            }
            #smallRapperRight{
                width:50%;
                text-align: right;
            }
            #progressRapper{
                width:62.11%;
                margin-left: auto;
                margin-right: auto;
            }
            #text{
                border-style:groove;
                height:250px;
                width:95%;
                resize: none;
            }

            .btn-default{
                color: #337ab7;
            }
            .btn-godkjent{
                color:white;
                background-color: green;
            }
            
        </style>
        <title>PST7 - Spill</title>
    </head>
    <body>
        <center>
            <div class="jumbotron">
                <h2>Oppgave ${oppgavenr}</h2>
                <p>${oppgaveBeskrivelse}</p> 
                <h4 style="float:left;">Denne oppgaven er&nbsp;</h4>
            <c:if test="${godkjent}">
                <h4 style="float:left; color:green;">godkjent.</h4>
            </c:if>
            <c:if test="${!godkjent}">
                <h4 style="float:left; color:red;">ikke godkjent.</h4>
            </c:if>
                <h4 style="float: left;">&nbsp;Du har fått ${score} poeng. Du må ha minst ${minpoeng} for å få oppgaven godkjent.</h4>
                <br style="float: bottom;">
            </div>
            <center>
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active" disabled><a href="#">HTML</a></li>
                    <li role="presentation"><a href="<spring:url value="/main/spill/interaktiv?oppgave=1"/>">CSS</a></li>
                </ul>
                    <nav>
                    <ul class="pagination">
                    <li>
                    <c:choose>
                        <c:when test="${oppgavenr>1}">
                            <a class="btn btn-default" href="/pst7/main/spill?oppgave=${oppgavenr-1}" aria-label="Last">
                            <span aria-hidden="true">&laquo;</span>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-default" disabled aria-label="Last">
                            <span aria-hidden="true">&laquo;</span>
                        </c:otherwise>
                    </c:choose>
                    </a>
                    </li>
                    <c:forEach var="i" begin="1" end="${maxoppgave-1}">
                        <li><a class="btn btn-godkjent" href="/pst7/main/spill?oppgave=${i}">${i}</a></li>
                    </c:forEach>
                        <li><a class="btn btn-default" href="/pst7/main/spill?oppgave=${maxoppgave}">${maxoppgave}</a></li>
                    <c:forEach var="i" begin="${maxoppgave+1}" end="10">
                        <li><a class="btn btn-default" disabled href="/pst7/main/spill?oppgave=${i}">${i}</a></li>
                    </c:forEach>
                    <li>
                    <c:choose>
                        <c:when test="${maxoppgave>oppgavenr}">
                            <a class="btn btn-default" href="/pst7/main/spill?oppgave=${oppgavenr+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-default" disabled aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </c:otherwise>
                    </c:choose>
                    </a>
                    </li>
                    </ul>
                  </nav>
            </center>
            <br>
            <form method="POST">
            <div id="mediumRapper">
                <div id="smallRapperLeft">
                    <textarea id="text" name ="postSvar" cols='40' rows='10'>${svar}</textarea>
                </div>
                <div id="smallRapperRight">
                    <iframe srcdoc="${svar}" id="text">Det ser ut til at nettleseren din ikke støtter denne funksjonen, prøv en nettleser som støtter iframe(f.eks. Google Chrome)</iframe>
                </div>
            </div>
            <div id='progressRapper'>
                <div class="progress">
                    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${maxoppgave*10}" aria-valuemin="0" aria-valuemax="100" style="width: ${maxoppgave*10}%">
                    <span class="sr-only">${maxoppgave*10}% Complete</span>
                    </div>
                </div>
                
                <a href="<spring:url value="/main/spill/reset"/>" id="resetKnapp" name="reset" class="btn btn-default">Reset</a>
                
                <button id="submitKnapp" name="submit" value="submit" type="submit" class="btn btn-default" onClick="">Se resultat</button>
                
            </form>
            </div>
        </center>
    </body>
</html>
