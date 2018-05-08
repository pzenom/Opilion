package es.induserco.opilion.presentacion.entidad;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.comun.Rol;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.data.comun.banca.DatoBancario;
import es.induserco.opilion.data.comun.contacto.Direccion;
import es.induserco.opilion.data.comun.contacto.Email;
import es.induserco.opilion.data.comun.contacto.Telefono;
import es.induserco.opilion.data.comun.contacto.TipoProveedor;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.presentacion.GestionEntidadesHelper;

public class IngresaEntidadAction extends ActionSupport implements
org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>,SessionAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Entidad entidad = new Entidad();
	private Map session;
	
//@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;			
	}

	//@Override
	public Object getModel() {	
		System.out.println("Agregar entidad!");		
		return entidad;
	}

	public String execute() throws Exception {
		System.out.println("IngresaEntidadAction");
		Vector<Direccion> direcciones = new Vector<Direccion>();
		Vector<Telefono> telefonos = new Vector<Telefono>();
		Vector<Email> emails = new Vector<Email>();
		Vector<DatoBancario> formasDePago = new Vector<DatoBancario>();
		Vector<TipoProveedor> tiposProveedor = new Vector<TipoProveedor>();
		Vector<Rol> rolesEntidad = new Vector<Rol>();
		String rolesEntidadString = request.getParameter("rolesDeLaEntidad");
		for (int i = 0; i < rolesEntidadString.length(); i++){
			Rol rol = new Rol();
			if (rolesEntidadString.charAt(i) == 'C')
				rol.setIdRol((long)1);
			else
				if (rolesEntidadString.charAt(i) == 'P')
					rol.setIdRol((long)2);
			rolesEntidad.add(rol);
		}
		long sectorCliente = Long.parseLong(request.getParameter("sectorEntidad"));
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
											}else
												if(parametro.compareTo("apellidos" + numeroLinea) == 0) {
													dir.setTipoDireccion(((String[])parametros.get(entry.getKey()))[0]);
											}
				}
				direcciones.add(dir);
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
									if(parametro.compareTo("notasEmail_" + numeroLinea) == 0) {
										String notasEmail = ((String[])parametros.get(entry.getKey()))[0];
										email.setNotas(notasEmail);
									}
						}
						emails.add(email);
					}else
						if(nombreParametro.indexOf("lineaFormaPago_") > -1) {
							String iban = "", banco = "", ofici = "",
								contr = "", numer = "";
							DatoBancario formaPago = new DatoBancario();
							int numeroLinea = Integer.parseInt(nombreParametro.split("_")[1]);
							System.out.println("Hemos encontrado una nueva forma de pago");
							Iterator itera = parametros.entrySet().iterator();
							while(itera.hasNext()) {
								Map.Entry entry = (Map.Entry) itera.next();
								parametro = (String) entry.getKey();
								if(parametro.compareTo("formaPago_" + numeroLinea) == 0) {
									formaPago.setIdFormaPago(Long.parseLong(((String[])parametros.get(entry.getKey()))[0]));
								}else
									if(parametro.compareTo("idFormaPagoDesde_" + numeroLinea) == 0) {
										formaPago.setIdFormaPagoDesde(Long.parseLong(((String[])parametros.get(entry.getKey()))[0]));
									}else
										if(parametro.compareTo("diasFormaPago_" + numeroLinea) == 0) {
											formaPago.setDiasFormaPago(Long.parseLong(((String[])parametros.get(entry.getKey()))[0]));
										}else
											if(parametro.compareTo("diaPago_" + numeroLinea) == 0) {
												formaPago.setDiaPago(Long.parseLong(((String[])parametros.get(entry.getKey()))[0]));
											}else
												if(parametro.compareTo("cuenta_iban_" + numeroLinea) == 0) {
													iban = ((String[])parametros.get(entry.getKey()))[0];
												}else
													if(parametro.compareTo("cuenta_banco_" + numeroLinea) == 0) {
														banco = ((String[])parametros.get(entry.getKey()))[0];
													}else
														if(parametro.compareTo("cuenta_ofici_" + numeroLinea) == 0) {
															ofici = ((String[])parametros.get(entry.getKey()))[0];
														}else
															if(parametro.compareTo("cuenta_contr_" + numeroLinea) == 0) {
																contr = ((String[])parametros.get(entry.getKey()))[0];
															}else
																if(parametro.compareTo("cuenta_numer_" + numeroLinea) == 0) {
																	numer = ((String[])parametros.get(entry.getKey()))[0];
																}
							}
							String numeroCuenta = iban + banco + ofici + contr + numer;
							formaPago.setNumCuenta(numeroCuenta);
							formasDePago.add(formaPago);
						}else
							if (nombreParametro.indexOf("tipoProveedor_") > -1){
								long idProveedor = Long.parseLong(nombreParametro.split("_")[1]);
								TipoProveedor tipo = new TipoProveedor();
								tipo.setIdTipo(idProveedor);
								tiposProveedor.add(tipo);
							}
		}
		entidad.setDirecciones(direcciones);
		entidad.setTelefonos(telefonos);
		entidad.setEmails(emails);
		entidad.setFormasPagoEntidad(formasDePago);
		entidad.setAutonomoEmpresa(this.request.getParameter("autonomoEmpresa").charAt(0));
		entidad.setRolesEntidad(rolesEntidad);
		entidad.setTiposProveedor(tiposProveedor);
		entidad.setIdSectorCliente(sectorCliente);
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		entidad.setResponsable(usuario.getLogin());
		System.out.println("PRESENTACION: procesando el execute de GestionentryAction");
		System.out.println("Registro de entry...");
		System.out.println(" El nombre es:" + entidad.getNombre());
		System.out.println(" La Razon Social es:" + entidad.getRazonSocial());
		System.out.println(" CIF:" + entidad.getNif());
		System.out.println(" La foto esta en:" + entidad.getFoto());
		System.out.println(" La web:" + entidad.getWeb());
		new GestionEntidadesHelper().addEntidad(entidad);
		return SUCCESS;			
	}

	//@Override
	public void setSession(Map session) {
		this.session = (Map) session;
	}
}