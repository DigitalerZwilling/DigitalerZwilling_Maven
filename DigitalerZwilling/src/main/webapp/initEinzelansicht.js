var divName = "einzelansicht";

function initEinzelansicht(documentNr){
        var elementId = localStorage.getItem('elementId_'+documentNr);
        var elementType = localStorage.getItem('elementType_'+documentNr);
    
        console.log("   -> lade Einzelansicht in Fenster " + documentNr + ": " + elementType + " " + elementId); //Ausgabe für Anwendertest
   
        var div = document.getElementById(divName+documentNr);
        
        while(div.childNodes.length >0){
            div.removeChild(div.childNodes[0]);
        }
        if(documentNr==7){
            var headlineTyp = document.createTextNode(elementType);
            var paragraph = document.createElement("p");
            paragraph.setAttribute('class',"category");
            paragraph.appendChild(headlineTyp);
            div.appendChild(paragraph);
        }
        
        var table = document.createElement("table");
        table.setAttribute('class', "table table-striped table-einzelansicht");
        
        
        table.appendChild(document.createElement("thead"));
        var tbody = document.createElement("tbody");
        tbody.id=documentNr+"_attributes";
        table.appendChild(tbody);
        div.appendChild(table);
        
        var divListe = document.createElement("div");
        divListe.id = documentNr+"_lists";
        div.appendChild(divListe);
    
    
        closeWebsockets(documentNr);

        
        switch (elementType) {
            case 'Artikel':
                initArtikel(documentNr, elementId);
                break;
            case "Warenträger":
                initWarentraeger(documentNr, elementId);
                break;
            case "Transportbänder":
                initTransportband(documentNr, elementId);
                break;
            case "Roboter":
                initRoboter(documentNr, elementId);
                break;
            case "Sektoren":
                initSektor(documentNr, elementId);
                break;
            case "Sensoren":
                initSensor(documentNr, elementId);
                break;
            case "Gelenke":
                initGelenk(documentNr, elementId);
                break;
            case "Werkzeuge":
                initWerkzeug(documentNr, elementId);
                break;
            case "Hubpositionierstationen":
                initHuPo(documentNr, elementId);
                break;
            case "Hub-Quer-Stationen":
                initHuQu(documentNr, elementId);
                break;
            default:
             console.log("         Einzelansicht in Fenster "+documentNr+": DEFAULT");
      }
    }
    
    function initArtikel(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Warenträger' ];
        var list_id    = ['warentraeger'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel']];
        var list_attribute = [['bezeichnung', 'zeitstempel']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var ArtikelWebSocket = new WebSocket("ws://"+location.host+"/"+host+"ArtikelWebSocket");
        var WarentraegerWebSocket = new WebSocket("ws://"+location.host+"/"+host+"WarentraegerWebSocket");
        addWebsockets(documentNr,[ArtikelWebSocket, WarentraegerWebSocket]);

        ArtikelWebSocket.onopen = function() {
                ArtikelWebSocket.send(id);
            };

        WarentraegerWebSocket.onopen = function() {
                WarentraegerWebSocket.send("LIST");
            };

        ArtikelWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToArtikel(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id, 1);
        };

        WarentraegerWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"artikelIDs","Warenträger");
        };
    }
    
    function initSensor(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Störung', 'Zustand', 'phy. Adresse', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'stoerung', 'zustand', 'phy_adresse', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Sektor' ];
        var list_id    = ['sektoren'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel', 'Störung']];
        var list_attribute = [['bezeichnung', 'zeitstempel', 'stoerung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);
        createAttributLink(documentNr,['Sektor'],['sektor']);

        //WebSockets
        var SensorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SensorWebSocket");
        var SektorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SektorWebSocket");
        addWebsockets(documentNr,[SensorWebSocket, SektorWebSocket]);
        
        SensorWebSocket.onopen = function() {
                SensorWebSocket.send(id);
            };

        SektorWebSocket.onopen = function() {
                SektorWebSocket.send("LIST");
            };

        SensorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToSensor(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id, 1);
        };

        SektorWebSocket.onmessage = function(event) {
            var json = JSON.parse(event.data);
            for(var i=0; i<json.inhalt.length; i++){
            var ids = json.inhalt[i]['sensorIDs'];
                    for(var j=0; j<ids.length; j++){
                    if(ids[j]==id){
                        updateAttributLink(documentNr,['sektor'],json.inhalt[i],[json.inhalt[i]['bezeichnung']],['sektor'],['Sektoren']);
                    }
                }
            }
        };
    }
    
    function initWarentraeger(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Zustand', 'Störung', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'zustand', 'stoerung', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Artikel', 'Transportband', 'Sektor'];
        var list_id    = ['artikel', 'transportband', 'sektoren'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel'],['Bezeichnung', 'Zeitstempel','Störung'],['Bezeichnung', 'Zeitstempel','Störung']];
        var list_attribute = [['bezeichnung', 'zeitstempel'],['bezeichnung', 'zeitstempel','stoerung'],['bezeichnung', 'zeitstempel','stoerung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var WarentreagerWebSocket = new WebSocket("ws://"+location.host+"/"+host+"WarentraegerWebSocket");
        var ArtikelWebSocket = new WebSocket("ws://"+location.host+"/"+host+"ArtikelWebSocket");
        var TransportbandWebSocket = new WebSocket("ws://"+location.host+"/"+host+"TransportbandWebSocket");
        var SektorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SektorWebSocket");
        addWebsockets(documentNr,[WarentreagerWebSocket, ArtikelWebSocket, TransportbandWebSocket, SektorWebSocket]);
        
        WarentreagerWebSocket.onopen = function() {
                WarentreagerWebSocket.send(id);
            };

        ArtikelWebSocket.onopen = function() {
                ArtikelWebSocket.send("LIST");
            };
            
        TransportbandWebSocket.onopen = function() {
                TransportbandWebSocket.send("LIST");
            };
            
        SektorWebSocket.onopen = function() {
                SektorWebSocket.send("LIST");
            };

        WarentreagerWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToWarentraeger(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id,1);
        };

        ArtikelWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"warentraegerIDs","Artikel");
        };
        
        TransportbandWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[1], jsonString, list_attribute[1],"warentraegerIDs","Transportbänder");
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[2], jsonString, list_attribute[2],"warentraegerIDs","Sektoren");
        };
    }
    
    function initTransportband(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Störung', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'stoerung', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Warenträger'];
        var list_id    = ['warentraeger'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel']];
        var list_attribute = [['bezeichnung', 'zeitstempel']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);
        createAttributLink(documentNr, ['Sektor vorher','Sektor nachher'],['vorTransportband','nachTransportband']);

        //WebSockets
        var TransportbandWebSocket = new WebSocket("ws://"+location.host+"/"+host+"TransportbandWebSocket");
        var WarentreagerWebSocket = new WebSocket("ws://"+location.host+"/"+host+"WarentraegerWebSocket");
        var SektorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SektorWebSocket"); 
        addWebsockets(documentNr,[TransportbandWebSocket, WarentreagerWebSocket, SektorWebSocket]);
        
        TransportbandWebSocket.onopen = function() {
                TransportbandWebSocket.send(id);
            };
            
        WarentreagerWebSocket.onopen = function() {
                WarentreagerWebSocket.send("LIST");
            };
            
        SektorWebSocket.onopen = function() {
                SektorWebSocket.send("LIST");
            };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var json = JSON.parse(jsonString);
            
            for(var i=0; i<json.inhalt.length; i++){
            var vid = json.inhalt[i]['vorTransportbandIDs'];
            var nid = json.inhalt[i]['nachTransportbandIDs'];
                for(var j=0; j<vid.length; j++){
                    if(vid[j]==id){
                        var attribute_value = jsonToSektor(json.inhalt[i]);
                        updateAttributLink(documentNr, ['bezeichnung'],json.inhalt[i],attribute_value,['vorTransportband'],'Sektoren');
                    }
                }
                for(var j=0; j<vid.length; j++){
                    if(nid[j]==id){
                        var attribute_value = jsonToSektor(json.inhalt[i]);
                        updateAttributLink(documentNr, ['bezeichnung'],json.inhalt[i],attribute_value,['nachTransportband'],'Sektoren');
                    }
                }
                
            }
        };

        TransportbandWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToTransportband(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id, 1);
        };

        WarentreagerWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"transportbandIDs","Warenträger");
        };
    }
    
    function initRoboter(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Störung', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'stoerung', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Sektoren','Gelenke'];
        var list_id    = ['sektoren','gelenke'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel','Störung'],['Bezeichnung', 'Zeitstempel','Gelenkstellung']];
        var list_attribute = [['bezeichnung', 'zeitstempel','stoerung'],['bezeichnung', 'zeitstempel','gelenkstellung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);
        createAttributLink(documentNr, ['Werkzeug'],['werkzeug']);

        //WebSockets
        var RoboterWebSocket = new WebSocket("ws://"+location.host+"/"+host+"RoboterWebSocket");
        var SektorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SektorWebSocket");
        var GelenkWebSocket = new WebSocket("ws://"+location.host+"/"+host+"GelenkWebSocket");
        var WerkzeugWebSocket = new WebSocket("ws://"+location.host+"/"+host+"WerkzeugWebSocket");
        addWebsockets(documentNr,[RoboterWebSocket, SektorWebSocket, GelenkWebSocket, WerkzeugWebSocket]);
        
        RoboterWebSocket.onopen = function() {
                RoboterWebSocket.send(id);
            };
            
        SektorWebSocket.onopen = function() {
                SektorWebSocket.send("LIST");
            };
            
        GelenkWebSocket.onopen = function() {
                GelenkWebSocket.send("LIST");
            };
            
        WerkzeugWebSocket.onopen = function() {
                WerkzeugWebSocket.send("LIST");
            };

        RoboterWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToRoboter(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id, 1);
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"roboterIDs","Sektoren");
        };


        GelenkWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[1], jsonString, list_attribute[1],"roboterID","Gelenke");
        };

        WerkzeugWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var json = JSON.parse(jsonString);
            
            for(var i=0; i<json.inhalt.length; i++){
            var wid = json.inhalt[i]['roboterID'];
                if(wid==id){
                        var attribute_value = jsonToWerkzeug(json.inhalt[i]);
                        updateAttributLink(documentNr, ['bezeichnung'],json.inhalt[i], attribute_value,['werkzeug'],'Werkzeuge');
                }
            }
        };
    }
    
    function initSektor(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Störung', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'stoerung', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Warenträger','Hubpositionierstation','Hub-Quer-Stationen','Roboter','Sensoren','Transportband vorher','Tansportband nachher'];
        var list_id    = ['warentraeger','hubpodest','hubquerpodest','roboter','sensor','nachTransportband','vorTransportband'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel'], 
                               ['Bezeichnung', 'Zeitstempel','Oben'],
                               ['Bezeichnung', 'Zeitstempel','Oben'],
                               ['Bezeichnung', 'Zeitstempel','Störung'],
                               ['Bezeichnung', 'Zeitstempel','Zustand'],
                               ['Bezeichnung', 'Zeitstempel','Störung'],
                               ['Bezeichnung', 'Zeitstempel','Störung']
                              ];
        var list_attribute = [['bezeichnung', 'zeitstempel'], 
                               ['bezeichnung', 'zeitstempel','oben'],
                               ['bezeichnung', 'zeitstempel','oben'],
                               ['bezeichnung', 'zeitstempel','stoerung'],
                               ['bezeichnung', 'zeitstempel','zustand'],
                               ['bezeichnung', 'zeitstempel','stoerung'],
                               ['bezeichnung', 'zeitstempel','stoerung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var SektorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SektorWebSocket");
        
        var WarentraegerWebSocket = new WebSocket("ws://"+location.host+"/"+host+"WarentraegerWebSocket");
        var HubpodestWebSocket = new WebSocket("ws://"+location.host+"/"+host+"HubPodestWebSocket");
        var HubquerpodestWebSocket = new WebSocket("ws://"+location.host+"/"+host+"HubQuerPodestWebSocket");
        var RoboterWebSocket = new WebSocket("ws://"+location.host+"/"+host+"RoboterWebSocket");
        var SensorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SensorWebSocket");
        var TransportbandWebSocket = new WebSocket("ws://"+location.host+"/"+host+"TransportbandWebSocket");
        
        addWebsockets(documentNr,[SektorWebSocket, WarentraegerWebSocket, HubpodestWebSocket, HubquerpodestWebSocket, RoboterWebSocket, SensorWebSocket, TransportbandWebSocket]);
        
        SektorWebSocket.onopen = function() {
                SektorWebSocket.send(id);
            };
            
        WarentraegerWebSocket.onopen = function() {
                WarentraegerWebSocket.send("LIST");
            };
            
        HubpodestWebSocket.onopen = function() {
                HubpodestWebSocket.send("LIST");
            };
            
        HubquerpodestWebSocket.onopen = function() {
                HubquerpodestWebSocket.send("LIST");
            };
            
        RoboterWebSocket.onopen = function() {
                RoboterWebSocket.send("LIST");
            };
            
        SensorWebSocket.onopen = function() {
                SensorWebSocket.send("LIST");
            };
            
        TransportbandWebSocket.onopen = function() {
                TransportbandWebSocket.send("LIST");
            };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToSektor(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id,1);
        };

        WarentraegerWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"sektorIDs","Warenträger");
        };

        HubpodestWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[1], jsonString, list_attribute[1],"sektorID","Hubpositionierstationen");
        };


        HubquerpodestWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[2], jsonString, list_attribute[2],"sektorID","Hub-Quer-Stationen");
        };

        RoboterWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[3], jsonString, list_attribute[3],"sektorIDs","Roboter");
        };

        SensorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            
            updateList(documentNr, id, list_id[4], jsonString, list_attribute[4],"sektorID","Sensoren");
        };

        TransportbandWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[5], jsonString, list_attribute[5],"vorSektorID","Transportbänder");
            updateList(documentNr, id, list_id[6], jsonString, list_attribute[6],"nachSektorID","Transportbänder");
        };
    }
    
    function initGelenk(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Gelenkstellung', 'Typ', 'Nummer', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'gelenkstellung', 'typ', 'nummer', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Roboter'];
        var list_id    = ['roboter'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel','Störung']];
        var list_attribute = [['bezeichnung', 'zeitstempel','stoerung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var GelenkWebSocket = new WebSocket("ws://"+location.host+"/"+host+"GelenkWebSocket");
        var RoboterWebSocket = new WebSocket("ws://"+location.host+"/"+host+"RoboterWebSocket");
        addWebsockets(documentNr,[GelenkWebSocket, RoboterWebSocket]);
        
        GelenkWebSocket.onopen = function() {
                GelenkWebSocket.send(id);
            };
            
        RoboterWebSocket.onopen = function() {
                RoboterWebSocket.send("LIST");
            };

        GelenkWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToGelenk(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value, attribute_id,1);
        };

        RoboterWebSocket.onmessage = function(event) {
            var jsonString = event.data;
             updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"gelenkeIDs","Gelenke");
        };
    }
    
    function initWerkzeug(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Zustand', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'zustand', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Roboter'];
        var list_id    = ['roboter'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel','Störung']];
        var list_attribute = [['bezeichnung', 'zeitstempel','stoerung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var WerkzeugWebSocket = new WebSocket("ws://"+location.host+"/"+host+"WerkzeugWebSocket");
        var RoboterWebSocket = new WebSocket("ws://"+location.host+"/"+host+"RoboterWebSocket");
        addWebsockets(documentNr,[WerkzeugWebSocket, RoboterWebSocket]);
        
        WerkzeugWebSocket.onopen = function() {
                WerkzeugWebSocket.send(id);
            };
            
        RoboterWebSocket.onopen = function() {
                RoboterWebSocket.send("LIST");
            };

        WerkzeugWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToWerkzeug(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id,1);
        };

        RoboterWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"werkzeugIDs","Roboter");
        };
    }
    
    function initHuPo(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Zustand', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'zustand', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Sektor'];
        var list_id    = ['Sektor'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel']];
        var list_attribute = [['bezeichnung', 'zeitstempel']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var HubpodestWebSocket = new WebSocket("ws://"+location.host+"/"+host+"HubPodestWebSocket");
        var SektorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SektorWebSocket");
        addWebsockets(documentNr,[HubpodestWebSocket, SektorWebSocket]);
        
        HubpodestWebSocket.onopen = function() {
                HubpodestWebSocket.send(id);
            };
            
        SektorWebSocket.onopen = function() {
                SektorWebSocket.send("LIST");
            };

        HubpodestWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToHuPo(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id,1);
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"hubpodestIDs","Sektoren");
        };
    }
    
    function initHuQu(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Zustand', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'zustand', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Sektor'];
        var list_id    = ['Sektor'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel']];
        var list_attribute = [['bezeichnung', 'zeitstempel']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var HubQuerPodestWebSocket = new WebSocket("ws://"+location.host+"/"+host+"HubQuerPodestWebSocket");
        var SektorWebSocket = new WebSocket("ws://"+location.host+"/"+host+"SektorWebSocket");
        addWebsockets(documentNr,[HubQuerPodestWebSocket, SektorWebSocket]);
        
        HubQuerPodestWebSocket.onopen = function() {
                HubQuerPodestWebSocket.send(id);
            };
            
        SektorWebSocket.onopen = function() {
                SektorWebSocket.send("LIST");
            };

        HubQuerPodestWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToHuQu(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id,1);
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"hubquerpodestIDs","Sektoren");
        };
    }
