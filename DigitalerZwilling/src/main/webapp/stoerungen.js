function initStoerung(documentNr){
    console.log("   -> lade Stoerung in Fenster "+documentNr);
    closeWebsockets(documentNr);
    
    var div = document.getElementById(divName + documentNr);
        var childs = div.childNodes;

        for(var i=0; i<childs.length; i++){
            div.removeChild(childs[i]);
        }
        
        var table = document.createElement("table");
        table.setAttribute('class', "table table-striped");
            var thead = document.createElement("thead");
                var tr_head = document.createElement("tr");
                    var th1 = document.createElement("th");
                    th1.innerHTML = 'Bezeichnung';
                    var th2 = document.createElement("th");
                    th2.innerHTML = 'Störung';
                tr_head.appendChild(th1);
                tr_head.appendChild(th2);
            thead.appendChild(tr_head);
            var tbody = document.createElement("tbody");
            tbody.id = "storung_tbody";
        table.appendChild(thead);
        table.appendChild(tbody);
    div.appendChild(table);
}

function initStoerungsZaehler(documentNr){
    if(websocketsStoerungErstellt == true) return;
    websocketsStoerungErstellt = true;
    
    var RoboterWebSocket = new WebSocket("ws://"+location.host+"/"+host+'RoboterWebSocket');
    var SektorWebSocket = new WebSocket("ws://"+location.host+"/"+host+'SektorWebSocket');
    var SensorWebSocket = new WebSocket("ws://"+location.host+"/"+host+'SensorWebSocket');
    var TransportbandWebSocket = new WebSocket("ws://"+location.host+"/"+host+'TransportbandWebSocket');
    var WarentraegerWebSocket = new WebSocket("ws://"+location.host+"/"+host+'WarentraegerWebSocket');
    var WerkzeugWebSocket = new WebSocket("ws://"+location.host+"/"+host+'WerkzeugWebSocket');
    
    
    
    
    RoboterWebSocket.onopen = function() {
                RoboterWebSocket.send("LIST");
    };
    
    SektorWebSocket.onopen = function() {
                SektorWebSocket.send("LIST");
    };
    
    SensorWebSocket.onopen = function() {
                SensorWebSocket.send("LIST");
    };
    
    TransportbandWebSocket.onopen = function() {
                TransportbandWebSocket.send("LIST");
    };
    
    WarentraegerWebSocket.onopen = function() {
                WarentraegerWebSocket.send("LIST");
    };
    
    WerkzeugWebSocket.onopen = function() {
                WerkzeugWebSocket.send("LIST");
    };
    
    
    
    RoboterWebSocket.onmessage = function(event) {
        var jsonString = event.data;
        var parent = document.getElementById("storung_tbody");
        stoerungsZaehler[0] = updateStoerung(documentNr, parent, jsonString,'Roboter');
        updateStoerungszaehler();
    };
    
    SektorWebSocket.onmessage = function(event) {
        var jsonString = event.data;
        var parent = document.getElementById("storung_tbody");
        stoerungsZaehler[1] = updateStoerung(documentNr, parent, jsonString,'Sektoren');
        updateStoerungszaehler();
    };
    
    TransportbandWebSocket.onmessage = function(event) {
        var jsonString = event.data;
        var parent = document.getElementById("storung_tbody");
        stoerungsZaehler[2] = updateStoerung(documentNr, parent, jsonString,'Transportbänder');
        updateStoerungszaehler();
    };
    
    SensorWebSocket.onmessage = function(event) {
        var jsonString = event.data;
        var parent = document.getElementById("storung_tbody");
        stoerungsZaehler[3] = updateStoerung(documentNr, parent, jsonString,'Sensoren');
        updateStoerungszaehler();
    };
    
    WarentraegerWebSocket.onmessage = function(event) {
        var jsonString = event.data;
        var parent = document.getElementById("storung_tbody");
        stoerungsZaehler[4] = updateStoerung(documentNr, parent, jsonString,'Warenträger');
        updateStoerungszaehler();
    };
    
    WerkzeugWebSocket.onmessage = function(event) {
        var jsonString = event.data;
        var parent = document.getElementById("storung_tbody");
        stoerungsZaehler[5] = updateStoerung(documentNr, parent, jsonString,'Werkzeuge');
        updateStoerungszaehler();
    };
}

function updateStoerung(documentNr, parentNode, jsonString, typ){
    var json = JSON.parse(jsonString);
    var cnt = 0;
    
    if(parentNode != null) removeLines(parentNode, typ);
    
    for(var i=0; i<json.inhalt.length; i++){
        if(json.inhalt[i].stoerung != 0){
            if(parentNode != null){
                addLine(documentNr, json.inhalt[i], ['bezeichnung','stoerung'], parentNode, typ);
            }
            cnt++;
        }
    }
    return cnt;
}

function removeLines(parentNode, typ){
    var childs = parentNode.childNodes;
    
    for(var i=0; i<childs.length; i++){
        var td = childs[i].childNodes[0];
        if(td == undefined){
            continue;
        }
        var a = td.childNodes[0];
        if(a == undefined){ 
            continue;
        }
        
        if(a.getAttribute("elementType")==typ){
            parentNode.removeChild(childs[i]);
            i--;
        }
    }
}

function updateStoerungszaehler(){
    var cnt =0;
    
    for(var i=0; i<6; i++){
        cnt += stoerungsZaehler[i];
    }
    
    document.getElementById("stoerungsZaehler").innerHTML = cnt;
    document.getElementById("stoerungsZaehlerMobil").innerHTML = cnt;
}
