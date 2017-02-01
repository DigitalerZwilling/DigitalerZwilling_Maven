DO SLEEP(10);
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
DO SLEEP(5);
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
DO SLEEP(5);
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 1;
DO SLEEP(5);
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 1;
DO SLEEP(5);
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;

DO SLEEP(10);
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '5');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('3', '67');
--------wichtig---------------------------------------------------
-- procedure aendern:
	-- wenn WT auf TB gesetzt, abstand_mm immer auf 0 setzen?
------------------------------------------------------------------
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('4', '1');
DO SLEEP(3);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('4', '13');
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 4;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 4;
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('5', '1');
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 4;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 4;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('5', '13');
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 4;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 4;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 5;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 5;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('4', '3');
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 5;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 5;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('4', '2');
DO SLEEP(5);
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('5', '2');


DO SLEEP(10);



CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('5', '911');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('6', '910');
UPDATE Warentraeger SET abstand_mm = 50 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 50 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 150 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 150 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 250 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 250 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 350 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 350 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 450 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 450 WHERE id_warentraeger = 6;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 6;
DO SLEEP(1);




-- Kompletten Rundlauf von WT 333
-- Rundlauf Start
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '1');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '13');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '3');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '34');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 807 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '4');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '45');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1203 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '5');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '56');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 627 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '6');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '67');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 205 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '7');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '78');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 878 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '8');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '89');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1190 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '9');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '911');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1680 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '11');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '1112');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 607 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 715 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '12');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '1213');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 620 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 733 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '13');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '1315');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1800 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1900 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 2000 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 2100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 2200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 2300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 2400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 2500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 2580 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '15');
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('333', '151');
DO SLEEP(1);

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 333;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1190 WHERE id_warentraeger = 333;
DO SLEEP(1);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('333', '1');
DO SLEEP(13);

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


