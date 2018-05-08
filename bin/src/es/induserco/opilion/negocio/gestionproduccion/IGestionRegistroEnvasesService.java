package es.induserco.opilion.negocio.gestionproduccion;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGestionRegistroEnvasesService.
 */
public interface IGestionRegistroEnvasesService {

	/**
	 * Gets the registro envases.
	 *
	 * @param idEnvase the id envase
	 * @param filtro the filtro
	 * @return the registro envases
	 * @throws Exception the exception
	 */
	public Vector getRegistroEnvases(long idEnvase, String filtro) throws Exception;
	
	/**
	 * Gets the registro envases.
	 *
	 * @param listenvases the listenvases
	 * @return the registro envases
	 * @throws Exception the exception
	 */
	public Vector getRegistroEnvases(List listenvases) throws Exception;	
	
	/**
	 * Adds the tmp registro ingredientes envases.
	 *
	 * @param gprod the gprod
	 * @param gpro the gpro
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addTmpRegistroIngredientesEnvases(GestionProduccion gprod,GestionProduccion gpro) throws Exception;
	
	/**
	 * Adds the tmp registro ingredientes envases.
	 *
	 * @param mapaCantidades the mapa cantidades
	 * @param orden the orden
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception;	
	
	/**
	 * Gets the tmp registro ingredientes envases.
	 *
	 * @param orden the orden
	 * @return the tmp registro ingredientes envases
	 * @throws Exception the exception
	 */
	public Vector getTmpRegistroIngredientesEnvases(String orden) throws Exception;
	
	/**
	 * Update tmp registro ingredientes envases.
	 *
	 * @param mapaCantidades the mapa cantidades
	 * @param orden the orden
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception;
	
	/**
	 * Update tmp registro mermas envases.
	 *
	 * @param mapaCantidades the mapa cantidades
	 * @param orden the orden
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateTmpRegistroMermasEnvases(Map mapaCantidades,String orden) throws Exception;	
	
	/**
	 * Gets the tmp registro ingredientes envases.
	 *
	 * @return the tmp registro ingredientes envases
	 * @throws Exception the exception
	 */
	public Vector getTmpRegistroIngredientesEnvases() throws Exception;
	
	/**
	 * Generar numero lote.
	 *
	 * @param codigo the codigo
	 * @return the string
	 * @throws Exception the exception
	 */
	public String generarNumeroLote(String codigo) throws Exception;
	
	/**
	 * Gets the sub registros entrada envasado.
	 *
	 * @param orden the orden
	 * @return the sub registros entrada envasado
	 * @throws Exception the exception
	 */
	public Vector getSubRegistrosEntradaEnvasado(String orden) throws Exception;
	
	/**
	 * Inserta cantidades envases.
	 *
	 * @param mapaCantidades the mapa cantidades
	 * @param orden the orden
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean insertaCantidadesEnvases(Map mapaCantidades, String orden) throws Exception;
	
	/**
	 * Gets the envases producto.
	 *
	 * @param idProducto the id producto
	 * @param filtro the filtro
	 * @return the envases producto
	 * @throws Exception the exception
	 */
	public Vector getEnvasesProducto(Long idProducto, String filtro) throws Exception;
}

