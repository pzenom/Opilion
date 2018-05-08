package es.induserco.opilion.datos.envase;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.infraestructura.ServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * The Interface IEnvaseDataService.
 */
public interface IEnvaseDataService {

	/**
	 * Adds the envase.
	 *
	 * @param entry the entry
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean addEnvase (Envase entry) throws Exception;
	
	/**
	 * Gets the envases.
	 *
	 * @param entry the entry
	 * @return the envases
	 * @throws Exception the exception
	 */
	Vector<Envase> getEnvases(Envase entry)throws Exception;
	
	/**
	 * Gets the envases.
	 *
	 * @param idMaterial the id material
	 * @return the envases
	 * @throws Exception the exception
	 */
	Vector getEnvases(Long idMaterial)throws Exception;	
	
	/**
	 * Update envase.
	 *
	 * @param entryFind the entry find
	 * @param entryUpdate the entry update
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean updateEnvase(Envase entryFind, Envase entryUpdate) throws Exception;
	
	/**
	 * Load envase.
	 *
	 * @param entry the entry
	 * @return the vector
	 * @throws Exception the exception
	 */
	Vector loadEnvase(Envase entry)  throws Exception;
	
	/**
	 * Gets the materiales.
	 *
	 * @return the materiales
	 * @throws Exception the exception
	 */
	Vector getMateriales() throws Exception;
	
	/**
	 * Deshabilita envase.
	 *
	 * @param entry the entry
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean deshabilitaEnvase(Envase entry)throws Exception;
	/*long getIdEnvase(String codigoEntrada) throws Exception;
	double getSaldoViejo(String codigoEntrada) throws Exception;
	double getStockEnvase(long idEnvase) throws Exception;
	double getTeoricoViejo(String codigoEntrada)throws Exception;*/
}
