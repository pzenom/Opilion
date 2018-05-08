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
INSERT INTO banco (idBanco, nombre) VALUES (1,' No definido'),(2,'Santander'),(3,'BBVA');
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE cosecha (
  idCosecha bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(100) NOT NULL DEFAULT '',
  tipo set('V','N','I') DEFAULT NULL,
  PRIMARY KEY (idCosecha)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE entidad_rol (
  idUsuario bigint(20) NOT NULL DEFAULT '0',
  idRol bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (idUsuario,idRol),
  KEY idRol (idRol)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE estadofabas (
  idEstadoFabas bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(100) DEFAULT NULL,
  PRIMARY KEY (idEstadoFabas)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE estadoproducto (
  idEstado bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(50) DEFAULT NULL,
  PRIMARY KEY (idEstado)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE homologacion_proveedor (
  idUsuario bigint(20) NOT NULL DEFAULT '0',
  idRequisito tinyint(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (idUsuario,idRequisito)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE impuestos (
  idImpuesto bigint(10) NOT NULL AUTO_INCREMENT,
  nombre varchar(50) DEFAULT NULL,
  porcentaje double unsigned DEFAULT NULL,
  PRIMARY KEY (idImpuesto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE incidencia (
  idIncidencia bigint(20) NOT NULL AUTO_INCREMENT,
  nombre varchar(50) DEFAULT NULL,
  PRIMARY KEY (idIncidencia)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE lopd (
  idLinea bigint(20) NOT NULL DEFAULT '0',
  texto varchar(200) DEFAULT NULL,
  PRIMARY KEY (idLinea)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE material (
  idMaterial bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(100) DEFAULT NULL,
  PRIMARY KEY (idMaterial)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE materiaprima_categoria (
  idMateriaCategoria bigint(20) NOT NULL AUTO_INCREMENT,
  idMateriaPrima bigint(20) NOT NULL,
  idCategoria bigint(20) NOT NULL,
  stock double(8,2) NOT NULL DEFAULT '0.00',
  habilitado char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (idMateriaCategoria)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto_compuesto (
  idProducto bigint(20) NOT NULL,
  idCompuestoDe bigint(20) NOT NULL DEFAULT '0',
  cantidad double NOT NULL DEFAULT '0',
  PRIMARY KEY (idProducto,idCompuestoDe)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
INSERT INTO provincia (idProvincia, nombre) VALUES (0,' No aplica'),(1,'Alava'),(2,'Albacete'),(3,'Alicante'),(4,'Almería'),(5,'Asturias'),(6,'�?vila'),(7,'Bajadoz'),(8,'Barcelona'),(9,'Burgos'),(10,'Cáceres'),(11,'Cádiz'),(12,'Cantabria'),(13,'Ciudad Real'),(14,'Córdoba'),(15,'Cuenca'),(16,'Gerona'),(17,'Granada'),(18,'Guadalajara'),(19,'Guipúzcoa'),(20,'Huelva'),(21,'Huesca'),(22,'Islas Baleares'),(23,'Jaén'),(24,'La Coruña'),(25,'La Rioja'),(26,'Las Palmas'),(27,'León'),(28,'Lugo'),(29,'Madrid'),(30,'Málaga'),(31,'Murcia'),(32,'Navarra'),(33,'Orense'),(34,'Palencia'),(35,'Pontevedra'),(36,'Salamanca'),(37,'Santa Cruz de Tenerife'),(38,'Segovia'),(39,'Sevilla'),(40,'Soria'),(41,'Tarragona'),(42,'Teruel'),(43,'Toledo'),(44,'Valencia'),(45,'Valladolid'),(46,'Vizcaya'),(47,'Zamora'),(48,'Zaragoza'),(49,'Castellón'),(50,'Lérida');
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
INSERT INTO requisito_homologacion (idRequisito, nombre) VALUES (0,'No aplica'),(1,'Fotocopia de Registro Sanitario'),(2,'Carta de compromiso de transporte en condiciones controladas: modelo de registro R03-P02'),(3,'Certificación de poseer y aplicar APPCC y/o fotocopia de otros certificados de Calidad (ISO 9000, 14000, etc.)'),(4,'Inscripción en el Registro del Consejo Regulador');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE rol (
  idRol bigint(20) NOT NULL AUTO_INCREMENT,
  nombre varchar(255) DEFAULT NULL,
  descripcion varchar(255) DEFAULT NULL,
  usuario_idUsuario bigint(20) DEFAULT NULL,
  PRIMARY KEY (idRol),
  KEY FK13DAF42275F90 (usuario_idUsuario)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipoubicacion (
  IdTipoUbicacion bigint(20) NOT NULL DEFAULT '0',
  nombre varchar(100) DEFAULT NULL,
  PRIMARY KEY (IdTipoUbicacion)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipovehiculo (
  idTipoVehiculo bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(100) DEFAULT NULL,
  PRIMARY KEY (idTipoVehiculo)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_estanteria (
  idEstanteria bigint(20) NOT NULL AUTO_INCREMENT,
  idLinea bigint(20) NOT NULL DEFAULT '0',
  idEstanteriaLinea bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) NOT NULL DEFAULT '',
  urlPlano varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idEstanteria)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_linea (
  idLinea bigint(20) NOT NULL AUTO_INCREMENT,
  idZona bigint(20) NOT NULL DEFAULT '0',
  idLineaZona bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) NOT NULL DEFAULT '',
  urlPlano varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idLinea)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ubicacion_piso (
  idPiso bigint(20) NOT NULL AUTO_INCREMENT,
  idEstanteria bigint(20) NOT NULL DEFAULT '0',
  idPisoEstanteria bigint(20) NOT NULL DEFAULT '0',
  descripcion varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (idPiso)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
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

