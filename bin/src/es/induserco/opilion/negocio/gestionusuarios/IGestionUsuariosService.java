package es.induserco.opilion.negocio.gestionusuarios;

import java.util.Vector;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;

public interface IGestionUsuariosService {
	
	public Boolean getUsuarioValidar (Usuario usuario) throws Exception;
	public Usuario getUsuarioCompleto (Usuario usuario) throws Exception;
	public Vector<Usuario> getUsuarios() throws Exception;
	public void addUsuario(Usuario usuario) throws Exception;
	public Vector<Usuario> getComerciales() throws Exception;
	public Vector<Ruta> getRutas() throws Exception;
	public void addRuta(Ruta ruta)throws Exception;
	public Ruta getRuta(Ruta ruta) throws Exception;
	public void updateRuta(Ruta ruta)throws Exception;
}