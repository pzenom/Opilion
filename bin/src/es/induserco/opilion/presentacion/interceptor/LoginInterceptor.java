package es.induserco.opilion.presentacion.interceptor;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import es.induserco.opilion.data.comun.Usuario;

public class LoginInterceptor extends AbstractInterceptor implements
		StrutsStatics {

	private static final long serialVersionUID = 1L;

	//@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Usuario usuario = (Usuario) invocation.getInvocationContext().getSession().get("usuario");
		//System.out.println("Invocado: " + invocation.getInvocationContext().getName());
		if (usuario == null ){
			System.out.println("El usuario no est� logueado");
			//Comprobamos si lo que se ha invocado es la aci�n de login. En ese caso, le dejamos pasar. Si no, redirigimos.
			if (!invocation.getInvocationContext().getName().equals("Login")){
				return "login";
			}
			return invocation.invoke();
		}
		//System.out.println("Logueado el usuario: " + usuario.getLogin());
		return invocation.invoke();
	}
}