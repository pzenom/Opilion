package es.induserco.opilion.presentacion.registros;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;

public class ConsRegiENEanAction extends ActionSupport implements
    org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
    
    private HttpServletRequest request;
    private Producto entry = new Producto();

    //@Override
    public void setServletRequest(HttpServletRequest request) {
        System.out.println("Salida Action...");
        this.request=request;   
    }

    //@Override
    public Object getModel() {
        return entry;
    }

    public String execute() throws Exception {
        String codigoEan=(String)request.getParameter("codigoEan");
		String linNum=(String)request.getParameter("linNum");
		String qty21Cant=(String)request.getParameter("qty21Cant");
		//Sube a la sesión
		request.getSession().setAttribute("linNum",linNum);
		request.getSession().setAttribute("qty21Cant",qty21Cant);
        //consulta los productos envasados correspondientes a ese EAN
        if(!codigoEan.equals(null))
            request.getSession().setAttribute("listaregienvasados",(new GestionRegistroEntradaHelper()).getREENProducto(codigoEan));
		System.out.println(" Numero de linea de la orden es " + linNum);
        return (SUCCESS);
    }
}