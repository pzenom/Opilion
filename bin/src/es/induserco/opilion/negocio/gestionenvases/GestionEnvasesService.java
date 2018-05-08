package es.induserco.opilion.negocio.gestionenvases;

import java.util.Vector;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Envase;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.ProductoMerma;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.EntidadDataHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionEnvasesService.
 */
public class GestionEnvasesService implements IGestionEnvasesService{

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#addEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Boolean addEnvase(Envase entry) throws Exception
	{
		return new GestionEnvasesBB().addEnvase(entry);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#getEnvases(es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Vector<Envase> getEnvases(Envase entry) throws Exception
	{
		return new GestionEnvasesBB().getEnvases(entry);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#getEnvases(java.lang.Long)
	 */
	//@Override
	public Vector getEnvases(Long idMaterial) throws Exception
	{
		return new GestionEnvasesBB().getEnvases(idMaterial);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#updateEnvase(es.induserco.opilion.data.entidades.Envase, es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Boolean updateEnvase(Envase envaseFind, Envase envaseUpdate)throws Exception {
		return new GestionEnvasesBB().updateEnvase(envaseFind, envaseUpdate);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#loadEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Vector loadEnvase(Envase entry) throws Exception{
		return new GestionEnvasesBB().loadEnvase(entry);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#getMateriales()
	 */
	//@Override
	public Vector getMateriales() throws Exception{
		return new GestionEnvasesBB().getMateriales();
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionenvases.IGestionEnvasesService#deshabilitaEnvase(es.induserco.opilion.data.entidades.Envase)
	 */
	//@Override
	public Boolean deshabilitaEnvase(Envase entry) throws Exception 
	{
		return new GestionEnvasesBB().deshabilitaEnvase(entry);
	}
	
	/*
	//@Override
	public long getIdEnvase(String codigoEntrada) throws Exception {
		return new GestionEnvasesBB().getIdEnvase(codigoEntrada);
	}
	//@Override
	public double getSaldoViejo(String codigoEntrada) throws Exception{
		return new GestionEnvasesBB().getSaldoViejo(codigoEntrada);
	}
	//@Override
	public double getStockEnvase(long idEnvase) throws Exception {
		return new GestionEnvasesBB().getStockEnvase(idEnvase);
	}
	//@Override
	public double getTeoricoViejo(String codigoEntrada) throws Exception {
		return new GestionEnvasesBB().getTeoricoViejo(codigoEntrada);
	}*/
}
