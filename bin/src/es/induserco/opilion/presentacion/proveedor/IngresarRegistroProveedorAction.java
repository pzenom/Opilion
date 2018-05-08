package es.induserco.opilion.presentacion.proveedor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Email;
import es.induserco.opilion.data.comun.contacto.Telefono;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class IngresarRegistroProveedorAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>,SessionAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad proveedor = new Entidad();
	private List listaReqHomologa;
	private Map session;

	public List getListaReqHomologa() {
		return listaReqHomologa;
	}

	public void setListaReqHomologa(List listaReqHomologa) {
		this.listaReqHomologa = listaReqHomologa;
	}

	//@Override
	public Object getModel() {	
		System.out.println("Ingresar Registro Proveedor!");
		return proveedor;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception {	
		System.out.println("Agregar proveedor!: "+proveedor.getNombre());
		List listarequisitos=new ArrayList();
		//Comprobamos que el listado de reqs exista...
		HashMap reqs = (HashMap) session.get("reqs");
		if (reqs == null ){
			reqs=new HashMap();
			session.put("reqs",reqs);
		}
		// Lista con los criterios de homologacion
		String criterios = request.getParameter("criterios");
		List listaCriterios = obtieneLista(criterios);
		System.out.println("Criterios: " + listaCriterios);

		Vector<Direccion> dirs = new Vector<Direccion>();
		Vector<Direccion> dirsContacto = new Vector<Direccion>();
		Vector<Telefono> telefonos = new Vector<Telefono>();
		Vector<Email> emails = new Vector<Email>();
		String nombreParametro = "", parametro = "";
		//obtengo el mapa de parametros
		Map <String, String[]> parametros = request.getParameterMap();
		// paso por todos los parametros
		Iterator iterator = parametros.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry e = (Map.Entry) iterator.next();
			nombreParametro = (String) e.getKey();
			System.out.println("Parametro: " + nombreParametro);
			if(nombreParametro.indexOf("lineaContacto_") > -1) {
				Direccion dir = new Direccion();
				int numeroLinea = Integer.parseInt(nombreParametro.split("_")[1]);
				System.out.println("Hemos encontrado una direccion de entrega");
				Iterator itera = parametros.entrySet().iterator();
				while(itera.hasNext()) {
					Map.Entry entry = (Map.Entry) itera.next();
					parametro = (String) entry.getKey();
					if(parametro.compareTo("provinciaContacto_" + numeroLinea) == 0) {
						dir.setIdProvincia(Long.parseLong(((String[])parametros.get(entry.getKey()))[0]));
					}else
						if(parametro.compareTo("localidadContacto_" + numeroLinea) == 0) {
							dir.setLocalidad(((String[])parametros.get(entry.getKey()))[0]);
						}else
							if(parametro.compareTo("codigoPostalContacto_" + numeroLinea) == 0) {
								dir.setCodigoPostal(((String[])parametros.get(entry.getKey()))[0]);
							}else
								if(parametro.compareTo("codigoEanDireccion_" + numeroLinea) == 0) {
									dir.setCodigoEan(((String[])parametros.get(entry.getKey()))[0]);
								}else
									if(parametro.compareTo("calleContacto_" + numeroLinea) == 0) {
										dir.setNombreCalle(((String[])parametros.get(entry.getKey()))[0]);
									}else
										if(parametro.compareTo("municipioContacto_" + numeroLinea) == 0) {
											dir.setMunicipio(((String[])parametros.get(entry.getKey()))[0]);
										}else
											if(parametro.compareTo("horarioContacto_" + numeroLinea) == 0) {
												dir.setHorario(((String[])parametros.get(entry.getKey()))[0]);
											} else
												if(parametro.compareTo("tipoContacto_" + numeroLinea) == 0) {
													dir.setTipoDireccion(((String[])parametros.get(entry.getKey()))[0]);
											}
				}
				dirsContacto.add(dir);
			}else
				if(nombreParametro.indexOf("lineaTfno_") > -1) {
					Telefono tfno = new Telefono();
					int numeroLinea = Integer.parseInt(nombreParametro.split("_")[1]);
					System.out.println("Hemos encontrado un telefono");
					Iterator itera = parametros.entrySet().iterator();
					while(itera.hasNext()) {
						Map.Entry entry = (Map.Entry) itera.next();
						parametro = (String) entry.getKey();
						if(parametro.compareTo("numeroTelefono_" + numeroLinea) == 0) {
							tfno.setNumero(((String[])parametros.get(entry.getKey()))[0]);
						}else
							if(parametro.compareTo("tipoTfno_" + numeroLinea) == 0) {
								String tipoTfno = ((String[])parametros.get(entry.getKey()))[0];
								tfno.setTipoTfno(tipoTfno);
							}else
								if(parametro.compareTo("notasTfno_" + numeroLinea) == 0) {
									String notasTfno = ((String[])parametros.get(entry.getKey()))[0];
									tfno.setNotas(notasTfno);
								}
					}
					telefonos.add(tfno);
				}else
					if(nombreParametro.indexOf("lineaEmail_") > -1) {
						Email email = new Email();
						int numeroLinea = Integer.parseInt(nombreParametro.split("_")[1]);
						System.out.println("Hemos encontrado un email");
						Iterator itera = parametros.entrySet().iterator();
						while(itera.hasNext()) {
							Map.Entry entry = (Map.Entry) itera.next();
							parametro = (String) entry.getKey();
							if(parametro.compareTo("direccionEmail_" + numeroLinea) == 0) {
								email.setDireccion(((String[])parametros.get(entry.getKey()))[0]);
							}else
								if(parametro.compareTo("tipoEmail_" + numeroLinea) == 0) {
									String tipoEmail = ((String[])parametros.get(entry.getKey()))[0];
									email.setTipo(tipoEmail);
								}else
									if(parametro.compareTo("notasEmail_" + numeroLinea) == 0) {
										String notasEmail = ((String[])parametros.get(entry.getKey()))[0];
										email.setNotas(notasEmail);
									}
						}
						emails.add(email);
					}
		}
		//proveedor.setDireccionesEntrega(dirs);
		//proveedor.setDireccionesContacto(dirsContacto);
		proveedor.setTelefonos(telefonos);
		proveedor.setEmails(emails);
		
		//Recupera el usuario
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		proveedor.setResponsable(usuario.getLogin());		
		
		System.out.println("PRESENTACION: procesando el execute de GestionProveedorAction");
		System.out.println("Registro de proveedor...");
		System.out.println(" El nombre es:"+proveedor.getNombre());
		//System.out.println(" El apellido es:" +proveedor.getApellido1());
		System.out.println(" La Razon Social es:"+proveedor.getRazonSocial());
		System.out.println(" CIF:"+proveedor.getNif());
		//System.out.println(" El correo es:"+proveedor.getEmail());
		System.out.println(" La foto esta en:"+proveedor.getFoto());
		System.out.println(" La web:"+proveedor.getWeb());
		System.out.println(" La web:"+proveedor.getResponsable());
		//request.setAttribute("addProveedor", (new GestionEntidadesHelper()).addEntidad(proveedor));
		//new GestionEntidadesHelper().addEntidad(proveedor,listarequisitos);
		//new GestionEntidadesHelper().addEntidad(proveedor, listaCriterios);
		return SUCCESS;
	}

	public String getNombre() {return proveedor.getNombre();}
	public void setNombre(String nombre) {request.setAttribute("nombre", proveedor.getNombre());}

	//@Override
	public void setSession(Map session) {
		this.session=(Map) session;
	}
	
	// Funci�n que, dado un String, lo convierte en List
	private List obtieneLista (String l){
		List lista = new ArrayList();
		String elto;
		StringTokenizer tokenizer= new StringTokenizer(l, ",");
		while (tokenizer.hasMoreTokens()) {
			elto = tokenizer.nextToken();
			lista.add(elto);
		}
		return lista;
	}
}