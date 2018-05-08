package es.induserco.opilion.presentacion;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService;

public class GestionRegistroEnvasesHelper{
	
	public Vector getRegistroEnvases(long idEnvase, String filtro) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).getRegistroEnvases(idEnvase, filtro);
	}

	public Vector getRegistroEnvases(List listenvases) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).getRegistroEnvases(listenvases);
	}

	public Boolean addTmpRegistroIngredientesEnvases(GestionProduccion gprod,GestionProduccion gpro) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).addTmpRegistroIngredientesEnvases(gprod,gpro);
	}

	public Boolean addTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).addTmpRegistroIngredientesEnvases(mapaCantidades,orden);
	}

	public Vector getTmpRegistroIngredientesEnvases(String orden) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).getTmpRegistroIngredientesEnvases(orden);
	}

	public Vector getSubRegistrosEntradaEnvasado(String orden) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).getSubRegistrosEntradaEnvasado(orden);
	}

	public Boolean updateTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).updateTmpRegistroIngredientesEnvases(mapaCantidades,orden);
	}	

	public Boolean insertaCantidadesEnvases(Map mapaCantidades,String orden) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).insertaCantidadesEnvases(mapaCantidades,orden);
	}	
		
	public Boolean updateTmpRegistroMermasEnvases(Map mapaCantidades,String orden) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).updateTmpRegistroMermasEnvases(mapaCantidades,orden);
	}	
	
	public Vector getTmpRegistroIngredientesEnvases() throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).getTmpRegistroIngredientesEnvases();
	}

	public String generarNumeroLote(String codigo) throws Exception{
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).generarNumeroLote(codigo);
	}

	public Vector getEnvasesProducto(Long idProducto, String filtro) throws Exception {
		return ((IGestionRegistroEnvasesService)(new ServiceLocator()).getService("IGestionRegistroEnvasesService")).getEnvasesProducto(idProducto, filtro);
	}
}