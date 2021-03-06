    function createAttributes(documentNr, attribute, id){
        var attributes = document.getElementById(documentNr+"_attributes");
        
        
        for(var i=0; i<attribute.length; i++){
            var zeile = document.createElement("tr");
            var name = document.createElement("td");
            name.setAttribute('class',"table-row-name");
            name.innerHTML = attribute[i];
                        
            zeile.appendChild(name);

            var value = document.createElement("td");
            value.id = documentNr+"_"+id[i];
            zeile.appendChild(value);

            attributes.appendChild(zeile);
        }
    }
    
    
    function createAttributLink(documentNr, attribute, id){
        var attributes = document.getElementById(documentNr+"_attributes");
        
        
        for(var i=0; i<attribute.length; i++){
            var zeile = document.createElement("tr");
            var name = document.createElement("td");
            name.setAttribute('class',"table-row-name");
            name.innerHTML = attribute[i];
            zeile.appendChild(name);

            var value = document.createElement("td");
            var link = document.createElement("a");
            link.id = documentNr+"_"+id[i];
            value.appendChild(link);
            zeile.appendChild(value);

            attributes.appendChild(zeile);
                  

            link.onclick = function() {  
                var elementId = $(this).attr("elementId");
                var elementTyp = $(this).attr("elementType");
                console.log("<> Fenster "+documentNr+": "+elementTyp+" "+elementId);
                
                addZurueckList(documentNr, elementId, elementTyp);
                
                $(".dd-btn"+documentNr).html(elementTyp + ' <span class = "caret"></span');
                  
                closeWebsockets(documentNr);
                localStorage.setItem("elementId_"+documentNr,elementId);
                localStorage.setItem("elementType_"+documentNr,elementTyp);

                        var div = document.getElementById(divName+documentNr);
                        var childs = div.childNodes;

                        for(var i=0; i<childs.length; i++){
                            div.removeChild(childs[i]);
                        }

                initEinzelansicht(documentNr);
            }
        }
    }
        
    function createLists(documentNr, list_tilte, list_id, list_header){
        if(list_tilte.length !== list_id.length) return;
        
        var lists = document.getElementById(documentNr+"_lists");
        
        for(var i=0; i<list_tilte.length; i++){
            var div = document.createElement("div");
            div.id = documentNr+"_div_"+list_id[i];
            div.setAttribute("hidden","");

            
            var paragraph = document.createElement("p");
            paragraph.setAttribute('class',"table-headline");
            var name = document.createTextNode(list_tilte[i]);
            paragraph.appendChild(name);
            
            div.appendChild(paragraph);
            var table = document.createElement("table");
            var tbody = document.createElement("tbody");
            tbody.id = documentNr+"_"+list_id[i];          
            table.setAttribute('class', "table table-striped table-tabellenansicht");
            
            setHeader(list_header[i], table);
            
            table.appendChild(tbody);
            div.appendChild(table);
            //div.appendChild(document.createElement("br"));
            lists.appendChild(div);
        }
        
    }
    
    function removeList(parent){
            var childs = parent.childNodes;
            
            while(childs.length>0){
                parent.removeChild(childs[0]);
            }
    }
    
    function setHeader(headerlist, parentNode){
            var thead = document.createElement("thead");
            var tr = document.createElement("tr");
            for(var i=0; i<headerlist.length; i++){
                var th = document.createElement("th");
                th.innerHTML = headerlist[i];
                tr.appendChild(th);
            }
            thead.appendChild(tr)
            parentNode.appendChild(thead);
    }
    
    function addLine(documentNr, jsonObject, attributes, parentNode, type){
            var zeile = document.createElement("tr");
            for(var k=0; k<attributes.length; k++){
                var element = document.createElement("td");
                if(attributes[k]=="bezeichnung"){
                    var link = document.createElement("a");
                    link.innerHTML = jsonObject[attributes[k]];
                    link.setAttribute("elementId",jsonObject['id']);
                    link.setAttribute("elementType",type);
                    link.onclick = function() {
                        var elementId = $(this).attr("elementId");
                        var elementTyp = $(this).attr("elementType");
                        console.log("<> Fenster "+documentNr+": "+elementTyp+" "+elementId);
                        
                        addZurueckList(documentNr, elementId, elementTyp);
                        
                        $(".dd-btn"+documentNr).html(elementTyp + ' <span class = "caret"></span');
                        
                        closeWebsockets(documentNr);
                        localStorage.setItem("elementId_"+documentNr,elementId);
                        localStorage.setItem("elementType_"+documentNr,elementTyp);
                        
                        var div = document.getElementById(divName+documentNr);
                        var childs = div.childNodes;
                        
                        for(var i=0; i<childs.length; i++){
                            div.removeChild(childs[i]);
                        }
                        
                        initEinzelansicht(documentNr);
                    };
                    
                    element.appendChild(link);
                }else{
                    element.innerHTML = jsonObject[attributes[k]];
                }
                zeile.appendChild(element);
            }
            parentNode.appendChild(zeile);
    }
    
   
    
    function updateAttributes(documentNr, attribute_id, attribute_value, element_id, number){
        for(var i=0; i<attribute_id.length; i++){
            //console.log("i: " + i + "attribute_id: " + attribute_id + ", attribute_value: "+ attribute_value);
            var element = document.getElementById(documentNr+"_"+element_id[i]);
            if(i==0 && number==1){
                element.setAttribute('class',"headliner");
            }
            element.innerHTML = attribute_value[i]; //json[attribute_id[i]];
        }
    }
    
    function updateAttributLink(documentNr, attribute_id, json, attribute_value, element_id, type){
        for(var i=0; i<attribute_id.length; i++){
            var element = document.getElementById(documentNr+"_"+element_id[i]);
            element.innerHTML = attribute_value[i]; //json[attribute_id[i]];
            element.setAttribute("elementId",json['id']);
            element.setAttribute("elementType",type);
        }
    }
    
    function updateList(documentNr, id, list_id, jsonString, childAttributes, objectIDs, type){        
        var json = JSON.parse(jsonString);
        var parent = document.getElementById(documentNr+"_"+list_id);
        removeList(parent);
        
        for(var i=0; i<json.inhalt.length; i++){
            var ids = json.inhalt[i][objectIDs];
            if (ids.length === undefined){
                if(ids==id){
                    addLine(documentNr, json.inhalt[i],childAttributes,parent, type);
                }
            }else{
                 for(var j=0; j<ids.length; j++){
                     //console.log(ids[j]);
                    if(ids[j]==id){
                        //console.log("ok");
                        addLine(documentNr, json.inhalt[i],childAttributes,parent, type);
                    }
                }
            }
           
        }
        var div = document.getElementById(documentNr+"_div_"+list_id);
        if(parent.childNodes.length<1){
            div.setAttribute("hidden","");
        }else{
            div.removeAttribute("hidden");
        }
    }
    
    function create(documentNr, attribute_title, attribute_id, list_title, list_id, list_header){
        createAttributes(documentNr, attribute_title,attribute_id);
        createLists(documentNr, list_title, list_id, list_header);
    }
    
    function jsonToArtikel(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        
        return [bezeichnung, zeitstempel];
    }
    
    function jsonToWarentraeger(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        var montagezustand = jsonObject.montagezustand;
        var rfid = "";//jsonObject.rfid;
        var stoerung = jsonObject.stoerung;
        var userParameter = jsonObject.user_Parameter;
        
        var zustand = montagezustand + "% - " + rfid;
        
        return [bezeichnung, zeitstempel, zustand, stoerung, userParameter];
    }
    
    function jsonToTransportband(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        var stoerung = jsonObject.stoerung;
        var userParameter = jsonObject.user_Parameter;
        
        return [bezeichnung, zeitstempel, stoerung, userParameter];
    }
    
    function jsonToRoboter(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        var stoerung = jsonObject.stoerung;
        var userParameter = jsonObject.user_Parameter;
        
        return [bezeichnung, zeitstempel, stoerung, userParameter];
    }
    
    function jsonToSektor(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        var stoerung = jsonObject.stoerung;
        var userParameter = jsonObject.user_Parameter;
        
        return [bezeichnung, zeitstempel, stoerung, userParameter];
    }
    
    function jsonToSensor(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        var stoerung = jsonObject.stoerung;
        var zustand = jsonObject.zustand;
        var phyAdrr = jsonObject.phy_adresse;
        var userParameter = jsonObject.user_Parameter;
        
        return [bezeichnung, zeitstempel, stoerung, zustand, phyAdrr, userParameter];
    }
    
    function jsonToGelenk(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        var gelenkstellung = jsonObject.gelenkstellung;
        var typ = jsonObject.typ;
        var nr = jsonObject.nummer;
        var userParameter = jsonObject.user_Parameter;
        
        return [bezeichnung, zeitstempel, gelenkstellung, typ, nr, userParameter];
    }
    
    function jsonToWerkzeug(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        var zustand = jsonObject.zustand;
        var userParameter = jsonObject.user_Parameter;
        
        return [bezeichnung, zeitstempel, zustand, userParameter];
    }
    
    function jsonToHuPo(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        
        var oben = jsonObject.oben;
        var unten = jsonObject.unten;
        var userParameter = jsonObject.user_Parameter;
        var zustand="X";
        
        if(oben=="1" && unten=="0"){
            zustand = "&uarr;";
        }else if(oben=="0" && unten=="1"){
            zustand = "&darr;";
        }
        
        return [bezeichnung, zeitstempel, zustand, userParameter];
    }
    
    function jsonToHuQu(jsonObject){
        var bezeichnung = jsonObject.bezeichnung;
        var zeitstempel = jsonObject.zeitstempel;
        var oben = jsonObject.oben;
        var mittig = jsonObject.mittig;
        var unten = jsonObject.unten;
        var motor = jsonObject.motor;
        var userParameter = jsonObject.user_Parameter;
        var zustand="X";
        
        if(oben=="1" && mittig=="0"&& unten=="0"){
            zustand = "\u2191";
        }else if(oben=="0" && mittig=="1" && unten=="0"){
            zustand = "\u2194";
        }else if(oben=="0" && mittig=="0" && unten=="1"){
            zustand = "\u2193";
        }
        
        if(motor == "1"){
            zustand = "ein - " + zustand;
        }else{
            zustand = "aus - " + zustand;
        }
        
        return [bezeichnung, zeitstempel, zustand, userParameter];
    }
    
    function closeWebsockets(documentId){
        var websocketList = [];
        switch (documentId) {
           case 1: websocketList = websocketList_1; break;
           case 2: websocketList = websocketList_2; break;
           case 3: websocketList = websocketList_3; break;
           case 4: websocketList = websocketList_4; break;
           case 5: websocketList = websocketList_5; break;
           case 6: websocketList = websocketList_6; break;
           case 7: websocketList = websocketList_7; break;
           case 8:  websocketList = websocketList_8; break;
        }
        
        while(websocketList.length>0){
            websocketList[websocketList.length-1].close();
            websocketList.pop();
        }
    }
    
    function addWebsockets(documentId, array){
        var websocketList = [];
        switch (documentId) {
           case 1: websocketList = websocketList_1; break;
           case 2: websocketList = websocketList_2; break;
           case 3: websocketList = websocketList_3; break;
           case 4: websocketList = websocketList_4; break;
           case 5: websocketList = websocketList_5; break;
           case 6: websocketList = websocketList_6; break;
           case 7: websocketList = websocketList_7; break;
           case 8: websocketList = websocketList_8; break;
        }
        
        for(var i=0; i< array.length; i++){
            websocketList.push(array[i]);
        }
        
    }
    
    function addZurueckList(documentId, elementId, elementTyp){
        var zurueckList = [];
        var backButton;
        switch (documentId) {
           case 1: zurueckList = zurueckList_1; backButton = backButtons[0]; break;
           case 2: zurueckList = zurueckList_2; backButton = backButtons[1]; break;
           case 3: zurueckList = zurueckList_3; backButton = backButtons[2]; break;
           case 4: zurueckList = zurueckList_4; backButton = backButtons[3]; break;
           case 5: zurueckList = zurueckList_5; backButton = backButtons[4]; break;
           case 6: zurueckList = zurueckList_6; backButton = backButtons[5]; break;
           case 7: zurueckList = zurueckList_7; backButton = backButtons[7]; break;

        }
        zurueckList.push([elementTyp,elementId]);
        if(zurueckList.length>=2){
            if(backButton==backButtons[5]){
                $(backButton).show();
                backButton=backButtons[6];
                $(backButton).show();
            }else{
                $(backButton).show();
            }
        }
    }

    function historyCheck(ansicht){
        if(ansicht==="details"){
            var i = 0;
            for(i=0;i<6;i++){
                if(zurueckList_all[i].length<=1){
                    $(backButtons[i]).hide();
                }
            }
        }else{
            if(zurueckList_all[5].length<=1){
                $(backButtons[6]).hide();
            }
            if (zurueckList_all[6].length<=1){
                $(backButtons[7]).hide();
            }
        }
    }
    
    function historyCheckDiv(ansicht, nummer){
        var backButton;
        if(ansicht="details"){
            switch (nummer){
                case 1: backButton = backButtons[0]; break;
                case 2: backButton = backButtons[1]; break;
                case 3: backButton = backButtons[2]; break;
                case 4: backButton = backButtons[3]; break;
                case 5: backButton = backButtons[4]; break;
                case 6: backButton = backButtons[5]; break;           
            }  
        }
        if(ansicht="uebersicht"){
            switch (nummer){
                case 6: backButton = backButtons[6]; break;
                case 7: backButton = backButtons[7]; break;           
            }  
        }
        $(backButton).hide();
    }
    
    function loadHistory(nummer, ansicht){
        var nrAusgabe = nummer+1;
        var lastElement = zurueckList_all[nummer].length-1;

        localStorage.setItem("elementType_"+nrAusgabe, zurueckList_all[(nummer)][lastElement-1][0]);
        localStorage.setItem("elementId_"+nrAusgabe, zurueckList_all[(nummer)][lastElement-1][1]);
        zurueckList_all[nummer].pop();

        if(zurueckList_all[5].length==1 && ansicht==="uebersicht"){
            $(backButtons[6]).hide();         
        }else if(zurueckList_all[5].length==1 && ansicht==="details"){
            $(backButtons[5]).hide();    
        }
        if(zurueckList_all[6].length==1 && ansicht==="uebersicht"){
            $(backButtons[7]).hide();         
        }
        if(zurueckList_all[nummer].length==1){
            $(backButtons[nummer]).hide();            
        }       
            $(".dd-btn"+nrAusgabe).html(localStorage.getItem("elementType_"+ nrAusgabe) + ' <span class = "caret"></span');
            if(zurueckList_all[nummer][lastElement-1][1]==-1){
                if (zurueckList_all[nummer] == zurueckList_all[6]){
                    initStoerung(7);
                }else{
                    loadDiv(nummer+1);   
                }
            }else{
                initEinzelansicht(nummer+1);  
            }                
    }
    
    function fillDivs(ansicht){
            
        if(ansicht=="details"){
            for(var i=0;i<6;i++){
                if(localStorage.getItem(elementTypeList[i])!=null){
                    if(localStorage.getItem(elementIdList[i])!=-1){
                        reloadEinzelansicht(localStorage.getItem(elementTypeList[i]),localStorage.getItem(elementIdList[i]),i);
                    }else{
                        reloadTabelle(localStorage.getItem(elementTypeList[i]),i);
                    }
                }
            } 
        }else if(ansicht=="uebersicht"){
            for(var i=5;i<7;i++){
                if(localStorage.getItem(elementTypeList[i])!=null){
                    if(localStorage.getItem(elementIdList[i])!=-1){
                        reloadEinzelansicht(localStorage.getItem(elementTypeList[i]),localStorage.getItem(elementIdList[i]),i);
                    }else{
                        if (i==6){
                            return;
                        }
                        reloadTabelle(localStorage.getItem(elementTypeList[i]),i);
                    }
                }
            } 
        }
    }
    
    function reloadTabelle(elementType, divNumber){
        var i = divNumber;
        $val = elementType;
        $(".dd-btn"+(i+1)).html($val+$caret);
        loadDiv(i+1);
    }

    function reloadEinzelansicht(elementType, elementId, divNumber){
       
        var i = divNumber;
        $val = elementType;
        $(".dd-btn"+(i+1)).html($val+$caret);
        initEinzelansicht(i+1);
    }
    
    function getLocalStorage(){
        console.log("------------------------------");
        console.log("LocalStorage:");
        
        for(var i=1; i<7; i++){
            var id = localStorage.getItem("elementId_"+i);
            var type = localStorage.getItem("elementType_"+i);
            
            console.log("Fenster "+i+": "+type+" "+id);
        }
        console.log("------------------------------");
    }
    
    