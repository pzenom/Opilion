package es.induserco.opilion.presentacion.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.entidades.Backup;
import es.induserco.opilion.data.entidades.Factura;
import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.entidades.GestionRoles;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;
import es.induserco.opilion.presentacion.GestionRegistroEntradaHelper;
import es.induserco.opilion.presentacion.GestionRegistroSalidaHelper;
import es.induserco.opilion.presentacion.GestionUbicacionesHelper;
import es.induserco.opilion.presentacion.GestionUsuariosHelper;
import es.induserco.opilion.presentacion.GestionesEspecialesHelper;

public class LoginAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Usuario usuario = new Usuario();
    private PrintWriter pw = null;

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public String execute() throws Exception {
		//System.out.println("PRESENTACION: procesando el execute de Login");
		//System.out.println("Usuariolog: " + usuario.getLogin());
		//System.out.println("Password: " + usuario.getPassword());
		request.getSession().setAttribute("usuario", usuario);
		request.getSession().setAttribute("rolesUsuario", new GestionUsuariosHelper().getUsuarioCompleto(usuario).getRoles());
		request.getSession().setAttribute("mensajes", new GestionUsuariosHelper().getUsuarioCompleto(usuario).getMensajes());
		request.setAttribute("usuarioValidar",
				(new GestionUsuariosHelper()).getUsuarioValidar(usuario));	
		comprobarMantenimientos();
		comprobarBackup();
		if (request.getAttribute("usuarioValidar").equals(true)) {
			//responseMap.put("success", true);
			request.getSession().setAttribute("usuario", usuario);
			//MOSTRAMOS LA FECHA Y LA HORA
			Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora = calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String dia = Integer.toString(calendario.get(Calendar.DATE));
			String mes = Integer.toString(calendario.get(Calendar.MONTH) + 1);
			String annio = Integer.toString(calendario.get(Calendar.YEAR));
			System.out.println("\r\n----------------");
			System.out.println("USUARIO CONECTADO: " + usuario.getLogin());
			System.out.println("FECHA: " + dia + "/" + mes + "/" + annio);
			System.out.println("HORA: " + hora + ":" + minutos + ":" + segundos);
			System.out.println("----------------\r\n");
			request.getSession().setAttribute("listavehiculos", (new GestionUbicacionesHelper()).getVehiculos());
			request.getSession().setAttribute("comerciales", (new GestionUsuariosHelper()).getComerciales());
			request.getSession().setAttribute("rutas", (new GestionUsuariosHelper()).getRutas());
			//Cargamos las facturas para mostrar en el calendario
			Factura f = new Factura();
			Vector<Factura> facturas = new GestionRegistroSalidaHelper().getFacturas(f, null, null, usuario);
			request.getSession().setAttribute("facturas", facturas);
			request.getSession().setAttribute("listaclientes",(new GestionRegistroSalidaHelper()).getClientes());
			request.getSession().setAttribute("listaestados",(new GestionRegistroSalidaHelper()).getEstadosFacturas());
			Vector<GestionProduccion> envasados = (Vector<GestionProduccion>) new GestionEnvasadoHelper().getProcesosPendientes(null, "S");
			request.getSession().setAttribute("listaregienvasados", envasados);
			request.getSession().setAttribute("listaproductos",
					(new GestionEnvasadoHelper()).getPresentacionProductos(false));
			GestionRoles gestionRoles = new GestionEntidadesHelper().getRoles();
			request.getSession().setAttribute("gestionRoles", gestionRoles);
			return SUCCESS;
		}
		else{
			System.out.println("LOGIN: Usuario no validado");
			return "failure";
		}
	}

	//logout
	public String logout() throws Exception {
	        return SUCCESS;
	}

	//@Override
	public Object getModel() {
		usuario.setLogin(request.getParameter("login"));
		usuario.setPassword(request.getParameter("pass"));
		return usuario;
	}

	public String getLogin(){return usuario.getLogin();}
	public String getPassword(){return usuario.getPassword();}	
	public void setLogin(String login){request.setAttribute("login",usuario.getLogin());}
	public void setPassword(String password){request.setAttribute("password",usuario.getPassword());}
	
	private void comprobarMantenimientos() throws Exception{
		new GestionRegistroEntradaHelper().checkMT();
	}
	
	private void comprobarBackup() throws Exception {    	
		boolean error = false;
		Backup ultimo = new GestionesEspecialesHelper().getUltimoBackupAuto();
		String fechaUltimo = ultimo.getFecha();
		int annio = 0, mes = 0, dia = 0;
		long diferencia = 0;
		if (fechaUltimo != null){
			//String hoy = dateFormat.format(calendar.getTime());
			//Comparar fechas
			//Si el numero de dias es menor que 7, hacer copia de seguridad y actualizar la base de datos
			final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
			java.util.Date hoy = new Date(); //Fecha de hoy
			String frag[] = fechaUltimo.split("-");
			annio = Integer.parseInt(frag[0]);
			mes = Integer.parseInt(frag[1]);
			dia = Integer.parseInt(frag[2]);
			Calendar programada = new GregorianCalendar(annio, mes - 1, dia); 
			java.sql.Date fechaProgramada = new java.sql.Date(programada.getTimeInMillis());
			System.out.println("Milisegundos hoy: " + hoy.getTime());
			System.out.println("Milisegundos programada: " + fechaProgramada.getTime());
			diferencia = (hoy.getTime() - fechaProgramada.getTime() ) / MILLSECS_PER_DAY;
			System.out.println("Diferencia en dias entre " + fechaUltimo
					+ " y " + hoy + " es de " + diferencia + " dias");
		}
		if (diferencia > 1 || fechaUltimo == null){//HACER COPIA DE SEGURIDAD y actualizar la base de datos
			int BUFFER = 10485760;
			//Para guardar en memmoria
		    StringBuffer temp = null;
		    //Para guardar el archivo SQL
		    FileWriter fichero = null;
	    	String mensaje = "";
		    try{
		    	String ruta = "";
		    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				String path = loader.getResource("configuracion.xml").toURI().getPath();
				Document doc = dBuilder.parse(new File(path));
				doc.getDocumentElement().normalize();
				//System.out.println("El elemento raiz es: " + doc.getDocumentElement().getNodeName());
				NodeList listaNodosHijos = doc.getElementsByTagName("dbBackUp");
				//System.out.println("Existen "+ listaNodosHijos.getLength() +" nodos hijo de tipo baseDatos");
				Node configBD = listaNodosHijos.item(0);
				
				Element elemento = (Element) configBD;
				String host = getTagValue("host", elemento);
				String db = getTagValue("db", elemento);
				String puerto = getTagValue("puerto", elemento);
				String fileName = getTagValue("fileName", elemento);
				String rutaProceso = getTagValue("rutaProceso", elemento);
				String user = getTagValue("user", elemento);
				String password = getTagValue("pass", elemento);
				String filePath = getTagValue("filePath", elemento);
				String rutaBackups = getTagValue("rutaBackups", elemento);
				
				//Preparamos el nombre del fichero de salida
				//Calendar calendario = Calendar.getInstance();
				Calendar calendario = new GregorianCalendar();
				int hora, minutos, segundos, ms;
				hora = calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				ms = calendario.get(Calendar.MILLISECOND);
				dia = calendario.get(Calendar.DAY_OF_MONTH);
				mes = calendario.get(Calendar.MONTH) + 1;
				annio = calendario.get(Calendar.YEAR);
				fileName += "_" + dia + "_" + mes + "_" + annio + "_" + hora +
					"_" + minutos + "_" + segundos + "_" + ms + ".sql";
		    	//Sentencia para crear el BackUp
		    	//String ruta = "C:\\xampp\\mysql\\bin\\mysqldump " +
		        ruta = rutaProceso + 
		    			" --host=" + host + " --port=" + puerto +
				        " --user=" + user + " --password=" + password +
				        " --compact --complete-insert --extended-insert --skip-quote-names" +
				        " --skip-comments --skip-triggers " + db;
		    	System.out.println(ruta);
		    	Process run = Runtime.getRuntime().exec(ruta);
		    	//1. Leemos los errores
		    	InputStream er = run.getErrorStream();
		    	BufferedReader brEr = new BufferedReader(new InputStreamReader(er));
			    temp = new StringBuffer();
			    int count = 0;
			    char[] cbuf = new char[BUFFER];
			    if (brEr.ready())
			    	while ((count = brEr.read(cbuf, 0, BUFFER)) != -1)
			    		temp.append(cbuf, 0, count);
		        brEr.close();
		        er.close();
		        System.out.println(temp.toString());
		        //mysqldump: Got error: 1044: Access denied for user 'opilion_app'@'%' to database 'bdd_trazabilidad' when using LOCK TABLES
		        //mysqldump: Got error: 1045: Access denied for user 'andres'@'INDUPC05' (using password: YES) when trying to connect
	        	if (temp.toString().indexOf("Got error: 1044:") > -1){
		        	mensaje = "Usuario sin permisos suficientes";
		        	System.out.println(mensaje);
		        	request.getSession().setAttribute("mensajeError", mensaje);
		        	error = true;
		        } else
		        	if (temp.toString().indexOf("Got error: 1045:") > -1){
		        		mensaje = "Acceso denegado para el usuario " + user + ".";
			        	System.out.println(mensaje);
			        	request.getSession().setAttribute("mensajeError", mensaje);
			        	error = true;
			        }if (!error){
				    	//2. Leemos la salida normal de la ejecucion
				    	InputStream in = run.getInputStream();
					    BufferedReader br = new BufferedReader(new InputStreamReader(in));
					    temp = new StringBuffer();
					    count = 0;
					    cbuf = new char[BUFFER];
				        while ((count = br.read(cbuf, 0, BUFFER)) != -1)
				            temp.append(cbuf, 0, count);
				        br.close();
				        in.close();
				        fichero = new FileWriter(filePath + rutaBackups + fileName);
				        pw = new PrintWriter(fichero);
				        pw.println(temp.toString());
			        	request.getSession().setAttribute("path", rutaBackups + fileName);
			        	System.out.println("Copia de seguridad realizada con éxito. " +
			        			"Actualizando la base de datos...");
			        	new GestionesEspecialesHelper().registroBackupAuto();
			        	System.out.println("Base de datos actualizada con éxito!");
				    	}
			} catch (Exception ex){
		    	mensaje = "Error realizando la copia de seguridad";
	            ex.printStackTrace();
		    } finally {
		    	try {
		    		if (null != fichero)
		    			fichero.close();
		    	} catch (Exception e2) {
		    		e2.printStackTrace();
		    	}
		    }
		}
	}
	
	private static String getTagValue(String sTag, Element eElement){
		NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}