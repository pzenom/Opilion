package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class ConfiguracionDB implements Serializable{

	private static final long serialVersionUID = 1L;
	private String host;
	private String db;
	private String puerto;
	private String filePath;
	private String rutaProceso;
	private String rutaBackups;
	private String user;
	private String pass;
	
	public ConfiguracionDB() {
		super();
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getDb() {
		return db;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setRutaProceso(String rutaProceso) {
		this.rutaProceso = rutaProceso;
	}

	public String getRutaProceso() {
		return rutaProceso;
	}

	public void setRutaBackups(String rutaBackups) {
		this.rutaBackups = rutaBackups;
	}

	public String getRutaBackups() {
		return rutaBackups;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}
}