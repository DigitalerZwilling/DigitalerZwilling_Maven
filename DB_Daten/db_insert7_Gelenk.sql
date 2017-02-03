INSERT INTO Gelenk (
		id_gelenk,	
		bezeichnung,
		user_parameter,
		typ,
		nummer,
		gelenkstellung,
		id_roboter
	) VALUES (
		1,
		'Beispiel Gelenk1',
		'',
		'Rotationsgelenk',
		1,
		0,
		3
	);
	
INSERT INTO Gelenk (
		id_gelenk,	
		bezeichnung,
		user_parameter,
		typ,
		nummer,
		gelenkstellung,
		id_roboter
	) VALUES (
		1,
		'Beispiel Gelenk2',
		'',
		'Translationsgelenk',
		2,
		0,
		3
	);
	
COMMIT;