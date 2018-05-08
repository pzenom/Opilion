package es.induserco.opilion.data.comun.banca;

import java.io.Serializable;

import es.induserco.opilion.data.entidades.Entidad;

public class DatoBancario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idFormaPago, idFormaPagoDesde, idEntidad, idDatoBancario, idBanco;
	private long diasFormaPago, diaPago;
	private boolean cuentaAsociada;
	private String descripcion, descripcionFormaPagoDesde, numCuenta, cuentaIban, cuentaBanco, cuentaOfici, cuentaContr, cuentaNumer;
	private Entidad entidad;

	public DatoBancario() {
	}

	public DatoBancario(Long idDatoBancario, Long idBanco, String numCuenta, Long idFormaPago,Entidad entidad) {
		super();
		this.idDatoBancario = idDatoBancario;
		this.idBanco = idBanco;
		this.numCuenta = numCuenta;
		this.idFormaPago = idFormaPago;
		this.entidad = entidad;
	}

	public Long getIdDatoBancario() { return idDatoBancario; }
	public String getNumCuenta() { return numCuenta;}	
	public Entidad getEntidad() { return entidad; }	
	public Long getIdFormaPago() {return idFormaPago;}
	public Long getIdBanco() {return idBanco;}	
	public void setIdDatoBancario(Long idDatoBancario) { this.idDatoBancario = idDatoBancario; }
	public void setNumCuenta(String numCuenta) { this.numCuenta = numCuenta; }
	public void setEntidad(Entidad Entidad) { this.entidad = Entidad; }
	public void setIdFormaPago(Long idFormaPago) { this.idFormaPago = idFormaPago;}
	public void setIdBanco(Long idBanco) { this.idBanco = idBanco;}

	public void setDiasFormaPago(long diasFormaPago) {
		this.diasFormaPago = diasFormaPago;
	}

	public long getDiasFormaPago() {
		return diasFormaPago;
	}

	public void setDiaPago(long diaPago) {
		this.diaPago = diaPago;
	}

	public long getDiaPago() {
		return diaPago;
	}

	public void setCuentaAsociada(boolean cuentaAsociada) {
		this.cuentaAsociada = cuentaAsociada;
	}

	public boolean isCuentaAsociada() {
		return cuentaAsociada;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setCuentaNumer(String cuentaNumer) {
		this.cuentaNumer = cuentaNumer;
	}

	public String getCuentaNumer() {
		return cuentaNumer;
	}

	public void setCuentaContr(String cuentaContr) {
		this.cuentaContr = cuentaContr;
	}

	public String getCuentaContr() {
		return cuentaContr;
	}

	public void setCuentaOfici(String cuentaOfici) {
		this.cuentaOfici = cuentaOfici;
	}

	public String getCuentaOfici() {
		return cuentaOfici;
	}

	public void setCuentaBanco(String cuentaBanco) {
		this.cuentaBanco = cuentaBanco;
	}

	public String getCuentaBanco() {
		return cuentaBanco;
	}

	public void setCuentaIban(String cuentaIban) {
		this.cuentaIban = cuentaIban;
	}

	public String getCuentaIban() {
		return cuentaIban;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdFormaPagoDesde(Long idFormaPagoDesde) {
		this.idFormaPagoDesde = idFormaPagoDesde;
	}

	public Long getIdFormaPagoDesde() {
		return idFormaPagoDesde;
	}

	public void setDescripcionFormaPagoDesde(String descripcionFormaPagoDesde) {
		this.descripcionFormaPagoDesde = descripcionFormaPagoDesde;
	}

	public String getDescripcionFormaPagoDesde() {
		return descripcionFormaPagoDesde;
	}
}