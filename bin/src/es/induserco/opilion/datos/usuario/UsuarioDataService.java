package es.induserco.opilion.datos.usuario;

import java.util.Vector;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;

public class UsuarioDataService implements IUsuarioDataService {

	//@Override
	public Boolean addUsuario(Usuario usuario) throws Exception {
		return (new UsuarioDAO()).addUsuario(usuario);
	}

	//@Override
	public Boolean getUsuarioExiste(Usuario usuario) throws Exception {
		System.out.println("DATOS: DataSimpleService -> UsuarioDAO");
		return (new UsuarioDAO()).getUsuarioExiste(usuario);
	}

	//@Override
	public Vector<Usuario> getUsuarios() throws Exception {
		return (new UsuarioDAO()).getUsuarios();
	}

	//@Override
	public Boolean getUsuarioValidar(Usuario usuario) throws Exception {
		return (new UsuarioDAO()).getUsuarioValidar(usuario);
	}

	//@Override
	public Boolean getUsuarioAutenticar(Usuario usuario, String action) throws Exception {
		return (new UsuarioDAO()).getUsuarioAutenticar(usuario,action);
	}

	//@Override
	public Usuario getUsuarioCompleto(Usuario usuario) throws Exception {
		return (new UsuarioDAO()).getUsuarioCompleto(usuario);
	}

	//@Override
	public Vector<Usuario> getComerciales() throws Exception {
		return (new UsuarioDAO()).getComerciales();
	}

	//@Override
	public Vector<Ruta> getRutas() throws Exception {
		return (new UsuarioDAO()).getRutas();
	}

	//@Override
	public void addRuta(Ruta ruta) throws Exception {
		(new UsuarioDAO()).addRuta(ruta);
	}

	//@Override
	public void updateRuta(Ruta ruta) throws Exception {
		(new UsuarioDAO()).updateRuta(ruta);
	}
	
	//@Override
	public Ruta getRuta(Ruta ruta) throws Exception {
		return (new UsuarioDAO()).getRuta(ruta);
	}
}