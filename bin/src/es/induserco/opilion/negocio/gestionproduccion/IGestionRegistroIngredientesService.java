package es.induserco.opilion.negocio.gestionproduccion;

import java.util.List;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGestionRegistroIngredientesService.
 */
public interface IGestionRegistroIngredientesService {

	/**
	 * Gets the registro ingredientes.
	 *
	 * @return the registro ingredientes
	 * @throws Exception the exception
	 */
	public Vector getRegistroIngredientes() throws Exception;
	
	/**
	 * Gets the registro ingredientes.
	 *
	 * @param listaingred the listaingred
	 * @return the registro ingredientes
	 * @throws Exception the exception
	 */
	public Vector getRegistroIngredientes(List listaingred) throws Exception;
	
	/**
	 * Gets the registro ingredientes.
	 *
	 * @param orden the orden
	 * @param idCategoria the id categoria
	 * @param idProducto the id producto
	 * @return the registro ingredientes
	 * @throws Exception the exception
	 */
	public Vector getRegistroIngredientes(String orden, long idCategoria, long idProducto) throws Exception;
	
	/**
	 * Gets the registro ingredientes.
	 *
	 * @param orden the orden
	 * @param idProducto the id producto
	 * @return the registro ingredientes
	 * @throws Exception the exception
	 */
	public Vector getRegistroIngredientes(String orden, long idProducto) throws Exception;
	
	/**
	 * Gets the registro ingredientes.
	 *
	 * @param idProducto the id producto
	 * @param filtro the filtro
	 * @return the registro ingredientes
	 * @throws Exception the exception
	 */
	public Vector getRegistroIngredientes(long idProducto, String filtro) throws Exception;
	
	/**
	 * Gets the info materia prima.
	 *
	 * @param idProducto the id producto
	 * @return the info materia prima
	 * @throws Exception the exception
	 */
	public Vector getInfoMateriaPrima(long idProducto) throws Exception;
	
	/**
	 * Adds the componentes envasado.
	 *
	 * @param listaelementos the listaelementos
	 * @param orden the orden
	 * @param tipo the tipo
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addComponentesEnvasado(List listaelementos, String orden,String tipo) throws Exception;	
	
	/**
	 * Adds the componentes proceso.
	 *
	 * @param listaelementos the listaelementos
	 * @param orden the orden
	 * @param tipo the tipo
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addComponentesProceso(List listaelementos, String orden,String tipo) throws Exception;
	
	/**
	 * Gets the ingredientes producto.
	 *
	 * @param idProducto the id producto
	 * @param filtro the filtro
	 * @return the ingredientes producto
	 * @throws Exception the exception
	 */
	public Vector getIngredientesProducto(Long idProducto, String filtro) throws Exception;
}
