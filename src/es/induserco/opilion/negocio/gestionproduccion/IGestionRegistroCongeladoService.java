package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Ubicacion;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGestionRegistroCongeladoService.
 */
public interface IGestionRegistroCongeladoService {
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 * @throws Exception the exception
	 */
	public String getFechaRegistro() throws Exception;
	
	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 * @throws Exception the exception
	 */
	public Vector getProductos() throws Exception;
	
	/**
	 * Gets the registro congelados.
	 *
	 * @param orden the orden
	 * @param fecha the fecha
	 * @param idProducto the id producto
	 * @return the registro congelados
	 * @throws Exception the exception
	 */
	public Vector<GestionProduccion> getRegistroCongelados(String orden,String fecha,Long idProducto) throws Exception;
	
	/**
	 * Gets the registro entrada congelado.
	 *
	 * @param orden the orden
	 * @param idProducto the id producto
	 * @return the registro entrada congelado
	 * @throws Exception the exception
	 */
	public Vector getRegistroEntradaCongelado(String orden,long idProducto) throws Exception;
	//public Boolean addRegistroProceso(GestionProduccion gprod, String proceso)throws Exception;

	public void guardarUbicacionesCongelado(Vector<Ubicacion> ubicaciones, String orden, String proceso) throws Exception;
}