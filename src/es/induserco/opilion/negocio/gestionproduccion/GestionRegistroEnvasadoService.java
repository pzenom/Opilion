package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.datos.produccion.IGestionProduccionDataService;
import es.induserco.opilion.infraestructura.ServiceLocator;

public class GestionRegistroEnvasadoService implements IGestionRegistroEnvasadoService {

	//@Override
	public Boolean addOrdenEnvasado(RegistroEnvasado envasado) throws Exception {
		return new GestionRegistroEnvasadoBB().addOrdenEnvasado(envasado);
	}

	//@Override //Deprecated
	public Boolean addRegistroEnvasado(GestionProduccion gprod) throws Exception {
		return new GestionRegistroEnvasadoBB().addRegistroEnvasado(gprod);
	}

	//@Override
	public Vector getEnvases() throws Exception {
		return new GestionRegistroEnvasadoBB().getEnvases();
	}

	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionRegistroEnvasadoBB().getFechaRegistro();
	}

	//@Override
	public Vector getProductos() throws Exception {
		return new GestionRegistroEnvasadoBB().getProductos();
	}

	//@Override
	public Vector<GestionProduccion> getRegistroEnvasados(String orden, long idProducto, int filtro) throws Exception {
		return new GestionRegistroEnvasadoBB().getRegistroEnvasados(orden,idProducto, filtro);
	}
	
	//@Override
	public Boolean updateRegistroEnvasado(GestionProduccion gprodf,
			GestionProduccion gprodu) throws Exception {
		return new GestionRegistroEnvasadoBB().updateRegistroEnvasado(gprodf,gprodu);
	}

	//@Override
	public Boolean deleteRegistroEnvasado(GestionProduccion gprodf,
			GestionProduccion gprodd) throws Exception {
		return new GestionRegistroEnvasadoBB().deleteRegistroEnvasado(gprodf,gprodd);
	}

	//@Override
	public Vector getOperarios() throws Exception {
		return new GestionRegistroEnvasadoBB().getOperarios();
	}

	//@Override
	public Vector getPresentacionProductos(boolean stockSuficiente) throws Exception {
		return new GestionRegistroEnvasadoBB().getPresentacionProductos(stockSuficiente);
	}	

	//@Override
	public GestionProduccion getEtiquetaEN(String codigoOrden) throws Exception{
		return new GestionRegistroEnvasadoBB().getEtiquetaEN(codigoOrden);
	}

	//@Override
	public GestionProduccion getMaestroEN(String orden) throws Exception {
		return new GestionRegistroEnvasadoBB().getMaestroEN(orden);
	}

	//@Override
	public Vector<GestionProduccion> getProcesosPendientes(GestionProduccion gprod, String tipo) throws Exception {
		return new GestionRegistroEnvasadoBB().getProcesosPendientes(gprod, tipo);
	}

	//@Override
	public Vector delProcesoPendiente(RegistroEnvasado envasado) throws Exception {
		return new GestionRegistroEnvasadoBB().delProcesoPendiente(envasado);
	}

	//@Override
	public GestionProduccion getInfoProcesosEnv(String orden, String filtro) throws Exception {
		return new GestionRegistroEnvasadoBB().getInfoProcesosEnv(orden, filtro);
	}
	
	public void actualizaCaducidadEnvasado(String proceso, String caducidad, String usuario) throws Exception{
		new GestionRegistroEnvasadoBB().actualizaCaducidadEnvasado(proceso, caducidad, usuario);
	}
}