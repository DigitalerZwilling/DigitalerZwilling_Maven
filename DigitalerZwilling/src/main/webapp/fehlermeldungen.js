/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var host = "DigitalerZwilling/";
var divName = "fehlermeldungen";

function initfehlermeldungen() {
    var ErrorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"ExceptionWebSocket");
    
    ErrorWebSocket.onmessage = function(event) {
        var jsonString = event.data;
        updateSystemfehler(jsonToFehler(JSON.parse(jsonString)));
    };

}

function jsonToFehler(json){
    if(json.datenbankFehlerStatus=="true"){
        return "FEHLER: Datenbank nicht erreichbar";
    }
    
    if(json.spsFehlerStatus=="true"){
        return "FEHLER: SPS nicht erreichbar";
    }
    
    return "";
}

function updateSystemfehler(string){
    
    var footer = document.getElementById("fehlerausgabe");
    while (footer.childNodes.length>0){
        footer.removeChild(footer.childNodes[0]);
    }
    footer.innerHTML = "&#9888; " + string;
}
