package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionRegistroFumigadoService.
 */
public class GestionRegistroFumigadoService implements IGestionRegistroFumigadoService {

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFumigadoService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionRegistroFumigadoBB().getFechaRegistro();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFumigadoService#getProductos()
	 */
	//@Override
	public Vector getProductos() throws Exception {
		return new GestionRegistroFumigadoBB().getProductos();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFumigadoService#getRegistroFumigados(java.lang.String, java.lang.String)
	 */
	//@Override
	public Vector<GestionProduccion> getRegistroFumigados(String orden, String fecha) throws Exception {
		return new GestionRegistroFumigadoBB().getRegistroFumigados(orden,fecha);
	}



}


