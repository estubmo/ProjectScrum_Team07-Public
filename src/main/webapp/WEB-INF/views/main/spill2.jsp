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
        <title>PST7 - Spill</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="<spring:url value='/js/hsps/url.jsp' />"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            #satan {
                height: 200px;
                background-color: gray;
            }
            #canvas {
                border: solid black 2px;
                float: right;
                width: 50%;
                height: 600px;
            }

            #kodeomrade {
                border: solid black 2px;
                border-right: solid black 0px;
                float: left;
                height: 600px;
                width: 50%;
            }
            #uskrivbar{
                height: 50%;
                width: 100%;
            }
            .tekstfelt {
                width: 100%;
                height: 50%;
                resize: none;
                font-size: 30px;
                border-color: #ffffff;
                border: solid black 0px;
            }
            .midtstill {
                text-align: center;
            }
            #resultatKnapp{
                margin-top: 10px;
            }
            #lyd {
                visibility: hidden;
            }
            #oppgavenavigering {
                text-align: center;
            }
            textarea:hover,
            textarea:active,
            textarea:focus
            {
                outline:0px !important;
                -webkit-appearance:none;
            }
            #ikkeskrivbartfelt{
                background-color: #EAEAEA;
            }


        </style>
    </head>
    <body>

        <div class="jumbotron">
            <h2>Oppgave ${oppgavenr}</h2>
            <p>${oppgavebeskrivelse}</p>
            <h3 style="float:left;">Denne oppgaven er&nbsp;</h3>
            <c:if test="${godkjentliste[oppgavenr-1]}">
                <h3 style="float:left; color:green;">godkjent.</h3>
            </c:if>
            <c:if test="${!godkjentliste[oppgavenr-1]}">
                <h3 style="float:left; color:red;">ikke godkjent.</h3>
            </c:if>
                <br style="float: bottom;">
        </div>
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="/pst7/main/spill?oppgave=1">HTML</a></li>
            <li role="presentation" disabled class="active"><a>CSS</a></li>
        </ul>
        <div id="oppgavenavigering">
            <nav>
                <ul class="pagination">
                    <%
                        int teller = 1;
                    %>
                    <c:forEach items="${godkjentliste}" var="godkjent">
                        <c:if test="${godkjent}">
                            <li><a style="background-color:#66cc00;color:white;border:solid white 1px" href="/pst7/main/spill/interaktiv?oppgave=<%out.println(teller);%>"><%out.println(teller);
                                teller++;%></a></li>
                                </c:if>
                                <c:if test="${!godkjent}">
                            <li><a style="background-color:#ffffff" href="/pst7/main/spill/interaktiv?oppgave=<%out.println(teller);%>"><%out.println(teller);
                                teller++;%></a></li>
                                </c:if>               

                    </c:forEach>
                </ul>
            </nav>
        </div>
        <section id="kodeomrade">
            <textarea class="tekstfelt" id="ikkeskrivbartfelt" readonly>${oppgavetekst}</textarea>
            <textarea class="tekstfelt" id="kodefelt" autofocus>${standardoppgavetekst}</textarea>
        </section>
        <section>
            <canvas id="canvas"></canvas>
            <script type="text/javascript">

                var canvas = document.getElementById('canvas');
                var ctx = canvas.getContext('2d');
                var ferdig = false;
                var kodegudd = false;
                var safetyNet = 5;

                canvas.width = $("canvas").width();
                canvas.height = $("canvas").height();

                var mySprite = {
                    x: 100,
                    y: 100,
                    width: 50,
                    height: 50,
                    speed: 200,
                    color: '#c00'
                };
                var målImage = new Image();
                målImage.src = "http://bildr.no/image/N251Z3da.jpeg";
                var mål = {
                    x: canvas.width - 48,
                    y: (canvas.height / 2) - (84),
                    width: 48,
                    height: 168
                };
                var hindring = {
                    x: 400,
                    y: 0,
                    width: 50,
                    height: canvas.height,
                    color: '#fff'
                };
                var ekstra = {
                    x: 0,
                    y: 0,
                    width: 200,
                    height: 100,
                    color: '#fff'
                };

                var keysDown = {};
                window.addEventListener('keydown', function (e) {
                    keysDown[e.keyCode] = true;
                });
                window.addEventListener('keyup', function (e) {
                    delete keysDown[e.keyCode];
                });

                function initLevel2() {

                    hindring.x = 300;
                    hindring.width = 100;
                    hindring.color = '#5858FA';
                    ekstra.color = '#6C3737';
                    ekstra.width = 50;
                    ekstra.height = 25;
                    ekstra.x = hindring.x - 20;
                    ekstra.y = (canvas.height / 2) - (ekstra.height / 2);
                }
                function initLevel3() {
                    hindring.x = 0;
                    hindring.y = 0;
                    hindring.width = 0;
                    hindring.height = 0;
                    mål.x = -100;
                }
                function update(mod) {
                    if (ferdig) {
                        clearInterval(interval);

                        document.getElementById("tidInput").value = time - timeStart;
                        document.getElementById("submitForm").submit();
                    }
                    if (mySprite.x >= 0) { //venstre
                        if (venstreOk() || kodegudd) {
                            if (37 in keysDown) {
                                mySprite.x -= mySprite.speed * mod;
                            }
                        }
                    }
                    if (mySprite.y >= 0) { //opp
                        if (38 in keysDown) {
                            if (oppOk() || kodegudd) {
                                mySprite.y -= mySprite.speed * mod;
                            }
                        }
                    }
                    if (mySprite.x <= canvas.width - mySprite.width) { //høyre      
                        if (hoyreOk() || kodegudd) { //sjekker om spiller er utenfor hindring
                            if (39 in keysDown) {
                                mySprite.x += mySprite.speed * mod;
                            }
                        }
                    }
                    if (mySprite.y <= canvas.height - mySprite.height) {//ned
                        if (40 in keysDown) {
                            if (nedOk() || kodegudd) { // sjekker om spiller er utenfor hindring
                                mySprite.y += mySprite.speed * mod;
                            }
                        }
                    }

                    if (mySprite.x + (mySprite.width / 2) > mål.x && mySprite.x < mål.x + mål.width && mySprite.y + (mySprite.height / 2) > mål.y && mySprite.y < mål.y - (mySprite.height / 2) + (mål.height)) {
                        ferdig = true;
                    }
                }
                function nedOk() {
                    if ((!innenforObjekt(mySprite.x, mySprite.y + mySprite.height + safetyNet, hindring) && (!innenforObjekt(mySprite.x + mySprite.width, mySprite.y + mySprite.height + safetyNet, hindring)))) {
                        return true;
                    }
                    else {
                        if ((innenforObjekt(mySprite.x, mySprite.y + mySprite.height + safetyNet, ekstra)) || (!innenforObjekt(mySprite.x, mySprite.y + mySprite.height + safetyNet, hindring)) && ((innenforObjekt(mySprite.x + mySprite.width, mySprite.y + mySprite.height + safetyNet, ekstra)) || (!innenforObjekt(mySprite.x + mySprite.width, mySprite.y + mySprite.height + safetyNet, hindring)))) {
                            return true;
                        }
                    }
                    return false;
                }
                function oppOk() {
                    if ((!innenforObjekt(mySprite.x, mySprite.y - safetyNet, hindring) && (!innenforObjekt(mySprite.x + mySprite.width, mySprite.y - safetyNet, hindring)))) {
                        return true;
                    }
                    else {
                        if (((innenforObjekt(mySprite.x, mySprite.y - safetyNet, ekstra)) || (!innenforObjekt(mySprite.x, mySprite.y - safetyNet, hindring))) && (innenforObjekt(mySprite.x + mySprite.width, mySprite.y - safetyNet, ekstra) || (!innenforObjekt(mySprite.x + mySprite.width, mySprite.y - safetyNet, hindring)))) {
                            return true;
                        }
                    }
                    return false;
                }
                function venstreOk() {
                    if ((!innenforObjekt(mySprite.x - safetyNet, mySprite.y, hindring)) && (!innenforObjekt(mySprite.x - safetyNet, mySprite.y + mySprite.height, hindring))) {
                        return true;
                    }
                    else {
                        if (innenforObjekt(mySprite.x - safetyNet, mySprite.y, ekstra) && innenforObjekt(mySprite.x - safetyNet, mySprite.y + mySprite.height, ekstra)) {
                            return true;
                        }
                    }
                    return false;
                }
                function hoyreOk() {
                    if ((!innenforObjekt(mySprite.x + mySprite.width + safetyNet, mySprite.y, hindring)) && (!innenforObjekt(mySprite.x + mySprite.width + safetyNet, mySprite.y + mySprite.height, hindring))) {
                        return true;
                    }
                    else {
                        if (innenforObjekt(mySprite.x + mySprite.width + safetyNet, mySprite.y, ekstra) && innenforObjekt(mySprite.x + mySprite.width + safetyNet, mySprite.y + mySprite.height, ekstra)) {
                            return true;
                        }
                    }
                    return false;
                }
                function innenforObjekt(posx, posy, obj) {//pos er posisjon du vil sjekke, og obj er objektet, returnerer true hvis posisjon er inni objektet
                    if (posx < obj.x + obj.width && posx > obj.x && posy < obj.y + obj.height && posy > obj.y) {
                        return true;
                    }
                    return false;
                }
                function render() {
                    //tegner bakgrunn
                    ctx.fillStyle = '#555';
                    ctx.fillRect(0, 0, canvas.width, canvas.height);
                    //tegner mål
                    ctx.drawImage(målImage, mål.x, mål.y);
                    //tegner hindring
                    ctx.fillStyle = hindring.color;
                    ctx.fillRect(hindring.x, hindring.y, hindring.width, hindring.height);
                    if (${oppgavenr} === 2) {
                        //tegner bro
                        ctx.fillStyle = ekstra.color;
                        ctx.fillRect(ekstra.x, ekstra.y, ekstra.width, ekstra.height);
                    }
                    //tegner spiller
                    ctx.fillStyle = mySprite.color;
                    ctx.fillRect(mySprite.x, mySprite.y, mySprite.width, mySprite.height);
                    //tegner tid
                    ctx.fillStyle = '#ffffff';
                    ctx.font = "20px serif";
                    ctx.fillText(((Date.now() - timeStart) / 1000), 10, 20);
                }
                function run() {
                    update((Date.now() - time) / 1000);
                    render();
                    time = Date.now();
                }



                if (${oppgavenr} === 2) {
                    initLevel2();
                }
                if (${oppgavenr} === 3) {
                    initLevel3();
                }
                var time = Date.now();
                var timeStart = Date.now();
                var interval = setInterval(run, 10);
                //----------------------------------------------------------------------------

                function sjekkKode() { //går gjennom koden som står i kodefeltet og vurderer om koden tar vekk veggen.
                    var teksten = document.getElementById("kodefelt").value;
                    var posStart;
                    var posEnde;
                    var funnetBartekroll = false;
                    teksten = teksten.replace(/\s+/g, '');

                    if (${oppgavenr} === 1) {
                        posStart = teksten.search("#hindring{");
                        if (!(posStart === -1)) {
                            posStart += 10;
                        }
                    }
                    else if (${oppgavenr} === 2) {
                        posStart = teksten.search("#bro{");
                        if (!(posStart === -1)) {
                            posStart += 5;
                        }
                    }
                    else if (${oppgavenr} === 3) {
                        posStart = teksten.search(".mål{");
                        if (!(posStart === -1)) {
                            posStart += 5;
                        }
                    }

                    for (i = posStart; !funnetBartekroll && i < teksten.length; i++) { //finner bartekrølli
                        if (teksten.charAt(i) === '{') {
                            funnetBartekroll = false;
                            break;
                        }
                        else if (teksten.charAt(i) === '}') {
                            posEnde = i;
                            funnetBartekroll = true;
                        }
                    }
                    if (funnetBartekroll) {
                        teksten = teksten.substring(posStart, posEnde);

                        if (${oppgavenr} === 1) {
                            posStart = teksten.search("background-color:#");
                            if (posStart > -1) {
                                var nytekst = teksten.substring(posStart + 18, teksten.length);
                                posEnde = nytekst.search(';');
                                if (posEnde > -1) {
                                    try {
                                        nytekst = nytekst.substring(0, posEnde);
                                        if (nytekst.length === 3 || nytekst.length === 6) {
                                            var erIkkeFargekode = false;
                                            nytekst = nytekst.toLowerCase();
                                            for (i = 0; i < nytekst.length; i++) {
                                                if (!((nytekst.charCodeAt(i) >= 97 && nytekst.charCodeAt(i) <= 102) || (nytekst.charCodeAt(i) >= 48 && nytekst.charCodeAt(i) <= 57))) {
                                                    erIkkeFargekode = true;
                                                }
                                            }
                                            if (!erIkkeFargekode) {
                                                hindring.color = '#' + nytekst;
                                                if (nytekst === "555") {
                                                    kodegudd = true;
                                                }
                                                else{
                                                    kodegudd = false;
                                                }
                                            }
                                        }
                                    }
                                    catch (e) {
                                    }
                                }
                            }
                        }
                        else if (${oppgavenr} === 2) {
                            posStart = teksten.search("width:");
                            if (posStart > -1) {
                                var nytekst = teksten.substring(posStart + 6, teksten.length);
                                posEnde = nytekst.search(';');
                                if (posEnde > -1) {
                                    try {
                                        nytekst = nytekst.substring(0, posEnde);
                                        var verdi = parseInt(nytekst, 10);
                                        if (verdi > 0) {
                                            ekstra.width = verdi;
                                        }
                                    }
                                    catch (e) {

                                    }
                                }
                            }
                            posStart = teksten.search("height:");
                            if (posStart > -1) {
                                var nytekst = teksten.substring(posStart + 7, teksten.length);
                                posEnde = nytekst.search(';');
                                if (posEnde > -1) {
                                    try {
                                        nytekst = nytekst.substring(0, posEnde);
                                        var verdi = parseInt(nytekst, 10);
                                        if (verdi > 0) {
                                            ekstra.height = verdi;
                                        }
                                    }
                                    catch (e) {

                                    }
                                }
                            }
                        }
                        else if (${oppgavenr} === 3) {
                            posStart = teksten.search("visibility:");
                            if (posStart > -1) {
                                nytekst = teksten.substring(posStart + 11, teksten.length);
                                posEnde = nytekst.search(';');
                                if (posEnde > -1) {
                                    try {
                                        nytekst = nytekst.substring(0, posEnde);
                                        if (nytekst === "visible") {
                                            mål.x = canvas.width - 48;
                                            mål.y = (canvas.height / 2) - (84);
                                        }
                                        else if (nytekst === "hidden") {
                                            mål.x = -100;
                                        }
                                    }
                                    catch (e) {

                                    }
                                }
                            }
                        }
                    }
                }
            </script>
        </section>
        <section class="midtstill">
            <button onclick ="sjekkKode()" class="btn btn-default" id="resultatKnapp">Se resultat</button>
        </section>
        <form method="post" id="submitForm" hidden >
            <input type="text" name="tid" id="tidInput"></input>
        </form>

    </body>
</html>
