package es.induserco.opilion.datos.especial;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.OrdersLoc;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.edifact.data.x12.IMD;
import es.induserco.edifact.negocio.EdifactParserDataHelper;
import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.comun.Rol;
import es.induserco.opilion.data.comun.banca.DatoBancario;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Email;
import es.induserco.opilion.data.comun.contacto.Telefono;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Backup;
import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.infraestructura.DatabaseConfig;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;
import es.induserco.opilion.presentacion.sincronizacion.SimpleErrorHandler;

/**
 * @author andres (01/07/2011) - (27/08/2012)
 * @version 2.0
 */
public class GestionesEspecialesDAO extends DatabaseConfig implements IGestionesEspecialesDataService {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;
	
	/**
	 * Devuelve texto sobre LOPD para imprimir en las facturas/albaranes
	 * @return lopd
	 * @throws Exception
	 */
	//@Override
	public String getLOPDFactura() throws Exception{
		String lopd = "";
		try {
			con = bddConexion();
			String Qry = "SELECT lopdFactura FROM empresa WHERE idEmpresa = 1";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				lopd = rs.getString("lopdFactura");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {}
		}
		return lopd;
	}
	
	/**
	 * Registra la copia de seguridad pasada por par�metros
	 */
	//@Override
	public void registrarBackup(Backup backup) throws Exception{
		con = bddConexion();
		Statement stmt1 = null;
		//SQL de insercion
		String insertSql =
			"INSERT INTO copiaseguridad(fecha, auto) VALUES(CURRENT_TIMESTAMP(),'N')";
		// System.out.println(insertSql);
		stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt1.executeUpdate(insertSql);			
	}
	
