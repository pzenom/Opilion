package es.induserco.opilion.datos.entrada;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.induserco.opilion.data.entidades.AnalisisRegistro;
import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.Categoria;
import es.induserco.opilion.data.entidades.Ciclo;
import es.induserco.opilion.data.entidades.Cosecha;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.EstadoFabas;
import es.induserco.opilion.data.entidades.FormatoEntrega;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Incidencia;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroCalidad;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.data.entidades.TipoMantenimiento;
import es.induserco.opilion.data.entidades.TipoMaquina;
import es.induserco.opilion.data.entidades.TipoUbicacion;
import es.induserco.opilion.data.entidades.Vehiculo;
import es.induserco.opilion.datos.produccion.GestionProduccionDAO;
import es.induserco.opilion.data.entidades.MovimientoVehiculo;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.infraestructura.DatabaseConfig;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

/**
 * @author andres (26/05/2011) - (08.11.11)
 * @version 1.1
 */
@SuppressWarnings("deprecation")
public class RegistroEntradaDAO extends DatabaseConfig implements IRegistroEntradaDataService{

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;
	private String USER;
	private String PASS;
	
	public String getDate(Date fc){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    	int anno = fc.getYear() + 1900;
    	int mes = fc.getMonth() + 1;
    	int dia = fc.getDate();
    	Date d = null;
    	String dd = null;
		Calendar fecha = Calendar.getInstance();
		fecha.set(anno,mes,dia);
		String strFecha = fecha.get(java.util.Calendar.YEAR) + "-" +
			mes + "-" + fecha.get(java.util.Calendar.DATE); 
		try {
		    d = format.parse(strFecha);
		    dd = format.format(d);
		} catch (ParseException ex) { ex.printStackTrace(); }
		return dd;
	}

