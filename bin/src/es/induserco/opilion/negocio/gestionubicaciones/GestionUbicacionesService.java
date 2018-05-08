package es.induserco.opilion.negocio.gestionubicaciones;

import java.util.ArrayList;
import java.util.Vector;

import es.induserco.opilion.data.comun.Lote;
import es.induserco.opilion.data.entidades.Incidencia;
import es.induserco.opilion.data.entidades.Lanzadera;
import es.induserco.opilion.data.entidades.MovimientoAlmacen;
import es.induserco.opilion.data.entidades.RegistroUbicacion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.data.entidades.Vehiculo;

public class GestionUbicacionesService implements IGestionUbicacionesService{

	public Vector<Lanzadera> getLanzaderas(Lanzadera lanzadera)	throws Exception {
		return new GestionUbicacionesBB().getLanzaderas(lanzadera);    
	}

	public Vehiculo getVehiculoCompleto(long idVehiculo) throws Exception {
		return new GestionUbicacionesBB().getVehiculoCompleto(idVehiculo);    
	}

	public Vehiculo getVehiculoLanzadera(long idVehiculo) throws Exception {
		return new GestionUbicacionesBB().getVehiculoLanzadera(idVehiculo);    
	}

	public Lanzadera insertaLogLanzadera(Lanzadera lanzadera) throws Exception {
		return new GestionUbicacionesBB().insertaLogLanzadera(lanzadera);    
	}

	public int getNumeroProductosNuevaLanzadera(long idVehiculo) throws Exception {
		return new GestionUbicacionesBB().getNumeroProductosNuevaLanzadera(idVehiculo); 
	}
	

	public Boolean addUbicacion(Ubicacion ubicacion) throws Exception{
		return new GestionUbicacionesBB().addUbicacion(ubicacion);
	}


	public Vector getUbicaciones(Long idDireccion) throws Exception
	{
		return new GestionUbicacionesBB().getUbicaciones(idDireccion);    
	}	


	public Ubicacion loadUbicacion(Ubicacion ubicacion) throws Exception {
		return new GestionUbicacionesBB().loadUbicacion(ubicacion);		
	}


	public Boolean updateUbicacion(Ubicacion entidadFind, Ubicacion entidadUpdate) throws Exception  {
		return new GestionUbicacionesBB().updateUbicacion(entidadFind,entidadUpdate);
	}	


	public Vector getDireccionesUbicacion() throws Exception
	{
		return new GestionUbicacionesBB().getDireccionesUbicacion();    
	}	


	public Boolean addRegistroUbicacionTmp(RegistroUbicacion entry) throws Exception {
		return new GestionUbicacionesBB().addRegistroUbicacionTmp(entry);
	}


	public Boolean addRegistroUbicacion(RegistroUbicacion entry) throws Exception{
		return new GestionUbicacionesBB().addRegistroUbicacion(entry);
	}


	public Vector getPasillosUbicacion() throws Exception {
		return new GestionUbicacionesBB().getPasillosUbicacion();    
	}	


	public Vector getEstanteriasUbicacion() throws Exception {
		return new GestionUbicacionesBB().getEstanteriasUbicacion();    
	}	


	public Vector getAlturasUbicacion() throws Exception {
		return new GestionUbicacionesBB().getAlturasUbicacion();    
	}


	public String getPlanoAlmacen(int idAlmacen) throws Exception {
		return new GestionUbicacionesBB().getPlanoAlmacen(idAlmacen);    
	}


	public Ubicacion getUbicacion(Ubicacion ubi) throws Exception {
		return new GestionUbicacionesBB().getUbicacion(ubi);    
	}


	public long guardarRegistro(long idHueco, String registro, long idPalet)
			throws Exception {
		return new GestionUbicacionesBB().guardarRegistro(idHueco, registro, idPalet);
	}


	public Vector<Ubicacion> getRegistrosAlmacenados(long idHueco) throws Exception {
		return new GestionUbicacionesBB().getRegistrosAlmacenados(idHueco);    
	}


