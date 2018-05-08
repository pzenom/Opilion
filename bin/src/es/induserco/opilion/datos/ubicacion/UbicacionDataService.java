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

public class UbicacionDataService implements IUbicacionDataService {
	
	public Vehiculo getVehiculoCompleto(long idVehiculo) throws Exception {
		return (new UbicacionDAO()).getVehiculoCompleto(idVehiculo);
	}
	public Vehiculo getVehiculoLanzadera(long idVehiculo) throws Exception {
		return (new UbicacionDAO()).getVehiculoLanzadera(idVehiculo);
	}
	public Lanzadera insertaLogLanzadera(Lanzadera lanzadera) throws Exception {
		return new UbicacionDAO().insertaLogLanzadera(lanzadera);
	}
	public Vector<Lanzadera> getLanzaderas(Lanzadera lanzadera) throws Exception {
		return new UbicacionDAO().getLanzaderas(lanzadera);
	}
	public int getNumeroProductosNuevaLanzadera(long idVehiculo) throws Exception {
		return (new UbicacionDAO()).getNumeroProductosNuevaLanzadera(idVehiculo);
	}
	
	public Boolean addUbicacion(Ubicacion ubicacion)throws Exception{
		return (new UbicacionDAO()).addUbicacion(ubicacion);
	}

	public Vector getUbicaciones(Long idDireccion)throws Exception{
		return (new UbicacionDAO()).getUbicaciones(idDireccion);
	}	

	public Boolean updateUbicacion(Ubicacion ubicacionFind, Ubicacion ubicacionUpdate)throws Exception{
		return (new UbicacionDAO()).updateUbicacion(ubicacionFind, ubicacionUpdate);
	}

	public Ubicacion loadUbicacion(Ubicacion ubicacion) throws Exception {
		return (new UbicacionDAO()).loadUbicacion(ubicacion);
	}

	public Vector getDireccionesUbicacion()throws Exception{
		return (new UbicacionDAO()).getDireccionesUbicacion();
	}

	public Boolean addRegistroUbicacion(RegistroUbicacion entry) throws Exception {
		return (new UbicacionDAO()).addRegistroUbicacion(entry);
	}

	public Boolean addRegistroUbicacionTmp(RegistroUbicacion entry) throws Exception {
		return (new UbicacionDAO()).addRegistroUbicacionTmp(entry);
	}

	public Vector getPasillosUbicacion()throws Exception{
		return (new UbicacionDAO()).getPasillosUbicacion();
	}

	public Vector getEstanteriasUbicacion()throws Exception{
		return (new UbicacionDAO()).getEstanteriasUbicacion();
	}
	
	public Vector getAlturasUbicacion()throws Exception{
		return (new UbicacionDAO()).getAlturasUbicacion();
	}

	public Boolean addRegistroUbicacion(RegistroUbicacion entry, Boolean temp) throws Exception {
		return (new UbicacionDAO()).addRegistroUbicacion(entry, temp);
	}
	public Boolean addRegistrosTemporales() throws Exception {
		return (new UbicacionDAO()).addRegistrosTemporales();
	}

	public String getPlanoAlmacen(int idAlmacen) throws Exception{
		return (new UbicacionDAO()).getPlanoAlmacen(idAlmacen);
	}
	public Ubicacion getUbicacion(Ubicacion ubica) throws Exception {
		return (new UbicacionDAO()).getUbicacion(ubica);
	}

	public long guardarRegistro(long idHueco, String registro, long idPalet) throws Exception {
		return (new UbicacionDAO()).guardarRegistro(idHueco, registro, idPalet);
	}

	public Vector<Ubicacion> getRegistrosAlmacenados(long idHueco)
			throws Exception {
		return (new UbicacionDAO()).getRegistrosAlmacenados(idHueco);
	}
	public Vector<Ubicacion> getHuecosOcupados(Ubicacion ubica)
			throws Exception {
		return (new UbicacionDAO()).getHuecosOcupados(ubica);
	}
	public void salvarUbicaciones(Vector<Ubicacion> ubicaciones, boolean movimiento) throws Exception {
		(new UbicacionDAO()).salvarUbicaciones(ubicaciones, movimiento);
	}

	public boolean sacarUbicaciones(Vector<Ubicacion> ubicaciones, boolean incidencia, boolean registroEntrada, boolean produccion) throws Exception {
		return (new UbicacionDAO()).sacarUbicaciones(ubicaciones, incidencia, registroEntrada, produccion);
	}

	public ArrayList<Ubicacion> getUbicacionesEntrada(String entrada, boolean ean13) throws Exception {
		return (new UbicacionDAO()).getUbicacionesEntrada(entrada, ean13);
	}

	public void reubicar(String orden, String entrada, String idUbicacion,
			String idUbicacionNueva, long idPalet, double cantidad) throws Exception {
		(new UbicacionDAO()).reubicar(orden, entrada, idUbicacion, idUbicacionNueva, idPalet, cantidad);
	}

	public Vector<Incidencia> getIncidencias() throws Exception {
		return (new UbicacionDAO()).getIncidencias();
	}

	public long ubicarSeleccion(String registro, String idHueco, long idPalet, double cantidad) throws Exception {
		return (new UbicacionDAO()).ubicarSeleccion(registro, idHueco, idPalet, cantidad);
	}

	public void ubicarCongelado(String proceso, String tipo, Ubicacion ubicacion) throws Exception {
		(new UbicacionDAO()).ubicarCongelado(proceso, tipo, ubicacion);
	}

	public Vector<Ubicacion> getBigBags() throws Exception {
		return (new UbicacionDAO()).getBigBags();
	}
	
	public void registrarAlmacen(Ubicacion almacen) throws Exception {
		(new UbicacionDAO()).registrarAlmacen(almacen);
	}

	public long getIdHuecoVehiculo(long idAlmacen) throws Exception {
		return (new UbicacionDAO()).getIdHuecoVehiculo(idAlmacen);
	}
	
	public Vector<Vehiculo> getVehiculos() throws Exception{
		return (new UbicacionDAO()).getVehiculos();
	}

	public int mover(String origen, String producto, double cantidad, String destino) throws Exception {
		return (new UbicacionDAO()).mover(origen, producto, cantidad, destino);
	}

	public int merma(String origen, String producto, double cantidad) throws Exception {
		return (new UbicacionDAO()).merma(origen, producto, cantidad);
	}
	
	public long getIdHueco(String descripcion) throws Exception {
		return (new UbicacionDAO()).getIdHueco(descripcion);
	}

	public int checkUbicacion(Lote l) throws Exception {
		return (new UbicacionDAO()).checkUbicacion(l);
	}

	public long getIdUbicacion(long idHueco, String registro) throws Exception {
		return (new UbicacionDAO()).getIdUbicacion(idHueco, registro);
	}

	public Vector<MovimientoAlmacen> getMovimientosLote(String lote) throws Exception {
		return (new UbicacionDAO()).getMovimientosLote(lote);
	}
	public Vector<Ubicacion> getUbicacionesRegistro(String lote) throws Exception {
		return (new UbicacionDAO()).getUbicacionesRegistro(lote);
	}
	
	public Vector<MovimientoAlmacen> getModificacionesStock(String lote) throws Exception {
		return (new UbicacionDAO()).getModificacionesStock(lote);
	}
	public Ubicacion getUbicacionRegistro(String lote, long idHueco) throws Exception {
		return (new UbicacionDAO()).getUbicacionRegistro(lote, idHueco);
	}
}