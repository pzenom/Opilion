package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class ConsProdFinaAction extends ActionSupport implements ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();
	/*private HashMap<String, String> items;
	private HashMap<String, String> agrupados;*/
	
	/*public HashMap<String, String> getItems() {return items;}
	public void setItems(HashMap<String, String> items) {this.items = items;}
	public HashMap<String, String> getAgrupados() {return agrupados;}
	public void setAgrupados(HashMap<String, String> agrupados) {this.agrupados = agrupados;} */
	
	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("Execute ConsProdFinaAction");
		return gprod;
	}

	public String execute() throws Exception {
		//sacamos los procesos de envasado pendientes
		request.getSession().setAttribute("envasadoPendientes",
				(new GestionEnvasadoHelper()).getProcesosPendientes(gprod, "E"));
		//Colocamos las listas necesarias en la request.
		//Lista de Productos Finales: productos presentacion
		Vector<Producto> eans13 = (new GestionEnvasadoHelper()).getPresentacionProductos(true);
		Vector<Producto> eans14 = (new GestionEnvasadoHelper()).getAgrupaciones(true);
		//Los productos que est�n en eans14, hay que sacarlos de eans13
		for (int i = 0; i < eans14.size(); i++){
			Producto p = eans14.get(i);
			long idProducto = p.getIdProducto();
			for (int j = 0; j < eans13.size(); j++){
				Producto p2 = eans13.get(j);
				long id2 = p2.getIdProducto();
				if (idProducto == id2){
					eans13.remove(j);
					break;
				}
			}
		}
		
		/*Vector<Producto> suma = new Vector<Producto>();
		suma.addAll(eans13);
		suma.addAll(eans14);*/
		//request.getSession().setAttribute("items", eans13);
		//request.getSession().setAttribute("agrupados", eans14);
		
		/*items = new HashMap<String, String>();
		agrupados = new HashMap<String, String>();
		for (int i = 0; i < eans13.size(); i++){
			items.put(eans13.get(i).getIdProducto().toString() + "_" + 1,
					eans13.get(i).getNombre());
		}
		for (int i = 0; i < eans14.size(); i++){
			//agrupados.put(eans14.get(i).getIdProducto().toString() + "_" + 2,	eans14.get(i).getNombre());
		}*/
		
		request.getSession().setAttribute("vacia", new Vector<Producto>());
		request.getSession().setAttribute("eans13", eans13);
		request.getSession().setAttribute("eans14", eans14);
		
		//request.getSession().setAttribute("cboproductospresentacion", suma);
		//request.getSession().setAttribute("true", "true");
		
		request.getSession().setAttribute("listaregistroingredientes", null);
		
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(date);
		request.getSession().setAttribute("fechaRegistro", fecha);
		//Date registro = new Date();
		//registro.
		
		//listaregistroingredientes
		//listaregistroenvases
		
		return (SUCCESS);
	}
}