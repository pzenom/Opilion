package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import net.sf.jasperreports.engine.JasperCompileManager;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class ConsREJRAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private RegistroEntrada entrada= new RegistroEntrada();
	private Vector re = new Vector();
	
	public Vector getRe() {
		return re;
	}

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("procesando el execute de Consulta Registro Entrada!");
		return entrada;
	}

	public String execute() throws Exception {
		//Colocamos la lista de entradades en la request.
		request.getSession().setAttribute("codigoEntrada",(entrada.getCodigoEntrada()));
		request.getSession().setAttribute("listaentradas",
				(new GestionRegistroEntradaHelper()).getRegistroEntradas(entrada.getCodigoEntrada(),
						entrada.getFecha(),entrada.getIdProducto()));
		request.getSession().setAttribute("listaenvases",
				(new GestionRegistroEntradaHelper()).getEnvases());
		//Levantamos el evento success para especificar que todo ha ido bien
		re = new GestionRegistroEntradaHelper().getRegistroEntradas(null,null,null);
		//generación del reporte
		try {
            JasperCompileManager.compileReportToFile(
            		/*"C:\\reportes\\ConsREJR.jrxml",
            		"C:\\reportes\\ConsREJR.jasper");*/
            		"C:\\reportes\\ROrden.jrxml",
    				"C:\\reportes\\ROrden.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
		return SUCCESS;
	}
}