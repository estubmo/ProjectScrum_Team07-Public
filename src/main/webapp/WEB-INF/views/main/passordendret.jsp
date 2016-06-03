<%-- 
    Document   : welcome
    Created on : Dec 30, 2014, 12:37:58 AM
    Author     : Eirik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
    .carousel-inner > .item > img,
    .carousel-inner > .item > a > img {
        width: 70%;
        margin: auto;
    }
</style>

<div class="container">
    <div class="jumbotron">
        <h1>Passordendring</h1>
    </div>
    <div class="alert alert-success" id="success" role="alert" ${endret}>
        Passord endret.
    </div>
    <div class="alert alert-danger" id="error" role="alert" ${feil}>
        Gammelt passord var ikke riktig. Prøv på nytt.
    </div>
</div>


