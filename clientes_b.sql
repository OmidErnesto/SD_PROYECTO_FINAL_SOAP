-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-08-2023 a las 04:00:09
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
-- Base de datos: `banco_b`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes_b`
--

CREATE TABLE `clientes_b` (
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `dni` int(8) DEFAULT NULL,
  `clave` int(4) DEFAULT NULL,
  `saldo` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes_b`
--

INSERT INTO `clientes_b` (`nombre`, `apellido`, `dni`, `clave`, `saldo`) VALUES
('Corina', 'Hancco', 71950236, 1234, 700),
('Juan', 'Pérez', 71234568, 2345, 6000),
('Ana', 'López', 72345679, 6789, 9000),
('Carlos', 'García', 74567890, 7890, 11000),
('Luis', 'López', 71234570, 5432, 16000),
('Carmen', 'Ruiz', 72345680, 8765, 22000),
('José', 'López', 71234572, 4567, 32000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
