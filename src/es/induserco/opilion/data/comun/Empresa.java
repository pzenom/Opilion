package es.induserco.opilion.data.comun;

public class Empresa {
	
	private long idEmpresa;
	private String nombre, registroSanitario, lopdFactura;
	
	public void setLopdFactura(String lopdFactura) {
		this.lopdFactura = lopdFactura;
	}
	public String getLopdFactura() {
		return lopdFactura;
	}
	public void setRegistroSanitario(String registroSanitario) {
		this.registroSanitario = registroSanitario;
	}
	public String getRegistroSanitario() {
		return registroSanitario;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public long getIdEmpresa() {
		return idEmpresa;
	}
}