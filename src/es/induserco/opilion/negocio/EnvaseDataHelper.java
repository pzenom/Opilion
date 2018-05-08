package es.induserco.opilion.negocio;

import java.util.Vector;

import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.datos.envase.IEnvaseDataService;

// TODO: Auto-generated Javadoc
/**
 * The Class EnvaseDataHelper.
 */
public class EnvaseDataHelper {
	
	/**
	 * Adds the envase.
	 *
	 * @param entry the entry
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean addEnvase (Envase entry) throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).addEnvase(entry);		 
	}
	
	/**
	 * Gets the envases.
	 *
	 * @param entry the entry
	 * @return the envases
	 * @throws Exception the exception
	 */
	public Vector<Envase> getEnvases(Envase entry)throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).getEnvases(entry);
	}

	/**
	 * Gets the envases.
	 *
	 * @param idMaterial the id material
	 * @return the envases
	 * @throws Exception the exception
	 */
	public Vector getEnvases(Long idMaterial)throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).getEnvases(idMaterial);
	}

	/**
	 * Update envase.
	 *
	 * @param entryFind the entry find
	 * @param entryUpdate the entry update
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean updateEnvase(Envase entryFind, Envase entryUpdate)  throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).updateEnvase(entryFind, entryUpdate);
	}

	/**
	 * Load envase.
	 *
	 * @param entry the entry
	 * @return the vector
	 * @throws Exception the exception
	 */
	public Vector loadEnvase(Envase entry)  throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).loadEnvase(entry);
	}

	/**
	 * Gets the materiales.
	 *
	 * @return the materiales
	 * @throws Exception the exception
	 */
	public Vector getMateriales() throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).getMateriales();
	}

	/**
	 * Deshabilita envase.
	 *
	 * @param entry the entry
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean deshabilitaEnvase(Envase entry)throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).deshabilitaEnvase(entry);
	}

	/*public long getIdEnvase(String codigoEntrada) throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).getIdEnvase(codigoEntrada);
	}
	
	public double getSaldoViejo(String codigoEntrada) throws Exception{
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).getSaldoViejo(codigoEntrada);
	}

	public double getStockEnvase(long idEnvase) throws Exception{
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).getStockEnvase(idEnvase);
	}

	public double getTeoricoViejo(String codigoEntrada) throws Exception {
		return((IEnvaseDataService)(new ServiceLocator()).getService("IEnvaseDataService")).getTeoricoViejo(codigoEntrada);
	}*/
}
