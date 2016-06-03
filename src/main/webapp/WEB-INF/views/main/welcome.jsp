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
        <h1>Velkommen til hovedsiden!</h1>      
        <p>Denne siden vil bare bli større og større jo flere ting vi legger til! ${email}</p>
    </div>
</div>
<div class="container">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">

            <div class="item active">
                <img src="https://lh6.ggpht.com/PCrc5I3dFmhyQENQZfvcHjmSgomMnrueKDZ12T1rFk0yNuAXzbgUr9TxNS95Tm65csSL=h900" alt="Cute cat 1" width="720" height="720">
                <div class="carousel-caption">
                    <h3>Cat 1</h3>
                    <p>The cat has a touch of Florence and Venice.</p>
                </div>
            </div>

            <div class="item">
                <img src="https://lh6.ggpht.com/PCrc5I3dFmhyQENQZfvcHjmSgomMnrueKDZ12T1rFk0yNuAXzbgUr9TxNS95Tm65csSL=h900" alt="Cute cat 2" width="720" height="720">
                <div class="carousel-caption">
                    <h3>Cat 2</h3>
                    <p>The cat has a touch of France and Venecuela.</p>
                </div>
            </div>

            <div class="item">
                <img src="https://lh6.ggpht.com/PCrc5I3dFmhyQENQZfvcHjmSgomMnrueKDZ12T1rFk0yNuAXzbgUr9TxNS95Tm65csSL=h900" alt="Cute cate 3" width="720" height="720">
                <div class="carousel-caption">
                    <h3>Cat 3</h3>
                    <p>Beatiful cat in Kolymbari, Crete.</p>
                </div>
            </div>

        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>

