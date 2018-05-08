package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.negocio.GestionProduccionDataHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionRegistroFiltradoBB.
 */
public class GestionRegistroFiltradoBB implements IGestionRegistroFiltradoService{

	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFiltradoService#getProductos()
	 */
	//@Override
	public Vector getProductos() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector producto = edh.getProductos();
		return producto;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFiltradoService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionProduccionDataHelper().getFechaRegistro();
	}

}


