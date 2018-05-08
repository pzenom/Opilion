package es.induserco.opilion.datos.ubicacion;

import java.util.ArrayList;
import java.util.Vector;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.entidades.Incidencia;
import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.data.entidades.MovimientoAlmacen;
import es.induserco.opilion.data.entidades.RegistroUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.Vehiculo;

public interface IUbicacionDataService {

	Vehiculo getVehiculoCompleto(long idVehiculo) throws Exception;
	Vehiculo getVehiculoLanzadera(long idVehiculo) throws Exception;
	Lanzadera insertaLogLanzadera(Lanzadera lanzadera) throws Exception;
	Vector<Lanzadera> getLanzaderas(Lanzadera lanzadera) throws Exception;
	int getNumeroProductosNuevaLanzadera(long idVehiculo) throws Exception;
	
	Boolean addUbicacion(Ubicacion ubicacion) throws Exception;

	Vector getUbicaciones(Long idDireccion)throws Exception;

	Ubicacion loadUbicacion(Ubicacion ubicacion)throws Exception;

	Boolean updateUbicacion(Ubicacion ubicacionFind, Ubicacion ubicacionUpdate)throws Exception;

	Vector getDireccionesUbicacion()throws Exception;	

	Vector getPasillosUbicacion()throws Exception;

	Vector getEstanteriasUbicacion()throws Exception;

	Vector getAlturasUbicacion()throws Exception;

	Boolean addRegistroUbicacion(RegistroUbicacion entry)throws Exception;

	Boolean addRegistroUbicacionTmp(RegistroUbicacion entry) throws Exception;

	Boolean addRegistroUbicacion(RegistroUbicacion entry, Boolean temp)	throws Exception;

	public Boolean addRegistrosTemporales() throws Exception;

	Ubicacion getUbicacion(Ubicacion ubica) throws Exception;

	long guardarRegistro(long idHueco, String registro, long idPalet) throws Exception;

	Vector<Ubicacion> getRegistrosAlmacenados(long idHueco) throws Exception;

	Vector<Ubicacion> getHuecosOcupados(Ubicacion ubica) throws Exception;

	boolean sacarUbicaciones(Vector<Ubicacion> ubicaciones, boolean incidencia,
			boolean registroEntrada, boolean produccion) throws Exception;

	ArrayList<Ubicacion> getUbicacionesEntrada(String entrada, boolean ean13) throws Exception;

	void reubicar(String orden, String entrada, String idUbicacion,
			String idUbicacionNueva, long idPalet, double cantidad) throws Exception;

	Vector<Incidencia> getIncidencias() throws Exception;

	long ubicarSeleccion(String registro, String idHueco, long idPalet,
			double cantidad) throws Exception;

	void ubicarCongelado(String proceso, String tipoProceso, Ubicacion ubicacion) throws Exception;

	Vector<Ubicacion> getBigBags() throws Exception;

	void registrarAlmacen(Ubicacion almacen) throws Exception;

	long getIdHuecoVehiculo(long idAlmacen) throws Exception;

	Vector<Vehiculo> getVehiculos() throws Exception;

	int mover(String origen, String producto, double cantidad, String destino)
			throws Exception;

	int merma(String origen, String producto, double cantidad) throws Exception;

	long getIdHueco(String descripcion) throws Exception;

	int checkUbicacion(Lote l) throws Exception;

	void salvarUbicaciones(Vector<Ubicacion> ubicaciones, boolean movimiento) throws Exception;

	long getIdUbicacion(long idHueco, String registro) throws Exception;

	Vector<MovimientoAlmacen> getMovimientosLote(String lote) throws Exception;
	Vector<Ubicacion> getUbicacionesRegistro(String lote) throws Exception;
	Vector<MovimientoAlmacen> getModificacionesStock(String lote) throws Exception;
}