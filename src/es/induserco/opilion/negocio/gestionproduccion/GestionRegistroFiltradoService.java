package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionRegistroFiltradoService.
 */
public class GestionRegistroFiltradoService implements IGestionRegistroFiltradoService {


	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFiltradoService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionRegistroFiltradoBB().getFechaRegistro();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFiltradoService#getProductos()
	 */
	//@Override
	public Vector getProductos() throws Exception {
		return new GestionRegistroFiltradoBB().getProductos();
	}
}

