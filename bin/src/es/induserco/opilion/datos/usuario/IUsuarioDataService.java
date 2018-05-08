package es.induserco.opilion.datos.usuario;

import java.util.Vector;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Ruta;

public interface IUsuarioDataService {
	Vector<Usuario> getUsuarios() throws Exception;
	Boolean addUsuario(Usuario usuario) throws Exception;
	Boolean getUsuarioExiste(Usuario usuario) throws Exception;
	Boolean getUsuarioValidar(Usuario usuario) throws Exception;
	Boolean getUsuarioAutenticar(Usuario usuario, String action) throws Exception;
	Usuario getUsuarioCompleto(Usuario usuario) throws Exception;
	Vector<Ruta> getRutas() throws Exception;
	Vector<Usuario> getComerciales() throws Exception;
	void addRuta(Ruta ruta) throws Exception;
	Ruta getRuta(Ruta ruta) throws Exception;
	void updateRuta(Ruta ruta) throws Exception;
}