-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-08-2023 a las 04:00:15
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
-- Base de datos: `banco_c`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes_c`
--

CREATE TABLE `clientes_c` (
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `dni` int(8) DEFAULT NULL,
  `clave` int(4) DEFAULT NULL,
  `saldo` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes_c`
--

INSERT INTO `clientes_c` (`nombre`, `apellido`, `dni`, `clave`, `saldo`) VALUES
('NuevoCliente', 'ApellidoCliente', 987654, 4321, 5000),
('Sofía', 'Hernández', 71234569, 3456, 7000),
('Marcelo', 'Díaz', 73456780, 8901, 10000),
('Laura', 'Fernández', 74567891, 9012, 13000),
('Miguel', 'García', 71234571, 7654, 20000),
('Carmen', 'Ruiz', 72345680, 9876, 24000),
('José', 'López', 71234572, 5678, 34000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
