WAITFOR DELAY '00:00:10'; -- hh:mm:ss warten

-- heatbeat alle x sekunden aktuelisieren


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

-- proceduren aufrufen:
	-- move_warentraeger_nach_sektor
	-- move_warentraeger_nach_transportband



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


