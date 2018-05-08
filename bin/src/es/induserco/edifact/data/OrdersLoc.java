package es.induserco.edifact.data;

public class OrdersLoc {
	
	private Long idOrders;
	private String idLin;
	private String loc;
	private String qty;
	private String descripcion;
	private String localizacionProcesada;
	
	public OrdersLoc() { super(); }

	public OrdersLoc(Long idOrders, String idLin, String loc, String qty) {
		this.idOrders = idOrders;
		this.idLin = idLin;
		this.loc = loc;
		this.qty = qty;
	}

	public Long getIdOrders() 				{ return idOrders; }
	public String getIdLin() 				{ return idLin; }
	public String getLoc() 					{ return loc; }
	public String getQty() 					{ return qty; }
	public String getDescripcion()			{ return descripcion; }
	public String getLocalizacionProcesada()		{ return localizacionProcesada; }
	
	public void setIdOrders(Long idOrders) 	{ this.idOrders = idOrders; }
	public void setIdLin(String idLin) 		{ this.idLin = idLin; }
	public void setLoc(String loc) 			{ this.loc = loc; }
	public void setQty(String qty) 			{ this.qty = qty; }
	public void setDescripcion(String d)	{ this.descripcion = d; }
	public void setLocalizacionProcesada(String l)	{ this.localizacionProcesada = l; }
}