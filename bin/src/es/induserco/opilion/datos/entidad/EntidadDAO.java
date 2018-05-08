package es.induserco.opilion.datos.entidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.presentacion.EdifactParserHelper;
import es.induserco.opilion.data.comun.Empresa;
import es.induserco.opilion.data.comun.Provincia;
import es.induserco.opilion.data.comun.Rol;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.banca.Banco;
import es.induserco.opilion.data.comun.banca.DatoBancario;
import es.induserco.opilion.data.comun.banca.FormaPago;
import es.induserco.opilion.data.comun.banca.FormaPagoDesde;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Email;
import es.induserco.opilion.data.comun.contacto.Entrega;
import es.induserco.opilion.data.comun.contacto.Requisito;
import es.induserco.opilion.data.comun.contacto.Ruta;
import es.induserco.opilion.data.comun.contacto.Sector;
import es.induserco.opilion.data.comun.contacto.Telefono;
import es.induserco.opilion.data.comun.contacto.TipoProveedor;
import es.induserco.opilion.data.entidades.Accion;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.GestionRoles;
import es.induserco.opilion.infraestructura.DatabaseConfig;

/**
 * @author andres (20/05/2011 - 01/12/2011)
 * @version 1.6
 */
@SuppressWarnings("unchecked")
public class EntidadDAO extends DatabaseConfig implements IEntidadDataService {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;

