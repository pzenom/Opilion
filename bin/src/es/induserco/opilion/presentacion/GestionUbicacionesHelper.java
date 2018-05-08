package es.induserco.opilion.presentacion;

import java.util.ArrayList;
import java.util.Vector;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionubicaciones.IGestionUbicacionesService;
import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.entidades.Incidencia;
import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.data.entidades.MovimientoAlmacen;
import es.induserco.opilion.data.entidades.RegistroUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.Vehiculo;

public class GestionUbicacionesHelper {
	
	public Vehiculo getVehiculoCompleto(long idVehiculo) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getVehiculoCompleto(idVehiculo);
	}
	public Vehiculo getVehiculoLanzadera(long idVehiculo) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getVehiculoLanzadera(idVehiculo);
	}
	public Lanzadera insertaLogLanzadera(Lanzadera lanzadera) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).insertaLogLanzadera(lanzadera);
	}
	public Vector<Lanzadera> getLanzaderas(Lanzadera lanzadera) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getLanzaderas(lanzadera);
	}
	public int getNumeroProductosNuevaLanzadera(long idVehiculo) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getNumeroProductosNuevaLanzadera(idVehiculo);
	}

	public Boolean addUbicacion(Ubicacion ubicacion) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).addUbicacion(ubicacion);
	}

	public Vector getUbicaciones(Long idDireccion) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getUbicaciones(idDireccion);
	}

	public Boolean updateUbicacion(Ubicacion ubicacionFind, Ubicacion ubicacionUpdate)throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).updateUbicacion(ubicacionFind, ubicacionUpdate);
	}

	public Ubicacion loadUbicacion(Ubicacion entry) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).loadUbicacion(entry);
	}

	public Vector getDireccionesUbicacion() throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getDireccionesUbicacion();
	}	

	public Vector getPasillosUbicacion() throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getPasillosUbicacion();
	}

	public Vector getEstanteriasUbicacion() throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getEstanteriasUbicacion();
	}

	public Vector getAlturasUbicacion() throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getAlturasUbicacion();
	}

	public Boolean addRegistroUbicacion(RegistroUbicacion entry)throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).addRegistroUbicacion(entry);
	}

	public Boolean addRegistroUbicacionTmp(RegistroUbicacion entry) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).addRegistroUbicacionTmp(entry);
	}

	public String getPlanoAlmacen(int idAlmacen) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getPlanoAlmacen(idAlmacen);
	}

	public Ubicacion getUbicacion(Ubicacion ubi) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getUbicacion(ubi);
	}

	public long guardarRegistro(long idHueco, String registro, long idPalet) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).guardarRegistro(idHueco, registro, idPalet);
	}

	public Vector<Ubicacion> getRegistrosAlmacenados(long idHueco) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).getRegistrosAlmacenados(idHueco);
	}

	public Vector<Ubicacion> getHuecosOcupados(Ubicacion ubicacion) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).getHuecosOcupados(ubicacion);
	}

	public void salvarUbicaciones(Vector<Ubicacion> ubicaciones, boolean movimiento) throws Exception{
		((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).salvarUbicaciones(ubicaciones, movimiento);
	}

	public boolean sacarUbicaciones(Vector<Ubicacion> ubicaciones, boolean incidencia,
			boolean registroEntrada, boolean produccion) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).sacarUbicaciones(ubicaciones, incidencia, registroEntrada, produccion);
	}

	public ArrayList<Ubicacion> getUbicacionesEntrada(String entrada, boolean ean13) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).getUbicacionesEntrada(entrada, ean13);
	}

	public void reubicar(String orden, String entrada,
			String idUbicacion, String idUbicacionNueva, long idPalet, double cantidad) throws Exception{
		((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).reubicar(orden,
						entrada, idUbicacion, idUbicacionNueva, idPalet, cantidad);
	}

	public Vector<Incidencia> getIncidencias() throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).getIncidencias();
	}

	public long ubicarSeleccion(String registro, String idHueco, long idPalet, double cantidad) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).ubicarSeleccion(registro, idHueco, idPalet, cantidad);
	}

	public void ubicarCongelado(String proceso, String tipoProceso, Ubicacion ubicacion) throws Exception {
		((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).ubicarCongelado(proceso, tipoProceso, ubicacion);
	}

	public Vector<Ubicacion> getBigBags()throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).getBigBags();
	}

	public void registrarAlmacen(Ubicacion almacen) throws Exception{
		((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).registrarAlmacen(almacen);
	}

	public long getIdHuecoVehiculo(int idVehiculo) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).getIdHuecoVehiculo(idVehiculo);
	}

	public Vector<Vehiculo> getVehiculos() throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).getVehiculos();
	}

	public int mover(String origen, String producto, double cantidad,
			String destino) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).mover(origen, producto, cantidad, destino);
	}

	public int mermar(String origen, String producto, double cantidad) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).
				getService("IGestionUbicacionesService")).merma(origen, producto, cantidad);
	}

	public long getIdHueco(String ubicacion) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getIdHueco(ubicacion);
	}

	public int checkUbicacion(Lote l) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).checkUbicacion(l);
	}

	public long getIdUbicacion(long idHueco, String registro) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getIdUbicacion(idHueco, registro);
	}
	public Vector<MovimientoAlmacen> getMovimientosLote(String lote) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getMovimientosLote(lote);
	}
	public Vector<Ubicacion> getUbicacionesRegistro(String lote) throws Exception {
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getUbicacionesRegistro(lote);
	}
	
	public Vector<MovimientoAlmacen> getModificacionesStock(String lote) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getModificacionesStock(lote);
	}
	public Ubicacion getUbicacionRegistro(String lote, long idHueco) throws Exception{
		return ((IGestionUbicacionesService)(new ServiceLocator()).getService("IGestionUbicacionesService")).getUbicacionRegistro(lote, idHueco);
	}
}