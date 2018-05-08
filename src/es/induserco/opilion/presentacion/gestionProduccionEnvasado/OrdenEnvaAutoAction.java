package es.induserco.opilion.presentacion.gestionProduccionEnvasado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;
import es.induserco.opilion.presentacion.GestionRegistroIngredientesHelper;

/**
 * Genera una orden de envasado de forma automatica
 * @author andres (29/04/2011)
 * @version 0.1
 */
public class OrdenEnvaAutoAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();
	
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
		System.out.println("execute OrdenEnvaAuto...");
		String nombreParametro = "";
		List<RegistroEnvasado> registrosEnvasado = new ArrayList<RegistroEnvasado>();
		List<Producto> productos = new ArrayList<Producto>();
		
		Map <String, String[]> parametros = request.getParameterMap();
		// paso por todos los parametros
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro=(String) e.getKey();
			if(nombreParametro.indexOf("ean_") > -1) {
				RegistroEnvasado envasado = new RegistroEnvasado();
				//Obtenemos el codigo ean del producto que hay que envasar
				String ean = ((String[])parametros.get(e.getKey()))[0];
				String cantidad = "";
				Iterator itera = parametros.entrySet().iterator();
				while(itera.hasNext()) {
					Map.Entry ee = (Map.Entry) itera.next();
					nombreParametro=(String) ee.getKey();
					if(nombreParametro.compareTo("envasar_" + ean) == 0) {
						cantidad = ((String[])parametros.get(ee.getKey()))[0];
					}
				}
				Producto producto = new Producto();
				producto.setCodigoEan(ean);
				producto.setCantidad(Double.parseDouble(cantidad));
				//A partir del ean, obtener info del producto: Descripcion,
				producto.setDescripcion(((Producto)new GestionProductosHelper().getProductos(producto, false).elementAt(0)).
						getDescripcion());
				envasado.setProducto(producto);
				productos.add(producto);
				registrosEnvasado.add(envasado);
			}
		}
		//Cargamos la fecha actual
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(date);
		request.getSession().setAttribute("fechaRegistro", fecha);
		
		//Cargamos los productos que tenemos que envasar
		request.getSession().setAttribute("listaproductosenvasar", productos);
		request.getSession().setAttribute("productoSeleccionado", productos.get(0).getCodigoEan());
		request.getSession().setAttribute("codigoEan", productos.get(0).getCodigoEan());
		request.getSession().setAttribute("descripcion", productos.get(0).getDescripcion());
		request.getSession().setAttribute("stock", productos.get(0).getStock());
		request.getSession().setAttribute("cantidad", productos.get(0).getCantidad());
		
		//Cargamos los envases asociados al primer producto
		Producto p = productos.get(0);
		Producto aux = (Producto)new GestionProductosHelper().getProductos(p, false).get(0);
		Vector<LineaProducto> envases =
			//(Vector<LineaProducto>)(new GestionRegistroEnvasesHelper()).getEnvasesProducto(aux.getIdProducto(), "");
			(Vector<LineaProducto>)(new GestionRegistroEnvasesHelper()).getEnvasesProducto(aux.getIdProducto(), "");
		int contador = 0;
		long idAnterior = 0;
		contador = envases.size();
		//Para poder mostrar los lotes de envases agrupados para cada envase
		for (int j = 1; j < contador; j++)
		{
			LineaProducto envase = (LineaProducto) envases.elementAt(j - 1);
			idAnterior = envase.getIdLinea();
			envases.elementAt(j).setIdAnterior(idAnterior);
		}
		request.getSession().setAttribute("listaregistroenvases", envases);
		
		//Cargamos las materias primas asociadas al primer producto
		Vector<LineaProducto> materias =
			(Vector<LineaProducto>)(new GestionRegistroIngredientesHelper()).getIngredientesProducto(aux.getIdProducto(), "");
		contador = 0;
		idAnterior = 0;
		contador = materias.size();
		for (int i = 1; i < contador; i++)
		{
			LineaProducto materia = (LineaProducto) materias.elementAt(i - 1);
			idAnterior = materia.getIdLinea();
			materias.elementAt(i).setIdAnterior(idAnterior);
		}
		request.getSession().setAttribute("listaregistroingredientes", materias);
		
		//sacamos los procesos de envasado pendientes
		//request.getSession().setAttribute("envasadoPendientes",(new GestionEnvasadoHelper()).getProcesosPendientes("E"));
		
		//Colocamos las listas necesarias en la request.
		//Lista de Productos Finales: productos presentacion
		//request.getSession().setAttribute("cboproductospresentacion",(new GestionEnvasadoHelper()).getPresentacionProductos(true));
		
		return SUCCESS;
	}
}