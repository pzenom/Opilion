package es.induserco.opilion.negocio.gestionusuarios;

import java.util.Vector;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;
import es.induserco.opilion.negocio.UsuarioDataHelper;

public class GestionUsuariosBB implements IGestionUsuariosService {

	//@Override
	public Boolean getUsuarioValidar(Usuario usuario) throws Exception {
		return new UsuarioDataHelper().getUsuarioValidar(usuario);
	}

	//@Override
	public Usuario getUsuarioCompleto(Usuario usuario) throws Exception {
		return new UsuarioDataHelper().getUsuarioCompleto(usuario);
	}

	//@Override
	public Vector<Usuario> getUsuarios() throws Exception {
		return new UsuarioDataHelper().getUsuarios();
	}

	//@Override
	public void addUsuario(Usuario usuario) throws Exception {
		new UsuarioDataHelper().addUsuario(usuario);
	}

	//@Override
	public Vector<Usuario> getComerciales() throws Exception {
		return new UsuarioDataHelper().getComerciales();
	}

	//@Override
	public Vector<Ruta> getRutas() throws Exception {
		return new UsuarioDataHelper().getRutas();
	}

	//@Override
	public void addRuta(Ruta ruta) throws Exception {
		new UsuarioDataHelper().addRuta(ruta);
	}

	//@Override
	public void updateRuta(Ruta ruta) throws Exception {
		new UsuarioDataHelper().updateRuta(ruta);
	}

	
	//@Override
	public Ruta getRuta(Ruta ruta) throws Exception {
		return new UsuarioDataHelper().getRuta(ruta);
	}
}