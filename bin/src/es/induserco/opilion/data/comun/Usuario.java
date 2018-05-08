package es.induserco.opilion.data.comun;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Usuario {
	
	private Long idUsuario;
	private String login;
	private String password;
	private String nuevoLogin;
	private long tiempo, idRolPredeterminado;
	private Vector<Rol> roles = new Vector<Rol>();
	private List mensajes = new ArrayList<Mensaje>();

	public Long getIdUsuario() 					{ return idUsuario; }
	public String getLogin()					{ return login; }
	public String getPassword() 				{ return password; }
	public String getNuevoLogin() 				{ return nuevoLogin; }
	public Vector<Rol> getRoles() 				{ return roles; }
	public long getTiempo() 					{ return tiempo; }
	public List getMensajes()					{ return mensajes;}
	
	public void setPassword(String password) 	{ this.password = password; }
	public void setNuevoLogin(String nuevoLogin) { this.nuevoLogin = nuevoLogin; }
	public void setLogin(String login) 			{ this.login = login; }
	public void setIdUsuario(Long idUsuario) 	{ this.idUsuario = idUsuario; }
	public void setRoles(Vector<Rol> roles) 	{ this.roles = roles; }
	public void setTiempo(long tiempo) 			{ this.tiempo = tiempo; }
	public void setMensajes(List mensajes)		{ this.mensajes = mensajes; }
	
	public void setIdRolPredeterminado(long idRolPredeterminado) {
		this.idRolPredeterminado = idRolPredeterminado;
	}
	public long getIdRolPredeterminado() {
		return idRolPredeterminado;
	}
}