	public Vector<Ubicacion> getHuecosOcupados(Ubicacion ubicacion)
			throws Exception {
		return new GestionUbicacionesBB().getHuecosOcupados(ubicacion);    
	}


	public void salvarUbicaciones(Vector<Ubicacion> ubicaciones, boolean movimiento)
			throws Exception {
		new GestionUbicacionesBB().salvarUbicaciones(ubicaciones, movimiento);
	}


	public boolean sacarUbicaciones(Vector<Ubicacion> ubicaciones, boolean incidencia,
			boolean registroEntrada, boolean produccion)
			throws Exception {
		return new GestionUbicacionesBB().sacarUbicaciones(ubicaciones,
				incidencia, registroEntrada, produccion);
	}


	public ArrayList<Ubicacion> getUbicacionesEntrada(String entrada, boolean ean13)
			throws Exception {
		return new GestionUbicacionesBB().getUbicacionesEntrada(entrada, ean13);    
	}


	public void reubicar(String orden, String entrada, String idUbicacion,
			String idUbicacionNueva, long idPalet, double cantidad) throws Exception {
		new GestionUbicacionesBB().reubicar(orden, entrada, idUbicacion, idUbicacionNueva, idPalet, cantidad);    
	}


	public Vector<Incidencia> getIncidencias() throws Exception {
		return new GestionUbicacionesBB().getIncidencias();    
	}


	public long ubicarSeleccion(String registro, String idHueco, long idPalet,
			double cantidad) throws Exception {
		return new GestionUbicacionesBB().ubicarSeleccion(registro, idHueco, idPalet, cantidad);    
	}


	public void ubicarCongelado(String proceso, String tipo, Ubicacion ubicacion)
			throws Exception {
		new GestionUbicacionesBB().ubicarCongelado(proceso, tipo, ubicacion);    
	}


	public Vector<Ubicacion> getBigBags() throws Exception {
		return new GestionUbicacionesBB().getBigBags();    
	}


	public void registrarAlmacen(Ubicacion almacen) throws Exception {
		new GestionUbicacionesBB().registrarAlmacen(almacen);    
	}


	public long getIdHuecoVehiculo(int idVehiculo) throws Exception {
		return new GestionUbicacionesBB().getIdHuecoVehiculo(idVehiculo);    
	}


	public Vector<Vehiculo> getVehiculos() throws Exception {
		return new GestionUbicacionesBB().getVehiculos();    
	}


	public int mover(String origen, String producto, double cantidad,
			String destino) throws Exception {
		return new GestionUbicacionesBB().mover(origen, producto, cantidad, destino);    
	}
	

	public int merma(String origen, String producto, double cantidad) throws Exception {
		return new GestionUbicacionesBB().merma(origen, producto, cantidad);    
	}
	

	public long getIdHueco(String descripcion) throws Exception {
		return new GestionUbicacionesBB().getIdHueco(descripcion);    
	}


	public int checkUbicacion(Lote l) throws Exception {
		return new GestionUbicacionesBB().checkUbicacion(l);    
	}


	public long getIdUbicacion(long idHueco, String registro) throws Exception {
		return new GestionUbicacionesBB().getIdUbicacion(idHueco, registro);    
	}

	public Vector<MovimientoAlmacen> getMovimientosLote(String lote)throws Exception {
		return new GestionUbicacionesBB().getMovimientosLote(lote);    
	}
	
	public Vector<Ubicacion> getUbicacionesRegistro(String lote) throws Exception {
		return new GestionUbicacionesBB().getUbicacionesRegistro(lote);    
	}
	
	public Vector<MovimientoAlmacen> getModificacionesStock(String lote) throws Exception{
		return new GestionUbicacionesBB().getModificacionesStock(lote);
	}

	public Ubicacion getUbicacionRegistro(String lote, long idHueco) throws Exception {
		return new GestionUbicacionesBB().getUbicacionRegistro(lote, idHueco);
	}
}