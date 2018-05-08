/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE albaran (
  idAlbaran bigint(20) NOT NULL DEFAULT '0',
  idCliente bigint(20) DEFAULT NULL,
  idVehiculo bigint(20) DEFAULT NULL,
  idComercial bigint(20) DEFAULT NULL,
  idTipoVehiculo bigint(20) DEFAULT NULL,
  tipoAlbaran char(1) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  fecha date DEFAULT NULL,
  destino varchar(100) DEFAULT NULL,
  codigoAlbaran varchar(50) DEFAULT NULL,
  pesoNetoTotal double(10,2) DEFAULT NULL,
  pesoBrutoTotal double(10,2) DEFAULT NULL,
  numeroBultosTotal double(10,2) DEFAULT NULL,
  cantidadTotal double(10,2) DEFAULT NULL,
  precioUnitarioTotal double(10,2) DEFAULT NULL,
  importeTotal double(10,2) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  habilitado char(1) DEFAULT NULL,
  facturado char(1) DEFAULT 'N',
  estado char(1) DEFAULT 'P',
  idFormaPago bigint(20) DEFAULT '0',
  peso double(8,2) DEFAULT '0.00',
  PRIMARY KEY (idAlbaran)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE albaran_detalle (
  idRegistroSalida bigint(20) NOT NULL DEFAULT '0',
  idAlbaran bigint(20) DEFAULT NULL,
  linNum tinyint(4) DEFAULT NULL,
  codigoAlbaran varchar(20) DEFAULT NULL,
  codigoSalida varchar(20) DEFAULT NULL,
  eanProducto varchar(14) DEFAULT NULL,
  pesoNeto double(10,2) DEFAULT NULL,
  numeroBultos bigint(20) DEFAULT NULL,
  cantidadUnitaria double(10,2) DEFAULT NULL,
  precioUnitario double(10,2) DEFAULT NULL,
  importe double(10,2) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  PRIMARY KEY (idRegistroSalida)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE albaran_factura (
  idFactura bigint(20) NOT NULL DEFAULT '0',
  idAlbaran bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (idFactura,idAlbaran)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE analisis_registro (
  idAnalisis bigint(20) NOT NULL DEFAULT '0',
  idRegistroEntrada bigint(20) NOT NULL DEFAULT '0',
  varIG tinyint(5) DEFAULT '0',
  varSP tinyint(5) DEFAULT '0',
  varDP tinyint(5) DEFAULT '0',
  varDA tinyint(5) DEFAULT '0',
  varM tinyint(5) DEFAULT '0',
  calidad double(4,2) DEFAULT '0.00',
  baremoCalidad varchar(100) DEFAULT NULL,
  PRIMARY KEY (idAnalisis)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE banco (
  idBanco bigint(20) NOT NULL AUTO_INCREMENT,
  nombre varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (idBanco)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO banco (idBanco, nombre) VALUES (1,'Santander'),(2,'BBVA'),(4,' No definido');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE bulto (
  idBulto bigint(20) NOT NULL,
  codigoSalida varchar(20) DEFAULT NULL,
  direccionEntrega varchar(14) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE bulto_lotes (
  idBulto bigint(20) NOT NULL,
  lote varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE categoria (
  idCategoria bigint(20) NOT NULL AUTO_INCREMENT,
  nombre varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (idCategoria)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO categoria (idCategoria, nombre) VALUES (1,'Extra'),(2,'Extra B'),(3,'Primera'),(4,'Segunda'),(6,'Destrio'),(10,'Sin categorizar');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE cliente_diasentrega (
  idUsuario bigint(20) NOT NULL DEFAULT '0',
  idDiaEntrega tinyint(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (idUsuario,idDiaEntrega)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE contacto (
  idContacto bigint(20) NOT NULL DEFAULT '0',
  idUsuario bigint(20) NOT NULL DEFAULT '0',
  nombreContacto varchar(200) NOT NULL DEFAULT '',
  cargoContacto varchar(150) DEFAULT NULL,
  telefonoContacto varchar(50) DEFAULT NULL,
  extensionContacto varchar(10) DEFAULT NULL,
  emailContacto varchar(100) DEFAULT NULL,
  dptoContacto varchar(100) DEFAULT NULL,
  PRIMARY KEY (idContacto),
  KEY idUsuario (idUsuario)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO contacto (idContacto, idUsuario, nombreContacto, cargoContacto, telefonoContacto, extensionContacto, emailContacto, dptoContacto) VALUES (1,1,'Juan Carlos Barrios','Gerente','','','',''),(2,2,'Nelson Perez','Gerente','','','',''),(3,3,'Tino','Agricultor','985979801','','',''),(4,4,'José Antonio -Maite','Jefe Ventas','985898052-605240951','','',''),(5,5,'Kin','','','','',''),(6,6,'','','','','',''),(7,7,'','','','','',''),(8,8,'Fanny Cárdenas','Gerente','0059176635960','','',''),(9,9,'Juan Carlos','Gerente','630988771','','',''),(10,10,'Manuel','Gerente','630187682','','',''),(11,11,'','','','','',''),(12,12,'Jose Manuel Garcia','Gerente','647 575 078','','',''),(13,13,'Francisco Junco','Jefe Ventas','985794215','','',''),(14,14,'','','','','',''),(15,15,'','','','','',''),(16,16,'','','','','',''),(17,17,'','','985551011','','',''),(18,18,'Víctor','','','','',''),(19,19,'','','','','',''),(20,20,'Jose Maria Vazquez','Gerente','972357015','','',''),(21,21,'Antonio Albornoz','Gerente','625 348 700','','',''),(22,22,'','','','','',''),(23,23,'David','Comercial','','','',''),(24,24,'Paqui','Responsable ventas','608821116','','',''),(25,25,'Antonio-Maria','','983313339','','',''),(26,26,'Antonio Albornoz','Gerente','625 348 700','','','');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE cosecha (
  idCosecha bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(100) NOT NULL DEFAULT '',
  tipo set('V','N','I') DEFAULT NULL,
  PRIMARY KEY (idCosecha)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO cosecha (idCosecha, nombre, tipo) VALUES (1,'IMP-Mayo-Julio','V'),(2,'IMP-Diciembre-Enero','I'),(3,'NAC-Septiembre','N'),(0,'Sin referencia','');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE direccion (
  idDireccion bigint(20) NOT NULL AUTO_INCREMENT,
  empresa_idUsuario bigint(20) NOT NULL DEFAULT '0',
  idProvincia bigint(20) DEFAULT '0',
  idRuta bigint(20) DEFAULT NULL,
  tipoDireccion char(1) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL DEFAULT 'F',
  nombreCalle varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  ubicacion varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT '0',
  portal varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  piso varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  letra varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  puerta varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  escalera varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  localidad varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  municipio varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  codigoPostal varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  pais varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  horarioEntrega varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  ean varchar(14) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (idDireccion),
  KEY FK6CB05D10918B1F06 (empresa_idUsuario),
  KEY idProvincia (idProvincia),
  KEY idRuta (idRuta)
) ENGINE=MyISAM AUTO_INCREMENT=153 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO direccion (idDireccion, empresa_idUsuario, idProvincia, idRuta, tipoDireccion, nombreCalle, ubicacion, portal, piso, letra, puerta, escalera, localidad, municipio, codigoPostal, pais, horarioEntrega, ean) VALUES (1,1,0,NULL,'F','','null',NULL,NULL,NULL,NULL,NULL,'Santa Cruz de la Sierra',NULL,'',NULL,NULL,NULL),(2,2,0,NULL,'F','','null',NULL,NULL,NULL,NULL,NULL,'Santa Cruz de la Sierra',NULL,'',NULL,NULL,NULL),(3,3,5,NULL,'F','S/N','null',NULL,NULL,NULL,NULL,NULL,'Modreiros',NULL,'',NULL,NULL,NULL),(4,4,5,NULL,'F','Naveda,12','null',NULL,NULL,NULL,NULL,NULL,'Naveda',NULL,'33310',NULL,NULL,NULL),(5,5,5,NULL,'F','Valtravieso','null',NULL,NULL,NULL,NULL,NULL,'Santiago de Otur',NULL,'',NULL,NULL,NULL),(6,6,5,NULL,'F','','Valdés',NULL,NULL,NULL,NULL,NULL,'La Figal s/n',NULL,'',NULL,NULL,NULL),(7,7,5,NULL,'F','P.I. BARCIA-ALMUÃ‘A Parc. 11 Fase II','null',NULL,NULL,NULL,NULL,NULL,'LUARCA',NULL,'33700',NULL,NULL,NULL),(8,8,0,NULL,'F','Manuel María Caballero','null',NULL,NULL,NULL,NULL,NULL,'Comarapa',NULL,'',NULL,NULL,NULL),(9,9,43,NULL,'F','ol. Industrial Bargas, Nave 21','null',NULL,NULL,NULL,NULL,NULL,'Bargas',NULL,'',NULL,NULL,NULL),(10,10,24,NULL,'F','Calle del Balneario','null',NULL,NULL,NULL,NULL,NULL,'Arteixo',NULL,'15142',NULL,NULL,NULL),(11,11,0,NULL,'F','','null',NULL,NULL,NULL,NULL,NULL,'',NULL,'',NULL,NULL,NULL),(12,12,5,NULL,'F','pol. ind. Barcia-almuña Parcela 14 Fase I','null',NULL,NULL,NULL,NULL,NULL,'Luarca',NULL,'33787',NULL,NULL,NULL),(13,13,5,NULL,'F','Parque Empresarial Aguila del Nora','null',NULL,NULL,NULL,NULL,NULL,'Colloto',NULL,'',NULL,NULL,NULL),(14,14,5,NULL,'F','Edif. Servicios Logisticos nÂº 1-Puerto El Musel','null',NULL,NULL,NULL,NULL,NULL,'Gijon',NULL,'33212',NULL,NULL,NULL),(15,15,5,NULL,'F','Puerto del Musel','null',NULL,NULL,NULL,NULL,NULL,'Gijón',NULL,'33212',NULL,NULL,NULL),(16,16,5,NULL,'F','Cervantes, 1 Esq. Covadonga 2','null',NULL,NULL,NULL,NULL,NULL,'Lugones',NULL,'33420',NULL,NULL,NULL),(17,17,5,NULL,'F','Avda. de Gijon, 2','null',NULL,NULL,NULL,NULL,NULL,'Aviles',NULL,'33400',NULL,NULL,NULL),(18,18,5,NULL,'F','P.I. BARCIA-ALMUÃ‘A Parc. 11 Fase II','null',NULL,NULL,NULL,NULL,NULL,'Luarca',NULL,'33700',NULL,NULL,NULL),(19,19,5,NULL,'F','','null',NULL,NULL,NULL,NULL,NULL,'',NULL,'',NULL,NULL,NULL),(20,20,16,NULL,'F','Montjuic, 21','null',NULL,NULL,NULL,NULL,NULL,'Blanes',NULL,'17300',NULL,NULL,NULL),(21,21,5,NULL,'F','Plaza Longoria Carbajal, 3 2Âº D','null',NULL,NULL,NULL,NULL,NULL,'Oviedo',NULL,'33002',NULL,NULL,NULL),(22,22,5,NULL,'F','Pol. Granda II. C/ Los Pinos,Nave 12','null',NULL,NULL,NULL,NULL,NULL,'Granda',NULL,'33199',NULL,NULL,NULL),(23,23,50,NULL,'F','olígono Sidamon Industrial ','null',NULL,NULL,NULL,NULL,NULL,'Sidamon',NULL,'25222',NULL,NULL,NULL),(24,24,3,NULL,'F','Alcalde Baeza Santamaria,8','null',NULL,NULL,NULL,NULL,NULL,'El Campello',NULL,'03560',NULL,NULL,NULL),(25,25,45,NULL,'F','Etileno,10','null',NULL,NULL,NULL,NULL,NULL,'Pol. Ind. San Cristobal',NULL,'47012',NULL,NULL,NULL),(26,26,5,NULL,'F','Plaza Longoria Carbajal, 3 2Âº D','null',NULL,NULL,NULL,NULL,NULL,'Oviedo',NULL,'33002',NULL,NULL,NULL);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE entidad (
  idUsuario bigint(20) NOT NULL AUTO_INCREMENT,
  idSector bigint(20) DEFAULT NULL,
  idTipo bigint(20) DEFAULT NULL,
  idRol bigint(20) NOT NULL DEFAULT '0',
  idProvincia bigint(20) DEFAULT NULL,
  login varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  nombre varchar(255) DEFAULT NULL,
  apellido1 varchar(255) DEFAULT NULL,
  apellido2 varchar(255) DEFAULT NULL,
  nif varchar(255) DEFAULT NULL,
  ean varchar(14) DEFAULT NULL,
  registroMercantil varchar(50) DEFAULT NULL,
  telefono varchar(30) DEFAULT NULL,
  fax varchar(30) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  web varchar(255) DEFAULT NULL,
  dsctoMercancia decimal(5,2) DEFAULT '1.00',
  dsctoValor decimal(5,2) DEFAULT '0.00',
  foto varchar(255) DEFAULT NULL,
  descripcion varchar(255) DEFAULT NULL,
  IDCONTACTO bigint(20) DEFAULT NULL,
  IDSUCURSAL bigint(20) DEFAULT NULL,
  fechaRegistro date DEFAULT NULL,
  usuarioResponsable varchar(50) DEFAULT NULL,
  habilitado char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (idUsuario),
  KEY FK392F62E475F64A24 (IDSUCURSAL),
  KEY FK392F62E4CD2D5BF3 (IDCONTACTO),
  KEY idSector (idSector),
  KEY idTipo (idTipo),
  KEY idRol (idRol)
) ENGINE=MyISAM AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO entidad (idUsuario, idSector, idTipo, idRol, idProvincia, login, password, nombre, apellido1, apellido2, nif, ean, registroMercantil, telefono, fax, email, web, dsctoMercancia, dsctoValor, foto, descripcion, IDCONTACTO, IDSUCURSAL, fechaRegistro, usuarioResponsable, habilitado) VALUES (1,NULL,1,2,NULL,NULL,NULL,'AGRISOS',NULL,NULL,'','0000000000001',NULL,'','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','prueba','S'),(2,NULL,1,2,NULL,NULL,NULL,'ASOPROF',NULL,NULL,'','0000000000002',NULL,'00 591 33433630','','www.asoprof.com','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','prueba','S'),(3,NULL,1,2,NULL,NULL,NULL,'Celestino Perez Santiago(Prod.NÃ‚Âº 361- D.Origen)',NULL,NULL,'','0000000000003',NULL,'985979801','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(4,NULL,1,2,NULL,NULL,NULL,'EMBUTIDOS NAVEDA -Antonio Palacio Longo',NULL,NULL,'52618571E','0000000000004',NULL,'985898052 Fax:985898078','','www.naveda.com','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(5,NULL,1,2,NULL,NULL,NULL,'Francisco (Kin)',NULL,NULL,'','0000000000005',NULL,'985641227','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(6,NULL,1,2,NULL,NULL,NULL,'Jose Garcia Perez (Prod. 368 Den Origen)',NULL,NULL,'71867185M','0000000000006',NULL,'985470697','','','','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(7,NULL,1,2,NULL,NULL,NULL,'LA TIERRINA VAQUEIRA',NULL,NULL,'B74140591','0000000000007',NULL,'','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(8,NULL,1,2,NULL,NULL,NULL,'LEGUCOM',NULL,NULL,'2998041013','0000000000008',NULL,'0059139462141','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(9,NULL,1,2,NULL,NULL,NULL,'LEGUMBRES SELECTAS LA POSADA S.L.',NULL,NULL,'B45428596','0000000000009',NULL,'630988771','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(10,NULL,1,2,NULL,NULL,NULL,'MANUEL MAREQUE BOEDO',NULL,NULL,'76306597A',NULL,NULL,'630187682','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(11,NULL,1,2,NULL,NULL,NULL,'Maruja de Sante',NULL,NULL,'',NULL,NULL,'','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(12,NULL,1,2,NULL,NULL,NULL,'Pepe el Bueno, Herederos de',NULL,NULL,'',NULL,NULL,'Tel./Fax: 985 470475','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(13,NULL,1,2,NULL,NULL,NULL,'QUESOS DEL PRINCIPADO DE ASTURIAS SL',NULL,NULL,'B33120361',NULL,NULL,'985794215','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(14,NULL,2,2,NULL,NULL,NULL,'ASTRACON',NULL,NULL,'F33968934',NULL,NULL,'985325784','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(15,NULL,2,2,NULL,NULL,NULL,'COGITRANS',NULL,NULL,'',NULL,NULL,'','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(16,NULL,2,2,NULL,NULL,NULL,'MRW-Mensastur Lugones S.L.',NULL,NULL,'B33536665',NULL,NULL,'Tel. 985266211-Fax: 985268175','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(17,NULL,2,2,NULL,NULL,NULL,'NACEX- Paqueteria',NULL,NULL,'',NULL,NULL,'985551011-900100000','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(18,NULL,2,2,NULL,NULL,NULL,'Tierrina Vaqueira (Transporte)',NULL,NULL,'',NULL,NULL,'','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(19,NULL,2,2,NULL,NULL,NULL,'ZAMAR -Grupo DUCO',NULL,NULL,'',NULL,NULL,'985263663','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(20,NULL,3,2,NULL,NULL,NULL,'Fantexsa SL (Hilos Máquina Coser)',NULL,NULL,'B60151602',NULL,NULL,'Tel./Fax: 972357','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(21,NULL,4,2,NULL,NULL,NULL,'Albornoz y Asociados SL',NULL,NULL,'',NULL,NULL,'Tel:985206103 Fax: 985207607','','www.albornozyasociados.com','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(22,NULL,4,2,NULL,NULL,NULL,'Bobes- Rafael Bobes Fdez.',NULL,NULL,'',NULL,NULL,'985985070','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(23,NULL,4,2,NULL,NULL,NULL,'ILERAFITEX',NULL,NULL,'',NULL,NULL,'973 560 231','','www.ilerafitex.com','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(24,NULL,4,2,NULL,NULL,NULL,'ORION IMPESIONES S.L.',NULL,NULL,'B54074307',NULL,NULL,'965636537- 608821116','','','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(25,NULL,4,2,NULL,NULL,NULL,'SERVIGRAMA Ind. Pub. S.L.',NULL,NULL,'B47321476',NULL,NULL,'983313339 Fax:983313400','','www.servigrama.es','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S'),(26,NULL,5,2,NULL,NULL,NULL,'Albornoz y Asociados SL',NULL,NULL,'B74040320',NULL,NULL,'Tel:985206103 Fax: 985207607','','www.albornozyasociados.com','null','0.00','0.00',NULL,NULL,NULL,NULL,'2011-04-11','admin','S');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE entidad_rol (
  idUsuario bigint(20) NOT NULL DEFAULT '0',
  idRol bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (idUsuario,idRol),
  KEY idRol (idRol)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO entidad_rol (idUsuario, idRol) VALUES (1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(24,2),(25,2),(26,2);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE entrega (
  idEntrega int(5) NOT NULL,
  nombre varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idEntrega)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE envase (
  idEnvase bigint(20) NOT NULL AUTO_INCREMENT,
  idMaterial bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(255) NOT NULL DEFAULT '',
  descripcion text,
  dimensiones varchar(255) DEFAULT NULL,
  peso double(10,2) DEFAULT NULL,
  stock double(10,2) DEFAULT NULL,
  ean double(10,2) DEFAULT NULL,
  habilitado char(1) DEFAULT 'S',
  PRIMARY KEY (idEnvase)
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO envase (idEnvase, idMaterial, nombre, descripcion, dimensiones, peso, stock, ean, habilitado) VALUES (1,1,'Bolsa 15x35 cm trasparente -Hasta 1 Kg.','','',0.00,7901.00,NULL,'S'),(2,1,'Bolsa 30x50 cm Faba Fresca,Transp. galga 200-Hasta 2,5 Kg.',NULL,NULL,NULL,100.00,NULL,'S'),(3,1,'Bolsa 18x30 cm trasparente galga 300-Hasta 1 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(4,1,'Bolsa 18x19 cm trasparente galga 300-Hasta 0,5 Kg.','','18x19',0.00,0.00,NULL,'S'),(5,1,'Bolsa Plástico 0.5kg. Orbayu',NULL,NULL,NULL,9675.00,NULL,'S'),(6,2,'Saco Sin Marca 65x40 cm. Transparente-Hasta 15 Kg.','','',0.00,0.00,NULL,'S'),(7,2,'Saco Sin Marca 67x35 cm con asa, Transp. -Hasta 10 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(8,2,'Saco Sin Marca 80x45 cm.  Blanco con fuelle - Hasta 25 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(9,2,'Saco Sin Marca 95x55 cm.  Blanco con fuelle- Hasta 50 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(10,2,'Saco \"La Tierrina Vaqueira\"  67x35 cm. con asa, Transp. -Hasta 10 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(11,2,'Saco \"Legumbres FABES\"  80x45 cm. Transparente. -Hasta 25 Kg.','saco laminado micro-perforado en caliente, agujero mediano y pre-separados para maquina envasadora.','80 x 45',10.00,0.00,NULL,'S'),(12,2,'Saco Recuperado todos tipos -Hasta 50 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(13,2,'Saco Big-Bag con tapa y descarga- Hasta 500 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(14,2,'Saco Big-Bag con tapa y descarga- Hasta 900 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(15,3,'Estuche Faba Ast. D.O. \"Tierrina Vaqueira\" 500 g.',NULL,NULL,NULL,0.00,NULL,'S'),(16,3,'Estuche Faba Ast. D.O. \"Tierrina Vaqueira\" 1 Kg.',NULL,NULL,NULL,0.00,NULL,'S'),(17,3,'Estuche Faba Verdina  \"Tierrina Vaqueira\" 500 g.',NULL,NULL,NULL,0.00,NULL,'S'),(18,3,'Caja Expositor Faba Ast. D.O.\"Tierrina Vaqueira\" 24 Uds. x 500 g.',NULL,NULL,NULL,0.00,14.00,'S'),(19,3,'Caja Expositor Faba Ast. D.O.\"Tierrina Vaqueira\" 12 Uds. x 1 Kg..',NULL,NULL,NULL,0.00,14.00,'S'),(20,3,'Caja Expositor Faba Verdina\"Tierrina Vaqueira\" 24 Uds. x 500 g.',NULL,NULL,NULL,0.00,14.00,'S'),(21,3,'Caja Expositor Faba Granja \"Orbayu\" 21 Uds. x 700 g.',NULL,NULL,NULL,0.00,14.00,'S'),(22,3,'Caja Expositor Faba Granja \"Orbayu\" 15 Uds. x 1 Kg.',NULL,NULL,NULL,0.00,14.00,'S'),(23,3,'Caja  Faba Granja \"Orbayu\" 12 Uds. x 700 g',NULL,NULL,NULL,0.00,14.00,'S'),(24,3,'Caja Preparado Fabada \"Orbayu\" 10 Uds. x 2 rac.',NULL,NULL,NULL,0.00,14.00,'S'),(25,3,'Caja Faba Fresca Congelado \"Tierrina Vaqueira\" 4 Uds. x 2,5 Kg.',NULL,NULL,NULL,0.00,14.00,'S'),(26,3,'Plancha cartón 25x14 cm. Preparado Fabada 2 rac.',NULL,NULL,NULL,0.00,13.00,'S'),(27,3,'Plancha cartón 80 x120 paletizado',NULL,NULL,NULL,0.00,0.00,'S'),(28,4,'Saquete Faba Granja \"Orbayu\" 500 g',NULL,NULL,NULL,0.00,13.00,'S'),(29,4,'Saquete Faba Granja \"Orbayu\" 700 g',NULL,NULL,NULL,0.00,13.00,'S'),(30,4,'Saquete tela 700gr. Orbayu',NULL,NULL,NULL,0.00,13.00,'S'),(31,4,'Saquete Faba Granja ALDI \"Orbayu\" 700 g','','',90.00,0.00,13.00,'S'),(32,4,'Saquete Faba Granja \"Orbayu\" 1 Kg.','','',900.00,0.00,13.00,'S'),(33,4,'Saquete Faba Granja \"La Aldea\" 1 Kg.','','',80000.00,0.00,13.00,'S'),(34,4,'Saquete Faba Granja \"La Aldea\" 500g.','','',100.00,0.00,13.00,'S'),(35,4,'Saquete Faba Ast D.O. \"Tierra Astur\" 1 Kg.','','',100.00,0.00,13.00,'S'),(36,4,'Saquete Faba Ast. D.O. \"Sabrosona\" 500 g.','','',56.00,0.00,13.00,'S'),(37,4,'Saquete Faba Ast D.O. \"Tierrina Vaqueira\" 500 g.','','',68.00,0.00,13.00,'S'),(38,4,'Saquete Faba Ast D.O. \"Tierrina Vaqueira\" 1 Kg.','','',10.00,0.00,13.00,'S'),(39,4,'Saquete Faba Ast D.O. \"Tierrina Vaqueira\" 5 Kg.','','',10.00,0.00,13.00,'S'),(40,4,'Saquete Faba Verdina \"Tierrina Vaqueira\" 500 g.','','',10.00,0.00,13.00,'S'),(41,4,'Saquete Faba Verdina \"Tierrina Vaqueira\" 1 Kg.','','',2.00,0.00,NULL,'S'),(42,4,'Saquete Preparado Fabada \"Orbayu\"  2 rac.','','',90.00,0.00,NULL,'S'),(43,4,'Saquete Preparado Fabada \"Orbayu\"  4 rac','','',89.00,0.00,13.00,'S'),(44,5,'Palet EUR 80x120    blanco','','',22.00,0.00,14.00,'S'),(45,5,'Palet EUR 80x120   pintado','','',8.00,0.00,14.00,'S'),(46,5,'Palet 80x120   ligero','','',2.00,0.00,14.00,'S'),(47,5,'Palet 100x120   Blanco','','',3.00,8104.00,14.00,'S'),(48,5,'Palet 100x120   Pintado','','',1.00,0.00,14.00,'S'),(49,5,'Palet 100x120   ligero','','',34.00,0.00,14.00,'S'),(50,5,'Palet 110 x 120','','',NULL,0.00,14.00,'S'),(51,5,'Palet 110 x 110','','',NULL,0.00,14.00,'S'),(52,5,'Palet 120 x120','','',10.00,0.00,NULL,'S'),(53,5,'Palet Otras medidas','','',10.00,0.00,NULL,'S'),(54,5,'Caja madera 60x40 \"Sallar\"','','',30.00,0.00,NULL,'S'),(55,6,'Caja Plastico Faba Fresca','','',1.00,0.00,NULL,'S'),(56,2,'Saquete Faba Granja \"Orbayu\" 5 Kg.',NULL,NULL,NULL,0.00,NULL,'S');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE estadofabas (
  idEstadoFabas bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(100) DEFAULT NULL,
  PRIMARY KEY (idEstadoFabas)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO estadofabas (idEstadoFabas, descripcion) VALUES (1,'Partidas'),(2,'Rajadas'),(3,'Descorticadas'),(4,'Incompletas'),(5,'Manchadas'),(6,'Mohosas'),(7,'Descoloridas'),(8,'Con impurezas'),(9,'Mojadas'),(10,'Germinadas'),(11,'Dañadas'),(0,'Sin fallos');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE estadoproducto (
  idEstado bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(50) DEFAULT NULL,
  PRIMARY KEY (idEstado)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO estadoproducto (idEstado, nombre) VALUES (1,'bueno'),(2,'malo');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fac_orders (
  idOrders bigint(20) NOT NULL AUTO_INCREMENT,
  unh varchar(50) DEFAULT NULL,
  bgmTipo varchar(50) DEFAULT NULL,
  bgmNum varchar(50) DEFAULT NULL,
  bgmFunc varchar(50) DEFAULT NULL,
  aliCond varchar(50) DEFAULT NULL,
  ftxCali varchar(50) DEFAULT NULL,
  nadMs varchar(50) DEFAULT NULL,
  rffMs varchar(50) DEFAULT NULL,
  nadMr varchar(50) DEFAULT NULL,
  rffMr varchar(50) DEFAULT NULL,
  nadSu varchar(50) DEFAULT NULL,
  rffSu varchar(50) DEFAULT NULL,
  nadBy varchar(50) DEFAULT NULL,
  rffBy varchar(50) DEFAULT NULL,
  nadDp varchar(50) DEFAULT NULL,
  rffDp varchar(50) DEFAULT NULL,
  nadIv varchar(50) DEFAULT NULL,
  rffIv varchar(50) DEFAULT NULL,
  nadPr varchar(50) DEFAULT NULL,
  rffPr varchar(50) DEFAULT NULL,
  cux varchar(50) DEFAULT NULL,
  moa79 varchar(50) DEFAULT NULL,
  cnt varchar(50) DEFAULT NULL,
  unt varchar(50) DEFAULT NULL,
  estado char(1) DEFAULT 'R',
  idFormaPago bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (idOrders)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fac_orders_dtm (
  idOrders bigint(20) DEFAULT NULL,
  dtmFech varchar(50) DEFAULT NULL,
  dtmForm varchar(50) DEFAULT NULL,
  dtmFunc varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fac_orders_imd (
  idOrders bigint(20) DEFAULT NULL,
  idLin varchar(50) DEFAULT NULL,
  imdTipo varchar(50) DEFAULT NULL,
  imdCara varchar(50) DEFAULT NULL,
  imdDesc varchar(50) DEFAULT NULL,
  imdDescCod varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fac_orders_lin (
  idOrders bigint(20) DEFAULT NULL,
  idLin varchar(50) DEFAULT NULL,
  linNum varchar(50) DEFAULT NULL,
  piaNumSA varchar(50) DEFAULT NULL,
  piaNumIN varchar(50) DEFAULT NULL,
  imdTipo varchar(50) DEFAULT NULL,
  imdCara varchar(50) DEFAULT NULL,
  imdDesc varchar(50) DEFAULT NULL,
  imdDescCod varchar(50) DEFAULT NULL,
  qty21Cant varchar(50) DEFAULT NULL,
  qty59Cant varchar(50) DEFAULT NULL,
  moa203 varchar(50) DEFAULT NULL,
  priAaa varchar(50) DEFAULT NULL,
  priAab varchar(50) DEFAULT NULL,
  priInf varchar(50) DEFAULT NULL,
  observacion varchar(100) DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fac_orders_loc (
  idOrders varchar(50) DEFAULT NULL,
  idLin varchar(50) DEFAULT NULL,
  idLoc varchar(50) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  qty varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE factura (
  idFactura bigint(10) NOT NULL DEFAULT '0',
  codigoFactura varchar(15) DEFAULT NULL,
  fecha date DEFAULT NULL,
  fechaVencimiento date DEFAULT NULL,
  pedido varchar(20) DEFAULT NULL,
  albaran varchar(20) DEFAULT NULL,
  importeTotal double(10,2) DEFAULT NULL,
  descuento double(10,2) DEFAULT NULL,
  valorIva double(10,2) DEFAULT NULL,
  subtotal double(10,2) DEFAULT NULL,
  cargosTotal double(10,2) DEFAULT NULL,
  total double(10,2) DEFAULT NULL,
  PRIMARY KEY (idFactura)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE factura_cargo (
  idFactura bigint(20) DEFAULT NULL,
  idCargo tinyint(4) DEFAULT NULL,
  tipoCargo char(1) DEFAULT NULL,
  valorCargo double(8,2) DEFAULT NULL,
  ivaCargo double(8,2) DEFAULT NULL,
  totalCargo double(8,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE factura_item (
  idFactura bigint(10) NOT NULL DEFAULT '0',
  idItem bigint(20) NOT NULL DEFAULT '0',
  idRegistroSalida bigint(20) DEFAULT NULL,
  idProducto bigint(10) DEFAULT NULL,
  codigoItem varchar(20) DEFAULT NULL,
  cantidad bigint(3) DEFAULT NULL,
  precioUnitBruto double(8,2) DEFAULT NULL,
  precioUnitNeto double(8,2) DEFAULT NULL,
  precioTotal double(8,2) DEFAULT NULL,
  iva double(8,2) DEFAULT NULL,
  PRIMARY KEY (idItem,idFactura)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE familia (
  idFamilia bigint(20) NOT NULL AUTO_INCREMENT,
  descripcion varchar(255) DEFAULT NULL,
  stock bigint(20) DEFAULT NULL,
  stockMinimo bigint(20) DEFAULT NULL,
  familiaPadre_idFamilia bigint(20) DEFAULT NULL,
  PRIMARY KEY (idFamilia),
  KEY FKEA3E9B6DCCE7DA79 (familiaPadre_idFamilia)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO familia (idFamilia, descripcion, stock, stockMinimo, familiaPadre_idFamilia) VALUES (1,'Legumbres',0,0,NULL),(2,'Especias',0,0,NULL),(3,'Preparado',0,0,NULL),(4,'Especias',0,0,NULL),(5,'Otros',0,0,NULL);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE formapago (
  idFormaPago bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) DEFAULT NULL,
  PRIMARY KEY (idFormaPago)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO formapago (idFormaPago, descripcion) VALUES (1,'Efectivo'),(2,'Cheque'),(3,'Letra 30 días'),(4,'Letra 60 días'),(5,'Letra 90 días'),(6,'Abono bancario'),(7,'Cargo bancario'),(8,'Transferencia bancaria'),(9,'Pagaré'),(10,'Compensación de deuda'),(0,' No aplica');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE formatoentrega (
  idFormatoEntrega bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(20) DEFAULT NULL,
  PRIMARY KEY (idFormatoEntrega)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO formatoentrega (idFormatoEntrega, descripcion) VALUES (1,'No paletizado/Sacos'),(2,'Pallet Europeo'),(3,'Pallet Americano'),(4,'Big Bag'),(5,'Otros');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gestion_produccion_cribado (
  idGestionProduccion bigint(20) NOT NULL DEFAULT '0',
  orden varchar(20) NOT NULL DEFAULT '',
  idCodigoEntradaProducto varchar(20) NOT NULL DEFAULT '',
  idProducto bigint(20) DEFAULT NULL,
  PRIMARY KEY (idGestionProduccion,idCodigoEntradaProducto,orden)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gestion_produccion_desgranado (
  idGestionProduccion bigint(20) NOT NULL DEFAULT '0',
  orden varchar(20) DEFAULT NULL,
  idCodigoEntradaProducto varchar(20) DEFAULT NULL,
  idProducto bigint(20) DEFAULT NULL,
  PRIMARY KEY (idGestionProduccion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gestion_produccion_envasado (
  orden varchar(20) DEFAULT NULL,
  idGestionProduccion varchar(20) DEFAULT NULL,
  idGestionProduccionEnvasado bigint(20) NOT NULL AUTO_INCREMENT,
  codigoEntradaIngredientes varchar(20) DEFAULT NULL,
  idIngredientes bigint(20) DEFAULT NULL,
  cantidadIngredientes bigint(20) DEFAULT NULL,
  cantidadUsadaIngredientes bigint(20) DEFAULT NULL,
  mermaIngredientes bigint(20) DEFAULT NULL,
  codigoEntradaEnvases varchar(20) DEFAULT NULL,
  idEnvases bigint(20) DEFAULT NULL,
  cantidadEnvases bigint(20) DEFAULT NULL,
  cantidadUsadaEnvases bigint(20) DEFAULT NULL,
  mermaEnvases bigint(20) DEFAULT NULL,
  PRIMARY KEY (idGestionProduccionEnvasado)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gestion_produccion_registroactividad (
  idRegistro bigint(20) NOT NULL AUTO_INCREMENT,
  idOrden bigint(20) DEFAULT NULL,
  horaInicio datetime DEFAULT NULL,
  horaFin datetime DEFAULT NULL,
  responsable varchar(20) DEFAULT NULL,
  observaciones varchar(150) DEFAULT NULL,
  PRIMARY KEY (idRegistro)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gestionproduccion (
  idGestionProduccion bigint(20) NOT NULL DEFAULT '0',
  idProducto bigint(20) DEFAULT NULL,
  proceso varchar(50) DEFAULT NULL,
  orden varchar(20) DEFAULT NULL,
  codigoEntrada varchar(20) DEFAULT NULL,
  fecha date DEFAULT NULL,
  habilitado set('S','N') DEFAULT 'S',
  horainicio varchar(50) DEFAULT NULL,
  horafin varchar(50) DEFAULT NULL,
  cantidadprod double(10,2) DEFAULT NULL,
  mermasIngredientes double(8,2) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  notasincidencias varchar(200) DEFAULT NULL,
  notasinstrucciones varchar(200) DEFAULT NULL,
  observaciones varchar(200) DEFAULT NULL,
  estadoproceso char(1) DEFAULT NULL,
  PRIMARY KEY (idGestionProduccion),
  KEY idProducto (idProducto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_congelado (
  orden varchar(20) NOT NULL DEFAULT '',
  codigoEntrada varchar(20) NOT NULL DEFAULT '',
  cantidadDisponible double(10,2) DEFAULT NULL,
  cantidadUsable double(10,2) DEFAULT NULL,
  mermaProducto double(10,2) DEFAULT NULL,
  descripcion varchar(100) DEFAULT NULL,
  PRIMARY KEY (codigoEntrada,orden)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_congelado_ubicacion (
  orden varchar(20) NOT NULL DEFAULT '',
  codigoEntrada varchar(20) NOT NULL DEFAULT '',
  idUbicacion bigint(20) NOT NULL DEFAULT '0',
  idUbicacionNueva bigint(20) DEFAULT NULL,
  cantidad double(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (orden,codigoEntrada,idUbicacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_cribado (
  idGestionProduccion bigint(20) NOT NULL DEFAULT '0',
  orden varchar(20) NOT NULL DEFAULT '',
  idCodigoEntradaProducto varchar(20) NOT NULL DEFAULT '',
  idProducto bigint(20) DEFAULT NULL,
  cantidadUsable decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (idGestionProduccion,idCodigoEntradaProducto,orden)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_desgranado (
  idGestionProduccion bigint(20) NOT NULL DEFAULT '0',
  orden varchar(20) DEFAULT NULL,
  idCodigoEntradaProducto varchar(20) DEFAULT NULL,
  idProducto bigint(20) DEFAULT NULL,
  cantidadUsable decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (idGestionProduccion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_envasado (
  idEnvasado bigint(20) NOT NULL DEFAULT '0',
  idProducto bigint(20) NOT NULL DEFAULT '0',
  orden varchar(20) DEFAULT NULL,
  fecha date DEFAULT NULL,
  fechaCaducidad date DEFAULT NULL,
  lote varchar(20) DEFAULT NULL,
  horainicio datetime DEFAULT NULL,
  horafin datetime DEFAULT NULL,
  cantidad double(8,2) DEFAULT NULL,
  mermasMP double(8,2) DEFAULT NULL,
  mermasEN double(8,2) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  observaciones varchar(200) DEFAULT NULL,
  estadoproceso char(1) DEFAULT NULL,
  habilitado set('S','N') DEFAULT 'S',
  elaborado double(8,2) DEFAULT '0.00',
  precioCoste double(8,2) DEFAULT '0.00',
  ubicado char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (idEnvasado)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_envasado_detalle (
  idLinea bigint(20) NOT NULL AUTO_INCREMENT,
  idTipoRegistro set('E','M') DEFAULT NULL,
  orden varchar(20) DEFAULT NULL,
  codigoEntrada varchar(20) DEFAULT NULL,
  idProducto bigint(20) DEFAULT NULL,
  cantidadDisponible double(8,2) DEFAULT NULL,
  cantidadTeorica double(8,2) DEFAULT NULL,
  cantidadReal double(8,2) DEFAULT NULL,
  mermaProducto double(8,2) DEFAULT NULL,
  descripcion varchar(100) DEFAULT NULL,
  idUbicacion bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (idLinea)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_envasado_registroactividad (
  idRegistro bigint(20) NOT NULL AUTO_INCREMENT,
  idOrden varchar(20) DEFAULT NULL,
  horaInicio datetime DEFAULT NULL,
  horaFin datetime DEFAULT NULL,
  responsable varchar(20) DEFAULT NULL,
  observaciones varchar(150) DEFAULT NULL,
  PRIMARY KEY (idRegistro)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_envasado_ubicacion (
  ordenEnvasado varchar(20) NOT NULL DEFAULT '',
  codigoEntrada varchar(20) NOT NULL DEFAULT '',
  idUbicacion bigint(20) NOT NULL DEFAULT '0',
  cantidad double(8,2) NOT NULL DEFAULT '0.00',
  cantidadReal double(8,2) NOT NULL DEFAULT '0.00',
  mermas double(8,2) NOT NULL DEFAULT '0.00',
  idUbicacionNueva bigint(20) DEFAULT NULL,
  PRIMARY KEY (ordenEnvasado,codigoEntrada,idUbicacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_fumigado (
  orden varchar(20) NOT NULL DEFAULT '',
  codigoEntrada varchar(20) NOT NULL DEFAULT '',
  cantidadDisponible double(10,2) DEFAULT NULL,
  cantidadUsable double(10,2) DEFAULT NULL,
  mermaProducto double(10,2) DEFAULT NULL,
  descripcion varchar(100) DEFAULT NULL,
  PRIMARY KEY (codigoEntrada,orden)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_fumigado_ubicacion (
  orden varchar(20) NOT NULL DEFAULT '',
  codigoEntrada varchar(20) NOT NULL DEFAULT '',
  idUbicacion bigint(20) NOT NULL DEFAULT '0',
  idUbicacionNueva bigint(20) DEFAULT NULL,
  cantidad double(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (orden,codigoEntrada,idUbicacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE gp_seleccion (
  orden varchar(20) NOT NULL DEFAULT '',
  codigoEntrada varchar(20) NOT NULL DEFAULT '',
  tipoSeleccion set('M','A') NOT NULL DEFAULT '',
  cantidadDisponible double(10,2) DEFAULT NULL,
  cantidadUsable double(10,2) DEFAULT NULL,
  mermaProducto double(10,2) DEFAULT NULL,
  PRIMARY KEY (codigoEntrada,orden)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE grupo_producto (
  idGrupoProducto bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (idGrupoProducto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO grupo_producto (idGrupoProducto, nombre) VALUES (1,'Fabas'),(2,'Faba Fresca'),(3,'Alubias Color'),(4,'Alubias Blancas'),(5,'Otras Legumbres'),(6,'Otros Productos');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE homologacion_proveedor (
  idUsuario bigint(20) NOT NULL DEFAULT '0',
  idRequisito tinyint(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (idUsuario,idRequisito)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO homologacion_proveedor (idUsuario, idRequisito) VALUES (1,1),(4,1),(5,4),(6,4),(7,1),(7,3),(8,1),(8,2),(8,3),(9,2),(9,3),(10,1),(13,2),(13,3),(14,2),(15,2),(20,1),(22,1),(23,1),(24,2),(25,2),(27,1),(28,1);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE impuestos (
  idImpuesto bigint(10) NOT NULL AUTO_INCREMENT,
  nombre varchar(50) DEFAULT NULL,
  porcentaje double unsigned DEFAULT NULL,
  PRIMARY KEY (idImpuesto)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO impuestos (idImpuesto, nombre, porcentaje) VALUES (1,'IVA 18%',18),(2,'IVA 8%',8),(3,'IVA 4%',4);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE incidencia (
  idIncidencia bigint(20) NOT NULL AUTO_INCREMENT,
  nombre varchar(50) DEFAULT NULL,
  PRIMARY KEY (idIncidencia)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO incidencia (idIncidencia, nombre) VALUES (1,'Cribar'),(2,'Congelar'),(3,'Fumigar'),(4,'Seleccionar'),(5,'Desgranar'),(6,'Otra');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE incidenciaproducto (
  idIncidencia bigint(20) NOT NULL AUTO_INCREMENT,
  loteProducto varchar(20) DEFAULT NULL,
  estado varchar(255) DEFAULT NULL,
  fecha datetime DEFAULT NULL,
  descripcion varchar(255) DEFAULT NULL,
  cantidad double(8,2) DEFAULT NULL,
  idUbicacion bigint(20) DEFAULT NULL,
  descripcionUbicacion varchar(50) DEFAULT NULL,
  PRIMARY KEY (idIncidencia),
  KEY FK237229F98193FD3A (loteProducto)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE lopd (
  idLinea bigint(20) NOT NULL DEFAULT '0',
  texto varchar(200) DEFAULT NULL,
  PRIMARY KEY (idLinea)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO lopd (idLinea, texto) VALUES (1,'Texto sobre LOPD');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE maquina (
  idMaquina bigint(10) NOT NULL DEFAULT '0',
  idTipo bigint(10) NOT NULL DEFAULT '0',
  nombre varchar(50) NOT NULL DEFAULT '',
  fecha date NOT NULL,
  descripcion varchar(200) DEFAULT '',
  PRIMARY KEY (idMaquina)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO maquina (idMaquina, idTipo, nombre, fecha, descripcion) VALUES (1,1,'extintor','2011-06-17','extintor de polvo ABC');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE maquina_calibracion (
  idCalibrado bigint(20) NOT NULL,
  patron varchar(50) DEFAULT '',
  medidaPatron varchar(50) DEFAULT '',
  medidaEquipo varchar(50) DEFAULT '',
  desviacion varchar(50) DEFAULT '',
  PRIMARY KEY (idCalibrado)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE maquina_mantenimiento (
  idMantenimiento bigint(10) NOT NULL AUTO_INCREMENT,
  idTipo bigint(10) NOT NULL DEFAULT '0',
  nombre varchar(50) NOT NULL DEFAULT '',
  descripcion varchar(200) DEFAULT '',
  PRIMARY KEY (idMantenimiento)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE maquina_mantenimiento_ciclo (
  idCiclo bigint(10) NOT NULL,
  descripcion varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idCiclo)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
INSERT INTO maquina_mantenimiento_ciclo (idCiclo, descripcion) VALUES (1,'Diario'),(2,'Semanal'),(3,'Quincenal'),(4,'Mensual'),(5,'Trimestral'),(6,'Anual');
CREATE TABLE maquina_mantenimiento_programacion (
  idMantenimientoProgramacion bigint(10) NOT NULL,
  idMaquina bigint(10) NOT NULL,
  idMantenimiento bigint(10) NOT NULL,
  observaciones varchar(200) DEFAULT NULL,
  fechaProgramada date NOT NULL,
  fechaRealizada date DEFAULT NULL,
  responsable varchar(20) DEFAULT NULL,
  estado char(1) NOT NULL DEFAULT 'P',
  idCiclo bigint(10) NOT NULL,
  idCalibrado bigint(10) NOT NULL DEFAULT '0',
  clasificacion char(1) NOT NULL DEFAULT 'P',
  PRIMARY KEY (idMaquina,idMantenimiento,idMantenimientoProgramacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE maquina_tipo (
  idTipo bigint(10) NOT NULL AUTO_INCREMENT,
  descripcion varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idTipo)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO maquina_tipo (idTipo, descripcion) VALUES (1,'Equipos'),(2,'Instalación'),(3,'Transporte');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE maquinamant (
  idTipoMant tinyint(4) DEFAULT NULL,
  idMaquina tinyint(4) DEFAULT NULL,
  fecha date DEFAULT NULL,
  descripcion text,
  estado set('S','N') DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO maquinamant (idTipoMant, idMaquina, fecha, descripcion, estado) VALUES (1,1,NULL,NULL,'N'),(1,1,NULL,NULL,'N');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE material (
  idMaterial bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(100) DEFAULT NULL,
  PRIMARY KEY (idMaterial)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO material (idMaterial, nombre) VALUES (1,'Bolsas'),(2,'Sacos PP'),(3,'Cartón'),(4,'Saquetes tela'),(5,'Madera'),(6,'Otros Materiales');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE materiaprima (
  idProducto bigint(20) NOT NULL DEFAULT '0',
  idGrupo bigint(20) DEFAULT NULL,
  nombre varchar(200) NOT NULL DEFAULT '',
  tipo char(1) DEFAULT 'M',
  stock double(10,2) DEFAULT NULL,
  habilitado set('S','N') DEFAULT 'S',
  PRIMARY KEY (idProducto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO materiaprima (idProducto, idGrupo, nombre, tipo, stock, habilitado) VALUES (1,1,'Faba Asturiana DO','M',0.00,'S'),(2,1,'Faba Granja Importación','M',0.00,'S'),(3,1,'Faba Granja Nacional','M',0.00,'S'),(4,1,'Faba Verdina Asturiana','M',0.00,'S'),(5,1,'Faba Verdina Importación','M',0.00,'S'),(6,2,'Faba Fresca','M',0.00,'S'),(7,2,'Faba Fresca Vaina','M',0.00,'S'),(8,3,'Alubia Roxa','M',0.00,'S'),(9,3,'Alubia Canaria','M',0.00,'S'),(10,3,'Alubia Pinta Redonda','M',0.00,'S'),(11,3,'Alubia Pinta Larga','M',0.00,'S'),(12,3,'Alubia Caparrón','M',0.00,'S'),(13,3,'Alubia Morada Redonda','M',0.00,'S'),(14,3,'Alubia Morada Larga','M',0.00,'S'),(15,3,'Alubia Palmeña Redonda','M',0.00,'S'),(16,3,'Alubia Palmeña Larga','M',0.00,'S'),(17,3,'Alubia Canela','M',0.00,'S'),(18,3,'Alubia Negra Tolosana','M',0.00,'S'),(19,3,'Frijol Negrito','M',0.00,'S'),(20,3,'Soja','M',0.00,'S'),(21,3,'Frijol Castilla','M',0.00,'S'),(22,4,'Alubia Canellini','M',0.00,'S'),(23,4,'Alubia Planchada','M',0.00,'S'),(24,4,'Alubia Plancheta','M',0.00,'S'),(25,4,'Alubia Redonda','M',0.00,'S'),(26,4,'Judión Grande','M',0.00,'S'),(27,4,'Judión Mediano','M',0.00,'S'),(28,5,'Harina Maíz','M',0.00,'S'),(29,5,'Harina Maíz Torrada','M',0.00,'S'),(30,5,'Arroz Redondo','M',0.00,'S'),(31,5,'Arroz Largo','M',0.00,'S'),(32,5,'Arroz Vaporizado','M',0.00,'S'),(33,5,'Lenteja Pardina','M',0.00,'S'),(34,5,'Lenteja Stom','M',0.00,'S'),(35,5,'Lenteja Castellana','M',0.00,'S'),(36,5,'Garbanzo Castellano Gordo','M',0.00,'S'),(37,5,'Garbanzo Castellano Mediano','M',0.00,'S'),(38,5,'Garbanzo Pedrosillanco','M',0.00,'S'),(39,5,'Garbanzo Fuentesaúco','M',0.00,'S');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE materiaprima_categoria (
  idMateriaCategoria bigint(20) NOT NULL AUTO_INCREMENT,
  idMateriaPrima bigint(20) NOT NULL,
  idCategoria bigint(20) NOT NULL,
  stock double(8,2) NOT NULL DEFAULT '0.00',
  habilitado char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (idMateriaCategoria)
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO materiaprima_categoria (idMateriaCategoria, idMateriaPrima, idCategoria, stock, habilitado) VALUES (1,1,1,0.00,'S'),(2,1,2,0.00,'N'),(3,1,4,0.00,'N'),(4,2,3,0.00,'S'),(5,1,6,0.00,'N'),(6,1,3,0.00,'N'),(7,12,0,0.00,'S'),(29,12,3,0.00,'S'),(30,12,1,0.00,'S'),(96,0,0,0.00,'S'),(94,2,1,0.00,'S'),(95,10,1,0.00,'S'),(92,13,0,0.00,'S'),(93,15,0,0.00,'S'),(91,17,0,0.00,'S'),(90,4,4,0.00,'S'),(89,4,6,0.00,'S'),(88,40,2,0.00,'S'),(97,1,10,0.00,'N'),(98,8,2,0.00,'S'),(99,8,1,0.00,'S'),(100,9,3,0.00,'S'),(101,9,4,0.00,'S'),(102,31,2,0.00,'S'),(103,31,3,0.00,'S'),(104,31,6,0.00,'S'),(105,30,3,0.00,'S'),(106,16,3,0.00,'S'),(107,9,1,0.00,'S');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE materiaprima_envase (
  idProducto bigint(20) NOT NULL DEFAULT '0',
  idEnvase bigint(20) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ordenentrada (
  idOrden bigint(20) NOT NULL DEFAULT '0',
  codigoOrden varchar(25) DEFAULT NULL,
  fecha datetime DEFAULT NULL,
  idTipoRegistro char(1) DEFAULT NULL,
  idProveedor bigint(20) DEFAULT NULL,
  idTransportista bigint(20) DEFAULT NULL,
  origen varchar(50) DEFAULT NULL,
  albaran varchar(50) DEFAULT NULL,
  descVehiculo varchar(100) DEFAULT NULL,
  notasVehiculo varchar(250) DEFAULT NULL,
  habilitado char(1) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  PRIMARY KEY (idOrden)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ordenentrada_tmp (
  idOrden bigint(20) NOT NULL DEFAULT '0',
  codigoOrden varchar(25) DEFAULT NULL,
  fecha datetime DEFAULT NULL,
  idTipoRegistro char(1) DEFAULT NULL,
  idProveedor bigint(20) DEFAULT NULL,
  idTransportista bigint(20) DEFAULT NULL,
  origen varchar(50) DEFAULT NULL,
  albaran varchar(50) DEFAULT NULL,
  descVehiculo varchar(100) DEFAULT NULL,
  notasVehiculo varchar(250) DEFAULT NULL,
  habilitado char(1) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  PRIMARY KEY (idOrden)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto (
  idProducto bigint(20) NOT NULL AUTO_INCREMENT,
  idCategoria bigint(20) NOT NULL DEFAULT '0',
  idEstado bigint(20) DEFAULT '0',
  idTipo bigint(20) DEFAULT NULL,
  idGrupo bigint(20) DEFAULT NULL,
  idMateriaPrima bigint(20) DEFAULT NULL,
  familia_idFamilia bigint(20) DEFAULT NULL,
  fecha date DEFAULT NULL,
  codigoEan varchar(14) DEFAULT NULL,
  nombre varchar(255) DEFAULT NULL,
  descripcion varchar(255) DEFAULT NULL,
  stock double(8,2) DEFAULT '0.00',
  stockMinimo double(8,2) DEFAULT '0.00',
  idBolsa bigint(10) DEFAULT NULL,
  idSaco bigint(10) DEFAULT NULL,
  idCarton bigint(10) DEFAULT NULL,
  idSaquete bigint(10) DEFAULT NULL,
  idMadera bigint(10) DEFAULT NULL,
  idOtro bigint(10) DEFAULT NULL,
  proveedor_idUsuario bigint(20) DEFAULT NULL,
  cliente_idUsuario bigint(20) DEFAULT NULL,
  productoComposicion_idProducto bigint(20) DEFAULT NULL,
  codigoProducto varchar(50) DEFAULT NULL,
  precio double(8,2) DEFAULT NULL,
  precioCoste double(8,2) DEFAULT NULL,
  idImpuesto bigint(20) NOT NULL DEFAULT '1',
  foto varchar(50) DEFAULT 'nohay.jpg',
  fechaActualizacion timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  habilitado char(1) NOT NULL DEFAULT 'S',
  peso double(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (idProducto),
  KEY FKF2D1C16016780DBF (productoComposicion_idProducto),
  KEY FKF2D1C160DF420B37 (cliente_idUsuario),
  KEY FKF2D1C160AB05831B (proveedor_idUsuario),
  KEY FKF2D1C1606FF9C26D (familia_idFamilia),
  KEY idCategoria (idCategoria),
  KEY idEstado (idEstado),
  KEY idTipo (idTipo),
  KEY idGrupo (idGrupo)
) ENGINE=MyISAM AUTO_INCREMENT=118 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO producto (idProducto, idCategoria, idEstado, idTipo, idGrupo, idMateriaPrima, familia_idFamilia, fecha, codigoEan, nombre, descripcion, stock, stockMinimo, idBolsa, idSaco, idCarton, idSaquete, idMadera, idOtro, proveedor_idUsuario, cliente_idUsuario, productoComposicion_idProducto, codigoProducto, precio, precioCoste, idImpuesto, foto, fechaActualizacion, habilitado, peso) VALUES (1,0,NULL,NULL,1,NULL,0,'2010-02-23','8436016090087','Faba Asturiana DO - Tierrina Vaqueira - 1Kg - Cartón','Faba Asturiana DO - Extra - Estuche de Cartón 1kg. Tierrina Vaqueira',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,1,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(71,1,NULL,NULL,1,NULL,1,'2011-03-29','2','Agrupación Faba D.O. \"SABROSONA\" 500 g.','Saco PP 10 uds x 500 g. Faba D.O. Marca \"Sabrosona\"',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(5,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090094','Faba Asturiana DO - Extra -\"TIERRINA VAQUEIRA\"  Estuche 500 g. ','Estuche cartón Faba Asturiana DO -\"TIERRINA VAQUEIRA\"  -  500g. ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(6,1,NULL,NULL,1,NULL,1,'2010-02-23','8888','Faba Ast. DO - Extra \"SABROSONA\" Saquete tela 500gr. ','Faba Asturiana DO - Extra - Saquete tela 500gr. Sabrosona',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(7,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090032','Faba Asturiana DO - Extra - Saco 5kg Tierrina Vaqueira','Faba Asturiana DO - Extra - Saco 5kg Tierrina Vaqueira',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(8,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090049','Faba Asturiana DO - Extra - Saco 10kg Tierrina Vaqueira','Faba Asturiana DO - Extra - Saco 10kg Tierrina Vaqueira',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(10,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090810','Paquete Vacio \"Tierrina Vaqueira\" Faba Asturiana DO - Extra - Envase 400 g.','Faba Asturiana DO - Extra - Envase 400 g.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(11,1,NULL,NULL,1,NULL,1,'2011-02-18','8436016095013','Faba de Granja I. - Extra A - Saco 10kg.','Faba de Granja I. - Extra A - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,NULL,3,'nohay.jpg','2011-06-06 13:17:30','S',100.00),(12,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095020','Faba de Granja I. - Extra A - Saco 15kg.','Faba de Granja I. - Extra A - Saco 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,45.71,3,'nohay.jpg','2011-06-06 13:17:30','S',0.00),(13,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095037','Faba de Granja I. - Extra A - Saco 25kg.','Faba de Granja I. - Extra A - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(14,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095044','Faba de Granja I. - Extra A - Saco 50kg.','Faba de Granja I. - Extra A - Saco 50kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(15,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095105','Faba de Granja I. - Extra B - Saco 10kg.','Faba de Granja I. - Extra B - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(16,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095112','Faba de Granja I. - Extra B - Saco 15kg.','Faba de Granja I. - Extra B - Saco 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(17,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095129','Faba de Granja I. - Extra B - Saco 25kg.','Faba de Granja I. - Extra B - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(18,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095136','Faba de Granja I. - Extra B - Saco 50kg.','Faba de Granja I. - Extra B - Saco 50kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(19,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095204','Faba de Granja I. - Primera - Saco 10kg.','Faba de Granja I. - Primera - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,414.02,3,'nohay.jpg','2011-06-06 13:17:30','S',0.00),(20,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095211','Faba de Granja I. - Primera - Saco 15kg.','Faba de Granja I. - Primera - Saco 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(21,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095228','Faba de Granja I. - Primera - Saco 25kg.','Faba de Granja I. - Primera - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(22,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095228','Faba de Granja I. - Primera - Saco 50kg.','Faba de Granja I. - Primera - Saco 50kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(23,4,NULL,NULL,1,NULL,1,'2010-02-23','8436016095303','Faba de Granja I. - Segunda - Saco 10kg.','Faba de Granja I. - Segunda - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(24,4,NULL,NULL,1,NULL,1,'2010-02-23','8436016095310','Faba de Granja I. - Segunda - Saco 15kg.','Faba de Granja I. - Segunda - Saco 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(25,4,NULL,NULL,1,NULL,1,'2010-02-23','8436016095327','Faba de Granja I. - Segunda - Saco 25kg.','Faba de Granja I. - Segunda - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(26,4,NULL,NULL,1,NULL,1,'2010-02-23','8436016095334','Faba de Granja I. - Segunda - Saco 50kg.','Faba de Granja I. - Segunda - Saco 50kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(27,6,NULL,NULL,1,NULL,1,'2010-02-23','8436016095396','Faba de Granja I. - Destrio - Saco 25kg.','Faba de Granja I. - Destrio - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(28,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090131','Saquete \"Orbayu\" Faba de Granja I. - Extra A Extra B -  500 gr. ','Saquete \"Orbayu\" Faba de Granja I. - Extra A Extra B -  500 gr. ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(29,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090148','Saquete \"Orbayu\" \" Faba de Granja I. - Extra A Extra B - Saquete tela 700gr. ','Saquete \"Orbayu\" Faba de Granja I. - Extra A Extra B -  700gr.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(30,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090124','Saquete \"Orbayu\" Faba de Granja I. - Extra A Extra B - 1 Kg. ','Saquete \"Orbayu\" Faba de Granja I. - Extra A Extra B - 1 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:25:44','S',0.00),(31,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095006','Faba de Granja I. - Extra A Extra B - Saco 5kg. ','Faba de Granja I. - Extra A Extra B - Saco 5kg. ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(33,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090506','Bolsa Plástico Faba de Granja I. - Extra A Extra B - 1 Kg. ','Bolsa Plástico con Etiqueta- Faba de Granja I. - Extra A Extra B - 1 Kg. ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(34,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090605','Paquete Vacio \"Orbayu\" Faba de Granja I. - Extra A Extra B - Envase  200gr ','Paquete Vacio \"Orbayu\" Faba de Granja I. - Extra A Extra B - Envase  200gr ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(35,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090612','Paquete Vacio \"Orbayu\" Faba de Granja I. - Extra A Extra B - Envase  400 g.','Paquete Vacio \"Orbayu\" Faba de Granja I. - Extra A Extra B - Envase  400 g.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(36,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090209','Faba de Granja I.- Extra A Extra B - Saquete tela 500gr. La Aldea','Faba de Granja I. - Extra A Extra B - Saquete tela 500gr. La Aldea',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(37,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090216','Faba de Granja I. - Extra A Extra B - Saquete tela 1kg. La Aldea','Faba de Granja I. - Extra A Extra B - Saquete tela 1kg. La Aldea',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(38,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095402','Faba Granja Nacional - Extra A - Saco 10kg.','Faba Granja Nacional - Extra A - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(39,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095419','Faba Granja Nacional - Extra A - Saco 15kg.','Faba Granja Nacional - Extra A - Saco 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(40,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095426','Faba Granja Nacional - Extra A - Saco 25kg.','Faba Granja Nacional - Extra A - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(41,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095433','Faba Granja Nacional - Extra A - Saco 50kg.','Faba Granja Nacional - Extra A - Saco 50kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(42,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095518','Faba Granja Nacional - Primera - Saco 10kg.','Faba Granja Nacional(Granjilla) - Primera - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(43,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095525','Faba Granja Nacional - Primera - Saco 15kg.','Faba Granja Nacional(Granjilla) - Primera - Saco 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(44,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095532','Faba Granja Nacional - Primera - Saco 25kg.','Faba Granja Nacional(Granjilla) - Primera - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(45,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095549','Faba Granja Nacional - Primera - Saco 50kg.','Faba Granja Nacional -(Granjilla) - Saco 50kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(46,4,NULL,NULL,1,NULL,1,'2010-02-23','8436016095600','Faba Granja Nacional - Segunda - Saco 10kg.','Faba Granja Nacional - (Manchada)Segunda - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(47,4,NULL,NULL,1,NULL,1,'2010-02-23','8436016095617','Faba Granja Nacional - Segunda - Saco 15kg.','Faba Granja Nacional - (Manchada)Segunda - Saco 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(48,4,NULL,NULL,1,NULL,1,'2010-02-23','8436016095624','Faba Granja Nacional - Segunda - Saco 25kg.','Faba Granja Nacional - (Manchada)Segunda - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(49,4,NULL,NULL,1,NULL,1,'2010-02-23','8436016095631','Faba Granja Nacional - Segunda - Saco 50kg.','Faba Granja Nacional - (Manchada)Segunda - Saco 50kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(50,6,NULL,NULL,1,NULL,1,'2010-02-23','8436016095693','Faba Granja Nacional - Destrio - Saco 25kg.','Faba Granja Nacional - Destrio - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(56,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095716','Faba Verdina Asturiana - Extra - Saco 10kg.','Faba Verdina Asturiana - Extra - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(57,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095723','Faba Verdina Asturiana - Extra - Saco 15kg.','Faba Verdina Asturiana - Extra - Saco 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(58,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095730','Faba Verdina Asturiana - Extra - Saco 25kg.','Faba Verdina Asturiana - Extra - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(59,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016095747','Faba Verdina Asturiana - Extra - Saco 50kg.','Faba Verdina Asturiana - Extra - Saco 50kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(60,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095815','Faba Verdina Asturiana - Primera - Saco 10kg.','Faba Verdina Asturiana -(Blanqueada-Siembra) - Saco 10kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(61,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095808','Faba Verdina Asturiana - Primera - Saco 5kg.','Faba VerdinaAsturiana(Blanqueada-Siembra) - Saco 5kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(62,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095822','Faba Verdina Asturiana - Primera - Saco 25kg.','Faba Verdina asturiana (Blanqueada-Siembra) - Saco 25kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(63,3,NULL,NULL,1,NULL,1,'2010-02-23','8436016095709','Faba Verdina Asturiana- Extra - Saco 5 kg.','Faba Verdina Asturiana Extra - Saco 5 kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(64,1,NULL,NULL,1,NULL,1,'2011-04-04','8436016097017','Faba Fresca - Extra - Bolsa plástica 2.5kg.','Faba Fresca - Extra - Bolsa plástica 2.5kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:33','S',0.00),(65,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016097024','Faba Fresca - Extra - Bolsa plástica 0.5kg. (Muestra)','Faba Fresca - Extra - Bolsa plástica 0.5kg. (Muestra)',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(66,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016097000','Faba Fresca - Extra - . Bolsa Plástica 1 kg','Faba Fresca - Extra - . Bolsa Plástica 1 kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(67,1,NULL,NULL,1,NULL,1,'2010-02-23','1','Agrupación Faba Fresca - Extra - Caja 10 kg. 2.5 x 4 uds.','Faba Fresca - Extra - \"Tierrina Vaqueira\" Caja Cartón 10 kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:12:44','S',0.00),(68,1,NULL,NULL,1,NULL,0,'2011-03-23','8436016097505','Faba Fresca en Vaina - Extra - Caja  plástica plegable 15kg.','Faba Fresca en Vaina - Extra - Caja  plástica plegable 15kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(2,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090018','Faba Asturiana DO - Tierrina Vaqueira - 1Kg - Saquete Tela','Faba Asturiana DO - Extra - Saquete de tela 1kg. Tierrina Vaqueira',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:13:50','S',0.00),(3,1,NULL,NULL,1,NULL,1,'2010-02-23','8436027260028','Faba Ast. DO - TIERRA ASTUR - 1Kg  - Saquete Tela','Faba Asturiana DO - Extra - Saquete de tela 1kg. \"Tierra Astur\"',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10685.88,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(72,1,NULL,NULL,1,NULL,1,'2011-03-29','18436016090084','Agrupación NÂº 1 - Faba D.O. \"TIERRINA VAQUEIRA\"  1 Kg.','Caja Expositor  Faba Asturiana D.O. 12 Uds. x 1 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(73,1,NULL,NULL,1,NULL,1,'2011-03-29','28436016090098','Agrupación  Faba D.O. \"Tierrina Vaqueira\" 24 uds x 500 g. ','Caja Expositor  Faba Asturiana D.O. 24 Uds. x 500 g.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(32,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090513','Bolsa Plástico con etiqueta Faba de Granja I.- Extra A Extra B -  500gr. ','Bolsa Plástico con etiqueta Faba de Granja I.- Extra A Extra B -  500gr.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(9,1,NULL,NULL,1,NULL,1,'2010-02-23','8436016090803','Paquete vacio Faba Asturiana DO - Extra - Envase  200 gr.','Paquete vacío \"Tierrina Vaqueira\" Faba Asturiana DO - Extra - Envase 200 gr.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:01','S',0.00),(74,0,1,NULL,0,NULL,0,'2011-04-04','8436016098007','Paquete Compango para Fabada Asturiana(1-1-1) Al vacio','Paquete Compango para Fabada Asturiana(1-1-1) Al vacio',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(75,1,1,NULL,3,NULL,1,'2011-04-04','8436016096003','Saco 10 Kg. Faba Roxa Nacional','Saco 10 Kg. Faba Roxa Nacional',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(76,1,NULL,NULL,3,NULL,1,'2011-04-04','8436016096010','Saco Faba Amarilla(Roxa) Nacional 15 Kg ','Saco Faba Amarilla(Roxa) Nacional 15 Kg ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:33','S',0.00),(77,1,1,NULL,3,NULL,1,'2011-04-04','8436016096027','Saco Faba Amarilla(Roxa) Nacional 25 Kg ','Saco Faba Amarilla(Roxa) Nacional 25 Kg ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(78,1,NULL,NULL,3,NULL,1,'2011-04-04','8436016096058','Saco Alubia Amarilla redonda León 10 Kg ','Saco Alubia Amarilla redonda León 10 Kg ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:33','S',0.00),(79,1,NULL,NULL,3,NULL,1,'2011-04-04','8436016096065','Saco Alubia Amarilla redonda León 15 Kg ','Saco Alubia Amarilla redonda León 15 Kg ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:33','S',0.00),(80,1,NULL,NULL,3,NULL,1,'2011-04-04','8436016096102','Saco Alubia Pinta redonda 15 Kg','Saco Alubia Pinta redonda 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:33','S',0.00),(81,1,NULL,NULL,3,NULL,1,'2011-04-04','8436016096157','Saco Alubia  Pinta larga 15 Kg','Saco Alubia  Pinta larga 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:33','S',0.00),(82,1,1,NULL,3,NULL,1,'2011-04-04','8436016096201','Saco Alubia Canela 15 Kg.','Saco Alubia Canela 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(83,1,1,NULL,3,NULL,1,'2011-04-04','8436016096256','Saco Alubia Morada redonda  15 Kg ','Saco Alubia Morada redonda  15 Kg ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(84,1,1,NULL,3,NULL,1,'2011-04-04','8436016096300','Saco Alubia Morada larga  15 Kg ','Saco Alubia Morada larga  15 Kg ',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(85,1,1,NULL,3,NULL,1,'2011-04-04','8436016096355','Saco Alubia Caparrón Larga  15 Kg.','Saco Alubia Caparrón Larga  15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(86,1,1,NULL,3,NULL,1,'2011-04-04','8436016096409','Saco Alubia Mandilín -(Caparron redonda)  15 Kg.','Saco Alubia Mandilín -(Caparron redonda)  15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(87,1,1,NULL,3,NULL,1,'2011-04-04','8436016096454','Saco Alubia Palmeña redonda 15 Kg.','Saco Alubia Palmeña redonda 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(88,1,1,NULL,3,NULL,1,'2011-04-04','8436016096508','Saco Alubia Palmeña larga 15 Kg.','Saco Alubia Palmeña larga 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(89,1,1,NULL,3,NULL,1,'2011-04-04','8436016096553','Saco Frijol Negrito 15 Kg.','Saco Frijol Negrito 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(90,1,NULL,NULL,3,NULL,1,'2011-04-04','8436016096607','Saco Alubia Negra Tolosana 15 Kg.','Saco Alubia Negra Tolosana 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-04-08 12:09:33','S',0.00),(91,1,1,NULL,3,NULL,1,'2011-04-04','8436016096652','Saco Soja Mungo 15 Kg','Saco Soja Mungo 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(92,1,1,NULL,4,NULL,1,'2011-04-04','8436016096751','Saco Alubia Riñón (canellini) 15 Kg.','Saco Alubia Riñón (canellini) 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(93,1,1,NULL,4,NULL,1,'2011-04-04','8436016096850','Saco Judión Grande 15 Kg.','Saco Judión Grande 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(94,1,1,NULL,4,NULL,1,'2011-04-04','8436016096881','Saco Judión Mediano 15 Kg.','Saco Judión Mediano 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(95,1,1,NULL,4,NULL,1,'2011-04-04','8436016096904','Saco Alubia Planchada 15 Kg','Saco Alubia Planchada 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(96,1,1,NULL,4,NULL,1,'2011-04-04','8436016096935','Saco Alubia Plancheta 15 Kg.','Saco Alubia Plancheta 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(97,1,1,NULL,4,NULL,1,'2011-04-04','8436016096966','Saco Alubia redonda Blanca 15 Kg.','Saco Alubia redonda Blanca 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(98,1,1,NULL,5,NULL,1,'2011-04-04','8436016094009','Saco Lenteja Pardina 15 Kg.','Saco Lenteja Pardina 15 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(99,1,1,NULL,5,NULL,1,'2011-04-04','8436016094054','Saco Lenteja Castellana 15 Kg','Saco Lenteja Castellana 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(100,0,1,NULL,0,NULL,0,'2011-04-04','8436016094108','Saco Garbanzo Pedrosillano Nacional 15 Kg','Saco Garbanzo Pedrosillano Nacional 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(101,1,1,NULL,5,NULL,1,'2011-04-04','8436016094153','Saco Garbanzo Castellano Gordo 15 Kg','Saco Garbanzo Castellano Gordo 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(102,1,1,NULL,5,NULL,1,'2011-04-04','8436016094207','Saco Garbanzo Castellano Mediano 15 Kg','Saco Garbanzo Castellano Mediano 15 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(103,1,NULL,NULL,6,NULL,5,'2011-04-04','8436016098106','Bolsa Harina Maíz 1 Kg.','Bolsa Harina Maíz 1 Kg',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,NULL,3,'nohay.jpg','2011-05-19 15:43:08','S',1.00),(104,1,1,NULL,6,NULL,5,'2011-04-04','8436016098144','Bolsa Harina Maíz  \"Torrada\"  1 Kg.','Bolsa Harina Maíz \"Torrada\"  1 Kg.',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00),(105,1,1,NULL,5,NULL,1,'2011-04-04','8436016094702','Saco Arroz Redondo 15 Kg.','',0.00,0.00,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'nohay.jpg','2011-07-15 06:13:01','S',0.00);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto_compuesto (
  idProducto bigint(20) NOT NULL,
  idCompuestoDe bigint(20) NOT NULL DEFAULT '0',
  cantidad double NOT NULL DEFAULT '0',
  PRIMARY KEY (idProducto,idCompuestoDe)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO producto_compuesto (idProducto, idCompuestoDe, cantidad) VALUES (71,5,10),(72,1,12),(73,5,24);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto_envase (
  idProducto bigint(20) NOT NULL DEFAULT '0',
  idEnvase bigint(20) NOT NULL DEFAULT '0',
  cantidad double DEFAULT NULL,
  PRIMARY KEY (idProducto,idEnvase)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto_materiaprima (
  idProducto bigint(20) NOT NULL DEFAULT '0',
  idMateriaPrima bigint(20) NOT NULL DEFAULT '0',
  cantidad double DEFAULT NULL,
  PRIMARY KEY (idProducto,idMateriaPrima)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto_merma (
  idMerma bigint(20) NOT NULL DEFAULT '0',
  idProducto bigint(20) NOT NULL DEFAULT '0',
  idUbicacion bigint(20) NOT NULL DEFAULT '0',
  fecha date DEFAULT NULL,
  cantidad double(15,2) DEFAULT NULL,
  responsable varchar(100) DEFAULT NULL,
  motivo text,
  PRIMARY KEY (idMerma)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto_registrosalida (
  PRODUCTO_idProducto bigint(20) NOT NULL DEFAULT '0',
  registroSalidas_idRegistroSalida bigint(20) NOT NULL DEFAULT '0',
  KEY FK171588BA8193FD3A (PRODUCTO_idProducto),
  KEY FK171588BA4CAE9E99 (registroSalidas_idRegistroSalida)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto_ubicacion (
  PRODUCTO_idProducto bigint(20) NOT NULL DEFAULT '0',
  ubicaciones_idUbicacion bigint(20) NOT NULL DEFAULT '0',
  KEY FKD7CE10604F841CD1 (ubicaciones_idUbicacion),
  KEY FKD7CE10608193FD3A (PRODUCTO_idProducto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE provincia (
  idProvincia bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idProvincia)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO provincia (idProvincia, nombre) VALUES (1,'Alava'),(2,'Albacete'),(3,'Alicante'),(4,'Almería'),(5,'Asturias'),(6,'Ávila'),(7,'Bajadoz'),(8,'Barcelona'),(9,'Burgos'),(10,'Cáceres'),(11,'Cádiz'),(12,'Cantabria'),(13,'Ciudad Real'),(14,'Córdoba'),(15,'Cuenca'),(16,'Gerona'),(17,'Granada'),(18,'Guadalajara'),(19,'Guipúzcoa'),(20,'Huelva'),(21,'Huesca'),(22,'Islas Baleares'),(23,'Jaén'),(24,'La Coruña'),(25,'La Rioja'),(26,'Las Palmas'),(27,'León'),(28,'Lugo'),(29,'Madrid'),(30,'Málaga'),(31,'Murcia'),(32,'Navarra'),(33,'Orense'),(34,'Palencia'),(35,'Pontevedra'),(36,'Salamanca'),(37,'Santa Cruz de Tenerife'),(38,'Segovia'),(39,'Sevilla'),(40,'Soria'),(41,'Tarragona'),(42,'Teruel'),(43,'Toledo'),(44,'Valencia'),(45,'Valladolid'),(46,'Vizcaya'),(47,'Zamora'),(48,'Zaragoza'),(49,'Castellón'),(50,'Lérida'),(0,' No aplica');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE referenciabancaria (
  idDatoBancario bigint(20) NOT NULL AUTO_INCREMENT,
  idBanco bigint(20) NOT NULL DEFAULT '0',
  idFormaPago bigint(20) NOT NULL DEFAULT '0',
  empresa_idUsuario bigint(20) NOT NULL DEFAULT '0',
  numCuenta varchar(50) NOT NULL DEFAULT '0',
  limiteCredito varchar(100) DEFAULT NULL,
  fechaVencimiento date DEFAULT NULL,
  fechaPago date DEFAULT NULL,
  oficina varchar(255) DEFAULT NULL,
  PRIMARY KEY (idDatoBancario),
  KEY FK3C0C89B918B1F06 (empresa_idUsuario),
  KEY idBanco (idBanco),
  KEY idFormaPago (idFormaPago)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO referenciabancaria (idDatoBancario, idBanco, idFormaPago, empresa_idUsuario, numCuenta, limiteCredito, fechaVencimiento, fechaPago, oficina) VALUES (1,4,0,30,'','','2011-01-01','2011-01-01',NULL),(2,4,0,32,'','','2011-01-01','2011-01-01',NULL);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registro_ubicacion (
  idRegistroUbicacion bigint(20) unsigned DEFAULT NULL,
  idRegistro bigint(20) unsigned DEFAULT NULL,
  idUbicacion bigint(20) unsigned DEFAULT NULL,
  responsable varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroentrada (
  idEntrada bigint(20) NOT NULL AUTO_INCREMENT,
  codigoOrden varchar(20) DEFAULT NULL,
  idEntradaPadre bigint(20) DEFAULT NULL,
  idProducto bigint(20) DEFAULT NULL,
  idCategoria bigint(20) DEFAULT NULL,
  idFormatoEntrega bigint(20) DEFAULT NULL,
  idCosecha bigint(20) DEFAULT NULL,
  idTipoRegistro char(1) DEFAULT NULL,
  idCategoriaEntrada bigint(20) DEFAULT NULL,
  idEnvase bigint(20) DEFAULT NULL,
  codigoEntrada varchar(25) DEFAULT NULL,
  fecha datetime DEFAULT NULL,
  fechaCaducidad date DEFAULT NULL,
  fechaLlegada date DEFAULT NULL,
  lote varchar(10) DEFAULT NULL,
  habilitado char(1) NOT NULL DEFAULT 'S',
  cantidad double(10,2) DEFAULT NULL,
  saldo double(10,2) DEFAULT NULL,
  notasincidencias longtext,
  numeroPallets bigint(20) DEFAULT NULL,
  numeroBultos bigint(20) DEFAULT NULL,
  temperatura double(10,2) DEFAULT NULL,
  humedad double(10,2) DEFAULT NULL,
  costoUnitario double(10,2) DEFAULT NULL,
  costoTotal double(10,2) DEFAULT NULL,
  idTipoUbicacion bigint(20) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  PRIMARY KEY (idEntrada),
  KEY idProducto (idProducto),
  KEY idCategoria (idCategoria),
  KEY idFormatoEntrega (idFormatoEntrega)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroentrada_bulto (
  codigoEntrada varchar(20) NOT NULL,
  numBulto bigint(20) NOT NULL DEFAULT '0',
  peso double(10,2) NOT NULL DEFAULT '0.00',
  numBultosPalet bigint(20) NOT NULL DEFAULT '0',
  pBruto double(10,2) NOT NULL DEFAULT '0.00',
  pNeto double(10,2) NOT NULL DEFAULT '0.00',
  responsable varchar(50) DEFAULT '',
  PRIMARY KEY (codigoEntrada,numBulto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroentrada_estado (
  idEntrada bigint(20) NOT NULL DEFAULT '0',
  idEstado bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (idEntrada,idEstado)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroentrada_estado_tmp (
  idEntrada bigint(20) NOT NULL DEFAULT '0',
  idEstado bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (idEntrada,idEstado)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroentrada_incidencia (
  idEntrada bigint(20) NOT NULL DEFAULT '0',
  idIncidencia bigint(20) NOT NULL DEFAULT '0',
  estado set('S','N') DEFAULT 'N',
  PRIMARY KEY (idEntrada,idIncidencia),
  KEY idIncidencia (idIncidencia)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroentrada_incidencia_tmp (
  idEntrada bigint(20) NOT NULL DEFAULT '0',
  idIncidencia bigint(20) NOT NULL DEFAULT '0',
  estado set('S','N') DEFAULT 'N',
  PRIMARY KEY (idEntrada,idIncidencia),
  KEY idIncidencia (idIncidencia)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroentrada_subregistro (
  idRegistroEntrada bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroentrada_tmp (
  idEntrada bigint(20) NOT NULL AUTO_INCREMENT,
  codigoOrden varchar(20) DEFAULT NULL,
  idEntradaPadre bigint(20) DEFAULT NULL,
  idProducto bigint(20) DEFAULT NULL,
  idCategoria bigint(20) DEFAULT NULL,
  idFormatoEntrega bigint(20) DEFAULT NULL,
  idCosecha bigint(20) DEFAULT NULL,
  idTipoRegistro char(1) DEFAULT NULL,
  idCategoriaEntrada bigint(20) DEFAULT NULL,
  idEnvase bigint(20) DEFAULT NULL,
  codigoEntrada varchar(25) DEFAULT NULL,
  fecha datetime DEFAULT NULL,
  fechaCaducidad date DEFAULT NULL,
  fechaLlegada date DEFAULT NULL,
  lote varchar(10) DEFAULT NULL,
  habilitado char(1) NOT NULL DEFAULT 'S',
  cantidad double(10,2) DEFAULT NULL,
  saldo double(10,2) DEFAULT NULL,
  notasincidencias longtext,
  numeroPallets bigint(20) DEFAULT NULL,
  numeroBultos bigint(20) DEFAULT NULL,
  temperatura float(10,2) DEFAULT NULL,
  humedad double(10,2) DEFAULT NULL,
  costoUnitario double(10,2) DEFAULT NULL,
  costoTotal double(10,2) DEFAULT NULL,
  idTipoUbicacion bigint(20) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  PRIMARY KEY (idEntrada),
  KEY idProducto (idProducto),
  KEY idCategoria (idCategoria),
  KEY idFormatoEntrega (idFormatoEntrega)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registrosalida (
  idRegistroSalida bigint(20) NOT NULL DEFAULT '0',
  idAlbaran bigint(20) DEFAULT NULL,
  idCliente_idUsuario bigint(20) DEFAULT NULL,
  idCliente bigint(20) DEFAULT NULL,
  eanProducto varchar(14) NOT NULL,
  idVehiculo bigint(20) DEFAULT NULL,
  idComercial bigint(20) DEFAULT NULL,
  codigoSalida varchar(20) DEFAULT NULL,
  codigoEntrada varchar(20) DEFAULT NULL,
  fecha date DEFAULT NULL,
  fechaCaducidad date DEFAULT NULL,
  pesoNeto double(10,2) DEFAULT NULL,
  pesoBruto double(10,2) DEFAULT NULL,
  numeroBultos bigint(20) DEFAULT NULL,
  cantidad double(10,2) DEFAULT NULL,
  cantidadUnitaria double(10,2) DEFAULT NULL,
  precioUnitario double(10,2) DEFAULT NULL,
  habilitado char(1) DEFAULT 'S',
  destino varchar(50) DEFAULT NULL,
  albaran bigint(20) DEFAULT NULL,
  notasvehiculo longtext,
  notasincidencias longtext,
  notasinstrucciones longtext,
  usuarioResponsable varchar(20) DEFAULT NULL,
  PRIMARY KEY (idRegistroSalida),
  KEY FK3EA1C7FB320777C (idCliente_idUsuario),
  KEY idProducto (eanProducto),
  KEY idVehiculo (idVehiculo)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registrosalida_producto (
  REGISTROSALIDA_idRegistroSalida bigint(20) NOT NULL DEFAULT '0',
  productoBeans_idProducto bigint(20) NOT NULL DEFAULT '0',
  KEY FK39A6F524143C40D6 (REGISTROSALIDA_idRegistroSalida),
  KEY FK39A6F52463A6D157 (productoBeans_idProducto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroubicacion (
  idRegistroUbicacion bigint(20) NOT NULL DEFAULT '0',
  idUbicacion int(8) unsigned DEFAULT NULL,
  idRegistro bigint(20) unsigned DEFAULT NULL,
  codigoEntrada varchar(100) DEFAULT NULL,
  responsable varchar(100) DEFAULT NULL,
  PRIMARY KEY (idRegistroUbicacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registroubicacion_tmp (
  idRegistroUbicacion bigint(20) NOT NULL DEFAULT '0',
  idUbicacion int(8) unsigned DEFAULT NULL,
  idRegistro bigint(20) unsigned DEFAULT NULL,
  codigoEntrada varchar(100) DEFAULT NULL,
  responsable varchar(100) DEFAULT NULL,
  PRIMARY KEY (idRegistroUbicacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE requisito_homologacion (
  idRequisito bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (idRequisito)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO requisito_homologacion (idRequisito, nombre) VALUES (1,'Fotocopia de Registro Sanitario'),(2,'Carta de compromiso de transporte en condiciones controladas: modelo de registro R03-P02'),(3,'Certificación de poseer y aplicar APPCC y/o fotocopia de otros certificados de Calidad (ISO 9000, 14000, etc.)'),(4,'Inscripción en el Registro del Consejo Regulador'),(0,'No aplica');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE rol (
  idRol bigint(20) NOT NULL AUTO_INCREMENT,
  nombre varchar(255) DEFAULT NULL,
  descripcion varchar(255) DEFAULT NULL,
  usuario_idUsuario bigint(20) DEFAULT NULL,
  PRIMARY KEY (idRol),
  KEY FK13DAF42275F90 (usuario_idUsuario)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO rol (idRol, nombre, descripcion, usuario_idUsuario) VALUES (1,'cliente','administrador del sistema',NULL),(2,'proveedor','proveedor del sistema',NULL),(3,'administrador','cliente del sistema',NULL),(4,'usuario','usuario del sistema',NULL);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE rolacceso (
  idRol bigint(20) unsigned DEFAULT NULL,
  accion varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ruta (
  idRuta bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(50) DEFAULT NULL,
  PRIMARY KEY (idRuta)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO ruta (idRuta, nombre) VALUES (0,'prueba ruta');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE rutavehiculo (
  idOrden bigint(20) NOT NULL DEFAULT '0',
  idTransportista bigint(20) DEFAULT NULL,
  idVehiculo bigint(20) DEFAULT NULL,
  idEstado bigint(20) DEFAULT NULL,
  codigoOrden tinyint(3) DEFAULT NULL,
  codigoSalida varchar(20) DEFAULT NULL,
  fecha tinyint(3) DEFAULT NULL,
  horainicio tinyint(3) DEFAULT NULL,
  horafin varchar(50) DEFAULT NULL,
  kminicio double(10,2) DEFAULT NULL,
  kmfinal double(10,2) DEFAULT NULL,
  salida varchar(150) DEFAULT NULL,
  llegada varchar(20) DEFAULT NULL,
  notasIniciales text,
  notasFinales text,
  usuarioReponsable varchar(20) DEFAULT NULL,
  PRIMARY KEY (idOrden)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE sector (
  idSector bigint(20) NOT NULL AUTO_INCREMENT,
  nombre varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idSector)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO sector (idSector, nombre) VALUES (1,'HORECA'),(2,'Tienda tradicional'),(3,'Cadena de supermercados'),(4,'Distribuidores'),(6,'No aplica');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE secuencial_lote (
  idSecuencial bigint(20) NOT NULL AUTO_INCREMENT,
  valorSecuencial bigint(20) DEFAULT NULL,
  PRIMARY KEY (idSecuencial)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE telefono (
  idTelefono bigint(20) NOT NULL AUTO_INCREMENT,
  numeroTelefono varchar(255) DEFAULT NULL,
  empresa_idUsuario bigint(20) DEFAULT NULL,
  PRIMARY KEY (idTelefono),
  KEY FKDD8E5AF4918B1F06 (empresa_idUsuario)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipo_cargo (
  idCargo tinyint(4) NOT NULL DEFAULT '0',
  nombreCargo varchar(100) DEFAULT NULL,
  PRIMARY KEY (idCargo)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO tipo_cargo (idCargo, nombreCargo) VALUES (1,'Transporte'),(2,'Bancario'),(3,'Devolución');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipo_producto (
  idTipoProducto bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (idTipoProducto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipo_proveedor (
  idTipo bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idTipo)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO tipo_proveedor (idTipo, nombre) VALUES (1,'Materia prima'),(2,'Transporte'),(3,'Insumos'),(4,'Envases'),(5,'Servicios');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipomantenimiento (
  idTipoMant tinyint(3) DEFAULT NULL,
  nombre varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO tipomantenimiento (idTipoMant, nombre) VALUES (1,'Calibracion'),(2,'General');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipoubicacion (
  IdTipoUbicacion bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(100) DEFAULT NULL,
  PRIMARY KEY (IdTipoUbicacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO tipoubicacion (IdTipoUbicacion, nombre) VALUES (1,'Barcia'),(2,'Nave Antigua'),(3,'Operador Logístico'),(4,'Autoventa'),(0,'No aplica');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipovehiculo (
  idTipoVehiculo bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(100) DEFAULT NULL,
  PRIMARY KEY (idTipoVehiculo)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO tipovehiculo (idTipoVehiculo, descripcion) VALUES (1,'Transporte'),(2,'De agencia externa'),(3,'Directo y medios propios'),(4,'Auto venta'),(0,'No aplica');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tmp_gp (
  idGestionProduccion bigint(20) NOT NULL DEFAULT '0',
  idProducto bigint(20) DEFAULT NULL,
  proceso varchar(50) DEFAULT NULL,
  orden varchar(20) DEFAULT NULL,
  habilitado set('S','N') DEFAULT 'S',
  horainicio varchar(50) DEFAULT NULL,
  horafin varchar(50) DEFAULT NULL,
  cantidadprod double(10,2) DEFAULT NULL,
  cantidadIngredientesini bigint(20) DEFAULT NULL,
  cantidadIngredientesfin bigint(20) DEFAULT NULL,
  mermasIngredientes double(8,2) DEFAULT NULL,
  loteingredientes varchar(20) DEFAULT NULL,
  cantidadEnvasesini bigint(20) DEFAULT NULL,
  cantidadEnvasesfin bigint(20) DEFAULT NULL,
  mermasEnvases bigint(20) DEFAULT NULL,
  loteenvases varchar(20) DEFAULT NULL,
  usuarioResponsable varchar(20) DEFAULT NULL,
  notasincidencias varchar(200) DEFAULT NULL,
  notasinstrucciones varchar(200) DEFAULT NULL,
  observaciones varchar(200) DEFAULT NULL,
  estadoproceso char(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tmp_gp_envasado_detalle (
  idTmpIngredientes int(10) NOT NULL DEFAULT '0',
  idTipoRegistro set('E','M') DEFAULT NULL,
  orden varchar(20) DEFAULT NULL,
  codigoEntrada varchar(20) DEFAULT NULL,
  idProducto int(20) DEFAULT NULL,
  cantidadDisponible decimal(10,2) DEFAULT NULL,
  cantidadUsable decimal(10,2) DEFAULT NULL,
  mermaProducto decimal(10,2) DEFAULT NULL,
  descripcion varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tmp_ingredientes_desgranado (
  idTmpIngredientes bigint(20) NOT NULL AUTO_INCREMENT,
  codigoEntradaIngrediente varchar(20) DEFAULT NULL,
  PRIMARY KEY (idTmpIngredientes)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tmp_ingredientes_envases (
  idTmpIngredientes bigint(20) NOT NULL AUTO_INCREMENT,
  idTipoRegistro set('E','M') DEFAULT NULL,
  orden varchar(20) DEFAULT NULL,
  codigoEntrada varchar(20) DEFAULT NULL,
  idProducto bigint(20) DEFAULT NULL,
  cantidadDisponible bigint(20) DEFAULT NULL,
  cantidadUsable bigint(20) DEFAULT NULL,
  mermaProducto bigint(20) DEFAULT NULL,
  descripcionEnvase varchar(100) DEFAULT NULL,
  descripcionMateria varchar(100) DEFAULT NULL,
  PRIMARY KEY (idTmpIngredientes)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE transporte (
  idTransporte int(11) NOT NULL AUTO_INCREMENT,
  idTransportista int(11) NOT NULL DEFAULT '0',
  matricula varchar(255) DEFAULT NULL,
  temperatura int(11) NOT NULL DEFAULT '0',
  transportista_idUsuario bigint(20) DEFAULT NULL,
  PRIMARY KEY (idTransporte),
  KEY FKB685DA5CE067397D (transportista_idUsuario)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion (
  idUbicacion bigint(20) NOT NULL AUTO_INCREMENT,
  idHueco bigint(20) NOT NULL,
  registro varchar(50) NOT NULL,
  cantidad double(8,2) DEFAULT NULL,
  idPalet bigint(20) NOT NULL,
  orden varchar(20) DEFAULT NULL,
  congelado char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (idUbicacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_almacen (
  idAlmacen bigint(20) NOT NULL AUTO_INCREMENT,
  idDireccion bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) NOT NULL DEFAULT '',
  urlPlano varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idAlmacen)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO ubicacion_almacen (idAlmacen, idDireccion, descripcion, urlPlano) VALUES (1,1,'Nave Barcia','/img/planos/naveBarcia.png'),(2,0,'Vehiculo I',''),(3,0,'Vehiculo II',''),(4,0,'Vehiculo III','');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_estanteria (
  idEstanteria bigint(20) NOT NULL AUTO_INCREMENT,
  idLinea bigint(20) NOT NULL DEFAULT '0',
  idEstanteriaLinea bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) NOT NULL DEFAULT '',
  urlPlano varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idEstanteria)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO ubicacion_estanteria (idEstanteria, idLinea, idEstanteriaLinea, descripcion, urlPlano) VALUES (1,1,1,'A1:Z1:L1:E1',''),(2,1,2,'A1:Z1:L1:E2',''),(3,1,3,'A1:Z1:L1:E3',''),(4,2,1,'A1:Z1:L2:E1',''),(5,2,2,'A1:Z1:L2:E2',''),(6,2,3,'A1:Z1:L2:E3',''),(7,3,1,'A1:Z2:L1:E1',''),(8,3,2,'A1:Z2:L1:E2',''),(9,3,3,'A1:Z2:L1:E3',''),(10,4,1,'A1:Z2:L2:E1',''),(11,4,2,'A1:Z2:L2:E2',''),(12,4,3,'A1:Z2:L2:E3',''),(13,5,1,'A1:Z3:L1:E1',''),(14,5,2,'A1:Z3:L1:E2',''),(15,6,1,'A1:Z4:L1:E1',''),(16,6,2,'A1:Z4:L1:E2',''),(17,6,3,'A1:Z4:L1:E3',''),(18,6,4,'A1:Z4:L1:E4',''),(19,7,1,'A1:Z5:L1:E1',''),(20,7,2,'A1:Z5:L1:E2',''),(21,7,3,'A1:Z5:L1:E3',''),(22,7,4,'A1:Z5:L1:E4',''),(24,9,1,'Vehículo II',''),(23,8,1,'Vehículo I',''),(25,10,1,'Vehículo III',''),(26,11,1,'Zona de envasado','');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_hueco (
  idHueco bigint(20) NOT NULL AUTO_INCREMENT,
  idPiso bigint(20) NOT NULL,
  idHuecoPiso bigint(20) NOT NULL,
  descripcion varchar(50) NOT NULL DEFAULT '',
  numeroRegistros int(11) NOT NULL DEFAULT '0',
  x int(10) DEFAULT '0',
  y int(10) DEFAULT '0',
  z int(10) DEFAULT '0',
  bigBag char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (idHueco)
) ENGINE=MyISAM AUTO_INCREMENT=215 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO ubicacion_hueco (idHueco, idPiso, idHuecoPiso, descripcion, numeroRegistros, x, y, z, bigBag) VALUES (1,1,1,'A1:Z1:L1:E1:P1:H1',0,0,0,0,'N'),(2,2,1,'A1:Z1:L1:E1:P2:H1',0,0,0,0,'N'),(3,3,1,'A1:Z1:L1:E1:P3:H1',0,0,0,0,'N'),(5,4,2,'A1:Z1:L1:E2:P1:H2',0,0,0,0,'N'),(6,4,3,'A1:Z1:L1:E2:P1:H3',0,0,0,0,'N'),(8,5,1,'A1:Z1:L1:E2:P2:H1',0,0,0,0,'N'),(9,5,2,'A1:Z1:L1:E2:P2:H2',0,0,0,0,'N'),(10,5,3,'A1:Z1:L1:E2:P2:H3',0,0,0,0,'N'),(12,6,1,'A1:Z1:L1:E2:P3:H1',0,0,0,0,'N'),(13,6,2,'A1:Z1:L1:E2:P3:H2',0,0,0,0,'N'),(14,6,3,'A1:Z1:L1:E2:P3:H3',0,0,0,0,'N'),(4,4,1,'A1:Z1:L1:E2:P1:H1',0,0,0,0,'N'),(16,7,1,'A1:Z1:L1:E3:P1:H1',0,0,0,0,'N'),(17,7,2,'A1:Z1:L1:E3:P1:H2',0,0,0,0,'N'),(18,7,3,'A1:Z1:L1:E3:P1:H3',0,0,0,0,'N'),(20,8,1,'A1:Z1:L1:E3:P2:H1',0,0,0,0,'N'),(21,8,2,'A1:Z1:L1:E3:P2:H2',0,0,0,0,'N'),(22,8,3,'A1:Z1:L1:E3:P2:H3',0,0,0,0,'N'),(24,9,1,'A1:Z1:L1:E3:P3:H1',0,0,0,0,'N'),(25,9,2,'A1:Z1:L1:E3:P3:H2',0,0,0,0,'N'),(26,9,3,'A1:Z1:L1:E3:P3:H3',0,0,0,0,'N'),(7,4,4,'A1:Z1:L1:E2:P1:H4',0,0,0,0,'N'),(11,5,4,'A1:Z1:L1:E2:P2:H4',0,0,0,0,'N'),(15,6,4,'A1:Z1:L1:E2:P3:H4',0,0,0,0,'N'),(19,7,4,'A1:Z1:L1:E3:P1:H4',0,0,0,0,'N'),(23,8,4,'A1:Z1:L1:E3:P2:H4',0,0,0,0,'N'),(27,9,4,'A1:Z1:L1:E3:P3:H4',0,0,0,0,'N'),(28,10,1,'A1:Z1:L2:E1:P1:H1',0,0,0,0,'N'),(29,10,2,'A1:Z1:L2:E1:P1:H2',0,0,0,0,'N'),(30,10,3,'A1:Z1:L2:E1:P1:H3',0,0,0,0,'N'),(31,11,1,'A1:Z1:L2:E1:P2:H1',0,0,0,0,'N'),(32,11,2,'A1:Z1:L2:E1:P2:H2',0,0,0,0,'N'),(33,11,3,'A1:Z1:L2:E1:P2:H3',0,0,0,0,'N'),(34,12,1,'A1:Z1:L2:E1:P3:H1',0,0,0,0,'N'),(35,12,2,'A1:Z1:L2:E1:P3:H2',0,0,0,0,'N'),(36,12,3,'A1:Z1:L2:E1:P3:H3',0,0,0,0,'N'),(37,13,1,'A1:Z1:L2:E2:P1:H1',0,0,0,0,'N'),(38,13,2,'A1:Z1:L2:E2:P1:H2',0,0,0,0,'N'),(39,13,3,'A1:Z1:L2:E2:P1:H3',0,0,0,0,'N'),(40,14,1,'A1:Z1:L2:E2:P2:H1',0,0,0,0,'N'),(41,14,2,'A1:Z1:L2:E2:P2:H2',0,0,0,0,'N'),(42,14,3,'A1:Z1:L2:E2:P2:H3',0,0,0,0,'N'),(43,15,1,'A1:Z1:L2:E2:P3:H1',0,0,0,0,'N'),(44,15,2,'A1:Z1:L2:E2:P3:H2',0,0,0,0,'N'),(45,15,3,'A1:Z1:L2:E2:P3:H3',0,0,0,0,'N'),(46,16,1,'A1:Z1:L2:E3:P1:H1',0,0,0,0,'N'),(47,16,2,'A1:Z1:L2:E3:P1:H2',0,0,0,0,'N'),(48,17,1,'A1:Z1:L2:E3:P2:H1',0,0,0,0,'N'),(49,17,2,'A1:Z1:L2:E3:P2:H2',0,0,0,0,'N'),(50,18,1,'A1:Z1:L2:E3:P3:H1',0,0,0,0,'N'),(51,18,2,'A1:Z1:L2:E3:P3:H2',0,0,0,0,'N'),(52,19,1,'A1:Z2:L1:E1:P1:H1',0,0,0,0,'N'),(53,20,1,'A1:Z2:L1:E1:P2:H1',0,0,0,0,'N'),(54,21,1,'A1:Z2:L1:E1:P3:H1',0,0,0,0,'N'),(55,22,1,'A1:Z2:L1:E2:P1:H1',0,0,0,0,'N'),(56,22,2,'A1:Z2:L1:E2:P1:H2',0,0,0,0,'N'),(57,23,1,'A1:Z2:L1:E2:P2:H1',0,0,0,0,'N'),(58,23,2,'A1:Z2:L1:E2:P2:H2',0,0,0,0,'N'),(59,24,1,'A1:Z2:L1:E2:P3:H1',0,0,0,0,'N'),(60,24,2,'A1:Z2:L1:E2:P3:H2',0,0,0,0,'N'),(61,25,1,'A1:Z2:L1:E3:P1:H1',0,0,0,0,'N'),(62,25,2,'A1:Z2:L1:E3:P1:H2',0,0,0,0,'N'),(63,25,3,'A1:Z2:L1:E3:P1:H3',0,0,0,0,'N'),(64,25,4,'A1:Z2:L1:E3:P1:H4',0,0,0,0,'N'),(65,26,1,'A1:Z2:L1:E3:P2:H1',0,0,0,0,'N'),(66,26,2,'A1:Z2:L1:E3:P2:H2',0,0,0,0,'N'),(67,26,3,'A1:Z2:L1:E3:P2:H3',0,0,0,0,'N'),(68,26,4,'A1:Z2:L1:E3:P2:H4',0,0,0,0,'N'),(69,27,1,'A1:Z2:L1:E3:P3:H1',0,0,0,0,'N'),(70,27,2,'A1:Z2:L1:E3:P3:H2',0,0,0,0,'N'),(71,27,3,'A1:Z2:L1:E3:P3:H3',0,0,0,0,'N'),(72,27,4,'A1:Z2:L1:E3:P3:H4',0,0,0,0,'N'),(73,28,1,'A1:Z2:L2:E1:P1:H1',0,0,0,0,'N'),(74,28,2,'A1:Z2:L2:E1:P1:H2',0,0,0,0,'N'),(75,28,3,'A1:Z2:L2:E1:P1:H3',0,0,0,0,'N'),(76,28,4,'A1:Z2:L2:E1:P1:H4',0,0,0,0,'N'),(77,29,1,'A1:Z2:L2:E1:P2:H1',0,0,0,0,'N'),(78,29,2,'A1:Z2:L2:E1:P2:H2',0,0,0,0,'N'),(79,29,3,'A1:Z2:L2:E1:P2:H3',0,0,0,0,'N'),(80,29,4,'A1:Z2:L2:E1:P2:H4',0,0,0,0,'N'),(81,30,1,'A1:Z2:L2:E1:P3:H1',0,0,0,0,'N'),(82,30,2,'A1:Z2:L2:E1:P3:H2',0,0,0,0,'N'),(83,30,3,'A1:Z2:L2:E1:P3:H3',0,0,0,0,'N'),(84,30,4,'A1:Z2:L2:E1:P3:H4',0,0,0,0,'N'),(85,31,1,'A1:Z2:L2:E2:P1:H1',0,0,0,0,'N'),(86,31,2,'A1:Z2:L2:E2:P1:H2',0,0,0,0,'N'),(87,31,3,'A1:Z2:L2:E2:P1:H3',0,0,0,0,'N'),(88,31,4,'A1:Z2:L2:E2:P1:H4',0,0,0,0,'N'),(89,32,1,'A1:Z2:L2:E2:P2:H1',0,0,0,0,'N'),(90,32,2,'A1:Z2:L2:E2:P2:H2',0,0,0,0,'N'),(91,32,3,'A1:Z2:L2:E2:P2:H3',0,0,0,0,'N'),(92,32,4,'A1:Z2:L2:E2:P2:H4',0,0,0,0,'N'),(93,33,1,'A1:Z2:L2:E2:P3:H1',0,0,0,0,'N'),(94,33,2,'A1:Z2:L2:E2:P3:H2',0,0,0,0,'N'),(95,33,3,'A1:Z2:L2:E2:P3:H3',0,0,0,0,'N'),(96,33,4,'A1:Z2:L2:E2:P3:H4',0,0,0,0,'N'),(97,34,1,'A1:Z2:L2:E3:P1:H1',0,0,0,0,'N'),(98,35,1,'A1:Z2:L2:E3:P2:H1',0,0,0,0,'N'),(99,36,1,'A1:Z2:L2:E3:P3:H1',0,0,0,0,'N'),(100,37,1,'A1:Z3:L1:E1:P1:H1',0,0,0,0,'N'),(101,37,2,'A1:Z3:L1:E1:P1:H2',0,0,0,0,'N'),(102,37,3,'A1:Z3:L1:E1:P1:H3',0,0,0,0,'N'),(103,38,1,'A1:Z3:L1:E1:P2:H1',0,0,0,0,'N'),(104,38,2,'A1:Z3:L1:E1:P2:H2',0,0,0,0,'N'),(105,38,3,'A1:Z3:L1:E1:P2:H3',0,0,0,0,'N'),(106,39,1,'A1:Z3:L1:E1:P3:H1',0,0,0,0,'N'),(107,39,2,'A1:Z3:L1:E1:P3:H2',0,0,0,0,'N'),(108,39,3,'A1:Z3:L1:E1:P3:H3',0,0,0,0,'N'),(109,40,1,'A1:Z3:L1:E1:P4:H1',0,0,0,0,'N'),(110,40,2,'A1:Z3:L1:E1:P4:H2',0,0,0,0,'N'),(111,40,3,'A1:Z3:L1:E1:P4:H3',0,0,0,0,'N'),(112,41,1,'A1:Z3:L1:E2:P1:H1',0,0,0,0,'N'),(113,41,2,'A1:Z3:L1:E2:P1:H2',0,0,0,0,'N'),(114,42,1,'A1:Z3:L1:E2:P2:H1',0,0,0,0,'N'),(115,42,2,'A1:Z3:L1:E2:P2:H2',0,0,0,0,'N'),(116,43,1,'A1:Z3:L1:E2:P3:H1',0,0,0,0,'N'),(117,43,2,'A1:Z3:L1:E2:P3:H2',0,0,0,0,'N'),(118,44,1,'A1:Z3:L1:E2:P4:H1',0,0,0,0,'N'),(119,44,2,'A1:Z3:L1:E2:P4:H2',0,0,0,0,'N'),(120,45,1,'A1:Z4:L1:E1:P1:H1',0,0,0,0,'N'),(121,45,2,'A1:Z4:L1:E1:P1:H2',0,0,0,0,'N'),(122,46,1,'A1:Z4:L1:E1:P2:H1',0,0,0,0,'N'),(123,46,2,'A1:Z4:L1:E1:P2:H2',0,0,0,0,'N'),(124,47,1,'A1:Z4:L1:E1:P3:H1',0,0,0,0,'N'),(125,47,2,'A1:Z4:L1:E1:P3:H2',0,0,0,0,'N'),(126,48,1,'A1:Z4:L1:E1:P4:H1',0,0,0,0,'N'),(127,48,2,'A1:Z4:L1:E1:P4:H2',0,0,0,0,'N'),(128,49,1,'A1:Z4:L1:E2:P1:H1',0,0,0,0,'N'),(129,49,2,'A1:Z4:L1:E2:P1:H2',0,0,0,0,'N'),(130,50,1,'A1:Z4:L1:E2:P2:H1',0,0,0,0,'N'),(131,50,2,'A1:Z4:L1:E2:P2:H2',0,0,0,0,'N'),(132,51,1,'A1:Z4:L1:E2:P3:H1',0,0,0,0,'N'),(133,51,2,'A1:Z4:L1:E2:P3:H2',0,0,0,0,'N'),(134,52,1,'A1:Z4:L1:E2:P4:H1',0,0,0,0,'N'),(135,52,2,'A1:Z4:L1:E2:P4:H2',0,0,0,0,'N'),(136,53,1,'A1:Z4:L1:E3:P1:H1',0,0,0,0,'N'),(137,53,2,'A1:Z4:L1:E3:P1:H2',0,0,0,0,'N'),(138,53,3,'A1:Z4:L1:E3:P1:H3',0,0,0,0,'N'),(139,54,1,'A1:Z4:L1:E3:P2:H1',0,0,0,0,'N'),(140,54,2,'A1:Z4:L1:E3:P2:H2',0,0,0,0,'N'),(141,54,3,'A1:Z4:L1:E3:P2:H3',0,0,0,0,'N'),(142,55,1,'A1:Z4:L1:E3:P3:H1',0,0,0,0,'N'),(143,55,2,'A1:Z4:L1:E3:P3:H2',0,0,0,0,'N'),(144,55,3,'A1:Z4:L1:E3:P3:H3',0,0,0,0,'N'),(145,56,1,'A1:Z4:L1:E3:P4:H1',0,0,0,0,'N'),(146,56,2,'A1:Z4:L1:E3:P4:H2',0,0,0,0,'N'),(147,56,3,'A1:Z4:L1:E3:P4:H3',0,0,0,0,'N'),(148,57,1,'A1:Z4:L1:E4:P1:H1',0,0,0,0,'N'),(149,57,2,'A1:Z4:L1:E4:P1:H2',0,0,0,0,'N'),(150,57,3,'A1:Z4:L1:E4:P1:H3',0,0,0,0,'N'),(151,58,1,'A1:Z4:L1:E4:P2:H1',0,0,0,0,'N'),(152,58,2,'A1:Z4:L1:E4:P2:H2',0,0,0,0,'N'),(153,58,3,'A1:Z4:L1:E4:P2:H3',0,0,0,0,'N'),(154,59,1,'A1:Z4:L1:E4:P3:H1',0,0,0,0,'N'),(155,59,2,'A1:Z4:L1:E4:P3:H2',0,0,0,0,'N'),(156,59,3,'A1:Z4:L1:E4:P3:H3',0,0,0,0,'N'),(157,60,1,'A1:Z4:L1:E4:P4:H1',0,0,0,0,'N'),(158,60,2,'A1:Z4:L1:E4:P4:H2',0,0,0,0,'N'),(159,60,3,'A1:Z4:L1:E4:P4:H3',0,0,0,0,'N'),(160,61,1,'A1:Z5:L1:E1:P1:H1',0,0,0,0,'N'),(161,61,2,'A1:Z5:L1:E1:P1:H2',0,0,0,0,'N'),(162,62,1,'A1:Z5:L1:E1:P2:H1',0,0,0,0,'N'),(163,62,2,'A1:Z5:L1:E1:P2:H2',0,0,0,0,'N'),(164,63,1,'A1:Z5:L1:E1:P3:H1',0,0,0,0,'N'),(165,63,2,'A1:Z5:L1:E1:P3:H2',0,0,0,0,'N'),(166,64,1,'A1:Z5:L1:E1:P4:H1',0,0,0,0,'N'),(167,64,2,'A1:Z5:L1:E1:P4:H2',0,0,0,0,'N'),(168,65,1,'A1:Z5:L1:E2:P1:H1',0,0,0,0,'N'),(169,65,2,'A1:Z5:L1:E2:P1:H2',0,0,0,0,'N'),(170,66,1,'A1:Z5:L1:E2:P2:H1',0,0,0,0,'N'),(171,66,2,'A1:Z5:L1:E2:P2:H2',0,0,0,0,'N'),(172,67,1,'A1:Z5:L1:E2:P3:H1',0,0,0,0,'N'),(173,67,2,'A1:Z5:L1:E2:P3:H2',0,0,0,0,'N'),(174,68,1,'A1:Z5:L1:E2:P4:H1',0,0,0,0,'N'),(175,68,2,'A1:Z5:L1:E2:P4:H2',0,0,0,0,'N'),(176,69,1,'A1:Z5:L1:E3:P1:H1',0,0,0,0,'N'),(177,69,2,'A1:Z5:L1:E3:P1:H2',0,0,0,0,'N'),(178,69,3,'A1:Z5:L1:E3:P1:H3',0,0,0,0,'N'),(179,70,1,'A1:Z5:L1:E3:P2:H1',0,0,0,0,'N'),(180,70,2,'A1:Z5:L1:E3:P2:H2',0,0,0,0,'N'),(181,70,3,'A1:Z5:L1:E3:P2:H3',0,0,0,0,'N'),(182,71,1,'A1:Z5:L1:E3:P3:H1',0,0,0,0,'N'),(183,71,2,'A1:Z5:L1:E3:P3:H2',0,0,0,0,'N'),(184,71,3,'A1:Z5:L1:E3:P3:H3',0,0,0,0,'N'),(185,72,1,'A1:Z5:L1:E3:P4:H1',0,0,0,0,'N'),(186,72,2,'A1:Z5:L1:E3:P4:H2',0,0,0,0,'N'),(187,72,3,'A1:Z5:L1:E3:P4:H3',0,0,0,0,'N'),(188,73,1,'A1:Z5:L1:E4:P1:H1',0,0,0,0,'N'),(189,73,2,'A1:Z5:L1:E4:P1:H2',0,0,0,0,'N'),(190,73,3,'A1:Z5:L1:E4:P1:H3',0,0,0,0,'N'),(191,74,1,'A1:Z5:L1:E4:P2:H1',0,0,0,0,'N'),(192,74,2,'A1:Z5:L1:E4:P2:H2',0,0,0,0,'N'),(193,74,3,'A1:Z5:L1:E4:P2:H3',0,0,0,0,'N'),(194,75,1,'A1:Z5:L1:E4:P3:H1',0,0,0,0,'N'),(195,75,2,'A1:Z5:L1:E4:P3:H2',0,0,0,0,'N'),(196,75,3,'A1:Z5:L1:E4:P3:H3',0,0,0,0,'N'),(197,76,1,'A1:Z5:L1:E4:P4:H1',0,0,0,0,'N'),(198,76,2,'A1:Z5:L1:E4:P4:H2',0,0,0,0,'N'),(199,76,3,'A1:Z5:L1:E4:P4:H3',0,0,0,0,'N'),(200,77,1,'Vehículo I',0,0,0,0,'N'),(201,78,1,'Vehículo II',0,0,0,0,'N'),(202,79,1,'Vehículo III',0,0,0,0,'N'),(203,80,1,'Big Bag 01',0,0,0,0,'S'),(204,80,1,'Big Bag 02',0,0,0,0,'S'),(205,80,1,'Big Bag 03',0,0,0,0,'S'),(206,80,1,'Big Bag 04',0,0,0,0,'S'),(207,80,1,'Big Bag 05',0,0,0,0,'S'),(208,80,1,'Big Bag 06',0,0,0,0,'S'),(209,80,1,'Big Bag 07',0,0,0,0,'S'),(210,80,1,'Big Bag 08',0,0,0,0,'S'),(211,80,1,'Big Bag 09',0,0,0,0,'S'),(212,80,1,'Big Bag 10',0,0,0,0,'S'),(213,80,1,'Big Bag 11',0,0,0,0,'S'),(214,80,1,'Big Bag 12',0,0,0,0,'S');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_linea (
  idLinea bigint(20) NOT NULL AUTO_INCREMENT,
  idZona bigint(20) NOT NULL DEFAULT '0',
  idLineaZona bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) NOT NULL DEFAULT '',
  urlPlano varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idLinea)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO ubicacion_linea (idLinea, idZona, idLineaZona, descripcion, urlPlano) VALUES (1,1,1,'Conservacion 1 arriba','ubicacion/A1Z1L1.jsp'),(2,1,2,'Conservacion 1 abajo','ubicacion/A1Z1L2.jsp'),(3,2,1,'Conservacion 2 arriba','ubicacion/A1Z2L1.jsp'),(4,2,2,'Conservacion 2 abajo','ubicacion/A1Z2L2.jsp'),(5,3,1,'Cartonaje','ubicacion/A1Z3L1.jsp'),(6,4,1,'Almacen 1','ubicacion/A1Z4L1.jsp'),(7,5,1,'Almacen 2','ubicacion/A1Z5L1.jsp'),(8,9,1,'Vehículo I',''),(9,10,1,'Vehículo II',''),(10,11,1,'Vehículo III',''),(11,6,1,'Zona de envasado','ubicacion/zonaEnvasado.jsp');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_piso (
  idPiso bigint(20) NOT NULL AUTO_INCREMENT,
  idEstanteria bigint(20) NOT NULL DEFAULT '0',
  idPisoEstanteria bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idPiso)
) ENGINE=MyISAM AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO ubicacion_piso (idPiso, idEstanteria, idPisoEstanteria, descripcion) VALUES (1,1,1,'A1:Z1:L1:E1:P1'),(2,1,2,'A1:Z1:L1:E1:P2'),(3,1,3,'A1:Z1:L1:E1:P3'),(4,2,1,'A1:Z1:L1:E2:P1'),(5,2,2,'A1:Z1:L1:E2:P2'),(6,2,3,'A1:Z1:L1:E2:P3'),(7,3,1,'A1:Z1:L1:E3:P1'),(8,3,2,'A1:Z1:L1:E3:P2'),(9,3,3,'A1:Z1:L1:E3:P3'),(10,4,1,'A1:Z1:L2:E1:P1'),(11,4,2,'A1:Z1:L2:E1:P2'),(12,4,3,'A1:Z1:L2:E1:P3'),(13,5,1,'A1:Z1:L2:E2:P1'),(14,5,2,'A1:Z1:L2:E2:P2'),(15,5,3,'A1:Z1:L2:E2:P3'),(16,6,1,'A1:Z1:L2:E3:P1'),(17,6,2,'A1:Z1:L2:E3:P2'),(18,6,2,'A1:Z1:L2:E3:P3'),(19,7,1,'A1:Z2:L1:E1:P1'),(20,7,2,'A1:Z2:L1:E1:P2'),(21,7,3,'A1:Z2:L1:E1:P3'),(22,8,1,'A1:Z2:L1:E2:P1'),(23,8,2,'A1:Z2:L1:E2:P2'),(24,8,3,'A1:Z2:L1:E2:P3'),(25,9,1,'A1:Z2:L1:E3:P1'),(26,9,2,'A1:Z2:L1:E3:P2'),(27,9,3,'A1:Z2:L1:E3:P3'),(28,10,1,'A1:Z2:L2:E1:P1'),(29,10,2,'A1:Z2:L2:E1:P2'),(30,10,3,'A1:Z2:L2:E1:P3'),(31,11,1,'A1:Z2:L2:E2:P1'),(32,11,2,'A1:Z2:L2:E2:P2'),(33,11,3,'A1:Z2:L2:E2:P3'),(34,12,1,'A1:Z2:L2:E3:P1'),(35,12,2,'A1:Z2:L2:E3:P2'),(36,12,3,'A1:Z2:L2:E3:P3'),(37,13,1,'A1:Z3:L1:E1:P1'),(38,13,2,'A1:Z3:L1:E1:P2'),(39,13,3,'A1:Z3:L1:E1:P3'),(40,13,4,'A1:Z3:L1:E1:P4'),(41,14,1,'A1:Z3:L1:E2:P1'),(42,14,2,'A1:Z3:L1:E2:P2'),(43,14,3,'A1:Z3:L1:E2:P3'),(44,14,4,'A1:Z3:L1:E2:P4'),(45,15,1,'A1:Z4:L1:E1:P1'),(46,15,2,'A1:Z4:L1:E1:P2'),(47,15,3,'A1:Z4:L1:E1:P3'),(48,15,4,'A1:Z4:L1:E1:P4'),(49,16,1,'A1:Z4:L1:E2:P1'),(50,16,2,'A1:Z4:L1:E2:P2'),(51,16,3,'A1:Z4:L1:E2:P3'),(52,16,4,'A1:Z4:L1:E2:P4'),(53,17,1,'A1:Z4:L1:E3:P1'),(54,17,2,'A1:Z4:L1:E3:P2'),(55,17,3,'A1:Z4:L1:E3:P3'),(56,17,4,'A1:Z4:L1:E3:P4'),(57,18,1,'A1:Z4:L1:E4:P1'),(58,18,2,'A1:Z4:L1:E4:P2'),(59,18,3,'A1:Z4:L1:E4:P3'),(60,18,4,'A1:Z4:L1:E4:P4'),(61,19,1,'A1:Z5:L1:E1:P1'),(62,19,2,'A1:Z5:L1:E1:P2'),(63,19,3,'A1:Z5:L1:E1:P3'),(64,19,4,'A1:Z5:L1:E1:P4'),(65,20,1,'A1:Z5:L1:E2:P1'),(66,20,2,'A1:Z5:L1:E2:P2'),(67,20,3,'A1:Z5:L1:E2:P3'),(68,20,4,'A1:Z5:L1:E2:P4'),(69,21,1,'A1:Z5:L1:E3:P1'),(70,21,2,'A1:Z5:L1:E3:P2'),(71,21,3,'A1:Z5:L1:E3:P3'),(72,21,4,'A1:Z5:L1:E3:P4'),(73,22,1,'A1:Z5:L1:E4:P1'),(74,22,2,'A1:Z5:L1:E4:P2'),(75,22,3,'A1:Z5:L1:E4:P3'),(76,22,4,'A1:Z5:L1:E4:P4'),(77,23,1,'Vehículo I'),(78,24,1,'Vehículo II'),(79,25,1,'Vehículo III'),(80,26,1,'Zona de envasado');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_producto (
  UBICACION_idUbicacion bigint(20) NOT NULL DEFAULT '0',
  productosUbicacion_idProducto bigint(20) NOT NULL DEFAULT '0',
  KEY FK37AE05205530B0E3 (UBICACION_idUbicacion),
  KEY FK37AE05206FDE382E (productosUbicacion_idProducto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_zona (
  idZona bigint(20) NOT NULL AUTO_INCREMENT,
  idAlmacen bigint(20) NOT NULL DEFAULT '0',
  idZonaAlmacen bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) NOT NULL DEFAULT '',
  tipoAlmacenado char(1) NOT NULL DEFAULT '',
  urlPlano varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idZona)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO ubicacion_zona (idZona, idAlmacen, idZonaAlmacen, descripcion, tipoAlmacenado, urlPlano) VALUES (0,1,0,'Entrada','','/img/planos/barciaEntrada.png'),(1,1,1,'Conservacion 1','','/img/planos/barciaConservacion1.png'),(2,1,2,'Conservacion 2','','/img/planos/barciaConservacion2.png'),(8,1,8,'Congelador','','/img/planos/barciaCongelador.png'),(3,1,3,'Cartonaje','','/img/planos/barciaCartonaje.png'),(4,1,4,'Almacen 1','','/img/planos/barciaAlmacen1.png'),(5,1,5,'Almacen 2','','/img/planos/barciaAlmacen2.png'),(6,1,6,'Zona de envasado','','/img/planos/barciaZonaEnvasado.png'),(7,1,7,'Almacenamiento palets - Maquinaria y vehiculos','','/img/planos/barciaPaletsMaquinaria.png'),(9,2,1,'Vehículo I','',''),(10,3,2,'Vehículo II','',''),(11,4,3,'Vehículo III','','');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacionzona (
  idZona bigint(20) NOT NULL DEFAULT '0',
  idTipoUbicacion bigint(20) DEFAULT NULL,
  nave char(3) DEFAULT NULL,
  nombre varchar(255) DEFAULT NULL,
  descripcion text,
  dimensiones varchar(255) DEFAULT NULL,
  idDireccion bigint(20) DEFAULT NULL,
  PRIMARY KEY (idZona)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO ubicacionzona (idZona, idTipoUbicacion, nave, nombre, descripcion, dimensiones, idDireccion) VALUES (1,1,'N1','CÃƒÂ¡mara de conservaciÃƒÂ³n 1',NULL,NULL,NULL),(2,1,'N1','CÃƒÂ¡mara de conservaciÃƒÂ³n 2',NULL,NULL,NULL),(3,1,'N1','AlmacÃƒÂ©n de cartonaje',NULL,NULL,NULL),(4,1,'N1','AlmacÃƒÂ©n 1',NULL,NULL,NULL),(5,1,'N1','AlmacÃƒÂ©n 2',NULL,NULL,NULL),(6,1,'N1','Zona de envasado',NULL,NULL,NULL),(7,1,'N1','Congelador',NULL,NULL,NULL),(9,1,'N1','Entrada',NULL,NULL,NULL);
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE usuario (
  idRol tinyint(3) unsigned DEFAULT NULL,
  login varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO usuario (idRol, login, password) VALUES (2,'prueba','e7ad116336da8568d74ec4047e6c8926'),(1,'admin','f561aaf6ef0bf14d4208bb46a4ccb3ad'),(2,'Victor','1cc0aab597061d1d7afe37cf8e9a8564');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE vehiculo (
  idVehiculo bigint(20) NOT NULL AUTO_INCREMENT,
  matricula varchar(50) DEFAULT NULL,
  idProveedor bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (idVehiculo)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

