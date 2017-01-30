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
    
    var footer = document.getElementById("footer");
    while (footer.childNodes.length>0){
        footer.removeChild(footer.childNodes[0]);
    }
    
    
    var footer = document.getElementById("footer");
        var div = document.createElement("div");
        div.setAttribute("align","center");
            var font = document.createElement("font");
            font.setAttribute("color","black");
            font.setAttribute("size","4");
                var b = document.createElement("b");
                b.innerHTML = string;
            font.appendChild(b);
        div.appendChild(font);
    footer.appendChild(div);
}
