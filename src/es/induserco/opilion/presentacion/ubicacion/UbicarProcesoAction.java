package es.induserco.opilion.presentacion.ubicacion;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Ubicacion;
import es.induserco.opilion.presentacion.GestionDesgranadoHelper;

public class UbicarProcesoAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Ubicacion ubicacion = new Ubicacion();

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	//@Override
	public Object getModel(){
		return ubicacion;
	}

	public String execute() throws Exception{
		
		System.out.println("Execute UbicarProcesoAction");
		String proceso = request.getParameter("proceso");
		String tipoProceso = request.getParameter("tipoProceso");
		if (proceso == null || proceso.compareTo("") == 0){
			proceso = (String)request.getSession().getAttribute("proceso");
		}
		request.getSession().setAttribute("reubicar", request.getParameter("reubicar"));
		request.setAttribute("reubicar", request.getParameter("reubicar"));
		request.getSession().setAttribute("ubicar", true);
		request.getSession().setAttribute("procesoSeleccion", proceso);
		if (tipoProceso == null || tipoProceso.compareTo("") == 0){
			tipoProceso = (String)request.getSession().getAttribute("tipoProceso");
		}
		request.getSession().setAttribute("tipoProceso", tipoProceso);
		Vector<GestionProduccion> vector = new GestionDesgranadoHelper().getDetallesRegistroProceso(tipoProceso, proceso);
		for (int i = 1; i < vector.size(); i++){
			GestionProduccion gp = vector.elementAt(i);
			GestionProduccion gpAnterior = vector.elementAt(i - 1);
			gp.setOrdenAnterior(gpAnterior.getOrden());
		}
		int grupo = 0;
		vector.get(0).setGrupo(grupo);
		for (int i = 1; i < vector.size(); i++){
			GestionProduccion gp = vector.elementAt(i);
			if (gp.getOrdenAnterior().compareTo(gp.getOrden()) != 0){
				gp.setGrupo(++grupo);
			}else
				gp.setGrupo(grupo);
		}
		int lineaGrupo = 0;
		vector.get(0).setLineaGrupo(lineaGrupo++);
		for (int i = 1; i < vector.size(); i++){
			GestionProduccion gp = vector.elementAt(i);
			GestionProduccion gpAnterior = vector.elementAt(i - 1);
			if (gp.getGrupo() == gpAnterior.getGrupo()){
				gp.setLineaGrupo(lineaGrupo++);
			}else{
				gp.setLineaGrupo(lineaGrupo = 0);
				gpAnterior.setUltimo(1);
				lineaGrupo++;
			}
		}
		vector.get(vector.size() - 1).setUltimo(1);
		request.getSession().setAttribute("listaUbicar", vector);
		return SUCCESS;
	}
}