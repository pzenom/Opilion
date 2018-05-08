package es.induserco.opilion.datos.especial;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Backup;

public class GestionesEspecialesDataService implements IGestionesEspecialesDataService {

	//@Override
	public String getLOPDFactura() throws Exception {
		return (new GestionesEspecialesDAO()).getLOPDFactura();
	}

	//@Override
	public void registrarBackup(Backup backup) throws Exception {
		new GestionesEspecialesDAO().registrarBackup(backup);
	}

	//@Override
	public Backup getUltimoBackupAuto() throws Exception {
		return (new GestionesEspecialesDAO()).getUltimoBackupAuto();
	}

	//@Override
	public void registroBackupAuto() throws Exception {
		new GestionesEspecialesDAO().registroBackupAuto();
	}
	
	//@Override
	public void exportarDatos(long idVehiculo, long idComercial) throws Exception {
		new GestionesEspecialesDAO().exportarDatos(idVehiculo, idComercial);
	}

	//@Override
	public String getRegistroSanitario(long idEmpresa) throws Exception {
		return (new GestionesEspecialesDAO()).getRegistroSanitario(idEmpresa);
	}

	//@Override
	public void importarAlbaranes(String MY_XML) throws Exception{
		new GestionesEspecialesDAO().importarAlbaranes(MY_XML);
	}
	
	//@Override
	public void importarPedidos(String MY_XML) throws Exception{
		new GestionesEspecialesDAO().importarPedidos(MY_XML);
	}
	
	//@Override
	public void importarClientes(String MY_XML) throws Exception{
		new GestionesEspecialesDAO().importarClientes(MY_XML);
	}
	
	//@Override
	public boolean validarXML(String MY_XML, String MY_SCHEMA) throws Exception{
		return (new GestionesEspecialesDAO()).validarXML(MY_XML, MY_SCHEMA);
	}
	
	//@Override
	public Vector<String> getFicherosXML() throws Exception{
		return (new GestionesEspecialesDAO()).getFicherosXML();
	}

	//@Override
	public void importarDevoluciones(String MY_XML) throws Exception {
		new GestionesEspecialesDAO().importarDevoluciones(MY_XML);
	}
}