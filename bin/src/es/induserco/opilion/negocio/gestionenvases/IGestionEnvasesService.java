package es.induserco.opilion.negocio.gestionenvases;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Envase;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGestionEnvasesService.
 */
public interface IGestionEnvasesService {

	/**
	 * Adds the envase.
	 *
	 * @param entry the entry
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addEnvase(Envase entry) throws Exception;
	
	/**
	 * Gets the envases.
	 *
	 * @param entry the entry
	 * @return the envases
	 * @throws Exception the exception
	 */
	public Vector<Envase> getEnvases(Envase entry) throws Exception;
	
	/**
	 * Gets the envases.
	 *
	 * @param idMaterial the id material
	 * @return the envases
	 * @throws Exception the exception
	 */
	public Vector getEnvases(Long idMaterial) throws Exception;	
	
	/**
	 * Update envase.
	 *
	 * @param envaseFind the envase find
	 * @param envaseUpdate the envase update
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateEnvase(Envase envaseFind, Envase envaseUpdate)throws Exception;
	
	/**
	 * Load envase.
	 *
	 * @param entry the entry
	 * @return the vector
	 * @throws Exception the exception
	 */
	public Vector loadEnvase(Envase entry) throws Exception;
	
	/**
	 * Gets the materiales.
	 *
	 * @return the materiales
	 * @throws Exception the exception
	 */
	public Vector getMateriales() throws Exception;
	
	/**
	 * Deshabilita envase.
	 *
	 * @param entry the entry
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean deshabilitaEnvase(Envase entry) throws Exception;
	/*public double getStockEnvase(long idEnvase) throws Exception;
	public long getIdEnvase(String codigoEntrada) throws Exception;
	public double getSaldoViejo(String codigoEntrada) throws Exception;
	public double getTeoricoViejo(String codigoEntrada) throws Exception;*/
}
