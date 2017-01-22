DELIMITER //
CREATE PROCEDURE `UPDATE_ARTIKEL_WARENTRAEGER`(IN `id_warentraeger` BIGINT, IN `id_artikel_1` BIGINT, IN `id_artikel_2` BIGINT, IN `id_artikel_3` BIGINT)
    MODIFIES SQL DATA
BEGIN
	DELETE FROM Artikel_Warentraeger WHERE Artikel_Warentraeger.id_warentraeger = `id_warentraeger`;
	
	IF `id_artikel_1` <> NULL THEN
		INSERT INTO Artikel_Warentraeger(id_artikel, id_warentraeger) VALUES (`id_artikel_1`, `id_warentraeger`);
		
		IF `id_artikel_2` <> NULL THEN
			INSERT INTO Artikel_Warentraeger(id_artikel, id_warentraeger) VALUES (`id_artikel_2`, `id_warentraeger`);
			
			IF `id_artikel_3` <> NULL THEN
				INSERT INTO Artikel_Warentraeger(id_artikel, id_warentraeger) VALUES (`id_artikel_3`, `id_warentraeger`);
			END IF;
		END IF;
	END IF;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `UPDATE_ROBOTER_SEKTOR`(IN `id_roboter` BIGINT, IN `id_sektor` BIGINT)
    MODIFIES SQL DATA
BEGIN
	DELETE FROM Roboter_Sektor WHERE Roboter_Sektor.id_roboter = `id_roboter`;
	INSERT INTO Roboter_Sektor(id_roboter, id_sektor) VALUES (`id_roboter`, `id_sektor`);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `UPDATE_ROBOTER_WERKZEUG`(IN `id_roboter` BIGINT, IN `id_werkzeug` BIGINT)
    MODIFIES SQL DATA
BEGIN
	DELETE FROM Roboter_Werkzeug WHERE Roboter_Werkzeug.id_roboter = `id_roboter`;
	INSERT INTO Roboter_Werkzeug(id_roboter, id_werkzeug) VALUES (`id_roboter`, `id_werkzeug`);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `MOVE_WARENTRAEGER_TO_TRANSPORTBAND`(IN `id_warentraeger` BIGINT, IN `id_transportband` BIGINT)
    MODIFIES SQL DATA
BEGIN
	DELETE FROM Transportband_Warentraeger WHERE Transportband_Warentraeger.id_warentraeger = `id_warentraeger`;
	DELETE FROM Sektor_Warentraeger WHERE Sektor_Warentraeger.id_warentraeger = `id_warentraeger`;
	INSERT INTO Transportband_Warentraeger(id_warentraeger, id_transportband) VALUES (`id_warentraeger`, `id_transportband`);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE `MOVE_WARENTRAEGER_TO_SEKTOR`(IN `id_warentraeger` BIGINT, IN `id_sektor` BIGINT)
    MODIFIES SQL DATA
BEGIN
	DELETE FROM Transportband_Warentraeger WHERE Transportband_Warentraeger.id_warentraeger = `id_warentraeger`;
	DELETE FROM Sektor_Warentraeger WHERE Sektor_Warentraeger.id_warentraeger = `id_warentraeger`;
	INSERT INTO Sektor_Warentraeger(id_warentraeger, id_sektor) VALUES (`id_warentraeger`, `id_sektor`);
END//
DELIMITER ;