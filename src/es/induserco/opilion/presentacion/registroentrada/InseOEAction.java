package es.induserco.opilion.presentacion.registroentrada;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;

public class InseOEAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String url;
	public String getUrl() { return url; }
	
	private RegistroOrden entry = new RegistroOrden();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Insertar Orden de entrada Action...");
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		return entry;
	}

	public String execute() throws Exception {
        try {
        	Usuario us = (Usuario) request.getSession().getAttribute("usuario");
        	// Antes de insertar, comprobar que hay registros de entrada
        	Vector <RegistroEntrada> listare = new GestionRegistroEntradaHelper().
        		getRegistrosEntradaOrdenTmp((String)request.getSession().getAttribute("codigoorden"));
        	if(listare.size()==0) {
        		// Mensaje de aviso
        		//OptionPanel.showMessageDialog(null, "Error al insertar", "No existen registros de entrada", JOptionPane.WARNING_MESSAGE);
        		System.out.println("InseOEAction - Error al insertar. No existen registros de entrada");
        		// Purgar las tablas registroentrada_tmp, registroentrada_estado_tmp, registroentrada_incidencia_tmp
        		new GestionRegistroEntradaHelper().deleteRegistroEntradaTmp(us.getLogin());
        	}
        	else {
        		// Insertar en ordenentrada el ultimo registro de la tabla temporal
        		new GestionRegistroEntradaHelper().addRegistroOrden((RegistroOrden) 
        				new GestionRegistroEntradaHelper().getOrdenEntradaTmp((String)request.getSession().getAttribute("codigoorden"))); 
        		//new GestionRegistroEntradaHelper().addRegistroOrden(entry); 
        		// Copiar en registroentrada, registro_estado y registro_incidencias las tablas temporales 
        		new GestionRegistroEntradaHelper().addRegistrosTemporales(us.getLogin());
        		new GestionRegistroEntradaHelper().deleteRegistroEntradaTmp(us.getLogin());
        		url = "PreviaRegistroOE.action";
    			request.getSession().setAttribute("codigoOrden", (String)request.getSession().getAttribute("codigoorden"));
        	}
        	return "redirect";
        }
		catch (Exception e) {
			e.printStackTrace();
			return (ERROR);
		}
	}
}