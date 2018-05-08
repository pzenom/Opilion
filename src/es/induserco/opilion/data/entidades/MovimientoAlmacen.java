package es.induserco.opilion.data.entidades;

public class MovimientoAlmacen {

	private int cantidad;
	private String descripcionHueco, fecha, responsable, tipo;
	private String origen, destino, causa;
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	public void setDescripcionHueco(String descripcionHueco) {
		this.descripcionHueco = descripcionHueco;
	}
	public String getDescripcionHueco() {
		return descripcionHueco;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	public String getCausa() {
		return causa;
	}
}