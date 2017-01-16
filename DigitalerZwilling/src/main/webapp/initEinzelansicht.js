var host = "ws://131.173.127.174:8080/DigitalerZwilling/";
var divName = "einzelansicht";

function initEinzelansicht(documentNr){
        var div = document.getElementById(divName+documentNr);
        var childs = div.childNodes;

        for(var i=0; i<childs.length; i++){
            div.removeChild(childs[i]);
        }
        
        var table = document.createElement("table");
        table.id=documentNr+"_attributes";
        div.appendChild(table);
        
        var divListe = document.createElement("div");
        divListe.id = documentNr+"_lists";
        div.appendChild(divListe);
    
    
        closeWebsockets(documentNr);
    
        
        var elementId = localStorage.getItem('elementId_'+documentNr);
        var elementType =  localStorage.getItem('elementType_'+documentNr);
        
        switch (elementType) {
            case 'artikel':
                initArtikel(documentNr, elementId);
                break;
            case "warentraeger":
                initWarentraeger(documentNr, elementId);
                break;
            case "transportbaender":
                initTransportbaender(documentNr, elementId);
                break;
            case "roboter":
                initRoboter(documentNr, elementId);
                break;
            case "sektor":
                initSektoren(documentNr, elementId);
                break;
            case "sensor":
                initSensor(documentNr, elementId);
                break;
            case "gelenk":
                initGelenke(documentNr, elementId);
                break;
            case "werkzeug":
                initWerkzeuge(documentNr, elementId);
                break;
            case "hupo":
                initHuPo(documentNr, elementId);
                break;
            case "huqu":
                initHuQu(documentNr, elementId);
                break;
            default:
             console.log("default");
             //init(documentNr, 'artikel', 1);
      }
    }
    
    function initArtikel(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Wartenträger' ];
        var list_id    = ['wartentraeger'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel']];
        var list_attribute = [['bezeichnung', 'zeitstempel']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var ArtikelWebSocket = new WebSocket(host+"ArtikelWebSocket");
        var WarentraegerWebSocket = new WebSocket(host+"WarentraegerWebSocket");
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
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        WarentraegerWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"artikelIDs","warentraeger");
        };
    }
    
    function initSensor(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Störung', 'Zustand', 'phy. Adresse', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'stoerung', 'zustand', 'phy_adresse', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Sektoren' ];
        var list_id    = ['sektoren'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel', 'Störung']];
        var list_attribute = [['bezeichnung', 'zeitstempel', 'stoerung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var SensorWebSocket = new WebSocket(host+"SensorWebSocket");
        var SektorWebSocket = new WebSocket(host+"SektorWebSocket");
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
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"sensorIDs","sektor");
        };
    }
    
    function initWarentraeger(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Zustand', 'Störung', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'zustand', 'stoerung', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Artikel', 'Transportband', 'Sektoren'];
        var list_id    = ['artikel', 'transportband', 'sektoren'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel'],['Bezeichnung', 'Zeitstempel','Störung'],['Bezeichnung', 'Zeitstempel','Störung']];
        var list_attribute = [['bezeichnung', 'zeitstempel'],['bezeichnung', 'zeitstempel','stoerung'],['bezeichnung', 'zeitstempel','stoerung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);

        //WebSockets
        var WarentreagerWebSocket = new WebSocket(host+"WarentraegerWebSocket");
        var ArtikelWebSocket = new WebSocket(host+"ArtikelWebSocket");
        var TransportbandWebSocket = new WebSocket(host+"TransportbandWebSocket");
        var SektorWebSocket = new WebSocket(host+"SektorWebSocket");
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
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        ArtikelWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"warentraegerIDs","artikel");
        };
        
        TransportbandWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[1], jsonString, list_attribute[1],"warentraegerIDs"),"transportband";
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[2], jsonString, list_attribute[2],"warentraegerIDs","sektor");
        };
    }
    
    function initTransportbaender(documentNr, id){
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
        var TransportbandWebSocket = new WebSocket(host+"TransportbandWebSocket");
        var WarentreagerWebSocket = new WebSocket(host+"WarentraegerWebSocket");
        var SektorWebSocket = new WebSocket(host+"SektorWebSocket"); 
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
                        updateAttributLink(documentNr, ['bezeichnung'],json.inhalt[i],attribute_value,['vorTransportband'],'sektor');
                    }
                }
                for(var j=0; j<vid.length; j++){
                    if(nid[j]==id){
                        var attribute_value = jsonToSektor(json.inhalt[i]);
                        updateAttributLink(documentNr, ['bezeichnung'],json.inhalt[i],attribute_value,['nachTransportband'],'sektor');
                    }
                }
                
            }
        };

        TransportbandWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var attribute_value = jsonToTransportband(JSON.parse(jsonString));
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        WarentreagerWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"transportbandIDs","warentraeger");
        };
    }
    
    function initRoboter(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Störung', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'stoerung', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Sektoren'];
        var list_id    = ['sektoren'];

        //Eigenschaften der Tabellen
        var list_header     = [['Bezeichnung', 'Zeitstempel','Störung']];
        var list_attribute = [['bezeichnung', 'zeitstempel','stoerung']];

        //Tabelle Erstellen
        create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header);
        createAttributLink(documentNr, ['Gelenk','Werkzeug'],['gelenk','werkzeug']);

        //WebSockets
        var RoboterWebSocket = new WebSocket(host+"RoboterWebSocket");
        var SektorWebSocket = new WebSocket(host+"SektorWebSocket");
        var GelenkWebSocket = new WebSocket(host+"GelenkWebSocket");
        var WerkzeugWebSocket = new WebSocket(host+"WerkzeugWebSocket");
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
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"roboterIDs","sektor");
        };


        GelenkWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var json = JSON.parse(jsonString);
            
            for(var i=0; i<json.inhalt.length; i++){
            var rid = json.inhalt[i]['roboterID'];
                if(rid==id){
                        var attribute_value = jsonToGelenk(json.inhalt[i]);
                        updateAttributes(documentNr, ['bezeichnung'],attribute_value,['gelenk']);
                }
            }
        };

        WerkzeugWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var json = JSON.parse(jsonString);
            
            for(var i=0; i<json.inhalt.length; i++){
            var wid = json.inhalt[i]['roboterID'];
                if(wid==id){
                        var attribute_value = jsonToWerkzeug(json.inhalt[i]);
                        updateAttributes(documentNr, ['bezeichnung'],attribute_value,['werkzeug']);
                }
            }
        };
    }
    
    function initSektoren(documentNr, id){
        //Einzelne Attribute:
        var attribute_title = ['Bezeichnung', 'Zeitstempel', 'Störung', 'User Parameter'];
        var attribute_id    = ['bezeichnung', 'zeitstempel', 'stoerung', 'user_parameter'];

        //Einzelne Tabellen:
        var list_title = ['Warenträger','Hubpodest','Hubquerpodest','Roboter','Sensor','Transportband vorher','Tansportband nachher'];
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
        var SektorWebSocket = new WebSocket(host+"SektorWebSocket");
        
        var WarentraegerWebSocket = new WebSocket(host+"WarentraegerWebSocket");
        var HubpodestWebSocket = new WebSocket(host+"HubPodestWebSocket");
        var HubquerpodestWebSocket = new WebSocket(host+"HubQuerPodestWebSocket");
        var RoboterWebSocket = new WebSocket(host+"RoboterWebSocket");
        var SensorWebSocket = new WebSocket(host+"SensorWebSocket");
        var TransportbandWebSocket = new WebSocket(host+"TransportbandWebSocket");
        
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
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        WarentraegerWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"sektorIDs","warentraeger");
        };

        HubpodestWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var json = JSON.parse(jsonString);
            var parent = document.getElementById(list_id[1]);
            removeList(parent);
        
            for(var i=0; i<json.inhalt.length; i++){
            var sid = json.inhalt[i]['sektorID'];
                if(sid==id){
                    addLine(documentNr, json.inhalt[i],list_attribute[1],parent,'hupo');
                }
            }
        };


        HubquerpodestWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var json = JSON.parse(jsonString);
            var parent = document.getElementById(list_id[2]);
            removeList(parent);
        
            for(var i=0; i<json.inhalt.length; i++){
            var sid = json.inhalt[i]['sektorID'];
                if(sid==id){
                    addLine(documentNr, json.inhalt[i],list_attribute[2],parent,'huqu');
                }
            }
        };

        RoboterWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[3], jsonString, list_attribute[3],"sektorIDs","roboter");
        };

        SensorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var json = JSON.parse(jsonString);
            var parent = document.getElementById(list_id[4]);
            removeList(parent);
        
            for(var i=0; i<json.inhalt.length; i++){
            var sid = json.inhalt[i]['sektorID'];
                if(sid==id){
                    addLine(documentNr, json.inhalt[i],list_attribute[4],parent,'sensor');
                }
            }
        };

        TransportbandWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[5], jsonString, list_attribute[5],"vorSektorID","tarnsportband");
            updateList(documentNr, id, list_id[6], jsonString, list_attribute[6],"nachSektorID","transportband");
        };
    }
    
    function initGelenke(documentNr, id){
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
        var GelenkWebSocket = new WebSocket(host+"GelenkWebSocket");
        var RoboterWebSocket = new WebSocket(host+"RoboterWebSocket");
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
            updateAttributes(documentNr, attribute_id,attribute_value, attribute_id);
        };

        RoboterWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            var json = JSON.parse(jsonString);
            var parent = document.getElementById(list_id[0]);
            removeList(parent);
        
            for(var i=0; i<json.inhalt.length; i++){
            var sid = json.inhalt[i]['gelenkID'];
                if(sid==id){
                    addLine(documentNr, json.inhalt[i],list_attribute[0],parent);
                }
            }
        };
    }
    
    function initWerkzeuge(documentNr, id){
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
        var WerkzeugWebSocket = new WebSocket(host+"WerkzeugWebSocket");
        var RoboterWebSocket = new WebSocket(host+"RoboterWebSocket");
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
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        RoboterWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"werkzeugIDs","roboter");
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
        var HubpodestWebSocket = new WebSocket(host+"HubPodestWebSocket");
        var SektorWebSocket = new WebSocket(host+"SektorWebSocket");
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
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"hubpodestIDs","sektor");
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
        var HubQuerPodestWebSocket = new WebSocket(host+"HubQuerPodestWebSocket");
        var SektorWebSocket = new WebSocket(host+"SektorWebSocket");
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
            updateAttributes(documentNr, attribute_id,attribute_value,attribute_id);
        };

        SektorWebSocket.onmessage = function(event) {
            var jsonString = event.data;
            updateList(documentNr, id, list_id[0], jsonString, list_attribute[0],"hubquerpodestIDs","sektor");
        };
    }
