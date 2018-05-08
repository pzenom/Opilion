package es.induserco.opilion.negocio;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Backup;
import es.induserco.opilion.datos.especial.IGestionesEspecialesDataService;
import es.induserco.opilion.infraestructura.ServiceLocator;

public class GestionesEspecialesDataHelper {

	public String getLOPDFactura() throws Exception {
		return((IGestionesEspecialesDataService)(new ServiceLocator()).getService("IGestionesEspecialesDataService")).getLOPDFactura();
	}

	public void registrarBackup(Backup backup) throws Exception {
		((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).registrarBackup(backup);
	}

	public Backup getUltimoBackupAuto() throws Exception{
		return((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).getUltimoBackupAuto();
	}

	public void registroBackupAuto() throws Exception {
		((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).registroBackupAuto();
	}

	public void exportarDatos(long idVehiculo, long idComercial) throws Exception {
		((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).exportarDatos(idVehiculo, idComercial);
	}

	public String getRegistroSanitario(int idEmpresa) throws Exception {
		return((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).getRegistroSanitario(idEmpresa);
	}
	
	public void importarAlbaranes(String MY_XML) throws Exception{
		((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).importarAlbaranes(MY_XML);
	}
	
	public void importarPedidos(String MY_XML) throws Exception{
		((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).importarPedidos(MY_XML);
	}
	
	public void importarClientes(String MY_XML) throws Exception{
		((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).importarClientes(MY_XML);
	}
	
	public boolean validarXML(String MY_XML, String MY_SCHEMA) throws Exception{
		return((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).validarXML(MY_XML, MY_SCHEMA);
	}
	
	public Vector<String> getFicherosXML() throws Exception{
		return((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).getFicherosXML();
	}

	public void importarDevoluciones(String my_xml) throws Exception{
		((IGestionesEspecialesDataService)(new ServiceLocator()).
				getService("IGestionesEspecialesDataService")).importarDevoluciones(my_xml);
	}
}