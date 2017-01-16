/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 function loadDiv(divId, documentNr){
     //elemenType aus localstorage lesen ->typ
     //divID und documentNr
     
  switch (typ) {
            case 'Artikel':
               var artikelSocket = new WebSocket(host+"ArtikelWebSocket");       
              
                artikelSocket.onopen = function() {
                    artikelSocket.send("LIST");
                };
                
               /*artikelSocket.onclose = function() {
                    alert("closed");
                };*/
               
              /*  for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }
                 websocketList_1 = [artikelSocket];*/
                
                artikelSocket.onmessage = function(event) {
                    var received_msg = event.data;
                   // console.log("Message"+received_msg);
                    erzeugeTabelle("artikel", received_msg, artikelSocket, documentNr);
                };
            break;
            
            case "Warenträger":
                var warentraegerSocket = new WebSocket(host+"WarentraegerWebSocket");
                /* for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }*/
               // websocketList_1 = [warentraegerSocket];
        
                
                warentraegerSocket.onopen = function() {
                    warentraegerSocket.send("LIST");
                };
                warentraegerSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    //console.log("Message"+received_msg);
                    erzeugeTabelle("warentraeger", received_msg, warentraegerSocket, documentNr); 
                };
                
            
                break;
                
            case "Transportbänder":
              
             /*  for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                } */
               // websocketList_1 = [transportbandSocket];
               var transportbandSocket = new WebSocket(host+"TransportbandWebSocket");         
               transportbandSocket.onopen = function() {
                    transportbandSocket.send("LIST");
                };

                transportbandSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("transportband", received_msg, transportbandSocket, documentNr);
                };
                break;
                
            case "Roboter":
                var roboterSocket = new WebSocket(host+"RoboterWebSocket");
                /*for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }*/
              //  websocketList_1[0] = roboterSocket;
                roboterSocket.onopen = function() {
                    roboterSocket.send("LIST");
                };
                
                roboterSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("roboter", received_msg, roboterSocket, documentNr);
                };
                break;
                
            case "Sektoren":
                var sektorSocket = new WebSocket(host+"SektorWebSocket");
                 /*for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }
                websocketList_1 = [sektorSocket];*/
                sektorSocket.onopen = function() {
                    sektorSocket.send("LIST");
                };
                
                sektorSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("sektor", received_msg, sektorSocket, documentNr);
                };
                break;
            case "Sensoren":
                var sensorSocket = new WebSocket(host+"SensorWebSocket");
                 /*for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }
                websocketList_1 = [sensorSocket];*/
                                    
                sensorSocket.onopen = function() {
                    sensorSocket.send("LIST");
                };
                
                sensorSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("sensor", received_msg, sensorSocket, documentNr);
                };
                break;
            case "Gelenke":
                var gelenkSocket = new WebSocket(host+"GelenkWebSocket");
                 /*for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }
                websocketList_1 = [gelenkSocket];*/
                                        
                 gelenkSocket.onopen = function() {
                    gelenkSocket.send("LIST");
                };
                
                gelenkSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("gelenk", received_msg, gelenkSocket, documentNr);
                };
                break;
           
            case "Werkzeuge":
                var werkzeugSocket = new WebSocket(host+"WerzeugWebSocket");
                /*for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }
                websocketList_1 = [werkzeugSocket];*/
                 
                 werkzeugSocket.onopen = function() {
                    werkzeugSocket.send("LIST");
                };
                
                werkzeugSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("werkzeug", received_msg, werkzeugSocket, documentNr);
                };
                break;
            case "Hubpositionierstationen":
                var hubpoSocket = new WebSocket(host+"HubPodestWebSocket");
               /*for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }
                websocketList_1 = [hubpoSocket];*/
           
                huboSocket.onopen = function() {
                    huboSocket.send("LIST");
                };
                
                huboSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("hubpositionierstation", received_msg, hubpoSocket, documentNr);
                };
                break;
            case "Hub-Quer-Stationen":
                var huquSocket = new WebSocket(host+"HubQuerPodestWebSocket");
                 /*for (var i = 0; i < websocketList_1.length; i++){
                    websocketList_1[i].close();
                }
                websocketList_1 = [huquSocket];*/
                huquSocket.onopen = function() {
                    huquSocket.send("LIST");
                };
                
                huquSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("hubQuerStation", received_msg, huquSocket, documentNr);
                };
                break;
            default:
                document.getElementById("spalte_1.1").innerHTML = 'Fehler beim Darstellen der Tabelle (ready-Function)';
      }
    };



    /* ------------------------------ERZEUGE TABELLE -------------------------------- */
    function erzeugeTabelle(typ, listeJSON, websocket, documentNr){
        websocket.onmessage = function(event) {
        };
        
        //console.log("++++++++++++++++++++++++++++++");
        //console.log("ErzeugeTabelle vom Typ: " + typ + ", Websocket: " + websocket);
        var liste = JSON.parse(listeJSON);
        var spaltennamen = getSpaltenname(typ); //Ermittelt die benötigten Spaltnamen

        //Erstellen der Tabelle
        node = document.getElementById("tabelle_"+documentNr); //Tabelle wird an schon im Body erstellte Tabelle angehangen
        node.parentNode.insertBefore(createTable(typ, liste, spaltennamen, liste.inhalt.length, spaltennamen.length, documentNr), node);
        
        //UpdateTabelle wird bei Empfang von neuen Daten aufgerufen
        switch (typ) {
            case 'artikel':
                websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
               updateTabelle("artikel", received_msg, documentNr);
            };
            break;
        case 'warentraeger':
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("warentraeger", received_msg, documentNr);
            };
            break;
        case 'transportband':
                websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("transportband", received_msg, documentNr);
            };
            break;
        case 'roboter':
                websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("roboter", received_msg, documentNr);
            };
            break;
        case "sektor":
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("sektor", received_msg, documentNr);
            };
            break;
        case "sensor":
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("sensor", received_msg, documentNr);
            };
            break;
        case "gelenk":
             websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("gelenk", received_msg, documentNr);
            };
            break;

        case "werkzeug":
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("werkzeug", received_msg, documentNr);
            };
            break;
            
        case "hubpositionierstation":
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("hubpositionierstation", received_msg, documentNr);
            }
            break;
            
        case "hubQuerStation":
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("hubQuerStation", received_msg, documentNr);
            }
            break;
            
        default:
            //document.getElementById("spalte"+documentNr+".1").innerHTML = 'Fehler beim Switch in tabelleErzeugen()';
        }
    }
        

    function getSpaltenname(typ){
        //gibt die passenenen Spaltennamen je nach Typ zurück
        var spaltennamen;
         switch (typ) {
            case 'artikel':
                spaltennamen = ['Bezeichnung', 'Zeitstempel']; 
                return spaltennamen;
            case "warentraeger":
               spaltennamen =  ['Bezeichnung', 'Zeitstempel', 'Montagezustand'];
                return spaltennamen;
            case "transportband":
                 spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Stoerung'];
                return spaltennamen;
            case "roboter":
                console.log("++++Roboter");
                spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Stoerung'];
                return spaltennamen;
            case "sektor":
                 spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Stoerung'];
                return spaltennamen;
            case "sensor":
                 spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Zustand']; 
                return spaltennamen;
            case "gelenk":
                spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Gelenkstellung'];
                return spaltennamen;
            case "werkzeug":
                 spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Zustand'];
                return spaltennamen;
            case "hubpositionierstation":
                spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Position'];
                return spaltennamen;
            case "hubQuerStation":
                spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Zustand'];
                return spaltennamen;
            default:
                //document.getElementById("spalte1.1").innerHTML = 'Fehler beim Darstellen der Tabelle getSpaltenname';
      }
    }
    
    /* ------------------------------UPDATE TABELLE -------------------------------- */
    function updateTabelle(typ, listeJSON, documentNr){
        //Aktualisiert die Werte in der schon erstellten Tabelle
        
        //console.log("update Tabelle von " + typ);
        var liste = JSON.parse(listeJSON);
        var anzahlElemente = liste.inhalt.length;
        
        
       //console.log(typ+"Bezeichnung_"+liste.inhalt[0].id + "_" + documentNr);
        //Aktualisieren der Werte:
        for (var i = 0; i < anzahlElemente; i++){
            document.getElementById(typ+"Bezeichnung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].bezeichnung;
            document.getElementById(typ+"Zeitstempel_"+liste.inhalt[i].id+ "_" + documentNr).innerHTML = liste.inhalt[i].zeitstempel;
         
              switch (typ) {
                case "artikel":
                    break;
                case "warentraeger":
                    document.getElementById(typ + "Montagezustand_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].montagezustand;
                    break;
                case "transportband":
                    document.getElementById(typ+"Stoerung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].stoerung;
                    break;
                case "toboter":
                    document.getElementById(typ +"Stoerung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].stoerung;
                    break;
                case "sektor":
                    document.getElementById(typ + "Stoerung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].stoerung;
                    break;
                case "sensor":
                    document.getElementById(typ +"Zustand_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].zustand;
                    break;
                case "gelenk":
                    document.getElementById(typ +"Stellung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].gelenkstellung;
                    break;
                case "werkzeug":
                    document.getElementById(typ +"Zustand_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].zustand;
                    break;
                case "hubpositionierstation":
                   /*TODO*/
                   document.getElementById('id', typ+"Postition_"+liste.inhalt[j].id  + "_" + documentNr).innerHTML = '';
                   break;
                case "hubQuerStation":
                    /*TODO*/
                    document.getElementById(typ+"Zustand_"+liste.inhalt[j].id  + "_" + documentNr).innerHTML = '';
                    break;
                default:
                 //   document.getElementById("spalte1.3").innerHTML = 'Fehler im switch case - updateTabelle';                      
           } 
        }
    }
    
   
   
   
       //-------------TABELLENERZEUGUNG-----------------------------------------------------
    function createTable(typ, liste, spaltennamen, row, col, documentNr) {
        //console.log("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        //console.log("In CreateTable mit Typ: " + typ);
        
        
        //Gibt den Spalten, die im Body erzeugt wurden die jeweiligen Namen
        if (spaltennamen < 2){
            //console.log("Fehler: spaltennamen < 2");
        }else{
            document.getElementById("spalte_"+documentNr+".1").innerHTML = spaltennamen[0];
            document.getElementById("spalte_"+documentNr+".2").innerHTML = spaltennamen[1];
        }

        //Falls eine dritte Spalte benötigt wird:
        if (spaltennamen.length == 3){
            document.getElementById("spalte_"+documentNr+".3").innerHTML = spaltennamen[2];
        }

        var myTable = document.getElementById("tabelle_"+documentNr); //holt sich die im Body erstellte Tabelle
            
        /*if (document.getElementsByTagName("tbody")[0] != null){
            zeile = document.getElementsByTagName("tbody")[0];
            zeile.parentNode.removeChild(zeile);
        }*/
        
        
         if (document.getElementById("tbody_" + documentNr) != null){
            zeile = document.getElementById("tbody_" + documentNr);
            zeile.parentNode.removeChild(zeile);
        }

        var mytablebody = document.createElement("tbody");
           mytablebody.setAttribute('id', "tbody_" + "documentNr");

        for(var j = 0; j < row; j++) {
            mycurrent_row = document.createElement("tr");     

            for(var i = 0; i < col; i++) {
                mycurrent_cell = document.createElement("td");

                      

                //1.Spalte - Bezeichnung:
                if (i == 0){
                    currenttext = document.createElement("a");
                    currenttext.setAttribute('id', typ+"Bezeichnung_"+liste.inhalt[j].id + "_" + documentNr);
                    //console.log("My currenttext: " + currenttext.id);
                    currenttext.innerHTML = liste.inhalt[j].bezeichnung;
                    currenttext.setAttribute("elementId",liste.inhalt[j].id);
                    currenttext.setAttribute("elementType",typ);
                    //currenttext.setAttribute("elementId_"+documentNr,liste.inhalt[j].id);
                    // currenttext.setAttribute("elementType_"+documentNr,typ);
                    
                    currenttext.onclick = function() {
                        localStorage.setItem("elementId",$(this).attr("elementId"));
                        localStorage.setItem("elementType",$(this).attr("elementType"));
                        // localStorage.setItem("elementId_"+documentNr,$(this).attr("elementId"));
                        // localStorage.setItem("elementType_"+documentNr,$(this).attr("elementType"));
                        //TODO: -elementId_ und elementType + documentNr
                        //      -im localstorage DocumentType auf Einzelansicht ändern
                      
                    $("#einzelansicht"+documentNr).load("indexNiklas2.html");
                    // $("#einzelansicht1").load("indexNiklas.html");
                    };
                }

                //2.Spalte - Zeitstempel:
                if (i == 1){
                    mycurrent_cell.setAttribute('id', typ+"Zeitstempel_"+liste.inhalt[j].id + "_" + documentNr);
                    //console.log("My current_cell: " + mycurrent_cell.id);
                    currenttext = document.createTextNode(liste.inhalt[j].zeitstempel);
                }

                //3.Spalte:
                if (i == 2){
                    switch (spaltennamen[2]) {
                        case 'Montagezustand':
                            mycurrent_cell.setAttribute('id', typ+"Montagezustand_"+liste.inhalt[j].id + "_" + documentNr);
                            currenttext = document.createTextNode(liste.inhalt[j].montagezustand);
                            break;
                        case "Zustand":
                            mycurrent_cell.setAttribute('id', typ+"Zustand_"+liste.inhalt[j].id + "_" + documentNr);
                            currenttext = document.createTextNode(liste.inhalt[j].zustand);
                            break;
                        case "Stoerung":
                            mycurrent_cell.setAttribute('id', typ+"Stoerung_"+liste.inhalt[j].id  + "_" + documentNr);
                            console.log("My current_cell: " + mycurrent_cell.id);
                            currenttext = document.createTextNode(liste.inhalt[j].stoerung);
                            break;
                        case "Gelenkstellung":
                           mycurrent_cell.setAttribute('id', typ+"Gelenkstellung_"+liste.inhalt[j].id  + "_" + documentNr);
                           currenttext = document.createTextNode(liste.inhalt[j].gelenkstellung);
                            break;
                        case "Position":
                          // TODO:  mycurrent_cell.setAttribute('id', "stoerung_"+liste.inhalt[j].id);
                            //TODO: muss noch durch Symbole ersetzt werden!
                           if (liste.inhalt[j].oben == 1){
                               mycurrent_cell.setAttribute('id', typ+"Position_"+liste.inhalt[j].id  + "_" + documentNr);
                               currenttext = document.createTextNode("Pfeil nach oben");
                           }else if (liste.inhalt.unten == 1){
                               mycurrent_cell.setAttribute('id', typ+"Postition_"+liste.inhalt[j].id  + "_" + documentNr);
                               currenttext = document.createTextNode("Pfeil nach unten");
                           }
                            break;
                        case "Zustand":
                           var zustand = getZustandHuQu(liste.inhalt[j].motor, liste.inhalt[j].oben, liste.inhalt[j].mittig, liste.inhalt[j].unten);
                           currenttext = document.createTextNode(zustand);
                            mycurrent_cell.setAttribute('id', typ+"Zustand_"+liste.inhalt[j].id  + "_" + documentNr);
                            break;
                        default:
                         document.getElementById("spalte1.3").innerHTML = 'Fehler im switch case';

                    } 
                }
                mycurrent_cell.appendChild(currenttext); //akt. Text wird an Zeile angehangen
                mycurrent_row.appendChild(mycurrent_cell); //akt. Zeile wird an Spalte angehangen
                }
                mytablebody.appendChild(mycurrent_row); //komplette Spalte wird an Tabellenbody angehangen
        }
        myTable.appendChild(mytablebody); //kompletter Tabellenbody mit allen Spalten wird an die schon existierende Tabelle angehangen

        return myTable;
    }
    
    function getZustandHuQu(motor, oben, mittig, unten){
        var motorzustand = '';
        var zustandHuQu = '';
                           if (liste.inhalt[j].motor == 1){
                               motorzustand = "ein";
                           }
                           else{
                               motorzustand = "aus";
                           }
                           
                            if (liste.inhalt[j].oben){
                                zustandHuQu = motorzustand+ " - Oben";
                            }else if (liste.inhalt[j].mittig){
                                zustandHuQu = motorzustand+ " - Mittig";
                            }else if (liste.inhalt[j].unten){
                                zustandHuQu = motorzustand+ " - Unten"; 
                            }
                            return zustandHuQu;
    }
    
    function closeWebsockets(){
        
    }
    
    //Alles vom Div lösche
    //Alle Websockets schließen
    