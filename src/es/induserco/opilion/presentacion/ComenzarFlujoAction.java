package es.induserco.opilion.presentacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.flujos.FlujoDevolucion;
import es.induserco.opilion.data.comun.flujos.FlujoMerma;
import es.induserco.opilion.data.comun.flujos.FlujoMover;

public class ComenzarFlujoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String codigo;
	private String url;
	
	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest;		 }

	public String execute() throws Exception {
		if (codigo == null)
			codigo = (String) request.getSession().getAttribute("codigo");
		System.out.println("Comenzar el flujo con código inicial " + codigo);
		if (codigo.compareTo("X0000007") == 0){//Mover
			FlujoMover f = new FlujoMover();
			request.getSession().setAttribute("flujo", f);
			request.getSession().setAttribute("msg", "");
			url = "FlujoMover";
		}else
			if (codigo.compareTo("X0000006") == 0){//Merma
				FlujoMerma f = new FlujoMerma();
				request.getSession().setAttribute("flujo", f);
				request.getSession().setAttribute("msg", "");
				url = "FlujoMerma";
			}else
				if (codigo.compareTo("X0000009") == 0){//Devolución
					FlujoDevolucion f = new FlujoDevolucion();
					request.getSession().setAttribute("flujo", f);
					request.getSession().setAttribute("msg", "");
					url = "FlujoDevolucion";
				}
		return SUCCESS;
	}

	public Object getModel() { return codigo; }
	public void setCodigo(String codigo) { this.codigo = codigo; }
	public void setUrl(String url) { this.url = url; }
	public String getCodigo() { return codigo; }
	public String getUrl() { return url; }
}