var svgns = "http://www.w3.org/2000/svg";
var sektor_width = 400;
var sektor_height = 400;
var sektor_fill = '#6BCDFD';
var WT_width = 200;
var WT_height = 200;
var WT_fill = '#FF9900';
var baender_thick = 50;
var baender_fill = '#CCCCCC';
var stoerung_fill = '#FF0000';
var f_size = 100;
var zoom = 0.18;
    
    
    
function initSVG(){
    var documentNr=8;
    
    var SektorWebsocket=new WebSocket(host+"SektorWebSocket");
    var TransportbandWebsocket=new WebSocket(host+"TransportbandWebSocket");
    var WarentraegerWebsocket=new WebSocket(host+"WarentraegerWebSocket");

    addWebsockets(documentNr, [SektorWebsocket,TransportbandWebsocket,WarentraegerWebsocket]);
    
    SektorWebsocket.onopen = function() {
        SektorWebsocket.send("LIST");
    };
                    
    TransportbandWebsocket.onopen = function() {
        TransportbandWebsocket.send("LIST");
    };
                    
    WarentraegerWebsocket.onopen = function() {
        WarentraegerWebsocket.send("LIST");
    };
}


    function sektorenErstellen(j_sektoren){
		for (var i=0;i<j_sektoren.inhalt.length;i++) {
			//Sektoren erstellen
			var rect = document.createElementNS(svgns, 'rect');
                        //rect = document.getElementById("svgOne").getElementById("Sektor" + j_sektoren.inhalt[i].id);
			rect.setAttributeNS(null, 'id', 'Sektor' + j_sektoren.inhalt[i].id);
			rect.setAttributeNS(null, 'x', j_sektoren.inhalt[i].x);
			rect.setAttributeNS(null, 'y', j_sektoren.inhalt[i].y);
			rect.setAttributeNS(null, 'height', sektor_height);
			rect.setAttributeNS(null, 'width', sektor_width);
			if (j_sektoren.inhalt[i].stoerung != 0)
				rect.setAttributeNS(null, 'fill', stoerung_fill);
			else 
				rect.setAttributeNS(null, 'fill', sektor_fill);
			rect.setAttributeNS(null, 'stroke-width', '5');
			rect.setAttributeNS(null, 'stroke', 'black');
			rect.setAttributeNS(null, 'onclick', 'idAusgeben(this)'); //Beim anklicken wird die Id des Elementes ausgeben
			document.getElementById('svgOne').appendChild(rect);
			
            //zugehöriges Label erstellen
            var text = document.createElementNS(svgns, 'text');
            text.setAttributeNS(null, 'id', 'Text' + j_sektoren.inhalt[i].id);
            text.setAttributeNS(null, 'fill', 'black');
            text.setAttributeNS(null, 'x', j_sektoren.inhalt[i].x);
            text.setAttributeNS(null, 'y', j_sektoren.inhalt[i].y+f_size/1.5);
            text.setAttributeNS(null,"font-size",f_size);
            text.setAttributeNS(null,"font-family","Calibri");


            var textNode = document.createTextNode("S" + j_sektoren.inhalt[i].id);
            text.appendChild(textNode);
            //document.getElementById("svgOne").getElementById("Sektor" + j_sektoren.inhalt[i].id).appendChild(text);
            document.getElementById("svgOne").appendChild(text);
		}
	}
	
	function transportbaenderErstellen(j_sektoren, j_baender){
		var id_vor = 0;
		var id_nach = 0;
		for (var i =0;i < j_baender.inhalt.length; i++) {
		//for (var i =0;i < 1; i++) {
			id_vor = j_baender.inhalt[i].vorSektorID;
			id_nach = j_baender.inhalt[i].nachSektorID;
                        //console.log(id_vor+"------"+j_baender.inhalt[i].bezeichnung+"----------"+id_nach)
                        //console.log(document.getElementById("svgOne").getElementById("Transportband" + j_baender.inhalt[i].id));
			var rect = document.createElementNS(svgns, 'rect');
                        //rect = document.getElementById("svgOne").getElementById("Transportband" + j_baender.inhalt[i].id);
			rect.setAttributeNS(null, 'id', 'Transportband' + j_baender.inhalt[i].id);
			rect.setAttributeNS(null, 'stroke-width', '3');
			rect.setAttributeNS(null, 'stroke', 'black');

			rect.setAttributeNS(null, 'onclick', 'idAusgeben(this)');
			if (j_baender.inhalt[i].stoerung != 0) {
				rect.setAttributeNS(null, 'fill', stoerung_fill);
			}
			else {
				rect.setAttributeNS(null, 'fill', baender_fill);
			}
			switch(j_sektoren.inhalt[getSekByID(id_vor)].ausrichtung) {
			//ausrichtung des Sektors finden, welcher vor dem TB liegt
				case 90: //"Ausgang" des Sektors nach rechts
					//Reihe (innen/mitte/aussen) des TB bestimmen und plazieren
					rect.setAttributeNS(null, 'x', (j_sektoren.inhalt[getSekByID(id_vor)].x + sektor_width));
					if (j_baender.inhalt[i].reihe == -1) {
						rect.setAttributeNS(null, 'y', (j_sektoren.inhalt[getSekByID(id_vor)].y + sektor_height) - baender_thick);
					}
					else if (j_baender.inhalt[i].reihe == 1) {
						rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(id_vor)].y);
					}
					else {
						rect.setAttributeNS(null, 'y', (j_sektoren.inhalt[getSekByID(id_vor)].y + sektor_height/2) - baender_thick/2);
					}
					rect.setAttributeNS(null, 'width', j_sektoren.inhalt[getSekByID(id_nach)].x - (j_sektoren.inhalt[getSekByID(id_vor)].x + sektor_width));
					rect.setAttributeNS(null, 'height', baender_thick);
					break;
				case 180: //"Ausgang" des Sektors nach rechts
					//Reihe (innen/mitte/aussen) des TB bestimmen und plazieren
					if (j_baender.inhalt[i].reihe == -1) {
						rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(id_vor)].x);
					}
					else if (j_baender.inhalt[i].reihe == 1) {
						rect.setAttributeNS(null, 'x', (j_sektoren.inhalt[getSekByID(id_vor)].x + sektor_width) - baender_thick);
					}
					else {
						rect.setAttributeNS(null, 'x', (j_sektoren.inhalt[getSekByID(id_vor)].x + sektor_width/2) - baender_thick/2);
					}
					rect.setAttributeNS(null, 'y', (j_sektoren.inhalt[getSekByID(id_vor)].y + sektor_height));
					rect.setAttributeNS(null, 'width', baender_thick);
					rect.setAttributeNS(null, 'height', j_sektoren.inhalt[getSekByID(id_nach)].y - (j_sektoren.inhalt[getSekByID(id_vor)].y + sektor_height));
					break;
				case 270: //"Ausgang" des Sektors nach rechts
					//Reihe (innen/mitte/aussen) des TB bestimmen und plazieren
					rect.setAttributeNS(null, 'x', (j_sektoren.inhalt[getSekByID(id_nach)].x + sektor_width));
					if (j_baender.inhalt[i].reihe == -1) {
						rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(id_vor)].y);
					}
					else if (j_baender.inhalt[i].reihe == 1) {
						rect.setAttributeNS(null, 'y', (j_sektoren.inhalt[getSekByID(id_vor)].y + sektor_height) - baender_thick);
					}
					else {
						rect.setAttributeNS(null, 'y', (j_sektoren.inhalt[getSekByID(id_vor)].y + sektor_height/2) - baender_thick/2);
					}
					rect.setAttributeNS(null, 'width', j_sektoren.inhalt[getSekByID(id_vor)].x - (j_sektoren.inhalt[getSekByID(id_nach)].x + sektor_width));
					rect.setAttributeNS(null, 'height', baender_thick);
					break;
				case 0: //"Ausgang" des Sektors nach rechts
					//Reihe (innen/mitte/aussen) des TB bestimmen und plazieren
					if (j_baender.inhalt[i].reihe == -1) {
						rect.setAttributeNS(null, 'x', (j_sektoren.inhalt[getSekByID(id_vor)].x + sektor_width) - baender_thick);
						rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(id_vor)].x);
					}
					else if (j_baender.inhalt[i].reihe == 1) {
						rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(id_vor)].x);
					}
					else {
						rect.setAttributeNS(null, 'x', (j_sektoren.inhalt[getSekByID(id_vor)].x + sektor_width/2) - baender_thick/2);
					}
					rect.setAttributeNS(null, 'y', (j_sektoren.inhalt[getSekByID(id_nach)].y + sektor_height));
					rect.setAttributeNS(null, 'width', baender_thick);
					rect.setAttributeNS(null, 'height', j_sektoren.inhalt[getSekByID(id_vor)].y - (j_sektoren.inhalt[getSekByID(id_nach)].y + sektor_height));
					break;
				default:
					console.log("Sektor " + j_sektoren.inhalt[getSekByID(id_vor)].id + " falsche Ausrichtung.");
			}
			document.getElementById('svgOne').appendChild(rect);
		}
	}

