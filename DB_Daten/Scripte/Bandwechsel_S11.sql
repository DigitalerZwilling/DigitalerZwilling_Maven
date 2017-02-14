-- 
-- beim 1. das 1. nach innen
-- beim 1. das 2. nach aussen 


UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 1;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 2;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 3;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 4;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 5;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 6;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 7;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 8;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 9;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 10;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 11;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 12;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 13;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 14;
UPDATE Sektor SET stoerung = 0 WHERE id_sektor = 15;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 13;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 34;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 45;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 56;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 67;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 78;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 89;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 910;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 911;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 1011;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 1112;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 1113;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 1213;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 1314;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 1315;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 1415;
UPDATE Transportband SET stoerung = 0 WHERE id_transportband = 151;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 1;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 2;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 3;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 4;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 5;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 6;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 7;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 8;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 9;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 10;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 11;
UPDATE Warentraeger SET stoerung = 0 WHERE id_warentraeger = 12;
DELETE FROM sektor_warentraeger;
DELETE FROM transportband_warentraeger;
UPDATE Sensor SET zustand=0 WHERE id_sensor=43;
UPDATE Sensor SET zustand=0 WHERE id_sensor=44;
UPDATE Sensor SET zustand=0 WHERE id_sensor=45;
UPDATE Sensor SET zustand=0 WHERE id_sensor=52;
UPDATE Sensor SET zustand=0 WHERE id_sensor=53;
UPDATE Sensor SET zustand=0 WHERE id_sensor=50;
UPDATE Hubquerpodest SET motor=0 WHERE id_hubquerpodest=113;
UPDATE Hubquerpodest SET motor=0 WHERE id_hubquerpodest=114;
UPDATE Hubquerpodest SET oben=0, mittig=1 WHERE id_hubquerpodest=113;
UPDATE Hubquerpodest SET oben=0, mittig=1 WHERE id_hubquerpodest=114;


CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '911');
UPDATE Warentraeger SET abstand_mm = 1300 WHERE id_warentraeger = 1;
DO SLEEP(3);
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 1;
DO SLEEP(3);
UPDATE Warentraeger SET abstand_mm = 1500 WHERE id_warentraeger = 1;
DO SLEEP(3);
UPDATE Warentraeger SET abstand_mm = 1600 WHERE id_warentraeger = 1;
DO SLEEP(3);
UPDATE Warentraeger SET abstand_mm = 1680 WHERE id_warentraeger = 1;

UPDATE Sensor SET zustand=1 WHERE id_sensor=44;
DO SLEEP(10);

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '11');
UPDATE Sensor SET zustand=0 WHERE id_sensor=44;
UPDATE Sensor SET zustand=1 WHERE id_sensor=45;
DO SLEEP(10);
UPDATE Sensor SET zustand=0 WHERE id_sensor=45;
UPDATE Sensor SET zustand=1 WHERE id_sensor=43;
DO SLEEP(10);
UPDATE Sensor SET zustand=0 WHERE id_sensor=43;
UPDATE Sensor SET zustand=1 WHERE id_sensor=53;
DO SLEEP(10);
UPDATE Hubquerpodest SET oben=1, mittig=0 WHERE id_hubquerpodest=113;
UPDATE Hubquerpodest SET oben=1, mittig=0 WHERE id_hubquerpodest=114;
DO SLEEP(10);
UPDATE Hubquerpodest SET motor=1 WHERE id_hubquerpodest=113;
UPDATE Hubquerpodest SET motor=1 WHERE id_hubquerpodest=114;
DO SLEEP(10);
UPDATE Sensor SET zustand=0 WHERE id_sensor=53;
UPDATE Sensor SET zustand=1 WHERE id_sensor=52;
DO SLEEP(10);
UPDATE Hubquerpodest SET motor=0 WHERE id_hubquerpodest=113;
UPDATE Hubquerpodest SET motor=0 WHERE id_hubquerpodest=114;
DO SLEEP(10);
UPDATE Hubquerpodest SET oben=0, mittig=1 WHERE id_hubquerpodest=113;
UPDATE Hubquerpodest SET oben=0, mittig=1 WHERE id_hubquerpodest=114;
DO SLEEP(10);
UPDATE Sensor SET zustand=0 WHERE id_sensor=52;
UPDATE Sensor SET zustand=1 WHERE id_sensor=50;
DO SLEEP(10);
UPDATE Sensor SET zustand=0 WHERE id_sensor=50;
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '1112');
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(2);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;


