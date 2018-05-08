package es.induserco.opilion.presentacion.producto;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.presentacion.GestionProductosHelper;

public class ActualizaProductoAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Producto entry = new Producto();
	
	//@Override
	public Object getModel() {	
		return entry;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception {
		System.out.println("PRESENTACION: Actualiza Producto");
		/*
		 * 1. Borrar toda la informaci�n que hay del producto (Tablas: producto, producto_compuesto, producto_materiaprima, producto_envase
		 * OJO. Borrado "especial", conservar el id del producto.
		 * Borrado normal, pero al volver a insertar, hacer un insertado especial, especificando el id del producto
		 */
		long idProducto = entry.getIdProducto();
		Producto producto = new GestionProductosHelper().getProducto(idProducto);
		//Eliminamos el producto (Toda la informacion del producto en las 4 tablas)
		boolean borradoExitoso = new GestionProductosHelper().eliminarProducto(idProducto);
		if (!borradoExitoso)
			return ERROR;
		//Volvemos a insertar el producto, especificando el id que tendr�, que es el mismo que ten�a antes
		//Tabla 'producto' actualizada
		boolean inserta = new GestionProductosHelper().addProducto(entry, true, idProducto);
		if (!inserta)
			return ERROR;
		Map parametros = request.getParameterMap();
		Vector<double[]> materias = new Vector<double[]>();
		Vector<double[]> EANs13 = new Vector<double[]>();
		Vector<double[]> envases = new Vector<double[]>();
		Vector<double[]> compuesto = new Vector<double[]>();
		String nombreParametro = "";
		String frag[];
		Iterator iterator = parametros.entrySet().iterator();
		//En primer lugar cargamos en una tabla los envases del producto y las cantidades de envases que asociamos al producto
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if (nombreParametro.startsWith("EN_")){//Encuentro un envase
				frag = nombreParametro.split("_");
				int idEnvase = Integer.parseInt(frag[1]);
				//envases.add(idEnvase);
				Iterator i = parametros.entrySet().iterator();
				//Buscamos la cantidad de envases
				String nombrePar = "";
				while(i.hasNext()) {
					Map.Entry entrada = (Map.Entry) i.next();
					nombrePar = (String) entrada.getKey();
					if (nombrePar.compareTo("CANTIDAD_EN_" + idEnvase) == 0){
						double[] fila = {idEnvase, Double.parseDouble(((String[])parametros.get(entrada.getKey()))[0])};
						envases.add(fila);
						break;
					}
				}
			}
		}
		
		//REPETIMOS lo mismo para las materias primas
		iterator = parametros.entrySet().iterator();
		//En primer lugar cargamos en una tabla las materias del producto y las cantidades de materias que asociamos al producto
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if (nombreParametro.startsWith("MP_")){//Encuentro un envase
				frag = nombreParametro.split("_");
				int idMateria = Integer.parseInt(frag[1]);
				//envases.add(idMateria);
				Iterator i = parametros.entrySet().iterator();
				//Buscamos la cantidad de envases
				String nombrePar = "";
				while(i.hasNext()){
					Map.Entry entrada = (Map.Entry) i.next();
					nombrePar = (String) entrada.getKey();
					if (nombrePar.compareTo("CANTIDAD_MP_" + idMateria) == 0){
						double[] fila = {idMateria, Double.parseDouble(((String[])parametros.get(entrada.getKey()))[0])};
						materias.add(fila);
						break;
					}
				}
			}
		}
		
		//REPETIMOS lo mismo para las materias primas
		iterator = parametros.entrySet().iterator();
		//En primer lugar cargamos en una tabla las materias del producto y las cantidades de materias que asociamos al producto
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if (nombreParametro.startsWith("EAN13_")){//Encuentro un envase
				frag = nombreParametro.split("_");
				int idPro = Integer.parseInt(frag[1]);
				//envases.add(idMateria);
				Iterator i = parametros.entrySet().iterator();
				//Buscamos la cantidad de envases
				String nombrePar = "";
				while(i.hasNext()){
					Map.Entry entrada = (Map.Entry) i.next();
					nombrePar = (String) entrada.getKey();
					if (nombrePar.compareTo("CANTIDAD_EAN13_" + idPro) == 0){
						double[] fila = {idPro, Double.parseDouble(((String[])parametros.get(entrada.getKey()))[0])};
						EANs13.add(fila);
						break;
					}
				}
			}
		}
		
		//REPETIMOS lo mismo para los productos que componen el nuevo producto
		iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			if (nombreParametro.startsWith("Item_")){//Encuentro un envase
				frag = nombreParametro.split("_");
				int idCompone = Integer.parseInt(frag[1]);
				//envases.add(idMateria);
				Iterator i = parametros.entrySet().iterator();
				//Buscamos la cantidad de envases
				String nombrePar = "";
				while(i.hasNext()) {
					Map.Entry entrada = (Map.Entry) i.next();
					nombrePar = (String) entrada.getKey();
					String compara = "CANTIDAD_Item_" + idCompone;
					if (nombrePar.compareTo(compara) == 0){
						double[] fila = {idCompone, Double.parseDouble(((String[])parametros.get(entrada.getKey()))[0])};
						compuesto.add(fila);
						break;
					}
				}
			}
		}
		
		//1. Guardar los envases asociados al producto
		if (envases.size() > 0)
			if (! (new GestionProductosHelper().setEnvasesProducto(idProducto, envases)))
				return ERROR;
		//2. Guardar las materias primas asociadas al producto
		if (materias.size() > 0)
			if (! (new GestionProductosHelper().setMateriasPrimasProducto(idProducto, materias)))
				return ERROR;
		//2.1 Guardar los EANs13 asociados al producto
		if (EANs13.size() > 0)
			if (! (new GestionProductosHelper().setEANs13Producto(idProducto, EANs13)))
				return ERROR;
		//3. Guardar los componentes que forman el producto
		if (compuesto.size() > 0)
			if (! (new GestionProductosHelper().setProductoCompuesto(idProducto, compuesto)))
				return ERROR;
		//Guardamos la fecha original de creaci�n del producto
		new GestionProductosHelper().setCampoProducto(idProducto, "fecha", producto.getFecha().toString());
		
		//Subimos la imagen
		try {
			boolean imagenActualizada = false;
			//Nombre de la imagen
			System.out.println("Image name: " + userImageFileName);
			//Ruta donde vamos a guardar la imagen
			String filePath = request.getRealPath("/img/productos");
			System.out.println("Server path:" + filePath);
			//Creamos el fichero donde copiaremos la imagen
			if (userImageFileName != null){
				File fileToCreate = new File(filePath, this.userImageFileName);
				//Copiamos la imagen
				FileUtils.copyFile(this.userImage, fileToCreate);
				//Guardar ruta de la imagen en la BD
				imagenActualizada =
					new GestionProductosHelper().setCampoProducto(idProducto, "foto", userImageFileName);
			}else
				//Si no se selecciona ninguna imagen, escribimos en la BD la imagen antigua
				imagenActualizada =
					new GestionProductosHelper().setCampoProducto(idProducto,
							"foto", producto.getFoto());
			} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}
}