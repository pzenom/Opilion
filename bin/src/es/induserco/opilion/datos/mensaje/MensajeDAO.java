package es.induserco.opilion.datos.mensaje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import es.induserco.opilion.data.comun.Mensaje;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.infraestructura.DatabaseConfig;

public class MensajeDAO extends DatabaseConfig implements IMensajeDataService {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//@Override
	public Mensaje addMensaje(Usuario usuario, Mensaje mensaje) throws Exception {
		// System.out.println("DAO addMensaje");
		try {
			con = bddConexion();
			//obtener el nuevo Id
			/*
			ps = con.prepareStatement("SELECT max(idMensaje) as idMaxMensaje FROM usuario_mensaje");
			rs = ps.executeQuery();
			long idMax = 0 ;
			if(rs.next()){
				idMax=rs.getLong("idMaxMensaje")+1;
				mensaje.setIdMensaje(idMax);
			}
			else
				mensaje.setIdMensaje(idMax+1);
			// System.out.println("el id de entrada es... "+ idMax);*/
			//la fecha la debe cargar autom�ticamente
			/*ps = con.prepareStatement(" SELECT CURDATE() as fecha ");
			rs = ps.executeQuery();
			while(rs.next()){
				mensaje.setFecha(rs.getDate("fecha"));
	        }*/
			String insertString= 
			"INSERT INTO usuario_mensaje (idUsuario, mensaje) " +
			"VALUES ("+mensaje.getIdUsuario()+", '"+mensaje.getMensaje()+"')";
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {}
		}	
		return mensaje;
	}

	//@Override
	public Vector<Mensaje> getMensajes() throws Exception {
		//Inicializamos el Vector de retorno.
		Vector<Mensaje> resultado = new Vector<Mensaje>();
		try {
			con = bddConexion();
			ps = con.prepareStatement("SELECT * FROM usuario_mensaje");
			rs = ps.executeQuery();
			while (rs.next()) {
				Mensaje mensaje = new Mensaje();
				mensaje.setIdMensaje(rs.getLong("idMensaje"));
				mensaje.setIdUsuario(rs.getLong("idUsuario"));
				mensaje.setMensaje(rs.getString("mensaje"));
				mensaje.setEstado(rs.getString("estado"));
				mensaje.setFecha(rs.getTimestamp("fecha"));
				resultado.add(mensaje);
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

	//@Override
	public Vector<Mensaje> getMensajes(Usuario usuario) throws Exception {
		Vector<Mensaje> resultado = new Vector<Mensaje>();
		try {
			con = bddConexion();
			ps = con.prepareStatement("SELECT * FROM usuario_mensaje WHERE idUsuario= " +
					"(SELECT idUsuario FROM usuario WHERE login='"+usuario.getLogin()+"')");
			rs = ps.executeQuery();
			while (rs.next()) {
				Mensaje mensaje = new Mensaje();
				mensaje.setIdMensaje(rs.getLong("idMensaje"));
				mensaje.setIdUsuario(rs.getLong("idUsuario"));
				mensaje.setMensaje(rs.getString("mensaje"));
				mensaje.setEstado(rs.getString("estado"));
				mensaje.setFecha(rs.getDate("fecha"));
				resultado.add(mensaje);
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

	//@Override
	public Vector<Mensaje> addMensajes(Usuario usuario, List<Mensaje> mensajes) throws Exception {
		// System.out.println("FUNCION addMensajes NO IMPLEMENTADA");
		return null;
	}
}