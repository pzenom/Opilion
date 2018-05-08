package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Vector;
import es.induserco.opilion.data.comun.Rol;

public class GestionRoles implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long numeroRoles;
	private Vector<Rol> roles;
	private Vector<Accion> acciones;

	public void setAcciones(Vector<Accion> acciones) { this.acciones = acciones; }
	public void setRoles(Vector<Rol> roles) { this.roles = roles; }

	public Vector<Accion> getAcciones() { return acciones; }
	public Vector<Rol> getRoles() { return roles; }
	public void setNumeroRoles(long numeroRoles) {
		this.numeroRoles = numeroRoles;
	}
	public long getNumeroRoles() {
		return numeroRoles;
	}
}