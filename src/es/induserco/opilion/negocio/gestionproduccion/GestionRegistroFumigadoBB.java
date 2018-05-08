package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Date;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.negocio.GestionProduccionDataHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionRegistroFumigadoBB.
 */
public class GestionRegistroFumigadoBB implements IGestionRegistroFumigadoService{

	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFumigadoService#getProductos()
	 */
	//@Override
	public Vector getProductos() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector producto = edh.getProductos();
		return producto;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFumigadoService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionProduccionDataHelper().getFechaRegistro();
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroFumigadoService#getRegistroFumigados(java.lang.String, java.lang.String)
	 */
	//@Override
	public Vector<GestionProduccion> getRegistroFumigados(String orden, String fecha)throws Exception{
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector<GestionProduccion> entradas = edh.getRegistroFumigados(orden, fecha);
		return entradas;
	}

}





