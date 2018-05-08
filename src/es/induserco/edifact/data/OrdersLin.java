package es.induserco.edifact.data;

import java.util.ArrayList;
import java.util.List;

import es.induserco.edifact.data.x12.IMD;

public class OrdersLin {
	
	private String bgmNum, descripcionProducto, descripcionFormaPago;
	private Long idOrders, idFormaPago, idProducto;
	private String idLin, lineaProcesada, linNum, piaNumIn;
	private String piaNumSa;
	private String imdTipo;
	private String imdCara;
	private String imdDesc;
	private String imdDescCod;
	private String qty21Cant;
	private String qty59Cant;
	private String moa203;
	private String priAaa, priAab, priInf;
	private List <OrdersLoc> loc;
	private List <IMD> imd;
	private String tipoOrdena;
	private String nombreProducto, observaciones;
	private double peso;
	private int numeroAgrupaciones;
	private double pesoLinea;
	private int EANs13;

	public OrdersLin() { super();
		loc = new ArrayList<OrdersLoc>();
		imd = new ArrayList<IMD>();
	}

	public OrdersLin(Long idOrders, String idLin, String linNum, String piaNumIn,
			String piaNumSa, String imdTipo, String imdCara, String imdDesc,
			String imdDescCod, String qty21Cali, String qty21Cant,
			String qty59Cali, String qty59Cant, String moa203, String priAaa,
			String priAab, String priInf, List<OrdersLoc> loc, List <IMD> imd, String tipoOrdena) {
		this.idOrders = idOrders;
		this.idLin = idLin;
		this.linNum = linNum;
		this.piaNumIn = piaNumIn;
		this.piaNumSa = piaNumSa;
		this.imdTipo = imdTipo;
		this.imdCara = imdCara;
		this.imdDesc = imdDesc;
		this.imdDescCod = imdDescCod;
		this.qty21Cant = qty21Cant;
		this.qty59Cant = qty59Cant;
		this.moa203 = moa203;
		this.priAaa = priAaa;
		this.priAab = priAab;
		this.priInf = priInf;
		this.loc = loc;
		this.imd = imd;
		this.tipoOrdena = tipoOrdena;
	}

	public Long getIdOrders() 					{ return idOrders; }
	public String getIdLin() 					{ return idLin; }
	public String getLinNum() 					{ return linNum; }
	public String getPiaNumIn()					{ return piaNumIn; }
	public String getPiaNumSa()					{ return piaNumSa; }
	public String getImdTipo() 					{ return imdTipo; }
	public String getImdCara() 					{ return imdCara; }
	public String getImdDesc() 					{ return imdDesc; }
	public String getImdDescCod() 				{ return imdDescCod; }
	
	/**
	 * Gets the qty21 cant.
	 *
	 * @return the qty21 cant
	 */
	public String getQty21Cant() 				{ return qty21Cant; }
	
	/**
	 * Gets the qty59 cant.
	 *
	 * @return the qty59 cant
	 */
	public String getQty59Cant() 				{ return qty59Cant; }
	
	/**
	 * Gets the moa203.
	 *
	 * @return the moa203
	 */
	public String getMoa203() 					{ return moa203; }
	
	/**
	 * Gets the pri aaa.
	 *
	 * @return the pri aaa
	 */
	public String getPriAaa() 					{ return priAaa; }
	
	/**
	 * Gets the pri aab.
	 *
	 * @return the pri aab
	 */
	public String getPriAab() 					{ return priAab; }
	
	/**
	 * Gets the pri inf.
	 *
	 * @return the pri inf
	 */
	public String getPriInf() 					{ return priInf; }
	
	/**
	 * Gets the loc.
	 *
	 * @return the loc
	 */
	public List<OrdersLoc> getLoc() 			{ return loc; }
	
	/**
	 * Gets the imd.
	 *
	 * @return the imd
	 */
	public List<IMD> getImd() 					{ return imd; }
	
	/**
	 * Sets the id orders.
	 *
	 * @param idOrders the new id orders
	 */
	public void setIdOrders(Long idOrders) 		{ this.idOrders = idOrders; }
	
	/**
	 * Sets the id lin.
	 *
	 * @param idLin the new id lin
	 */
	public void setIdLin(String idLin) 			{ this.idLin = idLin; }
	
	/**
	 * Sets the lin num.
	 *
	 * @param linNum the new lin num
	 */
	public void setLinNum(String linNum) 		{ this.linNum = linNum; }
	
