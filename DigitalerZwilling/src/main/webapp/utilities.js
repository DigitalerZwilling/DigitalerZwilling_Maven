    function createAttributes(documentNr, attribute, id){
        var attributes = document.getElementById(documentNr+"_attributes");
        
        
        for(var i=0; i<attribute.length; i++){
            var zeile = document.createElement("tr");
            var name = document.createElement("td");
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
            name.innerHTML = attribute[i];
            zeile.appendChild(name);

            var value = document.createElement("td");
            var link = document.createElement("a");
            link.id = documentNr+"_"+id[i];
            value.appendChild(link);
            zeile.appendChild(value);

            attributes.appendChild(zeile);
                  
            $("#"+documentNr+"_"+id[i]).click(function() {  
                closeWebsockets(documentNr);
                alert("id="+$(this).attr("elementId")+" type="+$(this).attr("elementType"));
                localStorage.setItem("elementId_"+documentNr,$(this).attr("elementId"));
                localStorage.setItem("elementType_"+documentNr,$(this).attr("elementType"));

                var div = document.getElementById(divName+documentNr);
                var childs = div.childNodes;

                for(var i=0; i<childs.length; i++){
                    div.removeChild(childs[i]);
                }

                initEinzelansicht(documentNr);
            });
        }
    }
        
    function createLists(documentNr, list_tilte, list_id, list_header){
        if(list_tilte.length !== list_id.length) return;
        
        var lists = document.getElementById(documentNr+"_lists");
        
        for(var i=0; i<list_tilte.length; i++){
            var div = document.createElement("div");
            div.id = documentNr+"_div_"+list_id[i];
            div.setAttribute("hidden","");
            
            
            var name = document.createTextNode(list_tilte[i]);
            div.appendChild(name);
            var table = document.createElement("table");
            var tbody = document.createElement("tbody");
            tbody.id = documentNr+"_"+list_id[i];          
            table.setAttribute('class', "table table-striped");
            
            setHeader(list_header[i], table);
            
            table.appendChild(tbody);
            div.appendChild(table)
            lists.appendChild(div);
            lists.appendChild(document.createElement("br"));
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
                        closeWebsockets(documentNr);
                        localStorage.setItem("elementId_"+documentNr,$(this).attr("elementId"));
                        localStorage.setItem("elementType_"+documentNr,$(this).attr("elementType"));
                        
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
    
   
    
    function updateAttributes(documentNr, attribute_id, attribute_value, element_id){
        for(var i=0; i<attribute_id.length; i++){
            var element = document.getElementById(documentNr+"_"+element_id[i]);
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
                     console.log(ids[j]);
                    if(ids[j]==id){
                        console.log("ok");
                        addLine(documentNr, json.inhalt[i],childAttributes,parent, type);
                    }
                }
            }
           
        }
        var div = document.getElementById(documentNr+"_div_"+list_id);
        if(parent.childNodes.length<1){
            //div.setAttribute("hidden","");
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
        
        zeitstempel.replace('T',' ');
        var oben = jsonObject.oben;
        var unten = jsonObject.unten;
        var userParameter = jsonObject.user_Parameter;
        var zustand="X";
        
        if(oben==="1" && unten==="0"){
            zustand = "&uarr;";
        }else if(oben==="0" && unten==="1"){
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
        
        if(oben==="1" && mittig==="0"&& unten==="0"){
            zustand = "&uarr;";
        }else if(oben==="0" && mittig==="1" && unten==="0"){
            zustand = "&mdash;";
        }else if(oben==="0" && mittig==="0" && unten==="1"){
            zustand = "darr;";
        }
        
        if(motor === "1"){
            zustand = "ein " + zustand;
        }else{
            zustand = "aus " + zustand;
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
        }
        
        console.log("length="+websocketList.length);
        
        while(websocketList.length>0){
            websocketList[websocketList.length-1].close();
            websocketList.pop();
        }
    }
    
    function addWebsockets(documentId, array){
        var websocketList= [];
        switch (documentId) {
           case 1: websocketList = websocketList_1; break;
           case 2: websocketList = websocketList_2; break;
           case 3: websocketList = websocketList_3; break;
           case 4: websocketList = websocketList_4; break;
           case 5: websocketList = websocketList_5; break;
           case 6: websocketList = websocketList_6; break;
           case 7: websocketList = websocketList_7; break;
        }
        
        for(var i=0; i< array.length; i++){
            websocketList.push(array[i]);
        }
        
    }
    
    function addZurueckList(documentId, elementId, elementTyp){
        console.log("Fenster"+documentId+': ElementID='+elementId+", ElementTyp="+elementTyp)
    }