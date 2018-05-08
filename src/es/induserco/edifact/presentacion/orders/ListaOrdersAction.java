package es.induserco.edifact.presentacion.orders;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.presentacion.EdifactParserHelper;

public class ListaOrdersAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object> {
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Order pedido = new Order();

	public void setServletRequest(HttpServletRequest request) { this.request = request;	}

 	public String execute() throws IOException {
		System.out.println("ListaOrdersAction");
		try {
			int filtro = 1;
			if (request.getParameter("filtro") != null)
				filtro = Integer.parseInt((String)request.getParameter("filtro"));
			Vector<Order> resultado = new EdifactParserHelper().orderVisualizaService(pedido, filtro);
			if(resultado.size() > 0){
				if(resultado.get(0).getClass().toString().endsWith("String")){
					System.out.println("Hay un error");
					request.getSession().setAttribute("mensaje", resultado);
					return ERROR;
				}
			}
			//System.out.println("Presentacion -EDIFACT: " + resultado.toString());
			request.getSession().setAttribute("pedido", resultado);
			return SUCCESS;
		} catch (Exception e) { e.printStackTrace(); }
		return ERROR;
	 }

	public Object getModel() {
		return pedido;
	}
}