	/**
	 * Sets the pia num in.
	 *
	 * @param piaNumIn the new pia num in
	 */
	public void setPiaNumIn(String piaNumIn)	{ this.piaNumIn = piaNumIn; }
	
	/**
	 * Sets the pia num sa.
	 *
	 * @param piaNumSa the new pia num sa
	 */
	public void setPiaNumSa(String piaNumSa)	{ this.piaNumSa = piaNumSa; }
	
	/**
	 * Sets the imd tipo.
	 *
	 * @param imdTipo the new imd tipo
	 */
	public void setImdTipo(String imdTipo) 		{ this.imdTipo = imdTipo; }
	
	/**
	 * Sets the imd cara.
	 *
	 * @param imdCara the new imd cara
	 */
	public void setImdCara(String imdCara) 		{ this.imdCara = imdCara; }
	
	/**
	 * Sets the imd desc.
	 *
	 * @param imdDesc the new imd desc
	 */
	public void setImdDesc(String imdDesc) 		{ this.imdDesc = imdDesc; }
	
	/**
	 * Sets the imd desc cod.
	 *
	 * @param imdDescCod the new imd desc cod
	 */
	public void setImdDescCod(String imdDescCod) { this.imdDescCod = imdDescCod; }
	
	/**
	 * Sets the qty21 cant.
	 *
	 * @param qty21Cant the new qty21 cant
	 */
	public void setQty21Cant(String qty21Cant) 	{ this.qty21Cant = qty21Cant; }
	
	/**
	 * Sets the qty59 cant.
	 *
	 * @param qty59Cant the new qty59 cant
	 */
	public void setQty59Cant(String qty59Cant) 	{ this.qty59Cant = qty59Cant; }
	
	/**
	 * Sets the moa203.
	 *
	 * @param moa203 the new moa203
	 */
	public void setMoa203(String moa203) 		{ this.moa203 = moa203; }
	
	/**
	 * Sets the pri aaa.
	 *
	 * @param priAaa the new pri aaa
	 */
	public void setPriAaa(String priAaa) 		{ this.priAaa = priAaa; }
	
	/**
	 * Sets the pri aab.
	 *
	 * @param priAab the new pri aab
	 */
	public void setPriAab(String priAab) 		{ this.priAab = priAab; }
	
	/**
	 * Sets the pri inf.
	 *
	 * @param priInf the new pri inf
	 */
	public void setPriInf(String priInf) 		{ this.priInf = priInf; }
	
	/**
	 * Sets the loc.
	 *
	 * @param loc the new loc
	 */
	public void setLoc(List<OrdersLoc> loc) 	{ this.loc = loc; }
	
	/**
	 * Sets the loc.
	 *
	 * @param loc the new loc
	 */
	public void setLoc(OrdersLoc loc) 			{ this.loc.add(loc); }
	
	/**
	 * Sets the imd.
	 *
	 * @param imd the new imd
	 */
	public void setImd(List<IMD> imd) 			{ this.imd = imd; }
	
	/**
	 * Sets the imd.
	 *
	 * @param imd the new imd
	 */
	public void setImd(IMD imd)		 			{ this.imd.add(imd); }

	public void setTipoOrdena(String string) {
		this.tipoOrdena = string;
	}

	public String getTipoOrdena() {
		return this.tipoOrdena;
	}
	
	public double getPeso(){return this.peso;}
	public void setPeso(double peso){this.peso = peso;}
	
	public String getNombreProducto(){ return this.nombreProducto; }
	public void setNombreProducto(String nombre){ this.nombreProducto = nombre; }

	public void setNumeroAgrupaciones(int numeroAgrupaciones) {
		this.numeroAgrupaciones = numeroAgrupaciones;
	}
	
	public int getNumeroAgrupaciones(){
		return this.numeroAgrupaciones;
	}

	public void setBgmNum(String bgmNum) {
		this.bgmNum = bgmNum;
	}

	public String getBgmNum() {
		return bgmNum;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
	}

	public String getDescripcionFormaPago() {
		return descripcionFormaPago;
	}

	public void setIdFormaPago(Long idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public Long getIdFormaPago() {
		return idFormaPago;
	}

	public void setLineaProcesada(String lineaProcesada) {
		this.lineaProcesada = lineaProcesada;
	}

	public String getLineaProcesada() {
		return lineaProcesada;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setPesoLinea(double pesoLinea) {
		this.pesoLinea = pesoLinea;
	}

	public double getPesoLinea() {
		return pesoLinea;
	}

	public void setEANs13(int eANs13) {
		EANs13 = eANs13;
	}

	public int getEANs13() {
		return EANs13;
	}
}