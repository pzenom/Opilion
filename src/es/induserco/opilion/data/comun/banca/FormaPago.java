package es.induserco.opilion.data.comun.banca;

//@Embeddable
/**
 * The Class FormaPago.
 */
public class FormaPago {
	
	private Long idFormaPago;
	private String descripcion;
	
	public FormaPago() {
		super();
		this.idFormaPago = null;
		this.descripcion = null;
	}

	public FormaPago(Long idFormaPago, String descripcion) {
		super();
		this.idFormaPago = idFormaPago;
		this.descripcion = descripcion;
	}

	public Long getIdFormaPago() {
		return idFormaPago;
	}

	public void setIdFormaPago(Long idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}