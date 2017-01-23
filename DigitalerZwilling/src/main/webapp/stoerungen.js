/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function initStoerung(documentNr){
    closeWebsockets(documentNr);
    
    var div = document.getElementById(divName+documentNr);
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
        table.appendChild(thead);
        table.appendChild(tbody);
    div.appendChild(table);
    
    
    var RoboterWebSocket = new WebSocket(host+'RoboterWebSocket');
    var SektorWebSocket = new WebSocket(host+'SektorWebSocket');
    var SensorWebSocket = new WebSocket(host+'SensorWebSocket');
    var TransportbandWebSocket = new WebSocket(host+'TransportbandWebSocket');
    var WarentraegerWebSocket = new WebSocket(host+'WarentraegerWebSocket');
    var WerkzeugWebSocket = new WebSocket(host+'WerkzeugWebSocket');
    
    
    
    
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
        updateStoerung(documentNr, tbody, jsonString,'Roboter');
    };
}


function updateStoerung(documentNr, parentNode, jsonString, typ){
    console.log()
    var json = JSON.parse(jsonString);
    
    removeLines(parentNode, typ);
    
    for(var i=0; i<json.inhalt.length; i++){
        if(json.inhalt[i].stoerung != 0){
            addLine(documentNr, json.inhalt[i], ['bezeichnung','stoerung'], parentNode, typ);
        }
    }
    
}

function removeLines(parentNode, typ){
    var childs = parentNode.childNodes;

    for(var i=0; i<childs.length; i++){
        var td = childs[i].childNodes[0];
        if(td == undefined) continue;
        var a = td.childNodes[0];
        if(a == undefined) continue;
        if(a.getAttribute("elementType")==typ){
            parentNode.removeChild(childs[i]);
        } 
    }
}