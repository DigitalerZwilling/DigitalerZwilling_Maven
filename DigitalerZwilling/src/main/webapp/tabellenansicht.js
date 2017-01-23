/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function loadDiv(documentNr){
    console.log("DokuNr: " + documentNr);
    var div = document.getElementById("einzelansicht"+documentNr);
    var childs = div.childNodes;

    for(var i=0; i<childs.length; i++){
        div.removeChild(childs[i]);
    }
 
     closeWebsockets(documentNr);
     console.log("In tabellenansicht.js");

  var typ = localStorage.getItem('elementType_'+documentNr);
  console.log("Type="+typ);
  
  switch (typ) {
            case 'Artikel':
                
               var artikelSocket = new WebSocket(host+"ArtikelWebSocket");       
                artikelSocket.onopen = function() {
                    artikelSocket.send("LIST");
                };
            
               addWebsockets(documentNr, [artikelSocket]);

                artikelSocket.onmessage = function(event) {
                    var received_msg = event.data;
                   // console.log("Message"+received_msg);
                    erzeugeTabelle("artikel", received_msg, artikelSocket, documentNr);
                   // erzeugeTabelle("Artikel", received_msg, artikelSocket, documentNr);
                };
            break;
            
            case "Warenträger":
                var warentraegerSocket = new WebSocket(host+"WarentraegerWebSocket");
                addWebsockets(documentNr, [warentraegerSocket]);
  
                warentraegerSocket.onopen = function() {
                    warentraegerSocket.send("LIST");
                };
                warentraegerSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    //console.log("Message"+received_msg);
                    erzeugeTabelle("warentraeger", received_msg, warentraegerSocket, documentNr); 
                    //erzeugeTabelle("Warenträger", received_msg, warentraegerSocket, documentNr); 
                };
                break;
                
            case "Transportbänder":
               var transportbandSocket = new WebSocket(host+"TransportbandWebSocket");         
               transportbandSocket.onopen = function() {
                    transportbandSocket.send("LIST");
                };

               addWebsockets(documentNr, [transportbandSocket]);
               
                transportbandSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    //console.log("Message"+received_msg);
                    erzeugeTabelle("transportband", received_msg, transportbandSocket, documentNr);
                    //erzeugeTabelle("Transportbänder", received_msg, transportbandSocket, documentNr);
                };
                break;
                
            case "Roboter":
                var roboterSocket = new WebSocket(host+"RoboterWebSocket");
               
                addWebsockets(documentNr, [roboterSocket]);
                roboterSocket.onopen = function() {
                    roboterSocket.send("LIST");
                };
                
                roboterSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    //console.log("Message"+received_msg);
                    erzeugeTabelle("roboter", received_msg, roboterSocket, documentNr);
                    //erzeugeTabelle("Roboter", received_msg, roboterSocket, documentNr);
                };
                break;
                
            case "Sektoren":
                var sektorSocket = new WebSocket(host+"SektorWebSocket");
                
                addWebsockets(documentNr, [sektorSocket]);
                sektorSocket.onopen = function() {
                    sektorSocket.send("LIST");
                };
                
                sektorSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("sektor", received_msg, sektorSocket, documentNr);
                    //erzeugeTabelle("Sektoren", received_msg, sektorSocket, documentNr);
                };
                break;
            case "Sensoren":
                var sensorSocket = new WebSocket(host+"SensorWebSocket");
               
                addWebsockets(documentNr, [sensorSocket]);
                                    
                sensorSocket.onopen = function() {
                    sensorSocket.send("LIST");
                };
                
                sensorSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    //console.log("Message"+received_msg);
                    erzeugeTabelle("sensor", received_msg, sensorSocket, documentNr);
                   // erzeugeTabelle("Sensoren", received_msg, sensorSocket, documentNr);
                };
                break;
            case "Gelenke":
                var gelenkSocket = new WebSocket(host+"GelenkWebSocket");
               
                addWebsockets(documentNr, [gelenkSocket]);
                                        
                 gelenkSocket.onopen = function() {
                    gelenkSocket.send("LIST");
                };
                
                gelenkSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    //console.log("Message"+received_msg);
                    erzeugeTabelle("gelenk", received_msg, gelenkSocket, documentNr);
                    //erzeugeTabelle("Gelenke", received_msg, gelenkSocket, documentNr);
                };
                break;
           
            case "Werkzeuge":
                var werkzeugSocket = new WebSocket(host+"WerzeugWebSocket");
               
                addWebsockets(documentNr, [werkzeugSocket]);
                 
                 werkzeugSocket.onopen = function() {
                    werkzeugSocket.send("LIST");
                };
                
                werkzeugSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    //console.log("Message"+received_msg);
                    erzeugeTabelle("werkzeug", received_msg, werkzeugSocket, documentNr);
                    //erzeugeTabelle("Werkzeuge", received_msg, werkzeugSocket, documentNr);
                };
                break;
            case "Hubpositionierstationen":
                var hupoSocket = new WebSocket(host+"HubPodestWebSocket");
               
                addWebsockets(documentNr, [hupoSocket]);
                hupoSocket.onopen = function() {
                    hupoSocket.send("LIST");
                };
                
                hupoSocket.onmessage = function(event) {
                    var received_msg = event.data;
                  //  console.log("Message"+received_msg);
                    erzeugeTabelle("hubpositionierstation", received_msg, hupoSocket, documentNr);
                    //erzeugeTabelle("Hubpositionierstationen", received_msg, hupoSocket, documentNr);
                };
                break;
            case "Hub-Quer-Stationen":
                var huquSocket = new WebSocket(host+"HubQuerPodestWebSocket");
               
                addWebsockets(documentNr, [huquSocket]);
                huquSocket.onopen = function() {
                    huquSocket.send("LIST");
                };
                
                huquSocket.onmessage = function(event) {
                    var received_msg = event.data;
                    console.log("Message"+received_msg);
                    erzeugeTabelle("hubQuerStation", received_msg, huquSocket, documentNr);
                    //erzeugeTabelle("HubQuerStationen", received_msg, huquSocket, documentNr);
                };
                break;
            default:
                //document.getElementById("spalte_1.1").innerHTML = 'Fehler beim Darstellen der Tabelle (ready-Function)';
      }
    }



    /* ------------------------------ERZEUGE TABELLE -------------------------------- */
    function erzeugeTabelle(typ, listeJSON, websocket, documentNr){
        websocket.onmessage = function(event) {
        };
        
        console.log("++++++++++++++++++++++++++++++");
        console.log("ErzeugeTabelle vom Typ: " + typ + ", Websocket: " + websocket);
        var liste = JSON.parse(listeJSON);
        var spaltennamen = getSpaltenname(typ); //Ermittelt die benötigten Spaltnamen

        //Erstellen der Tabelle
        node = document.getElementById("einzelansicht"+documentNr); //Tabelle an passendes Div angehangen
        node.appendChild(createTable(typ, liste, spaltennamen, liste.inhalt.length, spaltennamen.length, documentNr));
        
        
        //UpdateTabelle wird bei Empfang von neuen Daten aufgerufen
        switch (typ) {
            case 'artikel':
           // case 'Artikel':
                websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
               updateTabelle("artikel", received_msg, documentNr);
            };
            break;
        case 'warentraeger':
        //case 'Warenträger':
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("warentraeger", received_msg, documentNr);
            };
            break;
        case 'transportband':
        //case 'Transportbänder':
                websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("transportband", received_msg, documentNr);
            };
            break;
        case 'roboter':
        //case 'Roboter':
                websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("roboter", received_msg, documentNr);
            };
            break;
        case "sektor":
        //case 'Sektoren':
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("sektor", received_msg, documentNr);
            };
            break;
        case "sensor":
        //case "Sensoren":
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("sensor", received_msg, documentNr);
            };
            break;
        case "gelenk":
        //case "Gelenke":
             websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("gelenk", received_msg, documentNr);
            };
            break;

        case "werkzeug":
        //case "Werkzeuge":
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("werkzeug", received_msg, documentNr);
            };
            break;
            
        case "hubpositionierstation":
        //case "Hubpositionierstationen":
            websocket.onmessage = function(event) {
                var received_msg = event.data;
                //console.log("Message"+received_msg);
                updateTabelle("hubpositionierstation", received_msg, documentNr);
            }
            break;
            
        case "hubQuerStation":
        //case "HubQuerStationen":
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
            //case 'Artikel':
                spaltennamen = ['Bezeichnung', 'Zeitstempel']; 
                return spaltennamen;
            case "warentraeger":
            //case "Warenträger":
               spaltennamen =  ['Bezeichnung', 'Zeitstempel', 'Montagezustand'];
                return spaltennamen;
            case "transportband":
            //case "Transportbänder":
                 spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Stoerung'];
                return spaltennamen;
            case "roboter":
            //case "Roboter":
                console.log("++++Roboter");
                spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Stoerung'];
                return spaltennamen;
            case "sektor":
            //case "Sektoren":
                 spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Stoerung'];
                return spaltennamen;
            case "sensor":
            //case "Sensoren":
                 spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Zustand']; 
                return spaltennamen;
            case "gelenk":
            //case "Gelenke":
                spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Gelenkstellung'];
                return spaltennamen;
            case "werkzeug":
            //case "Werkzeuge":
                 spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Zustand'];
                return spaltennamen;
            case "hubpositionierstation":
            //case "Hubpositionierstationen":
                spaltennamen = ['Bezeichnung', 'Zeitstempel', 'Position'];
                return spaltennamen;
            case "hubQuerStation":
            //case "HubQuerStationen":
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
        
        //Aktualisieren der Werte:
        for (var i = 0; i < anzahlElemente; i++){
            document.getElementById(typ+"Bezeichnung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].bezeichnung;
            document.getElementById(typ+"Zeitstempel_"+liste.inhalt[i].id+ "_" + documentNr).innerHTML = liste.inhalt[i].zeitstempel;
         
              switch (typ) {
                case "artikel":
                //case "Artikel":
                    break;
                case "warentraeger":
                //case "Warenträger":
                    document.getElementById(typ + "Montagezustand_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].montagezustand;
                    break;
                case "transportband":
                //case "Transportbänder"
                    document.getElementById(typ+"Stoerung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].stoerung;
                    break;
                case "roboter":
                //case "Roboter":
                    document.getElementById(typ +"Stoerung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].stoerung;
                    break;
                case "sektor":
                //case "Sektoren":
                    document.getElementById(typ + "Stoerung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].stoerung;
                    break;
                case "sensor":
                //case "Sensoren":
                    document.getElementById(typ +"Zustand_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].zustand;
                    break;
                case "gelenk":
                //case "Gelenke":
                    document.getElementById(typ +"Stellung_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].gelenkstellung;
                    break;
                case "werkzeug":
                //case "Werkzeuge":
                    document.getElementById(typ +"Zustand_"+liste.inhalt[i].id + "_" + documentNr).innerHTML = liste.inhalt[i].zustand;
                    break;
                case "hubpositionierstation":
                 //case "Hubpositionierstationen":
                    var position = "";
                    if (liste.inhalt[i].oben == 1){
                        position = "\u2191";
                    }else if (liste.inhalt[i].unten == 1){
                        position = "\u2193";
                    }else{
                        position = "-";
                    }
                   document.getElementById('id', typ+"Postition_"+liste.inhalt[i].id  + "_" + documentNr).innerHTML = position;
                   break;
                case "hubQuerStation":
                //case "HubQuerStationen":
                     var zustand = getZustandHuQu(liste.inhalt[j].motor, liste.inhalt[i].oben, liste.inhalt[j].mittig, liste.inhalt[j].unten);
                    document.getElementById(typ+"Zustand_"+liste.inhalt[i].id  + "_" + documentNr).innerHTML = zustand;
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
 
        var myTable = document.createElement("table");
        myTable.setAttribute('class', "table table-striped");
        myTable.setAttribute('id', "tablle_"+documentNr);
        
        
        var thead = document.createElement("thead");
        thead.setAttribute('id', "thead_" + documentNr);
        
        var tr = document.createElement("tr");
        tr.setAttribute('id', "tr_" + documentNr);
        
        var th1 = document.createElement("th");
        th1.setAttribute('id', "spalte_" + documentNr + ".1");
        
        var th2 = document.createElement("th");
        th2.setAttribute('id', "spalte_" + documentNr + ".2");
        
        var th3 = document.createElement("th");
        th3.setAttribute('id', "spalte_" + documentNr + ".3");
        
        tr.appendChild(th1);
        tr.appendChild(th2);
        tr.appendChild(th3);
        
        thead.appendChild(tr);
        myTable.appendChild(thead);
        
           if (spaltennamen < 2){
            //console.log("Fehler: spaltennamen < 2");
        }else{
            th1.innerHTML = spaltennamen[0];
            th2.innerHTML = spaltennamen[1];
        }

        //Falls eine dritte Spalte benötigt wird:
        if (spaltennamen.length == 3){
            th3.innerHTML = spaltennamen[2];
        }

         if (document.getElementById("tbody_" + documentNr) != null){
            zeile = document.getElementById("tbody_" + documentNr);
            zeile.parentNode.removeChild(zeile);
        }

        var mytablebody = document.createElement("tbody");
           mytablebody.setAttribute('id', "tbody_" + documentNr);

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
                    
                    //TODO nur zu Testzwecken! Einheitlicher Typ in Tabellen und Einzelansichten nötig!
                    if (typ == "hubpositionierstation"){
                        typ = "hupo";
                    }
                    if (typ == "hubQuerStation"){
                        typ = "huqu";
                    }
                    //Ende Test
                    
                    currenttext.setAttribute("elementType",typ);
                   
                    
                    currenttext.onclick = function() {
                       
                       console.log("click");
                         localStorage.setItem("elementId_"+documentNr,$(this).attr("elementId"));
                         localStorage.setItem("elementType_"+documentNr,$(this).attr("elementType"));

                        closeWebsockets(documentNr);
                        initEinzelansicht(documentNr);
                        
                    //TODO nur zu Testzwecken! Einheitlicher Typ in Tabellen und Einzelansichten nötig!
                    if (typ == "hupo"){
                        typ = "hubpositionierstation";
                    }
                    if (typ == "huqu"){
                        typ = "hubQuerStation";
                    }
                    //Ende Test
                    }
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
                            if (typ == "hubQuerStation"){
                                var zustand = getZustandHuQu(liste.inhalt[j].motor, liste.inhalt[j].oben, liste.inhalt[j].mittig, liste.inhalt[j].unten);
                                mycurrent_cell.setAttribute('id', typ+"Zustand_"+liste.inhalt[j].id  + "_" + documentNr);
                                currenttext = document.createTextNode(zustand);
                            }else{
                                mycurrent_cell.setAttribute('id', typ+"Zustand_"+liste.inhalt[j].id + "_" + documentNr);
                                currenttext = document.createTextNode(liste.inhalt[j].zustand);
                            }
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
                           if (liste.inhalt[j].oben == 1){
                               mycurrent_cell.setAttribute('id', typ+"Position_"+liste.inhalt[j].id  + "_" + documentNr);
                               currenttext = document.createTextNode("\u2191");
                           }else if (liste.inhalt.unten == 1){
                               mycurrent_cell.setAttribute('id', typ+"Position_"+liste.inhalt[j].id  + "_" + documentNr);
                               currenttext = document.createTextNode("\u2193");
                           }else{
                                mycurrent_cell.setAttribute('id', typ+"Position_"+liste.inhalt[j].id  + "_" + documentNr);
                               currenttext = document.createTextNode("-");
                           }
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
                           if (motor == 1){
                               motorzustand = "ein";
                               console.log("ein");
                           }
                           else if (motor == 0){
                               motorzustand = "aus";
                               console.log("aus");
                           }
                            if (oben){
                                zustandHuQu = motorzustand+ " - Oben";
                            }else if (mittig){
                                zustandHuQu = motorzustand+ " - Mittig";
                            }else if (unten){
                                zustandHuQu = motorzustand+ " - Unten"; 
                            }
                            else{
                                zustandHuQu = motorzustand+ " - x "; 
                            }
                            return zustandHuQu;
    }
    
