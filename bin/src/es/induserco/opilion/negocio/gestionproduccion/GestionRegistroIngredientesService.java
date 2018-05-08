package es.induserco.opilion.negocio.gestionproduccion;

import java.util.List;
import java.util.Vector;

import es.induserco.opilion.datos.produccion.GestionProduccionDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionRegistroIngredientesService.
 */
public class GestionRegistroIngredientesService implements IGestionRegistroIngredientesService{

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#getRegistroIngredientes()
	 */
	//@Override
	public Vector getRegistroIngredientes() throws Exception {
		return new GestionRegistroIngredientesBB().getRegistroIngredientes();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#getRegistroIngredientes(java.util.List)
	 */
	//@Override
	public Vector getRegistroIngredientes(List listaingred) throws Exception {
		return new GestionRegistroIngredientesBB().getRegistroIngredientes(listaingred);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#getRegistroIngredientes(java.lang.String, long, long)
	 */
	//@Override
	public Vector getRegistroIngredientes(String orden, long idCategoria,
			long idProducto) throws Exception {
		return new GestionRegistroIngredientesBB().getRegistroIngredientes(orden, idCategoria, idProducto);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#getRegistroIngredientes(java.lang.String, long)
	 */
	//@Override
	public Vector getRegistroIngredientes(String orden,long idProducto)throws Exception {
		return new GestionRegistroIngredientesBB().getRegistroIngredientes(orden, idProducto);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#getRegistroIngredientes(long, java.lang.String)
	 */
	//@Override
	public Vector getRegistroIngredientes(long idProducto, String filtro)throws Exception {
		return new GestionRegistroIngredientesBB().getRegistroIngredientes(idProducto, filtro);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#getInfoMateriaPrima(long)
	 */
	//@Override
	public Vector getInfoMateriaPrima(long idProducto) throws Exception {
		return new GestionRegistroIngredientesBB().getInfoMateriaPrima(idProducto);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#addComponentesEnvasado(java.util.List, java.lang.String, java.lang.String)
	 */
	//@Override
	public Boolean addComponentesEnvasado(List listaelementos, String orden,
			String tipo) throws Exception {
		return new GestionRegistroIngredientesBB().addComponentesEnvasado(listaelementos,orden,tipo);
	}	

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#addComponentesProceso(java.util.List, java.lang.String, java.lang.String)
	 */
	//@Override
	public Boolean addComponentesProceso(List listaelementos, String orden,
			String tipo) throws Exception {
		return new GestionRegistroIngredientesBB().addComponentesProceso(listaelementos,orden,tipo);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService#getIngredientesProducto(java.lang.Long, java.lang.String)
	 */
	//@Override
	public Vector getIngredientesProducto(Long idProducto, String filtro) throws Exception {
		return new GestionRegistroIngredientesBB().getIngredientesProducto(idProducto, filtro);
	}
}