SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET autocommit = false;

CREATE TABLE Sektor (
	id_sektor BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	stoerung INT,
	position_x INT,
	position_y INT,
	position_z INT,
	position_ausrichtung INT,
	PRIMARY KEY (id_sektor)
	);

CREATE TABLE Warentraeger (
	id_warentraeger BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	stoerung INT,
	montagezustand INT,
	RFID_inhalt CHAR(128),
	abstand_mm INT,
	PRIMARY KEY (id_warentraeger)
	);

CREATE TABLE Artikel (
	id_artikel BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	PRIMARY KEY (id_artikel)
	);
	
CREATE TABLE Transportband (
	id_transportband BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	reihe INT,
	stoerung INT,
	laenge INT,
	geschwindigkeit INT,
	id_sektor_vor BIGINT,
	id_sektor_nach BIGINT,
	PRIMARY KEY (id_transportband),
	FOREIGN KEY (id_sektor_vor) REFERENCES Sektor(id_sektor),
	FOREIGN KEY (id_sektor_nach) REFERENCES Sektor(id_sektor)
	);
	
CREATE TABLE Sensor (
	id_sensor BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	stoerung INT,
	zustand INT,
	phy_adresse CHAR(10),
	id_sektor BIGINT,
	PRIMARY KEY (id_sensor),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor)
	);

CREATE TABLE Roboter (
	id_roboter BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	stoerung INT,
	position_x INT,
	position_y INT,
	position_z INT,
	position_ausrichtung INT,
	PRIMARY KEY (id_roboter)
	);
	
CREATE TABLE Gelenk (
	id_gelenk BIGINT,	
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	typ VARCHAR(100),
	nummer INT,
	gelenkstellung INT,
	id_roboter BIGINT,
	PRIMARY KEY (id_gelenk),
	FOREIGN KEY (id_roboter) REFERENCES Roboter(id_roboter)
	);
	
CREATE TABLE Werkzeug (
	id_werkzeug BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	zustand INT,
	id_roboter BIGINT,
	PRIMARY KEY (id_werkzeug),
	FOREIGN KEY (id_roboter) REFERENCES Roboter(id_roboter)
	);
	
CREATE TABLE Hubpodest (
	id_hubpodest BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	oben INT,
	unten INT,
	id_sektor BIGINT,
	PRIMARY KEY (id_hubpodest),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor)
	);
	
CREATE TABLE Hubquerpodest (
	id_hubquerpodest BIGINT,
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	user_parameter LONGTEXT,
	motor INT,
	oben INT,
	mittig INT,
	unten INT,
	id_sektor BIGINT,
	PRIMARY KEY (id_hubquerpodest),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor)
	);
	
CREATE TABLE Artikel_Warentraeger (
	id_artikel BIGINT,
	id_warentraeger BIGINT,
	PRIMARY KEY (id_artikel, id_warentraeger),
	FOREIGN KEY (id_artikel) REFERENCES Artikel(id_artikel),
	FOREIGN KEY (id_warentraeger) REFERENCES Warentraeger(id_warentraeger)
	);
	
CREATE TABLE Sektor_Warentraeger (
	id_sektor BIGINT,
	id_warentraeger BIGINT,
	PRIMARY KEY (id_sektor, id_warentraeger),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor),
	FOREIGN KEY (id_warentraeger) REFERENCES Warentraeger(id_warentraeger)
	);
	
CREATE TABLE Transportband_Warentraeger (
	id_transportband BIGINT,
	id_warentraeger BIGINT,
	PRIMARY KEY (id_transportband, id_warentraeger),
	FOREIGN KEY (id_transportband) REFERENCES Transportband(id_transportband),
	FOREIGN KEY (id_warentraeger) REFERENCES Warentraeger(id_warentraeger)
	);
	
CREATE TABLE Roboter_Sektor (
	id_roboter BIGINT,
	id_sektor BIGINT,
	PRIMARY KEY (id_roboter, id_sektor),
	FOREIGN KEY (id_roboter) REFERENCES Roboter(id_roboter),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor)
	);
	
CREATE TABLE Roboter_Werkzeug (
	id_roboter BIGINT,
	id_werkzeug BIGINT,
	PRIMARY KEY (id_roboter, id_werkzeug),
	FOREIGN KEY (id_roboter) REFERENCES Roboter(id_roboter),
	FOREIGN KEY (id_werkzeug) REFERENCES Werkzeug(id_werkzeug)
	);
	
CREATE TABLE Hubquerpodest_Hubquerpodest (
	id_Hubquerpodest1 BIGINT,
	id_hubquerpodest2 BIGINT,
	PRIMARY KEY (id_hubquerpodest1, id_hubquerpodest2),
	FOREIGN KEY (id_hubquerpodest1) REFERENCES Hubquerpodest(id_hubquerpodest),
	FOREIGN KEY (id_hubquerpodest2) REFERENCES Hubquerpodest(id_hubquerpodest)
	);
	
CREATE TABLE Heartbeat (
	id_heartbeat BIGINT,
	zeitstempel TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (id_heartbeat)
	);
	
COMMIT;