package es.induserco.opilion.data.entidades;

public class Lanzadera {

	private long idLanzadera, idVehiculo, idComercial;
	private String usuarioResponsable, fecha, fechaFormateada, nombreReporte;
	
	public long getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getUsuarioResponsable() {
		return usuarioResponsable;
	}
	public void setUsuarioResponsable(String usuarioResponsable) {
		this.usuarioResponsable = usuarioResponsable;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	private long lanzaderaNumero, totalLanzaderas;
	
	public long getIdLanzadera() {
		return idLanzadera;
	}
	public void setIdLanzadera(long idLanzadera) {
		this.idLanzadera = idLanzadera;
	}
	public long getLanzaderaNumero() {
		return lanzaderaNumero;
	}
	public void setLanzaderaNumero(long lanzaderaNumero) {
		this.lanzaderaNumero = lanzaderaNumero;
	}
	public long getTotalLanzaderas() {
		return totalLanzaderas;
	}
	public void setTotalLanzaderas(long totalLanzaderas) {
		this.totalLanzaderas = totalLanzaderas;
	}
	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}
	public String getFechaFormateada() {
		return fechaFormateada;
	}
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	public String getNombreReporte() {
		return nombreReporte;
	}
	public void setIdComercial(long idComercial) {
		this.idComercial = idComercial;
	}
	public long getIdComercial() {
		return idComercial;
	}
}