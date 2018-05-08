package es.induserco.opilion.negocio.gestionenvases;

import java.util.Vector;

import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.ProductoMerma;
import es.induserco.opilion.negocio.EntidadDataHelper;
import es.induserco.opilion.negocio.EnvaseDataHelper;
import es.induserco.opilion.negocio.ProductoDataHelper;
import es.induserco.opilion.negocio.RegistroEntradaDataHelper;
import es.induserco.opilion.negocio.gestionproductos.IGestionProductosService;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionEnvasesBB.
 */
public class GestionEnvasesBB implements IGestionEnvasesService{

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#addEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Boolean addEnvase(Envase entry) throws Exception {
		return new EnvaseDataHelper().addEnvase(entry);		
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#getEnvases(es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Vector<Envase> getEnvases(Envase entry) throws Exception
	{	
		//Recuperamos la lista de entidades
		EnvaseDataHelper edh = new EnvaseDataHelper();
		Vector<Envase> envases = edh.getEnvases(entry);
		
		//si hay que aplicar alguna regla del negocio aqui!!!
		return envases;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#getEnvases(java.lang.Long)
	 */
	//@Override
	public Vector getEnvases(Long idMaterial) throws Exception
	{	
		//Recuperamos la lista de entidades
		EnvaseDataHelper edh = new EnvaseDataHelper();
		Vector envases = edh.getEnvases(idMaterial);
		
		//si hay que aplicar alguna regla del negocio aqui!!!
		return envases;
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#updateEnvase(es.induserco.opilion.data.entidades.Envase, es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Boolean updateEnvase(Envase envaseFind, Envase envaseUpdate) throws Exception {
		return new EnvaseDataHelper().updateEnvase(envaseFind, envaseUpdate);		
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#loadEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Vector loadEnvase(Envase entry) throws Exception {
		return new EnvaseDataHelper().loadEnvase(entry);		
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#getMateriales()
	 */
	//@Override
	public Vector getMateriales() throws Exception
	{	
		//Recuperamos la lista de entidades
		EnvaseDataHelper edh = new EnvaseDataHelper();
		Vector materiales = edh.getMateriales();
		
		//si hay que aplicar alguna regla del negocio aqui!!!
		return materiales;
	}	
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#deshabilitaEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Boolean deshabilitaEnvase(Envase entry) throws Exception {
		return new EnvaseDataHelper().deshabilitaEnvase(entry);
	}
	
	/*
	//@Override
	public long getIdEnvase(String codigoEntrada) throws Exception {
		return new EnvaseDataHelper().getIdEnvase(codigoEntrada);
	}
	//@Override
	public double getSaldoViejo(String codigoEntrada) throws Exception{
		return new EnvaseDataHelper().getSaldoViejo(codigoEntrada);
	}
	//@Override
	public double getStockEnvase(long idEnvase) throws Exception {
		return new EnvaseDataHelper().getStockEnvase(idEnvase);
	}
	//@Override
	public double getTeoricoViejo(String codigoEntrada) throws Exception {
		return new EnvaseDataHelper().getTeoricoViejo(codigoEntrada);
	}*/
}
