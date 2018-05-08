package es.induserco.opilion.negocio.gestionesespeciales;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Backup;

public interface IGestionesEspecialesService {
	
	String getLOPDFactura() throws Exception;
	void registrarBackup(Backup backup)throws Exception;
	Backup getUltimoBackupAuto() throws Exception;
	void registroBackupAuto() throws Exception;
	void exportarDatos(long idVehiculo, long idComercial) throws Exception;
	String getRegistroSanitario(int idEmpresa) throws Exception;
	void importarAlbaranes(String my_xml) throws Exception;
	void importarPedidos(String my_xml) throws Exception;
	void importarClientes(String my_xml) throws Exception;
	Vector<String> getFicherosXML() throws Exception;
	boolean validarXML(String my_xml, String my_schema) throws Exception;
	void importarDevoluciones(String my_xml) throws Exception;
}