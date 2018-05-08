package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;

public class SeleccionPisoAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();

	public String execute() throws Exception{
		long idZona = ubicacion.getIdZona();
		long idAlmacen = ubicacion.getIdAlmacen();
		long idLinea = ubicacion.getIdLinea();
		long idEstanteria = ubicacion.getIdEstanteria();
		long idPiso = ubicacion.getIdPiso();
		
		request.getSession().setAttribute("idZona", idZona);
		request.getSession().setAttribute("idAlmacen", idAlmacen);
		switch (Integer.parseInt(Long.toString(idAlmacen))){
		case 1://Barcia
			//INCORRECTO. idZona = idLinea
			switch (Integer.parseInt(Long.toString(idZona))){
			case 1:
				switch (Integer.parseInt(Long.toString(idLinea))){
				case 1:
					switch (Integer.parseInt(Long.toString(idEstanteria))){
					case 1:
						switch (Integer.parseInt(Long.toString(idPiso))){
						case 1:
							return "A1Z1L1E1P1";
						case 2:
							return "A1Z1L1E1P2";
						case 3:
							return "A1Z1L1E1P3";
						}
						break;
					}
					break;
				}
			case 2:
				break;
			}
			break;
		}
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest httpServletRequest) { this.request = httpServletRequest; }
	public Object getModel(){ return ubicacion; }
}