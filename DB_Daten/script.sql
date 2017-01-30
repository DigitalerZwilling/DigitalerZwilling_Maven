WAITFOR DELAY '00:00:10'; -- hh:mm:ss warten
--max abstand pro TB
--TB 13		800
--TB 34		807
--TB 45		1203
--TB 56		627
--TB 67		205
--TB 78		878
--TB 89		1190
--TB 911	1680
--TB 910	665
--TB 1011	404
--TB 1113	2053
--TB 1112	715
--TB 1213	733
--TB 1315	2580
--TB 1314	930
--TB 1415	1050
--TB 151	1190

-- heartbeat aktualisieren
UPDATE `df_16115`.`heartbeat` SET `id_heartbeat`=1 WHERE  `id_heartbeat`=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '5');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '5');
-- Sektoren stoerungen entfernen
UPDATE Sektor
SET stoerung = 0
WHERE id_sektor = 13;
UPDATE Sektor
SET stoerung = 0
WHERE id_sektor = 14;

-- WTs bewegen
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
WAITFOR DELAY '00:00:05'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
WAITFOR DELAY '00:00:05'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 1;
WAITFOR DELAY '00:00:05'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 1;
WAITFOR DELAY '00:00:05'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;

WAITFOR DELAY '00:00:10'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '5');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('3', '67');
--------wichtig---------------------------------------------------
-- procedure aendern:
	-- wenn WT auf TB gesetzt, abstand_mm immer auf 0 setzen?
------------------------------------------------------------------
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('4', '1');
WAITFOR DELAY '00:00:03'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('4', '13');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 4;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 4;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('5', '1');
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 4;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 4;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('5', '13');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 4;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 4;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 5;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 5;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('4', '3');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 5;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 5;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('4', '2');
WAITFOR DELAY '00:00:05'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('5', '2');


WAITFOR DELAY '00:00:10'; -- hh:mm:ss warten



CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('5', '911');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('6', '910');
UPDATE Warentraeger SET abstand_mm = 50 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 50 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 150 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 150 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 250 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 250 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 350 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 350 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 450 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 450 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 6;
WAITFOR DELAY '00:00:01'; -- hh:mm:ss warten




-- Kompletten Rundlauf von WT 333
-- Rundlauf Start
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '1');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '13');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '3');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '34');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '4');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '45');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '5');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '56');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '6');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '67');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '7');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '78');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '8');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '89');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '9');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '911');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '11');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '1112');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '12');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '1213');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '13');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '1315');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '15');
WAITFOR DELAY '00:00:04'; -- hh:mm:ss warten
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '151');
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
WAITFOR DELAY '00:00:02'; -- hh:mm:ss warten

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '1');
WAITFOR DELAY '00:00:13'; -- hh:mm:ss warten

-- Rundelauf Ende










-- zu nutzende funktionen:
UPDATE Warentraeger
SET montagezustand = /*inhalt*/
WHERE id_warentraeger = 000;

UPDATE Warentraeger
SET abstand_mm = /*inhalt*/
WHERE id_warentraeger = 000;

UPDATE Sensor
SET zustand = 1
WHERE id_sensor = 000;

UPDATE Hubpodest
SET /*Spaltenname*/ = /*inhalt*/
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_hubpodest = 000;

UPDATE Hubquerpodest
SET /*Spaltenname*/ = /*inhalt*/
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_hubquerpodest = 000;

UPDATE Artikel_Warentraeger
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_artikel = 000 AND id_warentraeger = 000;


-- funktionen zum stoerung setzen
UPDATE Sektor
SET stoerung = 4
WHERE id_sektor = 000;
UPDATE Warentraeger
SET stoerung = 4
WHERE id_warentraeger = 000;
UPDATE Transportband
SET stoerung = 4
WHERE id_transportband = 000;
UPDATE Sensor
SET stoerung = 4
WHERE id_sensor = 000;
UPDATE Roboter
SET stoerung = 4
WHERE id_roboter = 000;


