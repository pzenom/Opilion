package es.induserco.opilion.presentacion;

import java.util.Vector;

import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;
import es.induserco.opilion.negocio.gestionusuarios.IGestionUsuariosService;

public class GestionUsuariosHelper {

	public Boolean getUsuarioValidar(Usuario usuario) throws Exception {
		return ((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).getUsuarioValidar(usuario);
	}
	
	public Usuario getUsuarioCompleto(Usuario usuario) throws Exception {
		return ((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).getUsuarioCompleto(usuario);
	}

	public Vector<Usuario> getUsuarios() throws Exception {
		return ((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).getUsuarios();
	}

	public void addUsuario(Usuario usuario) throws Exception {
		((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).addUsuario(usuario);
	}

	public Vector<Usuario> getComerciales() throws Exception {
		return ((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).getComerciales();
	}

	public Vector<Ruta> getRutas() throws Exception {
		return ((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).getRutas();
	}

	public void addRuta(Ruta ruta) throws Exception {
		((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).addRuta(ruta);
	}

	public Ruta getRuta(Ruta ruta) throws Exception {
		return ((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).getRuta(ruta);
	}

	public void updateRuta(Ruta ruta) throws Exception {
		((IGestionUsuariosService)(new ServiceLocator()).getService("IGestionUsuariosService")).updateRuta(ruta);
	}
}