	/**
	 * Registra la copia de seguridad pasada por par�metros
	 */
	//@Override
	public Backup getUltimoBackupAuto() throws Exception{
		Backup backup = new Backup();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM  copiaseguridad WHERE auto='S' AND ultimoAuto='S'";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				backup.setAuto('S');
				backup.setFecha(rs.getString("fecha"));
				backup.setNombre(rs.getString("nombre"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {}
		}
		return backup;
	}
	
	//@Override
	public void registroBackupAuto() throws Exception{
		try {
			//1. Modificamos el campo auto del que hasta ahora era el ultimo backup
			Connection conn = bddConexion();
			Statement stmt1;
			//SQL de insercion
			String insertSql = "UPDATE copiaseguridad SET ultimoAuto='N' WHERE auto='S' AND ultimoAuto='S'";
			// System.out.println(insertSql);
			stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt1.executeUpdate(insertSql);
			//2. Insertamos el nuevo backup
			//SQL de insercion
			insertSql =	"INSERT INTO copiaseguridad(fecha, auto, ultimoAuto) VALUES(CURRENT_TIMESTAMP(),'S','S')";
			// System.out.println(insertSql);
			stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt1.executeUpdate(insertSql);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
	
	//@Override
	public Vector<String> getFicherosXML() throws Exception{
		Vector<String> ficherosXML = new Vector<String>();
		//Cargamos la ruta del fichero validacionXML
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String path = loader.getResource("configuracion.xml").toURI().getPath();
		Document doc = dBuilder.parse(new File(path));
		doc.getDocumentElement().normalize();
		NodeList listaNodosHijos = doc.getElementsByTagName("sincronizacion");
		Node sincronizacion = listaNodosHijos.item(0);
		Element elemento = (Element) sincronizacion;
		String MY_SCHEMA = getTagValue("ficheroValidacion", elemento);
		String MY_XML = getTagValue("ficheroImportar", elemento);
		ficherosXML.add(MY_XML);
		ficherosXML.add(MY_SCHEMA);
		return ficherosXML;
	}
	
	//@Override
	public boolean validarXML(String MY_XML, String MY_SCHEMA) throws Exception{
		boolean ficheroValidado = true;
		SAXParserFactory  factory = SAXParserFactory .newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		SchemaFactory schemaFactory = SchemaFactory .newInstance("http://www.w3.org/2001/XMLSchema");
		factory.setSchema(schemaFactory.newSchema(new Source [] {new StreamSource (MY_SCHEMA)}));
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		reader.setErrorHandler(new SimpleErrorHandler());
		try {
			reader.parse(new InputSource (MY_XML));
		} catch (SAXException saxEx){
			// System.out.println(saxEx.getMessage());
			ficheroValidado = false;
			// saxEx.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
			ficheroValidado = false;
		}
		return ficheroValidado;
	}
	
	//@Override
	public void importarAlbaranes(String MY_XML) throws Exception{
		//Importamos albaranes
		// System.out.println("Vamos a importar los albaranes");
		try {
			File fXmlFile = new File(MY_XML);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			// System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("albaran");
			// System.out.println("-----------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList hijos = eElement.getChildNodes();
					//Tenemos un nuevo pedido
					Albaran albaran = new Albaran();
					double importeTotalAlbaran = 0;
					int lineasAlbaran = 0;
					int cuantosHijos = hijos.getLength();
					for (int i = 0; i < cuantosHijos; i++){
						//Un hijo ser� datosGenerales y el otro ser� detalles albar�n
						Element e = (Element) hijos.item(i);
						String nombreNodo = e.getNodeName();
						if (nombreNodo.compareToIgnoreCase("datosGenerales") == 0){
							
							String sCodigoAlbaran = getTagValue("codigoPedido", e);
							albaran.setCodigoAlbaran(sCodigoAlbaran);
							// System.out.println("codigoAlbaran : " + sCodigoAlbaran);
							
							String sIdCliente = getTagValue("idCliente", e);
							albaran.setIdCliente(Long.parseLong(sIdCliente));
							// System.out.println("idCliente : " + sIdCliente);
							
							String sIdFormaPago = getTagValue("idFormaPago", e);
							albaran.setIdFormaPago(Long.parseLong(sIdFormaPago));
							// System.out.println("idFormaPago : " + sIdFormaPago);
							
							String sFechaRegistro = getTagValue("fechaRegistro", e);
							String sFechaEntrega = getTagValue("fechaEntrega", e);
							String sFechaVencimiento = getTagValue("fechaVencimiento", e);
							//La fecha viene de la forma 15/5/2012
							//Insertamos las fechas para el pedido
							/*String fechaRegistroFormateada = formatearFecha(sFechaRegistro);
							String fechaEntregaFormateada = formatearFecha(sFechaEntrega);
							String fechaVencimientoFormateada = formatearFecha(sFechaVencimiento);*/
							albaran.setFecha(sFechaRegistro);
							albaran.setFechaEntrega(sFechaEntrega);
							albaran.setFechaVencimiento(sFechaVencimiento);

							String sObservaciones = getTagValue("observaciones", e);
							albaran.setObservaciones(sObservaciones);
							// System.out.println("observaciones : " + sObservaciones);
							
							String sResponsable = getTagValue("responsable", e);
							albaran.setUsuarioResponsable(sResponsable);
							// System.out.println("sResponsable : " + sResponsable);
						}else{
							if (nombreNodo.compareToIgnoreCase("detallesPedido") == 0){
								NodeList hijos2 = e.getChildNodes();
								int cuantosHijos2 = hijos2.getLength();
								for (int j = 0; j < cuantosHijos2; j++){
									//Tendr� j hijos lineaAlbaran
									Element el = (Element) hijos2.item(j);
									String nombreNodo2 = el.getNodeName();
									if (nombreNodo2.compareToIgnoreCase("lineaPedido") == 0){
										
										Vector<Bulto> bultos = new Vector<Bulto>();
										
										//A�adir una nueva linea al pedido
										RegistroSalida lineaAlbaran = new RegistroSalida();
										lineasAlbaran++;
										lineaAlbaran.setNumLinea((long)j + 1);
										
										String sIdProducto = getTagValue("idProducto", el);
										// System.out.println("idProducto : " + sIdProducto);
										lineaAlbaran.setIdProducto(Long.parseLong(sIdProducto));

										String sIdDireccion = getTagValue("idDireccion", el);
										// System.out.println("idDireccion : " + sIdDireccion);
										lineaAlbaran.setIdDireccion(Long.parseLong(sIdProducto));

										String sRegistro = getTagValue("registro", el);
										// System.out.println("registro : " + sRegistro);
										//lineaAlbaran.setRegistro(sRegistro);
										
										String sCantidad = getTagValue("cantidad", el);
										// System.out.println("cantidad : " + sCantidad);
										lineaAlbaran.setCantidadUnitaria(Double.parseDouble(sCantidad));
										
										/*String sKilos = getTagValue("kilos", el);
										// System.out.println("kilos : " + sKilos);
										lineaAlbaran.setKilos(Double.parseDouble(sKilos));*/
									
										String sPeso = getTagValue("peso", el);
										// System.out.println("peso : " + sPeso);
										double peso = Double.parseDouble(sPeso);
										
										String sPrecioKilo = getTagValue("precioKilo", el);
										double precioKilo = Double.parseDouble(sPrecioKilo);
										double precioTotal = Double.parseDouble(sCantidad) * peso * precioKilo;
										
										double precioUnitario = precioTotal / Double.parseDouble(sCantidad);
										lineaAlbaran.setPrecioUnitario(precioUnitario);
										lineaAlbaran.setPrecioTotal(precioTotal);
										// System.out.println("precioKilo : " + precioKilo);
										// System.out.println("precioUnitario : " + precioUnitario);
										// System.out.println("precioTotal : " + precioTotal);

										importeTotalAlbaran += precioTotal;
										
										Bulto bulto = new Bulto();
										//Creamos el array de lotes
										Vector<Lote> lotes = new Vector<Lote>();
										Lote loteLeido = new Lote();
										loteLeido.setLote(sRegistro);
										loteLeido.setCantidad(Double.parseDouble(sCantidad));
										lotes.add(loteLeido);
										bulto.setLotes(lotes);
										bulto.setDireccionEntrega(Long.parseLong(albaran.getDestino()));
										bultos.add(bulto);
										lineaAlbaran.setBultos(bultos);
										lineaAlbaran.setNumeroBultos((long)bultos.size());
										lineaAlbaran.setAlbaran(albaran);
									}
								}
							}
						}
					}
					//C�digo del pedido
					String codigoAlbaran = new GestionRegistroSalidaHelper().getCodigoAlbaran();
					albaran.setCodigoAlbaran(codigoAlbaran);
					//Importe total del pedido
					albaran.setImporteTotal(importeTotalAlbaran);
					//Salvamos el pedido
					new GestionRegistroSalidaHelper().addAlbaran(albaran);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Override
	public void importarDevoluciones(String MY_XML) throws Exception{
		//Importamos albaranes
		// System.out.println("Vamos a importar las devoluciones");
		try {
			File fXmlFile = new File(MY_XML);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			// System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("albaran");
			// System.out.println("-----------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList hijos = eElement.getChildNodes();
					//Tenemos un nuevo pedido
					Albaran albaran = new Albaran();
					double importeTotalAlbaran = 0;
					int lineasAlbaran = 0;
					int cuantosHijos = hijos.getLength();
					for (int i = 0; i < cuantosHijos; i++){
						//Un hijo ser� datosGenerales y el otro ser� detalles albar�n
						Element e = (Element) hijos.item(i);
						String nombreNodo = e.getNodeName();
						if (nombreNodo.compareToIgnoreCase("datosGenerales") == 0){
							
							String sCodigoAlbaran = getTagValue("codigoPedido", e);
							albaran.setCodigoAlbaran(sCodigoAlbaran);
							// System.out.println("codigoAlbaran : " + sCodigoAlbaran);
							
							String sIdCliente = getTagValue("idCliente", e);
							albaran.setIdCliente(Long.parseLong(sIdCliente));
							// System.out.println("idCliente : " + sIdCliente);
							
							String sIdFormaPago = getTagValue("idFormaPago", e);
							albaran.setIdFormaPago(Long.parseLong(sIdFormaPago));
							// System.out.println("idFormaPago : " + sIdFormaPago);
							
							String sFechaRegistro = getTagValue("fechaRegistro", e);
							String sFechaEntrega = getTagValue("fechaEntrega", e);
							String sFechaVencimiento = getTagValue("fechaVencimiento", e);
							//La fecha viene de la forma 15/5/2012
							//Insertamos las fechas para el pedido
							/*String fechaRegistroFormateada = formatearFecha(sFechaRegistro);
							String fechaEntregaFormateada = formatearFecha(sFechaEntrega);
							String fechaVencimientoFormateada = formatearFecha(sFechaVencimiento);*/
							albaran.setFecha(sFechaRegistro);
							albaran.setFechaEntrega(sFechaEntrega);
							albaran.setFechaVencimiento(sFechaVencimiento);

							String sObservaciones = getTagValue("observaciones", e);
							albaran.setObservaciones(sObservaciones);
							// System.out.println("observaciones : " + sObservaciones);
							
							String sResponsable = getTagValue("responsable", e);
							albaran.setUsuarioResponsable(sResponsable);
							// System.out.println("sResponsable : " + sResponsable);
						}else{
							if (nombreNodo.compareToIgnoreCase("detallesPedido") == 0){
								NodeList hijos2 = e.getChildNodes();
								int cuantosHijos2 = hijos2.getLength();
								for (int j = 0; j < cuantosHijos2; j++){
									//Tendr� j hijos lineaAlbaran
									Element el = (Element) hijos2.item(j);
									String nombreNodo2 = el.getNodeName();
									if (nombreNodo2.compareToIgnoreCase("lineaPedido") == 0){
										
										Vector<Bulto> bultos = new Vector<Bulto>();
										
										//A�adir una nueva linea al pedido
										RegistroSalida lineaAlbaran = new RegistroSalida();
										lineasAlbaran++;
										lineaAlbaran.setNumLinea((long)j + 1);
										
										String sIdProducto = getTagValue("idProducto", el);
										// System.out.println("idProducto : " + sIdProducto);
										lineaAlbaran.setIdProducto(Long.parseLong(sIdProducto));

										String sIdDireccion = getTagValue("idDireccion", el);
										// System.out.println("idDireccion : " + sIdDireccion);
										lineaAlbaran.setIdDireccion(Long.parseLong(sIdProducto));

										String sRegistro = getTagValue("registro", el);
										// System.out.println("registro : " + sRegistro);
										//lineaAlbaran.setRegistro(sRegistro);
										
										String sCantidad = getTagValue("cantidad", el);
										// System.out.println("cantidad : " + sCantidad);
										lineaAlbaran.setCantidadUnitaria(Double.parseDouble(sCantidad));
										
										/*String sKilos = getTagValue("kilos", el);
										// System.out.println("kilos : " + sKilos);
										lineaAlbaran.setKilos(Double.parseDouble(sKilos));*/
									
										String sPeso = getTagValue("peso", el);
										// System.out.println("peso : " + sPeso);
										double peso = Double.parseDouble(sPeso);
										
										String sPrecioKilo = getTagValue("precioKilo", el);
										double precioKilo = Double.parseDouble(sPrecioKilo);
										double precioTotal = Double.parseDouble(sCantidad) * peso * precioKilo;
										
										double precioUnitario = precioTotal / Double.parseDouble(sCantidad);
										lineaAlbaran.setPrecioUnitario(precioUnitario);
										lineaAlbaran.setPrecioTotal(precioTotal);
										// System.out.println("precioKilo : " + precioKilo);
										// System.out.println("precioUnitario : " + precioUnitario);
										// System.out.println("precioTotal : " + precioTotal);

										importeTotalAlbaran += precioTotal;
										
										Bulto bulto = new Bulto();
										//Creamos el array de lotes
										Vector<Lote> lotes = new Vector<Lote>();
										Lote loteLeido = new Lote();
										loteLeido.setLote(sRegistro);
										loteLeido.setCantidad(Double.parseDouble(sCantidad));
										lotes.add(loteLeido);
										bulto.setLotes(lotes);
										bulto.setDireccionEntrega(Long.parseLong(albaran.getDestino()));
										bultos.add(bulto);
										lineaAlbaran.setBultos(bultos);
										lineaAlbaran.setNumeroBultos((long)bultos.size());
										lineaAlbaran.setAlbaran(albaran);
									}
								}
							}
						}
					}
					//C�digo del pedido
					String codigoAlbaran = new GestionRegistroSalidaHelper().getCodigoAlbaran();
					albaran.setCodigoAlbaran(codigoAlbaran);
					//Importe total del pedido
					albaran.setImporteTotal(importeTotalAlbaran);
					//Salvamos el pedido
					new GestionRegistroSalidaHelper().addAlbaran(albaran);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Override
	public void importarPedidos(String MY_XML) throws Exception{
		//Importamos pedidos
		// System.out.println("Vamos a importar los pedidos");
		try {
			File fXmlFile = new File(MY_XML);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			// System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("pedido");
			// System.out.println("-----------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList hijos = eElement.getChildNodes();
					//Tenemos un nuevo pedido
					Order pedido = new Order();
					double importeTotalPedido = 0;
					int lineasPedido = 0;
					int cuantosHijos = hijos.getLength();
					for (int i = 0; i < cuantosHijos; i++){
						//Un hijo ser� datosGenerales y el otro ser� detalles pedido
						Element e = (Element) hijos.item(i);
						String nombreNodo = e.getNodeName();
						if (nombreNodo.compareToIgnoreCase("datosGenerales") == 0){
							
							String sIdCliente = getTagValue("idCliente", e);
							pedido.setNadBy(sIdCliente);
							pedido.setNadMs(sIdCliente);
							// System.out.println("idCliente : " + sIdCliente);
							
							pedido.setCux("EUR");
							
							String sIdFormaPago = getTagValue("idFormaPago", e);
							pedido.setIdFormaPago(Long.parseLong(sIdFormaPago));
							// System.out.println("idFormaPago : " + sIdFormaPago);
							String sFecha = getTagValue("fecha", e);
							//La fecha viene de la forma 15/5/2012
							//Insertamos las fechas para el pedido
							String fechaFormateada = formatearFecha(sFecha);
							DTM fechaRegistro = new DTM(new Long(0), fechaFormateada, "102", "137");
							DTM fechaPedido = new DTM(new Long(0), fechaFormateada, "102", "2");
							pedido.setDtm(fechaRegistro);
							pedido.setDtm(fechaPedido);
							// System.out.println("fecha : " + sFecha);
							String sObservaciones = getTagValue("observaciones", e);
							pedido.setObservacionesPedido(sObservaciones);
							// System.out.println("observaciones : " + sObservaciones);

							String sResponsable = getTagValue("responsable", e);
							pedido.setNombreResponsable(sResponsable);
							// System.out.println("sResponsable : " + sResponsable);
						}else{
							if (nombreNodo.compareToIgnoreCase("detallesPedido") == 0){
								NodeList hijos2 = e.getChildNodes();
								int cuantosHijos2 = hijos2.getLength();
								for (int j = 0; j < cuantosHijos2; j++){
									//Tendr� j hijos lineaPedido
									Element el = (Element) hijos2.item(j);
									String nombreNodo2 = el.getNodeName();
									if (nombreNodo2.compareToIgnoreCase("lineapedido") == 0){
										//A�adir una nueva linea al pedido
										OrdersLin lineaPedido = new OrdersLin();
										lineasPedido++;
										int linNum = j + 1;
										lineaPedido.setLinNum(String.valueOf(linNum));
										
										String sIdProducto = getTagValue("idProducto", el);
										// System.out.println("idProducto : " + sIdProducto);
										lineaPedido.setIdLin(sIdProducto);
										lineaPedido.setIdProducto(Long.parseLong(sIdProducto));
										lineaPedido.setPiaNumSa(sIdProducto);
										
										String sCantidad = getTagValue("cantidad", el);
										// System.out.println("cantidad : " + sCantidad);
										lineaPedido.setQty21Cant(sCantidad);
										lineaPedido.setQty59Cant(sCantidad);
										
										String sKilos = getTagValue("kilos", el);
										// System.out.println("kilos : " + sKilos);
										//lineaPedido.setKilos(Double.parseDouble(sKilos));
									
										String sPeso = getTagValue("peso", el);
										// System.out.println("peso : " + sPeso);
										double peso = Double.parseDouble(sPeso);
										
										String sPrecioKilo = getTagValue("precioKilo", el);
										double precioKilo = Double.parseDouble(sPrecioKilo);
										double precioTotal = Double.parseDouble(sCantidad) * peso * precioKilo;
										
										double precioUnitario = precioTotal / Double.parseDouble(sCantidad);
										lineaPedido.setPriAaa(String.valueOf(precioUnitario));
										lineaPedido.setMoa203(String.valueOf(precioTotal));
										// System.out.println("precioKilo : " + precioKilo);
										// System.out.println("precioUnitario : " + precioUnitario);
										// System.out.println("precioTotal : " + precioTotal);
										
										importeTotalPedido += precioTotal;
										
										//Descripcion de la linea: IMD
										IMD imd = new IMD();
										con = bddConexion();
										String Qry =
											" SELECT codigoEan, nombre " +
											" FROM producto " +
											" WHERE idProducto = " + lineaPedido.getIdProducto();			
										// System.out.println(Qry);
										ps = con.prepareStatement(Qry);
										rs = ps.executeQuery();
										if (rs.next()){
											if (rs.getString("codigoEan") != null && !rs.getString("codigoEan").equals(""))
												imd.setIdLinea(Long.parseLong(rs.getString("codigoEan")));
											else
												imd.setIdLinea((long)0);
											imd.setImdDesc(rs.getString("nombre"));
										}
										ps.close();
										con.close();
										imd.setIdProducto(lineaPedido.getIdProducto());
										imd.setImdTipo("F");
										imd.setImdCara("DSC");
										
										//Pero la linea pedido tiene otro hijo, donde almacenamos las direcciones
										NodeList hijosDirecciones = el.getChildNodes();
										int cuantasDirecciones = hijosDirecciones.getLength();
										for (int k = 0; k < cuantasDirecciones; k++){
											//Tendr� k hijos direccionPedido											
											Element ele = (Element) hijosDirecciones.item(k);
											String nombreNodoDireccion = ele.getNodeName();
											if (nombreNodoDireccion.compareToIgnoreCase("direccionPedido") == 0){
												OrdersLoc direccion = new OrdersLoc();
												int idLin = j + 1;
												direccion.setIdLin(String.valueOf(idLin));
												
												String sIdDireccion = getTagValue("idDireccion", ele);
												// System.out.println("idDireccion : " + sIdDireccion);
												direccion.setLoc(sIdDireccion);
												
												String sCantidadDireccion = getTagValue("cantidad", ele);
												//sCantidadDireccion es el n�mero de bultos que van a esta direccion
												//hay que dividir la cantidad total de la linea entre la cantidad para la direccion
												double agrupacionesDireccion =
													Double.parseDouble(lineaPedido.getQty21Cant()) / Double.parseDouble(sCantidadDireccion);
												// System.out.println("cantidad : " + agrupacionesDireccion);
												direccion.setQty(String.valueOf(agrupacionesDireccion));
												
												lineaPedido.setLoc(direccion);
											}
										}
										lineaPedido.setImd(imd);
										pedido.setLin(lineaPedido);
									}
								}
							}
						}
					}
					//Tipo de pedido: Pedido
					pedido.setBgmTipo("220");
					//Funcion del mensaje: Propuesta
					pedido.setBgmFunc("16");
					//Usuario responsable
					pedido.setNadMr("8");//Usuario autoventa
					//C�digo del pedido
					long numPedido = new EdifactParserDataHelper().getProximoId();
					String cadenaPedido = "P" + numPedido;
					pedido.setBgmNum(cadenaPedido);
					//Importe total del pedido
					pedido.setMoa79(String.valueOf(importeTotalPedido));
					//Lineas totales del pedido
					pedido.setCnt(String.valueOf(lineasPedido));
					//Salvamos el pedido
					new EdifactParserDataHelper().orderParse(pedido);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String formatearFecha(String fecha){
		//15/5/2012
		String formateada = "";
		String frags[] = fecha.split("/");
		String anio = frags[2], mes = frags[1], dia = frags[0];
		if (mes.length() == 1)
			mes = "0" + mes;
		if (dia.length() == 1)
			dia = "0" + dia;
		formateada = anio + mes + dia;
		return formateada;
	}
	
	//@Override
	public void importarClientes(String MY_XML) throws Exception{
		//Importamos clientes
		// System.out.println("Vamos a importar los clientes");
		try {
			File fXmlFile = new File(MY_XML);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			// System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("cliente");
			// System.out.println("-----------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList hijos = eElement.getChildNodes();
					//Tenemos un nuevo pedido
					Entidad entidad = new Entidad();
					
					Vector<Direccion> direcciones = new Vector<Direccion>();
					Vector<Telefono> telefonos = new Vector<Telefono>();
					Vector<Email> emails = new Vector<Email>();
					Vector<DatoBancario> formasDePago = new Vector<DatoBancario>();
					Vector<Rol> rolesEntidad = new Vector<Rol>();
					
					/*Rol rol = new Rol();
					rol.setIdRol((long)1);
					rolesEntidad.add(rol);
					entidad.setRoles(rolesEntidad);*/
					
					int cuantosHijos = hijos.getLength();
					for (int i = 0; i < cuantosHijos; i++){
						//Un hijo ser� datosGenerales y el otro ser� detalles pedido
						Element e = (Element) hijos.item(i);
						String nombreNodo = e.getNodeName();
						if (nombreNodo.compareToIgnoreCase("datosGenerales") == 0){
							
							String sIdCliente = getTagValue("id", e);
							entidad.setIdUsuario(Long.parseLong(sIdCliente));
							// System.out.println("idCliente : " + sIdCliente);
							
							String sNombre = getTagValue("nombre", e);
							entidad.setNombre(sNombre);
							// System.out.println("sNombre : " + sNombre);
							String sApellidos = getTagValue("apellidos", e);
							entidad.setApellidos(sApellidos);
							// System.out.println("sApellidos : " + sApellidos);

							String sNif = getTagValue("nif", e);
							entidad.setNif(sNif);
							// System.out.println("sNif : " + sNif);
							String sEan = getTagValue("ean", e);
							entidad.setEan(sEan);
							// System.out.println("sEan : " + sEan);
							String sWeb = getTagValue("web", e);
							entidad.setWeb(sWeb);
							// System.out.println("sWeb : " + sWeb);
							String sTipoPersona = getTagValue("tipoPersona", e);
							entidad.setAutonomoEmpresa(sTipoPersona.charAt(0));
							// System.out.println("sTipoPersona : " + sTipoPersona);
							String sEstado = getTagValue("estado", e);
							entidad.setEstado(sEstado);
							// System.out.println("sEstado : " + sEstado);
							
							String sLimiteCredito = getTagValue("limiteCredito", e);
							entidad.setLimiteCredito(Double.parseDouble(sLimiteCredito));
							// System.out.println("sLimiteCredito : " + sLimiteCredito);
							String sRecargoEquivalencia = getTagValue("recargoEquivalencia", e);
							entidad.setRecargoEquivalencia(Double.parseDouble(sRecargoEquivalencia));
							// System.out.println("sRecargoEquivalencia : " + sRecargoEquivalencia);
							String sDsctoMercancia = getTagValue("dsctoMercancia", e);
							entidad.setDsctoMercancia(Double.parseDouble(sDsctoMercancia));
							// System.out.println("sDsctoMercancia : " + sDsctoMercancia);
							String sDsctoValor = getTagValue("dsctoValor", e);
							entidad.setDsctoValor(Double.parseDouble(sDsctoValor));
							// System.out.println("sDsctoValor : " + sDsctoValor);
						}else{
							if (nombreNodo.compareToIgnoreCase("direcciones") == 0){
								NodeList hijos2 = e.getChildNodes();
								int cuantosHijos2 = hijos2.getLength();
								for (int j = 0; j < cuantosHijos2; j++){
									//Tendr� j hijos lineaPedido
									Element el = (Element) hijos2.item(j);
									String nombreNodo2 = el.getNodeName();
									if (nombreNodo2.compareToIgnoreCase("direccion") == 0){
										Direccion direccion = new Direccion();
										String sIdDireccion = getTagValue("idDireccion", el);
										// System.out.println("sIdDireccion : " + sIdDireccion);
										direccion.setIdDireccion(Long.parseLong(sIdDireccion));
										String sNombreCalle = getTagValue("nombreCalle", el);
										// System.out.println("sNombreCalle : " + sNombreCalle);
										direccion.setNombreCalle(sNombreCalle);
										direccion.setTipoDireccion("A");
										direccion.setIdProvincia((long) 1);
										direcciones.add(direccion);
									}
								}
							}else{
								if (nombreNodo.compareToIgnoreCase("emails") == 0){
									NodeList hijos2 = e.getChildNodes();
									int cuantosHijos2 = hijos2.getLength();
									for (int j = 0; j < cuantosHijos2; j++){
										//Tendr� j hijos lineaPedido
										Element el = (Element) hijos2.item(j);
										String nombreNodo2 = el.getNodeName();
										if (nombreNodo2.compareToIgnoreCase("email") == 0){
											Email email = new Email();
											String sIdEmail = getTagValue("idEmail", el);
											// System.out.println("sIdEmail : " + sIdEmail);
											email.setIdEmail(Long.parseLong(sIdEmail));
											String sEmail = getTagValue("direccion", el);
											// System.out.println("sEmail : " + sEmail);
											email.setDireccion(sEmail);
											emails.add(email);
										}
									}
								}else{
									if (nombreNodo.compareToIgnoreCase("formasPago") == 0){
										NodeList hijos2 = e.getChildNodes();
										int cuantosHijos2 = hijos2.getLength();
										for (int j = 0; j < cuantosHijos2; j++){
											//Tendr� j hijos lineaPedido
											Element el = (Element) hijos2.item(j);
											String nombreNodo2 = el.getNodeName();
											if (nombreNodo2.compareToIgnoreCase("formaDePago") == 0){
												/*DatoBancario dato = new DatoBancario();
												
												String sIdDatoBancario = getTagValue("idDatoBancario", el);
												// System.out.println("sIdDatoBancario : " + sIdDatoBancario);
												dato.setIdDatoBancario(Long.parseLong(sIdDatoBancario));
												String sIdFormaPago = getTagValue("idFormaPago", el);
												// System.out.println("idFormaPago : " + sIdFormaPago);
												dato.setIdFormaPago(Long.parseLong(sIdFormaPago));
												String sDiasFormaPago = getTagValue("diasFormaPago", el);
												// System.out.println("sDiasFormaPago : " + sDiasFormaPago);
												dato.setDiasFormaPago(Long.parseLong(sDiasFormaPago));
												String sDiaPago = getTagValue("diaPago", el);
												// System.out.println("sDiaPago : " + sDiaPago);
												dato.setDiaPago(Long.parseLong(sDiaPago));
												String sIdBanco = getTagValue("idBanco", el);
												// System.out.println("sIdBanco : " + sIdBanco);
												dato.setIdBanco(Long.parseLong(sIdBanco));
												String sIdFormaPagoDesde = getTagValue("idFormaPagoDesde", el);
												// System.out.println("sIdFormaPagoDesde : " + sIdFormaPagoDesde);
												dato.setIdFormaPagoDesde(Long.parseLong(sIdFormaPagoDesde));
												String sNumCuenta = getTagValue("numCuenta", el);
												// System.out.println("sNumCuenta : " + sNumCuenta);
												dato.setNumCuenta(sNumCuenta);
												
												formasDePago.add(dato);
												*/
											}
										}
									}else{
										if (nombreNodo.compareToIgnoreCase("roles") == 0){
											NodeList hijos2 = e.getChildNodes();
											int cuantosHijos2 = hijos2.getLength();
											for (int j = 0; j < cuantosHijos2; j++){
												//Tendr� j hijos lineaPedido
												Element el = (Element) hijos2.item(j);
												String nombreNodo2 = el.getNodeName();
												if (nombreNodo2.compareToIgnoreCase("rol") == 0){
													Rol rol = new Rol();
													
													String sIdRol = getTagValue("idRol", el);
													// System.out.println("sIdDatoBancario : " + sIdRol);
													rol.setIdRol(Long.parseLong(sIdRol));
													String sIdTipoSector = getTagValue("idTipoSector", el);
													// System.out.println("sIdTipoSector : " + sIdTipoSector);
													entidad.setIdSectorCliente(Long.parseLong(sIdTipoSector));
													
													rolesEntidad.add(rol);
												}
											}
										}else{
											if (nombreNodo.compareToIgnoreCase("telefonos") == 0){
												NodeList hijos2 = e.getChildNodes();
												int cuantosHijos2 = hijos2.getLength();
												for (int j = 0; j < cuantosHijos2; j++){
													//Tendr� j hijos lineaPedido
													Element el = (Element) hijos2.item(j);
													String nombreNodo2 = el.getNodeName();
													if (nombreNodo2.compareToIgnoreCase("telefono") == 0){
														Telefono tfno = new Telefono();
														String sIdTfno = getTagValue("idTelefono", el);
														// System.out.println("sIdTfno : " + sIdTfno);
														tfno.setIdTelefono(Long.parseLong(sIdTfno));
														String sNumeroTelefono = getTagValue("numeroTelefono", el);
														// System.out.println("sNumeroTelefono : " + sNumeroTelefono);
														tfno.setNumero(sNumeroTelefono);
														telefonos.add(tfno);
													}
												}
											}
										}
									}
								}
							}
						}
					}
					entidad.setDirecciones(direcciones);
					entidad.setTelefonos(telefonos);
					entidad.setEmails(emails);
					entidad.setFormasPagoEntidad(formasDePago);
					entidad.setRolesEntidad(rolesEntidad);
					entidad.setExportableAutoventa("S");
					//Estados: O: Opilion; C: Creado; M: Modificado; E: Eliminado
					if (entidad.getEstado().equals("C")){
						//Salvamos el cliente
						new GestionEntidadesHelper().addEntidad(entidad);
					}else{
						if (entidad.getEstado().equals("M")){
							//Salvamos el cliente
							new GestionEntidadesHelper().updateEntidad(entidad, entidad);
						}else{
							if (entidad.getEstado().equals("B")){
								//Salvamos el cliente
								new GestionEntidadesHelper().deshabilitaEntidad(entidad);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Override
	/** Exporta datos de la base de datos a un fichero csv
	 * @since 1.1 (22/03/2012)
	 */
	public void exportarDatos(long idVehiculo, long idComercial) throws Exception{
		try{
			con = bddConexion();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			String path = loader.getResource("configuracion.xml").toURI().getPath();
			Document doc = dBuilder.parse(new File(path));
			doc.getDocumentElement().normalize();
			NodeList listaNodosHijos = doc.getElementsByTagName("baseDatos");
			Node configBD = listaNodosHijos.item(0);
			Element elemento = (Element) configBD;
			String rutaFicherosCSV = getTagValue("rutaFicherosCSV", elemento);
			//0. Exportar albaranes
			File fichero = new File(rutaFicherosCSV + "/albaran.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/albaran_detalle.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/albaran_lotes.csv");
			if (fichero != null)
				fichero.delete();
			String consulta =
				" SELECT DISTINCT a.idAlbaran, a.idCliente, a.fecha, a.fechaEntrega, a.fechaVencimiento, a.destino AS idDireccion, " +
					" a.codigoAlbaran, a.pesoNetoTotal, a.numeroBultosTotal, a.cantidadTotal, a.importeTotal, a.estado, " +
					" a.idFormaPago, a.descripcionIvaAplicable, a.idTelefono, a.observaciones, a.horarioEntrega " +
				" FROM albaran a, entidad e " +
				" WHERE a.idCliente = e.idUsuario AND e.idComercial = " + idComercial +
				" INTO OUTFILE '" + rutaFicherosCSV + "/albaran.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			String subQuery =
				" SELECT DISTINCT a.codigoAlbaran " +
				" FROM albaran a, entidad e " +
				" WHERE a.idCliente = e.idUsuario AND e.idComercial = " + idComercial;
			consulta =
				" SELECT codigoAlbaran, codigoSalida, idProducto, cantidadUnitaria, precioUnitario, importe " +
				" FROM albaran_detalle ad " +
				" WHERE ad.codigoAlbaran IN (" + subQuery + ")" +
				" INTO OUTFILE '" + rutaFicherosCSV + "/albaran_detalle.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT DISTINCT ad.codigoAlbaran, ad.idProducto, bl.lote, bl.cantidad " +
				" FROM bulto b, bulto_lotes bl, albaran_detalle ad " +
				" WHERE b.idBulto = bl.idBulto " +
					" AND ad.codigoSalida = b.codigoSalida " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/albaran_lotes.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			//1. Exportar productos
			//Empezamos por eliminar el fichero actual
			fichero = new File(rutaFicherosCSV + "/productos.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/productos_categorizacion.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/productos_compuesto.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/productos_vehiculo.csv");
			if (fichero != null)
				fichero.delete();
			consulta =
				" SELECT p.idProducto, p.codigoEan, p.nombre, p.descripcion, p.peso, p.stock, " +
					" p.stockAgrupado, p.precio, p.precioCoste, p.idGrupo, p.mostrar " +
				" FROM producto p " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/productos.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT * " +
				" FROM producto_categorizacion pc " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/productos_categorizacion.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT * " +
				" FROM producto_compuesto pc " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/productos_compuesto.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT DISTINCT p.idProducto, p.codigoEan, p.nombre, p.descripcion, p.peso, u.cantidad AS stock, " +
					" p.stockAgrupado, p.precio, p.precioCoste, u.registro, re.fechaCaducidad " +
				" FROM producto p, registroentrada re, ubicacion u, ubicacion_hueco uh, " +
					" ubicacion_piso up, ubicacion_estanteria ue, ubicacion_linea ul, " +
					" ubicacion_zona uz, ubicacion_almacen ua " +
				" WHERE u.registro = re.codigoEntrada " +
					" AND re.idProducto = p.idProducto " +
					" AND ua.idAlmacen = " + idVehiculo +
					" AND uz.idAlmacen = ua.idAlmacen " +
					" AND ul.idZona = uz.idZona " +
					" AND ue.idLinea = ul.idLinea " +
					" AND up.idEstanteria = ue.idEstanteria " +
					" AND uh.idPiso = up.idPiso " +
					" AND u.idHueco = uh.idHueco " +
			" UNION ALL " +
				" SELECT DISTINCT p.idProducto, p.codigoEan, p.nombre, p.descripcion, p.peso,u.cantidad AS stock, " +
					" p.stockAgrupado, p.precio, p.precioCoste, u.registro, gp.fechaCaducidad " +				
				" FROM producto p, gp_envasado gp, ubicacion u, ubicacion_hueco uh, " +
					" ubicacion_piso up, ubicacion_estanteria ue, ubicacion_linea ul, " +
					" ubicacion_zona uz, ubicacion_almacen ua " +
				" WHERE u.registro = gp.lote " +
					" AND gp.idProducto = p.idProducto " +
					" AND ua.idAlmacen = " + idVehiculo +
					" AND uz.idAlmacen = ua.idAlmacen " +
					" AND ul.idZona = uz.idZona " +
					" AND ue.idLinea = ul.idLinea " +
					" AND up.idEstanteria = ue.idEstanteria " +
					" AND uh.idPiso = up.idPiso " +
					" AND u.idHueco = uh.idHueco " +
			" INTO OUTFILE '" + rutaFicherosCSV + "/productos_vehiculo.csv' " +
			" FIELDS TERMINATED BY ',' " +
			" ENCLOSED BY " + '"' + "'" + '"' + " " +
			" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			//FIN. Productos exportados
			//1.5 Exportar movimientos entre almacenes
			fichero = new File(rutaFicherosCSV + "/productos_movimientos.csv");
			if (fichero != null)
				fichero.delete();
			consulta =
				" SELECT um.* " +
				" FROM ubicacion_movimiento um, ubicacion_hueco uh, " +
					" ubicacion_piso up, ubicacion_estanteria ue, ubicacion_linea ul, " +
					" ubicacion_zona uz, ubicacion_almacen ua " +
				" WHERE um.idHueco = uh.idHueco " +
					" AND ua.idAlmacen = " + idVehiculo +
					" AND uz.idAlmacen = ua.idAlmacen " +
					" AND ul.idZona = uz.idZona " +
					" AND ue.idLinea = ul.idLinea " +
					" AND up.idEstanteria = ue.idEstanteria " +
					" AND uh.idPiso = up.idPiso " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/productos_movimientos.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			//2. Exportar entidades
			//Empezamos por eliminar los ficheros actuales
			fichero = new File(rutaFicherosCSV + "/entidades.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_direccion.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_email.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_telefono.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_rol.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_referenciabancaria.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_formapago.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_sectores.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_formapagodesde.csv");
			if (fichero != null)
				fichero.delete();
			fichero = new File(rutaFicherosCSV + "/entidades_banco.csv");
			if (fichero != null)
				fichero.delete();
			consulta =
				" SELECT e.idUsuario, e.nombre, e.apellidos, " +
					" e.nif, e.ean, e.web, e.dsctoMercancia, e.dsctoValor, e.tipoPersona, " +
					" e.limiteCredito, e.recargoEquivalencia " +
				" FROM entidad e, entidad_rol rol " +
				" WHERE rol.idRol = 1 AND rol.idEntidad = e.idUsuario AND e.habilitado = 'S' AND e.exportableAutoventa = 'S' " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			//Almacenamos los id de los clientes que exportamos, para exportar solo las direcciones, referencias bancarias, telefonos y emails de esos clientes
			String cadenaIN = "";
			consulta =
				" SELECT e.idUsuario " +
				" FROM entidad e, entidad_rol rol " +
				" WHERE rol.idRol = 1 AND rol.idEntidad = e.idUsuario AND e.habilitado = 'S' AND e.exportableAutoventa = 'S'; ";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			boolean primero = true;
			while (rs.next()){
				if (!primero){
					cadenaIN += ",";
				}
				cadenaIN += rs.getLong("idUsuario") + "";
				if (primero)
					primero = false;
			}
			consulta =
				" SELECT d.idDireccion, d.empresa_idUsuario, d.nombreCalle, d.localidad, p.nombre " +
				" FROM direccion d, provincia p WHERE p.idProvincia = d.idProvincia AND d.empresa_idUsuario IN (" + cadenaIN + ")" +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_direccion.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT e.idEmail, e.idUsuario, e.direccion " +
				" FROM email e WHERE e.idUsuario IN (" + cadenaIN + ") " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_email.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT er.idEntidad, er.idRol, er.idTipoSector " +
				" FROM entidad_rol er WHERE er.idEntidad IN (" + cadenaIN + ") " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_rol.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT t.idTelefono, t.idUsuario, t.numeroTelefono " +
				" FROM telefono t WHERE t.idUsuario IN (" + cadenaIN + ") " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_telefono.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT r.idDatoBancario, r.idFormaPago, r.empresa_idUsuario, r.numCuenta, " +
					" r.diasFormaPago, r.diaPago, r.idBanco, r.idFormaPagoDesde " +
				" FROM referenciabancaria r WHERE r.empresa_idUsuario IN (" + cadenaIN + ") " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_referenciabancaria.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT f.idFormaPago, f.descripcion " +
				" FROM formapago f " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_formapago.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT * " +
				" FROM formapago_desde f " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_formapagodesde.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT * " +
				" FROM banco b " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_banco.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			consulta =
				" SELECT s.idSector, s.nombre " +
				" FROM sector s " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/entidades_sectores.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			//FIN. Entidades exportadas
			//Exportamos los usuarios de autoventa
			fichero = new File(rutaFicherosCSV + "/usuarios.csv");
			if (fichero != null)
				fichero.delete();
			consulta =
				" SELECT u.idUsuario, u.login, u.password " +
				" FROM usuario u, usuario_rol rol " +
				" WHERE rol.idRol = 5 AND rol.idUsuario = u.idUsuario AND u.habilitado = 'S' " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/usuarios.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			//FIN. Usuarios exportados
			//Exportamos las incidencias
			fichero = new File(rutaFicherosCSV + "/incidencias.csv");
			if (fichero != null)
				fichero.delete();
			consulta =
				" SELECT * " +
				" FROM estadofabas e " +
				" INTO OUTFILE '" + rutaFicherosCSV + "/incidencias.csv' " +
				" FIELDS TERMINATED BY ',' " +
				" ENCLOSED BY " + '"' + "'" + '"' + " " +
				" LINES TERMINATED BY '" + '\\' + "n" + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
		} catch (Exception ex){
            ex.printStackTrace();
	    } finally {
	    	try {
				con.close();
	    	} catch (Exception e2) { e2.printStackTrace(); }
	    }
	}
	
	private static String getTagValue(String sTag, Element eElement){
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return "";
		return nValue.getNodeValue();
	}
	
	//@Override
	public String getRegistroSanitario(long idEmpresa) throws Exception{
		String registro = "";
		//Consultamos el registro sanitario
		try {
			con = bddConexion();
			String Qry =
				" SELECT e.registroSanitario " +
				" FROM empresa e " +
				" WHERE e.idEmpresa = " + idEmpresa;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (rs.next()){
				registro = rs.getString("registroSanitario");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return registro;
	}
}