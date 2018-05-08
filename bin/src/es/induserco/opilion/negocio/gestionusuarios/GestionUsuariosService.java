package es.induserco.opilion.negocio.gestionusuarios;

import java.util.Vector;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;

public class GestionUsuariosService implements IGestionUsuariosService {

	//@Override
	public Boolean getUsuarioValidar(Usuario usuario) throws Exception {
		return new GestionUsuariosBB().getUsuarioValidar(usuario);
	}

	//@Override
	public Usuario getUsuarioCompleto(Usuario usuario) throws Exception {
		return new GestionUsuariosBB().getUsuarioCompleto(usuario);
	}

	//@Override
	public Vector<Usuario> getUsuarios() throws Exception {
		return new GestionUsuariosBB().getUsuarios();
	}

	//@Override
	public void addUsuario(Usuario usuario) throws Exception {
		new GestionUsuariosBB().addUsuario(usuario);
	}

	//@Override
	public Vector<Usuario> getComerciales() throws Exception {
		return new GestionUsuariosBB().getComerciales();
	}

	//@Override
	public Vector<Ruta> getRutas() throws Exception {
		return new GestionUsuariosBB().getRutas();
	}

	//@Override
	public void addRuta(Ruta ruta) throws Exception {
		new GestionUsuariosBB().addRuta(ruta);
	}
	
	//@Override
	public void updateRuta(Ruta ruta) throws Exception {
		new GestionUsuariosBB().updateRuta(ruta);
	}

	//@Override
	public Ruta getRuta(Ruta ruta) throws Exception {
		return new GestionUsuariosBB().getRuta(ruta);
	}
}