/*	-- Insert Befehle --  */

INSERT INTO Sektor (id_sektor, bezeichnung, zeitstempel, user_parameter, stoerung, position_x, position_y, position_z, position_ausrichtung)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,INT,INT,INT*/);

INSERT INTO Warentraeger (id_warentraeger, bezeichnung, zeitstempel, user_parameter, stoerung, montagezustand, RFID_inhalt, abstand_mm)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,CHAR(128),INT*/);

INSERT INTO Artikel (id_artikel, bezeichnung, zeitstempel, user_parameter)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB*/);

INSERT INTO Transportband (id_transportband, bezeichnung, zeitstempel, user_parameter, stoerung, laenge, geschwindigkeit, id_sektor_vor, id_sektor_nach)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,INT,BIGINT,BIGINT*/);

INSERT INTO Sensor (id_sensor, bezeichnung, zeitstempel, user_parameter, stoerung, zustand, phy_adresse, id_sektor)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,CHAR(10),BIGINT*/);

INSERT INTO Roboter (id_roboter, bezeichnung, zeitstempel, user_parameter, stoerung, position_x, position_y, position_z, position_ausrichtung)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,INT,INT,INT*/);

INSERT INTO Gelenk (id_gelenk, bezeichnung, zeitstempel, user_parameter, typ, nummer, gelenkstellung, id_roboter)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,VARCHAR(100),INT,INT,BIGINT*/);

INSERT INTO Werkzeug (id_werkzeug, bezeichnung, zeitstempel, user_parameter, zustand, id_roboter)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,BIGINT*/);

INSERT INTO Hubpodest (id_hubpodest, bezeichnung, zeitstempel, user_parameter, oben, unten, id_sektor)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,BIGINT*/);

INSERT INTO Hubquerpodest (id_hubquerpodest, bezeichnung, zeitstempel, user_parameter, motor, oben, mittig, unten, id_sektor)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,INT,INT,BIGINT*/);

INSERT INTO Artikel_Warentraeger (id_artikel, id_warentraeger)
VALUES (/*BIGINT,BIGINT*/);

INSERT INTO Sektor_Warentraeger (id_sektor, id_warentraeger)
VALUES (/*BIGINT,BIGINT*/);

INSERT INTO Transportband_Warentraeger (id_transportband, id_warentraeger)
VALUES (/*BIGINT,BIGINT*/);

INSERT INTO Roboter_Sektor (id_roboter, id_sektor)
VALUES (/*BIGINT,BIGINT*/);


/*  -- Update Befehle --  */

UPDATE Sektor
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_sektor = 000;

UPDATE Warentraeger
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_warentraeger = 000;

UPDATE Artikel
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_artikel = 000;

UPDATE Transportband
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_transportband = 000;

UPDATE Sensor
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_sensor = 000;

UPDATE Roboter
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_roboter = 000;

UPDATE Gelenk
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_gelenk = 000;

UPDATE Werkzeug
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_werkzeug = 000;

UPDATE Hubpodest
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_hubpodest = 000;

UPDATE Hubquerpodest
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_hubquerpodest = 000;

UPDATE Artikel_Warentraeger
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_artikel = 000 AND id_warentraeger = 000;

UPDATE Sektor_Warentraeger
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_sektor = 000 AND id_warentraeger = 000;

UPDATE Transportband_Warentraeger
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_transportband = 000 AND id_warentraeger = 000;

UPDATE Roboter_Sektor
SET /*Spaltenname*/ = /*inhalt*/
WHERE id_roboter = 000 AND id_sektor = 000;


/*	-- Delete Befehle --  */

DELETE FROM Sektor
WHERE id_sektor = 000;

DELETE FROM Warentraeger
WHERE id_warentraeger = 000;

DELETE FROM Artikel
WHERE id_artikel = 000;

DELETE FROM Transportband
WHERE id_transportband = 000;

DELETE FROM Sensor
WHERE id_sensor = 000;

DELETE FROM Roboter
WHERE id_roboter = 000;

DELETE FROM Gelenk
WHERE id_gelenk = 000;

DELETE FROM Werkzeug
WHERE id_werkzeug = 000;

DELETE FROM Hubpodest
WHERE id_hubpodest = 000;

DELETE FROM Hubquerpodest
WHERE id_hubquerpodest = 000;

DELETE FROM Artikel_Warentraeger
WHERE id_artikel = 000 AND id_warentraeger = 000;

DELETE FROM Sektor_Warentraeger
WHERE id_sektor = 000 AND id_warentraeger = 000;

DELETE FROM Transportband_Warentraeger
WHERE id_transportband = 000 AND id_warentraeger = 000;

DELETE FROM Roboter_Sektor
WHERE id_roboter = 000 AND id_sektor = 000;



