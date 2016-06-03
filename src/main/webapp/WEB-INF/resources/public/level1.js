
                var canvas = document.getElementById('canvas');
                var ctx = canvas.getContext('2d');
                var ferdig = false;
                var kodegudd = false;

                canvas.width = $("canvas").width();
                canvas.height = $("canvas").height();

                var mySprite = {
                    x: 200,
                    y: 200,
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
                var keysDown = {};
                window.addEventListener('keydown', function (e) {
                    keysDown[e.keyCode] = true;
                });
                window.addEventListener('keyup', function (e) {
                    delete keysDown[e.keyCode];
                });

                function update(mod) {
                    if (ferdig) {
                        alert((Date.now() - timeStart) / 1000);
                        alert("lol");
                        clearInterval(interval);
                    }
                    if (mySprite.x >= 0) { //venstre
                        if (37 in keysDown) {
                            mySprite.x -= mySprite.speed * mod;
                        }
                    }
                    if (mySprite.y >= 0) { //opp
                        if (38 in keysDown) {
                            mySprite.y -= mySprite.speed * mod;
                        }
                    }
                    if (mySprite.x <= canvas.width - mySprite.width) { //høyre
                        if (kodegudd) {
                            if (39 in keysDown) {
                                mySprite.x += mySprite.speed * mod;
                            }
                        }
                        else {
                            if (mySprite.x + mySprite.width < hindring.x) {
                                if (39 in keysDown) {
                                    mySprite.x += mySprite.speed * mod;
                                }
                            }
                        }
                    }
                    if (mySprite.y <= canvas.height - mySprite.height) {//ned
                        if (40 in keysDown) {
                            mySprite.y += mySprite.speed * mod;
                        }
                    }
                    if (mySprite.x + (mySprite.width / 2) > mål.x && mySprite.x < mål.x + mål.width && mySprite.y + (mySprite.height / 2) > mål.y && mySprite.y < mål.y - (mySprite.height / 2) + (mål.height)) {
                        ferdig = true;
                    }
                }

                function render() {
                    ctx.fillStyle = '#555';
                    ctx.fillRect(0, 0, canvas.width, canvas.height);
                    ctx.drawImage(målImage, mål.x, mål.y);
                    ctx.fillStyle = hindring.color;
                    ctx.fillRect(hindring.x, hindring.y, hindring.width, hindring.height);
                    ctx.fillStyle = mySprite.color;
                    ctx.fillRect(mySprite.x, mySprite.y, mySprite.width, mySprite.height);
                }
                function kodd() {
                    hindring.color = '#555';
                    kodegudd = true;
                }

                function run() {
                    update((Date.now() - time) / 1000);
                    render();
                    time = Date.now();
                }
                var time = Date.now();
                var interval = setInterval(run, 10);
                var timeStart = Date.now();
                //----------------------------------------------------------------------------

                function sjekkKode() { //går gjennom koden som står i kodefeltet og vurderer om koden tar vekk veggen.
                    var teksten = document.getElementById("kodefelt").value;
                    var posStart;
                    var posEnde;
                    var funnetBartekroll = false;
                    teksten = teksten.replace(/\s+/g, '');
                    posStart = teksten.search("#hindring{");
                    if (!(posStart === -1)) {
                        posStart += 10;
                    }

                    for (i = posStart; !funnetBartekroll && i < teksten.length; i++) { //finner bartekrølli
                        if (teksten.charAt(i) === '{') {
                            funnet = false;
                            break;
                        }
                        else if (teksten.charAt(i) === '}') {
                            posEnde = i;
                            funnetBartekroll = true;
                        }
                    }
                    if (funnetBartekroll) {
                        teksten = teksten.substring(posStart, posEnde);
                        posStart = teksten.search("background-color:#555;");
                        if (posStart > -1) {
                            kodegudd = true;
                            hindring.color = '#555';
                        }
                        else {
                            alert(false);
                        }
                    }
                }