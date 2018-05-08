package es.induserco.opilion.datos.envase;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.ProductoMerma;
import es.induserco.opilion.datos.entidad.EntidadDAO;
import es.induserco.opilion.infraestructura.ServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class EnvaseDataService.
 */
public class EnvaseDataService implements IEnvaseDataService {

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.envase.IEnvaseDataService#addEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	public Boolean addEnvase (Envase entry) throws Exception {
		return (new EnvaseDAO()).addEnvase(entry);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.envase.IEnvaseDataService#getEnvases(es.induserco.opilion.data.entidades.Envase)
	 */
	public Vector<Envase> getEnvases(Envase entry)throws Exception {
		return (new EnvaseDAO()).getEnvases(entry);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.envase.IEnvaseDataService#getEnvases(java.lang.Long)
	 */
	public Vector getEnvases(Long idMaterial)throws Exception {
		return (new EnvaseDAO()).getEnvases(idMaterial);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.envase.IEnvaseDataService#updateEnvase(es.induserco.opilion.data.entidades.Envase, es.induserco.opilion.data.entidades.Envase)
	 */
	public Boolean updateEnvase(Envase entryFind, Envase entryUpdate)throws Exception {
		return (new EnvaseDAO()).updateEnvase(entryFind,entryUpdate);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.envase.IEnvaseDataService#loadEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	public Vector loadEnvase(Envase entry)  throws Exception {
		return (new EnvaseDAO()).loadEnvase(entry);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.envase.IEnvaseDataService#getMateriales()
	 */
	public Vector getMateriales() throws Exception {
		return (new EnvaseDAO()).getMateriales();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.datos.envase.IEnvaseDataService#deshabilitaEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	public Boolean deshabilitaEnvase(Envase entry)throws Exception {
		return (new EnvaseDAO()).deshabilitaEnvase(entry);
	
	}

	/*//@Override
	public long getIdEnvase(String codigoEntrada) throws Exception {
		return (new EnvaseDAO()).getIdEnvase(codigoEntrada);
	}

	//@Override
	public double getSaldoViejo(String codigoEntrada) throws Exception {
		return (new EnvaseDAO()).getSaldoViejo(codigoEntrada);
	}

	//@Override
	public double getStockEnvase(long idEnvase) throws Exception {
		return (new EnvaseDAO()).getStockEnvase(idEnvase);
	}

	//@Override
	public double getTeoricoViejo(String codigoEntrada) throws Exception {
		return (new EnvaseDAO()).getTeoricoViejo(codigoEntrada);
	}*/
}
