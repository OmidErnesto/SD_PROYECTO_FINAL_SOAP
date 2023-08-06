-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-08-2023 a las 03:59:57
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `banco_a`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes_a`
--

CREATE TABLE `clientes_a` (
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `dni` int(8) DEFAULT NULL,
  `clave` int(4) DEFAULT NULL,
  `saldo` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes_a`
--

INSERT INTO `clientes_a` (`nombre`, `apellido`, `dni`, `clave`, `saldo`) VALUES
('Omid', 'Ernesto', 71950539, 1234, 1750),
('Jorge', 'Curioso', 71950538, 1234, 500),
('María', 'Gómez', 71234567, 1234, 5000),
('Pedro', 'Martínez', 72345678, 5678, 8000),
('Luisa', 'Ramírez', 73456789, 9012, 12000),
('Luis', 'López', 71234570, 4321, 15000),
('Miguel', 'García', 71234571, 6543, 18000),
('José', 'López', 71234572, 3456, 30000),
('Pepe', 'Majolote', 71952630, 1234, 4800);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
