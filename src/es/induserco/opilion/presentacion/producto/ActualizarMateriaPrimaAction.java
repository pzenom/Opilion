package es.induserco.opilion.presentacion.producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ActualizarMateriaPrimaAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private MateriaPrima materiaFind = new MateriaPrima();
	private MateriaPrima mpActualiza = new MateriaPrima();

	//@Override
	public Object getModel() {	
		return mpActualiza;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception {	
		Map <String, String[]> parametros = request.getParameterMap();
		ArrayList<Long> categorias = new ArrayList<Long>();
		String nombreParametro = "";
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro=(String) e.getKey();
			if(checkIfNumber(nombreParametro)) {
				categorias.add(Long.parseLong(nombreParametro));
			}
		}
	
		mpActualiza.setCategorias(categorias);
		//request.getSession().setAttribute("resultado", new GestionProductosHelper().addMateriaPrima(entry));
		//long idMateriaPrima = Long.parseLong(request.getSession().getAttribute("resultado").toString());
		/*request.getSession().setAttribute("categorias" ,
				new GestionProductosHelper().getCategoriasMateria((long)idMateriaPrima));*/
		request.getSession().setAttribute("categorias" ,
				new GestionProductosHelper().getCategoriasMateria(mpActualiza.getIdMateriaPrima()));
		System.out.println("PRESENTACION: Actualizar Materia Prima");
		materiaFind.setIdMateriaPrima((Long)request.getSession().getAttribute("idMateriaPrima"));
		new GestionProductosHelper().updateMateriaPrima(materiaFind, mpActualiza);
		request.getSession().setAttribute("listaregistros",(new GestionProductosHelper()).loadMateriaPrima(materiaFind));		
		return SUCCESS;
	}
	
	private boolean checkIfNumber(String in){
		try {
			Integer.parseInt(in);
		}
		catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
}