package es.induserco.opilion.datos.ubicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Incidencia;
import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.data.entidades.MovimientoAlmacen;
import es.induserco.opilion.data.entidades.ProductoUbicado;
import es.induserco.opilion.data.entidades.RegistroUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.UbicacionZona;
import es.induserco.opilion.data.entidades.Vehiculo;
import es.induserco.opilion.infraestructura.DatabaseConfig;

/**
 * @author andres (07/06/2011) (21/11/2011)
 * @version 1.6
 */
public class UbicacionDAO extends DatabaseConfig implements IUbicacionDataService {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;
	
	//@Override
	public Vector<Ubicacion> getUbicaciones(Long idZona) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Ubicacion> resultado = new Vector<Ubicacion>();
		try {
			//Obtenemos la conexión a la base de datos.
			con = bddConexion();
			String Qry =
				" SELECT b.idUbicacion as idUbicacion,a.nombre AS nombre,b.pasillo AS pasillo, " +
					" b.estanteria as estanteria,a.nave AS nave,b.altura AS altura " +
				" FROM ubicacion b, ubicacionzona a " +
				" WHERE a.idZona = b.idZona AND b.idZona = " + idZona;			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setIdUbicacion(rs.getLong("idUbicacion"));
				ubicacion.setNombre(rs.getString("nombre"));
				ubicacion.setPasillo(rs.getString("pasillo"));				
				ubicacion.setEstanteria(rs.getString("estanteria"));
				ubicacion.setAltura(rs.getString("altura"));
				//La añadimos al Vector de resultado
				resultado.add(ubicacion);
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
	public Boolean addUbicacion(Ubicacion ubicacion) throws Exception {
		// System.out.println("DAO addUbicacion");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			// System.out.println("SQL");
			//Obtenemos la conexión a la base de datos.
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idUbicacion) as idMaxUbicacion FROM ubicacion");
			rs = ps.executeQuery();
			long idUbicacionMax =0 ;
			while(rs.next()){
				Ubicacion ubicacionMaximo = new Ubicacion();
				ubicacionMaximo.setIdUbicacion(rs.getLong("idMaxUbicacion"));
				idUbicacionMax= ubicacionMaximo.getIdUbicacion() + 1;
			}
			// System.out.println("el id de ubicacion es... "+ idUbicacionMax);
			//SQL de insersion
			String insertString= 
				"insert into ubicacion(idUbicacion,idTipoUbicacion,idZona,estanteria,pasillo,altura,nombre,descripcion,dimensiones) values(" +
				idUbicacionMax + ","
				+ ubicacion.getIdTipoUbicacion()+","
				+ ubicacion.getIdZona()+",'"				
				+ ubicacion.getEstanteria()+"','"
				+ ubicacion.getPasillo() +"','"
				+ ubicacion.getAltura() +"','"
				+ ubicacion.getNombre() +"','"
				+ ubicacion.getDescripcion() +"','"		
				+ ubicacion.getDimensiones() +"')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
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
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Boolean updateUbicacion(Ubicacion ubicacionFind, Ubicacion ubicacionUpdate) throws Exception {
		// System.out.println("DAO updateUbicacion");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			// System.out.println("SQL");
			con = bddConexion();
			//SQL de insersion
			String updateString= 
				"UPDATE ubicacion SET " 
				+ "idTipoUbicacion = " + ubicacionUpdate.getIdTipoUbicacion() + ","
				+ "idZona = " + ubicacionUpdate.getIdZona() + ","	
				+ "estanteria = '" + ubicacionUpdate.getEstanteria() + "',"
				+ "pasillo = '" + ubicacionUpdate.getPasillo() + "',"					
				+ "altura = '" + ubicacionUpdate.getAltura() + "',"	
				+ "nombre = '" + ubicacionUpdate.getNombre() + "',"	
				+ "descripcion = '" + ubicacionUpdate.getDescripcion() + "',"	
				+ "dimensiones = '" + ubicacionUpdate.getDimensiones() + "'"					
				+ " WHERE idUbicacion =" + ubicacionFind.getIdUbicacion();
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("Ubicacion ACTUALIZADO");
				resultado=true;
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
	public Ubicacion loadUbicacion(Ubicacion ubicacion) throws Exception {
		//Inicializamos el Vector de retorno.
		Ubicacion ubicacionConsulta = new Ubicacion();
		// System.out.println("DAO loadUbicacion");
		try {
			con = bddConexion();
			ps = con.prepareStatement("SELECT * FROM  ubicacion " +
					" WHERE habilitado = 'S' " +
						" AND idUbicacion = " + ubicacion.getIdUbicacion());
			rs = ps.executeQuery();
			while (rs.next()) {
				// System.out.println("devuelve la ubicacion correspondiente");				
				//Completamos los datos del Libro en la ubicacion
				ubicacionConsulta.setIdUbicacion(rs.getLong("idUbicacion"));
				ubicacionConsulta.setIdTipoUbicacion(rs.getLong("idTipoUbicacion"));
				ubicacionConsulta.setEstanteria(rs.getString("estanteria"));
				ubicacionConsulta.setPasillo(rs.getString("pasillo"));
				ubicacionConsulta.setAltura(rs.getString("altura"));
				ubicacionConsulta.setIdZona(rs.getLong("idZona"));
				ubicacionConsulta.setNombre(rs.getString("nombre"));
				ubicacionConsulta.setDescripcion(rs.getString("descripcion"));
				ubicacionConsulta.setDimensiones(rs.getString("dimensiones"));
			}
			// System.out.println("ubica"+ubicacionConsulta.getIdUbicacion());
			// System.out.println("estanteria"+ubicacionConsulta.getEstanteria());
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
		return ubicacionConsulta;
	}

	//@Override
	public Vector<UbicacionZona> getDireccionesUbicacion() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<UbicacionZona> resultado = new Vector<UbicacionZona>();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM  ubicacionzona ORDER BY nombre";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				UbicacionZona zona = new UbicacionZona();
				zona.setIdZona(rs.getLong("idZona"));
				zona.setNombre(rs.getString("nombre"));
				//La añadimos al Vector de resultado
				resultado.add(zona);
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
	public Boolean addRegistroUbicacionTmp(RegistroUbicacion entry) throws Exception {
		return addRegistroUbicacion(entry, true);
	}

	//@Override
	public Boolean addRegistroUbicacion(RegistroUbicacion entry) throws Exception {
		return addRegistroUbicacion(entry, false);
	}

	//@Override
	public Boolean addRegistroUbicacion(RegistroUbicacion entry, Boolean temp) throws Exception {
		// System.out.println("DAO addRegistroUbicacion");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT max(idRegistroUbicacion) as idMaxUbicacion FROM registroubicacion");
			rs = ps.executeQuery();
			long idUbicacionMax =0 ;
			if(rs.next()){
				RegistroUbicacion ubicacionMaximo = new RegistroUbicacion();
				ubicacionMaximo.setIdRegistroUbicacion(rs.getLong("idMaxUbicacion"));
				idUbicacionMax= ubicacionMaximo.getIdRegistroUbicacion()+1;
			}else
				idUbicacionMax = 1;
			// System.out.println("el id de ubicacion es... "+ idUbicacionMax);
			ps = con.prepareStatement("SELECT max(idRegistroUbicacion) as idMaxUbicacion FROM registroubicacion_tmp");
			rs = ps.executeQuery();
			if(rs.next()){
				Long maxim =(rs.getLong("idMaxUbicacion"));
				// System.out.println("MAX: "+maxim);
				if((maxim+1)>idUbicacionMax) {
					// System.out.println("Temporal: " + maxim + " Normal:"+idUbicacionMax);
					idUbicacionMax = maxim+1;
				}
			}
			// System.out.println("DESPUES:el id de entrada es... "+ idUbicacionMax);
			//SQL de insercion
			String insertString= "insert into registroubicacion";
			if (temp) {
				insertString +="_tmp ";
			}
			insertString+="(idRegistroUbicacion,codigoEntrada,idUbicacion,responsable) values(" +
				idUbicacionMax + ",'"
				+ entry.getCodigoEntrada()+"',"
				+ entry.getIdUbicacion()+",'"
				+ entry.getResponsable()+"')";
			// System.out.println(insertString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
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
			} catch (Exception e) { e.printStackTrace(); }
		}
	}	

	//@Override
	public Vector<Ubicacion> getPasillosUbicacion() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Ubicacion> resultado = new Vector<Ubicacion>();
		try {
			con = bddConexion();
			String Qry="SELECT distinct pasillo as pasillo FROM ubicacion order by pasillo";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setPasillo(rs.getString("pasillo"));
			
				//La añadimos al Vector de resultado
				resultado.add(ubicacion);
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
	public Vector<Ubicacion> getEstanteriasUbicacion() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Ubicacion> resultado = new Vector<Ubicacion>();
		try {
			con = bddConexion();
			String Qry="SELECT distinct estanteria as estanteria FROM ubicacion order by estanteria";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setEstanteria(rs.getString("estanteria"));
			
				//La añadimos al Vector de resultado
				resultado.add(ubicacion);
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
	public Vector<Ubicacion> getAlturasUbicacion() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Ubicacion> resultado = new Vector<Ubicacion>();
		try {
			con = bddConexion();
			String Qry="SELECT distinct altura as altura FROM ubicacion order by altura";			
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setAltura(rs.getString("altura"));
				//La añadimos al Vector de resultado
				resultado.add(ubicacion);
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
	 * Purga temporales.
	 *
	 * @param con the con
	 * @throws SQLException the sQL exception
	 */
	@SuppressWarnings("unused")
	private void purgaTemporales() throws SQLException {
		try {
			con = bddConexion();
		    // System.out.print(" Purgando temporales");
			//Borrando contenido SQL
		    String deleteSql = "DELETE FROM registroubicacion_tmp";
			// System.out.println(deleteSql);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(deleteSql);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}

	//@Override
	public Boolean addRegistrosTemporales() throws Exception {
		// System.out.println("DAO addRegistrosTemporales");
		try {
			con = bddConexion();
			//Borrando contenido SQL		    
		    String insertSql = "INSERT INTO registroubicacion " +
		    		" SELECT * FROM  registroubicacion_tmp";
			// System.out.println(insertSql);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertSql);	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return true;
	}
	
	public String getPlanoAlmacen(int idAlmacen) throws Exception{
		String url = "";
		try {
			con = bddConexion();
			String Qry = "SELECT DISTINCT urlPlano FROM " +
					" ubicacion_almacen u WHERE u.idAlmacen = " + idAlmacen;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				url = rs.getString("urlPlano");
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
		return url;
	}
	
	//@Override
	public Vector<Ubicacion> getHuecosOcupados(Ubicacion ubica) throws Exception{
		Vector<Ubicacion> ocupados = new Vector<Ubicacion>();
		try {
			con = bddConexion();
			String Qry = "SELECT DISTINCT uh.idHueco " +
					" FROM ubicacion_hueco uh, ubicacion_piso up, ubicacion_estanteria ue, " +
					" ubicacion_linea ul " +
					" WHERE ul.idLinea =" + ubica.getIdLinea() + " AND uh.idPiso = up.idPiso " +
						" AND up.idEstanteria = ue.idEstanteria " +
						" AND ue.idLinea = ul.idLinea " +
						" AND uh.idHueco IN " +
						" ( " +
						" SELECT DISTINCT idHueco " +
						" FROM ubicacion u " +
						" ) ORDER BY idhueco";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ubicacion u = new Ubicacion();
				u.setIdHueco(rs.getLong("idHueco"));
				u.setIdLinea(ubica.getIdLinea());
				ocupados.add(u);
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
		return ocupados;
	}
	
	//@Override
	public long getIdUbicacion(long idHueco, String registro) throws Exception{
		long idUbicacion = 0;
		try {
			con = bddConexion();
			String Qry = "SELECT * " +
					" FROM ubicacion u " +
					" WHERE u.idHueco = '" + idHueco + "' " +
							" AND u.registro='" + registro + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				idUbicacion = rs.getLong("idUbicacion");
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
		return idUbicacion;
	}
	
	/**
	 * Devuelve un almacen, zona, linea, etc... dependiendo de la ubicación pasada
	 * @param ubica Ubicación donde se define el id del almacen, zona... que buscamos
	 * @return Ubicacion buscada
	 * @author andres (7/06/2011)
	 * @since 1.2
	 */
	//@Override
	public Ubicacion getUbicacion(Ubicacion ubica) throws Exception{
		Ubicacion u = new Ubicacion();
		try {
			con = bddConexion();
			String Qry = "";
			if (ubica.getIdHueco() > 0)
				Qry =
					" SELECT DISTINCT uh.descripcion as ref, ua.idAlmacen, ua.urlPlano as urlPlanoAlmacen, " +
						" ua.descripcion as descAlmacen, uz.idZona, uz.descripcion as descZona, " +
						" ul.idLinea, ul.descripcion as descLinea, ue.idEstanteria, ue.descripcion as descEstanteria, " +
						" up.idPiso, up.descripcion as descPiso, uh.idHueco, uh.descripcion as descHueco, ul.urlPlano " +
					" FROM  ubicacion_hueco uh " +
						" INNER JOIN ubicacion_piso up ON uh.idPiso = up.idPiso  " +
						" INNER JOIN ubicacion_estanteria ue ON up.idEstanteria = ue.idEstanteria  " +
						" INNER JOIN ubicacion_linea ul ON ue.idLinea = ul.idLinea  " +
						" INNER JOIN ubicacion_zona uz	ON ul.idZona = uz.idZona  " +
						" INNER JOIN ubicacion_almacen ua ON uz.idAlmacen = ua.idAlmacen  " +
					" WHERE uh.idHueco = " + ubica.getIdHueco();
			else
				if (ubica.getIdLinea() > 0 && ubica.getIdZona() > 0 && ubica.getIdAlmacen() > 0)
					Qry =
						" SELECT DISTINCT ua.idAlmacen, ua.urlPlano as urlPlanoAlmacen, ua.descripcion as descAlmacen, " +
							" uz.descripcion as descZona, ua.idDireccion, uz.descripcion, uz.idZona, uz.idZonaAlmacen, " +
							" ul.urlPlano, ul.idLinea, ul.idLineaZona, ul.descripcion as descLinea " +
						" FROM ubicacion_linea ul " +
							" INNER JOIN ubicacion_zona uz ON ul.idZona = uz.idZona " +
							" INNER JOIN ubicacion_almacen ua ON uz.idAlmacen = ua.idAlmacen " +
						" WHERE ul.idLinea = " + ubica.getIdLinea();
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (ubica.getIdLinea() > 0){
					u.setIdLinea(rs.getLong("idLinea"));
					u.setNombreLinea(rs.getString("descLinea"));
					u.setUrlPlanoLinea(rs.getString("urlPlano"));
					u.setIdZona(rs.getLong("idZona"));
					u.setNombreZona(rs.getString("descZona"));
					u.setIdAlmacen(rs.getLong("idAlmacen"));
					u.setNombreAlmacen(rs.getString("descAlmacen"));
					u.setUrlPlanoAlmacen(rs.getString("urlPlanoAlmacen"));
				}
				if (ubica.getIdHueco() > 0){
					u.setIdHueco(rs.getLong("idHueco"));
					u.setNombreHueco(rs.getString("descHueco"));
					u.setIdPiso(rs.getLong("idPiso"));
					u.setNombrePiso(rs.getString("descPiso"));
					u.setIdEstanteria(rs.getLong("idEstanteria"));
					u.setNombreEstanteria(rs.getString("descEstanteria"));
					u.setIdLinea(rs.getLong("idLinea"));
					u.setNombreLinea(rs.getString("descLinea"));
					u.setIdZona(rs.getLong("idZona"));
					u.setNombreZona(rs.getString("descZona"));
					u.setIdAlmacen(rs.getLong("idAlmacen"));
					u.setNombreAlmacen(rs.getString("descAlmacen"));
					//u.setCantidad(rs.getDouble("cantidad"));
					u.setDescripcion(rs.getString("ref"));
					//u.setIdUbicacion(rs.getLong("idUbicacion"));
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
		return u;
	}
	
	/**
	 * @author andres (07/06/2011)
	 * @since 1.2
	 */
	//@Override
	public long guardarRegistro(long idHueco, String registro, long idPalet) throws Exception {
		// System.out.println("DAO guardarRegistro");
		long idUbicacionMax = 0 ;
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Statement stmt;
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idUbicacion) AS idMaxUbicacion FROM ubicacion");
			rs = ps.executeQuery();
			if(rs.next()){
				idUbicacionMax = rs.getLong("idMaxUbicacion") + 1;
			}else
				idUbicacionMax = 1;
			String insertSql = "INSERT INTO ubicacion (idUbicacion, idHueco, registro, idPalet) VALUES (" +
		    		idUbicacionMax + "," + idHueco + ",'" + registro + "'," + idPalet + ")";
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertSql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return idUbicacionMax;
	}
	
	/**
	 * @author andres (07/06/2011)
	 * @since 1.2
	 * Devuelve los registros almacenados en un hueco determinado
	 * @param idHueco
	 * @return
	 * @throws Exception
	 */
	//@Override
	public Vector<Ubicacion> getRegistrosAlmacenados(long idHueco) throws Exception{
		Vector<Ubicacion> almacenados = new Vector<Ubicacion>();
		try {
			if (con == null || con.isClosed())
				con = bddConexion();
			String Qry =
				" SELECT u.registro, u.cantidad, u.idUbicacion, u.idPalet, " +
					" uh.idHueco, uh.descripcion as nombreHueco, uz.descripcion as nombreZona, " +
					" ul.descripcion as nombreLinea, gea.idAgrupacion,/* p2.nombre,*/ IF (re.lote IS NOT NULL, re.lote, oe.codigoOrden) AS lote, " +
					" re2.idProducto, re2.idEnvase, IF (e.nombre IS NOT NULL, e.nombre, 'Tierrina Vaqueira (Envasado)') AS nombreProveedor, " +
					" IF( " +
						" CONCAT(mp.nombre, ' - ', c.nombre) IS NOT NULL, " +
						" CONCAT(mp.nombre, ' - ', c.nombre), " +
						" IF (" +
							" enva.nombre IS NOT NULL, " +
							" enva.nombre, " +
							" IF ( " +
								" gea.idAgrupacion > 0 AND p2.nombre IS NOT NULL, " +
								" p2.nombre, " +
								" IF ( " +
									" p.nombre IS NOT NULL, " +
									" p.nombre, " +
									" p3.nombre " +
								" ) " +
							" ) " +
						" ) " +
					" ) AS nombreProducto, " +
					" IF ( " +
						" ge.fecha IS NOT NULL, " +
						" ge.fecha, " +
						" oe.fecha" +
					" ) AS fecha " +
			" FROM ubicacion u " +
				" INNER JOIN ubicacion_hueco uh ON u.idHueco = uh.idHueco " +
				" INNER JOIN ubicacion_piso up ON uh.idPiso = up.idPiso " +
				" INNER JOIN ubicacion_estanteria ue ON up.idEstanteria = ue.idEstanteria " +
				" INNER JOIN ubicacion_linea ul ON ue.idLinea = ul.idLinea " +
				" INNER JOIN ubicacion_zona uz ON ul.idZona = uz.idZona " +
				 
				" LEFT JOIN gp_envasado ge ON ge.lote = u.registro " +
				" LEFT JOIN producto p ON ge.idProducto = p.idProducto " +
				 
				" LEFT JOIN gp_envasado_agrupacion gea ON gea.ordenEnvasado = ge.orden " +
				 
				" LEFT JOIN producto p2 ON gea.idAgrupacion = p2.idProducto " +
				 
				" LEFT JOIN gp_envasado_detalle deta ON deta.orden = ge.orden " +
				" LEFT JOIN registroentrada re ON re.codigoEntrada = deta.codigoEntrada AND deta.idTipoRegistro = 'M' " +
				 
				" LEFT JOIN registroentrada re2 ON re2.codigoEntrada = u.registro " +
				" LEFT JOIN ordenentrada oe ON oe.codigoOrden = re2.codigoOrden " +
				" LEFT JOIN entidad e ON e.idUsuario = oe.idProveedor " +
				" LEFT JOIN materiaprima mp ON mp.idProducto = re2.idProducto " +
				" LEFT JOIN materiaprima_categoria mpc ON mpc.idMateriaPrima = mp.idProducto " +
				" LEFT JOIN categoria c ON c.idCategoria = mpc.idCategoria " +
				 
				" LEFT JOIN envase enva ON enva.idEnvase = re2.idEnvase " +
				 
				" LEFT JOIN producto p3 ON re2.idProducto = p3.idProducto " +
			
			" WHERE u.idHueco = " + idHueco + " AND u.habilitado = 'S' AND u.cantidad > 0 " +
			 
			" GROUP BY u.registro; ";
			
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ubicacion u = new Ubicacion();
				u.setRegistro(rs.getString("registro"));
				u.setFecha(rs.getString("fecha"));
				u.setProveedor(rs.getString("nombreProveedor"));
				u.setNombreProducto(rs.getString("nombreProducto"));
				u.setOrden(rs.getString("lote"));
				
				u.setCantidad(rs.getDouble("cantidad"));
				u.setIdHueco(rs.getLong("idHueco"));
				u.setIdUbicacion(rs.getLong("idUbicacion"));
				u.setIdPalet(rs.getLong("idPalet"));
				u.setNombreLinea(rs.getString("nombreLinea"));
				u.setNombreHueco(rs.getString("nombreHueco"));
				u.setNombreZona(rs.getString("nombreZona"));
				u.setFecha(formatearFecha(u.getFecha()));
				almacenados.add(u);
			}
			/*String Qry =
				" SELECT registro,cantidad,idUbicacion,idPalet,uh.idHueco,uh.descripcion as nombreHueco," +
					" uz.descripcion as nombreZona, ul.descripcion as nombreLinea" +
				" FROM ubicacion u, ubicacion_hueco uh, ubicacion_piso up,ubicacion_estanteria ue,ubicacion_linea ul," +
					" ubicacion_zona uz " +
				" WHERE u.idHueco = " + idHueco + " " +
					" AND u.idHueco = uh.idHueco " +
					" AND uh.idPiso = up.idPiso " +
					" AND up.idEstanteria = ue.idEstanteria " +
					" AND ue.idLinea = ul.idLinea " +
					" AND ul.idZona = uz.idZona " +
					" AND u.habilitado = 'S' " +
					" AND cantidad > 0; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ubicacion u = new Ubicacion();
				u.setRegistro(rs.getString("registro"));
				PreparedStatement pst = null;
				ResultSet rse = null;
				if (u.getRegistro()!= null && u.getRegistro().compareTo("") != 0 &&
						u.getRegistro().charAt(0) == 'E'){
					//Mirar primero si se trata de entrada de materia prima o de entrada de envase
					Qry =
						" SELECT re.idProducto, re.idCategoriaEntrada, re.idEnvase " +
						" FROM registroentrada re " +
						" WHERE re.codigoEntrada = '" + u.getRegistro() + "'; ";
					// System.out.println(Qry);
					pst = con.prepareStatement(Qry);
					rse = pst.executeQuery();
					long idProducto = 0, idEnvase = 0;
					if (rse.next()){ idProducto = rse.getLong("idProducto"); idEnvase = rse.getLong("idEnvase"); }
					if (idProducto > 0)//Materia prima
						Qry =
							" SELECT oe.fecha, e.nombre, CONCAT(mp.nombre, ' - ', c.nombre) as nombreProducto " +
							" FROM registroentrada re, ordenentrada oe, entidad e, " +
								" materiaprima_categoria mpc, materiaprima mp, categoria c " +
							" WHERE oe.idProveedor=e.idUsuario AND oe.codigoOrden = re.codigoOrden" +
								" AND re.codigoEntrada='" + u.getRegistro() + "' AND mp.idProducto = re.idProducto " +
								" AND re.idCategoriaEntrada = c.idCategoria AND mpc.idMateriaPrima = mp.idProducto " +
								" AND mpc.idCategoria = c.idCategoria";
					else
						if (idEnvase > 0)//Envase
							Qry =
								"SELECT oe.fecha, e.nombre, enva.nombre as nombreProducto " +
								" FROM registroentrada re, ordenentrada oe, envase enva, entidad e " +
								" WHERE oe.idProveedor = e.idUsuario " +
									" AND oe.codigoOrden = re.codigoOrden " +
									" AND re.codigoEntrada='" + u.getRegistro() + "' AND enva.idEnvase = re.idEnvase";
					// System.out.println(Qry);
					pst = con.prepareStatement(Qry);
					rse = pst.executeQuery();
					if (rse.next()) {
						u.setFecha(rse.getString("fecha"));
						u.setProveedor(rse.getString("nombre"));
						u.setNombreProducto(rse.getString("nombreProducto"));
					}else{
						Qry =
							" SELECT p.nombre as nombreProducto, re.lote, oe.fecha, oe.idProveedor, e.nombre " +
							" FROM registroentrada re, producto p, ordenentrada oe, entidad e " +
							" WHERE re.codigoEntrada = '" + u.getRegistro() + "' " +
									" AND re.idProducto = p.idProducto " +
									" AND oe.codigoOrden = re.codigoOrden " +
									" AND e.idUsuario = oe.idProveedor ";
						// System.out.println(Qry);
						pst = con.prepareStatement(Qry);
						rse = pst.executeQuery();
						if (rse.next()) {
							u.setFecha(rse.getString("fecha"));
							u.setProveedor(rse.getString("nombre"));
							u.setNombreProducto(rse.getString("nombreProducto"));
						}
					}
				}else{
					if (u.getRegistro()!= null && u.getRegistro().compareTo("") != 0 &&
							u.getRegistro().charAt(0) == '0'){//envasado
						String registro = u.getRegistro();
						String[] frag = registro.split(" ");
						if (frag.length > 1)
							registro = frag[0];
						Qry =
							" SELECT p.nombre as nombreProducto, ge.fecha " +
							" FROM gp_envasado ge, producto p " +
							" WHERE ge.lote = '" + registro + "' " +
									" AND ge.idProducto = p.idProducto";
						// System.out.println(Qry);
						pst = con.prepareStatement(Qry);
						rse = pst.executeQuery();
						while (rse.next()) {
							u.setNombreProducto(rse.getString("nombreProducto"));
							u.setProveedor("Tierrina Vaqueira (Envasado)");
							u.setFecha(rse.getString("fecha"));
						}
						GestionProduccion gestion = new GestionProduccion();
						gestion.setLoteAsignado(u.getRegistro());
						String qry2 =
							" SELECT gea.idAgrupacion, p.nombre, gea.elaborado " +
							" FROM gp_envasado ge, gp_envasado_agrupacion gea, producto p " +
							" WHERE ge.habilitado = 'S' " +
								" AND ge.orden = gea.ordenEnvasado " +
								" AND ge.lote = '" + gestion.getLoteAsignado() + "' " +
								" AND p.idProducto = gea.idAgrupacion ";
						ResultSet rs2 = null;
						PreparedStatement ps2 = null;
						ps2 = con.prepareStatement(qry2);
						rs2 = ps2.executeQuery();
						if (rs2.next()) {
							u.setNombreProducto(rs2.getString("nombre"));
						}else{
						}
						String consulta =
							" SELECT re.lote " +
							" FROM registroentrada re " +
								" INNER JOIN gp_envasado_detalle deta ON re.codigoEntrada = deta.codigoEntrada AND deta.idTipoRegistro = 'M' " +
								" INNER JOIN gp_envasado gpe ON deta.orden = gpe.orden AND gpe.lote = '" + u.getRegistro() + "' ";
						PreparedStatement ps3 = con.prepareStatement(consulta);
						ResultSet rs3 = ps3.executeQuery();
						if (rs3.next()) {
							String loteEntrada = rs3.getString("lote");
							if (!loteEntrada.equals("")){
								u.setRegistro(u.getRegistro());
								u.setOrden(loteEntrada);
							}
						}
					}
				}
				u.setCantidad(rs.getDouble("cantidad"));
				u.setIdHueco(rs.getLong("idHueco"));
				u.setIdUbicacion(rs.getLong("idUbicacion"));
				u.setIdPalet(rs.getLong("idPalet"));
				u.setNombreLinea(rs.getString("nombreLinea"));
				u.setNombreHueco(rs.getString("nombreHueco"));
				u.setNombreZona(rs.getString("nombreZona"));
				u.setFecha(formatearFecha(u.getFecha()));
				almacenados.add(u);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return almacenados;
	}
	
	//Llega 2012-06-31, ó tb con la hora... 2012-06-31 00:...
	private String formatearFecha(String fecha){
		String formateada = "";
		if (fecha != null){
			String frag[] = fecha.split(" ");
			if (frag.length > 1){
				fecha = frag[0];
			}
			frag = fecha.split("-");
			String diaS = frag[2], mesS = frag[1], annioS = frag[0];
			if (diaS.length() == 1)
				diaS = "0" + diaS;
			if (mesS.length() == 1)
				mesS = "0" + mesS;
			annioS = annioS.substring(2);
			formateada = diaS + "/" + mesS + "/" + annioS;
		}
		return formateada;
	}
	
	/**
	 * @author andres (09/06/2011)
	 * @since 1.2
	 * Salvar en la base de datos las ubicaciones pasadas por parámetros
	 */
	//@Override
	public void salvarUbicaciones(Vector<Ubicacion> ubicaciones, boolean movimiento){
		// System.out.println("DAO salvarUbicaciones");
		try {
			if (!movimiento){
				Statement stmt;
				con = bddConexion();
				for (int i = 0; i < ubicaciones.size(); i++){
					Ubicacion u = ubicaciones.get(i);
					if (!u.getEstaUbicado()){
						String Qry = "";
						if (u.getCongelado() != null && (u.getCongelado().compareTo("F") == 0 || u.getCongelado().compareTo("C") == 0))
							Qry = "SELECT idUbicacion,registro,idPalet,cantidad FROM ubicacion u WHERE u.idHueco = " + u.getIdHueco() +
							" AND u.registro='" + u.getRegistro() + "'";
						else
							if (u.isSalidaEnvasado()){
								Qry = "SELECT idUbicacion,registro,idPalet,cantidad FROM ubicacion u WHERE u.idHueco = " + u.getIdUbicacion() +
									" AND u.registro='" + u.getRegistro() + "'";
							}else
								Qry = "SELECT idUbicacion,registro,idPalet,cantidad FROM ubicacion u WHERE u.idHueco = " + u.getIdHueco() +
								" AND u.registro='" + u.getRegistro() + "'";
						// System.out.println(Qry);
						ps = con.prepareStatement(Qry);
						rs = ps.executeQuery();
						boolean existe = false;
						double cantidad = 0;
						long idUbicacion = 0;
						while (rs.next()) {
							idUbicacion = rs.getLong("idUbicacion");
							cantidad = rs.getDouble("cantidad");
							existe = true;
						}
						String insertSql = "";
						if (existe){
							insertSql = "UPDATE ubicacion SET cantidad = '" + (cantidad + u.getCantidad()) + 
								"' WHERE idUbicacion = " + idUbicacion;
						}else{
							if (u.isSalidaEnvasado()){	
								insertSql = "INSERT INTO ubicacion (idHueco, registro, cantidad, idPalet) VALUES (" +
									u.getIdUbicacion() + ",'" + u.getRegistro() + "','" + u.getCantidad() + "','" + u.getIdPalet() + "')";
							}else
								insertSql = "INSERT INTO ubicacion (idHueco, registro, cantidad, idPalet, orden, numeroBultos) VALUES (" +
									u.getIdHueco() + ",'" + u.getRegistro() + "','" + u.getCantidad() + "','" + u.getIdPalet() +
									"','" + u.getOrden() + "'," + u.getNumeroBultos() + ")";
						}
						// System.out.println(insertSql);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertSql);
						//Registramos el movimiento
						insertSql =
							" INSERT INTO ubicacion_movimiento (idHueco, registro, cantidad, responsable, fecha, sacarMeter) " +
								" VALUES(" + u.getIdHueco() + ", '" + u.getRegistro() +
								"', " + u.getCantidad() + ", '" + u.getUsuarioResponsable() + "', CURRENT_TIMESTAMP(), 'M'); ";
						// System.out.println(insertSql);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertSql);
						if (u.isSalidaEnvasado()){
							insertSql = "UPDATE gp_envasado_ubicacion SET idHuecoNuevo = " + u.getIdUbicacion() +" " +
										" WHERE ordenEnvasado = '" + u.getOrden() + "' " +
												"AND codigoEntrada = '" + u.getRegistro() + "' AND idHueco = " + u.getIdHueco();
							// System.out.println(insertSql);
							stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
							stmt.executeUpdate(insertSql);
						}
						if (u.getCongelado() != null && (u.getCongelado().compareTo("F") == 0 || u.getCongelado().compareTo("C") == 0)){
							Qry = "SELECT idUbicacion,registro,idPalet,cantidad FROM ubicacion u WHERE u.idHueco = " + u.getIdHueco() +
							" AND u.registro='" + u.getRegistro() + "'";
							// System.out.println(Qry);
							ps = con.prepareStatement(Qry);
							rs = ps.executeQuery();
							while (rs.next()) {
								idUbicacion = rs.getLong("idUbicacion");
							}
							actualizacionGenerica("ubicacion u", "u.congelado",
									u.getCongelado(), "u.idUbicacion='" + idUbicacion + "'");
							actualizacionGenerica("ubicacion u", "u.orden",
									u.getOrden(), "u.idUbicacion='" + idUbicacion + "'");
						}
					}
				}
			}else
				if (movimiento){
					// System.out.println("REALIZAMOS LA TRANSACCION DEL MOVIMIENTO");
					Ubicacion meter = ubicaciones.get(0);
					Ubicacion sacar = new Ubicacion();
					con = bddConexion();
					String consulta =
						" SELECT * " +
						" FROM ubicacion_tmp u " +
						" WHERE u.registro = '" + meter.getRegistro() + "' " +
							" AND u.cantidad = '" + meter.getCantidad() + "' " +
							" AND u.idPalet = '" + meter.getIdPalet() + "' " +
							" AND u.movido = 'N' " +
							" AND u.cancelado = 'N'; ";
					// System.out.println(consulta);
					ps = con.prepareStatement(consulta);
					rs = ps.executeQuery();
					long idHuecoSacar = 0;
					while (rs.next()) {
						sacar.setRegistro(rs.getString("registro"));
						sacar.setCantidad(Double.parseDouble(rs.getString("cantidad")));
						sacar.setIdPalet(Integer.parseInt(rs.getString("idPalet")));
						sacar.setOrden(rs.getString("orden"));
						sacar.setUsuarioResponsable(rs.getString("responsable"));
						idHuecoSacar = rs.getLong("idHueco");
					}
					String Qry =
						" SELECT idUbicacion " +
						" FROM ubicacion u " +
						" WHERE u.idHueco = " + idHuecoSacar +
							" AND u.registro = '" + meter.getRegistro() + "' " +
							" AND idPalet = " + sacar.getIdPalet();
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					long idUbicacion = 0;
					while (rs.next()) {
						idUbicacion = rs.getLong("idUbicacion");
					}
					sacar.setIdUbicacion(idUbicacion);
					Vector<Ubicacion> ubs = new Vector<Ubicacion>();
					ubs.add(sacar);
					this.registrarSacarUbicaciones(ubs);//sacar
					ubicaciones.get(0).setOrden(sacar.getOrden());
					this.salvarUbicaciones(ubicaciones, false);//meter
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) { // System.out.println("error"); e.printStackTrace();
			}
		}
	}
	
	/**
	 * Saca de su ubicacion a cada una de las ubicaciones pasadas por parámetros
	 * @author andres (28/11/11)
	 * @since 1.6
	 */
	//@Override
	public boolean sacarUbicaciones(Vector<Ubicacion> ubicaciones, boolean incidencia,
			boolean registroEntrada, boolean produccion){
		//Si estamos moviendo entre almacenes, aquí solo mueve temporalmente. espera para hacer la transaccion completa
		boolean retorno = false;
		// System.out.println("DAO sacarUbicaciones");
		if (incidencia || produccion){
			try {
				Statement stmt;
				int res = 0;
				con = bddConexion();
				for (int i = 0; i < ubicaciones.size(); i++){
					Ubicacion u = ubicaciones.get(i);
					String Qry = "SELECT u.idHueco,cantidad,congelado, orden " +
							" FROM ubicacion u WHERE u.idUbicacion = " + u.getIdUbicacion();
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					double cantidad = 0;
					String congelado = "";
					String orden = "";
					long idHueco = 0;
					while (rs.next()){
						idHueco = rs.getLong("idHueco");
						cantidad = rs.getDouble("cantidad");
						congelado = rs.getString("congelado");
						orden = rs.getString("orden");
					}
					double cantidadSacar = u.getCantidad();
					double nuevaCantidad = cantidad - cantidadSacar;
					String insertSql = "";
					if (nuevaCantidad > 0){//UPDATE
						insertSql = "UPDATE ubicacion SET cantidad = " + nuevaCantidad +
								" WHERE ubicacion.idUbicacion = " + u.getIdUbicacion();
					}else{//DELETE
						insertSql = "DELETE FROM ubicacion WHERE idUbicacion = " +
								u.getIdUbicacion();
					}
					// System.out.println(insertSql);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(insertSql);
					retorno = true;
					//Miramos si es un congelado para actualizar la tabla gp_congelado_ubicacion
					boolean flag = false;
					if (congelado.compareTo("C") == 0){
						flag = true;
						if (nuevaCantidad > 0)
							insertSql = "UPDATE gp_congelado_ubicacion gcu SET cantidad = " + nuevaCantidad +
								" WHERE gcu.orden = '" + orden + "' AND gcu.codigoEntrada = '" + u.getRegistro() + "' " +
										" AND gcu.idHueco=" + idHueco;
						else
							insertSql = "DELETE FROM gp_congelado_ubicacion " +
								" WHERE orden = '" + orden + "' AND codigoEntrada = '" + u.getRegistro() + "' " +
								" AND idHueco=" + idHueco;
					}else
						if (congelado.compareTo("F") == 0){
							flag = true;
							if (nuevaCantidad > 0)
								insertSql = "UPDATE gp_fumigado_ubicacion gcu SET cantidad = " + nuevaCantidad +
									" WHERE gcu.orden = '" + orden + "' AND gcu.codigoEntrada = '" + u.getRegistro() + "' " +
										" AND gcu.idHueco=" + idHueco;
							else
								insertSql = "DELETE FROM gp_fumigado_ubicacion " +
									" WHERE orden = '" + orden + "' AND codigoEntrada = '" + u.getRegistro() + "' " +
										" AND idHueco=" + idHueco;
						}
					if (flag){
						// System.out.println(insertSql);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						res = stmt.executeUpdate(insertSql);
					}
					//Miramos si hay que generar incidencia
					if (incidencia){
						Qry = "SELECT uh.descripcion FROM ubicacion u, ubicacion_hueco uh WHERE u.idUbicacion = " + u.getIdUbicacion() +
							" AND u.idHueco=uh.idHueco";
						// System.out.println(Qry);
						con = bddConexion();
						ps = con.prepareStatement(Qry);
						rs = ps.executeQuery();
						String ubicacion = "";
						while (rs.next()) {
							ubicacion = rs.getString("descripcion");
						}
						//Calculamos el valor de la incidencia
						double valor = 0;
						//Miramos si se trata de merma sobre un RE o sobre un lote ya envasado
						if (u.getRegistro().charAt(0) == 'E'){
							//Registro de entrada
							Qry = "SELECT re.costoUnitario " +
									" FROM registroentrada re " +
									" WHERE re.codigoEntrada = '" + u.getRegistro() + "'";
							// System.out.println(Qry);
							ps = con.prepareStatement(Qry);
							rs = ps.executeQuery();
							while (rs.next()) {
								valor = rs.getDouble("costoUnitario");
							}
						}else
							if (u.getRegistro().charAt(0) == '0'){
								//Lote envasado
								Qry = "SELECT p.precio " +
										" FROM producto p, gp_envasado gp " +
										" WHERE gp.lote = '" + u.getRegistro() + "' " +
												" AND gp.idProducto = p.idProducto";
								// System.out.println(Qry);
								ps = con.prepareStatement(Qry);
								rs = ps.executeQuery();
								while (rs.next()) {
									valor = rs.getDouble("precio");
								}
							}
						String insertString = "INSERT INTO incidenciaproducto(loteProducto, fecha, " +
							" descripcion, cantidad, idUbicacion, descripcionUbicacion, valor) " +
							" VALUES('" + u.getRegistro() + "',CURRENT_TIMESTAMP(),'" + u.getDescripcion() + "'," + u.getCantidad() +
							"," + u.getIdUbicacion() + ",'" + ubicacion + "','" + (valor * u.getCantidad()) + "')";
						res = 0;
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						res = stmt.executeUpdate(insertString);
						if (res == 1){
							// System.out.println("INCIDENCIA REGISTRADA");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					con.close();
				} catch (Exception e) { e.printStackTrace(); }
			}
		}else{//Si no es una incidencia, registramos en la base de datos pero no sacamos
			try {
				if (ubicaciones.size() > 0){
					con = bddConexion();
					Ubicacion u = ubicaciones.get(0);
					String Qry = "SELECT u.idHueco, congelado, orden " +
							" FROM ubicacion u WHERE u.idUbicacion = " + u.getIdUbicacion();
					// System.out.println(Qry);
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					String congelado = "";
					String orden = "";
					long idHueco = 0;
					while (rs.next()){
						idHueco = rs.getLong("idHueco");
						congelado = rs.getString("congelado");
						orden = rs.getString("orden");
					}
					int res = 0;
					//Si hay alguna linea con movido y cancelado igual a 'N', la cancelamos
					String insertString = "UPDATE ubicacion_tmp SET cancelado='S' WHERE movido='N' AND cancelado='N'";
					// System.out.println(insertString);
					Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(insertString);
					if (res == 1){
						// System.out.println("SACAR DE UBICACION REGISTRADO EN TABLA TEMPORAL");
						retorno = true;
					}
					//Los lotes nos llegan de la forma 02177001 (VA 10/11)
					//Tenemos que quedarnos solo con la primera parte del lote: 02177001
					String frag[] = u.getRegistro().split(" ");
					String registro = frag[0];
					insertString = "INSERT INTO ubicacion_tmp(idHueco, registro, cantidad,"
							+ " idPalet, orden, congelado, movido, cancelado, responsable) "
						+ " VALUES('" + idHueco + "', '" + registro + "'," + u.getCantidad() + ",'" 
						+ u.getIdPalet() + "','" + orden + "','" + congelado + "','N','N','" + u.getUsuarioResponsable() + "')";
					// System.out.println(insertString);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(insertString);
					if (res == 1){
						// System.out.println("SACAR DE UBICACION REGISTRADO EN TABLA TEMPORAL");
						retorno = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					con.close();
				} catch (Exception e) { e.printStackTrace(); }
			}
		}
		return retorno;
	}
	
	/**
	 * Saca de su ubicacion a cada una de las ubicaciones pasadas por parámetros
	 * @author andres (28/11/11)
	 * @since 1.6
	 */
	private boolean registrarSacarUbicaciones(Vector<Ubicacion> ubicaciones){
		boolean retorno = false;
		// System.out.println("DAO sacarUbicaciones");
		try {
			Statement stmt;
			con = bddConexion();
			for (int i = 0; i < ubicaciones.size(); i++){
				Ubicacion u = ubicaciones.get(i);
				String Qry =
					" SELECT u.idHueco,cantidad, congelado, orden " +
					" FROM ubicacion u " +
					" WHERE u.idUbicacion = " + u.getIdUbicacion() +
						" AND u.habilitado = 'S'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				double cantidad = 0;
				long idHueco = 0;
				while (rs.next()){
					cantidad = rs.getDouble("cantidad");
					idHueco = rs.getLong("idHueco");
				}
				double cantidadSacar = u.getCantidad();
				double nuevaCantidad = cantidad - cantidadSacar;
				String insertSql = "";
				if (nuevaCantidad > 0){//UPDATE
					insertSql = "UPDATE ubicacion SET cantidad = " + nuevaCantidad +
							" WHERE ubicacion.idUbicacion = " + u.getIdUbicacion();
				}else{//DELETE
					insertSql = "DELETE FROM ubicacion WHERE idUbicacion = " +
							u.getIdUbicacion();
				}
				// System.out.println(insertSql);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertSql);
				//Registramos el movimiento
				insertSql =
					" INSERT INTO ubicacion_movimiento (idHueco, registro, cantidad, responsable, fecha, sacarMeter) " +
						" VALUES(" + idHueco + ", '" + u.getRegistro() +
						"', " + u.getCantidad() + ", '" + u.getUsuarioResponsable() + "', CURRENT_TIMESTAMP(), 'S'); ";
				// System.out.println(insertSql);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertSql);
				retorno = true;
				//Actualizamos la tabla ubicacion_tmp
				String updateSql = "UPDATE ubicacion_tmp SET movido='S' "
					+ " WHERE registro = '" + u.getRegistro() + "' " +
						"AND cantidad = '" + u.getCantidad() + "' AND idPalet = " + u.getIdPalet();
				// System.out.println(updateSql);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(updateSql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return retorno;
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
	 * A partir de un registro de entrada, devuelve una lista con las ubicaciones donde se puede encontrar el registro
	 * @author andres (14/06/2011)
	 * @since 1.3
	 */
	//@Override
	public ArrayList<Ubicacion> getUbicacionesEntrada(String entrada, boolean ean13) throws Exception{
		ArrayList<Ubicacion> almacenados = new ArrayList<Ubicacion>();
		try {
			con = bddConexion();
			String Qry = "";
			if (ean13){
				Qry =
					" SELECT * " +
					" FROM ubicacion " +
					" WHERE registro = '" + entrada + "' " +
						" AND cantidad > 0 " +
						" AND habilitado = 'S'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					Ubicacion aux = new Ubicacion();
					aux.setIdHueco(result.getLong("idHueco"));
					Ubicacion u = this.getUbicacion(aux);
					u.setIdUbicacion(result.getLong("idUbicacion"));
					u.setIdHueco(result.getLong("idHueco"));
					u.setRegistro(result.getString("registro"));
					u.setNumeroBultos(result.getLong("numeroBultos"));
					u.setCantidad(result.getDouble("cantidad"));
					u.setIdPalet(result.getLong("idPalet"));
					u.setOrden(result.getString("orden"));
					u.setCongelado(result.getString("congelado"));
					almacenados.add(u);	
				}
			}else{
				Qry =
					" SELECT * " +
					" FROM ubicacion " +
					" WHERE registro = '" + entrada + "' " +
						" AND cantidad > 0 " +
						" AND habilitado = 'S'";
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					//con = bddConexion();
					Ubicacion u = new Ubicacion();
					u.setIdHueco(result.getLong("idHueco"));
					u = getUbicacion(u);
					u.setIdUbicacion(result.getLong("idUbicacion"));
					u.setRegistro(result.getString("registro"));
					u.setNumeroBultos(result.getLong("numeroBultos"));
					u.setCantidad(result.getDouble("cantidad"));
					u.setIdPalet(result.getLong("idPalet"));
					u.setOrden(result.getString("orden"));
					u.setCongelado(result.getString("congelado"));
					almacenados.add(u);
				}
			}
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
		return almacenados;
	}

	//@Override
	public long ubicarSeleccion(String registro, String idHueco, long idPalet, double cantidad) throws Exception {
		// System.out.println("DAO guardarRegistro");
		long idUbicacionMax = 0 ;
		try {
			Statement stmt;
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idUbicacion) AS idMaxUbicacion FROM ubicacion");
			rs = ps.executeQuery();
			if(rs.next()){
				idUbicacionMax = rs.getLong("idMaxUbicacion") + 1;
			}else
				idUbicacionMax = 1;
			String Qry =
				" SELECT idUbicacion,registro,idPalet,cantidad " +
				" FROM ubicacion u " +
				" WHERE u.idHueco = " + idHueco +
					" AND u.idPalet=" + 0 + " " +
					" AND u.registro='" + registro + "'" +
					" AND u.habilitado = 'S'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			boolean existe = false;
			double cuanto = 0;
			long idUbicacion = 0;
			while (rs.next()) {
				idUbicacion = rs.getLong("idUbicacion");
				cuanto = rs.getDouble("cantidad");
				existe = true;
			}
			String insertSql = "";
			if (existe){
				insertSql = "UPDATE ubicacion SET cantidad = '" + (cuanto + cantidad) + 
					"' WHERE idUbicacion = " + idUbicacion;
			}else{
				insertSql = "INSERT INTO ubicacion (idHueco, registro, cantidad, idPalet) VALUES (" +
				idHueco + ",'" + registro + "','" + cantidad + "','" + 0 + "')";
			}
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertSql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return idUbicacionMax;
	}
	
	//@Override
	public void reubicar(String orden, String entrada, String idHueco,
			String idHuecoNuevo, long idPalet, double cantidad) throws Exception {
		// System.out.println("--> reubicar DAO");
		if (idHuecoNuevo.compareTo("") != 0){
			this.actualizacionGenerica("gp_envasado_ubicacion geu", "geu.idHuecoNuevo",
				idHuecoNuevo, "geu.ordenEnvasado='" + orden + "' AND geu.codigoEntrada='" + entrada + "' " +
				" AND geu.idHueco=" + idHueco + " AND geu.idPalet = " + idPalet);
			this.actualizacionGenerica("ubicacion u", "u.idHueco",
					idHuecoNuevo, "u.registro='" + entrada + "' AND u.idPalet='" + idPalet + "' " +
					" AND u.idHueco=" + idHueco);
		}
		else{
			guardarRegistro(Long.parseLong(idHueco), entrada, idPalet);
			PreparedStatement ps = null;
			Statement stmt;
			con = bddConexion();
			boolean existe = false;
			double cantidadVieja = 0;
			//long ubicacion = 0;
			try {
				con = bddConexion();
				String Qry = "SELECT * " +
					" FROM gp_envasado_ubicacion geu WHERE geu.codigoEntrada = '" + entrada + "' AND " +
							" geu.ordenEnvasado = '" + orden + "' AND geu.idHueco = " + idHueco;
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				ResultSet result = ps.executeQuery();
				while (result.next()){
					existe = true;
					cantidadVieja = result.getDouble("cantidad");
					//ubicacion = result.getLong("idUbicacion");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw (e);
			}
			if (existe)
				this.actualizacionGenerica("gp_envasado_ubicacion geu", "geu.cantidad",
					"" + cantidad, "geu.ordenEnvasado='" + orden + "' AND geu.codigoEntrada='" + entrada + "' " +
					" AND geu.idHueco= " + idHueco);
			else{
				// SQL de insersion
				String insertString = "INSERT INTO gp_envasado_ubicacion(ordenEnvasado, codigoEntrada, " +
						" idHueco, cantidad) " +
						" VALUES('" + orden + "','" + entrada +  "','" + idHueco + "','" + 
						(cantidad + cantidadVieja) + "')";
				int res = 0;
				// System.out.println(insertString);
				con = bddConexion();
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1) {
					// System.out.println("REGISTRO INSERTADO");
				}
			}
		}
	}
	
	//@Override
	public Vector<Incidencia> getIncidencias() throws Exception{
		Vector<Incidencia> incidencias = new Vector<Incidencia>();
		try {
			con = bddConexion();
			String Qry = "SELECT * " +
				" FROM incidenciaproducto";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Incidencia i = new Incidencia();
				i.setCantidad(result.getDouble("cantidad"));
				i.setDescripcion(result.getString("descripcion"));
				i.setFecha(result.getDate("fecha"));
				i.setIdUbicacion(result.getLong("idUbicacion"));
				i.setIdIncidencia(result.getLong("idIncidencia"));
				i.setValor(result.getDouble("valor"));
				i.setLoteProducto(result.getString("loteProducto"));
				i.setDescripcionUbicacion(result.getString("descripcionUbicacion"));
				incidencias.add(i);
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
		return incidencias;
	}
	
	//@Override
	public void ubicarCongelado(String proceso, String tipoProceso, Ubicacion ubicacion) throws Exception{
		Statement stmt;
		con = bddConexion();
		boolean existe = false;
		double cantidadVieja = 0;
		String tabla = "";
		if (tipoProceso.equals("Congelado"))
			tabla = "gp_congelado_ubicacion";
		else
			if (tipoProceso.equals("Fumigado"))
				tabla = "gp_fumigado_ubicacion";
		//long ubicacion = 0;
		try {
			con = bddConexion();
			String Qry = "SELECT * " +
				" FROM " + tabla + " gcu WHERE gcu.codigoEntrada = '" + ubicacion.getRegistro() + "' AND " +
						" gcu.orden = '" + proceso + "' AND gcu.idHueco = " + ubicacion.getIdHueco();
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			ResultSet result = ps.executeQuery();
			while (result.next()){
				existe = true;
				cantidadVieja = result.getDouble("cantidad");
				//ubicacion = result.getLong("idHueco");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		if (existe)
			this.actualizacionGenerica(tabla + " gcu", "gcu.cantidad",
				"" + (ubicacion.getCantidad() + cantidadVieja), "gcu.orden='" + proceso + 
				"' AND gcu.codigoEntrada='" + ubicacion.getRegistro() + "' " +
				" AND gcu.idHueco= " + ubicacion.getIdHueco());
		else{
			// SQL de insersion
			String insertString = "INSERT INTO " + tabla + "(orden, codigoEntrada, " +
					" idHueco, cantidad) " +
					" VALUES('" + proceso + "','" + ubicacion.getRegistro() +  "','" + ubicacion.getIdHueco() + "','" + 
					(ubicacion.getCantidad() + cantidadVieja) + "')";
			int res = 0;
			// System.out.println(insertString);
			con = bddConexion();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (res == 1) {
				// System.out.println("REGISTRO INSERTADO");
			}
		}
	}
	
	//@Override
	public Vector<Ubicacion> getBigBags() throws Exception{
		Vector<Ubicacion> resultado = new Vector<Ubicacion>();
		try {
			con = bddConexion();
			String Qry = "SELECT idHueco, descripcion " +
					" FROM ubicacion_hueco uh WHERE uh.bigBag = 'S'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ubicacion u = new Ubicacion();
				u.setIdHueco(rs.getLong("idHueco"));
				u.setDescripcion(rs.getString("descripcion"));
				resultado.add(u);
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
		return resultado;
	}
	
	
	/**
	 * A partir del idAlmacen de un vehiculo/almacen, nos devuelve el idHueco que representa a dicho vehículo/almacén
	 * @author andres (04/08/2011)
	 * @since 1.4
	 */
	//@Override
	public long getIdHuecoVehiculo(long idAlmacen) throws Exception{
		Ubicacion ubica = new Ubicacion();
		long idHueco = 0;
		try {
			con = bddConexion();
			//Buscamos en ubicacion_zona
			String Qry = "SELECT idZona " +
					" FROM ubicacion_zona uz WHERE uz.idAlmacen = '" + idAlmacen + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				ubica.setIdZona(rs.getLong("idZona"));
			}
			//Buscamos en ubicacion_linea
			Qry = "SELECT idLinea " +
					" FROM ubicacion_linea ul WHERE ul.idZona = '" + ubica.getIdZona() + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				ubica.setIdLinea(rs.getLong("idLinea"));
			}
			//Buscamos en ubicacion_estanteria
			Qry = "SELECT idEstanteria " +
					" FROM ubicacion_estanteria ue WHERE ue.idLinea = '" + ubica.getIdLinea() + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				ubica.setIdEstanteria(rs.getLong("idEstanteria"));
			}
			//Buscamos en ubicacion_piso
			Qry = "SELECT idPiso " +
					" FROM ubicacion_piso up WHERE up.idEstanteria = '" + ubica.getIdEstanteria() + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				ubica.setIdPiso(rs.getLong("idPiso"));
			}
			//Buscamos en ubicacion_hueco
			Qry = "SELECT idHueco " +
					" FROM ubicacion_hueco uh WHERE uh.idPiso = '" + ubica.getIdPiso() + "'";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				ubica.setIdHueco(rs.getLong("idHueco"));
				idHueco = ubica.getIdHueco();
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
		return idHueco;
	}
	
	//@Override
	public Vector<Vehiculo> getVehiculos() throws Exception{
		Vector<Vehiculo> vehiculos = new Vector<Vehiculo>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT idAlmacen, descripcion " +
				" FROM ubicacion_almacen WHERE esVehiculo = 'S'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				Vehiculo v = new Vehiculo();
				v.setMatricula(rs.getString("descripcion"));
				v.setIdVehiculo(rs.getLong("idAlmacen"));
				vehiculos.add(v);
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
		return vehiculos;
	}
	
	/**
	 * @author andres (02/04/2013)
	 * @return Vehiculo, incluyendo su carga y su código QR
	 * @throws Exception
	 */
	//@Override
	public Vehiculo getVehiculoCompleto(long idVehiculo) throws Exception{
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setIdVehiculo(idVehiculo);
		PreparedStatement pst = null;
		try {
			con = bddConexion();
			String Qry =
				" SELECT v.descripcion, i.nombre AS nombreImpresora " +
				" FROM (ubicacion_hueco v " +
					" LEFT OUTER JOIN autoventa_vehiculo_impresora vi " +
					" ON v.idHueco = vi.idVehiculo) " +
					" LEFT OUTER JOIN autoventa_impresora i " +
					" ON i.idImpresora = vi.idImpresora " +
				" WHERE v.idHueco = ?; ";
			ps = con.prepareStatement(Qry);
			ps.setLong(1, idVehiculo);
			rs = ps.executeQuery();
			if (rs.next()){
				vehiculo.setMatricula(rs.getString("descripcion"));
				String nombreImpresora = rs.getString("nombreImpresora");
				if (nombreImpresora != null){
					vehiculo.setNombreImpresoraAsociada(nombreImpresora);
				}else{
					vehiculo.setNombreImpresoraAsociada("Ninguna");
				}
			}
			Vector<ProductoUbicado> carga = new Vector<ProductoUbicado>();
			Qry =
				" SELECT u.registro, u.cantidad " +
				" FROM ubicacion u " +
				" WHERE u.habilitado = 'S' " +
					" AND u.cantidad > 0 " +
					" AND u.idHueco = ?;";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			ps.setLong(1, idVehiculo);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductoUbicado productoUbicado = new ProductoUbicado();
				String registro = rs.getString("registro");
				double cantidad = rs.getDouble("cantidad");
				productoUbicado.setRegistro(registro);
				productoUbicado.setCantidad(cantidad);
				ResultSet rse = null;
				if (registro != null && !registro.equals("") && registro.charAt(0) == 'E'){ //Entrada
					//E12108-141
					Qry =
						" SELECT re.listoDistribuir, re.idProducto, re.idCategoria, re.fechaCaducidad " +
						" FROM registroentrada re " +
						" WHERE re.codigoEntrada = ?; ";
					pst = con.prepareStatement(Qry);
					pst.setString(1, registro);
					rse = pst.executeQuery();
					if (rse.next()) {
						String productoFinal = rse.getString("listoDistribuir");
						String fechaCaducidad = rse.getString("fechaCaducidad");//2014-04-01
						SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
						Date fecha = formatoDeFecha.parse(fechaCaducidad);
						formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
						fechaCaducidad = formatoDeFecha.format(fecha);
						productoUbicado.setFechaCaducidad(fechaCaducidad);
						long idProducto = rse.getLong("idProducto");
						productoUbicado.setIdProducto(idProducto);
						if (productoFinal.equals("S")){
							//Es un registro de un producto final
							Qry =
								" SELECT p.nombre " +
								" FROM producto p " +
								" WHERE p.idProducto = ?; ";
							// System.out.println(Qry);
							pst = con.prepareStatement(Qry);
							pst.setLong(1, idProducto);
							rse = pst.executeQuery();
							if (rse.next()) {
								productoUbicado.setNombreProducto(rse.getString("nombre"));
							}
						}else{
							//Es un registro de una materia prima (idProducto y categoria). materiaprima_categoria
							long idCategoria = rse.getLong("idCategoria");
							productoUbicado.setIdCategoria(idCategoria);
							String nombreMateriaPrima = "", nombreCategoria = "";
							Qry =
								" SELECT m.nombre " +
								" FROM materiaprima m " +
								" WHERE m.idProducto = ?; ";
							// System.out.println(Qry);
							pst = con.prepareStatement(Qry);
							pst.setLong(1, idProducto);
							rse = pst.executeQuery();
							if (rse.next()) {
								nombreMateriaPrima = rse.getString("nombre");
							}
							Qry =
								" SELECT c.nombre " +
								" FROM categoria c " +
								" WHERE c.idCategoria = ?; ";
							// System.out.println(Qry);
							pst = con.prepareStatement(Qry);
							pst.setLong(1, idCategoria);
							rse = pst.executeQuery();
							if (rse.next()) {
								nombreCategoria = rse.getString("nombre");
							}
							productoUbicado.setNombreProducto(nombreMateriaPrima + " - " + nombreCategoria);
						}
					}
				}else{
					if (registro != null && !registro.equals("") && registro.charAt(0) == '0'){ //Envasado
						String[] frag = registro.split(" ");
						if (frag.length > 1)
							registro = frag[0];
						Qry =
							" SELECT p.idProducto, p.nombre, ge.fechaCaducidad " +
							" FROM gp_envasado ge, producto p " +
							" WHERE ge.lote = '" + registro + "' " +
									" AND ge.idProducto = p.idProducto; ";
						// System.out.println(Qry);
						pst = con.prepareStatement(Qry);
						rse = pst.executeQuery();
						if (rse.next()) {
							productoUbicado.setIdProducto(rse.getLong("idProducto"));
							String fechaCaducidad = rse.getString("fechaCaducidad");
							SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
							Date fecha = formatoDeFecha.parse(fechaCaducidad);
							formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
							fechaCaducidad = formatoDeFecha.format(fecha);
							productoUbicado.setFechaCaducidad(fechaCaducidad);
							String qryAgrupacion =
								" SELECT en.orden, en.lote, en_ag.idAgrupacion, p.nombre " +
								" FROM (gp_envasado en " +
									" INNER JOIN gp_envasado_agrupacion en_ag " +
									" ON en.orden = en_ag.ordenEnvasado) " +
									" INNER JOIN producto p " +
									" ON p.idProducto = en_ag.idAgrupacion " +
								" WHERE en.lote = ?; ";
							PreparedStatement ps2 = con.prepareStatement(qryAgrupacion);
							ps2.setString(1, registro);
							ResultSet rs2 = ps2.executeQuery();
							if (rs2.next()) {
								productoUbicado.setNombreProducto(rs2.getString("nombre"));
							}else{
								productoUbicado.setNombreProducto(rse.getString("nombre"));
							}
						}
					}
				}
				carga.add(productoUbicado);
			}
			vehiculo.setCarga(carga);
			//Formamos la url del código QR del vehículo
			long idUltimaSincronizacion = 0;
			// Para el idVehiculo, mirar cuando fue la última sincronización
			String qry =
				" SELECT log.id, log.fecha " +
				" FROM log_sincronizacion log " +
				" WHERE log.idVehiculo = ? " +
				" ORDER BY fecha DESC " +
				" LIMIT 1 ";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(qry);
			ps.setLong(1, idVehiculo);
			rs = ps.executeQuery();
			if (rs.next()){
				idUltimaSincronizacion = rs.getLong("id");
			}
			//VIEJO. Un único código para todo el vehículo
			/*String urlCodigoQR = "RV=&idSincronizacion=" + idUltimaSincronizacion + "&idVehiculo=" + idVehiculo + "&";
			for (int i = 0; i < carga.size(); i++){
				
				urlCodigoQR += "PF_" + producto.getIdProducto() + "=" + producto.getRegistro() + "__" +
					producto.getCantidad() + "__" + producto.getFechaCaducidad();
				if (i < carga.size() - 1)
					urlCodigoQR += "&";
			}
			// System.out.println("urlCodigoQR: " + urlCodigoQR);
			vehiculo.setUrlCodigoQR(urlCodigoQR);*/
			final int PRODUCTOS_POR_HOJA = 10;
			//En cada hoja entran 10 productos
			int cuantasHojas = carga.size() / PRODUCTOS_POR_HOJA;
			int productosUltimaHoja = carga.size() % PRODUCTOS_POR_HOJA;
			if (productosUltimaHoja > 0)
				cuantasHojas++;
			int indiceProducto = 0;
			for (int i = 0; i < cuantasHojas; i++){
				String urlCodigoQRHoja = "RV=&idSincronizacion=" + idUltimaSincronizacion + "&idVehiculo=" + idVehiculo + "&idPagina=" + (i + 1) + "&";
				//Recorre 10 productos que irán en esta hoja
				for (int h = 0; h < PRODUCTOS_POR_HOJA && indiceProducto < carga.size(); indiceProducto++, h++){
					ProductoUbicado producto = carga.get(indiceProducto);
					urlCodigoQRHoja += "PF_" + producto.getIdProducto() + "=" + producto.getRegistro() + "__" +
						producto.getCantidad() + "__" + producto.getFechaCaducidad();
					if ((h < (PRODUCTOS_POR_HOJA - 1)) && (indiceProducto < (carga.size() - 1)))
						urlCodigoQRHoja += "&";
				}
				//Insertamos la url de la hoja
				vehiculo.getUrlCodigosQR().add(urlCodigoQRHoja);
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
		return vehiculo;
	}
	
	/**
	 * @author andres (02/04/2013)
	 * @return Vehiculo, incluyendo su carga y su código QR
	 * @throws Exception
	 */
	//@Override
	public Vehiculo getVehiculoLanzadera(long idVehiculo) throws Exception{
		Vehiculo vehiculo = new Vehiculo();
		//Marcamos el vehículo como lanzadera
		vehiculo.setIdVehiculo(idVehiculo);		
		PreparedStatement pst = null;
		try {
			con = bddConexion();
			//Obtenemos la informacion del vehiculo
			String qry =
				" SELECT v.descripcion, i.nombre AS nombreImpresora " +
				" FROM (ubicacion_hueco v " +
					" LEFT OUTER JOIN autoventa_vehiculo_impresora vi " +
					" ON v.idHueco = vi.idVehiculo) " +
					" LEFT OUTER JOIN autoventa_impresora i " +
					" ON i.idImpresora = vi.idImpresora " +
				" WHERE v.idHueco = ?; ";
			ps = con.prepareStatement(qry);
			ps.setLong(1, idVehiculo);
			rs = ps.executeQuery();
			if (rs.next()){
				vehiculo.setMatricula(rs.getString("descripcion"));
				String nombreImpresora = rs.getString("nombreImpresora");
				if (nombreImpresora != null){
					vehiculo.setNombreImpresoraAsociada(nombreImpresora);
				}else{
					vehiculo.setNombreImpresoraAsociada("Ninguna");
				}
			}
			// Para el idVehiculo, mirar cuando fue la última sincronización
			long idUltimaSincronizacion = 0, idUltimaLanzadera = 0;
			String fechaComprobar = "";
			qry =
				" SELECT log.fecha, log.id " +
				" FROM log_sincronizacion log " +
				" WHERE log.idVehiculo = ? " +
				" ORDER BY fecha DESC " +
				" LIMIT 1 ";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(qry);
			ps.setLong(1, idVehiculo);
			rs = ps.executeQuery();
			if (rs.next()){
				fechaComprobar = rs.getString("fecha");
				idUltimaSincronizacion = rs.getLong("id");
			}
			// Mirar si despues de la última sincronización hubo alguna lanzadera
			qry =
				" SELECT log.fecha " +
				" FROM log_lanzadera_productos_vehiculo log " +
				" WHERE log.idVehiculo = ? " +
					" AND log.fecha > ? " +
				" ORDER BY fecha DESC " +
				" LIMIT 1 ";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(qry);
			ps.setLong(1, idVehiculo);
			ps.setString(2, fechaComprobar);
			rs = ps.executeQuery();
			if (rs.next()){
				fechaComprobar = rs.getString("fecha");
			}
			//Miramos que id tendrá la lanzadera que vamos a generar. El log lanzadera lo insertamos más adelante (insertaloglanzadera)
			qry =
				" SELECT MAX(id) as idLanzadera " +
				" FROM log_lanzadera_productos_vehiculo; ";
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				idUltimaLanzadera = rs.getLong("idLanzadera") + 1;
			}
			//Buscamos los movimientos que hubo despues de la fechaComprobar
			Vector<ProductoUbicado> carga = new Vector<ProductoUbicado>();
			qry =
				" SELECT * " +
				" FROM ubicacion_movimiento " +
				" WHERE idHueco = ? " +
				" AND fecha > ? " +
				" AND sacarMeter = 'M'; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, idVehiculo);
			ps.setString(2, fechaComprobar);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductoUbicado productoUbicado = new ProductoUbicado();
				String registro = rs.getString("registro");
				double cantidad = rs.getDouble("cantidad");
				productoUbicado.setRegistro(registro);
				productoUbicado.setCantidad(cantidad);
				ResultSet rse = null;
				if (registro != null && !registro.equals("") && registro.charAt(0) == 'E'){
					//E12108-141
					String Qry =
						" SELECT re.listoDistribuir, re.idProducto, re.idCategoria, re.fechaCaducidad " +
						" FROM registroentrada re " +
						" WHERE re.codigoEntrada = ?; ";
					pst = con.prepareStatement(Qry);
					pst.setString(1, registro);
					rse = pst.executeQuery();
					if (rse.next()) {
						String productoFinal = rse.getString("listoDistribuir");
						String fechaCaducidad = rse.getString("fechaCaducidad");//2014-04-01
						SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
						Date fecha = formatoDeFecha.parse(fechaCaducidad);
						formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
						fechaCaducidad = formatoDeFecha.format(fecha);
						productoUbicado.setFechaCaducidad(fechaCaducidad);
						long idProducto = rse.getLong("idProducto");
						productoUbicado.setIdProducto(idProducto);
						if (productoFinal.equals("S")){
							//Es un registro de un producto final
							Qry =
								" SELECT p.nombre " +
								" FROM producto p " +
								" WHERE p.idProducto = ?; ";
							// System.out.println(Qry);
							pst = con.prepareStatement(Qry);
							pst.setLong(1, idProducto);
							rse = pst.executeQuery();
							if (rse.next()) {
								productoUbicado.setNombreProducto(rse.getString("nombre"));
							}
						}else{
							//Es un registro de una materia prima (idProducto y categoria). materiaprima_categoria
							long idCategoria = rse.getLong("idCategoria");
							productoUbicado.setIdCategoria(idCategoria);
							String nombreMateriaPrima = "", nombreCategoria = "";
							Qry =
								" SELECT m.nombre " +
								" FROM materiaprima m " +
								" WHERE m.idProducto = ?; ";
							// System.out.println(Qry);
							pst = con.prepareStatement(Qry);
							pst.setLong(1, idProducto);
							rse = pst.executeQuery();
							if (rse.next()) {
								nombreMateriaPrima = rse.getString("nombre");
							}
							Qry =
								" SELECT c.nombre " +
								" FROM categoria c " +
								" WHERE c.idCategoria = ?; ";
							// System.out.println(Qry);
							pst = con.prepareStatement(Qry);
							pst.setLong(1, idCategoria);
							rse = pst.executeQuery();
							if (rse.next()) {
								nombreCategoria = rse.getString("nombre");
							}
							productoUbicado.setNombreProducto(nombreMateriaPrima + " - " + nombreCategoria);
						}
					}
				}else{
					if (registro != null && !registro.equals("") && registro.charAt(0) == '0'){ //Envasado
						String[] frag = registro.split(" ");
						if (frag.length > 1)
							registro = frag[0];
						qry =
							" SELECT p.idProducto, p.nombre, ge.fechaCaducidad " +
							" FROM gp_envasado ge, producto p " +
							" WHERE ge.lote = '" + registro + "' " +
									" AND ge.idProducto = p.idProducto; ";
						// System.out.println(Qry);
						pst = con.prepareStatement(qry);
						rse = pst.executeQuery();
						if (rse.next()) {
							productoUbicado.setIdProducto(rse.getLong("idProducto"));
							String fechaCaducidad = rse.getString("fechaCaducidad");
							SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
							Date fecha = formatoDeFecha.parse(fechaCaducidad);
							formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
							fechaCaducidad = formatoDeFecha.format(fecha);
							productoUbicado.setFechaCaducidad(fechaCaducidad);
							productoUbicado.setNombreProducto(rse.getString("nombre"));
						}
					}
				}
				carga.add(productoUbicado);
			}
			vehiculo.setCarga(carga);
			//VIEJO. Un único código para todo el vehículo
			//Formamos la url del código QR del vehículo
			/*String urlCodigoQR = "LV=&idSincronizacion=" + idUltimaSincronizacion +
				"&idLanzadera=" + idUltimaLanzadera + "&idVehiculo=" + idVehiculo + "&";
			for (int i = 0; i < carga.size(); i++){
				ProductoUbicado producto = carga.get(i);
				urlCodigoQR += "PF_" + producto.getIdProducto() + "=" + producto.getRegistro() + "__" +
					producto.getCantidad() + "__" + producto.getFechaCaducidad();
				if (i < carga.size() - 1)
					urlCodigoQR += "&";
			}
			// System.out.println("urlCodigoQR: " + urlCodigoQR);
			vehiculo.setUrlCodigoQR(urlCodigoQR);*/
			final int PRODUCTOS_POR_HOJA = 10;
			//En cada hoja entran 10 productos
			int cuantasHojas = carga.size() / PRODUCTOS_POR_HOJA;
			int productosUltimaHoja = carga.size() % PRODUCTOS_POR_HOJA;
			if (productosUltimaHoja > 0)
				cuantasHojas++;
			int indiceProducto = 0;
			for (int i = 0; i < cuantasHojas; i++){
				String urlCodigoQRHoja = "LV=&idSincronizacion=" + idUltimaSincronizacion +
				"&idLanzadera=" + idUltimaLanzadera + "&idVehiculo=" + idVehiculo + "&idPagina=" + (i + 1) + "&";
				//Recorre 10 productos que irán en esta hoja
				for (int h = 0; h < PRODUCTOS_POR_HOJA && indiceProducto < carga.size(); indiceProducto++, h++){
					ProductoUbicado producto = carga.get(indiceProducto);
					urlCodigoQRHoja += "PF_" + producto.getIdProducto() + "=" + producto.getRegistro() + "__" +
						producto.getCantidad() + "__" + producto.getFechaCaducidad();
					if ((h < (PRODUCTOS_POR_HOJA - 1)) && (indiceProducto < (carga.size() - 1)))
						urlCodigoQRHoja += "&";
				}
				//Insertamos la url de la hoja
				vehiculo.getUrlCodigosQR().add(urlCodigoQRHoja);
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
		return vehiculo;
	}
	
	/**
	 * @author andres (05/04/2013)
	 * @since 1.6
	 * Devuelve el número de productos que irán en la nueva lanzadera para el vehículo
	 * @param idVehiculo
	 * @return Número de productos
	 * @throws Exception
	 */
	//@Override
	public int getNumeroProductosNuevaLanzadera(long idVehiculo) throws Exception{
		int numeroProductos = 0;
		try {
			con = bddConexion();
			// Para el idVehiculo, mirar cuando fue la última sincronización
			String fechaComprobar = "";
			String qry =
				" SELECT log.fecha " +
				" FROM log_sincronizacion log " +
				" WHERE log.idVehiculo = ? " +
				" ORDER BY fecha DESC " +
				" LIMIT 1 ";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(qry);
			ps.setLong(1, idVehiculo);
			rs = ps.executeQuery();
			if (rs.next()){
				fechaComprobar = rs.getString("fecha");
			}
			// Mirar si despues de la última sincronización hubo alguna lanzadera
			qry =
				" SELECT log.fecha " +
				" FROM log_lanzadera_productos_vehiculo log " +
				" WHERE log.idVehiculo = ? " +
					" AND log.fecha > ? " +
				" ORDER BY fecha DESC " +
				" LIMIT 1 ";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(qry);
			ps.setLong(1, idVehiculo);
			ps.setString(2, fechaComprobar);
			rs = ps.executeQuery();
			if (rs.next()){
				fechaComprobar = rs.getString("fecha");
			}
			//Buscamos los movimientos que hubo despues de la fechaComprobar
			qry =
				" SELECT * " +
				" FROM ubicacion_movimiento " +
				" WHERE idHueco = ? " +
				" AND fecha > ? " +
				" AND sacarMeter = 'M'; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, idVehiculo);
			ps.setString(2, fechaComprobar);
			rs = ps.executeQuery();
			while (rs.next()) {
				numeroProductos++;
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
		return numeroProductos;
	}
	
	//@Override
	/**
	 * @author andres (04/04/2013)
	 * @since 1.6
	 */
	public Vector<Lanzadera> getLanzaderas(Lanzadera lanzadera) throws Exception{
		Vector<Lanzadera> lanzaderas = new Vector<Lanzadera>();
		try {
			con = bddConexion();
			//Buscamos el totalLanzaderas
			long totalLanzaderas = 0;
			String qry =
				" SELECT COUNT(id) as totalLanzaderas " +
				" FROM log_lanzadera_productos_vehiculo log " +
				" WHERE log.idVehiculo = ?; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, lanzadera.getIdVehiculo());
			rs = ps.executeQuery();
			if (rs.next()) {
				totalLanzaderas = rs.getLong("totalLanzaderas");
			}
			//Buscamos lanzaderas
			qry =
				" SELECT * " +
				" FROM log_lanzadera_productos_vehiculo log " +
				" WHERE log.idVehiculo = ?; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, lanzadera.getIdVehiculo());
			rs = ps.executeQuery();
			while (rs.next()) {
				Lanzadera l = new Lanzadera();
				l.setTotalLanzaderas(totalLanzaderas);
				l.setIdLanzadera(rs.getLong("id"));
				l.setIdVehiculo(lanzadera.getIdVehiculo());
				l.setUsuarioResponsable(rs.getString("usuarioResponsable"));
				l.setFecha(rs.getString("fecha"));//2013-04-04 17:31:28
				//Almacenamos tambien la fecha formateada
				String fechaStr = l.getFecha();//2013-04-04 17:31:28
				SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date fecha = formatoDeFecha.parse(fechaStr);
				formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//04/04/2013 17:31:28
				fechaStr = formatoDeFecha.format(fecha);
				l.setFechaFormateada(fechaStr);
				//Almacenamos el nombre del reporte de la lanzadera, que coincide con su timestamp
				formatoDeFecha = new SimpleDateFormat("yyyyMMdd_HHmmss");//20130404_132043
				fechaStr = formatoDeFecha.format(fecha);
				l.setNombreReporte(fechaStr);
				//Buscamos lanzaderaNumero (Cuantas lanzaderas hay antes que la actual)
				qry =
					" SELECT COUNT(id) AS cuantas " +
					" FROM log_lanzadera_productos_vehiculo log " +
					" WHERE log.fecha < ?" +
						" AND log.idVehiculo = ?; ";
				// System.out.println(Qry);
				PreparedStatement ps2 = con.prepareStatement(qry);
				ps2.setString(1, l.getFecha());
				ps2.setLong(2, lanzadera.getIdVehiculo());
				ResultSet rs2 = ps2.executeQuery();
				if (rs2.next()) {
					l.setLanzaderaNumero(rs2.getLong("cuantas") + 1);
				}
				lanzaderas.add(l);
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
		return lanzaderas;
	}
	
	/**
	 * @author andres (04/04/2013)
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
				" FROM log_lanzadera_productos_vehiculo; ";
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
				" INSERT INTO log_lanzadera_productos_vehiculo " +
					"(idVehiculo, usuarioResponsable, fecha) " +
				" VALUES(?, ?, ?); ";
			PreparedStatement stmt = con.prepareStatement(insertString);
			stmt.setLong(1, l.getIdVehiculo());
			stmt.setString(2, l.getUsuarioResponsable());
			stmt.setString(3, fechaStr);
			stmt.executeUpdate();
			//Buscamos lanzaderaNumero
			qry =
				" SELECT COUNT(id) as lanzaderaNumero " +
				" FROM log_lanzadera_productos_vehiculo log " +
				" WHERE log.idVehiculo = ?; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, l.getIdVehiculo());
			rs = ps.executeQuery();
			if (rs.next()) {
				lanzadera.setLanzaderaNumero(rs.getLong("lanzaderaNumero"));
			}
			//Buscamos totalLanzaderas (En este caso tendrá el mismo valor que lanzaderaNumero)
			qry =
				" SELECT COUNT(id) as totalLanzaderas " +
				" FROM log_lanzadera_productos_vehiculo log " +
				" WHERE log.idVehiculo = ?; ";
			// System.out.println(Qry);
			ps = con.prepareStatement(qry);
			ps.setLong(1, l.getIdVehiculo());
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
	
	//@Override
	/**
	 * @author andres (07.09.2011)
	 * @since 1.5
	 * 0://No existe el producto en ese hueco; 1://OK; 2://No existe el origen; 3://No hay cantidad suficiente del producto en el hueco;
	 */
	public int merma(String origen, String producto, double cantidad) throws Exception{
		int resultado = 0;
		long huecoOrigen = 0, idUbicacion = 0, idPalet = 0;
		String congelado = "", orden = "";
		try {
			con = bddConexion();
			//1. Buscar en la base de datos el id para el hueco de origen
			String Qry = "SELECT idHueco FROM ubicacion_hueco u WHERE u.descripcion = '" + origen + "'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			boolean huecoEncontrado = false;
			while (rs.next()){
				huecoOrigen = rs.getLong("idHueco");
				huecoEncontrado = true;
			}
			if (!huecoEncontrado)
				return 2;
			//Tenemos el id del hueco de origen
			//2. Mirar si el producto existe en el hueco de origen
			Qry = 
				" SELECT cantidad, idUbicacion, orden, idPalet, congelado " +
				" FROM ubicacion u" +
				" WHERE u.habilitado = 'S' " +
					" AND u.idHueco = '" + huecoOrigen + "' " +
					" AND u.registro = '" + producto + "'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			boolean existeProducto = false;
			double cuantoHay = 0;
			while (rs.next()){
				congelado = rs.getString("congelado");
				orden = rs.getString("orden");
				idPalet = rs.getLong("idPalet");
				cuantoHay = rs.getDouble("cantidad");
				idUbicacion = rs.getLong("idUbicacion");
				existeProducto = true;
			}
			if (!existeProducto)
				return 0;
			//3. Mirar si hay suficiente cantidad del producto en el hueco de origen
			if (cuantoHay < cantidad){
				return 3;
			}
			//5. MOVER
			//Sacar de ubicacion
			Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.setRegistro(producto);
			ubicacion.setCantidad(cantidad);
			ubicacion.setIdUbicacion(idUbicacion);
			ubicacion.setOrden(orden);
			ubicacion.setIdPalet(idPalet);
			ubicacion.setCongelado(congelado);
			ubicaciones.add(ubicacion);
			if (!sacarUbicaciones(ubicaciones, true, false, false))
				return -2;
			resultado = 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return resultado;
	}
	
	//@Override
	/**
	 * @author andres (07.09.2011)
	 * @since 1.5
	 * 0://No existe el producto en ese hueco; 1://OK; 2://No existe el origen; 3://No hay cantidad suficiente del producto en el hueco;
		4://No existe el destino
	 */
	public int mover(String origen, String producto, double cantidad, String destino) throws Exception{
		int resultado = 0;
		long huecoOrigen = 0, huecoDestino = 0,
			idUbicacion = 0, idPalet = 0;
		String congelado = "", orden = "";
		try {
			con = bddConexion();
			//1. Buscar en la base de datos el id para el hueco de origen
			String Qry = "SELECT idHueco FROM ubicacion_hueco u WHERE u.descripcion = '" + origen + "'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			boolean huecoEncontrado = false;
			while (rs.next()){
				huecoOrigen = rs.getLong("idHueco");
				huecoEncontrado = true;
			}
			if (!huecoEncontrado)
				return 2;
			//Tenemos el id del hueco de origen
			//2. Mirar si el producto existe en el hueco de origen
			Qry =
				" SELECT cantidad, idUbicacion, orden, idPalet, congelado " +
				" FROM ubicacion u " +
				" WHERE u.habilitado = 'S' " +
					" AND u.idHueco = '" + huecoOrigen + "' " +
					" AND u.registro = '" + producto + "'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			boolean existeProducto = false;
			double cuantoHay = 0;
			while (rs.next()){
				congelado = rs.getString("congelado");
				orden = rs.getString("orden");
				idPalet = rs.getLong("idPalet");
				cuantoHay = rs.getDouble("cantidad");
				idUbicacion = rs.getLong("idUbicacion");
				existeProducto = true;
			}
			if (!existeProducto)
				return 0;
			//3. Mirar si hay suficiente cantidad del producto en el hueco de origen
			if (cuantoHay < cantidad){
				return 3;
			}
			//4. Buscar en la base de datos el id para el hueco de destino
			Qry = "SELECT idHueco FROM ubicacion_hueco u WHERE u.descripcion = '" + destino + "'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			huecoEncontrado = false;
			while (rs.next()){
				huecoDestino = rs.getLong("idHueco");
				huecoEncontrado = true;
			}
			if (!huecoEncontrado)
				return 4;
			//Tenemos el id del hueco de destino
			//5. MOVER
			//Sacar de ubicacion
			Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.setRegistro(producto);
			ubicacion.setCantidad(cantidad);
			ubicacion.setIdUbicacion(idUbicacion);
			ubicacion.setOrden(orden);
			ubicacion.setIdPalet(idPalet);
			ubicacion.setCongelado(congelado);
			//ubicacion.setEstaUbicado(true);
			ubicacion.setIdHueco(huecoDestino);
			ubicaciones.add(ubicacion);
			if (!sacarUbicaciones(ubicaciones, false, false, false))
				return -2;
			//Meter en ubicacion
			salvarUbicaciones(ubicaciones, false);
			resultado = 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return resultado;
	}
	
	//@Override
	/**
	 * Inserta un nuevo almacén introducido por un usuario
	 * @author andres (04/08/2011)
	 * @since 1.4
	 */
	public void registrarAlmacen(Ubicacion almacen) throws Exception{
		Statement stmt;
		boolean vehiculo =  almacen.isEsVehiculo();
		char esVehiculo = 'N';
		if (vehiculo)
			esVehiculo = 'S';
		// SQL de insersion
		String insertString =
			" INSERT INTO ubicacion_almacen " +
				"(descripcion, esVehiculo, urlPlano) " +
			" VALUES('" + almacen.getNombre() + "','" + 
				esVehiculo + "','" + almacen.getUrlPlanoAlmacen() + "')";
		int res = 0;
		// System.out.println(insertString);
		con = bddConexion();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		res = stmt.executeUpdate(insertString);
		if (res == 1) {
			// System.out.println("ALMACEN INSERTADO (ubicacion_almacen)... I");
			String Qry = "SELECT max(idAlmacen) as max FROM ubicacion_almacen";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				almacen.setIdAlmacen(rs.getLong("max"));
			}
			//INSERTAR EN ubicacion_zona
			insertString = "INSERT INTO ubicacion_zona (idAlmacen, idZonaAlmacen, descripcion) " +
					" VALUES('" + almacen.getIdAlmacen() + "',1," +
							"'" + almacen.getNombre() + "')";
			res = 0;
			// System.out.println(insertString);
			con = bddConexion();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertString);
			if (res == 1) {
				// System.out.println("ALMACEN INSERTADO (ubicacion_zona)... II");
				Qry = "SELECT max(idZona) as max FROM ubicacion_zona";
				// System.out.println(Qry);
				//obtener el nuevo Id
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					almacen.setIdZona(rs.getLong("max"));
				}
				//INSERTAR EN ubicacion_linea
				insertString = "INSERT INTO ubicacion_linea (idZona, idLineaZona, descripcion) " +
						" VALUES('" + almacen.getIdZona() + "',1," +
								"'" + almacen.getNombre() + "')";
				res = 0;
				// System.out.println(insertString);
				con = bddConexion();
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				res = stmt.executeUpdate(insertString);
				if (res == 1) {
					// System.out.println("ALMACEN INSERTADO (ubicacion_linea)... III");
					Qry = "SELECT max(idLinea) as max FROM ubicacion_linea";
					// System.out.println(Qry);
					//obtener el nuevo Id
					ps = con.prepareStatement(Qry);
					rs = ps.executeQuery();
					while (rs.next()) {
						almacen.setIdLinea(rs.getLong("max"));
					}
					//INSERTAR EN ubicacion_estanteria
					insertString = "INSERT INTO ubicacion_estanteria (idLinea, idEstanteriaLinea, descripcion) " +
							" VALUES('" + almacen.getIdLinea() + "',1," +
									"'" + almacen.getNombre() + "')";
					res = 0;
					// System.out.println(insertString);
					con = bddConexion();
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					res = stmt.executeUpdate(insertString);
					if (res == 1) {
						// System.out.println("ALMACEN INSERTADO (ubicacion_estanteria)... IV");
						Qry = "SELECT max(idEstanteria) as max FROM ubicacion_estanteria";
						// System.out.println(Qry);
						//obtener el nuevo Id
						ps = con.prepareStatement(Qry);
						rs = ps.executeQuery();
						while (rs.next()) {
							almacen.setIdEstanteria(rs.getLong("max"));
						}
						//INSERTAR EN ubicacion_piso
						insertString = "INSERT INTO ubicacion_piso (idEstanteria, idPisoEstanteria, descripcion) " +
								" VALUES('" + almacen.getIdEstanteria() + "',1," +
										"'" + almacen.getNombre() + "')";
						res = 0;
						// System.out.println(insertString);
						con = bddConexion();
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						res = stmt.executeUpdate(insertString);
						if (res == 1) {
							// System.out.println("ALMACEN INSERTADO (ubicacion_piso)... V");
							Qry = "SELECT max(idPiso) as max FROM ubicacion_piso";
							// System.out.println(Qry);
							//obtener el nuevo Id
							ps = con.prepareStatement(Qry);
							rs = ps.executeQuery();
							while (rs.next()) {
								almacen.setIdPiso(rs.getLong("max"));
							}
							//INSERTAR EN ubicacion_hueco
							insertString = "INSERT INTO ubicacion_hueco (idPiso, idHuecoPiso, descripcion) " +
								" VALUES('" + almacen.getIdPiso() + "',1," +
									"'" + almacen.getNombre() + "')";
							res = 0;
							// System.out.println(insertString);
							con = bddConexion();
							stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_UPDATABLE);
							res = stmt.executeUpdate(insertString);
							if (res == 1) {
								// System.out.println("ALMACEN INSERTADO (ubicacion_hueco)... VI");
							}
						}
					}
				}
			}
		}
	}
	
	//@Override
	public long getIdHueco(String descripcion) throws Exception{
		long idHueco = 0;
		try {
			con = bddConexion();
			String Qry = "SELECT idHueco " +
					" FROM ubicacion_hueco WHERE descripcion = '" + 
					descripcion + "'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				idHueco = rs.getLong("idHueco");
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
		return idHueco;
	}
	
	//@Override
	public int checkUbicacion(Lote l) throws Exception{
		int resultado = 0;

		long idHueco = getIdHueco(l.getUbicacion());
		String lote = l.getLote();
		double cantidad = l.getCantidad();;
		try {
			con = bddConexion();
			String Qry = "SELECT descripcion " +
					" FROM ubicacion_hueco WHERE idHueco = '" + idHueco + "'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (!rs.next())
				return 2;
			Qry = "SELECT * " +
					" FROM ubicacion_hueco, ubicacion u WHERE u.idHueco = '" + idHueco + "' AND " +
							" u.registro = '" + lote + "'";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (!rs.next())
				return 3;
			Qry =
				" SELECT * " +
				" FROM ubicacion_hueco, ubicacion u " +
				" WHERE u.idHueco = '" + idHueco + "' " +
						" AND u.registro = '" + lote + "' AND u.cantidad >= " + cantidad + ";";
			// System.out.println(Qry);
			//obtener el nuevo Id
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (!rs.next())
				return 4;
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
		return resultado;
	}
	
	//@Override
	public Vector<MovimientoAlmacen> getMovimientosLote(String lote) throws Exception{
		Vector<MovimientoAlmacen> movimientos = new Vector<MovimientoAlmacen>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT um.fecha, um.responsable, um.cantidad, " +
					" CASE um.sacarMeter " +
						" WHEN 'S' THEN 'Origen' " +
						" ELSE 'Destino' " +
						" END AS tipo, " +
					" uh.descripcion " +
				" FROM ubicacion_movimiento um " +
				" LEFT JOIN ubicacion_hueco uh ON um.idHueco = uh.idHueco " +
				" WHERE um.registro = '" + lote + "' ";
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			String tipo = "", descripcionHueco = "";
			MovimientoAlmacen movimiento = null;
			
			while (rs.next()){
				tipo = rs.getString("tipo");
				descripcionHueco = rs.getString("descripcion");
				if (tipo.compareToIgnoreCase("origen") == 0){
					movimiento = new MovimientoAlmacen();
					movimiento.setOrigen(descripcionHueco);
				}else{
					if (tipo.compareToIgnoreCase("destino") == 0){
						if (movimiento == null){
							movimiento = new MovimientoAlmacen();
							movimiento.setOrigen("");
						}
						movimiento.setDestino(descripcionHueco);
						movimiento.setTipo(tipo);
						movimiento.setCantidad(rs.getInt("cantidad"));
						String fecha = rs.getString("fecha");//2012-08-13 00:00:00
						String frag[] = fecha.split(" ");
						String frag1[] = frag[0].split("-");
						fecha = frag1[2] + "/" + frag1[1] + "/" + frag1[0];
						if (frag[1] != null && !frag[1].equals("00:00:00.0")){
							fecha += " " + frag[1];
						}
						movimiento.setFecha(fecha);
						movimiento.setDescripcionHueco(descripcionHueco);
						movimiento.setResponsable(rs.getString("responsable"));
						movimientos.add(movimiento);
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
		return movimientos;
	}
	
	public Vector<MovimientoAlmacen> getModificacionesStock(String lote) throws Exception{
		Vector<MovimientoAlmacen> modificaciones = new Vector<MovimientoAlmacen>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT log.fecha, log.cantidad, log.causa, log.usuario, uh.descripcion " +
				" FROM log_stock_modificado log " +
				" LEFT JOIN ubicacion_hueco uh ON uh.idHueco = log.idHueco " +
				" WHERE log.registro = '" + lote + "'; ";
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			String descripcionHueco = "";
			while (rs.next()){
				MovimientoAlmacen movimiento = new MovimientoAlmacen();
				descripcionHueco = rs.getString("descripcion");
				movimiento.setDescripcionHueco(descripcionHueco);
				movimiento.setCantidad(rs.getInt("cantidad"));
				String fecha = rs.getString("fecha");//2012-08-13 00:00:00
				String frag[] = fecha.split(" ");
				String frag1[] = frag[0].split("-");
				fecha = frag1[2] + "/" + frag1[1] + "/" + frag1[0];
				if (frag[1] != null && !frag[1].equals("00:00:00.0")){
					fecha += " " + frag[1];
				}
				movimiento.setFecha(fecha);
				movimiento.setResponsable(rs.getString("usuario"));
				movimiento.setCausa(rs.getString("causa"));
				modificaciones.add(movimiento);			
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
		return modificaciones;
	}

	public Vector<Ubicacion> getUbicacionesRegistro(String lote) throws Exception {
		Vector<Ubicacion> ubicaciones = new Vector<Ubicacion>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT u.idUbicacion, uh.descripcion, u.idHueco " +
				" FROM ubicacion u " +
				" LEFT JOIN ubicacion_hueco uh ON u.idHueco = uh.idHueco " +
				" WHERE registro = '" + lote + "' ";
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()){
				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setDescripcion(rs.getString("descripcion"));
				ubicacion.setIdUbicacion(rs.getLong("idUbicacion"));
				ubicacion.setIdHueco(rs.getLong("idHueco"));
				ubicaciones.add(ubicacion);
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
		return ubicaciones;
	}

	public Ubicacion getUbicacionRegistro(String lote, long idHueco) throws Exception {
		Ubicacion ubicacion = new Ubicacion();
		try {
			con = bddConexion();
			String Qry =
				" SELECT u.cantidad " +
				" FROM ubicacion u " +
				" WHERE registro = '" + lote + "' AND idHueco = " + idHueco;
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (rs.next()){
				ubicacion.setCantidad(rs.getDouble("cantidad"));
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
		return ubicacion;
	}
}