package es.induserco.opilion.datos.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import es.induserco.opilion.data.comun.Mensaje;
import es.induserco.opilion.data.comun.Rol;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.datos.mensaje.MensajeDAO;
import es.induserco.opilion.infraestructura.DatabaseConfig;


/**
 * Clase que implementa las operaciones de acceso a la base de datos para la
 * entidad USUARIO.
 * 
 * @author
 * @since 11/11/2009
 * @version 1.0
 */
public class UsuarioDAO extends DatabaseConfig implements IUsuarioDataService {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;

	//@Override
	public Vector<Usuario> getUsuarios() throws Exception {
		Vector<Usuario> resultado = new Vector<Usuario>();
		try {
			con = bddConexion();
			String qry =
				" SELECT * " +
				" FROM usuario ORDER BY login";
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuario.setIdUsuario(rs.getLong("idUsuario"));
				usuario.setIdRolPredeterminado(rs.getLong("idRolPredeterminado"));
				qry =
					" SELECT ur.idRol " +
					" FROM usuario_rol ur " +
					" WHERE ur.idUsuario = " + usuario.getIdUsuario();
				PreparedStatement ps2 = con.prepareStatement(qry);
				ResultSet rs2 = ps2.executeQuery();
				Vector<Rol> roles = new Vector<Rol>();
				while (rs2.next()) {
					Rol rol = new Rol();
					rol.setIdRol(rs2.getLong("idRol"));
					roles.add(rol);
				}
				usuario.setRoles(roles);
				resultado.add(usuario);
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
		return resultado;
	}
	
	//@Override
	public Vector<Usuario> getComerciales() throws Exception {
		Vector<Usuario> resultado = new Vector<Usuario>();
		try {
			con = bddConexion();
			String qry =
				" SELECT * " +
				" FROM usuario u, usuario_rol ur " +
				" WHERE ur.idUsuario = u.idUsuario " +
					" AND ur.idRol = 5 " +
				" ORDER BY login";
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuario.setIdUsuario(rs.getLong("idUsuario"));
				resultado.add(usuario);
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
		return resultado;
	}
	
	//@Override
	public Vector<Ruta> getRutas() throws Exception {
		Vector<Ruta> resultado = new Vector<Ruta>();
		try {
			con = bddConexion();
			String qry =
				" SELECT r.*, u.login AS nombreComercial " +
				" FROM ruta r, usuario u WHERE r.idComercialDefecto = u.idUsuario ORDER BY r.nombre; ";
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ruta ruta = new Ruta();
				ruta.setIdRuta(rs.getLong("idRuta"));
				ruta.setNombre(rs.getString("nombre"));
				ruta.setIdComercialDefecto(rs.getLong("idComercialDefecto"));
				ruta.setComercialDefecto(rs.getString("nombreComercial"));
				resultado.add(ruta);
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
		return resultado;
	}
	
	//@Override
	public Ruta getRuta(Ruta ruta) throws Exception {
		Ruta resultado = new Ruta();
		try {
			con = bddConexion();
			String qry =
				" SELECT * " +
				" FROM ruta WHERE idRuta = " + ruta.getIdRuta();
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			while (rs.next()) {
				resultado.setIdRuta(rs.getLong("idRuta"));
				resultado.setNombre(rs.getString("nombre"));
				resultado.setIdComercialDefecto(rs.getLong("idComercialDefecto"));
				qry = " SELECT DISTINCT e.nombre, e.apellidos FROM entidad e WHERE idRuta = " + ruta.getIdRuta();
				PreparedStatement psAux = con.prepareStatement(qry);
				ResultSet rsAux = psAux.executeQuery();
				while (rsAux.next()){
					Entidad e = new Entidad();
					e.setNombre(rsAux.getString("nombre"));
					e.setApellidos(rsAux.getString("apellidos"));
					if (e.getNombre().equals("")){
						e.setNombre(e.getApellidos());
					}else{
						if (!e.getNombre().equals("") && !e.getApellidos().equals("")){
							e.setNombre(e.getNombre() + " " + e.getApellidos());
						}
					}
					resultado.addCliente(e);
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
		return resultado;
	}

	//@Override
	public Boolean addUsuario(Usuario usuario) throws Exception {
		try {
			con = bddConexion();
			ps = con.prepareStatement(" SELECT max(idUsuario) AS idUsuario FROM usuario; ");
			rs = ps.executeQuery();
			int maximo = 1;
			if (rs.next())
				maximo = rs.getInt("idUsuario");
			maximo++;
			// System.out.println("el identificador de usuario es... " + maximo);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String sql =
				" INSERT INTO usuario(idUsuario, login, password) " +
				" VALUES(" +
				maximo + ",'" + usuario.getLogin() + "','" + usuario.getPassword() + "'); ";
			stmt.executeUpdate(sql);
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
		if (rs != null)
			return true;	
		return false;
	}
	
	//@Override
	public void addRuta(Ruta ruta) throws Exception {
		try {
			con = bddConexion();
			ps = con.prepareStatement(" SELECT max(idRuta) AS idRuta FROM ruta; ");
			rs = ps.executeQuery();
			int maximo = 1;
			if (rs.next())
				maximo = rs.getInt("idRuta");
			maximo++;
			// System.out.println("el identificador de ruta es... " + maximo);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String sql =
				" INSERT INTO ruta(idRuta, nombre, idComercialDefecto) " +
				" VALUES(" +
				maximo + ",'" + ruta.getNombre() + "','" + ruta.getIdComercialDefecto() + "'); ";
			stmt.executeUpdate(sql);
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
	public void updateRuta(Ruta ruta) throws Exception {
		try {
			con = bddConexion();
			/*Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String sql =
				" UPDATE ruta " +
				" SET nombre = '" + ruta.getNombre() + "', idComercialDefecto = " + ruta.getIdComercialDefecto() +
				" WHERE idRuta = " + ruta.getIdRuta();*/
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
	public Boolean getUsuarioExiste(Usuario usuario) throws Exception {
		boolean existe = false;
		try {
			con = bddConexion();
			ps = con.prepareStatement("SELECT * FROM usuario WHERE login='" + usuario.getLogin()+"'");
			// System.out.println("SELECT * FROM usuario WHERE login=" + usuario.getLogin());
			rs = ps.executeQuery();
			if (rs.next()) existe=true;
			// System.out.println("Existe el usuario: " + rs.next());
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
		//Retornamos el vector de resultado.
		return existe;
	}

	//@Override
	public Boolean getUsuarioValidar(Usuario usuario) throws Exception {
		boolean validado=false;
		try {
			con = bddConexion();
			String qry = 
				" SELECT u.login, u.password " +
				" FROM usuario u " +
				" WHERE u.login = ? AND u.password = ? AND u.habilitado = 'S'; ";
			ps = con.prepareStatement(qry);
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				validado = true;
				// System.out.println("Validado");
			}
			// System.out.println("Existe el usuario: "+rs.next());
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
		//Retornamos el vector de resultado.
		return validado;
	}

	//@Override
	public Boolean getUsuarioAutenticar(Usuario usuario, String action) throws Exception {
		boolean autenticado = false;
		if (!(action.equals("Login") || action.equals("Inicio") ||
				action.equals("InicioAjax") || action.equals("Logout")) && usuario != null){
			try {
				con = bddConexion();
				String qry =
					" SELECT ar.idRol " +
					" FROM accion_rolpermitido ar, accion a, accion_action aa " +
					" WHERE a.idAccion = ar.idAccion " +
						" AND a.idAccion = aa.idAccion " +
						" AND aa.action = '" + action + "'";
				ps = con.prepareStatement(qry);
				// System.out.println(qry);
				rs = ps.executeQuery();
				boolean sigue = true;
				while (rs.next()) {
					for (int i = 0; i < usuario.getRoles().size(); i++){
						if (usuario.getRoles().get(i).getIdRol() == rs.getLong("idRol")){
							autenticado = true;
							// System.out.println("Autenticado!");
							sigue = false;
							break;
						}
					}
					if (!sigue)
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw (e);
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();
				} catch (Exception e) { e.printStackTrace(); }
			}
		}else
			if (action.equals("Login") || action.equals("Inicio") || action.equals("InicioAjax") || action.equals("Logout"))
				autenticado = true;
		//Retornamos el vector de resultado.
		return autenticado;
	}
	
	//@Override
	public Usuario getUsuarioCompleto(Usuario usuario) throws Exception {
		try {
			con = bddConexion();
			String qry =
				" SELECT * " +
				" FROM usuario " +
				" WHERE login = '" + usuario.getLogin() + "' " +
						" AND password = '" + usuario.getPassword() + "'";
			// System.out.println(qry);
			ps = con.prepareStatement(qry);
			rs = ps.executeQuery();
			if (rs.next()) {
				usuario.setIdUsuario(rs.getLong("idUsuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setIdRolPredeterminado(rs.getLong("idRolPredeterminado"));
				qry =
					" SELECT ur.idRol " +
					" FROM usuario_rol ur " +
					" WHERE ur.idUsuario = " + usuario.getIdUsuario();
					PreparedStatement ps2 = con.prepareStatement(qry);
					ResultSet rs2 = ps2.executeQuery();
					Vector<Rol> roles = new Vector<Rol>();
					while (rs2.next()) {
						Rol rol = new Rol();
						rol.setIdRol(rs2.getLong("idRol"));
						roles.add(rol);
					}
					usuario.setRoles(roles);
			}
			usuario.setMensajes((new MensajeDAO()).getMensajes(usuario));
			//le metemos los mensajes asociados
			ps = con.prepareStatement("SELECT * FROM usuario_mensaje WHERE idUsuario='" + usuario.getIdUsuario() + "'");
			rs = ps.executeQuery();
			List<Mensaje> mensajes = new ArrayList<Mensaje>();
			if (rs.next()) {
				Mensaje mensaje = new Mensaje();
				mensaje.setIdUsuario(usuario.getIdUsuario());
				mensaje.setIdMensaje(rs.getLong("idMensaje"));
				mensaje.setFecha(rs.getDate("fecha"));
				mensaje.setEstado(rs.getString("estado"));
				mensaje.setMensaje(rs.getString("mensaje"));
				mensajes.add(mensaje);
			}
			usuario.setMensajes(mensajes);
			// System.out.println("Usuario completo cargado: "+rs.next());
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
		//Retornamos el vector de resultado.
		return usuario;
	}

	public void updatePass(Usuario usuario) throws Exception{
		try {
			con = bddConexion();
			//SQL de update
			String updateString =
				"UPDATE usuario SET "
				+ " password = '" + usuario.getPassword() + "', login = '" + usuario.getNuevoLogin() + "'"
					+ " WHERE login = '" + usuario.getLogin() + "'";
			// System.out.println(updateString);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(updateString);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				con.close();
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
}