--
-- Base de datos: `Hotel`
--
DROP DATABASE IF EXISTS `Hotel`;
CREATE DATABASE IF NOT EXISTS `Hotel` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `Hotel`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuaris`
--

DROP TABLE IF EXISTS `Usuaris`;
CREATE TABLE `Usuaris` (
  `Usuari` varchar(255) COLLATE utf8_bin NOT NULL,
  `Password` varchar(255) COLLATE utf8_bin NOT NULL,
  `Nom` varchar(100) COLLATE utf8_bin NOT NULL,
  `Cognoms` varchar(255) COLLATE utf8_bin NOT NULL,
  `DNI/Passaport` varchar(255) COLLATE utf8_bin NOT NULL,
  `Nacionalitat` varchar(255) COLLATE utf8_bin NOT NULL,
  `Telèfon` varchar(255) COLLATE utf8_bin NOT NULL,
  `e-mail` varchar(255) COLLATE utf8_bin NOT NULL,
  `estado` tinyint(1) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Usuari`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Clientes`
--

DROP TABLE IF EXISTS `Clientes`;
CREATE TABLE `Clientes` (
  `id_client` int(255) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) COLLATE utf8_bin NOT NULL,
  `Cognoms` varchar(255) COLLATE utf8_bin NOT NULL,
  `DNI/Passaport` varchar(255) COLLATE utf8_bin NOT NULL,
  `Nacionalitat` varchar(255) COLLATE utf8_bin NOT NULL,
  `telèfon` int(20) NOT NULL,
  `e-mail` varchar(255) COLLATE utf8_bin NOT NULL,
  `ocupació` varchar(100) COLLATE utf8_bin NOT NULL,
  `estat civil` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY(`id_client`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Habitacions`
--

DROP TABLE IF EXISTS `Habitacions`;
CREATE TABLE `Habitacions` (
  `id_hab` int(11) NOT NULL AUTO_INCREMENT,
  `Número` int(11) NOT NULL,
  `Planta` int(11) NOT NULL,
  `Preu` int(11) NOT NULL,
  `Estat` varchar(20) COLLATE utf8_bin NOT NULL,
  `Tipus` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id_hab`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Reservas`
--

DROP TABLE IF EXISTS `Reservas`;
CREATE TABLE `Reservas` (
  `id_reserva` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(20) COLLATE utf8_bin NOT NULL,
  `Cost` int(11) NOT NULL,
  `Estat reserva` varchar(20) COLLATE utf8_bin NOT NULL,
  `data_entrada` date NOT NULL,
  `hora_entrada` varchar(20) COLLATE utf8_bin NULL,
  `data_sortida` date NOT NULL,
  `hora_sortida` varchar(20) COLLATE utf8_bin NULL,
  `fk_client` int(11) NOT NULL,
  `fk_hab` int(11) NOT NULL,
  PRIMARY KEY (`id_reserva`),
  FOREIGN KEY (`fk_client`) REFERENCES Clientes(`id_client`),
  FOREIGN KEY (`fk_hab`) REFERENCES Habitacions(`id_hab`)
);
