CREATE DATABASE european_war;
USE european_war;

CREATE TABLE partidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_jugador VARCHAR(50) NOT NULL,
    ronda INT NOT NULL,
    pais_jugador VARCHAR(50) NOT NULL,
    fecha_guardado TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE paises_partida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    partida_id INT NOT NULL,
    nombre_pais VARCHAR(50) NOT NULL,
    vida INT NOT NULL,
    misiles INT NOT NULL,
    defensas DOUBLE NOT NULL,
    cooldown_habilidad INT NOT NULL,
    bono_ataque DOUBLE NOT NULL,
    duracion_bono_ Ataque INT NOT NULL,
    bono_defensa DOUBLE NOT NULL,
    duracion_bono_defensa INT NOT NULL,
    turnos_desde_inicio INT NOT NULL,
    FOREIGN KEY (partida_id) REFERENCES partidas(id)
);