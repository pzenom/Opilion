package es.induserco.edifact.presentacion.orders;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;

import es.induserco.edifact.presentacion.EdifactParserHelper;

public class EliminarPedidoAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Struts EliminarPedidoAction...");
		this.request = request;
	} 
	
 	public String execute() throws IOException {
		try {
			System.out.println("EliminarPedidoAction");
			String codigo = request.getParameter("codigoSalida");
			System.out.println("EliminarPedidoAction: " + codigo);
			boolean eliminado = new EdifactParserHelper().eliminaOrder(codigo);
			if (eliminado)
				return SUCCESS;
			else
				return ERROR;
		} catch (Exception e) {	e.printStackTrace(); }
		return ERROR;
	 }
}