package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.negocio.GestionProduccionDataHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionRegistroCribadoBB.
 */
public class GestionRegistroCribadoBB implements IGestionRegistroCribadoService{

	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getProductos()
	 */
	//@Override
	public Vector getProductos() throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector producto = edh.getProductos();
		return producto;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionProduccionDataHelper().getFechaRegistro();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getHoraInicioProceso(java.lang.String)
	 */
	//@Override
	public String getHoraInicioProceso(String orden) throws Exception {
		return new GestionProduccionDataHelper().getHoraInicioProceso(orden);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getRegistroCribados(java.lang.String, java.lang.String, java.lang.Long)
	 */
	//@Override
	public Vector<GestionProduccion> getRegistroCribados(String orden, String fecha,
			Long idProducto) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector<GestionProduccion> entradas = edh.getRegistroCribados(orden, fecha, idProducto);
		return entradas;
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getRegistroEntradaCribado(java.lang.String, long)
	 */
	//@Override
	public Vector getRegistroEntradaCribado(String orden,long idProducto) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector entradas = edh.getRegistroEntradaCribado(orden, idProducto);
		return entradas;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getDetalleCribado(java.lang.String)
	 */
	//@Override
	public Vector getDetalleCribado(String codigoEntrada) throws Exception {
		GestionProduccionDataHelper edh = new GestionProduccionDataHelper();
		Vector entradas = edh.getDetalleCribado(codigoEntrada);
		return entradas;
	}


}



