-- Testlauf start
-- CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '3');

UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 1;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 2;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 3;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 4;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 5;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 6;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 7;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 8;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 9;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 10;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 11;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 12;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 13;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 14;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 15;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 13;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 34;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 45;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 56;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 67;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 78;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 89;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 910;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 911;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 1011;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 1112;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 1113;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 1213;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 1314;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 1315;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 1415;
UPDATE TRANSPORTBAND SET stoerung = 0 WHERE id_transportband = 151;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 1;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 2;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 3;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 4;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 5;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 6;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 7;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 8;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 9;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 10;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 11;
UPDATE WARENTRAEGER SET stoerung = 0 WHERE id_warentraeger = 12;
DELETE FROM sektor_warentraeger;
DELETE FROM transportband_warentraeger;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '34');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('2', '56');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('3', '89');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('4', '911');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('5', '1112');

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 50 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 120 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 40 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 40 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 240 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 80 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 80 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 150 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 360 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 120 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 120 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 480 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 160 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 160 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 250 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 720 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 240 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 240 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 700 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 350 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 840 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 280 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 280 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 807 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 960 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 320 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 320 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 450 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 1080 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 360 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 360 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '4');
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 1190 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 550 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 440 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 440 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '45');
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('3', '9');
UPDATE Warentraeger SET abstand_mm = 580 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 480 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 480 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 110 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 627 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 520 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 520 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('3', '910');
UPDATE Warentraeger SET abstand_mm = 220 WHERE id_warentraeger = 1;
UPDATE SEKTOR SET stoerung = 1 WHERE id_sektor = 6;
UPDATE Warentraeger SET abstand_mm = 560 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 560 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 330 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 50 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 600 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 440 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 640 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET abstand_mm = 710 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 550 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 150 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 680 WHERE id_warentraeger = 4;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 660 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 720 WHERE id_warentraeger = 4;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('5', '12');
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 770 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 250 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 760 WHERE id_warentraeger = 4;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 880 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 800 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 3 WHERE id_warentraeger = 5;
DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 990 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 350 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 840 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 6 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1100 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 880 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 9 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1203 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 450 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 920 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 12 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE SEKTOR SET stoerung = 0 WHERE id_sektor = 6;
UPDATE Warentraeger SET abstand_mm = 500 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 960 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 15 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '5');
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('2', '6');
UPDATE Warentraeger SET abstand_mm = 550 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1000 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 18 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 605 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1040 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 21 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '56');
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('2', '67');
UPDATE Warentraeger SET abstand_mm = 665 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1080 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 24 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 50 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 1120 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 27 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 130 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 2;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('3', '10');
UPDATE Warentraeger SET abstand_mm = 1160 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 30 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 260 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 150 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET abstand_mm = 1200 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 33 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 390 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 205 WHERE id_warentraeger = 2;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('3', '1011');
UPDATE Warentraeger SET abstand_mm = 1240 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 36 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 520 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 1280 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 39 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 627 WHERE id_warentraeger = 1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('2', '7');
UPDATE Warentraeger SET abstand_mm = 40 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1320 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 43 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 80 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1360 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 46 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '6');
UPDATE Warentraeger SET abstand_mm = 120 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 49 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 160 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1440 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 52 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '67');
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1480 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 55 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 240 WHERE id_warentraeger = 3;
UPDATE Transportband SET stoerung = 4 WHERE id_transportband = 911;
UPDATE Warentraeger SET montagezustand = 58 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 102 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 280 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET montagezustand = 61 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 205 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET abstand_mm = 320 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET montagezustand = 64 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 361 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET montagezustand = 67 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 404 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET montagezustand = 70 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET montagezustand = 73 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('3', '11');
UPDATE Warentraeger SET montagezustand = 76 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET montagezustand = 79 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('3', '1113');
UPDATE Warentraeger SET montagezustand = 82 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET montagezustand = 85 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET montagezustand = 88 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 3;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 911;
UPDATE Warentraeger SET montagezustand = 91 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1520 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 94 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1560 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 97 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE WARENTRAEGER SET stoerung = 99 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET abstand_mm = 1600 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET montagezustand = 100 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1640 WHERE id_warentraeger = 4;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('5', '1213');

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 1680 WHERE id_warentraeger = 4;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('4', '11');
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;
UPDATE WARENTRAEGER SET stoerung = 99 WHERE id_warentraeger = 5;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

DO SLEEP(1);
UPDATE heartbeat SET zeitstempel=CURRENT_TIMESTAMP WHERE id_heartbeat=1;

