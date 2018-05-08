package es.induserco.edifact.data.x12;

import java.util.Calendar;

public class DTM {
	
	private Long idOrders;   //identificador de orden de referencia
	private String dtmFech;
	private String dtmForm;
	private String dtmFunc;
	private Calendar fecha;
	private String fech;
	
	public DTM() { super(); }

	public DTM(Long idOrders, String dtmFech, String dtmForm, String dtmFunc) {
		this.idOrders = idOrders;
		this.dtmFech = dtmFech; //Fecha
		this.dtmForm = dtmForm; //Formato
		this.dtmFunc = dtmFunc; //Calificador
		fecha = Calendar.getInstance();
		setFecha(dtmFech);
	}

	public Long getIdOrders()  { return idOrders; }
	public String getDtmFech() { return dtmFech; }
	public String getDtmForm() { return dtmForm; }
	public String getDtmFunc() { return dtmFunc; }
	public String getFech()    { return fech; }
	public Calendar getFecha() { return fecha; }

	public void setIdOrders(Long idOrders) { this.idOrders = idOrders; }
	public void setDtmFech(String dtmFech) { this.dtmFech = dtmFech; }
	public void setDtmForm(String dtmForm) { this.dtmForm = dtmForm; }
	public void setDtmFunc(String dtmFunc) { this.dtmFunc = dtmFunc; }
	public void setFech(String fech)       { this.fech = fech; }
	public void setFecha(Calendar fecha)   { this.fecha = fecha; }

	public void setFecha(String dtmFech) { 
		if (dtmForm.equalsIgnoreCase("101")){ //Formato AAMMDD
			fecha.set(2000+Integer.parseInt(dtmFech.substring(0,2)), Integer.parseInt(dtmFech.substring(2,4))-1, Integer.parseInt(dtmFech.substring(4,6)), 0, 0, 0);
			System.out.println("Fecha: "+fecha.getTime().toString());
			fech="20"+dtmFech.substring(0,2)+"-"+dtmFech.substring(2,4)+"-"+(dtmFech.substring(4,6))+" Hora no especificada";
		}else if (dtmForm.equalsIgnoreCase("102")){ //Formato CCAAMMDD
			fecha.set(Integer.parseInt(dtmFech.substring(0,4)), Integer.parseInt(dtmFech.substring(4,6))-1, Integer.parseInt(dtmFech.substring(6,8)), 0, 0, 0);
			System.out.println("Fecha: "+fecha.getTime().toString());
			fech=dtmFech.substring(0,4)+"-"+dtmFech.substring(4,6)+"-"+dtmFech.substring(6,8)+" Hora no especificada";
		}else if (dtmForm.equalsIgnoreCase("203")){ //Formato CCAAMMDDHHMM
			fecha.set(Integer.parseInt(dtmFech.substring(0,4)), Integer.parseInt(dtmFech.substring(4,6))-1, Integer.parseInt(dtmFech.substring(6,8)), Integer.parseInt(dtmFech.substring(8,10)), Integer.parseInt(dtmFech.substring(10,12)), 0);
			System.out.println("Fecha: "+fecha.getTime().toString());
			fech=dtmFech.substring(0,4)+"-"+dtmFech.substring(4,6)+"-"+dtmFech.substring(6,8)+" "+dtmFech.substring(8,10)+":"+dtmFech.substring(10,12);
		}else System.out.println("[ERROR] DTM FORM: "+dtmForm);
		System.out.println("WAKALA: "+fech);
	}
}