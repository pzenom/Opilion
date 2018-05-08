package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.Vector;
import es.induserco.opilion.data.comun.Rol;

public class Accion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long idAccion, idRol, idPadre;
	private String nombreAccion, idRelacionado, valor;
	private Vector<Rol> rolesPermitidos;
	private Vector<String> idsRelacionados, clasesRelacionadas;

	public void setRolesPermitidos(Vector<Rol> rolesPermitidos) {
		this.rolesPermitidos = rolesPermitidos;
	}

	public Vector<Rol> getRolesPermitidos() {
		return rolesPermitidos;
	}

	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setIdAccion(long idAccion) {
		this.idAccion = idAccion;
	}

	public long getIdAccion() {
		return idAccion;
	}

	public void setIdsRelacionados(Vector<String> idsRelacionados) {
		this.idsRelacionados = idsRelacionados;
	}

	public Vector<String> getIdsRelacionados() {
		return idsRelacionados;
	}

	public void setIdRelacionado(String idRelacionado) {
		this.idRelacionado = idRelacionado;
	}

	public String getIdRelacionado() {
		return idRelacionado;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public long getIdRol() {
		return idRol;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setIdPadre(long idPadre) {
		this.idPadre = idPadre;
	}

	public long getIdPadre() {
		return idPadre;
	}

	public void setClasesRelacionadas(Vector<String> clasesRelacionadas) {
		this.clasesRelacionadas = clasesRelacionadas;
	}

	public Vector<String> getClasesRelacionadas() {
		return clasesRelacionadas;
	}	
}