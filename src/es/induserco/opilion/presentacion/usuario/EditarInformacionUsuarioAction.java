package es.induserco.opilion.presentacion.usuario;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.datos.usuario.UsuarioDAO;
import es.induserco.opilion.presentacion.GestionUsuariosHelper;

public class EditarInformacionUsuarioAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Usuario usuario = new Usuario();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public String execute() throws Exception {
		System.out.println("CAMBIO de contraseña: procesando el execute de EditarInformacionUsuario");
		System.out.println("Usuariolog: " + usuario.getLogin());
		//String nuevoPass = this.request.getParameter("nuevoPass");
		System.out.println("Nuevo pass: " + usuario.getPassword());
		request.setAttribute("usuarioValidar",
				(new GestionUsuariosHelper()).getUsuarioValidar(usuario));
		new UsuarioDAO().updatePass(usuario);
		if (request.getAttribute("usuarioValidar").equals(true)) {
			//responseMap.put("success", true);
			request.getSession().setAttribute("usuario",usuario) ;
			System.out.println("PRESENTACION(Login): Validacion correcta del usuario: " 
					+ usuario.getLogin() + " contrasena:" + usuario.getPassword());
			return SUCCESS;
		}
		else{
			//responseMap.put("failure", true);
			System.out.println("PRESENTACION(Login): Usuario no validado");
			return "failure";
		}
	}

	//@Override
	public Object getModel() {
		usuario.setLogin(request.getParameter("login"));
		usuario.setPassword(request.getParameter("pass"));
		return usuario;
	}

	public String getLogin(){return usuario.getLogin();}
	public String getPassword(){return usuario.getPassword();}	
	public void setLogin(String login){request.setAttribute("login",usuario.getLogin());}
	public void setPassword(String password){request.setAttribute("password",usuario.getPassword());}

}