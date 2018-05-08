package es.induserco.opilion.presentacion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Backup;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionesespeciales.IGestionesEspecialesService;

public class GestionesEspecialesHelper {

	public String getLOPDFactura() throws Exception{
		return ((IGestionesEspecialesService)(new ServiceLocator()).getService("IGestionesEspecialesService")).getLOPDFactura();
	}

	public void registrarBackup(Backup backup) throws Exception{
		((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).registrarBackup(backup);
	}

	public Backup getUltimoBackupAuto() throws Exception{
		return ((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).getUltimoBackupAuto();
	}

	public void registroBackupAuto() throws Exception{
		((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).registroBackupAuto();
	}

	public String getRegistroSanitario(int idEmpresa)throws Exception {
		return ((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).getRegistroSanitario(idEmpresa);
	}

	public void exportarDatos(long idVehiculo, long idComercial) throws Exception {
		((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).exportarDatos(idVehiculo, idComercial);
	}

	public Vector<String> getFicherosXML() throws Exception {
		return ((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).getFicherosXML();
	}

	public boolean validarXML(String my_xml, String my_schema) throws Exception {
		return ((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).validarXML(my_xml, my_schema);
	}

	public void importarClientes(String my_xml) throws Exception {
		((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).importarClientes(my_xml);
	}

	public void importarPedidos(String my_xml) throws Exception {
		((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).importarPedidos(my_xml);
	}

	public void importarAlbaranes(String my_xml) throws Exception {
		((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).importarAlbaranes(my_xml);
	}

	public void importarDevoluciones(String my_xml) throws Exception {
		((IGestionesEspecialesService)(new ServiceLocator()).
				getService("IGestionesEspecialesService")).importarDevoluciones(my_xml);
	}
}