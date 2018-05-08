package es.induserco.edifact.presentacion.orders;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.presentacion.EdifactParserHelper;

/**
 * Action que ejecuta la consulta de acuerdo a los parametros de
 * FiltAlbaAction.
 */
public class ConsOrdersAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Order pedido = new Order();

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	public Object getModel() {
		System.out.println("Consulta Pedido");
		return pedido;
	}

	public String execute() throws Exception {
		System.out.println("ConsOrdersAction");
		int filtro = Integer.parseInt((String)request.getParameter("filtro"));
		try {
			System.out.println("");
			Vector<Order> resultado = (new EdifactParserHelper()).orderVisualizaService(pedido, filtro);
			if(resultado.size() > 0){
				if(resultado.get(0).getClass().toString().endsWith("String")){
					System.out.println("Hay un error");
					request.getSession().setAttribute("mensaje",resultado);
					return ERROR;
				}
			}
			//System.out.println("Presentacion -EDIFACT: "+resultado.toString());
			request.getSession().setAttribute("pedido", resultado);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ERROR;
	}
}