package es.induserco.opilion.data.comun.contacto;

import es.induserco.opilion.data.comun.contacto.TipoTelefono;

public class Telefono {

	private Long idTelefono;
	private String numeroTelefono;
	private TipoTelefono tipo;
	private String tipoTfno;
	private String notas;

	public Telefono() {
		super();
		this.numeroTelefono = null;
		this.tipo = null;
	}

	public Telefono(Long idTelefono, String numero, TipoTelefono tipo, String tipoTelefono) {
		super();
		this.numeroTelefono = numero;
		this.tipo = tipo;
		this.tipoTfno = tipoTelefono;
	}

	public String getNumero() 					{ return numeroTelefono; }
	public TipoTelefono getTipo() 				{ return tipo; }
	public String getTipoTfno() 				{ return tipoTfno; }
	public Long getIdTelefono()					{ return idTelefono; }
	public String getNotas() 					{ return notas; }
	
	public void setNumero(String numero) 		{ this.numeroTelefono = numero; }
	public void setTipo(TipoTelefono tipo) 		{ this.tipo = tipo; }
	public void setTipoTfno(String tipoTfno) 	{ this.tipoTfno = tipoTfno; }
	public void setIdTelefono(Long idTelefono)	{ this.idTelefono = idTelefono; }
	public void setNotas(String notas) 			{ this.notas = notas; }
}