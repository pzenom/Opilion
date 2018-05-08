package es.induserco.opilion.presentacion.ubicacion;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;

public class CargaMapaAlmacenAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();
	private String url;
	
	public String getUrl(){ return this.url; }
	
	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}

	public String execute() throws Exception
	{
		//En ubicacion tenemos separadas idLinea de idLineaZona
		//Ubicacion ubi = new Ubicacion();
		//ubi.setIdAlmacen(ubicacion.getIdAlmacen());
		//ubi.setIdZona(ubicacion.getIdZona());
		//ubi.setIdLinea(ubicacion.getIdLinea());
		Ubicacion u = new GestionUbicacionesHelper().getUbicacion(ubicacion);
		/*switch (Integer.parseInt(Long.toString(idAlmacen))){
		case 1://Barcia
			switch (Integer.parseInt(Long.toString(idZona))){
			case 1:
				request.getSession().setAttribute("idZona", idZona);
				return "A1Z1L1";
			case 2:
				break;
			}
			break;
		}*/
		u.setRegistro(ubicacion.getRegistro());
		if (u.getIdLinea() > 0){
			request.getSession().setAttribute("ubicacion", u);
			if (u.getIdAlmacen() == 1)
				url = "ubicacion/barciaPlanoBase_Ajax.jsp";
			//url = u.getUrlPlanoAlmacen();
			return SUCCESS;
		}else
			return ERROR;
	}
}