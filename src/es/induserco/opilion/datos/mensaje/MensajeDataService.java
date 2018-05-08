package es.induserco.opilion.datos.mensaje;

import java.util.List;
import java.util.Vector;

import es.induserco.opilion.data.comun.Mensaje;
import es.induserco.opilion.data.comun.Usuario;

public class MensajeDataService implements IMensajeDataService {

	//@Override
	public Mensaje addMensaje(Usuario usuario, Mensaje mensaje) throws Exception {
		return (new MensajeDAO()).addMensaje(usuario, mensaje);	
	}

	//@Override
	public Vector<Mensaje> getMensajes() throws Exception {
		return (new MensajeDAO()).getMensajes();
	}

	//@Override
	public Vector<Mensaje> getMensajes(Usuario usuario) throws Exception {
		return (new MensajeDAO()).getMensajes(usuario);
	}

	//@Override
	public Vector<Mensaje> addMensajes(Usuario usuario, List<Mensaje> mensajes) throws Exception {
		return (new MensajeDAO()).addMensajes(usuario, mensajes);
	}

}
