-- Kompletten Rundlauf von WT 333
-- Rundlauf Start

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '1');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '13');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '3');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '34');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 807 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '4');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '45');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1203 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '5');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '56');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 627 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '6');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '67');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 205 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '7');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '78');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 878 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '8');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '89');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1190 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '9');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '911');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1680 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '11');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '1112');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 607 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 715 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '12');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '1213');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 620 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 733 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '13');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '1315');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1800 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1900 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 2000 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 2100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 2200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 2300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 2400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 2500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 2580 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '15');
DO SLEEP(2); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '151');
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 900 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1190 WHERE id_warentraeger = 1;
DO SLEEP(1); 
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '1');
DO SLEEP(5);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
DO SLEEP(5);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

-- Rundelauf Ende