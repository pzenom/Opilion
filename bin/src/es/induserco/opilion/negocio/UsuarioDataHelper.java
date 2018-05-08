package es.induserco.opilion.negocio;

import java.util.Vector;

import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;
import es.induserco.opilion.datos.usuario.IUsuarioDataService;

public class UsuarioDataHelper {
	
	public Boolean getUsuarioValidar (Usuario usuario) throws Exception {
		return((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).getUsuarioValidar(usuario);		 
	}
	
	public Usuario getUsuarioCompleto (Usuario usuario) throws Exception {
		return((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).getUsuarioCompleto(usuario);		 
	}

	public Vector<Usuario> getUsuarios() throws Exception {
		return((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).getUsuarios();
	}

	public void addUsuario(Usuario usuario) throws Exception {
		((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).addUsuario(usuario);
	}

	public Vector<Ruta> getRutas() throws Exception {
		return((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).getRutas();
	}

	public Vector<Usuario> getComerciales() throws Exception {
		return((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).getComerciales();
	}

	public void addRuta(Ruta ruta) throws Exception {
		((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).addRuta(ruta);
	}

	public Ruta getRuta(Ruta ruta) throws Exception {
		return((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).getRuta(ruta);
	}

	public void updateRuta(Ruta ruta) throws Exception {
		((IUsuarioDataService)(new ServiceLocator()).getService("IUsuarioDataService")).updateRuta(ruta);
	}
}