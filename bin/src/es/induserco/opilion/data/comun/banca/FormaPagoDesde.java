package es.induserco.opilion.data.comun.banca;

//Ej: Pagar� a 15 d�as DESDE FACTURACI�N
public class FormaPagoDesde {
	
	private Long idFormaPagoDesde;
	private String descripcion;
	
	public FormaPagoDesde() {
		super();
		this.idFormaPagoDesde = null;
		this.descripcion = null;
	}

	public FormaPagoDesde(Long idFormaPago, String descripcion) {
		super();
		this.idFormaPagoDesde = idFormaPago;
		this.descripcion = descripcion;
	}

	public Long getIdFormaPagoDesde() {
		return idFormaPagoDesde;
	}

	public void setIdFormaPagoDesde(Long idFormaPagoDesde) {
		this.idFormaPagoDesde = idFormaPagoDesde;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}