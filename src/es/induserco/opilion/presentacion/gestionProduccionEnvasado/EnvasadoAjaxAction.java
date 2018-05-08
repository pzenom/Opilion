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

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.RegistroEnvasado;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionProductosHelper;
import es.induserco.opilion.presentacion.GestionRegistroEnvasesHelper;
import es.induserco.opilion.presentacion.GestionRegistroIngredientesHelper;

public class EnvasadoAjaxAction extends ActionSupport 
implements org.apache.struts2.interceptor.ServletRequestAware,ModelDriven<Object>{

	private HttpServletRequest request;
	private GestionProduccion gprod= new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;	
	}

	//@Override
	public Object getModel() {
		System.out.println("Execute EnvasadoAjaxAction");
		return gprod;
	}
	
	public String execute() throws Exception {
		System.out.println("Execute EnvasadoAjax");
		String nombreParametro = "", eanSeleccionado = "", observaciones = "", fechaLlegada = "";
		double cantidadEnvases = 0, cantidadNecesaria = 0;
		List<Producto> productos = new ArrayList<Producto>();
		List <LineaProducto> in = new ArrayList();
		List <LineaProducto> en = new ArrayList();
		RegistroEnvasado envasado = new RegistroEnvasado();
		Map <String, String[]> parametros = request.getParameterMap();
		// paso por todos los parametros
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			LineaProducto producto = new LineaProducto ();
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro=(String) e.getKey();
			if(nombreParametro.compareTo("productoSeleccionado") == 0) {
				//Obtenemos el codigo ean del producto seleccionado
				eanSeleccionado = ((String[])parametros.get(e.getKey()))[0];
			} else
				if(nombreParametro.indexOf("teorica_env_") > -1) {
					cantidadEnvases += Double.parseDouble(((String[])parametros.get(e.getKey()))[0]);
					producto.setRegistroEntrada(nombreParametro.substring(nombreParametro.indexOf("teorica_env_")+12));
					producto.setTeorica(Double.parseDouble(((String[])parametros.get(e.getKey()))[0]));
					en.add(producto);
				}else
					if(nombreParametro.indexOf("teorica_ing_") > -1) {
						producto.setRegistroEntrada(nombreParametro.substring(nombreParametro.indexOf("teorica_ing_")+12));
						producto.setTeorica(Double.parseDouble(((String[])parametros.get(e.getKey()))[0]));
						in.add(producto);
					}else
						if (nombreParametro.compareToIgnoreCase("explica") == 0)
							observaciones = ((String[])parametros.get(e.getKey()))[0];
						else
							if (nombreParametro.compareToIgnoreCase("fechaLlegada") == 0)
								fechaLlegada = ((String[])parametros.get(e.getKey()))[0];
							else
								if(nombreParametro.compareTo("cantidadNecesaria") == 0) {
									cantidadNecesaria = Double.parseDouble(((String[])parametros.get(e.getKey()))[0]);
								}else
									if(nombreParametro.indexOf("producto_") > -1) {
										Producto pr = new Producto();
										pr.setCodigoEan(((String[])parametros.get(e.getKey()))[0]);
										productos.add(pr);
									}
		}
		
		Producto p = new Producto();
		p.setCodigoEan(eanSeleccionado);
		Producto aux = (Producto) new GestionProductosHelper().getProductos(p, false).get(0);
		//Si entra por aquí es porque va a envasar
		if (productos.size() > 0){
			//Si llegan suficientes ingrediente y envases para envasar, realizar envasado,
			//y quitar el producto de la lista de productos a envasar
			List<Producto> productosEnvasar = new ArrayList<Producto>();
			if (cantidadEnvases >= cantidadNecesaria){
				//1. Preparar orden de envasado (Grabar)
				envasado.setMateriaPrima(in);
				envasado.setEnvases(en);
				envasado.setOperario(((Usuario) request.getSession().getAttribute("usuario")).getLogin());
				envasado.setObservaciones(observaciones);
				envasado.setCantidad((int)cantidadNecesaria);
				
				Date fechaCaducidad = new Date();
				Calendar c = Calendar.getInstance();
				String frag[] = fechaLlegada.split("/");
				c.set(Integer.parseInt(frag[2]), Integer.parseInt(frag[1]),
						Integer.parseInt(frag[0]));
				c.add(Calendar.MONTH, 18);
				fechaCaducidad = c.getTime();
				//System.out.println(c.getTime());
				System.out.println("Dia de caducidad: " + fechaCaducidad.getDay());
				System.out.println("Mes de caducidad: " + fechaCaducidad.getMonth());
				int dif = fechaCaducidad.getYear() + 1900;
				int anio = 0;
				System.out.println("Año caducidad: " + (anio = (dif + 1900)));
				fechaCaducidad.setYear(anio);
				envasado.setFechaCaducidad(fechaCaducidad);
				
				envasado.setIdProducto(aux.getIdProducto());
				
				//insertar en la BD como proceso pendiente
				if((new GestionEnvasadoHelper()).addOrdenEnvasado(envasado))
					System.out.println("Orden de envasado creada");
				
				//2. Quitar producto de la lista de productos a envasar (Volver a crear la lista, pero no insertar el producto)
				Iterator iter = productos.iterator();
				Producto pro;
				while (iter.hasNext()){
					pro = (Producto) iter.next();
					pro = (Producto) new GestionProductosHelper().getProductos(pro, false).get(0);
					if (pro.getCodigoEan().compareTo(eanSeleccionado) != 0)
						productosEnvasar.add(pro);
				}
			} else
				productosEnvasar = productos;
			request.getSession().setAttribute("listaproductosenvasar", productosEnvasar);
			request.getSession().setAttribute("productoSeleccionado", productosEnvasar.get(0).getCodigoEan());
			request.getSession().setAttribute("codigoEan", productosEnvasar.get(0).getCodigoEan());
			request.getSession().setAttribute("descripcion", productosEnvasar.get(0).getDescripcion());
			request.getSession().setAttribute("stock", productosEnvasar.get(0).getStock());
			request.getSession().setAttribute("cantidad", productosEnvasar.get(0).getCantidad());
			eanSeleccionado = productosEnvasar.get(0).getCodigoEan();
		}else
		//Si no, cambia de producto
		{
			Producto prod = new Producto();
			prod.setCodigoEan(eanSeleccionado);
			prod = (Producto) new GestionProductosHelper().getProductos(prod, false).get(0);
			//request.getSession().setAttribute("productoSeleccionado", prod.getCodigoEan());
			request.getSession().setAttribute("codigoEan", prod.getCodigoEan());
			request.getSession().setAttribute("descripcion", prod.getDescripcion());
			request.getSession().setAttribute("stock", prod.getStock());
			request.getSession().setAttribute("cantidad", prod.getCantidad());
		}

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

		//listaregistroingredientes
		//listaregistroenvases
		//Cargamos la fecha actual
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(date);
		request.getSession().setAttribute("fechaRegistro", fecha);
		
		//Cargamos los productos que tenemos que envasar
		/*request.getSession().setAttribute("listaproductosenvasar", productos);
		request.getSession().setAttribute("productoSeleccionado", productos.get(0).getCodigoEan());*/
		//request.getSession().setAttribute("codigoEan", aux.getCodigoEan());
		//request.getSession().setAttribute("descripcion", aux.getDescripcion());
		//request.getSession().setAttribute("stock", aux.getStock());
		
		return SUCCESS;
	}    
}