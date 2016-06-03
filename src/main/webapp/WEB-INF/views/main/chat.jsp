<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <title>PST7 - Chat</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                max-width: 300px;
                padding: 19px 29px 29px;
                margin: 0 auto 20px;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
                -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
                box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            }

            .form-signin .form-signin-heading,.form-signin .checkbox {
                margin-bottom: 10px;
            }

            .form-signin input[type="text"],.form-signin input[type="password"] {
                font-size: 16px;
                height: auto;
                width: 140px;
                margin-bottom: 15px;
                padding: 7px 9px;
            }

            #chatroom {
                font-size: 16px;
                height: 40px;
                line-height: 40px;
                width: 70px;
            }

            .received {
                width: 160px;
                font-size: 10px;
            }

            table, tr, td {
                border: 0px;
            }
        </style>

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
              <script src="./resource/js/html5shiv.js"></script>
            <![endif]-->

        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144"
              href="./resource/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="./resource/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72"
              href="./resource/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed"
              href="./resource/ico/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="./resource/ico/favicon.png">
        <script>
            var wsocket;
            var serviceLocation = "ws://" + document.location.host + "/pst7/chat/";
            var $nickName;
            var $message;
            var $chatWindow;
            var room = '';
            var d;
            var tid = '';

            function addZero(i) {
                if (i < 10) {
                    i = "0" + i;
                }
                return i;
            }

            function displayTime() {
                d = new Date();
                var h = addZero(d.getHours());
                var m = addZero(d.getMinutes());
                var s = addZero(d.getSeconds());
                tid = h + ":" + m + ":" + s;
            }

            function onMessageReceived(evt) {
                displayTime();
                var msg = JSON.parse(evt.data); // native API
                var $messageLine = $('<tr><td style="width:10%">' + tid
                        + '</td><td style="width:17%">' + msg.sender
                        + '</td><td style="width:73%">' + msg.message
                        + '</td></tr>');
                $chatWindow.append($messageLine);
            }
            function sendMessage() {
                displayTime();
                var msg = '{"message":"' + $message.val() + '", "sender":"'
                        + $nickName.val() + '", "received":""}';
                wsocket.send(msg);
                $message.val('').focus();
            }

            function connectToChatserver() {
                room = $('#chatroom option:selected').val();
                wsocket = new WebSocket(serviceLocation + room);
                wsocket.onmessage = onMessageReceived;
            }
            
            function entretChat(){
                var msg = '{"message":"' + $nickName.val() + '" har entret. "' + '", "sender":"'
                        + '" Server "' + '", "received":""}';
                wsocket.send(msg);
                $message.val('').focus();
            }

            function leaveRoom() {
                wsocket.close();
                $chatWindow.empty();
                $('.chat-wrapper').hide();
                $('.chat-signin').show();
                $nickName.focus();
            }

            $(document).ready(function () {
                $nickName = $('#nickname');
                $message = $('#message');
                $chatWindow = $('#response');
                $('.chat-wrapper').hide();
                $nickName.focus();

                $('#enterRoom').click(function (evt) {
                    evt.preventDefault();
                    connectToChatserver();
                    $('.chat-wrapper h2').text(room);
                    $('.chat-signin').hide();
                    $('.chat-wrapper').show();
                    $message.focus();
                    entretChat();
                });
                $('#do-chat').submit(function (evt) {
                    evt.preventDefault();
                    if($message.val() !== ''){
                        sendMessage()
                    }
                });

                $('#leave-room').click(function () {
                    leaveRoom();
                });
            });
        </script>

    </head>

    <body>

        <div class="container chat-signin">
            <form class="form-signin">
                <h2 class="form-signin-heading">Chat</h2>
                <label for="nickname">Brukernavn</label> 
                <p></p>
                <input type="text" class="input-block-level" id="nickname" value="${pageContext.request.userPrincipal.name}" readonly>
                <div class="btn-group-fluid">
                    <label for="chatroom">Rom</label>
                    <p></p>
                    <select size="1" id="chatroom">
                        <option>Rom 1</option>
                        <option>Rom 2</option>
                        <option>Rom 3</option>
                        <option>Rom 4</option>
                    </select>
                </div>
                <p></p>
                <button class="btn btn-large btn-primary" type="submit" id="enterRoom">Bli med</button>
            </form>
        </div>
        <!-- /container -->

        <div class="container chat-wrapper">
            <form id="do-chat">
                <h2 class="alert alert-success"></h2>
                <table id="response" class="table table-bordered"></table>
                <fieldset>
<!--                    <legend>Enter your message..</legend>-->
                    <div class="container-fluid">
                        <div class="row">
                            <div class="container-fluid">
                                <input class="input form-control"  type="text" placeholder="Skriv her..." id="message" autocomplete="off" />
                            </div>
                        </div>
                        <div class="row">
                            <button class="btn btn-large btn-block btn-primary" type="submit">Send melding</button>
                            <button class="btn btn-large btn-block" type="button" id="leave-room">Forlat rom</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>