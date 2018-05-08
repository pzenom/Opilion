package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.sql.Date;

public class RegistroCalidad implements Serializable {

	private static final long serialVersionUID = 1L;	
	private Long idAnalisis;
	private String codigoEntrada;
	private String varIG;
	private String varSP;
	private String varDP;
	private String varDA;
	private String varM;
	private double calidad;
	private Date fecha;
	
	public void setIdAnalisis(Long idAnalisis)			{ this.idAnalisis = idAnalisis; }
	public void setCodigoEntrada(String codigoEntrada)	{ this.codigoEntrada = codigoEntrada; }	
	public void setVarIG(String varIG) 					{ this.varIG = varIG; }
	public void setVarSP(String varSP) 					{ this.varSP = varSP; }
	public void setVarDP(String varDP) 					{ this.varDP = varDP; }
	public void setVarDA(String varDA)					{ this.varDA = varDA; }
	public void setVarM(String varM)					{ this.varM = varM; }
	public void setCalidad(double calidad) 				{ this.calidad = calidad; }
	public void setFecha(Date fecha)					{ this.fecha = fecha; }
	
	public Long getIdAnalisis() 				{ return idAnalisis; }
	public String getCodigoEntrada() 			{ return codigoEntrada; }
	public static long getSerialVersionUID() 	{ return serialVersionUID; }
	public String getVarIG()					{ return varIG; }
	public String getVarSP()					{ return varSP; }
	public String getVarDP()					{ return varDP; }
	public String getVarDA()					{ return varDA; }
	public String getVarM()						{ return varM; }
	public double getCalidad() 					{ return calidad; }
	public Date getFecha()						{ return fecha; }
}