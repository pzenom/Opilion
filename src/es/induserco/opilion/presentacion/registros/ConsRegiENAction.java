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

// TODO: Auto-generated Javadoc
/**
 * The Class ConsRegiENAction.
 */
public class ConsRegiENAction extends ActionSupport implements
    org.apache.struts2.interceptor.ServletRequestAware, ModelDriven
{
    
    /** The request. */
    private HttpServletRequest request;
    
    /** The entry. */
    private Producto entry = new Producto();

    /* (non-Javadoc)
     * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
     */
    //@Override
    public void setServletRequest(HttpServletRequest request) {
        System.out.println("Salida Action...");
        this.request=request;   
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    //@Override
    public Object getModel() {
        return entry;
    }
   
    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute() throws Exception {
        String albaran=null;
        //request.getSession().setAttribute("albaran",(request.getAttribute("albaran")));
        albaran=request.getParameter("albaran");
        albaran=(String)request.getAttribute("albaran");
       
        String codigoEan=request.getParameter("codigoEan");
        System.out.println("codigoean "+codigoEan);
        
        //if(codigoEan.equals(null))
      /*
        if(codigoEan.length()!= 0 || codigoEan != null)
        	
        	System.out.println("codigoean "+codigoEan);
            //request.getSession().setAttribute("listaregienvasados",(new GestionRegistroEntradaHelper()).getREENProducto(codigoEan));
        else
            {
          */
            //Colocamos la lista de gprodes en la request.
            request.getSession().setAttribute("idProducto",entry.getIdProducto());
            System.out.println("el id prod pasado es "+entry.getIdProducto());
            request.getSession().setAttribute("listaregienvasados",(new GestionRegistroEntradaHelper()).getREENProducto(entry.getIdProducto()));
            //}
        
        return (SUCCESS);
    }
}