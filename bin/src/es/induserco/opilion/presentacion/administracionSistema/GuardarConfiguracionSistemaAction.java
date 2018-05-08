package es.induserco.opilion.presentacion.administracionSistema;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Empresa;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Accion;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class GuardarConfiguracionSistemaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = -622643281402760772L;
	private HttpServletRequest request;
	private Empresa empresa = new Empresa();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("GuardarConfiguracionSistemaAction Action...");
		this.request = request;	
	}

	//@Override
	public Object getModel(){
		System.out.println("ADMINISTRACION. Guardar configuraci�n");
		return empresa;
	}

	public String execute() throws Exception {
		String nombreParametro;
		Vector<Accion> modificaciones = new Vector<Accion>();
		Vector<Usuario> usuarios = new Vector<Usuario>();
		//Obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		// paso por todos los parametros
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if(nombreParametro.indexOf("accionRolModificado_") == 0) {
				System.out.println("Encontramos una accion/rol modificada");
				String[] frag = nombreParametro.split("_");
				long idAccion = Long.parseLong(frag[1]);
				long idRol = Long.parseLong(frag[2]);
				String nuevoValor = (((String[])parametros.get(e.getKey()))[0]);
				System.out.println("La accion " + idAccion + " se ha modificada para el rol " + idRol + ". �Puede acceder? " + nuevoValor);
				Accion accionRol = new Accion();
				accionRol.setIdAccion(idAccion);
				accionRol.setIdRol(idRol);
				accionRol.setValor(nuevoValor);
				modificaciones.add(accionRol);
			}else{
				if(nombreParametro.indexOf("usuarioRolModificado_") == 0) {
					System.out.println("Encontramos un usuario modificada");
					String[] frag = nombreParametro.split("_");
					long idUsuario = Long.parseLong(frag[1]);
					long idRol = Long.parseLong(frag[2]);
					String nuevoValor = (((String[])parametros.get(e.getKey()))[0]);
					System.out.println("El usuario " + idUsuario + " se ha modificada para el rol " + idRol + ". �Tiene el rol? " + nuevoValor);
					Usuario usuario = new Usuario();
					usuario.setIdUsuario(idUsuario);
					usuario.setIdRolPredeterminado(idRol);
					usuario.setLogin(nuevoValor);//En login pasamos el nuevo valor. "si" o "no"
					usuarios.add(usuario);
				}
			}
		}
		new GestionEntidadesHelper().guardarConfiguracionEmpresa(empresa, modificaciones, usuarios);
		return SUCCESS;
	}
}