package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class AnalisisRegistro implements Serializable {
	
	private static final long serialVersionUID = 4294091077070963679L;
	private Long idAnalisis;
	private Long idEntrada;
	private Long  varIGL;
	private Long  varSPL;
	private Long  varDPL;
	private Long  varDAL;

	public AnalisisRegistro() {
		super();
	}

	public Long getIdAnalisis() {
		return idAnalisis;
	}

	public void setIdAnalisis(Long idAnalisis) {
		this.idAnalisis = idAnalisis;
	}

	public Long getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Long idEntrada) {
		this.idEntrada = idEntrada;
	}
	
	public Long getVarIGL() {
		return varIGL;
	}
	
	public void setVarIGL(Long varIGL) {
		this.varIGL = varIGL;
	}

	public Long getVarSPL() {
		return varSPL;
	}
	
	public void setVarSPL(Long varSPL) {
		this.varSPL = varSPL;
	}
	
	public Long getVarDPL() {
		return varDPL;
	}
	
	public void setVarDPL(Long varDPL) {
		this.varDPL = varDPL;
	}
	
	public Long getVarDAL() {
		return varDAL;
	}
	
	public void setVarDAL(Long varDAL) {
		this.varDAL = varDAL;
	}
	
	public Long getVarML() {
		return varML;
	}
	
	public void setVarML(Long varML) {
		this.varML = varML;
	}
	
	private Long  varML;	
}