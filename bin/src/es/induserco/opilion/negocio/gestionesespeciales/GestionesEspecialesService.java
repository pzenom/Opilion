package es.induserco.opilion.negocio.gestionesespeciales;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Backup;

public class GestionesEspecialesService implements IGestionesEspecialesService{

	//@Override
	public String getLOPDFactura() throws Exception {
		return new GestionesEspecialesBB().getLOPDFactura();
	}

	//@Override
	public void registrarBackup(Backup backup) throws Exception {
		new GestionesEspecialesBB().registrarBackup(backup);
	}

	//@Override
	public Backup getUltimoBackupAuto() throws Exception {
		return new GestionesEspecialesBB().getUltimoBackupAuto();
	}

	//@Override
	public void registroBackupAuto() throws Exception {
		new GestionesEspecialesBB().registroBackupAuto();
	}
	
	//@Override
	public void exportarDatos(long idVehiculo, long idComercial) throws Exception {
		new GestionesEspecialesBB().exportarDatos(idVehiculo, idComercial);
	}

	//@Override
	public String getRegistroSanitario(int idEmpresa) throws Exception {
		return new GestionesEspecialesBB().getRegistroSanitario(idEmpresa);
	}

	//@Override
	public Vector<String> getFicherosXML() throws Exception {
		return new GestionesEspecialesBB().getFicherosXML();
	}

	//@Override
	public void importarAlbaranes(String my_xml) throws Exception {
		new GestionesEspecialesBB().importarAlbaranes(my_xml);
	}

	//@Override
	public void importarClientes(String my_xml) throws Exception {
		new GestionesEspecialesBB().importarClientes(my_xml);
	}

	//@Override
	public void importarPedidos(String my_xml) throws Exception {
		new GestionesEspecialesBB().importarPedidos(my_xml);
	}

	//@Override
	public boolean validarXML(String my_xml, String my_schema) throws Exception {
		return new GestionesEspecialesBB().validarXML(my_xml, my_schema);
	}

	//@Override
	public void importarDevoluciones(String my_xml) throws Exception {
		new GestionesEspecialesBB().importarDevoluciones(my_xml);
	}
}