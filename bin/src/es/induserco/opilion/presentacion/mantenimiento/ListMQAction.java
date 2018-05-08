package es.induserco.opilion.presentacion.mantenimiento;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ListMQAction.
 */
public class ListMQAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{
	
	private HttpServletRequest request;
	private Maquina maquina= new Maquina();
	//private Vector<Maquina> maquinas = new Vector<Maquina>();
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el getModel del listado de maquinas!");
		// TODO Auto-generated method stub
		return maquina;
	}

	public String execute() throws Exception {
		try {
			//maquinas=new GestionRegistroEntradaHelper().getMaquinas();
			request.getSession().setAttribute("listaMaquinas",new GestionRegistroEntradaHelper().getMaquinas(0,0,""));
			request.getSession().setAttribute("listaTiposMaquinas",(new GestionRegistroEntradaHelper()).getTiposMaquinas());
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }			
		return (SUCCESS);
	}
}