	//@Override
	public Boolean addRegistroOrden(RegistroOrden entry) throws Exception {
		// System.out.println("DAO addRegistroOrden");
		Statement stmt;
		Boolean resultado = false;
		Date fecha = null;
		String stringanno = null;
		int res = 0;		
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idOrden) as idMaxEntrada FROM ordenentrada");
			rs = ps.executeQuery();
			long idOrdenMax =0 ;
			if(rs.next()){
				RegistroOrden entradaMaximo = new RegistroOrden();
				entradaMaximo.setIdOrden(rs.getLong("idMaxEntrada"));
				idOrdenMax= entradaMaximo.getIdOrden()+1;
			}else
				idOrdenMax=1;
			// System.out.println("el id de entrada es... "+ idOrdenMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno = stringanno.substring(2);
			//SQL de insercion
			String insertString= 
				"INSERT INTO ordenentrada(idOrden,codigoorden,fecha,idProveedor,idTransportista," +
					"origen,albaran,descVehiculo,notasvehiculo,usuarioResponsable,habilitado) VALUE(" +
				idOrdenMax + ",'" + entry.getCodigoOrden()+ "','" + fecha + "',"
				+ entry.getIdProveedor() + "," + entry.getIdTransportista() + ",'"
				+ entry.getOrigen() + "','" + entry.getAlbaran() + "','" + entry.getDescVehiculo() + "','"
				+ entry.getNotasVehiculo() + "','"
				+ entry.getIdOperario() + "','S'"
				+ ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("REGISTRO ORDEN INSERTADA");
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
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	//@Override
	public Boolean updateRegistroOrden(RegistroOrden entry) throws Exception {
		// System.out.println("DAO updateRegistroOrden");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//SQL de insercion
			String updateString= 
				"UPDATE ordenentrada " +
					" SET idProveedor =" + entry.getIdProveedor() + ", idTransportista = " + entry.getIdTransportista() +
						", origen = '" + entry.getOrigen() + "', albaran = '" + entry.getAlbaran() +
						"', descVehiculo = '" + entry.getDescVehiculo() + "', notasVehiculo='" + entry.getNotasVehiculo() + "'" +
					" WHERE codigoOrden='" + entry.getCodigoOrden() + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("REGISTRO ORDEN ACTUALIZADA");
				resultado=true;
			}
			return resultado;			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	//@Override
	public Boolean addROMovimientoVehiculo(MovimientoVehiculo entry) throws Exception {
		// System.out.println("DAO addROMovimientoVehiculo");
		Boolean resultado = false;
		Date fecha = null;
		String codigoEntrada = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idOrden) as idMaxEntrada FROM ordenentrada");
			rs = ps.executeQuery();
			long idOrdenMax = 0;
			if(rs.next()){
				RegistroOrden entradaMaximo = new RegistroOrden();
				entradaMaximo.setIdOrden(rs.getLong("idMaxEntrada"));
				idOrdenMax= entradaMaximo.getIdOrden() + 1;
			}
			else
			idOrdenMax = 1;
			// System.out.println("el id de entrada es... "+ idOrdenMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno=stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0"+mes1;	
			}	
			else {
				stringmes = String.valueOf(mes1);
			}
			stringFecha = stringanno + stringmes+fecha.getDate();
			codigoEntrada = "O" + stringFecha + "-" + idOrdenMax;
			entry.setCodigoOrden(codigoEntrada);
			con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			if(res == 1){
				// System.out.println("MOVIMIENTO VEHICULO INSERTADA");
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

	/**
	 * Genara el codigo para los nuevos registros de entrada
	 * @author andres (08.11.11)
	 * @since 1.1 
	 */
	public String generarCodigoEntrada() throws Exception {
		Date fecha = null;
		String codigoEntrada = "";
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada");
			rs = ps.executeQuery();
			long idEntradaMax = 0;
			while(rs.next()){
				RegistroEntrada entradaMaximo = new RegistroEntrada();
				entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
				idEntradaMax= entradaMaximo.getIdEntrada() + 1;
			}
			// System.out.println("el id de entrada es... " + idEntradaMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() AS fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) AS anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno=stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0"+mes1;	
			}	
			else {
				stringmes = String.valueOf(mes1);
			}
			stringFecha = stringanno + stringmes + fecha.getDate();
			codigoEntrada = "E" + stringFecha + "-" + idEntradaMax;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codigoEntrada;
	}
	
	@SuppressWarnings("unchecked")
	//@Override
	public Boolean addRegistroEntrada(RegistroEntrada entry,List listindic,List listestados) throws Exception {
		// System.out.println("DAO addRegistroEntrada");
		Statement stmt;
		Boolean resultado = false;
		String strfechac = null;
		String strfechal = null;
		String codigoEntrada = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada");
			rs = ps.executeQuery();
			long idEntradaMax = 0;
			while(rs.next()){
				RegistroEntrada entradaMaximo = new RegistroEntrada();
				entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
				idEntradaMax= entradaMaximo.getIdEntrada() + 1;
			}
			// System.out.println("el id de entrada es... " + idEntradaMax);
			codigoEntrada = this.generarCodigoEntrada();
			entry.setCodigoEntrada(codigoEntrada);
			if (entry.getFechaCaducidad().split("-").length > 1)
				strfechac = entry.getFechaCaducidad();
			else
				strfechac = formatoDate(entry.getFechaCaducidad());
			if (entry.getFechaLlegada().split("-").length > 1)
				strfechal = entry.getFechaLlegada();
			else
				strfechal = formatoDate(entry.getFechaLlegada());
			//SQL de insercion para un RE original es el padre de simismo
			String insertString =
				"INSERT INTO registroentrada(idEntrada,idEntradaPadre,codigoEntrada,codigoOrden,fecha,fechaCaducidad,fechaLlegada," +
					"idProducto,idCategoria,saldo,cantidad," +
					"notasincidencias,idFormatoEntrega,numeroPallets,numeroBultos,usuarioResponsable,idCosecha," +
					"idCategoriaEntrada,idTipoRegistro,idEnvase,temperatura,humedad,costoUnitario," +
					"costoTotal,idTipoUbicacion,lote) " +
				" VALUES(" +
					idEntradaMax + ","+ idEntradaMax + ",'" + entry.getCodigoEntrada() + "','" + entry.getCodigoOrden() + "', CURRENT_TIMESTAMP, '"
					+ strfechac + "','" + strfechal + "',"
					+ entry.getIdProducto() +"," + entry.getIdCategoria() +","+ entry.getCantidad() +","
					+ entry.getCantidad() +",'" + entry.getNotasIncidencias() +"',"
					+ entry.getIdFormatoEntrega() +"," + entry.getNumeroPallets() +"," 
					+ entry.getNumeroBultos() +",'" + entry.getIdOperario() +"',"
					+ entry.getIdCosecha() +","
					+ entry.getIdCategoriaEntrada() + ",'" + entry.getIdTipoRegistro() +"',"		
					+ entry.getIdEnvase() + ","
					+ entry.getTemperatura() + "," + entry.getHumedad() + ","
					+ entry.getCostoUnitario() + "," + entry.getCostoTotal() + ","
					+ entry.getIdTipoUbicacion() + ",'" + entry.getLote() + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (listindic != null)
				addIncidenciasRegistroEntrada(entry, con, idEntradaMax, listindic);
			if (listestados != null)
				addEstadosRegistroEntrada(entry, con, idEntradaMax, listestados);
			if (entry.getIdTipoRegistro().compareTo("M") == 0){
				actualizaStockMateria(entry.getIdProducto(), entry.getIdCategoriaEntrada(), entry.getCantidad());
			} else
				if (entry.getIdTipoRegistro().compareTo("E") == 0){
					actualizaStockEnvase(entry.getIdEnvase(), entry.getCantidad());
				}
			if(res == 1){
				// System.out.println("REGISTRO INSERTADO");
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
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private boolean actualizaStockMateria(long idProducto, long categoria, double cantidad) throws Exception {
		boolean resultado = false;
		Statement stmt;
		int res=0;
		double stockActual = 0;
		try {
			con = bddConexion();
			//1. Obtener el stock actual DE LA MATERIA CATEGORIZADA
			ps = con.prepareStatement("SELECT stock FROM materiaprima_categoria mp WHERE" +
					" mp.idCategoria='" + categoria + "' " +
					" AND mp.idMateriaPrima = '" + idProducto + "'");
			rs = ps.executeQuery();
			int count = 0;
			while(rs.next()){
				stockActual = rs.getDouble("stock");
				count++;
	        }
			//2. Obtener el stock actual DE LA MATERIA
			double stockMateria = 0;
			ps = con.prepareStatement("SELECT stock FROM materiaprima m WHERE" +
					" m.idProducto='" + idProducto + "'");
			rs = ps.executeQuery();
			while(rs.next()){
				stockMateria = rs.getDouble("stock");
	        }
			if (count == 0){//Insertamos
				String insertString = 
					"INSERT INTO materiaprima_categoria(idMateriaPrima,idCategoria,stock) " +
					" VALUES('"+ idProducto + "','" + categoria + "'," + cantidad + ")";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if(res == 1){
					// System.out.println("REGISTRO INSERTADO");
					resultado=true;
				}
				String update = 
					"UPDATE materiaprima m SET" 
					+ " m.stock = " + (cantidad + stockMateria)
					+ " WHERE m.idProducto='" + idProducto + "'";
				// System.out.println(update);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(update);
				if(res == 1){
					// System.out.println("REGISTRO ACTUALIZADO");
					resultado=true;
				}
			}else{
				double stockFinal = stockActual + cantidad;
				String updateString= 
					"UPDATE materiaprima_categoria mp SET" 
					+ " mp.stock = " + stockFinal
					+ " WHERE mp.idCategoria='" + categoria + "' " +
						" AND mp.idMateriaPrima = '" + idProducto + "'";
				// System.out.println(updateString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(updateString);
				if(res == 1){
					// System.out.println("REGISTRO ACTUALIZADO");
					resultado=true;
				}
				String update = 
					"UPDATE materiaprima m SET"
					+ " m.stock = " + (stockMateria + stockFinal)
					+ " WHERE m.idProducto='" + idProducto + "'";
				// System.out.println(update);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(update);
				if(res == 1){
					// System.out.println("REGISTRO ACTUALIZADO");
					resultado=true;
				}
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

	private boolean actualizaStockEnvase(long idEnvase, double cantidad) throws Exception {
		boolean resultado = false;
		Statement stmt;
		int res=0;
		double stockActual = 0;
		try {
			con = bddConexion();
			//1. Obtener el stock actual
			ps = con.prepareStatement("SELECT stock FROM envase e WHERE" +
					" e.idEnvase = '" + idEnvase + "'");
			rs = ps.executeQuery();
			while(rs.next()){
				stockActual = rs.getDouble("stock");
	        }
			double stockFinal = stockActual + cantidad;
			String updateString = 
				"UPDATE envase e SET" 
				+ " e.stock = " + stockFinal
				+ " WHERE e.idEnvase = '" + idEnvase + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("REGISTRO ACTUALIZADO");
				resultado=true;
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
	
	private boolean actualizaStockProductoFinal(long idProducto, double cantidad) throws Exception{
		boolean resultado = false;
		Statement stmt;
		int res=0;
		double stockActual = 0;
		try {
			con = bddConexion();
			//1. Obtener el stock actual
			ps = con.prepareStatement("SELECT stock FROM producto p WHERE" +
					" p.idProducto = '" + idProducto + "'");
			rs = ps.executeQuery();
			while(rs.next()){
				stockActual = rs.getDouble("stock");
	        }
			double stockFinal = stockActual + cantidad;
			String updateString = 
				"UPDATE producto p SET " 
				+ " p.stock = " + stockFinal
				+ " WHERE p.idProducto = '" + idProducto + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("STOCK DE PRODUCTO FINAL ACTUALIZADO");
				resultado=true;
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

	public RegistroEntrada getRegistroEntrada(String codigoEntrada) throws Exception {
		RegistroEntrada entrada = new RegistroEntrada();
		String idTipoRegistro = "";
		try {
			con = bddConexion();
			//1. Mirar el tipo de registro de entrada
			String consulta =
				"SELECT idTipoRegistro " +
					" FROM registroentrada re " +
					" WHERE codigoEntrada = '" + codigoEntrada + "'";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()) {
				idTipoRegistro = rs.getString("idTipoRegistro");
			}
			if (idTipoRegistro.compareTo("") != 0){
				String Qry = "";
				if (idTipoRegistro.compareTo("E") == 0){
					Qry =
						" SELECT * " +
						" FROM registroentrada r, envase e " +
						" WHERE codigoEntrada ='" +codigoEntrada+"' " +
							" AND r.idEnvase = e.idEnvase";
				}else{
					if (idTipoRegistro.compareTo("M") == 0 || idTipoRegistro.compareTo("S") == 0){
						Qry = "SELECT re.*, m.nombre as nombreProducto, c.nombre as nombreCategoria " +
							" FROM registroentrada re, materiaprima m, categoria c, materiaprima_categoria mc " +
							" WHERE mc.idMateriaPrima = re.idProducto " +
								" AND mc.idCategoria = re.idCategoriaEntrada " +
								" AND m.idProducto = mc.idMateriaPrima " +
								" AND mc.idCategoria = c.idCategoria " +
								" AND codigoEntrada = '" + codigoEntrada + "'";
					}else{
						if (idTipoRegistro.compareTo("P") == 0 || idTipoRegistro.compareTo("D") == 0){
							Qry =
								"SELECT * " +
								" FROM registroentrada re, producto p " +
								" WHERE p.idProducto = re.idProducto " +
									" AND codigoEntrada = '" + codigoEntrada + "'";
						}
					}
				}
				// System.out.println(Qry);
				if (!Qry.equals("")){
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					if (rs.next()) {
						//Completamos los datos del registro
						entrada.setIdEntrada(rs.getLong("idEntrada"));
						entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
						entrada.setCodigoOrden(rs.getString("codigoOrden"));
						String subQry =
							" SELECT e.nombre, e.apellidos " +
							" FROM ordenentrada oe, entidad e " +
							" WHERE oe.codigoOrden = '" + entrada.getCodigoOrden() + "' " +
									" AND oe.idProveedor = e.idUsuario ";
						PreparedStatement ps2;
						ResultSet rs2;
						// System.out.println(subQry);
						ps2 = con.prepareStatement(subQry);
						rs2 = ps2.executeQuery();
						if (rs2.next()) {
							entrada.setDescProveedor(rs2.getString("nombre") + " " + rs2.getString("apellidos"));
						}
						entrada.setFecha(rs.getDate("fecha"));
						entrada.setFechaCaducidad(formatoFecha(rs.getString("fechaCaducidad")));
						entrada.setFechaLlegada(formatoFecha(rs.getString("fechaLlegada")));
						entrada.setHabilitado(rs.getString("habilitado"));
						entrada.setIdFormatoEntrega(rs.getLong("idFormatoEntrega"));
						entrada.setNumeroPallets(rs.getLong("numeroPallets"));
						entrada.setNumeroBultos(rs.getLong("numeroBultos"));
						entrada.setHumedad(rs.getDouble("humedad"));
						entrada.setIdCosecha(rs.getLong("idCosecha"));
						entrada.setLote(rs.getString("lote"));
						entrada.setIdProducto(rs.getLong("idProducto"));
						entrada.setIdEnvase(rs.getLong("idEnvase"));
						entrada.setIdCategoria(rs.getLong("idCategoria"));
						entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
						entrada.setCantidad(rs.getDouble("cantidad"));
						entrada.setNotasIncidencias(rs.getString("notasincidencias"));
						entrada.setIdOperario(rs.getString("usuarioResponsable"));
						entrada.setCostoUnitario(rs.getDouble("costoUnitario"));
						entrada.setCostoTotal(rs.getDouble("costoTotal"));
						entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));
						entrada.setIdTipoRegistro(idTipoRegistro);
						if (idTipoRegistro.compareTo("P") == 0 || idTipoRegistro.compareTo("E") == 0){
							entrada.setDescProducto(rs.getString("nombre"));
						}else{
							if (idTipoRegistro.compareTo("M") == 0 || idTipoRegistro.compareTo("S") == 0){
								entrada.setDescProducto(rs.getString("nombreProducto"));
								entrada.setDescCategoria(rs.getString("nombreCategoria"));
							}
						}
					}
				}else{
					System.out.println("Qry vacía!");
				}
			}else
				return entrada;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		//Retornamos el vector de resultado.
		return entrada;
	}	

	private RegistroEntrada getRegistroEntradaTmp(String codigoEntrada) throws Exception {
		RegistroEntrada entrada = new RegistroEntrada();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM registroentrada_tmp WHERE codigoEntrada = '" + codigoEntrada + "'; "; 
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del registro
				entrada.setIdEntrada(rs.getLong("idEntrada"));
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setFechaCaducidad(rs.getString("fechaCaducidad"));
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setHabilitado(rs.getString("habilitado"));
				entrada.setIdFormatoEntrega(rs.getLong("idFormatoEntrega"));
				entrada.setNumeroPallets(rs.getLong("numeroPallets"));
				entrada.setNumeroBultos(rs.getLong("numeroBultos"));
				entrada.setIdCosecha(rs.getLong("idCosecha"));
				entrada.setLote(rs.getString("lote"));
				entrada.setIdProducto(rs.getLong("idProducto"));
				entrada.setIdCategoria(rs.getLong("idCategoria"));
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				entrada.setCantidad(rs.getDouble("cantidad"));
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));
				entrada.setIdOperario(rs.getString("usuarioResponsable"));
				entrada.setCostoUnitario(rs.getDouble("costoUnitario"));
				entrada.setCostoTotal(rs.getDouble("costoTotal"));
				entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				//con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return entrada;
	}	

	//@Override
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,
			RegistroEntrada entry, List listindic, List listestados) throws Exception {
		// System.out.println("DAO addRegistroSubEntrada");
		Statement stmt;
		Boolean resultado = false;
		Date fecha = null;
		String strfechac = null;
		String strfechal = null;
		String codigoEntrada = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada");
			rs = ps.executeQuery();
			long idEntradaMax = 0;
			while(rs.next()){
				RegistroEntrada entradaMaximo = new RegistroEntrada();
				entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
				idEntradaMax= entradaMaximo.getIdEntrada()+1;
			}
			// System.out.println("el id de subentrada es... "+ idEntradaMax);
			//devuelve los datos complementarios del RE Padre
			RegistroEntrada regAux= getRegistroEntrada(regEntradaFind.getCodigoEntrada());
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno=stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0"+mes1;	
			}
			else {
				stringmes = String.valueOf(mes1);
			}
			stringFecha = stringanno+stringmes+fecha.getDate();
			codigoEntrada = "S"+stringFecha+"-"+idEntradaMax;
			entry.setCodigoEntrada(codigoEntrada);
			strfechac = formatoDate(entry.getFechaCaducidad());
			strfechal = formatoDate(regAux.getFechaLlegada());
			//SQL de insercion
			String insertString= 
				"INSERT INTO registroentrada(idEntrada,idEntradaPadre,codigoEntrada,fecha," +
				"fechaCaducidad,fechaLlegada,idProducto,idCategoriaEntrada,idCategoria,cantidad,saldo," +
				"notasincidencias,idFormatoEntrega,numeroPallets,numeroBultos,usuarioResponsable," +
				"idCosecha,lote,idTipoRegistro) values(" +
				idEntradaMax + "," + regAux.getIdEntrada()+ ",'" + entry.getCodigoEntrada()+ "','"+ fecha +"','"
				+ strfechac +"','"+ strfechal +"',"
				+ regAux.getIdProducto() +","+ regAux.getIdCategoria() +"," 
				+ entry.getIdCategoriaEntrada() +","+ entry.getCantidad() +","
				+ entry.getCantidad() +",'" + entry.getNotasIncidencias() +"',"
				+ regAux.getIdFormatoEntrega() +"," + regAux.getNumeroPallets() +"," 
				+ entry.getNumeroBultos() +",'" + entry.getIdOperario() +"',"
				+ regAux.getIdCosecha() +",'" + regAux.getLote() +"',"	
				+ "'S')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (listindic != null)
				addIncidenciasRegistroEntrada(entry, con, idEntradaMax, listindic);
			if (listestados != null)
				addEstadosRegistroEntrada(entry, con, idEntradaMax, listestados);
			if(res==1){
				// System.out.println("REGISTRO INSERTADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}	

	/**
	 * PROCESO: REGISTROS DE ENTRADA
	 * Retorna todos los RE de consulta general y Jasper)
	 * Sirve para update y delete de un RE
	 * *
	 */
	//@Override
	public Vector<RegistroEntrada> getRegistroEntradas(String codigoEntrada, Date fecha,Long idProducto) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		String Qry2 = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		try {
			con = bddConexion();
			// System.out.println("DAO getRegistroEntradas");
			String Qry =
				"SELECT DISTINCT r.codigoEntrada,r.codigoOrden,r.fecha,r.fechaCaducidad,r.fechaLlegada,r.habilitado," +
					"r.idFormatoEntrega, r.numeroPallets,r.numeroBultos, " +
					" e.nombre as descProveedor, r.idProducto, r.idEnvase, " + 
					"IF(r.idProducto = 0," +
						" (SELECT nombre FROM envase WHERE idEnvase=r.idEnvase)," +
						" (IF(r.listoDistribuir = 'N'," +
							" (SELECT nombre FROM materiaprima WHERE idProducto = r.idProducto) ," +
							" (SELECT nombre FROM producto p WHERE idProducto = r.idProducto))" +
						" )" +
					" ) as descProducto," +
					" r.idCategoria, "+ 
					" (SELECT nombre FROM categoria WHERE idCategoria=r.idCategoria)as descCategoria, " +
					" (SELECT nombre FROM categoria WHERE idCategoria=r.idCategoriaEntrada)as descCategoriaEntrada, "+ 
					" r.cantidad,r.saldo,r.notasincidencias,r.usuarioResponsable,  r.lote,  r.idCosecha, r.idCategoriaEntrada, "+ 
					" r.idTipoRegistro, r.temperatura, r.humedad,  r.idTipoUbicacion, r.costoUnitario, r.costoTotal, "+ 
					" o.origen, o.albaran "+  
					" FROM registroentrada r, entidad e, ordenentrada o, entidad_rol er "+
					" WHERE r.habilitado='S' " + 
						" AND o.codigoOrden= r.codigoOrden " + 
						" AND o.idProveedor = e.idUsuario " +
						" AND er.idEntidad = e.idUsuario ";
			Qry2 = " AND er.idRol = 2 ";
			if(codigoEntrada != null && !codigoEntrada.equals("")){
			   Qry2 = Qry2 + " AND r.codigoEntrada = '" + codigoEntrada + "'";
			}
			if(idProducto != null && idProducto != 0){
			   Qry2 = Qry2 + " AND r.idProducto = " + idProducto;
			}
			// System.out.println("fecha recibida para busqueda "+fecha);
			if(fecha != null){
				//obtener año fecha sistema
				ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
				rs = ps.executeQuery();
				while(rs.next()){
					stringanno = rs.getString("anno");
		        }
				int mes = fecha.getMonth() + 1;
				if (mes < 9){
					stringmes = "0"+mes;	
				}	
				stringFecha = stringanno+"-"+stringmes+"-"+fecha.getDate();
				Qry2 = Qry2 + " AND r.fecha = '" + stringFecha +"'";
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				RegistroEntrada entrada = new RegistroEntrada();
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setFechaCaducidad(rs.getString("fechaCaducidad"));
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setLote(rs.getString("lote"));
				entrada.setHabilitado(rs.getString("habilitado"));
				entrada.setIdFormatoEntrega(rs.getLong("idFormatoEntrega"));
				entrada.setNumeroPallets(rs.getLong("numeroPallets"));
				entrada.setNumeroBultos(rs.getLong("numeroBultos"));
				entrada.setDescProveedor(rs.getString("descProveedor"));
				entrada.setIdProducto(rs.getLong("idProducto"));
				entrada.setDescProducto(rs.getString("descProducto"));
				entrada.setIdCategoria(rs.getLong("idCategoria"));
				entrada.setDescCategoria(rs.getString("descCategoria"));
				entrada.setCantidad(rs.getDouble("cantidad"));	
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));	
				entrada.setIdOperario(rs.getString("usuarioResponsable"));
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				entrada.setIdCosecha(rs.getLong("IdCosecha"));		
				entrada.setIdTipoRegistro(rs.getString("IdTipoRegistro"));					
				entrada.setSaldo(rs.getDouble("saldo"));				
				entrada.setTemperatura(rs.getDouble("temperatura"));
				entrada.setHumedad(rs.getDouble("humedad"));
				entrada.setCostoTotal(rs.getDouble("costoTotal"));
				entrada.setCostoUnitario(rs.getDouble("costoUnitario"));
				entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));	
				entrada.setOrigen(rs.getString("origen"));
				entrada.setAlbaran(rs.getString("albaran"));
				entrada.setDescCategoriaEntrada(rs.getString("descCategoriaEntrada"));
				//La añadimos al Vector de resultado
				resultado.add(entrada);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				//con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	/**
	 * PROCESO: REGISTROS DE ENTRADA
	 * Retorna todos los RE de consulta general y Jasper)
	 * Sirve para update y delete de un RE
	 * *
	 */		
	//@Override
	public Vector<RegistroEntrada> getRegistroEntradas(RegistroEntrada rentrada, int filtro) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		String Qry2 = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		try {
			con = bddConexion();
			// System.out.println("DAO getRegistroEntradas");
			String Qry =
				" SELECT DISTINCT r.codigoEntrada,r.codigoOrden,r.fecha,r.fechaCaducidad,r.fechaLlegada," +
					"r.habilitado,r.idFormatoEntrega, r.numeroPallets,r.numeroBultos, " +
				" e.nombre as descProveedor, r.idProducto, r.idEnvase, " + 
				"IF(r.idProducto = 0," +
					" (SELECT nombre FROM envase WHERE idEnvase=r.idEnvase)," +
					" (IF(r.listoDistribuir = 'N'," +
						" (SELECT nombre FROM materiaprima WHERE idProducto = r.idProducto) ," +
						" (SELECT nombre FROM producto p WHERE idProducto = r.idProducto))" +
					" )" +
				" ) as descProducto," +
				"r.idCategoria, "+ 
				" (SELECT nombre FROM categoria WHERE idCategoria=r.idCategoria)as descCategoria, " +
				" (SELECT nombre FROM envase WHERE idEnvase = r.idEnvase) as descEnvase,"+ 
				" r.cantidad,r.saldo,r.notasincidencias,r.usuarioResponsable,  r.lote,  r.idCosecha, r.idCategoriaEntrada, "+ 
				" r.idTipoRegistro, r.temperatura, r.humedad,  r.idTipoUbicacion, r.costoUnitario, r.costoTotal, "+ 
				" o.origen, o.albaran "+  
				//" FROM registroentrada r, entidad e, ordenentrada o, entidad_rol er "+
				" FROM registroentrada r " +
					" INNER JOIN ordenentrada o ON o.codigoOrden = r.codigoOrden " +
					" INNER JOIN entidad e ON o.idProveedor = e.idUsuario " +
					" INNER JOIN entidad_rol er ON er.idEntidad = e.idUsuario " +
				" WHERE r.habilitado='S' ";
					/*" AND o.codigoOrden= r.codigoOrden " +
					" AND er.idEntidad = e.idUsuario " + 
					" AND o.idProveedor = e.idUsuario ";*/
				Qry2 = " AND er.idRol = 2 ";
			if(rentrada.getIdTipoRegistro() != null && !rentrada.getIdTipoRegistro().equals("") && !rentrada.getIdTipoRegistro().equals("0")){
				Qry2 = Qry2 + " AND r.idTipoRegistro = '" + rentrada.getIdTipoRegistro() + "'";
			}
			if(rentrada.getCodigoEntrada() != null && !rentrada.getCodigoEntrada().equals("")){
				Qry2 = Qry2 + " AND r.codigoEntrada = '" + rentrada.getCodigoEntrada() + "'";
			}
			if(rentrada.getIdProducto() != null && rentrada.getIdProducto() != 0){
				Qry2 = Qry2 + " AND r.idProducto = " + rentrada.getIdProducto();
			}
			if(rentrada.getIdEnvase() != null && rentrada.getIdEnvase() != 0){
				Qry2 = Qry2 + " AND r.idEnvase = " + rentrada.getIdEnvase();
			}
			// System.out.println("fecha recibida para busqueda "+rentrada.getFecha());
			if(rentrada.getFecha() != null){
				//obtener año fecha sistema
				ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
				rs = ps.executeQuery();
				while(rs.next()){
					stringanno = rs.getString("anno");
		        }
				int mes = rentrada.getFecha().getMonth() + 1;
				if (mes < 9){
					stringmes = "0"+mes;
				}	
				stringFecha = stringanno+"-"+stringmes+"-"+rentrada.getFecha().getDate();
				Qry2 = Qry2 + " AND r.fecha = '" + stringFecha +"'";
			}
			String limit = "";
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
					Qry2 = Qry2 + " AND r.fecha between '" + fechaInicio + "' AND '" + fechaFin + "' ";
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
			Qry2 += " ORDER BY r.fecha DESC ";
			Qry = Qry.concat(Qry2).concat(limit);
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				RegistroEntrada entrada = new RegistroEntrada();
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setFechaCaducidad(rs.getString("fechaCaducidad"));
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setLote(rs.getString("lote"));
				entrada.setHabilitado(rs.getString("habilitado"));
				entrada.setIdFormatoEntrega(rs.getLong("idFormatoEntrega"));
				entrada.setNumeroPallets(rs.getLong("numeroPallets"));
				entrada.setNumeroBultos(rs.getLong("numeroBultos"));
				entrada.setDescProveedor(rs.getString("descProveedor"));
				entrada.setIdProducto(rs.getLong("idProducto"));
				entrada.setIdEnvase(rs.getLong("idEnvase"));
				entrada.setIdTipoRegistro(rs.getString("idTipoRegistro"));
				if (entrada.getIdTipoRegistro().equals("E"))//Tenemos un envase
					entrada.setDescProducto(rs.getString("descEnvase"));
				else
					if (entrada.getIdTipoRegistro().equals("M"))
						entrada.setDescProducto(rs.getString("descProducto") + " - " + rs.getString("descCategoria"));
					else
						if (entrada.getIdTipoRegistro().equals("P"))
							entrada.setDescProducto(rs.getString("descProducto"));
				entrada.setIdCategoria(rs.getLong("idCategoria"));
				entrada.setDescCategoria(rs.getString("descCategoria"));
				entrada.setCantidad(rs.getDouble("cantidad"));
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));
				entrada.setIdOperario(rs.getString("usuarioResponsable"));
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				entrada.setIdCosecha(rs.getLong("idCosecha"));
				entrada.setSaldo(rs.getDouble("saldo"));
				entrada.setTemperatura(rs.getDouble("temperatura"));
				entrada.setHumedad(rs.getDouble("humedad"));
				entrada.setCostoTotal(rs.getDouble("costoTotal"));
				entrada.setCostoUnitario(rs.getDouble("costoUnitario"));
				entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));
				entrada.setOrigen(rs.getString("origen"));
				entrada.setAlbaran(rs.getString("albaran"));
				/*
				 * Aqui vamos a comprobar si el registro de entrada se puede eliminar
				 * Se puede eliminar si el codigo de entrada no está en:
				 * A. gp_envasado_detalle, columna codigoEntrada
				 * B. gestionProduccion, columna codigoEntrada
				 * C. bulto_lotes, columna lote
				 */
				String subQry =
					" SELECT gpe.codigoEntrada " +
					" FROM gp_envasado_detalle gpe" +
					" WHERE gpe.codigoEntrada = '" + entrada.getCodigoEntrada() + "'";
				PreparedStatement ps2;
				ResultSet rs2;
				// System.out.println(subQry);
				ps2 = con.prepareStatement(subQry);
				rs2 = ps2.executeQuery();
				if (rs2.next()) {
					entrada.setPodemosBorrar(false);
				}else{
					subQry =
						" SELECT gp.codigoEntrada " +
						" FROM gestionproduccion gp" +
						" WHERE gp.codigoEntrada = '" + entrada.getCodigoEntrada() + "'";
					// System.out.println(subQry);
					ps2 = con.prepareStatement(subQry);
					rs2 = ps2.executeQuery();
					if (rs2.next()) {
						entrada.setPodemosBorrar(false);
					}else{
						subQry =
							" SELECT b.lote " +
							" FROM bulto_lotes b" +
							" WHERE b.lote = '" + entrada.getCodigoEntrada() + "'";
						// System.out.println(subQry);
						ps2 = con.prepareStatement(subQry);
						rs2 = ps2.executeQuery();
						if (rs2.next()) {
							entrada.setPodemosBorrar(false);
						}else{
							entrada.setPodemosBorrar(true);
						}
					}
				}
				//La añadimos al Vector de resultado
				resultado.add(entrada);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;		
	}
	
	/**
	 * PROCESO: ETIQUETA DE REGISTRO DE ENTRADA
	 * Retorna los datos para etiqueta de Jasper
	 */	
	//@Override
	public RegistroEntrada getEtiquetaRE(String codigoEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		RegistroEntrada entrada = new RegistroEntrada();
		try {
			con = bddConexion();
			// System.out.println("DAO getEtiquetaRE");
			String Qry =
				" SELECT re.codigoEntrada, " +
				" IF (re.idProducto = 0, " +
					" (SELECT nombre FROM envase WHERE idEnvase=re.idEnvase), " +
					" (IF(re.listoDistribuir = 'N'," +
						" (SELECT nombre FROM materiaprima WHERE idProducto = re.idProducto), " +
						" (SELECT nombre FROM producto p WHERE idProducto = re.idProducto))" +
					")" +
				") as nombre," +
				" (SELECT nombre FROM envase WHERE idEnvase = re.idEnvase) AS nombreEnvase," +
				" IF(re.idProducto <> 0, (SELECT ca.nombre FROM categoria ca WHERE ca.idCategoria = re.idCategoria), '') AS descCategoria," +
				" re.fechaCaducidad, re.fechaLlegada,re.cantidad," +
				" re.numeroPallets, re.numeroBultos, re.lote " +
				" FROM registroentrada re" +
				" WHERE re.codigoEntrada = '" + codigoEntrada +"'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setDescProducto(rs.getString("nombre"));
				entrada.setDescCategoria(rs.getString("descCategoria"));
				if (entrada.getDescProducto() == null ||
						entrada.getDescProducto().compareTo("") == 0){
					entrada.setDescProducto(rs.getString("nombreEnvase"));
					entrada.setDescCategoria(" ");
				}
				//Formatear las fechas. Vienen de la forma aaaa-mm-dd
				String aux = rs.getString("fechaCaducidad");
				String frag[] = aux.split("-");
				String fechaCaducidad = frag[2] + "-" + frag[1] + "-" + frag[0];
				entrada.setFechaCaducidad(fechaCaducidad);
				aux = rs.getString("fechaLlegada");
				frag = aux.split("-");
				String fechaLlegada = frag[2] + "-" + frag[1] + "-" + frag[0];
				entrada.setFechaLlegada(fechaLlegada);
				entrada.setCantidad(rs.getDouble("cantidad"));
				entrada.setNumeroPallets(rs.getLong("numeroPallets"));
				entrada.setNumeroBultos(rs.getLong("numeroBultos"));
				entrada.setLote(rs.getString("lote"));
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
		return entrada;
	}

	/**
	 * @author Induserco
	 * PROCESO: REGISTROS DE ENTRADA
	 * Devuelve todos los RE asociados a una orden de envasado
	 * **/
	//@Override
	public Vector<Object> getRegistrosEntradaOrden(String codigoOrden) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Object> resultado = new Vector<Object>();
		try {
			con = bddConexion();
			String Qry=
				"SELECT r.codigoOrden,r.codigoEntrada,r.fecha,r.cantidad,r.idCategoriaEntrada, r.idTipoUbicacion, " +
					" r.idProducto,r.idEnvase,r.listoDistribuir " +
				" FROM registroentrada r" +
				" WHERE r.habilitado='S' " +
					" AND r.codigoOrden='" + codigoOrden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			String subQry = "";
			PreparedStatement ps2;
			ResultSet rs2;
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				RegistroEntrada entrada = new RegistroEntrada();
				entrada.setCodigoOrden(rs.getString("codigoOrden"));
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setCantidad(rs.getDouble("cantidad"));
				entrada.setIdProducto(rs.getLong("idProducto"));
				entrada.setIdEnvase(rs.getLong("idEnvase"));
				entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				//Buscamos el nombre del producto/envase
				String productoVenta = rs.getString("listoDistribuir");
				char listoDistribuir = productoVenta.charAt(0);
				char tipo = 'X';
				if (rs.getLong("idProducto") == 0) {
					// System.out.println("Se trata de un envase");
					subQry = "SELECT nombre FROM envase WHERE idEnvase ='" + entrada.getIdEnvase() + "'";
					tipo = 'E';
				} else {
					// System.out.println("Se trata de materia prima o producto para vender");
					if (listoDistribuir == 'N'){
						// System.out.println("ES MATERIA PRIMA");
						subQry =
							"SELECT materiaprima.nombre, c.nombre as nombreCategoria, mc.idMateriaCategoria as idProducto " +
							" FROM materiaprima, registroentrada entrada, categoria c, materiaprima_categoria mc " +
							" WHERE materiaprima.idProducto=mc.idMateriaPrima AND entrada.idCategoriaEntrada = mc.idCategoria " +
								" AND materiaprima.idProducto ='" + entrada.getIdProducto() + "' AND  entrada.idProducto = mc.idMateriaPrima " +
								" AND c.idCategoria = mc.idCategoria AND entrada.codigoEntrada='" + entrada.getCodigoEntrada() + "'";
						tipo = 'M';
					}else{
						if (listoDistribuir == 'S'){
							subQry =
								"SELECT nombre " +
								" FROM producto p " +
								" WHERE p.idProducto = " + entrada.getIdProducto();
							tipo = 'P';
						}
					}
				}
				entrada.setIdTipoRegistro("" + tipo);
				// System.out.println(subQry);
				ps2 = con.prepareStatement(subQry);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					if (tipo == 'E' || tipo == 'P'){
						entrada.setDescProducto(rs2.getString("nombre"));
					}else{
						if (tipo == 'M'){
							entrada.setDescProducto(rs2.getString("nombre") + " - " + rs2.getString("nombreCategoria"));
							entrada.setIdProducto(rs2.getLong("idProducto"));
						}
					}
					// System.out.println("XXXXXX: " + entrada.getDescProducto());
				}
				/*
				 * Aqui vamos a comprobar si el registro de entrada se puede eliminar
				 * Se puede eliminar si el codigo de entrada no está en:
				 * A. gp_envasado_detalle, columna codigoEntrada
				 * B. gestionProduccion, columna codigoEntrada
				 * C. bulto_lotes, columna lote
				 */
				subQry =
					" SELECT gpe.codigoEntrada " +
					" FROM gp_envasado_detalle gpe" +
					" WHERE gpe.codigoEntrada = '" + entrada.getCodigoEntrada() + "'";
				// System.out.println(subQry);
				ps2 = con.prepareStatement(subQry);
				rs2 = ps2.executeQuery();
				if (rs2.next()) {
					entrada.setPodemosBorrar(false);
				}else{
					subQry =
						" SELECT gp.codigoEntrada " +
						" FROM gestionproduccion gp" +
						" WHERE gp.codigoEntrada = '" + entrada.getCodigoEntrada() + "'";
					// System.out.println(subQry);
					ps2 = con.prepareStatement(subQry);
					rs2 = ps2.executeQuery();
					if (rs2.next()) {
						entrada.setPodemosBorrar(false);
					}else{
						subQry =
							" SELECT b.lote " +
							" FROM bulto_lotes b" +
							" WHERE b.lote = '" + entrada.getCodigoEntrada() + "'";
						// System.out.println(subQry);
						ps2 = con.prepareStatement(subQry);
						rs2 = ps2.executeQuery();
						if (rs2.next()) {
							entrada.setPodemosBorrar(false);
						}else{
							entrada.setPodemosBorrar(true);
						}
					}
				}
				//La añadimos al Vector de resultado
				resultado.add(entrada);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<GestionProduccion> getREENProducto(Long idProducto) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			String Qry = "";
			if (idProducto != 0){
				Qry = "SELECT gp.orden, gp.lote, gp.horaInicio,gp.cantidad" +
					   " FROM gp_envasado gp" +
					   " WHERE gp.estadoproceso = 'F' " +
					   " AND gp.idProducto = " + idProducto;
			} else
				Qry = "SELECT gp.orden, gp.lote, gp.horaInicio,gp.cantidad" +
				   " FROM gp_envasado gp" +
				   " WHERE gp.estadoproceso = 'F'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				GestionProduccion gp = new GestionProduccion();
				gp.setOrden(rs.getString("orden"));	
				gp.setLoteAsignado(rs.getString("lote"));
				gp.setHoraProceso(rs.getString("horaInicio"));
				gp.setCantidadProducto(rs.getDouble("cantidad"));
				//La añadimos al Vector de resultado
				resultado.add(gp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

    //@Override
    public Vector<GestionProduccion> getREENProducto(String codigoEan) throws Exception {
        //Inicializamos el Vector de retorno.
        Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
        try {
			con = bddConexion();
            String Qry="SELECT gp.orden, gp.lote, gp.horaInicio,gp.cantidad " +
                       " FROM gp_envasado gp, producto p"+
                       " WHERE gp.estadoproceso = 'F' " +
                       " AND gp.idProducto =p.idProducto " +
                       " AND p.codigoEan='"+codigoEan+"'";
            // System.out.println(Qry);
            ps = con.prepareStatement(Qry);
            rs = ps.executeQuery();
            while (rs.next()) {
                GestionProduccion gp = new GestionProduccion();
                gp.setOrden(rs.getString("orden"));   
                gp.setLoteAsignado(rs.getString("lote"));
                gp.setHoraProceso(rs.getString("horaInicio"));
                gp.setCantidadProducto(rs.getDouble("cantidad"));
                //La añadimos al Vector de resultado
                resultado.add(gp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw (e);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {e.printStackTrace();}
        }
        //Retornamos el vector de resultado.
        return resultado;
    }

	/**
	 * Retorna el detalle del RE del envasado seleccionado
	 * *
	 */
	//@Override
	public Vector<GestionProduccion> getREENDetalle(String orden) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			String Qry="SELECT gp.orden, gp.lote, gp.horaInicio,gp.cantidad" +
			   " FROM gp_envasado gp"+
			   " WHERE gp.orden ='"+orden+"'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				GestionProduccion gp = new GestionProduccion();
				gp.setOrden(rs.getString("orden"));	
				gp.setLoteAsignado(rs.getString("lote"));
				gp.setHoraProceso(rs.getString("horaInicio"));
				gp.setCantidadProducto(rs.getDouble("cantidad"));
				//La añadimos al Vector de resultado
				resultado.add(gp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	//@Override
	public Boolean updateRegistroEntrada(RegistroEntrada entryf, RegistroEntrada entryu,
			List listindic, List listestados)throws Exception {
		// System.out.println("DAO updateRegistroEntrada");
		Statement stmt;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		String subQry = "";
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			String strfechac = formatoDate(entryu.getFechaCaducidad());
			String strfechal = formatoDate(entryu.getFechaLlegada());
			//SQL de insercion
			String updateString =
				" UPDATE registroentrada SET " 
				+ " fechaCaducidad = '" + strfechac + "',"
				+ " fechaLlegada = '" + strfechal + "',"
				+ " idFormatoEntrega = " + entryu.getIdFormatoEntrega() + ","
				+ " numeroPallets = " + entryu.getNumeroPallets() + ","
				+ " numeroBultos = " + entryu.getNumeroBultos() + ","
				+ " lote = '" + entryu.getLote() + "',"
				+ " idProducto = " + entryu.getIdProducto() + ","
				+ " idCategoria = " + entryu.getIdCategoria() + ","
				+ " idCategoriaEntrada = " + entryu.getIdCategoriaEntrada() + ","
				+ " idEnvase = " + entryu.getIdEnvase() + ","
				+ " cantidad = " + entryu.getCantidad() + ","
				+ " notasincidencias = '" + entryu.getNotasIncidencias() + "',"
				+ " idCosecha = " + entryu.getIdCosecha() + ","
				+ " lote = '" + entryu.getLote() + "',"
				+ " usuarioResponsable = '" + entryf.getIdOperario() + "',"
				+ " temperatura = " + entryu.getTemperatura() + ","
				+ " humedad = " + entryu.getHumedad()+ ","
				+ " costoUnitario = " + entryu.getCostoUnitario() + ","
				+ " costoTotal = " + entryu.getCostoTotal()+ ","
				+ " idTipoUbicacion = " + entryu.getIdTipoUbicacion()
				+ " WHERE codigoEntrada = '" + entryf.getCodigoEntrada() +"'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			//Buscamos el nombre del producto/envase
			subQry = " SELECT idTipoRegistro FROM registroentrada WHERE codigoEntrada ='" + entryf.getCodigoEntrada() + "'";
			// System.out.println(subQry);
			ps2 = con.prepareStatement(subQry);
			rs2 = ps2.executeQuery();
			char tipo = 'X';
			if (rs2.next()) {
				tipo = rs2.getString("idTipoRegistro").charAt(0);
			}
			if (tipo == 'E') {
				// System.out.println("Se trata de un envase");
				subQry = "SELECT nombre FROM envase WHERE idEnvase ='" + entryu.getIdEnvase() + "'";
			} else {
				if (tipo == 'M'){
					// System.out.println("ES MATERIA PRIMA");
					subQry =
						"SELECT materiaprima.nombre, c.nombre as nombreCategoria, mc.idMateriaCategoria as idProducto " +
						" FROM materiaprima, registroentrada entrada, categoria c, materiaprima_categoria mc " +
						" WHERE materiaprima.idProducto=mc.idMateriaPrima AND entrada.idCategoriaEntrada = mc.idCategoria " +
							" AND materiaprima.idProducto ='" + entryu.getIdProducto() + "' AND  entrada.idProducto = mc.idMateriaPrima " +
							" AND c.idCategoria = mc.idCategoria AND entrada.codigoEntrada='" + entryf.getCodigoEntrada() + "'";
				}else{
					if (tipo == 'P'){
						subQry =
							"SELECT nombre " +
							" FROM producto p " +
							" WHERE p.idProducto = " + entryu.getIdProducto();
					}
				}
			}
			// System.out.println(subQry);
			ps2 = con.prepareStatement(subQry);
			rs2 = ps2.executeQuery();
			boolean estaCategorizada = false;
			while (rs2.next()) {
				if (tipo == 'E' || tipo == 'P'){
					entryu.setDescProducto(rs2.getString("nombre"));
				}else{
					if (tipo == 'M'){
						entryu.setDescProducto(rs2.getString("nombre") + " - " + rs2.getString("nombreCategoria"));
						entryu.setIdProducto(rs2.getLong("idProducto"));
						estaCategorizada = true;
					}
				}
				// System.out.println("XXXXXX: " + entryu.getDescProducto());
			}
			if (tipo == 'M' && !estaCategorizada){
				//Categorizar la materia prima
				 String insertSql = "INSERT INTO materiaprima_categoria(idMateriaPrima,idCategoria,stock,habilitado) " +
				 		" VALUES (" + entryu.getIdProducto() + "," + entryu.getIdCategoriaEntrada() + ",0,'S')";
					// System.out.println(insertSql);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertSql);
			}
			// Obtener el idEntrada
			RegistroEntrada RE = getRegistroEntrada(entryf.getCodigoEntrada());
			//actualizamos las incidencias y estados		
			updateEstadosRegistroEntrada(entryu, RE.getIdEntrada(), listestados);
			updateIncidenciasRegistroEntrada(entryu, RE.getIdEntrada(), listindic);
			if(res == 1){
				// System.out.println("REGISTRO ACTUALIZADO");
				resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private void updateEstadosRegistroEntrada(RegistroEntrada entry, long idRE, List listaEstados) throws SQLException {
		Statement stmt1;
		try {
			String deleteSql = "DELETE FROM registroentrada_estado WHERE idEntrada= '" + idRE + "'";
			con = bddConexion();
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			try {
				stmt1.executeUpdate(deleteSql);
			} catch (Exception e) {
				purgaTemporales(con, entry.getIdOperario());
			}
			if(listaEstados != null && listaEstados.isEmpty()){
				// System.out.println("No hay lista estados");
			}else {
				addEstadosRegistroEntrada(entry, con, idRE, listaEstados);
			}
		}catch (Exception e) {e.printStackTrace();}
	}
	
	private void updateIncidenciasRegistroEntrada(RegistroEntrada entry, long idRE, List listaIncidencias) throws SQLException {
		Statement stmt1;
		try {
			String deleteSql="DELETE FROM registroentrada_incidencia WHERE idEntrada= '" + idRE + "'";		
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			try {
				stmt1.executeUpdate(deleteSql);
			} catch (Exception e) {
				purgaTemporales(con, entry.getIdOperario());
			}
			if(listaIncidencias == null || listaIncidencias.isEmpty()){
				// System.out.println("No hay lista incidencias");
			}else {
				if (listaIncidencias != null)
					addIncidenciasRegistroEntrada(entry, con, idRE, listaIncidencias);
			}
		} catch (Exception e) {e.printStackTrace();}
	}

	//@Override
	public Boolean deleteRegistroEntrada(RegistroEntrada entryd) throws Exception {
		// System.out.println("DAO deleteRegistroEntrada");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//1. SQL deshabilita registro
			String updateString = 
				"UPDATE registroentrada SET " 
				+ " habilitado = 'N' "
				+ " WHERE codigoEntrada = '" + entryd.getCodigoEntrada() +"'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			//2. Deshabilitamos las ubicaciones del registro
			updateString = 
				"UPDATE ubicacion SET " 
				+ " habilitado = 'N' "
				+ " WHERE registro = '" + entryd.getCodigoEntrada() + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			//3. Disminuimos el stock del envase, materia prima o producto final
			//Para ello, tenemos que saber primero si se trata de envase, materia prima o producto final
			String Qry =
				" SELECT re.idTipoRegistro, re.idEnvase, re.idProducto, re.idCategoria, re.saldo " +
				" FROM registroentrada re " +
				" WHERE re.codigoEntrada = '" + entryd.getCodigoEntrada() + "'";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			char idTipoRegistro = ' ';
			long idEnvase = 0, idProducto = 0, idMateria = 0, idCategoria = 0;
			double saldo = 0;
			if (rs.next()) {
				idTipoRegistro = rs.getString("idTipoRegistro").charAt(0);
				idEnvase = rs.getLong("idEnvase");
				idProducto = rs.getLong("idProducto");
				idMateria = rs.getLong("idProducto");
				idCategoria = rs.getLong("idCategoria");
				saldo = rs.getDouble("saldo");
			}
			updateString = "";
			switch (idTipoRegistro){
				case 'M'://Materia prima
					updateString = 
						" UPDATE materiaprima_categoria " +
						" SET stock = stock - " + saldo + " " +
						" WHERE idMateriaPrima = '" + idMateria + "' " +
								" AND idCategoria = '" + idCategoria + "'";
					break;
				case 'E'://Envase
					updateString = 
						" UPDATE envase " +
						" SET stock = stock - " + saldo + " " +
						" WHERE idEnvase = '" + idEnvase + "'";
					break;
				case 'P'://Producto
					updateString = 
						" UPDATE producto " +
						" SET stock = stock - " + saldo + " " +
						" WHERE idProducto = '" + idProducto + "'";
					break;
			}
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if (idTipoRegistro == 'M'){
				//Modificamos tambien el stock de la materia prima (sin categorizar)
				updateString = 
					" UPDATE materiaprima " +
					" SET stock = stock - " + saldo + " " +
					" WHERE idProducto = '" + idMateria + "' ";
				// System.out.println(updateString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(updateString);
			}
			// Obtener el idEntrada
			//RegistroEntrada RE = getRegistroEntrada(entryd.getCodigoEntrada());
			// Borrar las incidencias y estados
			//deleteEstadosRegistroEntrada(RE.getIdEntrada());
			//deleteIncidenciasRegistroEntrada(RE.getIdEntrada());
			if(res == 1){
				// System.out.println("REGISTRO ELIMINADO (Deshabilitado)");
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
	public Boolean deleteOrdenEntrada(String codigoOrden) throws Exception {
		//ATENCION: En codigoOrden llega registroEntrada, no el codigo de la orden
		// System.out.println("DAO deleteOrdenEntrada");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			String Qry =
				" SELECT re.codigoOrden " +
				" FROM registroentrada re " +
				" WHERE re.codigoEntrada = '" + codigoOrden + "'";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			String orden = "";
			if (rs.next()) {
				orden = rs.getString("codigoOrden");
			}
			//SQL deshabilita registro
			String updateString= 
				"UPDATE ordenentrada SET "
				+ " habilitado = 'N' "
				+ " WHERE codigoOrden = '" + orden +"'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("ORDEN ENTRADA ELIMINADA (Deshabilitada)");
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

	/*private void deleteEstadosRegistroEntrada(long idRE, String idOperario) throws SQLException {
		Statement stmt1;
		try {
			String deleteSql="DELETE FROM registroentrada_estado WHERE idEntrada= '" + idRE + "'";
			con = bddConexion();
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			try {
				stmt1.executeUpdate(deleteSql);
			} catch (Exception e) {
				purgaTemporales(con, idOperario);
			}
		}catch (Exception e) {e.printStackTrace();}
	}
	
	private void deleteIncidenciasRegistroEntrada(long idRE, String idOperario) throws SQLException {
		Statement stmt1;
		try {
			String deleteSql="DELETE FROM registroentrada_incidencia WHERE idEntrada= '" + idRE + "'";		
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			try {
				stmt1.executeUpdate(deleteSql);
			} catch (Exception e) {
				purgaTemporales(con, idOperario);
			}
		} catch (Exception e) {e.printStackTrace();}
	}*/

	//@Override
	/**
	 * @method		getOrdenes()
	 * @params 		ninguno
	 * @return 		vector de ordenes
	 * @author 		Induserco
	 * @explain 	retorna las órdenes abiertas en la BDD.
	 * 
	 */
	public Vector<RegistroOrden> getOrdenes() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<RegistroOrden> resultado = new Vector<RegistroOrden>();
		try {
			con = bddConexion();
			String Qry="SELECT idOrden,codigoOrden FROM ordenentrada WHERE habilitado = 'S'";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos de la orden
				RegistroOrden registroOrden = new RegistroOrden();
				registroOrden.setIdOrden(rs.getLong("idOrden"));
				registroOrden.setCodigoOrden(rs.getString("codigoOrden"));
				//La añadimos al Vector de resultado
				resultado.add(registroOrden);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e){ e.printStackTrace(); }
		}
		//Retornamos el vector de resultado.
		return resultado;
	}	

	//@Override
	/**
	 * @method		getOrdenes()
	 * @param filtro 0: todos; 1: ultimos 20; 2: ultimos 50; 3: ultima semana; 4: ultimo mes; 5: ultimo año; 
	 * @return 		vector de ordenes
	 * @author 		Induserco
	 * @explain 	retorna las órdenes abiertas en la BDD.
	 * 
	 */
	public Vector <RegistroOrden> getOrdenes(RegistroOrden orden, int filtro) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<RegistroOrden> resultado = new Vector<RegistroOrden>();
		String Qry = "";
		String Qry2 = "";
		try {
			con = bddConexion();
			Qry =
				" SELECT o.codigoOrden, o.fecha, o.idTipoRegistro, o.idProveedor, e.nombre,o.albaran "+
				" FROM ordenentrada o, entidad e"+
				" WHERE o.idProveedor = e.idUsuario ";
			Qry2 = " AND o.habilitado='S'";			
			if(orden.getCodigoOrden() != null && !orden.getCodigoOrden().equals("")){
			   Qry2 = Qry2 + " AND o.codigoOrden = '" + orden.getCodigoOrden() + "'";
			}
			if(orden.getIdProveedor() != null && orden.getIdProveedor() != 0){
			   Qry2 = Qry2 + " AND o.idProveedor = " + orden.getIdProveedor();
			}
			String limit = "";
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
					Qry2 = Qry2 + " AND o.fecha between '" + fechaInicio + "' AND '" + fechaFin + "' ";
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
			Qry2 += " ORDER BY o.fecha DESC ";
			Qry = Qry.concat(Qry2).concat(limit);
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos de la orden
				RegistroOrden registroOrden = new RegistroOrden();
				Entidad proveedor = new Entidad();
				registroOrden.setCodigoOrden(rs.getString("codigoOrden"));
				registroOrden.setFecha(rs.getDate("fecha"));
				registroOrden.setIdTipoRegistro(rs.getString("idTipoRegistro"));
				registroOrden.setAlbaran(rs.getString("albaran"));
				proveedor.setNombre(rs.getString("nombre"));
				registroOrden.setProveedor(proveedor);
				//La añadimos al Vector de resultado
				resultado.add(registroOrden);
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
		//Retornamos el vector de resultado.
		return resultado;
	}	

	//@Override
	/**
	 * @method		getProveedores()
	 * @params 		ninguno
	 * @return 		vector de proveedores
	 * @author 		Induserco
	 * @explain 	retorna los proveedores restringidos a idRol=2 en la BDD.
	 * 
	 */
	public Vector<Entidad> getProveedores() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Entidad> resultado = new Vector<Entidad>();
		try {
			con = bddConexion();
			//Muestra solo los proveedores habilitados
			String Qry =
				" SELECT IF (nombre='', apellidos, IF(apellidos='', nombre, CONCAT(nombre, ' - ', apellidos))) as nombre, " +
					" en.idUsuario,en.nif,en.fechaRegistro,en.web,rol.idTipoSector " +
				" FROM entidad en, entidad_rol rol " +
				" WHERE rol.idRol = 2 " +
					" AND rol.idEntidad = en.idUsuario  AND en.habilitado='S' AND idTipoSector IN(1,4) " +
				" GROUP BY idUsuario ORDER BY en.nombre";		
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Entidad entidad = new Entidad();
				entidad.setIdUsuario(rs.getLong("idUsuario"));
				entidad.setNombre(rs.getString("nombre"));
				long idTipo = rs.getLong("idTipoSector");
				entidad.setIdTipo(idTipo);
				//La añadimos al Vector de resultado
				resultado.add(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	/**
	 * @method		getTransportistas()
	 * @params 		ninguno
	 * @return 		vector de proveedores
	 * @author 		Induserco
	 * @explain 	retorna los proveedores restringidos a idRol=2 en la BDD.
	 * 
	 */
	public Vector<Entidad> getTransportistas() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Entidad> resultado = new Vector<Entidad>();
		try {
			con = bddConexion();
			String Qry =
				"SELECT IF (nombre='', apellidos, IF(apellidos='', nombre, CONCAT(nombre, ' - ', apellidos))) as nombre, " +
					" en.idUsuario,en.nif,en.fechaRegistro,en.web,rol.idTipoSector " +
				" FROM entidad en, entidad_rol rol " +
				" WHERE rol.idRol = 2 " +
					" AND rol.idTipoSector = 2 " +
					" AND rol.idEntidad = en.idUsuario  AND en.habilitado='S' " +
				" ORDER BY en.nombre";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Entidad entidad = new Entidad();
				entidad.setIdUsuario(rs.getLong("idUsuario"));
				entidad.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Producto> getProductos(String filtro) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry="SELECT idProducto,nombre FROM materiaprima WHERE tipo='"+filtro+"'";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Producto prod = new Producto();
				prod.setIdProducto(rs.getLong("idProducto"));
				prod.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Producto> getProductos() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry="SELECT idProducto,nombre FROM materiaprima order by nombre";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto prod = new Producto();
				prod.setIdProducto(rs.getLong("idProducto"));
				prod.setNombre(rs.getString("nombre"));
				resultado.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Envase> getEnvases() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Envase> resultado = new Vector<Envase>();
		try {
			con = bddConexion();
			String Qry="SELECT idEnvase,nombre FROM envase order by nombre";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Envase envase = new Envase();
				envase.setIdEnvase(rs.getLong("idEnvase"));
				envase.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(envase);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	//@Override
	public Vector<Categoria> getCategorias(String codigoEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Categoria> resultado = new Vector<Categoria>();
		try {
			con = bddConexion();
			String Qry = "";
			if (codigoEntrada.compareTo("") != 0){
				Qry = "SELECT DISTINCT mp.nombre as nombreMateria, c.nombre, mc.idCategoria, mc.stock " +
						" FROM categoria c, materiaprima_categoria mc, materiaprima mp, registroentrada re " +
						" WHERE c.idCategoria = mc.idCategoria " +
							" AND mp.idProducto = mc.idMateriaPrima " +
							" AND re.idProducto = mp.idProducto " +
							//" AND re.idCategoria = c.idCategoria " + 
							" AND re.codigoEntrada = '" + codigoEntrada + "'";
			}else
				Qry = "SELECT idCategoria,nombre FROM categoria ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getLong("idCategoria"));
				cat.setNombreCategoria(rs.getString("nombre"));
				if (codigoEntrada.compareTo("") != 0)
					cat.setStock(rs.getDouble("stock"));
				//La añadimos al Vector de resultado
				resultado.add(cat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Categoria> getCategoriasFiltradas(int filtro) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Categoria> resultado = new Vector<Categoria>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM  categoria WHERE idCategoria="+filtro;			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Categoria cat = new Categoria();
				cat.setIdCategoria(rs.getLong("idCategoria"));
				cat.setNombreCategoria(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(cat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Vehiculo> getVehiculos() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Vehiculo> resultado = new Vector<Vehiculo>();
		try {
			con = bddConexion();
			String Qry="SELECT idVehiculo,matricula FROM vehiculo";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Vehiculo vh = new Vehiculo();
				vh.setIdVehiculo(rs.getLong("idVehiculo"));
				vh.setMatricula(rs.getString("matricula"));
				//La añadimos al Vector de resultado
				resultado.add(vh);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Cosecha> getCosechas() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Cosecha> resultado = new Vector<Cosecha>();
		try {
			con = bddConexion();
			String Qry="SELECT idCosecha,nombre FROM cosecha";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Cosecha c = new Cosecha();
				c.setIdCosecha(rs.getLong("idCosecha"));
				c.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Incidencia> getIncidenciasFiltradas(int filtro) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Incidencia> resultado = new Vector<Incidencia>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM  incidencia";
			String Qry1 = "";
			if(filtro == 1)
				Qry1=" WHERE idIncidencia != 1";
			Qry = Qry + Qry1;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Incidencia in = new Incidencia();
				in.setIdIncidencia(rs.getLong("idIncidencia"));
				in.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(in);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Listado de todas las incidencias a un RE.
	 *
	 * @return the incidencias
	 * @throws Exception the exception
	 */
	//@Override
	public Vector<Incidencia> getIncidencias() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Incidencia> resultado = new Vector<Incidencia>();
		try {
			con = bddConexion();
			String Qry = "SELECT idIncidencia,nombre FROM incidencia ORDER BY 1";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Incidencia in = new Incidencia();
				in.setIdIncidencia(rs.getLong("idIncidencia"));
				in.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public String getFechaRegistro() throws Exception {
	   String mes = ""; 
	   Calendar fecha = java.util.Calendar.getInstance();
	   switch(fecha.get(java.util.Calendar.MONTH)){
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
	   String fecharetorno = fecha.get(java.util.Calendar.DATE) + "/"
	   		+ mes + "/"
		    + fecha.get(java.util.Calendar.YEAR);
	   return fecharetorno;
	   
	}

	//@Override
	public String getFechaCaducidad() throws Exception {
	   String mes = ""; 
	   Calendar fecha = java.util.Calendar.getInstance();
	   fecha.add(Calendar.DAY_OF_MONTH, 540);
	   switch(fecha.get(java.util.Calendar.MONTH)){
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
	   String fecharetorno = fecha.get(java.util.Calendar.DATE) + "/"
	   		+ mes + "/"
		    + fecha.get(java.util.Calendar.YEAR);
	   // System.out.println("Fecha de Caducidad en el DAO "+fecharetorno);
	   return fecharetorno;
	   
	}	

	//@Override
	public Vector<FormatoEntrega> getFormatos() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<FormatoEntrega> resultado = new Vector<FormatoEntrega>();
		try {
			con = bddConexion();
			String Qry="SELECT idFormatoEntrega,descripcion FROM formatoentrega";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del formato de entrada en el registro
				FormatoEntrega fe = new FormatoEntrega();
				fe.setidFormatoEntrega(rs.getLong("idFormatoEntrega"));
				fe.setDescripcion(rs.getString("descripcion"));
				//La añadimos al Vector de resultado
				resultado.add(fe);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Entidad> getOperarios() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Entidad> resultado = new Vector<Entidad>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM entidad WHERE idRol = 3";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Entidad entidad = new Entidad();
				entidad.setIdUsuario(rs.getLong("idUsuario"));
				entidad.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(entidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Estado de los productos en RE.
	 *
	 * @return the estados fabas
	 * @throws Exception the exception
	 */
	//@Override
	public Vector<EstadoFabas> getEstadosFabas() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<EstadoFabas> resultado = new Vector<EstadoFabas>();
		try {
			con = bddConexion();
			String Qry="SELECT idEstadoFabas,descripcion FROM estadofabas order by 1";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				//Completamos los datos del formato de entrada en el registro
				EstadoFabas ef = new EstadoFabas();
				ef.setIdEstadoFabas(rs.getLong("idEstadoFabas"));
				ef.setDescripcion(rs.getString("descripcion"));
				//La añadimos al Vector de resultado
				resultado.add(ef);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	private void addIncidenciasRegistroEntrada(RegistroEntrada entry, Connection con,
			long idRegistroEntradaMax, List listaIncidencias) throws SQLException {
		Statement stmt1;
		if(listaIncidencias.isEmpty()){
			// System.out.println("No hay lista incidencias adios");
		}else{
			Iterator itr = listaIncidencias.iterator();
			while(itr.hasNext()) {
			    String idIncid = (String)itr.next();
			    // System.out.print(" Incidencia es :" + idIncid);
				//SQL de insercion
				String insertSql=
					"INSERT INTO registroentrada_incidencia(idEntrada,idIncidencia) VALUES(" +
					idRegistroEntradaMax + ","
					+ idIncid + ")";
				// System.out.println(insertSql);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertSql);
			}
		}
	}

	private void addEstadosRegistroEntrada(RegistroEntrada entry, Connection con,
			long idRegistroEntradaMax, List listaEstados) throws SQLException {
		Statement stmt1;
		if(listaEstados == null || listaEstados.isEmpty()){
			// System.out.println("No hay lista estados");
		}else{
			Iterator itr = listaEstados.iterator(); 
			while(itr.hasNext()) {
			    String idEstado = (String)itr.next(); 
			    // System.out.print("Calidad es :"+idEstado);
				//SQL de insercion
				String insertSql= 
					"INSERT INTO registroentrada_estado(idEntrada,idEstado) " +
						" VALUES(" + idRegistroEntradaMax + "," + idEstado + ")";		
				// System.out.println(insertSql);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertSql);
			}
		}
	}

	//@Override
	public Boolean addAnalisisCalidadRegistro(RegistroEntrada entry,
		Long varIGL, Long varSPL, Long varDPL, Long varDAL, Long varML,
		double calidad, String baremoCalidad) throws Exception {
		// System.out.println("DAO addAnalisisCalidadRegistro");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id Analisis
			ps = con.prepareStatement("SELECT max(idAnalisis) as idMaxAnalisis FROM analisis_registro");
			rs = ps.executeQuery();
			long idAnalisisMax =0 ;
			if(rs.next()){
				AnalisisRegistro analisisMaximo = new AnalisisRegistro();
				analisisMaximo.setIdAnalisis(rs.getLong("idMaxAnalisis"));
				idAnalisisMax= analisisMaximo.getIdAnalisis()+1;
			}else
				idAnalisisMax=1;
			//obtener el idRegistro
			ps = con.prepareStatement("SELECT idEntrada FROM registroentrada WHERE codigoEntrada='"+entry.getCodigoEntrada()+"'");
			rs = ps.executeQuery();
			RegistroEntrada registroEntrada = new RegistroEntrada();
			if(rs.next()){
				registroEntrada.setIdEntrada(rs.getLong("idEntrada"));
			}
			// System.out.println("el id de Analisis es... "+ idAnalisisMax);
			// System.out.println("el id de Id Entrada es... "+ registroEntrada.getIdEntrada());
			//SQL de insercion
			String insertString =
				"INSERT INTO analisis_registro(idAnalisis,idRegistroEntrada,varIG,varSP,varDP,varDA,varM,calidad,baremoCalidad) " +
					" VALUES(" +
					idAnalisisMax + ","
					+ registroEntrada.getIdEntrada() + ","
					+ varIGL +","
					+ varSPL +","
					+ varDPL +","
					+ varDAL +","	
					+ varML +","	
					+ calidad +",'"				
					+ baremoCalidad +
					"')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("ANALISIS REGISTRO INSERTADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	//@Override
	public Vector<TipoUbicacion> getTipoUbicaciones() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<TipoUbicacion> resultado = new Vector<TipoUbicacion>();
		try {
			con = bddConexion();
			String Qry="SELECT idAlmacen, descripcion FROM ubicacion_almacen";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				TipoUbicacion tu = new TipoUbicacion();
				tu.setIdTipoUbicacion(rs.getLong("idAlmacen"));
				tu.setNombre(rs.getString("descripcion"));
				//La añadimos al Vector de resultado
				resultado.add(tu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Genera un subregistro de entrada derivado de un proceso
	 * Desgranado, Cribado, Envasado, etc.
	 *
	 * @param regEntradaFind the reg entrada find
	 * @param entry the entry
	 * @param listindic the listindic
	 * @param listestados the listestados
	 * @param tipoProceso the tipo proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	//@Override
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind,
			RegistroEntrada entry,List listindic,List listestados,String tipoProceso) throws Exception {
		// System.out.println("DAO addRegistroSubEntrada");
		Statement stmt;
		Boolean resultado = false;
		Date fecha = null;
		String strfechac = null;
		String strfechal = null;
		String codigoEntrada = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		Long producto = null;
		Long categoria = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada");
			rs = ps.executeQuery();
			long idEntradaMax =0 ;
			while(rs.next()){
				RegistroEntrada entradaMaximo = new RegistroEntrada();
				entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
				idEntradaMax= entradaMaximo.getIdEntrada()+1;
			}
			// System.out.println("el id de subentrada es... "+ idEntradaMax);
			//devuelve los datos complementarios del RE Padre
			RegistroEntrada regAux = getRegistroEntrada(regEntradaFind.getCodigoEntrada());
			
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno=stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0"+mes1;	
			}	
			else {
				stringmes = String.valueOf(mes1);
			}			
			stringFecha = stringanno+stringmes+fecha.getDate();
			codigoEntrada = "S" + stringFecha + "-" + idEntradaMax;
			entry.setCodigoEntrada(codigoEntrada);
			strfechac = formatoDate(entry.getFechaCaducidad());
			strfechal = formatoDate(regAux.getFechaLlegada());
			if(tipoProceso.equalsIgnoreCase("D")){
				producto = entry.getIdProducto();
				categoria = regAux.getIdCategoriaEntrada();
			}else if(tipoProceso.equalsIgnoreCase("C")){
				producto = regAux.getIdProducto();
				categoria = entry.getIdCategoriaEntrada();
			}else{
				producto = regAux.getIdProducto();
				categoria = entry.getIdCategoriaEntrada();
			}
			//1. Inserta el nuevo Subregistro de Entrada con campos heredados y nuevos
			String insertString =
				" INSERT INTO registroentrada(idEntrada,idEntradaPadre,codigoEntrada,fecha,fechaCaducidad,fechaLlegada," +
					" idProducto,idCategoriaEntrada,idCategoria,cantidad,saldo,notasincidencias," +
					" idFormatoEntrega,numeroPallets,numeroBultos,usuarioResponsable,idCosecha,lote,idTipoRegistro) " +
				" VALUES(" +
					idEntradaMax + "," + regAux.getIdEntrada()+ ",'" + entry.getCodigoEntrada()+ "','"+ fecha +"','"
					+ strfechac +"','"+ strfechal +"',"
					+ producto +","+ regAux.getIdCategoria() +"," + categoria +","
					+ entry.getCantidad() +","+ entry.getCantidad() +",'" + entry.getNotasIncidencias() +"',"
					+ regAux.getIdFormatoEntrega() +"," + regAux.getNumeroPallets() +"," 
					+ entry.getNumeroBultos() +",'" + entry.getIdOperario() +"',"
					+ regAux.getIdCosecha() +",'" + regAux.getLote() +"'," + "'S'"
				+ ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			/** 1.1 addIncidenciasRegistroEntrada: en este caso le estamos pasando listindic vacía porque pidio que en 
			 * 		el proceso de Desgranado D se omitan las incidencias.
			 **/
			if (listindic != null)
				addIncidenciasRegistroEntrada(entry, con, idEntradaMax, listindic);
			/** 1.2 addEstadosRegistroEntrada: son los estados en los q se puede encontrar un RE.
			 **/
			if (listestados != null)
				addEstadosRegistroEntrada(entry, con, idEntradaMax, listestados);
			/** 1.3 updateRegistroPadreSaldo: actualiza el saldo del RE Padre
			 **/			
			updateRegistroPadreSaldo(regAux.getIdEntrada(),con, entry.getCantidad());
			GestionProduccionDAO gpd = new GestionProduccionDAO();
			long idHueco = 221;//Identificador de la entrada al almacen
			if (regAux.getIdTipoRegistro().compareTo("P") == 0){
				//Si el registro de entrada es un producto para vender, lo almacenamos directamente en salida (id del hueco = 223)
				idHueco = 223;
			}
			insertString =
				" INSERT INTO ubicacion(idHueco, registro, cantidad, orden) " +
				" VALUES (" + idHueco + ", '" + entry.getCodigoEntrada() + "'" +
					", " + entry.getCantidad() + "," + entry.getCodigoOrden() + ");";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(tipoProceso.equalsIgnoreCase("D")){
				GestionProduccion gp = new GestionProduccion();
				List<RegistroEntrada> regEntradas= new ArrayList<RegistroEntrada>();
				regEntradas.add(regAux);
				gp.setRegEntradas(regEntradas);
				if (producto != 40){
					//ver si es que estos 3 métodos
					//pueden ser reutilizables tambien para todos los procc
					//procesos incluyendo cribado
					long idMP = regAux.getIdProducto();
					long idCate = regAux.getIdCategoria();
					ps = con.prepareStatement(" SELECT mc.idMateriaCategoria " +
							" FROM materiaprima_categoria mc " +
							" WHERE mc.idCategoria=" + idCate + " AND mc.idMateriaPrima = " + idMP);
					rs = ps.executeQuery();
					while(rs.next()){
						gp.setIdProducto(rs.getLong("idMateriaCategoria"));
					}
					//gp.setIdProducto(regAux.getIdProducto());
					gp.setIdOperario(regAux.getIdOperario());
					gp.setNotasIncidencias(regAux.getNotasIncidencias());
					//aniade un registro del proceso
					gpd.addRegistroProceso(gp, "Desgranado");
					gpd.addTmpDetalleRegistroDesgranado(entry, con);
					gpd.addDetalleRegistroDesgranado(entry, con, "Desgranado");
				}else{
					long idMP = regAux.getIdProducto();
					long idCate = regAux.getIdCategoria();
					ps = con.prepareStatement(" SELECT mc.idMateriaCategoria " +
							" FROM materiaprima_categoria mc " +
							" WHERE mc.idCategoria=" + idCate + " AND mc.idMateriaPrima = " + idMP);
					rs = ps.executeQuery();
					while(rs.next()){
						gp.setIdProducto(rs.getLong("idMateriaCategoria"));
					}
					//gp.setIdProducto(regAux.getIdProducto());
					gp.setIdOperario(regAux.getIdOperario());
					gp.setNotasIncidencias(regAux.getNotasIncidencias());
					//aniade un registro del proceso
					gpd.addRegistroProceso(gp, "Desgranado");
					gpd.addTmpDetalleRegistroDesgranado(entry, con);
					gpd.addDetalleRegistroDesgranado(entry, con, "Desgranado");
				}
			}
			else if(tipoProceso.equalsIgnoreCase("C")){
				//En caso de que sea el SRE para extra crea el RegistroGP
				if(categoria==1){
					GestionProduccion gp = new GestionProduccion();
					List<RegistroEntrada> regEntradas= new ArrayList<RegistroEntrada>();
					regEntradas.add(regAux);
					gp.setRegEntradas(regEntradas);
					long idMP = regAux.getIdProducto();
					long idCate = regAux.getIdCategoria();
					ps = con.prepareStatement(" SELECT mc.idMateriaCategoria " +
							" FROM materiaprima_categoria mc " +
							" WHERE mc.idCategoria=" + idCate + " AND mc.idMateriaPrima = " + idMP);
					rs = ps.executeQuery();
					while(rs.next()){
						gp.setIdProducto(rs.getLong("idMateriaCategoria"));
					}
					//gp.setIdProducto(regAux.getIdProducto());
					gp.setIdOperario(regAux.getIdOperario());
					gp.setNotasIncidencias(regAux.getNotasIncidencias());
					//aniade un registro del proceso
					gpd.addRegistroProceso(gp, "Cribado");
					gpd.addTmpDetalleRegistroDesgranado(entry, con);
					gpd.addDetalleRegistroDesgranado(entry, con, "Cribado");
				}
				else{
					gpd.addTmpDetalleRegistroDesgranado(entry, con);
					gpd.addDetalleRegistroDesgranado(entry, con, "Cribado");
				}
			}
			else{}
			if(res == 1){
				// System.out.println("REGISTRO INSERTADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	/**
	 * Proceso: Seleccion
	 * Genera varios Subregistros de entrada de un Registro Padre derivado de un proceso automáticamente
	 * Selección Automática y Manual
	 */
	//@Override
	public String addRegistrosSubEntradaProceso(RegistroEntrada regEntradaFind, Map mapaCategorias,
			String proceso,Double cantidadProceso) throws Exception {
		// System.out.println("DAO addRegistrosSubEntradaProceso");
		Statement stmt;
		String orden = "";
		Date fecha = null;
		String strfechacad = null;
		String strfechalle = null;
		String codigoEntrada = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		int res = 0;
		double destrio = regEntradaFind.getSaldo();
		RegistroEntrada entry = new RegistroEntrada();
		try {
			//Genera una nueva orden de GP
			//agrega un registro de GP	
			GestionProduccionDAO gpd = new GestionProduccionDAO();
			GestionProduccion gp = new GestionProduccion();
			gp.setCantidadProducto(cantidadProceso);
			//devuelve los datos complementarios del RE Padre
			RegistroEntrada regAux = getRegistroEntrada(regEntradaFind.getCodigoEntrada());	
			//agrega el RE que inicio el proceso de SM
			List<RegistroEntrada> regEntradas= new ArrayList<RegistroEntrada>();
			regEntradas.add(regAux);
			gp.setRegEntradas(regEntradas);
			long idMP = regAux.getIdProducto();
			long idCate = regAux.getIdCategoria();
			con = bddConexion();
			ps = con.prepareStatement(" SELECT mc.idMateriaCategoria " +
					" FROM materiaprima_categoria mc " +
					" WHERE mc.idCategoria=" + idCate + " AND mc.idMateriaPrima = " + idMP);
			rs = ps.executeQuery();
			if(rs.next()){
				gp.setIdProducto(rs.getLong("idMateriaCategoria"));
			}
			//gp.setIdProducto(regAux.getIdProducto());
			gp.setIdOperario(regAux.getIdOperario());
			gp.setNotasIncidencias(regAux.getNotasIncidencias());
			//aniade un registro del proceso
			gpd.addRegistroProceso(gp, proceso);
			//obtener fecha sistema
			ps = con.prepareStatement("SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			if(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			if(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno = stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0"+mes1;
			}else {
				stringmes = String.valueOf(mes1);
			}			
			stringFecha = stringanno+stringmes+fecha.getDate();	
			strfechacad = formatoDate(regAux.getFechaCaducidad());
			strfechalle = formatoDate(regAux.getFechaLlegada());
			//iteraciones para generar los SRE
			for(Iterator it = mapaCategorias.keySet().iterator(); it.hasNext();) { 
				String categoriaSalida = (String)it.next();
				// System.out.println("idCategoria :" +categoriaSalida);
				//1) Obtener el nuevo Id
				ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada");
				rs = ps.executeQuery();
				long idEntradaMax = 0;
				if(rs.next()){
					RegistroEntrada entradaMaximo = new RegistroEntrada();
					entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
					idEntradaMax= entradaMaximo.getIdEntrada()+1;
				}
				// System.out.println("IdSubentrada Max : "+ idEntradaMax);
				//2. Generar código SRE
				codigoEntrada = "S" + stringFecha + "-" + idEntradaMax;
				entry.setCodigoEntrada(codigoEntrada);
				//Recupera la cantidad usable
				Double cantidad = Double.parseDouble((String)mapaCategorias.get(categoriaSalida));
				entry.setCantidad(cantidad);
				//3. Inserta el nuevo Subregistro de Entrada con campos heredados y nuevos
				String insertString = 
					" INSERT INTO registroentrada(idEntrada,idEntradaPadre,codigoEntrada,fecha,fechaCaducidad,fechaLlegada," +
					"	idProducto,idCategoriaEntrada,idCategoria,cantidad,saldo,usuarioResponsable,idCosecha,lote,idTipoRegistro)" +
					" VALUES(" + idEntradaMax + "," + regAux.getIdEntrada()+ ",'" + entry.getCodigoEntrada()+ "','"+ fecha +"','"
					+ strfechacad + "','" + strfechalle + "',"
					+ regAux.getIdProducto() +","
					+ regAux.getIdCategoria() +"," + categoriaSalida +","
					+ entry.getCantidad()+","+ entry.getCantidad()+",'"
					+ gp.getIdOperario() +"',"+ regAux.getIdCosecha() +",'" + regAux.getLote() +"',"	
					+ "'S'"	+ ")";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				//Actualizar el stock de la materia prima categorizada
				String qry =
					" SELECT mc.idMateriaCategoria " +
					" FROM materiaprima_categoria mc " +
					" WHERE mc.idMateriaPrima = " + regAux.getIdProducto() + " AND mc.idCategoria = " + categoriaSalida + "; ";
				ps = con.prepareStatement(qry);
				rs = ps.executeQuery();
				if(rs.next()){
					qry = 
						" UPDATE materiaprima_categoria " +
						" SET stock = stock + " + entry.getCantidad() + " " +
						" WHERE idMateriaPrima = " + regAux.getIdProducto() + " " +
							" AND idCategoria = " + categoriaSalida + "; ";
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(qry);
				}else{
					qry = 
						" INSERT INTO materiaprima_categoria (idMateriaPrima, idCategoria, stock) VALUES ('" + regAux.getIdProducto() + "', '" + categoriaSalida + "', '" + entry.getCantidad() + "'); ";
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(qry);
				}
				//Actualizar el stock de la materia prima
				qry = 
					" UPDATE materiaprima " +
					" SET stock = stock + " + entry.getCantidad() + " " +
					" WHERE idProducto = " + regAux.getIdProducto();
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(qry);
				//4. Inserta cada SRE al GP orden, codigo de entrada,cantidadUsable
				entry.setSaldo(destrio);
				orden = gpd.addDetalleRegistroProceso(entry, con, proceso);
				// System.out.println("Insertó el detalle de los SRE en GPdetalle");
				//5. Ubica en Entrada cada SRE
				long idHueco = 221;//Identificador de la entrada al almacen
				if (regAux.getIdTipoRegistro().compareTo("P") == 0){
					//Si el registro de entrada es un producto para vender, lo almacenamos directamente en salida (id del hueco = 223)
					idHueco = 223;
				}
				insertString =
					" INSERT INTO ubicacion(idHueco, registro, cantidad, orden) " +
					" VALUES (" + idHueco + ", '" + entry.getCodigoEntrada() + "'" +
						", " + entry.getCantidad() + "," + entry.getCodigoOrden() + ");";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
			}
			//actualiza el saldo del RE padre
			updateRegistroPadreSaldo(regAux.getIdEntrada(),con, cantidadProceso);
			if(res == 1){
				// System.out.println("PROCESO FINALIZADO!");
			}
			return orden;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	public void updateRegistroPadreSaldo(Long idEntradaPadre,
		Connection con, Double cantidad) throws SQLException {
		// System.out.println("Actualiza saldo RE Padre");
		Statement stmt1;
		int res1;
		//SQL de insercion
		String insertSql = 
			"UPDATE registroentrada SET saldo = saldo - " +
			cantidad + " WHERE idEntrada="+idEntradaPadre;
		// System.out.println(insertSql);
		stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		res1 = stmt1.executeUpdate(insertSql);	
		if(res1 == 1){
			// System.out.println("Saldo RE Padre actualizado");
		}else{
			// System.out.println("Saldo RE Padre NO actualizado");
		}
		String Qry = "SELECT idCategoria, idProducto FROM registroEntrada WHERE idEntrada=" + idEntradaPadre;			
		// System.out.println(Qry);
		ps = con.prepareStatement(Qry);
		rs = ps.executeQuery();
		long idMateriaPrima = 0;
		long idCategoria = 0;
		while (rs.next()) {
			idMateriaPrima = rs.getLong("idProducto");
			idCategoria = rs.getLong("idCategoria");
		}
		insertSql = 
			"UPDATE materiaprima_categoria SET stock=stock-" + cantidad +
				" WHERE idMateriaPrima = " + idMateriaPrima + " AND idCategoria=" + idCategoria;
		// System.out.println(insertSql);
		stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		res1 = stmt1.executeUpdate(insertSql);
		if(res1==1){
			// System.out.println("Saldo materia_categoria actualizado");
		}else{
			// System.out.println("Saldo materia_categoria NO actualizado");
		}
	}

	//@Override
	public Double getSaldoRegistro(String codigoEntrada) throws Exception {
		Double saldo = null;
		try {
			con=bddConexion();
			String Qry="SELECT saldo FROM registroentrada WHERE codigoEntrada='"+codigoEntrada+"'";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				saldo=rs.getDouble("saldo");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return saldo;
	}

	//@Override
	public String getCodigoOrden() throws Exception {
		// System.out.println("DAO getCodigoOrden");
		Date fecha = null;
		String codigoEntrada = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;		
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idOrden) as idMaxEntrada FROM ordenentrada");
			rs = ps.executeQuery();
			long idOrdenMax =0 ;
			if(rs.next()){
				RegistroOrden entradaMaximo = new RegistroOrden();
				entradaMaximo.setIdOrden(rs.getLong("idMaxEntrada"));
				idOrdenMax= entradaMaximo.getIdOrden()+1;
			}
			else
			idOrdenMax = 1;
			// System.out.println("el id de entrada es... "+ idOrdenMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno=stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0"+mes1;	
			}	
			else {
				stringmes = String.valueOf(mes1);
			}			
			stringFecha = stringanno+stringmes+fecha.getDate();
			codigoEntrada = "O"+stringFecha+"-"+idOrdenMax;
			// System.out.println("cod-orden es "+codigoEntrada);
			return codigoEntrada;
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
	public Boolean addMQ(Maquina maquina) throws Exception{
		// System.out.println("DAO addMQ");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idMaquina) as idMaxMaquina FROM maquina");
			rs = ps.executeQuery();
			long idMaqMax =0 ;

			if(rs.next()){
				RegistroOrden entradaMaximo = new RegistroOrden();
				entradaMaximo.setIdOrden(rs.getLong("idMaxMaquina"));
				idMaqMax= entradaMaximo.getIdOrden()+1;
			}
			else
				idMaqMax=1;
			// System.out.println("el id de entrada es... "+ idMaqMax);
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			//SQL de insercion
			String insertString= 
				"INSERT INTO maquina(idMaquina,idTipo, nombre,fecha,descripcion) VALUES(" +
				idMaqMax + "," + maquina.getIdTipo() + ",'" + maquina.getNombre()+ "',CURDATE(),'"
				+ maquina.getDescripcion() +"'"
				+ ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("MAQUINARIA INSERTADA");
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
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	//@Override
	public Boolean addMT(Mantenimiento mant) throws Exception{
		// System.out.println("DAO addMQ");
		try {
			con = bddConexion();
			Statement stmt;
			int res = 0;
			boolean resultado = false;
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idMantenimiento) AS idMaxMantenimiento FROM maquina_mantenimiento");
			rs = ps.executeQuery();
			long idMantMax =0 ;
			if(rs.next()){
				idMantMax = rs.getLong("idMaxMantenimiento") + 1;
			}else
				idMantMax = 1;
			//SQL de insercion
			String insertString= 
				"INSERT INTO maquina_mantenimiento (idMantenimiento, idTipo, nombre, descripcion)" +
				" VALUES( " + idMantMax + "," + mant.getIdTipo() +  ",'" + mant.getNombre() + "','" + mant.getDescripcion() + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("MANTENIMIENTO INSERTADO");
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
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	//@Override
	public boolean addMTProgramado(Mantenimiento mant) throws Exception{
		// System.out.println("DAO addMQ");
		try {
			con = bddConexion();
			Statement stmt;
			int res = 0;
			boolean resultado = false;
			//SQL de insercion
			String insertString = "";
			//1. Obtener id del nuevo mantenimiento programado
			ps = con.prepareStatement("SELECT MAX(idMantenimientoProgramacion) AS idMaxMantenimientoProgramacion FROM maquina_mantenimiento_programacion");
			rs = ps.executeQuery();
			long idMantenimientoProgramacion = 0;
			if(rs.next()){
				idMantenimientoProgramacion = rs.getLong("idMaxMantenimientoProgramacion") + 1;
			}else
				idMantenimientoProgramacion = 1;
			//Comprobamos si tiene calibrado
			if (mant.getIdCalibrado() == 1){
				//1. Obtener id del nuevo calibrado
				//obtener el nuevo Id
				ps = con.prepareStatement("SELECT MAX(idCalibrado) AS idCalibra FROM maquina_calibracion");
				rs = ps.executeQuery();
				long idCalibrado = 0;
				if(rs.next()){
					idCalibrado = rs.getLong("idCalibra") + 1;
				}else
					idCalibrado = 1;
				//2. Creamos la fila en la tabla maquina_calibracion
				insertString =
					"INSERT INTO maquina_calibracion (idCalibrado)" +
					" VALUES( " + idCalibrado + ")";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				insertString =
					"INSERT INTO maquina_mantenimiento_programacion (idMantenimientoProgramacion,idMaquina, idMantenimiento, observaciones, " +
					"fechaProgramada, responsable, estado, idCiclo, idCalibrado)" +
					" VALUES( " + idMantenimientoProgramacion + "," + mant.getIdMaquina() + "," + mant.getIdMantenimiento() +  ",'" + mant.getObservaciones() + "','" +
					mant.getFechaProgramada() + "','" + mant.getResponsable() + "','P'," + mant.getIdCiclo() + "," + idCalibrado + ")";
			} else
				insertString =
					"INSERT INTO maquina_mantenimiento_programacion (idMantenimientoProgramacion, idMaquina, idMantenimiento, " +
					"observaciones, fechaProgramada, responsable, estado, idCiclo, idCalibrado)" +
					" VALUES( " + idMantenimientoProgramacion + "," + mant.getIdMaquina() + "," + mant.getIdMantenimiento() +  ",'" + mant.getObservaciones() + "','" +
					mant.getFechaProgramada() + "','" + mant.getResponsable() + "','P'," + mant.getIdCiclo() + ",0)";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("MANTENIMIENTO INSERTADO");
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
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	/**
	 * @author andres (26/05/2011)
	 * @since 1.0
	 */
	//@Override
	public Vector<TipoMantenimiento> getTipoMant() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<TipoMantenimiento> resultado = new Vector<TipoMantenimiento>();
		try {
			con = bddConexion();
			//String Qry="SELECT idTipoMant,nombre FROM tipomantenimiento";
			String Qry="SELECT idTipoMant,nombre FROM tipomantenimiento";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				TipoMantenimiento tm = new TipoMantenimiento();
				tm.setIdTipoMant(rs.getLong("idTipoMant"));
				tm.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(tm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	//@Override
	public Vector<Maquina> getMaquinas(long idTipo, long idMaquina, String fecha) throws Exception {
		Vector<Maquina> resultado = new Vector<Maquina>();
		try {
			con = bddConexion();
			String Qry = "SELECT m.idMaquina, m.nombre,m.idTipo, m.descripcion, m.fecha, mt.descripcion AS descripcionTipo " +
					" FROM maquina m, maquina_tipo mt " +
					" WHERE m.idTipo = mt.idTipo ";
			if (idTipo > 0){
				Qry += " AND m.idTipo=" + idTipo + " ";
			}
			if (idMaquina > 0){
				Qry += " AND m.idMaquina=" + idMaquina + " ";
			}
			if (fecha.compareTo("") != 0){
				Qry += " AND m.fecha='" + fecha + "'";
			}
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Maquina mq = new Maquina();
				mq.setIdMaquina(rs.getLong("idMaquina"));
				mq.setNombre(rs.getString("nombre"));
				mq.setDescripcion(rs.getString("descripcion"));
				mq.setFecha(rs.getDate("fecha"));
				mq.setDescripcionTipo(rs.getString("descripcionTipo"));
				mq.setIdTipo(rs.getLong("idTipo"));
				resultado.add(mq);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return resultado;
	}
	
	//@Override
	public void inseRegMT(Mantenimiento mant) throws Exception {
		try {
			con = bddConexion();
			// System.out.println("Actualizacion de un registro de mantenimiento");
			long idMantenimientoProgramacion = mant.getIdMantenimientoProgramacion();
			Statement stmt;
			int res = 0;
			//ACTUALIZAR EL REGISTRO DE MANTENIMIENTO, Y EL DE CALIBRACION SI FUESE EL CASO
			String insertString =
				"UPDATE maquina_mantenimiento_programacion SET fechaRealizada='" + mant.getFechaRealizada() + "', estado='F'," +
						"observaciones = '" + mant.getObservaciones() + "' " +
				" WHERE idMantenimientoProgramacion=" + idMantenimientoProgramacion;
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			//mant = getMantenimientos(mant.getIdMantenimientoProgramacion(), 0, 0).get(0);
			if(res == 1){
				// System.out.println("REGISTRO DE MANTENIMIENTO ACTUALIZADO");
			}
			if (mant.getIdCalibrado() > 0){//Actualizar el calibrado
				con = bddConexion();
				insertString =
					"UPDATE maquina_calibracion SET patron='" + mant.getPatron() + "', medidaPatron='" + mant.getMedidaPatron() + "'," +
							" medidaEquipo=' " + mant.getMedidaEquipo() + "', desviacion='" + mant.getDesviacion() + "' " +
							" WHERE idCalibrado=" + mant.getIdCalibrado();
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if(res == 1){
					// System.out.println("REGISTRO DE CALIBRADO ACTUALIZADO");
				}
			}
			//CREAR UN NUEVO REGISTRO DE MANTENIMIENTO, ACTUALIZANDO LA FECHA CON EL CICLO
			//Obtener id del nuevo mantenimiento programado
			ps = con.prepareStatement("SELECT MAX(idMantenimientoProgramacion) AS " +
					" idMaxMantenimientoProgramacion FROM maquina_mantenimiento_programacion");
			rs = ps.executeQuery();
			long idMaxMantenimientoProgramacion = 0;
			if(rs.next()){
				idMaxMantenimientoProgramacion = rs.getLong("idMaxMantenimientoProgramacion") + 1;
			}else
				idMaxMantenimientoProgramacion = 1;
			String fechaRealizada = mant.getFechaRealizada();
			String[] frag = fechaRealizada.split("-");
			Calendar calendario = Calendar.getInstance();
			calendario.set(Integer.parseInt(frag[0]), Integer.parseInt(frag[1]), Integer.parseInt(frag[2]));
			switch(Integer.parseInt(Long.toString(mant.getIdCiclo()))){
			case 1://Mensual
				calendario.add(Calendar.MONTH, 1);
				break;
			case 2://Trimestral
				calendario.add(Calendar.MONTH, 3);
				break;
			case 3://Anual
				calendario.add(Calendar.YEAR, 1);
				break;
			}
			String fechaProgramada = calendario.get(calendario.YEAR) + "-" + calendario.get(calendario.MONTH) +
				"-" + calendario.get(calendario.DATE);
			long idCalibrado = mant.getIdCalibrado();
			if (idCalibrado > 0){//Calculamos el nuevo idCalibrado
				//Obtener id del nuevo calibrado
				ps = con.prepareStatement("SELECT MAX(idCalibrado) AS idMaxCalibrado FROM maquina_calibracion");
				rs = ps.executeQuery();
				if(rs.next()){
					idCalibrado = rs.getLong("idMaxCalibrado") + 1;
				}else
					idCalibrado = 1;
				//Creamos la fila en la tabla maquina_calibracion
				insertString =
					"INSERT INTO maquina_calibracion (idCalibrado)" +
					" VALUES( " + idCalibrado + ")";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
			} else
				idCalibrado = 0;
			insertString =
				"INSERT INTO maquina_mantenimiento_programacion(idMantenimientoProgramacion, idMaquina, " +
				" idMantenimiento, observaciones, fechaProgramada, responsable, estado, idCiclo, idCalibrado, clasificacion) VALUES(" +
				"'" + idMaxMantenimientoProgramacion + "',"+ mant.getIdMaquina() + ",'" + mant.getIdMantenimiento() + "','" +
				mant.getObservaciones() + "','" + fechaProgramada + "','" + mant.getResponsable() + "','P'," + mant.getIdCiclo() +
				"," + idCalibrado + ",'P')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("NUEVO REGISTRO DE MANTENIMIENTO ACTUALIZADO");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}	
	
	//@Override
	public Vector<Mantenimiento> getMantenimientos(long idMantenimientoProgramacion,
			long idTipo, long idMaquina) throws Exception {
		Vector<Mantenimiento> resultado = new Vector<Mantenimiento>();
		try {
			con = bddConexion();
			String Qry = " ";
			if (idMantenimientoProgramacion != 0){
				Qry = "SELECT m.idMaquina,mmp.idMantenimientoProgramacion, mm.idMantenimiento,mm.idTipo,mm.nombre,mm.descripcion,mt.descripcion AS tipoMaquina, " +
				" mmp.observaciones, mmp.fechaProgramada, mmp.fechaRealizada, mmp.idCiclo, mmp.idCalibrado, mmp.estado,mmp.responsable, mmc.descripcion as descripcionCiclo, " +
				" IF (mmp.idCalibrado = 0, 'No', 'Si') as calibracion " +
				" FROM maquina_mantenimiento mm, maquina_tipo mt,maquina m,maquina_mantenimiento_programacion mmp, maquina_mantenimiento_ciclo mmc " +
				" WHERE mm.idTipo=mt.idTipo " +
				" AND m.idTipo=mm.idTipo " +
				" AND mmp.idMaquina=m.idMaquina " +
				" AND mmp.idMantenimiento=mm.idMantenimiento " +
				" AND mmc.idCiclo = mmp.idCiclo " +
				"AND mmp.idMantenimientoProgramacion = " + idMantenimientoProgramacion;
			}else
				if (idMaquina == 0){
					Qry = "SELECT mm.idMantenimiento,mm.idTipo,mm.nombre,mm.descripcion,mt.descripcion AS tipoMaquina " +
						" FROM maquina_mantenimiento mm, maquina_tipo mt " +
						" WHERE mm.idTipo=mt.idTipo";
				}else{
					Qry = "SELECT m.idMaquina,mmp.idMantenimientoProgramacion, mm.idMantenimiento,mm.idTipo,mm.nombre,mm.descripcion,mt.descripcion AS tipoMaquina, " +
							" mmp.observaciones,mmp.idCiclo, mmp.idCalibrado, mmp.fechaProgramada, mmp.fechaRealizada, mmp.estado,mmp.responsable, mmc.descripcion as descripcionCiclo, " +
							" IF (mmp.idCalibrado = 0, 'No', 'Si') as calibracion " +
							" FROM maquina_mantenimiento mm, maquina_tipo mt,maquina m,maquina_mantenimiento_programacion mmp, maquina_mantenimiento_ciclo mmc " +
							" WHERE mm.idTipo=mt.idTipo " +
							" AND m.idTipo=mm.idTipo " +
							" AND mmp.idMaquina=m.idMaquina " +
							" AND mmp.idMantenimiento=mm.idMantenimiento " +
							" AND mmc.idCiclo = mmp.idCiclo " +
							"AND m.idMaquina = " + idMaquina;
				}
			if (idTipo != 0){
				Qry += " AND mm.idTipo = " + idTipo;
			}
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Mantenimiento mt = new Mantenimiento();
				mt.setIdMantenimiento(rs.getLong("idMantenimiento"));
				mt.setIdTipo(rs.getLong("idTipo"));
				mt.setNombre(rs.getString("nombre"));
				mt.setDescripcion(rs.getString("descripcion"));
				mt.setTipoMaquina(rs.getString("tipoMaquina"));
				if (idMantenimientoProgramacion != 0 || idMaquina != 0){
					mt.setIdMantenimientoProgramacion(rs.getLong("idMantenimientoProgramacion"));
					mt.setObservaciones(rs.getString("observaciones"));
					mt.setIdMaquina(rs.getLong("idMaquina"));
					mt.setFechaProgramada(rs.getString("fechaProgramada"));
					mt.setFechaRealizada(rs.getString("fechaRealizada"));
					mt.setResponsable(rs.getString("responsable"));
					mt.setEstado(rs.getString("estado").charAt(0));
					mt.setCalibrado(rs.getString("calibracion"));
					mt.setCiclo(rs.getString("descripcionCiclo"));
					mt.setIdCalibrado(rs.getLong("idCalibrado"));
					mt.setIdCiclo(rs.getLong("idCiclo"));
				}
				resultado.add(mt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return resultado;
	}
	
	//@Override
	public Vector<Ciclo> getCiclos() throws Exception {
		Vector<Ciclo> resultado = new Vector<Ciclo>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM  maquina_mantenimiento_ciclo";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ciclo c = new Ciclo();
				c.setIdCiclo(rs.getLong("idCiclo"));
				c.setDescripcion(rs.getString("descripcion"));
				resultado.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return resultado;
	}
	
	//@Override
	public Vector<TipoMaquina> getTiposMaquinas() throws Exception {
		Vector<TipoMaquina> resultado = new Vector<TipoMaquina>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM  maquina_tipo";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				TipoMaquina tipo = new TipoMaquina();
				tipo.setIdTipo(rs.getLong("idTipo"));
				tipo.setDescripcion(rs.getString("descripcion"));
				resultado.add(tipo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return resultado;
	}

	//@Override
	public Vector<Mantenimiento> getRegistrosMT(long idTipoMant,long idMaquina,String fechaConsulta)throws Exception {
		Vector<Mantenimiento> resultado = new Vector<Mantenimiento>();
		try {
			con = bddConexion();
			String Qry2 = "";
			String Qry =
				" SELECT mm.idMantenimiento, mm.idTipo, mm.nombre, mm.descripcion, mt.descripcion as descripcionTipo " +
				" FROM maquina_mantenimiento mm, " +
					" maquina_tipo mt " +
				" WHERE mm.idTipo = mt.idTipo ";
			if (idTipoMant != 0) {
				Qry2 = Qry2 + " AND mm.idTipo=" + idTipoMant;
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Mantenimiento mt = new Mantenimiento();
				mt.setIdMantenimiento(rs.getLong("idMantenimiento"));
				mt.setIdTipo(rs.getLong("idTipo"));
				mt.setNombre(rs.getString("nombre"));
				mt.setDescripcion(rs.getString("descripcion"));
				mt.setTipoMaquina(rs.getString("descripcionTipo"));
				resultado.add(mt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return resultado;
	}
	
	//@Override
	public Vector<Mantenimiento> getMantenimientosProgramados(long idTipoMant,long idMaquina,String fechaConsulta)throws Exception{
		Vector<Mantenimiento> resultado = new Vector<Mantenimiento>();
		try {
			con = bddConexion();
			String Qry2 = "";
			String Qry = "SELECT mm.idMantenimiento, mm.idTipo, mm.nombre, mm.descripcion as descripcionMantenimiento, " +
				" mt.descripcion as descripcionTipo, clasificacion, m.nombre as nombreMaquina, mmp.observaciones, mmp.fechaProgramada, mmp.fechaRealizada " +
				" FROM maquina_mantenimiento mm, maquina_tipo mt, maquina_mantenimiento_programacion mmp, maquina m " +
				" WHERE mm.idTipo = mt.idTipo AND mmp.idMantenimiento = mm.idMantenimiento AND mmp.idMaquina = m.idMaquina ";
			
			if (idTipoMant != 0) {
				Qry2 = Qry2 + " AND mm.idTipo=" + idTipoMant;
			}
			if (idMaquina != 0){
				Qry2 += " AND mmp.idMaquina = " + idMaquina;
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Mantenimiento mt = new Mantenimiento();
				mt.setNombreMaquina(rs.getString("nombreMaquina"));
				mt.setObservaciones(rs.getString("observaciones"));
				mt.setDescripcionTipo(rs.getString("descripcionTipo"));
				mt.setIdMantenimiento(rs.getLong("idMantenimiento"));
				mt.setIdTipo(rs.getLong("idTipo"));
				mt.setNombre(rs.getString("nombre"));
				mt.setDescripcionMantenimiento(rs.getString("descripcionMantenimiento"));
				if (rs.getString("clasificacion").compareToIgnoreCase("P") == 0)
					mt.setClasificacion("Preventivo");
				else
					if (rs.getString("clasificacion").compareToIgnoreCase("C") == 0)
						mt.setClasificacion("Correctivo");				
				resultado.add(mt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return resultado;
	}

	//@Override
	public Boolean getOrdenEntradaTemporal(RegistroEntrada entrada) throws Exception {
		// System.out.println("DAO getOrdenTemporal");
		Statement stmt;
		Boolean resultado = false;
		Date fecha = null;
		int res = 0;		
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idOrden) AS idMaxEntrada FROM ordenentrada");
			rs = ps.executeQuery();
			long idOrdenMax = 0;
			if(rs.next()){	
				RegistroOrden entradaMaximo = new RegistroOrden();
				entradaMaximo.setIdOrden(rs.getLong("idMaxEntrada"));
				idOrdenMax= entradaMaximo.getIdOrden()+1;
			}
			else
				idOrdenMax=1;
			//comprobamos que es el máximo de la tabla temporal (por si hay algún registro no concluido)
			ps = con.prepareStatement("SELECT MAX(idOrden) AS idMaxEntrada FROM ordenentrada_tmp");
			rs = ps.executeQuery();
			if(rs.next()){
				Long maxim =(rs.getLong("idMaxEntrada"));
				// System.out.println("MAX: " + maxim);
				if((maxim+1)>idOrdenMax) {
					// System.out.println("Temporal: " + maxim + " Normal:" + idOrdenMax);
					idOrdenMax = maxim+1;
				}
			}
			// System.out.println("el id de entrada es... "+ idOrdenMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			//SQL de insercion
			String insertString= 
				"INSERT INTO ordenentrada_tmp(idOrden,codigoorden,fecha,idProveedor, " +
					" idTransportista,origen,albaran,descVehiculo,notasvehiculo, " +
					" usuarioResponsable,habilitado) " +
				" VALUES(" +
					idOrdenMax + ",'" + entrada.getCodigoOrden() + "','" + fecha + "',"
					+ entrada.getIdProveedor() + "," + entrada.getIdTransportista() + ",'"
					+ entrada.getOrigen() + "','" + entrada.getAlbaran() + "','" + entrada.getDescVehiculo() + "','"
					+ entrada.getNotasVehiculo() + "','"
					+ entrada.getIdOperario() + "','S'"
				+ ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("REGISTRO ORDEN INSERTADA");
				resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			purgaTemporales(con, entrada.getIdOperario());
			throw (e);			
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	//@Override
	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada,
			List listindic, List listestados) throws Exception {
		// System.out.println("DAO addRegistroEntradaTMP con listas");
		//SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Statement stmt;
		Boolean resultado = false;
		Date fecha = null;
		String strfechac = null;
		String strfechal = null;
		String codigoEntrada = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada");
			rs = ps.executeQuery();
			long idEntradaMax = 0;
			while(rs.next()){
				RegistroEntrada entradaMaximo = new RegistroEntrada();
				entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
				idEntradaMax= entradaMaximo.getIdEntrada()+1;
			}
			// System.out.println("ANTES: el id de entrada es... "+ idEntradaMax);
			ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada_tmp");
			rs = ps.executeQuery();
			if(rs.next()){
				Long maxim =(rs.getLong("idMaxEntrada"));
				// System.out.println("MAX: "+maxim);
				if((maxim+1)>idEntradaMax) {
					// System.out.println("Temporal: "+maxim+" Normal:"+idEntradaMax);
					idEntradaMax = maxim+1;
				}
			}
			// System.out.println("DESPUES:el id de entrada es... "+ idEntradaMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno=stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0" + mes1;
			}else {
				stringmes = String.valueOf(mes1);
			}
			stringFecha = stringanno+stringmes+fecha.getDate();
			codigoEntrada = "E"+stringFecha+"-"+idEntradaMax;
			registroEntrada.setCodigoEntrada(codigoEntrada);
			strfechac = formatoDate(registroEntrada.getFechaCaducidad());
			strfechal = formatoDate(registroEntrada.getFechaLlegada());
			//SQL de insercion para un RE original es el padre de simismo
			String listoDistribuir = "";
			if (registroEntrada.isListoDistribuir())
				listoDistribuir = "S";
			else
				listoDistribuir = "N";
			String insertString =
				"INSERT INTO registroentrada_tmp(idEntrada,idEntradaPadre,codigoEntrada,codigoOrden," +
						"fecha,fechaCaducidad,fechaLlegada,idProducto,idCategoria,saldo,cantidad,notasincidencias," +
						"idFormatoEntrega,numeroPallets,numeroBultos,usuarioResponsable,idCosecha,idCategoriaEntrada," +
						"idTipoRegistro,idEnvase,temperatura,humedad,costoUnitario,costoTotal,lote,listoDistribuir) " +
					" VALUES(" +
						idEntradaMax + "," + idEntradaMax + ",'" + registroEntrada.getCodigoEntrada() +
						"','" + registroEntrada.getCodigoOrden() + "','" + fecha + "','" +
						strfechac + "','" + strfechal + "'," +
						registroEntrada.getIdProducto() + "," + registroEntrada.getIdCategoria() +
						"," + registroEntrada.getCantidad() + "," +
						registroEntrada.getCantidad() + ",'" + registroEntrada.getNotasIncidencias() + "'," +
						registroEntrada.getIdFormatoEntrega() + "," + registroEntrada.getNumeroPallets() + "," +
						registroEntrada.getNumeroBultos() + ",'" + registroEntrada.getIdOperario() + "'," +
						registroEntrada.getIdCosecha() + "," +
						registroEntrada.getIdCategoriaEntrada() +",'" + registroEntrada.getIdTipoRegistro() + "'," +
						registroEntrada.getIdEnvase() + "," +
						registroEntrada.getTemperatura() + "," + registroEntrada.getHumedad() + "," +
						registroEntrada.getCostoUnitario() + "," + registroEntrada.getCostoTotal() + "," +
						"'" + registroEntrada.getLote() + "','" + listoDistribuir + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			//introducimos las posibles incidencias y estados
			if (listindic != null)
				addIncidenciasRegistroEntradaTmp(registroEntrada, con, idEntradaMax, listindic);
			if (listestados != null)
				addEstadosRegistroEntradaTmp(registroEntrada, con, idEntradaMax, listestados);
			if(res == 1){
				// System.out.println("REGISTRO INSERTADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			purgaTemporales(con, registroEntrada.getIdOperario());
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada) throws Exception {
		// System.out.println("DAO addRegistroEntradaTMP");
		Statement stmt;
		Boolean resultado = false;
		Date fecha = null;
		String strfechac = null;
		String strfechal = null;
		String codigoEntrada = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada");
			rs = ps.executeQuery();
			long idEntradaMax =0 ;
			while(rs.next()){
				RegistroEntrada entradaMaximo = new RegistroEntrada();
				entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
				idEntradaMax= entradaMaximo.getIdEntrada()+1;
			}
			// System.out.println("ANTES: el id de entrada es... "+ idEntradaMax);
			//Se calcula el máximo de registroentrada y la temporalasociada
			ps = con.prepareStatement("SELECT max(idEntrada) as idMaxEntrada FROM registroentrada_tmp");
			rs = ps.executeQuery();
			if(rs.next()){
				Long maxim =(rs.getLong("idMaxEntrada"));
				// System.out.println("MAX: "+maxim);
				if((maxim+1)>idEntradaMax) {
					// System.out.println("Temporal: "+maxim+" Normal:"+idEntradaMax);
					idEntradaMax = maxim+1;
				}
			}
			// System.out.println("DESPUES:el id de entrada es... "+ idEntradaMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno=stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0"+mes1;	
			}	
			else {
				stringmes = String.valueOf(mes1);
			}			
			stringFecha = stringanno+stringmes+fecha.getDate();
			codigoEntrada = "E"+stringFecha+"-"+idEntradaMax;
			registroEntrada.setCodigoEntrada(codigoEntrada);
			strfechac = formatoDate(registroEntrada.getFechaCaducidad());
			strfechal = formatoDate(registroEntrada.getFechaLlegada());
			//SQL de insercion para un RE original es el padre de simismo
			String insertString = 
				"insert into registroentrada_tmp(idEntrada,idEntradaPadre,codigoEntrada,codigoOrden,fecha,fechaCaducidad,fechaLlegada," +
				"idProducto,idCategoria,saldo,cantidad," +
				"notasincidencias,idFormatoEntrega,numeroPallets,numeroBultos,usuarioResponsable,idCosecha," +
				"idCategoriaEntrada,idTipoRegistro,idEnvase,temperatura,humedad,costoUnitario," +
				"costoTotal,idTipoUbicacion,lote) values(" +
				idEntradaMax + ","+ idEntradaMax +",'" + registroEntrada.getCodigoEntrada()+ "','" + registroEntrada.getCodigoOrden()+ "','"+ fecha +"','"
				+ strfechac +"','"+ strfechal +"',"
				+ registroEntrada.getIdProducto() +"," + registroEntrada.getIdCategoria() +","+ registroEntrada.getCantidad() +","
				+ registroEntrada.getCantidad() +",'" + registroEntrada.getNotasIncidencias() +"',"
				+ registroEntrada.getIdFormatoEntrega() +"," + registroEntrada.getNumeroPallets() +"," 
				+ registroEntrada.getNumeroBultos() +",'" + registroEntrada.getIdOperario() +"',"
				+ registroEntrada.getIdCosecha() +","
				+ registroEntrada.getIdCategoriaEntrada() +",'" + registroEntrada.getIdTipoRegistro() +"',"		
				+ registroEntrada.getIdEnvase()+","
				+ registroEntrada.getTemperatura() +"," + registroEntrada.getHumedad()+","
				+ registroEntrada.getCostoUnitario() +"," + registroEntrada.getCostoTotal()+","
				+ registroEntrada.getIdTipoUbicacion()+",'"+ registroEntrada.getLote()+"'"
				+ ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			
			if(res==1)
				{
				// System.out.println("REGISTRO INSERTADO");
				resultado=true;
				}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			purgaTemporales(con, registroEntrada.getIdOperario());
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {	}
		}
	}

	/**
	 * Adds the incidencias registro entrada tmp.
	 *
	 * @param entry the entry
	 * @param con the con
	 * @param idRegistroEntradaMax the id registro entrada max
	 * @param listaIncidencias the lista incidencias
	 * @throws SQLException the sQL exception
	 */
	private void addIncidenciasRegistroEntradaTmp(RegistroEntrada entry, Connection con,
			long idRegistroEntradaMax, List listaIncidencias) throws SQLException {
		Statement stmt1;
		if(listaIncidencias.isEmpty()){
			// System.out.println("No hay lista incidencias adios");
		}else{
			Iterator itr = listaIncidencias.iterator(); 
			while(itr.hasNext()) {
			    String idIncid = (String)itr.next(); 
			    // System.out.print(" Incidencia es :"+idIncid);
				//SQL de insercion
				String insertSql= 
					"INSERT INTO registroentrada_incidencia_tmp(idEntrada,idIncidencia,usuarioResponsable) " +
						" VALUES(" + idRegistroEntradaMax + "," + idIncid + ",'" + entry.getIdOperario() + "')";		
				// System.out.println(insertSql);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				try {
					stmt1.executeUpdate(insertSql);
				} catch (Exception e) {
					purgaTemporales(con, entry.getIdOperario());
				}
			}
		}
	}

	private void addEstadosRegistroEntradaTmp(RegistroEntrada entry, Connection con,
			long idRegistroEntradaMax, List listaEstados) throws SQLException {
		Statement stmt1;
		if(listaEstados.isEmpty()){
			// System.out.println("No hay lista estados");
		}else{
			Iterator itr = listaEstados.iterator(); 
			while(itr.hasNext()) {
			    String idEstado = (String)itr.next(); 
			    // System.out.print(" Calidad es :"+idEstado);
				//SQL de insercion
				String insertSql =
					"INSERT INTO registroentrada_estado_tmp(idEntrada,idEstado,usuarioResponsable)" +
						" VALUES(" +
							idRegistroEntradaMax + "," + idEstado + ",'" + entry.getIdOperario() + "')";
				// System.out.println(insertSql);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				try {
					stmt1.executeUpdate(insertSql);    
				} catch (Exception e) {
					e.printStackTrace();
					purgaTemporales(con, entry.getIdOperario());
				}
			} 
		}
	}

	private void purgaTemporales(Connection con, String idOperario) throws SQLException {
		try {
			con = bddConexion();
		    // System.out.print(" Purgando temporales");
			//Borrando contenido SQL
		    String deleteSql = "DELETE FROM ordenentrada_tmp";
		    if (idOperario != null && !idOperario.equals(""))
		    	deleteSql += " WHERE usuarioResponsable = '"  + idOperario + "'";
			// System.out.println(deleteSql);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteSql);
			deleteSql = "DELETE FROM registroentrada_tmp";
			if (idOperario != null && !idOperario.equals(""))
		    	deleteSql += " WHERE usuarioResponsable = '"  + idOperario + "'";
			// System.out.println(deleteSql);
			//stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteSql);
			deleteSql = "DELETE FROM registroentrada_estado_tmp";
			if (idOperario != null && !idOperario.equals(""))
		    	deleteSql += " WHERE usuarioResponsable = '"  + idOperario + "'";
			// System.out.println(deleteSql);
			//stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteSql);
			deleteSql = "DELETE FROM registroentrada_incidencia_tmp";
			if (idOperario != null && !idOperario.equals(""))
		    	deleteSql += " WHERE usuarioResponsable = '"  + idOperario + "'";
			// System.out.println(deleteSql);
			//stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteSql);
			//por si las moscas borramos los temporales de ubicacion
			deleteSql = "DELETE FROM registroubicacion_tmp";
			if (idOperario != null && !idOperario.equals(""))
		    	deleteSql += " WHERE responsable = '"  + idOperario + "'";
			// System.out.println(deleteSql);
			stmt.executeUpdate(deleteSql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private void purgaRETemporales(String idOperario) throws SQLException {
		try {
			con = bddConexion();
		    // System.out.print(" Purgando temporales");
			//Borrando contenido SQL
		    String deleteSql = "DELETE FROM registroentrada_tmp WHERE usuarioResponsable='" + idOperario + "'";
			// System.out.println(deleteSql);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteSql);
			deleteSql = "DELETE FROM registroentrada_estado_tmp WHERE usuarioResponsable='" + idOperario + "'";
			// System.out.println(deleteSql);
			stmt.executeUpdate(deleteSql);
			deleteSql = "DELETE FROM registroentrada_incidencia_tmp WHERE usuarioResponsable='" + idOperario + "'";
			// System.out.println(deleteSql);
			stmt.executeUpdate(deleteSql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Vector<RegistroEntrada> getRegistrosEntradaTmp(String codigoOrden) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT r.idCategoria,r.idCategoriaEntrada, r.codigoOrden," +
					"r.codigoEntrada,r.fecha,r.cantidad,r.idProducto," +
					"r.idEnvase,r.listoDistribuir " +
				" FROM registroentrada_tmp r " +
				" WHERE r.habilitado='S' AND r.codigoOrden='" + codigoOrden + "'";	
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			String subQry = "";
			PreparedStatement ps2;
			ResultSet rs2;
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				RegistroEntrada entrada = new RegistroEntrada();
				entrada.setCodigoOrden(rs.getString("codigoOrden"));				
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				//entrada.setDescProducto(rs.getString("descProducto"));
				//entrada.setDescCategoria(rs.getString("descCategoria"));
				entrada.setCantidad(rs.getDouble("cantidad"));
				entrada.setIdProducto(rs.getLong("idProducto"));
				entrada.setIdEnvase(rs.getLong("idEnvase"));
				entrada.setIdCategoria(rs.getLong("idCategoria"));
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				//Buscamos el nombre del producto/envase
				String productoVenta = rs.getString("listoDistribuir");
				char listoDistribuir = productoVenta.charAt(0);
				char tipo = 'X';
				if (rs.getLong("idProducto") == 0) {
					// System.out.println("Se trata de un envase");
					subQry = "SELECT nombre FROM envase WHERE idEnvase ='" + entrada.getIdEnvase() + "'";
					tipo = 'E';
				} else {
					// System.out.println("Se trata de materia prima o producto para vender");
					if (listoDistribuir == 'N'){
						// System.out.println("ES MATERIA PRIMA");
						subQry =
							"SELECT materiaprima.nombre, c.nombre as nombreCategoria, mc.idMateriaCategoria as idProducto " +
							" FROM materiaprima, registroentrada_tmp entrada, categoria c, materiaprima_categoria mc " +
							" WHERE materiaprima.idProducto=mc.idMateriaPrima AND entrada.idCategoriaEntrada = mc.idCategoria " +
								" AND materiaprima.idProducto ='" + entrada.getIdProducto() + "' AND  entrada.idProducto = mc.idMateriaPrima " +
								" AND c.idCategoria = mc.idCategoria AND entrada.codigoEntrada='" + entrada.getCodigoEntrada() + "'";
						tipo = 'M';
					}else{
						if (listoDistribuir == 'S'){
							// System.out.println("ES PRODUCTO FINAL");
							subQry =
								"SELECT nombre " +
								" FROM producto p " +
								" WHERE p.idProducto = " + entrada.getIdProducto();
							tipo = 'P';
						}
					}
				}
				// System.out.println(subQry);
				ps2 = con.prepareStatement(subQry);
				rs2 = ps2.executeQuery();
				boolean estaCategorizada = false;
				while (rs2.next()) {
					if (tipo == 'E' || tipo == 'P'){
						entrada.setDescProducto(rs2.getString("nombre"));
					}else{
						if (tipo == 'M'){
							entrada.setDescProducto(rs2.getString("nombre") + " - " + rs2.getString("nombreCategoria"));
							entrada.setIdProducto(rs2.getLong("idProducto"));
							estaCategorizada = true;
						}
					}
					// System.out.println("XXXXXX: " + entrada.getDescProducto());
				}
				if (tipo == 'M' && !estaCategorizada){
					//Categorizar la materia prima
					 String insertSql = "INSERT INTO materiaprima_categoria(idMateriaPrima,idCategoria,stock,habilitado) " +
					 		" VALUES (" + entrada.getIdProducto() + "," + entrada.getIdCategoriaEntrada() + ",0,'S')";
						// System.out.println(insertSql);
						Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertSql);
						//Buscamos el nombre del producto/envase
						subQry="SELECT materiaprima.nombre, c.nombre as nombreCategoria, mc.idMateriaCategoria as idProducto ";
						subQry+=" FROM materiaprima, registroentrada_tmp entrada, categoria c, materiaprima_categoria mc " +
							" WHERE materiaprima.idProducto=mc.idMateriaPrima AND entrada.idCategoriaEntrada = mc.idCategoria AND " +
							" materiaprima.idProducto ='"+entrada.getIdProducto()+"' AND  entrada.idProducto = mc.idMateriaPrima " +
							" AND c.idCategoria = mc.idCategoria AND entrada.codigoEntrada='" + entrada.getCodigoEntrada() + "'";
						// System.out.println(subQry);
						ps2 = con.prepareStatement(subQry);
						rs2 = ps2.executeQuery();
						while (rs2.next()) {
							entrada.setDescProducto(rs2.getString("nombre") + " - " + rs2.getString("nombreCategoria"));
							entrada.setIdProducto(rs2.getLong("idProducto"));
						}
				}
				//La añadimos al Vector de resultado
				resultado.add(entrada);
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
		//Retornamos el vector de resultado.
		return resultado;
	}	

	//@Override
	public RegistroEntrada getOrdenRegistroTmp() throws Exception {
		//Inicializamos el Vector de retorno.
		RegistroEntrada entrada = new RegistroEntrada();		
		try {
			con = bddConexion();
			// System.out.println("DAO getOrdenRegistroTmp");
			String Qry="SELECT r.codigoEntrada,r.fecha,r.fechaCaducidad,r.fechaLlegada,r.habilitado,r.idFormatoEntrega," +
			   " r.numeroPallets,r.numeroBultos,r.idProducto,r.idCategoria,r.cantidad,r.saldo,r.notasincidencias," +
			   " r.usuarioResponsable,r.lote,r.idCosecha, r.idCategoriaEntrada, r.idTipoRegistro, r.temperatura, " +
			   " r.humedad,r.idTipoUbicacion, r.costoUnitario, r.costoTotal " +					   
			   " FROM registroentrada_tmp r "+
			   " order by idEntrada desc limit 1";
			   //" WHERE r.codigoEntrada ='" +regEntrada.getCodigoEntrada()+"'";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setFechaCaducidad(rs.getString("fechaCaducidad"));
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setHabilitado(rs.getString("habilitado"));		
				entrada.setIdFormatoEntrega(rs.getLong("idFormatoEntrega"));	
				entrada.setNumeroPallets(rs.getLong("numeroPallets"));
				entrada.setNumeroBultos(rs.getLong("numeroBultos"));
				entrada.setIdProducto(rs.getLong("idProducto"));	
				entrada.setIdCategoria(rs.getLong("idCategoria"));	
				entrada.setCantidad(rs.getDouble("cantidad"));					
				entrada.setSaldo(rs.getDouble("saldo"));	
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));	
				entrada.setIdOperario(rs.getString("usuarioResponsable"));				
				entrada.setLote(rs.getString("lote"));
				entrada.setIdCosecha(rs.getLong("IdCosecha"));	
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				entrada.setIdTipoRegistro(rs.getString("IdTipoRegistro"));		
				entrada.setTemperatura(rs.getDouble("temperatura"));
				entrada.setHumedad(rs.getDouble("humedad"));
				entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));	
				entrada.setCostoUnitario(rs.getDouble("costoUnitario"));					
				entrada.setCostoTotal(rs.getDouble("costoTotal"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return entrada;
	}

	//@Override
	public Boolean deleteTemporales(String idOperario) throws Exception {
		try {
			con = bddConexion();
			// System.out.println("DAO deleteTemporales");
			this.purgaTemporales(con, idOperario);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return true;
	}
	
	public Boolean addRegistrosTemporales(String idOperario) throws Exception {
		// System.out.println("DAO addRegistrosTemporales");
		try {
			con = bddConexion();
			//Borrando contenido SQL		    
		    String insertSql = "INSERT INTO registroentrada SELECT * FROM  registroentrada_tmp WHERE usuarioResponsable='" + idOperario + "'";
			// System.out.println(insertSql);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			int res = stmt.executeUpdate(insertSql);
			String Qry = "SELECT * FROM  registroentrada_tmp WHERE usuarioResponsable='" + idOperario + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			Vector<RegistroEntrada> entradas = new Vector<RegistroEntrada>();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				RegistroEntrada entrada = new RegistroEntrada();
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setIdProducto(rs.getLong("idProducto"));
				entrada.setIdTipoRegistro(rs.getString("idTipoRegistro"));
				entrada.setCantidad(rs.getDouble("cantidad"));
				entrada.setIdEnvase(rs.getLong("idEnvase"));
				entradas.add(entrada);
				// System.out.println("VAMOS A INSERTAR EN ubicacion. Según llegan los registros de entrada, se almacenan en...");
				long numeroPaletsRegistro = rs.getLong("numeroPallets");
				//En un principio, dividimos la cantidad de forma equitativa
				double cantidad = rs.getDouble("cantidad") / numeroPaletsRegistro;
				long idHueco = 221;//Identificador de la entrada al almacen
				if (entrada.getIdTipoRegistro().compareTo("P") == 0){
					//Si el registro de entrada es un producto para vender, lo almacenamos directamente en salida (id del hueco = 223)
					idHueco = 223;
					String insertString =
						"INSERT INTO ubicacion(idHueco, registro, cantidad, numeroBultos, idPalet, orden, congelado) " +
							"VALUES (" + idHueco + ",'" + rs.getString("codigoEntrada") + "'," + rs.getLong("numeroBultos") + 
							"," + rs.getLong("numeroBultos") + ",0,'" + rs.getString("codigoOrden") + "','N')";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(insertString);
					if(res == 1){
						// System.out.println("REGISTRO DE ENTRADA UBICADO EN 'Salida'");
					}
					else
						// System.out.println("REGISTRO DE ENTRADA NO SE HA PODIDO UBICAR");
					//Y generamos ya sus bultos, no habrá gestion de bultos
					new GestionRegistroEntradaHelper().getBultosRegistroEntrada(entrada);
				}else{
					for (int i = 1; i <= numeroPaletsRegistro; i++){
						//SQL de insersion
						String insertString =
							"INSERT INTO ubicacion(idHueco, registro, cantidad, numeroBultos, idPalet, orden, congelado) " +
								"VALUES (" + idHueco + ",'" + rs.getString("codigoEntrada") + "'," + cantidad + 
								"," + rs.getLong("numeroBultos") + "," + i + ",'" + rs.getString("codigoOrden") + "','N')";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						res = stmt.executeUpdate(insertString);
						if(res == 1){
							// System.out.println("REGISTRO DE ENTRADA UBICADO EN 'Entrada'");
						}
						else{
							// System.out.println("REGISTRO DE ENTRADA NO SE HA PODIDO UBICAR");
						}
					}
				}
			}
			boolean actualiza = actualizarStockEntradas(entradas);
			if (!actualiza)
				return false;
			insertSql = "INSERT INTO registroentrada_estado SELECT * FROM  registroentrada_estado_tmp";
			// System.out.println(insertSql);
			res = stmt.executeUpdate(insertSql);
			insertSql = "INSERT INTO registroentrada_incidencia SELECT * FROM  registroentrada_incidencia_tmp";
			// System.out.println(insertSql);
			stmt.executeUpdate(insertSql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return true;
	}
	
	private boolean actualizarStockEntradas(Vector<RegistroEntrada> entradas) throws Exception {
		boolean retorno = true;
		for (int i = 0; i < entradas.size(); i++){
			if (entradas.elementAt(i).getIdTipoRegistro().compareTo("M") == 0){
				retorno = actualizaStockMateria(entradas.elementAt(i).getIdProducto(),
						entradas.elementAt(i).getIdCategoriaEntrada(),
						entradas.elementAt(i).getCantidad());
				if (!retorno){
					return false;
				}
			}else{
				if (entradas.elementAt(i).getIdTipoRegistro().compareTo("P") == 0){
					retorno = actualizaStockProductoFinal(entradas.elementAt(i).getIdProducto(),
							entradas.elementAt(i).getCantidad());
					if (!retorno){
						return false;
					}
				}else{
					if (entradas.elementAt(i).getIdTipoRegistro().compareTo("E") == 0){
						retorno = actualizaStockEnvase(entradas.elementAt(i).getIdEnvase(),
								entradas.elementAt(i).getCantidad());
						if (!retorno){
							return false;
						}
					}
				}
			}
		}
		return retorno;
	}

	//@Override
	public RegistroEntrada loadRegistroEntradaTmp(RegistroEntrada regEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		RegistroEntrada entrada = new RegistroEntrada();		
		try {
			con = bddConexion();
			// System.out.println("DAO loadRegistroEntradaTmp");
			String Qry="SELECT r.codigoEntrada,r.fecha,r.fechaCaducidad,r.fechaLlegada," +
					" r.habilitado,r.idFormatoEntrega,r.numeroPallets,r.numeroBultos," +
					" r.idProducto,r.idEnvase,r.idCategoria,r.cantidad,r.saldo," +
					" r.notasincidencias, r.usuarioResponsable,r.lote,r.idCosecha," +
					" r.idCategoriaEntrada," +
					" r.idTipoRegistro,r.temperatura,r.humedad,r.idTipoUbicacion," +
					" r.costoUnitario,r.costoTotal, o.origen,o.albaran,o.descVehiculo," +
					" o.notasVehiculo,o.idProveedor,o.idTransportista" +
					" FROM registroentrada_tmp r, ordenentrada_tmp o" +
					" WHERE r.codigoOrden = o.codigoOrden" +
					" AND r.codigoEntrada ='" +regEntrada.getCodigoEntrada()+"'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {				
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setFechaCaducidad(rs.getString("fechaCaducidad"));
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setHabilitado(rs.getString("habilitado"));		
				entrada.setIdFormatoEntrega(rs.getLong("idFormatoEntrega"));	
				entrada.setNumeroPallets(rs.getLong("numeroPallets"));
				entrada.setNumeroBultos(rs.getLong("numeroBultos"));
				entrada.setIdProducto(rs.getLong("idProducto"));	
				entrada.setIdEnvase(rs.getLong("idEnvase"));
				entrada.setIdCategoria(rs.getLong("idCategoria"));	
				entrada.setCantidad(rs.getDouble("cantidad"));					
				entrada.setSaldo(rs.getDouble("saldo"));	
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));	
				entrada.setIdOperario(rs.getString("usuarioResponsable"));				
				entrada.setLote(rs.getString("lote"));
				entrada.setIdCosecha(rs.getLong("IdCosecha"));	
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				entrada.setIdTipoRegistro(rs.getString("IdTipoRegistro"));		
				entrada.setTemperatura(rs.getDouble("temperatura"));
				entrada.setHumedad(rs.getDouble("humedad"));
				entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));	
				entrada.setCostoUnitario(rs.getDouble("costoUnitario"));					
				entrada.setCostoTotal(rs.getDouble("costoTotal"));
				entrada.setAlbaran(rs.getString("albaran"));
				entrada.setOrigen(rs.getString("origen"));
				entrada.setDescVehiculo(rs.getString("descVehiculo"));
				entrada.setNotasVehiculo(rs.getString("notasVehiculo"));
				entrada.setIdProveedor(rs.getLong("idProveedor"));
				entrada.setIdTransportista(rs.getLong("idTransportista"));
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
		return entrada;
	}

	//@Override
	public RegistroEntrada loadRegistroEntrada(RegistroEntrada regEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		RegistroEntrada entrada = new RegistroEntrada();		
		try {
			con = bddConexion();
			// System.out.println("DAO loadRegistroEntrada");
			String Qry="SELECT r.idEntrada,r.codigoEntrada,r.codigoOrden,r.fecha," +
					" r.habilitado,r.idFormatoEntrega,r.numeroPallets,r.numeroBultos," +
					" r.idProducto,r.idEnvase,r.idCategoria,r.cantidad,r.saldo," +
					" r.notasincidencias, r.usuarioResponsable,r.lote,r.idCosecha," +
					" r.idCategoriaEntrada,r.fechaCaducidad,r.fechaLlegada," +
					" r.idTipoRegistro,r.temperatura,r.humedad,r.idTipoUbicacion," +
					" r.costoUnitario,r.costoTotal, o.origen,o.albaran,o.descVehiculo," +
					" o.notasVehiculo,o.idProveedor,o.idTransportista,e.nombre as nombreProveedor " +
					" FROM registroentrada r, ordenentrada o, entidad e" +
					" WHERE r.codigoOrden = o.codigoOrden AND e.idUsuario = o.idProveedor" +
						" AND r.codigoEntrada ='" + regEntrada.getCodigoEntrada() + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				entrada.setCodigoOrden(rs.getString("codigoOrden"));
				entrada.setIdEntrada(rs.getLong("idEntrada"));
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setFechaCaducidad(rs.getString("fechaCaducidad"));
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setHabilitado(rs.getString("habilitado"));		
				entrada.setIdFormatoEntrega(rs.getLong("idFormatoEntrega"));	
				entrada.setNumeroPallets(rs.getLong("numeroPallets"));
				entrada.setNumeroBultos(rs.getLong("numeroBultos"));
				entrada.setIdProducto(rs.getLong("idProducto"));	
				entrada.setIdEnvase(rs.getLong("idEnvase"));
				entrada.setIdCategoria(rs.getLong("idCategoria"));
				entrada.setCantidad(rs.getDouble("cantidad"));					
				entrada.setSaldo(rs.getDouble("saldo"));	
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));
				entrada.setIdOperario(rs.getString("usuarioResponsable"));				
				entrada.setLote(rs.getString("lote"));
				entrada.setIdCosecha(rs.getLong("IdCosecha"));	
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				entrada.setIdTipoRegistro(rs.getString("idTipoRegistro"));		
				entrada.setTemperatura(rs.getDouble("temperatura"));
				entrada.setHumedad(rs.getDouble("humedad"));
				entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));	
				entrada.setCostoUnitario(rs.getDouble("costoUnitario"));					
				entrada.setCostoTotal(rs.getDouble("costoTotal"));
				entrada.setAlbaran(rs.getString("albaran"));
				entrada.setOrigen(rs.getString("origen"));
				entrada.setDescVehiculo(rs.getString("descVehiculo"));
				entrada.setNotasVehiculo(rs.getString("notasVehiculo"));
				entrada.setIdProveedor(rs.getLong("idProveedor"));
				entrada.setDescProveedor(rs.getString("nombreProveedor"));
				entrada.setIdTransportista(rs.getLong("idTransportista"));
				entrada.setDescResponsable(rs.getString("usuarioResponsable"));
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
		return entrada;
	}

	//@Override
	public Vector<EstadoFabas> loadEstadoFabasTmp(RegistroEntrada regEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<EstadoFabas> resultado = new Vector<EstadoFabas>();
		try {
			con = bddConexion();
			// System.out.println("DAO loadEstadoFabasTmp");
			String Qry="SELECT ef.idEstadoFabas, ef.descripcion from" +
			" registroEntrada_tmp re, registroEntrada_estado_tmp ree, estadoFabas ef" +
			" WHERE re.idEntrada=ree.idEntrada" +
			" AND ree.idEstado=ef.idEstadoFabas" +
			" AND re.codigoEntrada ='" +regEntrada.getCodigoEntrada()+"'";	
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				//Completamos los datos del formato de entrada en el registro
				EstadoFabas ef = new EstadoFabas();
				ef.setIdEstadoFabas(rs.getLong("idEstadoFabas"));
				ef.setDescripcion(rs.getString("descripcion"));
				//La añadimos al Vector de resultado
				resultado.add(ef);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	//@Override
	public Vector<Incidencia> loadIncidenciasTmp(RegistroEntrada regEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Incidencia> resultado = new Vector<Incidencia>();
		try {
			con = bddConexion();
			// System.out.println("DAO loadIncidenciasTmp");
			String Qry="SELECT i.idIncidencia, i.nombre FROM registroEntrada_tmp re," +
					" registroEntrada_incidencia_tmp rei, incidencia i " +
					" WHERE re.idEntrada=rei.idEntrada" +
					" AND rei.idIncidencia=i.idIncidencia" +
					" AND re.codigoEntrada='" +regEntrada.getCodigoEntrada()+"'";	
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del formato de entrada en el registro
				Incidencia i = new Incidencia();
				i.setIdIncidencia(rs.getLong("idIncidencia"));
				i.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(i);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<EstadoFabas> loadEstadoFabas(RegistroEntrada regEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<EstadoFabas> resultado = new Vector<EstadoFabas>();
		try {
			con = bddConexion();
			// System.out.println("DAO loadEstadoFabas");
			String Qry="SELECT ef.idEstadoFabas, ef.descripcion from" +
			" registroEntrada re, registroEntrada_estado ree, estadoFabas ef" +
			" WHERE re.idEntrada=ree.idEntrada" +
			" AND ree.idEstado=ef.idEstadoFabas" +
			" AND re.codigoEntrada ='" +regEntrada.getCodigoEntrada()+"'";	
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del formato de entrada en el registro
				EstadoFabas ef = new EstadoFabas();
				ef.setIdEstadoFabas(rs.getLong("idEstadoFabas"));
				ef.setDescripcion(rs.getString("descripcion"));
				//La añadimos al Vector de resultado
				resultado.add(ef);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}
	
	//@Override
	public Vector<Incidencia> loadIncidencias(RegistroEntrada regEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Incidencia> resultado = new Vector<Incidencia>();
		try {
			con = bddConexion();
			// System.out.println("DAO loadIncidencias");
			String Qry="SELECT i.idIncidencia, i.nombre FROM registroEntrada re," +
					" registroEntrada_incidencia rei, incidencia i " +
					" WHERE re.idEntrada=rei.idEntrada" +
					" AND rei.idIncidencia=i.idIncidencia" +
					" AND re.codigoEntrada='" +regEntrada.getCodigoEntrada()+"'";	
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del formato de entrada en el registro
				Incidencia i = new Incidencia();
				i.setIdIncidencia(rs.getLong("idIncidencia"));
				i.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Boolean updateRegistroEntradaTmp(RegistroEntrada entryf,RegistroEntrada entryu, List listindic, List listestados) throws Exception {
		// System.out.println("DAO updateRegistroEntradaTmp");
		Statement stmt;
		Boolean resultado = false;
		int res=0;
		try {
			con = bddConexion();
			String strfechac = formatoDate(entryu.getFechaCaducidad());
			String strfechal = formatoDate(entryu.getFechaLlegada());
			//SQL de insercion
			String updateString= 
				" UPDATE registroentrada_tmp set"
				+ " fechaCaducidad = '" + strfechac + "',"
				+ " fechaLlegada = '" + strfechal + "',"
				+ " idFormatoEntrega = " + entryu.getIdFormatoEntrega() + ","
				+ " numeroPallets = " + entryu.getNumeroPallets() + ","
				+ " numeroBultos = " + entryu.getNumeroBultos() + ","
				+ " lote = '" + entryu.getLote() + "',"
				+ " idProducto = " + entryu.getIdProducto() + ","
				+ " idCategoria = " + entryu.getIdCategoria() + ","
				+ " idCategoriaEntrada = " + entryu.getIdCategoriaEntrada() + ","
				+ " cantidad = " + entryu.getCantidad() + ","
				+ " saldo = " + entryu.getCantidad() + ","
				+ " notasincidencias = '" + entryu.getNotasIncidencias() + "',"
				+ " idCosecha = " + entryu.getIdCosecha() + ","
				+ " lote = '" + entryu.getLote() + "',"
				+ " usuarioResponsable = '" + entryf.getIdOperario() + "',"
				+ " temperatura = " + entryu.getTemperatura() + ","
				+ " humedad = " + entryu.getHumedad()+ ","
				+ " costoUnitario = " + entryu.getCostoUnitario() + ","
				+ " costoTotal = " + entryu.getCostoTotal() + ","
				+ " idTipoUbicacion = " + entryu.getIdTipoUbicacion()
				+ " WHERE codigoEntrada = '" + entryf.getCodigoEntrada() + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			// Obtener el idEntrada
			RegistroEntrada RE = getRegistroEntradaTmp(entryf.getCodigoEntrada());
			//actualizamos las incidencias y estados		
			updateEstadosRegistroEntradaTmp(entryu, RE.getIdEntrada(), listestados);
			updateIncidenciasRegistroEntradaTmp(entryu, RE.getIdEntrada(), listindic);
			if(res==1){
				// System.out.println("REGISTRO ACTUALIZADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {			
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private void updateEstadosRegistroEntradaTmp(RegistroEntrada entry, long idRE, List listaEstados) throws SQLException {
		Statement stmt1;
		try {
			String deleteSql="DELETE FROM registroentrada_estado_tmp WHERE idEntrada= '" + idRE + "'";
			con = bddConexion();
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			try {
				stmt1.executeUpdate(deleteSql);
			} catch (Exception e) {
				purgaTemporales(con, entry.getIdOperario());
			}
			if(listaEstados != null && listaEstados.isEmpty()){
				// System.out.println("No hay lista estados");
			}else {
				addEstadosRegistroEntradaTmp(entry, con, idRE, listaEstados);
			}
		}catch (Exception e) {e.printStackTrace();}
	}

	private void updateIncidenciasRegistroEntradaTmp(RegistroEntrada entry,
			long idRE, List listaIncidencias) throws SQLException {
		Statement stmt1;
		try {
			String deleteSql="DELETE FROM registroentrada_incidencia_tmp WHERE idEntrada= '" + idRE + "'";		
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			try {
				stmt1.executeUpdate(deleteSql);
			} catch (Exception e) {
				purgaTemporales(con, entry.getIdOperario());
			}
			if(listaIncidencias == null || listaIncidencias.isEmpty()){
				// System.out.println("No hay lista incidencias");
			}else {
				if (listaIncidencias != null)
					addIncidenciasRegistroEntradaTmp(entry, con, idRE, listaIncidencias);
			}
		} catch (Exception e) {e.printStackTrace();}
	}

	//@Override
	public Boolean deleteRegistroEntradaTmp(RegistroEntrada entryf,RegistroEntrada entryd) throws Exception {
		// System.out.println("DAO deleteRegistroEntradaTmp");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//SQL deshabilita registro
			String updateString= 
				"UPDATE registroentrada_tmp SET " 
				+ " habilitado = 'N',"
				+ " usuarioResponsable = '" + entryf.getIdOperario() + "'"
				+ " WHERE codigoEntrada = '" + entryf.getCodigoEntrada() +"'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("REGISTRO ELIMINADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	//@Override
	public Boolean deleteRegistroEntradaTmp(String idOperario) throws Exception {
		try {
			// System.out.println("DAO deleteRegistroEntradaTmp");
			this.purgaRETemporales(idOperario);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		return true;
	}

	//@Override
	public RegistroEntrada getOrdenRegistroTmp(String codigoOrden) throws Exception {
		// System.out.println("DAORE getOrdenRegistroTmp");
		try {
			con = bddConexion();
			//SQL de seleccion
			String selectString= 
				"SELECT * FROM registroentrada_tmp WHERE codigoEntrada='" + codigoOrden+"'";
			// System.out.println(selectString);
			ps = con.prepareStatement(selectString);
			rs = ps.executeQuery();
			RegistroEntrada entrada = new RegistroEntrada();
			if (rs.next()) {
				entrada.setIdEntrada(rs.getLong("idEntrada"));
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setFechaCaducidad(rs.getString("fechaCaducidad"));
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setHabilitado(rs.getString("habilitado"));		
				entrada.setIdFormatoEntrega(rs.getLong("idFormatoEntrega"));	
				entrada.setNumeroPallets(rs.getLong("numeroPallets"));
				entrada.setNumeroBultos(rs.getLong("numeroBultos"));
				entrada.setIdProducto(rs.getLong("idProducto"));	
				entrada.setIdEnvase(rs.getLong("idEnvase"));
				entrada.setIdCategoria(rs.getLong("idCategoria"));	
				entrada.setCantidad(rs.getDouble("cantidad"));					
				entrada.setSaldo(rs.getDouble("saldo"));	
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));	
				entrada.setIdOperario(rs.getString("usuarioResponsable"));				
				entrada.setLote(rs.getString("lote"));
				entrada.setIdCosecha(rs.getLong("IdCosecha"));	
				entrada.setIdCategoriaEntrada(rs.getLong("idCategoriaEntrada"));
				entrada.setIdTipoRegistro(rs.getString("IdTipoRegistro"));		
				entrada.setTemperatura(rs.getDouble("temperatura"));
				entrada.setHumedad(rs.getDouble("humedad"));
				entrada.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));	
				entrada.setCostoUnitario(rs.getDouble("costoUnitario"));					
				entrada.setCostoTotal(rs.getDouble("costoTotal"));
				return entrada;
			}else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("No hay tal registro temporal");
			return null;
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	/**
	 * ***********************************************.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the fecha caducidad tmp
	 * @throws Exception the exception
	 */
	/* Selección de Fechas para tabla real y temporal */
	/**************************************************/
	//@Override
	public String getFechaCaducidadTmp(String codigoOrden) throws Exception {
		return getFechaCaducidad(codigoOrden,true);
	}

	//@Override
	public String getFechaCaducidad(String codigoOrden) throws Exception {
		return getFechaCaducidad(codigoOrden,false);
	}	

	//@Override
	public String getFechaCaducidad (String codigoOrden, Boolean temp) throws Exception {
		// System.out.println("DAORE getFechaRegistro");
		try {
			con = bddConexion();
			//SQL de seleccion
			String selectString= 
				"SELECT fechaCaducidad FROM registroentrada";
				if(temp){selectString+="_tmp ";}
				selectString+=" WHERE codigoEntrada='" + codigoOrden+"'";
			// System.out.println(selectString);
			ps = con.prepareStatement(selectString);
			rs = ps.executeQuery();
				
			Date fecha = new Date();
			if (rs.next()) {
				fecha=(rs.getDate("fechaCaducidad"));				
			}else return null;			
			String mes="";
			switch(fecha.getMonth()){
			   case 0:  mes = "01"; break;
			   case 1:  mes = "02"; break;
			   case 2:  mes = "03"; break;
			   case 3:  mes = "04"; break;
			   case 4:  mes = "05"; break;
			   case 5:  mes = "06"; break;
			   case 6:  mes = "07"; break;
			   case 7:  mes = "08"; break;
			   case 8:  mes = "09"; break;
			   case 9:  mes = "10"; break;
			   case 10: mes = "11"; break;
			   case 11: mes = "12"; break;
			}
			String fecharetorno = fecha.getDate() + "/" + mes + "/" + (1900+fecha.getYear());
			// System.out.println("La fecha devuelta es: "+fecharetorno);
			return fecharetorno;
			
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("No hay tal registro temporal");
			return null;
		} finally {
			try {
				con.close();
			} catch (Exception e) {	}
		}
	}

	//@Override
	public String getFechaRegistroTmp(String codigoOrden) throws Exception {
		return getFechaRegistro(codigoOrden,true);
	}

	//@Override
	public String getFechaRegistro(String codigoOrden) throws Exception {
		return getFechaRegistro(codigoOrden,false);
	}

	//@Override
	public String getFechaRegistro(String codigoOrden, Boolean temp) throws Exception {
		// System.out.println("DAORE getFechaRegistro");
		try {
			con = bddConexion();
			//SQL de seleccion
			String selectString= 
				"SELECT fechaLlegada FROM registroentrada";
				if(temp){selectString+="_tmp ";}
				selectString+=" WHERE codigoEntrada='" + codigoOrden+"'";
			// System.out.println(selectString);			
			//String selectString= 
			//	"SELECT fechaLlegada FROM registroentrada_tmp WHERE codigoEntrada='" + codigoOrden+"'";
			//// System.out.println(selectString);
			ps = con.prepareStatement(selectString);
			rs = ps.executeQuery();
			Date fecha = new Date();
			if (rs.next()) {
				fecha=(rs.getDate("fechaLlegada"));				
			}else return null;			
			String mes="";
			switch(fecha.getMonth()){
			   case 0:  mes = "01"; break;
			   case 1:  mes = "02"; break;
			   case 2:  mes = "03"; break;
			   case 3:  mes = "04"; break;
			   case 4:  mes = "05"; break;
			   case 5:  mes = "06"; break;
			   case 6:  mes = "07"; break;
			   case 7:  mes = "08"; break;
			   case 8:  mes = "09"; break;
			   case 9:  mes = "10"; break;
			   case 10: mes = "11"; break;
			   case 11: mes = "12"; break;
			}
			String fecharetorno = fecha.getDate() + "/" + mes + "/" + (1900+fecha.getYear());
			// System.out.println("La fecha devuelta es: "+fecharetorno);
			return fecharetorno;
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("No hay tal registro temporal");
			return null;
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	/**
	 * *************************************************.
	 *
	 * @param codigoOrden the codigo orden
	 * @return the orden entrada tmp
	 * @throws Exception the exception
	 */
	/* Obtenemos el regisro de entrada de cada tabla    */
	/****************************************************/
	//@Override
	public RegistroOrden getOrdenEntradaTmp(String codigoOrden) throws Exception {
		return getOrdenEntrada(codigoOrden,true);
	}

	//@Override
	public RegistroOrden getOrdenEntrada(String codigoOrden) throws Exception {
		return getOrdenEntrada(codigoOrden,false);
	}

	//@Override
	public RegistroOrden getOrdenEntrada(String codigoOrden, Boolean temp) throws Exception {
		//Inicializamos el Vector de retorno.
		RegistroOrden entrada = new RegistroOrden();
		try{
			con = bddConexion();
			// System.out.println("DAO getOrdenEntrada para "+codigoOrden);
			String Qry= 
				"SELECT * FROM  ordenentrada";
				if(temp){Qry+="_tmp ";}
				Qry+=" WHERE codigoOrden='" + codigoOrden+"'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {	
				entrada.setIdOrden(rs.getLong("idOrden"));
				entrada.setCodigoOrden(rs.getString("codigoOrden"));
				entrada.setFecha(rs.getDate("fecha"));
				entrada.setIdTipoRegistro(rs.getString("idTipoRegistro"));
				entrada.setIdProveedor(rs.getLong("idProveedor"));
				entrada.setIdTransportista(rs.getLong("idTransportista"));
				entrada.setOrigen(rs.getString("origen"));
				entrada.setAlbaran(rs.getString("albaran"));
				entrada.setDescVehiculo(rs.getString("descVehiculo"));
				entrada.setNotasVehiculo(rs.getString("notasVehiculo"));
				entrada.setHabilitado(rs.getString("habilitado"));
				entrada.setIdOperario(rs.getString("usuarioResponsable"));				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return entrada;
	}
	
	/**
	 * ********************************************************.
	 *
	 * @param codigoEntrada the codigo entrada
	 * @return the registros calidad
	 * @throws Exception the exception
	 */
	/*  CALIDAD */
	/***********************************************************/
	//@Override
	public Vector<RegistroCalidad> getRegistrosCalidad(String codigoEntrada) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector <RegistroCalidad> resultado = new Vector<RegistroCalidad>();
		//RegistroCalidad entrada;
		try {
			con = bddConexion();
			// System.out.println("DAO getRegistrosCalidad para "+codigoEntrada);
			String qry="SELECT * FROM analisis_registro WHERE codigoEntrada='" + codigoEntrada+"' order by fecha,idAnalisis desc";
			// System.out.println(qry);		
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				RegistroCalidad entrada = new RegistroCalidad();
				entrada.setIdAnalisis(rs.getLong("idAnalisis"));
				entrada.setVarIG(rs.getString("varIG"));
				entrada.setVarSP(rs.getString("varSP"));
				entrada.setVarDP(rs.getString("varDP"));
				entrada.setVarDA(rs.getString("varDA"));
				entrada.setVarM(rs.getString("varM"));
				entrada.setCalidad(rs.getDouble("calidad"));
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFecha(rs.getDate("fecha"));
				//entrada.setResponsable(rs.getString("responsable"));
				resultado.add(entrada);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
		return resultado;
	}

	//@Override
	public Boolean addAnalisisCalidadRegistro(RegistroCalidad calidad) throws Exception {
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id Analisis
			ps = con.prepareStatement("SELECT max(idAnalisis) as idMaxAnalisis FROM analisis_registro");
			rs = ps.executeQuery();
			long idAnalisisMax = 0;
			if(rs.next()){
				AnalisisRegistro analisisMaximo = new AnalisisRegistro();
				analisisMaximo.setIdAnalisis(rs.getLong("idMaxAnalisis"));
				idAnalisisMax= analisisMaximo.getIdAnalisis()+1;
			}else
				idAnalisisMax = 1;
			//SQL de insercion
			String insertString =
				"INSERT INTO analisis_registro(idAnalisis," +
					//"idRegistroEntrada," +
					"varIG,varSP,varDP,varDA,varM,calidad,fecha,codigoEntrada) values(" +
					idAnalisisMax + ","
					//+ calidad.getregistroEntrada.getIdEntrada() + ","
					+ calidad.getVarIG()+","
					+ calidad.getVarSP()+","
					+ calidad.getVarDP()+","
					+ calidad.getVarDA()+","
					+ calidad.getVarM() +","
					+ calidad.getCalidad()+",curdate(),'"
					//+ getDate(fecha)+",'"
					+ calidad.getCodigoEntrada() +
					//faltaría la fecha, responsable y baremo
					"')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("ANALISIS REGISTRO INSERTADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	/**
	 * ******************************************************
	 * @param entrada the entrada
	 * @return the bultos registro entrada
	 * @throws Exception the exception
	 */
	/*   GESTION DE BULTOS   */
	/*********************************************************/
	//@Override
	public Vector<Bulto> getBultosRegistroEntrada(RegistroEntrada entrada) throws Exception {
		Vector<Bulto> resultado = new Vector<Bulto>();
		try {
			con = bddConexion();
			// System.out.println("DAO getBultosRegistroEntrada para " + entrada.getCodigoEntrada());
			String qry =
				" SELECT rb.*, re.idTipoRegistro " +
				" FROM registroentrada_bulto rb, registroentrada re " +
				" WHERE rb.codigoEntrada = '" + entrada.getCodigoEntrada() + "' " +
					" AND re.codigoEntrada = rb.codigoEntrada; ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			int contador = 1;
			while (rs.next()) {
				Bulto bulto = new Bulto();
				bulto.setCodigoEntrada(rs.getString("codigoEntrada"));
				PreparedStatement pst = null;
				ResultSet rse = null;
				String qry2 =
					" SELECT u.idHueco, u.idUbicacion, descripcion, cantidad " +
					" FROM ubicacion u, ubicacion_hueco uh " +
					" WHERE uh.idHueco = u.idHueco " +
						" AND u.registro = '" + bulto.getCodigoEntrada() + "' " +
						" AND u.idPalet = " + contador;
				// System.out.println(qry2);
				pst = con.prepareStatement(qry2);
				rse = pst.executeQuery();
				while (rse.next()) {
					bulto.setIdUbicacion(rse.getLong("idUbicacion"));
					bulto.setIdHueco(rse.getLong("idHueco"));
					bulto.setDescripcionUbicacion(rse.getString("descripcion"));
					bulto.setCantidad(rse.getDouble("cantidad"));
					bulto.setIdPalet(contador);
				}
				bulto.setNumBulto(rs.getInt("numBulto"));
				String tipoRegistro = rs.getString("idTipoRegistro");
				if (tipoRegistro.equals("P")){
					//Peso del producto
					String consulta =
						" SELECT re.idProducto, p.peso AS pesoProducto " +
						" FROM registroentrada re, producto p " +
						" WHERE re.codigoEntrada = '" + entrada.getCodigoEntrada() + "' " +
							" AND re.idProducto = p.idProducto; ";
					PreparedStatement pst2 = null;
					ResultSet rse2 = null;
					// System.out.println(consulta);
					pst2 = con.prepareStatement(consulta);
					rse2 = pst2.executeQuery();
					if (rse2.next()) {
						bulto.setPeso(rse2.getDouble("pesoProducto"));
					}
				}else{
					bulto.setPeso(rs.getDouble("peso"));
				}
				bulto.setResponsable(rs.getString("responsable"));
				bulto.setPBruto(rs.getDouble("pBruto"));
				bulto.setPNeto(rs.getDouble("pNeto"));
				bulto.setNumBultosPalet(rs.getInt("numBultosPalet"));
				//Incluimos en el bulto información propia del registro de entrada,
					//para poder mostrarla en las etiquetas que se imprimen seguidas
				bulto.setDescripcionProducto(entrada.getDescProducto());
				bulto.setFechaLlegada(entrada.getFechaLlegada());
				bulto.setFechaCaducidad(entrada.getFechaCaducidad());
				bulto.setCodigoEntrada(entrada.getCodigoEntrada());
				bulto.setDescCategoria(entrada.getDescCategoria());
				bulto.setCantidad(entrada.getCantidad());
				if (bulto.getDescCategoria() != null){
					if(bulto.getDescCategoria().equals("Extra")){
						bulto.setOrigen("E");
					} else if(bulto.getDescCategoria().equals("Extra B")){
						bulto.setOrigen("B");
					} else if(bulto.getDescCategoria().equals("Primera")){
						bulto.setOrigen("P");
					} else if(bulto.getDescCategoria().equals("Segunda")){
						bulto.setOrigen("S");
					}
				}
				bulto.setNumeroPallets(entrada.getNumeroPallets());
				bulto.setNumeroBultos(entrada.getNumeroBultos());
				bulto.setCostoUnitario(bulto.getPNeto());
				bulto.setAlbaran("" + contador);
				bulto.setHumedad(bulto.getPBruto());
				bulto.setDescVehiculo(entrada.getDescVehiculo());
				qry2 =
					" SELECT p.idCategoria, c.nombre " +
					" FROM registroentrada re, producto p, categoria c " +
					" WHERE re.codigoEntrada = '" + bulto.getCodigoEntrada() + "' " +
							" AND re.idProducto = p.idProducto " +
							" AND re.idTipoRegistro = 'P' " +
							" AND c.idCategoria = p.idCategoria";
				// System.out.println(qry2);
				pst = con.prepareStatement(qry2);
				rse = pst.executeQuery();
				if (rse.next()) {
					bulto.setDescCategoria(rse.getString("nombre"));
				}else{
				}
				resultado.add(bulto);
				contador++;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	//@Override
	public Boolean addBultoRE(Bulto bulto) {
		// System.out.println("DAO addBultoRE");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		String insertString;
		try {
			con = bddConexion();
			// Si existe bulto, se hace update
			//if(existeBultoRE(idEntrada,numBulto)) {
			if(existeBultoRE(bulto)) {
				// System.out.println("Existe bulto");
				insertString =
					"UPDATE registroentrada_bulto SET peso=" + bulto.getPeso() + ", pNeto=" +bulto.getPNeto() +
					", pBruto=" + bulto.getPBruto() + ", numBultosPalet=" + bulto.getNumBultosPalet() +
					" WHERE codigoEntrada='"+bulto.getCodigoEntrada()+"' AND numBulto="+bulto.getNumBulto();
			}
			else {
				// System.out.println("No existe bulto");
				//SQL de insercion
				insertString= 
					"INSERT INTO registroentrada_bulto(codigoEntrada,numBulto,peso,numBultosPalet, pBruto, pNeto) VALUES('" +
					bulto.getCodigoEntrada() + "',"+ bulto.getNumBulto() +"," + bulto.getPeso()+"," +
					bulto.getNumBultosPalet()+"," + bulto.getPeso()+"," + bulto.getPeso()+")";
			}
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("BULTO INSERTADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return resultado;
		} finally {
			try {
				con.close();
			} catch (Exception e) {	e.printStackTrace(); }
		}
	}
	
	private Boolean existeBultoRE(Bulto bulto) {
		// System.out.println("DAO existeBultoRE");
		try {
			//SQL de seleccion
			String selectString = 
				"SELECT * FROM  registroentrada_bulto " +
				" WHERE codigoEntrada='" + bulto.getCodigoEntrada() + 
				"' AND numBulto=" + bulto.getNumBulto();
			// System.out.println(selectString);
			ps = con.prepareStatement(selectString);
			rs = ps.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	//@Override
	public Boolean updateBultoRE(Bulto bulto) throws Exception {
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			// System.out.println("DAO updateBultosRegistroEntrada para " + bulto.getCodigoEntrada());
			String qry =
				" UPDATE registroentrada_bulto " +
				" SET pBruto=" + bulto.getPBruto() + 
					", pNeto=" + bulto.getPNeto() + ", numBultosPalet = " + bulto.getNumBultosPalet() + 
				" WHERE codigoEntrada = '" + bulto.getCodigoEntrada() + "' AND numBulto = " + bulto.getNumBulto();
			// System.out.println(qry);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(qry);			
			if(res == 1){
				// System.out.println("REGISTRO ACTUALIZADO");
				resultado=true;
			}
			qry =
				" UPDATE ubicacion " +
				" SET cantidad = " + bulto.getPNeto() + 
				" WHERE registro = '" + bulto.getCodigoEntrada() + "' AND idPalet = " + bulto.getNumBulto();
			// System.out.println(qry);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(qry);			
			if(res == 1){
				// System.out.println("REGISTRO ACTUALIZADO");
				resultado=true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return resultado;
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	//@Override
	public Boolean addBultoRE(String idEntrada, int numBulto, double peso) throws Exception {		
		// System.out.println("Llegamos al DAO addBultosRE con parametros");
		Bulto bulto = new Bulto();
		bulto.setCodigoEntrada(idEntrada);
		bulto.setNumBulto(numBulto);
		bulto.setPeso(peso);
		return addBultoRE(bulto);
	}

	//@Override
	public Boolean inicializaBultosRE(Bulto bulto, double pesoTotal) throws Exception {
		// System.out.println("Llegamos al DAO inicializaBultosRE");
		try {
				// System.out.println("Bulto numero " + bulto.getNumBulto());
				//for(int i = 1; i <= bulto.getNumBulto(); i++) {
				Bulto bultoAux = new Bulto();
				//bultoAux.setNumBulto(i);
				bultoAux.setNumBulto(bulto.getNumBulto());
				bultoAux.setCodigoEntrada(bulto.getCodigoEntrada());
				bultoAux.setPeso(bulto.getPeso());
				bultoAux.setPBruto(bulto.getPeso());
				bultoAux.setPNeto(bulto.getPeso());
				addBultoRE(bultoAux);
			//}
			return true;
		}
		catch (Exception e) { e.printStackTrace(); return (false); }
	}
	
	//@Override
	public Vector<RegistroEntrada> getDevoluciones(String fecha) throws Exception{
		Vector <RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		//RegistroCalidad entrada;		
		try {
			con = bddConexion();
			// System.out.println("DAO getDevoluciones");
			//String qry="SELECT * FROM  registroentrada re, gp_envasado en, producto p WHERE idTipoRegistro='D' AND re.lote=en.lote AND p.idProducto = en.idProducto ";
			String qry =
				" SELECT re.*, e.descripcion, rei.estado, rei.estado AS estadoIncidencia, en.nombre AS nombreClienteDevolucion " +
				" FROM registroentrada re, registroentrada_incidencia rei, estadofabas e, entidad en " +
				" WHERE idTipoRegistro = 'D' " +
					" AND re.idEntrada = rei.idEntrada " +
					" AND e.idEstadoFabas = rei.idIncidencia " +
					" AND en.idUsuario = re.idClienteDevolucion ";
			if (fecha.compareTo("") != 0)
				qry += " AND fechaLlegada='" + fecha + "'";
			qry += " ORDER BY fechaLlegada ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				RegistroEntrada entrada = new RegistroEntrada();
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setLote(rs.getString("lote"));
				entrada.setCantidad(rs.getDouble("cantidad"));
				entrada.setDescResponsable(rs.getString("usuarioResponsable"));
				entrada.setIncidencia(rs.getString("descripcion"));
				boolean reaprovechar = false;
				String reap = rs.getString("reaprovecha");
				if (reap.equals("S"))
					reaprovechar = true;
				entrada.setReaprovechar(reaprovechar);
				entrada.setNombreClienteDevolucion(rs.getString("nombreClienteDevolucion"));
				if (rs.getString("estadoIncidencia").equals("S"))
					entrada.setIdIncidencias((long)0);
				else
					entrada.setIdIncidencias((long)1);
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));
				String estado = rs.getString("estado");
				if (estado.equals("S")){
					entrada.setAccionCorrectora("Devolver stock");
				}else{
					if (reaprovechar)
						entrada.setAccionCorrectora("Reaprovechar");
					else
						entrada.setAccionCorrectora("Enviar a destrio");
				}
				resultado.add(entrada);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw (e);
		}finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return resultado;
	}
	
	//@Override
	public Vector<RegistroEntrada> getDevolucionesLote(String lote) throws Exception{
		Vector <RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		//RegistroCalidad entrada;		
		try {
			con = bddConexion();
			// System.out.println("DAO getDevoluciones");
			//String qry="SELECT * FROM  registroentrada re, gp_envasado en, producto p WHERE idTipoRegistro='D' AND re.lote=en.lote AND p.idProducto = en.idProducto ";
			String qry =
				" SELECT re.*, e.descripcion, en.nombre AS nombreClienteDevolucion " +
				" FROM registroentrada re, registroentrada_incidencia rei, estadofabas e, entidad en " +
				" WHERE idTipoRegistro = 'D' " +
					" AND re.idEntrada = rei.idEntrada " +
					" AND e.idEstadoFabas = rei.idIncidencia " +
					" AND en.idUsuario = re.idClienteDevolucion " +
					" AND re.lote = '" + lote + "' ";
			qry += " ORDER BY fechaLlegada ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				RegistroEntrada entrada = new RegistroEntrada();
				String fecha = rs.getString("fechaLlegada");
				entrada.setFechaLlegada(formatoFecha(fecha));
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setLote(rs.getString("lote"));
				entrada.setCantidad(rs.getDouble("cantidad"));
				entrada.setDescResponsable(rs.getString("usuarioResponsable"));
				entrada.setIncidencia(rs.getString("descripcion"));
				entrada.setNombreClienteDevolucion(rs.getString("nombreClienteDevolucion"));
				entrada.setNotasIncidencias(rs.getString("notasincidencias"));
				resultado.add(entrada);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw (e);
		}finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return resultado;
	}
	
	//@Override
	public void devolucion(RegistroEntrada entry) throws Exception{
		// System.out.println("Devolver lote");
		try {
			//RegistroEntrada entry = new RegistroEntrada();
			con = bddConexion();
			Statement stmt;
			int res = 0;
			//obtener el nuevo Id
			ps = con.prepareStatement(" SELECT MAX(idEntrada) AS idMaxEntrada FROM registroentrada; ");
			rs = ps.executeQuery();
			long idEntradaMax = 0;
			while(rs.next()){
				RegistroEntrada entradaMaximo = new RegistroEntrada();
				entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
				idEntradaMax = entradaMaximo.getIdEntrada()+1;
			}
			// System.out.println("el id de entrada es... "+ idEntradaMax);
			//obtener fecha sistema
			ps = con.prepareStatement("SELECT CURDATE() as fecha");
			rs = ps.executeQuery();
			Date fecha = null;
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement("SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			String stringanno = "", stringmes = "";
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno = stringanno.substring(2);
			int mes1 = fecha.getMonth() + 1;
			if (mes1 <= 9){
				stringmes = "0" + mes1;
			}
			else {
				stringmes = String.valueOf(mes1);
			}
			String stringFecha = stringanno+stringmes+fecha.getDate();
			String codigoEntrada = "E" + stringFecha + "-" + idEntradaMax;
			entry.setCodigoEntrada(codigoEntrada);
			//String strfechal = getDate(entry.getFechaLlegada());
			//SQL de insercion para un RE original es el padre de simismo
			String reaprovecha = "N";
			if (entry.isReaprovechar())
				reaprovecha = "S";
			String insertString = 
				" INSERT INTO registroentrada(idEntrada, idEntradaPadre, codigoEntrada, fechaLlegada, " +
					" cantidad, notasincidencias, usuarioResponsable, lote, idTipoRegistro, idClienteDevolucion, reaprovecha) " +
				" VALUES(" +
					idEntradaMax + "," + idEntradaMax + ",'" + entry.getCodigoEntrada()+ "','" +
					fecha + "'," + entry.getCantidad() + ",'" + entry.getNotasIncidencias() + "','" +
					entry.getResponsable().getNombre() + "','" +
					entry.getLote() + "', 'D', " + entry.getIdClienteDevolucion() + ",'" + reaprovecha + "'); ";//Habilitado = D (Devolución)
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			String estado = "N";
			if (entry.isDevolverStock()){
				estado = "S";
			}
			insertString = 
				" INSERT INTO registroentrada_incidencia(idEntrada, idIncidencia, estado, usuarioResponsable) " +
				" VALUES(" +
					idEntradaMax + ", '" + entry.getIdIncidencias() + "', '" + estado + "','" +
					entry.getResponsable().getNombre() + "'); ";
			// System.out.println(insertString);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("REGISTRO INSERTADO");
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
	}
	
	//@Override
	public void checkMT() throws Exception{
		// System.out.println("Comprobamos los mantenimientos programados que se tendrán que ejecutar próximamente");
		try {
			//RegistroEntrada entry = new RegistroEntrada();
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT mm.nombre AS nombreMantenimiento, mmp.fechaProgramada, mm.descripcion AS descMantenimiento, " +
					" m.nombre as nombreMaquina, m.descripcion as descMaquina " +
					" FROM maquina_mantenimiento_programacion mmp, maquina_mantenimiento mm, maquina m " +
					" WHERE mmp.idMaquina=m.idMaquina AND mmp.idMantenimiento=mm.idMantenimiento AND mmp.estado='P'");
			rs = ps.executeQuery();
			final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
			java.util.Date hoy = new Date(); //Fecha de hoy 
			String contenidoMensaje = "";
			boolean avisar = false;
			while(rs.next()){
				String fecha = rs.getString("fechaProgramada");
				String frag[] = fecha.split("-");
				int annio = Integer.parseInt(frag[0]);
				int mes = Integer.parseInt(frag[1]);
				int dia = Integer.parseInt(frag[2]);
				Calendar programada = new GregorianCalendar(annio, mes - 1, dia); 
				java.sql.Date fechaProgramada = new java.sql.Date(programada.getTimeInMillis());
				// System.out.println("Milisegundos hoy: " + hoy.getTime());
				// System.out.println("Milisegundos programada: " + fechaProgramada.getTime());
				long diferencia = (hoy.getTime() - fechaProgramada.getTime() ) / MILLSECS_PER_DAY;
				// System.out.println(diferencia);
				if (diferencia > -7){//Falta menos de una semana para que se cumpla la fecha programada para el mantenimiento
					if (!rs.isFirst())
						contenidoMensaje += "#############\n\n";
					if (diferencia == 0)
						contenidoMensaje += "Mantenimiento programado para hoy: '" +
							rs.getString("nombreMantenimiento") + " -> " + rs.getString("descMantenimiento") + "' sobre la máquina " +
							rs.getString("nombreMaquina") + ": " + rs.getString("descMaquina") + ". Fecha programada: " + fecha + "\n\n";
					else
						if (diferencia > 0)
							contenidoMensaje += "Ha sobrepasado la fecha programada para el mantenimiento: '" +
								rs.getString("nombreMantenimiento") + " -> " + rs.getString("descMantenimiento") + "' sobre la máquina " +
								rs.getString("nombreMaquina") + ": " + rs.getString("descMaquina") + ". Fecha programada: " + fecha + "\n\n";
						else
							contenidoMensaje += "Falta menos de una semana para que se cumpla la fecha programada para el mantenimiento: '" +
								rs.getString("nombreMantenimiento") + " -> " + rs.getString("descMantenimiento") + "' sobre la máquina " +
								rs.getString("nombreMaquina") + ": " + rs.getString("descMaquina") + ". Fecha programada: " + fecha + "\n\n";
					avisar = true;
				}
			}
			if (avisar){
				// System.out.println(contenidoMensaje);
				try{
					// System.out.println("Notificamos por email los mantenimientos");
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					ClassLoader loader = Thread.currentThread().getContextClassLoader();
					String path = loader.getResource("configuracion.xml").toURI().getPath();
					Document doc = dBuilder.parse(new File(path));
					doc.getDocumentElement().normalize();
					NodeList listaNodosHijos = doc.getElementsByTagName("email");       
					Node email = listaNodosHijos.item(0);
					Element elemento = (Element) email;
					String HOST = getTagValue("host", elemento);
					USER = getTagValue("user", elemento);
					PASS = getTagValue("pass", elemento);
					String FROM = getTagValue("from", elemento);
					String TO = getTagValue("to", elemento);
					String ASUNTO = getTagValue("asunto", elemento);
					Properties props = new Properties();
					props.put("mail.smtp.host", HOST);
					props.put("mail.smtp.auth", "true");
					props.put("mail.debug", "true");
					props.put("mail.smtp.user", USER);
					props.put("mail.smtp.password", PASS);
					props.put("mail.smtp.port", 25 );
					String[] emailList = {TO};
					boolean debug = true;
					Authenticator auth = new SMTPAuthenticator();
					Session session = Session.getDefaultInstance(props, auth);
					session.setDebug(debug);
					Message msg = new MimeMessage(session);
					InternetAddress addressFrom = new InternetAddress(FROM);
					msg.setFrom(addressFrom);
					InternetAddress[] addressTo = new InternetAddress[emailList.length];
					for (int i = 0; i < emailList.length; i++){
						addressTo[i] = new InternetAddress(emailList[i]);
					}
					msg.setRecipients(Message.RecipientType.TO, addressTo);
					msg.setSubject(ASUNTO);
					msg.setContent(contenidoMensaje, "text/plain");
					//Transport.send(msg);	
				    }catch(Exception ex){ex.printStackTrace();}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	
	private static String getTagValue(String sTag, Element eElement){
		NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			String username = USER;//"prueba@induserco.com";
			String password = PASS;//"holahola";
			return new PasswordAuthentication(username, password);
		}
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
	 * Nos llega una fecha de la forma aaaa-mm-dd, y la necesitamos de la forma dd/mm/aaaa
	 * @param fecha
	 * @return
	 */
	private String formatoFecha(String fecha){
		String retorno = "";
		String[] frag = fecha.split("-");
		String diaAux = frag[2];
		if (diaAux.length() == 1){
			diaAux = "0" + diaAux;
		}
		retorno += diaAux;
		String mesAux = frag[1];
		if (mesAux.length() == 1){
			mesAux = "0" + mesAux;
		}
		retorno += "/" + mesAux;
		String anioAux = frag[0];
		if (anioAux.length() == 2){
			anioAux = "20" + anioAux;
		}
		retorno += "/" + anioAux;
		return retorno;
	}
	
	/**
	 * @param linea (lote, teorica)
	 * @throws Exception
	 */
	//@Override
	public void reaprovecharDevolucion(LineaProducto linea) throws Exception{
		// System.out.println("reaprovecharDevolucion");
		Statement stmt;
		Date fecha = null;
		String stringanno = null;
		int res = 0;	
		try {
			RegistroEntrada entry = this.getRegistroEntrada(linea.getLote());
			String origen = "Devolucion", albaran = "D" + linea.getLote(), descVehiculo = "", notasVehiculo = "Devolucion";
			long idProveedor = 41, idTransportista = 52, idProducto = entry.getIdProducto();
			double cantidad = linea.getTeorica();
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idOrden) as idMaxEntrada FROM ordenentrada");
			rs = ps.executeQuery();
			long idOrdenMax = 0;
			if(rs.next()){
				RegistroOrden entradaMaximo = new RegistroOrden();
				entradaMaximo.setIdOrden(rs.getLong("idMaxEntrada"));
				idOrdenMax = entradaMaximo.getIdOrden() + 1;
			}else
				idOrdenMax = 1;
			// System.out.println("el id de entrada es... "+ idOrdenMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			//formatear fecha sistema para codigo entrada
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while(rs.next()){
				stringanno = rs.getString("anno");
	        }
			stringanno = stringanno.substring(2);
			//SQL de insercion
			String codigoOrden = getCodigoOrden();
			con = bddConexion();
			String insertString = 
				"INSERT INTO ordenentrada (idOrden, codigoorden, fecha, idProveedor, idTransportista, " +
					"origen,albaran,descVehiculo,notasvehiculo,usuarioResponsable,habilitado) " +
				" VALUES(" +
					idOrdenMax + ",'" + codigoOrden + "','" + fecha + "',"
					+ idProveedor + "," + idTransportista + ",'"
					+ origen + "','" + albaran + "','" + descVehiculo + "','"
					+ notasVehiculo + "','"
					+ linea.getUsuarioResponsable() + "','S'"
				+ ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println(" REGISTRO ORDEN INSERTADA ");
			}
			String strfechac = null;
			String strfechal = null;
			String codigoEntrada = null;
			//obtener el nuevo Id
			ps = con.prepareStatement(" SELECT max(idEntrada) as idMaxEntrada FROM registroentrada ");
			rs = ps.executeQuery();
			long idEntradaMax = 0;
			while(rs.next()){
				RegistroEntrada entradaMaximo = new RegistroEntrada();
				entradaMaximo.setIdEntrada(rs.getLong("idMaxEntrada"));
				idEntradaMax= entradaMaximo.getIdEntrada() + 1;
			}
			// System.out.println("el id de entrada es... " + idEntradaMax);
			//obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() AS fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				fecha = rs.getDate("fecha");
	        }
			codigoEntrada = this.generarCodigoEntrada();
			if (entry != null){
				if (entry.getFechaCaducidad() != null){
					if (entry.getFechaCaducidad().split("-").length > 1)
						strfechac = entry.getFechaCaducidad();
					else
						strfechac = formatoDate(entry.getFechaCaducidad());
				}
				if (entry.getFechaLlegada() != null){
					if (entry.getFechaLlegada().split("-").length > 1)
						strfechal = entry.getFechaLlegada();
					else
						strfechal = formatoDate(entry.getFechaLlegada());
				}
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = new java.util.Date();
			strfechal = dateFormat.format(date);
			// System.out.println("Current Date : " + strfechal);
			String idTipoRegistro = entry.getIdTipoRegistro();
			if (linea.getLote().charAt(0) == '0'){
				idTipoRegistro = "P";
				//Buscamos la fecha de caducidad
				ps = con.prepareStatement(" SELECT fechaCaducidad, idProducto, orden " +
						" FROM gp_envasado WHERE lote = '" + linea.getLote() + "'; ");
				rs = ps.executeQuery();
				if(rs.next()){
					strfechac = rs.getString("fechaCaducidad");
					idProducto = rs.getLong("idProducto");
					//Comprobamos si se envasó una agrupación o no
					String orden = rs.getString("orden");
					ps = con.prepareStatement(" SELECT idAgrupacion FROM gp_envasado_agrupacion WHERE ordenEnvasado = '" + orden + "' AND idAgrupacion > 0; ");
					rs = ps.executeQuery();
					if(rs.next()){
						idProducto = rs.getLong("idAgrupacion");
					}
		        }
			}
			//SQL de insercion para un RE original es el padre de simismo
			insertString =
				" INSERT INTO registroentrada (idEntrada,idEntradaPadre,codigoEntrada,codigoOrden,fecha,fechaCaducidad,fechaLlegada, " +
					"idProducto,idCategoria,saldo,cantidad," +
					"notasincidencias,idFormatoEntrega,numeroPallets,numeroBultos,usuarioResponsable,idCosecha," +
					"idCategoriaEntrada,idTipoRegistro,idEnvase,costoUnitario," +
					"costoTotal,idTipoUbicacion,lote) " +
				" VALUES(" +
					idEntradaMax + ","+ idEntradaMax + ",'" + codigoEntrada + "','" + codigoOrden + "','" + fecha + "','"
					+ strfechac + "','" + strfechal + "',"
					+ idProducto +"," + entry.getIdCategoria() + "," + cantidad +","
					+ cantidad + ",'" + entry.getNotasIncidencias() +"',"
					+ entry.getIdFormatoEntrega() + "," + 1 + "," 
					+ 1 + ",'" + linea.getUsuarioResponsable() + "',"
					+ entry.getIdCosecha() +","
					+ entry.getIdCategoriaEntrada() + ",'" + idTipoRegistro + "',"		
					+ entry.getIdEnvase() + ","	+ 0 + "," + 0 + ","
					+ entry.getIdTipoUbicacion() + ",'" + linea.getLote() + "'); ";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (idTipoRegistro.compareTo("M") == 0){
				actualizaStockMateria(entry.getIdProducto(), entry.getIdCategoriaEntrada(), cantidad);
			} else{
				if (idTipoRegistro.compareTo("E") == 0){
					actualizaStockEnvase(entry.getIdEnvase(), cantidad);
				}
			}
			long idHueco = 221;//Identificador de la entrada al almacen
			if (idTipoRegistro.compareTo("P") == 0){
				//Si el registro de entrada es un producto para vender, lo almacenamos directamente en salida (id del hueco = 223)
				idHueco = 223;
			}
			con = bddConexion();
			insertString =
				" INSERT INTO ubicacion(idHueco, registro, cantidad, orden, idPalet) " +
				" VALUES (" + idHueco + ", '" + codigoEntrada + "'" +
					", " + cantidad + ", '" + codigoOrden + "', 1); ";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if(res == 1){
				// System.out.println("REGISTRO INSERTADO");
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
	}
}