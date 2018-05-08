package es.induserco.opilion.presentacion.controlTiempos;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import es.induserco.opilion.data.entidades.GestionProduccion;
import es.induserco.opilion.data.comun.RegistroActividad;
import es.induserco.opilion.data.comun.Usuario;
import es.induserco.opilion.presentacion.GestionEnvasadoHelper;

public class ControlTiemposAction extends ActionSupport implements org.apache.struts2.interceptor.ServletRequestAware, ModelDriven<Object>{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private GestionProduccion gpro = new GestionProduccion();

	//@Override
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("ControTiemposAction...");
		this.request=request;	
	}

	//@Override
	public Object getModel(){ return gpro; }

	public String execute() throws Exception {
		System.out.println("ControTiemposAction");
		String proceso = request.getParameter("proceso");
		long horas = 0, minutos = 0;
		Vector<Usuario> responsables = new Vector<Usuario>();
		String tipo = "";
		if (proceso.indexOf("EN") == 0){
			tipo = "Envasado";
			//Proceso de envasado
			Vector<RegistroActividad> registros = new GestionEnvasadoHelper().getRegistrosActividad(proceso);
			request.getSession().setAttribute("registros", registros);
			//Calculamos el tiempo total que llevó el proceso
			boolean iniciado = false;
			Timestamp horaInicio = null;
			Timestamp horaFin = null;
			Calendar fechaInicio;
			String nombreResponsable = "";
			fechaInicio = GregorianCalendar.getInstance();
			long difHoras = 0;
			long difMinutos = 0;
			for (int i = 0; i < registros.size(); i++){
				RegistroActividad regi = registros.elementAt(i);
				String observacion = regi.getObservaciones();
				if (observacion.indexOf("FINALIZADO") == 0){
					//horaFin = regi.getHoraFin();
					horaFin = regi.getHoraInicio();
					//fechaFin.setTime(horaFin);
					//long h = fechaInicio.HOUR;
					//fechaFin.setTimeInMillis(horaFin.getTime()); 
					//long m = fechaInicio.MINUTE;
					long h = horaInicio.getHours();
					long m = horaInicio.getMinutes();
					//long difHoras = fechaFin.HOUR - h;
					difHoras = horaFin.getHours() - h;
					difMinutos = 0;
					//difMinutos = fechaFin.MINUTE - m;
					difMinutos = horaFin.getMinutes() - m;
					if (difMinutos < 0){
						difHoras--;
						difMinutos += 60;
					}
					horas += difHoras;
					minutos += difMinutos;
					//u.setTiempo(u.getTiempo() + difMinutos + (difHoras * 60));
				}else
					if (observacion.indexOf("PAUSADO") == 0){
						//horaFin = regi.getHoraFin();
						horaFin = regi.getHoraInicio();
						//fechaFin.setTime(horaFin);
						//long h = fechaInicio.HOUR;
						//fechaFin.setTimeInMillis(horaFin.getTime()); 
						//long m = fechaInicio.MINUTE;
						long h = horaInicio.getHours();
						long m = horaInicio.getMinutes();
						//long difHoras = fechaFin.HOUR - h;
						difHoras = horaFin.getHours() - h;
						difMinutos = 0;
						//difMinutos = fechaFin.MINUTE - m;
						difMinutos = horaFin.getMinutes() - m;
						if (difMinutos < 0){
							difHoras--;
							difMinutos += 60;
						}
						horas += difHoras;
						minutos += difMinutos;
						
					}else
						if (observacion.indexOf("iniciado") > -1){
							if (!iniciado){
								horas = 0;
								minutos = 0;
							}
							iniciado = true;
							horaInicio = regi.getHoraInicio();
							fechaInicio.setTime(horaInicio);
						}
				nombreResponsable = regi.getResponsable();
				boolean flag = false;
				for (int j = 0; j < responsables.size(); j++){
					if (!flag && responsables.elementAt(j).getLogin().compareTo(nombreResponsable) == 0){
						responsables.elementAt(j).setTiempo(responsables.elementAt(j).getTiempo() + difMinutos);
						flag = true;
					}
					/*if (flag){
						flag = false;
					}*/
				}
				if (!flag){
					Usuario u = new Usuario();
					u.setLogin(nombreResponsable);
					if (difMinutos > 0)
						u.setTiempo(difMinutos);
					responsables.add(u);
				}
				//u.setTiempo(u.getTiempo() + difMinutos + (difHoras * 60));
			}
			int horasExtra = (int)(horas / 60);
			horas += horasExtra;
			long minutosExtra = horas%60;
			minutos += minutosExtra;
		}
		//TODO Cargamos la fecha de caducidad para poder modificarla
		request.getSession().setAttribute("fechaCaducidad", new GestionEnvasadoHelper().getProcesosEnvasado(proceso, "", "", 0, null, ' ').get(0).getFechaCaducidad());
		request.getSession().setAttribute("responsables", responsables);
		request.getSession().setAttribute("proceso", proceso);
		request.getSession().setAttribute("horas", horas);
		request.getSession().setAttribute("minutos", minutos);
		if (tipo.compareTo("Envasado") == 0){
			return "ENVASADO";
		}
		return ERROR;
	}
}