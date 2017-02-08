-- 
-- beim 1. das 1. nach innen
-- beim 1. das 2. nach aussen 
-- 
-- WT ist auf 911
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '911');
UPDATE Warentraeger SET abstand_mm = 1300 WHERE id_warentraeger = 1;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1400 WHERE id_warentraeger = 1;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1500 WHERE id_warentraeger = 1;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1600 WHERE id_warentraeger = 1;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 1680 WHERE id_warentraeger = 1;
-- Sensor WT vor Eingansstopper	innen
UPDATE `df_16115`.`sensor` SET `zustand`=1 WHERE  `id_sensor`=44;
DO SLEEP(3);
-- procedure(MOVE_WT_TO_SEKTOR(1,11));
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '11');
-- Sensor WT nach Eingansstopper innen
UPDATE `df_16115`.`sensor` SET `zustand`=1 WHERE  `id_sensor`=45;
DO SLEEP(1);
-- Sensor WT auf erster HuQu innen
UPDATE `df_16115`.`sensor` SET `zustand`=1 WHERE  `id_sensor`=43;
DO SLEEP(2);
-- Sensor WT auf zweiter HuQu innen
UPDATE `df_16115`.`sensor` SET `zustand`=1 WHERE  `id_sensor`=53;
DO SLEEP(3);
-- huqu zweites innen -> hoch
UPDATE `df_16115`.`hubquerpodest` SET `oben`=1 `mittig`=0 WHERE  `id_hubquerpodest`=113;
-- huqu zweites aussen -> hoch
UPDATE `df_16115`.`hubquerpodest` SET `oben`=1 `mittig`=0 WHERE  `id_hubquerpodest`=114;
DO SLEEP(2);
-- huqu zweites innen motor an
UPDATE `df_16115`.`hubquerpodest` SET `motor`=1 WHERE  `id_hubquerpodest`=113;
-- huqu zweites aussen motor an
UPDATE `df_16115`.`hubquerpodest` SET `motor`=1 WHERE  `id_hubquerpodest`=114;
DO SLEEP(3);
-- huqu zweites innen motor aus
UPDATE `df_16115`.`hubquerpodest` SET `motor`=0 WHERE  `id_hubquerpodest`=113;
-- huqu zweites aussen motor aus
UPDATE `df_16115`.`hubquerpodest` SET `motor`=0 WHERE  `id_hubquerpodest`=114;
DO SLEEP(2);
-- huqu zweites innen -> mitte
UPDATE `df_16115`.`hubquerpodest` SET `oben`=0 `mittig`=1 WHERE  `id_hubquerpodest`=113;
-- huqu zweites aussen -> mitte
UPDATE `df_16115`.`hubquerpodest` SET `oben`=0 `mittig`=1 WHERE  `id_hubquerpodest`=114;
DO SLEEP(2);
UPDATE `df_16115`.`sensor` SET `zustand`=1 WHERE  `id_sensor`=53;
DO SLEEP(2);
CALL `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`('1', '1112');
UPDATE Warentraeger SET abstand_mm = 100 WHERE id_warentraeger = 1;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 200 WHERE id_warentraeger = 1;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 300 WHERE id_warentraeger = 1;
DO SLEEP(1);
UPDATE Warentraeger SET abstand_mm = 400 WHERE id_warentraeger = 1;