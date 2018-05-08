package es.induserco.opilion.negocio.gestionproduccion;

import java.util.List;
import java.util.Vector;

import es.induserco.opilion.negocio.GestionProduccionDataHelper;

public class GestionRegistroIngredientesBB implements IGestionRegistroIngredientesService{

	//@Override
	public Vector getRegistroIngredientes() throws Exception {
		return new GestionProduccionDataHelper().getRegistroIngredientes();
	}

	//@Override
	public Vector getRegistroIngredientes(List listaingred) throws Exception {
		return new GestionProduccionDataHelper().getRegistroIngredientes(listaingred);
	}

	//@Override
	public Vector getRegistroIngredientes(String orden, long idCategoria,
			long idProducto) throws Exception {
		return new GestionProduccionDataHelper().getRegistroIngredientes(orden, idCategoria, idProducto);
	}

	//@Override
	public Vector getRegistroIngredientes(String orden,long idProducto) throws Exception {
		return new GestionProduccionDataHelper().getRegistroIngredientes(orden, idProducto);
	}

	//@Override
	public Vector getRegistroIngredientes(long idProducto, String filtro) throws Exception {
		return new GestionProduccionDataHelper().getRegistroIngredientes(idProducto, filtro);
	}

	//@Override
	public Vector getInfoMateriaPrima(long idProducto) throws Exception {
		return new GestionProduccionDataHelper().getInfoMateriaPrima(idProducto);
	}

	//@Override
	public Boolean addComponentesEnvasado(List listaelementos, String orden,String tipo) throws Exception {
		return new GestionProduccionDataHelper().addComponentesEnvasado(listaelementos,orden,tipo);
	}

	//@Override
	public Boolean addComponentesProceso(List listaelementos, String orden,String tipo) throws Exception {
		return new GestionProduccionDataHelper().addComponentesProceso(listaelementos,orden,tipo);
	}

	//@Override
	public Vector getIngredientesProducto(Long idProducto, String filtro) throws Exception {
		return new GestionProduccionDataHelper().getIngredientesProducto(idProducto, filtro);
	}
}