function warentraegerErstellen(j_sektoren, j_baender, j_warentraeger){
		for (var i = 0;i<j_warentraeger.inhalt.length;i++) {
                        //console.log(document.getElementById("svgOne").getElementById("Warentraeger" + j_warentraeger.inhalt[i].id));
			var rect = document.createElementNS(svgns, 'rect');
                        //rect = document.getElementById("svgOne").getElementById("Warentraeger" + j_warentraeger.inhalt[i].id);
			rect.setAttributeNS(null, 'id', 'Warentraeger' + j_warentraeger.inhalt[i].id);
			if ((j_warentraeger.inhalt[i].transportbandIDs.length===1) && (j_warentraeger.inhalt[i].sektorIDs.length===0)) {
			//WT liegt auf einem TB und in keinem Sektor
				switch(j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].ausrichtung) {
				//ausrichtung des Sektors finden, welcher vor dem TB liegt
					case 90: //"Ausgang" des Sektors nach rechts
						//Reihe (innen/mitte/aussen) des TB bestimmen, WT plazieren
						if (j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].reihe == -1) {
							rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].y+sektor_height-WT_height/2-baender_thick/2);
						}
						else if (j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].reihe == 1) {
							rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].y-WT_height/2+baender_thick/2);
						}
						else {
							rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].y+sektor_height/2-WT_height/2);
						}
						rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].x+sektor_width+j_warentraeger.inhalt[i].abstand_mm);	
						break;
					case 180: //"Ausgang" des Sektors nach unten
						//Reihe (innen/mitte/aussen) des TB bestimmen, WT plazieren
						if (j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].reihe == -1) {
							rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].x-WT_width/2+baender_thick/2);
						}
						else if (j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].reihe == 1) {
							rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].x+sektor_width-WT_width/2-baender_thick/2);
						}
						else {
							rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].x+sektor_width/2-WT_width/2);
						}
						rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].y+sektor_height+j_warentraeger.inhalt[i].abstand_mm);
						break;
					case 270: //"Ausgang" des Sektors nach links
						//Reihe (innen/mitte/aussen) des TB bestimmen, WT plazieren
						if (j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].reihe == -1) {
							rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].y-WT_height/2+baender_thick/2);
						}
						else if (j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].reihe == 1) {
							rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].y+sektor_height-WT_height/2-baender_thick/2);
						}
						else {
							rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].y+sektor_height/2-WT_height/2);
						}
						rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].x-WT_width-j_warentraeger.inhalt[i].abstand_mm);
						break;
					case 0: //"Ausgang" des Sektors nach oben
						//Reihe (innen/mitte/aussen) des TB bestimmen, WT plazieren
						if (j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].reihe == -1) {
							rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].x+sektor_width-WT_width/2+baender_thick/2);
						}
						else if (j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].reihe == 1) {
							rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].x-WT_width/2-baender_thick/2);
						}
						else {
							rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].x+sektor_width/2-WT_width/2);
						}
						rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_baender.inhalt[getTBByID(j_warentraeger.inhalt[i].transportbandIDs[0])].vorSektorID)].y-WT_height-j_warentraeger.inhalt[i].abstand_mm);
						break;
					default:
					console.log("Sektor falsche ausrichtung.");
				}
			}
			
			else if ((j_warentraeger.inhalt[i].transportbandIDs.length==0) && (j_warentraeger.inhalt[i].sektorIDs.length==1)) {
			//WT liegt in einem Sektor und auf keinem TB
				rect = document.createElementNS(svgns, 'rect');
				rect.setAttributeNS(null, 'id', 'Warentraeger' + j_warentraeger.inhalt[i].id);
				rect.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_warentraeger.inhalt[i].sektorIDs[0])].x+sektor_width/2-WT_width/2);
				rect.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_warentraeger.inhalt[i].sektorIDs[0])].y+sektor_height/2-WT_height/2);
				
				var count = coutWTinSek(j_warentraeger.inhalt[i].sektorIDs[0]);
				if (count>1) {
					var rect2 = document.createElementNS(svgns, 'rect');
					rect2.setAttributeNS(null, 'x', j_sektoren.inhalt[getSekByID(j_warentraeger.inhalt[i].sektorIDs[0])].x+sektor_width/2.5);
					rect2.setAttributeNS(null, 'y', j_sektoren.inhalt[getSekByID(j_warentraeger.inhalt[i].sektorIDs[0])].y+sektor_height/2.5);
					rect2.setAttributeNS(null, 'height', WT_height);
					rect2.setAttributeNS(null, 'width', WT_width);
					//if (j_warentraeger.inhalt[i].stoerung != 0)
						//rect2.setAttributeNS(null, 'fill', stoerung_fill);
					//else 
						rect2.setAttributeNS(null, 'fill', WT_fill);
					rect2.setAttributeNS(null, 'stroke-width', '5');
					rect2.setAttributeNS(null, 'stroke', 'black');
					//rect2.setAttributeNS(null, 'onclick', 'idAusgeben(this)'); //Beim anklicken wird die Id des Elementes ausgeben
					document.getElementById('svgOne').appendChild(rect2);
				}
			}
			else {
			//WT liegt auf einem TB und in einem Sektor -- Fehler
				console.log("WT" + j_warentraeger.inhalt[i].id + " hat falsche Zuordnung.");
			}
			rect.setAttributeNS(null, 'height', WT_height);
			rect.setAttributeNS(null, 'width', WT_width);
			rect.setAttributeNS(null, 'stroke-width', '7');
			rect.setAttributeNS(null, 'stroke', 'black');
			if (j_warentraeger.inhalt[i].stoerung != 0) //Farbe bestimmen bzgl. stoerung
				rect.setAttributeNS(null, 'fill', stoerung_fill);
			else 
				rect.setAttributeNS(null, 'fill', WT_fill);
			rect.setAttributeNS(null, 'onclick', 'idAusgeben(this)'); //Beim anklicken wird die Id des Elementes ausgeben
			document.getElementById('svgOne').appendChild(rect);
			
		}
	}
        
        