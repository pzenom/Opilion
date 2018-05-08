package es.induserco.opilion.data.comun.contacto;

public class Email {

	private Long idEmail;
	private String direccion;
	private String tipo;
	private String notas;

	public Email() {
		super();
		this.direccion = null;
		this.tipo = null;
	}

	public Email(Long idEmail, String direccion, String tipo) {
		super();
		this.idEmail = idEmail;
		this.direccion = direccion;
		this.tipo = tipo;
	}

	public Long getIdEmail() 					{ return idEmail; }
	public String getTipo() 					{ return tipo; }
	public String getDireccion()				{ return direccion; }
	public String getNotas()					{ return notas; }
	
	public void setIdEmail(Long idEmail) 		{ this.idEmail = idEmail; }
	public void setTipo(String tipo) 			{ this.tipo = tipo; }
	public void setDireccion(String direccion)	{ this.direccion = direccion; }
	public void setNotas(String notas) 			{ this.notas = notas; }
}