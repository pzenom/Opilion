package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class CuotaFactura implements Serializable{

	private static final long serialVersionUID = 6565329439400863272L;
	private String codigoFactura, codigoNuevaFactura, observaciones, fecha, estado;
	private long numeroCuota, idEstado;
	private double importe, porcentaje;
	
	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}
	public String getCodigoFactura() {
		return codigoFactura;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado() {
		return estado;
	}
	public void setNumeroCuota(long numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public long getNumeroCuota() {
		return numeroCuota;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public double getImporte() {
		return importe;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFecha() {
		return fecha;
	}
	public void setCodigoNuevaFactura(String codigoNuevaFactura) {
		this.codigoNuevaFactura = codigoNuevaFactura;
	}
	public String getCodigoNuevaFactura() {
		return codigoNuevaFactura;
	}
	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}
	public long getIdEstado() {
		return idEstado;
	}
}