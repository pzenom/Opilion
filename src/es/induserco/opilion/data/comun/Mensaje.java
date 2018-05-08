package es.induserco.opilion.data.comun;

import java.util.Date;

public class Mensaje {

	private Long idMensaje;
	private Long idUsuario;
	private String mensaje;
	private String estado;
	private Date fecha;
	
	public Mensaje(){
		this.idMensaje=null;
		this.idUsuario=null;
		this.mensaje="";
		this.estado="S";
		//this.fecha
	}
	
	public Long getIdMensaje() 	{ return idMensaje; }
	public Long getIdUsuario() 	{ return idUsuario; }
	public String getMensaje() 	{ return mensaje; }
	public String getEstado() 	{ return estado; }
	public Date getFecha() 		{ return fecha; }
	
	public void setIdMensaje(Long idMensaje) 	{ this.idMensaje = idMensaje; }
	public void setIdUsuario(Long idUsuario) 	{ this.idUsuario = idUsuario; }
	public void setMensaje(String mensaje) 		{ this.mensaje = mensaje; }
	public void setEstado(String estado) 		{ this.estado = estado; }
	public void setFecha(Date fecha) 			{ this.fecha = fecha; }	
	
}
