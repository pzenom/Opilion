# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.1.41
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2011-11-02 10:56:17
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for tierrina
CREATE DATABASE IF NOT EXISTS `tierrina` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `tierrina`;


# Dumping structure for table tierrina.albaran
CREATE TABLE IF NOT EXISTS `albaran` (
  `idAlbaran` bigint(20) NOT NULL DEFAULT '0',
  `idCliente` bigint(20) DEFAULT NULL,
  `idVehiculo` bigint(20) DEFAULT NULL,
  `idComercial` bigint(20) DEFAULT NULL,
  `idTipoVehiculo` bigint(20) DEFAULT NULL,
  `tipoAlbaran` char(1) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `destino` varchar(100) DEFAULT NULL,
  `codigoAlbaran` varchar(50) DEFAULT NULL,
  `pesoNetoTotal` double(10,2) DEFAULT NULL,
  `pesoBrutoTotal` double(10,2) DEFAULT NULL,
  `numeroBultosTotal` double(10,2) DEFAULT NULL,
  `cantidadTotal` double(10,2) DEFAULT NULL,
  `precioUnitarioTotal` double(10,2) DEFAULT NULL,
  `importeTotal` double(10,2) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  `habilitado` char(1) DEFAULT NULL,
  `facturado` char(1) DEFAULT 'N',
  `estado` char(1) DEFAULT 'P',
  `idFormaPago` bigint(20) DEFAULT '0',
  `peso` double(8,2) DEFAULT '0.00',
  PRIMARY KEY (`idAlbaran`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.albaran: 0 rows
DELETE FROM `albaran`;
/*!40000 ALTER TABLE `albaran` DISABLE KEYS */;
/*!40000 ALTER TABLE `albaran` ENABLE KEYS */;


# Dumping structure for table tierrina.albaran_detalle
CREATE TABLE IF NOT EXISTS `albaran_detalle` (
  `idRegistroSalida` bigint(20) NOT NULL DEFAULT '0',
  `idAlbaran` bigint(20) DEFAULT NULL,
  `linNum` tinyint(4) DEFAULT NULL,
  `codigoAlbaran` varchar(20) DEFAULT NULL,
  `codigoSalida` varchar(20) DEFAULT NULL,
  `eanProducto` varchar(14) DEFAULT NULL,
  `pesoNeto` double(10,2) DEFAULT NULL,
  `numeroBultos` bigint(20) DEFAULT NULL,
  `cantidadUnitaria` double(10,2) DEFAULT NULL,
  `precioUnitario` double(10,2) DEFAULT NULL,
  `importe` double(10,2) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idRegistroSalida`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.albaran_detalle: 0 rows
DELETE FROM `albaran_detalle`;
/*!40000 ALTER TABLE `albaran_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `albaran_detalle` ENABLE KEYS */;


# Dumping structure for table tierrina.albaran_factura
CREATE TABLE IF NOT EXISTS `albaran_factura` (
  `idFactura` bigint(20) NOT NULL DEFAULT '0',
  `idAlbaran` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idFactura`,`idAlbaran`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.albaran_factura: 0 rows
DELETE FROM `albaran_factura`;
/*!40000 ALTER TABLE `albaran_factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `albaran_factura` ENABLE KEYS */;


# Dumping structure for table tierrina.analisis_registro
CREATE TABLE IF NOT EXISTS `analisis_registro` (
  `idAnalisis` bigint(20) NOT NULL DEFAULT '0',
  `idRegistroEntrada` bigint(20) NOT NULL DEFAULT '0',
  `varIG` tinyint(5) DEFAULT '0',
  `varSP` tinyint(5) DEFAULT '0',
  `varDP` tinyint(5) DEFAULT '0',
  `varDA` tinyint(5) DEFAULT '0',
  `varM` tinyint(5) DEFAULT '0',
  `calidad` double(4,2) DEFAULT '0.00',
  `baremoCalidad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idAnalisis`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.analisis_registro: 0 rows
DELETE FROM `analisis_registro`;
/*!40000 ALTER TABLE `analisis_registro` DISABLE KEYS */;
/*!40000 ALTER TABLE `analisis_registro` ENABLE KEYS */;


# Dumping structure for table tierrina.banco
CREATE TABLE IF NOT EXISTS `banco` (
  `idBanco` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`idBanco`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.banco: 3 rows
DELETE FROM `banco`;
/*!40000 ALTER TABLE `banco` DISABLE KEYS */;
INSERT INTO `banco` (`idBanco`, `nombre`) VALUES
	(1, ' No definido'),
	(2, 'Santander'),
	(3, 'BBVA');
/*!40000 ALTER TABLE `banco` ENABLE KEYS */;


# Dumping structure for table tierrina.bulto
CREATE TABLE IF NOT EXISTS `bulto` (
  `idBulto` bigint(20) NOT NULL,
  `codigoSalida` varchar(20) DEFAULT NULL,
  `direccionEntrega` varchar(14) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.bulto: 0 rows
DELETE FROM `bulto`;
/*!40000 ALTER TABLE `bulto` DISABLE KEYS */;
/*!40000 ALTER TABLE `bulto` ENABLE KEYS */;


# Dumping structure for table tierrina.bulto_lotes
CREATE TABLE IF NOT EXISTS `bulto_lotes` (
  `idBulto` bigint(20) NOT NULL,
  `lote` varchar(20) DEFAULT NULL,
  `idHueco` bigint(20) DEFAULT NULL,
  `cantidad` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.bulto_lotes: 0 rows
DELETE FROM `bulto_lotes`;
/*!40000 ALTER TABLE `bulto_lotes` DISABLE KEYS */;
/*!40000 ALTER TABLE `bulto_lotes` ENABLE KEYS */;


# Dumping structure for table tierrina.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `idCategoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`idCategoria`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.categoria: 6 rows
DELETE FROM `categoria`;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`idCategoria`, `nombre`) VALUES
	(1, 'Sin categorizar'),
	(2, 'Extra'),
	(3, 'Extra B'),
	(4, 'Primera'),
	(5, 'Segunda'),
	(6, 'Destrio');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;


# Dumping structure for table tierrina.cliente_diasentrega
CREATE TABLE IF NOT EXISTS `cliente_diasentrega` (
  `idUsuario` bigint(20) NOT NULL DEFAULT '0',
  `idDiaEntrega` tinyint(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUsuario`,`idDiaEntrega`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.cliente_diasentrega: 0 rows
DELETE FROM `cliente_diasentrega`;
/*!40000 ALTER TABLE `cliente_diasentrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente_diasentrega` ENABLE KEYS */;


# Dumping structure for table tierrina.codigos_especiales
CREATE TABLE IF NOT EXISTS `codigos_especiales` (
  `idCodigo` int(10) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(8) COLLATE utf8_bin NOT NULL DEFAULT '00000000',
  `descripcion` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT 'Ejecucion',
  PRIMARY KEY (`idCodigo`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# Dumping data for table tierrina.codigos_especiales: 24 rows
DELETE FROM `codigos_especiales`;
/*!40000 ALTER TABLE `codigos_especiales` DISABLE KEYS */;
INSERT INTO `codigos_especiales` (`idCodigo`, `codigo`, `descripcion`) VALUES
	(1, 'X0000001', 'Salto de bulto'),
	(2, 'X0000002', 'INTRO'),
	(3, 'X0000003', 'Atrás'),
	(4, 'X0000004', 'Cancelar'),
	(5, 'X0000005', 'FIN'),
	(6, 'X0000006', 'Merma'),
	(7, 'X0000007', 'Mover'),
	(8, 'X0000008', 'Inventario'),
	(9, 'X0000009', 'Devolución'),
	(10, 'XN000000', '0'),
	(11, 'XN000001', '1'),
	(12, 'XN000002', '2'),
	(13, 'XN000003', '3'),
	(14, 'XN000004', '4'),
	(15, 'XN000005', '5'),
	(16, 'XN000006', '6'),
	(17, 'XN000007', '7'),
	(18, 'XN000008', '8'),
	(19, 'XN000009', '9'),
	(20, 'XN00000.', ','),
	(21, 'XN000000', '0'),
	(22, 'XN000000', '0'),
	(23, 'XN000000', '0'),
	(24, 'XN000000', '0');
/*!40000 ALTER TABLE `codigos_especiales` ENABLE KEYS */;


# Dumping structure for table tierrina.contacto
CREATE TABLE IF NOT EXISTS `contacto` (
  `idContacto` bigint(20) NOT NULL DEFAULT '0',
  `idUsuario` bigint(20) NOT NULL DEFAULT '0',
  `nombreContacto` varchar(200) COLLATE utf8_spanish_ci NOT NULL DEFAULT '',
  `cargoContacto` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `telefonoContacto` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `extensionContacto` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `emailContacto` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `dptoContacto` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`idContacto`),
  KEY `idUsuario` (`idUsuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

# Dumping data for table tierrina.contacto: 26 rows
DELETE FROM `contacto`;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
INSERT INTO `contacto` (`idContacto`, `idUsuario`, `nombreContacto`, `cargoContacto`, `telefonoContacto`, `extensionContacto`, `emailContacto`, `dptoContacto`) VALUES
	(1, 1, 'Juan Carlos Barrios', 'Gerente', '', '', '', ''),
	(2, 2, 'Nelson Pérez', 'Gerente', '', '', '', ''),
	(3, 3, 'Tino', 'Agricultor', '985979801', '', '', ''),
	(4, 4, 'José Antonio -Maite', 'Jefe Ventas', '985898052-605240951', '', '', ''),
	(5, 5, 'Kin', '', '', '', '', ''),
	(6, 6, '', '', '', '', '', ''),
	(7, 7, '', '', '', '', '', ''),
	(8, 8, 'Fanny Cárdenas', 'Gerente', '0059176635960', '', '', ''),
	(9, 9, 'Juan Carlos', 'Gerente', '630988771', '', '', ''),
	(10, 10, 'Manuel', 'Gerente', '630187682', '', '', ''),
	(11, 11, '', '', '', '', '', ''),
	(12, 12, 'Jose Manuel Garcia', 'Gerente', '647 575 078', '', '', ''),
	(13, 13, 'Francisco Junco', 'Jefe Ventas', '985794215', '', '', ''),
	(14, 14, '', '', '', '', '', ''),
	(15, 15, '', '', '', '', '', ''),
	(16, 16, '', '', '', '', '', ''),
	(17, 17, '', '', '985551011', '', '', ''),
	(18, 18, 'Víctor', '', '', '', '', ''),
	(19, 19, '', '', '', '', '', ''),
	(20, 20, 'Jose Maria Vazquez', 'Gerente', '972357015', '', '', ''),
	(21, 21, 'Antonio Albornoz', 'Gerente', '625 348 700', '', '', ''),
	(22, 22, '', '', '', '', '', ''),
	(23, 23, 'David', 'Comercial', '', '', '', ''),
	(24, 24, 'Paqui', 'Responsable ventas', '608821116', '', '', ''),
	(25, 25, 'Antonio-Maria', '', '983313339', '', '', ''),
	(26, 26, 'Antonio Albornoz', 'Gerente', '625 348 700', '', '', '');
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;


# Dumping structure for table tierrina.copiaseguridad
CREATE TABLE IF NOT EXISTS `copiaseguridad` (
  `idBackup` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `fecha` date DEFAULT NULL,
  `auto` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'N',
  `ultimoAuto` char(1) COLLATE utf8_bin DEFAULT 'S',
  PRIMARY KEY (`idBackup`)
) ENGINE=MyISAM AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# Dumping data for table tierrina.copiaseguridad: 6 rows
DELETE FROM `copiaseguridad`;
/*!40000 ALTER TABLE `copiaseguridad` DISABLE KEYS */;
INSERT INTO `copiaseguridad` (`idBackup`, `nombre`, `fecha`, `auto`, `ultimoAuto`) VALUES
	(67, '0', '2011-09-12', 'N', 'S'),
	(66, '0', '2011-09-07', 'S', 'N'),
	(64, '0', '2011-08-30', 'N', 'S'),
	(65, '0', '2011-08-30', 'S', 'N'),
	(62, '0', '2011-08-04', 'S', 'N'),
	(75, '0', '2011-10-20', 'S', 'S');
/*!40000 ALTER TABLE `copiaseguridad` ENABLE KEYS */;


# Dumping structure for table tierrina.cosecha
CREATE TABLE IF NOT EXISTS `cosecha` (
  `idCosecha` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(100) NOT NULL DEFAULT '',
  `tipo` set('V','N','I') DEFAULT NULL,
  PRIMARY KEY (`idCosecha`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.cosecha: 4 rows
DELETE FROM `cosecha`;
/*!40000 ALTER TABLE `cosecha` DISABLE KEYS */;
INSERT INTO `cosecha` (`idCosecha`, `nombre`, `tipo`) VALUES
	(0, 'Sin referencia', ''),
	(1, 'IMP-Mayo-Julio', 'V'),
	(2, 'IMP-Diciembre-Enero', 'I'),
	(3, 'NAC-Septiembre', 'N');
/*!40000 ALTER TABLE `cosecha` ENABLE KEYS */;


# Dumping structure for table tierrina.direccion
CREATE TABLE IF NOT EXISTS `direccion` (
  `idDireccion` bigint(20) NOT NULL AUTO_INCREMENT,
  `empresa_idUsuario` bigint(20) NOT NULL DEFAULT '0',
  `idProvincia` bigint(20) DEFAULT '0',
  `idRuta` bigint(20) DEFAULT NULL,
  `tipoDireccion` char(1) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL DEFAULT 'F',
  `nombreCalle` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `ubicacion` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT '0',
  `portal` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `piso` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `letra` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `puerta` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `escalera` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `localidad` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `municipio` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `codigoPostal` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `pais` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `horarioEntrega` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `ean` varchar(14) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idDireccion`),
  KEY `FK6CB05D10918B1F06` (`empresa_idUsuario`),
  KEY `idProvincia` (`idProvincia`),
  KEY `idRuta` (`idRuta`)
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

# Dumping data for table tierrina.direccion: 29 rows
DELETE FROM `direccion`;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` (`idDireccion`, `empresa_idUsuario`, `idProvincia`, `idRuta`, `tipoDireccion`, `nombreCalle`, `ubicacion`, `portal`, `piso`, `letra`, `puerta`, `escalera`, `localidad`, `municipio`, `codigoPostal`, `pais`, `horarioEntrega`, `ean`) VALUES
	(1, 1, 5, NULL, 'F', '', '0', NULL, NULL, NULL, NULL, NULL, 'Gijón', 'Gijón', '33290', NULL, '', '8436016000000'),
	(2, 3, 0, NULL, 'F', 'Calle Santiago de Compostela Sur, S/N- Edificio de Oficinas Madrid 2', '0', NULL, NULL, NULL, NULL, NULL, 'La Vaguada', '', '28029', NULL, '', '8436016000024 '),
	(31, 15, 5, NULL, 'E', 'C/ Doctor Graiño,35', '0', NULL, NULL, NULL, NULL, NULL, 'Avilés', 'Avilés', '33401', NULL, '', '8436016000130'),
	(30, 15, 5, NULL, 'E', 'Plaza de los Hermanos Orbon, 12', '0', NULL, NULL, NULL, NULL, NULL, 'Avilés', 'Avilés', '33401', NULL, '', '8436016000147 '),
	(6, 16, 5, NULL, 'E', 'Polígono Aguila negra', '0', NULL, NULL, NULL, NULL, NULL, 'Colloto', 'Oviedo', '', NULL, '', '8436016090100'),
	(7, 16, 5, NULL, 'F', 'Santa Susana, nº2', '0', NULL, NULL, NULL, NULL, NULL, 'Oviedo', 'Oviedo', '33008', NULL, '', '8436016090101'),
	(8, 35, 0, NULL, 'E', 'Santa Cruz de la Sierra', '0', NULL, NULL, NULL, NULL, NULL, '', '', '', NULL, '', '0'),
	(9, 38, 5, NULL, 'E', 'Naveda,12', '0', NULL, NULL, NULL, NULL, NULL, 'Naveda', '', '', NULL, '', '0'),
	(10, 39, 0, NULL, 'E', 'Valtravieso', '0', NULL, NULL, NULL, NULL, NULL, 'Santiago de Otur', '', '', NULL, '', '0'),
	(11, 41, 5, NULL, 'E', 'P.I. BARCIA-ALMUÑA Parc. 11 Fase II', '0', NULL, NULL, NULL, NULL, NULL, 'LUARCA', '', '33700', NULL, '', '0'),
	(12, 42, 0, NULL, 'E', 'Manuel María Caballero', '0', NULL, NULL, NULL, NULL, NULL, 'Comarapa', '', '', NULL, '', '0'),
	(13, 43, 43, NULL, 'E', 'ol. Industrial Bargas, Nave 21', '0', NULL, NULL, NULL, NULL, NULL, 'Bargas', '', '', NULL, '', '0'),
	(14, 44, 24, NULL, 'E', 'Calle del Balneario', '0', NULL, NULL, NULL, NULL, NULL, 'Arteixo', '', '15142', NULL, '', '0'),
	(15, 46, 5, NULL, 'E', 'pol. ind. Barcia-almuña Parcela 14 Fase I', '0', NULL, NULL, NULL, NULL, NULL, 'Luarca', '', '33787', NULL, '', '0'),
	(16, 47, 5, NULL, 'E', 'Parque Empresarial Aguila del Nora', '0', NULL, NULL, NULL, NULL, NULL, 'Colloto', '', '', NULL, '', '0'),
	(17, 48, 5, NULL, 'E', 'Edif. Servicios Logisticos nº 1-Puerto El Musel', '0', NULL, NULL, NULL, NULL, NULL, 'Gijón', '', '33212', NULL, '', '0'),
	(18, 49, 5, NULL, 'E', 'Puerto del Musel', '0', NULL, NULL, NULL, NULL, NULL, 'Gijón', '', '33212', NULL, '', '0'),
	(19, 51, 5, NULL, 'E', 'Avda. de Gijon, 2', '0', NULL, NULL, NULL, NULL, NULL, 'Avilés', '', '33400', NULL, '', '0'),
	(20, 52, 5, NULL, 'E', 'P.I. BARCIA-ALMUÑA Parc. 11 Fase II', '0', NULL, NULL, NULL, NULL, NULL, 'Luarca', '', '33700', NULL, '', '0'),
	(21, 54, 16, NULL, 'E', 'Montjuic, 21', '0', NULL, NULL, NULL, NULL, NULL, 'Blanes', '', '17300', NULL, '', '0'),
	(22, 55, 5, NULL, 'E', 'Plaza Longoria Carbajal, 3 2º D', '0', NULL, NULL, NULL, NULL, NULL, 'Oviedo', '', '33002', NULL, '', '0'),
	(23, 56, 5, NULL, 'E', 'Pol. Granda II. C/ Los Pinos,Nave 12', '0', NULL, NULL, NULL, NULL, NULL, 'Granda', '', '33199', NULL, '', '0'),
	(24, 57, 50, NULL, 'E', 'Polígono Sidamon Industrial ', '0', NULL, NULL, NULL, NULL, NULL, 'Sidamon', '', '25222', NULL, '', '0'),
	(25, 58, 3, NULL, 'E', 'Alcalde Baeza Santamaria,8', '0', NULL, NULL, NULL, NULL, NULL, 'El Campello', '', '03560', NULL, '', '0'),
	(26, 59, 45, NULL, 'E', 'Etileno,10', '0', NULL, NULL, NULL, NULL, NULL, 'Pol. Ind. San Cristobal', '', '47012', NULL, '', '0'),
	(27, 60, 5, NULL, 'E', 'Plaza Longoria Carbajal, 3 2º D', '0', NULL, NULL, NULL, NULL, NULL, 'Oviedo', '', '33002', NULL, '', '0'),
	(28, 61, 35, NULL, 'E', 'Ctra. Peinador, 5-A', '0', NULL, NULL, NULL, NULL, NULL, 'Mos', 'Mos', '36416', NULL, '', '0'),
	(29, 62, 0, NULL, 'E', 'Muelle Del Rendiello, S/N- El Musel', '0', NULL, NULL, NULL, NULL, NULL, 'Lorquí', '', '30564', NULL, '', '0'),
	(32, 15, 5, NULL, 'E', 'C/ La Camara, 42', '0', NULL, NULL, NULL, NULL, NULL, 'Avilés', 'Avilés', '33401', NULL, '', '8436016000130 ');
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;


# Dumping structure for table tierrina.email
CREATE TABLE IF NOT EXISTS `email` (
  `idEmail` bigint(20) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `tipo` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `idUsuario` bigint(20) NOT NULL DEFAULT '0',
  `notas` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idEmail`),
  KEY `idUsuario` (`idUsuario`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# Dumping data for table tierrina.email: 14 rows
DELETE FROM `email`;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` (`idEmail`, `direccion`, `tipo`, `idUsuario`, `notas`) VALUES
	(1, 'administracion@agromar.es', 'Trabajo', 1, ''),
	(2, 'con_sdelgado@ahorramas.com', 'Trabajo', 2, ''),
	(3, ' aromamanchego@aromamanchego.com', 'Trabajo', 7, ''),
	(4, 'consultas@asturiasmascerca.com', 'Trabajo', 8, ''),
	(5, 'correo@balbona.es', 'Trabajo', 12, ''),
	(6, 'info@crivencar.com', 'Trabajo', 16, ''),
	(7, 'irf@carnicas-ismael.com', 'Trabajo', 17, ''),
	(8, 'info@hotellacepada.com', 'Trabajo', 30, ''),
	(9, 'correo@exclusivasapolo.com ', 'Trabajo', 31, ''),
	(10, 'info@massprofessional.com ', 'Trabajo', 33, ''),
	(11, 'robertonavelgas@gmail.com', 'Trabajo', 34, ''),
	(12, 'acpack@acpack.info', 'Trabajo', 61, ''),
	(13, 'manuelgarcia@acpack.info', 'Trabajo', 61, 'Manuel García - Gerente'),
	(14, 'info.murcia@schoellerarca.com', 'Trabajo', 62, '');
/*!40000 ALTER TABLE `email` ENABLE KEYS */;


# Dumping structure for table tierrina.entidad
CREATE TABLE IF NOT EXISTS `entidad` (
  `idUsuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `idSector` bigint(20) DEFAULT NULL,
  `idTipo` bigint(20) DEFAULT NULL,
  `idRol` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `apellido1` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `apellido2` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `apellidos` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nif` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `ean` varchar(14) CHARACTER SET latin1 DEFAULT NULL,
  `registroMercantil` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `web` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `dsctoMercancia` decimal(5,2) DEFAULT '1.00',
  `dsctoValor` decimal(5,2) DEFAULT '0.00',
  `foto` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `descripcion` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `usuarioResponsable` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `habilitado` char(1) CHARACTER SET latin1 NOT NULL DEFAULT 'S',
  `tipoSincronizacion` char(1) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'N',
  PRIMARY KEY (`idUsuario`),
  KEY `idSector` (`idSector`),
  KEY `idTipo` (`idTipo`),
  KEY `idRol` (`idRol`)
) ENGINE=MyISAM AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

# Dumping data for table tierrina.entidad: 62 rows
DELETE FROM `entidad`;
/*!40000 ALTER TABLE `entidad` DISABLE KEYS */;
INSERT INTO `entidad` (`idUsuario`, `idSector`, `idTipo`, `idRol`, `nombre`, `apellido1`, `apellido2`, `apellidos`, `nif`, `ean`, `registroMercantil`, `web`, `dsctoMercancia`, `dsctoValor`, `foto`, `descripcion`, `fechaRegistro`, `usuarioResponsable`, `habilitado`, `tipoSincronizacion`) VALUES
	(1, 7, NULL, 1, 'CONSERVAS AGROMAR S.A.', NULL, NULL, '', 'A33683145', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(2, 3, NULL, 1, 'AHORRAMAS S.A.', NULL, NULL, '', 'A28600278', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(3, 3, NULL, 1, 'ALCAMPO S.A.', NULL, NULL, '', 'A28581882', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(4, 3, NULL, 1, 'ALDI SAN ISIDRO SUPERMERCADOS SL', NULL, NULL, '', 'B53948204', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(5, 0, NULL, 1, 'ALDI MASQUEFA SUPERMERCADOS SL', NULL, NULL, '', 'B63667109', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(6, 3, NULL, 1, 'ALDI DOS HERMANAS SUPERMERCADOS SL', NULL, NULL, '', 'B91405142', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(7, 7, NULL, 1, 'Aroma Manchego S.L.', NULL, NULL, '', 'B45517919', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(8, 4, NULL, 1, 'ASTURIAS MAS CERCA S.L', NULL, NULL, '', 'B97212740', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(9, 1, NULL, 1, 'MATERASTURIAS S.L', NULL, NULL, '', 'B33924374', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(10, 4, NULL, 1, 'ASTURVISA (Asturiana de Vinos S.A.)', NULL, NULL, '', 'A33056151', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(11, 0, NULL, 1, 'JESUS FERNANDEZ ALLICA', NULL, NULL, '', '13790497L', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(12, 0, NULL, 1, 'BALBONA PASTELEROS S.A.', NULL, NULL, '', 'B33642018', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(13, 0, NULL, 1, 'PRODUCTOS CAMPANAL S.A.', NULL, NULL, '', 'A33600396', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(14, 3, NULL, 1, 'CODEFRUT S.A.', NULL, NULL, '', 'A33395476', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(15, 2, NULL, 1, 'Carnicerías Antonio SL', NULL, NULL, '', 'B33324302', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(16, 4, NULL, 1, 'QUESOS DEL PRINCIPADO DE ASTURIAS S.L.', NULL, NULL, '', 'B33120361', '', NULL, '  http://www.productosdeasturias.com', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(17, 2, NULL, 1, 'INDUSTRIAS CARNICAS ISMAEL SL', NULL, NULL, '', 'B81689663', '', NULL, 'www.carnicas-ismael.com', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(18, 2, NULL, 1, 'CÁRNICAS LUISMA S.C.', NULL, NULL, '', 'E33557505', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(19, 4, NULL, 1, 'COMERCIAL GALLEGA DE LOS PALLARES S.L.', NULL, NULL, '', 'B95448924', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(20, 1, NULL, 1, 'DEL VALLE Y MARTINEZ C.B.', NULL, NULL, '', 'E33545278', '', NULL, 'www.elhuertodelermitanodecovadonga.com ', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(21, 3, NULL, 1, 'EL CORTE INGLÉS S.A.', NULL, NULL, '', 'A28017895', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(22, 4, NULL, 1, 'EMBUASTUR S.L.', NULL, NULL, '', 'B33481243', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(23, 4, NULL, 1, 'EMBUTIDOS EL HORREO S.L.', NULL, NULL, '', 'B33354432', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(24, 4, NULL, 1, 'EMBUTIDOS LA ALDEA S.L.', NULL, NULL, '', 'B33428921', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(25, 4, NULL, 1, 'EMBUTIDOS LA TINETENSE S.L.', NULL, NULL, '', 'B33354432', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(26, 4, NULL, 1, 'EMBUTIDOS MAYBE S.A..', NULL, NULL, '', 'A33044199', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(27, 4, NULL, 1, 'EMBUTIDOS NAVEDA-Antonio Palacio Longo', NULL, NULL, '', '52618571E', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(28, 1, NULL, 1, 'Colegio del Pilar ', NULL, NULL, '', 'R2700037-A', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(29, 1, NULL, 1, 'otumbin villaframil C.B.', NULL, NULL, '', 'E27408590', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(30, 1, NULL, 1, 'ETXIZARRETINEZ HOSTELERIA S.L.L', NULL, NULL, '', 'B74011727', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(31, 4, NULL, 1, 'EXCLUSIVAS APOLO S.L.', NULL, NULL, '', 'B39326558', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(32, 4, NULL, 1, 'LA ESPAÑOLA MEATS, INC', NULL, NULL, '', '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(33, 4, NULL, 1, 'Food Profesional s.l.', NULL, NULL, '', 'B33878182', '', NULL, 'www.massprofessional.com', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(34, 4, NULL, 1, 'Roberto Fernandez Perez ', NULL, NULL, '', '45429750N', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(35, NULL, 1, 2, 'AGRISOS', NULL, NULL, NULL, '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(36, NULL, 1, 2, 'ASOPROF', NULL, NULL, NULL, '', '', NULL, 'www.asoprof.com', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(37, NULL, 1, 2, 'Celestino Pérez Santiago (Prod 361 DO)', NULL, NULL, NULL, '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(38, NULL, 1, 2, 'EMBUTIDOS NAVEDA - Antonio Palacio Longo', NULL, NULL, NULL, '52618571E', '', NULL, 'www.naveda.com', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(39, NULL, 1, 2, 'Francisco (Kin)', NULL, NULL, NULL, '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(40, NULL, 1, 2, 'Jesús', NULL, NULL, 'García Pérez', '71867185M', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-12', 'prueba', 'S', 'N'),
	(41, NULL, 1, 2, 'LA TIERRINA VAQUEIRA', NULL, NULL, '', 'B74140591', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(42, NULL, 1, 2, 'LEGUCOM', NULL, NULL, '', '2998041013', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(43, NULL, 1, 2, 'LEGUMBRES SELECTAS LA POSADA S.L.', NULL, NULL, '', 'B45428596', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(44, NULL, 1, 2, 'MANUEL', NULL, NULL, 'MAREQUE BOEDO', '76306597A', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(45, NULL, 1, 2, 'Maruja de Sante', NULL, NULL, '', '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(46, NULL, 1, 2, 'Pepe el Bueno, Herederos de', NULL, NULL, '', '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(47, NULL, 1, 2, 'QUESOS DEL PRINCIPADO DE ASTURIAS SL', NULL, NULL, '', 'B33120361', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(48, NULL, 2, 2, 'ASTRACON', NULL, NULL, '', 'F33968934', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(49, NULL, 2, 2, 'COGITRANS', NULL, NULL, '', '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(50, NULL, 2, 2, 'MRW-Mensastur Lugones S.L.', NULL, NULL, '', 'B33536665', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(51, NULL, 2, 2, 'NACEX- Paqueteria', NULL, NULL, '', '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(52, NULL, 2, 2, 'Tierrina Vaqueira (Transporte)', NULL, NULL, '', '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(53, NULL, 2, 2, 'ZAMAR -Grupo DUCO', NULL, NULL, '', '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(54, NULL, 3, 2, 'Fantexsa SL (Hilos Máquina Coser)', NULL, NULL, '', 'B60151602', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(55, NULL, 1, 2, 'Albornoz y Asociados SL', NULL, NULL, '', '', '', NULL, 'www.albornozyasociados.com', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(56, NULL, 4, 2, 'Bobes- Rafael Bobes Fdez.', NULL, NULL, '', '', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(57, NULL, 4, 2, 'ILERAFITEX', NULL, NULL, '', '', '', NULL, 'www.ilerafitex.com', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(58, NULL, 4, 2, 'ORION IMPESIONES S.L.', NULL, NULL, '', 'B54074307', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(59, NULL, 4, 2, 'SERVIGRAMA Ind. Pub. S.L.', NULL, NULL, '', 'B47321476', '', NULL, 'www.servigrama.es', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(60, NULL, 5, 2, 'Albornoz y Asociados SL', NULL, NULL, '', 'B74040320', '', NULL, 'www.albornozyasociados.com', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(61, NULL, 4, 2, 'ACPACK SL', NULL, NULL, '', 'B36472868', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N'),
	(62, NULL, 1, 2, 'Schoeller Arca Systems SLU', NULL, NULL, '', 'B-30595524', '', NULL, '', 0.00, 0.00, NULL, NULL, '2011-09-13', 'prueba', 'S', 'N');
/*!40000 ALTER TABLE `entidad` ENABLE KEYS */;


# Dumping structure for table tierrina.entidad_rol
CREATE TABLE IF NOT EXISTS `entidad_rol` (
  `idUsuario` bigint(20) NOT NULL DEFAULT '0',
  `idRol` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUsuario`,`idRol`),
  KEY `idRol` (`idRol`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.entidad_rol: 0 rows
DELETE FROM `entidad_rol`;
/*!40000 ALTER TABLE `entidad_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidad_rol` ENABLE KEYS */;


# Dumping structure for table tierrina.entrega
CREATE TABLE IF NOT EXISTS `entrega` (
  `idEntrega` int(5) NOT NULL,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idEntrega`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.entrega: 0 rows
DELETE FROM `entrega`;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;


# Dumping structure for table tierrina.envase
CREATE TABLE IF NOT EXISTS `envase` (
  `idEnvase` bigint(20) NOT NULL AUTO_INCREMENT,
  `idMaterial` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(255) NOT NULL DEFAULT '',
  `descripcion` text,
  `dimensiones` varchar(255) DEFAULT NULL,
  `peso` double(10,2) DEFAULT NULL,
  `stock` double(10,2) DEFAULT NULL,
  `ean` double(10,2) DEFAULT NULL,
  `habilitado` char(1) DEFAULT 'S',
  PRIMARY KEY (`idEnvase`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.envase: 56 rows
DELETE FROM `envase`;
/*!40000 ALTER TABLE `envase` DISABLE KEYS */;
INSERT INTO `envase` (`idEnvase`, `idMaterial`, `nombre`, `descripcion`, `dimensiones`, `peso`, `stock`, `ean`, `habilitado`) VALUES
	(1, 1, 'Bolsa 15x35 cm trasparente -Hasta 1 Kg.', '', '', 0.00, 0.00, NULL, 'S'),
	(2, 1, 'Bolsa 30x50 cm Faba Fresca,Transp. galga 200-Hasta 2,5 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(3, 1, 'Bolsa 18x30 cm trasparente galga 300-Hasta 1 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(4, 1, 'Bolsa 18x19 cm trasparente galga 300-Hasta 0,5 Kg.', '', '18x19', 0.00, 0.00, NULL, 'S'),
	(5, 1, 'Bolsa PlÃ¡stico 0.5kg. Orbayu', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(6, 2, 'Saco Sin Marca 65x40 cm. Transparente-Hasta 15 Kg.', '', '', 0.00, 0.00, NULL, 'S'),
	(7, 2, 'Saco Sin Marca 67x35 cm con asa, Transp. -Hasta 10 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(8, 2, 'Saco Sin Marca 80x45 cm.  Blanco con fuelle - Hasta 25 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(9, 2, 'Saco Sin Marca 95x55 cm.  Blanco con fuelle- Hasta 50 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(10, 2, 'Saco "La Tierrina Vaqueira"  67x35 cm. con asa, Transp. -Hasta 10 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(11, 2, 'Saco "Legumbres FABES"  80x45 cm. Transparente. -Hasta 25 Kg.', 'saco laminado micro-perforado en caliente, agujero mediano y pre-separados para maquina envasadora.', '80 x 45', 10.00, 0.00, NULL, 'S'),
	(12, 2, 'Saco Recuperado todos tipos -Hasta 50 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(13, 2, 'Saco Big-Bag con tapa y descarga- Hasta 500 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(14, 2, 'Saco Big-Bag con tapa y descarga- Hasta 900 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(15, 3, 'Estuche Faba Ast. D.O. "Tierrina Vaqueira" 500 g.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(16, 3, 'Estuche Faba Ast. D.O. "Tierrina Vaqueira" 1 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(17, 3, 'Estuche Faba Verdina  "Tierrina Vaqueira" 500 g.', NULL, NULL, NULL, 0.00, NULL, 'S'),
	(18, 3, 'Caja Expositor Faba Ast. D.O."Tierrina Vaqueira" 24 Uds. x 500 g.', NULL, NULL, NULL, 0.00, 14.00, 'S'),
	(19, 3, 'Caja Expositor Faba Ast. D.O."Tierrina Vaqueira" 12 Uds. x 1 Kg..', NULL, NULL, NULL, 0.00, 14.00, 'S'),
	(20, 3, 'Caja Expositor Faba Verdina"Tierrina Vaqueira" 24 Uds. x 500 g.', NULL, NULL, NULL, 0.00, 14.00, 'S'),
	(21, 3, 'Caja Expositor Faba Granja "Orbayu" 21 Uds. x 700 g.', NULL, NULL, NULL, 0.00, 14.00, 'S'),
	(22, 3, 'Caja Expositor Faba Granja "Orbayu" 15 Uds. x 1 Kg.', NULL, NULL, NULL, 0.00, 14.00, 'S'),
	(23, 3, 'Caja  Faba Granja "Orbayu" 12 Uds. x 700 g', NULL, NULL, NULL, 0.00, 14.00, 'S'),
	(24, 3, 'Caja Preparado Fabada "Orbayu" 10 Uds. x 2 rac.', NULL, NULL, NULL, 0.00, 14.00, 'S'),
	(25, 3, 'Caja Faba Fresca Congelado "Tierrina Vaqueira" 4 Uds. x 2,5 Kg.', NULL, NULL, NULL, 0.00, 14.00, 'S'),
	(26, 3, 'Plancha cartÃ³n 25x14 cm. Preparado Fabada 2 rac.', NULL, NULL, NULL, 0.00, 13.00, 'S'),
	(27, 3, 'Plancha cartÃ³n 80 x120 paletizado', NULL, NULL, NULL, 0.00, 0.00, 'S'),
	(28, 4, 'Saquete Faba Granja "Orbayu" 500 g', NULL, NULL, NULL, 0.00, 13.00, 'S'),
	(29, 4, 'Saquete Faba Granja "Orbayu" 700 g', NULL, NULL, NULL, 0.00, 13.00, 'S'),
	(30, 4, 'Saquete tela 700gr. Orbayu', NULL, NULL, NULL, 0.00, 13.00, 'S'),
	(31, 4, 'Saquete Faba Granja ALDI "Orbayu" 700 g', '', '', 90.00, 0.00, 13.00, 'S'),
	(32, 4, 'Saquete Faba Granja "Orbayu" 1 Kg.', '', '', 900.00, 0.00, 13.00, 'S'),
	(33, 4, 'Saquete Faba Granja "La Aldea" 1 Kg.', '', '', 8000000.00, 0.00, 13.00, 'S'),
	(34, 4, 'Saquete Faba Granja "La Aldea" 500g.', '', '', 100.00, 0.00, 13.00, 'S'),
	(35, 4, 'Saquete Faba Ast D.O. "Tierra Astur" 1 Kg.', '', '', 100.00, 0.00, 13.00, 'S'),
	(36, 4, 'Saquete Faba Ast. D.O. "Sabrosona" 500 g.', '', '', 56.00, 0.00, 13.00, 'S'),
	(37, 4, 'Saquete Faba Ast D.O. "Tierrina Vaqueira" 500 g.', '', '', 68.00, 0.00, 13.00, 'S'),
	(38, 4, 'Saquete Faba Ast D.O. "Tierrina Vaqueira" 1 Kg.', '', '', 10.00, 0.00, 13.00, 'S'),
	(39, 4, 'Saquete Faba Ast D.O. "Tierrina Vaqueira" 5 Kg.', '', '', 10.00, 0.00, 13.00, 'S'),
	(40, 4, 'Saquete Faba Verdina "Tierrina Vaqueira" 500 g.', '', '', 10.00, 0.00, 13.00, 'S'),
	(41, 4, 'Saquete Faba Verdina "Tierrina Vaqueira" 1 Kg.', '', '', 2.00, 0.00, NULL, 'S'),
	(42, 4, 'Saquete Preparado Fabada "Orbayu"  2 rac.', '', '', 90.00, 0.00, NULL, 'S'),
	(43, 4, 'Saquete Preparado Fabada "Orbayu"  4 rac', '', '', 89.00, 0.00, 13.00, 'S'),
	(44, 5, 'Palet EUR 80x120    blanco', '', '', 22.00, 0.00, 14.00, 'S'),
	(45, 5, 'Palet EUR 80x120   pintado', '', '', 8.00, 0.00, 14.00, 'S'),
	(46, 5, 'Palet 80x120   ligero', '', '', 2.00, 0.00, 14.00, 'S'),
	(47, 5, 'Palet 100x120   Blanco', '', '', 3.00, 0.00, 14.00, 'S'),
	(48, 5, 'Palet 100x120   Pintado', '', '', 1.00, 0.00, 14.00, 'S'),
	(49, 5, 'Palet 100x120   ligero', '', '', 34.00, 0.00, 14.00, 'S'),
	(50, 5, 'Palet 110 x 120', '', '', NULL, 0.00, 14.00, 'S'),
	(51, 5, 'Palet 110 x 110', '', '', NULL, 0.00, 14.00, 'S'),
	(52, 5, 'Palet 120 x120', '', '', 10.00, 0.00, NULL, 'S'),
	(53, 5, 'Palet Otras medidas', '', '', 10.00, 0.00, NULL, 'S'),
	(54, 5, 'Caja madera 60x40 "Sallar"', '', '', 30.00, 0.00, NULL, 'S'),
	(55, 6, 'Caja Plastico Faba Fresca', '', '', 1.00, 0.00, NULL, 'S'),
	(56, 2, 'Saquete Faba Granja "Orbayu" 5 Kg.', NULL, NULL, NULL, 0.00, NULL, 'S');
/*!40000 ALTER TABLE `envase` ENABLE KEYS */;


# Dumping structure for table tierrina.estadofabas
CREATE TABLE IF NOT EXISTS `estadofabas` (
  `idEstadoFabas` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEstadoFabas`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.estadofabas: 12 rows
DELETE FROM `estadofabas`;
/*!40000 ALTER TABLE `estadofabas` DISABLE KEYS */;
INSERT INTO `estadofabas` (`idEstadoFabas`, `descripcion`) VALUES
	(0, 'Sin fallos'),
	(1, 'Partidas'),
	(2, 'Rajadas'),
	(3, 'Descorticadas'),
	(4, 'Incompletas'),
	(5, 'Manchadas'),
	(6, 'Mohosas'),
	(7, 'Descoloridas'),
	(8, 'Con impurezas'),
	(9, 'Mojadas'),
	(10, 'Germinadas'),
	(11, 'Dañadas');
/*!40000 ALTER TABLE `estadofabas` ENABLE KEYS */;


# Dumping structure for table tierrina.estadoproducto
CREATE TABLE IF NOT EXISTS `estadoproducto` (
  `idEstado` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.estadoproducto: 2 rows
DELETE FROM `estadoproducto`;
/*!40000 ALTER TABLE `estadoproducto` DISABLE KEYS */;
INSERT INTO `estadoproducto` (`idEstado`, `nombre`) VALUES
	(1, 'bueno'),
	(2, 'malo');
/*!40000 ALTER TABLE `estadoproducto` ENABLE KEYS */;


# Dumping structure for table tierrina.factura
CREATE TABLE IF NOT EXISTS `factura` (
  `idFactura` bigint(10) NOT NULL DEFAULT '0',
  `codigoFactura` varchar(15) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fechaVencimiento` date DEFAULT NULL,
  `pedido` varchar(20) DEFAULT NULL,
  `albaran` varchar(20) DEFAULT NULL,
  `importeTotal` double(10,2) DEFAULT NULL,
  `descuento` double(10,2) DEFAULT NULL,
  `valorIva` double(10,2) DEFAULT NULL,
  `subtotal` double(10,2) DEFAULT NULL,
  `cargosTotal` double(10,2) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`idFactura`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.factura: 0 rows
DELETE FROM `factura`;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;


# Dumping structure for table tierrina.factura_cargo
CREATE TABLE IF NOT EXISTS `factura_cargo` (
  `idFactura` bigint(20) DEFAULT NULL,
  `idCargo` tinyint(4) DEFAULT NULL,
  `tipoCargo` char(1) DEFAULT NULL,
  `valorCargo` double(8,2) DEFAULT NULL,
  `ivaCargo` double(8,2) DEFAULT NULL,
  `totalCargo` double(8,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.factura_cargo: 0 rows
DELETE FROM `factura_cargo`;
/*!40000 ALTER TABLE `factura_cargo` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_cargo` ENABLE KEYS */;


# Dumping structure for table tierrina.factura_item
CREATE TABLE IF NOT EXISTS `factura_item` (
  `idFactura` bigint(10) NOT NULL DEFAULT '0',
  `idItem` bigint(20) NOT NULL DEFAULT '0',
  `idRegistroSalida` bigint(20) DEFAULT NULL,
  `idProducto` bigint(10) DEFAULT NULL,
  `codigoItem` varchar(20) DEFAULT NULL,
  `cantidad` bigint(3) DEFAULT NULL,
  `precioUnitBruto` double(8,2) DEFAULT NULL,
  `precioUnitNeto` double(8,2) DEFAULT NULL,
  `precioTotal` double(8,2) DEFAULT NULL,
  `iva` double(8,2) DEFAULT NULL,
  PRIMARY KEY (`idItem`,`idFactura`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.factura_item: 0 rows
DELETE FROM `factura_item`;
/*!40000 ALTER TABLE `factura_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_item` ENABLE KEYS */;


# Dumping structure for table tierrina.fac_orders
CREATE TABLE IF NOT EXISTS `fac_orders` (
  `idOrders` bigint(20) NOT NULL AUTO_INCREMENT,
  `unh` varchar(50) DEFAULT NULL,
  `bgmTipo` varchar(50) DEFAULT NULL,
  `bgmNum` varchar(50) DEFAULT NULL,
  `bgmFunc` varchar(50) DEFAULT NULL,
  `aliCond` varchar(50) DEFAULT NULL,
  `ftxCali` varchar(50) DEFAULT NULL,
  `nadMs` varchar(50) DEFAULT NULL,
  `rffMs` varchar(50) DEFAULT NULL,
  `nadMr` varchar(50) DEFAULT NULL,
  `rffMr` varchar(50) DEFAULT NULL,
  `nadSu` varchar(50) DEFAULT NULL,
  `rffSu` varchar(50) DEFAULT NULL,
  `nadBy` varchar(50) DEFAULT NULL,
  `rffBy` varchar(50) DEFAULT NULL,
  `nadDp` varchar(50) DEFAULT NULL,
  `rffDp` varchar(50) DEFAULT NULL,
  `nadIv` varchar(50) DEFAULT NULL,
  `rffIv` varchar(50) DEFAULT NULL,
  `nadPr` varchar(50) DEFAULT NULL,
  `rffPr` varchar(50) DEFAULT NULL,
  `cux` varchar(50) DEFAULT NULL,
  `moa79` varchar(50) DEFAULT NULL,
  `cnt` varchar(50) DEFAULT NULL,
  `unt` varchar(50) DEFAULT NULL,
  `estado` char(1) DEFAULT 'R',
  `idFormaPago` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idOrders`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.fac_orders: 0 rows
DELETE FROM `fac_orders`;
/*!40000 ALTER TABLE `fac_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `fac_orders` ENABLE KEYS */;


# Dumping structure for table tierrina.fac_orders_dtm
CREATE TABLE IF NOT EXISTS `fac_orders_dtm` (
  `idOrders` bigint(20) DEFAULT NULL,
  `dtmFech` varchar(50) DEFAULT NULL,
  `dtmForm` varchar(50) DEFAULT NULL,
  `dtmFunc` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.fac_orders_dtm: 0 rows
DELETE FROM `fac_orders_dtm`;
/*!40000 ALTER TABLE `fac_orders_dtm` DISABLE KEYS */;
/*!40000 ALTER TABLE `fac_orders_dtm` ENABLE KEYS */;


# Dumping structure for table tierrina.fac_orders_imd
CREATE TABLE IF NOT EXISTS `fac_orders_imd` (
  `idOrders` bigint(20) DEFAULT NULL,
  `idLin` varchar(50) DEFAULT NULL,
  `imdTipo` varchar(50) DEFAULT NULL,
  `imdCara` varchar(50) DEFAULT NULL,
  `imdDesc` varchar(50) DEFAULT NULL,
  `imdDescCod` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.fac_orders_imd: 0 rows
DELETE FROM `fac_orders_imd`;
/*!40000 ALTER TABLE `fac_orders_imd` DISABLE KEYS */;
/*!40000 ALTER TABLE `fac_orders_imd` ENABLE KEYS */;


# Dumping structure for table tierrina.fac_orders_lin
CREATE TABLE IF NOT EXISTS `fac_orders_lin` (
  `idOrders` bigint(20) DEFAULT NULL,
  `idLin` varchar(50) DEFAULT NULL,
  `linNum` varchar(50) DEFAULT NULL,
  `piaNumSA` varchar(50) DEFAULT NULL,
  `piaNumIN` varchar(50) DEFAULT NULL,
  `imdTipo` varchar(50) DEFAULT NULL,
  `imdCara` varchar(50) DEFAULT NULL,
  `imdDesc` varchar(50) DEFAULT NULL,
  `imdDescCod` varchar(50) DEFAULT NULL,
  `qty21Cant` varchar(50) DEFAULT NULL,
  `qty59Cant` varchar(50) DEFAULT NULL,
  `moa203` varchar(50) DEFAULT NULL,
  `priAaa` varchar(50) DEFAULT NULL,
  `priAab` varchar(50) DEFAULT NULL,
  `priInf` varchar(50) DEFAULT NULL,
  `observacion` varchar(100) DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.fac_orders_lin: 0 rows
DELETE FROM `fac_orders_lin`;
/*!40000 ALTER TABLE `fac_orders_lin` DISABLE KEYS */;
/*!40000 ALTER TABLE `fac_orders_lin` ENABLE KEYS */;


# Dumping structure for table tierrina.fac_orders_loc
CREATE TABLE IF NOT EXISTS `fac_orders_loc` (
  `idOrders` varchar(50) DEFAULT NULL,
  `idLin` varchar(50) DEFAULT NULL,
  `idLoc` varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `qty` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.fac_orders_loc: 0 rows
DELETE FROM `fac_orders_loc`;
/*!40000 ALTER TABLE `fac_orders_loc` DISABLE KEYS */;
/*!40000 ALTER TABLE `fac_orders_loc` ENABLE KEYS */;


# Dumping structure for table tierrina.familia
CREATE TABLE IF NOT EXISTS `familia` (
  `idFamilia` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `stock` bigint(20) DEFAULT NULL,
  `stockMinimo` bigint(20) DEFAULT NULL,
  `familiaPadre_idFamilia` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idFamilia`),
  KEY `FKEA3E9B6DCCE7DA79` (`familiaPadre_idFamilia`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.familia: 5 rows
DELETE FROM `familia`;
/*!40000 ALTER TABLE `familia` DISABLE KEYS */;
INSERT INTO `familia` (`idFamilia`, `descripcion`, `stock`, `stockMinimo`, `familiaPadre_idFamilia`) VALUES
	(1, 'Legumbres', 0, 0, NULL),
	(2, 'Especias', 0, 0, NULL),
	(3, 'Preparado', 0, 0, NULL),
	(4, 'Especias', 0, 0, NULL),
	(5, 'Otros', 0, 0, NULL);
/*!40000 ALTER TABLE `familia` ENABLE KEYS */;


# Dumping structure for table tierrina.formapago
CREATE TABLE IF NOT EXISTS `formapago` (
  `idFormaPago` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idFormaPago`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.formapago: 11 rows
DELETE FROM `formapago`;
/*!40000 ALTER TABLE `formapago` DISABLE KEYS */;
INSERT INTO `formapago` (`idFormaPago`, `descripcion`) VALUES
	(1, 'Efectivo'),
	(2, 'Cheque'),
	(3, 'Letra 30 días'),
	(4, 'Letra 60 días'),
	(5, 'Letra 90 días'),
	(6, 'Abono bancario'),
	(7, 'Cargo bancario'),
	(8, 'Transferencia bancaria'),
	(9, 'Pagaré'),
	(10, 'Compensación de deuda'),
	(0, ' No aplica');
/*!40000 ALTER TABLE `formapago` ENABLE KEYS */;


# Dumping structure for table tierrina.formatoentrega
CREATE TABLE IF NOT EXISTS `formatoentrega` (
  `idFormatoEntrega` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idFormatoEntrega`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.formatoentrega: 5 rows
DELETE FROM `formatoentrega`;
/*!40000 ALTER TABLE `formatoentrega` DISABLE KEYS */;
INSERT INTO `formatoentrega` (`idFormatoEntrega`, `descripcion`) VALUES
	(1, 'No paletizado/Sacos'),
	(2, 'Pallet Europeo'),
	(3, 'Pallet Americano'),
	(4, 'Big Bag'),
	(5, 'Otros');
/*!40000 ALTER TABLE `formatoentrega` ENABLE KEYS */;


# Dumping structure for table tierrina.gestionproduccion
CREATE TABLE IF NOT EXISTS `gestionproduccion` (
  `idGestionProduccion` bigint(20) NOT NULL DEFAULT '0',
  `idProducto` bigint(20) DEFAULT NULL,
  `proceso` varchar(50) DEFAULT NULL,
  `orden` varchar(20) DEFAULT NULL,
  `codigoEntrada` varchar(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `habilitado` set('S','N') DEFAULT 'S',
  `horainicio` varchar(50) DEFAULT NULL,
  `horafin` varchar(50) DEFAULT NULL,
  `cantidadprod` double(10,2) DEFAULT NULL,
  `mermasIngredientes` double(8,2) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  `notasincidencias` varchar(200) DEFAULT NULL,
  `notasinstrucciones` varchar(200) DEFAULT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `estadoproceso` char(1) DEFAULT NULL,
  PRIMARY KEY (`idGestionProduccion`),
  KEY `idProducto` (`idProducto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gestionproduccion: 0 rows
DELETE FROM `gestionproduccion`;
/*!40000 ALTER TABLE `gestionproduccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `gestionproduccion` ENABLE KEYS */;


# Dumping structure for table tierrina.gestion_produccion_cribado
CREATE TABLE IF NOT EXISTS `gestion_produccion_cribado` (
  `idGestionProduccion` bigint(20) NOT NULL DEFAULT '0',
  `orden` varchar(20) NOT NULL DEFAULT '',
  `idCodigoEntradaProducto` varchar(20) NOT NULL DEFAULT '',
  `idProducto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idGestionProduccion`,`idCodigoEntradaProducto`,`orden`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gestion_produccion_cribado: 0 rows
DELETE FROM `gestion_produccion_cribado`;
/*!40000 ALTER TABLE `gestion_produccion_cribado` DISABLE KEYS */;
/*!40000 ALTER TABLE `gestion_produccion_cribado` ENABLE KEYS */;


# Dumping structure for table tierrina.gestion_produccion_desgranado
CREATE TABLE IF NOT EXISTS `gestion_produccion_desgranado` (
  `idGestionProduccion` bigint(20) NOT NULL DEFAULT '0',
  `orden` varchar(20) DEFAULT NULL,
  `idCodigoEntradaProducto` varchar(20) DEFAULT NULL,
  `idProducto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idGestionProduccion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gestion_produccion_desgranado: 0 rows
DELETE FROM `gestion_produccion_desgranado`;
/*!40000 ALTER TABLE `gestion_produccion_desgranado` DISABLE KEYS */;
/*!40000 ALTER TABLE `gestion_produccion_desgranado` ENABLE KEYS */;


# Dumping structure for table tierrina.gestion_produccion_envasado
CREATE TABLE IF NOT EXISTS `gestion_produccion_envasado` (
  `orden` varchar(20) DEFAULT NULL,
  `idGestionProduccion` varchar(20) DEFAULT NULL,
  `idGestionProduccionEnvasado` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoEntradaIngredientes` varchar(20) DEFAULT NULL,
  `idIngredientes` bigint(20) DEFAULT NULL,
  `cantidadIngredientes` bigint(20) DEFAULT NULL,
  `cantidadUsadaIngredientes` bigint(20) DEFAULT NULL,
  `mermaIngredientes` bigint(20) DEFAULT NULL,
  `codigoEntradaEnvases` varchar(20) DEFAULT NULL,
  `idEnvases` bigint(20) DEFAULT NULL,
  `cantidadEnvases` bigint(20) DEFAULT NULL,
  `cantidadUsadaEnvases` bigint(20) DEFAULT NULL,
  `mermaEnvases` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idGestionProduccionEnvasado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gestion_produccion_envasado: 0 rows
DELETE FROM `gestion_produccion_envasado`;
/*!40000 ALTER TABLE `gestion_produccion_envasado` DISABLE KEYS */;
/*!40000 ALTER TABLE `gestion_produccion_envasado` ENABLE KEYS */;


# Dumping structure for table tierrina.gestion_produccion_registroactividad
CREATE TABLE IF NOT EXISTS `gestion_produccion_registroactividad` (
  `idRegistro` bigint(20) NOT NULL AUTO_INCREMENT,
  `idOrden` bigint(20) DEFAULT NULL,
  `horaInicio` datetime DEFAULT NULL,
  `horaFin` datetime DEFAULT NULL,
  `responsable` varchar(20) DEFAULT NULL,
  `observaciones` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idRegistro`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gestion_produccion_registroactividad: 0 rows
DELETE FROM `gestion_produccion_registroactividad`;
/*!40000 ALTER TABLE `gestion_produccion_registroactividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `gestion_produccion_registroactividad` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_congelado
CREATE TABLE IF NOT EXISTS `gp_congelado` (
  `orden` varchar(20) NOT NULL DEFAULT '',
  `codigoEntrada` varchar(20) NOT NULL DEFAULT '',
  `cantidadDisponible` double(10,2) DEFAULT NULL,
  `cantidadUsable` double(10,2) DEFAULT NULL,
  `mermaProducto` double(10,2) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigoEntrada`,`orden`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gp_congelado: 0 rows
DELETE FROM `gp_congelado`;
/*!40000 ALTER TABLE `gp_congelado` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_congelado` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_congelado_ubicacion
CREATE TABLE IF NOT EXISTS `gp_congelado_ubicacion` (
  `orden` varchar(20) NOT NULL DEFAULT '',
  `codigoEntrada` varchar(20) NOT NULL DEFAULT '',
  `idUbicacion` bigint(20) NOT NULL DEFAULT '0',
  `idUbicacionNueva` bigint(20) DEFAULT NULL,
  `cantidad` double(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`orden`,`codigoEntrada`,`idUbicacion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

# Dumping data for table tierrina.gp_congelado_ubicacion: 0 rows
DELETE FROM `gp_congelado_ubicacion`;
/*!40000 ALTER TABLE `gp_congelado_ubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_congelado_ubicacion` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_cribado
CREATE TABLE IF NOT EXISTS `gp_cribado` (
  `idGestionProduccion` bigint(20) NOT NULL DEFAULT '0',
  `orden` varchar(20) NOT NULL DEFAULT '',
  `idCodigoEntradaProducto` varchar(20) NOT NULL DEFAULT '',
  `idProducto` bigint(20) DEFAULT NULL,
  `cantidadUsable` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idGestionProduccion`,`idCodigoEntradaProducto`,`orden`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gp_cribado: 0 rows
DELETE FROM `gp_cribado`;
/*!40000 ALTER TABLE `gp_cribado` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_cribado` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_desgranado
CREATE TABLE IF NOT EXISTS `gp_desgranado` (
  `idGestionProduccion` bigint(20) NOT NULL DEFAULT '0',
  `orden` varchar(20) DEFAULT NULL,
  `idCodigoEntradaProducto` varchar(20) DEFAULT NULL,
  `idProducto` bigint(20) DEFAULT NULL,
  `cantidadUsable` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idGestionProduccion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gp_desgranado: 0 rows
DELETE FROM `gp_desgranado`;
/*!40000 ALTER TABLE `gp_desgranado` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_desgranado` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_envasado
CREATE TABLE IF NOT EXISTS `gp_envasado` (
  `idEnvasado` bigint(20) NOT NULL DEFAULT '0',
  `idProducto` bigint(20) NOT NULL DEFAULT '0',
  `orden` varchar(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fechaCaducidad` date DEFAULT NULL,
  `lote` varchar(20) DEFAULT NULL,
  `horainicio` datetime DEFAULT NULL,
  `horafin` datetime DEFAULT NULL,
  `cantidad` double(8,2) DEFAULT NULL,
  `mermasMP` double(8,2) DEFAULT NULL,
  `mermasEN` double(8,2) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `estadoproceso` char(1) DEFAULT NULL,
  `habilitado` set('S','N') DEFAULT 'S',
  `elaborado` double(8,2) DEFAULT '0.00',
  `precioCoste` double(8,2) DEFAULT '0.00',
  `ubicado` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`idEnvasado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gp_envasado: 0 rows
DELETE FROM `gp_envasado`;
/*!40000 ALTER TABLE `gp_envasado` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_envasado` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_envasado_detalle
CREATE TABLE IF NOT EXISTS `gp_envasado_detalle` (
  `idLinea` bigint(20) NOT NULL AUTO_INCREMENT,
  `idTipoRegistro` set('E','M','P') DEFAULT NULL,
  `orden` varchar(20) DEFAULT NULL,
  `codigoEntrada` varchar(20) DEFAULT NULL,
  `idProducto` bigint(20) DEFAULT NULL,
  `cantidadDisponible` double(8,2) DEFAULT NULL,
  `cantidadTeorica` double(8,2) DEFAULT NULL,
  `cantidadReal` double(8,2) DEFAULT NULL,
  `mermaProducto` double(8,2) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `idUbicacion` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idLinea`)
) ENGINE=MyISAM AUTO_INCREMENT=149 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gp_envasado_detalle: 0 rows
DELETE FROM `gp_envasado_detalle`;
/*!40000 ALTER TABLE `gp_envasado_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_envasado_detalle` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_envasado_registroactividad
CREATE TABLE IF NOT EXISTS `gp_envasado_registroactividad` (
  `idRegistro` bigint(20) NOT NULL AUTO_INCREMENT,
  `idOrden` varchar(20) DEFAULT NULL,
  `horaInicio` datetime DEFAULT NULL,
  `horaFin` datetime DEFAULT NULL,
  `responsable` varchar(20) DEFAULT NULL,
  `observaciones` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idRegistro`)
) ENGINE=MyISAM AUTO_INCREMENT=201 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

# Dumping data for table tierrina.gp_envasado_registroactividad: 0 rows
DELETE FROM `gp_envasado_registroactividad`;
/*!40000 ALTER TABLE `gp_envasado_registroactividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_envasado_registroactividad` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_envasado_ubicacion
CREATE TABLE IF NOT EXISTS `gp_envasado_ubicacion` (
  `ordenEnvasado` varchar(20) NOT NULL DEFAULT '',
  `codigoEntrada` varchar(20) NOT NULL DEFAULT '',
  `idHueco` bigint(20) NOT NULL DEFAULT '0',
  `cantidad` double(8,2) NOT NULL DEFAULT '0.00',
  `cantidadReal` double(8,2) NOT NULL DEFAULT '0.00',
  `mermas` double(8,2) NOT NULL DEFAULT '0.00',
  `idHuecoNuevo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ordenEnvasado`,`codigoEntrada`,`idHueco`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

# Dumping data for table tierrina.gp_envasado_ubicacion: 0 rows
DELETE FROM `gp_envasado_ubicacion`;
/*!40000 ALTER TABLE `gp_envasado_ubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_envasado_ubicacion` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_fumigado
CREATE TABLE IF NOT EXISTS `gp_fumigado` (
  `orden` varchar(20) NOT NULL DEFAULT '',
  `codigoEntrada` varchar(20) NOT NULL DEFAULT '',
  `cantidadDisponible` double(10,2) DEFAULT NULL,
  `cantidadUsable` double(10,2) DEFAULT NULL,
  `mermaProducto` double(10,2) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigoEntrada`,`orden`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gp_fumigado: 0 rows
DELETE FROM `gp_fumigado`;
/*!40000 ALTER TABLE `gp_fumigado` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_fumigado` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_fumigado_ubicacion
CREATE TABLE IF NOT EXISTS `gp_fumigado_ubicacion` (
  `orden` varchar(20) NOT NULL DEFAULT '',
  `codigoEntrada` varchar(20) NOT NULL DEFAULT '',
  `idHueco` bigint(20) NOT NULL DEFAULT '0',
  `idHuecoNuevo` bigint(20) DEFAULT NULL,
  `cantidad` double(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`orden`,`codigoEntrada`,`idHueco`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

# Dumping data for table tierrina.gp_fumigado_ubicacion: 0 rows
DELETE FROM `gp_fumigado_ubicacion`;
/*!40000 ALTER TABLE `gp_fumigado_ubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_fumigado_ubicacion` ENABLE KEYS */;


# Dumping structure for table tierrina.gp_seleccion
CREATE TABLE IF NOT EXISTS `gp_seleccion` (
  `orden` varchar(20) NOT NULL DEFAULT '',
  `codigoEntrada` varchar(20) NOT NULL DEFAULT '',
  `tipoSeleccion` set('M','A') NOT NULL DEFAULT '',
  `cantidadDisponible` double(10,2) DEFAULT NULL,
  `cantidadUsable` double(10,2) DEFAULT NULL,
  `mermaProducto` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`codigoEntrada`,`orden`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.gp_seleccion: 0 rows
DELETE FROM `gp_seleccion`;
/*!40000 ALTER TABLE `gp_seleccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `gp_seleccion` ENABLE KEYS */;


# Dumping structure for table tierrina.grupo_producto
CREATE TABLE IF NOT EXISTS `grupo_producto` (
  `idGrupoProducto` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`idGrupoProducto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.grupo_producto: 6 rows
DELETE FROM `grupo_producto`;
/*!40000 ALTER TABLE `grupo_producto` DISABLE KEYS */;
INSERT INTO `grupo_producto` (`idGrupoProducto`, `nombre`) VALUES
	(1, 'Fabas'),
	(2, 'Faba Fresca'),
	(3, 'Alubias Color'),
	(4, 'Alubias Blancas'),
	(5, 'Otras Legumbres'),
	(6, 'Otros Productos');
/*!40000 ALTER TABLE `grupo_producto` ENABLE KEYS */;


# Dumping structure for table tierrina.homologacion_proveedor
CREATE TABLE IF NOT EXISTS `homologacion_proveedor` (
  `idUsuario` bigint(20) NOT NULL DEFAULT '0',
  `idRequisito` tinyint(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUsuario`,`idRequisito`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.homologacion_proveedor: 0 rows
DELETE FROM `homologacion_proveedor`;
/*!40000 ALTER TABLE `homologacion_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `homologacion_proveedor` ENABLE KEYS */;


# Dumping structure for table tierrina.impuestos
CREATE TABLE IF NOT EXISTS `impuestos` (
  `idImpuesto` bigint(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `porcentaje` double unsigned DEFAULT NULL,
  PRIMARY KEY (`idImpuesto`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.impuestos: 3 rows
DELETE FROM `impuestos`;
/*!40000 ALTER TABLE `impuestos` DISABLE KEYS */;
INSERT INTO `impuestos` (`idImpuesto`, `nombre`, `porcentaje`) VALUES
	(1, 'IVA 18%', 18),
	(2, 'IVA 8%', 8),
	(3, 'IVA 4%', 4);
/*!40000 ALTER TABLE `impuestos` ENABLE KEYS */;


# Dumping structure for table tierrina.incidencia
CREATE TABLE IF NOT EXISTS `incidencia` (
  `idIncidencia` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idIncidencia`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.incidencia: 6 rows
DELETE FROM `incidencia`;
/*!40000 ALTER TABLE `incidencia` DISABLE KEYS */;
INSERT INTO `incidencia` (`idIncidencia`, `nombre`) VALUES
	(1, 'Cribar'),
	(2, 'Congelar'),
	(3, 'Fumigar'),
	(4, 'Seleccionar'),
	(5, 'Desgranar'),
	(6, 'Otra');
/*!40000 ALTER TABLE `incidencia` ENABLE KEYS */;


# Dumping structure for table tierrina.incidenciaproducto
CREATE TABLE IF NOT EXISTS `incidenciaproducto` (
  `idIncidencia` bigint(20) NOT NULL AUTO_INCREMENT,
  `loteProducto` varchar(20) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `descripcion` varchar(255) NOT NULL DEFAULT '""',
  `cantidad` double(8,2) DEFAULT NULL,
  `idUbicacion` bigint(20) DEFAULT NULL,
  `descripcionUbicacion` varchar(50) DEFAULT NULL,
  `valor` double(10,2) DEFAULT '0.00',
  PRIMARY KEY (`idIncidencia`),
  KEY `FK237229F98193FD3A` (`loteProducto`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.incidenciaproducto: 0 rows
DELETE FROM `incidenciaproducto`;
/*!40000 ALTER TABLE `incidenciaproducto` DISABLE KEYS */;
/*!40000 ALTER TABLE `incidenciaproducto` ENABLE KEYS */;


# Dumping structure for table tierrina.lopd
CREATE TABLE IF NOT EXISTS `lopd` (
  `idLinea` bigint(20) NOT NULL DEFAULT '0',
  `texto` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idLinea`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

# Dumping data for table tierrina.lopd: 0 rows
DELETE FROM `lopd`;
/*!40000 ALTER TABLE `lopd` DISABLE KEYS */;
/*!40000 ALTER TABLE `lopd` ENABLE KEYS */;


# Dumping structure for table tierrina.maquina
CREATE TABLE IF NOT EXISTS `maquina` (
  `idMaquina` bigint(10) NOT NULL DEFAULT '0',
  `idTipo` bigint(10) NOT NULL DEFAULT '0',
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `fecha` date NOT NULL,
  `descripcion` varchar(200) DEFAULT '',
  PRIMARY KEY (`idMaquina`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.maquina: 0 rows
DELETE FROM `maquina`;
/*!40000 ALTER TABLE `maquina` DISABLE KEYS */;
/*!40000 ALTER TABLE `maquina` ENABLE KEYS */;


# Dumping structure for table tierrina.maquina_calibracion
CREATE TABLE IF NOT EXISTS `maquina_calibracion` (
  `idCalibrado` bigint(20) NOT NULL,
  `patron` varchar(50) DEFAULT '',
  `medidaPatron` varchar(50) DEFAULT '',
  `medidaEquipo` varchar(50) DEFAULT '',
  `desviacion` varchar(50) DEFAULT '',
  PRIMARY KEY (`idCalibrado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.maquina_calibracion: 0 rows
DELETE FROM `maquina_calibracion`;
/*!40000 ALTER TABLE `maquina_calibracion` DISABLE KEYS */;
/*!40000 ALTER TABLE `maquina_calibracion` ENABLE KEYS */;


# Dumping structure for table tierrina.maquina_mantenimiento
CREATE TABLE IF NOT EXISTS `maquina_mantenimiento` (
  `idMantenimiento` bigint(10) NOT NULL AUTO_INCREMENT,
  `idTipo` bigint(10) NOT NULL DEFAULT '0',
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `descripcion` varchar(200) DEFAULT '',
  PRIMARY KEY (`idMantenimiento`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.maquina_mantenimiento: 0 rows
DELETE FROM `maquina_mantenimiento`;
/*!40000 ALTER TABLE `maquina_mantenimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `maquina_mantenimiento` ENABLE KEYS */;


# Dumping structure for table tierrina.maquina_mantenimiento_ciclo
CREATE TABLE IF NOT EXISTS `maquina_mantenimiento_ciclo` (
  `idCiclo` bigint(10) NOT NULL,
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idCiclo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.maquina_mantenimiento_ciclo: 6 rows
DELETE FROM `maquina_mantenimiento_ciclo`;
/*!40000 ALTER TABLE `maquina_mantenimiento_ciclo` DISABLE KEYS */;
INSERT INTO `maquina_mantenimiento_ciclo` (`idCiclo`, `descripcion`) VALUES
	(1, 'Diario'),
	(2, 'Semanal'),
	(3, 'Quincenal'),
	(4, 'Mensual'),
	(5, 'Trimestral'),
	(6, 'Anual');
/*!40000 ALTER TABLE `maquina_mantenimiento_ciclo` ENABLE KEYS */;


# Dumping structure for table tierrina.maquina_mantenimiento_programacion
CREATE TABLE IF NOT EXISTS `maquina_mantenimiento_programacion` (
  `idMantenimientoProgramacion` bigint(10) NOT NULL,
  `idMaquina` bigint(10) NOT NULL,
  `idMantenimiento` bigint(10) NOT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `fechaProgramada` date NOT NULL,
  `fechaRealizada` date DEFAULT NULL,
  `responsable` varchar(20) DEFAULT NULL,
  `estado` char(1) NOT NULL DEFAULT 'P',
  `idCiclo` bigint(10) NOT NULL,
  `idCalibrado` bigint(10) NOT NULL DEFAULT '0',
  `clasificacion` char(1) NOT NULL DEFAULT 'P',
  PRIMARY KEY (`idMaquina`,`idMantenimiento`,`idMantenimientoProgramacion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.maquina_mantenimiento_programacion: 0 rows
DELETE FROM `maquina_mantenimiento_programacion`;
/*!40000 ALTER TABLE `maquina_mantenimiento_programacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `maquina_mantenimiento_programacion` ENABLE KEYS */;


# Dumping structure for table tierrina.maquina_tipo
CREATE TABLE IF NOT EXISTS `maquina_tipo` (
  `idTipo` bigint(10) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idTipo`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.maquina_tipo: 3 rows
DELETE FROM `maquina_tipo`;
/*!40000 ALTER TABLE `maquina_tipo` DISABLE KEYS */;
INSERT INTO `maquina_tipo` (`idTipo`, `descripcion`) VALUES
	(1, 'Equipos'),
	(2, 'Instalación'),
	(3, 'Transporte');
/*!40000 ALTER TABLE `maquina_tipo` ENABLE KEYS */;


# Dumping structure for table tierrina.material
CREATE TABLE IF NOT EXISTS `material` (
  `idMaterial` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idMaterial`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.material: 6 rows
DELETE FROM `material`;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` (`idMaterial`, `nombre`) VALUES
	(1, 'Bolsas'),
	(2, 'Sacos PP'),
	(3, 'Cartón'),
	(4, 'Saquetes tela'),
	(5, 'Madera'),
	(6, 'Otros Materiales');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;


# Dumping structure for table tierrina.materiaprima
CREATE TABLE IF NOT EXISTS `materiaprima` (
  `idProducto` bigint(20) NOT NULL DEFAULT '0',
  `idGrupo` bigint(20) DEFAULT NULL,
  `nombre` varchar(200) NOT NULL DEFAULT '',
  `tipo` char(1) DEFAULT 'M',
  `stock` double(10,2) DEFAULT NULL,
  `habilitado` set('S','N') DEFAULT 'S',
  PRIMARY KEY (`idProducto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.materiaprima: 39 rows
DELETE FROM `materiaprima`;
/*!40000 ALTER TABLE `materiaprima` DISABLE KEYS */;
INSERT INTO `materiaprima` (`idProducto`, `idGrupo`, `nombre`, `tipo`, `stock`, `habilitado`) VALUES
	(1, 1, 'Faba Asturiana DO', 'M', 0.00, 'S'),
	(2, 1, 'Faba Granja Importación', 'M', 0.00, 'S'),
	(3, 1, 'Faba Granja Nacional', 'M', 0.00, 'S'),
	(4, 1, 'Faba Verdina Asturiana', 'M', 0.00, 'S'),
	(5, 1, 'Faba Verdina Importación', 'M', 0.00, 'S'),
	(6, 2, 'Faba Fresca', 'M', 0.00, 'S'),
	(7, 2, 'Faba Fresca Vaina', 'M', 0.00, 'S'),
	(8, 3, 'Alubia Roxa', 'M', 0.00, 'S'),
	(9, 3, 'Alubia Canaria', 'M', 0.00, 'S'),
	(10, 3, 'Alubia Pinta Redonda', 'M', 0.00, 'S'),
	(11, 3, 'Alubia Pinta Larga', 'M', 0.00, 'S'),
	(12, 3, 'Alubia Caparrón', 'M', 0.00, 'S'),
	(13, 3, 'Alubia Morada Redonda', 'M', 0.00, 'S'),
	(14, 3, 'Alubia Morada Larga', 'M', 0.00, 'S'),
	(15, 3, 'Alubia Palmeña Redonda', 'M', 0.00, 'S'),
	(16, 3, 'Alubia Palmeña Larga', 'M', 0.00, 'S'),
	(17, 3, 'Alubia Canela', 'M', 0.00, 'S'),
	(18, 3, 'Alubia Negra Tolosana', 'M', 0.00, 'S'),
	(19, 3, 'Frijol Negrito', 'M', 0.00, 'S'),
	(20, 3, 'Soja', 'M', 0.00, 'S'),
	(21, 3, 'Frijol Castilla', 'M', 0.00, 'S'),
	(22, 4, 'Alubia Canellini', 'M', 0.00, 'S'),
	(23, 4, 'Alubia Planchada', 'M', 0.00, 'S'),
	(24, 4, 'Alubia Plancheta', 'M', 0.00, 'S'),
	(25, 4, 'Alubia Redonda', 'M', 0.00, 'S'),
	(26, 4, 'Judión Grande', 'M', 0.00, 'S'),
	(27, 4, 'Judión Mediano', 'M', 0.00, 'S'),
	(28, 5, 'Harina Maíz', 'M', 0.00, 'S'),
	(29, 5, 'Harina Maíz Torrada', 'M', 0.00, 'S'),
	(30, 5, 'Arroz Redondo', 'M', 0.00, 'S'),
	(31, 5, 'Arroz Largo', 'M', 0.00, 'S'),
	(32, 5, 'Arroz Vaporizado', 'M', 0.00, 'S'),
	(33, 5, 'Lenteja Pardina', 'M', 0.00, 'S'),
	(34, 5, 'Lenteja Stom', 'M', 0.00, 'S'),
	(35, 5, 'Lenteja Castellana', 'M', 0.00, 'S'),
	(36, 5, 'Garbanzo Castellano Gordo', 'M', 0.00, 'S'),
	(37, 5, 'Garbanzo Castellano Mediano', 'M', 0.00, 'S'),
	(38, 5, 'Garbanzo Pedrosillanco', 'M', 0.00, 'S'),
	(39, 5, 'Garbanzo Fuentesaúco', 'M', 0.00, 'S');
/*!40000 ALTER TABLE `materiaprima` ENABLE KEYS */;


# Dumping structure for table tierrina.materiaprima_categoria
CREATE TABLE IF NOT EXISTS `materiaprima_categoria` (
  `idMateriaCategoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `idMateriaPrima` bigint(20) NOT NULL,
  `idCategoria` bigint(20) NOT NULL,
  `stock` double(8,2) NOT NULL DEFAULT '0.00',
  `habilitado` char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (`idMateriaCategoria`)
) ENGINE=MyISAM AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.materiaprima_categoria: 25 rows
DELETE FROM `materiaprima_categoria`;
/*!40000 ALTER TABLE `materiaprima_categoria` DISABLE KEYS */;
INSERT INTO `materiaprima_categoria` (`idMateriaCategoria`, `idMateriaPrima`, `idCategoria`, `stock`, `habilitado`) VALUES
	(1, 9, 2, 0.00, 'S'),
	(2, 14, 3, 0.00, 'S'),
	(3, 13, 0, 0.00, 'S'),
	(4, 22, 2, 0.00, 'S'),
	(5, 16, 0, 0.00, 'S'),
	(6, 12, 3, 0.00, 'S'),
	(7, 14, 0, 0.00, 'S'),
	(8, 22, 0, 0.00, 'S'),
	(9, 18, 2, 0.00, 'S'),
	(10, 21, 4, 0.00, 'S'),
	(11, 12, 2, 0.00, 'S'),
	(12, 17, 2, 0.00, 'S'),
	(13, 17, 0, 0.00, 'S'),
	(14, 17, 0, 0.00, 'S'),
	(15, 22, 0, 0.00, 'S'),
	(16, 17, 1, 0.00, 'S'),
	(17, 10, 2, 0.00, 'S'),
	(18, 10, 3, 0.00, 'S'),
	(19, 2, 2, 0.00, 'S'),
	(20, 2, 5, 0.00, 'S'),
	(21, 9, 4, 0.00, 'S'),
	(22, 9, 3, 0.00, 'S'),
	(23, 1, 2, 0.00, 'S'),
	(24, 16, 5, 0.00, 'S'),
	(26, 22, 1, 0.00, 'S');
/*!40000 ALTER TABLE `materiaprima_categoria` ENABLE KEYS */;


# Dumping structure for table tierrina.materiaprima_envase
CREATE TABLE IF NOT EXISTS `materiaprima_envase` (
  `idProducto` bigint(20) NOT NULL DEFAULT '0',
  `idEnvase` bigint(20) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.materiaprima_envase: 0 rows
DELETE FROM `materiaprima_envase`;
/*!40000 ALTER TABLE `materiaprima_envase` DISABLE KEYS */;
/*!40000 ALTER TABLE `materiaprima_envase` ENABLE KEYS */;


# Dumping structure for table tierrina.ordenentrada
CREATE TABLE IF NOT EXISTS `ordenentrada` (
  `idOrden` bigint(20) NOT NULL DEFAULT '0',
  `codigoOrden` varchar(25) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `idTipoRegistro` char(1) DEFAULT NULL,
  `idProveedor` bigint(20) DEFAULT NULL,
  `idTransportista` bigint(20) DEFAULT NULL,
  `origen` varchar(50) DEFAULT NULL,
  `albaran` varchar(50) DEFAULT NULL,
  `descVehiculo` varchar(100) DEFAULT NULL,
  `notasVehiculo` varchar(250) DEFAULT NULL,
  `habilitado` char(1) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idOrden`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ordenentrada: 0 rows
DELETE FROM `ordenentrada`;
/*!40000 ALTER TABLE `ordenentrada` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordenentrada` ENABLE KEYS */;


# Dumping structure for table tierrina.ordenentrada_tmp
CREATE TABLE IF NOT EXISTS `ordenentrada_tmp` (
  `idOrden` bigint(20) NOT NULL DEFAULT '0',
  `codigoOrden` varchar(25) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `idTipoRegistro` char(1) DEFAULT NULL,
  `idProveedor` bigint(20) DEFAULT NULL,
  `idTransportista` bigint(20) DEFAULT NULL,
  `origen` varchar(50) DEFAULT NULL,
  `albaran` varchar(50) DEFAULT NULL,
  `descVehiculo` varchar(100) DEFAULT NULL,
  `notasVehiculo` varchar(250) DEFAULT NULL,
  `habilitado` char(1) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idOrden`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

# Dumping data for table tierrina.ordenentrada_tmp: 0 rows
DELETE FROM `ordenentrada_tmp`;
/*!40000 ALTER TABLE `ordenentrada_tmp` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordenentrada_tmp` ENABLE KEYS */;


# Dumping structure for table tierrina.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `idProducto` bigint(20) NOT NULL AUTO_INCREMENT,
  `idCategoria` bigint(20) NOT NULL DEFAULT '0',
  `idEstado` bigint(20) DEFAULT '0',
  `idTipo` bigint(20) DEFAULT NULL,
  `idGrupo` bigint(20) DEFAULT NULL,
  `idMateriaPrima` bigint(20) DEFAULT NULL,
  `familia_idFamilia` bigint(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `codigoEan` varchar(14) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `stock` double(8,2) DEFAULT '0.00',
  `stockMinimo` double(8,2) DEFAULT '0.00',
  `idBolsa` bigint(10) DEFAULT NULL,
  `idSaco` bigint(10) DEFAULT NULL,
  `idCarton` bigint(10) DEFAULT NULL,
  `idSaquete` bigint(10) DEFAULT NULL,
  `idMadera` bigint(10) DEFAULT NULL,
  `idOtro` bigint(10) DEFAULT NULL,
  `proveedor_idUsuario` bigint(20) DEFAULT NULL,
  `cliente_idUsuario` bigint(20) DEFAULT NULL,
  `productoComposicion_idProducto` bigint(20) DEFAULT NULL,
  `codigoProducto` varchar(50) DEFAULT NULL,
  `precio` double(8,2) DEFAULT NULL,
  `precioCoste` double(8,2) DEFAULT NULL,
  `idImpuesto` bigint(20) NOT NULL DEFAULT '1',
  `foto` varchar(50) DEFAULT 'nohay.jpg',
  `fechaActualizacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `habilitado` char(1) NOT NULL DEFAULT 'S',
  `peso` double(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`idProducto`),
  KEY `FKF2D1C16016780DBF` (`productoComposicion_idProducto`),
  KEY `FKF2D1C160DF420B37` (`cliente_idUsuario`),
  KEY `FKF2D1C160AB05831B` (`proveedor_idUsuario`),
  KEY `FKF2D1C1606FF9C26D` (`familia_idFamilia`),
  KEY `idCategoria` (`idCategoria`),
  KEY `idEstado` (`idEstado`),
  KEY `idTipo` (`idTipo`),
  KEY `idGrupo` (`idGrupo`)
) ENGINE=MyISAM AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.producto: 97 rows
DELETE FROM `producto`;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`idProducto`, `idCategoria`, `idEstado`, `idTipo`, `idGrupo`, `idMateriaPrima`, `familia_idFamilia`, `fecha`, `codigoEan`, `nombre`, `descripcion`, `stock`, `stockMinimo`, `idBolsa`, `idSaco`, `idCarton`, `idSaquete`, `idMadera`, `idOtro`, `proveedor_idUsuario`, `cliente_idUsuario`, `productoComposicion_idProducto`, `codigoProducto`, `precio`, `precioCoste`, `idImpuesto`, `foto`, `fechaActualizacion`, `habilitado`, `peso`) VALUES
	(1, 0, NULL, NULL, 1, NULL, 0, '2010-02-23', '8436016090087', 'Faba Asturiana DO - Tierrina Vaqueira - 1Kg - Cartón', 'Faba Asturiana DO - Extra - Estuche de Cartón 1kg. Tierrina Vaqueira', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 1, 'nohay.jpg', '2011-08-19 08:16:39', 'S', 0.00),
	(71, 1, NULL, NULL, 1, NULL, 1, '2011-03-29', '0000000000002', 'Agrupación Faba D.O. "SABROSONA" 500 g.', 'Saco PP 10 uds x 500 g. Faba D.O. Marca "Sabrosona"', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, NULL, 3, 'nohay.jpg', '2011-10-21 09:55:40', 'S', 0.00),
	(5, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090094', 'Faba Asturiana DO - Extra -"TIERRINA VAQUEIRA"  Estuche 500 g. ', 'Estuche cartón Faba Asturiana DO -"TIERRINA VAQUEIRA"  -  500g. ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(7, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090032', 'Faba Asturiana DO - Extra - Saco 5kg Tierrina Vaqueira', 'Faba Asturiana DO - Extra - Saco 5kg Tierrina Vaqueira', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(8, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090049', 'Faba Asturiana DO - Extra - Saco 10kg Tierrina Vaqueira', 'Faba Asturiana DO - Extra - Saco 10kg Tierrina Vaqueira', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(10, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090810', 'Paquete Vacio "Tierrina Vaqueira" Faba Asturiana DO - Extra - Envase 400 g.', 'Faba Asturiana DO - Extra - Envase 400 g.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(11, 1, NULL, NULL, 1, NULL, 1, '2011-02-18', '8436016095013', 'Faba de Granja I. - Extra A - Saco 10kg.', 'Faba de Granja I. - Extra A - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, NULL, 3, 'nohay.jpg', '2011-06-06 13:17:30', 'S', 100.00),
	(12, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095020', 'Faba de Granja I. - Extra A - Saco 15kg.', 'Faba de Granja I. - Extra A - Saco 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 45.71, 3, 'nohay.jpg', '2011-06-06 13:17:30', 'S', 0.00),
	(13, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095037', 'Faba de Granja I. - Extra A - Saco 25kg.', 'Faba de Granja I. - Extra A - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(14, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095044', 'Faba de Granja I. - Extra A - Saco 50kg.', 'Faba de Granja I. - Extra A - Saco 50kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(15, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095105', 'Faba de Granja I. - Extra B - Saco 10kg.', 'Faba de Granja I. - Extra B - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(16, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095112', 'Faba de Granja I. - Extra B - Saco 15kg.', 'Faba de Granja I. - Extra B - Saco 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(17, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095129', 'Faba de Granja I. - Extra B - Saco 25kg.', 'Faba de Granja I. - Extra B - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(18, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095136', 'Faba de Granja I. - Extra B - Saco 50kg.', 'Faba de Granja I. - Extra B - Saco 50kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(19, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095204', 'Faba de Granja I. - Primera - Saco 10kg.', 'Faba de Granja I. - Primera - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 414.02, 3, 'nohay.jpg', '2011-06-06 13:17:30', 'S', 0.00),
	(20, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095211', 'Faba de Granja I. - Primera - Saco 15kg.', 'Faba de Granja I. - Primera - Saco 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(21, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095228', 'Faba de Granja I. - Primera - Saco 25kg.', 'Faba de Granja I. - Primera - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(22, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095228', 'Faba de Granja I. - Primera - Saco 50kg.', 'Faba de Granja I. - Primera - Saco 50kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(23, 4, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095303', 'Faba de Granja I. - Segunda - Saco 10kg.', 'Faba de Granja I. - Segunda - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(24, 4, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095310', 'Faba de Granja I. - Segunda - Saco 15kg.', 'Faba de Granja I. - Segunda - Saco 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(25, 4, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095327', 'Faba de Granja I. - Segunda - Saco 25kg.', 'Faba de Granja I. - Segunda - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(26, 4, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095334', 'Faba de Granja I. - Segunda - Saco 50kg.', 'Faba de Granja I. - Segunda - Saco 50kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(27, 6, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095396', 'Faba de Granja I. - Destrio - Saco 25kg.', 'Faba de Granja I. - Destrio - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(28, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090131', 'Saquete "Orbayu" Faba de Granja I. - Extra A Extra B -  500 gr. ', 'Saquete "Orbayu" Faba de Granja I. - Extra A Extra B -  500 gr. ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(29, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090148', 'Saquete "Orbayu" " Faba de Granja I. - Extra A Extra B - Saquete tela 700gr. ', 'Saquete "Orbayu" Faba de Granja I. - Extra A Extra B -  700gr.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(30, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090124', 'Saquete "Orbayu" Faba de Granja I. - Extra A Extra B - 1 Kg. ', 'Saquete "Orbayu" Faba de Granja I. - Extra A Extra B - 1 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:25:44', 'S', 0.00),
	(31, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095006', 'Faba de Granja I. - Extra A Extra B - Saco 5kg. ', 'Faba de Granja I. - Extra A Extra B - Saco 5kg. ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(33, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090506', 'Bolsa Plástico Faba de Granja I. - Extra A Extra B - 1 Kg. ', 'Bolsa Plástico con Etiqueta- Faba de Granja I. - Extra A Extra B - 1 Kg. ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(34, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090605', 'Paquete Vacio "Orbayu" Faba de Granja I. - Extra A Extra B - Envase  200gr ', 'Paquete Vacio "Orbayu" Faba de Granja I. - Extra A Extra B - Envase  200gr ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(35, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090612', 'Paquete Vacio "Orbayu" Faba de Granja I. - Extra A Extra B - Envase  400 g.', 'Paquete Vacio "Orbayu" Faba de Granja I. - Extra A Extra B - Envase  400 g.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(36, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090209', 'Faba de Granja I.- Extra A Extra B - Saquete tela 500gr. La Aldea', 'Faba de Granja I. - Extra A Extra B - Saquete tela 500gr. La Aldea', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(37, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090216', 'Faba de Granja I. - Extra A Extra B - Saquete tela 1kg. La Aldea', 'Faba de Granja I. - Extra A Extra B - Saquete tela 1kg. La Aldea', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(38, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095402', 'Faba Granja Nacional - Extra A - Saco 10kg.', 'Faba Granja Nacional - Extra A - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(39, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095419', 'Faba Granja Nacional - Extra A - Saco 15kg.', 'Faba Granja Nacional - Extra A - Saco 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(40, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095426', 'Faba Granja Nacional - Extra A - Saco 25kg.', 'Faba Granja Nacional - Extra A - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(41, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095433', 'Faba Granja Nacional - Extra A - Saco 50kg.', 'Faba Granja Nacional - Extra A - Saco 50kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(42, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095518', 'Faba Granja Nacional - Primera - Saco 10kg.', 'Faba Granja Nacional(Granjilla) - Primera - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(43, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095525', 'Faba Granja Nacional - Primera - Saco 15kg.', 'Faba Granja Nacional(Granjilla) - Primera - Saco 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(44, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095532', 'Faba Granja Nacional - Primera - Saco 25kg.', 'Faba Granja Nacional(Granjilla) - Primera - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(45, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095549', 'Faba Granja Nacional - Primera - Saco 50kg.', 'Faba Granja Nacional -(Granjilla) - Saco 50kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(46, 4, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095600', 'Faba Granja Nacional - Segunda - Saco 10kg.', 'Faba Granja Nacional - (Manchada)Segunda - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(47, 4, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095617', 'Faba Granja Nacional - Segunda - Saco 15kg.', 'Faba Granja Nacional - (Manchada)Segunda - Saco 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(48, 4, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095624', 'Faba Granja Nacional - Segunda - Saco 25kg.', 'Faba Granja Nacional - (Manchada)Segunda - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(49, 4, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095631', 'Faba Granja Nacional - Segunda - Saco 50kg.', 'Faba Granja Nacional - (Manchada)Segunda - Saco 50kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(50, 6, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095693', 'Faba Granja Nacional - Destrio - Saco 25kg.', 'Faba Granja Nacional - Destrio - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(56, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095716', 'Faba Verdina Asturiana - Extra - Saco 10kg.', 'Faba Verdina Asturiana - Extra - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(57, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095723', 'Faba Verdina Asturiana - Extra - Saco 15kg.', 'Faba Verdina Asturiana - Extra - Saco 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(58, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095730', 'Faba Verdina Asturiana - Extra - Saco 25kg.', 'Faba Verdina Asturiana - Extra - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(59, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095747', 'Faba Verdina Asturiana - Extra - Saco 50kg.', 'Faba Verdina Asturiana - Extra - Saco 50kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(60, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095815', 'Faba Verdina Asturiana - Primera - Saco 10kg.', 'Faba Verdina Asturiana -(Blanqueada-Siembra) - Saco 10kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(61, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095808', 'Faba Verdina Asturiana - Primera - Saco 5kg.', 'Faba VerdinaAsturiana(Blanqueada-Siembra) - Saco 5kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(62, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095822', 'Faba Verdina Asturiana - Primera - Saco 25kg.', 'Faba Verdina asturiana (Blanqueada-Siembra) - Saco 25kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(63, 3, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016095709', 'Faba Verdina Asturiana- Extra - Saco 5 kg.', 'Faba Verdina Asturiana Extra - Saco 5 kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(64, 1, NULL, NULL, 1, NULL, 1, '2011-04-04', '8436016097017', 'Faba Fresca - Extra - Bolsa plástica 2.5kg.', 'Faba Fresca - Extra - Bolsa plástica 2.5kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:33', 'S', 0.00),
	(65, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016097024', 'Faba Fresca - Extra - Bolsa plástica 0.5kg. (Muestra)', 'Faba Fresca - Extra - Bolsa plástica 0.5kg. (Muestra)', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(66, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016097000', 'Faba Fresca - Extra - . Bolsa Plástica 1 kg', 'Faba Fresca - Extra - . Bolsa Plástica 1 kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(67, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '0000000000001', 'Agrupación Faba Fresca - Extra - Caja 10 kg. 2.5 x 4 uds.', 'Faba Fresca - Extra - "Tierrina Vaqueira" Caja Cartón 10 kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-09-09 12:21:24', 'S', 0.00),
	(68, 1, NULL, NULL, 1, NULL, 0, '2011-03-23', '8436016097505', 'Faba Fresca en Vaina - Extra - Caja  plástica plegable 15kg.', 'Faba Fresca en Vaina - Extra - Caja  plástica plegable 15kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(2, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090018', 'Faba Asturiana DO - Tierrina Vaqueira - 1Kg - Saquete Tela', 'Faba Asturiana DO - Extra - Saquete de tela 1kg. Tierrina Vaqueira', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:13:50', 'S', 0.00),
	(6, 2, NULL, NULL, 1, NULL, 1, '2010-02-23', '0000000008888', 'Faba Ast. DO - Extra "SABROSONA" Saquete tela 500gr. ', 'Faba Asturiana DO - Extra - Saquete tela 500gr. Sabrosona', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, NULL, 3, 'nohay.jpg', '2011-10-21 10:07:43', 'S', 0.50),
	(72, 1, NULL, NULL, 1, NULL, 1, '2011-03-29', '18436016090084', 'Agrupación N1 - Faba D.O. "TIERRINA VAQUEIRA"  1 Kg.', 'Caja Expositor  Faba Asturiana D.O. 12 Uds. x 1 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(73, 1, NULL, NULL, 1, NULL, 1, '2011-03-29', '28436016090098', 'Agrupación  Faba D.O. "Tierrina Vaqueira" 24 uds x 500 g. ', 'Caja Expositor  Faba Asturiana D.O. 24 Uds. x 500 g.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(32, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090513', 'Bolsa Plástico con etiqueta Faba de Granja I.- Extra A Extra B -  500gr. ', 'Bolsa Plástico con etiqueta Faba de Granja I.- Extra A Extra B -  500gr.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-10-20 13:06:59', 'S', 0.00),
	(9, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436016090803', 'Paquete vacio Faba Asturiana DO - Extra - Envase  200 gr.', 'Paquete vacío "Tierrina Vaqueira" Faba Asturiana DO - Extra - Envase 200 gr.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:01', 'S', 0.00),
	(74, 0, 1, NULL, 0, NULL, 0, '2011-04-04', '8436016098007', 'Paquete Compango para Fabada Asturiana(1-1-1) Al vacio', 'Paquete Compango para Fabada Asturiana(1-1-1) Al vacio', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(75, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096003', 'Saco 10 Kg. Faba Roxa Nacional', 'Saco 10 Kg. Faba Roxa Nacional', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(76, 1, NULL, NULL, 3, NULL, 1, '2011-04-04', '8436016096010', 'Saco Faba Amarilla(Roxa) Nacional 15 Kg ', 'Saco Faba Amarilla(Roxa) Nacional 15 Kg ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:33', 'S', 0.00),
	(77, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096027', 'Saco Faba Amarilla(Roxa) Nacional 25 Kg ', 'Saco Faba Amarilla(Roxa) Nacional 25 Kg ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(78, 1, NULL, NULL, 3, NULL, 1, '2011-04-04', '8436016096058', 'Saco Alubia Amarilla redonda León 10 Kg ', 'Saco Alubia Amarilla redonda León 10 Kg ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:33', 'S', 0.00),
	(79, 1, NULL, NULL, 3, NULL, 1, '2011-04-04', '8436016096065', 'Saco Alubia Amarilla redonda León 15 Kg ', 'Saco Alubia Amarilla redonda León 15 Kg ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:33', 'S', 0.00),
	(80, 1, NULL, NULL, 3, NULL, 1, '2011-04-04', '8436016096102', 'Saco Alubia Pinta redonda 15 Kg', 'Saco Alubia Pinta redonda 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:33', 'S', 0.00),
	(81, 1, NULL, NULL, 3, NULL, 1, '2011-04-04', '8436016096157', 'Saco Alubia  Pinta larga 15 Kg', 'Saco Alubia  Pinta larga 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:33', 'S', 0.00),
	(82, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096201', 'Saco Alubia Canela 15 Kg.', 'Saco Alubia Canela 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(83, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096256', 'Saco Alubia Morada redonda  15 Kg ', 'Saco Alubia Morada redonda  15 Kg ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(84, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096300', 'Saco Alubia Morada larga  15 Kg ', 'Saco Alubia Morada larga  15 Kg ', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(85, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096355', 'Saco Alubia Caparrón Larga  15 Kg.', 'Saco Alubia Caparrón Larga  15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(86, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096409', 'Saco Alubia Mandilín -(Caparron redonda)  15 Kg.', 'Saco Alubia Mandilín -(Caparron redonda)  15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(87, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096454', 'Saco Alubia Palmeña redonda 15 Kg.', 'Saco Alubia Palmeña redonda 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(88, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096508', 'Saco Alubia Palmeña larga 15 Kg.', 'Saco Alubia Palmeña larga 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(89, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096553', 'Saco Frijol Negrito 15 Kg.', 'Saco Frijol Negrito 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(90, 1, NULL, NULL, 3, NULL, 1, '2011-04-04', '8436016096607', 'Saco Alubia Negra Tolosana 15 Kg.', 'Saco Alubia Negra Tolosana 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-04-08 12:09:33', 'S', 0.00),
	(91, 1, 1, NULL, 3, NULL, 1, '2011-04-04', '8436016096652', 'Saco Soja Mungo 15 Kg', 'Saco Soja Mungo 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(92, 1, 1, NULL, 4, NULL, 1, '2011-04-04', '8436016096751', 'Saco Alubia Riñón (canellini) 15 Kg.', 'Saco Alubia Riñón (canellini) 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(93, 1, 1, NULL, 4, NULL, 1, '2011-04-04', '8436016096850', 'Saco Judión Grande 15 Kg.', 'Saco Judión Grande 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(94, 1, 1, NULL, 4, NULL, 1, '2011-04-04', '8436016096881', 'Saco Judión Mediano 15 Kg.', 'Saco Judión Mediano 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(95, 1, 1, NULL, 4, NULL, 1, '2011-04-04', '8436016096904', 'Saco Alubia Planchada 15 Kg', 'Saco Alubia Planchada 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(96, 1, 1, NULL, 4, NULL, 1, '2011-04-04', '8436016096935', 'Saco Alubia Plancheta 15 Kg.', 'Saco Alubia Plancheta 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(97, 1, 1, NULL, 4, NULL, 1, '2011-04-04', '8436016096966', 'Saco Alubia redonda Blanca 15 Kg.', 'Saco Alubia redonda Blanca 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(98, 1, 1, NULL, 5, NULL, 1, '2011-04-04', '8436016094009', 'Saco Lenteja Pardina 15 Kg.', 'Saco Lenteja Pardina 15 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(99, 1, 1, NULL, 5, NULL, 1, '2011-04-04', '8436016094054', 'Saco Lenteja Castellana 15 Kg', 'Saco Lenteja Castellana 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(100, 0, 1, NULL, 0, NULL, 0, '2011-04-04', '8436016094108', 'Saco Garbanzo Pedrosillano Nacional 15 Kg', 'Saco Garbanzo Pedrosillano Nacional 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(101, 1, 1, NULL, 5, NULL, 1, '2011-04-04', '8436016094153', 'Saco Garbanzo Castellano Gordo 15 Kg', 'Saco Garbanzo Castellano Gordo 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(102, 1, 1, NULL, 5, NULL, 1, '2011-04-04', '8436016094207', 'Saco Garbanzo Castellano Mediano 15 Kg', 'Saco Garbanzo Castellano Mediano 15 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(103, 1, NULL, NULL, 6, NULL, 5, '2011-04-04', '8436016098106', 'Bolsa Harina Maíz 1 Kg.', 'Bolsa Harina Maíz 1 Kg', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, NULL, 3, 'nohay.jpg', '2011-05-19 15:43:08', 'S', 1.00),
	(104, 1, 1, NULL, 6, NULL, 5, '2011-04-04', '8436016098144', 'Bolsa Harina Maíz  "Torrada"  1 Kg.', 'Bolsa Harina Maíz "Torrada"  1 Kg.', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(105, 1, 1, NULL, 5, NULL, 1, '2011-04-04', '8436016094702', 'Saco Arroz Redondo 15 Kg.', '', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'nohay.jpg', '2011-07-15 06:13:01', 'S', 0.00),
	(3, 1, NULL, NULL, 1, NULL, 1, '2010-02-23', '8436027260028', 'Faba Ast. DO - TIERRA ASTUR - 1Kg  - Saquete Tela', 'Faba Asturiana DO - Extra - Saquete de tela 1kg. "Tierra Astur"', 0.00, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 8.81, 3, 'nohay.jpg', '2011-09-01 12:46:34', 'S', 0.00);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;


# Dumping structure for table tierrina.producto_compuesto
CREATE TABLE IF NOT EXISTS `producto_compuesto` (
  `idProducto` bigint(20) NOT NULL,
  `idCompuestoDe` bigint(20) NOT NULL DEFAULT '0',
  `cantidad` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProducto`,`idCompuestoDe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.producto_compuesto: 3 rows
DELETE FROM `producto_compuesto`;
/*!40000 ALTER TABLE `producto_compuesto` DISABLE KEYS */;
INSERT INTO `producto_compuesto` (`idProducto`, `idCompuestoDe`, `cantidad`) VALUES
	(71, 6, 10),
	(72, 1, 12),
	(73, 5, 24);
/*!40000 ALTER TABLE `producto_compuesto` ENABLE KEYS */;


# Dumping structure for table tierrina.producto_envase
CREATE TABLE IF NOT EXISTS `producto_envase` (
  `idProducto` bigint(20) NOT NULL DEFAULT '0',
  `idEnvase` bigint(20) NOT NULL DEFAULT '0',
  `cantidad` double DEFAULT NULL,
  PRIMARY KEY (`idProducto`,`idEnvase`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.producto_envase: 98 rows
DELETE FROM `producto_envase`;
/*!40000 ALTER TABLE `producto_envase` DISABLE KEYS */;
INSERT INTO `producto_envase` (`idProducto`, `idEnvase`, `cantidad`) VALUES
	(2, 48, 1),
	(110, 6, 1),
	(5, 15, 1),
	(6, 36, 1),
	(7, 10, 1),
	(8, 13, 1),
	(11, 3, 1),
	(12, 6, 1),
	(66, 1, 1),
	(14, 9, 1),
	(15, 7, 0),
	(16, 11, 1),
	(17, 11, 1),
	(18, 9, 0),
	(19, 6, 1),
	(20, 6, 1),
	(21, 11, 1),
	(22, 9, 1),
	(23, 6, 1),
	(24, 11, 1),
	(25, 11, 1),
	(26, 9, 1),
	(27, 12, 1),
	(28, 31, 1),
	(29, 32, 1),
	(30, 42, 1),
	(31, 7, 1),
	(32, 1, 1),
	(33, 1, 1),
	(34, 52, 1),
	(35, 53, 1),
	(36, 44, 1),
	(37, 43, 1),
	(38, 7, 1),
	(39, 11, 1),
	(40, 11, 1),
	(41, 9, 1),
	(42, 6, 1),
	(43, 11, 1),
	(44, 11, 1),
	(45, 9, 1),
	(46, 11, 1),
	(47, 11, 1),
	(48, 11, 1),
	(49, 9, 1),
	(50, 12, 1),
	(56, 7, 1),
	(57, 6, 1),
	(58, 11, 1),
	(59, 9, 1),
	(60, 7, 1),
	(61, 7, 1),
	(62, 8, 1),
	(63, 7, 1),
	(64, 2, 1),
	(65, 1, 1),
	(1, 19, 1),
	(3, 35, 1),
	(104, 13, 1),
	(1, 3, 1),
	(11, 7, 1),
	(5, 4, 1),
	(67, 25, 1),
	(68, 55, 0),
	(9, 42, 1),
	(9, 26, 1),
	(75, 6, 1),
	(76, 6, 1),
	(77, 11, 1),
	(78, 6, 1),
	(79, 6, 1),
	(80, 6, 1),
	(81, 6, 1),
	(82, 6, 1),
	(83, 6, 1),
	(84, 6, 1),
	(85, 6, 1),
	(86, 6, 1),
	(87, 6, 1),
	(88, 6, 1),
	(89, 6, 1),
	(90, 6, 1),
	(91, 6, 1),
	(92, 6, 1),
	(93, 6, 1),
	(94, 6, 1),
	(95, 6, 1),
	(96, 6, 1),
	(97, 6, 1),
	(98, 6, 1),
	(99, 6, 1),
	(100, 6, 1),
	(101, 6, 1),
	(102, 6, 1),
	(105, 6, 1),
	(106, 55, 1),
	(107, 51, 1),
	(108, 47, 1);
/*!40000 ALTER TABLE `producto_envase` ENABLE KEYS */;


# Dumping structure for table tierrina.producto_materiaprima
CREATE TABLE IF NOT EXISTS `producto_materiaprima` (
  `idProducto` bigint(20) NOT NULL DEFAULT '0',
  `idMateriaPrima` bigint(20) NOT NULL DEFAULT '0',
  `cantidad` double DEFAULT NULL,
  PRIMARY KEY (`idProducto`,`idMateriaPrima`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

# Dumping data for table tierrina.producto_materiaprima: 96 rows
DELETE FROM `producto_materiaprima`;
/*!40000 ALTER TABLE `producto_materiaprima` DISABLE KEYS */;
INSERT INTO `producto_materiaprima` (`idProducto`, `idMateriaPrima`, `cantidad`) VALUES
	(2, 1, 1),
	(6, 23, 0.5),
	(7, 1, 5),
	(8, 1, 10),
	(11, 2, 10),
	(12, 2, 15),
	(13, 2, 25),
	(14, 2, 50),
	(15, 2, 10),
	(16, 2, 15),
	(17, 2, 25),
	(18, 2, 50),
	(19, 2, 10),
	(20, 2, 15),
	(21, 2, 25),
	(22, 2, 50),
	(23, 2, 10),
	(24, 2, 15),
	(25, 2, 25),
	(26, 2, 50),
	(27, 2, 25),
	(28, 2, 0.5),
	(29, 2, 0.7),
	(30, 2, 1),
	(31, 2, 5),
	(32, 2, 0.5),
	(33, 2, 1),
	(34, 2, 0.2),
	(35, 2, 0.4),
	(36, 2, 0.5),
	(37, 2, 1),
	(38, 3, 10),
	(39, 3, 15),
	(40, 3, 25),
	(41, 3, 50),
	(42, 3, 10),
	(43, 3, 15),
	(44, 3, 25),
	(45, 3, 50),
	(46, 3, 10),
	(47, 3, 15),
	(48, 3, 25),
	(49, 3, 50),
	(50, 3, 25),
	(56, 4, 10),
	(57, 4, 15),
	(58, 4, 25),
	(59, 4, 50),
	(60, 4, 10),
	(61, 4, 5),
	(62, 4, 25),
	(63, 4, 5),
	(64, 6, 2.5),
	(65, 6, 0.5),
	(1, 1, 1),
	(68, 7, 15),
	(3, 23, 0.5),
	(5, 1, 0.5),
	(66, 6, 1),
	(9, 1, 0.2),
	(10, 1, 0.4),
	(75, 8, 10),
	(76, 8, 15),
	(77, 8, 25),
	(78, 9, 10),
	(79, 9, 15),
	(80, 10, 15),
	(81, 11, 15),
	(82, 17, 15),
	(83, 13, 15),
	(84, 14, 15),
	(85, 12, 15),
	(86, 12, 15),
	(87, 15, 15),
	(88, 16, 15),
	(89, 19, 15),
	(90, 18, 15),
	(91, 20, 15),
	(92, 22, 15),
	(93, 26, 15),
	(94, 27, 15),
	(95, 23, 15),
	(96, 24, 15),
	(97, 25, 15),
	(98, 33, 15),
	(99, 35, 15),
	(100, 38, 15),
	(101, 36, 15),
	(102, 37, 15),
	(104, 29, 1),
	(105, 30, 15),
	(106, 18, 0.5),
	(106, 17, 0.5),
	(107, 20, 0.5),
	(107, 19, 0.5),
	(108, 10, 0.5);
/*!40000 ALTER TABLE `producto_materiaprima` ENABLE KEYS */;


# Dumping structure for table tierrina.producto_merma
CREATE TABLE IF NOT EXISTS `producto_merma` (
  `idMerma` bigint(20) NOT NULL DEFAULT '0',
  `idProducto` bigint(20) NOT NULL DEFAULT '0',
  `idUbicacion` bigint(20) NOT NULL DEFAULT '0',
  `fecha` date DEFAULT NULL,
  `cantidad` double(15,2) DEFAULT NULL,
  `responsable` varchar(100) DEFAULT NULL,
  `motivo` text,
  PRIMARY KEY (`idMerma`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.producto_merma: 0 rows
DELETE FROM `producto_merma`;
/*!40000 ALTER TABLE `producto_merma` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto_merma` ENABLE KEYS */;


# Dumping structure for table tierrina.producto_registrosalida
CREATE TABLE IF NOT EXISTS `producto_registrosalida` (
  `PRODUCTO_idProducto` bigint(20) NOT NULL DEFAULT '0',
  `registroSalidas_idRegistroSalida` bigint(20) NOT NULL DEFAULT '0',
  KEY `FK171588BA8193FD3A` (`PRODUCTO_idProducto`),
  KEY `FK171588BA4CAE9E99` (`registroSalidas_idRegistroSalida`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.producto_registrosalida: 0 rows
DELETE FROM `producto_registrosalida`;
/*!40000 ALTER TABLE `producto_registrosalida` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto_registrosalida` ENABLE KEYS */;


# Dumping structure for table tierrina.producto_ubicacion
CREATE TABLE IF NOT EXISTS `producto_ubicacion` (
  `PRODUCTO_idProducto` bigint(20) NOT NULL DEFAULT '0',
  `ubicaciones_idUbicacion` bigint(20) NOT NULL DEFAULT '0',
  KEY `FKD7CE10604F841CD1` (`ubicaciones_idUbicacion`),
  KEY `FKD7CE10608193FD3A` (`PRODUCTO_idProducto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.producto_ubicacion: 0 rows
DELETE FROM `producto_ubicacion`;
/*!40000 ALTER TABLE `producto_ubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto_ubicacion` ENABLE KEYS */;


# Dumping structure for table tierrina.provincia
CREATE TABLE IF NOT EXISTS `provincia` (
  `idProvincia` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idProvincia`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.provincia: 51 rows
DELETE FROM `provincia`;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` (`idProvincia`, `nombre`) VALUES
	(0, ' No aplica'),
	(1, 'Alava'),
	(2, 'Albacete'),
	(3, 'Alicante'),
	(4, 'Almería'),
	(5, 'Asturias'),
	(6, 'Ávila'),
	(7, 'Bajadoz'),
	(8, 'Barcelona'),
	(9, 'Burgos'),
	(10, 'Cáceres'),
	(11, 'Cádiz'),
	(12, 'Cantabria'),
	(13, 'Ciudad Real'),
	(14, 'Córdoba'),
	(15, 'Cuenca'),
	(16, 'Gerona'),
	(17, 'Granada'),
	(18, 'Guadalajara'),
	(19, 'Guipúzcoa'),
	(20, 'Huelva'),
	(21, 'Huesca'),
	(22, 'Islas Baleares'),
	(23, 'Jaén'),
	(24, 'La Coruña'),
	(25, 'La Rioja'),
	(26, 'Las Palmas'),
	(27, 'León'),
	(28, 'Lugo'),
	(29, 'Madrid'),
	(30, 'Málaga'),
	(31, 'Murcia'),
	(32, 'Navarra'),
	(33, 'Orense'),
	(34, 'Palencia'),
	(35, 'Pontevedra'),
	(36, 'Salamanca'),
	(37, 'Santa Cruz de Tenerife'),
	(38, 'Segovia'),
	(39, 'Sevilla'),
	(40, 'Soria'),
	(41, 'Tarragona'),
	(42, 'Teruel'),
	(43, 'Toledo'),
	(44, 'Valencia'),
	(45, 'Valladolid'),
	(46, 'Vizcaya'),
	(47, 'Zamora'),
	(48, 'Zaragoza'),
	(49, 'Castellón'),
	(50, 'Lérida');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;


# Dumping structure for table tierrina.referenciabancaria
CREATE TABLE IF NOT EXISTS `referenciabancaria` (
  `idDatoBancario` bigint(20) NOT NULL AUTO_INCREMENT,
  `idBanco` bigint(20) NOT NULL DEFAULT '0',
  `idFormaPago` bigint(20) NOT NULL DEFAULT '0',
  `empresa_idUsuario` bigint(20) NOT NULL DEFAULT '0',
  `numCuenta` varchar(50) NOT NULL DEFAULT '0',
  `limiteCredito` varchar(100) DEFAULT NULL,
  `fechaVencimiento` date DEFAULT NULL,
  `fechaPago` date DEFAULT NULL,
  `oficina` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idDatoBancario`),
  KEY `FK3C0C89B918B1F06` (`empresa_idUsuario`),
  KEY `idBanco` (`idBanco`),
  KEY `idFormaPago` (`idFormaPago`)
) ENGINE=MyISAM AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.referenciabancaria: 62 rows
DELETE FROM `referenciabancaria`;
/*!40000 ALTER TABLE `referenciabancaria` DISABLE KEYS */;
INSERT INTO `referenciabancaria` (`idDatoBancario`, `idBanco`, `idFormaPago`, `empresa_idUsuario`, `numCuenta`, `limiteCredito`, `fechaVencimiento`, `fechaPago`, `oficina`) VALUES
	(1, 1, 9, 1, '', '', '2011-01-01', '2011-01-01', NULL),
	(2, 1, 0, 2, '', '', '2011-01-01', '2011-01-01', NULL),
	(3, 1, 0, 3, '', '', '2011-01-01', '2011-01-01', NULL),
	(4, 1, 0, 4, '', '', '2011-01-01', '2011-01-01', NULL),
	(5, 1, 0, 5, '', '', '2011-01-01', '2011-01-01', NULL),
	(6, 1, 0, 6, '', '', '2011-01-01', '2011-01-01', NULL),
	(7, 1, 0, 7, '', '', '2011-01-01', '2011-01-01', NULL),
	(8, 1, 0, 8, '', '', '2011-01-01', '2011-01-01', NULL),
	(9, 1, 0, 9, '', '', '2011-01-01', '2011-01-01', NULL),
	(10, 1, 0, 10, '', '', '2011-01-01', '2011-01-01', NULL),
	(11, 1, 8, 11, '', '3000', '2011-01-01', '2011-01-01', NULL),
	(12, 1, 1, 12, '', '100', '2011-01-01', '2011-01-01', NULL),
	(13, 1, 0, 13, '', '', '2011-01-01', '2011-01-01', NULL),
	(14, 1, 0, 14, '', '40000', '2011-01-01', '2011-01-01', NULL),
	(15, 1, 0, 15, '', '', '2011-01-01', '2011-01-01', NULL),
	(16, 1, 0, 16, '', '', '2011-01-01', '2011-01-01', NULL),
	(17, 1, 0, 17, '', '1500', '2011-01-01', '2011-01-01', NULL),
	(18, 1, 0, 18, '', '1000', '2011-01-01', '2011-01-01', NULL),
	(19, 1, 0, 19, '', '600', '2011-01-01', '2011-01-01', NULL),
	(20, 1, 1, 20, '', '600', '2011-01-01', '2011-01-01', NULL),
	(21, 1, 0, 21, '', '', '2011-01-01', '2011-01-01', NULL),
	(22, 1, 1, 22, '', '', '2011-01-01', '2011-01-01', NULL),
	(23, 1, 0, 23, '', '600', '2011-01-01', '2011-01-01', NULL),
	(24, 1, 1, 24, '', '1500', '2011-01-01', '2011-01-01', NULL),
	(25, 1, 6, 25, '', '1000', '2011-01-01', '2011-01-01', NULL),
	(26, 1, 0, 26, '', '600', '2011-01-01', '2011-01-01', NULL),
	(27, 1, 1, 27, '', '2000', '2011-01-01', '2011-01-01', NULL),
	(28, 1, 7, 28, '', '300', '2011-01-01', '2011-01-01', NULL),
	(29, 1, 7, 29, '', '150', '2011-01-01', '2011-01-01', NULL),
	(30, 1, 7, 30, '', '300', '2011-01-01', '2011-01-01', NULL),
	(31, 1, 0, 31, '', '600', '2011-01-01', '2011-01-01', NULL),
	(32, 1, 8, 32, '', '1500', '2011-01-01', '2011-01-01', NULL),
	(33, 1, 0, 33, '', '', '2011-01-01', '2011-01-01', NULL),
	(34, 1, 8, 34, '', '2000', '2011-01-01', '2011-01-01', NULL),
	(35, 1, 0, 35, '', '', '2011-01-01', '2011-01-01', NULL),
	(36, 1, 0, 36, '', '', '2011-01-01', '2011-01-01', NULL),
	(37, 1, 0, 37, '', '', '2011-01-01', '2011-01-01', NULL),
	(38, 1, 0, 38, '', '', '2011-01-01', '2011-01-01', NULL),
	(39, 1, 0, 39, '', '', '2011-01-01', '2011-01-01', NULL),
	(40, 1, 0, 40, '', '', '2011-01-01', '2011-01-01', NULL),
	(41, 1, 0, 41, '', '', '2011-01-01', '2011-01-01', NULL),
	(42, 1, 0, 42, '', '', '2011-01-01', '2011-01-01', NULL),
	(45, 1, 0, 45, '', '', '2011-01-01', '2011-01-01', NULL),
	(44, 1, 0, 44, '', '', '2011-01-01', '2011-01-01', NULL),
	(43, 1, 0, 43, '', '', '2011-01-01', '2011-01-01', NULL),
	(46, 1, 0, 46, '', '', '2011-01-01', '2011-01-01', NULL),
	(47, 1, 0, 47, '', '', '2011-01-01', '2011-01-01', NULL),
	(48, 1, 0, 48, '', '', '2011-01-01', '2011-01-01', NULL),
	(49, 1, 0, 49, '', '', '2011-01-01', '2011-01-01', NULL),
	(50, 1, 0, 50, '', '', '2011-01-01', '2011-01-01', NULL),
	(51, 1, 0, 51, '', '', '2011-01-01', '2011-01-01', NULL),
	(52, 1, 0, 52, '', '', '2011-01-01', '2011-01-01', NULL),
	(53, 1, 0, 53, '', '', '2011-01-01', '2011-01-01', NULL),
	(54, 3, 0, 54, '0182 0847 96 0210048415', '', '2011-01-01', '2011-01-01', NULL),
	(55, 1, 0, 55, '', '', '2011-01-01', '2011-01-01', NULL),
	(56, 1, 0, 56, '', '', '2011-01-01', '2011-01-01', NULL),
	(57, 1, 0, 57, '', '', '2011-01-01', '2011-01-01', NULL),
	(58, 1, 0, 58, '', '', '2011-01-01', '2011-01-01', NULL),
	(59, 1, 0, 59, '', '', '2011-01-01', '2011-01-01', NULL),
	(60, 1, 0, 60, '', '', '2011-01-01', '2011-01-01', NULL),
	(61, 1, 7, 61, '2048 0186 18 3400002003', '', '2011-01-01', '2011-01-01', NULL),
	(62, 1, 7, 62, '', '', '2011-01-01', '2011-01-01', NULL);
/*!40000 ALTER TABLE `referenciabancaria` ENABLE KEYS */;


# Dumping structure for table tierrina.registroentrada
CREATE TABLE IF NOT EXISTS `registroentrada` (
  `idEntrada` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoOrden` varchar(20) DEFAULT NULL,
  `idEntradaPadre` bigint(20) DEFAULT NULL,
  `idProducto` bigint(20) DEFAULT '0',
  `idCategoria` bigint(20) DEFAULT NULL,
  `idFormatoEntrega` bigint(20) DEFAULT NULL,
  `idCosecha` bigint(20) DEFAULT NULL,
  `idTipoRegistro` char(1) DEFAULT NULL,
  `idCategoriaEntrada` bigint(20) DEFAULT NULL,
  `idEnvase` bigint(20) DEFAULT '0',
  `codigoEntrada` varchar(25) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `fechaCaducidad` date DEFAULT NULL,
  `fechaLlegada` date DEFAULT NULL,
  `lote` varchar(10) DEFAULT NULL,
  `habilitado` char(1) NOT NULL DEFAULT 'S',
  `cantidad` double(10,2) DEFAULT NULL,
  `saldo` double(10,2) DEFAULT NULL,
  `notasincidencias` longtext,
  `numeroPallets` bigint(20) DEFAULT NULL,
  `numeroBultos` bigint(20) DEFAULT NULL,
  `temperatura` double(10,2) DEFAULT NULL,
  `humedad` double(10,2) DEFAULT NULL,
  `costoUnitario` double(10,2) DEFAULT NULL,
  `costoTotal` double(10,2) DEFAULT NULL,
  `idTipoUbicacion` bigint(20) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idEntrada`),
  KEY `idProducto` (`idProducto`),
  KEY `idCategoria` (`idCategoria`),
  KEY `idFormatoEntrega` (`idFormatoEntrega`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registroentrada: 0 rows
DELETE FROM `registroentrada`;
/*!40000 ALTER TABLE `registroentrada` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroentrada` ENABLE KEYS */;


# Dumping structure for table tierrina.registroentrada_bulto
CREATE TABLE IF NOT EXISTS `registroentrada_bulto` (
  `codigoEntrada` varchar(20) NOT NULL,
  `numBulto` bigint(20) NOT NULL DEFAULT '0',
  `peso` double(10,2) NOT NULL DEFAULT '0.00',
  `numBultosPalet` bigint(20) NOT NULL DEFAULT '0',
  `pBruto` double(10,2) NOT NULL DEFAULT '0.00',
  `pNeto` double(10,2) NOT NULL DEFAULT '0.00',
  `responsable` varchar(50) DEFAULT '',
  PRIMARY KEY (`codigoEntrada`,`numBulto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registroentrada_bulto: 0 rows
DELETE FROM `registroentrada_bulto`;
/*!40000 ALTER TABLE `registroentrada_bulto` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroentrada_bulto` ENABLE KEYS */;


# Dumping structure for table tierrina.registroentrada_estado
CREATE TABLE IF NOT EXISTS `registroentrada_estado` (
  `idEntrada` bigint(20) NOT NULL DEFAULT '0',
  `idEstado` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEntrada`,`idEstado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registroentrada_estado: 0 rows
DELETE FROM `registroentrada_estado`;
/*!40000 ALTER TABLE `registroentrada_estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroentrada_estado` ENABLE KEYS */;


# Dumping structure for table tierrina.registroentrada_estado_tmp
CREATE TABLE IF NOT EXISTS `registroentrada_estado_tmp` (
  `idEntrada` bigint(20) NOT NULL DEFAULT '0',
  `idEstado` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEntrada`,`idEstado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

# Dumping data for table tierrina.registroentrada_estado_tmp: 0 rows
DELETE FROM `registroentrada_estado_tmp`;
/*!40000 ALTER TABLE `registroentrada_estado_tmp` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroentrada_estado_tmp` ENABLE KEYS */;


# Dumping structure for table tierrina.registroentrada_incidencia
CREATE TABLE IF NOT EXISTS `registroentrada_incidencia` (
  `idEntrada` bigint(20) NOT NULL DEFAULT '0',
  `idIncidencia` bigint(20) NOT NULL DEFAULT '0',
  `estado` set('S','N') DEFAULT 'N',
  PRIMARY KEY (`idEntrada`,`idIncidencia`),
  KEY `idIncidencia` (`idIncidencia`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registroentrada_incidencia: 0 rows
DELETE FROM `registroentrada_incidencia`;
/*!40000 ALTER TABLE `registroentrada_incidencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroentrada_incidencia` ENABLE KEYS */;


# Dumping structure for table tierrina.registroentrada_incidencia_tmp
CREATE TABLE IF NOT EXISTS `registroentrada_incidencia_tmp` (
  `idEntrada` bigint(20) NOT NULL DEFAULT '0',
  `idIncidencia` bigint(20) NOT NULL DEFAULT '0',
  `estado` set('S','N') DEFAULT 'N',
  PRIMARY KEY (`idEntrada`,`idIncidencia`),
  KEY `idIncidencia` (`idIncidencia`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

# Dumping data for table tierrina.registroentrada_incidencia_tmp: 0 rows
DELETE FROM `registroentrada_incidencia_tmp`;
/*!40000 ALTER TABLE `registroentrada_incidencia_tmp` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroentrada_incidencia_tmp` ENABLE KEYS */;


# Dumping structure for table tierrina.registroentrada_subregistro
CREATE TABLE IF NOT EXISTS `registroentrada_subregistro` (
  `idRegistroEntrada` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registroentrada_subregistro: 0 rows
DELETE FROM `registroentrada_subregistro`;
/*!40000 ALTER TABLE `registroentrada_subregistro` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroentrada_subregistro` ENABLE KEYS */;


# Dumping structure for table tierrina.registroentrada_tmp
CREATE TABLE IF NOT EXISTS `registroentrada_tmp` (
  `idEntrada` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoOrden` varchar(20) DEFAULT NULL,
  `idEntradaPadre` bigint(20) DEFAULT NULL,
  `idProducto` bigint(20) DEFAULT '0',
  `idCategoria` bigint(20) DEFAULT NULL,
  `idFormatoEntrega` bigint(20) DEFAULT NULL,
  `idCosecha` bigint(20) DEFAULT NULL,
  `idTipoRegistro` char(1) DEFAULT NULL,
  `idCategoriaEntrada` bigint(20) DEFAULT NULL,
  `idEnvase` bigint(20) DEFAULT '0',
  `codigoEntrada` varchar(25) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `fechaCaducidad` date DEFAULT NULL,
  `fechaLlegada` date DEFAULT NULL,
  `lote` varchar(10) DEFAULT NULL,
  `habilitado` char(1) NOT NULL DEFAULT 'S',
  `cantidad` double(10,2) DEFAULT NULL,
  `saldo` double(10,2) DEFAULT NULL,
  `notasincidencias` longtext,
  `numeroPallets` bigint(20) DEFAULT NULL,
  `numeroBultos` bigint(20) DEFAULT NULL,
  `temperatura` float(10,2) DEFAULT NULL,
  `humedad` double(10,2) DEFAULT NULL,
  `costoUnitario` double(10,2) DEFAULT NULL,
  `costoTotal` double(10,2) DEFAULT NULL,
  `idTipoUbicacion` bigint(20) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idEntrada`),
  KEY `idProducto` (`idProducto`),
  KEY `idCategoria` (`idCategoria`),
  KEY `idFormatoEntrega` (`idFormatoEntrega`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

# Dumping data for table tierrina.registroentrada_tmp: 0 rows
DELETE FROM `registroentrada_tmp`;
/*!40000 ALTER TABLE `registroentrada_tmp` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroentrada_tmp` ENABLE KEYS */;


# Dumping structure for table tierrina.registrosalida
CREATE TABLE IF NOT EXISTS `registrosalida` (
  `idRegistroSalida` bigint(20) NOT NULL DEFAULT '0',
  `idAlbaran` bigint(20) DEFAULT NULL,
  `idCliente_idUsuario` bigint(20) DEFAULT NULL,
  `idCliente` bigint(20) DEFAULT NULL,
  `eanProducto` varchar(14) NOT NULL,
  `idVehiculo` bigint(20) DEFAULT NULL,
  `idComercial` bigint(20) DEFAULT NULL,
  `codigoSalida` varchar(20) DEFAULT NULL,
  `codigoEntrada` varchar(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fechaCaducidad` date DEFAULT NULL,
  `pesoNeto` double(10,2) DEFAULT NULL,
  `pesoBruto` double(10,2) DEFAULT NULL,
  `numeroBultos` bigint(20) DEFAULT NULL,
  `cantidad` double(10,2) DEFAULT NULL,
  `cantidadUnitaria` double(10,2) DEFAULT NULL,
  `precioUnitario` double(10,2) DEFAULT NULL,
  `habilitado` char(1) DEFAULT 'S',
  `destino` varchar(50) DEFAULT NULL,
  `albaran` bigint(20) DEFAULT NULL,
  `notasvehiculo` longtext,
  `notasincidencias` longtext,
  `notasinstrucciones` longtext,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idRegistroSalida`),
  KEY `FK3EA1C7FB320777C` (`idCliente_idUsuario`),
  KEY `idProducto` (`eanProducto`),
  KEY `idVehiculo` (`idVehiculo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registrosalida: 0 rows
DELETE FROM `registrosalida`;
/*!40000 ALTER TABLE `registrosalida` DISABLE KEYS */;
/*!40000 ALTER TABLE `registrosalida` ENABLE KEYS */;


# Dumping structure for table tierrina.registrosalida_producto
CREATE TABLE IF NOT EXISTS `registrosalida_producto` (
  `REGISTROSALIDA_idRegistroSalida` bigint(20) NOT NULL DEFAULT '0',
  `productoBeans_idProducto` bigint(20) NOT NULL DEFAULT '0',
  KEY `FK39A6F524143C40D6` (`REGISTROSALIDA_idRegistroSalida`),
  KEY `FK39A6F52463A6D157` (`productoBeans_idProducto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registrosalida_producto: 0 rows
DELETE FROM `registrosalida_producto`;
/*!40000 ALTER TABLE `registrosalida_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `registrosalida_producto` ENABLE KEYS */;


# Dumping structure for table tierrina.registroubicacion
CREATE TABLE IF NOT EXISTS `registroubicacion` (
  `idRegistroUbicacion` bigint(20) NOT NULL DEFAULT '0',
  `idUbicacion` int(8) unsigned DEFAULT NULL,
  `idRegistro` bigint(20) unsigned DEFAULT NULL,
  `codigoEntrada` varchar(100) DEFAULT NULL,
  `responsable` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idRegistroUbicacion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registroubicacion: 0 rows
DELETE FROM `registroubicacion`;
/*!40000 ALTER TABLE `registroubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroubicacion` ENABLE KEYS */;


# Dumping structure for table tierrina.registroubicacion_tmp
CREATE TABLE IF NOT EXISTS `registroubicacion_tmp` (
  `idRegistroUbicacion` bigint(20) NOT NULL DEFAULT '0',
  `idUbicacion` int(8) unsigned DEFAULT NULL,
  `idRegistro` bigint(20) unsigned DEFAULT NULL,
  `codigoEntrada` varchar(100) DEFAULT NULL,
  `responsable` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idRegistroUbicacion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registroubicacion_tmp: 0 rows
DELETE FROM `registroubicacion_tmp`;
/*!40000 ALTER TABLE `registroubicacion_tmp` DISABLE KEYS */;
/*!40000 ALTER TABLE `registroubicacion_tmp` ENABLE KEYS */;


# Dumping structure for table tierrina.registro_ubicacion
CREATE TABLE IF NOT EXISTS `registro_ubicacion` (
  `idRegistroUbicacion` bigint(20) unsigned DEFAULT NULL,
  `idRegistro` bigint(20) unsigned DEFAULT NULL,
  `idUbicacion` bigint(20) unsigned DEFAULT NULL,
  `responsable` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.registro_ubicacion: 0 rows
DELETE FROM `registro_ubicacion`;
/*!40000 ALTER TABLE `registro_ubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_ubicacion` ENABLE KEYS */;


# Dumping structure for table tierrina.requisito_homologacion
CREATE TABLE IF NOT EXISTS `requisito_homologacion` (
  `idRequisito` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`idRequisito`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.requisito_homologacion: 5 rows
DELETE FROM `requisito_homologacion`;
/*!40000 ALTER TABLE `requisito_homologacion` DISABLE KEYS */;
INSERT INTO `requisito_homologacion` (`idRequisito`, `nombre`) VALUES
	(0, 'No aplica'),
	(1, 'Fotocopia de Registro Sanitario'),
	(2, 'Carta de compromiso de transporte en condiciones controladas: modelo de registro R03-P02'),
	(3, 'Certificación de poseer y aplicar APPCC y/o fotocopia de otros certificados de Calidad (ISO 9000, 14000, etc.)'),
	(4, 'Inscripción en el Registro del Consejo Regulador');
/*!40000 ALTER TABLE `requisito_homologacion` ENABLE KEYS */;


# Dumping structure for table tierrina.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `idRol` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `usuario_idUsuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idRol`),
  KEY `FK13DAF42275F90` (`usuario_idUsuario`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.rol: 4 rows
DELETE FROM `rol`;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`idRol`, `nombre`, `descripcion`, `usuario_idUsuario`) VALUES
	(1, 'cliente', 'administrador del sistema', NULL),
	(2, 'proveedor', 'proveedor del sistema', NULL),
	(3, 'administrador', 'cliente del sistema', NULL),
	(4, 'usuario', 'usuario del sistema', NULL);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;


# Dumping structure for table tierrina.rolacceso
CREATE TABLE IF NOT EXISTS `rolacceso` (
  `idRol` bigint(20) unsigned DEFAULT NULL,
  `accion` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.rolacceso: 0 rows
DELETE FROM `rolacceso`;
/*!40000 ALTER TABLE `rolacceso` DISABLE KEYS */;
/*!40000 ALTER TABLE `rolacceso` ENABLE KEYS */;


# Dumping structure for table tierrina.ruta
CREATE TABLE IF NOT EXISTS `ruta` (
  `idRuta` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idRuta`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ruta: 0 rows
DELETE FROM `ruta`;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;


# Dumping structure for table tierrina.rutavehiculo
CREATE TABLE IF NOT EXISTS `rutavehiculo` (
  `idOrden` bigint(20) NOT NULL DEFAULT '0',
  `idTransportista` bigint(20) DEFAULT NULL,
  `idVehiculo` bigint(20) DEFAULT NULL,
  `idEstado` bigint(20) DEFAULT NULL,
  `codigoOrden` tinyint(3) DEFAULT NULL,
  `codigoSalida` varchar(20) DEFAULT NULL,
  `fecha` tinyint(3) DEFAULT NULL,
  `horainicio` tinyint(3) DEFAULT NULL,
  `horafin` varchar(50) DEFAULT NULL,
  `kminicio` double(10,2) DEFAULT NULL,
  `kmfinal` double(10,2) DEFAULT NULL,
  `salida` varchar(150) DEFAULT NULL,
  `llegada` varchar(20) DEFAULT NULL,
  `notasIniciales` text,
  `notasFinales` text,
  `usuarioReponsable` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idOrden`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.rutavehiculo: 0 rows
DELETE FROM `rutavehiculo`;
/*!40000 ALTER TABLE `rutavehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `rutavehiculo` ENABLE KEYS */;


# Dumping structure for table tierrina.sector
CREATE TABLE IF NOT EXISTS `sector` (
  `idSector` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idSector`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.sector: 6 rows
DELETE FROM `sector`;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` (`idSector`, `nombre`) VALUES
	(1, 'HORECA'),
	(2, 'Tienda tradicional'),
	(3, 'Cadena de supermercados'),
	(4, 'Distribuidores'),
	(6, 'No aplica'),
	(7, 'Fabricas');
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;


# Dumping structure for table tierrina.secuencial_lote
CREATE TABLE IF NOT EXISTS `secuencial_lote` (
  `idSecuencial` bigint(20) NOT NULL AUTO_INCREMENT,
  `valorSecuencial` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idSecuencial`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.secuencial_lote: 0 rows
DELETE FROM `secuencial_lote`;
/*!40000 ALTER TABLE `secuencial_lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `secuencial_lote` ENABLE KEYS */;


# Dumping structure for table tierrina.telefono
CREATE TABLE IF NOT EXISTS `telefono` (
  `idTelefono` bigint(20) NOT NULL AUTO_INCREMENT,
  `numeroTelefono` varchar(255) DEFAULT NULL,
  `idUsuario` bigint(20) NOT NULL DEFAULT '0',
  `tipoTelefono` varchar(100) NOT NULL DEFAULT '0',
  `notas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTelefono`),
  KEY `FKDD8E5AF4918B1F06` (`idUsuario`)
) ENGINE=MyISAM AUTO_INCREMENT=156 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.telefono: 107 rows
DELETE FROM `telefono`;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` (`idTelefono`, `numeroTelefono`, `idUsuario`, `tipoTelefono`, `notas`) VALUES
	(1, '985316804', 1, 'Otro', 'Responsable de Fabricación'),
	(2, '985321815', 1, 'Otro', 'Responsable de Fabricación'),
	(3, '985321658', 1, 'Fax del trabajo', ''),
	(4, '91 660 81 74', 2, 'Fax del trabajo', ''),
	(5, '91 660 21 00', 2, 'Trabajo', 'Azael García - Encargado compras'),
	(6, '917306666', 3, 'Otro', 'Javier Cuñado - Encargado compras'),
	(7, '965489192', 4, 'Fax del trabajo', ''),
	(8, '965489191', 4, 'Trabajo', 'Responsable de Compras'),
	(9, '935444000', 5, 'Trabajo', ''),
	(10, '937739700', 5, 'Trabajo', 'Jon Spiegel - Jefe Compras'),
	(11, '935444010', 5, 'Fax del trabajo', ''),
	(12, '954488257', 6, 'Fax del trabajo', ''),
	(13, '954930783', 6, 'Trabajo', 'Jefe Compras'),
	(14, '915418250', 7, 'Fax del trabajo', ''),
	(15, '925418281', 7, 'Trabajo', ''),
	(16, '962818256', 8, 'Fax del trabajo', ''),
	(17, '629990188', 8, 'Trabajo', 'Pepe García Valverde - Encargado compras'),
	(18, '653867118', 9, 'Trabajo', 'Paco Botas - Jefe Compras y Cocina'),
	(19, '985166060', 9, 'Telefono de la empresa', ''),
	(20, '985307553', 10, 'Fax del trabajo', ''),
	(21, '985307132', 10, 'Casa', ''),
	(22, '942524278', 11, 'Trabajo', 'Pepe-Jesus - Gerente'),
	(23, '609423984', 11, 'Movil', 'Pepe-Jesus - Gerente'),
	(24, '942524386', 11, 'Fax del trabajo', ''),
	(25, '985345146', 12, 'Trabajo', ''),
	(26, '985168857', 12, 'Trabajo', ''),
	(27, '942660098', 13, 'Fax del trabajo', ''),
	(28, '942660086', 13, 'Trabajo', 'Compras'),
	(29, '619406487', 14, 'Movil', 'Olga - Jefe compras'),
	(30, '985791301', 14, 'Trabajo', ''),
	(31, '985794840', 14, 'Fax del trabajo', ''),
	(154, '985520430', 15, 'Trabajo', 'Antonio - Gerente'),
	(33, '629733580', 16, 'Movil', 'Paco Suarez Junco - Jefe de compras'),
	(34, '985794215', 16, 'Trabajo', ''),
	(35, '985792547', 16, 'Fax del trabajo', ''),
	(36, '913690493', 17, 'Fax del trabajo', ''),
	(37, '913690250', 17, 'Trabajo', 'Ismael Rodriguez - Gerente'),
	(38, '985402315', 18, 'Trabajo', 'Luisma - Gerente'),
	(39, '944495104', 19, 'Trabajo', 'Gerente'),
	(40, '985846097', 20, 'Trabajo', 'Jose del Valle - Jefe Cocina'),
	(41, '914018500', 21, 'Fax del trabajo', ''),
	(42, '914016229', 21, 'Trabajo', 'Paquita - Contabilidad Proveedores'),
	(43, '985837481', 22, 'Fax del trabajo', ''),
	(44, '985837701', 22, 'Trabajo', 'Jefa Compras'),
	(45, '985744519', 23, 'Fax del trabajo', ''),
	(46, '985740079', 23, 'Trabajo', 'Carmen - Compras'),
	(47, '985800224', 24, 'Fax del trabajo', ''),
	(48, '985800706', 24, 'Casa', 'Graciela - Jose Manuel - Jefe compras'),
	(49, '985800674', 25, 'Fax del trabajo', ''),
	(50, '985800174', 25, 'Trabajo', 'Sonia, Gerente, Compras'),
	(51, '985263126', 26, 'Fax del trabajo', ''),
	(52, '985260251', 26, 'Trabajo', 'Jefa Compras'),
	(53, '985898052', 27, 'Trabajo', 'Jose Antonio-Maite - Gerente'),
	(54, '605240951', 27, 'Trabajo', 'Jose Antonio-Maite - Gerente'),
	(55, '985898078', 27, 'Fax del trabajo', ''),
	(56, '982140351', 28, 'Fax del trabajo', ''),
	(57, '982140196', 28, 'Trabajo', ''),
	(58, '985123259', 29, 'Trabajo', ''),
	(59, '985849566', 30, 'Fax del trabajo', ''),
	(60, '985849445', 30, 'Trabajo', ''),
	(61, '942262585', 31, 'Fax del trabajo', ''),
	(62, '942262568', 31, 'Trabajo', ''),
	(63, '00 1 310 5395989', 32, 'Fax del trabajo', ''),
	(64, '00-1-310 5390455', 32, 'Casa', 'Mari Carmen Faraone - Jefa compras'),
	(65, '985793987', 33, 'Trabajo', 'Gerente'),
	(66, '639182879', 34, 'Trabajo', 'Roberto - Gerente'),
	(67, '916704638', 34, 'Fax del trabajo', ''),
	(68, '662290048', 34, 'Trabajo', 'Roberto - Gerente'),
	(69, '0', 35, 'Trabajo', 'Juan Carlos Barrios - Gerente'),
	(70, '00 591 33433630', 36, 'Trabajo', 'Nelson Pérez - Gerente'),
	(71, '605240951', 38, 'Movil', ''),
	(72, '985898052', 38, 'Trabajo', 'José Antonio -Maite, Jefes de Ventas'),
	(73, '985898078', 38, 'Fax del trabajo', ''),
	(74, '985641227', 39, 'Trabajo', 'Kin'),
	(75, '0059176635960', 42, 'Trabajo', 'Fanny Cárdenas - Gerente'),
	(76, '0059139462141', 42, 'Trabajo', ''),
	(77, '630988771', 43, 'Trabajo', 'Juan Carlos - Gerente'),
	(78, '630187682', 44, 'Trabajo', 'Manuel - Gerente'),
	(79, '985 470475', 46, 'Fax del trabajo', ''),
	(80, '985 470475', 46, 'Trabajo', ''),
	(81, '647 575 078', 46, 'Movil', 'Jose Manuel García - Gerente'),
	(82, '985794215', 47, 'Trabajo', 'Francisco Junco - Jefe de ventas'),
	(83, '985325784', 48, 'Trabajo', ''),
	(84, '985268175', 50, 'Fax del trabajo', ''),
	(85, '985266211', 50, 'Trabajo', ''),
	(86, '900100000', 51, 'Otro', ''),
	(87, '985551011', 51, 'Trabajo', ''),
	(88, '985263663', 53, 'Trabajo', ''),
	(89, '972357015', 54, 'Trabajo', 'Jose María Vázquez - Gerente'),
	(90, '972357', 54, 'Fax del trabajo', ''),
	(91, '625 348 700', 55, 'Movil', 'Antonio Albornoz - Gerente'),
	(92, '985206103', 55, 'Trabajo', ''),
	(93, '985207607', 55, 'Fax del trabajo', ''),
	(94, '985985070', 56, 'Trabajo', ''),
	(95, '973 560 231', 57, 'Trabajo', 'David - Comercial'),
	(96, '608821116', 58, 'Movil', 'Paqui - Responsable de ventas'),
	(97, '965636537', 58, 'Trabajo', ''),
	(98, '983313400', 59, 'Fax del trabajo', ''),
	(99, '983313339', 59, 'Trabajo', 'Antonio - María'),
	(100, '625 348 700', 60, 'Movil', 'Antonio Albornoz - Gerente'),
	(101, '985206103', 60, 'Trabajo', ''),
	(102, '985207607', 60, 'Fax del trabajo', ''),
	(103, '98628993 ', 61, 'Trabajo', ''),
	(104, '986288994', 61, 'Fax del trabajo', ''),
	(105, '607 209 163', 61, 'Movil', 'Manuel García - Gerente'),
	(106, '968 687 519', 62, 'Trabajo', 'Ignacio Ansotegui - Representante zona norte'),
	(108, '968690552', 62, 'Fax del trabajo', '');
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;


# Dumping structure for table tierrina.tipomantenimiento
CREATE TABLE IF NOT EXISTS `tipomantenimiento` (
  `idTipoMant` tinyint(3) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tipomantenimiento: 2 rows
DELETE FROM `tipomantenimiento`;
/*!40000 ALTER TABLE `tipomantenimiento` DISABLE KEYS */;
INSERT INTO `tipomantenimiento` (`idTipoMant`, `nombre`) VALUES
	(1, 'Calibracion'),
	(2, 'General');
/*!40000 ALTER TABLE `tipomantenimiento` ENABLE KEYS */;


# Dumping structure for table tierrina.tipoubicacion
CREATE TABLE IF NOT EXISTS `tipoubicacion` (
  `IdTipoUbicacion` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdTipoUbicacion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tipoubicacion: 0 rows
DELETE FROM `tipoubicacion`;
/*!40000 ALTER TABLE `tipoubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoubicacion` ENABLE KEYS */;


# Dumping structure for table tierrina.tipovehiculo
CREATE TABLE IF NOT EXISTS `tipovehiculo` (
  `idTipoVehiculo` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idTipoVehiculo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tipovehiculo: 5 rows
DELETE FROM `tipovehiculo`;
/*!40000 ALTER TABLE `tipovehiculo` DISABLE KEYS */;
INSERT INTO `tipovehiculo` (`idTipoVehiculo`, `descripcion`) VALUES
	(0, 'No aplica'),
	(1, 'Transporte'),
	(2, 'De agencia externa'),
	(3, 'Directo y medios propios'),
	(4, 'Auto venta');
/*!40000 ALTER TABLE `tipovehiculo` ENABLE KEYS */;


# Dumping structure for table tierrina.tipo_cargo
CREATE TABLE IF NOT EXISTS `tipo_cargo` (
  `idCargo` tinyint(4) NOT NULL DEFAULT '0',
  `nombreCargo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tipo_cargo: 3 rows
DELETE FROM `tipo_cargo`;
/*!40000 ALTER TABLE `tipo_cargo` DISABLE KEYS */;
INSERT INTO `tipo_cargo` (`idCargo`, `nombreCargo`) VALUES
	(1, 'Transporte'),
	(2, 'Bancario'),
	(3, 'Devolución');
/*!40000 ALTER TABLE `tipo_cargo` ENABLE KEYS */;


# Dumping structure for table tierrina.tipo_producto
CREATE TABLE IF NOT EXISTS `tipo_producto` (
  `idTipoProducto` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`idTipoProducto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tipo_producto: 0 rows
DELETE FROM `tipo_producto`;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;


# Dumping structure for table tierrina.tipo_proveedor
CREATE TABLE IF NOT EXISTS `tipo_proveedor` (
  `idTipo` bigint(20) NOT NULL DEFAULT '0',
  `nombre` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idTipo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tipo_proveedor: 5 rows
DELETE FROM `tipo_proveedor`;
/*!40000 ALTER TABLE `tipo_proveedor` DISABLE KEYS */;
INSERT INTO `tipo_proveedor` (`idTipo`, `nombre`) VALUES
	(1, 'Materia prima'),
	(2, 'Transporte'),
	(3, 'Insumos'),
	(4, 'Envases'),
	(5, 'Servicios');
/*!40000 ALTER TABLE `tipo_proveedor` ENABLE KEYS */;


# Dumping structure for table tierrina.tmp_gp
CREATE TABLE IF NOT EXISTS `tmp_gp` (
  `idGestionProduccion` bigint(20) NOT NULL DEFAULT '0',
  `idProducto` bigint(20) DEFAULT NULL,
  `proceso` varchar(50) DEFAULT NULL,
  `orden` varchar(20) DEFAULT NULL,
  `habilitado` set('S','N') DEFAULT 'S',
  `horainicio` varchar(50) DEFAULT NULL,
  `horafin` varchar(50) DEFAULT NULL,
  `cantidadprod` double(10,2) DEFAULT NULL,
  `cantidadIngredientesini` bigint(20) DEFAULT NULL,
  `cantidadIngredientesfin` bigint(20) DEFAULT NULL,
  `mermasIngredientes` double(8,2) DEFAULT NULL,
  `loteingredientes` varchar(20) DEFAULT NULL,
  `cantidadEnvasesini` bigint(20) DEFAULT NULL,
  `cantidadEnvasesfin` bigint(20) DEFAULT NULL,
  `mermasEnvases` bigint(20) DEFAULT NULL,
  `loteenvases` varchar(20) DEFAULT NULL,
  `usuarioResponsable` varchar(20) DEFAULT NULL,
  `notasincidencias` varchar(200) DEFAULT NULL,
  `notasinstrucciones` varchar(200) DEFAULT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `estadoproceso` char(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tmp_gp: 0 rows
DELETE FROM `tmp_gp`;
/*!40000 ALTER TABLE `tmp_gp` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_gp` ENABLE KEYS */;


# Dumping structure for table tierrina.tmp_gp_envasado_detalle
CREATE TABLE IF NOT EXISTS `tmp_gp_envasado_detalle` (
  `idTmpIngredientes` int(10) NOT NULL DEFAULT '0',
  `idTipoRegistro` set('E','M') DEFAULT NULL,
  `orden` varchar(20) DEFAULT NULL,
  `codigoEntrada` varchar(20) DEFAULT NULL,
  `idProducto` int(20) DEFAULT NULL,
  `cantidadDisponible` decimal(10,2) DEFAULT NULL,
  `cantidadUsable` decimal(10,2) DEFAULT NULL,
  `mermaProducto` decimal(10,2) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tmp_gp_envasado_detalle: 0 rows
DELETE FROM `tmp_gp_envasado_detalle`;
/*!40000 ALTER TABLE `tmp_gp_envasado_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_gp_envasado_detalle` ENABLE KEYS */;


# Dumping structure for table tierrina.tmp_ingredientes_desgranado
CREATE TABLE IF NOT EXISTS `tmp_ingredientes_desgranado` (
  `idTmpIngredientes` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoEntradaIngrediente` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idTmpIngredientes`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tmp_ingredientes_desgranado: 0 rows
DELETE FROM `tmp_ingredientes_desgranado`;
/*!40000 ALTER TABLE `tmp_ingredientes_desgranado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_ingredientes_desgranado` ENABLE KEYS */;


# Dumping structure for table tierrina.tmp_ingredientes_envases
CREATE TABLE IF NOT EXISTS `tmp_ingredientes_envases` (
  `idTmpIngredientes` bigint(20) NOT NULL AUTO_INCREMENT,
  `idTipoRegistro` set('E','M') DEFAULT NULL,
  `orden` varchar(20) DEFAULT NULL,
  `codigoEntrada` varchar(20) DEFAULT NULL,
  `idProducto` bigint(20) DEFAULT NULL,
  `cantidadDisponible` bigint(20) DEFAULT NULL,
  `cantidadUsable` bigint(20) DEFAULT NULL,
  `mermaProducto` bigint(20) DEFAULT NULL,
  `descripcionEnvase` varchar(100) DEFAULT NULL,
  `descripcionMateria` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idTmpIngredientes`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.tmp_ingredientes_envases: 0 rows
DELETE FROM `tmp_ingredientes_envases`;
/*!40000 ALTER TABLE `tmp_ingredientes_envases` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmp_ingredientes_envases` ENABLE KEYS */;


# Dumping structure for table tierrina.transporte
CREATE TABLE IF NOT EXISTS `transporte` (
  `idTransporte` int(11) NOT NULL AUTO_INCREMENT,
  `idTransportista` int(11) NOT NULL DEFAULT '0',
  `matricula` varchar(255) DEFAULT NULL,
  `temperatura` int(11) NOT NULL DEFAULT '0',
  `transportista_idUsuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idTransporte`),
  KEY `FKB685DA5CE067397D` (`transportista_idUsuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.transporte: 0 rows
DELETE FROM `transporte`;
/*!40000 ALTER TABLE `transporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `transporte` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacion
CREATE TABLE IF NOT EXISTS `ubicacion` (
  `idUbicacion` bigint(20) NOT NULL AUTO_INCREMENT,
  `idHueco` bigint(20) NOT NULL,
  `registro` varchar(50) NOT NULL,
  `numeroBultos` bigint(20) NOT NULL DEFAULT '0',
  `cantidad` double(8,2) DEFAULT NULL,
  `idPalet` bigint(20) NOT NULL DEFAULT '0',
  `orden` varchar(20) DEFAULT NULL,
  `congelado` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`idUbicacion`)
) ENGINE=MyISAM AUTO_INCREMENT=208 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacion: 0 rows
DELETE FROM `ubicacion`;
/*!40000 ALTER TABLE `ubicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `ubicacion` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacionzona
CREATE TABLE IF NOT EXISTS `ubicacionzona` (
  `idZona` bigint(20) NOT NULL DEFAULT '0',
  `idTipoUbicacion` bigint(20) DEFAULT NULL,
  `nave` char(3) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `dimensiones` varchar(255) DEFAULT NULL,
  `idDireccion` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idZona`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacionzona: 0 rows
DELETE FROM `ubicacionzona`;
/*!40000 ALTER TABLE `ubicacionzona` DISABLE KEYS */;
/*!40000 ALTER TABLE `ubicacionzona` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacion_almacen
CREATE TABLE IF NOT EXISTS `ubicacion_almacen` (
  `idAlmacen` bigint(20) NOT NULL AUTO_INCREMENT,
  `idDireccion` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `urlPlano` varchar(50) NOT NULL DEFAULT '',
  `esVehiculo` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`idAlmacen`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacion_almacen: 5 rows
DELETE FROM `ubicacion_almacen`;
/*!40000 ALTER TABLE `ubicacion_almacen` DISABLE KEYS */;
INSERT INTO `ubicacion_almacen` (`idAlmacen`, `idDireccion`, `descripcion`, `urlPlano`, `esVehiculo`) VALUES
	(1, 1, 'Nave Barcia', '/img/planos/naveBarcia.png', 'N'),
	(2, 0, '0973 CPT', '', 'S'),
	(3, 0, '8066 HFW', '', 'S'),
	(4, 0, 'Vehiculo III', '', 'S'),
	(5, 0, 'Granda', '', 'N');
/*!40000 ALTER TABLE `ubicacion_almacen` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacion_estanteria
CREATE TABLE IF NOT EXISTS `ubicacion_estanteria` (
  `idEstanteria` bigint(20) NOT NULL AUTO_INCREMENT,
  `idLinea` bigint(20) NOT NULL DEFAULT '0',
  `idEstanteriaLinea` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `urlPlano` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idEstanteria`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacion_estanteria: 29 rows
DELETE FROM `ubicacion_estanteria`;
/*!40000 ALTER TABLE `ubicacion_estanteria` DISABLE KEYS */;
INSERT INTO `ubicacion_estanteria` (`idEstanteria`, `idLinea`, `idEstanteriaLinea`, `descripcion`, `urlPlano`) VALUES
	(1, 1, 1, 'A1:Z1:L1:E1', ''),
	(2, 1, 2, 'A1:Z1:L1:E2', ''),
	(3, 1, 3, 'A1:Z1:L1:E3', ''),
	(4, 2, 1, 'A1:Z1:L2:E1', ''),
	(5, 2, 2, 'A1:Z1:L2:E2', ''),
	(6, 2, 3, 'A1:Z1:L2:E3', ''),
	(7, 3, 1, 'A1:Z2:L1:E1', ''),
	(8, 3, 2, 'A1:Z2:L1:E2', ''),
	(9, 3, 3, 'A1:Z2:L1:E3', ''),
	(10, 4, 1, 'A1:Z2:L2:E1', ''),
	(11, 4, 2, 'A1:Z2:L2:E2', ''),
	(12, 4, 3, 'A1:Z2:L2:E3', ''),
	(13, 5, 1, 'A1:Z3:L1:E1', ''),
	(14, 5, 2, 'A1:Z3:L1:E2', ''),
	(15, 6, 1, 'A1:Z4:L1:E1', ''),
	(16, 6, 2, 'A1:Z4:L1:E2', ''),
	(17, 6, 3, 'A1:Z4:L1:E3', ''),
	(18, 6, 4, 'A1:Z4:L1:E4', ''),
	(19, 7, 1, 'A1:Z5:L1:E1', ''),
	(20, 7, 2, 'A1:Z5:L1:E2', ''),
	(21, 7, 3, 'A1:Z5:L1:E3', ''),
	(22, 7, 4, 'A1:Z5:L1:E4', ''),
	(24, 9, 1, '8066 HFW', ''),
	(23, 8, 1, '0973 CPT', ''),
	(25, 10, 1, 'Vehículo III', ''),
	(26, 11, 1, 'Zona de envasado', ''),
	(34, 19, 1, 'Granda', ''),
	(33, 18, 1, 'Entrada', ''),
	(35, 20, 1, 'Salida', '');
/*!40000 ALTER TABLE `ubicacion_estanteria` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacion_hueco
CREATE TABLE IF NOT EXISTS `ubicacion_hueco` (
  `idHueco` bigint(20) NOT NULL AUTO_INCREMENT,
  `idPiso` bigint(20) NOT NULL,
  `idHuecoPiso` bigint(20) NOT NULL,
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `numeroRegistros` int(11) NOT NULL DEFAULT '0',
  `x` int(10) DEFAULT '0',
  `y` int(10) DEFAULT '0',
  `z` int(10) DEFAULT '0',
  `bigBag` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`idHueco`)
) ENGINE=MyISAM AUTO_INCREMENT=228 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacion_hueco: 217 rows
DELETE FROM `ubicacion_hueco`;
/*!40000 ALTER TABLE `ubicacion_hueco` DISABLE KEYS */;
INSERT INTO `ubicacion_hueco` (`idHueco`, `idPiso`, `idHuecoPiso`, `descripcion`, `numeroRegistros`, `x`, `y`, `z`, `bigBag`) VALUES
	(1, 1, 1, 'A1:Z1:L1:E1:P1:H1', 0, 0, 0, 0, 'N'),
	(2, 2, 1, 'A1:Z1:L1:E1:P2:H1', 0, 0, 0, 0, 'N'),
	(3, 3, 1, 'A1:Z1:L1:E1:P3:H1', 0, 0, 0, 0, 'N'),
	(5, 4, 2, 'A1:Z1:L1:E2:P1:H2', 0, 0, 0, 0, 'N'),
	(6, 4, 3, 'A1:Z1:L1:E2:P1:H3', 0, 0, 0, 0, 'N'),
	(8, 5, 1, 'A1:Z1:L1:E2:P2:H1', 0, 0, 0, 0, 'N'),
	(9, 5, 2, 'A1:Z1:L1:E2:P2:H2', 0, 0, 0, 0, 'N'),
	(10, 5, 3, 'A1:Z1:L1:E2:P2:H3', 0, 0, 0, 0, 'N'),
	(12, 6, 1, 'A1:Z1:L1:E2:P3:H1', 0, 0, 0, 0, 'N'),
	(13, 6, 2, 'A1:Z1:L1:E2:P3:H2', 0, 0, 0, 0, 'N'),
	(14, 6, 3, 'A1:Z1:L1:E2:P3:H3', 0, 0, 0, 0, 'N'),
	(4, 4, 1, 'A1:Z1:L1:E2:P1:H1', 0, 0, 0, 0, 'N'),
	(16, 7, 1, 'A1:Z1:L1:E3:P1:H1', 0, 0, 0, 0, 'N'),
	(17, 7, 2, 'A1:Z1:L1:E3:P1:H2', 0, 0, 0, 0, 'N'),
	(18, 7, 3, 'A1:Z1:L1:E3:P1:H3', 0, 0, 0, 0, 'N'),
	(20, 8, 1, 'A1:Z1:L1:E3:P2:H1', 0, 0, 0, 0, 'N'),
	(21, 8, 2, 'A1:Z1:L1:E3:P2:H2', 0, 0, 0, 0, 'N'),
	(22, 8, 3, 'A1:Z1:L1:E3:P2:H3', 0, 0, 0, 0, 'N'),
	(24, 9, 1, 'A1:Z1:L1:E3:P3:H1', 0, 0, 0, 0, 'N'),
	(25, 9, 2, 'A1:Z1:L1:E3:P3:H2', 0, 0, 0, 0, 'N'),
	(26, 9, 3, 'A1:Z1:L1:E3:P3:H3', 0, 0, 0, 0, 'N'),
	(7, 4, 4, 'A1:Z1:L1:E2:P1:H4', 0, 0, 0, 0, 'N'),
	(11, 5, 4, 'A1:Z1:L1:E2:P2:H4', 0, 0, 0, 0, 'N'),
	(15, 6, 4, 'A1:Z1:L1:E2:P3:H4', 0, 0, 0, 0, 'N'),
	(19, 7, 4, 'A1:Z1:L1:E3:P1:H4', 0, 0, 0, 0, 'N'),
	(23, 8, 4, 'A1:Z1:L1:E3:P2:H4', 0, 0, 0, 0, 'N'),
	(27, 9, 4, 'A1:Z1:L1:E3:P3:H4', 0, 0, 0, 0, 'N'),
	(28, 10, 1, 'A1:Z1:L2:E1:P1:H1', 0, 0, 0, 0, 'N'),
	(29, 10, 2, 'A1:Z1:L2:E1:P1:H2', 0, 0, 0, 0, 'N'),
	(30, 10, 3, 'A1:Z1:L2:E1:P1:H3', 0, 0, 0, 0, 'N'),
	(31, 11, 1, 'A1:Z1:L2:E1:P2:H1', 0, 0, 0, 0, 'N'),
	(32, 11, 2, 'A1:Z1:L2:E1:P2:H2', 0, 0, 0, 0, 'N'),
	(33, 11, 3, 'A1:Z1:L2:E1:P2:H3', 0, 0, 0, 0, 'N'),
	(34, 12, 1, 'A1:Z1:L2:E1:P3:H1', 0, 0, 0, 0, 'N'),
	(35, 12, 2, 'A1:Z1:L2:E1:P3:H2', 0, 0, 0, 0, 'N'),
	(36, 12, 3, 'A1:Z1:L2:E1:P3:H3', 0, 0, 0, 0, 'N'),
	(37, 13, 1, 'A1:Z1:L2:E2:P1:H1', 0, 0, 0, 0, 'N'),
	(38, 13, 2, 'A1:Z1:L2:E2:P1:H2', 0, 0, 0, 0, 'N'),
	(39, 13, 3, 'A1:Z1:L2:E2:P1:H3', 0, 0, 0, 0, 'N'),
	(40, 14, 1, 'A1:Z1:L2:E2:P2:H1', 0, 0, 0, 0, 'N'),
	(41, 14, 2, 'A1:Z1:L2:E2:P2:H2', 0, 0, 0, 0, 'N'),
	(42, 14, 3, 'A1:Z1:L2:E2:P2:H3', 0, 0, 0, 0, 'N'),
	(43, 15, 1, 'A1:Z1:L2:E2:P3:H1', 0, 0, 0, 0, 'N'),
	(44, 15, 2, 'A1:Z1:L2:E2:P3:H2', 0, 0, 0, 0, 'N'),
	(45, 15, 3, 'A1:Z1:L2:E2:P3:H3', 0, 0, 0, 0, 'N'),
	(46, 16, 1, 'A1:Z1:L2:E3:P1:H1', 0, 0, 0, 0, 'N'),
	(47, 16, 2, 'A1:Z1:L2:E3:P1:H2', 0, 0, 0, 0, 'N'),
	(48, 17, 1, 'A1:Z1:L2:E3:P2:H1', 0, 0, 0, 0, 'N'),
	(49, 17, 2, 'A1:Z1:L2:E3:P2:H2', 0, 0, 0, 0, 'N'),
	(50, 18, 1, 'A1:Z1:L2:E3:P3:H1', 0, 0, 0, 0, 'N'),
	(51, 18, 2, 'A1:Z1:L2:E3:P3:H2', 0, 0, 0, 0, 'N'),
	(52, 19, 1, 'A1:Z2:L1:E1:P1:H1', 0, 0, 0, 0, 'N'),
	(53, 20, 1, 'A1:Z2:L1:E1:P2:H1', 0, 0, 0, 0, 'N'),
	(54, 21, 1, 'A1:Z2:L1:E1:P3:H1', 0, 0, 0, 0, 'N'),
	(55, 22, 1, 'A1:Z2:L1:E2:P1:H1', 0, 0, 0, 0, 'N'),
	(56, 22, 2, 'A1:Z2:L1:E2:P1:H2', 0, 0, 0, 0, 'N'),
	(57, 23, 1, 'A1:Z2:L1:E2:P2:H1', 0, 0, 0, 0, 'N'),
	(58, 23, 2, 'A1:Z2:L1:E2:P2:H2', 0, 0, 0, 0, 'N'),
	(59, 24, 1, 'A1:Z2:L1:E2:P3:H1', 0, 0, 0, 0, 'N'),
	(60, 24, 2, 'A1:Z2:L1:E2:P3:H2', 0, 0, 0, 0, 'N'),
	(61, 25, 1, 'A1:Z2:L1:E3:P1:H1', 0, 0, 0, 0, 'N'),
	(62, 25, 2, 'A1:Z2:L1:E3:P1:H2', 0, 0, 0, 0, 'N'),
	(63, 25, 3, 'A1:Z2:L1:E3:P1:H3', 0, 0, 0, 0, 'N'),
	(64, 25, 4, 'A1:Z2:L1:E3:P1:H4', 0, 0, 0, 0, 'N'),
	(65, 26, 1, 'A1:Z2:L1:E3:P2:H1', 0, 0, 0, 0, 'N'),
	(66, 26, 2, 'A1:Z2:L1:E3:P2:H2', 0, 0, 0, 0, 'N'),
	(67, 26, 3, 'A1:Z2:L1:E3:P2:H3', 0, 0, 0, 0, 'N'),
	(68, 26, 4, 'A1:Z2:L1:E3:P2:H4', 0, 0, 0, 0, 'N'),
	(69, 27, 1, 'A1:Z2:L1:E3:P3:H1', 0, 0, 0, 0, 'N'),
	(70, 27, 2, 'A1:Z2:L1:E3:P3:H2', 0, 0, 0, 0, 'N'),
	(71, 27, 3, 'A1:Z2:L1:E3:P3:H3', 0, 0, 0, 0, 'N'),
	(72, 27, 4, 'A1:Z2:L1:E3:P3:H4', 0, 0, 0, 0, 'N'),
	(73, 28, 1, 'A1:Z2:L2:E1:P1:H1', 0, 0, 0, 0, 'N'),
	(74, 28, 2, 'A1:Z2:L2:E1:P1:H2', 0, 0, 0, 0, 'N'),
	(75, 28, 3, 'A1:Z2:L2:E1:P1:H3', 0, 0, 0, 0, 'N'),
	(76, 28, 4, 'A1:Z2:L2:E1:P1:H4', 0, 0, 0, 0, 'N'),
	(77, 29, 1, 'A1:Z2:L2:E1:P2:H1', 0, 0, 0, 0, 'N'),
	(78, 29, 2, 'A1:Z2:L2:E1:P2:H2', 0, 0, 0, 0, 'N'),
	(79, 29, 3, 'A1:Z2:L2:E1:P2:H3', 0, 0, 0, 0, 'N'),
	(80, 29, 4, 'A1:Z2:L2:E1:P2:H4', 0, 0, 0, 0, 'N'),
	(81, 30, 1, 'A1:Z2:L2:E1:P3:H1', 0, 0, 0, 0, 'N'),
	(82, 30, 2, 'A1:Z2:L2:E1:P3:H2', 0, 0, 0, 0, 'N'),
	(83, 30, 3, 'A1:Z2:L2:E1:P3:H3', 0, 0, 0, 0, 'N'),
	(84, 30, 4, 'A1:Z2:L2:E1:P3:H4', 0, 0, 0, 0, 'N'),
	(85, 31, 1, 'A1:Z2:L2:E2:P1:H1', 0, 0, 0, 0, 'N'),
	(86, 31, 2, 'A1:Z2:L2:E2:P1:H2', 0, 0, 0, 0, 'N'),
	(87, 31, 3, 'A1:Z2:L2:E2:P1:H3', 0, 0, 0, 0, 'N'),
	(88, 31, 4, 'A1:Z2:L2:E2:P1:H4', 0, 0, 0, 0, 'N'),
	(89, 32, 1, 'A1:Z2:L2:E2:P2:H1', 0, 0, 0, 0, 'N'),
	(90, 32, 2, 'A1:Z2:L2:E2:P2:H2', 0, 0, 0, 0, 'N'),
	(91, 32, 3, 'A1:Z2:L2:E2:P2:H3', 0, 0, 0, 0, 'N'),
	(92, 32, 4, 'A1:Z2:L2:E2:P2:H4', 0, 0, 0, 0, 'N'),
	(93, 33, 1, 'A1:Z2:L2:E2:P3:H1', 0, 0, 0, 0, 'N'),
	(94, 33, 2, 'A1:Z2:L2:E2:P3:H2', 0, 0, 0, 0, 'N'),
	(95, 33, 3, 'A1:Z2:L2:E2:P3:H3', 0, 0, 0, 0, 'N'),
	(96, 33, 4, 'A1:Z2:L2:E2:P3:H4', 0, 0, 0, 0, 'N'),
	(97, 34, 1, 'A1:Z2:L2:E3:P1:H1', 0, 0, 0, 0, 'N'),
	(98, 35, 1, 'A1:Z2:L2:E3:P2:H1', 0, 0, 0, 0, 'N'),
	(99, 36, 1, 'A1:Z2:L2:E3:P3:H1', 0, 0, 0, 0, 'N'),
	(100, 37, 1, 'A1:Z3:L1:E1:P1:H1', 0, 0, 0, 0, 'N'),
	(101, 37, 2, 'A1:Z3:L1:E1:P1:H2', 0, 0, 0, 0, 'N'),
	(102, 37, 3, 'A1:Z3:L1:E1:P1:H3', 0, 0, 0, 0, 'N'),
	(103, 38, 1, 'A1:Z3:L1:E1:P2:H1', 0, 0, 0, 0, 'N'),
	(104, 38, 2, 'A1:Z3:L1:E1:P2:H2', 0, 0, 0, 0, 'N'),
	(105, 38, 3, 'A1:Z3:L1:E1:P2:H3', 0, 0, 0, 0, 'N'),
	(106, 39, 1, 'A1:Z3:L1:E1:P3:H1', 0, 0, 0, 0, 'N'),
	(107, 39, 2, 'A1:Z3:L1:E1:P3:H2', 0, 0, 0, 0, 'N'),
	(108, 39, 3, 'A1:Z3:L1:E1:P3:H3', 0, 0, 0, 0, 'N'),
	(109, 40, 1, 'A1:Z3:L1:E1:P4:H1', 0, 0, 0, 0, 'N'),
	(110, 40, 2, 'A1:Z3:L1:E1:P4:H2', 0, 0, 0, 0, 'N'),
	(111, 40, 3, 'A1:Z3:L1:E1:P4:H3', 0, 0, 0, 0, 'N'),
	(112, 41, 1, 'A1:Z3:L1:E2:P1:H1', 0, 0, 0, 0, 'N'),
	(113, 41, 2, 'A1:Z3:L1:E2:P1:H2', 0, 0, 0, 0, 'N'),
	(114, 42, 1, 'A1:Z3:L1:E2:P2:H1', 0, 0, 0, 0, 'N'),
	(115, 42, 2, 'A1:Z3:L1:E2:P2:H2', 0, 0, 0, 0, 'N'),
	(116, 43, 1, 'A1:Z3:L1:E2:P3:H1', 0, 0, 0, 0, 'N'),
	(117, 43, 2, 'A1:Z3:L1:E2:P3:H2', 0, 0, 0, 0, 'N'),
	(118, 44, 1, 'A1:Z3:L1:E2:P4:H1', 0, 0, 0, 0, 'N'),
	(119, 44, 2, 'A1:Z3:L1:E2:P4:H2', 0, 0, 0, 0, 'N'),
	(120, 45, 1, 'A1:Z4:L1:E1:P1:H1', 0, 0, 0, 0, 'N'),
	(121, 45, 2, 'A1:Z4:L1:E1:P1:H2', 0, 0, 0, 0, 'N'),
	(122, 46, 1, 'A1:Z4:L1:E1:P2:H1', 0, 0, 0, 0, 'N'),
	(123, 46, 2, 'A1:Z4:L1:E1:P2:H2', 0, 0, 0, 0, 'N'),
	(124, 47, 1, 'A1:Z4:L1:E1:P3:H1', 0, 0, 0, 0, 'N'),
	(125, 47, 2, 'A1:Z4:L1:E1:P3:H2', 0, 0, 0, 0, 'N'),
	(126, 48, 1, 'A1:Z4:L1:E1:P4:H1', 0, 0, 0, 0, 'N'),
	(127, 48, 2, 'A1:Z4:L1:E1:P4:H2', 0, 0, 0, 0, 'N'),
	(128, 49, 1, 'A1:Z4:L1:E2:P1:H1', 0, 0, 0, 0, 'N'),
	(129, 49, 2, 'A1:Z4:L1:E2:P1:H2', 0, 0, 0, 0, 'N'),
	(130, 50, 1, 'A1:Z4:L1:E2:P2:H1', 0, 0, 0, 0, 'N'),
	(131, 50, 2, 'A1:Z4:L1:E2:P2:H2', 0, 0, 0, 0, 'N'),
	(132, 51, 1, 'A1:Z4:L1:E2:P3:H1', 0, 0, 0, 0, 'N'),
	(133, 51, 2, 'A1:Z4:L1:E2:P3:H2', 0, 0, 0, 0, 'N'),
	(134, 52, 1, 'A1:Z4:L1:E2:P4:H1', 0, 0, 0, 0, 'N'),
	(135, 52, 2, 'A1:Z4:L1:E2:P4:H2', 0, 0, 0, 0, 'N'),
	(136, 53, 1, 'A1:Z4:L1:E3:P1:H1', 0, 0, 0, 0, 'N'),
	(137, 53, 2, 'A1:Z4:L1:E3:P1:H2', 0, 0, 0, 0, 'N'),
	(138, 53, 3, 'A1:Z4:L1:E3:P1:H3', 0, 0, 0, 0, 'N'),
	(139, 54, 1, 'A1:Z4:L1:E3:P2:H1', 0, 0, 0, 0, 'N'),
	(140, 54, 2, 'A1:Z4:L1:E3:P2:H2', 0, 0, 0, 0, 'N'),
	(141, 54, 3, 'A1:Z4:L1:E3:P2:H3', 0, 0, 0, 0, 'N'),
	(142, 55, 1, 'A1:Z4:L1:E3:P3:H1', 0, 0, 0, 0, 'N'),
	(143, 55, 2, 'A1:Z4:L1:E3:P3:H2', 0, 0, 0, 0, 'N'),
	(144, 55, 3, 'A1:Z4:L1:E3:P3:H3', 0, 0, 0, 0, 'N'),
	(145, 56, 1, 'A1:Z4:L1:E3:P4:H1', 0, 0, 0, 0, 'N'),
	(146, 56, 2, 'A1:Z4:L1:E3:P4:H2', 0, 0, 0, 0, 'N'),
	(147, 56, 3, 'A1:Z4:L1:E3:P4:H3', 0, 0, 0, 0, 'N'),
	(148, 57, 1, 'A1:Z4:L1:E4:P1:H1', 0, 0, 0, 0, 'N'),
	(149, 57, 2, 'A1:Z4:L1:E4:P1:H2', 0, 0, 0, 0, 'N'),
	(150, 57, 3, 'A1:Z4:L1:E4:P1:H3', 0, 0, 0, 0, 'N'),
	(151, 58, 1, 'A1:Z4:L1:E4:P2:H1', 0, 0, 0, 0, 'N'),
	(152, 58, 2, 'A1:Z4:L1:E4:P2:H2', 0, 0, 0, 0, 'N'),
	(153, 58, 3, 'A1:Z4:L1:E4:P2:H3', 0, 0, 0, 0, 'N'),
	(154, 59, 1, 'A1:Z4:L1:E4:P3:H1', 0, 0, 0, 0, 'N'),
	(155, 59, 2, 'A1:Z4:L1:E4:P3:H2', 0, 0, 0, 0, 'N'),
	(156, 59, 3, 'A1:Z4:L1:E4:P3:H3', 0, 0, 0, 0, 'N'),
	(157, 60, 1, 'A1:Z4:L1:E4:P4:H1', 0, 0, 0, 0, 'N'),
	(158, 60, 2, 'A1:Z4:L1:E4:P4:H2', 0, 0, 0, 0, 'N'),
	(159, 60, 3, 'A1:Z4:L1:E4:P4:H3', 0, 0, 0, 0, 'N'),
	(160, 61, 1, 'A1:Z5:L1:E1:P1:H1', 0, 0, 0, 0, 'N'),
	(161, 61, 2, 'A1:Z5:L1:E1:P1:H2', 0, 0, 0, 0, 'N'),
	(162, 62, 1, 'A1:Z5:L1:E1:P2:H1', 0, 0, 0, 0, 'N'),
	(163, 62, 2, 'A1:Z5:L1:E1:P2:H2', 0, 0, 0, 0, 'N'),
	(164, 63, 1, 'A1:Z5:L1:E1:P3:H1', 0, 0, 0, 0, 'N'),
	(165, 63, 2, 'A1:Z5:L1:E1:P3:H2', 0, 0, 0, 0, 'N'),
	(166, 64, 1, 'A1:Z5:L1:E1:P4:H1', 0, 0, 0, 0, 'N'),
	(167, 64, 2, 'A1:Z5:L1:E1:P4:H2', 0, 0, 0, 0, 'N'),
	(168, 65, 1, 'A1:Z5:L1:E2:P1:H1', 0, 0, 0, 0, 'N'),
	(169, 65, 2, 'A1:Z5:L1:E2:P1:H2', 0, 0, 0, 0, 'N'),
	(170, 66, 1, 'A1:Z5:L1:E2:P2:H1', 0, 0, 0, 0, 'N'),
	(171, 66, 2, 'A1:Z5:L1:E2:P2:H2', 0, 0, 0, 0, 'N'),
	(172, 67, 1, 'A1:Z5:L1:E2:P3:H1', 0, 0, 0, 0, 'N'),
	(173, 67, 2, 'A1:Z5:L1:E2:P3:H2', 0, 0, 0, 0, 'N'),
	(174, 68, 1, 'A1:Z5:L1:E2:P4:H1', 0, 0, 0, 0, 'N'),
	(175, 68, 2, 'A1:Z5:L1:E2:P4:H2', 0, 0, 0, 0, 'N'),
	(176, 69, 1, 'A1:Z5:L1:E3:P1:H1', 0, 0, 0, 0, 'N'),
	(177, 69, 2, 'A1:Z5:L1:E3:P1:H2', 0, 0, 0, 0, 'N'),
	(178, 69, 3, 'A1:Z5:L1:E3:P1:H3', 0, 0, 0, 0, 'N'),
	(179, 70, 1, 'A1:Z5:L1:E3:P2:H1', 0, 0, 0, 0, 'N'),
	(180, 70, 2, 'A1:Z5:L1:E3:P2:H2', 0, 0, 0, 0, 'N'),
	(181, 70, 3, 'A1:Z5:L1:E3:P2:H3', 0, 0, 0, 0, 'N'),
	(182, 71, 1, 'A1:Z5:L1:E3:P3:H1', 0, 0, 0, 0, 'N'),
	(183, 71, 2, 'A1:Z5:L1:E3:P3:H2', 0, 0, 0, 0, 'N'),
	(184, 71, 3, 'A1:Z5:L1:E3:P3:H3', 0, 0, 0, 0, 'N'),
	(185, 72, 1, 'A1:Z5:L1:E3:P4:H1', 0, 0, 0, 0, 'N'),
	(186, 72, 2, 'A1:Z5:L1:E3:P4:H2', 0, 0, 0, 0, 'N'),
	(187, 72, 3, 'A1:Z5:L1:E3:P4:H3', 0, 0, 0, 0, 'N'),
	(188, 73, 1, 'A1:Z5:L1:E4:P1:H1', 0, 0, 0, 0, 'N'),
	(189, 73, 2, 'A1:Z5:L1:E4:P1:H2', 0, 0, 0, 0, 'N'),
	(190, 73, 3, 'A1:Z5:L1:E4:P1:H3', 0, 0, 0, 0, 'N'),
	(191, 74, 1, 'A1:Z5:L1:E4:P2:H1', 0, 0, 0, 0, 'N'),
	(192, 74, 2, 'A1:Z5:L1:E4:P2:H2', 0, 0, 0, 0, 'N'),
	(193, 74, 3, 'A1:Z5:L1:E4:P2:H3', 0, 0, 0, 0, 'N'),
	(194, 75, 1, 'A1:Z5:L1:E4:P3:H1', 0, 0, 0, 0, 'N'),
	(195, 75, 2, 'A1:Z5:L1:E4:P3:H2', 0, 0, 0, 0, 'N'),
	(196, 75, 3, 'A1:Z5:L1:E4:P3:H3', 0, 0, 0, 0, 'N'),
	(197, 76, 1, 'A1:Z5:L1:E4:P4:H1', 0, 0, 0, 0, 'N'),
	(198, 76, 2, 'A1:Z5:L1:E4:P4:H2', 0, 0, 0, 0, 'N'),
	(199, 76, 3, 'A1:Z5:L1:E4:P4:H3', 0, 0, 0, 0, 'N'),
	(200, 77, 1, '0973 CPT', 0, 0, 0, 0, 'N'),
	(201, 78, 1, '8066 HFW', 0, 0, 0, 0, 'N'),
	(202, 79, 1, 'Vehículo III', 0, 0, 0, 0, 'N'),
	(203, 80, 1, 'Big Bag 01', 0, 0, 0, 0, 'S'),
	(204, 80, 1, 'Big Bag 02', 0, 0, 0, 0, 'S'),
	(205, 80, 1, 'Big Bag 03', 0, 0, 0, 0, 'S'),
	(206, 80, 1, 'Big Bag 04', 0, 0, 0, 0, 'S'),
	(207, 80, 1, 'Big Bag 05', 0, 0, 0, 0, 'S'),
	(208, 80, 1, 'Big Bag 06', 0, 0, 0, 0, 'S'),
	(209, 80, 1, 'Big Bag 07', 0, 0, 0, 0, 'S'),
	(210, 80, 1, 'Big Bag 08', 0, 0, 0, 0, 'S'),
	(211, 80, 1, 'Big Bag 09', 0, 0, 0, 0, 'S'),
	(212, 80, 1, 'Big Bag 10', 0, 0, 0, 0, 'S'),
	(213, 80, 1, 'Big Bag 11', 0, 0, 0, 0, 'S'),
	(214, 80, 1, 'Big Bag 12', 0, 0, 0, 0, 'S'),
	(223, 89, 1, 'Salida', 0, 0, 0, 0, 'N'),
	(222, 88, 1, 'Granda', 0, 0, 0, 0, 'N'),
	(221, 87, 1, 'Entrada', 0, 0, 0, 0, 'N');
/*!40000 ALTER TABLE `ubicacion_hueco` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacion_linea
CREATE TABLE IF NOT EXISTS `ubicacion_linea` (
  `idLinea` bigint(20) NOT NULL AUTO_INCREMENT,
  `idZona` bigint(20) NOT NULL DEFAULT '0',
  `idLineaZona` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `urlPlano` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idLinea`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacion_linea: 14 rows
DELETE FROM `ubicacion_linea`;
/*!40000 ALTER TABLE `ubicacion_linea` DISABLE KEYS */;
INSERT INTO `ubicacion_linea` (`idLinea`, `idZona`, `idLineaZona`, `descripcion`, `urlPlano`) VALUES
	(1, 1, 1, 'Conservacion 1 arriba', 'ubicacion/A1Z1L1.jsp'),
	(2, 1, 2, 'Conservacion 1 abajo', 'ubicacion/A1Z1L2.jsp'),
	(3, 2, 1, 'Conservacion 2 arriba', 'ubicacion/A1Z2L1.jsp'),
	(4, 2, 2, 'Conservacion 2 abajo', 'ubicacion/A1Z2L2.jsp'),
	(5, 3, 1, 'Cartonaje', 'ubicacion/A1Z3L1.jsp'),
	(6, 4, 1, 'Almacen 1', 'ubicacion/A1Z4L1.jsp'),
	(7, 5, 1, 'Almacen 2', 'ubicacion/A1Z5L1.jsp'),
	(8, 9, 1, '0973 CPT', ''),
	(9, 10, 1, '8066 HFW', ''),
	(10, 11, 1, 'Vehículo III', ''),
	(11, 6, 1, 'Zona de envasado', 'ubicacion/zonaEnvasado.jsp'),
	(19, 13, 1, 'Granda', ''),
	(18, 12, 1, 'Entrada', ''),
	(20, 14, 1, 'Salida', '');
/*!40000 ALTER TABLE `ubicacion_linea` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacion_piso
CREATE TABLE IF NOT EXISTS `ubicacion_piso` (
  `idPiso` bigint(20) NOT NULL AUTO_INCREMENT,
  `idEstanteria` bigint(20) NOT NULL DEFAULT '0',
  `idPisoEstanteria` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idPiso`)
) ENGINE=MyISAM AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacion_piso: 83 rows
DELETE FROM `ubicacion_piso`;
/*!40000 ALTER TABLE `ubicacion_piso` DISABLE KEYS */;
INSERT INTO `ubicacion_piso` (`idPiso`, `idEstanteria`, `idPisoEstanteria`, `descripcion`) VALUES
	(1, 1, 1, 'A1:Z1:L1:E1:P1'),
	(2, 1, 2, 'A1:Z1:L1:E1:P2'),
	(3, 1, 3, 'A1:Z1:L1:E1:P3'),
	(4, 2, 1, 'A1:Z1:L1:E2:P1'),
	(5, 2, 2, 'A1:Z1:L1:E2:P2'),
	(6, 2, 3, 'A1:Z1:L1:E2:P3'),
	(7, 3, 1, 'A1:Z1:L1:E3:P1'),
	(8, 3, 2, 'A1:Z1:L1:E3:P2'),
	(9, 3, 3, 'A1:Z1:L1:E3:P3'),
	(10, 4, 1, 'A1:Z1:L2:E1:P1'),
	(11, 4, 2, 'A1:Z1:L2:E1:P2'),
	(12, 4, 3, 'A1:Z1:L2:E1:P3'),
	(13, 5, 1, 'A1:Z1:L2:E2:P1'),
	(14, 5, 2, 'A1:Z1:L2:E2:P2'),
	(15, 5, 3, 'A1:Z1:L2:E2:P3'),
	(16, 6, 1, 'A1:Z1:L2:E3:P1'),
	(17, 6, 2, 'A1:Z1:L2:E3:P2'),
	(18, 6, 2, 'A1:Z1:L2:E3:P3'),
	(19, 7, 1, 'A1:Z2:L1:E1:P1'),
	(20, 7, 2, 'A1:Z2:L1:E1:P2'),
	(21, 7, 3, 'A1:Z2:L1:E1:P3'),
	(22, 8, 1, 'A1:Z2:L1:E2:P1'),
	(23, 8, 2, 'A1:Z2:L1:E2:P2'),
	(24, 8, 3, 'A1:Z2:L1:E2:P3'),
	(25, 9, 1, 'A1:Z2:L1:E3:P1'),
	(26, 9, 2, 'A1:Z2:L1:E3:P2'),
	(27, 9, 3, 'A1:Z2:L1:E3:P3'),
	(28, 10, 1, 'A1:Z2:L2:E1:P1'),
	(29, 10, 2, 'A1:Z2:L2:E1:P2'),
	(30, 10, 3, 'A1:Z2:L2:E1:P3'),
	(31, 11, 1, 'A1:Z2:L2:E2:P1'),
	(32, 11, 2, 'A1:Z2:L2:E2:P2'),
	(33, 11, 3, 'A1:Z2:L2:E2:P3'),
	(34, 12, 1, 'A1:Z2:L2:E3:P1'),
	(35, 12, 2, 'A1:Z2:L2:E3:P2'),
	(36, 12, 3, 'A1:Z2:L2:E3:P3'),
	(37, 13, 1, 'A1:Z3:L1:E1:P1'),
	(38, 13, 2, 'A1:Z3:L1:E1:P2'),
	(39, 13, 3, 'A1:Z3:L1:E1:P3'),
	(40, 13, 4, 'A1:Z3:L1:E1:P4'),
	(41, 14, 1, 'A1:Z3:L1:E2:P1'),
	(42, 14, 2, 'A1:Z3:L1:E2:P2'),
	(43, 14, 3, 'A1:Z3:L1:E2:P3'),
	(44, 14, 4, 'A1:Z3:L1:E2:P4'),
	(45, 15, 1, 'A1:Z4:L1:E1:P1'),
	(46, 15, 2, 'A1:Z4:L1:E1:P2'),
	(47, 15, 3, 'A1:Z4:L1:E1:P3'),
	(48, 15, 4, 'A1:Z4:L1:E1:P4'),
	(49, 16, 1, 'A1:Z4:L1:E2:P1'),
	(50, 16, 2, 'A1:Z4:L1:E2:P2'),
	(51, 16, 3, 'A1:Z4:L1:E2:P3'),
	(52, 16, 4, 'A1:Z4:L1:E2:P4'),
	(53, 17, 1, 'A1:Z4:L1:E3:P1'),
	(54, 17, 2, 'A1:Z4:L1:E3:P2'),
	(55, 17, 3, 'A1:Z4:L1:E3:P3'),
	(56, 17, 4, 'A1:Z4:L1:E3:P4'),
	(57, 18, 1, 'A1:Z4:L1:E4:P1'),
	(58, 18, 2, 'A1:Z4:L1:E4:P2'),
	(59, 18, 3, 'A1:Z4:L1:E4:P3'),
	(60, 18, 4, 'A1:Z4:L1:E4:P4'),
	(61, 19, 1, 'A1:Z5:L1:E1:P1'),
	(62, 19, 2, 'A1:Z5:L1:E1:P2'),
	(63, 19, 3, 'A1:Z5:L1:E1:P3'),
	(64, 19, 4, 'A1:Z5:L1:E1:P4'),
	(65, 20, 1, 'A1:Z5:L1:E2:P1'),
	(66, 20, 2, 'A1:Z5:L1:E2:P2'),
	(67, 20, 3, 'A1:Z5:L1:E2:P3'),
	(68, 20, 4, 'A1:Z5:L1:E2:P4'),
	(69, 21, 1, 'A1:Z5:L1:E3:P1'),
	(70, 21, 2, 'A1:Z5:L1:E3:P2'),
	(71, 21, 3, 'A1:Z5:L1:E3:P3'),
	(72, 21, 4, 'A1:Z5:L1:E3:P4'),
	(73, 22, 1, 'A1:Z5:L1:E4:P1'),
	(74, 22, 2, 'A1:Z5:L1:E4:P2'),
	(75, 22, 3, 'A1:Z5:L1:E4:P3'),
	(76, 22, 4, 'A1:Z5:L1:E4:P4'),
	(77, 23, 1, '0973 CPT'),
	(78, 24, 1, '8066 HFW'),
	(79, 25, 1, 'Vehículo III'),
	(80, 26, 1, 'Zona de envasado'),
	(88, 34, 1, 'Granda'),
	(87, 33, 1, 'Entrada'),
	(89, 35, 1, 'Salida');
/*!40000 ALTER TABLE `ubicacion_piso` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacion_producto
CREATE TABLE IF NOT EXISTS `ubicacion_producto` (
  `UBICACION_idUbicacion` bigint(20) NOT NULL DEFAULT '0',
  `productosUbicacion_idProducto` bigint(20) NOT NULL DEFAULT '0',
  KEY `FK37AE05205530B0E3` (`UBICACION_idUbicacion`),
  KEY `FK37AE05206FDE382E` (`productosUbicacion_idProducto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacion_producto: 0 rows
DELETE FROM `ubicacion_producto`;
/*!40000 ALTER TABLE `ubicacion_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `ubicacion_producto` ENABLE KEYS */;


# Dumping structure for table tierrina.ubicacion_zona
CREATE TABLE IF NOT EXISTS `ubicacion_zona` (
  `idZona` bigint(20) NOT NULL AUTO_INCREMENT,
  `idAlmacen` bigint(20) NOT NULL DEFAULT '0',
  `idZonaAlmacen` bigint(20) NOT NULL DEFAULT '0',
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `tipoAlmacenado` char(1) NOT NULL DEFAULT '',
  `urlPlano` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idZona`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.ubicacion_zona: 14 rows
DELETE FROM `ubicacion_zona`;
/*!40000 ALTER TABLE `ubicacion_zona` DISABLE KEYS */;
INSERT INTO `ubicacion_zona` (`idZona`, `idAlmacen`, `idZonaAlmacen`, `descripcion`, `tipoAlmacenado`, `urlPlano`) VALUES
	(1, 1, 1, 'Conservacion 1', '', '/img/planos/barciaConservacion1.png'),
	(2, 1, 2, 'Conservacion 2', '', '/img/planos/barciaConservacion2.png'),
	(8, 1, 8, 'Congelador', '', '/img/planos/barciaCongelador.png'),
	(3, 1, 3, 'Cartonaje', '', '/img/planos/barciaCartonaje.png'),
	(4, 1, 4, 'Almacen 1', '', '/img/planos/barciaAlmacen1.png'),
	(5, 1, 5, 'Almacen 2', '', '/img/planos/barciaAlmacen2.png'),
	(6, 1, 6, 'Zona de envasado', '', '/img/planos/barciaZonaEnvasado.png'),
	(7, 1, 7, 'Almacenamiento palets - Maquinaria y vehiculos', '', '/img/planos/barciaPaletsMaquinaria.png'),
	(9, 2, 1, '0973 CPT', '', ''),
	(10, 3, 1, '8066 HFW', '', ''),
	(11, 4, 1, 'Vehículo III', '', ''),
	(12, 1, 0, 'Entrada', '', '/img/planos/barciaEntrada.png'),
	(13, 5, 1, 'Granda', '', ''),
	(14, 1, 1, 'Salida', '', '');
/*!40000 ALTER TABLE `ubicacion_zona` ENABLE KEYS */;


# Dumping structure for table tierrina.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `idRol` tinyint(3) NOT NULL DEFAULT '1',
  `login` varchar(50) CHARACTER SET latin1 NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `last_passwd_gen` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `habilitado` char(50) CHARACTER SET latin1 NOT NULL DEFAULT 'S',
  PRIMARY KEY (`idUsuario`),
  KEY `idRol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

# Dumping data for table tierrina.usuario: ~3 rows (approximately)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`, `idRol`, `login`, `password`, `last_passwd_gen`, `habilitado`) VALUES
	(4, 2, 'prueba', 'e7ad116336da8568d74ec4047e6c8926', '2011-10-20 12:20:09', 'S'),
	(5, 1, 'admin', 'f561aaf6ef0bf14d4208bb46a4ccb3ad', '2011-10-20 12:20:09', 'S'),
	(6, 2, 'Victor', '1cc0aab597061d1d7afe37cf8e9a8564', '2011-10-20 12:20:09', 'S');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


# Dumping structure for table tierrina.usuario_mensaje
CREATE TABLE IF NOT EXISTS `usuario_mensaje` (
  `idMensaje` bigint(20) NOT NULL AUTO_INCREMENT,
  `idUsuario` bigint(20) NOT NULL,
  `mensaje` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `estado` char(1) COLLATE utf8_bin NOT NULL DEFAULT 'S',
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idMensaje`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# Dumping data for table tierrina.usuario_mensaje: ~4 rows (approximately)
DELETE FROM `usuario_mensaje`;
/*!40000 ALTER TABLE `usuario_mensaje` DISABLE KEYS */;
INSERT INTO `usuario_mensaje` (`idMensaje`, `idUsuario`, `mensaje`, `estado`, `fecha`) VALUES
	(1, 1, 'Hola mi pesadillad', 'S', '2011-10-04 11:46:20'),
	(2, 1, 'Otra vez al ataque', 'S', '2011-10-04 11:46:20'),
	(3, 1, 'adfa', 'S', '2011-10-04 11:46:20'),
	(4, 1, 'Que me cuentas', 'S', '2011-10-04 13:34:25');
/*!40000 ALTER TABLE `usuario_mensaje` ENABLE KEYS */;


# Dumping structure for table tierrina.vehiculo
CREATE TABLE IF NOT EXISTS `vehiculo` (
  `idVehiculo` bigint(20) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(50) DEFAULT NULL,
  `idProveedor` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`idVehiculo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

# Dumping data for table tierrina.vehiculo: 0 rows
DELETE FROM `vehiculo`;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
