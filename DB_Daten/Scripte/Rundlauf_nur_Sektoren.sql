-- Rundlauf durch alle Sektoren

CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '1');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '2');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '3');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '4');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '5');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '6');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '7');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '8');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '9');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '10');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '11');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '12');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '13');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '14');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '15');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;
CALL `MOVE_WARENTRAEGER_TO_SEKTOR`('1', '1');
DO SLEEP(0.5);UPDATE `df_16115`.`heartbeat` SET `zeitstempel`=CURRENT_TIMESTAMP WHERE  `id_heartbeat`=1;

-- Rundlauf Sektoren Ende