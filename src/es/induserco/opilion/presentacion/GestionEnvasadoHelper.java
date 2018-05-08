/*************************************************************
 * Gestor de las lineas de envasado (23/02/2011)
 * @version 1.1 
 * @author Administrador - Induserco
**************************************************************/	
package es.induserco.opilion.presentacion;

import java.util.ArrayList;
import java.util.Vector;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.comun.RegistroActividad;
import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.datos.produccion.IGestionProduccionDataService;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproduccion.IGestionRegistroEnvasadoService;

public class GestionEnvasadoHelper {
	
	public String getFechaRegistro() throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getFechaRegistro();
	}

	public Boolean addOrdenEnvasado(RegistroEnvasado envasado) throws Exception {
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).addOrdenEnvasado(envasado);
	}

	public Boolean addRegistroEnvasado(GestionProduccion gprod) throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).addRegistroEnvasado(gprod);
	}

	public Boolean updateRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodu) throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).updateRegistroEnvasado(gprodf,gprodu);
	}

	public Boolean deleteRegistroEnvasado(GestionProduccion gprodf,GestionProduccion gprodd) throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).deleteRegistroEnvasado(gprodf,gprodd);
	}

	public Vector getProductos() throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getProductos();
	}

	public Vector getEnvases() throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getEnvases();
	}
	
	/**
	 * Gets the registro envasados.
	 *
	 * @param orden the orden
	 * @param filtro 0: todos; 1: ultimos 20; 2: ultimos 50; 3: ultima semana; 4: ultimo mes; 5: ultimo año; 
	 * @param idProducto the id producto
	 * @return the registro envasados
	 * @throws Exception the exception
	 */
	public Vector<GestionProduccion> getRegistroEnvasados(String orden,long idProducto, int filtro) throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getRegistroEnvasados(orden,idProducto,filtro);
	}

	public Vector getOperarios() throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getOperarios();
	}

	public Vector<Producto> getPresentacionProductos(boolean stockSuficiente) throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).
				getService("IGestionRegistroEnvasadoService")).getPresentacionProductos(stockSuficiente);
	}	

	public GestionProduccion getEtiquetaEN(String codigoOrden) throws Exception{
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getEtiquetaEN(codigoOrden);
	}

	public GestionProduccion getMaestroEN(String orden) throws Exception {
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getMaestroEN(orden);
	}

	public Vector<GestionProduccion> getProcesosPendientes(GestionProduccion gprod, String tipo) throws Exception {
		return ((IGestionRegistroEnvasadoService)
				(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getProcesosPendientes(gprod, tipo);
	}

	/**
	 * * Borrado de proceso pendiente.
	 *
	 * @param envasado indica en proceso el id del envasado a eliminar
	 * @return the vector
	 * @throws Exception the exception
	 * @since 1.1
	 */ 
	public Vector delProcesoPendiente(RegistroEnvasado envasado) throws Exception {
		return ((IGestionRegistroEnvasadoService)(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).delProcesoPendiente(envasado);
	}

	public GestionProduccion getInfoProcesosEnv(String orden, String filtro) throws Exception {
		return ((IGestionRegistroEnvasadoService)
				(new ServiceLocator()).getService("IGestionRegistroEnvasadoService")).getInfoProcesosEnv(orden, filtro);
	}

	public Vector <LineaProducto> getLineaProducto(String idOrden, String proceso,String filtro,int option) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getLineaProducto(idOrden, proceso, filtro, option);
	}

	public  int idGestionToProducto(int id) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).idGestionToProducto(id);
	}
	
	/**
	 * Actualiza proceso envasado.
	 *
	 * @param cantidad the cantidad
	 * @param orden the orden
	 * @param fecha the fecha
	 * @param operario the operario
	 * @param observaciones the observaciones
	 * @param materias the materias
	 * @param envases the envases
	 * @param materiasLimpiar the materias limpiar
	 * @param envasesLimpiar the envases limpiar
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean actualizaProcesoEnvasado(int cantidad, String orden,
			String fecha, String operario, String observaciones, ArrayList materias, ArrayList envases, ArrayList materiasLimpiar, ArrayList envasesLimpiar) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
			actualizaProcesoEnvasado(cantidad, orden, fecha, operario, observaciones, materias, envases, materiasLimpiar, envasesLimpiar);
	}

	public boolean iniciaProcesoEnvasado(String orden, String operario) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
			iniciaProcesoEnvasado(orden, operario);
	}

	public boolean pausaProcesoEnvasado(String orden, String operario,
			double elaborado, String elaboradoAgrupar, String observaciones) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
			pausaProcesoEnvasado(orden, operario, elaborado, elaboradoAgrupar, observaciones);
	}

	public boolean productoCompuesto(String orden) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).productoCompuesto(orden);
	}

	public Vector<GestionProduccion> getProcesosEnvasado(String orden,
			String fechaConsulta, String loteAsignado, long idProducto, String estados[], char habilitado) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
		getProcesosEnvasado(orden, fechaConsulta, loteAsignado, idProducto, estados, habilitado);
}

	public String getObservacionesEnvasado(String orden) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
		getObservacionesEnvasado(orden);
	}

	public boolean finalizaProcesoEnvasado(RegistroEnvasado regi, Boolean anula) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
		finalizaProcesoEnvasado(regi, anula);
}

	public boolean actualizarPrecioCosteProducto(String orden) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
		actualizarPrecioCosteProducto(orden);
}

	public Vector<GestionProduccion> getDetalleEN(String ordenEnvasado) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
		getDetalleEN(ordenEnvasado);
	}

	public Vector<RegistroEntrada> getDetallesEnvasado(String ordenEnvasado) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
		getDetallesEnvasado(ordenEnvasado);
	}

	public boolean setUbicado(String ordenEnvasado, Vector<Ubicacion> ubicaciones, boolean actualizarCampoUbicado) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).
		setUbicado(ordenEnvasado, ubicaciones, actualizarCampoUbicado);
	}

	public Vector<LineaProducto> getEnvasesMateriasEnvasado(String ordenEnvasado, String filtro, int option) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getEnvasesMateriasEnvasado(ordenEnvasado, filtro, option);
	}

	public Vector<RegistroActividad> getRegistrosActividad(String proceso) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistrosActividad(proceso);
	}

	public Vector<Ubicacion> getUbicacionesEnvasado(String orden, String lote) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getUbicacionesEnvasado(orden, lote);
	}

	public Vector<Producto> getAgrupaciones(boolean stockSuficiente) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getAgrupaciones(stockSuficiente);
	}

	public void updateFechaProgramada(GestionProduccion gpro) throws Exception{
		((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).updateFechaProgramada(gpro);
	}

	public char compruebaLoteCantidad(Lote lote) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).compruebaLoteCantidad(lote);
	}

	public Vector<LineaProducto> getProductosComponenLote(String lote) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getProductosComponenLote(lote);
	}

	public Vector<RegistroEntrada> getRegistroIngredientes(long idMateriaPrima,	String filtro) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getRegistroIngredientes(idMateriaPrima, filtro);
	}

	public Vector<RegistroEntrada> getLotesIngrediente(long idMateriaPrima) throws Exception {
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getLotesIngrediente(idMateriaPrima);
	}

	public Vector<RegistroEntrada> getLotesEnvase(long idEnvase) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getLotesEnvase(idEnvase);
	}

	public Vector<Producto> getTrazabilidadRegistroEntrada(String lote) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).getTrazabilidadRegistroEntrada(lote);
	}

	public void actualizaCaducidadEnvasado(String proceso, String caducidad, String operario) throws Exception{
		((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).actualizaCaducidadEnvasado(proceso, caducidad, operario);
	}

	public boolean esFabaFresca(String orden) throws Exception{
		return ((IGestionProduccionDataService)(new ServiceLocator()).getService("IGestionProduccionDataService")).esFabaFresca(orden);
	}
}