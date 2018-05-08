package es.induserco.opilion.negocio.gestionproduccion;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.negocio.GestionProduccionDataHelper;

public class GestionRegistroEnvasesBB implements IGestionRegistroEnvasesService{

	//@Override
	public Boolean addTmpRegistroIngredientesEnvases(GestionProduccion gprod,GestionProduccion gpro) throws Exception {
		return new GestionProduccionDataHelper().addTmpRegistroIngredientesEnvases(gprod,gpro);
	}

	//@Override
	public Boolean addTmpRegistroIngredientesEnvases(Map mapaCantidades,String orden) throws Exception {
		return new GestionProduccionDataHelper().addTmpRegistroIngredientesEnvases(mapaCantidades,orden);
	}

	//@Override
	public Vector getTmpRegistroIngredientesEnvases(String orden) throws Exception {
		return new GestionProduccionDataHelper().getTmpRegistroIngredientesEnvases(orden);
	}

	//actualizacion de cantidades para envasado
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#updateTmpRegistroIngredientesEnvases(java.util.Map, java.lang.String)
	 */
	//@Override
	public Boolean updateTmpRegistroIngredientesEnvases(Map mapaCantidades,
			String orden) throws Exception {
		return new GestionProduccionDataHelper().updateTmpRegistroIngredientesEnvases(mapaCantidades,orden);
	}

	//actualizacion de mermas para envasado
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#updateTmpRegistroMermasEnvases(java.util.Map, java.lang.String)
	 */
	//@Override
	public Boolean updateTmpRegistroMermasEnvases(Map mapaCantidades,
			String orden) throws Exception {
		return new GestionProduccionDataHelper().updateTmpRegistroMermasEnvases(mapaCantidades,orden);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#getSubRegistrosEntradaEnvasado(java.lang.String)
	 */
	//@Override
	public Vector getSubRegistrosEntradaEnvasado(String orden) throws Exception{
		return new GestionProduccionDataHelper().getSubRegistrosEntradaEnvasado(orden);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#getTmpRegistroIngredientesEnvases()
	 */
	//@Override
	public Vector getTmpRegistroIngredientesEnvases() throws Exception {
		return new GestionProduccionDataHelper().getTmpRegistroIngredientesEnvases();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#getRegistroEnvases(long, java.lang.String)
	 */
	//@Override
	public Vector getRegistroEnvases(long idEnvase, String filtro) throws Exception {
		return new GestionProduccionDataHelper().getRegistroEnvases(idEnvase, filtro);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#generarNumeroLote(java.lang.String)
	 */
	//@Override
	public String generarNumeroLote(String codigo) throws Exception {
		return new GestionProduccionDataHelper().generarNumeroLote(codigo);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#getRegistroEnvases(java.util.List)
	 */
	//@Override
	public Vector getRegistroEnvases(List listenvases) throws Exception {
		return new GestionProduccionDataHelper().getRegistroEnvases(listenvases);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#insertaCantidadesEnvases(java.util.Map, java.lang.String)
	 */
	//@Override
	public Boolean insertaCantidadesEnvases(Map mapaCantidades, String orden)
			throws Exception {
		return new GestionProduccionDataHelper().insertaCantidadesEnvases(mapaCantidades,orden);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasesService#getEnvasesProducto(java.lang.Long, java.lang.String)
	 */
	//@Override
	public Vector getEnvasesProducto(Long idProducto, String filtro) throws Exception {
		return new GestionProduccionDataHelper().getEnvasesProducto(idProducto, filtro);
	}
}