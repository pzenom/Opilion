package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.negocio.GestionProduccionDataHelper;

public class GestionRegistroEnvasadoBB implements IGestionRegistroEnvasadoService{

	public Boolean addOrdenEnvasado(RegistroEnvasado envasado) throws Exception {
		return new GestionProduccionDataHelper().addOrdenEnvasado(envasado);
	}	

	public Boolean addRegistroEnvasado(GestionProduccion gprod) throws Exception {
		return new GestionProduccionDataHelper().addRegistroEnvasado(gprod);	
	}	

	public Boolean updateRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu) throws Exception {
		return new GestionProduccionDataHelper().updateRegistroEnvasado(gprodf,gprodu);
	}
	
	public Boolean deleteRegistroEnvasado(GestionProduccion gprodf,
			GestionProduccion gprodd) throws Exception {
		return new GestionProduccionDataHelper().deleteRegistroEnvasado(gprodf,gprodd);
	}

	public Vector getProductos() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector producto = edh.getProductos();
		return producto;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasadoService#getEnvases()
	 */
	//@Override
	public Vector getEnvases() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector envases = edh.getEnvases();
		return envases;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasadoService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionProduccionDataHelper().getFechaRegistro();
	}
	
	//@Override
	public Vector<GestionProduccion> getRegistroEnvasados(String orden, long idProducto, int filtro) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector<GestionProduccion> entradas = edh.getRegistroEnvasados(orden, idProducto, filtro);
		return entradas;
	}

	//@Override
	public Vector getOperarios() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector operarios = edh.getOperarios();
		return operarios;
	}

	//@Override
	public Vector getPresentacionProductos(boolean stockSuficiente) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector productos = edh.getPresentacionProductos(stockSuficiente);
		return productos;
	}

	//@Override
	public GestionProduccion getEtiquetaEN(String codigoOrden) throws Exception{
		return new GestionProduccionDataHelper().getEtiquetaEN(codigoOrden);
	}
	
	//@Override
	public GestionProduccion getMaestroEN(String orden) throws Exception {
		return new GestionProduccionDataHelper().getMaestroEN(orden);
	}

	//@Override
	public Vector<GestionProduccion> getProcesosPendientes(GestionProduccion gprod, String tipo) throws Exception {
		return new GestionProduccionDataHelper().getProcesosPendientes(gprod, tipo);
	}

	//@Override
	public Vector delProcesoPendiente(RegistroEnvasado envasado) throws Exception {
		//1. eliminamos el proceso devolviendo el stock
		new GestionProduccionDataHelper().delProcesoPendiente(envasado);
		//2. devolvemos los procesos pendientes actualizados
		return new GestionProduccionDataHelper().getProcesosPendientes(null, "E");		
	}

	//@Override
	public GestionProduccion getInfoProcesosEnv(String orden, String filtro) throws Exception {
		return new GestionProduccionDataHelper().getInfoProcesosEnv(orden, filtro);
	}

	public void actualizaCaducidadEnvasado(String proceso, String caducidad, String usuario) throws Exception {
		new GestionProduccionDataHelper().actualizaCaducidadEnvasado(proceso, caducidad, usuario);
	}
}