	//@Override
	public Vector<Entidad> getEntidades(String filtroConsulta) throws Exception {
		Vector<Entidad> resultado = new Vector<Entidad>();
		String filtro = " WHERE er.idRol= " + filtroConsulta + " AND habilitado='S'";
		// System.out.println("DAO getEntidades");
		try {
			con = bddConexion();
			String Qry="SELECT * FROM entidad e, entidad_rol er " + filtro;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Entidad entidad = new Entidad();
				entidad.setIdUsuario(rs.getLong("idUsuario"));
				entidad.setNombre(rs.getString("nombre"));
				entidad.setWeb(rs.getString("web"));
				entidad.setNif(rs.getString("nif"));
				resultado.add(entidad);
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
	public Vector<Order> getPedidosCliente(long idCliente) throws Exception{
		Vector<Order> resultado = new Vector<Order>();
		// System.out.println("DAO getEntidades");
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM  fac_orders o WHERE o.bgmTipo = 220 AND o.nadBy = " + idCliente;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order pedido = new Order();
				pedido.setBgmNum(rs.getString("bgmNum"));
				Vector<Order> vector = new EdifactParserHelper().orderVisualizaService(pedido.getBgmNum(), true);
				if (vector != null && vector.size() > 0){
					pedido = (Order) vector.get(0);
					resultado.add(pedido);
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	//@Override
	public Boolean updateEntidad(Entidad entidadFind, Entidad entidadUpdate) throws Exception {
		// System.out.println("DAO updateEntidad");
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			res = updateEntidadBasica(entidadFind, entidadUpdate, con);
			entidadUpdate.setIdUsuario(entidadFind.getIdUsuario());
			updateEntidadRolTipoSector(entidadUpdate, con);
			updateDireccionesEntidad(entidadUpdate, con);
			updateDatosBancarios(entidadUpdate, con);
			updateEmailsEntidad(entidadUpdate, con);
			updateTelefonosEntidad(entidadUpdate, con);
			if(res == 1){
				// System.out.println("ENTIDAD ACTUALIZADA");
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

	//@Override
	public Vector<Requisito> getListaReqHomologa() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Requisito> resultado = new Vector<Requisito>();
		try {
			con = bddConexion();
			String Qry="SELECT idRequisito,nombre FROM requisito_homologacion order by 1";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Requisito requisitoHomologacion = new Requisito();
				requisitoHomologacion.setIdRequisito(rs.getLong("idRequisito"));
				requisitoHomologacion.setNombre(rs.getString("nombre"));
				//La a�adimos al Vector de resultado
				resultado.add(requisitoHomologacion);
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

	public Vector<Requisito> getListaReqHomologa(Entidad entidad) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Requisito> resultado = new Vector<Requisito>();
		try {
			con = bddConexion();
			String Qry="SELECT hp.idRequisito, rh.nombre " +
					" FROM homologacion_proveedor hp, requisito_homologacion rh " +
					" WHERE idUsuario="+entidad.getIdUsuario()+" AND rh.idRequisito=hp.idRequisito";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Requisito requisitoHomologacion = new Requisito();
				requisitoHomologacion.setIdRequisito(rs.getLong("idRequisito"));
				requisitoHomologacion.setNombre(rs.getString("nombre"));
				//La a�adimos al Vector de resultado
				resultado.add(requisitoHomologacion);
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
	 * Update lista req homologa.
	 *
	 * @param entidad the entidad
	 * @param listaCriterios the lista criterios
	 * @param con the con
	 * @return the int
	 * @throws SQLException the sQL exception
	 */
	private int updateListaReqHomologa(Entidad entidad, List listaCriterios, Connection con) throws SQLException {
		Statement stmt;
		int res;
		//borramos los que ya habia
		String query ="DELETE FROM homologacion_proveedor WHERE idUsuario="+entidad.getIdUsuario();
		// System.out.println(query);
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		res = stmt.executeUpdate(query);
		for(int i=0;i<listaCriterios.size();i++) {
			//SQL de insercion
			query="INSERT INTO homologacion_proveedor (idUsuario,idRequisito) VALUES(" +
				entidad.getIdUsuario() + "," + listaCriterios.get(i) + ")";
			// System.out.println(query);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(query);
		}
		return res;
	}

	/**
	 * @throws SQLException the sQL exception
	 * @author andres (25/05/11)
	 * @since 1.3
	 */
	private void updateDireccionesEntidad(Entidad entidadUpdate,
			Connection con) throws SQLException {
		Statement stmt1;
		//1. Miramos las direcciones que hay que borrar
		String ids = "";
		int contador = 0;
		Vector<Direccion> direcciones = entidadUpdate.getDirecciones();
		for (int i = 0; i < direcciones.size(); i++){
			Direccion direccion = direcciones.get(i);
			if (direccion.getIdDireccion() != null && direccion.getIdDireccion() > 0){
				contador++;
				ids += direccion.getIdDireccion();
				if (i < (direcciones.size() - 1)){
					ids += ",";
				}
			}
		}
		if (direcciones.size() == 0){
			// System.out.println("NO ALMACENAMOS NINGUNA DIRECCION PARA LA ENTIDAD. BORRAMOS LAS QUE PUDIERA HABER");
			Statement stmt;
			String query = "DELETE FROM direccion WHERE empresa_idUsuario = " + entidadUpdate.getIdUsuario();
			// System.out.println(query);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(query);
		}
		if (contador > 0){
			int longitudCadena = ids.length();
			if (ids.charAt(longitudCadena - 1) == ','){
				ids = (String) ids.subSequence(0, longitudCadena - 1);
			}
			String qry1 =
				" SELECT idDireccion " +
				" FROM direccion d, entidad e " +
				" WHERE e.idUsuario = " + entidadUpdate.getIdUsuario() +
					" AND e.idUsuario = d.empresa_idUsuario " +
					" AND idDireccion " +
				" NOT IN (" + ids + ")";
			// System.out.println(qry1);
			//Y borrarlas...
			ps = con.prepareStatement(qry1);
			rs = ps.executeQuery();
			while (rs.next()) {
				long idDireccionBorrar = rs.getLong("idDireccion");
				Statement stmt;
				String query = "DELETE FROM direccion WHERE idDireccion=" + idDireccionBorrar;
				// System.out.println(query);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(query);
			}
		}
		//2. A�adir las que no existian o actualizar si ya existian
		for (int i = 0; i < direcciones.size(); i++){
			Direccion direccion = direcciones.get(i);
			if (direccion.getIdDireccion() != null && direccion.getIdDireccion() > 0){
				long idDireccion = direccion.getIdDireccion();
				//Existe, actualizar
				String insertDireccion =
					"UPDATE direccion SET ean='" + direccion.getCodigoEan() + "'," +
							"tipoDireccion='" + direccion.getTipoDireccion() + "'," +
							"municipio='" + direccion.getMunicipio() + "'," +
							"nombreCalle='" + direccion.getNombreCalle() + "'," +
							"codigoPostal='" + direccion.getCodigoPostal() + "'," +
							"localidad='" + direccion.getLocalidad() + "'," +
							"idProvincia='" + direccion.getIdProvincia() + "'," +
							"horarioEntrega='" + direccion.getHorario() + "' WHERE idDireccion = " + idDireccion;
				// System.out.println(insertDireccion);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertDireccion);
			}else{
				//No existe, insertar
				String insertDireccion =
					"INSERT INTO direccion(ean,tipoDireccion,empresa_idUsuario,municipio," +
					"nombreCalle,codigoPostal,localidad,idProvincia,horarioEntrega) " +
						" VALUES(" + "'"
							+ direccion.getCodigoEan() + "','"
							+ direccion.getTipoDireccion() + "',"
							+ entidadUpdate.getIdUsuario() + ",'"
							+ direccion.getMunicipio() + "','"
							+ direccion.getNombreCalle() +"','"
							+ direccion.getCodigoPostal() +"','"
							+ direccion.getLocalidad() +"',"
							+ direccion.getIdProvincia() + ",'"
							+ direccion.getHorario()
						+ "')";
				// System.out.println(insertDireccion);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertDireccion);
			}
		}
	}

	/**
	 * @author andres (31/08/11)
	 * @since 1.5
	 */
	private void updateTelefonosEntidad(Entidad entidadUpdate, Connection con) throws SQLException {
		Statement stmt1;
		//1. Miramos los telefonos que hay que borrar
		String ids = "";
		int contador = 0;
		Vector<Telefono> telefonos = entidadUpdate.getTelefonos();
		for (int i = 0; i < telefonos.size(); i++){
			Telefono direccion = telefonos.get(i);
			if (direccion.getIdTelefono() != null && direccion.getIdTelefono() > 0){
				contador++;
				ids += direccion.getIdTelefono();
				if (i < (telefonos.size() - 1)){
					ids += ",";
				}
			}
		}
		if (telefonos.size() == 0){
			// System.out.println("NO ALMACENAMOS NINGUN TELEFONO PARA LA ENTIDAD. BORRAMOS LOS QUE PUDIERA HABER");
			Statement stmt;
			String query = "DELETE FROM telefono WHERE idUsuario = " + entidadUpdate.getIdUsuario();
			// System.out.println(query);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(query);
		}
		if (contador > 0){
			int longitudCadena = ids.length();
			if (ids.charAt(longitudCadena - 1) == ','){
				ids = (String) ids.subSequence(0, longitudCadena - 1);
			}
			String qry1 =
				" SELECT idTelefono " +
				" FROM telefono t, entidad e " +
				" WHERE e.idUsuario = " + entidadUpdate.getIdUsuario() +
					" AND e.idUsuario = t.idUsuario " +
					" AND idTelefono " +
				" NOT IN (" + ids + ")";
			// System.out.println(qry1);
			//Y los borramos...
			ps = con.prepareStatement(qry1);
			rs = ps.executeQuery();
			while (rs.next()) {
				long idTelefonoBorrar = rs.getLong("idTelefono");
				Statement stmt;
				String query = "DELETE FROM telefono WHERE idTelefono = " + idTelefonoBorrar;
				// System.out.println(query);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(query);
			}
		}
		//2. A�adir las que no existian o actualizar si ya existian
		for (int i = 0; i < telefonos.size(); i++){
			Telefono telefono = telefonos.get(i);
			if (telefono.getIdTelefono() != null && telefono.getIdTelefono() > 0){
				long idTelefono = telefono.getIdTelefono();
				//Existe, actualizar
				String insertTfno =
					" UPDATE telefono " +
					" SET numeroTelefono='" + telefono.getNumero() + "'," +
						" idUsuario='" + entidadUpdate.getIdUsuario() + "'," +
						" tipoTelefono='" + telefono.getTipoTfno() + "'," +
						" notas='" + telefono.getNotas() + "' " +
					" WHERE idTelefono = " + idTelefono;
				// System.out.println(insertTfno);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertTfno);
			}else{
				//No existe, insertar
				String insertDireccion =
					"INSERT INTO telefono(numeroTelefono, idUsuario, tipoTelefono, notas) " +
					" VALUES(" + "'" + telefono.getNumero() + "'," + entidadUpdate.getIdUsuario() + ",'" + telefono.getTipoTfno() +
					"','" + telefono.getNotas() + "')";
				// System.out.println(insertDireccion);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertDireccion);
			}
		}
	}

	/**
	 * @author andres (1/09/11)
	 * @since 1.5
	 */
	private void updateEmailsEntidad(Entidad entidadUpdate, Connection con) throws SQLException {
		Statement stmt1;
		//1. Miramos los emails que hay que borrar
		String ids = "";
		int contador = 0;
		Vector<Email> emails = entidadUpdate.getEmails();
		for (int i = 0; i < emails.size(); i++){
			Email email = emails.get(i);
			if (email.getIdEmail() != null && email.getIdEmail() > 0){
				contador++;
				ids += email.getIdEmail();
				if (i < (emails.size() - 1)){
					ids += ",";
				}
			}
		}
		if (emails.size() == 0){
			// System.out.println("NO ALMACENAMOS NINGUN EMAIL PARA LA ENTIDAD. BORRAMOS LOS QUE PUDIERA HABER");
			Statement stmt;
			String query = "DELETE FROM email WHERE idUsuario = " + entidadUpdate.getIdUsuario();
			// System.out.println(query);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(query);
		}
		if (contador > 0){
			int longitudCadena = ids.length();
			if (ids.charAt(longitudCadena - 1) == ','){
				ids = (String) ids.subSequence(0, longitudCadena - 1);
			}
			String qry1 =
				" SELECT idEmail " +
				" FROM email em, entidad e " +
				" WHERE e.idUsuario = " + entidadUpdate.getIdUsuario() +
					" AND e.idUsuario = em.idUsuario " +
					" AND idEmail " +
				" NOT IN (" + ids + ")";
			// System.out.println(qry1);
			//Y los borramos...
			ps = con.prepareStatement(qry1);
			rs = ps.executeQuery();
			while (rs.next()) {
				long idEmailBorrar = rs.getLong("idEmail");
				Statement stmt;
				String query = "DELETE FROM email WHERE idEmail = " + idEmailBorrar;
				// System.out.println(query);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(query);
			}
		}
		//2. A�adir las que no existian o actualizar si ya existian
		for (int i = 0; i < emails.size(); i++){
			Email email = emails.get(i);
			if (email.getIdEmail() != null && email.getIdEmail() > 0){
				long idTelefono = email.getIdEmail();
				//Existe, actualizar
				String insertEmail =
					" UPDATE email " +
					" SET direccion='" + email.getDireccion() + "'," +
						" idUsuario='" + entidadUpdate.getIdUsuario() + "'," +
						" tipo='" + email.getTipo() + "'," +
						" notas='" + email.getNotas() + "' " +
					" WHERE idEmail = " + idTelefono;
				// System.out.println(insertEmail);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertEmail);
			}else{
				//No existe, insertar
				String insertSql =
					" INSERT INTO email(direccion, idUsuario, tipo, notas) " +
					" VALUES(" + "'" + email.getDireccion() + "'," + entidadUpdate.getIdUsuario() + ",'" +
					email.getTipo() + "','" + email.getNotas() + "')";
				// System.out.println(insertSql);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertSql);
			}
		}
	}

	private void updateDatosBancarios(Entidad entidadUpdate, Connection con) throws SQLException {
		Statement stmt1;
		//1. Miramos las formas de pago que hay que borrar
		String ids = "";
		int contador = 0;
		Vector<DatoBancario> formasPago = entidadUpdate.getFormasPagoEntidad();
		for (int i = 0; i < formasPago.size(); i++){
			DatoBancario formaPago = formasPago.get(i);
			if (formaPago.getIdDatoBancario() != null && formaPago.getIdDatoBancario() > 0){
				contador++;
				ids += formaPago.getIdDatoBancario();
				if (i < (formasPago.size() - 1)){
					ids += ",";
				}
			}
		}
		if (formasPago.size() == 0){
			// System.out.println("NO ALMACENAMOS NINGUNA FORMA DE PAGO PARA LA ENTIDAD. BORRAMOS LAS QUE PUDIERA HABER");
			Statement stmt;
			String query = "DELETE FROM referenciabancaria WHERE empresa_idUsuario = " + entidadUpdate.getIdUsuario();
			// System.out.println(query);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(query);
		}
		if (contador > 0){
			int longitudCadena = ids.length();
			if (ids.charAt(longitudCadena - 1) == ','){
				ids = (String) ids.subSequence(0, longitudCadena - 1);
			}
			String qry1 =
				" SELECT idDatoBancario " +
				" FROM referenciabancaria r, entidad e " +
				" WHERE e.idUsuario = " + entidadUpdate.getIdUsuario() +
					" AND e.idUsuario = r.empresa_idUsuario " +
					" AND idDatoBancario " +
				" NOT IN (" + ids + ")";
			// System.out.println(qry1);
			//Y los borramos...
			ps = con.prepareStatement(qry1);
			rs = ps.executeQuery();
			while (rs.next()) {
				long idDatoBancarioBorrar = rs.getLong("idDatoBancario");
				Statement stmt;
				String query = "DELETE FROM referenciabancaria WHERE idDatoBancario = " + idDatoBancarioBorrar;
				// System.out.println(query);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(query);
			}
		}
		//2. A�adir las que no existian o actualizar si ya existian
		for (int i = 0; i < formasPago.size(); i++){
			DatoBancario formaPago = formasPago.get(i);
			if (formaPago.getIdDatoBancario() != null && formaPago.getIdDatoBancario() > 0){
				long idFormaPago = formaPago.getIdDatoBancario();
				//Existe, actualizar
				String cuenta = formaPago.getNumCuenta();
				String banco = cuenta.substring(4, 8);
				long idBanco = Long.parseLong(banco);
				String insertFormaPago =
					" UPDATE referenciabancaria " +
					" SET idBanco='" + idBanco + "'," +
						" numCuenta='" + formaPago.getNumCuenta() + "'," +
						" idFormaPago='" + formaPago.getIdFormaPago() + "'," +
						" diaPago='" + formaPago.getDiaPago() + "', " +
						" diasFormaPago='" + formaPago.getDiasFormaPago() + "', " +
						" idFormaPagoDesde='" + formaPago.getIdFormaPagoDesde() + "' " +
					" WHERE idDatoBancario = " + idFormaPago;
				// System.out.println(insertFormaPago);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertFormaPago);
			}else{
				//No existe, insertar
				String cuenta = formaPago.getNumCuenta();
				String banco = cuenta.substring(4, 8);
				long idBanco = Long.parseLong(banco);
				String insertSql =
					"INSERT INTO referenciabancaria(empresa_idUsuario, idBanco, numCuenta, " +
					" idFormaPago, diaPago, diasFormaPago, idFormaPagoDesde) VALUES( " +
					entidadUpdate.getIdUsuario() + "," +
					idBanco + ",'" +
					formaPago.getNumCuenta() + "'," +
					formaPago.getIdFormaPago() + "," +
					formaPago.getDiaPago() + "," +
					formaPago.getDiasFormaPago() + "," +
					formaPago.getIdFormaPagoDesde() + ");";
				// System.out.println(insertSql);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertSql);
			}
		}
	}

	private int updateEntidadBasica(Entidad entidadFind, Entidad entidadUpdate,	Connection con) throws SQLException {
		PreparedStatement stmt;
		int res;
		NumberFormat nf = new DecimalFormat("0000000000000");
		String eanFormateado = "";
		int ean = 0;
		if (entidadUpdate.getEan().compareTo("") != 0){
			ean = Integer.parseInt(entidadUpdate.getEan());
			eanFormateado = nf.format(ean);
			entidadUpdate.setEan(eanFormateado);
			// System.out.println("El ean de la entidad es... " + eanFormateado);
		}
		String updateString =
			" UPDATE entidad SET "
			+ " ean = '" + entidadUpdate.getEan() + "',"
			+ " nombre = '" + entidadUpdate.getNombre() + "',"
			+ " apellidos = '" + entidadUpdate.getApellidos() + "',"
			+ " nif = '" + entidadUpdate.getNif() + "',"
			+ " web = '" + entidadUpdate.getWeb() + "',"
			+ " dsctoMercancia = '" + entidadUpdate.getDsctoMercancia() + "',"
			+ " dsctoValor = '" + entidadUpdate.getDsctoValor() + "',"
			+ " tipoPersona = '" + entidadUpdate.getAutonomoEmpresa() + "',"
			+ " recargoEquivalencia = " + entidadUpdate.getRecargoEquivalencia() + ","
			+ " limiteCredito = " + entidadUpdate.getLimiteCredito() + ","
			+ " exportableAutoventa = '" + entidadUpdate.getExportableAutoventa() + "', "
			+ " idRuta = " + entidadUpdate.getIdRuta() + ", "
			+ " idComercial = " + entidadUpdate.getIdComercial() + ", "
			+ " observaciones = ? "
			+ " WHERE idUsuario = " + entidadFind.getIdUsuario();
		// System.out.println(updateString);
		stmt = con.prepareStatement(updateString);
		stmt.setString(1, entidadUpdate.getObservaciones());
		res = stmt.executeUpdate();
		return res;
	}

	//@Override
	public Entidad loadEntidad(Entidad entry) throws Exception {
		Entidad entidadConsulta = new Entidad();
		// System.out.println("DAO loadEntidad");
		try {
			con = bddConexion();
			loadDatosEntidad(entry, entidadConsulta);
			loadDireccionesEntidad(entry, entidadConsulta);
			loadDatosBancariosEntidad(entry, entidadConsulta);
			loadTelefonosEntidad(entry, entidadConsulta);
			loadEmailsEntidad(entry, entidadConsulta);
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
		return entidadConsulta;
	}

	private void loadDatosEntidad(Entidad entry, Entidad entidadConsulta) throws SQLException {
		String sqlQry = " SELECT * FROM entidad WHERE idUsuario = " + entry.getIdUsuario();
		// System.out.println(sqlQry);
		ps = con.prepareStatement(sqlQry);
		rs = ps.executeQuery();
		while (rs.next()) {
			// System.out.println("Devuelve la entidad correspondiente");
			entidadConsulta.setIdUsuario(rs.getLong("idUsuario"));
			entidadConsulta.setNombre(rs.getString("nombre"));
			entidadConsulta.setApellidos(rs.getString("apellidos"));
			entidadConsulta.setNif(rs.getString("nif"));
			entidadConsulta.setWeb(rs.getString("web"));
			entidadConsulta.setEan(rs.getString("ean"));
			entidadConsulta.setAutonomoEmpresa(rs.getString("tipoPersona").charAt(0));
			entidadConsulta.setExportableAutoventa(rs.getString("exportableAutoventa"));
			entidadConsulta.setDsctoMercancia(rs.getDouble("dsctoMercancia"));
			entidadConsulta.setDsctoValor(rs.getDouble("dsctoValor"));
			entidadConsulta.setLimiteCredito(rs.getDouble("limiteCredito"));
			entidadConsulta.setRecargoEquivalencia(rs.getDouble("recargoEquivalencia"));
			entidadConsulta.setIdRuta(rs.getLong("idRuta"));
			entidadConsulta.setIdComercial(rs.getLong("idComercial"));
			entidadConsulta.setResponsable(rs.getString("usuarioResponsable"));
			entidadConsulta.setObservaciones(rs.getString("observaciones"));
		}
		sqlQry = "SELECT * FROM entidad_rol WHERE idEntidad = " + entry.getIdUsuario();
		// System.out.println(sqlQry);
		ps = con.prepareStatement(sqlQry);
		rs = ps.executeQuery();
		Vector<Rol> rolesEntidad = new Vector<Rol>();
		Vector<TipoProveedor> tiposProveedor = new Vector<TipoProveedor>();
		while (rs.next()) {
			// System.out.println("Devuelve los roles de la entidad");
			long idRol = rs.getLong("idRol");
			Rol rol = new Rol();
			rol.setIdRol(idRol);
			//Buscamos el nombre del rol
			PreparedStatement ps1;
			ResultSet rs1;
			String sql = "SELECT * " +
				" FROM rol " +
				" WHERE idRol = " + idRol;
			// System.out.println(sql);
			ps1 = con.prepareStatement(sql);
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				rol.setNombre(rs1.getString("nombre"));
			}
			rolesEntidad.add(rol);
			if (idRol == 2){
				//Proveedor
				TipoProveedor tipo = new TipoProveedor();
				tipo.setIdTipo(rs.getLong("idTipoSector"));
				//Buscamos el nombre del tipo de proveedor
				sql = "SELECT * " +
					" FROM tipo_proveedor " +
					" WHERE idTipo = " + tipo.getIdTipo();
				// System.out.println(sql);
				ps1 = con.prepareStatement(sql);
				rs1 = ps1.executeQuery();
				while(rs1.next()){
					tipo.setNombre(rs1.getString("nombre"));
				}
				tiposProveedor.add(tipo);
			}else
				if (idRol == 1){
					//Cliente
					entidadConsulta.setIdSectorCliente(rs.getLong("idTipoSector"));
				}
		}
		entidadConsulta.setRolesEntidad(rolesEntidad);
		entidadConsulta.setTiposProveedor(tiposProveedor);
	}

	/**
	 * @author andres (31.08.2011)
	 * @since 1.5
	 */
	private void loadTelefonosEntidad(Entidad entry, Entidad entidadConsulta) throws SQLException {
		String loadSql = "SELECT * FROM telefono WHERE idUsuario = " + entry.getIdUsuario();
		// System.out.println(loadSql);
		ps = con.prepareStatement(loadSql);
		rs = ps.executeQuery();
		Vector<Telefono> tfnos = new Vector<Telefono>();
		while (rs.next()) {
			// System.out.println("Devuelve los telefonos de la entidad correspondiente");
			Telefono tfno = new Telefono();
			tfno.setNumero(rs.getString("numeroTelefono"));
			tfno.setTipoTfno(rs.getString("tipoTelefono"));
			tfno.setIdTelefono(rs.getLong("idTelefono"));
			tfno.setNotas(rs.getString("notas"));
			tfnos.add(tfno);
		}
		entidadConsulta.setTelefonos(tfnos);
	}

	/**
	 * @author andres (1.9.2011)
	 * @since 1.5
	 */
	private void loadEmailsEntidad(Entidad entry, Entidad entidadConsulta) throws SQLException {
		String loadSql="SELECT * FROM  email WHERE idUsuario = " + entry.getIdUsuario();
		// System.out.println(loadSql);
		ps = con.prepareStatement(loadSql);
		rs = ps.executeQuery();
		Vector<Email> emails = new Vector<Email>();
		while (rs.next()) {
			// System.out.println("Devuelve los emails de la entidad correspondiente");
			Email email = new Email();
			email.setDireccion(rs.getString("direccion"));
			email.setTipo(rs.getString("tipo"));
			email.setIdEmail(rs.getLong("idEmail"));
			email.setNotas(rs.getString("notas"));
			emails.add(email);
		}
		entidadConsulta.setEmails(emails);
	}

	private void loadDireccionesEntidad(Entidad entry, Entidad entidadConsulta) throws SQLException {
		PreparedStatement ps1;
		ResultSet rs1;
		String updateSql =
			"SELECT d.idDireccion, d.nombreCalle, d.municipio, d.ean,d.localidad, d.idProvincia, d.codigoPostal," +
				"d.horarioEntrega, p.nombre as nombreProvincia, tipoDireccion " +
			" FROM direccion d, provincia p " +
			" WHERE p.idProvincia = d.idProvincia " +
				" AND empresa_idUsuario = " + entry.getIdUsuario();
		// System.out.println(updateSql);
		ps1 = con.prepareStatement(updateSql);
		rs1 = ps1.executeQuery();
		Vector<Direccion> direcciones = new Vector<Direccion>();
		Vector<Direccion> direccionesEntrega = new Vector<Direccion>();
		Vector<Direccion> direccionesFacturacion = new Vector<Direccion>();
		while(rs1.next()){
			Direccion d = new Direccion();
			// System.out.println("Devuelve las direcciones de la entidad correspondiente");
			d.setNombreCalle(rs1.getString("nombreCalle"));
			d.setTipoDireccion(rs1.getString("tipoDireccion"));
			d.setMunicipio(rs1.getString("municipio"));
			d.setIdDireccion(rs1.getLong("idDireccion"));
			d.setLocalidad(rs1.getString("localidad"));
			d.setIdProvincia(rs1.getLong("idProvincia"));
			d.setCodigoPostal(rs1.getString("codigoPostal"));
			d.setNombreProvincia(rs1.getString("nombreProvincia"));
			d.setHorario(rs1.getString("horarioEntrega"));
			d.setCodigoEan(rs1.getString("ean"));
			direcciones.add(d);
			if (d.getTipoDireccion().compareTo("F") == 0){
				direccionesFacturacion.add(d);
			}else{
				if (d.getTipoDireccion().compareTo("E") == 0){
					direccionesEntrega.add(d);
				}else{
					direccionesEntrega.add(d);
					direccionesFacturacion.add(d);
				}
			}
		}
		entidadConsulta.setDirecciones(direcciones);
		entidadConsulta.setDireccionesEntrega(direccionesEntrega);
		entidadConsulta.setDireccionesFacturacion(direccionesFacturacion);
	}

	//Carga los datos Bancarios de la entidad para Actualizar
	private void loadDatosBancariosEntidad(Entidad entry, Entidad entidadConsulta)
			throws SQLException {
		PreparedStatement ps1;
		ResultSet rs1;
		String loadSql =
			" SELECT * " +
			" FROM referenciabancaria rf, formapago fp " +
			" WHERE empresa_idUsuario = " + entry.getIdUsuario() +
				" AND rf.idFormaPago = fp.idFormaPago ";
		ps1 = con.prepareStatement(loadSql);
		rs1 = ps1.executeQuery();
		Vector<DatoBancario> formasPago = new Vector<DatoBancario>();
		// System.out.println("Devuelve los datos bancarios: " + loadSql);
		while(rs1.next()){
			DatoBancario formaPago = new DatoBancario();
			formaPago.setDiaPago(rs1.getLong("diaPago"));
			formaPago.setIdBanco(rs1.getLong("idBanco"));
			formaPago.setIdFormaPago(rs1.getLong("idFormaPago"));
			formaPago.setIdDatoBancario(rs1.getLong("idDatoBancario"));
			formaPago.setNumCuenta(rs1.getString("numCuenta"));
			String numeroCuenta = formaPago.getNumCuenta();
			if (numeroCuenta.compareTo("000000000000000000000000") == 0)
				formaPago.setCuentaAsociada(false);
			else
				formaPago.setCuentaAsociada(true);
			if (numeroCuenta.length() != 24){
				numeroCuenta = "000000000000000000000000";
			}
			formaPago.setCuentaIban(numeroCuenta.substring(0, 4));
			formaPago.setCuentaBanco(numeroCuenta.substring(4, 8));
			formaPago.setCuentaOfici(numeroCuenta.substring(8, 12));
			formaPago.setCuentaContr(numeroCuenta.substring(12, 14));
			formaPago.setCuentaNumer(numeroCuenta.substring(14, 24));
			formaPago.setDiasFormaPago(rs1.getLong("diasFormaPago"));

			String desc = "";
			String cuenta = rs1.getString("numCuenta");
			if (cuenta.equals("000000000000000000000000")){
				desc = rs1.getString("descripcion") + " a " +
					rs1.getLong("diasFormaPago") + " dias. Dia de pago: " + rs1.getLong("diaPago") + ".";
			}else{
				desc = rs1.getString("descripcion") + " a " +
					rs1.getLong("diasFormaPago") + " dias. Dia de pago: " + rs1.getLong("diaPago") +
					". Cuenta: " + rs1.getString("numCuenta");
			}
			formaPago.setDescripcion(desc);
			formaPago.setIdFormaPagoDesde(rs1.getLong("idFormaPagoDesde"));
			PreparedStatement ps2;
			ResultSet rs2;
			String qry =
				" SELECT * " +
				" FROM formapago_desde fp " +
				" WHERE idFormaPagoDesde = " + formaPago.getIdFormaPagoDesde();
			ps2 = con.prepareStatement(qry);
			rs2 = ps2.executeQuery();
			// System.out.println(qry);
			if (rs2.next()){
				formaPago.setDescripcionFormaPagoDesde(rs2.getString("descripcion"));
			}
			formasPago.add(formaPago);
		}
		entidadConsulta.setFormasPagoEntidad(formasPago);
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
	   		+ mes + "/" + fecha.get(java.util.Calendar.YEAR);
	   return fecharetorno;
	}

	//@Override
	public Vector<FormaPago> getFormasPago() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<FormaPago> resultado = new Vector<FormaPago>();
		try {
			con = bddConexion();
			String Qry = "SELECT idFormaPago, descripcion FROM formapago ORDER BY 2";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				FormaPago formaPago = new FormaPago();
				formaPago.setIdFormaPago(rs.getLong("idFormaPago"));
				formaPago.setDescripcion(rs.getString("descripcion"));
				//La a�adimos al Vector de resultado
				resultado.add(formaPago);
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
	public Vector<FormaPagoDesde> getFormasPagoDesde() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<FormaPagoDesde> resultado = new Vector<FormaPagoDesde>();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM formapago_desde ORDER BY descripcion";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				FormaPagoDesde formaPago = new FormaPagoDesde();
				formaPago.setIdFormaPagoDesde(rs.getLong("idFormaPagoDesde"));
				formaPago.setDescripcion(rs.getString("descripcion"));
				//La a�adimos al Vector de resultado
				resultado.add(formaPago);
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
	public Vector<DatoBancario> getFormasPagoEntidad(Entidad entidad) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<DatoBancario> resultado = new Vector<DatoBancario>();
		try {
			con = bddConexion();
			String Qry =
				" SELECT rb.empresa_idUsuario, rb.idDatoBancario, rb.diaPago, fp.descripcion, rb.diasFormaPago, " +
					" rb.numCuenta, rb.idFormaPagoDesde " +
				" FROM referenciabancaria rb, formapago fp " +
				" WHERE rb.idFormaPago = fp.idFormaPago ";
			if (entidad.getIdUsuario() != null && entidad.getIdUsuario() > 0)
				Qry += " AND rb.empresa_idUsuario = " + entidad.getIdUsuario();
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				DatoBancario dato = new DatoBancario();
				dato.setIdEntidad(rs.getLong("empresa_idUsuario"));
				dato.setIdDatoBancario(rs.getLong("idDatoBancario"));
				dato.setIdFormaPagoDesde(rs.getLong("idFormaPagoDesde"));
				dato.setDescripcion(rs.getString("descripcion"));
				dato.setDiasFormaPago(rs.getLong("diasFormaPago"));
				dato.setDiaPago(rs.getLong("diaPago"));
				dato.setNumCuenta(rs.getString("numCuenta"));
				if (dato.getNumCuenta().equals("000000000000000000000000"))
					dato.setCuentaAsociada(false);
				else
					dato.setCuentaAsociada(true);
				//La a�adimos al Vector de resultado
				PreparedStatement ps2;
				ResultSet rs2;
				String qry =
					" SELECT * " +
					" FROM formapago_desde fp " +
					" WHERE idFormaPagoDesde = " + dato.getIdFormaPagoDesde();
				ps2 = con.prepareStatement(qry);
				rs2 = ps2.executeQuery();
				// System.out.println(qry);
				if (rs2.next()){
					dato.setDescripcionFormaPagoDesde(rs2.getString("descripcion"));
				}
				resultado.add(dato);
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
	public Vector<Direccion> getDireccionesEntidad(Entidad entidad, String tipoDirecciones) throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Direccion> resultado = new Vector<Direccion>();
		try {
			con = bddConexion();
			String Qry =
				"SELECT * " +
					" FROM direccion d " +
					" WHERE d.empresa_idUsuario = " + entidad.getIdUsuario();
			if (tipoDirecciones != null && tipoDirecciones != "")
				Qry += " AND (d.tipoDireccion = '" + tipoDirecciones + "' OR d.tipoDireccion = 'A')";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Direccion dir = new Direccion();
				dir.setNombreCalle(rs.getString("nombreCalle"));
				dir.setLocalidad(rs.getString("localidad"));
				dir.setIdDireccion(rs.getLong("idDireccion"));
				resultado.add(dir);
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
	public Vector getProvincias() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector resultado = new Vector();
		try {
			con = bddConexion();
			String Qry="SELECT idProvincia,nombre FROM provincia ORDER BY 2";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Provincia prov = new Provincia();
				prov.setIdProvincia(rs.getLong("idProvincia"));
				prov.setNombre(rs.getString("nombre"));
				//La a�adimos al Vector de resultado
				resultado.add(prov);
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
	public Boolean addEntidad(Entidad entry) throws Exception {
		// System.out.println("DAO addENTIDAD (A�ADE CLIENTES Y PROVEEDORES)");
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idUsuario) AS idMaxUsuario FROM entidad");
			rs = ps.executeQuery();
			long idUsuarioMax = 0;
			while(rs.next()){
				Usuario usuarioMaximo = new Usuario();
				usuarioMaximo.setIdUsuario(rs.getLong("idMaxUsuario"));
				idUsuarioMax = usuarioMaximo.getIdUsuario() + 1;
			}
			// System.out.println("el id de la entidad es... " + idUsuarioMax);
			res = addEntidadBasico(entry, con, idUsuarioMax);
			addEntidadRolTipoSector(entry, con, idUsuarioMax);
			addDirecciones(entry, con, idUsuarioMax);
			addDatosBancarios(entry, con, idUsuarioMax);
			addTelefonosEntidad(entry, con, idUsuarioMax);
			addEmailsEntidad(entry, con, idUsuarioMax);
			if(res == 1){
				// System.out.println("REGISTRO DE ENTIDAD INSERTADO");
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

	private void addEntidadRolTipoSector(Entidad entry, Connection con,
			long idUsuarioMax) throws SQLException {
		Statement stmt;
		Vector<Rol> roles = entry.getRolesEntidad();
		for (int i = 0; i < roles.size(); i++){
			String insertString = "";
			//Insertamos los roles que tenga la entidad
			Rol rol = roles.get(i);
			//Roles: 1->cliente; 2->proveedor
			if (rol.getIdRol() == 1){
				//Rol cliente
				insertString =
					"INSERT INTO entidad_rol(idEntidad, idRol, idTipoSector) VALUES(" +
						idUsuarioMax + "," + rol.getIdRol() + "," + entry.getIdSectorCliente() + ")";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
			}else{
				if (rol.getIdRol() == 2){
					//Rol proveedor
					//Si se trata de un proveedor, insertamos todos los tipos de proveedor que pueda haber
					Vector<TipoProveedor> tipos = entry.getTiposProveedor();
					for (int j = 0; j < tipos.size(); j++){
						insertString =
							"INSERT INTO entidad_rol(idEntidad, idRol, idTipoSector) VALUES(" +
								idUsuarioMax + "," + rol.getIdRol() + "," + tipos.get(j).getIdTipo() + ")";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertString);
					}
				}
			}
		}
	}

	private void updateEntidadRolTipoSector(Entidad entidadUpdate, Connection con) throws SQLException {
		Statement stmt;
		//1. Eliminamos los roles del usuario (En lugar de actualizar)
		String query ="DELETE FROM entidad_rol WHERE idEntidad=" + entidadUpdate.getIdUsuario();
		// System.out.println(query);
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		stmt.executeUpdate(query);
		//2. Ahora los insertamos
		Vector<Rol> roles = entidadUpdate.getRolesEntidad();
		for (int i = 0; i < roles.size(); i++){
			String insertString = "";
			//Insertamos los roles que tenga la entidad
			Rol rol = roles.get(i);
			//Roles: 1->cliente; 2->proveedor
			if (rol.getIdRol() == 1){
				//Rol cliente
				insertString =
					"INSERT INTO entidad_rol(idEntidad, idRol, idTipoSector) VALUES(" +
					entidadUpdate.getIdUsuario() + "," + rol.getIdRol() + "," +
						entidadUpdate.getIdSectorCliente() + ")";
				// System.out.println(insertString);
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt.executeUpdate(insertString);
			}else{
				if (rol.getIdRol() == 2){
					//Rol proveedor
					//Si se trata de un proveedor, insertamos todos los tipos de proveedor que pueda haber
					Vector<TipoProveedor> tipos = entidadUpdate.getTiposProveedor();
					for (int j = 0; j < tipos.size(); j++){
						insertString =
							"INSERT INTO entidad_rol(idEntidad, idRol, idTipoSector) VALUES(" +
							entidadUpdate.getIdUsuario() + "," + rol.getIdRol() + "," + tipos.get(j).getIdTipo() + ")";
						// System.out.println(insertString);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertString);
					}
				}
			}
		}
	}

	private int addEntidadBasico(Entidad entry, Connection con, long idUsuarioMax) throws SQLException {
		PreparedStatement stmt;
		int res;
		//obtener el nuevo Id
		ps = con.prepareStatement("SELECT MAX(ean) AS maxEan FROM entidad");
		rs = ps.executeQuery();
		int ean = 0;
		NumberFormat nf = new DecimalFormat("0000000000000");
		String eanFormateado = "";
		if (entry.getEan().compareTo("") != 0){
			ean = Integer.parseInt(entry.getEan());
			eanFormateado = nf.format(ean);
			entry.setEan(eanFormateado);
			// System.out.println("el ean de la entidad es... " + eanFormateado);
		}
		String insertString =
			" INSERT INTO entidad(ean,idUsuario,nombre,apellidos,nif,web," +
				"dsctoMercancia,dsctoValor,usuarioResponsable,fechaRegistro," +
				"tipoPersona, recargoEquivalencia, limiteCredito, exportableAutoventa, idComercial, idRuta, observaciones) VALUES(" +
				"'" + eanFormateado + "',"
				+ idUsuarioMax + ",'"
				+ entry.getNombre() + "','"
				+ entry.getApellidos() + "','"
				+ entry.getNif() + "','"
				+ entry.getWeb() + "','"
				+ entry.getDsctoMercancia() + "','"
				+ entry.getDsctoValor() + "','"
				+ entry.getResponsable() + "',"
				+ "CURDATE(),'"
				+ entry.getAutonomoEmpresa() + "',"
				+ entry.getRecargoEquivalencia() + ","
				+ entry.getLimiteCredito() + ",'"
				+ entry.getExportableAutoventa() + "', "
				+ entry.getIdComercial() + ", "
				+ entry.getIdRuta() + ", "
				+ "?"
				+ ")";
		// System.out.println(insertString);
		stmt = con.prepareStatement(insertString);
		// System.out.println("OBSERVACIONES: " + entry.getObservaciones());
		stmt.setString(1, entry.getObservaciones());
		res = stmt.executeUpdate();
		return res;
	}

	/**
	 * Inserta TODAS las direcciones de una entidad
	 *
	 * @param entry El cliente
	 * @param con the con
	 * @param idUsuarioMax the id usuario max
	 * @throws SQLException the sQL exception
	 * @author andres (25/05/2011)
	 * @since 1.3
	 */
	private void addDirecciones(Entidad entry, Connection con, long idUsuario)throws SQLException {
		Statement stmt1;
		Vector<Direccion> direcciones = entry.getDirecciones();
		for (int i = 0; i < direcciones.size(); i++){
			Direccion direccion = direcciones.get(i);
			//SQL de insercion
			String insertDireccion =
				"INSERT INTO direccion(ean,tipoDireccion,empresa_idUsuario,municipio," +
				"nombreCalle,codigoPostal,localidad,idProvincia,horarioEntrega) " +
					" VALUES(" + "'"
						+ direccion.getCodigoEan() + "','"
						+ direccion.getTipoDireccion() + "',"
						+ idUsuario + ",'"
						+ direccion.getMunicipio() + "','"
						+ direccion.getNombreCalle() +"','"
						+ direccion.getCodigoPostal() +"','"
						+ direccion.getLocalidad() +"',"
						+ direccion.getIdProvincia() + ",'"
						+ direccion.getHorario()
					+ "')";
			// System.out.println(insertDireccion);
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt1.executeUpdate(insertDireccion);
		}
	}

	private void addTelefonosEntidad(Entidad entry, Connection con,
			long idUsuarioMax) throws SQLException {
		Statement stmt1;
		Vector<Telefono> telefonos = entry.getTelefonos();
		for (int i = 0; i < telefonos.size(); i++){
			Telefono tfno = telefonos.get(i);
			//SQL de insercion
			String insertDireccion =
				"INSERT INTO telefono(numeroTelefono, idUsuario, tipoTelefono, notas) " +
					" VALUES(" + "'" + tfno.getNumero() + "'," + idUsuarioMax + ",'" + tfno.getTipoTfno() +
					"','" + tfno.getNotas() + "')";
			// System.out.println(insertDireccion);
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt1.executeUpdate(insertDireccion);
		}
	}

	private void addEmailsEntidad(Entidad entry, Connection con,
			long idUsuarioMax) throws SQLException {
		Statement stmt1;
		Vector<Email> emails = entry.getEmails();
		for (int i = 0; i < emails.size(); i++){
			Email email = emails.get(i);
			//SQL de insercion
			String insertDireccion =
				"INSERT INTO email(direccion, idUsuario, tipo, notas) " +
					" VALUES(" + "'" + email.getDireccion() + "'," + idUsuarioMax + ",'" +
					email.getTipo() + "','" + email.getNotas() + "')";
			// System.out.println(insertDireccion);
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt1.executeUpdate(insertDireccion);
		}
	}

	//Inserta los datos bancarios
	private void addDatosBancarios(Entidad entry, Connection con,
			long idUsuarioMax) throws SQLException {
		Statement stmt1;
		Vector<DatoBancario> formasPago = entry.getFormasPagoEntidad();
		for (int i = 0; i < formasPago.size(); i++){
			DatoBancario formaPago = formasPago.get(i);
			//SQL de insercion
			String cuenta = formaPago.getNumCuenta();
			String banco = cuenta.substring(4, 8);
			long idBanco = Long.parseLong(banco);
			String insertDatoBancario =
				"INSERT INTO referenciabancaria(empresa_idUsuario, idBanco, numCuenta, " +
					" idFormaPago, diaPago, diasFormaPago, idFormaPagoDesde) VALUES( " +
					idUsuarioMax + "," +
					idBanco + ",'" +
					formaPago.getNumCuenta() + "'," +
					formaPago.getIdFormaPago() + "," +
					formaPago.getDiaPago() + "," +
					formaPago.getDiasFormaPago() + "," +
					formaPago.getIdFormaPagoDesde() + ")";
			// System.out.println(insertDatoBancario);
			stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt1.executeUpdate(insertDatoBancario);
		}
	}

	//@Override
	public Vector getListaDiasEntrega() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector resultado = new Vector();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM  entrega";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Entrega entrega = new Entrega();
				entrega.setIdEntrega(rs.getLong("idEntrega"));
				entrega.setNombre(rs.getString("nombre"));
				//La a�adimos al Vector de resultado
				resultado.add(entrega);
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
	public Vector getListaRutas() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector resultado = new Vector();
		try {
			con = bddConexion();
			String Qry="SELECT idRuta,nombre FROM ruta order by 1";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Ruta ruta = new Ruta();
				ruta.setIdRuta(rs.getLong("idRuta"));
				ruta.setNombre(rs.getString("nombre"));
				//La a�adimos al Vector de resultado
				resultado.add(ruta);
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
	public Vector getListaSectores() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector resultado = new Vector();
		try {
			con = bddConexion();
			String Qry="SELECT idSector,nombre FROM sector ORDER BY 1";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Sector sector = new Sector();
				sector.setIdSector(rs.getLong("idSector"));
				sector.setNombreSector(rs.getString("nombre"));
				//La a�adimos al Vector de resultado
				resultado.add(sector);
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
	public Vector getListaBancos() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector resultado = new Vector();
		try {
			con = bddConexion();
			String Qry="SELECT idBanco, nombre FROM banco ORDER BY 2";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				Banco banco = new Banco();
				banco.setIdBanco(rs.getLong("idBanco"));
				banco.setNombre(rs.getString("nombre"));
				//La a�adimos al Vector de resultado
				resultado.add(banco);
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
	public Vector getListaTiposProveedores() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector resultado = new Vector();
		try {
			con = bddConexion();
			String Qry="SELECT * FROM  tipo_proveedor";
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del proveedor en el registro
				TipoProveedor tipo = new TipoProveedor();
				tipo.setIdTipo(rs.getLong("idTipo"));
				tipo.setNombre(rs.getString("nombre"));
				//La a�adimos al Vector de resultado
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
		//Retornamos el vector de resultado.
		return resultado;
	}

	//Inserta los d�as de Entrega
	private void addRequisitosHomologacion(Entidad entry, Connection con, long idUsuarioMax, List listaReqHom) throws SQLException {
		Statement stmt1;
		if(listaReqHom.isEmpty()){
			// System.out.println("No hay requisitos de homologacion");
		}else{
			Iterator itr = listaReqHom.iterator();
			while(itr.hasNext()) {
			    String idReqHom = (String)itr.next();
			    // System.out.print(" Requisito es :"+idReqHom);
				//SQL de insercion
				String insertSql=
					"insert into homologacion_proveedor(idUsuario,idRequisito) values(" +
					  idUsuarioMax + "," + idReqHom + ")";
				// System.out.println(insertSql);
				stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(insertSql);
			}
		}
	}

	//@Override
	public Boolean deshabilitaEntidad(Entidad entidadFind) throws Exception {
		// System.out.println("DAO deshablitaEntidad");
		Statement stmt;
		Boolean resultado = false;
		int res = 0;
		try {
			con = bddConexion();
			//SQL de insercion
			String updateString= "UPDATE entidad SET habilitado = 'N' WHERE idUsuario =" + entidadFind.getIdUsuario();
			// System.out.println(updateString);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(updateString);
			if(res == 1){
				// System.out.println("ENTIDAD DESHABILITADA");
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

	/**
	 * Consulta de Proveedores filtrados por habilitados = 'S'.
	 * @param entry the entry
	 * @return the proveedores
	 * @throws Exception the exception
	 */
	//@Override
	public Vector<Entidad> getProveedores(Entidad entry) throws Exception {
		Vector<Entidad> resultado = new Vector<Entidad>();
		String qry2 = null;
		// System.out.println("DAO getProveedores");
		try {
			con = bddConexion();
			String Qry =
				"SELECT en.nombre,en.apellidos,en.idUsuario,en.nif,en.fechaRegistro,en.web " +
				" FROM entidad en, entidad_rol rol ";
			qry2 = " WHERE rol.idRol = 2 " +
				" AND rol.idEntidad = en.idUsuario " +
				" AND en.habilitado='S'";
			if(entry.getIdSector()!= 0){
				   qry2 = qry2 + " AND en.idSector="+entry.getIdSector();
			 }
			if(entry.getIdUsuario() != null && entry.getIdUsuario()!= 0){
				   qry2 = qry2 + " AND en.idUsuario="+entry.getIdUsuario();
			 }
			if(entry.getEan() != null && entry.getEan().compareTo("") != 0){
				   qry2 = qry2 + " AND en.ean='" + entry.getEan() + "'";
			}
			if(entry.getIdTipo() != 0){
				   qry2 = qry2 + " AND en.idTipo='" + entry.getIdTipo() + "'";
			 }
			qry2 = qry2+" GROUP BY idUsuario ORDER BY en.nombre ";
			Qry = Qry + qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Entidad entidad = new Entidad();
				entidad.setIdUsuario(rs.getLong("idUsuario"));
				entidad.setNombre(rs.getString("nombre"));
				entidad.setApellidos(rs.getString("apellidos"));
				entidad.setNif(rs.getString("nif"));
				entidad.setFecha(rs.getDate("fechaRegistro"));
				entidad.setWeb(rs.getString("web"));
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
	 * Consulta de Clientes filtrados por habilitados = 'S'.
	 * @param entry the entry
	 * @return the clientes
	 * @throws Exception the exception
	 */
	//@Override
	public Vector<Entidad> getClientes(Entidad entry) throws Exception {
		Vector<Entidad> resultado = new Vector<Entidad>();
		String qry2 = "";
		// System.out.println("DAO getClientes");
		//exportarClientes();
		try {
			con = bddConexion();
			String Qry =
				"SELECT en.nombre,en.apellidos,en.idUsuario,en.nif,en.fechaRegistro,en.web " +
				" FROM entidad en, entidad_rol rol ";
			qry2 = " WHERE rol.idRol = 1 " +
				" AND rol.idEntidad = en.idUsuario " +
				" AND en.habilitado='S'";
			if(entry.getIdSector() != 0){
				qry2=qry2+" AND rol.idTipoSector=" + entry.getIdSector();
			}
			if(entry.getIdUsuario() !=null && entry.getIdUsuario()!= 0){
				qry2=qry2+" AND en.idUsuario="+entry.getIdUsuario();
			}
			if(entry.getEan() !=null && entry.getEan().compareTo("") != 0){
				qry2=qry2+" AND en.ean='" + entry.getEan()+"'";
			}
			qry2 = qry2 + " GROUP BY idUsuario ORDER BY en.nombre";
			Qry = Qry + qry2;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Entidad entidad = new Entidad();
				entidad.setIdUsuario(rs.getLong("idUsuario"));
				entidad.setNombre(rs.getString("nombre"));
				entidad.setApellidos(rs.getString("apellidos"));
				entidad.setNif(rs.getString("nif"));
				entidad.setFecha(rs.getDate("fechaRegistro"));
				entidad.setWeb(rs.getString("web"));
				PreparedStatement ps2;
				ResultSet rs2;
				qry2 =
					" SELECT e.idUsuario " +
					" FROM entidad e, factura f " +
					" WHERE (f.estado = 2 OR f.estado = 1) " +
						" AND f.idCliente = e.idUsuario " +
						" AND f.idCliente = " + entidad.getIdUsuario();
				//// System.out.println(qry2);
				ps2 = con.prepareStatement(qry2);
				rs2 = ps2.executeQuery();
				//Vector<TipoProveedor> tipos = new Vector<TipoProveedor>();
				if (rs2.next()) {
					// System.out.println("Encontrado un cliente con deudas");
					entidad.setDeudas(true);
				}else
					entidad.setDeudas(false);
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
	 * Devuelve la descripcion de una referencia bancaria a partir de su id
	 * @param idFormaPago
	 * @return Descripci�n de la forma de pago
	 * @throws Exception
	 * @author andres (20/05/2011)
	 * @since 1.0
	 */
	public String getNombreFormaPago(long idDatoBancario) throws Exception{
		String nombre = "";
		try {
			con = bddConexion();
			String Qry = "SELECT rb.idDatoBancario, rb.diaPago, fp.descripcion, " +
					" rb.diasFormaPago, rb.numCuenta " +
					" FROM referenciabancaria rb, formaPago fp " +
					" WHERE rb.idFormaPago = fp.idFormaPago " +
						" AND rb.idDatoBancario = " + idDatoBancario;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				String cuenta = rs.getString("numCuenta");
				if (cuenta.equals("000000000000000000000000")){
					nombre = rs.getString("descripcion") + " a " +
						rs.getLong("diasFormaPago") + " dias. Dia de pago: " + rs.getLong("diaPago") + ".";
				}else{
					nombre = rs.getString("descripcion") + " a " +
						rs.getLong("diasFormaPago") + " dias. Dia de pago: " + rs.getLong("diaPago") +
						". Cuenta: " + rs.getString("numCuenta");
				}
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
		return nombre;
	}

    //@Override
	public Entidad getEntidad(long id) throws Exception {
		Entidad entidad = new Entidad();
		try {
			con = bddConexion();
			String Qry = " SELECT * FROM entidad WHERE idUsuario = '" + id + "'; ";
			// System.out.println("getEntidad(long id): " + Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				//Completamos los datos del registro
				entidad.setIdUsuario(id);
				entidad.setIdSector(rs.getLong("idSector"));
				entidad.setIdTipo(rs.getLong("idTipo"));
				entidad.setNombre(rs.getString("nombre"));
				entidad.setNif(rs.getString("nif"));
				entidad.setEan(rs.getString("ean"));
				entidad.setWeb(rs.getString("web"));
				entidad.setDsctoMercancia(rs.getDouble("dsctoMercancia"));
				entidad.setDsctoValor(rs.getDouble("dsctoValor"));
				entidad.setFoto(rs.getString("foto"));
				entidad.setObservaciones(rs.getString("observaciones"));
				// System.out.println("OBSERVACIONES ENTIDAD: " + entidad.getObservaciones());
				entidad.setHabilitado(rs.getString("habilitado"));
				entidad.setExportableAutoventa(rs.getString("exportableAutoventa"));
				PreparedStatement ps2;
				ResultSet rs2;
				String qry2 =
					"SELECT * " +
					" FROM entidad_rol er, tipo_proveedor tp " +
					" WHERE er.idEntidad = " + id +
						" AND er.idRol = 2 " +
						" AND er.idTipoSector = tp.idTipo";
				// System.out.println(qry2);
				ps2 = con.prepareStatement(qry2);
				rs2 = ps2.executeQuery();
				Vector<TipoProveedor> tipos = new Vector<TipoProveedor>();
				while (rs2.next()) {
					TipoProveedor tipo = new TipoProveedor();
					tipo.setIdTipo(rs2.getLong("idTipo"));
					tipo.setNombre(rs2.getString("nombre"));
					tipos.add(tipo);
				}
				entidad.setTiposProveedor(tipos);
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
		return entidad;
	}

    //@Override
	public void registrarSector(Sector sector) throws Exception{
    	// System.out.println("DAO registrarSector");
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idSector) AS idMaxSector FROM sector");
			rs = ps.executeQuery();
			long idMax = 0;
			if(rs.next()){
				idMax = rs.getLong("idMaxSector") + 1;
			}else
				idMax = 1;
			// System.out.println("el id del sector es... "+ idMax);
			Statement stmt;
			//SQL de insercion
			String insertSql =
				"INSERT INTO sector(idSector,nombre) VALUES(" +
				  idMax + ",'" + sector.getNombreSector() + "')";
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertSql);
			if(res == 1){
				// System.out.println("SECTOR INSERTADO");
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
	public void registrarFormaPago(FormaPago formaPago) throws Exception{
    	// System.out.println("DAO registrarFormaPago");
		int res = 0;
		try {
			con = bddConexion();
			Statement stmt;
			//SQL de insercion
			String insertSql =
				"INSERT INTO formapago(idFormaPago,descripcion) VALUES(" +
				  formaPago.getIdFormaPago() + ",'" + formaPago.getDescripcion() + "')";
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertSql);
			if(res == 1){
				// System.out.println("FORMA DE PAGO INSERTADO");
			}
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
	public void registrarBanco(Banco banco) throws Exception{
    	// System.out.println("DAO registrarBanco");
		int res = 0;
		try {
			con = bddConexion();
			//obtener el nuevo Id
			ps = con.prepareStatement("SELECT MAX(idBanco) AS idMaxBanco FROM banco");
			rs = ps.executeQuery();
			long idMax = 0;
			if(rs.next()){
				idMax = rs.getLong("idMaxBanco") + 1;
			}else
				idMax = 1;
			// System.out.println("el id del banco es... "+ idMax);
			Statement stmt;
			//SQL de insercion
			String insertSql =
				"INSERT INTO banco(idBanco,nombre) VALUES(" +
				  idMax + ",'" + banco.getNombre() + "')";
			// System.out.println(insertSql);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeUpdate(insertSql);
			if(res == 1){
				// System.out.println("BANCO INSERTADO");
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
    public Empresa getEmpresa(long idEmpresa) throws Exception{
    	Empresa empresa = new Empresa();
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM empresa e WHERE e.idEmpresa = " + idEmpresa;
			// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				//Completamos los datos de la empresa
				empresa.setIdEmpresa(idEmpresa);
				empresa.setNombre(rs.getString("nombre"));
				empresa.setLopdFactura(rs.getString("lopdFactura"));
				empresa.setRegistroSanitario(rs.getString("registroSanitario"));
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
		return empresa;
    }

    //@Override
    public GestionRoles getRoles() throws Exception{
    	GestionRoles gestionRoles = new GestionRoles();
    	Vector<Rol> roles = new Vector<Rol>();
    	Vector<Accion> acciones = new Vector<Accion>();//Todas las acciones, sin roles
    	//1. Cargar todos los roles
    	//2. Cargar todas las acciones
    	//3. Cargar las acciones con los roles permitidos
		try {
			con = bddConexion();
			String Qry = "SELECT * FROM rol r ORDER BY nombre";
			//// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Rol rol = new Rol();
				rol.setIdRol(rs.getLong("idRol"));
				rol.setNombre(rs.getString("nombre"));
				roles.add(rol);
			}
			Qry = "SELECT DISTINCT * FROM accion a ORDER BY idMostrar";
			//// System.out.println(Qry);
			ps = con.prepareStatement(Qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Accion accion = new Accion();
				accion.setIdAccion(rs.getLong("idAccion"));
				accion.setNombreAccion(rs.getString("nombre"));
				accion.setIdPadre(rs.getLong("idAccionPadre"));
				acciones.add(accion);
			}
			//Para cada accion, cargamos los roles permitidos
			for (int i = 0; i < acciones.size(); i++){
				Qry = "SELECT * FROM accion_rolpermitido a WHERE a.idAccion = " + acciones.get(i).getIdAccion();
				//// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				Vector<Rol> rolesPermitidos = new Vector<Rol>();
				while (rs.next()) {
					Rol rol = new Rol();
					rol.setIdRol(rs.getLong("idRol"));
					rolesPermitidos.add(rol);
				}
				acciones.get(i).setRolesPermitidos(rolesPermitidos);
				Qry = "SELECT * FROM accion_idsrelacionados a WHERE a.idAccion = " + acciones.get(i).getIdAccion();
				//// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				Vector<String> idsRelacionados = new Vector<String>();
				while (rs.next()) {
					idsRelacionados.add(rs.getString("idRelacionado"));
				}
				//Cargamos tambien los ids relaciones de la acci�n padre
				Qry = "SELECT * FROM accion_idsrelacionados a WHERE a.idAccion = " + acciones.get(i).getIdPadre();
				// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					idsRelacionados.add(rs.getString("idRelacionado"));
				}
				acciones.get(i).setIdsRelacionados(idsRelacionados);
				Qry = "SELECT * FROM accion_clasesrelacionadas a WHERE a.idAccion = " + acciones.get(i).getIdAccion();
				//// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				Vector<String> clasesRelacionadas = new Vector<String>();
				while (rs.next()) {
					clasesRelacionadas.add(rs.getString("claseRelacionada"));
				}
				//Cargamos tambien las clases relacionadas de la acci�n padre
				Qry = "SELECT * FROM accion_clasesrelacionadas a WHERE a.idAccion = " + acciones.get(i).getIdPadre();
				//// System.out.println(Qry);
				ps = con.prepareStatement(Qry);
				rs = ps.executeQuery();
				while (rs.next()) {
					clasesRelacionadas.add(rs.getString("claseRelacionada"));
				}
				acciones.get(i).setClasesRelacionadas(clasesRelacionadas);
			}
			gestionRoles.setAcciones(acciones);
			gestionRoles.setRoles(roles);
			gestionRoles.setNumeroRoles(roles.size());
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
		return gestionRoles;
    }

    //@Override
	public void guardarConfiguracionEmpresa(Empresa empresa, Vector<Accion> modificaciones, Vector<Usuario> usuarios) throws Exception{
    	//// System.out.println("guardarConfiguracionEmpresa DAO...");
    	try {
			con = bddConexion();
			String insertSql =
				" UPDATE empresa " +
				" SET lopdFactura = '" + empresa.getLopdFactura() + "', " +
					" registroSanitario = '" + empresa.getRegistroSanitario() + "' " +
				" WHERE idEmpresa = " + empresa.getIdEmpresa() + ";";
			//// System.out.println(insertSql);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(insertSql);
			//Modificamos la tabla accion_rolpermitido
			for (int i = 0; i < modificaciones.size(); i++){
				Accion accionRol = modificaciones.get(i);
				long idAccion = accionRol.getIdAccion();
				long idRol = accionRol.getIdRol();
				String valor = accionRol.getValor();
				if (valor.equals("si")){
					insertSql =
						" INSERT INTO accion_rolpermitido VALUES(" + idAccion + ", " + idRol + ");";
					//// System.out.println(insertSql);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertSql);
				}else{
					if (valor.equals("no")){
						insertSql =
							" DELETE FROM accion_rolpermitido WHERE idAccion = " + idAccion + " AND idRol = " + idRol + ";";
						// System.out.println(insertSql);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertSql);
					}
				}
			}
			for (int i = 0; i < usuarios.size(); i++){
				Usuario usuario = usuarios.get(i);
				long idUsuario = usuario.getIdUsuario();
				long idRol = usuario.getIdRolPredeterminado();
				String valor = usuario.getLogin();
				if (valor.equals("si")){
					insertSql =
						" INSERT INTO usuario_rol VALUES(" + idUsuario + ", " + idRol + ");";
					//// System.out.println(insertSql);
					stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					stmt.executeUpdate(insertSql);
				}else{
					if (valor.equals("no")){
						insertSql =
							" DELETE FROM usuario_rol WHERE idUsuario = " + idUsuario + " AND idRol = " + idRol + ";";
						// System.out.println(insertSql);
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						stmt.executeUpdate(insertSql);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) {e.printStackTrace();}
		}
    }
}