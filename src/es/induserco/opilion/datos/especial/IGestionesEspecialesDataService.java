package es.induserco.opilion.datos.especial;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Backup;

public interface IGestionesEspecialesDataService {
	String getLOPDFactura() throws Exception;
	void registrarBackup(Backup backup) throws Exception;
	Backup getUltimoBackupAuto() throws Exception;
	void registroBackupAuto() throws Exception;
	void exportarDatos(long idVehiculo, long idComercial) throws Exception;
	String getRegistroSanitario(long idEmpresa) throws Exception;
	void importarAlbaranes(String MY_XML) throws Exception;
	void importarPedidos(String MY_XML) throws Exception;
	void importarClientes(String MY_XML) throws Exception;
	boolean validarXML(String MY_XML, String MY_SCHEMA) throws Exception;
	Vector<String> getFicherosXML() throws Exception;
	void importarDevoluciones(String MY_XML) throws Exception;
}