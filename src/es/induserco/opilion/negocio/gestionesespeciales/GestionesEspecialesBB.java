package es.induserco.opilion.negocio.gestionesespeciales;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Backup;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.GestionesEspecialesDataHelper;

public class GestionesEspecialesBB implements IGestionesEspecialesService{

	//@Override
	public String getLOPDFactura() throws Exception {
		return new GestionesEspecialesDataHelper().getLOPDFactura();
	}

	//@Override
	public void registrarBackup(Backup backup) throws Exception {
		new GestionesEspecialesDataHelper().registrarBackup(backup);
	}

	//@Override
	public Backup getUltimoBackupAuto() throws Exception {
		return new GestionesEspecialesDataHelper().getUltimoBackupAuto();
	}

	//@Override
	public String getRegistroSanitario(int idEmpresa) throws Exception {
		return new GestionesEspecialesDataHelper().getRegistroSanitario(idEmpresa);		
	}
	
	//@Override
	public void registroBackupAuto() throws Exception {
		new GestionesEspecialesDataHelper().registroBackupAuto();
	}

	//@Override
	public void exportarDatos(long idVehiculo, long idComercial) throws Exception {
		new GestionesEspecialesDataHelper().exportarDatos(idVehiculo, idComercial);
	}

	//@Override
	public Vector<String> getFicherosXML() throws Exception {
		return new GestionesEspecialesDataHelper().getFicherosXML();
	}

	//@Override
	public boolean validarXML(String my_xml, String my_schema) throws Exception {
		return new GestionesEspecialesDataHelper().validarXML(my_xml, my_schema);
	}

	//@Override
	public void importarClientes(String my_xml) throws Exception {
		new GestionesEspecialesDataHelper().importarClientes(my_xml);
	}

	//@Override
	public void importarPedidos(String my_xml) throws Exception {
		new GestionesEspecialesDataHelper().importarPedidos(my_xml);
	}

	//@Override
	public void importarAlbaranes(String my_xml) throws Exception {
		new GestionesEspecialesDataHelper().importarAlbaranes(my_xml);
	}

	//@Override
	public void importarDevoluciones(String my_xml) throws Exception {
		new GestionesEspecialesDataHelper().importarDevoluciones(my_xml);
	}
}