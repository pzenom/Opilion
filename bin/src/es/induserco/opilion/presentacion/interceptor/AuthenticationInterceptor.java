package es.induserco.opilion.presentacion.interceptor;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Set;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.datos.usuario.UsuarioDAO;

public class AuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private String authenticationSessionField= new String ("authenticated");
	private Set<?> excludeActions = Collections.EMPTY_SET;

	//@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Usuario usuario = (Usuario) invocation.getInvocationContext().getSession().get("usuario");
		String loginUsuario = "";
		if (usuario!= null){
			loginUsuario = usuario.getLogin();
		}
		Boolean estado = false;
		//nombre del action
		String actionName = invocation.getInvocationContext().getName();
		//System.out.println("Invocado en autenticar: " + actionName);
		if(excludeActions.contains(actionName))
			return invocation.invoke();
		estado = new UsuarioDAO().getUsuarioAutenticar(usuario, actionName);
		Calendar calendario = new GregorianCalendar();
		int hora, minutos, segundos;
		hora = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String cadenaHoraUsuarioAction = hora + ":" + minutos + ":" + segundos + " -> Usuario: " + loginUsuario + ". " + actionName + " -> ";
		//comprobar si el action es permitido al usuario
		if(estado.equals(Boolean.TRUE)){
			System.out.println(cadenaHoraUsuarioAction + " SI se le permite");
			return invocation.invoke(); //"success";
		}else {
			System.out.println(cadenaHoraUsuarioAction + " NO se le permite");
			invocation.getInvocationContext().getSession().put("nombreUsuario", loginUsuario);
			return "nopermiso";
		}
	}

	public void setExcludeActions(String excludeActions) {
		if(excludeActions != null){
			this.excludeActions = TextParseUtil.commaDelimitedStringToSet(excludeActions);
		}
	}
	
	public String getAuthenticationSessionField() {
		return authenticationSessionField;
	}

	public void setAuthenticationSessionField(String authenticationSessionField) {
		this.authenticationSessionField = authenticationSessionField;
	}
}