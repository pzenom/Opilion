package es.induserco.opilion.datos.salida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.OrdersLoc;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.edifact.negocio.EdifactParserDataHelper;
import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.comun.Rol;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.banca.FormaPago;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.entidades.Albaran;
import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.Cargo;
import es.induserco.opilion.data.entidades.Comercial;
import es.induserco.opilion.data.entidades.CuotaFactura;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.EstadoPedido;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.data.entidades.ItemFactura;
import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.data.entidades.PortesTransporte;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroSalida;
import es.induserco.opilion.data.entidades.TemperaturaTransporte;
import es.induserco.opilion.data.entidades.TipoVehiculo;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.Vehiculo;
import es.induserco.opilion.infraestructura.DatabaseConfig;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

/**
 * Clase RegistroSalidaDAO
 *
 * @author Induserco, Andres (26/04/2011) 
 * @version 2.0 (16/02/2012)
 * @version 2.1 (23/05/2013) lanzadera de pedidos (y productos y entidades)
 */
public class RegistroSalidaDAO extends DatabaseConfig implements IRegistroSalidaDataService {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;
	private static Logger logger = Logger.getLogger(RegistroSalidaDAO.class);

	//@Override
	public String getCodigoAlbaran() throws Exception {
		// System.out.println("DAO getCodigoAlbaran");
		String codigoSalida = null;
		String stringFecha = null;
		try {
			con = bddConexion();
			// obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idAlbaran) as idMaxAlbaran FROM albaran");
			rs = ps.executeQuery();
			long idAlbaranMax = 0;
			if (rs.next()) {
				Albaran albaranMax = new Albaran();
				albaranMax.setIdAlbaran(rs.getLong("idMaxAlbaran"));
				idAlbaranMax = albaranMax.getIdAlbaran() + 1;
			} else
				idAlbaranMax = 1;
			// formatear fecha sistema para codigo salida
			ps = con
					.prepareStatement(" SELECT DATE_FORMAT(CURDATE(),'%Y%m%d') as fecha ");
			rs = ps.executeQuery();
			while (rs.next()) {
				stringFecha = rs.getString("fecha");
			}
			codigoSalida = "A" + stringFecha + "-" + idAlbaranMax;
			return codigoSalida;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}

	/**
	 * A partir del código EAN de un producto, obtenemos su descripción
	 *
	 * @author Induserco, Andrés (27/04/2011)
	 * @since 0.3
	 * @param EAN
	 *            Código EAN del producto
	 * @return Descripción del producto
	 * @throws Exception
	 */
	//@Override
	public String getInfoProducto(String EAN) throws Exception {
		// System.out.println("DAO getInfoProducto");
		String info = "";
		try {
			con = bddConexion();
			String query = "SELECT descripcion FROM producto p WHERE p.codigoEan = '"
					+ EAN + "'";
			// System.out.println(query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				info = (rs.getString("descripcion"));
			}
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Boolean addRegistroSalida(RegistroSalida exit) throws Exception {
		// System.out.println("DAO addRegistroSalida");
		Statement stmt;
		Boolean resultado = false;
		String codigoSalida = null;
		String stringFecha = null;
		int res = 0;
		try {
			con = bddConexion();
			// obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idRegistroSalida) AS idMaxRegistroSalida FROM albaran_detalle");
			rs = ps.executeQuery();
			long idRegistroSalidaMax = 0;
			if (rs.next()) {
				RegistroSalida salidaMaximo = new RegistroSalida();
				salidaMaximo.setIdRegistroSalida(rs.getLong("idMaxRegistroSalida"));
				idRegistroSalidaMax = salidaMaximo.getIdRegistroSalida() + 1;
			} else
				idRegistroSalidaMax = 1;
			// formatear fecha sistema para codigo salida
			ps = con.prepareStatement("SELECT DATE_FORMAT(CURDATE(),'%Y%m%d') as fecha ");
			rs = ps.executeQuery();
			while (rs.next()) {
				stringFecha = rs.getString("fecha");
			}
			codigoSalida = "S" + stringFecha + "-" + idRegistroSalidaMax;
			exit.setCodigoSalida(codigoSalida);
			// si no tiene numero de linea asociado igualo a 0
			if (exit.getNumLinea() == null)
				exit.setNumLinea(0L);
			NumberFormat nf = new DecimalFormat("0000000000000");
			/* String eanPro = nf.format(Long.parseLong(exit.getCodigoEan())); */
			long idProducto = exit.getIdProducto();
			// A partir de exit.getIdCliente() sacar nombreCliente, idCliente,
			// nifCliente, numeroTelefono
			// SQL de insersion
			String insertString = "INSERT INTO albaran_detalle (idRegistroSalida, linNum, idProducto, codigoAlbaran,codigoSalida,"
					+ "pesoNeto, numeroBultos, cantidadUnitaria, precioUnitario, importe, usuarioResponsable, descripcionPrecioKilo) " +
					" VALUES("
					+ idRegistroSalidaMax
					+ ","
					+ exit.getNumLinea()
					+ ",'"
					+ idProducto
					+ "','"
					+ exit.getAlbaran().getCodigoAlbaran()
					+ "','"
					+ codigoSalida
					+ "',"
					+ exit.getPesoNeto()
					+ ","
					+ exit.getNumeroBultos()
					+ ","
					+ exit.getCantidadUnitaria()
					+ ","
					+ exit.getPrecioUnitario()
					+ ","
					+ exit.getPrecioTotal()
					+ ",'"
					+ exit.getAlbaran().getUsuarioResponsable()
					+ "','"
					+ (exit.getPrecioUnitario() / exit.getCantidadUnitaria()) + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (res == 1) {
				// System.out.println("REGISTRO INSERTADO");
				resultado = true;
				// Ahora, insertar todos los bultos del albaran
				List<Bulto> bultos = exit.getBultos();
				for (int i = 0; i < bultos.size(); i++) {
					Bulto bulto = bultos.get(i);
					// Obtenemos el id del bulto
					String Qry = "SELECT MAX(idBulto) as idMaxBulto FROM bulto";
					// " AND loc.idLin = '" + linNum + "'" +
					// " AND dir.ean = loc.idLoc";
					 // System.out.println("Obtenemos el id que tendra el bulto: " + Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					int idBulto = 0;
					while (rs.next()) {
						idBulto = rs.getInt("idMaxBulto");
					}
					idBulto++;
					// Inserta el bulto
					long dire = bulto.getDireccionEntrega();
					insertString = "INSERT INTO bulto (idBulto, codigoSalida, direccionEntrega)"
							+ " VALUES("
							+ idBulto
							+ ", '"
							+ codigoSalida
							+ "','" + dire + "')";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(insertString);
					// Ahora, insertar los lotes en el bulto
					List<Lote> lotes = bulto.getLotes();
					nf = new DecimalFormat("00000000");
					for (int j = 0; j < lotes.size(); j++) {
						Lote l = lotes.get(j);
						String lote = l.getLote();
						// String ubicacion = l.getUbicacion();
						double cantidad = l.getCantidad();
						// long idHueco = new
						// GestionUbicacionesHelper().getIdHueco(ubicacion);
						long idHueco = 223;// OJO!! Solo vendemos productos en la zona de salida
						String out = "";
						if (lote.charAt(0) == 'E') {
							out = lote;
						} else {
							out = nf.format(Long.parseLong(lote));
						}
						insertString = "INSERT INTO bulto_lotes (idBulto, lote, idHueco, cantidad)"
								+ " VALUES("
								+ idBulto
								+ ",'"
								+ out
								+ "','"
								+ idHueco + "','" + cantidad + "')";
						// System.out.println(insertString);
						stmt = con.createStatement(
								ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						res = stmt.executeUpdate(insertString);
						// Restar tambien stock del producto final
						double sacar = cantidad;
						// Diferenciamos si es un lote envasado por nosotros...
						if (lote.charAt(0) == '0') {// Restamos de la tabla
													// producto...
							String consulta = " SELECT p.idProducto "
									+ " FROM producto p, gp_envasado gp "
									+ " WHERE gp.lote = '" + lote + "' "
									+ " AND gp.idProducto = p.idProducto";
							// System.out.println(consulta);
							ps = con.prepareStatement(consulta);
							rs = ps.executeQuery();
							long idProd = 0;
							while (rs.next()) {
								idProd = rs.getLong("idProducto");
							}
							// Al envasar el idProd en el envasado con lote
							// 'lote', se agrupó?
							consulta = " SELECT DISTINCT gpagrupa.idAgrupacion "
									+ " FROM gp_envasado gpe, gp_envasado_detalle gpdeta, gp_envasado_agrupacion gpagrupa "
									+ " WHERE gpe.orden = gpdeta.orden "
									+ " AND gpagrupa.ordenEnvasado = gpe.orden "
									+ " AND gpe.lote = '" + lote + "'";
							// System.out.println(consulta);
							ps = con.prepareStatement(consulta);
							rs = ps.executeQuery();
							long idAgrupacion = 0;
							while (rs.next()) {
								idAgrupacion = rs.getLong("idAgrupacion");
							}
							if (idAgrupacion > 0) {
								// Al generar el lote, se agrupo en EANS14
								consulta =
									" SELECT pc.idCompuestoDe, pc.cantidad "
									+ " FROM producto p, producto_compuesto pc "
									+ " WHERE p.idProducto = pc.idProducto "
										+ " AND p.idProducto = " + idAgrupacion;
								// System.out.println(consulta);
								ps = con.prepareStatement(consulta);
								rs = ps.executeQuery();
								long idCompuestoDe = 0;
								double cantidadCompuesto = 0;
								Vector<Producto> productos = new Vector<Producto>();
								while (rs.next()) {
									Producto p = new Producto();
									idCompuestoDe = rs.getLong("idCompuestoDe");
									p.setIdProducto(idCompuestoDe);
									cantidadCompuesto = rs.getDouble("cantidad");
									p.setCantidad(cantidadCompuesto);
									productos.add(p);
								}
								String insertSql = " UPDATE producto SET stock = stock - "
										+ cantidad
										+ " WHERE producto.idProducto = "
										+ idAgrupacion;
								// System.out.println(insertSql);
								stmt = con.createStatement(
										ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
								res = stmt.executeUpdate(insertSql);
								for (int k = 0; k < productos.size(); k++) {
									Producto p = productos.get(k);
									sacar = cantidad * p.getCantidad();
									insertSql = " UPDATE producto SET stockAgrupado = stockAgrupado - "
											+ sacar
											+ " WHERE producto.idProducto = "
											+ p.getIdProducto();
									// System.out.println(insertSql);
									stmt = con.createStatement(
											ResultSet.TYPE_SCROLL_SENSITIVE,
											ResultSet.CONCUR_UPDATABLE);
									res = stmt.executeUpdate(insertSql);
								}
							} else {
								// Se trata de un EAN13
								String insertSql = "UPDATE producto SET stock = stock - "
										+ cantidad
										+ " WHERE producto.idProducto = "
										+ idProd;
								// System.out.println(insertSql);
								stmt = con.createStatement(
										ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
								res = stmt.executeUpdate(insertSql);
							}
						} else {
							if (lote.charAt(0) == 'E') {// Se trata de un
														// producto final como
														// registro de entrada
								// Restamos de la tabla producto...
								// y Restar saldo del registro de entrada
								// A. Necesitamos el id del producto
								String consulta = "SELECT r.idProducto, p.stock "
										+ " FROM registroentrada r, producto p "
										+ " WHERE r.codigoEntrada = '"
										+ lote
										+ "' "
										+ " AND p.idProducto = r.idProducto";
								// System.out.println(consulta);
								ps = con.prepareStatement(consulta);
								rs = ps.executeQuery();
								long idProd = 0;
								while (rs.next()) {
									idProd = rs.getLong("idProducto");
								}
								String insertSql = "UPDATE producto SET stock = stock - "
										+ cantidad
										+ " WHERE producto.idProducto = "
										+ idProd;
								// System.out.println(insertSql);
								stmt = con.createStatement(
										ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
								res = stmt.executeUpdate(insertSql);
								insertSql = "UPDATE registroentrada SET saldo = saldo - "
										+ cantidad
										+ " WHERE codigoEntrada = '"
										+ lote + "'";
								// System.out.println(insertSql);
								stmt = con.createStatement(
										ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
								res = stmt.executeUpdate(insertSql);
							}
						}
						// Restar stock del lote en el hueco
						Ubicacion u = new Ubicacion();
						Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
						u.setIdHueco(idHueco);
						long idUbicacion = new GestionUbicacionesHelper().getIdUbicacion(idHueco, lote);
						u.setIdUbicacion(idUbicacion);
						u.setCantidad(cantidad);
						u.setRegistro(lote);
						ubicaciones.add(u);
						new GestionUbicacionesHelper().sacarUbicaciones(ubicaciones, false, false, true);
					}
				}
			}
			String insertamos = "SELECT MAX(idRegistroSalida) as idMaxRegistroSalida FROM registrosalida";
			// System.out.println("Obtenemos el id que tendra el registro de salida: " + insertamos);
			ps = con.prepareStatement(insertamos);
			rs = ps.executeQuery();
			int idRegistroSalida = 0;
			while (rs.next()) {
				idRegistroSalida = rs.getInt("idMaxRegistroSalida");
			}
			idRegistroSalida++;
			insertString =
				" INSERT INTO registrosalida (idRegistroSalida, idAlbaran, idCliente, idProducto,"
					+ "codigoSalida, fecha, pesoNeto, numeroBultos,"
					+ "cantidad, cantidadUnitaria,precioUnitario,habilitado)"
				+ " VALUES("
					+ idRegistroSalida
					+ ", '"
					+ exit.getAlbaran().getIdAlbaran()
					+ "','"
					+ exit.getIdCliente()
					+ "','"
					+ exit.getIdProducto()
					+ "','"
					+ exit.getCodigoSalida()
					+ "',CURRENT_TIMESTAMP,'"
					+ exit.getPesoNeto()
					+ "','"
					+ exit.getNumeroBultos()
					+ "','"
					+ exit.getAlbaran().getCantidadTotal()
					+ "','"
					+ exit.getCantidadUnitaria()
					+ "','"
					+ exit.getPrecioUnitario() + "','S')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}

	/**
	 * Inserta un Albarán Simple o a partir de una Orden de EDIFACT.
	 *
	 * @param albaran
	 *            the albaran
	 * @return the boolean
	 * @throws Exception
	 *             the exception
	 */
	//@Override
	public Boolean addAlbaran(Albaran albaran) throws Exception {
		// System.out.println("DAO addAlbaran");
		Statement stmt;
		Boolean resultado = false;
		Date fecha = null;
		int res = 0;
		try {
			con = bddConexion();
			// obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idAlbaran) as idMaxAlbaran FROM albaran");
			rs = ps.executeQuery();
			long idMaxAlbaran = 0;
			if (rs.next()) {
				Albaran albaranMax = new Albaran();
				albaranMax.setIdAlbaran(rs.getLong("idMaxAlbaran"));
				idMaxAlbaran = albaranMax.getIdAlbaran() + 1;
			} else
				idMaxAlbaran = 1;
			// obtener fecha sistema
			ps = con.prepareStatement(" SELECT DATE_FORMAT(CURDATE(),'%Y-%m-%d') AS fecha ");
			rs = ps.executeQuery();
			while (rs.next()) {
				fecha = rs.getDate("fecha");
			}
			String cliente = albaran.getCliente().getIdUsuario().toString();
			String descripcionFormaPago = "", descripcionIvaAplicable = "4";
			String qry =
				" SELECT " +
					" CONCAT (" +
					" fp.descripcion, " +
					" IF ( " +
						" rb.diasFormaPago = 0, " +
						" '', " +
						" CONCAT(' a ', rb.diasFormaPago, ' dias.') " +
					"), " +
					" IF ( " +
						" rb.diaPago = 0, " +
						" '', " +
						" CONCAT(' Dia de cobro:  ', rb.diaPago, '.') " +
					" ), " +
					" IF(rb.numCuenta = '000000000000000000000000', '', CONCAT('. Cuenta bancaria: ', rb.numCuenta))" +
					") AS formaDePago " +
				" FROM referenciabancaria rb, formapago fp " +
				" WHERE rb.idFormaPago = fp.idFormaPago " +
					" AND rb.idDatoBancario = " + albaran.getIdFormaPago();
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				descripcionFormaPago = rs.getString("formaDePago");
			}
			qry = " SELECT * FROM entidad e WHERE e.idUsuario = " + cliente;
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			long idTelefono = 0, idDireccionCliente = 0;
			qry = " SELECT * FROM telefono t WHERE t.idUsuario = " + cliente;
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				idTelefono = rs.getLong("idTelefono");
			}
			qry = " SELECT * FROM direccion d WHERE d.tipoDireccion = 'F' AND d.empresa_idUsuario = " + cliente;
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				idDireccionCliente = rs.getLong("idDireccion");
			}else{
				qry = " SELECT * FROM direccion d WHERE d.empresa_idUsuario = " + cliente;
				// System.out.println(qry);
				ps = con.prepareStatement(qry);
				rs = ps.executeQuery();
				if (rs.next()) {
					idDireccionCliente = rs.getLong("idDireccion");
				}
			}
			String fechaVencimiento = albaran.getFechaVencimiento();
			String vencimiento = formatoDate(fechaVencimiento);
			// SQL de insersion a la tabla principal: albaran
			String insertString =
				" INSERT INTO albaran (idAlbaran,idCliente,idComercial,idTipoVehiculo,"
					+ " fecha, fechaEntrega,codigoAlbaran,pesoNetoTotal,numeroBultosTotal,cantidadTotal,precioUnitarioTotal,"
					+ " importeTotal,destino,usuarioResponsable,tipoAlbaran,habilitado, idFormaPago, "
					+ " descripcionFormaPago, idTelefono, idDireccionCliente, descripcionIvaAplicable, idTransportista, "
					+ " idTemperaturaTransporte, idPortesTransporte, observaciones, numeroPedido, fechaVencimiento)"
				+ " VALUES ("
					+ idMaxAlbaran
					+ ","
					+ cliente
					+ ","
					+ albaran.getComercial().getIdUsuario()
					+ ","
					+ albaran.getTipoVehiculo().getIdTipoVehiculo()
					+ ",'"
					+ fecha
					+ "','"
					+ fecha
					+ "','"
					+ albaran.getCodigoAlbaran()
					+ "',"
					+ albaran.getPesoNetoTotal()
					+ ","
					+ albaran.getNumeroAgrupacionesTotal()
					+ ","
					+ albaran.getCantidadTotal()
					+ ","
					+ albaran.getPrecioUnitarioTotal()
					+ ","
					+ albaran.getImporteTotal()
					+ ",'"
					+ albaran.getDestino()
					+ "','"
					+ albaran.getUsuarioResponsable()
					+ "','"
					+ albaran.getTipoAlbaran()
					+ "','S',"
					+ albaran.getIdFormaPago()
					+ ",'"
					+ descripcionFormaPago
					+ "','"
					+ idTelefono
					+ "','"
					+ idDireccionCliente
					+ "','"
					+ descripcionIvaAplicable
					+ "','"
					+ albaran.getIdTransportista()
					+ "','"
					+ albaran.getIdTemperaturaTransporte()
					+ "','"
					+ albaran.getIdPortesTransporte()
					+ "','"
					+ albaran.getObservaciones()
					+ "','"
					+ albaran.getCodigoOrden()
					+ "','"
					+ vencimiento
					+ "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (res == 1) {
				// System.out.println("ALBARAN INSERTADO");
				resultado = true;
			}
			String codigoPedido = albaran.getCodigoOrden();
			insertString =
				" INSERT INTO albaran_pedido (idAlbaran, codigoOrden)"
				+ "VALUES ("
					+ idMaxAlbaran
					+ ",'"
					+ codigoPedido
					+ "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			// Si el pedido está compuesto por lineas de otros pedidos, marcamos
			// esas lineas como procesadas
			String Qry = " SELECT foc.idCompuestoDe, foc.idLoc, foc.idLinea as linNum, foli.idLin, foli.moa203 "
					+ " FROM fac_orders_compuesto foc, fac_orders fo, fac_orders_lin foli "
					+ " WHERE fo.bgmNum = '"
					+ codigoPedido
					+ "' "
					+ " AND fo.idOrders = foc.idOrder "
					+ " AND foli.idOrders = foc.idOrder "
					+ " AND foli.linNum = foc.idLinea";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Para cada pedido con que lo compone...
				long idOrder = rs.getLong("idCompuestoDe");
				long linNum = rs.getLong("linNum");
				long idLoc = rs.getLong("idLoc");
				String eanLin = rs.getString("idLin");
				double precioLinea = rs.getDouble("moa203");
				// 1. Deshabilitar la linea que proceda del pedido idOrder
				insertString = " UPDATE fac_orders_loc "
						+ " SET localizacionProcesada = 'S' "
						+ " WHERE idOrders = " + idOrder + " AND idLin = "
						+ linNum + " AND idLoc = " + idLoc;
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				// 2. Deshabilitar tambien: fac_orders_loc; fac_orders_imd
				// No hace falta, con deshabilitar solo la linea deberia ser
				// suficiente
				insertString = " NO EJECUTA: UPDATE fac_orders_loc "
						+ " SET lineaProcesada='S' " + " WHERE idOrders = "
						+ idOrder + " AND idLin = " + linNum;
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				// res = stmt.executeUpdate(insertString);
				insertString = " NO EJECUTA: UPDATE fac_orders_imd "
						+ " SET lineaProcesada='S' " + " WHERE idOrders = "
						+ idOrder + " AND idLin = '" + eanLin + "'";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				// res = stmt.executeUpdate(insertString);
				// Lineas deshabilitadas.
				// 3. Ahora hay que actualizar el pedido de dicha linea
				// fac_orders -> moa79 (importe total) y cnt (numero lineas,
				// REDUCIR??????)
				insertString = " NO EJECUTA: UPDATE fac_orders "
						+ " SET moa79 = moa79 - " + precioLinea
						+ " WHERE idOrders = " + idOrder;
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				// res = stmt.executeUpdate(insertString);
			}
			// System.out.println("FIN DE addAlbaran");
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}

	//@Override
	public Factura getNuevaFactura() throws Exception {
		// System.out.println("DAO getNuevaFactura");
		Factura factura = new Factura();
		try {
			con = bddConexion();
			ps = con
					.prepareStatement("SELECT MAX(idFactura) AS idMaxFactura FROM factura");
			rs = ps.executeQuery();
			long idMaxFact = 0;
			if (rs.next()) {
				Factura factMax = new Factura();
				factMax.setIdFactura(rs.getLong("idMaxFactura"));
				idMaxFact = factMax.getIdFactura() + 1;
			} else
				idMaxFact = 1;
			String codigoFact = "F" + idMaxFact;
			factura.setIdFactura(idMaxFact);
			factura.setCodigoFactura(codigoFact);
			return factura;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}

	//@Override
	public boolean addFactura(String codigoAlbaran, Factura fact) throws Exception {
		// System.out.println("DAO addFactura");
		Statement stmt;
		boolean resultado = false;
		int res2 = 0;
		int res1 = 0;
		int res3 = 0;
		try {
			con = bddConexion();
			Albaran alb = new Albaran();
			// 1. obtener el nuevo Id
			ps = con
					.prepareStatement("SELECT MAX(idFactura) AS idMaxFactura FROM factura");
			rs = ps.executeQuery();
			long idMaxFact = 0;
			if (rs.next()) {
				Factura factMax = new Factura();
				factMax.setIdFactura(rs.getLong("idMaxFactura"));
				idMaxFact = factMax.getIdFactura() + 1;
			} else
				idMaxFact = 1;
			// 2. generar el codigo factura
			String codigoFact = "F" + idMaxFact;
			// 3. crea el registro de la fact con datos iniciales
			String fechaVenc = fact.getFechaVencimiento();
			if (fechaVenc == null)
				fechaVenc = "NULL";
			// 2. recupera los datos totales de cada albaran
			String qry = " SELECT * " + " FROM albaran "
					+ " WHERE codigoAlbaran = '" + codigoAlbaran + "'";
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				alb.setIdAlbaran(rs.getLong("idAlbaran"));
				alb.setCodigoAlbaran(rs.getString("codigoAlbaran"));
				alb.setCantidadTotal(rs.getDouble("cantidadTotal"));
				alb.setEstado(rs.getString("estado").charAt(0));
				alb.setIdFormaPago(rs.getLong("idFormaPago"));
				alb.setIdCliente(rs.getLong("idCliente"));
			}
			char estadoFactura = ' ';
			/*
			 * if (alb.getEstado() == 'E'){ estadoFactura = '1'; }else{
			 * estadoFactura = '2'; }
			 */
			estadoFactura = '3';
			String insertString1 = "INSERT INTO factura(idFactura, codigoFactura, fecha, importeTotal, descuento, valorDescuento, "
					+ " subtotal, cargosTotal, total, fechaVencimiento, albaran, valorIva, estado, "
					+ " idFormaPago, idCliente, nombreCliente, nifCliente, telefonoCliente,"
					+ " idDestino, descripcionDestino, ivaAplicable, observaciones) "
					+ " VALUES("
					+ idMaxFact
					+ ",'"
					+ codigoFact
					+ "',CURRENT_DATE(),"
					+ fact.getImporteTotal()
					+ ","
					+ fact.getDescuento()
					+ ","
					+ fact.getValorDescuento()
					+ ","
					+ fact.getSubtotal()
					+ ","
					+ fact.getCargosTotal()
					+ ","
					+ fact.getTotal()
					+ ",'"
					+ fechaVenc
					+ "','"
					+ codigoAlbaran
					+ "',"
					+ fact.getValorIva()
					+ ",'"
					+ estadoFactura
					+ "','"
					+ fact.getIdFormaPago()
					+ "','"
					+ alb.getIdCliente()
					+ "','"
					+ fact.getNombreCliente()
					+ "','"
					+ fact.getNifCliente()
					+ "','"
					+ fact.getTelefonoCliente()
					+ "','"
					+ fact.getIdDestino()
					+ "','"
					+ fact.getDescripcionDestino()
					+ "','"
					+ fact.getIvaAplicable()
					+ "','"
					+ fact.getObservaciones()
					+ "')";
			// System.out.println(insertString1);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res1 = stmt.executeUpdate(insertString1);
			List<ItemFactura> items = fact.getItems();
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				ItemFactura item = (ItemFactura) iter.next();
				insertString1 = " INSERT INTO factura_item (idFactura, idItem, codigoItem, idProducto, "
						+ " cantidad, peso, precioTotal, precioKilo, descripcion) "
						+ " VALUES ('"
						+ idMaxFact
						+ "','"
						+ item.getIdItem()
						+ "','"
						+ item.getCodigoItem()
						+ "','"
						+ item.getIdProducto()
						+ "','"
						+ item.getCantidad()
						+ "','"
						+ item.getPeso()
						+ "','"
						+ item.getPrecioTotal()
						+ "','"
						+ item.getPrecioKilo()
						+ "','" + item.getDescripcion() + "');";
				// System.out.println(insertString1);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res1 = stmt.executeUpdate(insertString1);
			}
			// Insertamos en factura_compuesto
			insertString1 = "INSERT INTO factura_compuesto(idFactura,albaran) VALUES("
					+ idMaxFact + ",'" + codigoAlbaran + "');";
			// System.out.println(insertString1);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res1 = stmt.executeUpdate(insertString1);
			// hay que insertar en la albaran_factura idFact/idAlb
			// SQL de insersion
			String insertString2 = "INSERT INTO albaran_factura (idFactura,idAlbaran) VALUES("
					+ idMaxFact + "," + alb.getIdAlbaran() + ")";
			// System.out.println(insertString2);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res2 = stmt.executeUpdate(insertString2);
			// hay que insertar en la factura_cargo idFact/tipoCargo
			// SQL de insersion
			Iterator itr = fact.getCargos().iterator();
			while (itr.hasNext()) {
				Cargo cargo = (Cargo) itr.next();
				// System.out.print("Cargo es: " + cargo.getNombre());
				String insertString3 = "INSERT INTO factura_cargo(idFactura, tipoCargo, totalCargo, valorCargo, ivaCargo) "
						+ " VALUES("
						+ idMaxFact
						+ ",'"
						+ cargo.getNombre()
						+ "',"
						+ cargo.getTotalCargo()
						+ ","
						+ cargo.getValor()
						+ "," + cargo.getIvaCargo() + ")";
				// System.out.println(insertString3);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res3 = stmt.executeUpdate(insertString3);
			}
			itr = fact.getCuotas().iterator();
			while (itr.hasNext()) {
				CuotaFactura cuota = (CuotaFactura) itr.next();
				cuota.setEstado("O");
				String insertString3 = " INSERT INTO factura_cuotas(codigoFactura, idCuotaFactura, importe, porcentaje, fecha, observaciones, estado) "
						+ " VALUES('"
						+ codigoFact
						+ "','"
						+ cuota.getNumeroCuota()
						+ "',"
						+ cuota.getImporte()
						+ ","
						+ cuota.getPorcentaje()
						+ ",'"
						+ cuota.getFecha()
						+ "','"
						+ cuota.getObservaciones()
						+ "','"
						+ cuota.getEstado() + "');";
				// System.out.println(insertString3);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res3 = stmt.executeUpdate(insertString3);
			}
			if (res1 == 1 && res2 == 1 && res3 == 1) {
				// System.out.println("factura_detalle INSERTADO ahora actualizo el estado de ese albarán");
				this.updateEstadoAlbaran(codigoAlbaran);
				resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public boolean updateFactura(Factura fact) throws Exception {
		// System.out.println("DAO updateFactura");
		Statement stmt;
		boolean resultado = false;
		try {
			registrarCambioFactura(fact.getCodigoFactura(), fact.getUsuarioResponsable(), "Factura actualizada");
			con = bddConexion();
			String insertString1 =
				" UPDATE factura "
				+ " SET fechaVencimiento = '"
					+ fact.getFechaVencimiento()
					+ "',"
					+ "	importeTotal = '"
					+ fact.getImporteTotal()
					+ "', "
					+ "	descuento = '"
					+ fact.getDescuento()
					+ "', "
					+ "	valorDescuento = '"
					+ fact.getValorDescuento()
					+ "', "
					+ "	observaciones = '"
					+ fact.getObservaciones()
					+ "', "
					+ "	ivaAplicable = '"
					+ fact.getIvaAplicable()
					+ "', "
					+ "	valorIva = '"
					+ fact.getValorIva()
					+ "', "
					+ "	subtotal = '"
					+ fact.getSubtotal()
					+ "', "
					+ "	cargosTotal = '"
					+ fact.getCargosTotal()
					+ "', "
					+ "	idFormaPago = '"
					+ fact.getIdFormaPago()
					+ "', "
					+ "	idDestino = '"
					+ fact.getIdDestino()
					+ "', "
					+ "	nombreCliente = '"
					+ fact.getNombreCliente()
					+ "', "
					+ "	nifCliente = '"
					+ fact.getNifCliente()
					+ "', "
					+ "	telefonoCliente = '"
					+ fact.getTelefonoCliente()
					+ "' "
					+ " WHERE idFactura = '" + fact.getIdFactura() + "'";
			// System.out.println(insertString1);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString1);
			insertString1 = " DELETE FROM factura_item WHERE idFactura = "
					+ fact.getIdFactura();
			// System.out.println(insertString1);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString1);
			List<ItemFactura> items = fact.getItems();
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				ItemFactura item = (ItemFactura) iter.next();
				insertString1 = " INSERT INTO factura_item (idFactura, idItem, codigoItem, idProducto, "
						+ " cantidad, peso, precioTotal, precioKilo, descripcion) "
						+ " VALUES ('"
						+ fact.getIdFactura()
						+ "','"
						+ item.getIdItem()
						+ "','"
						+ item.getCodigoItem()
						+ "','"
						+ item.getIdProducto()
						+ "','"
						+ item.getCantidad()
						+ "','"
						+ item.getPeso()
						+ "','"
						+ item.getPrecioTotal()
						+ "','"
						+ item.getPrecioKilo()
						+ "','" + item.getDescripcion() + "');";
				// System.out.println(insertString1);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString1);
			}
			String insertString3 = " DELETE FROM factura_cargo WHERE idFactura = "
					+ fact.getIdFactura();
			// System.out.println(insertString3);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString3);
			Iterator itr = fact.getCargos().iterator();
			while (itr.hasNext()) {
				Cargo cargo = (Cargo) itr.next();
				// System.out.print("Cargo es: " + cargo.getNombre());
				insertString3 = "INSERT INTO factura_cargo(idFactura, tipoCargo, totalCargo, valorCargo, ivaCargo) "
						+ " VALUES("
						+ fact.getIdFactura()
						+ ",'"
						+ cargo.getNombre()
						+ "',"
						+ cargo.getTotalCargo()
						+ ","
						+ cargo.getValor()
						+ ","
						+ cargo.getIvaCargo()
						+ ")";
				// System.out.println(insertString3);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString3);
			}
			insertString3 = " DELETE FROM factura_cuotas WHERE codigoFactura = '"
					+ fact.getCodigoFactura() + "'";
			// System.out.println(insertString3);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString3);
			itr = fact.getCuotas().iterator();
			while (itr.hasNext()) {
				CuotaFactura cuota = (CuotaFactura) itr.next();
				cuota.setEstado("O");
				insertString3 = " INSERT INTO factura_cuotas(codigoFactura, idCuotaFactura, importe, porcentaje, fecha, observaciones, estado) "
						+ " VALUES('"
						+ fact.getCodigoFactura()
						+ "','"
						+ cuota.getNumeroCuota()
						+ "',"
						+ cuota.getImporte()
						+ ","
						+ cuota.getPorcentaje()
						+ ",'"
						+ cuota.getFecha()
						+ "','"
						+ cuota.getObservaciones()
						+ "','"
						+ cuota.getEstado() + "');";
				// System.out.println(insertString3);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString3);
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	/**
	 * Una vez insertado el encabezado de la fatura, inserto el detalle de los
	 * RS asociados a ese albarán.
	 */
	//@Override
	public Boolean updateEstadoAlbaran(String codigoAlbaran) throws Exception {
		// System.out.println("DAO updateEstadoAlbaran");
		Statement stmt;
		Boolean resultado = false;
		int res1 = 0;
		try {
			con = bddConexion();
			String insertString = " UPDATE albaran " + " SET facturado='S' "
					+ " WHERE codigoAlbaran = '" + codigoAlbaran + "'";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res1 = stmt.executeUpdate(insertString);
			if (res1 == 1) {
				// System.out.println("Estado de albarán actualizado INSERTADO");
				resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}

	/**
	 * Obtiene el detalle del albarán para poder generar una factura.
	 *
	 * @param codigoAlbaran
	 *            the codigo albaran
	 * @param fecha
	 *            the fecha
	 * @param idCliente
	 *            the id cliente
	 * @return the albaran
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("deprecation")
	//@Override
	public Vector<Albaran> getAlbaran(String codigoAlbaran, Date fecha, Long idCliente) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Albaran> resultado = new Vector<Albaran>();
		String Qry2 = " ";
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		try {
			con = bddConexion();
			String Qry =
				" SELECT al.estado, al.idAlbaran,al.idCliente,cl.nombre,al.idTipoVehiculo,al.idComercial,co.nombre as nombreComercial,"
					+ " al.fecha,al.codigoAlbaran,al.pesoNetoTotal,al.numeroBultosTotal,al.cantidadTotal,al.precioUnitarioTotal,"
					+ " al.destino,al.importeTotal,tv.descripcion as descripciontv, al.horarioEntrega "
				+ " FROM albaran al, entidad cl, entidad co,tipovehiculo tv"
				+ " WHERE al.habilitado='S' "
					+ " AND al.idCliente = cl.idUsuario "
					+ " AND al.idComercial = co.idUsuario "
					+ " AND al.idTipoVehiculo = tv.idTipoVehiculo";
			if (codigoAlbaran != null && !codigoAlbaran.equals("")) {
				Qry2 = Qry2 + " AND al.codigoAlbaran = '" + codigoAlbaran + "'";
			}
			if (idCliente != null && idCliente != 0) {
				Qry2 = Qry2 + " AND al.idCliente = " + idCliente;
			}
			if (fecha != null) {
				// obtener año fecha sistema
				ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
				rs = ps.executeQuery();

				while (rs.next()) {
					stringanno = rs.getString("anno");
				}
				int mes = fecha.getMonth() + 1;
				if (mes < 9) {
					stringmes = "0" + mes;
				}
				stringFecha = stringanno + "-" + stringmes + "-"
						+ fecha.getDate();
				Qry2 = Qry2 + " AND al.fecha = '" + stringFecha + "'";
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				Albaran albaran = new Albaran();
				Entidad cliente = new Entidad();
				Entidad comercial = new Entidad();
				TipoVehiculo tv = new TipoVehiculo();
				albaran.setEstado(rs.getString("estado").charAt(0));
				albaran.setIdAlbaran(rs.getLong("idAlbaran"));
				cliente.setIdUsuario(rs.getLong("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				tv.setIdTipoVehiculo(rs.getLong("idTipoVehiculo"));
				tv.setDescripcion(rs.getString("descripciontv"));
				comercial.setIdUsuario(rs.getLong("idComercial"));
				comercial.setNombre(rs.getString("nombreComercial"));
				albaran.setCodigoAlbaran(codigoAlbaran);
				albaran.setPesoNetoTotal(rs.getDouble("idComercial"));
				albaran.setNumeroAgrupacionesTotal(rs.getDouble("numeroBultosTotal"));
				albaran.setCantidadTotal(rs.getDouble("cantidadTotal"));
				albaran.setPrecioUnitarioTotal(rs.getDouble("precioUnitarioTotal"));
				albaran.setImporteTotal(rs.getDouble("importeTotal"));
				albaran.setDestino(rs.getString("destino"));
				albaran.setHorarioEntrega(rs.getString("horarioEntrega"));
				albaran.setCliente(cliente);
				albaran.setTipoVehiculo(tv);
				albaran.setComercial(comercial);
				// La añadimos al Vector de resultado
				resultado.add(albaran);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Obtiene el detalle del albarán para poder generar una factura.
	 *
	 * @param albaran
	 *            the albaran
	 * @param fechaInicio
	 *            the fecha inicio
	 * @param fechaFin the fecha fin
	 * @param filtro 0: todos; 1: ultimos 20; 2: ultimos 50; 3: ultima semana; 4: ultimo mes; 5: ultimo año; 
	 * @return the albaranes
	 * @throws Exception
	 *             the exception
	 */
	//@Override
	public Vector<Albaran> getAlbaranes(Albaran albaran, int filtro) throws Exception {
		// System.out.println("DAO getAlbaranes");
		// Inicializamos el Vector de retorno.
		Vector<Albaran> resultado = new Vector<Albaran>();
		String Qry2 = " ";
		try {
			con = bddConexion();
			String limit = "";
			String Qry =
				" SELECT al.destino, CONCAT(dir.nombreCalle, '. ', dir.localidad) AS direccionEntrega, " +
					" al.idFormaPago, al.idAlbaran,al.idCliente, "
					+ " CONCAT(cl.nombre, '', cl.apellidos) AS nombre, al.fecha,al.codigoAlbaran,al.pesoNetoTotal,"
					+ " al.numeroBultosTotal,al.cantidadTotal,al.precioUnitarioTotal,al.destino,al.importeTotal, "
					+ " IF ("
						+ " al.idFormaPago = 0,"
						+ " '',"
						+ " CONCAT (fp.descripcion,"
							+ " IF ("
									+ " rb.diasFormaPago = 0, "
									+ " '',"
									+ " CONCAT(' a ', rb.diasFormaPago, IF(rb.idFormaPagoDesde = 0, 'dias. ', CONCAT(' dias desde ', fpd.descripcion,'.'))) ), IF (rb.diaPago = 0, "
								+ " '',"
								+ " CONCAT(' Dia de cobro:  ', rb.diaPago, '.')"
							+ " ),"
							+ " IF(rb.numCuenta = '000000000000000000000000', '',"
								+ " 	CONCAT('. Cuenta bancaria: ', rb.numCuenta))"
							+ " )) AS formaDePago, "
					+ " al.facturado, al.tipoAlbaran,al.estado, estados.nombre AS nombreEstado, estados.descripcion AS descripcionEstado "
				+ " FROM albaran al, entidad cl, fac_orders_estados estados, direccion dir, formapago fp, referenciabancaria rb, formapago_desde fpd "
				+ " WHERE al.habilitado = 'S' "
					+ " AND al.idCliente = cl.idUsuario "
					+ " AND estados.idEstado = al.estado " +
					" AND dir.idDireccion = al.destino " +
					" AND rb.idFormaPago = fp.idFormaPago " +
					" AND fpd.idFormaPagoDesde = rb.idFormaPagoDesde " +
					" AND rb.idDatoBancario = al.idFormaPago ";
			if (albaran != null) {
				// Si viene vacio es porque se trata de un albarán simle
				if (albaran.getEstado() == 'P' || albaran.getEstado() == 'T') {
					Qry2 = Qry2 + " AND al.estado = '" + albaran.getEstado()
							+ "'";
				}
				if (albaran.getCodigoAlbaran() != null
						&& !albaran.getCodigoAlbaran().equals("")) {
					Qry2 = Qry2 + " AND al.codigoAlbaran = '"
							+ albaran.getCodigoAlbaran() + "'";
				}
				if (albaran.getCliente() != null
						&& albaran.getCliente().getIdUsuario() != null
						&& albaran.getCliente().getIdUsuario() != 0) {
					Qry2 = Qry2 + " AND al.idCliente = "
							+ albaran.getCliente().getIdUsuario();
				}
				if (albaran.getFacturado() != null
						&& albaran.getFacturado() != "") {
					Qry2 = Qry2 + " AND al.facturado = "
							+ albaran.getFacturado();
				}
			}
			if (filtro > 0){
				if (filtro == 3 || filtro == 4 || filtro == 5){
					String fechaInicio = "", fechaFin = "";
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					fechaFin = dateFormat.format(cal.getTime());
					switch (filtro) {
						case 3://ultima semana
							cal.add(Calendar.DATE, -7);
							fechaInicio = dateFormat.format(cal.getTime());
							break;
						case 4://ultimo mes
							cal.add(Calendar.MONTH, -1);
							fechaInicio = dateFormat.format(cal.getTime());
							break;
						case 5://ultimo año
							cal.add(Calendar.YEAR, -1);
							fechaInicio = dateFormat.format(cal.getTime());
							break;
						default:
							break;
					}
					Qry2 = Qry2 + " AND al.fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' ";
				}else{
					switch (filtro){
						case 1:
							limit = " LIMIT 20; ";
							break;
						case 2:
							limit = " LIMIT 50; ";
							break;
						default:
							break;
					}
				}
			}
			Qry2 += " ORDER BY al.fecha DESC ";
			Qry = Qry.concat(Qry2).concat(limit);
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				Albaran albaran1 = new Albaran();
				Entidad cliente = new Entidad();
				Entidad comercial = new Entidad();
				TipoVehiculo tv = new TipoVehiculo();
				albaran1.setIdAlbaran(rs.getLong("idAlbaran"));
				cliente.setIdUsuario(rs.getLong("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				albaran1.setCodigoAlbaran(rs.getString("codigoAlbaran"));
				ResultSet rs1 = null;
				String qry =
					" SELECT * " +
					" FROM factura_compuesto f " +
					" WHERE f.albaran = '" + albaran1.getCodigoAlbaran() + "'";
				ps = con.prepareStatement(qry);
				rs1 = ps.executeQuery();
				if (rs1.next()) {
					albaran1.setFacturado("S");
				} else
					albaran1.setFacturado("N");
				// Damos formato a la fecha
				java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(
						"dd/MM/yyyy");
				String fecha = formato.format(rs.getDate("fecha"));
				albaran1.setFecha(fecha);
				albaran1.setDescripcionFormaPago(rs.getString("formaDePago"));
				albaran1.setNumeroAgrupacionesTotal(rs.getDouble("numeroBultosTotal"));
				albaran1.setCantidadTotal(rs.getDouble("cantidadTotal"));
				albaran1.setPrecioUnitarioTotal(rs.getDouble("precioUnitarioTotal"));
				albaran1.setImporteTotal(rs.getDouble("importeTotal"));
				albaran1.setDireccionEntrega(rs.getLong("destino"));
				albaran1.setDestino(rs.getString("direccionEntrega"));
				albaran1.setTipoAlbaran(rs.getString("tipoAlbaran"));
				albaran1.setCliente(cliente);
				albaran1.setIdFormaPago(rs.getLong("idFormaPago"));
				albaran1.setTipoVehiculo(tv);
				albaran1.setComercial(comercial);
				albaran1.setEstado(rs.getString("estado").charAt(0));
				albaran1.setDescripcionEstado(rs.getString("nombreEstado"));
				// La añadimos al Vector de resultado
				resultado.add(albaran1);
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
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Tabla accion. Acción que muestra las facturas en el escritorio, id = 14
	 */
	//@Override
	public Vector<Factura> getFacturas(Factura factura, String fechaInicio,
			String fechaFin, Usuario usuario) throws Exception {
		// System.out.println("DAO getFacturas");
		// Inicializamos el Vector de retorno.
		Vector<Factura> resultado = new Vector<Factura>();
		String Qry2 = "";
		try {
			con = bddConexion();
			String Qry =
				" SELECT ar.idRol " +
				" FROM accion_rolpermitido ar " +
				" WHERE ar.idAccion = 14 ";//14: Mostrar facturas en el escritorio
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			boolean tienePermiso = false;
			Vector<Rol> roles = usuario.getRoles();
			while (rs.next()) {
				Rol rol = new Rol();
				rol.setIdRol(rs.getLong("idRol"));
				for (int i = 0; i < roles.size(); i++){
					if (rol.getIdRol() == roles.get(i).getIdRol()){
						tienePermiso = true;
						break;
					}
				}
				if (tienePermiso)
					break;
			}
			if (tienePermiso){
				Qry =
					" SELECT f.idFactura, f.codigoFactura, f.fecha, f.pedido, f.albaran, f.importeTotal, f.descuento,"
						+ " f.valorIva, f.subtotal, f.cargosTotal, f.total, f.fechaVencimiento, e.idUsuario, "
						+ " f.nombreCliente, foe.nombre as nombreEstado, foe.descripcion as descripcionEstado, foe.idEstado "
					+ " FROM factura f, albaran a, entidad e, fac_orders_estados foe "
					+ " WHERE f.albaran = a.codigoAlbaran "
						+ " AND a.idCliente = e.idUsuario "
						+ " AND f.estado = foe.idEstado ";
				if ((fechaInicio != null && fechaInicio != "")
						&& (fechaFin != null && fechaFin != ""))
					Qry2 = Qry2 + " AND f.fecha BETWEEN '" + fechaInicio
							+ "' AND '" + fechaFin + "'";
				if (factura != null && factura.getIdCliente() != 0)
					Qry2 = Qry2 + " AND e.idUsuario='" + factura.getIdCliente()
							+ "'";
				if (factura != null && factura.getEstado() != null
						&& !factura.getEstado().equals(""))
					Qry2 = Qry2 + " AND f.estado = '" + factura.getEstado() + "'";
				if (factura != null && factura.getCodigoFactura() != null
						&& !factura.getCodigoFactura().equals(""))
					Qry2 = Qry2 + " AND f.codigoFactura = '"
							+ factura.getCodigoFactura() + "'";
				Qry = Qry + Qry2;
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					// Completamos los datos del proveedor en el registro
					Factura f = new Factura();
					f.setIdFactura(rs.getLong("idFactura"));
					f.setCodigoFactura(rs.getString("codigoFactura"));
					ResultSet rs1 = null;
					String qry = " SELECT * " + " FROM factura_cuotas fc "
							+ " WHERE fc.codigoFactura = '" + f.getCodigoFactura()
							+ "'";
					ps = con.prepareStatement(qry);
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						f.setEnCuotas("si");
					} else
						f.setEnCuotas("no");
					f.setFecha(rs.getString("fecha"));
					f.setPedido(rs.getString("pedido"));
					f.setCodigoAlbaran(rs.getString("albaran"));
					f.setImporteTotal(rs.getDouble("importeTotal"));
					f.setDescuento(rs.getDouble("descuento"));
					f.setValorIva(rs.getDouble("valorIva"));
					f.setSubtotal(rs.getDouble("subtotal"));
					f.setCargosTotal(rs.getDouble("cargosTotal"));
					f.setTotal(rs.getDouble("total"));
					f.setFechaVencimiento(rs.getString("fechaVencimiento"));
					// f.setEanCliente(rs.getString("eanCliente"));
					f.setIdCliente(rs.getLong("idUsuario"));
					f.setNombreCliente(rs.getString("nombreCliente"));
					f.setEstado(rs.getString("idEstado"));
					f.setDescripcionEstado(rs.getString("nombreEstado"));
					// La añadimos al Vector de resultado
					resultado.add(f);
				}
				Qry2 = "";
				Qry = " SELECT f.idFactura, f.codigoFactura, f.fecha, f.pedido, f.albaran, f.importeTotal, f.descuento, f.idCliente, "
						+ " f.valorIva, f.subtotal, f.cargosTotal, f.total, f.fechaVencimiento, f.nombreCliente, "
						+ " foe.nombre as nombreEstado, foe.descripcion as descripcionEstado, foe.idEstado "
						+ " FROM factura f, fac_orders_estados foe "
						+ " WHERE f.estado = foe.idEstado AND f.albaran = ''";
				if ((fechaInicio != null && fechaInicio != "")
						&& (fechaFin != null && fechaFin != ""))
					Qry2 = Qry2 + " AND f.fecha BETWEEN '" + fechaInicio
							+ "' AND '" + fechaFin + "'";
				if (factura != null && factura.getIdCliente() != 0)
					Qry2 = Qry2 + " AND f.idCliente='" + factura.getIdCliente()
							+ "'";
				if (factura != null && factura.getEstado() != null
						&& !factura.getEstado().equals(""))
					Qry2 = Qry2 + " AND f.estado = '" + factura.getEstado() + "'";
				if (factura != null && factura.getCodigoFactura() != null
						&& !factura.getCodigoFactura().equals(""))
					Qry2 = Qry2 + " AND f.codigoFactura = '"
							+ factura.getCodigoFactura() + "'";
				Qry = Qry + Qry2;
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					// Completamos los datos del proveedor en el registro
					Factura f = new Factura();
					f.setIdFactura(rs.getLong("idFactura"));
					f.setCodigoFactura(rs.getString("codigoFactura"));
					ResultSet rs1 = null;
					String qry = " SELECT * " + " FROM factura_cuotas fc "
							+ " WHERE fc.codigoFactura = '" + f.getCodigoFactura()
							+ "'";
					ps = con.prepareStatement(qry);
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						f.setEnCuotas("si");
					} else
						f.setEnCuotas("no");
					f.setFecha(rs.getString("fecha"));
					f.setPedido(rs.getString("pedido"));
					f.setCodigoAlbaran(rs.getString("albaran"));
					f.setImporteTotal(rs.getDouble("importeTotal"));
					f.setDescuento(rs.getDouble("descuento"));
					f.setValorIva(rs.getDouble("valorIva"));
					f.setSubtotal(rs.getDouble("subtotal"));
					f.setCargosTotal(rs.getDouble("cargosTotal"));
					f.setTotal(rs.getDouble("total"));
					f.setFechaVencimiento(rs.getString("fechaVencimiento"));
					// f.setEanCliente(rs.getString("eanCliente"));
					f.setIdCliente(rs.getLong("idCliente"));
					f.setNombreCliente(rs.getString("nombreCliente"));
					f.setEstado(rs.getString("idEstado"));
					f.setDescripcionEstado(rs.getString("nombreEstado"));
					// La añadimos al Vector de resultado
					resultado.add(f);
				}
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
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Obtiene el encabezado del albarán para poder generar una factura.
	 *
	 * @param codigoAlbaran
	 *            the codigo albaran
	 * @return the encabezado albaran
	 * @throws Exception
	 *             the exception
	 */
	//@Override
	public Albaran getEncabezadoAlbaran(String codigoAlbaran) throws Exception {
		// Inicializamos el Vector de retorno.
		// System.out.println("DAO getEncabezadoAlbaran");
		Albaran albaran = new Albaran();
		try {
			con = bddConexion();
			String Qry = "SELECT DISTINCT al.fecha,al.idAlbaran,al.idCliente,cl.nombre,cl.nif as nifCliente,cl.idUsuario, "
					+ "al.fecha,al.codigoAlbaran,al.pesoNetoTotal,al.numeroBultosTotal,al.cantidadTotal,al.precioUnitarioTotal, "
					+ "al.destino,al.importeTotal FROM albaran al, entidad cl "
					+ "WHERE al.habilitado='S' AND al.idCliente = cl.idUsuario AND al.codigoAlbaran = '"
					+ codigoAlbaran + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Entidad cliente = new Entidad();
				albaran.setIdAlbaran(rs.getLong("idAlbaran"));
				cliente.setIdUsuario(rs.getLong("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setIdUsuario(rs.getLong("idUsuario"));
				cliente.setNif(rs.getString("nifCliente"));
				albaran.setCodigoAlbaran(rs.getString("codigoAlbaran"));
				albaran.setPesoNetoTotal(rs.getDouble("pesoNetoTotal"));
				albaran.setNumeroAgrupacionesTotal(rs
						.getDouble("numeroBultosTotal"));
				albaran.setCantidadTotal(rs.getDouble("cantidadTotal"));
				albaran.setPrecioUnitarioTotal(rs
						.getDouble("precioUnitarioTotal"));
				albaran.setImporteTotal(rs.getDouble("importeTotal"));
				albaran.setDestino(rs.getString("destino"));
				// Damos formato a la fecha
				java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(
						"dd/MM/yyyy");
				String fecha = formato.format(rs.getDate("fecha"));
				albaran.setFecha(fecha);
				albaran.setCliente(cliente);
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
		return albaran;
	}

	/**
	 * Obtiene el detalle del albarán para poder generar una factura.
	 *
	 * @param albaran
	 *            the albaran
	 * @return the detalle albaran
	 * @throws Exception
	 *             the exception
	 */
	//@Override
	public Vector<RegistroSalida> getDetalleAlbaran(Albaran albaran) throws Exception {
		// Inicializamos el Vector de retorno.
		// System.out.println("DAO getDetalleAlbaran");
		Vector<RegistroSalida> resultado = new Vector<RegistroSalida>();
		try {
			con = bddConexion();
			String Qry = "SELECT p.nombre, ad.idRegistroSalida,ad.codigoAlbaran,p.codigoEan,p.idProducto,ad.codigoSalida, "
					+ " ad.pesoNeto,ad.numeroBultos,ad.cantidadUnitaria,ad.precioUnitario,bl.idHueco,bl.cantidad, "
					+ " bl.lote "
					+ " FROM albaran_detalle ad, producto p, registrosalida s, bulto b, bulto_lotes bl "
					+ " WHERE ad.codigoAlbaran = ? "
					+ " AND b.idBulto = bl.idBulto "
					+ " AND b.codigoSalida = s.codigoSalida "
					+ " AND ad.codigoSalida = s.codigoSalida "
					+ " AND p.idProducto = ad.idProducto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			ps.setString(1, albaran.getCodigoAlbaran());
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				RegistroSalida salida = new RegistroSalida();
				salida.setCodigoSalida(rs.getString("codigoSalida"));
				salida.setCodigoEan(rs.getString("codigoEan"));
				salida.setIdProducto(rs.getLong("idProducto"));
				salida.setPesoNeto(rs.getDouble("pesoNeto"));
				salida.setNumeroBultos(rs.getLong("numeroBultos"));
				salida.setPrecioUnitario(rs.getDouble("precioUnitario"));
				salida.setDescripcion(rs.getString("nombre"));
				salida.setCantidadUnitaria(rs.getDouble("cantidad"));
				salida.setLote(rs.getString("lote"));
				// La añadimos al Vector de resultado
				resultado.add(salida);
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
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Obtiene los RS asociados a la línea del albarán Cuando se tiene una orden
	 * previa.
	 *
	 * @param codigoAlbaran
	 *            the codigo albaran
	 * @param linNum
	 *            the lin num
	 * @return the rS linea albaran
	 * @throws Exception
	 *             the exception
	 */
	//@Override
	public Vector<RegistroSalida> getRSLineaAlbaran(String codigoAlbaran,
			Long linNum) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<RegistroSalida> resultado = new Vector<RegistroSalida>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT codigoSalida, p.idProducto, p.codigoEan, linNum, cantidadUnitaria,pesoNeto,numeroBultos,precioUnitario "
				+ " FROM albaran_detalle ad, producto p "
				+ " WHERE codigoAlbaran = '"
					+ codigoAlbaran
					+ "' "
					+ " AND linNum = "
					+ linNum
					+ " "
					+ " AND p.idProducto = ad.idProducto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				RegistroSalida salida = new RegistroSalida();
				salida.setCodigoSalida(rs.getString("codigoSalida"));
				salida.setCodigoEan(rs.getString("codigoEan"));
				salida.setNumLinea(rs.getLong("linNum"));
				salida.setIdProducto(rs.getLong("idProducto"));
				salida.setCantidadUnitaria(rs.getDouble("cantidadUnitaria"));
				salida.setPesoNeto(rs.getDouble("pesoNeto"));
				salida.setPesoNeto(rs.getDouble("precioUnitario"));
				salida.setNumeroBultos(rs.getLong("numeroBultos"));
				// La añadimos al Vector de resultado
				resultado.add(salida);
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
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Object> getRSLineaAlbaranTotal(String codigoAlbaran,
			Long linNum) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Object> resultado = new Vector<Object>();
		try {
			con = bddConexion();
			// obtener totales de la línea del detalle del albarán
			String Qry = " SELECT SUM(pesoNeto) AS pesoNetoTotal, SUM(numeroBultos) AS numeroBultosTotal,"
					+ " SUM(cantidadUnitaria) AS cantidadTotal, SUM(precioUnitario) AS precioUnitarioTotal,"
					+ " SUM(importe) AS importeTotal "
					+ " FROM albaran_detalle ad "
					+ " WHERE codigoAlbaran = '"
					+ codigoAlbaran + "' " + " AND linNum = " + linNum;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				RegistroSalida regsalida = new RegistroSalida();
				regsalida.setPesoNeto(rs.getDouble("pesoNetoTotal"));
				regsalida.setNumeroBultos(rs.getLong("numeroBultosTotal"));
				regsalida.setCantidadUnitaria(rs.getDouble("cantidadTotal"));
				regsalida.setPrecioUnitario(rs.getDouble("precioUnitarioTotal"));
				regsalida.setPrecioTotal(rs.getDouble("importeTotal"));
				// La añadimos al Vector de resultado
				resultado.add(regsalida);
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
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Obtiene el encabezado del albarán para poder generar una factura.
	 *
	 * @param orden
	 *            the orden
	 * @return the encabezado albaran orden
	 * @throws Exception
	 *             the exception
	 */
	//@Override
	public Albaran getEncabezadoAlbaranOrden(Order orden) throws Exception {
		// Inicializamos el Vector de retorno.
		// System.out.println("DAO getEncabezadoAlbaran");
		Albaran albaran = new Albaran();
		try {
			con = bddConexion();
			String Qry = "SELECT fo.nadIv as eanComprador,cl.idUsuario as idCliente,cl.nombre as nombre ,"
					+ "co.idUsuario as idComercial,co.nombre as nombreComercial ,fo.nadSu as eanVendedor "
					+ " FROM fac_orders fo, entidad cl, entidad co"
					+ " WHERE fo.nadIv = cl.ean "
					+ " AND fo.nadSu = co.ean "
					+ " AND fo.idOrders = " + orden.getIdOrders();
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Entidad cliente = new Entidad();
				Entidad comercial = new Entidad();
				cliente.setIdUsuario(rs.getLong("idCliente"));
				cliente.setNombre(rs.getString("nombre"));
				comercial.setIdUsuario(rs.getLong("idComercial"));
				comercial.setNombre(rs.getString("nombreComercial"));
				albaran.setCliente(cliente);
				albaran.setComercial(comercial);
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
		return albaran;
	}

	//@Override
	public Albaran getTotalesAlbaranOrden(String codigoAlbaran)
			throws Exception {
		// Inicializamos el Vector de retorno.
		// System.out.println("DAO getTotalesAlbaranOrden");
		Albaran alb = new Albaran();
		try {
			con = bddConexion();
			String Qry = " SELECT SUM(ad.pesoNeto) AS pesoNeto, SUM(ad.numeroBultos)as numeroBultos, "
					+ " SUM(ad.cantidadUnitaria) as cantidadUnitaria,sum(ad.precioUnitario) as precioUnitario,  "
					+ " SUM(cantidadUnitaria)*sum(precioUnitario) as precioTotalLinea  "
					+ " FROM albaran_detalle ad"
					+ " WHERE ad.codigoAlbaran = '" + codigoAlbaran + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				alb.setPesoNetoTotal(rs.getDouble("pesoNeto"));
				alb.setNumeroAgrupacionesTotal(rs.getDouble("numeroBultos"));
				alb.setCantidadTotal(rs.getDouble("cantidadUnitaria"));
				alb.setPrecioUnitarioTotal(rs.getDouble("precioUnitario"));
				alb.setImporteTotal(rs.getDouble("precioTotalLinea"));
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
		return alb;
	}

	/**
	 * @author andres (31-05-2011)
	 * @since 1.2
	 */
	@SuppressWarnings("deprecation")
	//@Override
	public Vector<RegistroSalida> getRegistroSalidas(String lote,
			String codigoSalida, Date fecha, Long idProducto) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<RegistroSalida> resultado = new Vector<RegistroSalida>();
		String Qry2 = "";
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		int cual = 0;
		try {
			con = bddConexion();
			String Qry = "";
			if (lote == null || lote.compareTo("") == 0) {
				cual = 0;
				Qry =
					" SELECT r.codigoSalida,r.fecha,r.fechaCaducidad,r.habilitado,r.idCliente," +
						" e.nombre as descCliente,r.destino,r.albaran," +
						" r.idProducto,r.pesoNeto,r.pesoBruto,r.numeroBultos,p.nombre as descProducto," +
						" r.cantidad,r.idComercial,r.idVehiculo, r.idVehiculo, v.matricula as descVehiculo," +
						" r.notasvehiculo,r.notasincidencias,r.notasinstrucciones,r.usuarioResponsable " +
					" FROM registrosalida r, entidad e, entidad_rol er, producto p, vehiculo v  " +
					" WHERE r.idCliente = e.idUsuario " +
						" AND r.idProducto = p.idProducto " +
						" AND r.idVehiculo = v.idVehiculo " +
						" AND e.idUsuario = er.idEntidad ";
				Qry2 = " AND er.idRol = 1 ";
				if (codigoSalida != null && !codigoSalida.equals("")) {
					// Qry2 = Qry2 + " AND r.codigoSalida = '" + codigoSalida +
					// "'";
				}
				if (idProducto != null && idProducto != 0) {
					// Qry2 = Qry2 + " AND r.idProducto = " + idProducto;
				}
			} else {
				cual = 1;
				Qry =
					/*" SELECT DISTINCT bl.lote, b.codigoSalida, ad.codigoAlbaran, ad.cantidadUnitaria, " +
						" a.fecha as fechaAlbaran, ad.pesoNeto " + //, e.nombre, e.apellidos, e.nif, e.idUsuario, e.web, bl.cantidad, p.idProducto, p.codigoEan " +
					" FROM bulto_lotes bl, bulto b, albaran_detalle ad, albaran a, entidad e, producto p " +
					" WHERE bl.lote = '" + lote + "' " +
						" AND bl.idBulto = b.idBulto " +
						" AND ad.codigoSalida = b.codigoSalida " +
						" AND a.codigoAlbaran = ad.codigoAlbaran " +
						" AND e.idUsuario = a.idCliente " +
						" AND p.idProducto = ad.idProducto" +
						" AND a.habilitado = 'S' ";*/
					" SELECT DISTINCT bl.lote, b.codigoSalida, ad.codigoAlbaran, ad.cantidadUnitaria, " +
						" a.fechaEntrega as fechaAlbaran, ad.pesoNeto, bl.cantidad, a.idCliente, ad.idProducto" +
						//" /*, e.nombre, e.apellidos, e.nif, e.idUsuario, e.web, p.nombre, p.codigoEan*/ " +
					" FROM bulto_lotes bl " +
						" LEFT JOIN bulto b ON bl.idBulto = b.idBulto " +
						" LEFT JOIN albaran_detalle ad ON ad.codigoSalida = b.codigoSalida " +
						" LEFT JOIN albaran a ON a.codigoAlbaran = ad.codigoAlbaran AND a.habilitado = 'S' " +
						/*INNER JOIN entidad e ON e.idUsuario = a.idCliente 
	 					INNER JOIN producto p ON p.idProducto = ad.idProducto*/
					" WHERE bl.lote = '" + lote + "' ";
			}
			if (fecha != null) {
				// obtener año fecha sistema
				ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
				rs = ps.executeQuery();

				while (rs.next()) {
					stringanno = rs.getString("anno");
				}
				int mes = fecha.getMonth() + 1;
				if (mes < 9) {
					stringmes = "0" + mes;
				}
				stringFecha = stringanno + "-" + stringmes + "-"
						+ fecha.getDate();
				Qry2 = Qry2 + " AND r.fecha = '" + stringFecha + "'";
			}
			Qry = Qry + Qry2;
			Qry += " GROUP BY codigoAlbaran; ";
			System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				RegistroSalida salida = new RegistroSalida();
				if (cual == 0) {
					salida.setCodigoSalida(rs.getString("codigoSalida"));
					salida.setFecha(rs.getDate("fecha"));
					salida.setIdProducto(rs.getLong("idProducto"));
					salida.setCodigoEan(rs.getString("codigoEan"));
					salida.setNumeroBultos(rs.getLong("numeroBultos"));
					salida.setIdOperario(rs.getString("usuarioResponsable"));
					salida.setNombreCliente(rs.getString("nombreCliente"));
					salida.setIdCliente(rs.getLong("idCliente"));
				} else if (cual == 1) {
					salida.setCodigoSalida(rs.getString("codigoSalida"));
					salida.setCodigoAlbaran(rs.getString("codigoAlbaran"));
					
					String queryAux =
						" SELECT p.idProducto, p.codigoEan " +
						" FROM producto p " +
						" WHERE p.idProducto = " + rs.getLong("idProducto");
					PreparedStatement psAux = con.prepareStatement(queryAux);
					ResultSet rsAux = psAux.executeQuery();
					if (rsAux.next()){
						salida.setCodigoEan(rsAux.getString("codigoEan"));
						salida.setIdProducto(rsAux.getLong("idProducto"));
					}
					
					queryAux =
						" SELECT e.* " +
						" FROM entidad e " +
						" WHERE e.idUsuario = " + rs.getLong("idCliente");
					psAux = con.prepareStatement(queryAux);
					rsAux = psAux.executeQuery();
					if (rsAux.next()){						
						salida.setNombreCliente(rsAux.getString("nombre") + " " + rsAux.getString("apellidos"));
						salida.setNifCliente(rsAux.getString("nif"));
						salida.setIdCliente(rsAux.getLong("idUsuario"));
						salida.setWebCliente(rsAux.getString("web"));
					}
					rsAux.close();
					
					salida.setPesoNeto(rs.getDouble("pesoNeto"));
					salida.setCantidadUnitaria(rs.getDouble("cantidadUnitaria"));
					salida.setCantidad(rs.getDouble("cantidad"));
					salida.setFecha(rs.getDate("fechaAlbaran"));
				}
				// Añadimos al Vector de resultado
				resultado.add(salida);
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
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<RegistroSalida> loadRegistroSalida(String codigoSalida)
			throws Exception {
		Vector<RegistroSalida> resultado = new Vector<RegistroSalida>();
		try {
			con = bddConexion();
			String Qry =
				"SELECT codigoSalida, p.codigoEan, p.idProducto,pesoNeto,numeroBultos,cantidadUnitaria,precioUnitario"
				+ " FROM albaran_detalle ad, producto p "
				+ " WHERE codigoSalida= '"
					+ codigoSalida
					+ "' "
					+ " AND ad.idProducto = p.idProducto ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				RegistroSalida salida = new RegistroSalida();
				salida.setIdProducto(rs.getLong("idProducto"));
				salida.setCodigoEan(rs.getString("codigoEan"));
				salida.setCodigoSalida(rs.getString("codigoSalida"));
				salida.setOrdenEnvasado(rs.getString("ordenEnvasado"));
				salida.setPesoNeto(rs.getDouble("pesoNeto"));
				salida.setNumeroBultos(rs.getLong("numeroBultos"));
				salida.setCantidadUnitaria(rs.getDouble("cantidadUnitaria"));
				salida.setPrecioUnitario(rs.getDouble("precioUnitario"));
				// La añadimos al Vector de resultado
				resultado.add(salida);
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
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Boolean updateRegistroSalida(RegistroSalida exitf,
			RegistroSalida exitu) throws Exception {
		// System.out.println("DAO updateRegistroSalida");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			String updateString =
				" UPDATE albaran_detalle " +
				" SET "
					+ " usuarioResponsable = '" + exitu.getIdOperario() + "',"
					+ " pesoNeto = " + exitu.getPesoNeto() + ","
					+ " numeroBultos = " + exitu.getNumeroBultos() + ","
					+ " cantidadUnitaria = " + exitu.getCantidadUnitaria()
					+ "," + " precioUnitario = " + exitu.getPrecioUnitario()
				+ " WHERE codigoSalida = '" + exitf.getCodigoSalida() + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if (res == 1) {
				// System.out.println("RS ACTUALIZADO");
				resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Boolean deleteRegistroSalida(RegistroSalida exitf,
			RegistroSalida exitd) throws Exception {
		// System.out.println("DAO deleteRegistroSalida");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			String delString = "DELETE FROM albaran_detalle WHERE codigoSalida = '"
					+ exitf.getCodigoSalida() + "'";
			// System.out.println(delString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(delString);
			if (res == 1) {
				// System.out.println("RS ELIMINADO");
				resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}

	@SuppressWarnings("unchecked")
	//@Override
	public Boolean updateDetaAlba(Map mapaPrecUnit, String codigoAlbaran)
			throws Exception {
		// System.out.println(" FACTURA DAO updateDetaAlba con codigo codigoAlbaran");
		// Inicializamos el Vector de retorno.
		int res = 0;
		Statement stmt;
		Boolean resultado = false;
		try {
			con = bddConexion();
			for (Iterator it = mapaPrecUnit.keySet().iterator(); it.hasNext();) {
				String codigoSalidaMap = (String) it.next();
				// System.out.println("codigo salida mapa " + codigoSalidaMap);
				String updateString =
					" UPDATE albaran_detalle " +
					" SET"
						+ " precioUnitario = "
						+ Double.parseDouble((String) mapaPrecUnit
								.get(codigoSalidaMap))
					+ " WHERE codigoAlbaran = '" + codigoAlbaran + "'"
						+ " AND codigoSalida = '" + codigoSalidaMap + "'";
				// System.out.println(updateString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(updateString);
			}
			if (res == 1) {
				// System.out.println("REGISTRO ACTUALIZADO");
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * @author Induserco, Andrés (19/04/2011) - (26.10.2011)
	 * @since 0.2
	 */
	//@Override
	public Vector<Direccion> getDireccionesClientes(String tipo)
			throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Direccion> resultado = new Vector<Direccion>();
		try {
			con = bddConexion();
			String Qry = "SELECT DISTINCT idUsuario,idDireccion,"
					+ " IF (nombre='', apellidos, IF(apellidos='', nombre, CONCAT(nombre, ' - ', apellidos))) as nombre,"
					+ " nombreCalle,entidad.ean "
					+ " FROM entidad,direccion "
					+ " WHERE idRol = 1 AND direccion.empresa_idUsuario = entidad.idUsuario"
					+ " AND direccion.tipoDireccion='" + tipo + "' "
					+ " ORDER BY nombre";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				Direccion direccion = new Direccion();
				direccion.setIdProvincia(rs.getLong("idUsuario"));
				direccion.setNombreProvincia(rs.getString("nombre"));
				direccion.setUbicacion(rs.getString("ean"));
				direccion.setNombreCalle(rs.getString("nombreCalle"));
				direccion.setIdDireccion(rs.getLong("idDireccion"));
				// La añadimos al Vector de resultado
				resultado.add(direccion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * @author Induserco, Andrés (27/04/2011)
	 * @since 0.3
	 */
	//@Override
	public String getDireccionesEAN(String idDireccion) throws Exception {
		// Inicializamos el Vector de retorno.
		String resultado = "";
		try {
			con = bddConexion();
			String Qry = "SELECT DISTINCT ean FROM direccion "
					+ "WHERE direccion.idDireccion='" + idDireccion + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				resultado = rs.getString("ean");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * @author Induserco, Andrés (19/04/2011)
	 */
	//@Override
	public Vector<FormaPago> getFormasDePago() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<FormaPago> resultado = new Vector<FormaPago>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM  formapago";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				FormaPago pago = new FormaPago();
				pago.setIdFormaPago(rs.getLong("idFormaPago"));
				pago.setDescripcion(rs.getString("descripcion"));
				// La añadimos al Vector de resultado
				resultado.add(pago);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Entidad> getClientes() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Entidad> resultado = new Vector<Entidad>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT DISTINCT idUsuario,"
					+ " IF (nombre='', apellidos, IF(apellidos='', nombre, CONCAT(nombre, ' - ', apellidos))) as nombre,"
					+ " ean, nif " +
				" FROM entidad e, entidad_rol er " +
				" WHERE er.idEntidad = e.idUsuario " +
					" AND er.idRol = 1 " +
				" ORDER BY nombre ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				Entidad entidad = new Entidad();
				entidad.setIdUsuario(rs.getLong("idUsuario"));
				entidad.setNombre(rs.getString("nombre"));
				entidad.setEan(rs.getString("ean"));
				entidad.setNif(rs.getString("nif"));
				// La añadimos al Vector de resultado
				resultado.add(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Producto> getProductos() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM producto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				Producto prod = new Producto();
				prod.setIdProducto(rs.getLong("idProducto"));
				prod.setNombre(rs.getString("nombre"));
				// La añadimos al Vector de resultado
				resultado.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Vehiculo> getVehiculos() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Vehiculo> resultado = new Vector<Vehiculo>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM vehiculo";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				Vehiculo vh = new Vehiculo();
				vh.setIdVehiculo(rs.getLong("idVehiculo"));
				vh.setMatricula(rs.getString("matricula"));
				// La añadimos al Vector de resultado
				resultado.add(vh);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public String getFechaRegistro() throws Exception {

		String mes = "";
		Calendar fecha = java.util.Calendar.getInstance();
		switch (fecha.get(java.util.Calendar.MONTH)) {
		case 0:
			mes = "01";
			break;
		case 1:
			mes = "02";
			break;
		case 2:
			mes = "03";
			break;
		case 3:
			mes = "04";
			break;
		case 4:
			mes = "05";
			break;
		case 5:
			mes = "06";
			break;
		case 6:
			mes = "07";
			break;
		case 7:
			mes = "08";
			break;
		case 8:
			mes = "09";
			break;
		case 9:
			mes = "10";
			break;
		case 10:
			mes = "11";
			break;
		case 11:
			mes = "12";
			break;
		}
		String dia = "" + fecha.get(java.util.Calendar.DATE);
		// System.out.println("Dia: " + dia);
		if (dia.length() == 1) {
			dia = "0" + dia;
		}
		String fecharetorno = dia + "/" + mes + "/"
				+ fecha.get(java.util.Calendar.YEAR);
		return fecharetorno;

	}

	//@Override
	public Vector<TipoVehiculo> getTipoVehiculos() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<TipoVehiculo> resultado = new Vector<TipoVehiculo>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM tipovehiculo";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				TipoVehiculo tvh = new TipoVehiculo();
				tvh.setIdTipoVehiculo(rs.getLong("idTipoVehiculo"));
				tvh.setDescripcion(rs.getString("descripcion"));
				// La añadimos al Vector de resultado
				resultado.add(tvh);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Entidad> getComerciales() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Entidad> resultado = new Vector<Entidad>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM entidad WHERE idRol = 3";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				Entidad entidad = new Entidad();
				entidad.setIdUsuario(rs.getLong("idUsuario"));
				entidad.setNombre(rs.getString("nombre"));
				// La añadimos al Vector de resultado
				resultado.add(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;

	}

	//@Override
	public RegistroSalida getAlbaranRS(String orden) throws Exception {
		// Inicializamos el Vector de retorno.
		RegistroSalida salida = new RegistroSalida();
		Albaran albaran = new Albaran();
		try {
			con = bddConexion();
			// System.out.println("DAO getAlbaranRS");
			String Qry =
				" SELECT p.idProducto, p.codigoEan, ad.codigoAlbaran, ad.codigoSalida, ad.pesoNeto, "
					+ " ad.numeroBultos,ad.cantidadUnitaria,ad.importe, ad.usuarioResponsable "
				+ " FROM albaran_detalle ad, producto p "
				+ " WHERE ad.ordenEnvasado= ? "
					+ " AND p.idProducto = ad.idProducto; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			ps.setString(1, orden);
			rs = ps.executeQuery();
			while (rs.next()) {
				salida.setCodigoEan(rs.getString("codigoEan"));
				salida.setIdProducto(rs.getLong("idProducto"));
				albaran.setCodigoAlbaran(rs.getString("codigoAlbaran"));
				salida.setAlbaran(albaran);
				salida.setCodigoSalida(rs.getString("codigoSalida"));
				salida.setPesoNeto(rs.getDouble("pesoNeto"));
				salida.setNumeroBultos(rs.getLong("numeroBultos"));
				salida.setCantidadUnitaria(rs.getDouble("cantidadUnitaria"));
				salida.setPrecioTotal(rs.getDouble("importe"));
				salida.setIdOperario(rs.getString("usuarioResponsable"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		return salida;
	}

	/**
	 * @author Induserco, Andrés (26/04/2011)
	 * @since 0.3
	 */
	//@Override
	public Albaran getTotalesOrden(String codigoOrden) throws Exception {
		// Inicializamos el Vector de retorno.
		// System.out.println("DAO getTotalesAlbaranOrden");
		Albaran alb = new Albaran();
		try {
			con = bddConexion();
			String Qry = "SELECT SUM(lin.qty21Cant / lin.qty59Cant) AS agrupacionesTotal,"
					+ " orders.moa79 AS precioTotalLinea,"
					+ " sum(lin.priAab) AS precioUnitario,"
					+ " sum(lin.qty21Cant) AS cantidadUnitaria, "
					+ " SUM(kilos) as kilosTotalesPedido "
					+ " FROM fac_orders_lin lin, fac_orders orders"
					+ " WHERE lin.idOrders = orders.idOrders"
					+ " AND orders.bgmNum = '" + codigoOrden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				alb.setNumeroAgrupacionesTotal(rs
						.getDouble("agrupacionesTotal"));
				alb.setCantidadTotal(rs.getDouble("cantidadUnitaria"));
				alb.setPrecioUnitarioTotal(rs.getDouble("precioUnitario"));
				alb.setImporteTotal(rs.getDouble("precioTotalLinea"));
				alb.setPesoNetoTotal(rs.getDouble("kilosTotalesPedido"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		return alb;
	}

	//@Override
	public Vector<Albaran> getDireccionesCantidades(Long idOrders, String linNum)
			throws Exception {
		// System.out.println("DAO getDireccionesCantidades");
		Vector<Albaran> alb = new Vector<Albaran>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT loc.qty, dir.nombreCalle, dir.ean"
				+ " FROM fac_orders_loc loc " +
						" INNER JOIN direccion dir ON dir.ean = loc.idLoc "
					+ " WHERE loc.idOrders = '" + idOrders + "'"
					+ " AND loc.idLin = '" + linNum + "'; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Albaran a = new Albaran();
				a.setDestino(rs.getString("nombreCalle"));
				a.setCantidadTotal(rs.getDouble("qty"));
				a.setCodigoAlbaran(rs.getString("ean"));
				alb.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		return alb;
	}

	//@Override
	public List<OrdersLin> getLineasPedido(String codigo) throws Exception {
		// System.out.println("DAO getDireccionesCantidades");
		List<OrdersLin> lineas = new ArrayList<OrdersLin>();
		try {
			con = bddConexion();
			// Las lineas que tengas todas las localizaciones procesadas no se
			// cargan
			String Qry =
				" SELECT lin.idOrders, lin.idLin, lin.qty21Cant, lin.linNum, lin.idLin as idProducto, p.codigoEan " +
				" FROM fac_orders_lin lin " +
					" INNER JOIN producto p ON p.idProducto = lin.idLin" +
					" INNER JOIN fac_orders o ON o.idOrders = lin.idOrders " +
					" INNER JOIN fac_orders_loc loc ON loc.idOrders = o.idOrders AND loc.idLin = lin.linNum " +
				" WHERE o.bgmNum = '" + codigo + "' "
					+ " AND loc.localizacionProcesada = 'N' " +
				" GROUP BY linNum ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrdersLin linea = new OrdersLin();
				linea.setIdOrders(rs.getLong("idOrders"));
				linea.setIdLin(rs.getString("codigoEan"));
				linea.setIdProducto(rs.getLong("idProducto"));
				String cantidad = rs.getString("qty21Cant");
				float f = Float.parseFloat(cantidad);
				int cant = (int) f;
				linea.setQty21Cant(cant + "");
				lineas.add(linea);
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
		return lineas;
	}

	/**
	 * @author andres (10/05/2011)
	 * @since 0.3
	 * @param estado
	 * @throws Exception
	 */
	public boolean actualizaEstadoPedido(String pedido, char estado) throws Exception {
		boolean resultado = false;
		Statement stmt;
		int res1 = 0;
		try {
			con = bddConexion();
			String insertString = "UPDATE fac_orders SET estado='" + estado
					+ "' WHERE bgmNum = '" + pedido + "'";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res1 = stmt.executeUpdate(insertString);
			if (res1 == 1) {
				// System.out.println("estado de pedido actualizado");
				resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	/**
	 * Método que devuelve el albaran para el reporte
	 */
	//@Override
	public Albaran getAlbaran(String codigo, boolean lineaCarrefour) throws Exception {
		Albaran alba = new Albaran();
		try {
			con = bddConexion();
			// Version nueva del albaran
			String qryInfoAlba =
				" SELECT al.idFormaPago, al.destino, al.fechaVencimiento, " +
					" fp.descripcion AS descripcionFormaPago, fpd.descripcion AS descripcionFormaPagoDesde, " +
					" rb.diasFormaPago, rb.diaPago, rb.numCuenta, " +
					" IF ( al.idFormaPago = 0, '', CONCAT (IF ( rb.diasFormaPago = 0,  '', CONCAT(' a ', rb.diasFormaPago, " +
						" IF(rb.idFormaPagoDesde = 0, 'dias. ', CONCAT(' dias desde '))) ))) AS formaDePagoI, " +
					" CONCAT ( " +
						" IF (rb.diaPago = 0, '', CONCAT(" +
							" ' Dia de cobro:  ', rb.diaPago, '.' " +
						" )), " +
						" IF (rb.numCuenta = '000000000000000000000000', '', CONCAT('. Cuenta bancaria: ', rb.numCuenta))" +
					" )  AS formaDePagoII,"
					+ " al.descripcionIvaAplicable, al.observaciones, "
					+ " tt.descripcion as descripcionTemperatura, tp.descripcion as descripcionPortes, "
					+ " tt.idTemperatura, tp.idPorte, al.numeroPedido, cl.apellidos as nombreEntrega, "
					+ " IF ("
					+ " (SELECT t.notas FROM telefono t WHERE t.idUsuario = cl.idUsuario GROUP BY idUsuario) = '',"
					+ " (SELECT t.numeroTelefono FROM telefono t WHERE t.idUsuario = cl.idUsuario GROUP BY idUsuario),"
					+ " CONCAT("
					+ " (SELECT t.numeroTelefono FROM telefono t WHERE t.idUsuario = cl.idUsuario GROUP BY idUsuario),"
					+ " '(', "
					+ " (SELECT t.notas FROM telefono t WHERE t.idUsuario = cl.idUsuario GROUP BY idUsuario), "
					+ " ')')"
					+ ") AS tfno,"
					+ " IF ("
						+ " al.idFormaPago = 0,"
						+ " '',"
						+ " CONCAT (fp.descripcion,"
							+ " IF ("
									+ " rb.diasFormaPago = 0, "
									+ " '',"
									/*+ " CONCAT(' a ', rb.diasFormaPago, ' dias.')"
							+ " ),"
							+ " IF ("
								+ " rb.diaPago = 0, "*/
									+ " CONCAT(' a ', rb.diasFormaPago, IF(rb.idFormaPagoDesde = 0, 'dias. ', CONCAT(' dias desde ', fpd.descripcion,'.'))) ), IF (rb.diaPago = 0, "
								+ " '',"
								+ " CONCAT(' Dia de cobro:  ', rb.diaPago, '.')"
							+ " ),"
							+ " IF(rb.numCuenta = '000000000000000000000000', '',"
								+ " 	CONCAT('. Cuenta bancaria: ', rb.numCuenta))"
							+ " )) AS formaDePago, "
					/*+ " IF (al.idFormaPago = 0, '', "
					+ " CONCAT(fp.descripcion, ' a ', rb.diasFormaPago, ' dias. Dia de cobro: ', rb.diaPago, "
					+ " IF(rb.numCuenta = '000000000000000000000000', '', CONCAT('. Cuenta bancaria: ', rb.numCuenta)))"
					+ " ) as formaDePago, "*/
					+ " al.fecha as fecha, al.fechaEntrega, al.usuarioResponsable, al.idAlbaran,"
					+ " al.idCliente, cl.nif as nifCliente,cl.ean as eanCliente, "
					//+ " IF (cl.apellidos = '', cl.nombre, CONCAT(cl.nombre, ' (', cl.apellidos, ')')) AS nombreCliente, "
					+ " cl.nombre AS nombreCliente, "
					+ " al.idTelefono, al.idDireccionCliente, al.idTransportista, "
					+ " (SELECT t.numeroTelefono FROM telefono t WHERE t.idTelefono = al.idTelefono) AS numeroTelefono, "
					+ " (SELECT CONCAT(d.nombreCalle, '. ' ,d.localidad) FROM direccion d WHERE d.idDireccion = al.idDireccionCliente) AS nombreDireccionCliente, "
					+ " (SELECT e.nombre FROM entidad e WHERE e.idUsuario = al.idTransportista) AS nombreTransportista, "
					+ " al.codigoAlbaran,al.pesoNetoTotal,al.numeroBultosTotal,al.cantidadTotal,"
					+ " (SELECT d.horarioEntrega FROM direccion d WHERE d.idDireccion = al.destino) AS horario,"
					+ " al.destino,al.importeTotal "
				+ " FROM albaran al, entidad cl, formapago fp, " +
						" referenciabancaria rb, transporte_portes tp, transporte_temperatura tt, formapago_desde fpd "
				+ " WHERE al.habilitado='S' AND al.idCliente = cl.idUsuario "
					+ " AND al.codigoAlbaran = '" + codigo + "' "
					+ " AND al.idFormaPago = rb.idDatoBancario "
					+ " AND rb.idFormaPago = fp.idFormaPago "
					+ " AND tp.idPorte = al.idPortesTransporte "
					+ " AND tt.idTemperatura = al.idTemperaturaTransporte "
				+ " GROUP BY cl.idUsuario";
			// System.out.println(qryInfoAlba);
			ps = con.prepareStatement(qryInfoAlba);
			rs = ps.executeQuery();
			while (rs.next()) {
				alba.setCodigoOrden(rs.getString("numeroPedido"));
				alba.setCodigoAlbaran(codigo);
				alba.setHorarioEntrega(rs.getString("horario"));
				alba.setTemperaturaTransporte(rs.getString("descripcionTemperatura"));
				alba.setPortes(rs.getString("descripcionPortes"));
				alba.setObservaciones(rs.getString("observaciones"));
				alba.setNifCliente(rs.getString("nifCliente"));
				//alba.setDescripcionFormaPago(rs.getString("formaDePago"));
				alba.setDescripcionFormaPago(rs.getString("descripcionFormaPago") + rs.getString("formaDePagoI"));
				int diasFormaPago = rs.getInt("diasFormaPago");
				int diaPago = rs.getInt("diaPago");
				String numCuenta = rs.getString("numCuenta");
				if (diasFormaPago > 0){
					alba.setDescripcionFormaPago(alba.getDescripcionFormaPago() + rs.getString("descripcionFormaPagoDesde"));
				}
				if (diaPago > 0 || !numCuenta.equals("000000000000000000000000")){
					alba.setDescripcionFormaPago(alba.getDescripcionFormaPago() + ". " + rs.getString("formaDePagoII"));
				}
				alba.setNombreCliente(rs.getString("nombreCliente"));
				alba.setDescripcionNombreEntrega(rs.getString("nombreEntrega"));
				if (alba.getDescripcionNombreEntrega() == null || alba.getDescripcionNombreEntrega().equals("")){
					alba.setDescripcionNombreEntrega(alba.getNombreCliente());
				}
				alba.setNombreTransportista(rs.getString("nombreTransportista"));
				alba.setIdTelefono(rs.getLong("idTelefono"));
				alba.setIdPortesTransporte(rs.getLong("idPorte"));
				alba.setIdTemperaturaTransporte(rs.getLong("idTemperatura"));
				alba.setIdDireccionCliente(rs.getLong("idDireccionCliente"));
				alba.setDestino(rs.getString("destino"));
				alba.setIdTransportista(rs.getLong("idTransportista"));
				alba.setIdFormaPago(rs.getLong("idFormaPago"));
				PreparedStatement psSub = null;
				ResultSet rsSub = null;
				String subQry =
					" SELECT *, p.nombre AS nombreProvincia " +
					" FROM direccion d, provincia p " +
					" WHERE d.idDireccion = '" + rs.getString("destino") + "' AND p.idProvincia = d.idProvincia";
				// System.out.println(subQry);
				psSub = con.prepareStatement(subQry);
				rsSub = psSub.executeQuery();
				while (rsSub.next()){
					String descripcionDestino = rsSub.getString("nombreCalle");
					String localidad = rsSub.getString("localidad");
					String municipio = rsSub.getString("municipio");
					String provincia = rsSub.getString("nombreProvincia");
					String codigoPostal = rsSub.getString("codigoPostal");
					if (localidad != null && !localidad.equals(""))
						descripcionDestino += ". " + localidad;
					if (municipio != null && !municipio.equals(""))
						descripcionDestino += ". " + municipio;
					if (codigoPostal != null && !codigoPostal.equals(""))
						descripcionDestino += ". CP.: " + codigoPostal;
					if (provincia != null && !provincia.equals(""))
						descripcionDestino += ". " + provincia;
					alba.setNombreCalle(descripcionDestino);
					alba.setHorario(rsSub.getString("horarioEntrega"));
				}
				subQry =
					" SELECT *, p.nombre AS nombreProvincia " +
					" FROM direccion d, provincia p " +
					" WHERE d.idDireccion = '" + alba.getIdDireccionCliente() + "' AND p.idProvincia = d.idProvincia ";
				// System.out.println(subQry);
				psSub = con.prepareStatement(subQry);
				rsSub = psSub.executeQuery();
				while (rsSub.next()){
					String descripcionDestino = rsSub.getString("nombreCalle");
					String localidad = rsSub.getString("localidad");
					String municipio = rsSub.getString("municipio");
					String provincia = rsSub.getString("nombreProvincia");
					String codigoPostal = rsSub.getString("codigoPostal");
					if (localidad != null && !localidad.equals(""))
						descripcionDestino += ". " + localidad;
					if (municipio != null && !municipio.equals(""))
						descripcionDestino += ". " + municipio;
					if (codigoPostal != null && !codigoPostal.equals(""))
						descripcionDestino += ". CP.: " + codigoPostal;
					if (provincia != null && !provincia.equals(""))
						descripcionDestino += ". " + provincia;
					alba.setDireccionCliente(descripcionDestino);
				}
				subQry =
					" SELECT * " +
					" FROM telefono t " +
					" WHERE t.idTelefono = '" + alba.getIdTelefono() + "'";
				// System.out.println(subQry);
				psSub = con.prepareStatement(subQry);
				rsSub = psSub.executeQuery();
				while (rsSub.next()){
					alba.setNumeroTelefono(rsSub.getString("numeroTelefono"));
					//alba.setDireccionCliente(rsSub.getString("nombreCalle") + ". " + rsSub.getString("localidad"));
				}
				//alba.setDescripcionFormaPago(rs.getString("descripcionFormaPago"));
				String[] frag = alba.getDescripcionFormaPago().split("Cuenta bancaria: ");
				if (frag.length > 1){
					String cuentaBanco = frag[1];
					//4 primeros: IBAN
					//Mostrar los 4 siguientes (banco) y los 4 últimos
					if (cuentaBanco != null && cuentaBanco.length() == 24){
						String cuentaOculta = cuentaBanco.substring(0, 8);
						String subString = "************";//12 *
						cuentaOculta += subString;
						cuentaOculta += cuentaBanco.substring(20);
						//Tenemos que buscar el número de cuenta en la cadena y sustituirlo por cuentaOculta
						String nuevaDescripcionFormaPago = frag[0] + "Cuenta bancaria: " + cuentaOculta;
						alba.setDescripcionFormaPago(nuevaDescripcionFormaPago);
					}
				}
				alba.setIdCliente(rs.getInt("idCliente"));
				alba.setIdAlbaran(rs.getLong("idAlbaran"));
				java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
				Date f = rs.getDate("fechaEntrega");
				String fecha = formato.format(f);
				alba.setFecha(fecha);
				f = rs.getDate("fechaVencimiento");
				String fechaVencimiento = "";
				if (f != null && !f.equals(""))
					fechaVencimiento = formato.format(f);
				alba.setFechaVencimiento(fechaVencimiento);
				String fechaEntrega = formato.format(rs.getDate("fechaEntrega"));
				alba.setFechaEntrega(fechaEntrega);
				if (lineaCarrefour){
					//Es carrefour, preparamos la cadena exclusiva para carrefour
					String cadenaCarrefour = "";
					if (alba.getCodigoOrden() != null && !alba.getCodigoOrden().equals("")){
						cadenaCarrefour = "Numero de pedido: " + alba.getCodigoOrden() + ". ";
					}
					cadenaCarrefour += "Fecha entrega: " + fecha + ".";
					if (rs.getString("horario") != null && !rs.getString("horario").equals("")){
						cadenaCarrefour += "<br />Horario: " + rs.getString("horario") + ". ";
					}
					
					alba.setCadenaCarrefour(cadenaCarrefour);
				}
				alba.setIvaAplicable(rs.getString("descripcionIvaAplicable"));
				alba.setUsuarioResponsable(rs.getString("usuarioResponsable"));
				alba.setPesoNetoTotal(rs.getDouble("pesoNetoTotal"));
				alba.setNumeroAgrupacionesTotal(rs.getDouble("numeroBultosTotal"));
				alba.setCantidadTotal(rs.getDouble("cantidadTotal"));
				alba.setImporteTotal(rs.getDouble("importeTotal"));
				String QryRegistrosSalida =
					" SELECT DISTINCT al.idCliente, cl.idUsuario, dir.empresa_idUsuario, bl.lote, pr.peso, " +
						" SUM(bl.cantidad) AS cantidad, b.idBulto, b.direccionEntrega, dir.nombreCalle, " +
						" dir.horarioEntrega, ad.linNum, ad.codigoSalida,pr.idProducto, " +
						" pr.codigoEan,pr.nombre as descProducto, ad.pesoNeto, ad.numeroBultos, ad.cantidadUnitaria, " +
						" ad.precioUnitario, importe as precioTotalLinea, ad.descripcionPrecioKilo, pr.mostrar, ad.importe " +
					" FROM albaran al " +
						" LEFT JOIN albaran_detalle ad ON al.codigoAlbaran = ad.codigoAlbaran " +
						" LEFT JOIN entidad cl ON al.idCliente = cl.idUsuario " +
						" LEFT JOIN producto pr ON pr.idProducto = ad.idProducto " +
						" LEFT JOIN registrosalida rs ON rs.idProducto = pr.idProducto " +
						" LEFT JOIN bulto b ON rs.codigoSalida = b.codigoSalida " +
						" LEFT JOIN direccion dir ON dir.idDireccion = b.direccionEntrega " +
						" LEFT JOIN bulto_lotes bl ON bl.idBulto = b.idBulto " +
					" WHERE al.habilitado='S' AND al.codigoAlbaran = '" + codigo + "' AND al.tipoAlbaran='O' " +
							" AND dir.empresa_idUsuario = cl.idUsuario AND b.codigoSalida = ad.codigoSalida GROUP BY bl.lote;";
				// System.out.println(QryRegistrosSalida);
				PreparedStatement ps2 = con.prepareStatement(QryRegistrosSalida);
				ResultSet rs2 = ps2.executeQuery();
				double pesoTotal = 0;
				List<RegistroSalida> graneles = new ArrayList<RegistroSalida>();
				List<RegistroSalida> itemsAgrupaciones = new ArrayList<RegistroSalida>();
				boolean primero = true;
				long contador = 1;
				while (rs2.next()) {
					boolean modificado = false;
					RegistroSalida salida = new RegistroSalida();
					salida.setNumLinea(contador);
					contador++;
					salida.setIdBulto(rs2.getLong("idBulto"));
					salida.setCodigoSalida(rs2.getString("codigoSalida"));
					salida.setCodigoEan(rs2.getString("codigoEan"));
					salida.setIdProducto(rs2.getLong("idProducto"));
					long mostrar = rs2.getLong("mostrar");
					boolean granel = false;
					if (mostrar == 1)
						granel = true;
					salida.setPrecioUnitario(rs2.getDouble("precioUnitario"));
					salida.setPrecioUnitario(Math.rint(salida.getPrecioUnitario() * 1000)/1000);
					salida.setDescripcion(rs2.getString("descProducto"));
					salida.setNumeroBultos(rs2.getLong("cantidad"));
					
					pesoTotal += rs2.getDouble("peso") * salida.getNumeroBultos();
					
					salida.setCantidadUnitaria(rs2.getDouble("cantidadUnitaria"));
					salida.setLote(rs2.getString("lote"));
					boolean noAgrupacion = false;
					if (salida.getLote().charAt(0) == 'E') {
						String consulta =
							" SELECT re.lote "
							+ " FROM registroentrada re "
							+ " WHERE re.codigoEntrada = ?; ";
						PreparedStatement ps3 = con.prepareStatement(consulta);
						ps3.setString(1, salida.getLote());
						ResultSet rs3 = ps3.executeQuery();
						if (rs3.next()) {
							String loteEntrada = rs3.getString("lote");
							if (!loteEntrada.equals(""))
								salida.setLote(salida.getLote() + " (" + loteEntrada + ")");
						}
						//Comprobamos si el producto final es una agrupación o no
						consulta =
							" SELECT * " +
							" FROM producto_compuesto pc " +
							" WHERE pc.idProducto = ?; ";
						ps3 = con.prepareStatement(consulta);
						ps3.setLong(1, salida.getIdProducto());
						rs3 = ps3.executeQuery();
						if (rs3.next()) {
							noAgrupacion = false;
						}else{
							noAgrupacion = true;
						}
					} else{
						if (salida.getLote().charAt(0) == '0') {
							// Comprobamos si el lote corresponde a una
							// agrupación
							String qry =
								" SELECT DISTINCT gpagrupa.idAgrupacion, gpe.idProducto " +
								" FROM gp_envasado gpe, gp_envasado_detalle gpdeta, gp_envasado_agrupacion gpagrupa " +
								" WHERE gpe.orden = gpdeta.orden " +
									" AND gpagrupa.ordenEnvasado = gpe.orden " +
									" AND gpe.lote = '" + salida.getLote() + "'";
							// System.out.println(qry);
							ps = con.prepareStatement(qry);
							rs = ps.executeQuery();
							long idAgrupacion = 0;
							long idProducto = 0;
							if (rs.next()) {
								idAgrupacion = rs.getLong("idAgrupacion");
								idProducto = rs.getLong("idProducto");
							}
							if (idAgrupacion > 0) {
								// Se agrupó.
								double numBultos = salida.getNumeroBultos();
								double cantidadProductoAgrupacion = 0;
								//double peso = 0;
								qry = " SELECT pc.cantidad, p.peso "
										+ " FROM producto p, producto_compuesto pc "
										+ " WHERE pc.idProducto = "
										+ idAgrupacion
										+ " AND pc.idCompuestoDe = "
										+ idProducto
										+ " AND p.idProducto = pc.idCompuestoDe";
								System.out.println(qry);
								ps = con.prepareStatement(qry);
								rs = ps.executeQuery();
								if (rs.next()) {
									cantidadProductoAgrupacion = rs.getDouble("cantidad");
									//peso = rs.getDouble("peso");
								}
								double cantidadNueva = cantidadProductoAgrupacion * numBultos;
								//double pesoNuevo = peso * cantidadNueva;
								//salida.setCantidad(cantidadNueva);
								salida.setCantidad((double)numBultos);
								salida.setPesoNeto(cantidadNueva);
								modificado = true;
							}else{
								noAgrupacion = true;
							}
							String consulta =
								" SELECT re.lote "
								+ " FROM registroentrada re, gp_envasado gpe, gp_envasado_detalle deta "
								+ " WHERE gpe.lote = '" + salida.getLote() + "' "
									+ " AND re.codigoEntrada = deta.codigoEntrada "
									+ " AND deta.orden = gpe.orden "
									+ " AND deta.idTipoRegistro = 'M'";
							PreparedStatement ps3 = con.prepareStatement(consulta);
							ResultSet rs3 = ps3.executeQuery();
							if (rs3.next()) {
								String loteEntrada = rs3.getString("lote");
								if (!loteEntrada.equals(""))
									salida.setLote(salida.getLote() + " (" + loteEntrada + ")");
							}
						}
					}
					if (!modificado) {
						salida.setCantidad(rs2.getDouble("cantidad"));
						if (!granel && noAgrupacion){
							salida.setPesoNeto(salida.getCantidad());
							salida.setPrecioTotal(salida.getCantidad() * salida.getPrecioUnitario());
							salida.setPrecioTotal(Math.rint(salida.getPrecioTotal() * 1000)/1000);
						}else{
							salida.setPesoNeto(rs2.getDouble("peso") * salida.getCantidad());
						}
					}
					salida.setPrecioTotal(salida.getPesoNeto() * salida.getPrecioUnitario());
					salida.setPrecioTotal(Math.rint(salida.getPrecioTotal() * 1000)/1000);
					if (primero) {
						alba.setDireccionEntrega(rs2.getLong("direccionEntrega"));
					}
					if (granel){
						graneles.add(salida);
					}else{
						if (noAgrupacion){
							//Si es un producto (no granel), y no es una agrupación, bultos = unidades
							//En peso neto van las unidades
							salida.setPesoNeto(salida.getCantidad());
							salida.setPrecioTotal(salida.getCantidad() * salida.getPrecioUnitario());
							salida.setPrecioTotal(Math.rint(salida.getPrecioTotal() * 1000)/1000);
						}
						itemsAgrupaciones.add(salida);
					}
					primero = false;
				}
				// alba.setImporteTotal(importeTotal);
				alba.setGraneles(graneles);
				alba.setItemsAgrupaciones(itemsAgrupaciones);
				String consulta =
					" SELECT e.lopdFactura " +
					" FROM empresa e " +
					" WHERE e.idEmpresa = 1";
				PreparedStatement ps3 = con.prepareStatement(consulta);
				ResultSet rs3 = ps3.executeQuery();
				if (rs3.next()) {
					String lopd = rs3.getString("lopdFactura");
					alba.setLopd(lopd);
				}
				if (alba.getPesoNetoTotal() == 0)
					alba.setPesoNetoTotal(pesoTotal);
			}
			if ((alba.getItemsAgrupaciones() != null && alba.getItemsAgrupaciones().size() > 0) &&
					(alba.getGraneles() != null && alba.getGraneles().size() > 0)){
				alba.setZonasImprimir(3);
			}else{
				if (alba.getItemsAgrupaciones() != null && alba.getItemsAgrupaciones().size() > 0){
					alba.setZonasImprimir(1);
				}else{
					if (alba.getGraneles() != null && alba.getGraneles().size() > 0){
						alba.setZonasImprimir(2);
					}
				}
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
		return alba;
	}

	//@Override
	public Factura getFactura(String codigo) throws Exception {
		Factura factura = new Factura();
		factura.setCodigoFactura(codigo);
		try {
			PreparedStatement ps2 = null;
			ResultSet rs2 = null;
			con = bddConexion();
			String qryInfoFac =
				" SELECT DISTINCT " +
				" fp.descripcion AS descripcionFormaPago, fpd.descripcion AS descripcionFormaPagoDesde, rb.diasFormaPago, rb.diaPago, rb.numCuenta, " +
				" IF ( al.idFormaPago = 0, '', " +
					" CONCAT (IF ( rb.diasFormaPago = 0,  '', " +
					" CONCAT(' a ', rb.diasFormaPago, " +
					" IF(rb.idFormaPagoDesde = 0, 'dias. ', CONCAT(' dias desde '))) ))) AS formaDePagoI, " +
				" CONCAT ( " +
					" IF (rb.diaPago = 0, '', CONCAT( " +
					" 'Dia de cobro:  ', rb.diaPago, '.'  " +
					" )), " +
					" IF (rb.numCuenta = '000000000000000000000000', '', CONCAT('. Cuenta bancaria: ', rb.numCuenta))) AS formaDePagoII," +
				" fa.idFactura AS num_factura, fa.fecha AS fecha, fa.observaciones, fa.idFormaPago, "
					+ " fa.importeTotal AS importe_total, fa.descuento AS descuento, fa.valorIva AS valor_iva, "
					+ " fa.subtotal AS subtotal, fa.valorDescuento, fa.ivaAplicable, fa.cargosTotal AS cargos_total, "
					+ " fa.total AS total, fa.fechaVencimiento AS fecha_vencimiento, fa.nombreCliente AS nombre_cliente, "
					+ " en.nif AS nif_cliente, en.idUsuario as idCliente, en.ean AS ean_cliente, fa.telefonoCliente, "
					+ " fa.idDestino, "
						+ " IF (en.nombre != '', CONCAT (en.nombre, ' ',en.apellidos), en.apellidos) AS nombreCliente "
				+ " FROM factura fa, albaran_factura af, albaran al, entidad en, formapago fp, referenciabancaria rb, formapago_desde fpd "
				+ " WHERE fa.idFactura = af.idFactura " +
						" AND en.idUsuario = al.idCliente " +
						" AND af.idAlbaran = al.idAlbaran " +
						" AND rb.idDatoBancario = al.idFormaPago " +
						" AND rb.idFormaPago = fp.idFormaPago " +
						" AND fpd.idFormaPagoDesde = rb.idFormaPagoDesde " +
						" AND fa.codigoFactura = '" + codigo + "'";
			// System.out.println(qryInfoFac);
			ps = con.prepareStatement(qryInfoFac);
			rs = ps.executeQuery();
			if (rs.next()) {
				// Factura normal, asociada a albarán
				factura.setIdFactura(rs.getLong("num_factura"));
				
				// Damos formato a las fechas
				java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
				String fecha = formato.format(rs.getDate("fecha"));
				factura.setFecha(fecha);
				fecha = formato.format(rs.getDate("fecha_vencimiento"));
				factura.setFechaVencimiento(fecha);
				factura.setImporteTotal(rs.getDouble("importe_total"));
				factura.setDescuento(rs.getDouble("descuento"));
				factura.setValorDescuento(rs.getDouble("valorDescuento"));
				factura.setIvaAplicable(rs.getDouble("ivaAplicable"));
				factura.setValorIva(rs.getDouble("valor_iva"));
				factura.setSubtotal(rs.getDouble("subtotal"));
				factura.setCargosTotal(rs.getDouble("cargos_total"));
				factura.setObservaciones(rs.getString("observaciones"));
				factura.setTotal(rs.getDouble("total"));
				factura.setNombreCliente(rs.getString("nombreCliente"));
				factura.setNifCliente(rs.getString("nif_cliente"));
				factura.setIdCliente(rs.getLong("idCliente"));
				factura.setEanCliente(rs.getString("ean_cliente"));
				factura.setDescripcionFormaPago(rs.getString("descripcionFormaPago") + rs.getString("formaDePagoI"));
				int diasFormaPago = rs.getInt("diasFormaPago");
				int diaPago = rs.getInt("diaPago");
				String numCuenta = rs.getString("numCuenta");
				if (diasFormaPago > 0){
					factura.setDescripcionFormaPago(factura.getDescripcionFormaPago() + rs.getString("descripcionFormaPagoDesde"));
				}
				if (diaPago > 0 || !numCuenta.equals("000000000000000000000000")){
					factura.setDescripcionFormaPago(factura.getDescripcionFormaPago() + ". " + rs.getString("formaDePagoII"));
				}
				factura.setIdFormaPago(rs.getLong("idFormaPago"));
				factura.setIdDestino(rs.getLong("idDestino"));
				String subQry =
					" SELECT *, p.nombre AS nombreProvincia " +
					" FROM direccion d, provincia p " +
					" WHERE d.idDireccion = '" + factura.getIdDestino() + "' AND p.idProvincia = d.idProvincia";
				// System.out.println(subQry);
				PreparedStatement psSub = con.prepareStatement(subQry);
				ResultSet rsSub = psSub.executeQuery();
				while (rsSub.next()){
					String descripcionDestino = rsSub.getString("nombreCalle");
					String localidad = rsSub.getString("localidad");
					String municipio = rsSub.getString("municipio");
					String provincia = rsSub.getString("nombreProvincia");
					if (localidad != null && !localidad.equals(""))
						descripcionDestino += ". " + localidad;
					if (municipio != null && !municipio.equals(""))
						descripcionDestino += ". " + municipio;
					if (provincia != null && !provincia.equals(""))
						descripcionDestino += ". " + provincia;
					factura.setDescripcionDestino(descripcionDestino);
				}
				String[] frag = factura.getDescripcionFormaPago().split("Cuenta bancaria: ");
				if (frag.length > 1){
					String cuentaBanco = frag[1];
					//4 primeros: IBAN
					//Mostrar los 4 siguientes (banco) y los 4 últimos
					String cuentaOculta = cuentaBanco.substring(0, 8);
					String subString = "************";//12 *
					cuentaOculta += subString;
					cuentaOculta += cuentaBanco.substring(20);
					//Tenemos que buscar el número de cuenta en la cadena y sustituirlo por cuentaOculta
					String nuevaDescripcionFormaPago = frag[0] + "Cuenta bancaria: " + cuentaOculta;
					factura.setDescripcionFormaPago(nuevaDescripcionFormaPago);
				}
				factura.setTelefonoCliente(rs.getString("telefonoCliente"));
				String QryLineasFactura =
					" SELECT DISTINCT fi.codigoItem, fi.idItem, fi.descripcion, p.idProducto, p.codigoEan, "
						+ " fi.cantidad, fi.precioUnitBruto, fi.precioUnitNeto, fi.precioTotal, "
						+ " fi.agrupaciones, fi.peso, fi.precioKilo, p.mostrar "
					+ " FROM factura_item fi, producto p "
					+ " WHERE fi.idFactura='"
						+ factura.getIdFactura()
						+ "' "
						+ " AND p.idProducto = fi.idProducto "
					+ " ORDER BY fi.idItem";
				// System.out.println(QryLineasFactura);
				ps2 = con.prepareStatement(QryLineasFactura);
				rs2 = ps2.executeQuery();
				List<ItemFactura> items = new ArrayList<ItemFactura>();
				List<ItemFactura> graneles = new ArrayList<ItemFactura>();
				List<ItemFactura> itemsAgrupaciones = new ArrayList<ItemFactura>();
				while (rs2.next()) {
					ItemFactura item = new ItemFactura();
					item.setIdItem(rs2.getLong("idItem"));
					item.setDescripcion(rs2.getString("descripcion"));
					//item.setCodigoItem(rs2.getString("codigoEan"));
					item.setLote(rs2.getString("codigoItem"));
					item.setIdProducto(rs2.getLong("idProducto"));
					item.setCantidad(rs2.getDouble("cantidad"));
					item.setPrecioUnitBruto(rs2.getDouble("precioUnitBruto"));
					item.setPrecioUnitNeto(rs2.getDouble("precioUnitNeto"));
					item.setPrecioTotal(rs2.getDouble("precioTotal"));
					item.setAgrupaciones(rs2.getLong("agrupaciones"));
					item.setPeso(rs2.getDouble("peso"));
					item.setCodigoItem(rs2.getString("codigoEan"));
					
					String qry =
						" SELECT f.idFactura, a.codigoAlbaran, a.fechaEntrega " +
						" FROM factura f " +
						" LEFT JOIN albaran_factura af ON f.idFactura = af.idFactura " +
						" LEFT JOIN albaran a ON a.idAlbaran = af.idAlbaran " +
						" WHERE f.idFactura = " + factura.getIdFactura();
					PreparedStatement aux = con.prepareStatement(qry);
					ResultSet rsAux = aux.executeQuery();
					if (rsAux.next()){
						item.setAlbaran(rsAux.getString("codigoAlbaran"));
						
						String fechaStr = rsAux.getString("fechaEntrega");
						if (fechaStr != null && !fechaStr.equals("")){
							String fragFecha[] = fechaStr.split("-");
							fechaStr = fragFecha[2] + "/" + fragFecha[1] + "/" + fragFecha[0];
							item.setFechaEntrega(fechaStr);
						}
					}
					
					item.setPrecioKilo(rs2.getDouble("precioKilo"));
					long mostrar = rs2.getLong("mostrar");
					boolean granel = false;
					if (mostrar == 1)
						granel = true;
					if (granel){
						graneles.add(item);
					}else{
						itemsAgrupaciones.add(item);
					}
					//items.add(item);
				}
				factura.setItemsAgrupaciones(itemsAgrupaciones);
				factura.setGraneles(graneles);
				factura.setItems(items);
			} else {
				// Factura libre, no está asociada a ningún albarán
				qryInfoFac = " SELECT DISTINCT fa.idFactura AS num_factura, fa.fecha AS fecha, fa.descripcionDestino, "
						+ " fa.importeTotal AS importe_total, fa.descuento AS descuento, fa.valorIva AS valor_iva, "
						+ " fa.subtotal AS subtotal, fa.cargosTotal AS cargos_total, fa.total AS total, "
						+ " fa.fechaVencimiento AS fecha_vencimiento, fa.nombreCliente AS nombre_cliente, "
						+ " fa.nifCliente AS nif_cliente, fa.idCliente, fa.descripcionFormaPago as formaDePago, "
						+ " fa.telefonoCliente, fa.ivaAplicable, fa.valorDescuento, fa.observaciones "
						+ " FROM factura fa "
						+ " WHERE fa.codigoFactura = '"
						+ codigo + "'";
				// System.out.println(qryInfoFac);
				ps = con.prepareStatement(qryInfoFac);
				rs = ps.executeQuery();
				if (rs.next()) {
					factura.setIdFactura(rs.getLong("num_factura"));
					// Damos formato a las fechas
					java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
					String fecha = formato.format(rs.getDate("fecha"));
					factura.setFecha(fecha);
					fecha = formato.format(rs.getDate("fecha_vencimiento"));
					factura.setFechaVencimiento(fecha);
					factura.setImporteTotal(rs.getDouble("importe_total"));
					factura.setDescuento(rs.getDouble("descuento"));
					factura.setObservaciones(rs.getString("observaciones"));
					factura.setValorDescuento(rs.getDouble("valorDescuento"));
					factura.setIvaAplicable(rs.getDouble("ivaAplicable"));
					factura.setValorIva(rs.getDouble("valor_iva"));
					factura.setSubtotal(rs.getDouble("subtotal"));
					factura.setCargosTotal(rs.getDouble("cargos_total"));
					factura.setTotal(rs.getDouble("total"));
					factura.setNombreCliente(rs.getString("nombre_cliente"));
					factura.setNifCliente(rs.getString("nif_cliente"));
					factura.setIdCliente(rs.getLong("idCliente"));
					factura.setDescripcionFormaPago(rs.getString("formaDePago"));
					factura.setDescripcionDestino(rs.getString("descripcionDestino"));
					factura.setTelefonoCliente(rs.getString("telefonoCliente"));
					/* Consultar los items de la factura */
				}
				String QryLineasFactura =
					"SELECT DISTINCT fi.codigoItem, fi.idItem, fi.descripcion,fi.idProducto, " +
						" fi.cantidad, fi.precioUnitBruto, fi.precioUnitNeto,  fi.precioTotal, " +
						" fi.agrupaciones, fi.peso, fi.precioKilo " +
					" FROM factura_item fi " +
					" WHERE fi.idFactura='" + factura.getIdFactura() + "' " +
					" ORDER BY fi.idItem";
				// System.out.println(QryLineasFactura);
				ps2 = con.prepareStatement(QryLineasFactura);
				rs2 = ps2.executeQuery();
				List<ItemFactura> items = new ArrayList<ItemFactura>();
				while (rs2.next()) {
					ItemFactura item = new ItemFactura();
					item.setIdItem(rs2.getLong("idItem"));
					item.setDescripcion(rs2.getString("descripcion"));
					item.setCantidad(rs2.getDouble("cantidad"));
					item.setPrecioUnitBruto(rs2.getDouble("precioUnitBruto"));
					item.setPrecioUnitNeto(rs2.getDouble("precioUnitNeto"));
					item.setPrecioTotal(rs2.getDouble("precioTotal"));
					item.setAgrupaciones(rs2.getLong("agrupaciones"));
					item.setIdProducto(rs2.getLong("idProducto"));
					item.setPeso(rs2.getDouble("peso"));
					item.setLote(rs2.getString("codigoItem"));
					item.setPrecioKilo(rs2.getDouble("precioKilo"));
					items.add(item);
				}
				factura.setItems(items);
			}
			String QryCargosFactura =
				" SELECT * " + " FROM factura_cargo fc "
					+ " WHERE fc.idFactura = " + factura.getIdFactura();
			// System.out.println(QryCargosFactura);
			ps2 = con.prepareStatement(QryCargosFactura);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				String tipoCargo = rs2.getString("tipoCargo");
				if (tipoCargo.equals("T")) {
					factura.setCargoTran(rs2.getDouble("valorCargo"));
					factura.setIvaCargoTran(rs2.getDouble("ivaCargo"));
					factura.setTotalCargoTran(rs2.getDouble("totalCargo"));
				} else {
					if (tipoCargo.equals("B")) {
						factura.setCargoBanc(rs2.getDouble("valorCargo"));
						factura.setIvaCargoBanc(rs2.getDouble("ivaCargo"));
						factura.setTotalCargoBanc(rs2.getDouble("totalCargo"));
					} else {
						if (tipoCargo.equals("D")) {
							factura.setCargoDevo(rs2.getDouble("valorCargo"));
							factura.setIvaCargoDevo(rs2.getDouble("ivaCargo"));
							factura.setTotalCargoDevo(rs2.getDouble("totalCargo"));
						}
					}
				}
			}
			String QryCuotasFactura =
				" SELECT *, f.estado AS idEstadoCuota, fe.nombre as nombreEstadoCuota " +
				" FROM factura_cuotas fc, factura f, fac_orders_estados fe " +
				" WHERE fc.codigoFactura = '" + factura.getCodigoFactura() + "' " +
					" AND fc.codigoNuevaFactura = f.codigoFactura " +
					" AND fe.idEstado = f.estado";
			// System.out.println(QryCuotasFactura);
			ps2 = con.prepareStatement(QryCuotasFactura);
			rs2 = ps2.executeQuery();
			List<CuotaFactura> cuotas = new ArrayList<CuotaFactura>();
			while (rs2.next()) {
				CuotaFactura cuota = new CuotaFactura();
				cuota.setCodigoFactura(rs2.getString("codigoFactura"));
				cuota.setEstado(rs2.getString("nombreEstadoCuota"));
				cuota.setIdEstado(rs2.getLong("idEstadoCuota"));
				String fecha = rs2.getString("fecha");// aaaa-mm-dd
				String[] frag = fecha.split("-");
				cuota.setFecha(frag[2] + "/" + frag[1] + "/" + frag[0]);
				cuota.setImporte(rs2.getDouble("importe"));
				cuota.setCodigoNuevaFactura(rs2.getString("codigoNuevaFactura"));
				cuota.setNumeroCuota(rs2.getLong("idCuotaFactura"));
				cuota.setObservaciones(rs2.getString("observaciones"));
				cuota.setPorcentaje(rs2.getDouble("porcentaje"));
				cuotas.add(cuota);
			}
			factura.setCuotas(cuotas);
			QryCuotasFactura =
				" SELECT * " + " FROM factura_cuotas fc " +
				" WHERE fc.codigoNuevaFactura = '" + factura.getCodigoFactura() + "'";
			// System.out.println(QryCuotasFactura);
			ps2 = con.prepareStatement(QryCuotasFactura);
			rs2 = ps2.executeQuery();
			String esCuota = "no";
			if (rs2.next()) {
				esCuota = "si";
			}
			String consulta =
				" SELECT e.lopdFactura "
				+ " FROM empresa e "
				+ " WHERE e.idEmpresa = 1";
				PreparedStatement ps3 = con.prepareStatement(consulta);
				ResultSet rs3 = ps3.executeQuery();
				if (rs3.next()) {
					String lopd = rs3.getString("lopdFactura");
					factura.setLopd(lopd);
				}
			factura.setEsCuota(esCuota);
			if (factura.getItemsAgrupaciones().size() > 0 && factura.getGraneles().size() > 0){
				factura.setZonasImprimir(3);
			}else{
				if (factura.getItemsAgrupaciones().size() > 0){
					factura.setZonasImprimir(1);
				}else{
					if (factura.getGraneles().size() > 0){
						factura.setZonasImprimir(2);
					}
				}
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
		return factura;
	}

	//@Override
	public Albaran unirAlbaranes(List<String> codigosAlbaranes, DTM dtm) throws Exception {
		// Creamos un nuevo albaran
		Albaran albaran = new Albaran();
		String codigoNuevoAlbaran = this.getCodigoAlbaran();
		albaran.setCodigoAlbaran(codigoNuevoAlbaran);
		try {
			con = bddConexion();
			double pesoNetoTotal = 0, cantidadTotal = 0, importeTotal = 0;
			int numeroBultosTotal = 0;
			long idFormaPago = 0, idCliente = 0;
			String cadena = "";// 'P19', 'P20', 'P21'
			for (int i = 0; i < codigosAlbaranes.size(); i++) {
				cadena += "'" + codigosAlbaranes.get(i) + "'";
				if (i < codigosAlbaranes.size() - 1)
					cadena += ", ";
			}
			String qry =
				" SELECT SUM(a.pesoNetoTotal) AS pesoNetoTotal, "
					+ " SUM(a.numeroBultosTotal) AS numeroBultosTotal, "
					+ " SUM(a.cantidadTotal) AS cantidadTotal, "
					+ " SUM(a.importeTotal) AS importeTotal, "
					+ " a.idCliente, a.idFormaPago " +
				" FROM albaran a " +
				" WHERE a.codigoAlbaran IN(" + cadena + ")";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				idFormaPago = rs.getLong("idFormaPago");
				pesoNetoTotal = rs.getDouble("pesoNetoTotal");
				numeroBultosTotal = rs.getInt("numeroBultosTotal");
				cantidadTotal = rs.getDouble("cantidadTotal");
				importeTotal = rs.getDouble("importeTotal");
				idCliente = rs.getLong("idCliente");
			}
			albaran.setIdFormaPago(idFormaPago);
			albaran.setPesoNetoTotal(pesoNetoTotal);
			albaran.setNumeroBultos(numeroBultosTotal);
			albaran.setCantidadTotal(cantidadTotal);
			albaran.setImporteTotal(importeTotal);
			albaran.setIdCliente(idCliente);

			ps = con.prepareStatement(" SELECT MAX(idAlbaran) AS idMaxAlbaran FROM albaran; ");
			rs = ps.executeQuery();
			long idAlbaranMax = 0;
			if (rs.next()) {
				Albaran albaranMax = new Albaran();
				albaranMax.setIdAlbaran(rs.getLong("idMaxAlbaran"));
				idAlbaranMax = albaranMax.getIdAlbaran() + 1;
			} else
				idAlbaranMax = 1;
			albaran.setIdAlbaran(idAlbaranMax);
			
			long destino = 0;
			ps = con.prepareStatement(" SELECT destino FROM albaran WHERE codigoAlbaran='" + codigosAlbaranes.get(0) + "'; ");
			rs = ps.executeQuery();
			if (rs.next()) {
				destino = rs.getLong("destino");
			}

			ps = con.prepareStatement(" SELECT e.nif, e.nombre, e.apellidos FROM entidad e WHERE idUsuario = " + idCliente + "; ");
			rs = ps.executeQuery();
			if (rs.next()) {
				albaran.setNifCliente(rs.getString("nif"));
				albaran.setNombreCliente(rs.getString("nombre") + " " + rs.getString("apellidos"));
			}			
			
			ps = con.prepareStatement(" SELECT t.numeroTelefono, t.tipoTelefono, t.notas FROM telefono t WHERE idUsuario = " + idCliente + " LIMIT 1; ");
			rs = ps.executeQuery();
			if (rs.next()) {
				albaran.setNumeroTelefono(rs.getString("numeroTelefono") + " (" + rs.getString("tipoTelefono") + ")");
			}
			
			Statement stmt;
			// idFormaPago es el idDatoBancario en referenciabancaria
			String insertString =
				" INSERT INTO albaran(idAlbaran, idCliente, fecha, codigoAlbaran, pesoNetoTotal,"
					+ " numeroBultosTotal, cantidadTotal, importeTotal, usuarioResponsable, "
					+ " habilitado, facturado, estado, idFormaPago, destino, tipoAlbaran) "
				+ " VALUES(" + "'"
					+ idAlbaranMax
					+ "','"
					+ idCliente
					+ "','"
					+ dtm.getDtmFech()
					+ "','"
					+ codigoNuevoAlbaran
					+ "','"
					+ pesoNetoTotal
					+ "','"
					+ numeroBultosTotal
					+ "','"
					+ cantidadTotal
					+ "','"
					+ importeTotal
					+ "','"
					+ "admin"
					+ "','"
					+ "S"
					+ "','"
					+ "N"
					+ "','"
					+ "O"
					+ "','"
					+ idFormaPago + "', "
					+ destino
					+ ", 'O'"
				+ " ); ";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			long numeroLinea = 1;
			for (int i = 0; i < codigosAlbaranes.size(); i++) {
				String codigoAlbaran = codigosAlbaranes.get(i);
				int cuantasLineas = 0;
				qry = " SELECT COUNT(codigoAlbaran) as cuenta "
						+ " FROM albaran_detalle " + " WHERE codigoAlbaran = '"
						+ codigoAlbaran + "'";
				// System.out.println(qry);
				ps = con.prepareStatement(qry);
				rs = ps.executeQuery();
				if (rs.next()) {
					cuantasLineas = rs.getInt("cuenta");
				}
				for (int j = 0; j < cuantasLineas; j++) {
					ps = con.prepareStatement("SELECT MAX(idRegistroSalida) AS idRegistroSalidaMax FROM albaran_detalle");
					rs = ps.executeQuery();
					long idRegistroSalida = 0;
					if (rs.next()) {
						idRegistroSalida = rs.getLong("idRegistroSalidaMax") + 1;
					} else
						idRegistroSalida = 1;
					String insertar =
						" INSERT INTO albaran_detalle " +
							" SELECT '" + idRegistroSalida + "' AS idRegistroSalida, idAlbaran, '" + numeroLinea + "' AS linNum, " + "'" + codigoNuevoAlbaran + "' AS codigoAlbaran, " +
									" codigoSalida, idProducto, pesoNeto, numeroBultos, cantidadUnitaria, precioUnitario, importe, usuarioResponsable, descripcionPrecioKilo " +
							" FROM albaran_detalle WHERE codigoAlbaran = '" + codigoAlbaran + "' AND linNum = " + (j + 1);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertar);
					numeroLinea++;
				}
			}
			//TODO: En albaran, meter las lineas del albaran
			//Buscamos las lineas del albarán
			String QryRegistrosSalida =
				" SELECT DISTINCT al.idCliente, cl.idUsuario, dir.empresa_idUsuario, bl.lote, pr.peso, bl.cantidad, b.idBulto, b.direccionEntrega, " +
					" dir.nombreCalle, dir.horarioEntrega, ad.linNum, ad.codigoSalida,pr.idProducto, pr.codigoEan,pr.nombre AS descProducto, ad.pesoNeto, " +
					" ad.numeroBultos, ad.cantidadUnitaria, ad.precioUnitario, importe AS precioTotalLinea, ad.descripcionPrecioKilo, pr.mostrar, ad.importe " +
				" FROM albaran al " +
					" INNER JOIN albaran_detalle ad ON ad.codigoAlbaran= al.codigoAlbaran " +
					" INNER JOIN entidad cl ON al.idCliente = cl.idUsuario " +
					" INNER JOIN producto pr ON pr.idProducto = ad.idProducto " +
					" INNER JOIN registrosalida rs ON rs.idProducto = pr.idProducto " +
					" INNER JOIN bulto b ON rs.codigoSalida = b.codigoSalida AND b.codigoSalida = ad.codigoSalida " +
					" INNER JOIN direccion dir ON dir.idDireccion = b.direccionEntrega AND dir.empresa_idUsuario = cl.idUsuario " +
					" INNER JOIN bulto_lotes bl ON bl.idBulto = b.idBulto " +
				" WHERE al.habilitado='S' AND al.codigoAlbaran = ? AND al.tipoAlbaran='O'; ";
			// System.out.println(QryRegistrosSalida);
			PreparedStatement ps2 = con.prepareStatement(QryRegistrosSalida);
			ps2.setString(1, albaran.getCodigoAlbaran());
			ResultSet rs2 = ps2.executeQuery();
			List<RegistroSalida> graneles = new ArrayList<RegistroSalida>();
			List<RegistroSalida> itemsAgrupaciones = new ArrayList<RegistroSalida>();
			boolean primero = true;
			long contador = 1;
			while (rs2.next()) {
				boolean modificado = false;
				RegistroSalida salida = new RegistroSalida();
				salida.setNumLinea(contador);
				contador++;
				salida.setIdBulto(rs2.getLong("idBulto"));
				salida.setCodigoSalida(rs2.getString("codigoSalida"));
				salida.setCodigoEan(rs2.getString("codigoEan"));
				salida.setIdProducto(rs2.getLong("idProducto"));
				long mostrar = rs2.getLong("mostrar");
				boolean granel = false;
				if (mostrar == 1)
					granel = true;
				salida.setPrecioUnitario(rs2.getDouble("precioUnitario"));
				salida.setPrecioUnitario(Math.rint(salida.getPrecioUnitario() * 1000)/1000);
				salida.setDescripcion(rs2.getString("descProducto"));
				salida.setNumeroBultos(rs2.getLong("cantidad"));
				salida.setCantidadUnitaria(rs2.getDouble("cantidadUnitaria"));
				salida.setCantidad(rs2.getDouble("cantidad"));
				salida.setLote(rs2.getString("lote"));
				boolean noAgrupacion = false;
				if (salida.getLote().charAt(0) == 'E') {
					String consulta =
						" SELECT re.lote "
						+ " FROM registroentrada re "
						+ " WHERE re.codigoEntrada = '" + salida.getLote() + "'";
					PreparedStatement ps3 = con.prepareStatement(consulta);
					ResultSet rs3 = ps3.executeQuery();
					if (rs3.next()) {
						String loteEntrada = rs3.getString("lote");
						if (!loteEntrada.equals(""))
							salida.setLote(salida.getLote() + " (" + loteEntrada + ")");
					}
				} else{
					if (salida.getLote().charAt(0) == '0') {
						// Comprobamos si el lote corresponde a una
						// agrupación
						qry =
							" SELECT DISTINCT gpagrupa.idAgrupacion, gpe.idProducto " +
							" FROM gp_envasado gpe, gp_envasado_detalle gpdeta, gp_envasado_agrupacion gpagrupa " +
							" WHERE gpe.orden = gpdeta.orden " +
								" AND gpagrupa.ordenEnvasado = gpe.orden " +
								" AND gpe.lote = '" + salida.getLote() + "'";
						// System.out.println(qry);
						ps = con.prepareStatement(qry);
						rs = ps.executeQuery();
						long idAgrupacion = 0;
						long idProducto = 0;
						if (rs.next()) {
							idAgrupacion = rs.getLong("idAgrupacion");
							idProducto = rs.getLong("idProducto");
						}
						if (idAgrupacion > 0) {
							// Se agrupó.
							//double numBultos = salida.getCantidadUnitaria();
							double numBultos = salida.getCantidad();
							double cantidadProductoAgrupacion = 0;
							//double peso = 0;
							qry = " SELECT pc.cantidad, p.peso "
									+ " FROM producto p, producto_compuesto pc "
									+ " WHERE pc.idProducto = "
									+ idAgrupacion
									+ " AND pc.idCompuestoDe = "
									+ idProducto
									+ " AND p.idProducto = pc.idCompuestoDe";
							// System.out.println(qry);
							ps = con.prepareStatement(qry);
							rs = ps.executeQuery();
							if (rs.next()) {
								cantidadProductoAgrupacion = rs.getDouble("cantidad");
								//peso = rs.getDouble("peso");
							}
							double cantidadNueva = cantidadProductoAgrupacion * numBultos;
							//double pesoNuevo = peso * cantidadNueva;
							//salida.setCantidad(cantidadNueva);
							salida.setCantidad((double)numBultos);
							salida.setPesoNeto(cantidadNueva);
							modificado = true;
						}else{
							noAgrupacion = true;
						}
						String consulta =
							" SELECT re.lote "
							+ " FROM registroentrada re, gp_envasado gpe, gp_envasado_detalle deta "
							+ " WHERE gpe.lote = '" + salida.getLote() + "' "
								+ " AND re.codigoEntrada = deta.codigoEntrada "
								+ " AND deta.orden = gpe.orden "
								+ " AND deta.idTipoRegistro = 'M'";
						PreparedStatement ps3 = con.prepareStatement(consulta);
						ResultSet rs3 = ps3.executeQuery();
						if (rs3.next()) {
							String loteEntrada = rs3.getString("lote");
							if (!loteEntrada.equals(""))
								salida.setLote(salida.getLote() + " (" + loteEntrada + ")");
						}
					}
				}
				if (!modificado) {
					salida.setCantidad(rs2.getDouble("cantidad"));
					if (!granel && noAgrupacion){
						salida.setPesoNeto(salida.getCantidad());
						salida.setPrecioTotal(salida.getCantidad() * salida.getPrecioUnitario());
						salida.setPrecioTotal(Math.rint(salida.getPrecioTotal() * 1000)/1000);
					}else{
						salida.setPesoNeto(rs2.getDouble("peso") * salida.getCantidad());
					}
				}
				salida.setPrecioTotal(salida.getPesoNeto() * salida.getPrecioUnitario());
				salida.setPrecioTotal(Math.rint(salida.getPrecioTotal() * 1000)/1000);
				if (primero) {
					albaran.setDireccionEntrega(rs2.getLong("direccionEntrega"));
				}
				if (granel){
					graneles.add(salida);
				}else{
					if (noAgrupacion){
						salida.setPesoNeto(salida.getCantidad());
						salida.setPrecioTotal(salida.getCantidad() * salida.getPrecioUnitario());
						salida.setPrecioTotal(Math.rint(salida.getPrecioTotal() * 1000)/1000);
					}
					itemsAgrupaciones.add(salida);
				}
				primero = false;
			}
			// alba.setImporteTotal(importeTotal);
			albaran.setGraneles(graneles);
			albaran.setItemsAgrupaciones(itemsAgrupaciones);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// System.out.println("unirAlbaranes-FIN");
		return albaran;
	}

	//@Override
	public String unirPedidos(List<String> codigosPedidos, DTM fechaEntrega)
			throws Exception {
		// Creamos un nuevoPedido
		long pedido = new EdifactParserDataHelper().getProximoId();
		String bgmNum = "P" + pedido;
		long sumaCnt = 0, nadMs = 0, nadBy = 0, nadMr = 0, idFormaPago = 0;
		double sumaMoa79 = 0;
		String cux = "";
		try {
			con = bddConexion();
			String cadena = "";// 'P19', 'P20', 'P21'
			for (int i = 0; i < codigosPedidos.size(); i++) {
				cadena += "'" + codigosPedidos.get(i) + "'";
				if (i < codigosPedidos.size() - 1)
					cadena += ", ";
			}
			String qry = "SELECT SUM(fo.cnt) AS sumaCnt,	SUM(fo.moa79) AS sumaMoa79, "
					+ " fo.nadMs, fo.nadBy, fo.nadMr, "
					+ " fo.cux, fo.idFormaPago "
					+ " FROM fac_orders fo "
					+ " WHERE fo.bgmNum IN(" + cadena + ")";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				cux = rs.getString("cux");
				sumaCnt = rs.getLong("sumaCnt");
				sumaMoa79 = rs.getDouble("sumaMoa79");
				nadMs = rs.getLong("nadMs");
				nadBy = rs.getLong("nadBy");
				nadMr = rs.getLong("nadMr");
				idFormaPago = rs.getLong("idFormaPago");
			}
			Statement stmt;
			ps = null;
			rs = null;
			// idFormaPago es el idDatoBancario en referenciabancaria
			String insertString = "INSERT INTO fac_orders(unh,bgmTipo,bgmNum,bgmFunc,aliCond,ftxCali,"
					+ " nadMs,rffMs,nadMr,rffMr,nadSu,rffSu,nadBy,rffBy,nadDp,rffDp,nadIv,rffIv,nadPr,"
					+ " rffPr,cux,moa79,cnt,unt,estado,idFormaPago) values("
					+ "'" + "" + "','"
					+ 220
					+ "','"
					+ bgmNum
					+ "','"
					+ 26
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ nadMs
					+ "','"
					+ ""
					+ "','"
					+ nadMr
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ nadBy
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ cux
					+ "','"
					+ sumaMoa79
					+ "','"
					+ sumaCnt + "','" + "" + "','" + "O"// Oculto
					+ "','" + idFormaPago + "'" + ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			// dtm (fechas)
			String insertar = "INSERT INTO fac_orders_dtm " + " SELECT '"
					+ pedido + "' as idOrders, '" + fechaEntrega.getDtmFech()
					+ "' as dtmFech, dtmForm, dtmFunc "
					+ " FROM fac_orders_dtm WHERE idOrders = '" + pedido + "'";
			// System.out.println(insertar);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertar);
			long numeroLinea = 1;
			for (int i = 0; i < codigosPedidos.size(); i++) {
				String codigoPedido = codigosPedidos.get(i);
				String frag[] = codigoPedido.split("P");
				String numeroPedido = frag[1];
				// imd
				insertar = "INSERT INTO fac_orders_imd "
						+ " SELECT '"
						+ pedido
						+ "' as idOrders, idLin, imdTipo, imdCara, imdDesc, imdDescCod "
						+ " FROM fac_orders_imd WHERE idOrders = '"
						+ numeroPedido + "'";
				// System.out.println(insertar);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertar);
				// Miramos cuantas lineas tiene el pedido codigoPedido
				int cuantasLineas = 0;
				qry = " SELECT COUNT(idOrders) as cuenta "
						+ " FROM fac_orders_lin " + " WHERE idOrders='"
						+ numeroPedido + "'";
				// System.out.println(qry);
				ps = con.prepareStatement(qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					cuantasLineas = rs.getInt("cuenta");
				}
				for (int j = 0; j < cuantasLineas; j++) {
					// lin
					insertar = "INSERT INTO fac_orders_lin "
							+ " SELECT '"
							+ pedido
							+ "' as idOrders, idLin, "
							+ numeroLinea
							+ " as linNum, piaNumSA, piaNumIN, "
							+ " imdTipo, imdCara, imdDesc, imdDescCod, "
							+ " qty21Cant, kilos, qty59Cant, moa203, priAaa, priAab, priInf, observaciones, lineaProcesada"
							+ " FROM fac_orders_lin WHERE idOrders = '"
							+ numeroPedido + "' AND linNum = " + (j + 1);
					// System.out.println(insertar);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertar);
					// Actualizamos la tabla fac_orders_compuesto
					insertar = "INSERT INTO fac_orders_compuesto (idOrder, idCompuestoDe, idLinea, idLoc) "
							+ " VALUES('"
							+ pedido
							+ "', '"
							+ numeroPedido
							+ "','" + 0 + "','0');";// idLinea = 0 -> Todas las
													// lineas
					// System.out.println(insertar);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertar);
					// loc
					insertar = "INSERT INTO fac_orders_loc " + " SELECT '"
							+ pedido + "' as idOrders, " + numeroLinea
							+ " as idLin, idLoc, qty, localizacionProcesada "
							+ " FROM fac_orders_loc WHERE idOrders = '"
							+ numeroPedido + "' AND idLin = " + (j + 1);
					// System.out.println(insertar);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertar);
					numeroLinea++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		// System.out.println("unirPedidos-FIN");
		return bgmNum;
	}

	//@Override
	public String unirLineasPedidosDireccion(
			List<String> codigosLineasDireccion, DTM fechaEntrega,
			Long idDireccion) throws Exception {
		// Creamos un nuevoPedido
		long pedido = new EdifactParserDataHelper().getProximoId();
		String bgmNum = "P" + pedido;
		long sumaCnt = 0, nadMs = 0, nadBy = 0, nadMr = 0, idFormaPago = 0;
		double sumaMoa79 = 0;
		String cux = "";
		try {
			con = bddConexion();
			String leido = "";
			String cadenaPedidos = "";// 'P19', 'P20', 'P21'
			String cadenaLineas = "";// '1', '3'
			// String codigosPedidos = "";//'P19_1', 'P19_2'
			for (int i = 0; i < codigosLineasDireccion.size(); i++) {
				leido = codigosLineasDireccion.get(i);
				String frag[] = leido.split("_");
				// if (cadenaPedidos.indexOf(frag[0]) > -1){
				if (cadenaPedidos.indexOf(frag[0]) > -1) {
					// Ya existe
				} else {
					cadenaPedidos += "'" + frag[0] + "'";
					if (i < codigosLineasDireccion.size() - 1)
						cadenaPedidos += ",";
				}
				if (cadenaLineas.indexOf(frag[1]) > -1) {
					// Ya existe
				} else {
					cadenaLineas += "'" + frag[1] + "'";
					if (i < codigosLineasDireccion.size() - 1)
						cadenaLineas += ",";
				}
			}
			if (cadenaPedidos.charAt(cadenaPedidos.length() - 1) == ',') {
				String cadenaAux = "";
				char aux = ' ';
				for (int h = 0; h < cadenaPedidos.length() - 1; h++) {
					aux = cadenaPedidos.charAt(h);
					cadenaAux += aux;
				}
				cadenaPedidos = cadenaAux;
			}
			if (cadenaLineas.charAt(cadenaLineas.length() - 1) == ',') {
				String cadenaAux = "";
				char aux = ' ';
				for (int h = 0; h < cadenaLineas.length() - 1; h++) {
					aux = cadenaLineas.charAt(h);
					cadenaAux += aux;
				}
				cadenaLineas = cadenaAux;
			}
			String qry = "SELECT fo.nadMs, fo.nadBy, fo.nadMr, "
					+ " fo.cux, fo.idFormaPago, "
					+ " sum(folo.qty) as agrupaciones, "
					+ " sum(foli.qty59Cant * folo.qty) as unidades, "
					+ " sum((foli.qty59Cant * folo.qty) * p.peso) as peso_kilos, "
					+ " sum((foli.moa203 / kilos) * (folo.qty * foli.qty59Cant) * p.peso) as precio "
					+ " FROM fac_orders_loc folo, fac_orders fo, fac_orders_lin foli, producto p, referenciabancaria ref, formapago fp "
					+ " WHERE (fo.bgmNum IN(" + cadenaPedidos
					+ ") AND foli.linNum IN(" + cadenaLineas + ")) "
					+ " AND foli.idOrders = fo.idOrders "
					+ " AND folo.idLoc = " + idDireccion + " "
					+ " AND fo.idOrders = folo.idOrders "
					+ " AND foli.idOrders = folo.idOrders "
					+ " AND folo.idLin = foli.linNum "
					+ " AND p.idProducto = foli.piaNumSA "
					+ " AND ref.idFormaPago = fp.idFormaPago "
					+ " AND fo.idFormaPago = ref.idDatoBancario";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				cux = rs.getString("cux");
				sumaCnt = codigosLineasDireccion.size();
				sumaMoa79 = rs.getDouble("precio");
				nadMs = rs.getLong("nadMs");
				nadBy = rs.getLong("nadBy");
				nadMr = rs.getLong("nadMr");
				idFormaPago = rs.getLong("idFormaPago");
			}
			Statement stmt;
			ps = null;
			rs = null;
			// idFormaPago es el idDatoBancario en referenciabancaria
			String insertString = " INSERT INTO fac_orders(unh,bgmTipo,bgmNum,bgmFunc,aliCond,ftxCali,"
					+ " nadMs,rffMs,nadMr,rffMr,nadSu,rffSu,nadBy,rffBy,nadDp,rffDp,nadIv,rffIv,nadPr,"
					+ " rffPr,cux,moa79,cnt,unt,idFormaPago) values("
					+ "'"
					+ "" + "','"
					+ 666 // 666 indica que este pedido lo hemos generado
							// nosotros
					+ "','"
					+ bgmNum
					+ "','"
					+ 26
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ nadMs
					+ "','"
					+ ""
					+ "','"
					+ nadMr
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ nadBy
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ ""
					+ "','"
					+ cux
					+ "','"
					+ sumaMoa79
					+ "','"
					+ sumaCnt + "','" + "" + "','" + idFormaPago + "'" + ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			// dtm (fechas)
			String insertar = "INSERT INTO fac_orders_dtm (idOrders, dtmFech, dtmForm, dtmFunc) "
					+ " VALUES("
					+ pedido
					+ ",'"
					+ fechaEntrega.getDtmFech()
					+ "',102,2);";
			// System.out.println(insertar);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertar);
			insertar = "INSERT INTO fac_orders_dtm (idOrders, dtmFech, dtmForm, dtmFunc) "
					+ " VALUES("
					+ pedido
					+ ",'"
					+ fechaEntrega.getDtmFech()
					+ "',102,137);";
			// System.out.println(insertar);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertar);
			long numeroLinea = 1;
			String pedidosCompuestosInsertados = "";
			for (int i = 0; i < codigosLineasDireccion.size(); i++) {
				String codigoPedido = codigosLineasDireccion.get(i).split("_")[0];
				String idLinea = codigosLineasDireccion.get(i).split("_")[1];
				String frag[] = codigoPedido.split("P");
				String numeroPedido = frag[1];
				// imd
				insertar = "INSERT INTO fac_orders_imd "
						+ " SELECT '"
						+ pedido
						+ "' as idOrders, idLin, imdTipo, imdCara, imdDesc, imdDescCod "
						+ " FROM fac_orders_imd WHERE idOrders = '"
						+ numeroPedido + "'";
				// System.out.println(insertar);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertar);

				// lin
				// Calcular los nuevos: qty21Cant, kilos, qty59Cant, moa203,
				// ¿priAaa?
				// Necesito, de loc, qty para idOrders, idLin e idLoc conocidos
				String consulta = " SELECT * " + " FROM fac_orders_loc folo "
						+ " WHERE folo.idOrders='" + numeroPedido + "' "
						+ " AND folo.idLin = " + idLinea + " "
						+ " AND folo.idLoc = " + idDireccion;
				long qty = 0;
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rs = ps.executeQuery();
				while (rs.next()) {
					qty = rs.getLong("qty");
				}
				// System.out.println("Numero de agrupaciones para el pedido + linea + direccion: "	+ qty);
				consulta = " SELECT p.peso, foli.* "
						+ " FROM fac_orders_lin foli, producto p "
						+ " WHERE foli.idOrders = '" + numeroPedido + "' "
						+ " AND foli.linNum = " + idLinea
						+ " AND foli.piaNumSA = p.idProducto";
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rs = ps.executeQuery();
				// A partir de qty (numero de agrupaciones a esa direccion),
				// puedo sacar el resto
				// Para la linea, tenemos almacenado numero de unidades por
				// agrupacion,
				// numero de unidades de la linea (A partir de estos dos,
				// sacamos el número de unidades)
				long udsPorAgrupacion = 0;
				double peso = 0, precioUnitario = 0;
				while (rs.next()) {
					udsPorAgrupacion = rs.getLong("qty59Cant");
					peso = rs.getDouble("peso");
					precioUnitario = rs.getDouble("priAaa");
				}
				long qty21 = qty * udsPorAgrupacion;
				// System.out.println("Unidades del pedido para una linea y una direccion concreta: " + qty21);
				// Sabemos el peso del producto, asi que tenemos los kilos a la
				// dirección.
				double kilos = qty21 * peso;
				// System.out.println("Kilos del pedido para una linea y una direccion concreta: " + kilos);
				// Sabemos el precio por kilo, sabemos el precio de la nueva
				// linea
				double precioLinea = precioUnitario * qty21;
				// System.out.println("Precio del pedido para una linea y una direccion concreta: "	+ precioLinea);
				insertar = "INSERT INTO fac_orders_lin "
						+ " SELECT '"
						+ pedido
						+ "' AS idOrders, idLin, "
						+ numeroLinea
						+ " AS linNum, piaNumSA, piaNumIN, "
						+ " imdTipo, imdCara, imdDesc, imdDescCod, "
						+ " "
						+ qty21
						+ " AS qty21Cant, "
						+ kilos
						+ " as kilos, qty59Cant, "
						+ " "
						+ precioLinea
						+ " AS moa203, priAaa, priAab, priInf, observaciones, lineaProcesada "
						+ " FROM fac_orders_lin WHERE idOrders = '"
						+ numeroPedido + "' AND linNum = " + idLinea;
				// System.out.println(insertar);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertar);
				// loc
				insertar = "INSERT INTO fac_orders_loc " + " SELECT '" + pedido
						+ "' as idOrders, " + numeroLinea
						+ " as idLin, idLoc, qty, localizacionProcesada "
						+ " FROM fac_orders_loc WHERE idOrders = '"
						+ numeroPedido + "' AND idLin = " + idLinea
						+ " AND idLoc = " + idDireccion + "";
				// System.out.println(insertar);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertar);
				numeroLinea++;
				// Actualizamos la tabla fac_orders_compuesto
				if (pedidosCompuestosInsertados.indexOf(numeroPedido) < 0) {
					insertar = "INSERT INTO fac_orders_compuesto (idOrder, idCompuestoDe, idLinea, idLoc) "
							+ " VALUES('"
							+ pedido
							+ "', '"
							+ numeroPedido
							+ "','" + idLinea + "','" + idDireccion + "');";
					// System.out.println(insertar);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					pedidosCompuestosInsertados += numeroPedido + ", ";
					stmt.executeUpdate(insertar);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// System.out.println("unirPedidos-FIN");
		return bgmNum;
	}

	//@Override
	public Vector<OrdersLin> getLineasPedidoDireccion(long idDireccion) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<OrdersLin> resultado = new Vector<OrdersLin>();
		try {
			con = bddConexion();
			String qry = " SELECT "
					+ " IF (fo.idFormaPago = 0, '', CONCAT(fp.descripcion, ' a ', ref.diasFormaPago, ' dias. Dia de cobro: ',"
					+ " ref.diaPago, IF(ref.numCuenta = '000000000000000000000000', '', "
					+ " CONCAT('. Cuenta bancaria: ', ref.numCuenta)))) as formaDePago, "
					+ " fo.idFormaPago,"
					+ " fo.idOrders, fo.bgmNum, foli.linNum, p.nombre, "
					+ " folo.qty as agrupaciones, "
					+ " (foli.qty59Cant * folo.qty) as unidades, "
					+ " (foli.qty59Cant * folo.qty) * p.peso as peso_kilos, "
					+ " (foli.moa203 / kilos) * (folo.qty * foli.qty59Cant) * p.peso as precio "
					+ " FROM fac_orders_loc folo, fac_orders fo, fac_orders_lin foli, producto p, referenciabancaria ref, formapago fp "
					+ " WHERE folo.idLoc = " + idDireccion + " "
					+ " AND fo.idOrders = folo.idOrders "
					+ " AND foli.idOrders = folo.idOrders"
					+ " AND folo.idLin = foli.linNum "
					+ " AND p.idProducto = foli.piaNumSA "
					+ " AND ref.idFormaPago = fp.idFormaPago "
					+ " AND fo.idFormaPago = ref.idDatoBancario "
					+ " AND fo.bgmTipo = '220' "
					+ " AND folo.localizacionProcesada = 'N' ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				OrdersLin linea = new OrdersLin();
				linea.setIdOrders(rs.getLong("idOrders"));
				linea.setLinNum(rs.getString("linNum"));
				linea.setBgmNum(rs.getString("bgmNum"));
				linea.setDescripcionProducto(rs.getString("nombre"));
				linea.setQty21Cant(rs.getString("agrupaciones"));
				linea.setQty59Cant(rs.getString("unidades"));
				//linea.setKilos(rs.getDouble("peso_kilos"));
				linea.setMoa203(rs.getString("precio"));
				linea.setDescripcionFormaPago(rs.getString("formaDePago"));
				linea.setIdFormaPago(rs.getLong("idFormaPago"));
				// La añadimos al Vector de resultado
				resultado.add(linea);
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
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public char comprobarLoteLeido(Lote lote) throws Exception {
		char comprobacion = 'O';// O = OK; N = No existe el lote; C = el lote no Corresponde con el producto; S = Stock insuficiente;
		try {
			String qry = "";
			con = bddConexion();
			// 1. Mirar que el lote existe
			// 2. El producto envasado en el envasado del lote coincide con el codigoEan
			// Diferenciamos si el lote se trata de un producto envasado o de un
			// producto final registrado como una entrada
			if (lote.getLote().charAt(0) == '0') {
				// Comprobamos primero si en el proceso con lote lote.getLote() se agrupó
				qry =
					" SELECT DISTINCT gpe.idProducto, gpagrupa.idAgrupacion " +
					" FROM gp_envasado gpe " +
						" LEFT OUTER JOIN gp_envasado_agrupacion gpagrupa " +
						" ON gpagrupa.ordenEnvasado = gpe.orden " +
					" WHERE gpe.lote = ?; ";
				logger.trace(qry);
				ps = con.prepareStatement(qry);
				ps.setString(1, lote.getLote());
				logger.trace("gpe.lote = " + lote.getLote());
				rs = ps.executeQuery();
				long idProductoEnvasado = 0, idAgrupacionEnvasada = 0;
				if (rs.next()) {
					idProductoEnvasado = rs.getLong("idProducto");
					idAgrupacionEnvasada = rs.getLong("idAgrupacion");
				}
				if (idAgrupacionEnvasada > 0){
					if (idAgrupacionEnvasada != lote.getIdProducto()){
						comprobacion = 'C';
					}
				}else{
					if (idProductoEnvasado > 0){
						if (idProductoEnvasado != lote.getIdProducto()){
							comprobacion = 'C';
						}
					}else{
						//El lote no existe
						comprobacion = 'N';
					}
				}
			} else {
				if (lote.getLote().charAt(0) == 'E') {
					//1. Comprobar que exista el lote (registro de entrada)
					qry =
						" SELECT re.idProducto " +
						" FROM registroentrada re " +
						" WHERE re.codigoEntrada = ? " +
							" AND re.idTipoRegistro = 'P'; ";
					ps = con.prepareStatement(qry);
					logger.trace(qry);
					ps.setString(1, lote.getLote());
					logger.trace("re.codigoentrada = " + lote.getLote());
					rs = ps.executeQuery();
					if (!rs.next()) {
						comprobacion = 'N';
					}else{
						//2. Comprobar que el registro corresponda con el producto
						long idProductoEntrada = rs.getLong("idProducto");
						if (idProductoEntrada != lote.getIdProducto()){
							comprobacion = 'C';
						}
					}
				}
			}
			//3. Comprobar si hay stock del lote leido en ssalida (223)
			qry =
				" SELECT u.cantidad " +
				" FROM ubicacion u " +
				" WHERE u.registro = ?" +
					" AND u.idHueco = 223; ";
			ps = con.prepareStatement(qry);
			logger.trace(qry);
			ps.setString(1, lote.getLote());
			logger.trace("u.registro = " + lote.getLote());
			rs = ps.executeQuery();
			if (rs.next()) {
				double stock = rs.getDouble("cantidad");
				if (lote.getCantidad() > stock) {
					comprobacion = 'S';
				}
			}else{
				//Si no existe ya en ubicación, 
				comprobacion = 'S';
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
		// Retornamos el vector de resultado.
		return comprobacion;
	}

	//@Override
	public void entregarAlbaran(String codigoAlbaran, String usuario)
			throws Exception {
		Statement stmt;
		try {
			registrarCambioAlbaran(codigoAlbaran, usuario, "Albaran entregado");
			con = bddConexion();
			String insertString = "UPDATE albaran SET estado='E' WHERE codigoAlbaran = '"
					+ codigoAlbaran + "'";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			// Actualizamos tambien la factura asocida al albaran
			String qry = " SELECT fc.idFactura "
					+ " FROM factura_compuesto fc " + " WHERE fc.albaran = '"
					+ codigoAlbaran + "' ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			String idFactura = "";
			if (rs.next()) {
				idFactura = rs.getString("idFactura");
			}
			insertString = "UPDATE factura SET estado='1' WHERE idFactura = '"
					+ idFactura + "'";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Vector<Direccion> getDireccionesAlbaran(Albaran albaran)
			throws Exception {
		Vector<Direccion> direcciones = new Vector<Direccion>();
		try {
			con = bddConexion();
			String qry =
				" SELECT d.nombreCalle, d.localidad "
				+ " FROM albaran a, albaran_detalle ad, bulto b, direccion d "
				+ " WHERE a.codigoAlbaran='" + albaran.getCodigoAlbaran()
					+ "'" + " AND a.codigoAlbaran = ad.codigoAlbaran "
					+ " AND ad.codigoSalida = b.codigoSalida "
					+ " AND b.direccionEntrega = d.idDireccion";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Direccion dir = new Direccion();
				dir.setNombreCalle(rs.getString("nombreCalle"));
				dir.setLocalidad(rs.getString("localidad"));
				direcciones.add(dir);
			}
			return direcciones;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	/**
	 * @since 2.0
	 */
	public Vector<Lote> getLotesProducto(long idProducto) throws Exception {
		Vector<Lote> lotes = new Vector<Lote>();
		try {
			con = bddConexion();
			String qry =
				" SELECT g.lote, u.cantidad, uh.descripcion " +
				" FROM gp_envasado g " +
					" INNER JOIN ubicacion u ON u.registro = g.lote " +
					" INNER JOIN ubicacion_hueco uh ON u.idHueco = uh.idHueco " +
				" WHERE g.idProducto = " + idProducto +
					" AND u.cantidad > 0;";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Lote lote = new Lote();
				lote.setLote(rs.getString("lote"));
				lote.setUbicacion(rs.getString("descripcion"));
				lote.setCantidad(rs.getDouble("cantidad"));
				//Si el lote es de un proceso de envasado de agrupación, no cuenta, porque agrupamos, no tenemos el EAN13
				String consulta =
					" SELECT idAgrupacion " +
					" FROM gp_envasado_agrupacion gea " +
						" INNER JOIN gp_envasado gp ON gea.ordenEnvasado = gp.orden " +
					" WHERE gp.lote = '" + lote.getLote() + "'";
				// System.out.println(consulta);
				PreparedStatement ps2 = con.prepareStatement(consulta);
				ResultSet rs2 = ps2.executeQuery();
				long idAgrupacion = 0;
				if (rs2.next()){
					idAgrupacion = rs2.getLong("idAgrupacion");
				}
				if (idAgrupacion > 0){

				}else{
					if (lote.getLote().charAt(0) == '0'){
						consulta =
							" SELECT re.lote " +
							" FROM registroentrada re " +
								" INNER JOIN gp_envasado_detalle deta ON re.codigoEntrada = deta.codigoEntrada " +
								" INNER JOIN gp_envasado gpe ON deta.orden = gpe.orden " +
							" WHERE gpe.lote = '" + lote.getLote() + "' " + " AND deta.idTipoRegistro = 'M'";
						PreparedStatement ps3 = con.prepareStatement(consulta);
						ResultSet rs3 = ps3.executeQuery();
						if (rs3.next()) {
							String loteEntrada = rs3.getString("lote");
							if (!loteEntrada.equals(""))
								lote.setLote(lote.getLote() + " (" + loteEntrada + ")");
						}
					}
					lotes.add(lote);
				}
			}
			qry =
				" SELECT DISTINCT g.lote, uh.descripcion, u.cantidad " +
				" FROM gp_envasado g " +
					" INNER JOIN ubicacion u ON u.registro = g.lote " +
					" INNER JOIN ubicacion_hueco uh ON u.idHueco = uh.idHueco " +
					" INNER JOIN gp_envasado_agrupacion ga ON ga.ordenEnvasado = g.orden " +
				" WHERE ga.idAgrupacion = " + idProducto +
					" AND u.cantidad > 0; ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Lote lote = new Lote();
				lote.setLote(rs.getString("lote"));
				lote.setUbicacion(rs.getString("descripcion"));
				lote.setCantidad(rs.getDouble("cantidad"));
				if (lote.getLote().charAt(0) == '0'){
					String consulta =
						" SELECT re.lote "
						+ " FROM registroentrada re " +
							" INNER JOIN gp_envasado_detalle deta ON re.codigoEntrada = deta.codigoEntrada " +
							" INNER JOIN gp_envasado gpe ON deta.orden = gpe.orden " +
						" WHERE gpe.lote = '" + lote.getLote() + "' AND deta.idTipoRegistro = 'M'";
					PreparedStatement ps3 = con.prepareStatement(consulta);
					ResultSet rs3 = ps3.executeQuery();
					if (rs3.next()) {
						String loteEntrada = rs3.getString("lote");
						if (!loteEntrada.equals(""))
							lote.setLote(lote.getLote() + " (" + loteEntrada + ")");
					}
				}
				lotes.add(lote);
			}
			qry = " SELECT re.codigoEntrada " + " FROM registroentrada re "
					+ " WHERE re.idProducto = " + idProducto
					+ " AND re.idTipoRegistro = 'P' " + " AND re.saldo > 0";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Lote lote = new Lote();
				lote.setLote(rs.getString("codigoEntrada"));
				lotes.add(lote);
			}
			return lotes;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	/**
	 * @since 2.0
	 */
	//@Override
	public void actualizarAlbaran(Albaran albaran) throws Exception {
		registrarCambioAlbaran(albaran.getCodigoAlbaran(), albaran.getUsuarioResponsable(), "Albaran actualizado");
		Statement stmt;
		try {
			con = bddConexion();
			String fechaEntrega = "", fechaVencimiento = "";
			// Damos formato a la fecha
			String frag[] = albaran.getFechaEntrega().split("/");
			fechaEntrega = frag[2] + "-" + frag[1] + "-" + frag[0];
			frag = albaran.getFechaVencimiento().split("/");
			fechaVencimiento = frag[2] + "-" + frag[1] + "-" + frag[0];
			String insertString = " UPDATE albaran "
					+ " SET descripcionFormaPago='"
					+ albaran.getDescripcionFormaPago()
					+ "', "
					+ " idTelefono='"
					+ albaran.getIdTelefono()
					+ "', "
					+ " idFormaPago='"
					+ albaran.getIdFormaPago()
					+ "', "
					+ " idTransportista='"
					+ albaran.getIdTransportista()
					+ "', "
					+ " idPortesTransporte='"
					+ albaran.getIdPortesTransporte()
					+ "', "
					+ " idTemperaturaTransporte='"
					+ albaran.getIdTemperaturaTransporte()
					+ "', "
					+ " observaciones='"
					+ albaran.getObservaciones()
					+ "', "
					+ " idDireccionCliente='"
					+ albaran.getIdDireccionCliente()
					+ "', "
					+ " destino='"
					+ albaran.getDireccionEntrega()
					+ "', "
					+ " idTransportista='"
					+ albaran.getIdTransportista()
					+ "', "
					+ " numeroPedido='"
					+ albaran.getCodigoOrden()
					+ "', "
					+ " descripcionIvaAplicable='"
					+ albaran.getIvaAplicable()
					+ "', "
					+ " importeTotal='"
					+ albaran.getImporteTotal()
					+ "', "
					+ " fechaEntrega='"
					+ fechaEntrega
					+ "', "
					+ " fechaVencimiento='"
					+ fechaVencimiento
					+ "', "
					+ " horarioEntrega='"
					+ albaran.getHorarioEntrega()
					+ "' "
					+ "WHERE codigoAlbaran = '"
					+ albaran.getCodigoAlbaran() + "'";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			// Actualizamos cada linea del albarán
			//TODO
			/*Iterator iter = albaran.getRegistrosSalida().iterator();
			while (iter.hasNext()) {
				RegistroSalida reg = (RegistroSalida) iter.next();
				insertString =
					" UPDATE albaran_detalle "
					+ " SET descripcionPrecioKilo='"
						+ reg.getPrecioKilo() + "' "
					+ "WHERE codigoAlbaran = '" + albaran.getCodigoAlbaran() + "' " +
						" AND linNum = " + reg.getNumLinea();
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	/**
	 * @since 2.0
	 */
	//@Override
	public void insertarFacturaLibre(Factura factura) throws Exception {
		Statement stmt;
		try {
			con = bddConexion();
			String insertString = " INSERT INTO factura (idFactura, codigoFactura, fecha, fechaVencimiento, observaciones, "
					+ " importeTotal, total, idFormaPago, descripcionFormaPago, "
					+ " idCliente, nombreCliente, nifCliente, telefonoCliente, "
					+ " idDestino, descripcionDestino, ivaAplicable, subtotal, valorIva, descuento, valorDescuento, cargosTotal) "
					+ " VALUES ('"
					+ factura.getIdFactura()
					+ "','"
					+ factura.getCodigoFactura()
					+ "',CURDATE(),'"
					+ factura.getFechaVencimiento()
					+ "','"
					+ factura.getObservaciones()
					+ "','"
					+ factura.getImporteTotal()
					+ "','"
					+ factura.getImporteTotal()
					+ "','"
					+ factura.getIdFormaPago()
					+ "','"
					+ factura.getDescripcionFormaPago()
					+ "','"
					+ factura.getIdCliente()
					+ "','"
					+ factura.getNombreCliente()
					+ "','"
					+ factura.getNifCliente()
					+ "','"
					+ factura.getTelefonoCliente()
					+ "','"
					+ factura.getIdDestino()
					+ "','"
					+ factura.getDescripcionDestino()
					+ "','"
					+ factura.getIvaAplicable()
					+ "','"
					+ factura.getSubtotal()
					+ "','"
					+ factura.getValorIva()
					+ "','"
					+ factura.getDescuento()
					+ "','"
					+ factura.getValorDescuento()
					+ "','"
					+ factura.getCargosTotal() + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			List<ItemFactura> items = factura.getItems();
			Iterator itera = items.iterator();
			int contador = 1;
			while (itera.hasNext()) {
				ItemFactura item = (ItemFactura) itera.next();
				String insertString2 = " INSERT INTO factura_item (idFactura, idItem, codigoItem, idProducto, cantidad, peso, "
						+ " precioTotal, precioKilo, descripcion)"
						+ " VALUES('"
						+ factura.getIdFactura()
						+ "','"
						+ contador
						+ "','"
						+ item.getCodigoItem()
						+ "','"
						+ item.getIdProducto()
						+ "','"
						+ item.getCantidad()
						+ "','"
						+ item.getPeso()
						+ "','"
						+ item.getPrecioTotal()
						+ "','"
						+ item.getPrecioKilo()
						+ "','" + item.getDescripcion() + "');";
				// System.out.println(insertString2);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString2);
				contador++;
			}
			Iterator itr;
			if (factura.getCargos() != null) {
				itr = factura.getCargos().iterator();
				while (itr.hasNext()) {
					Cargo cargo = (Cargo) itr.next();
					// System.out.print("Cargo es: " + cargo.getNombre());
					String insertString3 = "INSERT INTO factura_cargo(idFactura, tipoCargo, totalCargo, valorCargo, ivaCargo) "
							+ " VALUES("
							+ factura.getIdFactura()
							+ ",'"
							+ cargo.getNombre()
							+ "',"
							+ cargo.getTotalCargo()
							+ ","
							+ cargo.getValor()
							+ ","
							+ cargo.getIvaCargo() + ")";
					// System.out.println(insertString3);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString3);
				}
			}
			if (factura.getCuotas() != null) {
				itr = factura.getCuotas().iterator();
				while (itr.hasNext()) {
					CuotaFactura cuota = (CuotaFactura) itr.next();
					cuota.setEstado("O");
					String insertString3 = " INSERT INTO factura_cuotas(codigoFactura, idCuotaFactura, importe, porcentaje, fecha, observaciones, estado) "
							+ " VALUES('"
							+ factura.getCodigoFactura()
							+ "','"
							+ cuota.getNumeroCuota()
							+ "',"
							+ cuota.getImporte()
							+ ","
							+ cuota.getPorcentaje()
							+ ",'"
							+ cuota.getFecha()
							+ "','"
							+ cuota.getObservaciones()
							+ "','"
							+ cuota.getEstado() + "');";
					// System.out.println(insertString3);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString3);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public void updateEstadoFactura(Factura factura) throws Exception {
		Statement stmt;
		String estado = factura.getEstado();
		try {
			con = bddConexion();
			if (estado.equals(1)) {
				String qry = " SELECT af.idAlbaran, a.estado "
						+ " FROM albaran_factura af, factura f, albaran a  "
						+ " WHERE af.idFactura = f.idFactura "
						+ " AND af.idAlbaran = a.idAlbaran "
						+ " AND f.codigoFactura = '"
						+ factura.getCodigoFactura() + "'";
				// System.out.println(qry);
				ps = con.prepareStatement(qry);
				rs = ps.executeQuery();
				if (rs.next()) {
					String estadoAlbaran = rs.getString("estado");
					if (estadoAlbaran.equals("E"))
						estado = "1";
					else
						estado = "2";
				}
			}
			String updateString = " UPDATE factura " + " SET estado = '"
					+ estado + "' " + " WHERE codigoFactura = '"
					+ factura.getCodigoFactura() + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(updateString);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}

	//@Override
	public Vector<OrdersLin> getProductosPedidos(long idCliente)
			throws Exception {
		Vector<OrdersLin> productos = new Vector<OrdersLin>();
		try {
			con = bddConexion();
			String qry = " SELECT fol.piaNumSA, p.nombre, COUNT(fol.idLin), SUM(fol.kilos) AS kilos "
					+ " FROM fac_orders fo, fac_orders_lin fol, producto p "
					+ " WHERE fo.nadBy = '"
					+ idCliente
					+ "' "
					+ " AND p.idProducto = fol.piaNumSA "
					+ " AND fo.idOrders = fol.idOrders " + " GROUP BY piaNumSA";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrdersLin lin = new OrdersLin();
				lin.setIdProducto(rs.getLong("piaNumSA"));
				lin.setNombreProducto(rs.getString("nombre"));
				//lin.setKilos(rs.getDouble("kilos"));
				productos.add(lin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		return productos;
	}

	private void registrarCambioAlbaran(String codigoAlbaran, String usuario,
			String descripcion) throws Exception {
		Statement stmt;
		try {
			con = bddConexion();
			String insertString = " INSERT INTO albaran_cambios (codigoAlbaran, usuario, descripcion) "
					+ " VALUES ('"
					+ codigoAlbaran
					+ "','"
					+ usuario
					+ "','"
					+ descripcion + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	private void registrarCambioFactura(String codigoFactura, String usuario,
			String descripcion) throws Exception {
		Statement stmt;
		try {
			con = bddConexion();
			String insertString = " INSERT INTO factura_cambios (codigoFactura, usuario, descripcion) "
					+ " VALUES ('"
					+ codigoFactura
					+ "','"
					+ usuario
					+ "','"
					+ descripcion + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}

	//@Override
	public Vector<Producto> getProductosAlbaran(String idPedido)
			throws Exception {
		Vector<Producto> productos = new Vector<Producto>();
		String Qry = " SELECT p.nombre, ad.pesoNeto, ad.cantidadUnitaria, p.idProducto "
				+ " FROM albaran a, albaran_detalle ad, producto p "
				+ " WHERE a.codigoAlbaran = ad.codigoAlbaran "
				+ " AND ad.idProducto = p.idProducto "
				+ " AND a.codigoAlbaran = '"
				+ idPedido
				+ "' "
				+ " ORDER BY (linNum + 0)";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto p = new Producto();
				p.setNombre(rs.getString("nombre"));
				p.setPeso(rs.getDouble("pesoNeto"));
				productos.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		return productos;
	}

	//@Override
	public Vector<Producto> getProductosFactura(String idPedido)
			throws Exception {
		Vector<Producto> productos = new Vector<Producto>();
		String Qry = " SELECT p.nombre, p.idProducto, fi.cantidad "
				+ " FROM factura f, factura_item fi, producto p "
				+ " WHERE f.idFactura = fi.idFactura "
				+ " AND fi.idProducto = p.idProducto "
				+ " AND f.codigoFactura = '" + idPedido + "' ";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto p = new Producto();
				p.setNombre(rs.getString("nombre"));
				p.setPeso(rs.getDouble("cantidad"));
				productos.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
		return productos;
	}

	//@Override
	public Vector<Factura> facturasAlbaran(String codigoAlbaran)
			throws Exception {
		Vector<Factura> facturas = new Vector<Factura>();
		String Qry = " SELECT f.codigoFactura, f.fecha, f.fechaVencimiento, f.importeTotal "
				+ " FROM factura f, albaran a "
				+ " WHERE f.albaran = a.codigoAlbaran "
				+ " AND a.codigoAlbaran = '" + codigoAlbaran + "' ";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Factura f = new Factura();
				f.setCodigoFactura(rs.getString("codigoFactura"));
				f.setFecha(rs.getString("fecha"));
				f.setFechaVencimiento(rs.getString("fechaVencimiento"));
				f.setImporteTotal(rs.getDouble("importeTotal"));
				facturas.add(f);
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
		return facturas;
	}

	//@Override
	public void updateFechaVencimientoFactura(Factura factura) throws Exception {
		// System.out.println("DAO updateFechaVencimientoFactura");
		String Qry = " SELECT f.fechaVencimiento " + " FROM factura f "
				+ " WHERE f.codigoFactura = '" + factura.getCodigoFactura()
				+ "' ";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			String fechaVencimiento = "";
			while (rs.next()) {
				fechaVencimiento = rs.getString("fechaVencimiento");
			}
			Statement stmt;
			int diasDesplazamiento = Integer.parseInt(factura
					.getFechaVencimiento());
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = null;
			try {
				fecha = formatoDelTexto.parse(fechaVencimiento);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			Calendar nuevaFecha = Calendar.getInstance();
			nuevaFecha.setTime(fecha);
			nuevaFecha.add(Calendar.DAY_OF_MONTH, diasDesplazamiento);
			// Date fechaUpdate = nuevaFecha.getTime();
			String cadenaNuevaFecha = nuevaFecha.get(Calendar.YEAR) + "-"
					+ (nuevaFecha.get(Calendar.MONTH) + 1) + "-"
					+ nuevaFecha.get(Calendar.DAY_OF_MONTH);
			registrarCambioFactura(factura.getCodigoFactura(), factura
					.getUsuarioResponsable(),
					"Fecha de vencimiento de la factura modificada");
			con = bddConexion();
			String insertString1 = " UPDATE factura "
					+ " SET fechaVencimiento = '" + cadenaNuevaFecha.toString()
					+ "' " + " WHERE codigoFactura = '"
					+ factura.getCodigoFactura() + "'";
			// System.out.println(insertString1);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString1);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	/**
	 * @since 2.0
	 */
	//@Override
	public void dividirFacturaCuotas(Factura factura) throws Exception {
		// System.out.println("DAO dividirFacturaCuotas");
		Statement stmt;
		String Qry = " SELECT fc.idCuotaFactura, fc.importe, fc.observaciones "
				+ " FROM factura_cuotas fc " + " WHERE fc.codigoFactura = '"
				+ factura.getCodigoFactura() + "' ";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			PreparedStatement ps2 = null;
			ResultSet rs2 = null;
			ps2 = con.prepareStatement(Qry);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Factura nueva = this.getNuevaFactura();
				// Para cada cuota, creamos una factura
				Factura f = getFactura(factura.getCodigoFactura());
				f.setImporteTotal(rs2.getDouble("importe"));
				f.setCuotas(null);
				f.setIdFactura(nueva.getIdFactura());
				f.setCodigoFactura(nueva.getCodigoFactura());
				String fecha = f.getFechaVencimiento();
				String frag[] = fecha.split("/");
				f.setFechaVencimiento(frag[2] + "-" + frag[1] + "-" + frag[0]);
				this.insertarFacturaLibre(f);
				con = bddConexion();
				String insertString1 = " UPDATE factura f "
						+ " SET observaciones = '"
						+ rs2.getString("observaciones") + "' "
						+ " WHERE codigoFactura = '" + nueva.getCodigoFactura()
						+ "'";
				// System.out.println(insertString1);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString1);
				insertString1 = " UPDATE factura_cuotas fc "
						+ " SET codigoNuevaFactura = '"
						+ nueva.getCodigoFactura() + "' "
						+ " WHERE codigoFactura = '"
						+ factura.getCodigoFactura() + "' "
						+ " AND idCuotaFactura = '"
						+ rs2.getLong("idCuotaFactura") + "'";
				// System.out.println(insertString1);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString1);
			}
			registrarCambioFactura(factura.getCodigoFactura(), factura
					.getUsuarioResponsable(), "Factura dividida en cuotas");
			con = bddConexion();
			String insertString1 = " UPDATE factura " + " SET estado = '" + 4
					+ "' " + " WHERE codigoFactura = '"
					+ factura.getCodigoFactura() + "'";
			// System.out.println(insertString1);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString1);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	//@Override
	public Vector<EstadoPedido> getEstadosFacturas() throws Exception {
		Vector<EstadoPedido> estados = new Vector<EstadoPedido>();
		String Qry = " SELECT * FROM fac_orders_estados WHERE factura = 'S' AND idEstado != 4";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				EstadoPedido estado = new EstadoPedido();
				estado.setIdEstadoPedido(rs.getString("idEstado").charAt(0));
				estado.setNombre(rs.getString("nombre"));
				estado.setDescripcion(rs.getString("descripcion"));
				estados.add(estado);
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
		return estados;
	}

	//@Override
	public Vector<TemperaturaTransporte> getTemperaturasTransporte() throws Exception {
		Vector<TemperaturaTransporte> temperaturas = new Vector<TemperaturaTransporte>();
		String Qry = " SELECT * FROM transporte_temperatura";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				TemperaturaTransporte temperatura = new TemperaturaTransporte();
				temperatura.setIdTemperatura(rs.getLong("idTemperatura"));
				temperatura.setDescripcion(rs.getString("descripcion"));
				temperaturas.add(temperatura);
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
		return temperaturas;
	}

	//@Override
	public Vector<PortesTransporte> getPortesTransporte() throws Exception {
		Vector<PortesTransporte> portes = new Vector<PortesTransporte>();
		String Qry = " SELECT * FROM transporte_portes";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				PortesTransporte porte = new PortesTransporte();
				porte.setIdPorte(rs.getLong("idPorte"));
				porte.setDescripcion(rs.getString("descripcion"));
				portes.add(porte);
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
		return portes;
	}

	/**
	 * Nos llega una fecha de la forma dd/mm/aaaa, y la necesitamos de la forma aaaa-mm-dd
	 * @param fecha
	 * @return
	 */
	private String formatoDate(String fecha){
		String retorno = "";
		String[] frag = fecha.split("/");
		String anioAux = frag[2];
		if (anioAux.length() == 2){
			anioAux = "20" + anioAux;
		}
		retorno = anioAux + "-";
		String mesAux = frag[1];
		if (mesAux.length() == 1){
			mesAux = "0" + mesAux;
		}
		retorno += mesAux + "-";
		String diaAux = frag[0];
		if (diaAux.length() == 1){
			diaAux = "0" + diaAux;
		}
		retorno += diaAux;
		return retorno;
	}
	
	/**
	 * @author andres (23/05/2013)
	 * @return Vehiculo, incluyendo su carga y su código QR
	 * @throws Exception
	 */
	//@Override
	public Comercial getLanzaderaPedidos(long idComercial) throws Exception{
		Comercial comercial = new Comercial();
		comercial.setIdComercial(idComercial);
		PreparedStatement pst = null;
		try {
			con = bddConexion();
			// Para el idComercial, mirar cuando fue la última sincronización
			long idUltimaSincronizacion = 0, idUltimaLanzadera = 0;
			String fechaComprobar = "";
			String qry =
				" SELECT log.fecha, log.id " +
				" FROM log_sincronizacion log " +
				" WHERE log.idComercial = ? " +
				" ORDER BY fecha DESC " +
				" LIMIT 1 ";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(qry);
			ps.setLong(1, idComercial);
			rs = ps.executeQuery();
			if (rs.next()){
				fechaComprobar = rs.getString("fecha");
				idUltimaSincronizacion = rs.getLong("id");
			}
			// Mirar si despues de la última sincronización hubo alguna lanzadera para el comercial
			qry =
				" SELECT log.fecha " +
				" FROM log_lanzadera_pedidos log " +
				" WHERE log.idComercial = ? " +
					" AND log.fecha > ? " +
				" ORDER BY fecha DESC " +
				" LIMIT 1 ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, idComercial);
			ps.setString(2, fechaComprobar);
			rs = ps.executeQuery();
			if (rs.next()){
				fechaComprobar = rs.getString("fecha");
			}
			//TODO Miramos que id tendrá la lanzadera que vamos a generar. El log lanzadera lo insertamos más adelante (insertaloglanzadera)
			qry =
				" SELECT MAX(id) as idLanzadera " +
				" FROM log_lanzadera_pedidos; ";
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				idUltimaLanzadera = rs.getLong("idLanzadera") + 1;
			}
			//TODO 1 Buscamos los pedidos que se hayan creado desde la fechaComprobacion
			qry =
				" SELECT DISTINCT p.idOrders, p.bgmNum, p.nadBy, p.moa79, p.idFormaPago, p.estado, p.responsable, p.observaciones " +
				" FROM fac_orders p " +
				" INNER JOIN entidad e ON p.nadBy = e.idUsuario " +
				" WHERE e.idComercial = ? " +
					" AND p.estado = 'R' " +
					" AND (p.fechaRegistro > ? OR p.fechaActualizacion > ?); ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, idComercial);
			ps.setString(2, fechaComprobar);
			ps.setString(3, fechaComprobar);
			rs = ps.executeQuery();
			//String cadenaPedidos = "";
			while (rs.next()) {
				Order pedido = new Order();
				pedido.setBgmNum(rs.getString("bgmNum"));
				pedido.setNadBy(rs.getString("nadBy"));
				pedido.setMoa79(rs.getString("moa79"));
				pedido.setIdFormaPago(rs.getLong("idFormaPago"));
				pedido.setEstado(rs.getString("estado").charAt(0));
				pedido.setResponsable(rs.getString("responsable"));
				pedido.setObservacionesPedido(rs.getString("observaciones"));
				// Para este pedido, mirar las fechas
				String Qry =
					" SELECT f.* " +
					" FROM fac_orders_dtm f " +
					" WHERE f.idOrders = ?; ";
				pst = con.prepareStatement(Qry);
				pst.setLong(1, rs.getLong("idOrders"));
				ResultSet rse = pst.executeQuery();
				while (rse.next()) {
					String dtmFunc = rse.getString("dtmFunc");
					String dtmFech = rse.getString("dtmFech");
					DTM fecha = new DTM();
					fecha.setDtmFech(dtmFech);
					fecha.setDtmFunc(dtmFunc);
					pedido.getDtm().add(fecha);
				}
				rse.close();
				//cadenaPedidos += rs.getLong("") + ",";
				// Leemos las lineas del pedido (los detalles del pedido)
				Qry =
					" SELECT lin.piaNumSA, lin.qty21Cant, lin.priAaa, lin.linNum, p.nombre " +
					" FROM fac_orders_lin lin " +
					" INNER JOIN producto p ON lin.piaNumSA = p.idProducto " +
					" WHERE lin.idOrders = ?; ";//TODO Estamos almacenando mal en la base de datos. Cruzados piaNumSA y piaNumIN
				pst = con.prepareStatement(Qry);
				pst.setLong(1, rs.getLong("idOrders"));
				rse = pst.executeQuery();
				while (rse.next()) {
					OrdersLin linea = new OrdersLin();
					linea.setPiaNumIn(rse.getString("piaNumSA"));
					linea.setQty21Cant(rse.getString("qty21Cant"));
					linea.setPriAaa(rse.getString("priAaa"));
					linea.setDescripcionProducto(rse.getString("nombre"));
					String linNum = rse.getString("linNum");
					Qry =
						" SELECT loc.idLoc, loc.qty " +
						" FROM fac_orders_loc loc " +
						" WHERE loc.idOrders = ? " +
							" AND loc.idLin = ?; ";
					PreparedStatement pst1 = con.prepareStatement(Qry);
					pst1.setLong(1, rs.getLong("idOrders"));
					pst1.setString(2, linNum);
					ResultSet rse1 = pst1.executeQuery();
					while (rse1.next()) {
						OrdersLoc loc = new OrdersLoc();
						loc.setLoc(rse1.getString("idLoc"));
						loc.setQty(rse1.getString("qty"));
						linea.getLoc().add(loc);
					}
					rse1.close();
					pedido.getLin().add(linea);
				}
				rse.close();
				
				comercial.getPedidos().add(pedido);
			}
			
			rs.close();
			
			final int PEDIDOS_POR_HOJA = 10;
			//En cada hoja entran 10 pedidos
			int cuantasHojas = comercial.getPedidos().size() / PEDIDOS_POR_HOJA;
			int productosUltimaHoja = comercial.getPedidos().size() % PEDIDOS_POR_HOJA;
			if (productosUltimaHoja > 0)
				cuantasHojas++;
			int indicePedido = 0;
			for (int i = 0; i < cuantasHojas; i++){
				String urlCodigoQRPagina = "LPEDIDOS=&idSincronizacion=" + idUltimaSincronizacion +
				"&idLanzadera=" + idUltimaLanzadera + "&idComercial=" + idComercial + "&idPagina=" + (i + 1) + "&";
				//Recorre 10 pedidos que irán en esta pagina
				for (int h = 0; h < PEDIDOS_POR_HOJA && indicePedido < comercial.getPedidos().size(); indicePedido++, h++){
					Order pedido = comercial.getPedidos().get(indicePedido);
					String observaciones = "";
					if (pedido.getObservacionesPedido() == null || pedido.getObservacionesPedido().equals(""))
						observaciones = " ";
					urlCodigoQRPagina += "PED_" + pedido.getBgmNum() + "=" + pedido.getNadBy() + "__" +
						pedido.getMoa79() + "__" + pedido.getIdFormaPago() + "__" + pedido.getEstado() + "__" +
						pedido.getResponsable() + "__" + observaciones;
					List<DTM> fechas = pedido.getDtm();
					//1º Fecha de entrega
					for (int k = 0; k < fechas.size(); k++){
						DTM fecha = fechas.get(k);
						if (fecha.getDtmFunc().equals("2")){
							urlCodigoQRPagina += "__" + fecha.getDtmFech();
							break;
						}
					}
					//2º Fecha de registro
					for (int k = 0; k < fechas.size(); k++){
						DTM fecha = fechas.get(k);
						if (fecha.getDtmFunc().equals("137")){
							urlCodigoQRPagina += "__" + fecha.getDtmFech();
							break;
						}
					}
					long idDireccion = Long.parseLong(pedido.getLin().get(0).getLoc().get(0).getLoc());
					urlCodigoQRPagina += "__" + idDireccion;
					List<OrdersLin> lineas = pedido.getLin();
					for (int k = 0; k < lineas.size(); k++){
						OrdersLin linea = lineas.get(k);
						urlCodigoQRPagina += "___" + linea.getPiaNumIn() + "__" +
						linea.getQty21Cant() + "__" + linea.getPriAaa();
					}
					if ((h < (PEDIDOS_POR_HOJA - 1)) && (indicePedido < (comercial.getPedidos().size() - 1)))
						urlCodigoQRPagina += "&";
				}
				//Insertamos la url de la hoja
				comercial.getCodigosQR().add(urlCodigoQRPagina);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				if (pst != null)
					pst.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return comercial;
	}
	
	/**
	 * @author andres (27/05/2013)
	 * @since 1.6
	 */
	public Lanzadera insertaLogLanzadera(Lanzadera l) throws Exception{
		Lanzadera lanzadera = new Lanzadera();
		try {
			con = bddConexion();
			//Obtenemos el id de la nueva lanzadera
			long idLanzadera = 0;
			String qry =
				" SELECT MAX(id) as idLanzadera " +
				" FROM log_lanzadera_pedidos; ";
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				idLanzadera = rs.getLong("idLanzadera") + 1;
			}
			lanzadera.setIdLanzadera(idLanzadera);
			//en l.fecha llega timeStamp
			String timeStamp = l.getFecha();//20130404_132043
			//2013-04-04 17:31:28
			String fechaStr = "";
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyyMMdd_HHmmss");
			Date fecha = formatoDeFecha.parse(timeStamp);
			formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2013-04-04 17:31:28
			fechaStr = formatoDeFecha.format(fecha);
			String insertString =
				" INSERT INTO log_lanzadera_pedidos " +
					"(idComercial, usuarioResponsable, fecha) " +
				" VALUES(?, ?, ?); ";
			PreparedStatement stmt = con.prepareStatement(insertString);
			stmt.setLong(1, l.getIdComercial());
			stmt.setString(2, l.getUsuarioResponsable());
			stmt.setString(3, fechaStr);
			stmt.executeUpdate();
			//Buscamos lanzaderaNumero
			qry =
				" SELECT COUNT(id) as lanzaderaNumero " +
				" FROM log_lanzadera_pedidos log " +
				" WHERE log.idComercial = ?; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, l.getIdComercial());
			rs = ps.executeQuery();
			if (rs.next()) {
				lanzadera.setLanzaderaNumero(rs.getLong("lanzaderaNumero"));
			}
			//Buscamos totalLanzaderas (En este caso tendrá el mismo valor que lanzaderaNumero)
			qry =
				" SELECT COUNT(id) as totalLanzaderas " +
				" FROM log_lanzadera_pedidos log " +
				" WHERE log.idComercial = ?; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, l.getIdComercial());
			rs = ps.executeQuery();
			if (rs.next()) {
				lanzadera.setTotalLanzaderas(rs.getLong("totalLanzaderas"));
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
		return lanzadera;
	}
	
}