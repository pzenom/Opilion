package es.induserco.edifact.presentacion.orders;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

import es.induserco.edifact.presentacion.EdifactParserHelper;

public class VisualizaOrdersAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware {
  	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Visualiza Orders Action...");
		this.request=request;	
	}   

 	public String execute() throws IOException {
		 String fichero = (String) request.getSession().getAttribute("filename");
		 System.out.println("El fichero es " + fichero);
		 System.out.println(request.getServletPath());
		 System.out.println(request.getContextPath());
		 System.out.println(request.getRequestURI());
		 System.out.println(System.getProperties().getProperty("user.dir")); 
		 System.out.println("---------------");
		 try {
			 Vector resultado =
				 (new EdifactParserHelper()).orderParserService(System.getProperties().getProperty("user.dir") +
						 "/webapps"+request.getContextPath() + "/WEB-INF/upload/" + fichero);
			//System.out.println("Presentacion -EDIFACT: "+pedido.getIdOrders().toString());
			if(resultado.get(0).getClass().toString().endsWith("String")){
				System.out.println("Hay un error");
				request.getSession().setAttribute("mensaje", resultado);
				return ERROR;
			}							
			System.out.println("Presentacion -EDIFACT: " + resultado.toString());
			request.getSession().setAttribute("pedido", resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		 return SUCCESS;
	 }
}