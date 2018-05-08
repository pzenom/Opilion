package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.flujos.FlujoMover;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

/**
 * @author andres (07.09.2011)
 * @version 1.0
 */
public class RealizarFlujoMoverAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private FlujoMover flujo = new FlujoMover();
	private String url;

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		return flujo;
	}

	public String execute() throws Exception {
		//request.getSession().setAttribute("origen", origenParseado);
		//Vector<FlujoMover> flujos = new Vector<FlujoMover>();
		//flujos.add(flujo);
		request.getSession().setAttribute("flujo", flujo);
		if (flujo.getCodigoEscape() != null){
			if (flujo.getCodigoEscape().compareTo("X0000004") == 0){//CANCELAR
				//Salir (al escritorio) sin mover nada
				setUrl("Inicio");
				return "redirect";
			}else
				if (flujo.getCodigoEscape().compareTo("X0000005") == 0){//FIN
					//Mover los datos introducidos y salir
					return mover(true);
				}else
					if (flujo.getCodigoEscape().compareTo("X0000002") == 0){//INTRO
						//Mover los datos introducidos
						return mover(false);
					}
		}else
			request.getSession().setAttribute("msg", "");
		return SUCCESS;
	}

	private String mover(boolean fin) throws Exception{
		int ubicacion =
			new GestionUbicacionesHelper().mover(flujo.getOrigen(),
				flujo.getProducto(), flujo.getCantidad(), flujo.getDestino());
		switch (ubicacion){
		case 0://No existe el producto en ese hueco
				request.getSession().setAttribute("msg", "No existe el producto ubicado en este hueco");
			break;
		case 1://OK
			if (fin)
				setUrl("Inicio.action");
			else{
				request.getSession().setAttribute("codigo", "X0000007");
				setUrl("ComenzarFlujo.action");
			}
			return "redirect";
		case 2://No existe el origen
			request.getSession().setAttribute("msg", "No existe el hueco de origen introducido");
			break;
		case 3://No hay cantidad suficiente del producto en el hueco
			request.getSession().setAttribute("msg", "No hay cantidad suficiente del producto en el hueco");
			break;
		case 4://No existe el destino
			request.getSession().setAttribute("msg", "No existe el hueco de destino");
			break;
		}
		return SUCCESS;
	}
	
	public void setUrl(String url) { this.url = url; }
	public String getUrl() { return url; }
}