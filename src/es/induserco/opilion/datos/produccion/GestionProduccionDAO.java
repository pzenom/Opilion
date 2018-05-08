package es.induserco.opilion.datos.produccion;

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
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.comun.RegistroActividad;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.EstadoFabas;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Incidencia;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.datos.entrada.RegistroEntradaDAO;
import es.induserco.opilion.infraestructura.DatabaseConfig;
import es.induserco.opilion.negocio.ProductoDataHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

import java.util.GregorianCalendar;

/**
 * CONTROLADOR DAO DE GESTION DE PRODUCCION
 * @author Induserco - Andrés (07/04/2011) (23/08/2012)
 * @version 2.2
 */
@SuppressWarnings("unchecked")
public class GestionProduccionDAO extends DatabaseConfig implements IGestionProduccionDataService {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;

	public String getDate(Date fc) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(fc);
		int anno = cal.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH) + 1;
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		Date d = null;
		String dd = null;
		Calendar fecha = Calendar.getInstance();
		fecha.set(anno, mes, dia);
		String strFecha = fecha.get(java.util.Calendar.YEAR) + "-" + mes + "-" + fecha.get(java.util.Calendar.DATE);
		try {
			d = format.parse(strFecha);
			dd = format.format(d);
		} catch (ParseException ex) { ex.printStackTrace(); }
		return dd;
	}

	/**
	 * Adds the registro envasado.
	 * @param gprod PROCESO: ENVASADO 1) Método que sirve cuando le estoy pasando
	 * la lista de los ingredientes y el proceso para que se cree
	 * @return the boolean
	 * @throws Exception the exception
	 * @author Induserco
	 */
	//@Override
	public Boolean addRegistroEnvasado(GestionProduccion gprod) throws Exception {
		// System.out.println("1)ENVASADO - DAO addRegistroEnvasado gprod");
		Statement stmt;
		String codigoOrden = null;
		Boolean resultado = false;
		String fecha = null;
		int res = 0;
		try {
			con = bddConexion();
			// obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEnvasado) as idMaxEnvasado FROM gp_envasado");
			rs = ps.executeQuery();
			long idMaxEnvasado = 0;
			if (rs.next()) {
				GestionProduccion envasadoMaximo = new GestionProduccion();
				envasadoMaximo.setIdGestion(rs.getLong("idMaxEnvasado"));
				idMaxEnvasado = envasadoMaximo.getIdGestion() + 1;
			} else
				idMaxEnvasado = 1;
			// System.out.println("el id de gestion es... " + idMaxEnvasado);
			// obtener fecha sistema
			ps = con.prepareStatement(" SELECT DATE_FORMAT(CURDATE(),'%Y%m%d') as fecha ");
			rs = ps.executeQuery();
			while (rs.next()) {
				fecha = rs.getString("fecha");
			}
			codigoOrden = "OE" + fecha + "0" + idMaxEnvasado;
			gprod.setOrden(codigoOrden);
			// SQL de insersion
			String insertString =
				" INSERT INTO gp_envasado(idEnvasado, idProducto, orden, fecha, horainicio, usuarioResponsable, estadoproceso) " +
				" VALUES("
					+ idMaxEnvasado
					+ ","
					+ gprod.getIdProducto()
					+ ",'"
					+ gprod.getOrden()
					+ "',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'I','"
					+ gprod.getIdOperario() + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (res == 1) {
				// System.out.println("REGISTRO INSERTADO");
				resultado = true;
			}
			stmt.close();
			ps.close();
			rs.close();
			con.close();
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
	 * Adds the componentes envasado.
	 *
	 * @param listaelementos the listaelementos
	 * @param orden the orden
	 * @param tipo the tipo
	 * @return the boolean
	 * @throws Exception the exception
	 * @author Induserco PROCESO: ENVASADO 2) Usado en Envasado Agrega los
	 * componentes para el envasado, pueden ser materias primas o
	 * envases.
	 */
	//@Override
	public Boolean addComponentesEnvasado(List<String> listaelementos, String orden,
			String tipo) throws Exception {
		// System.out.println("2) ENVASADO DAO 2. getEnvasadoInsertarMP con lista");
		// Inicializamos el Vector de retorno.
		Statement stmt = null;
		Boolean resultado = false;
		int finsubstring = 0;
		if (listaelementos.isEmpty()){
			// System.out.println("No hay lista de elementos");
		}else {
			Iterator<String> itr = listaelementos.iterator();
			while (itr.hasNext()) {
				String codigoEntrada = (String) itr.next();
				finsubstring = codigoEntrada.length() - 1;
				codigoEntrada = codigoEntrada.substring(0, finsubstring);
				RegistroEntrada regEntrada = new RegistroEntrada();
				try {
					con = bddConexion();
					String Qry =
						" SELECT re.codigoEntrada,re.saldo, re.idProducto, mp.nombre"
						+ " FROM registroentrada re, materiaprima mp "
						+ " WHERE re.idProducto= mp.idProducto "
							+ " AND re.codigoEntrada = '" + codigoEntrada + "'";
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					while (rs.next()) {
						// Completamos los datos del proveedor en el registro
						regEntrada.setCodigoEntrada(rs
								.getString("codigoEntrada"));
						regEntrada.setCantidad(rs.getDouble("saldo"));
						regEntrada.setIdProducto(rs.getLong("idProducto"));
						regEntrada.setDescProducto(rs.getString("nombre"));
					}
					String insertString =
						" INSERT INTO tmp_gp_envasado_detalle(orden, idTipoRegistro,idProducto,codigoEntrada," +
							" cantidadDisponible,descripcion) "
						+ " VALUES("
						+ "'"
						+ orden
						+ "','M',"
						+ regEntrada.getIdProducto()
						+ ",'"
						+ regEntrada.getCodigoEntrada()
						+ "',"
						+ regEntrada.getCantidad()
						+ ",'"
						+ regEntrada.getDescProducto() + "')";
					// System.out.println("insert al tmp_gp_envasado_detalle " + insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				} catch (Exception e) {
					e.printStackTrace();
					throw (e);
				} finally {
					try {
						ps.close();
						rs.close();
						stmt.close();
						con.close();
					} catch (Exception e) { e.printStackTrace(); }
				}
			}
		}
		return resultado;
	}

	/**
	 * Gets the tmp registro ingredientes envases.
	 *
	 * @param orden the orden
	 * @return the tmp registro ingredientes envases
	 * @throws Exception the exception
	 * @author Induserco PROCESO: ENVASADO 3) Recupera los RE registrados para el
	 * envasado SI SE USA
	 * *
	 */
	//@Override
	public Vector<GestionProduccion> getTmpRegistroIngredientesEnvases(String orden) throws Exception {
		// System.out.println("3) ENVASADO DAO getTmpRegistroIngredientesEnvases");
		// Inicializamos el ector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			// SELECT para extraer todos los RE insertados relacionados con esa
			// Orden de Envasado
			String Qry =
				" SELECT tmp.orden, tmp.codigoEntrada, tmp.idProducto, tmp.descripcion as descripcion, tmp.cantidadDisponible "
				+ " FROM tmp_gp_envasado_detalle tmp WHERE tmp.orden = '" + orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setProceso(rs.getString("codigoEntrada"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descripcion"));
				gestion.setCantidadProducto(rs.getDouble("cantidadDisponible"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/* Actualiza cantidades de los RE para envasado */
	/**
	 * Update tmp registro ingredientes envases.
	 *
	 * @param mapaCantidades the mapa cantidades
	 * @param orden the orden
	 * @return the boolean
	 * @throws Exception the exception
	 * @author Induserco
	 */
	//@Override
	public Boolean updateTmpRegistroIngredientesEnvases(Map mapaCantidades, String orden) throws Exception {
		// System.out.println(" 4)ENVASADO DAO updateTmpRegistroIngredientesEnvases con codigo orden");
		int res = 0;
		Statement stmt;
		Boolean resultado = false;
		try {
			con = bddConexion();
			for (Iterator it = mapaCantidades.keySet().iterator(); it.hasNext();) {
				String codigoEntradaMapa = (String) it.next();
				// System.out.println("codigo entrada mapa " + codigoEntradaMapa);
				String updateString = "update tmp_gp_envasado_detalle set"
						+ " cantidadUsable = "
						+ Double.parseDouble((String) mapaCantidades
						//+ Long.parseLong((String) mapaCantidades								
								.get(codigoEntradaMapa)) + " WHERE orden = '"
						+ orden + "'" + " AND codigoEntrada = '"
						+ codigoEntradaMapa + "'";
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
	 * Inserta cantidades envases.
	 *
	 * @param mapaCantidades the mapa cantidades
	 * @param orden the orden
	 * @return the boolean
	 * @throws Exception the exception
	 * @author Induserco PROCESO ENVASADO 5) Inserta las cantidades de la orden de
	 * los envases
	 * *
	 */
	//@Override
	public Boolean insertaCantidadesEnvases(Map mapaCantidades, String orden) throws Exception {
		// System.out.println("5) ENVASADO DAO insertaCantidadesEnvases con codigo orden");
		// Inicializamos el Vector de retorno.
		int res = 0;
		Statement stmt;
		Boolean resultado = false;
		RegistroEntrada regEntrada = new RegistroEntrada();
		try {
			con = bddConexion();
			for (Iterator<String> it = mapaCantidades.keySet().iterator(); it.hasNext();) {
				String codigoEntradaMapa = (String) it.next();
				// System.out.println("codigo entrada mapa " + codigoEntradaMapa);
				String Qry = " SELECT re.saldo, en.nombre"
						+ " FROM registroentrada re, envase en "
						+ " WHERE re.idEnvase = en.idEnvase "
						+ " AND re.codigoEntrada = '" + codigoEntradaMapa + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {// recuperar saldo original
					regEntrada.setCantidad(rs.getDouble("saldo"));
					regEntrada.setDescProducto(rs.getString("nombre"));
				}
				// para verificar si es que se tiene como null o no a la
				// cantidad
				Long cantEdit = null;
				if ((String) mapaCantidades.get(codigoEntradaMapa) == "") {
					cantEdit = 0L;
					// System.out.println("string vacio");
				} else {
					cantEdit = Long.parseLong((String) mapaCantidades
							.get(codigoEntradaMapa));
					// System.out.println("string con algo");
				}
				cantEdit = Long.parseLong((String) mapaCantidades.get(codigoEntradaMapa));
				String insertString = "insert into tmp_gp_envasado_detalle(orden, idTipoRegistro,codigoEntrada, cantidadDisponible, cantidadUsable, descripcion) values("
						+ "'"
						+ orden
						+ "',"
						+ "'E','"
						+ codigoEntradaMapa
						+ "',"
						+ regEntrada.getCantidad()
						+ ","
						+ cantEdit
						+ ",'" + regEntrada.getDescProducto() + "')";
				// System.out.println("insert al tmp_gp_envasado_detalle de envases utilizados" + insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
			}
			if (res == 1) {
				// System.out.println("REGISTRO INSERTADO");
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Gets the registro envases.
	 *
	 * @param listaenvases the listaenvases
	 * @return the registro envases
	 * @throws Exception the exception
	 * @author Induserco PROCESO: ENVASADO 6)Retorna los datos de los RE de envases
	 * relacionados con la lista.
	 * *
	 */
	//@Override
	public Vector<GestionProduccion> getRegistroEnvases(List listaenvases) throws Exception {
		// System.out.println("6) DAO getRegistroEnvases con lista");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		int finsubstring = 0;
		if (listaenvases.isEmpty()){
			// System.out.println("No hay lista envases");
		}else {
			Iterator itr = listaenvases.iterator();
			while (itr.hasNext()) {
				String codigo = (String) itr.next();
				finsubstring = codigo.length() - 1;
				codigo = codigo.substring(0, finsubstring);
				try {
					con = bddConexion();
					String Qry = " SELECT re.codigoEntrada,re.idEnvase,e.nombre as envases,re.saldo "
							+ " FROM registroentrada re, envase e WHERE re.idEnvase = e.idEnvase "
							+ " AND re.codigoEntrada = '" + codigo + "'";
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					while (rs.next()) {
						// Completamos los datos del proveedor en el registro
						GestionProduccion gestion = new GestionProduccion();
						gestion.setOrden(rs.getString("codigoEntrada"));
						gestion.setIdEnvase(rs.getLong("idEnvase"));
						gestion.setDescEnvase(rs.getString("envases"));
						gestion.setCantidadUnidadesEnvases(rs.getLong("saldo"));
						//gestion.setFecha(rs.getString("fecha"));
						//gestion.setFechaCaducidad(rs.getDate("fechaCaducidad"));
						// La añadimos al Vector de resultado
						resultado.add(gestion);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw (e);
				} finally {
					try {

						ps.close();
						rs.close();
						con.close();
					} catch (Exception e) { e.printStackTrace(); }
				}
			}
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Adds the tmp registro ingredientes envases.
	 *
	 * @param mapaCantidades the mapa cantidades
	 * @param orden the orden
	 * @return the boolean
	 * @throws Exception the exception
	 * @author Induserco PROCESO: ENVASADO 7)Inserta el detalle en la tabla
	 * tmp_gp_envasado_detalle
	 */
	//@Override
	public Boolean addTmpRegistroIngredientesEnvases(Map mapaCantidades,
			String orden) throws Exception {
		// System.out.println("7) INVOCADO DAO addTmpRegistroIngredientesEnvases");
		// Inicializamos el Vector de retorno.
		GestionProduccion gestionenv = new GestionProduccion();
		int res = 0;
		Statement stmt;
		Boolean resultado = false;
		try {
			con = bddConexion();
			for (Iterator it = mapaCantidades.keySet().iterator(); it.hasNext();) {
				String codigo = (String) it.next();
				String cantidad = (String) mapaCantidades.get(codigo);
				// System.out.println("Map creado " + codigo + " : " + cantidad);
				String Qry2 =
					" SELECT re.idProducto,re.idTipoRegistro,re.saldo,re.idEnvase,e.nombre FROM registroentrada re, envase e "
					+ " WHERE re.idEnvase = e.idEnvase AND re.habilitado = 'S' AND re.codigoEntrada = '" + codigo + "'";
				// System.out.println(Qry2);
				ps = con.prepareStatement(Qry2);
				rs = ps.executeQuery();
				while (rs.next()) {
					GestionProduccion gestionenvases = new GestionProduccion();
					gestionenvases.setIdProducto(rs.getLong("idProducto"));
					gestionenvases.setProceso(rs.getString("idTipoRegistro"));
					gestionenvases.setCantidadUnidadesEnvases(rs.getLong("saldo"));
					gestionenvases.setIdEnvase(rs.getLong("idEnvase"));
					gestionenvases.setDescEnvase(rs.getString("nombre"));
					gestionenv = gestionenvases;
				}
				String insertString = "insert into tmp_gp_envasado_detalle(orden,idTipoRegistro, " +
						" codigoEntrada,idProducto,cantidadDisponible,cantidadUsable,mermaProducto,descripcionEnvase) values("
						+ "'"
						+ orden
						+ "','"
						+ gestionenv.getProceso()
						+ "','"
						+ codigo
						+ "',"
						+ gestionenv.getIdEnvase()
						+ ","
						+ gestionenv.getCantidadUnidadesEnvases()
						+ ","
						+ cantidad
						+ ","
						+ gestionenv.getMermaEnvases()
						+ ",'"
						+ gestionenv.getDescEnvase() + "')";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
			}
			if (res == 1){
				// System.out.println("REGISTRO INSERTADO");
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {

				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Update tmp registro mermas envases.
	 *
	 * @param mapaCantidades the mapa cantidades
	 * @param orden the orden
	 * @return the boolean
	 * @throws Exception the exception
	 * @author Induserco PROCESO ENVASADO 8) Actualiza las cantidades de las mermas
	 * de los envases y mp seleccionados
	 */

	//@Override
	public Boolean updateTmpRegistroMermasEnvases(Map mapaCantidades,
			String orden) throws Exception {
		// System.out.println("8) ENVASADO DAO updateTmpRegistroMermasEnvases con codigo orden");
		// Inicializamos el Vector de retorno.
		int res = 0;
		Statement stmt;
		Boolean resultado = false;
		try {
			con = bddConexion();
			for (Iterator it = mapaCantidades.keySet().iterator(); it.hasNext();) {
				String ce = (String) it.next();
				// System.out.println("codigo entrada " + ce);
				String updateString = "update tmp_gp_envasado_detalle set"
						+ " mermaProducto = "
						+ Double.parseDouble((String) mapaCantidades.get(ce))
						//+ Long.parseLong((String) mapaCantidades.get(ce))
						+ " WHERE orden = '" + orden + "'"
						+ " AND codigoEntrada = '" + ce + "'";
				// System.out.println(updateString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	 * Gets the sub registros entrada envasado.
	 *
	 * @param orden the orden
	 * @return the sub registros entrada envasado
	 * @throws Exception the exception
	 * @author Induserco PROCESO ENVASADO 9) Lista todos los re de mp y de env
	 * asociados a una orden de envasado
	 * *
	 */
	//@Override
	public Vector<GestionProduccion> getSubRegistrosEntradaEnvasado(String orden) throws Exception {
		// System.out.println("9) ENVASADO DAO getSubRegistrosEntradaEnvasado");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			String Qry = " SELECT tmp.orden, tmp.codigoEntrada, tmp.idProducto, tmp.descripcion as descMateria,tmp.cantidadUsable, tmp.mermaProducto "
					+ " FROM tmp_gp_envasado_detalle tmp WHERE tmp.orden = '"
					+ orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setProceso(rs.getString("codigoEntrada"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descMateria"));
				gestion.setCantidadProducto(rs.getDouble("cantidadUsable"));
				gestion.setMermaIngredientes(rs.getDouble("mermaProducto"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {

				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Boolean insertRegistroEnvasado(GestionProduccion gprodf,
			GestionProduccion gprodu, String proceso) throws Exception {
		// System.out.println("10) ENVASADO DAO insertRegistroEnvasado: " + proceso);
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		Double cantTotalOrden = null;
		Double mermaTotalMP = null;
		Double mermaTotalEN = null;
		String loteOrden = null;
		try {
			con = bddConexion();
			// CANTIDAD TOTAL
			String QryTotales = " SELECT sum(cantidadUsable) as cantTotalOrden "
					+ " FROM tmp_gp_envasado_detalle "
					+ " WHERE orden='"
					+ gprodf.getOrden() + "'";
			ps = con.prepareStatement(QryTotales);
			rs = ps.executeQuery();
			while (rs.next()) {
				cantTotalOrden = rs.getDouble("cantTotalOrden");
			}
			// total merma MATERIA PRIMA
			String QryTotalMermaMP = " SELECT sum(mermaProducto) as mermaTotalMP"
					+ " FROM tmp_gp_envasado_detalle "
					+ " WHERE idTipoRegistro='M' "
					+ " AND orden='"
					+ gprodf.getOrden() + "'";
			ps = con.prepareStatement(QryTotalMermaMP);
			rs = ps.executeQuery();
			while (rs.next()) {
				mermaTotalMP = rs.getDouble("mermaTotalMP");
			}
			// total merma ENVASES
			String QryTotalMermaEN = " SELECT sum(mermaProducto) as mermaTotalEN"
					+ " FROM tmp_gp_envasado_detalle "
					+ " WHERE idTipoRegistro='E' "
					+ " AND orden='"
					+ gprodf.getOrden() + "'";
			ps = con.prepareStatement(QryTotalMermaEN);
			rs = ps.executeQuery();
			while (rs.next()) {
				mermaTotalEN = rs.getDouble("mermaTotalEN");
			}
			// GENERACION DEL LOTE DEL ENVASADO
			String QryLote = " SELECT concat('EN-',concat(idEnvasado,idProducto)) as lote "
					+ " FROM gp_envasado "
					+ " WHERE orden='"
					+ gprodf.getOrden() + "'";
			ps = con.prepareStatement(QryLote);
			rs = ps.executeQuery();
			while (rs.next()) {
				loteOrden = rs.getString("lote");
			}
			// actualizar los datos que faltan para concluir el registro de
			// envasado
			String updateString = "update gp_envasado set" + " cantidad = "
					+ cantTotalOrden + "," + " mermasMP = " + mermaTotalMP
					+ "," + " mermasEN = " + mermaTotalEN + "," + " lote = '"
					+ loteOrden + "'," + " horafin = CURRENT_TIMESTAMP(),"
					+ " usuarioResponsable = '" + gprodu.getIdOperario() + "',"
					+ " estadoProceso = 'F'" + " WHERE orden = '"
					+ gprodf.getOrden() + "'";
			// actualizar los saldos a esos registros de entrada
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if (res == 1) {
				// System.out.println("REGISTRO ACTUALIZADO");
				if (proceso.equals("Cribado") || proceso.equals("Desgranado")) {
					// 2. En el RE Padre resta las mermas del saldo
					// System.out.println("Actualizar saldos de RE Mermas");
					Long idEntradaPadre = getIdEntradaPadreDesgranado(gprodf
							.getOrden(), proceso);
					// System.out.println("el id padre es" + idEntradaPadre);
					RegistroEntradaDAO red = new RegistroEntradaDAO();
					red.updateRegistroPadreSaldo(idEntradaPadre, con, gprodu
							.getMermaIngredientes());
				}
				if (proceso.equals("Envasado")) {
					updateRegistrosEntradaEnvasado(gprodf.getOrden());
				}
				resultado = true;
			}
			ps.close();
			rs.close();
			con.close();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	/**
	 * @author Induserco PROCESO ENVASADO 11) Una vez que inserta el registro de
	 * ENVASADO actualiza los saldos
	 * *
	 */
	private Boolean updateRegistrosEntradaEnvasado(String orden)
			throws Exception {
		// System.out.println("11) ENVASADO DAO updateRegistrosEntradaEnvasado");
		String Qry = null;
		String QryUpdate = null;
		Statement stmt;
		try {
			con = bddConexion();
			Qry = " SELECT tmp.orden, tmp.codigoEntrada, tmp.cantidadUsable, tmp.mermaProducto "
					+ " FROM tmp_gp_envasado_detalle tmp WHERE tmp.orden = '"
					+ orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Recuperamos cada RE participante en una orden de envasado
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setProceso(rs.getString("codigoEntrada"));
				gestion.setCantidadProducto(rs.getDouble("cantidadUsable"));
				gestion.setMermaIngredientes(rs.getDouble("mermaProducto"));
				// sumar cantidad y merma para restar al saldo
				Double saldo = gestion.getCantidadProducto()
						+ gestion.getMermaIngredientes();
				// Restar la cant usada de cada RE del proceso
				QryUpdate = "update registroentrada set" + " saldo = saldo -"
						+ saldo + " WHERE codigoEntrada = '"
						+ gestion.getProceso() + "'";
				// System.out.println(QryUpdate);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(QryUpdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return true;
	}

	//@Override
	public Vector<Producto> getAgrupaciones(boolean suficienteStock) throws Exception{
		// Inicializamos el Vector de retorno.
		// System.out.println("ENVASADO DAO getAgrupaciones");
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT DISTINCT p.idProducto, nombre " +
				" FROM producto p, producto_compuesto pc " +
				" WHERE p.idProducto = pc.idProducto " +
				" ORDER BY nombre";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			boolean flag = false;
			if (suficienteStock){
				while (rs.next()) {
					flag = false;
					Producto prod = new Producto();
					Long idProd = rs.getLong("idProducto");
					prod.setItem("false");
					prod.setIdProducto(idProd);
					prod.setNombre(rs.getString("nombre"));
					//Miramos, si para cada uno de los productos asociados a este producto, hay stock
					//En el momento de que para alguno no haya stock, rompemos el bucle
					String consulta = "SELECT DISTINCT p.stock " +
							" FROM producto_compuesto pc, producto p " +
							" WHERE pc.idProducto = " + idProd +
							" AND pc.idCompuestoDe = p.idProducto";
					// System.out.println(consulta);
					PreparedStatement preSta;
					ResultSet resultSet;
					preSta = con.prepareStatement(consulta);
					resultSet = preSta.executeQuery();
					int countAux = 0;
					while (resultSet.next()) {
						countAux++;
						double stock = resultSet.getDouble("stock");
						if (stock > 0){
							flag = true;
							break;
						}
					}
					if (countAux > 0 && flag){
						//Miramos, si para cada uno de los envases asociados al producto hay stock
						String query = "SELECT DISTINCT e.stock, pe.idEnvase " + 
							" FROM producto_envase pe, envase e " +
							" WHERE pe.idProducto='" + idProd + "' " +
							" AND e.idEnvase = pe.idEnvase";
						// System.out.println(query);
						PreparedStatement preSt;
						ResultSet resSet;
						preSt = con.prepareStatement(query);
						resSet = preSt.executeQuery();
						int cuentame = 0;
						flag = false;
						while (resSet.next()) {
							cuentame++;
							double stock = resSet.getDouble("stock");
							if (stock > 0){
								flag = true;
								break;
							}
						}
						if (cuentame == 0)
							flag = false;
						//Si hay ambos, añadimos el producto al vector de resultado
						if (flag){
							resultado.add(prod);
							flag = false;
						}
					}
				}
			}
			else{
				while (rs.next()) {
					// Completamos los datos del proveedor en el registro
					Producto prod = new Producto();
					prod.setIdProducto(rs.getLong("idProducto"));
					prod.setNombre(rs.getString("nombre"));
					// La añadimos al Vector de resultado
					resultado.add(prod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}
	
	//@Override
	public Vector<Producto> getPresentacionProductos(boolean suficienteStock) throws Exception {
		// Inicializamos el Vector de retorno.
		// System.out.println("ENVASADO DAO getPresentacionProductos");
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry = " SELECT idProducto, TRIM(nombre) AS nombre FROM producto ORDER BY TRIM(nombre); ";
			// System.out.println(Qry);
			boolean flag = false;
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (suficienteStock){
				while (rs.next()) {
					flag = true;
					Producto prod = new Producto();
					prod.setItem("true");
					Long idProd = rs.getLong("idProducto");
					prod.setIdProducto(idProd);
					prod.setNombre(rs.getString("nombre"));
					//Miramos, si para cada una de las materias asociadas al producto hay stock
					//En el momento de que para alguna materia no haya stock, rompemos el bucle
					String consulta =
						" SELECT DISTINCT mc.stock, pm.idMateriaPrima " +
						" FROM producto_materiaprima pm, materiaprima_categoria mc " +
						" WHERE pm.idProducto='" + idProd + "' " +
							" AND pm.idMateriaPrima = mc.idMateriaCategoria";
					// System.out.println(consulta);
					PreparedStatement preSta;
					ResultSet resultSet;
					preSta = con.prepareStatement(consulta);
					resultSet = preSta.executeQuery();
					boolean tieneMPoEAN13 = false;
					while (resultSet.next()) {
						tieneMPoEAN13 = true;
						double stock = resultSet.getDouble("stock");
						if (stock <= 0){
							flag = false;
							break;
						}
					}
					preSta.close();
					resultSet.close();
					if (flag){
						consulta =
							" SELECT DISTINCT p.stock, p.idProducto " +
							" FROM producto p, producto_ean13 pe " +
							" WHERE pe.idProducto='" + idProd + "' " +
								" AND pe.idEAN13 = p.idProducto; ";
						// System.out.println(consulta);
						preSta = con.prepareStatement(consulta);
						resultSet = preSta.executeQuery();
						while (resultSet.next()) {
							tieneMPoEAN13 = true;
							double stock = resultSet.getDouble("stock");
							if (stock <= 0){
								flag = false;
								break;
							}
						}
						preSta.close();
						resultSet.close();
						if (flag && tieneMPoEAN13){
							//Miramos, si para cada uno de los envases asociados al producto hay stock
							String query =
								" SELECT DISTINCT e.stock, pe.idEnvase " + 
								" FROM producto_envase pe, envase e " +
								" WHERE pe.idProducto='" + idProd + "' " +
									" AND e.idEnvase = pe.idEnvase";
							// System.out.println(query);
							PreparedStatement preSt;
							ResultSet resSet;
							preSt = con.prepareStatement(query);
							resSet = preSt.executeQuery();
							int cuentame = 0;
							while (resSet.next()) {
								cuentame++;
								double stock = resSet.getDouble("stock");
								if (stock <= 0){
									flag = false;
									break;
								}
							}
							preSt.close();
							resSet.close();
							if (cuentame == 0)
								flag = false;
							//Si hay ambos, añadimos el producto al vector de resultado
							if (flag){
								resultado.add(prod);
								flag = false;
							}
						}
					}
				}
			}
			else{
				while (rs.next()) {
					// Completamos los datos del proveedor en el registro
					Producto prod = new Producto();
					prod.setIdProducto(rs.getLong("idProducto"));
					prod.setNombre(rs.getString("nombre"));
					// La añadimos al Vector de resultado
					resultado.add(prod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}
	
	/**
	 * 12)Consulta> Devuelve los registros envasados de una orden
	 * *
	 * @throws Exception the exception
	 * @author Induserco
	 * @param filtro 0: todos; 1: ultimos 20; 2: ultimos 50; 3: ultima semana; 4: ultimo mes; 5: ultimo año; 
	 */
	//@Override
	public Vector<GestionProduccion> getRegistroEnvasados(String orden, long idProducto, int filtro) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = "";
		try {
			con = bddConexion();
			String Qry =
				" SELECT (SELECT nombre FROM producto WHERE idProducto=ge.idProducto)AS nombre, " +
					" ge.idProducto, ge.orden, ge.idEnvasado, ge.idProducto, ge.fecha,ge.horainicio, " +
					" ge.horafin,ge.lote, ge.cantidad, ge.mermasMP, ge.mermasEN, " +
					" ge.usuarioResponsable,ge.estadoproceso,ge.observaciones " +
				" FROM gp_envasado ge " +
				" WHERE ge.habilitado = 'S' ";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND ge.orden = '" + orden + "'";
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
					Qry2 = Qry2 + " AND ge.horainicio BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' ";
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
			Qry2 = Qry2 + " ORDER BY ge.horainicio DESC ";
			Qry = Qry.concat(Qry2).concat(limit);
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				GestionProduccion gestion = new GestionProduccion();
				gestion.setLoteAsignado(rs.getString("lote"));
				String qry2 =
					" SELECT gea.idAgrupacion, p.nombre, gea.elaborado " +
					" FROM gp_envasado ge " +
					" INNER JOIN gp_envasado_agrupacion gea ON ge.orden = gea.ordenEnvasado " +
					" INNER JOIN producto p ON p.idProducto = gea.idAgrupacion" +
					" WHERE ge.habilitado = 'S' " +
						" AND ge.lote = '" + gestion.getLoteAsignado() + "'; ";
				ResultSet rs2 = null;
				PreparedStatement ps2 = null;
				ps2 = con.prepareStatement(qry2);
				rs2 = ps2.executeQuery();
				if (rs2.next()) {
					gestion.setDescProducto(rs2.getString("nombre"));
					gestion.setCantidadProducto(rs2.getDouble("elaborado"));
				}else{
					gestion.setDescProducto(rs.getString("nombre"));
					gestion.setCantidadProducto(rs.getDouble("cantidad"));
				}
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setHoraInicioProceso(rs.getString("horainicio"));
				gestion.setHoraFinProceso(rs.getString("horafin"));
				gestion.setDescIngrediente(rs.getString("nombre"));
				gestion.setMermaIngredientes(rs.getDouble("mermasMP"));
				gestion.setMermaEnvases(rs.getLong("mermasEN"));
				gestion.setIdOperario(rs.getString("usuarioResponsable"));
				gestion.setEstadoProceso(rs.getString("estadoproceso"));
				gestion.setObservaciones(rs.getString("observaciones"));
				gestion.setUsuarioResponsable(rs.getString("usuarioResponsable"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setIdGestion(rs.getLong("idEnvasado"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}
	
	/**
	 * @author Induserco, Andrés
	 * @since Versión 1.2
	 * Obtiene los procesos de envasado que están activos (Los que tienen estado Pendiente, Iniciado ó Pausado.
	 * @param orden Filtra los procesos obtenidos por el orden de envasado
	 * @param fecha Filtra los procesos obtenidos por la fecha de envasado
	 * @param lote Filtra los procesos obtenidos por el lote del producto final
	 * @param idProducto Filtra los procesos obtenidos por el id del producto final
	 * @param estado Filtra los procesos obtenidos por el estado actual de cada proceso
	 * 
	 * @return Vector con los procesos de envasado
	 * 
	 * @throws Exception Si falla la lectura de la base de datos
	 */
	//@Override
	public Vector<GestionProduccion> getProcesosEnvasado(String orden,
			String fecha, String lote, Long idProducto, String estados[], char habilitado) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = " ";
		try {
			con = bddConexion();
			String Qry = " SELECT (SELECT nombre FROM producto WHERE idProducto=ge.idProducto)as nombre, "
					+ " ge.idProducto, ge.orden, ge.fecha,ge.horainicio,ge.horafin,ge.lote, ge.cantidad, ge.idEnvasado, "
					+ " ge.mermasMP, ge.mermasEN,ge.usuarioResponsable,ge.estadoproceso,ge.observaciones, ge.fechaCaducidad "
					+ " FROM gp_envasado ge ";
			boolean WHERE = true;
			if (orden != null && !orden.equals("") && orden.compareTo("0") != 0) {
				if (WHERE){
					Qry2 = Qry2 + " WHERE ge.orden = '" + orden + "'";
					WHERE = false;
				}
				else
					Qry2 = Qry2 + " AND ge.orden = '" + orden + "'";
			}
			if (lote != null && !lote.equals("") && lote.compareTo("0") != 0) {
				if (WHERE){
					Qry2 = Qry2 + " WHERE ge.lote = '" + lote + "'";
					WHERE = false;
				} else
					Qry2 = Qry2 + " AND ge.lote = '" + lote + "'";
			}
			if (habilitado  != ' ') {
				if (WHERE){
					Qry2 = Qry2 + " WHERE ge.habilitado = '" + habilitado + "'";
					WHERE = false;
				} else
					Qry2 = Qry2 + " AND ge.habilitado = '" + habilitado + "'";
			}
			if (idProducto != null && idProducto != 0) {
				if (WHERE){
					Qry2 = Qry2 + " WHERE ge.idProducto = " + idProducto;
					WHERE = false;
				} else
					Qry2 = Qry2 + " AND ge.idProducto = " + idProducto;
			}
			if (fecha != null && !fecha.equals("")) {
				if (WHERE){
					Qry2 = Qry2 + " WHERE ge.fecha = '" + fecha + "'";
					WHERE = false;
				} else
					Qry2 = Qry2 + " AND ge.fecha = '" + fecha + "'";
			}
			if (estados != null && estados.length > 0){
				if (WHERE){
					Qry2 = Qry2 + " WHERE ge.estadoproceso = '" + estados[0] + "' ";
					for (int i = 1; i < estados.length; i++){
						Qry2 = Qry2 + " AND ge.estadoproceso = '" + estados[i] + "' ";
					}
					WHERE = false;
				} else{
					for (int i = 0; i < estados.length; i++){
						Qry2 = Qry2 + " AND ge.estadoproceso = '" + estados[i] + "' ";
					}
				}
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				GestionProduccion gestion = new GestionProduccion();
				gestion.setLoteAsignado(rs.getString("lote"));
				String qry2 =
					" SELECT if (ga.idAgrupacion<=0, ge.cantidad, " +
						" concat(ga.elaborado ,\" (\",ga.elaborado,\"x\", (select cantidad from producto_compuesto pc where pc.idProducto=ga.idAgrupacion LIMIT 1) ,\"=\",ge.cantidad,\")\")) resultado," +
						" (select p.nombre from producto p where p.idProducto=if (ga.idAgrupacion<=0, ge.idProducto, ga.idAgrupacion) LIMIT 1) nombre, ga.elaborado, if (ga.idAgrupacion<=0, ge.idProducto, ga.idAgrupacion) idProducto " +
					" FROM gp_envasado ge " +
						" LEFT JOIN gp_envasado_agrupacion ga ON ge.orden = ga.ordenEnvasado " +
					" WHERE ge.lote='" + gestion.getLoteAsignado() + "'";
				ResultSet rs2 = null;
				PreparedStatement ps2 = null;
				ps2 = con.prepareStatement(qry2);
				rs2 = ps2.executeQuery();
				if (rs2.next()) {
					gestion.setDescProducto(rs2.getString("nombre"));
					gestion.setDescIngrediente(rs2.getString("nombre"));
					gestion.setCantidadProducto(rs2.getDouble("elaborado"));
					gestion.setIdProducto(rs2.getLong("idProducto"));
				}else{
					gestion.setDescProducto(rs.getString("nombre"));
					gestion.setDescIngrediente(rs.getString("nombre"));
					gestion.setCantidadProducto(rs.getDouble("cantidad"));
					gestion.setIdProducto(rs.getLong("idProducto"));
				}
				gestion.setResultado(rs2.getString("resultado"));
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setFechaCaducidad(rs.getString("fechaCaducidad"));
				gestion.setHoraInicioProceso(rs.getString("horainicio"));
				gestion.setHoraFinProceso(rs.getString("horafin"));
				gestion.setMermaIngredientes(rs.getDouble("mermasMP"));
				gestion.setMermaEnvases(rs.getLong("mermasEN"));
				gestion.setIdOperario(rs.getString("usuarioResponsable"));
				gestion.setEstadoProceso(rs.getString("estadoproceso"));
				gestion.setObservaciones(rs.getString("observaciones"));
				gestion.setUsuarioResponsable(rs.getString("usuarioResponsable"));
				gestion.setIdGestion(rs.getLong("idEnvasado"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public GestionProduccion getMaestroEN(String orden) throws Exception {
		// Inicializamos el Vector de retorno.
		GestionProduccion produccion = new GestionProduccion();
		try {
			con = bddConexion();
			List<LineaProducto> lineasMP = new ArrayList<LineaProducto>(),
				lineasEN = new ArrayList<LineaProducto>();
			// System.out.println("DAO getRegistroEntradas");
			String Qry =
				" SELECT (SELECT nombre FROM producto WHERE idProducto = ge.idProducto) as nombre,"
				+ " (SELECT descripcion FROM producto WHERE idProducto = ge.idProducto) as descripcion, ge.lote, "
				+ " ge.fecha, ge.cantidad, ge.orden, ge.usuarioResponsable, ge.estadoproceso, ge.observaciones, ge.elaborado "
				+ " FROM gp_envasado ge  "
				+ " WHERE ge.habilitado='S' "
				+ " AND orden= '" + orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				produccion.setOrden(rs.getString("orden"));
				produccion.setLoteAsignado(rs.getString("lote"));
				Qry = "SELECT DISTINCT re.lote, gd.orden, gd.cantidadTeorica, gd.cantidadReal," +
						" gd.mermaProducto, gd.codigoEntrada,  CONCAT(m.nombre, '-', c.nombre) AS descripcion," +
						" (" +
							" SELECT en.nombre " +
							" FROM ordenentrada oe, registroentrada re, entidad en" +
							" WHERE oe.codigoOrden = re.codigoOrden " +
								" AND en.idUsuario = oe.idProveedor " +
								" AND re.codigoEntrada = gd.codigoEntrada" +
						") AS proveedor " +
						" FROM (gp_envasado_detalle gd, materiaprima m, " +
							" materiaprima_categoria mc, categoria c, registroentrada re) " +
						" WHERE gd.orden='" + orden + "' " +
							" AND gd.idTipoRegistro = 'M' " +
							" AND re.codigoEntrada = gd.codigoEntrada " +
							" AND re.idProducto = m.idProducto " +
							" AND re.idCategoria = mc.idCategoria " +
							" AND mc.idCategoria = c.idCategoria " +
							" AND mc.idMateriaPrima = m.idProducto";
				// System.out.println(Qry);
				PreparedStatement ps1 = con.prepareStatement(Qry);
				ResultSet rs1 = ps1.executeQuery();
				while (rs1.next()){
					LineaProducto l = new LineaProducto();
					l.setTeorica(rs1.getDouble("cantidadTeorica"));
					l.setReal(rs1.getDouble("cantidadReal"));
					l.setMermas(rs1.getDouble("mermaProducto"));
					l.setEntrada(rs1.getString("codigoEntrada"));
					l.setNombre(rs1.getString("descripcion"));
					l.setProveedor(rs1.getString("proveedor"));
					l.setLote(rs1.getString("lote"));
					lineasMP.add(l);
				}
				produccion.setLineasMP(lineasMP);
				Qry = "SELECT DISTINCT re.lote, gd.orden, gd.cantidadTeorica, gd.cantidadReal," +
						" gd.mermaProducto, gd.codigoEntrada, e.nombre as descripcion," +
						" (" +
							" SELECT en.nombre " +
							" FROM ordenentrada oe, registroentrada re, entidad en" +
							" WHERE oe.codigoOrden = re.codigoOrden " +
								" AND en.idUsuario = oe.idProveedor " +
								" AND re.codigoEntrada = gd.codigoEntrada" +
						") AS proveedor " +
						" FROM (gp_envasado_detalle gd, envase e, registroentrada re) " +
						" WHERE gd.orden='" + orden + "' " +
							" AND gd.idTipoRegistro = 'E' " +
							" AND re.codigoEntrada = gd.codigoEntrada " +
							" AND re.idEnvase = e.idEnvase";
				// System.out.println(Qry);
				ps1 = con.prepareStatement(Qry);
				rs1 = ps1.executeQuery();
				while (rs1.next()){
					LineaProducto l = new LineaProducto();
					l.setTeorica(rs1.getDouble("cantidadTeorica"));
					l.setReal(rs1.getDouble("cantidadReal"));
					l.setMermas(rs1.getDouble("mermaProducto"));
					l.setEntrada(rs1.getString("codigoEntrada"));
					l.setNombre(rs1.getString("descripcion"));
					l.setProveedor(rs1.getString("proveedor"));
					l.setLote(rs1.getString("lote"));
					lineasEN.add(l);
				}
				produccion.setLineasEN(lineasEN);
				produccion.setDescProducto(rs.getString("nombre"));
				String aux = rs.getString("fecha");
				String[] frag = aux.split("-");
				String fecha = frag[2] + "/" + frag[1] + "/" + frag[0];
				produccion.setFecha(fecha);
				produccion.setCantidadProducto(rs.getDouble("cantidad"));
				produccion.setIdOperario(rs.getString("usuarioResponsable"));
				produccion.setEstadoProceso(rs.getString("estadoproceso"));
				produccion.setObservaciones(rs.getString("observaciones"));
				produccion.setDescProducto(rs.getString("descripcion"));
				produccion.setCantidadUnidadesEnvases(rs.getLong("elaborado"));
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
		return produccion;
	}

	//@Override
	public Vector<GestionProduccion> getDetalleEN(String orden)
			throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> detalle = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			// System.out.println("DAO getRegistroEntradas");
			String Qry = "SELECT gd.orden,gd.descripcion, gd.cantidadUsable,gd.mermaProducto,gd.codigoEntrada, "
					+ " ( SELECT en.nombre "
					+ "    FROM ordenentrada oe, registroentrada re, entidad en"
					+ "    WHERE oe.codigoOrden=re.codigoOrden"
					+ "    AND en.idUsuario=oe.idProveedor "
					+ "    AND re.codigoEntrada=gd.codigoEntrada"
					+ "    ) as proveedor"
					+ " FROM tmp_gp_envasado_detalle gd  "
					+ " WHERE orden= '"
					+ orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				GestionProduccion entrada = new GestionProduccion();
				entrada.setOrden(rs.getString("orden"));
				entrada.setDescProducto(rs.getString("descripcion"));
				entrada.setCantidadProducto(rs.getDouble("cantidadUsable"));
				entrada.setMermaIngredientes(rs.getDouble("mermaProducto"));
				entrada.setLoteAsignado(rs.getString("codigoEntrada"));
				detalle.add(entrada);
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
		return detalle;
	}

	//@Override
	public Vector<GestionProduccion> getMermasProduccion(String fecha, Long idProducto)	throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry =
				"SELECT g.orden,g.fecha,g.proceso,g.habilitado,g.idProducto,p.nombre as descProducto,"
					+ " g.cantidadprod,g.idIngredientes,p.nombre as descIngredientes,g.cantidadIngredientesini,"
					+ " g.cantidadIngredientesfin,g.mermasIngredientes,g.loteIngredientes,g.idEnvase,e.modelo as descEnvase,g.cantidadEnvasesini,"
					+ " g.cantidadEnvasesfin,g.mermasEnvases,g.loteenvases, g.usuarioResponsable, g.notasincidencias, g.notasinstrucciones, g.observaciones "
				+ " FROM gestionproduccion g " +
						" INNER JOIN envase e ON g.idEnvase = e.idEnvase " +
						" INNER JOIN producto p ON g.idProducto = p.idProducto ";
			Qry2 = " ";
			if (idProducto != null && idProducto != 0) {
				Qry2 = Qry2 + " WHERE g.idProducto = " + idProducto;
			}
			// System.out.println("fecha recibida para busqueda " + fecha);
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setProceso(rs.getString("proceso"));
				gestion.setHabilitado(rs.getString("habilitado"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setCantidadProducto(rs.getDouble("cantidadprod"));
				gestion.setDescIngrediente(rs.getString("descIngredientes"));
				gestion.setMermaIngredientes(rs.getDouble("mermasIngredientes"));
				gestion.setDescEnvase(rs.getString("descEnvase"));
				gestion.setMermaEnvases(rs.getLong("mermasEnvases"));
				gestion.setIdOperario(rs.getString("usuarioResponsable"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Boolean updateRegistroEnvasado(GestionProduccion gprodf,
			GestionProduccion gprodu) throws Exception {
		// System.out.println("DAO updateRegistroEnvasado");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			Long mermaIngredientes;
			Long mermaEnvases;
			mermaIngredientes = gprodu.getCantidadIngredientesFin()
					- gprodu.getCantidadIngredientesIni();
			mermaEnvases = gprodu.getCantidadEnvasesFin()
					- gprodu.getCantidadEnvasesIni();
			//String strfechac = getDate(gprodu.getFechaCaducidad());
			String strfechac = gprodu.getFechaCaducidad();
			// SQL de update
			String updateString = "update gestionproduccion set" + " hora = '"
					+ gprodu.getHoraProceso() + "'," + " fechaCaducidad = '"
					+ strfechac + "'," + " loteasignado = '"
					+ gprodu.getLoteAsignado() + "'," + " horainicio = '"
					+ gprodu.getHoraInicioProceso() + "'," + " horafin = '"
					+ gprodu.getHoraFinProceso() + "'," + " idProducto = "
					+ gprodu.getIdProducto() + "," + " cantidadprod = "
					+ gprodu.getCantidadProducto() + "," + " idIngredientes = "
					+ gprodu.getIdIngredientes() + ","
					+ " cantidadIngredientesini = "
					+ gprodu.getCantidadIngredientesIni() + ","
					+ " cantidadIngredientesfin = "
					+ gprodu.getCantidadIngredientesFin() + ","
					+ " mermasIngredientes = " + mermaIngredientes + ","
					+ " loteIngredientes = '" + gprodu.getLoteIngredientes()
					+ "'," + " idEnvase = " + gprodu.getIdEnvase() + ","
					+ " cantidadEnvasesini = " + gprodu.getCantidadEnvasesIni()
					+ "," + " cantidadEnvasesfin = "
					+ gprodu.getCantidadEnvasesFin() + ","
					+ " mermasEnvases = " + mermaEnvases + ","
					+ " loteEnvases = '" + gprodu.getLoteEnvases() + "',"
					+ " usuarioResponsable = '" + gprodu.getIdOperario() + "',"
					+ " notasincidencias = '" + gprodu.getNotasIncidencias()
					+ "'," + " notasinstrucciones = '"
					+ gprodu.getNotasInstrucciones() + "',"
					+ " observaciones = '" + gprodu.getObservaciones() + "'"
					+ " WHERE orden = '" + gprodf.getOrden() + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if (res == 1) {
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
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Boolean deleteRegistroEnvasado(GestionProduccion gprodf,
			GestionProduccion gprodd) throws Exception {
		// System.out.println("DAO deleteRegistroEnvasado");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			// SQL de update
			String updateString = "update gestionproduccion set"
					+ " habilitado = '" + "N" + "'" + " WHERE orden = '"
					+ gprodf.getOrden() + "'";
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if (res == 1) {
				// System.out.println("REGISTRO ELIMINADO");
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
	 * Método Auxiliar que devuelve los registros de nombres de productos de materia prima.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	//@Override
	public Vector<Producto> getProductos() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry = "SELECT idProducto,nombre FROM materiaprima";
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
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Método Auxiliar que devuelve los registros de nombres de envases.
	 *
	 * @return the envases
	 * @throws Exception the exception
	 */
	//@Override
	public Vector<Envase> getEnvases() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Envase> resultado = new Vector<Envase>();
		try {
			con = bddConexion();
			String Qry = "SELECT idEnvase,nombre FROM envase";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				Envase en = new Envase();
				en.setIdEnvase(rs.getLong("idEnvase"));
				en.setNombre(rs.getString("nombre"));
				// La añadimos al Vector de resultado
				resultado.add(en);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/**
	 * Método Auxiliar que devuelve los registros de los operarios.
	 *
	 * @return the operarios
	 * @throws Exception the exception
	 */
	//@Override
	public Vector<Entidad> getOperarios() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Entidad> resultado = new Vector<Entidad>();
		try {
			con = bddConexion();
			String Qry = "SELECT idUsuario,nombre FROM entidad WHERE idRol = 3";
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
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
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
		String fecharetorno = fecha.get(java.util.Calendar.DATE) + "/" + mes
				+ "/" + fecha.get(java.util.Calendar.YEAR);
		return fecharetorno;
	}

	/**
	 * Método Auxiliar que la hora de inicio de cualquier proceso.
	 *
	 * @param orden the orden
	 * @return the hora inicio proceso
	 * @throws Exception the exception
	 */
	//@Override
	public String getHoraInicioProceso(String orden) throws Exception {
		String horaInicioProceso = null;
		try {
			con = bddConexion();
			String Qry = "SELECT horaInicio FROM gestionproduccion WHERE orden='"
					+ orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				horaInicioProceso = (rs.getString("horaInicio"));
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
		// Retorna la hora de inicio del proceso
		return horaInicioProceso;
	}

	//@Override
	public Vector<GestionProduccion> getRegistroCribados(String orden,
			String fecha, Long idProductoCribado) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry = "SELECT g.idGestionProduccion,g.idProducto,g.proceso,g.orden,g.fecha," +
					"g.horainicio,g.habilitado,g.usuarioResponsable,"
					+ "(SELECT sum(cantidadUsable) as cantidad FROM gp_cribado WHERE orden=g.orden) as cantidad, g.observaciones "
					+ " FROM gestionproduccion g ";
			Qry2 = " WHERE g.proceso ='Cribado'";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND g.orden = '" + orden + "'";
			}
			if (fecha != null && !fecha.equals("")) {
				Qry2 = Qry2 + " AND g.fecha = '" + fecha + "'";
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setProceso(rs.getString("proceso"));
				gestion.setHabilitado(rs.getString("habilitado"));
				gestion.setHoraInicioProceso(rs.getString("horainicio"));
				gestion.setCantidadProducto(rs.getDouble("cantidad"));
				//gestion.setHoraFinProceso(rs.getString("horafin"));
				gestion.setIdOperario(rs.getString("usuarioResponsable"));
				gestion.setObservaciones(rs.getString("observaciones"));
				long idProducto = rs.getLong("idProducto");
				String Qry3 = "SELECT m.nombre as nombreProducto, c.nombre as nombreCategoria "
					+ " FROM materiaprima_categoria mc, materiaprima m, categoria c "
					+ " WHERE mc.idMateriaPrima = m.idProducto AND mc.idCategoria = c.idCategoria " +
							" AND mc.idMateriaCategoria = " + idProducto;
				// System.out.println(Qry3);
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = con.prepareStatement(Qry3);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					gestion.setDescProducto(rs2.getString("nombreProducto") + " - " + rs2.getString("nombreCategoria"));
				}
				// La añadimos al Vector de resultado
				resultado.add(gestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<GestionProduccion> getRegistroDesgranados(String orden,
			String fecha, Long idProductoDesgranado) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry = "SELECT g.idGestionProduccion,g.idProducto,g.proceso,g.orden,g.fecha," +
					"g.horainicio,g.habilitado,g.usuarioResponsable,"
					+ " (SELECT SUM(cantidadUsable) AS cantidad FROM gp_desgranado WHERE orden=g.orden) AS cantidad, "
					//+ " (SELECT destrio FROM gp_desgranado WHERE orden=g.orden) AS destrio ,"
					+ " g.observaciones "
					+ " FROM gestionproduccion g ";
			Qry2 = " WHERE g.proceso ='Desgranado'";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND g.orden = '" + orden + "'";
			}
			if (fecha != null && !fecha.equals("")) {
				Qry2 = Qry2 + " AND g.fecha = '" + fecha + "'";
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setProceso(rs.getString("proceso"));
				gestion.setHabilitado(rs.getString("habilitado"));
				//gestion.setDestrio(rs.getDouble("destrio"));
				gestion.setHoraInicioProceso(rs.getString("horainicio"));
				gestion.setCantidadProducto(rs.getDouble("cantidad"));
				//gestion.setHoraFinProceso(rs.getString("horafin"));
				gestion.setIdOperario(rs.getString("usuarioResponsable"));
				gestion.setObservaciones(rs.getString("observaciones"));
				long idProducto = rs.getLong("idProducto");
				String Qry3 = "SELECT m.nombre as nombreProducto, c.nombre as nombreCategoria "
					+ " FROM materiaprima_categoria mc, materiaprima m, categoria c "
					+ " WHERE mc.idMateriaPrima = m.idProducto AND mc.idCategoria = c.idCategoria " +
							" AND mc.idMateriaCategoria = " + idProducto;
				// System.out.println(Qry3);
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = con.prepareStatement(Qry3);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					gestion.setDescProducto(rs2.getString("nombreProducto") + " - " + rs2.getString("nombreCategoria"));
				}
				// La añadimos al Vector de resultado
				resultado.add(gestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<GestionProduccion> getRegistroFumigados(String orden,
			String fecha) throws Exception {
		// Parámetros de búsqueda: Fecha, Orden, Proceso:Fumigado
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry = "SELECT g.orden,g.fecha,g.horainicio,g.horafin,"
					+ " g.usuarioResponsable,g.notasincidencias,"
					+ " f.codigoEntrada,f.cantidadusable,f.mermaProducto "
					+ " FROM gestionproduccion g, gp_fumigado f"
					+ " WHERE g.orden=f.orden " + " AND g.habilitado = 'S' ";
			Qry2 = " AND g.proceso = 'Fumigado'";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND g.orden = '" + orden + "'";
			}
			if (fecha != null && !fecha.equals("")) {
				Qry2 = Qry2 + " AND g.fecha = '" + fecha + "'";
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proceso Fumigado
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setHoraInicioProceso(rs.getString("horainicio"));
				gestion.setHoraFinProceso(rs.getString("horafin"));
				gestion.setIdOperario(rs.getString("usuarioResponsable"));
				gestion.setNotasIncidencias(rs.getString("notasincidencias"));
				gestion.setCantidadProducto(rs.getDouble("cantidadUsable"));
				gestion.setMermaIngredientes(rs.getDouble("mermaProducto"));
				// agrega los codigo de Entrada relacionados con el proceso
				RegistroEntrada re = new RegistroEntrada();
				re.setCodigoEntrada(rs.getString("codigoEntrada"));
				List<RegistroEntrada> res = new ArrayList<RegistroEntrada>();
				res.add(re);
				gestion.setRegEntradas(res);
				// La añadimos al Vector de resultado
				resultado.add(gestion);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	/* Devuelve todos los RE para que Congelarlos */
	//@Override
	public Vector<GestionProduccion> getRegistroCongelados(String orden, String fecha, Long idProducto) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry = "SELECT g.idGestionProduccion,g.proceso,g.orden,g.fecha,g.horainicio,"
					+ " g.habilitado,g.usuarioResponsable,"
					+ " (SELECT sum(cantidadUsable) as cantidad FROM gp_congelado WHERE orden=g.orden) as cantidad, g.observaciones   "
					+ " FROM gestionproduccion g  ";
			Qry2 = " WHERE g.proceso = 'Congelado'";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND g.orden = '" + orden + "'";
			}
			if (fecha != null && !fecha.equals("")) {
				Qry2 = Qry2 + " AND g.fecha = '" + fecha + "'";
			}			
			if (idProducto != null && idProducto != 0) {
				Qry2 = Qry2 + " AND g.idProducto = " + idProducto;
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setProceso(rs.getString("proceso"));
				gestion.setHabilitado(rs.getString("habilitado"));
				gestion.setHoraInicioProceso(rs.getString("horainicio"));
				gestion.setCantidadProducto(rs.getDouble("cantidad"));				
				//gestion.setHoraFinProceso(rs.getString("horafin"));
				gestion.setIdOperario(rs.getString("usuarioResponsable"));
				gestion.setObservaciones(rs.getString("observaciones"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<GestionProduccion> getDetallesRegistroProceso(String tipoProceso, String proceso) throws Exception{
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			String consulta = "";
			if (tipoProceso.compareTo("Seleccion") == 0)
				consulta =
					" SELECT re.codigoEntrada, gs.cantidadUsable as cantidad,gs.mermaProducto as merma, re.idCategoria " +
					" FROM gp_seleccion gs, registroentrada re " +
					" WHERE gs.orden = '" + proceso + "' " +
						" AND re.codigoEntrada = gs.codigoEntrada ";
			else
				if (tipoProceso.compareTo("Cribado") == 0)
					consulta =
						" SELECT re.codigoEntrada, gs.cantidadUsable as cantidad,gs.mermaProducto as merma, re.idCategoria " +
						" FROM gp_cribado gs, registroentrada re " +
						" WHERE gs.orden = '" + proceso + "' " +
							" AND re.codigoEntrada = gs.codigoEntrada ";
				else
					if (tipoProceso.compareTo("Desgranado") == 0)
						consulta =
							/*" SELECT re.codigoEntrada, gs.cantidadUsable as cantidad,gs.mermaProducto as merma, " +
								" CONCAT(m.nombre,' - ',c.nombre) AS descripcion, re.idCategoria " +
							" FROM gp_desgranado gs, registroentrada re, materiaprima m, materiaprima_categoria mc, categoria c " +
							" WHERE gs.orden = '" + proceso + "' " +
								" AND re.codigoEntrada = gs.codigoEntrada " +
								" AND mc.idMateriaPrima = m.idProducto " +
								" AND mc.idCategoria = c.idCategoria " +
								" AND re.idProducto = mc.idMateriaCategoria";*/
							" SELECT re.codigoEntrada, gs.cantidadUsable as cantidad,gs.mermaProducto as merma,  CONCAT(m.nombre,' - ',c.nombre) AS descripcion, re.idCategoria " +
							" FROM gp_desgranado gs " +
								" INNER JOIN registroentrada re ON re.codigoEntrada = gs.codigoEntrada " +
								" LEFT JOIN materiaprima_categoria mc  ON re.idProducto = mc.idMateriaCategoria " +
								" LEFT JOIN materiaprima m ON mc.idMateriaPrima = m.idProducto " +
								" LEFT JOIN categoria c ON mc.idCategoria = c.idCategoria" +
							" WHERE gs.orden = '" + proceso + "'; ";
					else
						if (tipoProceso.compareTo("Congelado") == 0)
							consulta =
								" SELECT DISTINCT gcu.codigoEntrada, gc.cantidadUsable as cantidad, " +
									" gc.mermaProducto as merma, re.idCategoria " +
									" FROM gp_congelado gc, registroentrada re, gp_congelado_ubicacion gcu " +
									" WHERE gc.orden = '" + proceso + "' AND re.codigoEntrada = gc.codigoEntrada " +
											" AND gcu.orden = gc.orden";
						else
							if (tipoProceso.compareTo("Fumigado") == 0)
								consulta = "SELECT DISTINCT gfu.codigoEntrada, gf.cantidadUsable as cantidad, gf.mermaProducto as merma," +
										"re.idCategoria " +
								" FROM gp_fumigado gf, registroentrada re, gp_fumigado_ubicacion gfu " +
								" WHERE gfu.orden = '" + proceso + "' " +
									"AND re.codigoEntrada = gf.codigoEntrada AND gfu.orden = gf.orden";
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rs = ps.executeQuery();
				while (rs.next()) {
					String orden = rs.getString("codigoEntrada");
					if (tipoProceso.compareTo("Congelado") == 0)
						consulta = "SELECT DISTINCT u.registro, gcu.cantidad ,descripcion, uh.idHueco, u.idUbicacion " +
								" FROM ubicacion u, ubicacion_hueco uh, gp_congelado_ubicacion gcu " +
								" WHERE u.idHueco = uh.idHueco " +
								" AND gcu.idHueco = u.idHueco " +
								" AND gcu.orden = '" + proceso + "' AND gcu.codigoEntrada = u.registro";
					else
						if (tipoProceso.compareTo("Fumigado") == 0)
							consulta = "SELECT DISTINCT u.registro, gcu.cantidad, descripcion, uh.idHueco, u.idUbicacion " +
								" FROM ubicacion u, ubicacion_hueco uh, gp_fumigado_ubicacion gcu " +
								" WHERE u.idHueco = uh.idHueco " +
								" AND gcu.idHueco = u.idHueco " +
								" AND gcu.orden = '" + proceso + "' AND gcu.codigoEntrada = u.registro";					
						else
							consulta = "SELECT uh.descripcion, u.cantidad, uh.idHueco, u.idUbicacion " +
									" FROM ubicacion u, ubicacion_hueco uh " +
									" WHERE u.registro = '" + orden + "' AND u.idHueco = uh.idHueco";
					// System.out.println(consulta);
					PreparedStatement ps2 = null;
					ResultSet rs2 = null;
					ps2 = con.prepareStatement(consulta);
					rs2 = ps2.executeQuery();
					boolean flag = false;
					while (rs2.next()) {
						flag = true;
						// Completamos los datos del proveedor en el registro
						GestionProduccion gestion = new GestionProduccion();
						gestion.setOrden(rs.getString("codigoEntrada"));
						gestion.setIdHueco(rs2.getLong("idHueco"));
						gestion.setCantidadProducto(rs.getDouble("cantidad") - rs.getDouble("merma"));
						gestion.setIdCategoria(rs.getLong("idCategoria"));
						gestion.setIdUbicacion(rs2.getLong("idUbicacion"));
						gestion.setCantidadUbicada(rs2.getDouble("cantidad"));
						gestion.setNombreHueco(rs2.getString("descripcion"));
						// La añadimos al Vector de resultado
						resultado.add(gestion);
					}
					if (!flag){
						GestionProduccion gestion = new GestionProduccion();
						gestion.setOrden(rs.getString("codigoEntrada"));
						gestion.setCantidadProducto(rs.getDouble("cantidad"));
						gestion.setIdCategoria(rs.getLong("idCategoria"));
						resultado.add(gestion);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<GestionProduccion> getRegistroSeleccion(String orden,
			String fecha) throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry = "SELECT g.idGestionProduccion,g.idProducto,g.proceso,g.orden,g.fecha," +
					"g.horainicio,g.habilitado,g.usuarioResponsable,"
					+ "(SELECT sum(cantidadUsable) as cantidad FROM gp_seleccion WHERE orden=g.orden) as cantidad, g.observaciones "
					+ " FROM gestionproduccion g ";
			Qry2 = " WHERE g.proceso ='SeleccionM'";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND g.orden = '" + orden + "'";
			}
			if (fecha != null && !fecha.equals("")) {
				Qry2 = Qry2 + " AND g.fecha = '" + fecha + "'";
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setProceso(rs.getString("proceso"));
				gestion.setHabilitado(rs.getString("habilitado"));
				gestion.setHoraInicioProceso(rs.getString("horainicio"));
				gestion.setCantidadProducto(rs.getDouble("cantidad"));
				//gestion.setHoraFinProceso(rs.getString("horafin"));
				gestion.setIdOperario(rs.getString("usuarioResponsable"));
				gestion.setObservaciones(rs.getString("observaciones"));
				long idProducto = rs.getLong("idProducto");
				String Qry3 = "SELECT m.nombre as nombreProducto, c.nombre as nombreCategoria "
					+ " FROM materiaprima_categoria mc, materiaprima m, categoria c "
					+ " WHERE mc.idMateriaPrima = m.idProducto AND mc.idCategoria = c.idCategoria " +
							" AND mc.idMateriaCategoria = " + idProducto;
				// System.out.println(Qry3);
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = con.prepareStatement(Qry3);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					gestion.setDescProducto(rs2.getString("nombreProducto") + " - " + rs2.getString("nombreCategoria"));
				}
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	 * Este método sirve registrar la orden del proceso esto es para todos los
	 * procesos: Cribado, Desgranado, Envasado, Congelado, Fumigado, Seleccion.
	 *
	 * @param gprod the gprod
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	//@Override
	public Boolean addRegistroProceso(GestionProduccion gprod, String proceso)
			throws Exception {
		// System.out.println("DAO addRegistroProceso : " + proceso);
		Statement stmt;
		String codigoOrden = null;
		String stringFecha = null;
		String stringanno = null;
		String stringmes = null;
		Boolean resultado = false;
		Date fecha = null;
		int res = 0;
		try {
			con = bddConexion();
			// 1. Obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idGestionProduccion) as idMaxGestionProduccion FROM gestionproduccion");
			rs = ps.executeQuery();
			long idMaxGestionProduccion = 0;
			while (rs.next()) {
				GestionProduccion filtradoMaximo = new GestionProduccion();
				filtradoMaximo.setIdGestion(rs
						.getLong("idMaxGestionProduccion"));
				idMaxGestionProduccion = filtradoMaximo.getIdGestion() + 1;
			}
			// System.out.println("el id de gestion es... " + idMaxGestionProduccion);
			// 2. Obtener fecha sistema
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while (rs.next()) {
				fecha = rs.getDate("fecha");
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(fecha);
			// 3. Generar Código Orden :formatear fecha sistema para codigo
			// orden
			ps = con.prepareStatement(" SELECT YEAR(CURDATE()) as anno ");
			rs = ps.executeQuery();
			while (rs.next()) {
				stringanno = rs.getString("anno");
			}
			int mes1 = cal.get(Calendar.MONTH) + 1;
			if (mes1 <= 9) {
				stringmes = "0" + mes1;
			}
			else {
				stringmes = String.valueOf(mes1);
			}
			stringFecha = stringanno + stringmes + cal.get(Calendar.DAY_OF_MONTH);
			codigoOrden = "ES" + stringFecha + "0" + idMaxGestionProduccion;
			gprod.setOrden(codigoOrden);
			// 4. Insertar el proceso en gestionproduccion
			// Seleccion, Cribado y Desgranado son procesos que parten de un
			// solo RE y hay que guardar esta referencia
			String insertString = "";
			if (proceso.equals("SeleccionA") || proceso.equals("SeleccionM")) {
				RegistroEntrada re = new RegistroEntrada();
				List<RegistroEntrada> regEntradas = gprod.getRegEntradas();
				Iterator it = regEntradas.iterator();
				while (it.hasNext()) {
					re = (RegistroEntrada) it.next();
				}
				insertString = "INSERT INTO gestionproduccion(idGestionProduccion,idProducto,proceso," +
						" orden,codigoEntrada,fecha,cantidadprod,horainicio, usuarioResponsable, notasincidencias) VALUES("
						+ idMaxGestionProduccion
						+ ","
						+ gprod.getIdProducto()
						+ ",'"
						+ proceso
						+ "','"
						+ gprod.getOrden()
						+ "','"
						+ re.getCodigoEntrada()
						+ "','"
						+ fecha
						+ "',"
						+ gprod.getCantidadProducto()
						+ ",CURRENT_TIMESTAMP(),'" + gprod.getIdOperario() + "','" + gprod.getNotasIncidencias() + "')";
			} else{
				if (proceso.equals("Cribado")
					|| proceso.equals("Desgranado")) {
					RegistroEntrada re = new RegistroEntrada();
					List<RegistroEntrada> regEntradas = gprod.getRegEntradas();
					Iterator it = regEntradas.iterator();
					while (it.hasNext()) {
						re = (RegistroEntrada) it.next();
					}
					insertString = "INSERT INTO gestionproduccion(idGestionProduccion,idProducto,proceso,orden,codigoEntrada," +
							"fecha,cantidadprod,horainicio,usuarioResponsable,notasincidencias) VALUES("
							+ idMaxGestionProduccion
							+ ","
							+ gprod.getIdProducto()
							+ ",'"
							+ proceso
							+ "','"
							+ gprod.getOrden()
							+ "','"
							+ re.getCodigoEntrada()
							+ "','"
							+ fecha
							+ "',"
							+ gprod.getCantidadProducto()
							+ ",CURRENT_TIMESTAMP(),'" + gprod.getIdOperario() + "','" + gprod.getNotasIncidencias() + "')";
	
				} else {
					insertString = "INSERT INTO gestionproduccion(idGestionProduccion,proceso,orden,fecha,horainicio) VALUES("
							+ idMaxGestionProduccion
							+ ",'"
							+ proceso
							+ "','"
							+ gprod.getOrden()
							+ "','"
							+ fecha
							+ "',CURRENT_TIMESTAMP())";
				}
			}
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			// 2. Inserta la ubicación del producto del desgrano
			if (proceso.equals("Desgranado"))
				addNuevaUbicacionDesgranado(con, gprod.getOrden(), gprod
						.getIdOperario());
			if (res == 1) {
				// System.out.println("REGISTRO gestionproduccion INSERTADO: " + proceso);
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
	 * Gets the id entrada padre desgranado.
	 *
	 * @param orden the orden
	 * @param proceso the proceso
	 * @return the id entrada padre desgranado
	 * @throws Exception the exception
	 */
	private Long getIdEntradaPadreDesgranado(String orden, String proceso)
			throws Exception {
		Long idEntradaPadre = null;
		return idEntradaPadre;
	}

	public String getCodigoEntradaOrden(String orden, String proceso)
			throws Exception {
		String codigoEntrada = null;
		return codigoEntrada;
	}

	/**
	 * Adds the nueva ubicacion desgranado.
	 *
	 * @param con the con
	 * @param codigoEntrada the codigo entrada
	 * @param responsable the responsable
	 * @throws SQLException the sQL exception
	 */
	private void addNuevaUbicacionDesgranado(Connection con,
			String codigoEntrada, String responsable) throws SQLException {
		// System.out.println("DAO addNuevaUbicacionDesgranado");
		Statement stmt1;
		// obtener el nuevo secuencial
		ps = con.prepareStatement("SELECT max(idRegistroUbicacion) as valorMaxSecuencial FROM registroubicacion");
		rs = ps.executeQuery();
		long valorMaxSecuencial = 0;
		while (rs.next()) {
			GestionProduccion envasadoMaximo = new GestionProduccion();
			envasadoMaximo.setIdGestion(rs.getLong("valorMaxSecuencial"));
			valorMaxSecuencial = envasadoMaximo.getIdGestion() + 1;
		}
		String insertSql = "insert into registroubicacion(idRegistroUbicacion,idUbicacion,codigoEntrada,responsable) values("
				+ valorMaxSecuencial
				+ ",25,'"
				+ codigoEntrada
				+ "','"
				+ responsable + "')";
		// System.out.println(insertSql);
		stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		stmt1.executeUpdate(insertSql);

	}

	// sirve para actualizar los Registro GP de Desgranado, Cribado, Congelado,
	//@Override
	public Boolean updateRegistroProceso(GestionProduccion gprodf,
			GestionProduccion gprodu, String proceso) throws Exception {
		// System.out.println("DAO updateRegistroProceso: " + proceso);
		Statement stmt;
		String tablagp = "";
		Double cantidadTotal = 0.0;
		Boolean resultado = false;
		int res = 0;
		if (proceso.equals("Cribado"))
			tablagp = "gp_cribado";
		else if (proceso.equals("Desgranado"))
			tablagp = "gp_desgranado";
		try {
			con = bddConexion();
			String updateString = "";
			// 1. Actualiza mermas y hora de fin del proceso de
			// gestionproduccion
			if (proceso.equals("Congelado") || proceso.equals("Fumigado"))
				updateString = "update gestionproduccion set"
						+ " horafin = CURRENT_TIMESTAMP(),"
						+ " usuarioResponsable = '" + gprodu.getIdOperario()
						+ "'," + " estadoProceso = 'F'" + " WHERE orden = '"
						+ gprodf.getOrden() + "'";
			else {
				//Obtiene el total de cantidad del proceso
				String Qry = "SELECT sum(cantidadUsable) as cantidadTotal "
					+ "from " + tablagp
					+ " WHERE orden='" + gprodf.getOrden() + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					cantidadTotal = (rs.getDouble("cantidadTotal"));
				}
				// OBTIENE EL COD DE RE ORIGINAL
				Iterator it = gprodf.getRegEntradas().iterator();
				RegistroEntrada regs = new RegistroEntrada();
				while (it.hasNext()) {
					regs = (RegistroEntrada) it.next();
				}
				updateString = "update gestionproduccion set"
						+ " cantidadprod = "
						+ cantidadTotal + ","
						+ " mermasIngredientes = "
						+ gprodu.getMermaIngredientes() + ","						
						+ " horafin = CURRENT_TIMESTAMP(),"
						+ " usuarioResponsable = '" + gprodu.getIdOperario()
						+ "'," + " estadoProceso = '"
						+ gprodu.getEstadoProceso() + "',"
						+ " codigoEntrada = '" + regs.getCodigoEntrada() + "'"
						+ " WHERE orden = '" + gprodf.getOrden() + "'";
			}
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if (res == 1) {
				// System.out.println("REGISTRO ACTUALIZADO");
				if (proceso.equals("Cribado") || proceso.equals("Desgranado")) {
					// 2. En el RE Padre resta las mermas del saldo
					// System.out.println("Actualizar saldos de RE Mermas");
					// System.out.println("la orden es " + gprodf.getOrden());
					Long idEntradaPadre = getIdEntradaPadreDesgranado(gprodf
							.getOrden(), proceso);
					// System.out.println("el id padre es" + idEntradaPadre);
					RegistroEntradaDAO red = new RegistroEntradaDAO();
					red.updateRegistroPadreSaldo(idEntradaPadre, con, gprodu.getMermaIngredientes());
				}
				// Actualiza saldo de los RE correspondientes
				resultado = true;
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	//@Override
	public Vector<GestionProduccion> getRegistroIngredientes() throws Exception {
		// System.out.println("DAO getRegistroIngredientes");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			String Qry = " SELECT re.habilitado,re.codigoEntrada,re.fecha,re.idProducto," +
					"mp.nombre as descProducto,c.nombre as descCategoria, re.saldo "
					+ " FROM registroentrada re, materiaprima mp, categoria c " +
							" WHERE re.idProducto = mp.idProducto AND re.idCategoria = c.idCategoria "
					+ " AND mp.stock > 0 AND re.saldo > 0 AND re.idTipoRegistro = 'M' AND re.habilitado = 'S' ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setHabilitado(rs.getString("habilitado"));
				gestion.setOrden(rs.getString("codigoEntrada"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setDescIngrediente(rs.getString("descCategoria"));
				gestion.setCantidadProducto(rs.getDouble("saldo"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<GestionProduccion> getRegistroIngredientes(List listaingred) throws Exception {
		// System.out.println("INVOCADO DAO getRegistroIngredientes 1");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		if (listaingred.isEmpty()){
			// System.out.println("No hay lista ingredientes");
		}else {
			Iterator itr = listaingred.iterator();
			while (itr.hasNext()) {
				String codigo = (String) itr.next();
				try {
					con = bddConexion();
					String Qry = " SELECT re.codigoEntrada,re.idProducto,mp.nombre as descProducto," +
							"re.saldo,e.nombre as envases,tmp.cantidadEnvases "
							+ " FROM registroentrada re, materiaprima mp, envase e," +
									"tmp_gp_envasado_detalle tmp WHERE re.idProducto = mp.idProducto "
							+ " AND e.idEnvase = tmp.idEnvase AND re.codigoEntrada = tmp.codigoEntradaIngrediente " +
							" AND re.idTipoRegistro = 'M' "
							+ " AND re.codigoEntrada = '" + codigo + "'";
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					while (rs.next()) {
						// Completamos los datos del proveedor en el registro
						GestionProduccion gestion = new GestionProduccion();
						gestion.setOrden(rs.getString("codigoEntrada"));
						gestion.setIdProducto(rs.getLong("idProducto"));
						gestion.setDescProducto(rs.getString("descProducto"));
						gestion.setCantidadProducto(rs.getDouble("saldo"));
						gestion.setDescEnvase(rs.getString("envases"));
						gestion.setCantidadUnidadesEnvases(rs
								.getLong("cantidadEnvases"));
						// La añadimos al Vector de resultado
						resultado.add(gestion);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw (e);
				} finally {
					try {
						ps.close();
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Boolean addTmpRegistroIngredientesEnvases(GestionProduccion gprod,
			GestionProduccion gpro) throws Exception {
		// System.out.println("INVOCADO DAO addTmpRegistroIngredientesEnvases 1");
		// Inicializamos el Vector de retorno.
		GestionProduccion gestionenv = new GestionProduccion();
		int res = 0;
		Statement stmt;
		Boolean resultado = false;
		try {
			con = bddConexion();
			String Qry1 = " SELECT re.idProducto,re.saldo FROM registroentrada re WHERE re.idTipoRegistro = 'M' AND re.habilitado = 'S' "
					+ " AND re.codigoEntrada = '" + gprod.getOrden() + "'";
			// System.out.println(Qry1);
			ps = con.prepareStatement(Qry1);
			rs = ps.executeQuery();
			while (rs.next()) {
				gprod.setIdProducto(rs.getLong("idProducto"));
				gprod.setCantidadProducto(rs.getDouble("saldo"));
			}
			String Qry2 = " SELECT re.idEnvase,re.saldo FROM registroentrada re, envase e "
					+ " WHERE re.idEnvase = e.idEnvase AND re.idTipoRegistro = 'E' "
					+ " AND re.codigoEntrada = '" + gpro.getOrden() + "'";
			// System.out.println(Qry2);
			ps = con.prepareStatement(Qry2);
			rs = ps.executeQuery();
			while (rs.next()) {
				GestionProduccion gestionenvases = new GestionProduccion();
				gestionenvases.setIdEnvase(rs.getLong("idEnvase"));
				gestionenvases.setCantidadUnidadesEnvases(rs.getLong("saldo"));
				gestionenv = gestionenvases;
			}
			String insertString = "insert into tmp_gp_envasado_detalle(codigoEntradaIngrediente,idIngrediente,cantidadIngredientes,codigoEntradaEnvase,idEnvase,cantidadEnvases) values("
					+ "'"
					+ gprod.getOrden()
					+ "',"
					+ gprod.getIdProducto()
					+ ","
					+ gprod.getCantidadProducto()
					+ ",'"
					+ gpro.getOrden()
					+ "',"
					+ gestionenv.getIdEnvase()
					+ ","
					+ gestionenv.getCantidadUnidadesEnvases() + ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (res == 1) {
				// System.out.println("REGISTRO INSERTADO");
				resultado = true;
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
	public Boolean updateRECantProceso(Map mapaCantidades, String orden,
			String proceso) throws Exception {
		// System.out.println("DAO updateRECantProceso con codigo orden");
		// Inicializamos el Vector de retorno.
		int res = 0;
		Statement stmt;
		Boolean resultado = false;
		String tablagp = "";
		try {
			con = bddConexion();
			if (proceso.equals("Congelado"))
				tablagp = "gp_congelado";
			else if (proceso.equals("Fumigado"))
				tablagp = "gp_fumigado";
			for (Iterator it = mapaCantidades.keySet().iterator(); it.hasNext();) {
				String codigoEntradaMapa = (String) it.next();
				// System.out.println("codigo entrada mapa " + codigoEntradaMapa);
				String updateString = "update "
						+ tablagp
						+ " set"
						+ " cantidadUsable = "
						+ Long.parseLong((String) mapaCantidades
								.get(codigoEntradaMapa)) + " WHERE orden = '"
						+ orden + "'" + " AND codigoEntrada = '"
						+ codigoEntradaMapa + "'";
				// System.out.println(updateString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(updateString);
			}
			if (res == 1) {
				// System.out.println("REGISTROS updateRECantProceso ACTUALIZADO");
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

	/* Actualiza mermas de los RE para procesos: Congelado, Fumigado */
	//@Override
	public Boolean updateREMermProceso(Map mapaCantidades, String orden,
			String proceso) throws Exception {
		// System.out.println("DAO updateREMermProceso con codigo orden: " + orden);
		// Inicializamos el Vector de retorno.
		int res = 0;
		Statement stmt;
		Boolean resultado = false;
		String tablagp = "";
		String updateString2 = "";
		String tipoCongelado = "X";
		try {
			con = bddConexion();
			if (proceso.equals("Congelado")){
				tablagp = "gp_congelado";
				tipoCongelado = "C";
			}else
				if (proceso.equals("Fumigado")){
					tablagp = "gp_fumigado";
					tipoCongelado = "F";
				}
			for (Iterator it = mapaCantidades.keySet().iterator(); it.hasNext();) {
				String codigoEntradaMapa = (String) it.next();
				// System.out.println("codigo entrada mapa " + codigoEntradaMapa);
				String updateString = "UPDATE "
						+ tablagp
						+ " SET"
						+ " mermaProducto = "
						+ Double.parseDouble((String) mapaCantidades
								.get(codigoEntradaMapa)) + " WHERE orden = '"
						+ orden + "'" + " AND codigoEntrada = '"
						+ codigoEntradaMapa + "'";
				// System.out.println(updateString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(updateString);
				//Modificamos la cantidad que se almacena despues del fumigado, restándole las mermas
				String nuevaEntrada = new GestionRegistroEntradaHelper().generarCodigoEntrada();
				if (proceso.equals("Congelado")){
					tablagp = "gp_congelado_ubicacion";
				}else{
					if (proceso.equals("Fumigado")){
						tablagp = "gp_fumigado_ubicacion";
					}
				}
				updateString2 = "UPDATE "
					+ tablagp
					+ " SET "
					+ " cantidad = cantidad - "
					+ Double.parseDouble((String) mapaCantidades
							.get(codigoEntradaMapa)) + ", codigoEntrada = '"
							+ nuevaEntrada + "' WHERE orden = '"
					+ orden + "'" + " AND codigoEntrada = '"
					+ codigoEntradaMapa + "'";
				// System.out.println("Actualiza saldo RE por proceso: " + proceso + " update es --> " + updateString2);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(updateString2);
				String Qry2 = " SELECT cantidad FROM " + tablagp +
					" WHERE orden = '" + orden + "'" + " AND codigoEntrada = '" + nuevaEntrada + "'";
				// System.out.println(Qry2);
				ps = con.prepareStatement(Qry2);
				rs = ps.executeQuery();
				double cantidad = 0;
				while (rs.next()) {
					cantidad = rs.getDouble("cantidad");
				}
				if (proceso.equals("Congelado") || proceso.equals("Fumigado")){
					updateString2 =
						" UPDATE registroentrada SET"
							+ " saldo = saldo - " + (Double.parseDouble((String) mapaCantidades.get(codigoEntradaMapa)) + cantidad)
						+ " WHERE codigoEntrada = '" + codigoEntradaMapa + "'";
					// System.out.println("Actualiza saldo RE por proceso: " + proceso + " update es " + updateString2);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(updateString2);
				}
				//Creamos un nuevo registro de entrada para el resultado del fumigado
				//Copiamos el registro de entrada original
				RegistroEntrada re = new RegistroEntrada();
				re = new GestionRegistroEntradaHelper().getRegistroEntrada(codigoEntradaMapa);
				//re.setCodigoOrden(orden);
				//re.setCodigoEntrada(new GestionRegistroEntradaHelper().generarCodigoEntrada());
				re.setCantidad(cantidad);
				//re.setCostoTotal(re.getCantidad() * re.getCostoUnitario());
				re.setCostoTotal((double)0);
				re.setCostoUnitario((double)0);
				re.setSaldo(cantidad);
				re.setNumeroBultos((long)1);
				re.setNumeroPallets((long)1);
				Vector<Incidencia> incidencias = new GestionRegistroEntradaHelper().loadIncidencias(re);
				Vector<EstadoFabas> estados = new Vector<EstadoFabas>();
				Collections.copy(estados,
						new GestionRegistroEntradaHelper().loadEstadoFabas(re));
				re.setIncidencias(incidencias);
				re.setEstados(estados);
				List<String> listInc = new ArrayList<String>(), listEsta = new ArrayList<String>();
				for (int i = 0; i < incidencias.size(); i++){
					listInc.add("" + incidencias.get(i).getIdIncidencia());
				}
				for (int i = 0; i < estados.size(); i++){
					listEsta.add("" + estados.get(i).getIdEstadoFabas());
				}
				new GestionRegistroEntradaHelper().addRegistroEntrada(re, listInc, listEsta);
				//Ubicamos ese registro de entrada nuevo en el mismo hueco
				long idHueco = 0;
				String Qry = "SELECT idHueco FROM " + tablagp + " WHERE orden = '" + orden + "'" + " AND codigoEntrada = '" + nuevaEntrada + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					idHueco = rs.getLong("idHueco");
				}
				String insertamos = "INSERT INTO ubicacion (idHueco, registro, congelado, cantidad, orden) " +
						" VALUES(" + idHueco + ",'" + nuevaEntrada + "','" + tipoCongelado + "'," + cantidad + ",'" + orden + "');";
				// System.out.println(insertamos);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertamos);
			}
			if (res == 1) {
				// System.out.println("REGISTROS updateREMermProceso ACTUALIZADO");
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
	 * @author Induserco toma todos los RE de MP y de Envases usados si se usa
	 * **/
	//@Override
	public Vector<GestionProduccion> getTmpRegistroIngredientesEnvases() throws Exception {
		// System.out.println("DAO getTmpRegistroIngredientesEnvases");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			String Qry = " SELECT re.codigoEntrada,re.idProducto,mp.nombre as descProducto,re.saldo,tmp.cantidadUsableIngredientes,tmp.mermaIngredientes,e.nombre as envases,tmp.cantidadEnvases,tmp.cantidadUsableEnvases,tmp.mermaEnvases "
					+ " FROM registroentrada re, materiaprima mp, envase e, tmp_gp_envasado_detalle tmp WHERE re.idProducto = mp.idProducto "
					+ " AND e.idEnvase = tmp.idEnvase AND re.codigoEntrada = tmp.codigoEntradaIngrediente AND re.idTipoRegistro = 'M' ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("codigoEntrada"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setCantidadProducto(rs.getDouble("saldo"));
				gestion.setCantidadIngredientesFin(rs
						.getLong("cantidadUsableIngredientes"));
				gestion.setMermaIngredientes(rs.getDouble("mermaIngredientes"));
				gestion.setDescEnvase(rs.getString("envases"));
				gestion.setCantidadUnidadesEnvases(rs
						.getLong("cantidadEnvases"));
				gestion.setCantidadEnvasesFin(rs
						.getLong("cantidadUsableEnvases"));
				gestion.setMermaEnvases(rs.getLong("mermaEnvases"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<GestionProduccion> getREProceso(String orden, String proceso) throws Exception {
		// System.out.println("DAO getREProceso");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String tablagp = "";
		try {
			con = bddConexion();
			if (proceso.equals("Congelado"))
				tablagp = "gp_congelado";
			else if (proceso.equals("Fumigado"))
				tablagp = "gp_fumigado";
			// SELECT para extraer todos los RE incluidos en el proceso
			// congelado o fumigado
			String Qry = " SELECT gpre.orden, gpre.codigoEntrada,gpre.descripcion, gpre.cantidadDisponible ,gpre.cantidadUsable"
					+ " FROM "
					+ tablagp
					+ " gpre "
					+ " WHERE gpre.orden = '"
					+ orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("orden"));
				gestion.setProceso(rs.getString("codigoEntrada"));
				gestion.setDescEnvase(rs.getString("descripcion"));
				gestion.setCantidadProducto(rs.getDouble("cantidadDisponible"));
				gestion.setSaldoProducto(rs.getDouble("cantidadUsable"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<GestionProduccion> getRegistroIngredientes(String orden, long idCategoria,
			long idProducto) throws Exception {
		// System.out.println("DAO getRegistroIngredientes");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry = " SELECT re.habilitado,re.codigoEntrada,re.fecha,re.idProducto,mp.nombre as descProducto,c.nombre as descCategoria, re.saldo "
					+ " FROM registroentrada re, materiaprima mp, categoria c WHERE re.idProducto = mp.idProducto AND re.idCategoria = c.idCategoria "
					+ " AND mp.stock > 0 AND re.saldo > 0 ";
			Qry2 = " AND re.idTipoRegistro = 'M' AND re.habilitado = 'S' ";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND re.codigoentrada = '" + orden + "'";
			}
			if (idCategoria != 0) {
				Qry2 = Qry2 + " AND re.idCategoria = " + idCategoria;
			}
			if (idProducto != 0) {
				Qry2 = Qry2 + " AND re.idProducto = " + idProducto;
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setHabilitado(rs.getString("habilitado"));
				gestion.setOrden(rs.getString("codigoEntrada"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setDescIngrediente(rs.getString("descCategoria"));
				gestion.setCantidadProducto(rs.getDouble("saldo"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<RegistroEntrada> getLotesIngrediente(long idMateriaPrima) throws Exception {
		// System.out.println("ENVASADO DAO getRegistroIngredientesx");
		// Inicializamos el Vector de retorno.
		Vector<RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT distinct re.codigoEntrada,re.fecha,re.fechaCaducidad, re.lote, re.saldo ," +
					" (SELECT nombre FROM ordenentrada, entidad e WHERE e.idUsuario = idProveedor AND codigoOrden = " +
						" (SELECT codigoOrden FROM registroentrada WHERE codigoEntrada = re.codigoEntrada)) nombre, " +
					" mp.nombre AS nombreMP, c.nombre AS nombreCategoria " +
				" FROM registroentrada re" +
				" inner join materiaprima mp on re.idProducto = mp.idProducto " +
				" inner join categoria c ON re.idCategoria = c.idCategoria " +
				" WHERE re.saldo > 0  AND re.idTipoRegistro in('M','S') AND re.habilitado = 'S' " +
					" AND re.idProducto = " + idMateriaPrima;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Recupera los datos de los RE de MP para envasar ese Producto
				// Terminado
				RegistroEntrada reMateriaPrima = new RegistroEntrada();
				reMateriaPrima.setCodigoEntrada(rs.getString("codigoEntrada"));
				reMateriaPrima.setFecha(rs.getDate("fecha"));
				reMateriaPrima.setFechaLlegada(rs.getString("fecha"));
				reMateriaPrima.setLote(rs.getString("lote"));
				reMateriaPrima.setCantidad(rs.getDouble("saldo"));
				//indico el nombre del proveedor ayudandome del Origen
				reMateriaPrima.setDescProveedor(rs.getString("nombre"));
				reMateriaPrima.setDescProducto(rs.getString("nombreMP") + " - " + rs.getString("nombreCategoria"));
				// La añadimos al Vector de resultado
				resultado.add(reMateriaPrima);
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
	public Vector<RegistroEntrada> getLotesEnvase(long idEnvase) throws Exception {
		// System.out.println("ENVASADO DAO getRegistroIngredientesx");
		// Inicializamos el Vector de retorno.
		Vector<RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT distinct re.codigoEntrada,re.fecha,re.fechaCaducidad, re.lote, re.saldo ," +
					" (SELECT nombre FROM ordenentrada, entidad e WHERE e.idUsuario = idProveedor AND codigoOrden = " +
						" (SELECT codigoOrden FROM registroentrada WHERE codigoEntrada = re.codigoEntrada)) nombre, " +
					" e.nombre AS nombreEnvase " +
				" FROM registroentrada re" +
				"	inner join envase e on re.idEnvase = e.idEnvase " +
				" WHERE re.saldo > 0  AND re.idTipoRegistro in('E','S') AND re.habilitado = 'S' " +
					" AND re.idEnvase = " + idEnvase + " ; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Recupera los datos de los RE de MP para envasar ese Producto
				// Terminado
				RegistroEntrada reMateriaPrima = new RegistroEntrada();
				reMateriaPrima.setCodigoEntrada(rs.getString("codigoEntrada"));
				reMateriaPrima.setFecha(rs.getDate("fecha"));
				reMateriaPrima.setFechaLlegada(rs.getString("fecha"));
				reMateriaPrima.setLote(rs.getString("lote"));
				reMateriaPrima.setCantidad(rs.getDouble("saldo"));
				//indico el nombre del proveedor ayudandome del Origen
				reMateriaPrima.setDescProveedor(rs.getString("nombre"));
				reMateriaPrima.setDescProducto(rs.getString("nombreEnvase"));
				// La añadimos al Vector de resultado
				resultado.add(reMateriaPrima);
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
	public Vector<RegistroEntrada> getRegistroIngredientes(long idProducto, String filtro) throws Exception {
		// System.out.println("ENVASADO DAO getRegistroIngredientesx");
		// Inicializamos el Vector de retorno.
		Vector<RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry = "SELECT distinct re.codigoEntrada,re.fecha,re.fechaCaducidad, re.lote, re.saldo "
					//la siguiente linea me saca el nombre del proveedor
					+ ",(SELECT nombre FROM ordenentrada, entidad e WHERE e.idUsuario = idProveedor AND codigoOrden = (SELECT codigoOrden FROM registroentrada WHERE codigoEntrada = re.codigoEntrada)) nombre"
					+ " FROM registroentrada re, producto pp "
					+ " WHERE re.idProducto = pp.idMateriaPrima "
					+ " AND re.saldo > 0" + filtro;
			Qry2 = " AND re.idTipoRegistro in('M','S') AND re.habilitado = 'S' ";
			if (idProducto != 0) {
				Qry2 = Qry2
					+ " AND re.idProducto = (SELECT idMateriaPrima FROM producto WHERE idProducto= " + idProducto + ")"
					//lo filtro por la categoría
					+ " AND re.idCategoria = (SELECT idCategoria FROM producto WHERE idProducto= " + idProducto + ")";
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Recupera los datos de los RE de MP para envasar ese Producto
				// Terminado
				RegistroEntrada reMateriaPrima = new RegistroEntrada();
				reMateriaPrima.setCodigoEntrada(rs.getString("codigoEntrada"));
				reMateriaPrima.setFecha(rs.getDate("fecha"));
				reMateriaPrima.setFechaCaducidad(rs.getString("fechaCaducidad"));
				reMateriaPrima.setLote(rs.getString("lote"));
				reMateriaPrima.setCantidad(rs.getDouble("saldo"));
				//indico el nombre del proveedor ayudandome del Origen
				reMateriaPrima.setOrigen(rs.getString("nombre"));
				// La añadimos al Vector de resultado
				resultado.add(reMateriaPrima);
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
	 * @author Induserco PROCESO: ENVASADO Retorna el nombre de la MP y de la
	 *         Categoria del Producto Final a elaborar ok
	 * **/
	//@Override
	public Vector getInfoMateriaPrima(long idProducto) throws Exception {
		// System.out.println("ENVASADO DAO getInfoMateriaPrima");
		// Inicializamos el Vector de retorno.
		Vector<RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		try {
			con = bddConexion();
			String Qry = "SELECT distinct mm.nombre as nombreMateriaPrima,cc.nombre as nombreCategoria "
					+ " FROM registroentrada re " +
							" inner join producto pp ON re.idProducto = pp.idMateriaPrima AND re.idCategoria = pp.idCategoria " +
							" inner joiN materiaprima mm ON mm.idProducto = pp.idMateriaPrima" +
							" INNER JOIN categoria cc ON cc.idCategoria =  pp.idCategoria "
					+ " WHERE re.idProducto = (SELECT idMateriaPrima FROM producto WHERE idProducto= "
					+ idProducto + ")";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Recupera info de MP
				RegistroEntrada reInfoMateriaPrima = new RegistroEntrada();
				reInfoMateriaPrima.setDescProducto(rs.getString("nombreMateriaPrima"));
				reInfoMateriaPrima.setDescCategoria(rs.getString("nombreCategoria"));
				// La añadimos al Vector de resultado
				resultado.add(reInfoMateriaPrima);
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
	public Vector<GestionProduccion> getRegistroEnvases(long idProducto, String filtro) throws Exception {
		// System.out.println("ENVASADO DAO getRegistroEnvases");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		try {
			con = bddConexion();
			String Qry = " SELECT re.habilitado,re.codigoEntrada,re.fecha, re.idEnvase,e.nombre as descProducto,re.saldo "
					// modificaciones introduciendo el numero de lote, fecha de caducidad y nombre del proveedor
					+ ", re.fechaCaducidad, re.lote,(SELECT nombre FROM ordenentrada, entidad e WHERE e.idUsuario = idProveedor AND codigoOrden= (SELECT codigoOrden FROM registroentrada WHERE codigoEntrada = re.codigoEntrada)) nombre "
					+ " FROM registroentrada re, envase e WHERE re.idEnvase = e.idEnvase AND re.saldo > 0 "
					+ " AND re.idEnvase in (SELECT mpe.idEnvase FROM producto_envase mpe"
					+ " WHERE mpe.idProducto = "+idProducto+") " + filtro;
			Qry2 = " AND re.habilitado = 'S' AND e.stock > 0  ";
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setHabilitado(rs.getString("habilitado"));
				gestion.setOrden(rs.getString("codigoEntrada"));
				//gestion.setFecha(rs.getString("fecha"));
				gestion.setIdProducto(rs.getLong("idEnvase"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setCantidadProducto(rs.getDouble("saldo"));
				//Date fecha = rs.getDate("fecha");
				gestion.setFecha(rs.getString("fecha"));
				//Date caduca = rs.getDate("fechaCaducidad");
				gestion.setFechaCaducidad(rs.getString("fechaCaducidad"));
				//nuevas opciones
				gestion.setLoteEnvases(rs.getString("lote"));
				//gestion.setFechaCaducidad(rs.getDate("fechaCaducidad"));
				gestion.setDescIngrediente(rs.getString("nombre"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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

	private void addNuevoSecuencialEnvasado(Connection con, long valorSecuencial)
			throws SQLException {

		// System.out.println("DAO addNuevoSecuencialEnvasado");
		Statement stmt1;
		String insertSql = "insert into secuencial_lote(valorSecuencial) values("
				+ valorSecuencial + ")";

		// System.out.println(insertSql);
		stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		stmt1.executeUpdate(insertSql);
	}

	/**
	 * Update registro gestion produccion.
	 *
	 * @param con the con
	 * @param orden the orden
	 * @param lote the lote
	 * @throws SQLException the sQL exception
	 */
	public void updateRegistroGestionProduccion(Connection con, String orden,
			String lote) throws SQLException {
		Statement stmt;
		String updateString = "update gestionproduccion set"
				+ " loteasignado = '" + lote + "'" + " WHERE orden = '" + orden + "'";
		// System.out.println(updateString);
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate(updateString);
	}

	//@Override
	public Vector<GestionProduccion> getREProceso(String orden, long idProducto, int idIncidencia)
			throws Exception {
		// System.out.println("DAO getREProceso");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		String Qry3 = null;
		try {
			con = bddConexion();
			String Qry = " SELECT DISTINCT re.codigoEntrada,re.fecha,re.idProducto, mp.nombre as descProducto,re.saldo, " +
					" cate.nombre as nombreCategoria "
					+ " FROM registroentrada re, materiaprima mp, materiaprima_categoria mc, categoria cate, " +
							" registroentrada_incidencia rei "
					+ " WHERE re.idProducto = mp.idProducto "
						+ " AND re.idEntrada=rei.idEntrada "
						+ " AND cate.idCategoria = mc.idCategoria "
						+ " AND mc.idMateriaPrima = mp.idProducto AND re.idCategoria = cate.idCategoria "
						+ " AND rei.estado='N' "
						+ " AND rei.idIncidencia = "
						+ idIncidencia
						+ " AND mp.stock > 0 "
						+ " AND re.saldo > 0 ";
			Qry2 = " AND re.idTipoRegistro = 'M' AND re.habilitado = 'S' ";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND re.codigoentrada = '" + orden + "'";
			}
			if (idProducto != 0) {
				//Qry2 = Qry2 + " AND re.idProducto = " + idProducto;
				Qry2 = Qry2 + " AND mc.idMateriaCategoria = " + idProducto;
			}
			Qry3 = " ORDER BY descProducto,re.fecha";
			Qry = Qry + Qry2 + Qry3;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("codigoEntrada"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descProducto") + " " + rs.getString("nombreCategoria"));
				gestion.setCantidadProducto(rs.getDouble("saldo"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<GestionProduccion> getRegistroEntradaCongelado(String orden, long idProducto)
			throws Exception {
		// System.out.println("DAO getRegistroEntradaCongelado");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		String Qry3 = null;
		try {
			con = bddConexion();
			String Qry = " SELECT DISTINCT re.codigoEntrada,re.fecha,re.idProducto, mp.nombre as descProducto,re.saldo "
					+ " FROM registroentrada re, materiaprima mp "
					+ "  WHERE re.idProducto = mp.idProducto " +
					// "  AND re.idEntrada=rei.idEntrada " +
					// "  AND rei.idIncidencia = 1 " +
					"  AND mp.stock > 0 " + "  AND re.saldo > 0 ";
			Qry2 = " AND re.idTipoRegistro = 'M' AND re.habilitado = 'S' ";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND re.codigoentrada = '" + orden + "'";
			}
			if (idProducto != 0) {
				Qry2 = Qry2 + " AND re.idProducto = " + idProducto;
			}
			Qry3 = " order by descProducto,re.fecha";
			Qry = Qry + Qry2 + Qry3;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("codigoEntrada"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setCantidadProducto(rs.getDouble("saldo"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<GestionProduccion> getRegistroEntradaCribado(String orden, long idProducto)
			throws Exception {
		// System.out.println("DAO getRegistroEntradaCribado");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = null;
		String Qry3 = null;
		try {
			con = bddConexion();
			String Qry = "SELECT DISTINCT re.codigoEntrada,re.fecha,re.idProducto, mp.nombre AS descProducto,re.saldo, " +
				" cate.nombre AS nombreCategoria "
					+ " FROM registroentrada re, materiaprima mp, registroentrada_incidencia rei, " +
							" materiaprima_categoria mc, categoria cate "
					+ " WHERE re.idProducto = mp.idProducto "
						+ " AND re.idEntrada=rei.idEntrada "
						+ " AND cate.idCategoria = mc.idCategoria "
						+ " AND mc.idMateriaPrima = mp.idProducto AND re.idCategoria = cate.idCategoria "
						+ " AND mp.stock > 0 "
						+ " AND re.saldo > 0 "
						+ " AND rei.idIncidencia = 1 ";		
			Qry2 = " AND re.idTipoRegistro = 'M' AND re.habilitado = 'S' ";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND re.codigoentrada = '" + orden + "'";
			}
			if (idProducto != 0) {
				Qry2 = Qry2 + " AND re.idProducto = " + idProducto;
			}
			Qry3 = " order by descProducto,re.fecha";
			Qry = Qry + Qry2 + Qry3;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setOrden(rs.getString("codigoEntrada"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descProducto") + " - " + rs.getString("nombreCategoria"));
				gestion.setCantidadProducto(rs.getDouble("saldo"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<RegistroEntrada> getDetalleCribado(String codigoEntrada) throws Exception {
		// System.out.println("DAO getDetalleCribado");
		// Inicializamos el Vector de retorno.
		Vector<RegistroEntrada> resultado = new Vector<RegistroEntrada>();
		try {
			con = bddConexion();
			String Qry = "SELECT re.codigoEntrada,ca.nombre as descCategoria,re.cantidad,re.fecha FROM registroentrada re, categoria ca "
					+ " WHERE re.idCategoria=ca.idCategoria"
					+ " AND idEntradaPadre=(SELECT idEntrada FROM registroentrada WHERE codigoEntrada='"
					+ codigoEntrada + "') " + " order by re.codigoEntrada";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Datos del subregistro
				RegistroEntrada subregistros = new RegistroEntrada();
				subregistros.setCodigoEntrada(rs.getString("codigoEntrada"));
				subregistros.setDescCategoria(rs.getString("descCategoria"));
				subregistros.setCantidad(rs.getDouble("cantidad"));
				subregistros.setFecha(rs.getDate("fecha"));
				// La añadimos al Vector de resultado
				resultado.add(subregistros);
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
	 * Cuando se va a finalizar un proceso, obtiene el código de orden
	 * correspondiente.
	 *
	 * @param proceso the proceso
	 * @return the codigo registro proceso
	 * @throws Exception the exception
	 */
	//@Override
	public String getCodigoRegistroProceso(String proceso) throws Exception {
		PreparedStatement ps1;
		// System.out.println("DAO getCodigoRegistroProceso de " + proceso);
		con = bddConexion();
		ps1 = con.prepareStatement("SELECT gp.orden FROM gestionproduccion gp "
						+ " WHERE gp.idGestionProduccion = (SELECT max(idGestionProduccion) FROM gestionproduccion) "
						+ " AND gp.proceso = '"
						+ proceso
						//+ "' AND gp.fechaCaducidad is null AND gp.hora is null "
						+ "' AND gp.horafin is null ");
		rs = ps1.executeQuery();
		String idProduccionMax = null;
		GestionProduccion gpMaximo = new GestionProduccion();
		if (rs.next()) {
			gpMaximo.setOrden(rs.getString("orden"));
			idProduccionMax = gpMaximo.getOrden();
		}
		return idProduccionMax;
	}

	//@Override
	public Vector<Producto> getProductosDesgranado() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			// restringe a todos los derivados de desgranado de faba excepto
			// vaina
			String Qry = "SELECT * FROM materiaprima WHERE idProducto < 8 AND idProducto != 7 ";
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
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Vector<Producto> getProductosDesgranadoVaina() throws Exception {
		// Inicializamos el Vector de retorno.
		Vector<Producto> resultado = new Vector<Producto>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM materiaprima WHERE nombre like 'Vaina%' ";
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
			} catch (Exception e) { e.printStackTrace(); }
		}
		// Retornamos el vector de resultado.
		return resultado;
	}
	
	//@Override
	/**
	 * @author andres (31/05/2011)
	 * @since 1.4
	 */
	public Vector<RegistroEntrada> getDetallesEnvasado(String ordenEnvasado) throws Exception{
		Vector<RegistroEntrada> detalles = new Vector<RegistroEntrada>();
		// System.out.println("DAO getDetallesEnvasado");
		try {
			con = bddConexion();
			String query =
				" SELECT r.idCategoria, r.idEnvase, r.codigoEntrada as codigoEntrada, e.nombre as descProveedor, " +
					" r.fechaLlegada, r.idEntrada, oe.idProveedor, oe.idTransportista, oe.origen, " +
					" r.usuarioResponsable, r.cantidad as cantidadProducto, ged.idTipoRegistro, r.idProducto " +
				" FROM registroentrada r, gp_envasado_detalle ged, ordenentrada oe, entidad e " +
				" WHERE r.codigoEntrada = ged.codigoEntrada " +
					" AND e.idUsuario = oe.idProveedor " +
					" AND r.codigoOrden = oe.codigoOrden " +
					" AND ged.orden = '" + ordenEnvasado + "'";
			// System.out.println(query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				RegistroEntrada entrada = new RegistroEntrada();
				entrada.setCodigoEntrada(rs.getString("codigoEntrada"));
				entrada.setFechaLlegada(rs.getString("fechaLlegada"));
				entrada.setIdProveedor(rs.getLong("idProveedor"));
				entrada.setDescProveedor(rs.getString("descProveedor"));
				entrada.setIdTransportista(rs.getLong("idTransportista"));
				entrada.setOrigen(rs.getString("origen"));
				//A partir del idEntrada, buscamos las incidencias, en registroentrada_incidencia
				long idEntrada = rs.getLong("idEntrada");
				entrada.setIdEntrada(idEntrada);
				query = "SELECT i.idIncidencia, i.nombre " +
						" FROM registroentrada_incidencia ri, incidencia i " +
						" WHERE ri.idEntrada = " + idEntrada + " AND ri.idIncidencia = i.idIncidencia";
				// System.out.println(query);
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rst = pst.executeQuery();
				List<Incidencia> incidencias = new ArrayList();
				while (rst.next()){
					Incidencia incidencia = new Incidencia();
					incidencia.setIdIncidencia(rst.getLong("idIncidencia"));
					incidencia.setNombre(rst.getString("nombre"));
					incidencias.add(incidencia);
				}
				entrada.setIncidencias(incidencias);
				Entidad entidad = new Entidad();
				entidad.setNombre(rs.getString("usuarioResponsable"));
				entrada.setResponsable(entidad);
				entrada.setCantidad(rs.getDouble("cantidadProducto"));
				entrada.setIdTipoRegistro(rs.getString("idTipoRegistro"));
				long idCategoria = rs.getLong("idCategoria");
				long idProducto = rs.getLong("idProducto");
				entrada.setIdProducto(idProducto);
				if (entrada.getIdTipoRegistro().compareTo("E") == 0){
					long idEnvase = rs.getLong("idEnvase");
					Connection co = bddConexion();
					String q =
						" SELECT * " +
						" FROM envase e " +
						" WHERE e.idEnvase = " + idEnvase;
					// System.out.println(q);
					PreparedStatement pstate = co.prepareStatement(q);
					ResultSet res = pstate.executeQuery();
					while (res.next()){
						entrada.setDescProducto(res.getString("nombre"));
					}
				} else{
					if (entrada.getIdTipoRegistro().compareTo("M") == 0){
						String q =
							" SELECT m.nombre as nombreMateria, c.nombre as nombreCategoria" +
							" FROM materiaprima m, materiaprima_categoria mc, categoria c " +
							" WHERE mc.idMateriaPrima = m.idProducto " +
								" AND mc.idCategoria = c.idCategoria " +
								" AND m.idProducto = " + idProducto + " " +
								" AND mc.idCategoria = " + idCategoria;
						// System.out.println(q);
						PreparedStatement psta = null;
						ResultSet res = null;
						psta = con.prepareStatement(q);
						res = psta.executeQuery();
						while (res.next()) {
							entrada.setDescProducto(res.getString("nombreMateria"));
							entrada.setDescCategoria(res.getString("nombreCategoria"));
							entrada.setIdCategoria(idCategoria);
							entrada.setIdProducto(idProducto);
						}
					}else{
						if (entrada.getIdTipoRegistro().compareTo("P") == 0){
							String q =
								" SELECT p.nombre " +
								" FROM producto p, gp_envasado gpe " +
								" WHERE gp.idProducto = p.idProducto " +
									" AND p.idProducto = " + idProducto;
							// System.out.println(q);
							PreparedStatement psta = null;
							ResultSet res = null;
							psta = con.prepareStatement(q);
							res = psta.executeQuery();
							while (res.next()) {
								entrada.setDescProducto(res.getString("nombre"));
								entrada.setIdProducto(idProducto);
							}
						}
					}
				}
				// La añadimos al vector de resultado
				detalles.add(entrada);
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
		return detalles;
	}

	//@Override
	public Vector<GestionProduccion> getRegistroIngredientes(String orden, long idProducto)
			throws Exception {
		// System.out.println("DAO getRegistroIngredientes");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		String Qry2 = "";
		try {
			con = bddConexion();
			String Qry = " SELECT re.habilitado,re.codigoEntrada,re.fecha,re.idProducto,mp.nombre as descProducto,re.saldo, re.lote, det.cantidadReal, det.mermaProducto "
				+ "  FROM registroentrada re, materiaprima mp, categoria c"
				+ "  WHERE re.idProducto = mp.idProducto "
				+ "  AND mp.stock > 0 " + "  AND re.saldo > 0  ";
			Qry2 = " AND re.idTipoRegistro = 'M' AND re.habilitado = 'S' ";
			if (orden != null && !orden.equals("")) {
				Qry2 = Qry2 + " AND re.codigoentrada = '" + orden + "'";
			}
			if (idProducto != 0) {
				Qry2 = Qry2 + " AND re.idProducto = " + idProducto;
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setHabilitado(rs.getString("habilitado"));
				gestion.setOrden(rs.getString("codigoEntrada"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setFechaCaducidad(rs.getString("fechaCaducidad"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setCantidadProducto(rs.getDouble("saldo"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	public Vector<GestionProduccion> getSubRegistrosEntrada(GestionProduccion gprod, String proceso)
			throws Exception {
		// Inicializamos el Vector de retorno.
		// System.out.println("DAO getSubRegistrosEntrada");
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
	/*	String tablaGP = null;
		try {
			con = bddConexion();
			// Utiliza la tabla de acuerdo al proceso
			if (proceso.equalsIgnoreCase("Cribado"))
				tablaGP = "gp_cribado";
			else if (proceso.equalsIgnoreCase("Desgranado"))
				tablaGP = "gp_desgranado";
			String Qry1 = "SELECT r.codigoEntrada as codigoEntrada, mp.nombre as descProducto, " +
					" ca.nombre as categoriaProducto,r.cantidad as cantidadProducto " +
					" FROM registroentrada r, " + tablaGP + " gp, materiaprima mp, materiaprima_categoria mc, categoria ca " +
					" WHERE r.idProducto = mp.idProducto AND gp.idProducto = mc.idMateriaCategoria AND mc.idMateriaPrima = r.idProducto " +
					" AND mc.idCategoria = r.idCategoria AND mc.idCategoria = ca.idCategoria " +
					" AND gp.idCodigoEntradaProducto = r.codigoEntrada AND r.idCategoria=ca.idCategoria " +
					" AND gp.orden = '" + gprod.getOrden() + "'";
			// System.out.println(Qry1);
			ps = con.prepareStatement(Qry1);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setDescEnvase(rs.getString("codigoEntrada"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setLoteIngredientes(rs.getString("categoriaProducto"));
				gestion.setCantidadProducto(rs.getDouble("cantidadProducto"));
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
		// Retornamos el vector de resultado.*/
		return resultado;
	}
	
	/**
	 * Detalle de los Subregistros de Entrada generados
	 * en los procesos de Cribado y Desgranado.
	 *
	 * @param entry the entry
	 * @param con the con
	 * @param proceso the proceso
	 * @throws Exception the exception
	 */
	public void addDetalleRegistroDesgranado(RegistroEntrada entry,
			Connection con, String proceso) throws Exception {
		/*	Statement stmt1;
		String tablaGP = "";
		// System.out.println("DAO addDetalleRegistroDesgranado");
		// Obtiene la orden con la que queremos trabajar
		GestionProduccion gprod = new GestionProduccion();
		String Qry0 = "SELECT gp.idGestionProduccion,gp.orden FROM gestionproduccion gp "
				+ " WHERE gp.idGestionProduccion = (SELECT max(idGestionProduccion) FROM gestionproduccion) "
				+ " AND gp.proceso = '"
				+ proceso
				//+ "' AND gp.fechaCaducidad is null AND gp.hora is null "
				+ "' AND gp.horafin is null ";
		// System.out.println(Qry0);
		// obtiene el código de órden generado
		ps = con.prepareStatement(Qry0);
		rs = ps.executeQuery();
		String idProduccionMax = null;
		GestionProduccion gpMaximo = new GestionProduccion();
		if (rs.next()) {
			gpMaximo.setOrden(rs.getString("orden"));
			idProduccionMax = gpMaximo.getOrden();
			gpMaximo.setIdGestion(rs.getLong("idGestionProduccion"));
		}
		// Obtiene el código del SRE y IdProducto almacenados en la tabla
		// temporal
		String Qry = "SELECT re.codigoEntrada, re.idProducto, re.idCategoria, mc.idMateriaCategoria " +
				" FROM registroentrada re, tmp_ingredientes_desgranado tmp, materiaprima_categoria mc, materiaprima m " +
				" WHERE re.codigoEntrada = tmp.codigoEntradaIngrediente " +
				" AND re.codigoEntrada = '" + entry.getCodigoEntrada() + "' " +
				" AND mc.idCategoria = re.idCategoria " +
				" AND mc.idMateriaPrima = re.idProducto " +
				" AND m.idProducto = re.idProducto";
		// System.out.println(Qry);
		ps = con.prepareStatement(Qry);
		rs = ps.executeQuery();
		while (rs.next()) {
			GestionProduccion gestion = new GestionProduccion();
			gestion.setOrden(rs.getString("codigoEntrada"));
			//gestion.setIdProducto(rs.getLong("idProducto"));
			gestion.setIdProducto(rs.getLong("idMateriaCategoria"));
			gprod = gestion;
		}
		// este insert depende del proceso que estamos ejecutando
		if (proceso.equalsIgnoreCase("Desgranado"))
			tablaGP = "gp_desgranado";
		else if (proceso.equalsIgnoreCase("Cribado"))
			tablaGP = "gp_cribado";
		String insertSql = "INSERT INTO "
				+ tablaGP
				+ "(idGestionProduccion,orden,idCodigoEntradaProducto,idProducto,cantidadUsable) values("
				+ gpMaximo.getIdGestion() + ",'" + idProduccionMax + "','"
				+ gprod.getOrden() + "'," + gprod.getIdProducto() + "," + entry.getCantidad() + ")";
		// System.out.println(insertSql);
		stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		stmt1.executeUpdate(insertSql);*/
	}

	public void addTmpDetalleRegistroDesgranado(RegistroEntrada entry,
			Connection con) throws SQLException {
		Statement stmt1;
		// SQL de insersion
		String insertSql = "INSERT INTO tmp_ingredientes_desgranado(codigoEntradaIngrediente) VALUES ('"
				+ entry.getCodigoEntrada() + "')";
		// System.out.println(insertSql);
		stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		stmt1.executeUpdate(insertSql);
	}

	/**
	 * Método para el detalle de la selección M o A
	 *
	 * @param entry the entry
	 * @param con the con
	 * @param proceso the proceso
	 * @throws Exception the exception
	 */
	public String addDetalleRegistroProceso(RegistroEntrada entry,
			Connection con, String proceso) throws Exception {
		// System.out.println("DAO addDetalleRegistroProceso");
		Statement stmt1;
		String tablaGP = null;
		String tipoSeleccion = null;
		// 1. Obtiene la orden con la que queremos trabajar
		String Qry0 = "SELECT gp.idGestionProduccion,gp.orden FROM gestionproduccion gp "
				+ " WHERE gp.idGestionProduccion = (SELECT MAX(idGestionProduccion) FROM gestionproduccion) "
				+ " AND gp.proceso = '"
				+ proceso
				+ "' AND gp.horafin is null ";
		// System.out.println(Qry0);
		ps = con.prepareStatement(Qry0);
		rs = ps.executeQuery();
		String orden = null;
		GestionProduccion gpMaximo = new GestionProduccion();
		if (rs.next()) {
			gpMaximo.setOrden(rs.getString("orden"));
			// código de órden generado
			orden = gpMaximo.getOrden();
		}
		// 2. Inserta en la tabla los SRE asociados al proceso
		String insertSql = "";
		if (proceso.equalsIgnoreCase("SeleccionM")) {
			tablaGP = "gp_seleccion";
			tipoSeleccion = "M";
			insertSql = "insert into " + tablaGP
				+ "(orden,codigoEntrada,tipoSeleccion,cantidadUsable) VALUES('"
				+ orden + "','" + entry.getCodigoEntrada() + "','"
				+ tipoSeleccion + "'," + entry.getCantidad() + ")";
		} else
			if (proceso.equalsIgnoreCase("SeleccionA")) {
				tablaGP = "gp_seleccion";
				tipoSeleccion = "A";
				insertSql = "insert into " + tablaGP
					+ "(orden,codigoEntrada,tipoSeleccion,cantidadUsable) VALUES('"
					+ orden + "','" + entry.getCodigoEntrada() + "','"
					+ tipoSeleccion + "'," + entry.getCantidad() + ")";
			}else
				if (proceso.equalsIgnoreCase("Cribado")){
					tablaGP = "gp_cribado";
					insertSql = "insert into " + tablaGP
						+ "(orden,codigoEntrada,cantidadUsable) VALUES('"
						+ orden + "','" + entry.getCodigoEntrada() + "',"
						+ entry.getCantidad() + ")";
				}else
					if (proceso.equalsIgnoreCase("Desgranado")){
						tablaGP = "gp_desgranado";
						insertSql = "INSERT INTO " + tablaGP
							+ "(orden,codigoEntrada,cantidadUsable) VALUES('"
							+ orden + "','" + entry.getCodigoEntrada() + "',"
							+ entry.getCantidad() + ")";
					}
		// System.out.println(insertSql);
		stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		stmt1.executeUpdate(insertSql);
		return orden;
	}

	/**
	 * @deprecated
	 */
	public String generarNumeroLote(String codigo) throws SQLException {
		String numerolote = null;
		GestionProduccion g = new GestionProduccion();
		try {
			con = bddConexion();
			// obtener el nuevo secuencial
			ps = con.prepareStatement("SELECT max(valorSecuencial) as valorMaxSecuencial FROM secuencial_lote");
			rs = ps.executeQuery();
			long valorMaxSecuencial = 0;
			while (rs.next()) {
				GestionProduccion envasadoMaximo = new GestionProduccion();
				envasadoMaximo.setIdGestion(rs.getLong("valorMaxSecuencial"));
				valorMaxSecuencial = envasadoMaximo.getIdGestion() + 1;
			}
			String Qry = " SELECT re.idProducto, re.idCategoria FROM registroentrada re "
					+ " WHERE re.codigoEntrada = '" + codigo + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setIdGestion(rs.getLong("idCategoria"));
				g = gestion;
			}
			if (valorMaxSecuencial < 10) {
				numerolote = "0" + g.getIdProducto() + g.getIdGestion()
						+ "0000" + valorMaxSecuencial;
			} else if (valorMaxSecuencial < 100) {
				numerolote = "0" + g.getIdProducto() + g.getIdGestion() + "000"
						+ valorMaxSecuencial;
			} else if (valorMaxSecuencial < 1000) {
				numerolote = "0" + g.getIdProducto() + g.getIdGestion() + "00"
						+ valorMaxSecuencial;
			} else if (valorMaxSecuencial < 10000) {
				numerolote = "0" + g.getIdProducto() + g.getIdGestion() + "0"
						+ valorMaxSecuencial;
			}
			addNuevoSecuencialEnvasado(con, valorMaxSecuencial);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return numerolote;
	}

	/**
	 * Agrega los componentes para el proceso Congelado / Fumigado.
	 *
	 * @param listaelementos the listaelementos
	 * @param orden the orden
	 * @param tipo the tipo
	 * @return the boolean
	 * @throws Exception the exception
	 */
	//@Override
	public Boolean addComponentesProceso(List listaelementos, String orden, String tipo) throws Exception {
		// System.out.println("DAO addComponentesProceso Congelado o Fumigado");
		// Inicializamos el Vector de retorno.
		Statement stmt;
		Boolean resultado = false;
		int finsubstring = 0;
		String insertString = null;
		String tablagp = null;
		if (tipo.equals("Congelado"))
			tablagp = "gp_congelado";
		else if (tipo.equals("Fumigado"))
			tablagp = "gp_fumigado";
		if (listaelementos.isEmpty()){
			// System.out.println("No hay lista de elementos");
		}else {
			Iterator itr = listaelementos.iterator();
			while (itr.hasNext()) {
				String codigoEntrada = (String) itr.next();
				finsubstring = codigoEntrada.length() - 1;
				codigoEntrada = codigoEntrada.substring(0, finsubstring);
				RegistroEntrada regEntrada = new RegistroEntrada();
				try {
					con = bddConexion();
					String Qry = "SELECT re.codigoEntrada,re.saldo,CONCAT(mp.nombre,' - ',c.nombre) as descProducto " +
							" FROM registroentrada re,categoria c, materiaprima mp " +
							" WHERE c.idCategoria = re.idCategoria AND re.codigoEntrada = '" + codigoEntrada + 
							"' AND re.idProducto = mp.idProducto";
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					while (rs.next()) {
						// Completamos los datos del proveedor en el registro
						regEntrada.setCodigoEntrada(rs.getString("codigoEntrada"));
						regEntrada.setCantidad(rs.getDouble("saldo"));
						regEntrada.setDescProducto(rs.getString("descProducto"));
					}
					insertString = "INSERT INTO "
							+ tablagp
							+ " (orden,codigoEntrada, cantidadDisponible, descripcion) VALUES("
							+ "'" + orden + "','"
							+ regEntrada.getCodigoEntrada() + "',"
							+ regEntrada.getCantidad() + ",'" + regEntrada.getDescProducto() + "')";
					// System.out.println("insert al gp_PROCESO_ubicacion " + insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
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
		return resultado;
	}

	//@Override
	public GestionProduccion getEtiquetaEN(String codigoOrden) throws Exception {
		//Si llega un codigo de entrada (de un producto final): E120412-89
		//Si llega un codigo de una orden de envasado: EN2012050964
		// Inicializamos el Vector de retorno.
		GestionProduccion gp = new GestionProduccion();
		try {
			con = bddConexion();
			// System.out.println("DAO getRegistroEntradas");
			if (codigoOrden.contains("EN")){
				//Envasado
				String Qry =
					" SELECT * "
					+ " FROM gp_envasado_agrupacion gpea "
					+ " WHERE gpea.ordenEnvasado = '" + codigoOrden + "'; ";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				long idAgrupacion = -1;
				if (rs.next()) {
					idAgrupacion = rs.getLong("idAgrupacion");
				}
				if (idAgrupacion > 0){
					Qry = " SELECT ge.lote,ge.fecha,fechaCaducidad,pr.nombre,pr.peso,c.nombre as cate, ge.horafin " +
					" FROM gp_envasado ge, gp_envasado_agrupacion gpea, producto pr, categoria c " +
					" WHERE c.idCategoria = pr.idCategoria" +
						" AND pr.idProducto = " + idAgrupacion + " " +
						" AND ge.orden = gpea.ordenEnvasado " +
						" AND pr.idProducto = gpea.idAgrupacion " +
						" AND ge.orden = '" + codigoOrden + "'";
				}else{
					Qry =
						" SELECT ge.lote,ge.fecha,fechaCaducidad,pr.nombre,pr.peso,c.nombre as cate, ge.horafin "
						+ " FROM gp_envasado ge, producto pr, categoria c"
						+ " WHERE ge.idProducto = pr.idProducto "
							+ " AND c.idCategoria = pr.idCategoria "
							+ " AND ge.orden = '" + codigoOrden + "'";
				}
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				if (rs.next()) {
					gp.setLoteAsignado(rs.getString("lote"));
					gp.setDescProducto(rs.getString("nombre"));
					String aux = rs.getString("horafin");
					String[] frag = aux.split(" ");
					frag = frag[0].split("-");
					String fecha = frag[2] + "/" + frag[1] + "/" + frag[0].charAt(2) + frag[0].charAt(3);
					gp.setFecha(fecha);
					gp.setPeso(rs.getDouble("peso"));
					aux = rs.getString("fechaCaducidad");
					frag = aux.split("-");
					String fechaCaducidad = frag[2] + "/" + frag[1] + "/" + frag[0].charAt(2) + frag[0].charAt(3);
					gp.setFechaCaducidad(fechaCaducidad);
					gp.setDescripcionCategoria(rs.getString("cate"));
					
					Qry =
						" SELECT oe.origen " +
						" FROM gp_envasado ge" +
							" INNER JOIN gp_envasado_detalle ged ON ge.orden = ged.orden " +
							" INNER JOIN registroentrada re ON ged.codigoEntrada = re.codigoEntrada " +	
							" INNER JOIN ordenentrada oe ON re.codigoOrden = oe.codigoOrden " +
						" WHERE ge.orden = '" + codigoOrden + "' " +
							" AND re.idTipoRegistro = 'M'; ";
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					if (rs.next()){
						gp.setOrigen(rs.getString("origen"));
					}else{
						Qry =
							" SELECT ged.codigoEntrada " +
							" FROM gp_envasado ge " +
								" INNER JOIN gp_envasado_detalle ged ON ge.orden = ged.orden " +
							" WHERE ge.orden = '" + codigoOrden + "' " +
								" AND ged.idTipoRegistro = 'P'; ";
						ps = con.prepareStatement(Qry);
						rs = ps.executeQuery();
						if (rs.next()){
							Qry =
								" SELECT oe.origen " +
								" FROM gp_envasado ge" +
									" INNER JOIN gp_envasado_detalle ged ON ge.orden = ged.orden " +
									" INNER JOIN registroentrada re ON ged.codigoEntrada = re.codigoEntrada " +	
									" INNER JOIN ordenentrada oe ON re.codigoOrden = oe.codigoOrden " +
								" WHERE ge.orden = '" + rs.getString("codigoEntrada") + "'; ";
							ps = con.prepareStatement(Qry);
							rs = ps.executeQuery();
							if (rs.next()){
								gp.setOrigen(rs.getString("origen"));
							}
						}else{
							Qry =
								" SELECT oe.origen FROM gp_envasado ge INNER JOIN gp_envasado_detalle ged ON ge.orden = ged.orden INNER JOIN registroentrada re ON ged.codigoEntrada = re.codigoEntrada INNER JOIN registroentrada re2 ON re.idEntradaPadre = re2.idEntrada LEFT JOIN ordenentrada oe ON re2.codigoOrden = oe.codigoOrden  WHERE ge.orden = '" + codigoOrden + "' AND re.idTipoRegistro = 'S'; ";
							ps = con.prepareStatement(Qry);
							rs = ps.executeQuery();
							if (rs.next()){
								gp.setOrigen(rs.getString("origen"));
							}
						}
					}
				}
			}else{
				if (codigoOrden.charAt(0) == 'E'){
					//Entrada de producto final
					gp.setLoteAsignado(codigoOrden);
					String Qry =
						" SELECT re.fechaLlegada, re.fechaCaducidad, pr.nombre, pr.peso, c.nombre as cate " +
						" FROM registroentrada re, producto pr, categoria c " +
						" WHERE re.idProducto = pr.idProducto " +
							" AND c.idCategoria = pr.idCategoria " +
							" AND re.codigoEntrada = '" + codigoOrden + "'";
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					if (rs.next()) {
						gp.setDescProducto(rs.getString("nombre"));
						String aux = rs.getString("fechaLlegada");
						String[] frag = aux.split(" ");
						frag = frag[0].split("-");
						String fecha = frag[2] + "/" + frag[1] + "/" + frag[0].charAt(2) + frag[0].charAt(3);
						gp.setFecha(fecha);
						gp.setPeso(rs.getDouble("peso"));
						aux = rs.getString("fechaCaducidad");
						frag = aux.split("-");
						String fechaCaducidad = frag[2] + "/" + frag[1] + "/" + frag[0].charAt(2) + frag[0].charAt(3);
						gp.setFechaCaducidad(fechaCaducidad);
						gp.setDescripcionCategoria(rs.getString("cate"));
						//Consultamos el registro sanitario
						Qry =
							" SELECT e.registroSanitario " +
							" FROM empresa e " +
							" WHERE e.idEmpresa = 1;";
						ps = con.prepareStatement(Qry);
						rs = ps.executeQuery();
						if (rs.next()){
							gp.setRegistroSanitario(rs.getString("registroSanitario"));
						}
						Qry = "SELECT oe.origen " +
							" FROM ordenentrada oe, registroentrada re " +
							" WHERE re.codigoEntrada = '" + codigoOrden + "' " +
								" AND re.codigoOrden = oe.codigoOrden " +
								" AND re.idTipoRegistro = 'P'; ";
						ps = con.prepareStatement(Qry);
						rs = ps.executeQuery();
						if (rs.next()){
							gp.setOrigen(rs.getString("origen"));
						}
					}
				}
			}
			//Consultamos el registro sanitario
			String consulta =
				" SELECT e.registroSanitario " +
				" FROM empresa e " +
				" WHERE e.idEmpresa = 1;";
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			if (rs.next()){
				gp.setRegistroSanitario(rs.getString("registroSanitario"));
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
		return gp;
	}
	
	public boolean esFabaFresca(String codigoOrden) throws Exception {
		boolean fabaFresca = false;
		try {
			con = bddConexion();
			String Qry = 
				" SELECT COUNT(*) AS cuantos " +
				" FROM gp_envasado gpe " +
					" INNER JOIN gp_envasado_agrupacion gpea ON gpea.ordenEnvasado = gpe.orden " +
					" INNER JOIN producto p ON p.idProducto = gpea.idAgrupacion " +
					" INNER JOIN producto_materiaprima pm ON pm.idProducto = gpe.idProducto " +
					" INNER JOIN materiaprima_categoria mc ON pm.idMateriaPrima = mc.idMateriaCategoria " +
					" INNER JOIN materiaprima m ON m.idProducto = mc.idMateriaPrima " +
				" WHERE gpea.idAgrupacion > 0 AND m.idProducto IN (6, 7) AND gpe.orden = '" + codigoOrden + "'; ";//6 y 7 son los IDs de la faba fresca (desgranada y en vaina)
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				int cuantos = rs.getInt("cuantos");
				if (cuantos > 0){
					fabaFresca = true;
				}
			}
			if (!fabaFresca){
				//Si llegamos aquí, miramos si era faba fresca sin estar agrupada
				Qry = 
					" SELECT COUNT(*) AS cuantos " +
					" FROM gp_envasado gpe " +
						" INNER JOIN producto_materiaprima pm ON pm.idProducto = gpe.idProducto " +
						" INNER JOIN materiaprima_categoria mc ON pm.idMateriaPrima = mc.idMateriaCategoria " +
						" INNER JOIN materiaprima m ON m.idProducto = mc.idMateriaPrima " +
					" WHERE m.idProducto IN (6, 7) AND gpe.orden = '" + codigoOrden + "'; ";//6 y 7 son los IDs de la faba fresca (desgranada y en vaina)
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				if (rs.next()) {
					int cuantos = rs.getInt("cuantos");
					if (cuantos > 0){
						fabaFresca = true;
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
		// Retornamos el vector de resultado.
		return fabaFresca;
	}

	/**
	 * ************************************************************.
	 *
	 * @param tipo the tipo
	 * @return the procesos pendientes
	 * @throws Exception the exception
	 */
	/*  PROCESOS PENDIENTES */
	/***************************************************************/
	//@Override
	public Vector<GestionProduccion> getProcesosPendientes(GestionProduccion gpro, String tipo) throws Exception {
		// System.out.println("DAO getProcesosPendientes");
		// Inicializamos el Vector de retorno.
		Vector<GestionProduccion> resultado = new Vector<GestionProduccion>();
		try {
			con = bddConexion();
			String Qry = "";
			String estado = "";
			if (tipo.equalsIgnoreCase("E"))
				estado = " AND env.estadoproceso='P' ";
			if (tipo.equalsIgnoreCase("S"))
				estado = " AND (env.estadoproceso='S' OR env.estadoproceso='P' OR env.estadoproceso='I') ";
			Qry = "SELECT env.ubicado,env.idEnvasado, env.idProducto, " +
					" (SELECT p.nombre FROM producto p WHERE env.idProducto=p.idProducto) descProducto, " +
					" env.orden, env.fecha, env.fechaCaducidad, env.lote, env.horainicio, " +
					" env.horafin, env.cantidad, env.mermasMP, env.mermasEN, env.usuarioResponsable, " +
					" env.observaciones, env.estadoproceso, env.habilitado " +
				" FROM gp_envasado env" +
				" WHERE env.habilitado='S'" + estado;
			String Qry2 = "", orden = "", lote = "", fechaProgramada = null;
			Long idProducto = null;
			if (gpro != null){
				orden = gpro.getOrden();
				lote = gpro.getLoteAsignado();
				fechaProgramada = gpro.getFechaProgramada();
				idProducto = gpro.getIdProducto();
			}
			boolean WHERE = false;
			if (orden != null && !orden.equals("") && orden.compareTo("0") != 0) {
				if (WHERE){
					Qry2 = Qry2 + " WHERE env.orden = '" + orden + "'";
					WHERE = false;
				}
				else
					Qry2 = Qry2 + " AND env.orden = '" + orden + "'";
			}
			if (lote != null && !lote.equals("") && lote.compareTo("0") != 0) {
				if (WHERE){
					Qry2 = Qry2 + " WHERE env.lote = '" + lote + "'";
					WHERE = false;
				} else
					Qry2 = Qry2 + " AND env.lote = '" + lote + "'";
			}
			if (idProducto != null && idProducto != 0) {
				if (WHERE){
					Qry2 = Qry2 + " WHERE env.idProducto = " + idProducto;
					WHERE = false;
				} else
					Qry2 = Qry2 + " AND env.idProducto = " + idProducto;
			}
			if (fechaProgramada != null && !fechaProgramada.equals("")) {
				// System.out.println(fechaProgramada);
				String frags[] = fechaProgramada.split("/");
				String anio = frags[2], mes = frags[1], dia = frags[0];
				if (mes.length() == 1)
					mes = "0" + mes;
				if (dia.length() == 1)
					dia = "0" + dia;
				String fechaFormateada = anio + "-" + mes + "-" + dia;
				Qry2 = Qry2 + " AND env.fecha = '" + fechaFormateada + "'";
			}
			Qry = Qry + Qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				// Completamos los datos del proveedor en el registro
				GestionProduccion gestion = new GestionProduccion();
				gestion.setIdGestion(rs.getLong("idEnvasado"));
				gestion.setOrden(rs.getString("orden"));
				gestion.setFecha(rs.getString("fecha"));
				gestion.setHoraInicioProceso(rs.getString("horainicio"));
				gestion.setHoraFinProceso(rs.getString("horafin"));
				gestion.setMermaEnvases(rs.getLong("mermasEN"));
				gestion.setMermaIngredientes(rs.getDouble("mermasMP"));
				gestion.setFechaCaducidad(rs.getString("fechaCaducidad"));
				gestion.setIdProducto(rs.getLong("idProducto"));
				gestion.setDescProducto(rs.getString("descProducto"));
				gestion.setCantidadProducto(rs.getDouble("cantidad"));
				gestion.setLoteAsignado(rs.getString("lote"));
				gestion.setUsuarioResponsable(rs.getString("usuarioResponsable"));
				//gestion.setEstadoProceso(rs.getString("estadoproceso"));
				estado = rs.getString("estadoproceso");
				if (estado.compareToIgnoreCase("p") == 0)
					estado = "Pendiente";
				else
					if (estado.compareToIgnoreCase("i") == 0)
						estado = "Iniciado";
					else
						if (estado.compareToIgnoreCase("s") == 0)
							estado = "Parado";
						else
							if (estado.compareToIgnoreCase("f") == 0)
								estado = "Finalizado";
							else
								if (estado.compareToIgnoreCase("b") == 0)
									estado = "Bloqueado";
								else
									if (estado.compareToIgnoreCase("a") == 0)
										estado = "Anulado";
				gestion.setEstadoProceso(estado);
				gestion.setUbicado(rs.getString("ubicado"));
				Vector<Producto> componen =
					new ProductoDataHelper().getProductosComponen(gestion.getIdProducto(), false);
				if (componen.size() > 0)
					gestion.setTipoEan(14);
				else
					gestion.setTipoEan(13);
				// La añadimos al Vector de resultado
				resultado.add(gestion);
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
	 * Añadir una nueva orden de envasado. Crea un nuevo proceso de envasado, con estado pendiente.
	 * @param envasado Registro de envasado a introducir
	 * @return Boolean true, si la orden de envasado se añade correctamente
	 * @throws Exception Si fallan las operaciones cuando se añade la nueva orden de envasado
	 * @since 1.1
	 */
	//@Override
	public Boolean addOrdenEnvasado(RegistroEnvasado envasado) throws Exception {
		// System.out.println("ADD ORDEN ENVASADO: (DAO)");
		Boolean resultado = false;
		//ResultSet rs = null;
		Statement stmt;
		try {
			//0. Si idProducto == 0 o == null, buscarlo a traves del codigo ean
			if (envasado.getIdProducto() == null || envasado.getIdProducto() == 0){
				con = bddConexion();			
				// 1. Obtener el nuevo Id
				ps = con.prepareStatement("SELECT p.idProducto FROM producto p WHERE p.codigoEan = '" + envasado.getEanProducto() + "'");
				rs = ps.executeQuery();
				while (rs.next()) {
					envasado.setIdProducto(rs.getLong("idProducto"));
				}
			}
			con = bddConexion();
			// 1. Obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idEnvasado) as idMaxEnvasado FROM gp_envasado");
			rs = ps.executeQuery();
			while (rs.next()) {
				envasado.setIdGestion(rs.getLong("idMaxEnvasado") + 1);
			}
			// System.out.println("el id de envasado es "+ envasado.getIdGestion());
			// 2. establecemos fecha en el caso de que no haya sido asignada y creamos el id de la orden
			ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			String anno="";
			while (rs.next()) {
				if(envasado.getFechaLlegada()==null){
					envasado.setFechaLlegada(rs.getDate("fecha"));
				}
				//guardamos la fecha en cadena para crear el id de la orden
				anno = rs.getString("fecha");
				// System.out.println("anno: " + anno);
			}
			//anno = anno.replace("-", "");
			envasado.setProceso("EN"+ anno.replace("-", "") + envasado.getIdGestion());
			this.actualizaRegistroActividadEnvasado(envasado.getProceso(), envasado.getOperario(),
					"Programacion inicial");
			String insertString = "";
			insertString =
				"INSERT INTO gp_envasado_agrupacion (ordenEnvasado, idAgrupacion) " +
					" VALUES('" + envasado.getProceso() + "', " + envasado.getIdAgrupacion() + ");";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			//Miramos sin envasamos EAN13 o EAN14
			if (envasado.getTipoEan() == 13){//Estamos envasando un ITEM
				Iterator <LineaProducto> iterator = envasado.getEans13().iterator();
				while(iterator.hasNext()) {
					LineaProducto lp = new LineaProducto();
					lp = iterator.next();
					// System.out.println("LINEA DE PRODUCTO:");
					//El item puede ser un producto envasado o un producto comprado
					boolean productoEntrada = false;
					long idProducto = 0;
					if (lp.getLote().charAt(0) == 'E')
						productoEntrada = true;
					//2. Actualizamos el saldo
					if (productoEntrada){
						insertString = 
							" UPDATE registroentrada " +
							" SET saldo = saldo - " + lp.getTeorica() +
							" WHERE codigoEntrada = '" + lp.getLote() + "'; ";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertString);
						String qry =
							" SELECT idProducto FROM registroentrada WHERE codigoEntrada = '" + lp.getLote() + "'; ";
						// System.out.println(qry);
						ps = con.prepareStatement(qry);
						rs = ps.executeQuery();
						while (rs.next()) {
							idProducto = rs.getLong("idProducto");
						}
					}else{
						String orden = "";
						String qry =
							" SELECT idProducto, orden FROM gp_envasado WHERE lote = '" + lp.getLote() + "'; ";
						// System.out.println(qry);
						ps = con.prepareStatement(qry);
						rs = ps.executeQuery();
						while (rs.next()) {
							idProducto = rs.getLong("idProducto");
							orden = rs.getString("orden");
						}
						qry =
							" SELECT idAgrupacion FROM gp_envasado_agrupacion WHERE ordenEnvasado = '" + orden + "' AND idAgrupacion > 0; ";
						// System.out.println(qry);
						ps = con.prepareStatement(qry);
						rs = ps.executeQuery();
						if (rs.next()) {
							idProducto = rs.getLong("idAgrupacion");
						}
					}
					insertString = "INSERT INTO gp_envasado_detalle " +
						" (idTipoRegistro, orden, codigoEntrada, idProducto, cantidadDisponible, " +
						" cantidadTeorica, mermaProducto, descripcion) " +
						" VALUES ('P','" + envasado.getProceso() + "','" + lp.getLote() +
						"'," + idProducto + "," + lp.getTeorica() + "," + lp.getTeorica() + ",0,'')";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
					//3. Actualizamos el stock del producto
					insertString =
						" UPDATE producto SET stock = stock - " + lp.getTeorica() + " WHERE idProducto = '" + idProducto + "'";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				}
				//introducimos la materia prima
				iterator = envasado.getMateriaPrima().iterator();
				while(iterator.hasNext()) {
					LineaProducto lp = new LineaProducto();
					lp = iterator.next();
					// System.out.println("LINEA DE PRODUCTO:");
					insertString = "INSERT INTO gp_envasado_detalle " +
						"(idTipoRegistro, orden, codigoEntrada, idProducto, cantidadDisponible, cantidadTeorica, mermaProducto, descripcion) " +
						" VALUES ('M','" + envasado.getProceso() + "','" + lp.getRegistroEntrada() + "'" +
						",0" + "," + lp.getTeorica() + "," + lp.getTeorica() + ",0,'')";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
					//1. Obtenemos el saldo de la orden de entrada
					insertString = "SELECT idProducto, saldo FROM registroentrada WHERE codigoEntrada='"+lp.getRegistroEntrada()+"'";
					// System.out.println(insertString);
					ps = con.prepareStatement(insertString);
					rs = ps.executeQuery();
					double saldo = 0;
					int idMateria = 0;
					while (rs.next()) {
						saldo = rs.getDouble("saldo");
						idMateria = rs.getInt("idProducto");
					}
					//2. Actualizamos el saldo
					insertString = 
						" UPDATE registroentrada " +
						" SET saldo = " + (saldo - lp.getTeorica()) +
						" WHERE codigoEntrada = '" + lp.getRegistroEntrada() + "'; ";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
					//3. Actualizamos el stock de la materia prima tambien en la tabla materiaprima
					insertString = "update materiaprima set stock="+(saldo-lp.getTeorica())+" WHERE idProducto='"+idMateria+"'";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
					//4. Actualizamos el stock de la materia prima categorizada
					insertString = "SELECT mc.stock, mc.idMateriaCategoria " +
						" FROM materiaprima_categoria mc, registroentrada re " +
						" WHERE re.codigoEntrada = '" + lp.getRegistroEntrada() +  "' " +
							" AND re.idCategoriaEntrada = mc.idCategoria " +
							" AND mc.idMateriaPrima = re.idProducto";
					// System.out.println(insertString);
					ps = con.prepareStatement(insertString);
					rs = ps.executeQuery();
					double stock = 0;
					double idMateriaCategoria = 0;
					while (rs.next()) {
						stock = rs.getDouble("stock");
						idMateriaCategoria = rs.getInt("idMateriaCategoria");
					}
					insertString = "UPDATE materiaprima_categoria SET stock = " + (stock-lp.getTeorica()) + " WHERE idMateriaCategoria='"+idMateriaCategoria+"'";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				}
				//introducimos los envases
				iterator = envasado.getEnvases().iterator();
				while(iterator.hasNext()) {
					LineaProducto lp = new LineaProducto();
					lp = iterator.next();
					// System.out.println("LINEA DE PRODUCTO:");
					insertString = " INSERT INTO gp_envasado_detalle " +
						"(idTipoRegistro, orden, codigoEntrada, idProducto, cantidadDisponible, cantidadTeorica, " +
						" mermaProducto, descripcion) " +
						"VALUES ('E','"+envasado.getProceso()+"','"+lp.getRegistroEntrada()+"'" +
						",0" +
						"," + lp.getTeorica()+","+lp.getTeorica()+",0,'')";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
					//1. Obtenemos el saldo de la orden de entrada
					insertString = "SELECT saldo,idEnvase FROM registroentrada WHERE codigoEntrada='"+lp.getRegistroEntrada()+"'";
					// System.out.println(insertString);
					ps = con.prepareStatement(insertString);
					rs = ps.executeQuery();
					double saldo = 0;
					double envase = 0;
					while (rs.next()) {
						saldo = rs.getDouble("saldo");
						envase = rs.getDouble("idEnvase");
					}
					//2. Actualizamos el saldo
					insertString = "UPDATE registroentrada SET saldo="+(saldo-lp.getTeorica()) +
						" WHERE codigoEntrada='"+lp.getRegistroEntrada()+"'";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
					//3. Actualizamos el stock del envase en la tabla envases
					insertString = "UPDATE envase SET stock="+(saldo-lp.getTeorica())+" WHERE idEnvase='" + envase + "'";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				}
			}else{
				//Estamos envasando un producto AGRUPADO
				if (envasado.getTipoEan() == 14){
					Iterator <LineaProducto> iterator = envasado.getEans13().iterator();
					while(iterator.hasNext()) {
						long idProducto = 0;
						String orden = "";
						LineaProducto lp = new LineaProducto();
						lp = iterator.next();
						// System.out.println("LINEA DE PRODUCTO:");
						String qry =
							" SELECT idProducto, orden FROM gp_envasado WHERE lote = '" + lp.getLote() + "'; ";
						// System.out.println(qry);
						ps = con.prepareStatement(qry);
						rs = ps.executeQuery();
						while (rs.next()) {
							idProducto = rs.getLong("idProducto");
							orden = rs.getString("orden");
						}
						qry =
							" SELECT idAgrupacion " +
							" FROM gp_envasado_agrupacion " +
							" WHERE ordenEnvasado = '" + orden + "' " +
									" AND idAgrupacion > 0; ";
						// System.out.println(qry);
						ps = con.prepareStatement(qry);
						rs = ps.executeQuery();
						if (rs.next()) {
							idProducto = rs.getLong("idAgrupacion");
						}
						insertString =
							" INSERT INTO gp_envasado_detalle " +
								" (idTipoRegistro, orden, codigoEntrada, idProducto, cantidadDisponible, " +
								" cantidadTeorica, mermaProducto, descripcion) " +
							" VALUES ('P','" + envasado.getProceso() + "','" + lp.getLote() +
								"'," + idProducto + "," + lp.getTeorica() + "," + lp.getTeorica() + ",0,''); ";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertString);
						//3. Actualizamos el stock del producto
						insertString =
							" UPDATE producto SET stock = stock - " + lp.getTeorica() + " WHERE idProducto = '" + idProducto + "'";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertString);
					}
					iterator = envasado.getEnvases().iterator();
					while(iterator.hasNext()) {
						LineaProducto lp = new LineaProducto();
						lp = iterator.next();
						// System.out.println("LINEA DE PRODUCTO:");
						insertString = "INSERT INTO gp_envasado_detalle " +
							" (idTipoRegistro, orden, codigoEntrada, idProducto, cantidadDisponible, " +
							" cantidadTeorica, mermaProducto, descripcion) " +
							" VALUES ('E','" + envasado.getProceso() + "','" + lp.getRegistroEntrada() +
							"',0," + lp.getTeorica() + "," + lp.getTeorica() + ",0,'')";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertString);
						//1. Obtenemos el saldo de la orden de entrada
						insertString = "SELECT saldo,idEnvase FROM registroentrada WHERE codigoEntrada='"+lp.getRegistroEntrada()+"'";
						// System.out.println(insertString);
						ps = con.prepareStatement(insertString);
						rs = ps.executeQuery();
						double saldo = 0;
						double envase = 0;
						while (rs.next()) {
							saldo = rs.getDouble("saldo");
							envase = rs.getDouble("idEnvase");
						}
						//2. Actualizamos el saldo
						insertString = " UPDATE registroentrada SET saldo = "+(saldo-lp.getTeorica()) +
							" WHERE codigoEntrada='" + lp.getRegistroEntrada()+"'";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertString);
						//3. Actualizamos el stock del envase en la tabla envases
						insertString = "UPDATE envase SET stock="+(saldo-lp.getTeorica())+" WHERE idEnvase='" + envase + "'";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertString);
					}
				}
			}
			String lote = this.generarLoteEnvasado(envasado);
			String fechaRegistro = envasado.getFechaRegistro();
			// System.out.println(fechaRegistro);
			String frags[] = fechaRegistro.split("/");
			String anio = frags[2], mes = frags[1], dia = frags[0];
			if (mes.length() == 1)
				mes = "0" + mes;
			if (dia.length() == 1)
				dia = "0" + dia;
			String fechaFormateada = anio + "-" + mes + "-" + dia;
			String estado = "'P'", ubicado = "'N'";
			//if (envasado.getTipoEan() == 14)
				//ubicado = "'S'";
			insertString = "INSERT INTO gp_envasado (idEnvasado, idProducto, orden, fecha, lote, " +
					"horainicio, horafin, cantidad, mermasMP, mermasEN, usuarioResponsable, observaciones, estadoproceso,habilitado, ubicado) " +
					"VALUES(" + envasado.getIdGestion() + "," + envasado.getIdProducto() + ",'" + envasado.getProceso() +
					"','" + fechaFormateada + "','" + lote + "', now(), null, " + envasado.getCantidad()+ ",0,0,'" +
					envasado.getOperario() + "','" + envasado.getObservaciones() + "'," + estado + ",'S'," + ubicado + ")";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}
	
	/**  Eliminar el proceso pendiente (ORDEN DE ENVASADO)
	 * @param envasado Registro de envasado a eliminar. Viene dentro de la variable proceso.
	 * @return True si se ha eliminado correctamente
	 * @since 1.1
	**************************************************************/	
	//@Override
	public Boolean delProcesoPendiente(RegistroEnvasado envasado) throws Exception {
		// System.out.println("DEL ORDEN ENVASADO PENDIENTE: (DAO) ");
		Boolean resultado = false;
		Statement stmt;
		try {			
			//0. Obtenemos el la orden de envasado completa
			RegistroEnvasado re = getProcesoPendiente(envasado);
			//1. Si ya había envasado algo, cerrarlo y ponerlo como A (Abortado)
			con = bddConexion();
			String consulta = "UPDATE gp_envasado SET estadoproceso='A' WHERE orden='" + envasado.getProceso() + "'";
			// System.out.println(consulta);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(consulta);
			//2. Para cada materia prima/envase devolver stock no utilizado
			if (returnStock(re.getEnvases(), envasado.getProceso()))
				// System.out.println("Stock de los envases sobrante devuelto");
			if (returnStock(re.getMateriaPrima(), envasado.getProceso()))
				// System.out.println("Stock de la materia prima sobrante devuelto");
			if (returnStock(re.getEans13(), envasado.getProceso()))
				// System.out.println("Stock de EANS13 sobrante devuelto");
			//3. Marca de Tiempo
			if(setHorarioActividad(re.getLote(), "Anulado", re.getOperario(), "envasado"))
				 System.out.println("Marca de tiempo de Anulado");
			//Marca de tiempo en la tabla gp_envasado tambien
			con = bddConexion();
			consulta = "UPDATE gp_envasado SET horafin = now() WHERE orden='" + re.getProceso() + "'";
			// System.out.println(consulta);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(consulta);
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
	 * **********************************************************.
	 *
	 * @param envasado the envasado
	 * @return the proceso pendiente
	 * @throws Exception the exception
	 */
	/**  Obtener Orden de Envasado Completa (ORDEN DE ENVASADO)
	 * @param envasado Registro de envasado a devolver. Viene dentro de la variable proceso.
	 * @return True si se ha eliminado correctamente
	 * @since 1.1
	**************************************************************/	
	//@Override
	public RegistroEnvasado getProcesoPendiente(RegistroEnvasado envasado) throws Exception {
		// System.out.println("GET ENVASADO PENDIENTE: (DAO) ");
		RegistroEnvasado resultado = new RegistroEnvasado();
		try {
			con = bddConexion();
			String consulta = "";
			if (envasado.getProceso() != null && !envasado.getProceso().equals(""))
				consulta = " SELECT * FROM gp_envasado WHERE orden = '" + envasado.getProceso() + "'; ";
			else
				consulta = " SELECT * FROM gp_envasado WHERE lote = '" + envasado.getLote() + "'; ";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()) {
				resultado.setIdGestion(rs.getLong("idEnvasado"));
				resultado.setLoteAsignado(rs.getString("lote"));
				resultado.setIdProducto(rs.getLong("idProducto"));
				resultado.setFecha(rs.getDate("fecha"));
				resultado.setFechaCaducidad(rs.getDate("fechaCaducidad"));
				resultado.setCantidad(rs.getInt("cantidad"));
				resultado.setElaborado(rs.getInt("elaborado"));
				resultado.setOperario(rs.getString("usuarioResponsable"));
				resultado.setProceso(rs.getString("orden"));
			}
			//1. Obtenemos la lista de materia prima
			resultado.setMateriaPrima(getLineaProducto(resultado.getProceso(), "", "", 1));
			//2. Obtenemos la lista de envases
			resultado.setEnvases(getLineaProducto(resultado.getProceso(), "", "", 2));
			//2. Obtenemos la lista de EANs13
			resultado.setEans13(getLineaProducto(resultado.getProceso(), "", "", 3));
			//3. Obtenemos los horarios de trabajo
			resultado.setHorarioTrabajado(getHorarioFrom(resultado.getLote(),"envasado",""));
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
	 * ***********************************************************
	 * Obtener la lista de materia prima (ORDEN DE ENVASADO).
	 *
	 * @param idOrden Numero de lote/orden para la cual hacemos la busqueda
	 * @param proceso Nombre del proceso/tabla a la que vamos a consultar (Ej. envasado)
	 * @param filtro tipo M para materia prima, E para envases (Ej.  AND idTipoRegistro='M')
	 * @param option the option
	 * @param idEnvase the id envase
	 * @return Vector <LineaProducto> con los registros encontrados
	 * @throws Exception the exception
	 * @since 1.1
	 * ************************************************************
	 */		
	public Vector <LineaProducto> getLineaProducto(String idOrden, String proceso, String filtro, int option) throws Exception {
		// System.out.println("GET INGREDIENTES FROM: (DAO) ");
		Vector<LineaProducto> resultado = new Vector<LineaProducto>();
		ResultSet rs = null;
		ResultSet rsaux = null;
		try {
			con = bddConexion();
			String consulta = "";
			if (option == 1)//Materia prima
				consulta =
					" SELECT DISTINCT materia.nombre as nombre, cate.nombre as nombreCategoria, re.saldo, mc.idCategoria, " +
						" mc.idMateriaCategoria as id, re.lote as loteProducto, idLinea, detalle.codigoEntrada as entrada, " +
						" cantidadDisponible, cantidadTeorica, cantidadReal, mermaProducto, re.fecha, re.fechaCaducidad " +
					" FROM materiaprima materia " +
						" INNER JOIN materiaprima_categoria mc ON materia.idProducto = mc.idMateriaPrima " +
						" INNER JOIN categoria cate ON mc.idCategoria = cate.idCategoria " +
						" INNER JOIN registroentrada re ON materia.idProducto = re.idProducto AND mc.idCategoria=re.idCategoriaEntrada " +
						" INNER JOIN gp_envasado_detalle as detalle ON re.codigoEntrada = detalle.codigoEntrada " +
						/*" INNER JOIN gp_envasado as envase ON envase.orden = detalle.orden " +*/
					" WHERE detalle.orden = '" + idOrden + "' " +
						" AND detalle.idTipoRegistro='M' " +
					" ORDER BY id; ";
			else
				if (option == 2)//Envase
					consulta =
						" SELECT DISTINCT e.nombre as nombre, re.saldo, e.idEnvase as id, re.lote as loteProducto, " +
							" idLinea, detalle.codigoEntrada as entrada, cantidadDisponible, cantidadTeorica, cantidadReal, " +
							" mermaProducto, re.fecha, re.fechaCaducidad " + 
						" FROM envase e " +
							" INNER JOIN registroentrada re ON e.idEnvase = re.idEnvase " +
							" INNER JOIN gp_envasado_detalle as detalle ON re.codigoEntrada = detalle.codigoEntrada " +
						" WHERE detalle.orden = '" + idOrden + "' " +
							" AND detalle.idTipoRegistro='E' ORDER BY id; ";
				else
					if (option == 3)
						consulta = 
							" SELECT DISTINCT p.nombre, p.idProducto as id, idLinea, detalle.codigoEntrada as entrada," +
								" cantidadDisponible, cantidadTeorica, cantidadReal, mermaProducto " +
							" FROM gp_envasado_detalle detalle " +
								" INNER JOIN gp_envasado gpen ON detalle.codigoEntrada = gpen.lote " +
								" INNER JOIN producto p ON gpen.idProducto = p.idProducto " +
							" WHERE detalle.orden = '" + idOrden + "'; ";
					else
						if (option == 4)//Envase para agrupacion
							consulta =
								"SELECT DISTINCT p.idProducto as idProductoAgrupacion, " +
									" p.nombre as nombreProductoAgrupacion, e.nombre as nombre, " +
									" re.saldo, e.idEnvase as id, re.lote as loteProducto, " +
									" idLinea, detalle.codigoEntrada as entrada, cantidadDisponible, " +
									" cantidadTeorica, cantidadReal, mermaProducto, re.fecha, re.fechaCaducidad " +
								" FROM envase e " +
									" INNER JOIN registroentrada re ON e.idEnvase = re.idEnvase " +
									" INNER JOIN gp_envasado_detalle as detalle ON re.codigoEntrada = detalle.codigoEntrada " +
									" INNER JOIN gp_envasado_agrupacion gpea ON gpea.registroentrada = re.codigoEntrada AND detalle.orden = gpea.ordenEnvasado " +
									" INNER JOIN producto p ON p.idProducto = gpea.idAgrupacion " +
									" INNER JOIN producto_compuesto pc ON p.idProducto = pc.idProducto AND gpea.idAgrupacion = pc.idProducto " +
								" WHERE detalle.orden = '" + idOrden + "' " +
									" AND detalle.idTipoRegistro = 'A' " +
								" ORDER BY id; ";
						else
							consulta =
								" SELECT * " +
								" FROM gp_" + proceso + "_detalle " +
										" WHERE orden = '" + idOrden + "' " + filtro;
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()) {
				LineaProducto lp = new LineaProducto();
				if (option == 1 || option == 2 || option == 4){
					lp.setLote(rs.getString("loteProducto"));
					lp.setRegistroEntrada(rs.getString("entrada"));
					lp.setFecha(rs.getDate("fecha"));
					lp.setFechaCaducidad(rs.getDate("fechaCaducidad"));
					lp.setIdProducto(rs.getLong("id"));
					lp.setNombre(rs.getString("nombre"));
					lp.setDisponible(rs.getDouble("saldo"));
					lp.setIdLinea(rs.getLong("idLinea"));
					if (option == 4){
						lp.setIdProducto(rs.getLong("idProductoAgrupacion"));
						lp.setNombre(rs.getString("nombre") + ". AGRUPACION: " +  rs.getString("nombreProductoAgrupacion"));
					}
					if (option == 1){
						lp.setIdCategoria(rs.getLong("idCategoria"));
						lp.setNombreCategoria(rs.getString("nombreCategoria"));
					}
				} else{
					if (option == 0){
						lp.setRegistroEntrada(rs.getString("codigoEntrada"));
						lp.setIdLinea(rs.getLong("idLinea"));
					}else
						if (option == 3){
							lp.setNombre(rs.getString("nombre"));
							lp.setIdLinea(rs.getLong("idLinea"));
							lp.setIdProducto(rs.getLong("id"));
							lp.setRegistroEntrada(rs.getString("entrada"));
							lp.setDisponible(rs.getDouble("cantidadDisponible"));
						}
					}
				lp.setTeorica(rs.getDouble("cantidadTeorica"));
				lp.setReal(rs.getDouble("cantidadReal"));
				lp.setMermas(rs.getDouble("mermaProducto"));
				consulta =
					" SELECT idUsuario, nombre FROM entidad " +
					" WHERE idUsuario = " +
						" (SELECT idProveedor FROM ordenentrada WHERE codigoOrden = " +
						" (SELECT codigoOrden FROM registroentrada WHERE codigoEntrada = '" + lp.getRegistroEntrada() + "')); ";
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rsaux = ps.executeQuery();
				while (rsaux.next()) {
					lp.setProveedor(rsaux.getString("nombre"));
				}
				//lo añado al vector resultante
				resultado.add(lp);
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
	public Vector <LineaProducto> getEnvasesMateriasEnvasado(String ordenEnvasado, String filtro, int option) throws Exception {
		// System.out.println("getEnvasesMateriasEnvasado (DAO) ");
		Vector<LineaProducto> resultado = new Vector<LineaProducto>();
		ResultSet rs = null, rsaux = null, rsaux1 = null, rsaux2 = null;
		try {
			con = bddConexion();
			String consulta = "";
			if (option == 1)
				consulta =
					"SELECT DISTINCT materia.nombre as nombre, cate.nombre as nombreCategoria, re.saldo, " +
						" mc.idMateriaCategoria as id, re.lote as loteProducto, ubicacion.codigoEntrada as entrada, " +
						" re.fecha, re.fechaCaducidad " +
					" FROM (materiaprima materia, " +
						" materiaprima_categoria mc, categoria cate, gp_envasado_ubicacion as ubicacion ," +
						" gp_envasado as envase, " +
						" registroentrada re)" +
					" WHERE materia.idProducto = mc.idMateriaPrima AND mc.idCategoria = cate.idCategoria " +
						" AND materia.idProducto = re.idProducto AND ubicacion.ordenEnvasado = '" + ordenEnvasado + "' AND " +
						" re.codigoEntrada = ubicacion.codigoEntrada AND mc.idCategoria=re.idCategoriaEntrada order by id";
			else
				if (option == 2)
					consulta =
						"SELECT DISTINCT e.nombre as nombre, re.saldo, e.idEnvase as id," +
							" re.lote as loteProducto, ubicacion.codigoEntrada as entrada, re.fecha," +
							" re.fechaCaducidad, gped.idTipoRegistro " +
						" FROM (envase e, gp_envasado_ubicacion as ubicacion, gp_envasado as envase, " +
							" registroentrada re, gp_envasado_detalle gped) " +
						" WHERE e.idEnvase = re.idEnvase " +
							" AND ubicacion.ordenEnvasado = '" + ordenEnvasado + "' " +
							" AND re.codigoEntrada = ubicacion.codigoEntrada" +
							" AND gped.orden = envase.orden" +
							" AND gped.codigoEntrada = re.codigoEntrada" +
							" AND gped.idTipoRegistro = 'E'" +
						" ORDER BY id";
				else
					if (option == 3)//Productos finales
						consulta =
							"SELECT DISTINCT p.nombre, p.idProducto as id, gpeu.codigoEntrada as entrada, " +
								" env.fecha, env.fechaCaducidad, gpeu.cantidad as saldo, gpeu.idHueco, " +
								" det.idTipoRegistro " +
							" FROM (producto p, gp_envasado_ubicacion as gpeu , gp_envasado as env," +
								" gp_envasado_detalle det,ubicacion u) " +
							" WHERE gpeu.ordenEnvasado = '" + ordenEnvasado + "' " +
								" AND gpeu.ordenEnvasado = env.orden " +
								" AND det.idProducto = p.idProducto " +
								" AND det.orden = env.orden " +
								" AND gpeu.idHueco = u.idHueco " +
								" AND gpeu.codigoEntrada = det.codigoEntrada " +
								" AND idTipoRegistro = 'P'" +
							" ORDER BY id";
					else
						if (option == 4){
							consulta =
								"SELECT DISTINCT p.idProducto as idProductoAgrupacion, " +
									" p.nombre as nombreProductoAgrupacion,e.nombre as nombre, " +
									" re.saldo, e.idEnvase as id, " +
									" re.lote as loteProducto, gpeu.codigoEntrada as entrada, " +
									" re.fecha, re.fechaCaducidad, gped.idTipoRegistro " +
								" FROM envase e, gp_envasado_ubicacion gpeu, gp_envasado gpe, " +
									" registroentrada re, gp_envasado_detalle gped, producto p, " +
									" producto_compuesto pc, gp_envasado_agrupacion gpea " +
								" WHERE e.idEnvase = re.idEnvase " +
									" AND gpeu.ordenEnvasado = '" + ordenEnvasado + "' " +
									" AND gpeu.ordenEnvasado = gped.orden " +
									" AND gpeu.codigoEntrada = re.codigoEntrada " +
									" AND gped.orden = gpea.ordenEnvasado " +
									" AND re.codigoEntrada = gped.codigoEntrada " +
									" AND gped.orden = gpe.orden " +
									" AND gped.idTipoRegistro = 'A' " +
									" AND gpea.idAgrupacion = pc.idProducto " +
									" AND gpea.registroEntrada = re.codigoEntrada " +
									" AND p.idProducto = gpea.idAgrupacion " +
								" ORDER BY id";
						}
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()) {
				String entrada = rs.getString("entrada");
				if (option != 3)
					//Buscamos la entrada con la ordenEnvasado en gp_envasado_ubicacion
					consulta =
						" SELECT ordenEnvasado, codigoEntrada, geu.idHueco, geu.cantidad, geu.cantidadReal, " +
							" geu.mermas, idHuecoNuevo, uh.descripcion, geu.idPalet " +
						" FROM gp_envasado_ubicacion geu, ubicacion_hueco uh " +
						" WHERE geu.ordenEnvasado = '" + ordenEnvasado + "' " +
							" AND geu.codigoEntrada = '" + entrada + "' " +
							" AND uh.idHueco = geu.idHueco";
				else
					consulta =
						" SELECT ordenEnvasado, codigoEntrada, geu.idHueco, geu.cantidad, geu.cantidadReal, " +
							" geu.mermas, idHuecoNuevo, uh.descripcion, geu.idPalet " +
						" FROM gp_envasado_ubicacion geu, ubicacion_hueco uh " +
						" WHERE geu.ordenEnvasado = '" + ordenEnvasado + "' " +
							" AND geu.codigoEntrada = '" + entrada + "' " +
							" AND uh.idHueco = geu.idHueco " +
							" AND uh.idHueco = " + rs.getLong("idHueco");
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rsaux = ps.executeQuery();
				while (rsaux.next()) {
					LineaProducto lp = new LineaProducto();
					//lp.setIdUbicacion(rsaux.getLong("hueco"));
					lp.setIdUbicacion(rsaux.getLong("idHueco"));
					lp.setCantidadUbicacion(rsaux.getDouble("cantidad"));
					lp.setIdProducto(rs.getLong("id"));
					lp.setIdPalet(rsaux.getLong("idPalet"));
					if (option != 3)
						lp.setLote(rs.getString("loteProducto"));
					lp.setRegistroEntrada(rs.getString("entrada"));
					lp.setFecha(rs.getDate("fecha"));
					lp.setFechaCaducidad(rs.getDate("fechaCaducidad"));
					lp.setIdProveedor(rs.getLong("id"));
					lp.setNombre(rs.getString("nombre"));
					if (option == 4){
						lp.setNombre(rs.getString("nombre") + ". AGRUPACION: " + rs.getString("nombreProductoAgrupacion"));
						lp.setIdProducto(rs.getLong("idProductoAgrupacion"));
					}
					lp.setDisponible(rs.getDouble("saldo"));
					if (option == 1)
						lp.setNombreCategoria(rs.getString("nombreCategoria"));
					lp.setIdLinea(rs.getLong("id"));
					lp.setTeorica(rsaux.getDouble("cantidad"));
					lp.setReal(rsaux.getDouble("cantidadReal"));
					lp.setMermas(rsaux.getDouble("mermas"));
					lp.setRefUbicacion(rsaux.getString("descripcion"));
					//lp.setLote(rs.getString("orden"));
					//En idProveedor le pasamos el id del envase o de la materia
					//Get idProveedor&proveedor
					consulta="SELECT idUsuario, nombre FROM entidad WHERE idUsuario=(SELECT idProveedor FROM ordenentrada " +
							" WHERE codigoOrden= (SELECT codigoOrden FROM registroentrada WHERE codigoEntrada='" + lp.getRegistroEntrada()+"'))";
					// System.out.println(consulta);
					ps = con.prepareStatement(consulta);
					rsaux1 = ps.executeQuery();
					while (rsaux1.next()) {
						lp.setProveedor(rsaux1.getString("nombre"));
					}						
					long idHuecoNuevo = rsaux.getLong("idHuecoNuevo");
					//long idHuecoAux = rsaux.getLong("idHueco");
					if (idHuecoNuevo > 0){
						consulta = "SELECT uh.descripcion FROM ubicacion_hueco uh WHERE uh.idHueco = " + idHuecoNuevo;
						// System.out.println(consulta);
						ps = con.prepareStatement(consulta);
						rsaux2 = ps.executeQuery();
						while (rsaux2.next()) {
							lp.setRefUbicacionNueva(rsaux2.getString("descripcion"));
						}
					}
					//lo añado al vector resultante
					resultado.add(lp);
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

	/**
	 * ***********************************************************
	 * Obtener la lista horaria de lo trabajado (ORDEN DE ENVASADO).
	 *
	 * @param idOrden Numero de lote/orden para la cual hacemos la busqueda
	 * @param proceso Nombre del proceso/tabla a la que vamos a consultar (Ej. envasado)
	 * @param filtro restricciones a aplicar
	 * @return Vector <LineaProducto> con los registros encontrados
	 * @throws Exception the exception
	 * @since 1.1
	 * ************************************************************
	 */	
	private Vector <RegistroActividad> getHorarioFrom (String idOrden, String proceso, String filtro) throws Exception {
		// System.out.println("GET HORARIO FROM: (DAO) ");
		Vector<RegistroActividad> resultado = new Vector<RegistroActividad>();
		try {
			con = bddConexion();
			String consulta="SELECT * FROM  gp_"+proceso+"_registroactividad WHERE idOrden='"+idOrden+"' "+filtro;
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()) {
				RegistroActividad ra = new RegistroActividad();
				ra.setIdRegistro(rs.getLong("idRegistro"));
				ra.setHoraInicio(rs.getTimestamp("horaInicio"));
				ra.setHoraFin(rs.getTimestamp("horaFin"));
				ra.setResponsable(rs.getString("responsable"));
				ra.setObservaciones(rs.getString("observaciones"));
				//lo añado al vector resultante
				resultado.add(ra);
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
	 * **********************************************************.
	 *
	 * @param list the list
	 * @return the boolean
	 * @throws Exception the exception
	 */
	/**  Devolver el stock sobrante de los productos de una lista 
	 * @param list lista de elementos a los cuales vamos a devolver el stock
	 * @return True si se ha devuelto correctamente
	 * @since 1.1
	**************************************************************/
	private Boolean returnStock (List list, String ordenEnvasado) throws Exception {
		// System.out.println("Devolviendo el stock");
		Statement stmt;	
		Boolean resultado = false;
		try {
			con = bddConexion();
			Iterator it = list.iterator();
			while(it.hasNext()){
				LineaProducto lp = (LineaProducto) it.next();
				// System.out.println("Reservamos: " + lp.getTeorica() + " ,Usamos: " + lp.getReal() + " ,Mermas: " + lp.getMermas());
				Double aDevolver = lp.getTeorica() - lp.getReal() - lp.getMermas();
				// System.out.println("Nos salen a devolver: " + aDevolver);
				//1. miramos cuanto stock hay actual
				String consulta =
					" SELECT codigoEntrada FROM gp_envasado_detalle WHERE idLinea = '" + lp.getIdLinea() + "'";
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rs = ps.executeQuery();
				String codigo = "";
				if (rs.next()){
					codigo = rs.getString("codigoEntrada");
				}
				String tipoRegistro = "";
				if (codigo.charAt(0) == 'E'){
					consulta =
						" SELECT saldo, idTipoRegistro, idEnvase, idProducto, idCategoria " +
						" FROM registroentrada " +
						" WHERE codigoEntrada = '" + codigo + "'";
				}else{
					if (codigo.charAt(0) == '0'){
						tipoRegistro = "P";
						consulta =
							" SELECT u.cantidad, gp.idProducto " +
							" FROM ubicacion u, gp_envasado gp " +
							" WHERE u.registro = gp.lote AND " +
								" registro = '" + codigo + "' AND idHueco = 223; ";
					}
				}
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rs = ps.executeQuery();
				Double saldo = null;
				int idMateria = 0, idEnvase = 0, idProducto = 0, idCategoria = 0;
				if (rs.next()) { 
					 if (codigo.charAt(0) == 'E'){
						 tipoRegistro = rs.getString("idTipoRegistro");
						 // System.out.println("Tipo de registro (M ó E ó P): " + tipoRegistro);
						 saldo = rs.getDouble("saldo");
						 // System.out.println("Nos queda en almacen: " + saldo);
						 idEnvase = rs.getInt("idEnvase");
						 // System.out.println("Id del envase: " + idEnvase);
						 idMateria = rs.getInt("idProducto");
						 // System.out.println("Id de la materia: " + idMateria);
						 idCategoria = rs.getInt("idCategoria");
						 // System.out.println("Id de la categoria: " + idCategoria);
						 idProducto = rs.getInt("idProducto");
						 // System.out.println("Id del EAN13: " + idProducto);
					 }else{
						 tipoRegistro = "P";
						 idProducto = rs.getInt("idProducto");
						 saldo = rs.getDouble("cantidad");
					 }
				}else{
					//Se trata de un producto de envasado, del que ya no queda nada en salida
				}
				//2. aumentamos lo no utilizado (restando mermas) en las tablas materiaprima y envase
				if (tipoRegistro.compareTo("E") == 0){
					consulta = "UPDATE envase SET stock=stock+" + aDevolver +
						" WHERE idEnvase='" + idEnvase + "'";
				} else{
					if (tipoRegistro.compareTo("M") == 0){
						consulta = "UPDATE materiaprima_categoria SET stock=stock+" + aDevolver + " " +
								" WHERE idMateriaPrima='" + idMateria + "' AND idCategoria = " + idCategoria;
						// System.out.println(consulta);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(consulta);
						consulta = "UPDATE materiaprima SET stock=stock+ " + aDevolver + " WHERE idProducto='" + idMateria + "'";
					}else{
						if (tipoRegistro.compareTo("P") == 0){
							consulta = "UPDATE producto SET stock = stock + "
							+ aDevolver + " WHERE idProducto='" + idProducto + "'";
						}else{
							throw new Exception("Error en la base de datos. Un registro debe ser MATERIAPRIMA ó ENVASE ó PRODUCTO FINAL");
						}
					}
				}
				// System.out.println(consulta);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(consulta);
				//3. le aumentamos lo no utilizado (restando mermas)
				consulta = "UPDATE registroentrada SET saldo =(" + saldo + " + " + aDevolver + ") WHERE codigoEntrada=(" +
						"SELECT codigoEntrada FROM gp_envasado_detalle WHERE idLinea='" + lp.getIdLinea() + "')";
				// System.out.println(consulta);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(consulta);
				//4. actualizamos el almacen, si ya hemos sacado los regitros
				String cons =
					" SELECT * " +
					" FROM gp_envasado_ubicacion " +
					" WHERE ordenEnvasado='" + ordenEnvasado + "' AND codigoEntrada =( " +
						" SELECT codigoEntrada FROM gp_envasado_detalle WHERE idLinea='" + lp.getIdLinea() + "')";
				// System.out.println(cons);
				PreparedStatement ps2 = con.prepareStatement(cons);
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()){
					long idHueco = rs2.getLong("idHueco");
					long idPalet = rs2.getLong("idPalet");
					double cantidad = rs2.getDouble("cantidad");
					String codigoEntrada = rs2.getString("codigoEntrada");
					//Miramos si todavía existe el registro en la tabla ubicación.
					//si no existe, es que ya lo sacamos todo, tendriamos que crear una nueva fila en la tabla
					String consAux =
						" SELECT * " +
						" FROM ubicacion " +
						" WHERE registro = '" + codigoEntrada + "' AND idHueco = " + idHueco + " AND idPalet = " + idPalet + ";";
					// System.out.println(consAux);
					PreparedStatement ps3 = con.prepareStatement(consAux);
					ResultSet rs3 = ps3.executeQuery();
					if (rs3.next()){
						consulta =
							" UPDATE ubicacion " +
							" SET cantidad = cantidad + " + cantidad + " " +
							" WHERE idHueco = " + idHueco + " AND registro = '" + codigoEntrada + "' " +
									" AND idPalet = " + idPalet + ";";
					}else{
						consulta =
							" INSERT INTO ubicacion (idHueco, registro, numeroBultos, cantidad, idPalet, orden, congelado, habilitado) " +
							" VALUES(" + idHueco + ", '" + codigoEntrada + "',1," + cantidad + "," + idPalet + ",'','N','S'); ";
					}
					// System.out.println(consulta);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(consulta);
				}
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
	 * **********************************************************.
	 *
	 * @param orden the orden
	 * @param mensaje the mensaje
	 * @param responsable the responsable
	 * @param proceso the proceso
	 * @return the boolean
	 * @throws Exception the exception
	 */
	/**  Establecer marca de tiempo
	 * @param orden 
	 * @param mensaje
	 * @param responsable usuario que deja constancia
	 * @param proceso para el cual se hace el registro (Ej. envasado)
	 * @return True si se ha devuelto correctamente
	 * @since 1.1
	**************************************************************/
	private Boolean setHorarioActividad (String orden, String mensaje, String responsable, String proceso) throws Exception {
		// System.out.println("Devolviendo el stock");
		Statement stmt;	
		Boolean resultado=false;
		try {
			con = bddConexion();
			String consulta = "insert into gp_"+proceso+"_registroactividad (idOrden,horaInicio,horaFin,responsable,observaciones)" +
					"values('"+orden+"',now(),now(),'"+responsable+"','"+mensaje+"')";		
			// System.out.println(consulta);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(consulta);
			resultado=true;					
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

	public GestionProduccion getInfoProcesosEnv(String orden, String filtro) throws Exception{
		GestionProduccion gp = new GestionProduccion();
		try {
			con = bddConexion();
			String cons =
				" SELECT pc.idCompuestoDe, p.idProducto, en.lote " +
				" FROM gp_envasado en" +
					" INNER JOIN producto_compuesto pc ON en.idProducto = pc.idProducto " +
					" INNER JOIN producto p ON p.idProducto = pc.idCompuestoDe " +
				" WHERE en.orden = '" + orden + "'; ";
			// System.out.println(cons);
			ps = con.prepareStatement(cons);
			rs = ps.executeQuery();
			boolean ean14 = false;
			if (rs.next()){
				ean14 = true;
				gp.setTipoEan(14);
			}else{
				gp.setTipoEan(13);
			}
			String consulta =
				" SELECT DISTINCT envasado.idProducto, envasado.cantidad, " +
					" elaborado, envasado.lote, horainicio, envasado.orden, " +
					" envasado.idProducto, envasado.estadoproceso " +
				" FROM gp_envasado envasado" +
					" INNER JOIN gp_envasado_detalle detalle ON envasado.orden = detalle.orden " +
				" WHERE envasado.orden = '" + orden + "' " + filtro;
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()){
				gp.setCantidadProducto(rs.getDouble("cantidad"));
				gp.setCantidadElaborada(rs.getDouble("elaborado"));
				gp.setLoteAsignado(rs.getString("lote"));
				gp.setHoraInicioProceso(rs.getString("horainicio"));
				gp.setOrden(rs.getString("orden"));
				gp.setIdProducto(rs.getLong("idProducto"));
				gp.setEstadoProceso(rs.getString("estadoproceso"));
				if (ean14){
					gp.setAgrupar(false);
				}else{
					consulta =
						" SELECT gpea.elaborado " +
						" FROM gp_envasado_agrupacion gpea, gp_envasado gpe " +
						" WHERE gpe.orden = gpea.ordenEnvasado " +
							" AND gpea.idAgrupacion != -1 " +
							" AND gpe.orden = '" + orden + "'";
					// System.out.println(consulta);
					PreparedStatement ps2 = con.prepareStatement(consulta);
					ResultSet rs2 = ps2.executeQuery();
					if (rs2.next()){
						gp.setCantidadElaboradaAgrupacion(rs2.getDouble("elaborado"));
						gp.setAgrupar(true);
					}else{
						gp.setAgrupar(false);
					}
					rs2.close();
					ps2.close();
				}
			}
			rs.close();
			ps.close();
			con.close();
			return gp;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	/**
	 * A partir del id de gestión de envasado, obtenemos el id del producto que ha sido envasado.
	 *
	 * @param id Identificador de la gestión de envasado
	 * @return id Identificador del producto envasado
	 * @throws Exception Fallo obteniendo el identificador del producto
	 */
	public int idGestionToProducto(int id) throws Exception{
		int retorno = 0;
		try {
			con = bddConexion();
			String consulta =
				" SELECT idProducto " +
				" FROM gp_envasado" +
				" WHERE idEnvasado = " + id;
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			rs.next();
			retorno = rs.getInt("idProducto");
			return retorno;
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
	 * Devuelve un vector con todos los envases (de los cuales hay stock en tabla envases) asociados a un producto
	 * 
	 * @param idProducto Identificador del producto
	 * @param filtro Filtro utilizado para buscar el producto
	 * @return Envases asociados al producto idProducto
	 * @throws Exception Fallo obteniendo los envases asociados al producto
	 * @since 1.3
	 * @author Induserco, Andrés
	 */
	//@Override
	public Vector<LineaProducto> getEnvasesProducto(Long idProducto, String filtro) throws Exception {
		Vector<LineaProducto> retorno = new Vector<LineaProducto>();
		try {
			String query =
				" SELECT DISTINCT re.saldo, p.cantidad, e.nombre, re.codigoEntrada, re.idEnvase," +
					" re.codigoOrden orden, re.habilitado, re.codigoEntrada entrada, re.fecha, re.fechaCaducidad, e.stock, " +
					" (SELECT lote FROM ordenentrada oe WHERE oe.codigoOrden = re.codigoOrden) lote " +
				" FROM producto_envase p " +
					" INNER JOIN registroEntrada re ON p.idEnvase = re.idEnvase " +
					" INNER JOIN envase e ON e.idEnvase = re.idEnvase " +
				" WHERE p.idProducto = " + idProducto +
					" AND re.idTipoRegistro = 'E'" +
					" AND re.habilitado = 'S'" +
					//" AND re.saldo > 0" +  filtro + " ORDER BY idEnvase";
					" AND e.stock > 0 " +  filtro + " " +
				" ORDER BY re.fechaLlegada ASC ";
			con = bddConexion();
			// System.out.println(query);
			PreparedStatement p = con.prepareStatement(query);
			ResultSet result = p.executeQuery();
			while (result.next()) {
				LineaProducto linea = new LineaProducto();
				String orden = result.getString("orden");
				String consulta = " SELECT en.nombre FROM ordenentrada oe, entidad en" +
					" WHERE oe.codigoOrden = '" + orden + "'" +
					" AND en.idUsuario = oe.idProveedor";
				// System.out.println("SQL: " + consulta);
				Connection c = bddConexion();
				PreparedStatement pre = c.prepareStatement(consulta);
				ResultSet res = pre.executeQuery();
				while (res.next())
					linea.setProveedor(res.getString("nombre"));
				pre.close();
				c.close();
				//Le pasamos la cantidad del envase asociado al producto, en el campo teorica
				linea.setTeorica(result.getDouble("cantidad"));
				linea.setIdLinea(result.getLong("idEnvase"));
				linea.setNombre(result.getString("nombre"));
				linea.setHabilitado(result.getString("habilitado"));
				linea.setEntrada(result.getString("entrada"));
				linea.setFecha(result.getDate("fecha"));
				linea.setFechaCaducidad(result.getDate("fechaCaducidad"));
				linea.setDisponible(result.getDouble("saldo"));
				System.out.println(linea.getDisponible());
				//linea.setDisponible(result.getDouble("stock"));
				linea.setLote(result.getString("lote"));
				retorno.add(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return retorno;
	}

	/**
	 * Devuelve un vector con todas las materias primas asociadas a un producto
	 *
	 * @param idProducto Identificador del producto
	 * @param filtro Filtro utilizado para buscar el producto
	 * @return Materias primas asociadas al producto idProducto
	 * @throws Exception Fallo obteniendo las materias primas asociadas al producto
	 */
	//@Override
	public Vector<LineaProducto> getIngredientesProducto(Long idProducto, String filtro) throws Exception {
		Vector<LineaProducto> retorno = new Vector<LineaProducto>();
		try {
			con = bddConexion();
			String query =
				" SELECT DISTINCT p.cantidad, m.nombre, c.nombre as nombreCategoria, " +
					" mc.idMateriaCategoria, re.codigoOrden orden, re.habilitado, " +
					" re.codigoEntrada entrada, re.saldo, re.fecha, re.fechaCaducidad, " +
					" IF ((SELECT lote FROM ordenentrada oe WHERE oe.codigoOrden = re.codigoOrden) IS NOT NULL, (SELECT lote FROM ordenentrada oe WHERE oe.codigoOrden = re.codigoOrden), re.codigoEntrada) AS lote " +
				" FROM producto_materiaprima p " +
					" INNER JOIN materiaprima_categoria mc ON p.idMateriaPrima = mc.idMateriaCategoria " +
					" INNER JOIN materiaprima m ON m.idProducto = mc.idMateriaPrima " +
					" INNER JOIN categoria c ON mc.idCategoria = c.idCategoria " +
					" INNER JOIN registroEntrada re ON re.idCategoria = mc.idCategoria " +
					  	" AND mc.idMateriaPrima = re.idProducto " +
				" WHERE p.idProducto = " + idProducto +
					" AND (re.idTipoRegistro = 'M' OR re.idTipoRegistro = 'S') " +
					" AND re.habilitado = 'S' AND mc.stock > 0 AND re.saldo > 0 " +
				" ORDER BY lote; ";
			// System.out.println(query);
			PreparedStatement p = con.prepareStatement(query);
			ResultSet result = p.executeQuery();
			while (result.next()) {
				LineaProducto linea = new LineaProducto();
				linea.setEntrada(result.getString("entrada"));
				linea.setDisponible(result.getDouble("saldo"));
				String consulta =
					" SELECT SUM(u.cantidad) AS cantidad " +
					" FROM ubicacion u " +
					" WHERE u.registro = '" + linea.getEntrada() + "' AND habilitado = 'S'; ";
				//Vamos a comprobar que la cantidad ubicada para esa entrada coincide con el saldo del regisro de entrada
				// System.out.println(consulta);
				PreparedStatement p2 = con.prepareStatement(consulta);
				ResultSet result2 = p2.executeQuery();
				double cantidadUbicada = 0;
				while (result2.next()) {
					cantidadUbicada = (result2.getDouble("cantidad"));
				}
				if (linea.getDisponible() != cantidadUbicada){
					//Tenemos algún desfase en la base de datos en la tabla del registro de entrada.
					//Actualizamos la tabla registro de entrada, y le ponemos como saldo la cantidad que hay en realidad ubicada
					String update =
						" UPDATE registroentrada " +
						" SET saldo = " + cantidadUbicada + " " +
						" WHERE codigoEntrada = '" + linea.getEntrada() + "'; ";
					// System.out.println(update);
					Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(update);
					linea.setDisponible(cantidadUbicada);
				}
				String orden = result.getString("orden");
				consulta = "SELECT en.nombre FROM ordenentrada oe, entidad en" +
					" WHERE oe.codigoOrden = '" + orden + "'" +
					" AND en.idUsuario = oe.idProveedor";
				// System.out.println("SQL: " + consulta);
				PreparedStatement pre = con.prepareStatement(consulta);
				ResultSet res = pre.executeQuery();
				while (res.next())
					linea.setProveedor(res.getString("nombre"));
				linea.setTeorica(result.getDouble("cantidad"));
				linea.setIdLinea(result.getLong("idMateriaCategoria"));
				linea.setNombre(result.getString("nombre"));
				linea.setHabilitado(result.getString("habilitado"));
				linea.setFecha(result.getDate("fecha"));
				linea.setFechaCaducidad(result.getDate("fechaCaducidad"));
				linea.setLote(result.getString("lote"));
				linea.setNombreCategoria(result.getString("nombreCategoria"));
				retorno.add(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return retorno;
	}
	
	/**
	 * Actualiza registro actividad envasado.
	 *
	 * @param ordenEnvasado the orden envasado
	 * @param operario the operario
	 * @param observaciones the observaciones
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean actualizaRegistroActividadEnvasado(String ordenEnvasado, String operario, String observaciones) throws Exception{
		Statement stmt;
		try{
			con = bddConexion();
			String insertString =
				" INSERT INTO gp_envasado_registroactividad " +
					"(idRegistro, idOrden, horaInicio, horaFin, responsable, observaciones)" +
				" VALUES (null" +
					",'" + ordenEnvasado + "'" +
					",now(),null,'"+ operario + "', '" + observaciones + "')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
			} catch (Exception e) { e.printStackTrace(); }
		}
		return true;
	}
	
	/**
	 * Actualiza toda la información sobre un proceso de envasado cuando ésta se modifica.
	 *
	 * @param cantidad Cantidad de productos finales
	 * @param orden Orden del proceso de envasado
	 * @param fechaInicio Fecha de inicio para el proceso de envasado
	 * @param operario Operario responsable del proceso de envasado
	 * @param observaciones Observaciones respecto al proceso de envasado
	 * @param materias Materias reservadas para el proceso de envasado
	 * @param envases Envases reservados para el proceso de envasado
	 * @param materiasLimpiar Materias que hay que liberar del proceso de envasado
	 * @param envasesLimpiar Envases que hay que liberar del proceso de envasado
	 * @return true, Si el proceso de envasado se actualiza correctamente
	 * @throws Exception La actualización del proceso de envasado falla
	 */
	//@Override
	public boolean actualizaProcesoEnvasado(int cantidad, String orden,
			String fechaInicio, String operario, String observaciones, ArrayList materias, ArrayList envases,
			ArrayList materiasLimpiar, ArrayList envasesLimpiar) throws Exception{
		// System.out.println("Actualizacion proceso envasado");
		boolean retorno = false;
		Statement stmt;
		try {
			con = bddConexion();
			//1. Actualiza el registro de actividad del proceso de envasado
			actualizaRegistroActividadEnvasado(orden, operario, observaciones);
			//2. Actualizar en gp_envasado: horainicio y cantidad
			String consulta = "UPDATE gp_envasado SET horainicio = '" + fechaInicio +
				"', cantidad = '" + cantidad + "' WHERE orden = '" + orden + "'";
			// System.out.println(consulta);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(consulta);
			/* ACTUALIZAMOS LAS MATERIAS PRIMAS */
			for (int i = 0; i < materias.size(); i++){
				LineaProducto materia = (LineaProducto)materias.get(i);
				String entrada = materia.getEntrada();
				long idMateria = 0;
				double stockViejo = 0, saldoViejo = 0, teoricaVieja = 0,
					disponible = 0, teorica = materia.getTeorica();
				//Sacamos ya el id de la materia
				String Qry =
					" SELECT m.idProducto, r.saldo"
					+ " FROM materiaprima m, registroentrada r"
					+ " WHERE r.codigoEntrada = '" + entrada + "'"
					+ " AND r.idProducto = m.idProducto";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					idMateria = rs.getLong("idProducto");
					disponible = rs.getDouble("saldo");
				}
				//3. Actualizar en gp_envasado_detalle, para el codigo de entrada 'entrada'
				//teorica
				//Guardamos antes la teoricaVieja
				Qry = "SELECT envasado.cantidadTeorica"
					+ " FROM gp_envasado_detalle envasado"
					+ " WHERE envasado.codigoEntrada = '" + entrada + "' AND envasado.orden = '" + orden + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					teoricaVieja = rs.getDouble("cantidadTeorica");
				}
				//Si el ingrediente todavía no está en la tabla, lo añadimos
				boolean flag = false;
				Qry = "SELECT envasado.orden"
					+ " FROM gp_envasado_detalle envasado"
					+ " WHERE envasado.codigoEntrada = '" + entrada + "' AND envasado.orden = '" + orden + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()){
					flag = true;
					break;
				}
				if (!flag){//Meter el ingrediente en la gp_envasado_detalle
					String insertString = "INSERT INTO gp_envasado_detalle " +
						"(idTipoRegistro, orden, codigoEntrada, idProducto, cantidadDisponible, cantidadTeorica, cantidadReal," +
						"mermaProducto, descripcion) " +
						"VALUES ('M', '" + orden + "', '" + entrada + "', " + idMateria + ", " + disponible +
						", " + teorica + ", 0, 0, '')";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);	
				}
				actualizacionGenerica("gp_envasado_detalle as detalle", "cantidadTeorica",
					String.valueOf(teorica), "detalle.codigoEntrada = '" + entrada + "'");
				//4. Actualizar stock en materiaprima
				Qry = "SELECT m.stock"
					+ " FROM materiaprima m"
					+ " WHERE m.idProducto = '" + idMateria + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					stockViejo = rs.getDouble("stock");
				}
				if ((teorica - teoricaVieja) != 0){
					double nuevoStock = (stockViejo) - (teorica - teoricaVieja);
					this.actualizacionGenerica("materiaprima", "stock", String.valueOf(nuevoStock),
							"materiaprima.idProducto = '" + idMateria + "'");
				}
				//5. Actualizar saldo en registroEntrada
				Qry = "SELECT re.saldo"
					+ " FROM registroentrada re"
					+ " WHERE re.codigoEntrada = '" + entrada + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					saldoViejo = rs.getDouble("saldo");
				}
				if ((teorica - teoricaVieja) != 0){
					double modifica = (saldoViejo) - (teorica - teoricaVieja);
					actualizacionGenerica("registroentrada", "saldo",
						String.valueOf(modifica),
						"registroentrada.codigoEntrada = '" + entrada + "'");
				}
			}
			/* ACTUALIZAMOS ENVASES */
			for (int i = 0; i < envases.size(); i++){
				LineaProducto envase = (LineaProducto)envases.get(i);
				String entrada = envase.getEntrada();
				long idEnvase = 0;
				double stockViejo = 0, saldoViejo = 0, teoricaVieja = 0, disponible = 0, teorica = envase.getTeorica();
				
				//Sacamos ya el id de la materia
				//Lo primero que necesitamos es el id del envase
				String Qry = "SELECT e.idEnvase, r.saldo"
					+ " FROM envase e, registroentrada r"
					+ " WHERE r.codigoEntrada = '" + entrada + "'"
					+ " AND r.idEnvase = e.idEnvase";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					idEnvase = rs.getLong("idEnvase");
					disponible = rs.getDouble("saldo");
				}
				//3. Actualizar en gp_envasado_detalle, para el codigo de entrada 'entrada'
				//teorica
				//Antes de actualizar guardamos la teoricaVieja
				Qry = "SELECT envasado.cantidadTeorica"
					+ " FROM gp_envasado_detalle envasado"
					+ " WHERE envasado.codigoEntrada = '" + entrada + "' AND envasado.orden = '" + orden + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					teoricaVieja = rs.getDouble("cantidadTeorica");
				}
				//Si el envase todavía no está en la tabla, lo añadimos
				boolean flag = false;
				Qry = "SELECT envasado.orden"
					+ " FROM gp_envasado_detalle envasado"
					+ " WHERE envasado.codigoEntrada = '" + entrada + "' AND envasado.orden = '" + orden + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()){
					flag = true;
					break;
				}
				if (!flag){//Meter el envasado en la gp_envasado_detalle
					//int res = 0;
					String insertString = "INSERT INTO gp_envasado_detalle " +
						"(idTipoRegistro, orden, codigoEntrada, idProducto, cantidadDisponible, cantidadTeorica, cantidadReal," +
						"mermaProducto, descripcion) " +
						"VALUES ('E', '" + orden + "', '" + entrada + "', " + idEnvase + ", " + disponible +
						", " + teorica + ", 0, 0, '')";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
				}
				actualizacionGenerica("gp_envasado_detalle as detalle", "cantidadTeorica",
					String.valueOf(teorica), "detalle.codigoEntrada = '" + entrada + "'");
				//4. Actualizar stock en envase
				Qry = "SELECT e.stock"
					+ " FROM envase e"
					+ " WHERE e.idEnvase = '" + idEnvase + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					stockViejo = rs.getDouble("stock");
				}
				if ((teorica - teoricaVieja) != 0){
					double nuevoStock = (stockViejo) - (teorica - teoricaVieja);
					this.actualizacionGenerica("envase", "stock", String.valueOf(nuevoStock),
						"envase.idEnvase = '" + idEnvase + "'");
				}
				//5. Actualizar saldo en registroEntrada
				Qry = "SELECT re.saldo"
					+ " FROM registroentrada re"
					+ " WHERE re.codigoEntrada = '" + entrada + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					saldoViejo = rs.getDouble("saldo");
				}
				if ((teorica - teoricaVieja) != 0){
					double modifica = (saldoViejo) - (teorica - teoricaVieja);
					if (modifica != 0)
						actualizacionGenerica("registroentrada", "saldo",
							String.valueOf(modifica),
							"registroentrada.codigoEntrada = '" + entrada + "'");
				}
			}
			//Limpiar envases y materias eliminadas del proceso: devolver stocks, quitar de envasado_detalle
			liberarEnvases(envasesLimpiar, orden);
			liberarMaterias(materiasLimpiar, orden);
			retorno = true;
		}catch (Exception e){
			e.printStackTrace();
			return retorno;
		}
		finally {
			try {
				con.close();
			} catch (Exception e) {	e.printStackTrace(); return false; }
		}
		return retorno;
	}
	
	/**
	 * Libera los envases que estaban reservados para un determinado proceso de envasado
	 *
	 * @param envases Los envases que se deben eliminar
	 * @param orden Orden de envasado donde se deben de eliminar los envases
	 * @return true, Si las cantidades reservadas de los envases se liberan con éxito
	 */
	private boolean liberarEnvases(ArrayList envases, String orden){
		Statement stmt;
		try {
			for (int i = 0; i < envases.size(); i++){
				long idEnvase = 0;
				LineaProducto l = (LineaProducto)envases.get(i);
				String entrada = l.getEntrada();
				//Necesitaremos el id del envase
				con = bddConexion();
				String Qry = "SELECT e.idEnvase"
					+ " FROM envase e, registroentrada r"
					+ " WHERE r.codigoEntrada = '" + entrada + "'"
					+ " AND r.idProducto = e.idEnvase";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					idEnvase = rs.getLong("idEnvase");
				}
				ps.close();
				//1. Quitamos de _detalle el envase
				// SQL de delete
				String deleteString = "DELETE FROM gp_envasado_detalle WHERE orden = '" + orden
					+ "' AND codigoEntrada = '" + entrada + "'";
				// System.out.println(deleteString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(deleteString);
				double reservado = l.getTeorica();
				//2. Devolver stock en registroentrada
				double stockActual = 0;
				Qry = "SELECT re.saldo"
					+ " FROM registroentrada re"
					+ " WHERE re.codigoEntrada = '" + entrada + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					stockActual = rs.getDouble("saldo");
				}
				ps.close();
				con.close();
				//Sumarle al stock actual la cantidad reservada
				double stockFinal = stockActual + reservado;				
				//3. Con el id del envase, vamos a tabla 'envase' y devolvemos el stock
				actualizacionGenerica("envase as e", "stock",
						String.valueOf(stockFinal), "e.idEnvase = '" + idEnvase + "'");
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Libera las materias que estaban reservadas para un determinado proceso de envasado
	 *
	 * @param materias Las materias que se deben eliminar
	 * @param orden Orden de envasado donde se deben de eliminar las materias
	 * @return true, Si las cantidades reservadas de las materias se liberan con éxito
	 */
	private boolean liberarMaterias(ArrayList materias, String orden){
		Statement stmt;
		try {
			for (int i = 0; i < materias.size(); i++){
				long idMateria = 0;
				LineaProducto l = (LineaProducto)materias.get(i);
				String entrada = l.getEntrada();
				//Necesitaremos el id de la materia
				con = bddConexion();
				String Qry = "SELECT m.idProducto"
					+ " FROM materiaprima m, registroentrada r"
					+ " WHERE r.codigoEntrada = '" + entrada + "'"
					+ " AND r.idProducto = m.idProducto";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					idMateria = rs.getLong("idProducto");
				}
				ps.close();
				//1. Quitamos de _detalle la materia
				// SQL de delete
				String deleteString = "DELETE FROM gp_envasado_detalle WHERE orden = '" + orden
					+ "' AND codigoEntrada = '" + entrada + "'";
				// System.out.println(deleteString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(deleteString);
				double reservado = l.getTeorica();
				//2. Devolver stock en registroentrada
				double stockActual = 0;
				Qry = "SELECT re.saldo"
					+ " FROM registroentrada re"
					+ " WHERE re.codigoEntrada = '" + entrada + "'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					stockActual = rs.getDouble("saldo");
				}
				ps.close();
				con.close();
				//Sumarle al stock actual la cantidad reservada
				double stockFinal = stockActual + reservado;
				//3. Con el id de la materia, vamos a materiaPrima y devolvemos el stock
				actualizacionGenerica("materiaprima as m", "stock",
						String.valueOf(stockFinal), "m.idProducto = '" + idMateria + "'");
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Actualiza el valor de un campo con la hora actual en una tabla bajo unas condiciones determinadas.
	 *
	 * @param tabla La tabla que queremos actualizar
	 * @param campo El campo que queremos actualizar de la tabla
	 * @param condiciones Las condiciones que se tienen que dar para que se actualice la tabla
	 * @return true, Si la actualización se realiza con éxito
	 * @throws Exception Excepción si la actualización falla por cualquier motivo
	 */
	private boolean actualizacionHoraActual (String tabla, String campo, String condiciones) throws Exception{
		// System.out.println("Actualizacion con la hora actual");
		boolean retorno = false;
		Statement stmt;
		try {
			con = bddConexion();
			String consulta = "UPDATE " + tabla + " SET " + campo + " = now()" +
				" WHERE " + condiciones;
			// System.out.println(consulta);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(consulta);
			return true;
		}catch (Exception e){
			return retorno;
		}
	}
	
	/**
	 * Actualiza el valor de un campo en una tabla bajo unas condiciones determinadas.
	 *
	 * @param tabla La tabla que queremos actualizar
	 * @param campo El campo que queremos actualizar de la tabla
	 * @param valor El nuevo valor para el campo
	 * @param condiciones Las condiciones que se tienen que dar para que se actualice la tabla
	 * @return true, Si la actualización se realiza con éxito
	 * @throws Exception Excepción si la actualización falla por cualquier motivo
	 */
	private boolean actualizacionGenerica (String tabla, String campo, String valor, String condiciones) throws Exception{
		// System.out.println("Actualizacion genérica");
		boolean retorno = false;
		Statement stmt;
		try {
			con = bddConexion();
			String consulta = "UPDATE " + tabla + " SET " + campo + " = '" + valor + "'" +
				" WHERE " + condiciones;
			// System.out.println(consulta);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(consulta);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return retorno;
		}
	}
	
	/**
	 * Modifica la base de datos cuando se inicia un proceso de envasado
	 * 
	 * @param orden Orden de envasado del proceso
	 * @return true, Si las actualización se realizan con éxito
	 * @throws Exception Fallo actualizando la base de datos
	 */
	public boolean iniciaProcesoEnvasado(String orden, String operario) throws Exception{
		boolean iniciado = false;
		//1. Actualizar el estado del proceso de envasado (Tabla gp_envasado, campo estadoproceso, valor='I")
		iniciado = actualizacionGenerica ("gp_envasado", "estadoproceso", "I", "gp_envasado.orden = '" + orden + "'");
		if (!iniciado)
			return false;
		//2. Actualizar registro de actividad en base de datos (Tabla: gp_envasado_registroactividad)
		iniciado = actualizaRegistroActividadEnvasado(orden,
				operario, "El proceso de envasado con orden " + orden + ", ha sido iniciado");
		if (!iniciado)
			return false;
		return true;
	}
	
	/**
	 * Modifica la base de datos cuando se pausa un proceso de envasado
	 * 
	 * @param orden Orden de envasado del proceso
	 * @return true, Si las actualizaciones se realizan con éxito
	 * @throws Exception Fallo actualizando la base de datos
	 */
	public boolean pausaProcesoEnvasado(String orden, String operario, double elaborado,
			String elaboradoAgrupar, String observaciones) throws Exception{
		boolean pausado = false;
		//1. Actualizar el estado del proceso de envasado (Tabla gp_envasado, campo estadoproceso, valor='S")
		pausado = actualizacionGenerica ("gp_envasado", "estadoproceso", "S", "gp_envasado.orden = '" + orden + "'");
		if (!pausado)
			return false;
		//2. Actualizar registro de actividad en base de datos (Tabla: gp_envasado_registroactividad)
		pausado = actualizaRegistroActividadEnvasado(orden,
				operario, "PAUSADO. Observaciones: " + observaciones);
		if (!pausado)
			return false;
		//3. Actualizar la cantidad ya elaborada
		double yaElaborado = 0;
		con = bddConexion();
		String Qry =
			" SELECT elaborado" +
			" FROM gp_envasado gp " +
			" WHERE gp.orden = '" + orden + "'";
		// System.out.println(Qry);
		ps = con.prepareStatement(Qry);
		rs = ps.executeQuery();
		while (rs.next()) {
			yaElaborado = rs.getDouble("elaborado");
		}
		double total = elaborado + yaElaborado;
		pausado = this.actualizacionGenerica("gp_envasado", "elaborado", Double.toString(total),
				"gp_envasado.orden = '" + orden + "'");
		yaElaborado = 0;
		Qry =
			" SELECT elaborado" +
			" FROM gp_envasado_agrupacion gp " +
			" WHERE gp.ordenEnvasado = '" + orden + "'";
		// System.out.println(Qry);
		ps = con.prepareStatement(Qry);
		rs = ps.executeQuery();
		while (rs.next()) {
			yaElaborado = rs.getDouble("elaborado");
		}
		double elaboradoAgruparDouble = Double.parseDouble(elaboradoAgrupar);
		total = elaboradoAgruparDouble + yaElaborado;
		pausado = this.actualizacionGenerica("gp_envasado_agrupacion", "elaborado",
				"" + total,
				"ordenEnvasado = '" + orden + "'");
		if (!pausado)
			return false;
		return true;
	}

	/**
	 * @since 2.1
	 * Actualiza el precio de coste de un producto, segun la media de todos los envasados de dicho producto
	 * @param ordenEnvasado Orden de envasado del producto que queremos actualizar
	 * @return true, si se actualiza con éxito
	 */
	public boolean actualizarPrecioCosteProducto(String ordenEnvasado) throws Exception{
		boolean actualizado = false;
		//1. Obtener el id del producto envasado
		long idProducto = 0;
		try {
			con = bddConexion();
			String Qry =
				" SELECT idProducto" +
				" FROM gp_envasado gp" +
				" WHERE gp.orden = '" + ordenEnvasado + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				idProducto = rs.getInt("idProducto");
			}
			ps.close();
			//2. Actualizar tabla producto, campo precioCoste,
			//valor la media de todos los precios de coste de todos los procesos de
			//envasado que envasaban este producto
			double media = 0;
			//Calculamos la media.
			con = bddConexion();
			Qry = " SELECT precioCoste, elaborado "
				+ " FROM gp_envasado gp"
				+ " WHERE gp.estadoproceso='F' AND gp.idProducto = '" + idProducto + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			double precio = 0, suma = 0;
			int elaborado = 0;
			int contador = 0;
			while (rs.next()) {
				elaborado = rs.getInt("elaborado");
				precio = rs.getDouble("precioCoste");
				suma += precio / elaborado;
				contador++;
			}
			ps.close();
			media = suma/contador;
			String mediaString = Double.toString(media);
			this.actualizacionGenerica("producto", "precioCoste", mediaString, "idProducto = '" + idProducto + "'");
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return actualizado;
	}
	
	/**
	 * Modifica la base de datos cuando se finaliza un proceso de envasado
	 * 
	 * @param orden Orden de envasado del proceso
	 * @return true, Si las actualizaciones se realizan con éxito
	 * @throws Exception Fallo actualizando la base de datos
	 */
	public boolean finalizaProcesoEnvasado(RegistroEnvasado envasado, boolean anula) throws Exception{
		boolean finalizado = false;
		long tipoEANEnvasado = envasado.getTipoEan();
		double cantidad = 0;
		if (tipoEANEnvasado == 13){
			cantidad = envasado.getElaborado();
		}else{
			if (tipoEANEnvasado == 14){
				cantidad = envasado.getElaboradoAgrupar();
			}
		}
		//2. Actualizar la hora del fin del proceso de envasado (Tabla: gp_envasado; Campo: horaFin)
		finalizado = actualizacionHoraActual ("gp_envasado", "horaFin", "gp_envasado.orden = '" + envasado.getProceso() + "'");
		if (!finalizado)
			return false;
		//3. Actualizar registro de actividad en base de datos (Tabla: gp_envasado_registroactividad)
		finalizado = actualizaRegistroActividadEnvasado(envasado.getProceso(),
				envasado.getOperario(), "FINALIZADO. Observaciones: " + envasado.getObservaciones());
		if (!finalizado)
			return false;
		if (tipoEANEnvasado == 13){
			//4. Para el array de materias recibido, actualizar en la tabla gp_envasado_detalle los campos real y mermas.
			//Tenemos codigoEntrada
			//Si no hay mermas, y la cantidad real es menor que la cantidad reservada para el proceso, devolver el exceso al stock
			double mermasTotalMaterias = 0;
			for (int i = 0; i < envasado.getMateriaPrima().size(); i++){
				LineaProducto materia = (LineaProducto) envasado.getMateriaPrima().get(i);
				String entrada = materia.getEntrada();
				double real = materia.getReal();
				if (!actualizacionGenerica ("gp_envasado_detalle detalle", "cantidadReal", Double.toString(real),
						"detalle.codigoEntrada = '" + entrada + "' AND detalle.orden = '" + envasado.getProceso() + "'"))
					return false;
				if (!actualizacionGenerica ("gp_envasado_ubicacion u", "cantidadReal", Double.toString(real),
						"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
								" AND u.idHueco = " + materia.getIdUbicacion() + " AND u.idPalet = " + materia.getIdPalet()))
					return false;
				double mermas = materia.getMermas();
				//Buscamos la cantidad reservada para esta materia
				double reservado = getReservado(envasado.getProceso(), entrada, materia.getIdUbicacion());
				if (real < reservado){
					double devuelve = reservado - real - mermas;
					if (entrada.charAt(0) == 'E'){
						//Buscamos el stock actual de ese lote de entrada, y le aniadimos devuelve
						double stockActual = getStockLote(entrada);
						//¿Actualizar o no con mermas?
						//double stockFinal = stockActual + devuelve - mermas;
						double stockFinal = stockActual + devuelve;
						if (!actualizacionGenerica ("registroentrada", "saldo", Double.toString(stockFinal),
								"registroentrada.codigoEntrada = '" + entrada + "'"))
							return false;
					}else{
						if (entrada.charAt(0) == '0'){
							//idProducto
							long idProducto = 0;
							String Qry =
								" SELECT idProducto " +
								" FROM gp_envasado gp" +
								" WHERE gp.lote = '" + entrada + "'; ";
							// System.out.println(Qry);
							ps = con.prepareStatement(Qry);
							rs = ps.executeQuery();
							if (rs.next()) {
								idProducto = rs.getLong("idProducto");
								String updateString =
									" UPDATE producto SET stock = stock + " + devuelve +
									" WHERE idProducto = " + idProducto;
								Statement stmt;
								// System.out.println(updateString);
								stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
								stmt.executeUpdate(updateString);
							}
							rs.close();
						}
					}
					if (devuelve > 0){
						if (!actualizacionGenerica ("gp_envasado_ubicacion u", "idHuecoNuevo", "" + materia.getIdUbicacion(),//"223",
								"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
										" AND u.idHueco = " + materia.getIdUbicacion() + " AND u.idPalet = " + materia.getIdPalet()))
							return false;
						/*
						 * */
						String Qry =
							" SELECT * " +
							" FROM ubicacion u " +
							" WHERE registro = '" + entrada + "' " +
								" AND idHueco = " + materia.getIdUbicacion() +
								" AND idPalet = " + materia.getIdPalet();
						ps = con.prepareStatement(Qry);
						rs = ps.executeQuery();
						if (rs.next()) {
							String updateString =
								" UPDATE ubicacion SET cantidad = cantidad + " + devuelve +
								" WHERE registro = '" + entrada + "' AND idHueco = " + materia.getIdUbicacion() +
									" AND idPalet = " + materia.getIdPalet();
							Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							stmt.executeUpdate(updateString);
						}else{
							String updateString =
								" INSERT INTO ubicacion (registro, idHueco, idPalet, cantidad) VALUES ('" + entrada + "', " + materia.getIdUbicacion() + ", " + materia.getIdPalet() + ", " + devuelve + "); ";
							Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
							stmt.executeUpdate(updateString);							
						}
					}
				}
				if (mermas > 0){
					if (!actualizacionGenerica ("gp_envasado_detalle detalle", "mermaProducto", Double.toString(mermas),
							"detalle.codigoEntrada = '" + entrada + "'"))
						return false;
					if (!actualizacionGenerica ("gp_envasado_ubicacion u", "mermas", Double.toString(mermas),
							"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
									" AND u.idHueco = " + materia.getIdUbicacion() + " AND u.idPalet = " + materia.getIdPalet()))
						return false;
					mermasTotalMaterias += mermas;
				}
			}
			//5. Actualizamos el número total de mermas de los ingredientes para un proceso de envasado
			if (!actualizacionGenerica ("gp_envasado gp", "mermasMP", Double.toString(mermasTotalMaterias),
					"gp.orden = '" + envasado.getProceso() + "'"))
				return false;
		}
		//6. Para el array de envases recibido, actualizar en la tabla gp_envasado_detalle los campos real y mermas.
		//Tenemos codigoEntrada
		//Si no hay mermas, y la cantidad real es menor que la cantidad reservada para el proceso, devolver el exceso al stock
		double mermasTotalEnvases = 0;
		for (int i = 0; i < envasado.getEnvases().size(); i++){
			LineaProducto envase = (LineaProducto) envasado.getEnvases().get(i);
			double real = envase.getReal();
			String entrada = envase.getEntrada();
			if (!actualizacionGenerica ("gp_envasado_detalle detalle", "cantidadReal", Double.toString(real),
					"detalle.codigoEntrada = '" + entrada + "' AND detalle.orden = '" + envasado.getProceso() + "'"))
				return false;
			if (!actualizacionGenerica ("gp_envasado_ubicacion u", "cantidadReal", Double.toString(real),
						"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
								" AND u.idHueco = " + envase.getIdUbicacion() + " AND u.idPalet = " + envase.getIdPalet()))
				return false;
			//Buscamos la cantidad reservada para este envase
			double mermas = envase.getMermas();
			double reservado = getReservado(envasado.getProceso(), entrada, envase.getIdUbicacion());
			if (real < reservado){
				double devuelve = reservado - real - mermas;
				//Buscamos el stock actual de ese lote de entrada, y le aniadimos devuelve
				double stockActual = getStockLote(entrada);
				double stockFinal = stockActual + devuelve;
				if (!actualizacionGenerica ("registroentrada", "saldo", Double.toString(stockFinal),
						"registroentrada.codigoEntrada = '" + entrada + "'"))
					return false;
				if (devuelve > 0){
					if (!actualizacionGenerica ("gp_envasado_ubicacion u", "idHuecoNuevo", "" + envase.getIdUbicacion(),
							"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
									" AND u.idHueco = " + envase.getIdUbicacion() + " AND u.idPalet = " + envase.getIdPalet()))
						return false;
					String updateString =
						" UPDATE ubicacion SET cantidad = cantidad + " + devuelve +
						" WHERE registro = '" + entrada + "' AND idHueco = " + envase.getIdUbicacion() +
							" AND idPalet = " + envase.getIdPalet();
					Statement stmt;
					// System.out.println(updateString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(updateString);
				}
			}
			if (mermas > 0){
				//gp_envasado -> mermas total del proceso de envasado && gp_envasado_detalle
				if (!actualizacionGenerica ("gp_envasado_detalle detalle", "mermaProducto", Double.toString(mermas),
						"detalle.codigoEntrada = '" + entrada + "'"))
					return false;
				if (!actualizacionGenerica ("gp_envasado_ubicacion u", "mermas", Double.toString(mermas),
						"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
								" AND u.idHueco = " + envase.getIdUbicacion() + " AND u.idPalet = " + envase.getIdPalet()))
					return false;
				mermasTotalEnvases += mermas;
			}
		}
		//Actualizamos el número total de mermas de los envases para un proceso de envasado
		if (!actualizacionGenerica ("gp_envasado gp", "mermasEN", Double.toString(mermasTotalEnvases),
				"gp.orden = '" + envasado.getProceso() + "'"))
			return false;
		if (tipoEANEnvasado == 14){
			//7. Para el array de items recibido, actualizar en la tabla gp_envasado_detalle los campos real y mermas.
			//Tenemos codigoEntrada
			//Si no hay mermas, y la cantidad real es menor que la cantidad reservada para el proceso, devolver el exceso al stock
			for (int i = 0; i < envasado.getEans13().size(); i++){
				LineaProducto item = (LineaProducto) envasado.getEans13().get(i);
				double real = item.getReal();
				double mermas = item.getMermas();
				String entrada = item.getEntrada();
				if (!actualizacionGenerica ("gp_envasado_ubicacion u", "cantidadReal", Double.toString(real),
							"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
									" AND u.idHueco = " + item.getIdUbicacion()))
					return false;
				//Buscamos la cantidad reservada para esta materia
				double reservado = getReservado(envasado.getProceso(), entrada, item.getIdUbicacion());
				if (real < reservado){
					double devuelve = reservado - real - mermas;
					//Buscamos el stock actual de ese lote de entrada, y le aniadimos devuelve
					double stockActual = getStockLote(entrada);
					double stockFinal = stockActual + devuelve;
					if (!actualizacionGenerica ("registroentrada", "saldo", Double.toString(stockFinal),
							"registroentrada.codigoEntrada = '" + entrada + "'"))
						return false;
					if (devuelve > 0){
						String hueco = "";
						String Qry = "SELECT ubi.idHueco " +
								" FROM gp_envasado_ubicacion ubi, gp_envasado e " +
								" WHERE ubi.ordenEnvasado = e.orden " +
								" AND e.orden = '" + envasado.getProceso() + "'";
						// System.out.println(Qry);
						ps = con.prepareStatement(Qry);
						rs = ps.executeQuery();
						while (rs.next()) {
							hueco = rs.getString("idHueco");
						}
						// System.out.println("El hueco anterior era: " + hueco);
						if (!actualizacionGenerica ("gp_envasado_ubicacion u", "idHuecoNuevo", hueco,
								"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
										" AND u.idHueco = " + item.getIdUbicacion()))
							return false;
					}
				}
				if (mermas > 0){
					//gp_envasado -> mermas total del proceso de envasado && gp_envasado_detalle
					if (!actualizacionGenerica ("gp_envasado_detalle detalle", "mermaProducto", Double.toString(mermas),
							"detalle.codigoEntrada = '" + entrada + "'"))
						return false;
					if (!actualizacionGenerica ("gp_envasado_ubicacion u", "mermas", Double.toString(mermas),
							"u.codigoEntrada = '" + entrada + "' AND u.ordenEnvasado = '" + envasado.getProceso() + "' " +
									" AND u.idHueco = " + item.getIdUbicacion()))
						return false;
				}
			}
		}
		//3. Actualizar la cantidad ya elaborada
		double yaElaborado = 0;
		con = bddConexion();
		String Qry =
			" SELECT elaborado " +
			" FROM gp_envasado gp " +
			" WHERE gp.orden = '" + envasado.getProceso() + "'";
		// System.out.println(Qry);
		ps = con.prepareStatement(Qry);
		rs = ps.executeQuery();
		while (rs.next()) {
			yaElaborado = rs.getDouble("elaborado");
		}
		double totalElaborado = cantidad + yaElaborado;
		this.actualizacionGenerica("gp_envasado", "elaborado", Double.toString(totalElaborado),
				"gp_envasado.orden = '" + envasado.getProceso() + "'");
		yaElaborado = 0;
		Qry =
			" SELECT elaborado" +
			" FROM gp_envasado_agrupacion gp " +
			" WHERE gp.ordenEnvasado = '" + envasado.getProceso() + "'";
		// System.out.println(Qry);
		ps = con.prepareStatement(Qry);
		rs = ps.executeQuery();
		while (rs.next()) {
			yaElaborado = rs.getDouble("elaborado");
		}
		double elaboradoAgruparDouble = envasado.getElaboradoAgrupar();
		double totalElaboradoAgrupacion = elaboradoAgruparDouble + yaElaborado;
		this.actualizacionGenerica("gp_envasado_agrupacion", "elaborado",
				"" + totalElaboradoAgrupacion,
				"ordenEnvasado = '" + envasado.getProceso() + "'");
		Qry =
			" SELECT gp.idProducto, gpa.idAgrupacion " +
			" FROM gp_envasado gp, gp_envasado_agrupacion gpa " +
			" WHERE gp.orden = '" + envasado.getProceso() + "' AND gpa.ordenEnvasado = gp.orden";
		// System.out.println(Qry);
		long idProducto = 0, idAgrupacion = 0;
		ps = con.prepareStatement(Qry);
		rs = ps.executeQuery();
		while (rs.next()) {
			idProducto = rs.getLong("idProducto");
			idAgrupacion = rs.getLong("idAgrupacion");
		}
		//AUMENTAMOS EL STOCK del producto envasado
		if (tipoEANEnvasado == 13){
			String insertString = "";
			if (idAgrupacion > 0){
				insertString =
					" UPDATE producto SET stockAgrupado = stockAgrupado + " + totalElaborado +
					" WHERE idProducto = '" + idProducto + "'";
				Statement stmt;
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
				insertString =
					" UPDATE producto SET stock = stock + " + totalElaboradoAgrupacion +
					" WHERE idProducto = '" + idAgrupacion + "'";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
			}else{
				insertString =
					" UPDATE producto SET stock = stock + " + totalElaborado +
					" WHERE idProducto = '" + idProducto + "'";
				Statement stmt;
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
			}
		}else{
			if (tipoEANEnvasado == 14){
				Statement stmt;
				String insertString =
					" UPDATE producto SET stock = stock + " + totalElaborado +
					" WHERE idProducto = '" + idProducto + "'";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
				for (int i = 0; i < envasado.getEans13().size(); i++){
					LineaProducto item = (LineaProducto) envasado.getEans13().get(i);
					String entrada = item.getEntrada();
					double real = item.getReal();
					double stockRestar = real;
					double stock = 0, stockAgrupado = 0;
					Qry =
						" SELECT p.idProducto, p.stock, p.stockAgrupado " +
						" FROM producto p, gp_envasado gp " +
						" WHERE gp.lote = '" + entrada + "' " +
								" AND p.idProducto = gp.idProducto";
					// System.out.println(Qry);
					long idPro = 0;
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					while (rs.next()) {
						idPro = rs.getLong("idProducto");
						stock = rs.getDouble("stock");
						stockAgrupado = rs.getDouble("stockAgrupado");
					}
					if (!actualizacionGenerica("producto", "stock", "" + (stock - stockRestar),
							"idProducto = '" + idPro + "'"))
							return false;
					if (!actualizacionGenerica("producto", "stockAgrupado", "" + (stockAgrupado + stockRestar),
							"idProducto = '" + idPro + "'"))
							return false;
				}
			}
		}
		if (!actualizacionGenerica("gp_envasado", "precioCoste", String.valueOf(envasado.getPrecioCoste()),
				"orden = '" + envasado.getProceso() + "'"))
				return false;
		//ULTIMO: Actualizar el estado del proceso de envasado (Tabla gp_envasado, campo estadoproceso, valor='F'(Finaliado) ó 'X'(Anulado)
		if (!anula)
			finalizado = actualizacionGenerica ("gp_envasado", "estadoproceso", "F", "gp_envasado.orden = '" + envasado.getProceso() + "'");
		else
			finalizado = actualizacionGenerica ("gp_envasado", "estadoproceso", "A", "gp_envasado.orden = '" + envasado.getProceso() + "'");
		if (!finalizado)
			return false;
		//Meter en ubicacion con idHueco 223 toda la cantidad envasada con registro = lote
		//loteAsignado y elaborado
		// SQL de insersion
		if (tipoEANEnvasado == 13){
			if (envasado.getElaboradoAgrupar() > 0){
				cantidad = envasado.getElaboradoAgrupar();
			}
		}
		String insertString =
			" INSERT INTO ubicacion (idHueco, cantidad, registro, orden) " +
				" VALUES('" + 223 + "','" + cantidad +  "','" +
					envasado.getLoteAsignado() + "','" + envasado.getProceso() + "')";
		Statement stmt;
		int res = 0;
		// System.out.println(insertString);
		con = bddConexion();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		res = stmt.executeUpdate(insertString);
		boolean retorno = false;
		if (res == 1) {
			// System.out.println("REGISTRO INSERTADO");
			retorno = true;
		}else
			return retorno;
		//Actualizamos la fecha de caducidad del envasado
		int mesesCaducidad = 18;
		Qry =
			" SELECT p.caducidad " +
			" FROM producto p " +
			" WHERE p.idProducto = '" + ((idAgrupacion > 0)? idAgrupacion: idProducto) + "'; ";
		System.out.println(Qry);
		ps = con.prepareStatement(Qry);
		rs = ps.executeQuery();
		if (rs.next()) {
			mesesCaducidad = rs.getInt("caducidad");
		}
		insertString =
			" UPDATE gp_envasado " +
			" SET fechaCaducidad = ADDDATE(CURRENT_DATE(), INTERVAL " + mesesCaducidad + " MONTH) " +
			" WHERE orden = '" + envasado.getProceso() + "'; ";
		// System.out.println(insertString);
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		res = stmt.executeUpdate(insertString);
		retorno = false;
		if (res == 1) {
			// System.out.println("Fecha de caducidad actualizada correctamente");
			retorno = true;
		}else{
			return retorno;
		}
		return retorno;
	}
	
	/**
	 * Obtiene el stock existente para un concreto lote de entrada
	 *
	 * @param entrada Codigo de entrada del producto (E/M)
	 * @return Stock del producto
	 */
	private double getStockLote(String entrada){
		double retorno = 0;
		try {
			con = bddConexion();
			String Qry =
				" SELECT r.saldo" +
				" FROM registroentrada r " +
				" WHERE r.codigoEntrada = '" + entrada + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				retorno = rs.getDouble("saldo");
			}
			ps.close();
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		return retorno;
	}
	
	/**
	 * Obtiene la cantidad de producto (E o M) reservada para una orden de envasado
	 *
	 * @param orden Orden de envasado donde se deben de eliminar las materias
	 * @param entrada Codigo de entrada del producto (E/M)
	 * @return Cantidad reservada del producto para la orden de envasado
	 */
	private double getReservado(String orden, String entrada, long idUbicacion){
		double retorno = 0;
		try {
			con = bddConexion();
			String Qry = "SELECT g.cantidad"
				+ " FROM gp_envasado_ubicacion g"
				+ " WHERE g.ordenEnvasado = '" + orden + "'"
				+ " AND g.codigoEntrada = '" + entrada + "' AND g.idHueco = " + idUbicacion;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				retorno = rs.getDouble("cantidad");
			}
			ps.close();
		}catch (Exception e){
			e.printStackTrace();
			return -1;
		}
		return retorno;
	}
	
	/**
	 * Comprueba si un producto está compuesto por otros subproductos
	 * @author Induserco - Andrés
	 * @since Versión 1.2
	 * @param orden Código de la orden de envasado donde se genera el producto que queremos saber si está compuesto o no.
	 * @return true, si el producto está compuesto de más subproductos
	 */
	public boolean productoCompuesto(String orden){
		boolean compuesto = false;
		try {
			con = bddConexion();
			String Qry = "SELECT DISTINCT e.idProducto"
				+ " FROM gp_envasado e"
				+ " WHERE e.orden = '" + orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			long idProducto = 0;
			while (rs.next())
				idProducto = rs.getLong("idProducto");
			Qry = "SELECT DISTINCT compuesto.idProducto"
				+ " FROM producto_compuesto compuesto, gp_envasado envasado"
				+ " WHERE envasado.orden = '" + orden + "'"
				+ " AND envasado.idProducto = compuesto.idProducto"
				+ " AND envasado.idProducto = '" + idProducto + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next())
				compuesto = true;
			ps.close();
		}catch (Exception e){
			e.printStackTrace();
			return compuesto;
		}
		return compuesto;
	}
	
	/**
	 * Genera el lote de un producto tras ser envasado
	 * @param envasado Registro de envasado para el que se genera el lote
	 * @return String El lote del producto envasado
	 * @author Induserco, Andrés (11/04/2011)
	 * @since 1.3
	 */
	private String generarLoteEnvasado(RegistroEnvasado envasado){
		// System.out.println("Generando el lote envasado");
		//1. 0: Código de control para los procesos de envasado
		String lote = "0", diaString = "";
		String fechaRegistro = envasado.getFechaRegistro();
		//20/03/2012
		int dia = 0, mes = 0, annio = 0;
		dia = Integer.parseInt(fechaRegistro.substring(0, 2));
		mes = Integer.parseInt(fechaRegistro.substring(3, 5));
		annio = Integer.parseInt(fechaRegistro.substring(6));
		//2. Último dígito del año
		lote += fechaRegistro.toCharArray()[9];
		//3. Número de día del año
		//int diaAnnio = cal.DAY_OF_YEAR;
		//int diaMes = cal.DAY_OF_MONTH;
		int diaAux = getDiaDelAnio(dia, mes, annio);
		if (diaAux < 10)
			diaString = "00" + diaAux;
		else
			if (diaAux < 100)
				diaString = "0" +  diaAux;
			else
				diaString = String.valueOf(diaAux);
		lote += diaString;
		//4. Contador
		int contador = getNumeroEnvasadosDia(dia, mes, annio);
		contador++;
		String contadorString = "";
		if (contador < 10)
			contadorString = "00" + String.valueOf(contador);
		else
			if (contador < 100)
				contadorString = "0" + String.valueOf(contador);
		lote += contadorString;
		// System.out.println("Lote de envasado generado: " + lote);
		return lote;
	}

	public String getObservacionesEnvasado(String orden) throws Exception{
		String obs = "";
		try {
			con = bddConexion();
			String Qry = "SELECT observaciones"
				+ " FROM gp_envasado gp"
				+ " WHERE gp.orden = '" + orden + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next())
				obs = rs.getString("observaciones");
			ps.close();
		}catch (Exception e){ e.printStackTrace(); }
		return obs;
	}
	
	/**
	 * Calcula el número de procesos de envasados que hay para un día concreto
	 * @param int Dia
	 * @param int Mes
	 * @param int Año
	 * @return int Número de procesos
	 * @author Induserco, Andrés (11/04/2011)
	 * @since 1.3
	 */
	private int getNumeroEnvasadosDia(int day, int month, int anio) {
		int contador = 0;
		try {
			con = bddConexion();
			String diaString = "", mesString = "";
			if (day < 10)
				diaString = "0" + day;
			else
				diaString = "" + day;
			if (month < 10)
				mesString = "0" + month;
			else
				mesString = "" + month;
			String Qry = "SELECT e.idProducto,e.fecha FROM gp_envasado e " +
					" WHERE e.fecha = '" + anio + "-" + mesString + "-" + diaString +"'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next())
				contador++;
			ps.close();
		}catch (Exception e){
			e.printStackTrace();
			return contador;
		}
		return contador;
	}

	/**
	 * Calcula el dia del año, a partir del día, mes y año
	 * @param int Dia
	 * @param int Mes
	 * @param int Año
	 * @return int Día del año
	 * @author Induserco, Andrés (11/04/2011)
	 * @since 1.3
	 */
	private int getDiaDelAnio(int dia, int mes, int anio){
		int diaDelAnio = 0;
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(GregorianCalendar.DAY_OF_MONTH, dia);
        gc.set(GregorianCalendar.MONTH, mes - 1);
        gc.set(GregorianCalendar.YEAR, anio);
        diaDelAnio = gc.get(GregorianCalendar.DAY_OF_YEAR);
		return diaDelAnio;
	}
	
	/**
	 * @author andres (27/06/2011) - (01/07/2011)
	 * @since 1.3
	 */
	//@Override
	public boolean setUbicado(String ordenEnvasado, Vector<Ubicacion> ubicaciones, boolean actualizarCampoUbicado) throws Exception{
		boolean retorno = false;
		for (int i = 0; i < ubicaciones.size(); i++){
			Ubicacion ub = ubicaciones.get(i);
			String registro = ub.getRegistro();
			//long idUbicacion = ub.getIdUbicacion();
			long idHueco = ub.getIdHueco();
			double cantidad = ub.getCantidad();
			// SQL de insersion
			String insertString = "INSERT INTO gp_envasado_ubicacion(ordenEnvasado, codigoEntrada, idPalet, idHueco, cantidad) " +
					" VALUES('" + ordenEnvasado + "','" + registro +  "','" + ub.getIdPalet() + "','" + idHueco + "','" + cantidad + "')";
			Statement stmt;
			int res = 0;
			// System.out.println(insertString);
			con = bddConexion();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (res == 1) {
				// System.out.println("REGISTRO INSERTADO");
				retorno = true;
			}else
				return retorno = false;
		}
		if (retorno && actualizarCampoUbicado)
			retorno = actualizacionGenerica ("gp_envasado", "ubicado", "S", "gp_envasado.orden = '" + ordenEnvasado + "'");
		return retorno;
	}

	/**
	 * @author andres Andrés (04/07/2011)
	 * @since 1.5
	 */
	//@Override
	public Vector<RegistroActividad> getRegistrosActividad(String proceso) throws Exception {
		Vector<RegistroActividad> registros = new Vector<RegistroActividad>();
		try {
			con = bddConexion();
			String Qry = "SELECT * "
				+ " FROM gp_envasado_registroactividad regi"
				+ " WHERE regi.idOrden = '" + proceso + "' ORDER BY idRegistro";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				RegistroActividad regi = new RegistroActividad();
				regi.setHoraInicio(rs.getTimestamp("horaInicio"));
				regi.setResponsable(rs.getString("responsable"));
				regi.setObservaciones(rs.getString("observaciones"));
				registros.add(regi);
			}
			ps.close();
		}catch (Exception e){
			e.printStackTrace();
			return registros;
		}
		return registros;
	}

	//@Override
	public Vector<Ubicacion> getUbicacionesEnvasado(String orden, String lote) {
		Vector<Ubicacion> registros = new Vector<Ubicacion>();
		try {
			con = bddConexion();
			String Qry = "SELECT u.registro, u.cantidad, u.idHueco, uh.descripcion " +
					" FROM ubicacion u, ubicacion_hueco uh " +
					" WHERE u.registro = '" + lote + "' " +
							" AND u.idHueco = uh.idHueco";				
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				Ubicacion u = new Ubicacion();
				//u.setRegistro(rs.getString("codigoEntrada"));
				u.setRegistro(rs.getString("registro"));
				u.setCantidad(rs.getDouble("cantidad"));
				//u.setIdUbicacion(rs.getLong("idHueco"));
				u.setNombreHueco(rs.getString("descripcion"));
				u.setIdHueco(rs.getLong("idHueco"));
				registros.add(u);
			}
			ps.close();
		}catch (Exception e){
			e.printStackTrace();
			return registros;
		}return registros;
	}

	//@Override
	public Vector<LineaProducto> getInfoMateria(String codigoEntrada)
			throws Exception {
		// System.out.println("getInfoMateria FROM: (DAO) ");
		Vector<LineaProducto> resultado = new Vector<LineaProducto>();
		ResultSet rs = null;
		ResultSet rsaux = null;
		try {
			con = bddConexion();
			String consulta = "";
			consulta = "SELECT DISTINCT materia.nombre as nombre, cate.nombre as nombreCategoria, " +
					" re.saldo, mc.idMateriaCategoria as id, re.lote as loteProducto,  " +
					" re.fecha, re.fechaCaducidad, mc.stock " +
					" FROM (materiaprima materia, materiaprima_categoria mc, categoria cate, registroentrada re) " +
					" WHERE materia.idProducto = mc.idMateriaPrima AND mc.idCategoria = cate.idCategoria " +
					" AND materia.idProducto = re.idProducto AND re.codigoEntrada = '" + codigoEntrada + "' AND " +
					" mc.idCategoria=re.idCategoriaEntrada order by id";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()) {
				LineaProducto lp = new LineaProducto();
				lp.setLote(rs.getString("loteProducto"));
				lp.setFecha(rs.getDate("fecha"));
				lp.setFechaCaducidad(rs.getDate("fechaCaducidad"));
				lp.setIdProveedor(rs.getLong("id"));
				lp.setNombre(rs.getString("nombre"));
				lp.setDisponible(rs.getDouble("saldo"));
				lp.setIdLinea(rs.getLong("id"));
				lp.setTeorica(rs.getDouble("stock"));
				lp.setNombreCategoria(rs.getString("nombreCategoria"));
				//lp.setMermas(rs.getDouble("mermaProducto"));
				lp.setRegistroEntrada(codigoEntrada);
				//lp.setLote(rs.getString("orden"));
				//En idProveedor le pasamos el id del envase o de la materia
				//Get idProveedor&proveedor
				consulta="SELECT idUsuario, nombre FROM entidad WHERE idUsuario=(SELECT idProveedor FROM ordenentrada WHERE codigoOrden= (SELECT codigoOrden FROM registroentrada WHERE codigoEntrada='"+lp.getRegistroEntrada()+"'))";
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rsaux = ps.executeQuery();
				while (rsaux.next()) {
					lp.setProveedor(rsaux.getString("nombre"));
				}
				consulta = "SELECT uh.descripcion FROM ubicacion_hueco uh, ubicacion u WHERE u.idHueco = uh.idHueco AND u.registro = '" + codigoEntrada + "'";
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rsaux = ps.executeQuery();
				while (rsaux.next()) {
					lp.setRefUbicacion(rsaux.getString("descripcion"));
				}
				//lo añado al vector resultante
				resultado.add(lp);
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
	public Vector<LineaProducto> getInfoMateriasProceso(String ordenProceso, String proceso)
			throws Exception {
		// System.out.println("getInfoMateria FROM: (DAO) ");
		Vector<LineaProducto> resultado = new Vector<LineaProducto>();
		ResultSet rsaux = null;
		try {
			con = bddConexion();
			String tabla = "";
			String consulta = "";
			if (proceso.compareTo("Congelado") == 0){
				tabla = "gp_congelado";
			}else{
				tabla = "gp_fumigado";
			}
			consulta = "SELECT DISTINCT materia.nombre as nombre, cate.nombre as nombreCategoria, re.codigoEntrada, " +
					" re.saldo, mc.idMateriaCategoria as id, re.lote as loteProducto, cantidadDisponible, " +
					" mermaProducto, re.fecha, re.fechaCaducidad " +
					" FROM (materiaprima materia, materiaprima_categoria mc, categoria cate, " +
					tabla + " as g, registroentrada re) " +
					" WHERE materia.idProducto = mc.idMateriaPrima AND mc.idCategoria = cate.idCategoria " +
					" AND materia.idProducto = re.idProducto AND re.codigoEntrada=g.codigoEntrada AND " +
					" mc.idCategoria=re.idCategoriaEntrada AND g.orden='" + ordenProceso + "' ORDER BY id";
			// System.out.println(consulta);
			ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()) {
				LineaProducto lp = new LineaProducto();
				lp.setLote(rs.getString("loteProducto"));
				lp.setFecha(rs.getDate("fecha"));
				lp.setFechaCaducidad(rs.getDate("fechaCaducidad"));
				lp.setIdProveedor(rs.getLong("id"));
				lp.setNombre(rs.getString("nombre"));
				lp.setDisponible(rs.getDouble("saldo"));
				lp.setIdLinea(rs.getLong("id"));
				lp.setNombreCategoria(rs.getString("nombreCategoria"));
				lp.setMermas(rs.getDouble("mermaProducto"));
				lp.setRegistroEntrada(rs.getString("codigoEntrada"));
				//lp.setLote(rs.getString("orden"));
				//En idProveedor le pasamos el id del envase o de la materia
				//Get idProveedor&proveedor
				consulta="SELECT idUsuario, nombre FROM entidad " +
						" WHERE idUsuario=(SELECT idProveedor FROM ordenentrada WHERE codigoOrden= (SELECT codigoOrden FROM registroentrada WHERE codigoEntrada='"+lp.getRegistroEntrada()+"'))";
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rsaux = ps.executeQuery();
				while (rsaux.next()) {
					lp.setProveedor(rsaux.getString("nombre"));
				}
				consulta = "SELECT uh.descripcion FROM ubicacion_hueco uh, ubicacion u " +
						" WHERE u.idHueco = uh.idHueco AND u.registro = '" + lp.getRegistroEntrada() + "'";
				// System.out.println(consulta);
				ps = con.prepareStatement(consulta);
				rsaux = ps.executeQuery();
				while (rsaux.next()) {
					lp.setRefUbicacion(rsaux.getString("descripcion"));
				}
				//lo añado al vector resultante
				resultado.add(lp);
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
	public void guardarUbicacionesCongelado(Vector<Ubicacion> ubicaciones, String orden, String proceso) throws Exception{
		// System.out.println("DAO guardarUbicacionesCongelado Congelado o Fumigado");
		Statement stmt;
		String insertString = null;
		String tablagp = "";
		if (proceso.compareTo("Congelado") == 0)
			tablagp = "gp_congelado_ubicacion";
		else if (proceso.compareTo("Fumigado") == 0)
			//tablagp = "gp_fumigado_ubicacion";
			tablagp = "gp_fumigado_ubicacion";
		if (ubicaciones.isEmpty()){
			// System.out.println("No hay lista de elementos");
		}else {
			Vector<Ubicacion> ubicaAux = new Vector<Ubicacion>();
			for (int i = 0; i < ubicaciones.size(); i++){
				Ubicacion u = ubicaciones.get(i);
				//A partir del idUbicacion, sacamos el idHueco
				String qry = "SELECT uh.idHueco FROM ubicacion u, ubicacion_hueco uh " +
						" WHERE u.idHueco = uh.idHueco AND u.idUbicacion = " + u.getIdUbicacion();
				// System.out.println(qry);
				con = bddConexion();
				ps = con.prepareStatement(qry);
				rs = ps.executeQuery();
				long idUbicacion = u.getIdUbicacion();
				long idHueco = 0;
				while (rs.next()) {
					idHueco = (rs.getLong("idHueco"));
				}
				String codigoEntrada = u.getRegistro();
				boolean flag = false;
				int j = 0;
				for (j = 0; j < ubicaAux.size(); j++){
					if (ubicaAux.get(j).getRegistro().compareTo(codigoEntrada) == 0){
						flag = true;
						break;
					}
				}
				double cantidad = u.getCantidad();
				if (!flag){
					Ubicacion ubi = new Ubicacion();
					ubi.setCantidad(cantidad);
					ubi.setRegistro(codigoEntrada);
					ubi.setIdHueco(idHueco);
					ubi.setIdUbicacion(idUbicacion);
					ubicaAux.add(ubi);
				}else{
					ubicaAux.get(j).setCantidad(ubicaAux.get(j).getCantidad() + cantidad);
				}
			}
			for (int i = 0; i < ubicaAux.size(); i++){
				try {
					Ubicacion u = ubicaAux.get(i);
					insertString = "INSERT INTO "
						+ tablagp
						+ " (orden,codigoEntrada,cantidad,idHueco) values("
						+ "'" + orden + "','"
						+ u.getRegistro() + "',"
						+ u.getCantidad() + ","
						+ u.getIdHueco() + ")";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertString);
					//En la tabla ubicacion, actualizamos la cantidad de 'registro' en 'idUbicacion' sumandole 'cantidad' ¿y palet = '0'?
					//Con palet = 0 evitamos errores, y marcamos la ubicacion para distinguirla de una entrada normal
					//Actualizamos o insertamos, si hubieramos sacado todas las fabas que había para fumigar
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
	}
	
	/**
	 * @since 2.0
	 */
	//@Override
	public void updateFechaProgramada(GestionProduccion gpro) throws Exception{
		// System.out.println("DAO updateFechaProgramada");
		String Qry =
			" SELECT gp.fecha " + " FROM gp_envasado gp " +
				" WHERE gp.orden = '" + gpro.getOrden() + "' ";
		// System.out.println(Qry);
		try {
			con = bddConexion();
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			String fechaProgramada = "";
			while (rs.next()) {
				fechaProgramada = rs.getString("fecha");
			}
			Statement stmt;
			int diasDesplazamiento = Integer.parseInt(gpro.getFechaProgramada());
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = null;
			try {
				fecha = formatoDelTexto.parse(fechaProgramada);
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
			actualizaRegistroActividadEnvasado(gpro.getOrden(), gpro.getUsuarioResponsable(), "Fecha programada modificada");
			con = bddConexion();
			String insertString1 = " UPDATE gp_envasado "
					+ " SET fecha = '" + cadenaNuevaFecha.toString()
					+ "' " + " WHERE orden = '" + gpro.getOrden() + "'";
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
	 * @since 2.2
	 */
	//@Override
	public char compruebaLoteCantidad(Lote lote) throws Exception {
		char comprobacion = 'O';// O = OK; N = No existe el lote; C = No hay Cantidad suficiente para el lote; S = Ya se han devuelto todos los lotes envasados
		try {
			con = bddConexion();
			// 1. Mirar que el lote existe
			String qry =
				" SELECT DISTINCT gpe.elaborado " +
				" FROM gp_envasado gpe " +
				" WHERE gpe.lote = '" + lote.getLote() + "'; ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			double elaborado = 0;
			if (rs.next()) {
				elaborado = rs.getDouble("elaborado");
				if (lote.getCantidad() > elaborado){
					comprobacion = 'C';
				}
			}else{
				//Comprobar si se trata de un producto venta
				qry =
					" SELECT * " +
					" FROM registroentrada re " +
					" WHERE re.idTipoRegistro = 'P' " +
						" AND re.codigoEntrada = '" + lote.getLote() + "'; ";
				// System.out.println(qry);
				ps = con.prepareStatement(qry);
				rs = ps.executeQuery();
				if (rs.next()) {
					//OK
				}else{
					comprobacion = 'N';
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		return comprobacion;
	}
	
	//@Override
	public Vector<LineaProducto> getProductosComponenLote(String lote) throws Exception{
		Vector<LineaProducto> productos = new Vector<LineaProducto>();
		try {
			con = bddConexion();
			String qry =
				" SELECT DISTINCT gped.codigoEntrada " +
				" FROM gp_envasado_detalle gped, gp_envasado gpe " +
				" WHERE gpe.lote = '" + lote + "' " +
					" AND gpe.orden = gped.orden; ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LineaProducto lp = new LineaProducto();
				String loteProducto = rs.getString("codigoEntrada");
				lp.setLote(loteProducto);
				productos.add(lp);
				if (loteProducto.charAt(0) == '0')
					productos.addAll(getProductosComponenLote(loteProducto));
			}
			//Ahora, para cada producto, necesitamos saber el id del producto, nombre del producto, tipo de producto
			for (int i = 0; i < productos.size(); i++){
				LineaProducto lp = productos.get(i);
				String loteProducto = lp.getLote();
				if (loteProducto.charAt(0) == '0'){
					//Producto envasado
					long idProducto = 0;
					String nombreProducto = "", orden = "";
					//Comprobamos si se trata de una agrupación
					//Buscamos la orden de envasado para el lote, y el id del producto
					qry =
						" SELECT DISTINCT deta.idProducto, gpe.orden, p.nombre " +
						" FROM gp_envasado gpe, gp_envasado_detalle deta, producto p " +
						" WHERE gpe.lote = '" + lote + "' " +
							" AND deta.idProducto = p.idProducto; ";
					// System.out.println(qry);
					ps = con.prepareStatement(qry);
					rs = ps.executeQuery();
					if (rs.next()) {
						idProducto = rs.getLong("idProducto");
						nombreProducto = rs.getString("nombre");
						orden = rs.getString("orden");
					}
					qry =
						" SELECT DISTINCT gpea.idAgrupacion, p.nombre " +
						" FROM gp_envasado_agrupacion gpea, producto p " +
						" WHERE gpea.ordenEnvasado = '" + orden + "' " +
								" AND gpea.idAgrupacion = p.idProducto; ";
					// System.out.println(qry);
					ps = con.prepareStatement(qry);
					rs = ps.executeQuery();
					if (rs.next()) {
						idProducto = rs.getLong("idAgrupacion");
						nombreProducto = rs.getString("nombre");
					}
					lp.setIdProducto(idProducto);
					lp.setNombre(nombreProducto);
				}else{
					if (loteProducto.charAt(0) == 'E'){
						//Registro de entrada
						//Miramos si es envase, materia prima, o producto venta
						char tipoRegistro = 'X';
						long idProducto = 0, idEnvase = 0, idCategoria = 0;
						qry =
							" SELECT DISTINCT re.idTipoRegistro, re.idProducto, re.idEnvase, re.idCategoria " +
							" FROM registroentrada re " +
							" WHERE re.codigoEntrada = '" + loteProducto + "'; ";
						// System.out.println(qry);
						ps = con.prepareStatement(qry);
						rs = ps.executeQuery();
						if (rs.next()) {
							tipoRegistro = rs.getString("idTipoRegistro").charAt(0);
							idProducto = rs.getLong("idProducto");
							idProducto = rs.getLong("idProducto");
							idCategoria = rs.getLong("idCategoria");
							idEnvase = rs.getLong("idEnvase");
						}
						//En función de lo que sea, obtenemos el id del producto y el nombre
						if (tipoRegistro == 'M'){
							qry =
								" SELECT m.nombre, c.nombre AS nombreCate " +
								" FROM materiaprima_categoria mc, materiaprima m, categoria c " +
								" WHERE mc.idMateriaPrima = '" + idProducto + "' " +
										" AND mc.idCategoria = " + idCategoria + 
										" AND mc.idMateriaPrima = m.idProducto " +
										" AND mc.idCategoria = c.idCategoria; ";
							// System.out.println(qry);
							ps = con.prepareStatement(qry);
							rs = ps.executeQuery();
							if (rs.next()) {
								lp.setIdProducto(idProducto);
								lp.setNombre(rs.getString("nombre") + " - " + rs.getString("nombreCate"));
							}
						}else{
							if (tipoRegistro == 'E'){
								qry =
									" SELECT e.nombre " +
									" FROM envase e " +
									" WHERE e.idEnvase = '" + idEnvase + "'; ";
								// System.out.println(qry);
								ps = con.prepareStatement(qry);
								rs = ps.executeQuery();
								if (rs.next()) {
									lp.setIdProducto(idEnvase);
									lp.setNombre(rs.getString("nombre"));
								}
							}else{
								if (tipoRegistro == 'P'){
									qry =
										" SELECT p.nombre " +
										" FROM producto p " +
										" WHERE p.idProducto = '" + idProducto + "'; ";
									// System.out.println(qry);
									ps = con.prepareStatement(qry);
									rs = ps.executeQuery();
									if (rs.next()) {
										lp.setIdProducto(idProducto);
										lp.setNombre(rs.getString("nombre"));
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		return productos;
	}
	
	public Vector<Producto> getTrazabilidadRegistroEntrada(String registro) throws Exception{
		Vector<Producto> productos = new Vector<Producto>();
		try {
			con = bddConexion();
			String qry =
				" SELECT gpe.orden, gpe.lote, gped.cantidadReal, gpe.fecha, " +
					" IF (gpea.idAgrupacion > 0, " +
						" ( SELECT nombre FROM producto WHERE idProducto = gpea.idAgrupacion ), " +
						" ( SELECT nombre FROM producto WHERE idProducto = gpe.idProducto ) " +
					" ) AS nombreProducto " +
				" FROM gp_envasado gpe " +
				" LEFT JOIN gp_envasado_detalle gped ON gped.orden = gpe.orden " +
				" LEFT JOIN producto p ON p.idProducto = gpe.idProducto " +
				" LEFT JOIN gp_envasado_agrupacion gpea ON gpea.ordenEnvasado = gpe.orden " +
				" LEFT JOIN producto p1 ON p1.idProducto = gpea.idAgrupacion " +
				" WHERE gped.codigoEntrada = '" + registro + "' ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setLote(rs.getString("lote"));
				producto.setCantidad(rs.getDouble("cantidadReal"));
				producto.setNombre(rs.getString("nombreProducto"));
				producto.setDescripcion(rs.getString("fecha"));
				productos.add(producto);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		return productos;
	}
	
	public void actualizaCaducidadEnvasado(String proceso, String caducidad, String usuario) throws Exception{
		try {
			con = bddConexion();
			String fechaVieja = "";
			String qry =
				" SELECT gpe.fechaCaducidad " +
				" FROM gp_envasado gpe " +
				" WHERE gpe.orden = '" + proceso + "' ";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				fechaVieja = rs.getString("fechaCaducidad");
			}
			rs.close();
			ps.close();
			String insertString = " UPDATE gp_envasado SET fechaCaducidad = '" + caducidad + "' WHERE orden = '" + proceso + "'; ";
			System.out.println(insertString);
			Statement stmt;
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			insertString = " INSERT INTO log_fechaCaducidad (orden, fecha, nuevaFecha, usuario, timestamp) VALUES ('" + proceso + "', '" + fechaVieja + "', '" + caducidad + "', '" + usuario + "', CURRENT_TIMESTAMP); ";
			System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertString);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
}