package es.induserco.opilion.presentacion;

import java.util.List;
import java.util.Vector;

import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroIngredientesService;

public class GestionRegistroIngredientesHelper{

	public Vector getRegistroIngredientes() throws Exception{
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).getService("IGestionRegistroIngredientesService")).getRegistroIngredientes();
	}

	public Vector getRegistroIngredientes(List listaingredientes) throws Exception{
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).getService("IGestionRegistroIngredientesService")).getRegistroIngredientes(listaingredientes);
	}

	public Vector getRegistroIngredientes(String orden, long idCategoria, long idProducto) throws Exception{
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).getService("IGestionRegistroIngredientesService")).getRegistroIngredientes(orden, idCategoria, idProducto);
	}

	public Vector getRegistroIngredientes(String orden, long idProducto) throws Exception{
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).getService("IGestionRegistroIngredientesService")).getRegistroIngredientes(orden, idProducto);
	}

	public Vector getRegistroIngredientes(long idProducto, String filtro) throws Exception{
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).getService("IGestionRegistroIngredientesService")).getRegistroIngredientes(idProducto, filtro);
	}

	public Vector getInfoMateriaPrima(long idProducto) throws Exception {
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).getService("IGestionRegistroIngredientesService")).getInfoMateriaPrima(idProducto);
	}

	public Boolean addComponentesEnvasado(List listaelementos, String orden, String tipo) throws Exception {	
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).getService("IGestionRegistroIngredientesService")).addComponentesEnvasado(listaelementos,orden,tipo);
	}

	public Boolean addComponentesProceso(List listaelementos, String orden, String tipo) throws Exception {	
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).getService("IGestionRegistroIngredientesService")).addComponentesProceso(listaelementos,orden,tipo);
	}

	public Vector getIngredientesProducto(Long idProducto, String filtro) throws Exception {
		return ((IGestionRegistroIngredientesService)(new ServiceLocator()).
				getService("IGestionRegistroIngredientesService")).getIngredientesProducto(idProducto, filtro);
	}
}