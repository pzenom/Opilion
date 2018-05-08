package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleRETmpAction.
 */
public class DeleRETmpAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The reg entrada find. */
	private RegistroEntrada regEntradaFind = new RegistroEntrada();
	
	/** The reg entrada elimina. */
	private RegistroEntrada regEntradaElimina = new RegistroEntrada();
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Eliminar Registro Entrada Tmp Action...");
		this.request=request;	
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	//@Override
	public Object getModel() {		
		return regEntradaElimina;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		try {
			System.out.println("codigo entrada a enviar al update "+request.getSession().getAttribute("codigoEntrada"));
			regEntradaFind.setCodigoEntrada((String)request.getSession().getAttribute("codigoEntrada"));
			new GestionRegistroEntradaHelper().deleteRegistroEntradaTmp(regEntradaFind, regEntradaElimina);
			
			//request.getSession().setAttribute("listaregistrosupd",(new GestionRegistroEntradaHelper()).getRegistroEntradas(regEntradaFind.getCodigoEntrada(),regEntradaFind.getFecha(),regEntradaFind.getIdProducto()));
			Vector <RegistroEntrada>listareorden=new GestionRegistroEntradaHelper().getRegistrosEntradaOrdenTmp((String)request.getSession().getAttribute("codigoorden"));
			request.getSession().setAttribute("listareorden",listareorden);	
			return SUCCESS;
		}
		
		catch (Exception e) {
			return (ERROR);
		}
	}
}
