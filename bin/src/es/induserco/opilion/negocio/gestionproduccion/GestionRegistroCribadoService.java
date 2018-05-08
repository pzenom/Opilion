package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionRegistroCribadoService.
 */
public class GestionRegistroCribadoService implements IGestionRegistroCribadoService {

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionRegistroCribadoBB().getFechaRegistro();
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getHoraInicioProceso(java.lang.String)
	 */
	//@Override
	public String getHoraInicioProceso(String orden) throws Exception {
		return new GestionRegistroCribadoBB().getHoraInicioProceso(orden);
	}	

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getProductos()
	 */
	//@Override
	public Vector getProductos() throws Exception {
		return new GestionRegistroCribadoBB().getProductos();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getRegistroCribados(java.lang.String, java.lang.String, java.lang.Long)
	 */
	//@Override
	public Vector<GestionProduccion> getRegistroCribados(String orden, String fecha,
			Long idProducto) throws Exception {
		return new GestionRegistroCribadoBB().getRegistroCribados(orden,fecha,idProducto);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getRegistroEntradaCribado(java.lang.String, long)
	 */
	//@Override
	public Vector getRegistroEntradaCribado(String orden,long idProducto) throws Exception {
		return new GestionRegistroCribadoBB().getRegistroEntradaCribado(orden,idProducto);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroCribadoService#getDetalleCribado(java.lang.String)
	 */
	//@Override
	public Vector getDetalleCribado(String codigoEntrada) throws Exception {
		return new GestionRegistroCribadoBB().getDetalleCribado(codigoEntrada);
	}



}


