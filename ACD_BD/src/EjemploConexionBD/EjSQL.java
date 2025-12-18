package EjemploConexionBD;

public class EjSQL {

}

/*
-- Consulta básica
SELECT * FROM persona;

-- Con condiciones y orden
SELECT dni, nombre, edad FROM persona WHERE edad > 30 ORDER BY nombre ASC;

-- Conteo agrupado
SELECT COUNT(*) as total, AVG(edad) as media_edad FROM persona GROUP BY edad > 30;


-- INSERT
INSERT INTO persona (dni, nombre, edad) VALUES ('12345678Z', 'Juan Pérez', 28);

-- UPDATE
UPDATE persona SET edad = 29 WHERE dni = '12345678Z';

-- DELETE
DELETE FROM persona WHERE edad < 25;


DELIMITER //
CREATE PROCEDURE agregarpersona(IN pdni VARCHAR(9), IN pnombre VARCHAR(20), IN pedad INT)
BEGIN
    INSERT INTO persona (dni, nombre, edad) VALUES (pdni, pnombre, pedad);
END //
DELIMITER ;

-- Llamada
CALL agregarpersona('88866622A', 'Ana López', 35);





DELIMITER //
CREATE FUNCTION contarpersonas(nom VARCHAR(20)) 
RETURNS INT
READS SQL DATA
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM persona WHERE nombre = nom;
    RETURN total;
END //
DELIMITER ;

-- Llamada
SELECT contarpersonas('Juan');




-- 1. Actualizar edad con incremento condicional
DELIMITER //
CREATE PROCEDURE actualizarEdad(IN pdni VARCHAR(9), IN increment INT)
BEGIN
    DECLARE nueva_edad INT;
    SELECT edad INTO nueva_edad FROM persona WHERE dni = pdni;
    IF nueva_edad IS NOT NULL THEN
        SET nueva_edad = nueva_edad + increment;
        UPDATE persona SET edad = nueva_edad WHERE dni = pdni;
    END IF;
END //
DELIMITER ;

-- 2. Eliminar personas por rango de edad con contador OUT
DELIMITER //
CREATE PROCEDURE eliminarRangoEdad(IN min_edad INT, IN max_edad INT, OUT eliminados INT)
BEGIN
    SET eliminados = 0;
    DELETE FROM persona WHERE edad BETWEEN min_edad AND max_edad;
    SET eliminados = ROW_COUNT();
END //
DELIMITER ;

-- Llamadas
CALL actualizarEdad('12345678Z', 5);
CALL eliminarRangoEdad(20, 30, @eliminados);
SELECT @eliminados;





DELIMITER //
CREATE PROCEDURE duplicarPersonas(IN veces INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= veces DO
        INSERT INTO persona (dni, nombre, edad) 
        SELECT CONCAT(dni, '_dup', i), nombre, edad FROM persona;
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;



*/