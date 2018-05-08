package es.induserco.opilion.presentacion.cliente;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Email;
import es.induserco.opilion.data.comun.contacto.Telefono;
import es.induserco.opilion.data.entidades.Entidad;

public class ActualizarClienteAction extends ActionSupport implements
	org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad clienteFind = new Entidad();
	private Entidad clienteActualiza = new Entidad();	
	
	//@Override
	public Object getModel() {	
		return clienteActualiza;
	}

	//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	public String execute() throws Exception {
		//Leemos las direcciones de entrega y contacto para el cliente
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
				System.out.println("Hemos encontrado una direccion de contacto");
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
							if(parametro.compareTo("codigoEanDireccion_" + numeroLinea) == 0) {
								dir.setCodigoEan(((String[])parametros.get(entry.getKey()))[0]);
							}else
								if(parametro.compareTo("codigoPostalContacto_" + numeroLinea) == 0) {
									dir.setCodigoPostal(((String[])parametros.get(entry.getKey()))[0]);
								}else
									if(parametro.compareTo("calleContacto_" + numeroLinea) == 0) {
										dir.setNombreCalle(((String[])parametros.get(entry.getKey()))[0]);
									}else
										if(parametro.compareTo("municipioContacto_" + numeroLinea) == 0) {
											dir.setMunicipio(((String[])parametros.get(entry.getKey()))[0]);
										}else
											if(parametro.compareTo("horarioContacto_" + numeroLinea) == 0) {
												dir.setHorario(((String[])parametros.get(entry.getKey()))[0]);
											}else
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
								if(parametro.compareTo("idTelefono_" + numeroLinea) == 0) {
									tfno.setIdTelefono(Long.parseLong(((String[])parametros.get(entry.getKey()))[0]));
								}else
									if(parametro.compareTo("notasTelefono_" + numeroLinea) == 0) {
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
									if(parametro.compareTo("idEmail_" + numeroLinea) == 0) {
										email.setIdEmail(Long.parseLong(((String[])parametros.get(entry.getKey()))[0]));
									}else
										if(parametro.compareTo("notasEmail_" + numeroLinea) == 0) {
											String notasEmail = ((String[])parametros.get(entry.getKey()))[0];
											email.setNotas(notasEmail);
										}
						}
						emails.add(email);
					}
		}
		clienteActualiza.setTelefonos(telefonos);
		clienteActualiza.setEmails(emails);
		//clienteActualiza.setDireccionesEntrega(dirs);
		//clienteActualiza.setDireccionesContacto(dirsContacto);
		//clienteActualiza.setAutonomoEmpresa(this.request.getParameter("autonomoEmpresa").charAt(0));
		clienteActualiza.setIdUsuario((Long)request.getSession().getAttribute("idUsuario"));
		clienteFind.setIdUsuario((Long)request.getSession().getAttribute("idUsuario"));
		System.out.println("PRESENTACION: Actualizar Cliente");
		System.out.println(" El nombre es: " + clienteFind.getIdUsuario());
		System.out.println(" El nombre es:" + clienteActualiza.getNombre());
		//new GestionEntidadesHelper().updateCliente(clienteFind, clienteActualiza);
		//Entidad cliente = new GestionEntidadesHelper().loadCliente(clienteFind);
		//request.getSession().setAttribute("listaregistros", cliente);
		//request.getSession().setAttribute("listaDireccionesEntrega", cliente.getDireccionesEntrega());
		return SUCCESS;
	}
}