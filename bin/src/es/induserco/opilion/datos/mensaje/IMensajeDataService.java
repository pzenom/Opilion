package es.induserco.opilion.datos.mensaje;

import java.util.List;
import java.util.Vector;

import es.induserco.opilion.data.comun.Mensaje;
import es.induserco.opilion.data.comun.Usuario;

public interface IMensajeDataService {

	public Vector<Mensaje> getMensajes() throws Exception ;
	public Vector<Mensaje> getMensajes(Usuario usuario) throws Exception;
	public Mensaje addMensaje (Usuario usuario, Mensaje mensaje) throws Exception;
	public Vector<Mensaje> addMensajes (Usuario usuario, List<Mensaje> mensajes) throws Exception;
	
}
