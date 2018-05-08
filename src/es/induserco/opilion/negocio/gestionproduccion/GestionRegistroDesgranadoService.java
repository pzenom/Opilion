package es.induserco.opilion.negocio.gestionproduccion;

import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public class GestionRegistroDesgranadoService implements IGestionRegistroDesgranadoService {

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService#addRegistroProceso(es.induserco.opilion.data.entidades.GestionProduccion, java.lang.String)
	 */
	//@Override
	public Boolean addRegistroProceso(GestionProduccion gprod, String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().addRegistroProceso(gprod,proceso);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService#insertRegistroEnvasado(es.induserco.opilion.data.entidades.GestionProduccion, es.induserco.opilion.data.entidades.GestionProduccion, java.lang.String)
	 */
	//@Override
	public Boolean insertRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu, String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().insertRegistroEnvasado(gprodf,gprodu,proceso);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService#updateRegistroProceso(es.induserco.opilion.data.entidades.GestionProduccion, es.induserco.opilion.data.entidades.GestionProduccion, java.lang.String)
	 */
	//@Override
	public Boolean updateRegistroProceso(GestionProduccion gprodf,
			GestionProduccion gprodu, String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().updateRegistroProceso(gprodf,gprodu, proceso);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService#getREProceso(java.lang.String, java.lang.String)
	 */
	//@Override
	public Vector getREProceso(String orden, String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().getREProceso(orden,proceso);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService#getREProceso(java.lang.String, long, int)
	 */
	//@Override
	public Vector getREProceso(String orden, long idProducto, int idIncidencia)throws Exception {
		return new GestionRegistroDesgranadoBB().getREProceso(orden,idProducto,idIncidencia);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService#updateRECantProceso(java.util.Map, java.lang.String, java.lang.String)
	 */
	//@Override
	public Boolean updateRECantProceso(Map mapaCantRE,String orden, String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().updateRECantProceso(mapaCantRE,orden, proceso);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService#updateREMermProceso(java.util.Map, java.lang.String, java.lang.String)
	 */
	//@Override
	public Boolean updateREMermProceso(Map mapaCantRE,String orden, String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().updateREMermProceso(mapaCantRE,orden, proceso);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroDesgranadoService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new GestionRegistroDesgranadoBB().getFechaRegistro();
	}

	//@Override
	public Vector getProductos() throws Exception {
		return new GestionRegistroDesgranadoBB().getProductos();
	}

	//@Override
	public Vector<GestionProduccion> getRegistroDesgranados(String orden, String fecha,
			Long idProducto) throws Exception {
		return new GestionRegistroDesgranadoBB().getRegistroDesgranados(orden,fecha,idProducto);
	}

	//@Override
	public Vector<GestionProduccion> getRegistroSeleccion(String orden, String fecha) throws Exception {
		return new GestionRegistroDesgranadoBB().getRegistroSeleccion(orden,fecha);
	}

	//@Override
	public Vector getProductosDesgranado() throws Exception {
		return new GestionRegistroDesgranadoBB().getProductosDesgranado();
	}

	//@Override
	public Vector getProductosDesgranadoVaina() throws Exception {
		return new GestionRegistroDesgranadoBB().getProductosDesgranadoVaina();
	}

	//@Override
	public Vector getSubRegistrosEntrada(GestionProduccion gprod, String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().getSubRegistrosEntrada(gprod,proceso);
	}

	//@Override
	public String getCodigoRegistroProceso(String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().getCodigoRegistroProceso(proceso);
	}

	//@Override
	public String getCodigoEntradaOrden(String orden,String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().getCodigoEntradaOrden(orden,proceso);
	}

	//@Override
	public Vector<GestionProduccion> getDetallesRegistroProceso(
			String tipoProceso, String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().getDetallesRegistroProceso(tipoProceso,proceso);
	}

	//@Override
	public Vector<LineaProducto> getInfoMateria(String codigoEntrada)
			throws Exception {
		return new GestionRegistroDesgranadoBB().getInfoMateria(codigoEntrada);
	}

	//@Override
	public Vector<LineaProducto> getInfoMateriasProceso(String orden,
			String proceso) throws Exception {
		return new GestionRegistroDesgranadoBB().getInfoMateriasProceso(orden, proceso);
	}

	/*//@Override
	public Vector<GestionProduccion> getDetallesRegistroSeleccion(String proceso)
			throws Exception {
		return new GestionRegistroDesgranadoBB().getDetallesRegistroSeleccion(proceso);
	}*/
}