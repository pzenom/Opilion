package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.datos.produccion.IGestionProduccionDataService;
import es.induserco.opilion.infraestructura.ServiceLocator;

public interface IGestionRegistroEnvasadoService {
	
	public String getFechaRegistro() throws Exception;
	public Boolean addOrdenEnvasado(RegistroEnvasado envasado)throws Exception;
	public Boolean addRegistroEnvasado(GestionProduccion gprod) throws Exception; //Deprecated
	public Boolean updateRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu) throws Exception;
	public Boolean deleteRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodd) throws Exception;
	public Vector getProductos() throws Exception;
	public Vector getEnvases() throws Exception;
	public Vector getOperarios() throws Exception;
	public Vector<Producto> getPresentacionProductos(boolean stockSuficiente) throws Exception;	
	public Vector<GestionProduccion> getRegistroEnvasados(String orden,long idProducto, int filtro) throws Exception;
	public GestionProduccion getEtiquetaEN(String codigoOrden) throws Exception ;
	public GestionProduccion getMaestroEN(String orden) throws Exception;
	public Vector getProcesosPendientes(GestionProduccion gprod, String tipo) throws Exception;
	public Vector delProcesoPendiente(RegistroEnvasado envasado) throws Exception;
	public GestionProduccion getInfoProcesosEnv(String orden, String filtro) throws Exception;
	public void actualizaCaducidadEnvasado(String proceso, String caducidad, String usuario) throws Exception;
}